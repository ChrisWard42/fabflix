package fabflix.filters;

// import required java libraries
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

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

        // Testing various request path outputs
        // sc.log("Context Path: " + httpRequest.getContextPath());
        // sc.log("Path Info: " + httpRequest.getPathInfo());
        // sc.log("Query String: " + httpRequest.getQueryString());
        // sc.log("Request URI: " + httpRequest.getRequestURI());
        // sc.log("Request URL: " + httpRequest.getRequestURL().toString());
        // sc.log("Servlet Path: " + httpRequest.getServletPath());

        // Check against login session and redirect
        // TODO: If page requested isn't login, and user isn't logged in, redirect to login
        // TODO: If page requested is login, and user is logged in, redirect to home

        // Pass request back down the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}