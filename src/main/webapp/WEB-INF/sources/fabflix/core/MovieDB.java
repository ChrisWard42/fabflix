package fabflix.core;

import fabflix.beans.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.*;

public class MovieDB{

	public static synchronized Connection getConnection(DataSource ds) throws SQLException{
		return ds.getConnection();
	}

	public static List<MovieInfo> searchMovies(String keywords, DataSource ds){
	    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
	    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

	    if(!keywords.isEmpty()){
	        // String loginUser = "testuser";
	        // String loginPasswd = "testpass";
	        // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

	        keywords = toBooleanKeywords(keywords);

	        Connection connection = null;

	        try {
	          // Class.forName("com.mysql.jdbc.Driver").newInstance();

	          // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

	          // Context initCtx = new InitialContext();
	          // Context envCtx = (Context) initCtx.lookup("java:comp/env");

	          // DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");

	          connection = getConnection(ds);
            connection.setReadOnly(true);

	          String query = "SELECT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " +
	          "FROM movies AS m " +
	          "WHERE MATCH(title) AGAINST( " + keywords + " IN BOOLEAN MODE) OR MATCH(director) AGAINST( " + keywords + " IN BOOLEAN MODE) OR " +
	          "m.id IN(SELECT movie_id FROM stars_in_movies AS sim WHERE " +
	          "  sim.star_id in(SELECT id " +
	          "                 FROM stars AS s2   " +
	          "                 WHERE MATCH(first_name, last_name) AGAINST( " + keywords + " IN BOOLEAN MODE)) " +
	          " );";

            Statement statement = connection.createStatement();
	          // PreparedStatement statement = connection.prepareStatement(query);
	          // statement.setString(1, keywords);
	          // statement.setString(2, keywords);
	          // statement.setString(3, keywords);

	          searchResultsMap = getMovieResultss(statement, connection, query);

	          statement.close();
	        } 
	        catch (Exception e) {
	          e.printStackTrace();
	        }
	        finally {
				try {if (connection != null) connection.close();} catch (SQLException e) {}
    		}
		}// end if(!keywords.isEmpty())
		for(MovieInfo value : searchResultsMap.values())
			searchResults.add(value);

		return searchResults;
	}

  public static List<MovieInfo> searchMovies(String title, String star, String year, String director, DataSource ds){
    if(title == null)
      title = "";
    if(star == null)
      star = "";
    if(year == null)
      year = "";
    if(director == null)
      director = "";

    //map of attributes that were entered, keys are their prepared statement index, value the attributes
    Map<Integer, String> attributeMap = new HashMap<>();

    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    // String loginUser = "testuser";
    // String loginPasswd = "testpass";
    // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    MovieInfo movie = null;
    try {
      // Class.forName("com.mysql.jdbc.Driver").newInstance();

      // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

      Connection connection = getConnection(ds);
      connection.setReadOnly(true);

      StringBuilder query = new StringBuilder("SELECT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " +
      "FROM movies AS m " +
      "WHERE ");

      //n is used for the index of the statement (e.g. setString(n, attribute))
      int n = 0;

      if(!title.isEmpty()){
        query.append("MATCH(title) AGAINST( ? IN BOOLEAN MODE) AND ");
        title = toBooleanKeywords(title);
        attributeMap.put(++n, title);
      }

      if(!year.isEmpty()){
        query.append("year = ? AND ");
        attributeMap.put(++n, year);
      }

      if(!director.isEmpty()){
        query.append("MATCH(director) AGAINST( ? IN BOOLEAN MODE) AND ");
        director = toBooleanKeywords(director);
        attributeMap.put(++n, director);
      }

      if(!star.isEmpty()){
        query.append("m.id IN(SELECT movie_id FROM stars_in_movies AS sim WHERE " +
                      "  sim.star_id in(SELECT id " +
                      "                 FROM stars AS s2   " +
                      "                 WHERE MATCH(first_name, last_name) AGAINST(? IN BOOLEAN MODE)) " +
                      " )");
        star = toBooleanKeywords(star);
        attributeMap.put(++n, star);
      }
      //trim off excess AND if no star was inputted
      else{
        query.delete(query.length() - 4, query.length());
      }
      query.append(";");

      PreparedStatement statement = connection.prepareStatement(query.toString());

      //set prepared statements according to which attributes were entered
      for(Map.Entry<Integer, String> entry : attributeMap.entrySet()){
        statement.setString(entry.getKey(), entry.getValue());
      }

      searchResultsMap = getMovieResults(statement, connection);

      statement.close();
      connection.close();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    for(MovieInfo value : searchResultsMap.values())
        searchResults.add(value);

    return searchResults;
  }

  public static List<MovieInfo> browseMoviesByGenre(String genre, DataSource ds){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    // String loginUser = "testuser";
    // String loginPasswd = "testpass";
    // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    try {
          // Class.forName("com.mysql.jdbc.Driver").newInstance();

          // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          Connection connection = getConnection(ds);
          connection.setReadOnly(true);

          String query = "SELECT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " +
          "FROM movies AS m " +
          "WHERE " +
          "m.id IN(SELECT movie_id FROM genres_in_movies AS gim WHERE " +
          "  gim.genre_id in(SELECT id " +
          "                 FROM genres AS g2   " +
          "                 WHERE g2.name = ? ) " +
          " );";

          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, genre);

          searchResultsMap = getMovieResults(statement, connection);

          statement.close();
          connection.close();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }

