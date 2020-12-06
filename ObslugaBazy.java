import java.sql.*;

public class ObslugaBazy {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://Cloud2020-88732:3306/baza";

   static final String USER = "DKrol";
   static final String PASS = "password";
   
	static Exception error;
	
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();
      String sql;
	   
      DatabaseMetaData dbm = con.getMetaData();
      // check if "Users" table exist
      ResultSet tables = dbm.getTables(null, null, "Users", null);
      if (!tables.next()) {	//Table Users does not exist
      	sql="CREATE TABLE Users (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,firstname VARCHAR(30) NOT NULL,lastname VARCHAR(30) NOT NULL,email VARCHAR(50))";	
	stmt.executeUpdate(sql);
	sql = "INSERT INTO Users " +
                   "VALUES (1, 'Ali', 'Baba', 'gold@hand.com')";
      	stmt.executeUpdate(sql);
      	sql = "INSERT INTO Users " +
                   "VALUES (2, 'Baba', 'Wanga', 'putin@forever.ru')";
      	stmt.executeUpdate(sql);
      	sql = "INSERT INTO Users " +
                   "VALUES (3, 'Jan', 'Kowalski', 'jKowalski@gmail.com')";
      	stmt.executeUpdate(sql);
	System.out.println("Created table Users in the database.");
      }
	   
      sql = "SELECT id, firstname, lastname, email FROM Users";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("TABLE USERS");
      String foramt = "%s\t| %s\t| %s\t| %s";
      System.out.println(String.format(format, "ID", "NAME", "SURNAME", "EMAIL"));
      while(rs.next()){
	 int id = rs.getInt("id");
         String first = rs.getString("firstname");
         String last = rs.getString("lastname");
	 String email = rs.getString("email");
         System.out.println(String.format(format, id+"", first+"", last, email));
	 //System.out.println(" ID: "+id+"\t Imię: " + first+"\t Nazwisko: " + last+"\t Email: " + email);
      }
	  
	   boolean running=false;
	   while(running){
	   	Thread.sleep(1000);
	   	System.out.println("Running...");
	   }
	   
      rs.close();
	   
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
	   error=se;
   }catch(Exception e){
      e.printStackTrace();
	   error=e;
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
	      error=se2;
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
	      error=se;
      }
	System.out.println("Coś poszło nie tak...");
	error.printStackTrace();
   }
 }
}
