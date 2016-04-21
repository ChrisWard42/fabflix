<tr>
    <td style="padding: 20px">
        <h3 style="text-align: center">LIKE Predicate Usage</h3><br><br>

        The LIKE predicate is used in Movie.java.
        Movie.java is a class to represent an entry in the movie table, and also has static
        methods to search for a movie. There are two searchMovies methods, one with one string
        parameter and one with four string parameters. There is also a getMovieByLetter
        method, which searches for movies starting with a particular letter.<br><br>

        The way the LIKE predicate works is, when saying LIKE in a SQL query, there must
        be a string with the '%' symbol on the left side, right side, or both sides of
        the string. The '%' symbol works very much like the '*' symbol in linux bash, it
        is a wildcard symbol. So if a user searches for "Hunger" and in the SQL query
        the user's input is surrounded by '%' on both sides, like so, %Hunger%, a match
        will be returned if the substring "Hunger" is found in whatever the LIKE
        predicate is being used on such as the movie title. If there is only one '%' to
        the left of a keyword, then a match is returned if anything ends with that
        keyword. If there is only one '%' on the right side of the keyword, a match is
        returned if anything starts with the keyword. There are more symbols that can be
        used with the LIKE predicate, but those are the only ones used in our queries.<br><br>

        The first searchMovies method only has one string parameter, and that parameter
        is the keywords a user will enter in the general, global search. This search
        located at the top of every webpage of our fabflix website, and the keywords
        will use the LIKE predicate with '%' surrounding both sides. The LIKE predicate
        searches for any matches in a movie's title, year, director or star. It is used
        here because a user may only know part of a movie's title or other attribute, so
        the results will return anything containing what the substring of what the user
        has entered. This makes it easier for the user to find what they are looking for
        because they do not need to remember the entire, exact name of an attribute,
        just a portion of it.<br><br>

        The second searchMovies method has four string parameters, for the same
        attributes, title, year, director and star. This method is used for the advanced
        search on our webpage, which is accessible after searching for anything in our
        global search at the top of any page. Each attribute has it's own search field
        in the advanced search and a query will be generated based on what the user
        entered. Any combonation of the fields entered will be ANDed together and all
        fields use the LIKE predicate for the same reason it is used for the golbal
        search: the user may not know the full name of what they are entering, so to
        make it easier to get what the user is looking for, they only need to put in a
        portion of whichever movie attribute they are looking for.<br><br>

        The getMovieByLetter method returns a list of movie titles starting with a
        particular letter. This is used in the browse page when a user clicks on a
        letter A-Z or number 0-9. The LIKE predicate is used here by simply having a '%'
        on the right side of what the user clicked on, so if a user clicks on 'A', the
        like predicate would look like "LIKE A%". This will return any match that starts
        with A.
    </td>
</tr>