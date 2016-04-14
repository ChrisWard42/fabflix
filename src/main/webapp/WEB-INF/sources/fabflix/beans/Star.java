package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Star implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String photoUrl;

    public Star() {
        id = 0;
        firstName = "";
        lastName = "";
        dob = new Date();
        photoUrl = "";
    }

    public Star(int id, String firstName, String lastName, Date dob, String photoUrl){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.photoUrl = photoUrl;
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

    public Date getDob(){
        return dob;
    }

    public String getPhotoUrl(){
        return photoUrl;
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

    public void setDob(Date dob){
        this.dob = dob;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}