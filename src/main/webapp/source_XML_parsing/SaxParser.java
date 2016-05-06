import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.*;

public class SaxParser{

   public static void storeItems(Map<String, MovieInfo> movieMap, Map<String, String> stars2dobMap){
      String user = "classta";
      String pass = "classta";
      List<String> oldKeys = new ArrayList<String>();
      List<String> newKeys = new ArrayList<String>();
      List<String> oldKeysStars = new ArrayList<String>();
      List<String> newKeysStars = new ArrayList<String>();
      List<String> oldKeysGenres = new ArrayList<String>();
      List<String> newKeysGenres = new ArrayList<String>();
      Queue<Integer> movieGenKeys = new LinkedList<Integer>();
      Queue<Integer> starGenKeys = new LinkedList<Integer>();
      Queue<Integer> genreGenKeys = new LinkedList<Integer>();
      Map<String, ArrayList<String>> stars2movies = new HashMap<String, ArrayList<String>>();
      Map<String, ArrayList<String>> genres2movies = new HashMap<String, ArrayList<String>>();
      Map<String, String> stars2dob = new HashMap<String, String>(stars2dobMap);
      String query = "";
      PreparedStatement statement = null;
      ResultSet gen_keys = null;

      try{
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/", user, pass);
         connection.setCatalog("moviedb_project3_grading");
         connection.setAutoCommit(false);

         //Current keys are the XML movie IDs. We will later replace these with the keys
         //generated when inserting into the database. Therefore these are the "old keys"
         for(String oldKey : movieMap.keySet()){
            oldKeys.add(oldKey);
         }
         //Keep consistent order of iterating through map
         Collections.sort(oldKeys);

         ///////////////////Insert Movies///////////////////////////////
         System.out.println("Inserting movies...");
         query = "INSERT INTO movies(title, year, director) VALUES(?, ?, ?);";

         statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

         for(String oldKey : oldKeys){
            statement.setString(1, movieMap.get(oldKey).getTitle());
            statement.setInt(2, movieMap.get(oldKey).getYear());
            statement.setString(3, movieMap.get(oldKey).getDirector());
            statement.addBatch();
            ///////////////Batchless insert code below///////////
            // statement.execute();
            // gen_keys = statement.getGeneratedKeys();

            // while(gen_keys.next()){
            //    movieGenKeys.add(gen_keys.getInt(1));
            // }
         }

         statement.executeBatch();
         /////////////////////////////////////////////////////////////


         /////////////Update keys with IDs///////////////////////
         gen_keys = statement.getGeneratedKeys();

         //getting the new generated keys
         while(gen_keys.next()){
            movieGenKeys.add(gen_keys.getInt(1));
         }

         //replacing old XML keys with new database generated keys
         for(String oldKey : oldKeys){
            movieMap.get(oldKey).setId(movieGenKeys.peek());
            newKeys.add(Integer.toString(movieGenKeys.peek()));
            MovieInfo currentMovie = movieMap.remove(oldKey);
            movieMap.put(Integer.toString(movieGenKeys.remove()), currentMovie);
         }
         //Again, for consistent map iteration
         Collections.sort(newKeys);
         /////////////////////////////////////////////////////////

         //Add set of stars to each movie
         for(String newKey : newKeys){
            Set<String> currentStarSet = new HashSet<String>(movieMap.get(newKey).getStarSet());
            for(String star : currentStarSet){
               if(!stars2movies.containsKey(star)){
                  stars2movies.put(star, new ArrayList<String>());
               }               
               ArrayList<String> movies = stars2movies.get(star);
               movies.add(newKey);
            }
         }

         //Add set of genres to each movie
         for(String newKey : newKeys){
            Set<String> currentGenreSet = new HashSet<String>(movieMap.get(newKey).getGenreSet());
            for(String genre : currentGenreSet){
               if(!genres2movies.containsKey(genre)){
                  genres2movies.put(genre, new ArrayList<String>());
               }               
               ArrayList<String> movies = genres2movies.get(genre);
               movies.add(newKey);
            }
         }

         for(String oldKeyStars : stars2dob.keySet()){
            oldKeysStars.add(oldKeyStars);
         }
         //Keep consistent order of iterating through map
         Collections.sort(oldKeysStars);

         //////////////////////////Insert Stars////////////////////////
         System.out.println("Inserting stars...");
         query = "INSERT INTO stars(first_name, last_name, dob) VALUES(?, ?, ?);";

         statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

         for(String oldKeyStars : oldKeysStars){
            //split name into first name and last name on first space
            String names[] = oldKeyStars.split(" ", 2);

            statement.setString(1, names[0]);
            //only put in second name if it exists, else put empty string
            if(names.length > 1)
               statement.setString(2, names[1]);
            else
               statement.setString(2, "");

            //only input dob if it exists, else put NULL
            if(!stars2dob.get(oldKeyStars).isEmpty()){
               statement.setDate(3, java.sql.Date.valueOf(stars2dob.get(oldKeyStars)));
            }
            else
               statement.setNull(3, java.sql.Types.DATE);

            statement.addBatch();
            ///////////////Batchless insert code below///////////
            // statement.execute();
            // gen_keys = statement.getGeneratedKeys();

            // while(gen_keys.next()){
            //    starGenKeys.add(gen_keys.getInt(1));
            // }

         }

         statement.executeBatch();
         //////////////////////////////////////////////////////////////

         /////////////////////Update Keys with Ids////////////////////////
         gen_keys = statement.getGeneratedKeys();

         while(gen_keys.next()){
            starGenKeys.add(gen_keys.getInt(1));
         }

         for(String oldKeyStars : oldKeysStars){
            if(stars2movies.containsKey(oldKeyStars)){
               newKeysStars.add(Integer.toString(starGenKeys.peek()));
               ArrayList<String> currentMovieList = stars2movies.remove(oldKeyStars);
               stars2movies.put(Integer.toString(starGenKeys.remove()), currentMovieList);
            }
         }
         //Again, for consistent map iteration
         Collections.sort(newKeysStars);
         // System.out.println(stars2movies);

         /////////////////////////////////////////////////////////////////

         for(String oldKeyGenres : genres2movies.keySet()){
            oldKeysGenres.add(oldKeyGenres);
         }
         //Keep consistent order of iterating through map
         Collections.sort(oldKeysGenres);
         //////////////////////////Insert Genres////////////////////////
         System.out.println("Inserting Genres...");
         query = "INSERT INTO genres(name) VALUES(?);";

         statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

         for(String oldKeyGenres : oldKeysGenres){
            statement.setString(1, oldKeyGenres);
            statement.addBatch();
            ///////////////Batchless insert code below///////////
            // statement.execute();
            // gen_keys = statement.getGeneratedKeys();

            // while(gen_keys.next()){
            //    genreGenKeys.add(gen_keys.getInt(1));
            // }
         }

         statement.executeBatch();
         //////////////////////////////////////////////////////////////

         /////////////////////Update Keys with Ids////////////////////////
         gen_keys = statement.getGeneratedKeys();

         while(gen_keys.next()){
            genreGenKeys.add(gen_keys.getInt(1));
         }

         for(String oldKeyGenres : oldKeysGenres){
            if(genres2movies.containsKey(oldKeyGenres)){
               newKeysGenres.add(Integer.toString(genreGenKeys.peek()));
               ArrayList<String> currentMovieList = genres2movies.remove(oldKeyGenres);
               genres2movies.put(Integer.toString(genreGenKeys.remove()), currentMovieList);
            }
         }
         //Again, for consistent map iteration
         Collections.sort(newKeysGenres);
         // System.out.println(genres2movies);

         /////////////////////////////////////////////////////////////////
         connection.commit();

         /////////////////////Insert stars_in_movies//////////////////////
         System.out.println("Inserting into stars_in_movies...");
         query = "INSERT INTO stars_in_movies(star_id, movie_id) VALUES(?, ?);";

         statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

         for(String newKeyStars : newKeysStars){
            if(!stars2movies.get(newKeyStars).isEmpty()){
               ArrayList<String> movieIdList = stars2movies.get(newKeyStars);
               for(String movieId : movieIdList){
                  statement.setInt(1, Integer.parseInt(newKeyStars));  
                  statement.setInt(2, Integer.parseInt(movieId));
                  statement.addBatch();
                  // statement.execute();
               }
            }  
         }

         statement.executeBatch();

         /////////////////////////////////////////////////////////////////

         /////////////////////Insert genres_in_movies//////////////////////
         System.out.println("Inserting into genres_in_movies...");
         query = "INSERT INTO genres_in_movies(genre_id, movie_id) VALUES(?, ?);";

         statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

         for(String newKeyGenres : newKeysGenres){
            if(!genres2movies.get(newKeyGenres).isEmpty()){
               ArrayList<String> movieIdList = genres2movies.get(newKeyGenres);
               for(String movieId : movieIdList){
                  statement.setInt(1, Integer.parseInt(newKeyGenres));
                  statement.setInt(2, Integer.parseInt(movieId));
                  statement.addBatch();
                  // statement.execute();
               }
            }        
         }

         statement.executeBatch();

         /////////////////////////////////////////////////////////////////



         // System.out.println(movieMap);
         // for(Map.Entry<String, ArrayList<String>> entry : stars2movies.entrySet()){
         //    System.out.println("Star: " + entry.getKey());
         //    System.out.println("Movie IDs: ");
         //    for(String id : entry.getValue()){
         //       System.out.println("          " + id);
         //    }
         // }
         connection.commit();

      }
      catch(Exception e){
         e.printStackTrace();    
      }
   }

