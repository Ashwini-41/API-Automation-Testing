package com.databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseClass {

	public static Connection con;
	
	@BeforeMethod
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
		//System.out.println("Connnect sucecssful");

		return con;
	}
	
	@AfterMethod
	public void tearDown() throws SQLException{
		con.close();
	}
	
}
