/* Displays the cart page and handles interactions therein */
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

public class Cart extends HttpServlet {
    public String getServletInfo() {
       return "Displays the cart page and handles interactions therein";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String action = request.getParameter("action");
        String idStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");
        int id = -1;
        int quantity = -1;

        if (idStr != null && !Objects.equals(idStr, ""))
            id = Integer.parseInt(request.getParameter("productId"));
        if (quantityStr != null && !Objects.equals(quantityStr, ""))
            quantity = Integer.parseInt(request.getParameter("quantity"));

        // Create a cart for this session if one doesn't already exist
        if (request.getSession().getAttribute("cart") == null) {
            request.getSession().setAttribute("cart", new HashMap<Integer, CartItem>());
        }

        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) request.getSession().getAttribute("cart");

        // Add the product id to the cart, or increment by 1
        if (Objects.equals(action, "add") && id != -1) {
            // Item in cart, increment by 1
            if (cart.containsKey(id)) {
                CartItem product = cart.get(id);
                product.incQuantity();
                cart.put(id, product);
            }
            // Item not in cart, add with quantity 1
            else {
                // TODO: Uncomment this when there's a way to search movie by id
                //MovieInfo details = Movie.searchMovies(id);
                // TEMPORARY CODE, REMOVE WHEN ABOVE AVAILABLE
                MovieInfo details = new MovieInfo();
                details.setId(id);
                details.setTitle("Title of " + Integer.toString(id));
                details.setYear(420);
                // TEMPORARY CODE, REMOVE WHEN ABOVE AVAILABLE
                if (details != null) {
                    cart.put(id, new CartItem(id, 1, details));
                }
            }
        }

        // Get update parameters and update the count for that product
        else if (Objects.equals(action, "update") && id != -1 && quantity != -1 && cart.containsKey(id)) {
            // Remove item if quantity set to 0
            if (quantity == 0) {
                cart.remove(id);
            }
            // Otherwise change the quantity of the item in the cart
            else {
                CartItem product = cart.get(id);
                product.setQuantity(quantity);
                cart.put(id, product);
            }
        }

        // Remove that item from the cart
        else if (Objects.equals(action, "remove") && id != -1 && cart.containsKey(id)) {
            cart.remove(id);
        }

        // Empty the cart
        else if (Objects.equals(action, "empty")) {
            cart.clear();
        }

        // If no action or invalid action provided, just display the contents of the cart

        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}