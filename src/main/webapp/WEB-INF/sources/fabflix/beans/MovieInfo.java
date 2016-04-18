package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class MovieInfo implements Serializable {
	private int id;
	private String title;
	private int year;
	private String director;
	private String bannerUrl;
    private String trailerUrl;
    private Set<Star> starSet;
    private Set<String> genreSet;


	public MovieInfo() {
		id = 0;
		title = "";
		year = 0;
		director = "";
		bannerUrl = "";
        trailerUrl = "";
        starSet = new HashSet<Star>();
        genreSet = new HashSet<String>();
	}

	public MovieInfo(int id, String title, int year, String director, 
					String bannerUrl, String trailerUrl, HashSet<Star> starSet,
					HashSet<String> genreSet){
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.bannerUrl = bannerUrl;
        this.trailerUrl = trailerUrl;
        this.starSet = new HashSet<Star>(starSet);
        this.genreSet = new HashSet<String>(genreSet);
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

    public Set<Star> getStarSet(){
    	return starSet;
    }

    public Set<String> getGenreSet(){
    	return genreSet;
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

    public void setStarSet(Set<Star> starSet){
    	this.starSet = new HashSet<Star>(starSet);
    }

    public void setGenreSet(Set<String> genreSet){
    	this.genreSet = new HashSet<String>(genreSet);
    }

    public void addToStarSet(Star star){
    	if(starSet != null){
    		starSet.add(star);
    	}
    }

    public void addToGenreSet(String genre){
    	if(genreSet != null){
    		genreSet.add(genre);
    	}
    }

}
