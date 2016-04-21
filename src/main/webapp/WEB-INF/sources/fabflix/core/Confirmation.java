
/* Displays the purchase confirmation page and handles interactions therein */
package fabflix.core;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

public class Confirmation extends HttpServlet {
    public String getServletInfo() {
       return "Displays the purchase confirmation page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {        
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        // Get the current value of the Cart session variable
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) request.getSession().getAttribute("cart");

        // If cart is null or empty, should not have reached this page, return home
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        else {
            // Move the contents of the cart to a request variable and empty the session cart
            request.setAttribute("purchased", cart);
            request.getSession().setAttribute("cart", new HashMap<Integer, CartItem>());
        }

        request.getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
    }
}