package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Sale implements Serializable {
    private int id;
    private int customerId;
    private int movieId;
    private Date sale;

    public Sale() {
        id = 0;
        customerId = 0;
        movieId = 0;
        sale = new Date();
    }

    public Sale(int id, int customerId, int movieId, Date sale){
        this.id = id;
        this.customerId = customerId;
        this.movieId = movieId;
        this.sale = sale;
    }

    public int getId(){
        return id;
    }

    public int getCustomerId(){
        return customerId;
    }

    public int getMovieId(){
        return movieId;
    }

    public Date getSale(){
        return sale;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    public void setSale(Date sale){
        this.sale = sale;
    }
}