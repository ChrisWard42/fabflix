package fabflix.filters;

// import required java libraries
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import fabflix.beans.*;

// Implements Filter class
public class Authentication implements Filter {

    private FilterConfig filterConfig = null;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
                        throws IOException, ServletException {
        // Initialize information parameters
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ServletContext sc = filterConfig.getServletContext();
        String filterName = filterConfig.getFilterName();

        // Check against login session and redirect
        Customer user = (Customer) httpRequest.getSession().getAttribute("user");
        String servlet = httpRequest.getServletPath();

        // If page requested is login, and user is logged in, redirect to home
        if (Objects.equals(servlet, "/login") && user != null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            return;
        }

        // If page requested is any other page and user is not logged in, redirect to login
        if (!Objects.equals(servlet, "/login") && !Objects.equals(servlet, "/reports") && user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        // Pass request back down the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}