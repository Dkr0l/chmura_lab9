import java.sql.*;

public class ObslugaBazy {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/baza";

   static final String USER = "DKrol";
   static final String PASS = "password";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();
      String sql;
	   
      sql="CREATE TABLE Users (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,firstname VARCHAR(30) NOT NULL,lastname VARCHAR(30) NOT NULL,email VARCHAR(50),reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)";	
      stmt.executeUpdate(sql);
      System.out.println("Created table in given database...");
	   
      sql = "INSERT INTO Users " +
                   "VALUES (1, 'Ali', 'Baba', 'gold@hand.com')";
      stmt.executeUpdate(sql);
      sql = "INSERT INTO Users " +
                   "VALUES (2, 'Baba', 'Wanga', 'putin@forever.ru')";
      stmt.executeUpdate(sql);
      sql = "INSERT INTO Users " +
                   "VALUES (3, 'Jan', 'Kowalski', 'jKowalski@gmail.com')";
      stmt.executeUpdate(sql);
	   
      sql = "SELECT firstname, lastname, email FROM Users";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         String first = rs.getString("firstname");
         String last = rs.getString("lastname");
	 String email = rs.getString("email");

         System.out.println(", Imię: " + first);
         System.out.println(", Nazwisko: " + last);
	 System.out.println(", Email: " + email);
      }
	   
      rs.close();
	   
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}
