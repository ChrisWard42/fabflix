/* Displays the checkout page and handles interactions therein */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

import javax.naming.Context;
import javax.sql.DataSource;

public class Checkout extends HttpServlet {

    private DataSource ds = null;

    @Override
    public void init() throws ServletException {
        try {
             //Create a datasource for pooled connections.
             ds = (DataSource) getServletContext().getAttribute("DBCPool");

             // //Register the driver for non-pooled connections.
             // Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception e) {
          throw new ServletException(e.getMessage());
        }

    }

    public String getServletInfo() {
       return "Displays the checkout and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String action = request.getParameter("action");
        String ccId = request.getParameter("ccNum");
        String expiry = request.getParameter("expiry");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // All of the boxes are filled out
        if(ccId != null && !ccId.isEmpty() && expiry != null && !expiry.isEmpty() &&
            firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()){

            // Test the credit card information against what's show in the database
            if (action != null && action.equals("purchase")) {
                boolean valid = false;

                // Get credit card number and expiry permutations
                if (Pattern.matches("\\d\\d/\\d\\d", expiry)) {
                    valid = MovieDB.checkCC(ccId, expiry, firstName, lastName, ds);
                    if (!valid){
                        valid = MovieDB.checkCC(ccId.replace(" ", "").replace("-", ""), expiry, firstName, lastName, ds);
                    }
                }
                else if (Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", expiry)) {
                    valid = MovieDB.checkCC(ccId, expiry.substring(5,7) + "/" + expiry.substring(2,4), firstName, lastName, ds);
                    if (!valid)
                        valid = MovieDB.checkCC(ccId.replace(" ", "").replace("-", ""), expiry.substring(5,7) + "/" + expiry.substring(2,4), firstName, lastName, ds);
                }
                else {
                    valid = MovieDB.checkCC(ccId, expiry, firstName, lastName, ds);
                    if (!valid)
                        valid = MovieDB.checkCC(ccId.replace(" ", "").replace("-", ""), expiry, firstName, lastName, ds);
                }

                if (valid) {
                    response.sendRedirect(request.getContextPath() + "/confirmation");
                    return;
                }
                else {
                    request.setAttribute("errorMsg", "Information does not match a credit card on file. Please try again.");
                }
            }
        }
        else if (action != null && action.equals("purchase")) {
            request.setAttribute("errorMsg", "Please fill out all fields before submitting.");
        }

        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
    }
}