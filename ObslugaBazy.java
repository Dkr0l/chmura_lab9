import java.sql.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ObslugaBazy {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://Cloud2020-88732:3306/baza";

	static final String USER = "DKrol";
	static final String PASS = "password";

	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			DatabaseMetaData dbm = conn.getMetaData();
			// check if "Users" table exist
			ResultSet tables = dbm.getTables(null, null, "Users", null);
			if (!tables.next()) {	//Table Users does not exist
				sql="CREATE TABLE Users (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,firstname VARCHAR(20) NOT NULL,lastname VARCHAR(20) NOT NULL,email VARCHAR(30))";	
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
			
			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			boolean running=true;
			while(running){
				System.out.println("\nType a command");
				String line=buffer.readLine();
				executeCmd(line);
			}
			
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
	
	static void executeCmd(String line){
		try{
			String command = line.substring(0,5));
			if(command.equals("SELECT")||command.equals("select"))printTab(line);
			else stmt.executeUpdate(line);
		}catch(SQLException se){
			System.out.println("\n Something went wrong...");
			se.printStackTrace();
		}
	}
	
	static void printTab(String line){
		try{
			ResultSet rs = stmt.executeQuery(line);
			System.out.println("\n\t\tTABLE USERS");
			String format = "%6s\t| %20s\t| %20s\t| %30s";
			System.out.println(String.format(format, "ID", "NAME", "SURNAME", "EMAIL"));
			System.out.println("--------+-----------------------+-----------------------+------------------------------");	//draws a horizontal line
			while(rs.next()){
				int id = rs.getInt("id");
				String first = rs.getString("firstname");
				String last = rs.getString("lastname");
				String email = rs.getString("email");
				System.out.println(String.format(format, id+"", first+"", last, email));
			}
			rs.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
}
