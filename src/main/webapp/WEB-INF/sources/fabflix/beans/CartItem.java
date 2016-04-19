package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import fabflix.beans.*;

public class CartItem implements Serializable {
    private int id;
    private int quantity;
    private MovieInfo details;

    public CartItem() {
        id = 0;
        quantity = 0;
        details = new MovieInfo();
    }

    public CartItem(int id, int quantity, MovieInfo details) {
        this.id = id;
        this.quantity = quantity;
        this.details = details;
    }

    public int getId(){
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public MovieInfo getDetails() {
        return details;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setDetails(MovieInfo details){
        this.details = details;
    }

    public void incQuantity() {
        this.quantity++;
    }
}