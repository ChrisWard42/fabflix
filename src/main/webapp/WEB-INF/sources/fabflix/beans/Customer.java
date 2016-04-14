package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Customer implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String ccid;
    private String address;
    private String email;
    private String password;

    public Customer() {
        id = 0;
        firstName = "";
        lastName = "";
        ccid = "";
        address = "";
        email = "";
        password = "";
    }

    public Customer(int id, String firstName, String lastName, String ccid
        , String address, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ccid = ccid;
        this.address = address;
        this.email = email;
        this.password = password;
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

    public String getCcid(){
        return ccid;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
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

    public void setCcid(String ccid){
        this.ccid = ccid;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setEmail(int email){
        this.email = email;
    }

    public void setPassword(int password){
        this.password = password;
    }
}