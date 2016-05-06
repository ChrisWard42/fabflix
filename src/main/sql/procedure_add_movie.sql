/*
Add Movie Procedure

Defines the procedure for inserting or updating a movie in the database. Takes in movie info,
star info, and genre info values from the employee dashboard as passed by dashboard servlet, 
along with a 'src' parameter defining whether to run insert or update.
*/

-- Set delimiter for stored procedure
DELIMITER |

DROP PROCEDURE IF EXISTS add_movie |

-- Procedure definition
CREATE PROCEDURE add_movie(IN mv_title VARCHAR(100),
    IN mv_year INTEGER, IN mv_director VARCHAR(100),
    IN mv_banner VARCHAR(200), IN mv_trailer VARCHAR(200),
    IN st_fname VARCHAR(50), IN st_lname VARCHAR(50),
    IN st_dob DATE, IN st_photo VARCHAR(200),
    IN gn_name VARCHAR(32), IN src INTEGER,
    OUT status INTEGER, OUT output VARCHAR(200))

BEGIN

-- 0. Declare Variables and check if star/genre provided
DECLARE mv_exists, st_provided, st_exists, gn_provided, gn_exists BOOLEAN DEFAULT 0;
DECLARE mv_id, st_id, gn_id INTEGER DEFAULT 0;

SET status = 0;
SET output = "";

IF st_lname != "" THEN
    SET st_provided = 1;
END IF;

IF gn_name != "" THEN
    SET gn_provided = 1;
END IF;

-- 1. Check if Movie exists in database
SELECT EXISTS(SELECT 1 FROM movies
    WHERE title = mv_title AND year = mv_year
    AND director = mv_director
    LIMIT 1)
    INTO mv_exists;

IF mv_exists = 1 THEN
    SELECT id INTO mv_id FROM movies
    WHERE title = mv_title AND year = mv_year
    AND director = mv_director
    LIMIT 1;
END IF;

-- 2. Insert/Update movie entry if valid procedure
IF src = 0 THEN
    IF mv_exists = 0 THEN
        INSERT INTO movies(title, year, director, banner_url, trailer_url)
        VALUES(mv_title, mv_year, mv_director, mv_banner, mv_trailer);
        SELECT LAST_INSERT_ID() INTO mv_id;
    ELSE
        SET status = 1;
        SET output = "Error. Movie already exists so insert failed. Try updating instead.";
    END IF;
END IF;
IF src = 1 THEN
    IF mv_exists = 0 THEN
        SET status = 2;
        SET output = "Error. Movie doesn't exist so update failed. Try inserting instead.";
    END IF;
END IF;

-- 3. Check if Star exists in database if it was provided
IF st_provided = 1 AND status = 0 THEN
    SELECT EXISTS(SELECT 1 FROM stars
    WHERE first_name = st_fname AND last_name = st_lname
    LIMIT 1)
    INTO st_exists;

    IF st_exists = 1 THEN
        SELECT id INTO st_id FROM stars
        WHERE first_name = st_fname AND last_name = st_lname
        LIMIT 1;
    END IF;
END IF;

-- 4. If star does not exist and was provided, insert new star
IF st_provided = 1 AND st_exists = 0 AND status = 0 THEN
    INSERT INTO stars(first_name, last_name, dob, photo_url)
    VALUES (st_fname, st_lname, st_dob, st_photo);
    SELECT LAST_INSERT_ID() INTO st_id;
END IF;

-- 5. Check if Genre exists in database if it was provided
IF gn_provided = 1 AND status = 0 THEN
    SELECT EXISTS(SELECT 1 FROM genres
    WHERE name = gn_name
    LIMIT 1)
    INTO gn_exists;

    IF gn_exists = 1 THEN
        SELECT id INTO gn_id FROM genres
        WHERE name = gn_name
        LIMIT 1;
    END IF;
END IF;

-- 6. If genre does not exist, insert new genre
IF gn_provided = 1 AND gn_exists = 0 AND status = 0 THEN
    INSERT INTO genres(name) VALUES (gn_name);
    SELECT LAST_INSERT_ID() INTO gn_id;
END IF;

-- 7. Link entries via stars_in_movies if Star was provided
IF st_provided = 1 AND status = 0 THEN
    INSERT INTO stars_in_movies(star_id, movie_id)
    VALUES (st_id, mv_id);
END IF;

-- 8. Link entries via genres_in_movies if Genre was provided
IF gn_provided = 1 AND status = 0 THEN
    INSERT INTO genres_in_movies(genre_id, movie_id)
    VALUES (gn_id, mv_id);
END IF;

IF status = 0 AND src = "insertmovie" THEN
    SET output = "Success. Movie was inserted without issue.";
ELSEIF status = 0 AND src = "updatemovie" THEN
    SET output = "Success. Movie was updated without issue.";
END IF;

END |

-- Reset delimiter to SQL default
DELIMITER ;