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

    public static List<MovieInfo> searchMovies(String keywords){
        String loginUser = "root";
        String loginPasswd = "waydowninthehole";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        List<MovieInfo> searchResults = new ArrayList<MovieInfo>();
        MovieInfo movie = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

             // TODO: Make this query combine genres_in_movies and stars_in_movies, use Star and
             //       Genre bean objects. Utilize name search code from proj 1.
            String starView = "CREATE OR REPLACE VIEW starView AS " + 
              "SELECT * " + 
              "FROM stars AS s2 " + 
              "WHERE first_name LIKE ? OR last_name LIKE ?;";

            String movieView = "CREATE OR REPLACE VIEW movieView AS " + 
              "SELECT DISTINCT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " + 
              "FROM movies AS m, stars_in_movies AS sim, starView AS ids " + 
              "WHERE m.title LIKE ? OR m.director LIKE ? OR m.year LIKE ? OR " + 
              "sim.star_id = ids.id AND sim.movie_id = m.id;";

            String finalQuery = "SELECT * FROM  movieView";

            // Declare our statement
            PreparedStatement statement = connection.prepareStatement(starView);

            statement.setString(1, "%" + keywords + "%");
            statement.setString(2, "%" + keywords + "%");
            statement.execute();

            statement = connection.prepareStatement(movieView);

            statement.setString(1, "%" + keywords + "%");
            statement.setString(2, "%" + keywords + "%");
            statement.setString(3, "%" + keywords + "%");
            statement.execute();

            Statement finalStatement = connection.createStatement();
            ResultSet results = statement.executeQuery(finalQuery);

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
                movie = new MovieInfo(id, title, year, director, bannerUrl, trailerUrl, new ArrayList<Star>(), new ArrayList<String>());

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
