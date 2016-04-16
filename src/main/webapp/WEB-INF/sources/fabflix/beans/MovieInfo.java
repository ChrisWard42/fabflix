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
    private List<Star> starList;
    private List<String> genreList;


	public MovieInfo() {
		id = 0;
		title = "";
		year = 0;
		director = "";
		bannerUrl = "";
        trailerUrl = "";
        starList = new ArrayList<Star>();
        genreList = new ArrayList<String>();
	}

	public MovieInfo(int id, String title, int year, String director, 
					String bannerUrl, String trailerUrl, ArrayList<Star> starList,
					ArrayList<String> genreList){
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.bannerUrl = bannerUrl;
        this.trailerUrl = trailerUrl;
        this.starList = new ArrayList<Star>(starList);
        this.genreList = new ArrayList<String>(genreList);
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

    public List<Star> getStarList(){
    	return starList;
    }

    public List<String> getGenreList(){
    	return genreList;
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

    public void setStarList(List<Star> starList){
    	this.starList = new ArrayList<Star>(starList);
    }

    public void setGenreList(List<String> genreList){
    	this.genreList = new ArrayList<String>(genreList);
    }

}
