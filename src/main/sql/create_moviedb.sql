/*
Create Table Statements

Note: NOT NULL constraint only enforces required property when MySQL has strict mode
enabled. This is true by default for MySQL 5.7.5 and later, but earlier versions would
need to have the flag STRICT_TRANS_TABLES set for desired behavior.
*/

-- Comment these out if db already created and selecting db to use from cmd line
-- CREATE DATABASE moviedb;
-- USE moviedb;

-- Create the tables if they don't already exist
CREATE TABLE IF NOT EXISTS movies
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year INTEGER NOT NULL,
    director VARCHAR(100) NOT NULL,
    banner_url VARCHAR(200),
    trailer_url VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stars
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dob DATE,
    photo_url VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stars_in_movies
(
    star_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    FOREIGN KEY (star_id) REFERENCES stars(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS genres
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS genres_in_movies
(
    genre_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS creditcards
(
    id VARCHAR(20) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    expiration DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customers
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    cc_id VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cc_id) REFERENCES creditcards(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sales
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    customer_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    sale DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS employees
(
    email VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    fullname VARCHAR(100),
    PRIMARY KEY (email)
);