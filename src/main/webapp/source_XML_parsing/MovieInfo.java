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
    private Set<String> starSet;
    private Set<String> genreSet;


	public MovieInfo() {
		id = 0;
		title = "";
		year = 0;
		director = "";
		bannerUrl = "";
        trailerUrl = "";
        starSet = new HashSet<String>();
        genreSet = new HashSet<String>();
	}

	public MovieInfo(int id, String title, int year, String director, 
					String bannerUrl, String trailerUrl, HashSet<String> starSet,
					HashSet<String> genreSet){
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.bannerUrl = bannerUrl;
        this.trailerUrl = trailerUrl;
        this.starSet = new HashSet<String>(starSet);
        this.genreSet = new HashSet<String>(genreSet);
	}

	/* Code borrowed from 
   * http://stackoverflow.com/questions/8180430/how-to-override-equals-method-in-java
   */
  @Override
  public boolean equals(Object obj) {
      if (obj == null) {
          return false;
      }
      if (!MovieInfo.class.isAssignableFrom(obj.getClass())) {
          return false;
      }
      final MovieInfo other = (MovieInfo) obj;

      if (!this.title.equals(other.getTitle()) || !this.director.equals(other.getDirector())) {
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

    public Set<String> getStarSet(){
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

    public void setStarSet(Set<String> starSet){
    	this.starSet = new HashSet<String>(starSet);
    }

    public void setGenreSet(Set<String> genreSet){
    	this.genreSet = new HashSet<String>(genreSet);
    }

    public void addToStarSet(String star){
    	if(starSet != null){
    		starSet.add(star);
    	}
    }

    public void addToGenreSet(String genre){
    	if(genreSet != null){
    		genreSet.add(genre);
    	}
    }

    @Override
    public String toString(){
    	return title;
    }

}