    for(MovieInfo value : searchResultsMap.values())
        searchResults.add(value);

    return searchResults;
  }

  public static List<MovieInfo> browseMoviesByLetter(String letter, DataSource ds){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    // letter = letter + "*";
    if(!letter.isEmpty())
      letter = letter + "%";

    // String loginUser = "testuser";
    // String loginPasswd = "testpass";
    // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    MovieInfo movie = null;
    try {
          // Class.forName("com.mysql.jdbc.Driver").newInstance();

          // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          Connection connection = getConnection(ds);
          connection.setReadOnly(true);
          String query = "";
          PreparedStatement statement = null;

          if(!letter.isEmpty()){
            query = "SELECT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " +
            "FROM movies AS m " +
            "WHERE title LIKE ?";

            statement = connection.prepareStatement(query);
            statement.setString(1, letter);
          } else {
            query = "SELECT * FROM  movies";

            statement = connection.prepareStatement(query);
          }

          searchResultsMap = getMovieResults(statement, connection);

          statement.close();
          connection.close();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    for(MovieInfo value : searchResultsMap.values())
        searchResults.add(value);

    return searchResults;
  }

  public static MovieInfo getMovieById(String id, DataSource ds){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    // String loginUser = "testuser";
    // String loginPasswd = "testpass";
    // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    try {
          // Class.forName("com.mysql.jdbc.Driver").newInstance();

          // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          Connection connection = getConnection(ds);
          connection.setReadOnly(true);

          String query = "SELECT * FROM movies WHERE id = ?;";

          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, id);

          searchResultsMap = getMovieResults(statement, connection);

          statement.close();
          connection.close();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    for(MovieInfo value : searchResultsMap.values())
        searchResults.add(value);

    if(searchResults.isEmpty())
      return null;
    else
      return searchResults.get(0);
  }

  public static List<Movie> searchMoviesByTitle(String keywords, DataSource ds){
      List<Movie> searchResults = new ArrayList<Movie>();
        // String loginUser = "testuser";
        // String loginPasswd = "testpass";
        // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        String words[] = keywords.split("\\s+");

        for(int i = 0; i < words.length; ++i){
            words[i] = "+" + words[i];
            if(i == words.length - 1)
                words[i] += "*";
        }

        keywords = "";
        for(int i = 0; i < words.length; ++i){
            if(i == words.length - 1)
                keywords += words[i];
            else
                keywords += words[i] + " ";
        }

        try {
              // Class.forName("com.mysql.jdbc.Driver").newInstance();

              // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          Connection connection = getConnection(ds);
          connection.setReadOnly(true);

          String query = "SELECT id, title, director, year FROM movies " +
              "WHERE MATCH(title) AGAINST(? IN BOOLEAN MODE);";

          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, keywords);

          ResultSet results = statement.executeQuery();

          while(results.next()){
              Integer id = results.getInt("id");
              String title = results.getString("title");
              Integer year = results.getInt("year");
              String director = results.getString("director");

              searchResults.add(new Movie(id, title, year, director, "", "")); 
          }
          statement.close();
          results.close();
          connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResults;

  }

  private static HashMap<Integer, MovieInfo> getMovieResults(PreparedStatement statement, Connection connection) throws Exception{
      HashMap<Integer, MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();
      ResultSet results = statement.executeQuery();

      // Iterate through each row of results
      Integer id = new Integer(0);
      while (results.next()) {
        id = results.getInt("id");
        String titleResult = results.getString("title");
        Integer yearResult = results.getInt("year");
        String directorResult = results.getString("director");
        String bannerUrl = results.getString("banner_url");
        String trailerUrl = results.getString("trailer_url");

        if(!searchResultsMap.containsKey(id)){
          searchResultsMap.put(id, 
            new MovieInfo(id, titleResult, yearResult, directorResult, bannerUrl, trailerUrl, 
                          new HashSet<Star>(getStarsInMovies(id, connection)),
                          new HashSet<String>(getGenresInMovies(id, connection))));
        }

      }
      
      results.close();
      return searchResultsMap;
  }

    private static HashMap<Integer, MovieInfo> getMovieResultss(Statement statement, Connection connection, String query) throws Exception{
      HashMap<Integer, MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();
      ResultSet results = statement.executeQuery(query);

      // Iterate through each row of results
      Integer id = new Integer(0);
      while (results.next()) {
        id = results.getInt("id");
        String titleResult = results.getString("title");
        Integer yearResult = results.getInt("year");
        String directorResult = results.getString("director");
        String bannerUrl = results.getString("banner_url");
        String trailerUrl = results.getString("trailer_url");

        if(!searchResultsMap.containsKey(id)){
          searchResultsMap.put(id, 
            new MovieInfo(id, titleResult, yearResult, directorResult, bannerUrl, trailerUrl, 
                          new HashSet<Star>(getStarsInMovies(id, connection)),
                          new HashSet<String>(getGenresInMovies(id, connection))));
        }

      }
      
      results.close();
      return searchResultsMap;
  }

    private static String toBooleanKeywords(String keywords){
      String words[] = keywords.split("\\s+");

      for(int i = 0; i < words.length; ++i){
          words[i] = "+" + words[i];
          if(i == words.length - 1)
              words[i] += "*";
      }

      keywords = "";
      for(int i = 0; i < words.length; ++i){
          if(i == words.length - 1)
              keywords += words[i];
          else
              keywords += words[i] + " ";
      }

      return keywords;
  }

    private static Set<Star> getStarsInMovies(Integer movieId, Connection connection) throws Exception{
	    Set<Star> starSet = new HashSet<Star>();

	    String query = "SELECT id as sid, first_name, last_name " +
	                    "FROM (SELECT * FROM stars_in_movies " +
	                            "WHERE movie_id = ?) sub " +
	                    "JOIN stars s ON s.id = sub.star_id;";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setInt(1, movieId);

	    ResultSet results = statement.executeQuery();
	    while(results.next()){
	      Integer id = results.getInt("sid");
	      String fName = results.getString("first_name");
	      String lName = results.getString("last_name");

	      starSet.add(new Star(id, fName, lName, "", ""));
	    }

	    statement.close();
	    results.close();

	    return starSet;
  }

    private static Set<String> getGenresInMovies(Integer movieId, Connection connection) throws Exception{
	    Set<String> genreSet = new HashSet<String>();

	    String query = "SELECT name " +
	                    "FROM (SELECT * FROM genres_in_movies " +
	                            "WHERE movie_id = ?) sub " +
	                    "JOIN genres g ON g.id = sub.genre_id;";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setInt(1, movieId);

	    ResultSet results = statement.executeQuery();
	    while(results.next()){
	      String genre = results.getString("name");

	      genreSet.add(genre);
	    }

	    statement.close();
	    results.close();

	    return genreSet;
  }

  ////////////////////////////////////Star stuff/////////////////////////////////////////////////////


      public static StarInfo getStarById(String id, DataSource ds){
      List<StarInfo> searchResults = new ArrayList<StarInfo>();
      HashMap<Integer,StarInfo> searchResultsMap = new HashMap<Integer, StarInfo>();

      // String loginUser = "testuser";
      // String loginPasswd = "testpass";
      // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

      try {
        // Class.forName("com.mysql.jdbc.Driver").newInstance();

        // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

        Connection connection = getConnection(ds);
        connection.setReadOnly(true);

        String query = "SELECT * FROM stars WHERE id = ? ;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);

        searchResultsMap = getStarResults(statement, connection);

        statement.close();
        connection.close();
      } 
      catch (Exception e) {
        e.printStackTrace();
      }
      for(StarInfo value : searchResultsMap.values())
          searchResults.add(value);

      if(searchResults.isEmpty())
        return null;
      else
        return searchResults.get(0);
      }

      private static HashMap<Integer, StarInfo> getStarResults(PreparedStatement statement, Connection connection) throws Exception{
        HashMap<Integer, StarInfo> searchResultsMap = new HashMap<Integer, StarInfo>();
        ResultSet results = statement.executeQuery();

        // Iterate through each row of results
        Integer id = new Integer(0);
        while (results.next())
        {
          id = results.getInt("id");
          String fName = results.getString("first_name");
          String lName = results.getString("last_name");
          String dob = results.getString("dob");
          String photo_url = results.getString("photo_url");

          if(!searchResultsMap.containsKey(id)){
            searchResultsMap.put(id, 
              new StarInfo(id, fName, lName, dob, photo_url, new HashSet<Movie>(getMoviesWithStars(id, connection))));
          }
          
         }
        results.close();
        return searchResultsMap;
      }

    private static Set<Movie> getMoviesWithStars(Integer starId, Connection connection) throws Exception{
      Set<Movie> movieSet = new HashSet<Movie>();

      String query = "SELECT id as mid, title " +
                      "FROM (SELECT * FROM stars_in_movies " +
                              "WHERE star_id = ?) sub " +
                      "JOIN movies m ON m.id = sub.movie_id;";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, starId);

      ResultSet results = statement.executeQuery();
      while(results.next()){
        Integer id = results.getInt("mid");
        String title = results.getString("title");

        movieSet.add(new Movie(id, title, 0, "", "", ""));
      }

      statement.close();
      results.close();

      return movieSet;
  }

 //////////////////////////////////////////////CreditCard stuff///////////////////////////////////////////////////

      public static boolean checkCC(String ccid, String expiry, String firstName, String lastName, DataSource ds){
        if(ccid == null)
            ccid = "";
        if(expiry == null)
            expiry = "01/01";
        if(firstName == null)
            firstName = "";
        if(lastName == null)
            lastName = "";

        // String loginUser = "testuser";
        // String loginPasswd = "testpass";
        // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        try {
            // Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

            Connection connection = getConnection(ds);
            connection.setReadOnly(true);

            String checkQuery = "SELECT * FROM  creditcards " +
                                "WHERE id = ? AND expiration >= ? AND expiration < ? AND first_name = ? AND last_name = ?;";

            PreparedStatement statement = null;

            int month = Integer.parseInt(expiry.substring(0,2));
            int year = Integer.parseInt(expiry.substring(3,5));

            String nextMonth = Integer.toString(month + 1, 10);

            if(month + 1 < 10)
              nextMonth = "0" + nextMonth;
            else if(month == 12)
              nextMonth = "01";

            if(month == 12)
              year++;

            String nextYear = Integer.toString(year, 10);

            if(year < 10)
              nextYear = "0" + nextYear;


            expiry = "20" + expiry.substring(3,5) + "-" + expiry.substring(0,2) + "-01";
            String endDate = "20" + nextYear + "-" + nextMonth + "-01";

            statement = connection.prepareStatement(checkQuery);

            statement.setString(1, ccid);
            statement.setDate(2, java.sql.Date.valueOf(expiry));
            statement.setDate(3, java.sql.Date.valueOf(endDate));
            statement.setString(4, firstName);
            statement.setString(5, lastName);

            ResultSet results = statement.executeQuery();

            if (!results.isBeforeFirst()){
                results.close();
                statement.close();
                connection.close();
                return false;
            }
            else{
                results.close();
                statement.close();
                connection.close();
                return true;
            }
        } 
        catch (Exception e) {
          e.printStackTrace();
        }

        return false;
    }
}