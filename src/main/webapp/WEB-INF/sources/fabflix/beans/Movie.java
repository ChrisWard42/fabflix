package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

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

  public static List<MovieInfo> searchMovies(String keywords){
    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    if(!keywords.isEmpty()){
        String loginUser = "root";
        String loginPasswd = "waydowninthehole";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        MovieInfo movie = null;
        try {
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

              String starView = "CREATE OR REPLACE VIEW starView AS " + 
                "SELECT * " + 
                "FROM stars AS s2 " + 
                "WHERE first_name LIKE ? OR last_name LIKE ?;";

              String movieView = "CREATE OR REPLACE VIEW movieView AS " + 
                "SELECT DISTINCT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " + 
                "FROM movies AS m, stars_in_movies AS sim " + 
                "WHERE m.title LIKE ? OR m.director LIKE ? OR m.year LIKE ? OR " + 
                "(sim.star_id IN (SELECT id FROM starView) AND sim.movie_id = m.id);";

              String finalQuery = "SELECT mv.id AS mid, mv.title, mv.year, mv.director, mv.banner_url, mv.trailer_url, s.id AS sid, s.first_name, s.last_name " + 
               "FROM  movieView AS mv, stars AS s, stars_in_movies as sim1 " + 
               "WHERE sim1.star_id = s.id AND sim1.movie_id = mv.id;";

              //create array for first_name last_name permuations
              String names[] = keywords.split("\\s+");
              ArrayList<String> possibleFirstNames = new ArrayList<String>();
              ArrayList<String> possibleLastNames = new ArrayList<String>();

              // Building the different permutations of
              // first and last names when many names seperated by a space are entered
              if(names.length > 1){
                  StringBuilder firstName = new StringBuilder();
                  StringBuilder lastName = new StringBuilder();
                  for(int i = 0; i < names.length - 1; ++i){
                      firstName.append(names[i]);
                      firstName.append(" ");
                      possibleFirstNames.add(firstName.substring(0, firstName.length()-1));

                      for(int j = i + 1; j < names.length; ++j){
                          if(j != names.length){
                              lastName.append(names[j]);
                              lastName.append(" ");
                          }
                      }
                      possibleLastNames.add(lastName.substring(0, lastName.length()-1));
                      lastName.setLength(0);
                  }
              }
              PreparedStatement statement = null;
              Statement finalStatement = null;
              ResultSet results = null;
              //for iterating do while, stop when finished iterating through all permutations
              int firstNameIndex = -1;

              do{
                  //always use firstname OR lastname with all keywords for first iteration
                  if(firstNameIndex == -1){
                    // Declare our statement
                    statement = connection.prepareStatement(starView);

                    statement.setString(1, "%" + keywords + "%");
                    statement.setString(2, "%" + keywords + "%");
                    statement.execute();
                  }
                  //for subsequent iterations, use permutations of first and last names ANDed together
                  else{
                    starView = "CREATE OR REPLACE VIEW starView AS " + 
                    "SELECT * " + 
                    "FROM stars AS s2 " + 
                    "WHERE first_name LIKE ? AND last_name LIKE ?;";

                    statement = connection.prepareStatement(starView);

                    statement.setString(1, "%" + possibleFirstNames.get(firstNameIndex) + "%");
                    statement.setString(2, "%" + possibleLastNames.get(firstNameIndex) + "%");
                    statement.execute();

                  }

                  statement = connection.prepareStatement(movieView);

                  statement.setString(1, "%" + keywords + "%");
                  statement.setString(2, "%" + keywords + "%");
                  statement.setString(3, "%" + keywords + "%");
                  statement.execute();

                  finalStatement = connection.createStatement();
                  //Iterate through results twice, once for star list, again for genre list
                  for(int i = 0; i < 2; ++i){
                    if(i == 0){
                      results = finalStatement.executeQuery(finalQuery);
                    }
                    else{
                      //change finalQuery to display all genres for each movie
                      String finalQueryGenre = "SELECT mv.id AS mid, mv.title, mv.year, mv.director, mv.banner_url, mv.trailer_url, g.name AS gname " + 
                      "FROM  movieView AS mv, genres AS g, genres_in_movies as gim " + 
                      "WHERE gim.genre_id = g.id AND gim.movie_id = mv.id;";
                      results = finalStatement.executeQuery(finalQueryGenre);
                    }

                    // Iterate through each row of results
                    while (results.next())
                    {
                      Integer id = results.getInt("mid");
                      String title = results.getString("title");
                      Integer year = results.getInt("year");
                      String director = results.getString("director");
                      String bannerUrl = results.getString("banner_url");
                      String trailerUrl = results.getString("trailer_url");
                      Integer sid = new Integer(0);
                      String actorF_Name = "";
                      String actorL_Name = "";
                      String genreName = "";
                      //get actor attributes if first iteration, else get genre attributes
                      if(i == 0){
                        sid = results.getInt("sid");
                        actorF_Name = results.getString("first_name");
                        actorL_Name = results.getString("last_name");
                      }
                      else{
                        genreName = results.getString("gname");
                      }

                      if(!searchResultsMap.containsKey(id)){
                        searchResultsMap.put(id, 
                          new MovieInfo(id, title, year, director, bannerUrl, trailerUrl, new HashSet<Star>(), new HashSet<String>()));
                      }
                      
                      //insert into star set if first iteration, else insert into genre set
                      if(i == 0)
                        searchResultsMap.get(id).addToStarSet(new Star(sid, actorF_Name, actorL_Name, new Date(), ""));
                      else
                        searchResultsMap.get(id).addToGenreSet(genreName);
                    }
                  }
                    ++firstNameIndex;
                }while(firstNameIndex != possibleFirstNames.size());
              
              results.close();
              statement.close();
              finalStatement.close();
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

    //list of attributes that were entered not including star
    List<String> attributeList = new ArrayList<String>();
    if(!title.isEmpty())
      attributeList.add(title);
    if(!year.isEmpty())
      attributeList.add(year);
    if(!director.isEmpty())
      attributeList.add(director);

    List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
    HashMap<Integer,MovieInfo> searchResultsMap = new HashMap<Integer, MovieInfo>();

    String loginUser = "root";
    String loginPasswd = "waydowninthehole";
    String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

    MovieInfo movie = null;
    try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
          StringBuilder starView =  new StringBuilder("CREATE OR REPLACE VIEW starView AS " + 
            "SELECT * " + 
            "FROM stars AS s2 " + 
            "WHERE "); //first_name LIKE ? OR last_name LIKE ?;";

          StringBuilder movieView = new StringBuilder("CREATE OR REPLACE VIEW movieView AS " + 
            "SELECT DISTINCT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " + 
            "FROM movies AS m, stars_in_movies AS sim " + 
            "WHERE "); // m.title LIKE ? AND m.director LIKE ? AND m.year LIKE ? AND " + 
            //"(sim.star_id IN (SELECT id FROM starView) AND sim.movie_id = m.id);";

          String finalQuery = "SELECT mv.id AS mid, mv.title, mv.year, mv.director, mv.banner_url, mv.trailer_url, s.id AS sid, s.first_name, s.last_name " + 
           "FROM  movieView AS mv, stars AS s, stars_in_movies as sim1 " + 
           "WHERE sim1.star_id = s.id AND sim1.movie_id = mv.id;";

          String names[] = star.split("\\s+");
          ArrayList<String> possibleFirstNames = new ArrayList<String>();
          ArrayList<String> possibleLastNames = new ArrayList<String>();

          // Building the different permutations of
          // first and last names when many names seperated by a space are entered
          if(names.length > 1){
              StringBuilder firstName = new StringBuilder();
              StringBuilder lastName = new StringBuilder();
              for(int i = 0; i < names.length - 1; ++i){
                  firstName.append(names[i]);
                  firstName.append(" ");
                  possibleFirstNames.add(firstName.substring(0, firstName.length()-1));

                  for(int j = i + 1; j < names.length; ++j){
                      if(j != names.length){
                          lastName.append(names[j]);
                          lastName.append(" ");
                      }
                  }
                  possibleLastNames.add(lastName.substring(0, lastName.length()-1));
                  lastName.setLength(0);
              }
          }
          PreparedStatement statement = null;
          Statement finalStatement = null;
          ResultSet results = null;
          //for iterating do while, stop when finished iterating through all permutations
          int firstNameIndex = -1;

          do{
              //always use firstname OR lastname with all keywords for first iteration(if star is entered)
              if(firstNameIndex == -1 && !star.isEmpty()){
                // Declare our statement
                starView.append("first_name LIKE ? OR last_name LIKE ?;");
                statement = connection.prepareStatement(starView.toString());

                statement.setString(1, "%" + star + "%");
                statement.setString(2, "%" + star + "%");
                statement.execute();
              }
              //for subsequent iterations, use permutations of first and last names ANDed together
              else if(!star.isEmpty()){
                starView.append("first_name LIKE ? AND last_name LIKE ?;");

                statement = connection.prepareStatement(starView.toString());

                statement.setString(1, "%" + possibleFirstNames.get(firstNameIndex) + "%");
                statement.setString(2, "%" + possibleLastNames.get(firstNameIndex) + "%");
                statement.execute();

              }

              if(!title.isEmpty()){
                movieView.append("m.title LIKE ? AND ");
              }

              if(!year.isEmpty()){
                movieView.append("m.year LIKE ? AND ");
              }

              if(!director.isEmpty()){
                movieView.append("m.director LIKE ? AND ");
              }

              if(!star.isEmpty()){
                movieView.append("(sim.star_id IN (SELECT id FROM starView) AND sim.movie_id = m.id)");
              }
              //trim off excess AND if no star was inputted
              else{
                movieView.delete(movieView.length() - 4, movieView.length());
              }
              movieView.append(";");



              statement = connection.prepareStatement(movieView.toString());

              int n = 1;
              //set prepared statements according to which attributes were entered
              for(String attribute : attributeList){
                statement.setString(n, "%" + attribute + "%");
                n++;
              }
              statement.execute();

              finalStatement = connection.createStatement();
              //Iterate through results twice, once for star list, again for genre list
              for(int i = 0; i < 2; ++i){
                if(i == 0){
                  results = finalStatement.executeQuery(finalQuery);
                }
                else{
                  //change finalQuery to display all genres for each movie
                  String finalQueryGenre = "SELECT mv.id AS mid, mv.title, mv.year, mv.director, mv.banner_url, mv.trailer_url, g.name AS gname " + 
                  "FROM  movieView AS mv, genres AS g, genres_in_movies as gim " + 
                  "WHERE gim.genre_id = g.id AND gim.movie_id = mv.id;";
                  results = finalStatement.executeQuery(finalQueryGenre);
                }

                // Iterate through each row of results
                while (results.next())
                {
                  Integer id = results.getInt("mid");
                  String titleResult = results.getString("title");
                  Integer yearResult = results.getInt("year");
                  String directorResult = results.getString("director");
                  String bannerUrl = results.getString("banner_url");
                  String trailerUrl = results.getString("trailer_url");
                  Integer sid = new Integer(0);
                  String actorF_Name = "";
                  String actorL_Name = "";
                  String genreName = "";
                  //get actor attributes if first iteration, else get genre attributes
                  if(i == 0){
                    sid = results.getInt("sid");
                    actorF_Name = results.getString("first_name");
                    actorL_Name = results.getString("last_name");
                  }
                  else{
                    genreName = results.getString("gname");
                  }

                  if(!searchResultsMap.containsKey(id)){
                    searchResultsMap.put(id, 
                      new MovieInfo(id, titleResult, yearResult, directorResult, bannerUrl, trailerUrl, new HashSet<Star>(), new HashSet<String>()));
                  }
                  
                  //insert into star set if first iteration, else insert into genre set
                  if(i == 0)
                    searchResultsMap.get(id).addToStarSet(new Star(sid, actorF_Name, actorL_Name, new Date(), ""));
                  else
                    searchResultsMap.get(id).addToGenreSet(genreName);
                }
              }

              ++firstNameIndex;

          }while(firstNameIndex != possibleFirstNames.size());
          
          results.close();
          statement.close();
          finalStatement.close();
          connection.close();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    return searchResults;
  }
}
