package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class Movie implements Serializable {
  private int id;
  private String title;
  private int year;
  private String director;
  private String bannerUrl;
    private String trailerUrl;

  public Movie() {
    id = 0;
    title = "";
    year = 0;
    director = "";
    bannerUrl = "";
        trailerUrl = "";
  }

  public Movie(int id, String title, int year, String director, String bannerUrl, String trailerUrl){
    this.id = id;
    this.title = title;
    this.year = year;
    this.director = director;
    this.bannerUrl = bannerUrl;
        this.trailerUrl = trailerUrl;
  }

  /* Code borrowed from 
   * http://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java
   */
  @Override
  public boolean equals(Object obj) {
      if (obj == null) {
          return false;
      }
      if (!Movie.class.isAssignableFrom(obj.getClass())) {
          return false;
      }
      final Movie other = (Movie) obj;

      if (this.id != other.getId()) {
          return false;
      }
      return true;
  }

  @Override
  public int hashCode() {
      int hash = 3;
      hash = 53 * hash + this.id;
      return hash;
  }
  /* End of borrowed code */

  public int getId(){
    return id;
  }

  public String getTitle(){
    return title;
  }

  public int getYear(){
    return year;
  }

  public String getDirector(){
    return director;
  }

  public String getBannerUrl(){
    return bannerUrl;
  }

  public String getTrailerUrl(){
      return trailerUrl;
  }

  public void setId(int id){
    this.id = id;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public void setYear(int year){
    this.year = year;
  }

  public void setDirector(String director){
    this.director = director;
  }

  public void setBannerUrl(String bannerUrl){
    this.bannerUrl = bannerUrl;
  }

  public void setTrailerUrl(String trailerUrl){
    this.trailerUrl = trailerUrl;
  }

  @Override
  public String toString(){
    String toReturn = id + " " + title;
    return toReturn;
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

  public static List<MovieInfo> searchMovies(String keywords){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    if(!keywords.isEmpty()){
        String loginUser = "testuser";
        String loginPasswd = "testpass";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        keywords = toBooleanKeywords(keywords);

        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          String query = "SELECT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " +
          "FROM movies AS m " +
          "WHERE MATCH(title) AGAINST( ? IN BOOLEAN MODE) OR MATCH(director) AGAINST( ? IN BOOLEAN MODE) OR " +
          "m.id IN(SELECT movie_id FROM stars_in_movies AS sim WHERE " +
          "  sim.star_id in(SELECT id " +
          "                 FROM stars AS s2   " +
          "                 WHERE MATCH(first_name, last_name) AGAINST(? IN BOOLEAN MODE)) " +
          " );";

          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, keywords);
          statement.setString(2, keywords);
          statement.setString(3, keywords);

          searchResultsMap = getResultss(statement, connection);

          statement.close();
          connection.close();
        } 
        catch (Exception e) {
          e.printStackTrace();
        }
      }// end if(!keywords.isEmpty())
      for(MovieInfo value : searchResultsMap.values())
        searchResults.add(value);
      return searchResults;
  }

  public static List<MovieInfo> searchMovies(String title, String star, String year, String director){
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

    String loginUser = "testuser";
    String loginPasswd = "testpass";
    String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    MovieInfo movie = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();

      Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

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

      searchResultsMap = getResultss(statement, connection);

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

  public static List<MovieInfo> browseMoviesByGenre(String genre){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    String loginUser = "testuser";
    String loginPasswd = "testpass";
    String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

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

          searchResultsMap = getResultss(statement, connection);

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

  public static List<MovieInfo> browseMoviesByLetter(String letter){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    // letter = letter + "*";
    if(!letter.isEmpty())
      letter = letter + "%";

    String loginUser = "testuser";
    String loginPasswd = "testpass";
    String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    MovieInfo movie = null;
    try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
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

          searchResultsMap = getResultss(statement, connection);

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

  public static MovieInfo getMovieById(String id){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    String loginUser = "testuser";
    String loginPasswd = "testpass";
    String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          String query = "SELECT * FROM movies WHERE id = ?;";

          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, id);

          searchResultsMap = getResultss(statement, connection);

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

  public static List<Movie> searchMoviesByTitle(String keywords){
      List<Movie> searchResults = new ArrayList<Movie>();
        String loginUser = "testuser";
        String loginPasswd = "testpass";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

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
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

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

  private static HashMap<Integer, MovieInfo> getResultss(PreparedStatement statement, Connection connection) throws Exception{
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
                          new HashSet<Star>(getStarsInMovies(id, connection)), new HashSet<String>()));
        }

      }
      
      results.close();
      return searchResultsMap;
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
}
