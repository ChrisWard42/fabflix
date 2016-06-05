package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;


public class Star implements Serializable, Comparable<Star>{
    private int id;
    private String firstName;
    private String lastName;
    private String dob;
    private String photoUrl;

    public Star() {
        id = 0;
        firstName = "";
        lastName = "";
        dob = "";
        photoUrl = "";
    }

    public Star(int id, String firstName, String lastName, String dob, String photoUrl){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.photoUrl = photoUrl;
    }

    @Override
    public int compareTo(Star other){
        return this.getId() - other.getId();
    }

    /* Code borrowed from 
     * http://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Star.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Star other = (Star) obj;

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

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getDob(){
        return dob;
    }

    public String getPhotoUrl(){
        return photoUrl;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setDob(String dob){
        this.dob = dob;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString(){
      String toReturn = id + " " + firstName + " " + lastName;
      return toReturn;
    }



    public static StarInfo getStarById(String id){
      List<StarInfo> searchResults = new ArrayList<StarInfo>();
      HashMap<Integer,StarInfo> searchResultsMap = new HashMap<Integer, StarInfo>();

      // String loginUser = "testuser";
      // String loginPasswd = "testpass";
      // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

      try {
        // Class.forName("com.mysql.jdbc.Driver").newInstance();

        // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");

        DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");

        Connection connection = ds.getConnection();

        String query = "SELECT * FROM stars WHERE id = ? ;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);

        searchResultsMap = getResults(statement, connection);

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

      private static HashMap<Integer, StarInfo> getResults(PreparedStatement statement, Connection connection) throws Exception{
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

    
}