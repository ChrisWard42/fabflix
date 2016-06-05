package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
// import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;

public class CreditCard implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String expiration;

    public CreditCard() {
        id = 0;
        firstName = "";
        lastName = "";
        expiration = "";
    }

    public CreditCard(int id, String firstName, String lastName, String expiration){
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

    public String getExpiration(){
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

    public void setExpiration(String expiration){
        this.expiration = expiration;
    }

    public static boolean check(String ccid, String expiry, String firstName, String lastName){
        if(ccid == null)
            ccid = "";
        if(expiry == null)
            expiry = "01/01";
        if(firstName == null)
            firstName = "";
        if(lastName == null)
            lastName = "";

        // String loginUser = "testuser";
        // String loginPasswd = "testpass";
        // String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        try {
            // Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");

            Connection connection = ds.getConnection();

            String checkQuery = "SELECT * FROM  creditcards " +
                                "WHERE id = ? AND expiration >= ? AND expiration < ? AND first_name = ? AND last_name = ?;";

            PreparedStatement statement = null;

            int month = Integer.parseInt(expiry.substring(0,2));
            int year = Integer.parseInt(expiry.substring(3,5));

            String nextMonth = Integer.toString(month + 1, 10);

            if(month + 1 < 10)
              nextMonth = "0" + nextMonth;
            else if(month == 12)
              nextMonth = "01";

            if(month == 12)
              year++;

            String nextYear = Integer.toString(year, 10);

            if(year < 10)
              nextYear = "0" + nextYear;


            expiry = "20" + expiry.substring(3,5) + "-" + expiry.substring(0,2) + "-01";
            String endDate = "20" + nextYear + "-" + nextMonth + "-01";

            statement = connection.prepareStatement(checkQuery);

            statement.setString(1, ccid);
            statement.setDate(2, java.sql.Date.valueOf(expiry));
            statement.setDate(3, java.sql.Date.valueOf(endDate));
            statement.setString(4, firstName);
            statement.setString(5, lastName);

            ResultSet results = statement.executeQuery();

            if (!results.isBeforeFirst()){
                results.close();
                statement.close();
                connection.close();
                return false;
            }
            else{
                results.close();
                statement.close();
                connection.close();
                return true;
            }
        } 
        catch (Exception e) {
          e.printStackTrace();
        }

        return false;
    }
}