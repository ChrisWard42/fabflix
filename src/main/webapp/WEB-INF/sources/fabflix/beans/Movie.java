package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

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

              // TODO: Make this query combine genres_in_movies and stars_in_movies, use Star and
              //       Genre bean objects. Utilize name search code from proj 1.
              String query = "SELECT DISTINCT * FROM movies WHERE title LIKE '%"
              				 + keywords + "%' OR director LIKE '%" + keywords + "%'"
                             + "OR year LIKE '%" + keywords + "%';";

              // Perform the query
              ResultSet results = statement.executeQuery(query);

              // Iterate through each row of results
              while (results.next())
              {
                Integer id = results.getInt("id");
                String title = results.getString("title");
                Integer year = results.getInt("year");
                String director = results.getString("director");
                String bannerUrl = results.getString("banner_url");
                String trailerUrl = results.getString("trailer_url");

                //Create movie object
                movie = new Movie(id, title, year, director, bannerUrl, trailerUrl);

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
