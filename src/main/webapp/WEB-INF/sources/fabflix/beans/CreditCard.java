package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.sql.Date;
import javax.servlet.*;
import javax.servlet.http.*;

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

    public boolean check(String ccid, String expiry, String firstName, String lastName){
        String loginUser = "root";
        String loginPasswd = "waydowninthehole";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

          String checkQuery = "SELECT * FROM  creditcards " +
                                "WHERE id = ? AND expiration >= ? AND expiration < ? AND first_name = ? AND last_name = ?;"

          PreparedStatement statement = null;

          int month = Integer.parseInt(expiry.substring(0,2));
          int year = Integer.parseInt(expiry.subString(3,5));

          String nextMonth = Integer.toString(month, 10);
          if(month < 10)
            nextMonth = "0" + nextMonth;
          else if(month == 12)
            nextMOnth = "01";

          String nextYear = Integer.toString(year, 10);
          if(month < 10)
            nextYear = "0" = nextYear;

          expiry = "20"+expiry.substring(0,2) + "-" + expiry.substring(3,5) + "-01"
          String nextMonth = Integer.toString(month + 1);

          statement = connection.prepareStatement(checkQuery);
          statement.setString


          statement.close();
          // finalStatement.close();
          connection.close();
        } 
        catch (Exception e) {
          e.printStackTrace();
        }


    }
}