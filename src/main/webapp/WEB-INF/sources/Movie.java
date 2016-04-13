import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Movie implements Serializable{
	private int id;
	private String title;
	private int year;
	private String director;
	private String banner_url;

	public Movie() {
		id = 0;
		title = "";
		year = 0;
		director = "";
		banner_url = "";
	}

	public Movie(int id, String title, int year, String director, String banner_url){
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.banner_url = banner_url;
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

	public String getBanner_url(){
		return banner_url;
	}

	public void setID(int id){
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

	public void setBanner_url(String banner_url){
		this.banner_url = banner_url;
	}

	public static List<Movie> searchMovies(String keywords){
		String loginUser = "root";
        String loginPasswd = "waydowninthehole";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        List<Movie> searchResults = new ArrayList<Movie>();
        Movie movie = null;
        try {
              Class.forName("com.mysql.jdbc.Driver").newInstance();

              Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = connection.createStatement();

              String query = "SELECT * FROM movies WHERE title LIKE '"
              				 + keywords + "%' or director LIKE '" + keywords + "%';";

              // Perform the query
              ResultSet results = statement.executeQuery(query);

              // Iterate through each row of results
              while (results.next())
              {
                Integer id = results.getInt("id");
                String title = results.getString("title");
                Integer year = results.getInt("year");
                String director = results.getString("director");
                String banner_url = results.getString("banner_url");

                //Create movie object
                movie = new Movie(id, title, year, director, banner_url);

                //Add to movie list
                searchResults.add(movie);
              }

              results.close();
              statement.close();
              connection.close();

        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
        return searchResults;
	}
}