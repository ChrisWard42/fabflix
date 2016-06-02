package fabflix.beans;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class tester {
	public static void main(String[] args){
		StarInfo star = Star.getStarById("683018");
		System.out.println(star);
		// MovieInfo movie = Movie.getMovieById("901");
		// System.out.println(movie);
		
	}
}