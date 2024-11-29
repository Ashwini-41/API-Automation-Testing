package Api_Automation.API_Automation;

/**
 * Hello world!
 *
 */
//public class App 
//{
//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//    }
//}

//package com.databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.testng.annotations.Test;

public class App {

	public static Connection con;
	
	public static void main(String[] args) {
		App bs = new App();
		bs.setUp();
	}
	
	//@Test
	public Connection setUp() {
		
		try {
			String dburl = "jdbc:mysql://localhost:3306/databasetest";
			String UserName = "root";
			String Password = "";
			
			con = DriverManager.getConnection(dburl, UserName, Password);
			
		}catch(SQLException sqe) {
			System.out.println(sqe.getSQLState());
			System.out.println(sqe.getErrorCode());
			System.out.println(sqe.getMessage());
			sqe.printStackTrace();

			
		}
		System.out.println("Connnect sucecssful");

		return con;
	}
	
	
//	public void tearDown() throws SQLException{
//		con.close();
//	}
	
}

