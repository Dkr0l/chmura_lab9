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
			String command = line.substring(0,6);
			System.out.println(command);
			if(command.equals("SELECT")||command.equals("select"))printTab(line);
			else stmt.executeUpdate(line);
		}catch(SQLException se){
			System.out.println("\n Something went wrong...");
			se.printStackTrace();
		}
	}
	
	static void printTab(String line) throws SQLException{
		ResultSet rs = stmt.executeQuery(line);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		String format="|%20s";
		for (int i = 1; i <= columnsNumber; i++) {
           			System.out.print(String.format(format, rsmd.getColumnName(i)));
       		}
		System.out.println("");
		for (int i = 1; i <= columnsNumber; i++) {
           			System.out.print("+--------------------");
       		}
		System.out.println("+");
   		while (rs.next()) {
       			for (int i = 1; i <= columnsNumber; i++) {
           			String columnValue = rs.getString(i);
           			System.out.print(String.format(format, columnValue));
       			}
       			System.out.println("|");
   		}
		rs.close();

	}
}
