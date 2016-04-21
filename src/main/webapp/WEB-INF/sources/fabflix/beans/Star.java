package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;


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

    public static StarInfo getStarById(String id){
        List<StarInfo> searchResults = new ArrayList<StarInfo>();
        HashMap<Integer,StarInfo> searchResultsMap = new HashMap<Integer, StarInfo>();

        String loginUser = "root";
        String loginPasswd = "waydowninthehole";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          String starView = "CREATE OR REPLACE VIEW starView AS " + 
              "SELECT DISTINCT s.id, s.first_name, s.last_name, s.dob, s.photo_url " + 
              "FROM stars AS s " + 
              "WHERE s.id = ?; "; 

          String finalQuery = "SELECT sv.id AS sid, sv.first_name, sv.last_name, sv.dob, sv.photo_url, m.id as mid, m.title " + 
            "FROM  starView AS sv, movies AS m, stars_in_movies as sim1 " + 
            "WHERE sim1.star_id = sv.id AND sim1.movie_id = m.id;";

          PreparedStatement statement = null;
          Statement finalStatement = null;

          statement = connection.prepareStatement(starView);
          statement.setString(1, id);
          statement.execute();

          finalStatement = connection.createStatement();
          searchResultsMap = getResults(finalStatement, finalQuery, searchResultsMap);

          statement.close();
          finalStatement.close();
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

    private static HashMap<Integer, StarInfo> getResults(Statement statement, String query, HashMap<Integer, StarInfo> searchResultsMap) throws Exception{
      ResultSet results = null;
      results = statement.executeQuery(query);

      // Iterate through each row of results
      while (results.next())
      {
        Integer id = results.getInt("sid");
        String fName = results.getString("first_name");
        String lName = results.getString("last_name");
        String dob = results.getString("dob");
        String photo_url = results.getString("photo_url");
        Integer movieId = results.getInt("mid");
        String movieTitle = results.getString("title");

        if(!searchResultsMap.containsKey(id)){
          searchResultsMap.put(id, 
            new StarInfo(id, fName, lName, dob, photo_url, new HashSet<Movie>()));
        }
        
        searchResultsMap.get(id).addToMovieSet(new Movie(movieId, movieTitle, 0, "", "", ""));
       }
      results.close();
      return searchResultsMap;
    }
    
    }