	public static void main(String[] args){
      //for keeping track of runtime
      long startTime = System.currentTimeMillis();

		try{
         File inputFile = new File("mains243.xml");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         //Parse mains for movies
         MainSaxHandler mainhandler = new MainSaxHandler();
         saxParser.parse(inputFile, mainhandler);
         Map<String, MovieInfo> movieMap = new HashMap<String, MovieInfo>(mainhandler.getMap());

         //Parse casts for stars
         inputFile = new File("casts124.xml");
         CastsSaxHandler castshandler = new CastsSaxHandler(movieMap);
         saxParser.parse(inputFile, castshandler);
         movieMap = new HashMap<String, MovieInfo>(castshandler.getMap());

         //Parse actors for star dobs
         inputFile = new File("actors63.xml");
         ActorsSaxHandler actorshandler = new ActorsSaxHandler();
         saxParser.parse(inputFile, actorshandler);
         Map<String, String> stars2dob = new HashMap<String, String>(actorshandler.getMap());


         storeItems(movieMap, stars2dob);

         // for(Map.Entry<String, String> entry : stars2dob.entrySet()){
         //    System.out.println("Star: " + entry.getKey());
         //    System.out.println("DOB: " + entry.getValue());
         //    System.out.println();
         // }
         // for(Map.Entry<String, MovieInfo> entry : movieMap.entrySet()){
         // 	System.out.println("fid: " + entry.getKey());
         // 	System.out.println("Movie: " + entry.getValue().getTitle());
         // 	System.out.println("Director: " + entry.getValue().getDirector());
         // 	System.out.println("Year: " + entry.getValue().getYear());
         //    System.out.println("Stars: ");
         //    for(String star : entry.getValue().getStarSet()){
         //       System.out.println("        " + star);
         //    }
         // 	System.out.println("Genres: ");
         // 	for(String genre : entry.getValue().getGenreSet()){
         // 		System.out.println("        " + genre);
         // 	}
         // 	System.out.println("--------------------------------------------------");
         // }
         System.out.println("Total movies: " + movieMap.size());

		}catch(Exception e){
			e.printStackTrace();
		}
      long ms = (System.currentTimeMillis()-startTime);
      long remainderMS = ms % 1000;
      long sec = (ms/1000) % 60;
      long min = (ms/60000) % 60;
      long hr = ms/3600000;
      System.out.println("Runtime: " + hr + " hours " + min + " minutes " + sec + " seconds " + remainderMS + " milliseconds");
	}

}
