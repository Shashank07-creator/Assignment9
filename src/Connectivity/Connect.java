package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
	private static Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String user = "System",password = "sys";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			
			con = DriverManager.getConnection(url,user,password);
			
			
		} catch (SQLException e) {
			System.out.println("Error in Connectivity.Connect.getConnection() "+e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {

			System.out.println("Error in Connectivity.Connect.closeConnection() "+e.getMessage());
			
		}
	}
}

	
