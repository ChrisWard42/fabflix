import java.sql.*;
import java.util.Scanner;
import java.io.Console;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.lang.StringBuilder;

public class jdbcTest{
    // Pretty colors! strings below are for printing in color
    // Format: println(ANSI_COLOR + String + ANSI_RESET)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Set to prevent printing of duplicate movies in print_movies.
    // Duplicates arise from printing results of multiple queries.
    // However, this is rare and therefore can just easily be circumvented with a set.
    public static HashSet<Integer> printMoviesIDSet = new HashSet<Integer>();

    /**
    * Start of Code borrowed from: https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlexception.html
    **/
    public static boolean ignoreSQLException(String sqlState) {

        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }

        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32"))
            return true;

        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55"))
            return true;

        return false;
    }

    public static void printSQLException(SQLException ex) {

        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(
                    ((SQLException)e).
                    getSQLState()) == false) {

                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " +
                        ((SQLException)e).getSQLState());

                    System.err.println("Error Code: " +
                        ((SQLException)e).getErrorCode());

                    System.err.println("Message: " + e.getMessage());

                    Throwable t = ex.getCause();
                    while(t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }
    /**
    * End of Code borrowed from: https://docs.oracle.com/javase/tutorial/jdbc/basics/sqlexception.html
    **/

    // Overload print_movie_results with booleans default to true
    public static void print_movie_results(ResultSet result){
        print_movie_results(result, true, true);
    }

    // Prints out attributes from table with movies table attributes
    public static void print_movie_results(ResultSet result, Boolean firstResult, Boolean lastResult){
        try{
            if(firstResult==true)
                System.out.println(ANSI_GREEN + "+--------------------------------------------" + ANSI_RESET);

            while( result.next()){
                // Check if this movie has already been printed
                if(printMoviesIDSet.add(result.getInt(1))){
                    System.out.println(ANSI_GREEN + "| " + ANSI_RESET + "Id = " + result.getInt(1));
                    System.out.println(ANSI_GREEN + "| " + ANSI_RESET + "Title = " + result.getString(2));
                    System.out.println(ANSI_GREEN + "| " + ANSI_RESET + "Year = " + result.getInt(3));
                    System.out.println(ANSI_GREEN + "| " + ANSI_RESET + "Director = " + result.getString(4));
                    System.out.println(ANSI_GREEN + "| " + ANSI_RESET + "BannerURL = " + result.getString(5));
                    System.out.println(ANSI_GREEN + "| " + ANSI_RESET + "TrailerURL = " + result.getString(6));
                    if(lastResult==false || !result.isLast())
                        System.out.println(ANSI_GREEN + "+" + ANSI_RESET + "--------------------------------------------");
                }
            }

            if(lastResult==true)
                System.out.println(ANSI_GREEN + "+--------------------------------------------" + ANSI_RESET);

        } catch(SQLException e){
            printSQLException(e);
        }
    }


    /**
     * Print out (to the screen) the movies featuring a given star. All movie attributes should appear,
     * labeled and neatly arranged; the star can be queried via first name and/or last name or by ID.
     * First name and/or last name means that a star should be queried by both
     * a) first name AND last name b) first name or last name.
     */
    public static void print_movies(Connection connection, Console console) {
        Scanner reader = new Scanner(System.in);

        String idQuery = "" +
                "SELECT DISTINCT m.id, m.title, m.year, m.director, m.banner_url, m.trailer_url " + 
                "FROM movies AS m, stars_in_movies AS sim " + 
                "WHERE sim.star_id = ? AND sim.movie_id = m.id";

        String orQuery = "" + 
                "SELECT DISTINCT m.id, m.title, m.year, m.director , m.banner_url, m.trailer_url " +
                "FROM movies AS m, stars_in_movies AS sim," + 
                    "(SELECT * " + 
                     "FROM stars AS s2 " + 
                     "WHERE first_name = ? OR last_name = ?) AS ids " + 
                "WHERE sim.star_id = ids.id AND sim.movie_id = m.id";

        String andQuery = "" + 
                "SELECT DISTINCT m.id, m.title, m.year, m.director , m.banner_url, m.trailer_url " +
                "FROM movies AS m, stars_in_movies AS sim," + 
                    "(SELECT * " + 
                     "FROM stars AS s2 " + 
                     "WHERE first_name = ? AND last_name = ?) AS ids " + 
                "WHERE sim.star_id = ids.id AND sim.movie_id = m.id";


        System.out.print("Enter Star ID (leave empty if unknown): " );
        String starID = reader.nextLine();

        // Strip input of non-alphanumeric characters
        starID = starID.replaceAll("[^0-9]", "");
        int intStarID = 0;

        // If user enters a starID, then there is no need to enter a name since starIDs map to 
        // one unique star.
        if(!starID.isEmpty()){
            intStarID = Integer.parseInt(starID);
            try (PreparedStatement preparedSelect = connection.prepareStatement(idQuery)){
                preparedSelect.setInt(1, intStarID);

                try(ResultSet result = preparedSelect.executeQuery()){
                    print_movie_results(result);
                }

            } catch(SQLException e){
                printSQLException(e);
            }
        }    
        else{
            System.out.print("Enter star name: " );
            String name = reader.nextLine();
            String names[] = name.split("\\s+");
            ArrayList<String> possibleFirstNames = new ArrayList<String>();
            ArrayList<String> possibleLastNames = new ArrayList<String>();

            // Building the different permutations of
            // first and last names when many names seperated by a space are entered
            if(names.length > 1){
                StringBuilder firstName = new StringBuilder();
                StringBuilder lastName = new StringBuilder();
                for(int i = 0; i < names.length - 1; ++i){
                    firstName.append(names[i]);
                    firstName.append(" ");
                    possibleFirstNames.add(firstName.substring(0, firstName.length()-1));

                    for(int j = i + 1; j < names.length; ++j){
                        if(j != names.length){
                            lastName.append(names[j]);
                            lastName.append(" ");
                        }
                    }
                    possibleLastNames.add(lastName.substring(0, lastName.length()-1));
                    lastName.setLength(0);
                }
            }

            try (PreparedStatement nameSelect = connection.prepareStatement(orQuery)){
                Boolean firstResult = true;
                Boolean lastResult = true;
                nameSelect.setString(1, name);
                nameSelect.setString(2, name);

                try(ResultSet result = nameSelect.executeQuery()){
                    if(names.length > 1)
                        lastResult = false;

                    print_movie_results(result, firstResult, lastResult);
                }

                if(!possibleFirstNames.isEmpty() && !possibleLastNames.isEmpty()){
                    try (PreparedStatement twoNameSelect = connection.prepareStatement(andQuery)){
                        firstResult = false;
                        for(int i = 0; i < possibleFirstNames.size(); ++i){
                            twoNameSelect.setString(1, possibleFirstNames.get(i));
                            twoNameSelect.setString(2, possibleLastNames.get(i));

                            try(ResultSet result = twoNameSelect.executeQuery()){
                                if(i+1 == possibleFirstNames.size())
                                    lastResult = true;

                                print_movie_results(result, firstResult, lastResult);
                            }
                        }
                    }
                }

            } catch(SQLException e){
                printSQLException(e);
            }

        }



    }


    /**
     * Insert a new star into the database. If the star has a single name, add it as his last_name
     * and assign an empty string ("") to first_name.
     */
    public static void insert_star(Connection connection, Console console) {
        Scanner input = new Scanner(System.in);
        
        String firstName = "";
        String lastName = "";
        String DOB = "";
        String photoURL = "";
        
        do {
            System.out.print("Please enter star's first name: ");
           
            firstName = input.nextLine();
            
            System.out.print("Please enter star's last name: ");
            
            lastName = input.nextLine();
            
            if (lastName.equals("")) {
                lastName = firstName;
                firstName = "";
            }
            
            if (firstName.equals("") && lastName.equals("")) {
                System.out.println("Inputs Invalid. Please retry again...");
            }
        } while (firstName.equals("") && lastName.equals(""));

        System.out.print("Please enter star's DOB (YYYY-MM-DD) (OPTIONAL): ");
        DOB = input.nextLine();
        
        System.out.print("Please enter star's photo URL (OPTIONAL): ");
        photoURL = input.nextLine();
        
        String insertTableSQL = "INSERT INTO stars(first_name, last_name, dob, photo_url) VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);){
               
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            if (DOB.equals("")) {
                preparedStatement.setNull(3, java.sql.Types.DATE);
            }
            else {
                preparedStatement.setDate(3, java.sql.Date.valueOf(DOB));
            }
            if (photoURL.equals("")) {
                preparedStatement.setNull(4, java.sql.Types.VARCHAR);
            }
            else {
                preparedStatement.setString(4, photoURL);
            }
            
            preparedStatement.executeUpdate();
            System.out.println(ANSI_GREEN + "Star Inserted Successfully:" + ANSI_RESET + firstName + " " + lastName);

        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    /**
     * Insert a customer into the database. Do not allow insertion of a customer if his credit card
     * does not exist in the credit card table. The credit card table simulates the bank records.
     * If the customer has a single name, add it as his last_name and assign an empty string ("") to first_name.
     */
    public static void insert_customer(Connection connection, Console console) {
        // Get customer information from the user        
        String firstName = console.readLine("First Name: "); // maxlen: 50
        String lastName = console.readLine("Last Name: "); // maxlen: 50
        String ccID = console.readLine("Credit Card ID: "); // maxlen: 20
        String address = console.readLine("Address: "); // maxlen: 200
        String email = console.readLine("Email: "); // maxlen: 50
        String password = new String(console.readPassword("Password: ")); // maxlen: 20
        
        // Swap single name customers to lastName
        if (lastName.equals("")) {
            lastName = firstName;
            firstName = "";
        }

        // Check credit card database for customer's card, deny insert if not valid
        try (PreparedStatement select = connection.prepareStatement("SELECT * FROM creditcards WHERE id = ?"); ) {
            select.setString(1, ccID);
            try (ResultSet cc_exists = select.executeQuery(); ) {
                if (!cc_exists.next()) {
                    System.out.println(ANSI_RED + "Insertion Failed:" + ANSI_RESET + " Customer has no valid credit card stored in the database.");
                    return;
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        // Insert the customer into the database
        try (PreparedStatement insert = connection.prepareStatement("INSERT INTO customers(first_name, last_name, cc_id, address, email, password)"
                                                                + " VALUES (?, ?, ?, ?, ?, ?)"); ) {
            insert.setString(1, firstName);
            insert.setString(2, lastName);
            insert.setString(3, ccID);
            insert.setString(4, address);
            insert.setString(5, email);
            insert.setString(6, password);

            int rows_affected = insert.executeUpdate();

            if (rows_affected == 0) {
                System.out.println(ANSI_RED + "Insertion Failed:" + ANSI_RESET + " Unable to insert customer due to invalid input.");
                return;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }

        System.out.println(ANSI_GREEN + "Customer Inserted Successfully:" + ANSI_RESET + firstName + " " + lastName);
    }


    /**
     * Delete a customer from the database. (The delete operation should be queried by Credit Card id)
     */
    public static void delete_customer(Connection connection, Console console) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter customer's credit card id: ");
        String ccid = reader.nextLine();

        try(PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM customers WHERE cc_id = ?")){
            deleteStmt.setString(1, ccid);

            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected == 0){
                System.out.println(ANSI_RED + "Delete failed:" + ANSI_RESET + " No customer with that credit card id exists.");
                return;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }

        System.out.println(ANSI_GREEN + "Customer Deleted Successfully" + ANSI_RESET);

    }


    /**
     * Provide the metadata of the database; in particular, print out the name of each table and,
     * for each table, each attribute and its type.
     */

    public static void printTable(String title, ArrayList<String> contentName, int maxLength) {
        int max = Math.max(title.length(), maxLength);
        String border = "+";
        for (int x = 0; x < (max + 2); x++) {
            border += "-";
        }
        border = border + "+";
        System.out.println(border);
        String space = "";
        for (int x = 0; x < (max - title.length()); x++) {
            space += " ";
        }
        System.out.println("| " + title + space + " |");
        System.out.println(border);

        for (String s : contentName) {
            space = "";
            for (int x = 0; x < (max - s.length()); x++) {
                space += " ";
            }
            System.out.println("| " + s + space + " |");
        }
        System.out.println(border + "\n");

    }

    public static void printColTable(String title, ArrayList<String> fieldList, ArrayList<String> typeList, int maxFieldLength, int maxTypeLength) {
        String titleBorder = "+";
        for (int x = 0; x < title.length() + 2; x++) {
            titleBorder += "-";
        }
        titleBorder += "+";
        System.out.println(titleBorder);

        String tableTitle = "| " + title + " |";
        System.out.println(tableTitle);
        System.out.println(titleBorder);



        String border = "+";
        int maxFieldCol = Math.max("Field".length(), maxFieldLength);
        int maxTypeCol = Math.max("Type".length(), maxTypeLength);

        for (int x = 0; x < (maxFieldCol + 2) + (maxTypeCol + 2); x++) {
            if (x == (maxFieldCol + 2)) {
                border += "+";
            } 
            border += "-";
        }
        border += "+";
        System.out.println(border);



        String colTitle = "| FIELD";
        for (int x = 0; x < (maxFieldLength - "Field".length()); x++) {
            colTitle += " ";
        }

        colTitle += " | TYPE";
        for (int x = 0; x < (maxTypeLength - "Type".length()); x++) {
            colTitle += " ";
        }
        colTitle += " |";

        System.out.println(colTitle);
        System.out.println(border);

        for (int x = 0; x < fieldList.size(); x++) {
            String content = "| ";
            content += ((String)fieldList.get(x));
            for (int y = 0; y < (maxFieldCol - ((String)fieldList.get(x)).length()); y++) {
                content += " ";
            }
            content += " | ";
            content += ((String)typeList.get(x));
            for (int y = 0; y < (maxTypeCol - ((String)typeList.get(x)).length()); y++) {
                content += " ";
            }
            content += " |";
            System.out.println(content);
        }
        System.out.println(border + "\n");
        
    }    

    public static void get_metadata(Connection connection, Console console) {
        DatabaseMetaData meta = null;
        try {
            meta = connection.getMetaData();
            ArrayList<String> tableName = new ArrayList<String>();
            try (ResultSet result = meta.getTables(null, null, null, new String[]{"TABLE"});) {
                int maxLength = 0;

                while (result.next()) {
                    String line = result.getString("TABLE_NAME");
                    tableName.add(line);

                    if (line.length() > maxLength) {
                        maxLength = line.length();
                    }
            
                }

                printTable("Tables_in_Database", tableName, maxLength);
            }

            for (String s : tableName) {
                try (ResultSet colResult = meta.getColumns(null, null, s, null);) {
                    ArrayList<String> fieldList = new ArrayList<String>();
                    int maxFieldLength = 0;
                    ArrayList<String> typeList = new ArrayList<String>();
                    int maxTypeLength = 0;

                    while (colResult.next()) {
                        String field = colResult.getString("COLUMN_NAME");
                        String type = colResult.getString("TYPE_NAME");

                        fieldList.add(field);
                        typeList.add(type);

                        maxFieldLength = Math.max(maxFieldLength, field.length());
                        maxTypeLength = Math.max(maxTypeLength, type.length());

                    }

                    printColTable(s, fieldList, typeList, maxFieldLength, maxTypeLength);
                }
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    /**
     * Enter a valid SELECT/UPDATE/INSERT/DELETE SQL command. The system should take the corresponding action,
     * and return and display the valid results. For a SELECT query, display the answers.
     * For the other types of queries, give enough information about the status of the execution of the query.
     * For instance, for an UPDATE query, show the user how many records have been successfully changed.
     */
    public static void execute_sql_command(Connection connection, Console console) {
        Scanner input = new Scanner(System.in).useDelimiter("\\s*;\\s*");
        System.out.print("Enter Query (terminate with ;): ");
        String query = input.next();

        try (Statement statement = connection.createStatement(); ) {         
            // Handle Select Statements
            if (query.toLowerCase().split("\\s+")[0].contains("select")) {
                try (ResultSet results = statement.executeQuery(query); ) {
                    ResultSetMetaData metadata = results.getMetaData();

                    // Get column count and table name
                    int numCols = metadata.getColumnCount();
                    if (numCols < 1)
                        return;
                    String tblName = metadata.getTableName(1);
                    
                    // Get column types and names
                    ArrayList<Integer> colTypes = new ArrayList<Integer>();
                    for (int i = 1; i <= numCols; ++i) {
                        colTypes.add(metadata.getColumnType(i));
                    }
                    ArrayList<String> colNames = new ArrayList<String>();
                    for (int i = 1; i <= numCols; ++i) {
                        colNames.add(metadata.getColumnName(i));
                    }

                    // Build a query to get column lengths
                    StringBuilder lengthQuery = new StringBuilder("SELECT ");
                    for (int i = 0; i < numCols; ++i) {
                        lengthQuery.append("max(length(");
                        lengthQuery.append(colNames.get(i));
                        lengthQuery.append(")) ");
                        lengthQuery.append(colNames.get(i));
                        if (i < numCols - 1)
                            lengthQuery.append(",");
                        lengthQuery.append(" ");
                    }
                    lengthQuery.append("FROM ");
                    lengthQuery.append(tblName);

                    // Query database to get length of longest entry in each column
                    ArrayList<Integer> colLengths = new ArrayList<Integer>();
                    try (Statement info = connection.createStatement();
                         ResultSet lengths = info.executeQuery(lengthQuery.toString()); ) {
                        lengths.last();
                        for (int i = 1; i <= numCols; ++i) {
                            colLengths.add(Math.max(lengths.getInt(i), colNames.get(i-1).length()));
                        }
                    }

                    // Formatting of border
                    StringBuilder border = new StringBuilder("+");
                    for (int max : colLengths) {
                        border.append(new String(new char[max + 2]).replace("\0", "-"));
                        border.append("+");
                    }

                    // Formatting of header
                    StringBuilder header = new StringBuilder("|");
                    for (int i = 0; i < colLengths.size(); ++i) {
                        int spaces = colLengths.get(i) - colNames.get(i).length();
                        header.append(" ");
                        header.append(colNames.get(i));
                        if (spaces > 0)
                            header.append(new String(new char[spaces]).replace("\0", " "));
                        header.append(" |");
                    }

                    // Print Header Info
                    System.out.println(border);
                    System.out.println(header);

                    // Print Results
                    System.out.println(border);
                    int numRows = 0;
                    while (results.next()) {
                        ++numRows;
                        StringBuilder row = new StringBuilder("|");
                        for (int i = 0; i < colLengths.size(); ++i) {
                            row.append(" ");

                            String colVal;
                            if (colTypes.get(i) == 4) { // Integer
                                colVal = String.valueOf(results.getInt(i+1));
                                int spaces = colLengths.get(i) - colVal.length();
                                if (spaces > 0)
                                    row.append(new String(new char[spaces]).replace("\0", " "));
                                row.append(colVal);
                            }
                            else if (colTypes.get(i) == 12) { // String
                                colVal = results.getString(i+1);
                                if (colVal == null)
                                    colVal = "NULL";
                                int spaces = colLengths.get(i) - colVal.length();
                                row.append(colVal);
                                if (spaces > 0)
                                    row.append(new String(new char[spaces]).replace("\0", " "));
                            }
                            else { // == 91 Date
                                Date colValDate = results.getDate(i+1);
                                if (colValDate == null)
                                    colVal = "NULL";
                                else
                                    colVal = colValDate.toString();
                                int spaces = colLengths.get(i) - colVal.length();
                                row.append(colVal);
                                if (spaces > 0)
                                    row.append(new String(new char[spaces]).replace("\0", " "));
                            }

                            row.append(" |");
                        }
                        System.out.println(row);
                    }
                    System.out.println(border);
                    System.out.println(numRows + " rows in set.");
                } 
            }
            // Handle Insert/Update/Delete Statements
            else if (query.toLowerCase().split("\\s+")[0].contains("insert")
                  || query.toLowerCase().split("\\s+")[0].contains("update")
                  || query.toLowerCase().split("\\s+")[0].contains("delete")) {
                int rowsAffected = statement.executeUpdate(query);
                System.out.println(ANSI_GREEN + "Query OK." + ANSI_RESET + " " + String.valueOf(rowsAffected) + " rows affected.");
            }
            // Deny permission to all other query types
            else {
                System.out.println(ANSI_RED + "Invalid:" + ANSI_RESET + " Invalid syntax or you lack acccess to perform this type of query.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    public static void main(String[] args) throws Exception{
        //Console instead of scanner for typing in password without echo
        Console console = null;
        String db = null;
        String user = null;

        console = System.console();

        while(true){
            if (console != null){
                System.out.println("Enter mysql username, or enter :quit to exit...");
                user = console.readLine("Username(:quit to exit): ");

                if (!user.equals(":quit")){
                    // Characters typed will not show using readPassword
                    char[] passArray = console.readPassword("Password: ");
                    String pass = new String(passArray);

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/", user, pass); ) {
                        db = console.readLine("Database name: ");
                        connection.setCatalog(db);

                        System.out.println(ANSI_GREEN + "Access granted" + ANSI_RESET);

                        String option = "";

                        while(!option.matches("7")){
                            System.out.println("1 - Print movies featuring given star");
                            System.out.println("2 - Insert star into database");
                            System.out.println("3 - Insert customer into database");
                            System.out.println("4 - Delete customer from database");                            
                            System.out.println("5 - Display database metadata");
                            System.out.println("6 - Enter SELECT/UPDATE/INSERT/DELETE query");
                            System.out.println("7 - Exit this menu");   
                            option = console.readLine("Enter option number: ");
                            System.out.println();

                            switch(option){
                                case "1":
                                    print_movies(connection, console);
                                    System.out.println();
                                    break;
                                case "2":
                                    insert_star(connection, console);
                                    System.out.println();
                                    break;
                                case "3":
                                    insert_customer(connection, console);
                                    System.out.println();
                                    break;
                                case "4":
                                    delete_customer(connection, console);
                                    System.out.println();
                                    break;
                                case "5":
                                    get_metadata(connection, console);
                                    System.out.println();
                                    break;
                                case "6":
                                    execute_sql_command(connection, console);
                                    System.out.println();
                                    break;
                                case "7":
                                    System.out.println();
                                    break;
                                default:
                                    System.out.println(ANSI_RED + "Invalid option" + ANSI_RESET);
                                    System.out.println();
                                    break;
                            }
                        }

                    } catch (SQLException sqle){
                        printSQLException(sqle);
                    }
                }
                else //if user == ":quit"
                    break;                
            }
        }
    }
}
