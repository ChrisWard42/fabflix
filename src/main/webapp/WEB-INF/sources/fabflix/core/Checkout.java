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

public class Checkout extends HttpServlet {
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
        String ccId = request.getParameter("ccId");
        String expiry = request.getParameter("expiry");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Test the credit card information against what's show in the database
        if (action.equals("purchase")) {
            boolean valid = false;

            // Get credit card number and expiry permutations
            if (Pattern.matches("\d\d/\d\d", expiry)) {
                valid = CreditCard.check(ccId, expiry, firstName, lastName);
                if (!valid)
                    valid = CreditCard.check(ccId.replace(" ", "").replace("-", ""), expiry, firstName, lastName);
            }
            else if (Pattern.matches("\d\d\d\d-\d\d-\d\d", expiry)) {
                valid = CreditCard.check(ccId, expiry.substring(2,3) + "/" + expiry.substring(5,6), firstName, lastName);
                if (!valid)
                    valid = CreditCard.check(ccId.replace(" ", "").replace("-", ""), expiry.substring(2,3) + "/" + expiry.substring(5,6), firstName, lastName);
            }
            else {
                valid = CreditCard.check(ccId, expiry, firstName, lastName);
                if (!valid)
                    valid = CreditCard.check(ccId.replace(" ", "").replace("-", ""), expiry, firstName, lastName);
            }

            if (valid) {
                response.sendRedirect(request.getContextPath() + "/confirmation");
                return
            }
        }

        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
    }
}