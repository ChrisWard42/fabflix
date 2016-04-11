<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"
%>

<h3>hello world</h3><br>
<%
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection connection =
      DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "testuser", "testpass");
    Statement select = connection.createStatement();
    ResultSet result = select.executeQuery("Select * from creditcards where ID LIKE '87%'");
    out.println("The results of the query");

    ResultSetMetaData  metadata;
    metadata = result.getMetaData();
    out.println("There are "+metadata.getColumnCount()+" columns <br>");
    for (int i=1; i<=metadata.getColumnCount(); i++){
      out.println("Type of column "+i+" is " + 
		  metadata.getColumnTypeName(i) + "<br>");
    }
    while (result.next()){
      out.print("First_name  = "+result.getString(2));
      out.print("  Last_name = "+result.getString(3));
      out.println("<br>");
    } 
%> 


</body>
</html>