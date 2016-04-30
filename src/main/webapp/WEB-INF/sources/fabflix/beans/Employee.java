package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Employee implements Serializable {
    private String email;
    private String password;
    private String fullname;

    public Employee() {
        email = "";
        password = "";
        fullname = "";
    }

    public Employee(String email, String password, String fullname){
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getFullname(){
        return fullname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }
}