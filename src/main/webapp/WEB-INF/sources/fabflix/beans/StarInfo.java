package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public class StarInfo implements Serializable{
    private int id;
    private String firstName;
    private String lastName;
    private String dob;
    private String photoUrl;
    private Set<Movie> movieSet;

    public StarInfo() {
        id = 0;
        firstName = "";
        lastName = "";
        dob = "";
        photoUrl = "";
        movieSet = new HashSet<Movie>();
    }

    public StarInfo(int id, String firstName, String lastName, String dob, String photoUrl, HashSet<Movie> movieSet){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.photoUrl = photoUrl;
        this.movieSet = new HashSet<Movie>(movieSet);
    }

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

    public Set<Movie> getMovieSet(){
        return movieSet;
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

    public void setMovieSet(Set<Movie> movieSet){
        this.movieSet = movieSet;
    }

    public void addToMovieSet(Movie movie){
        if(movieSet != null){
            movieSet.add(movie);
        }
    }
}