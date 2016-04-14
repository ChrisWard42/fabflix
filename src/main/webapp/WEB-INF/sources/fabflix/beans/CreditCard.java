package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public class CreditCard implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private Date expiration;

    public CreditCard() {
        id = 0;
        firstName = "";
        lastName = "";
        expiration = new Date();
    }

    public CreditCard(int id, String firstName, String lastName, Date expiration){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expiration = expiration;
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

    public Date getExpiration(){
        return expiration;
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

    public void setExpiration(Date expiration){
        this.expiration = expiration;
    }
}