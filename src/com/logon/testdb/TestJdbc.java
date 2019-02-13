package com.logon.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/logon_app?useSSL=false&serverTimezone=UTC";
		String user = "logonapp";
		String pass = "password";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn =
					DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
			
		}
		catch (Exception exc) {
			System.out.println("not connected");
			exc.getMessage();
		}

	}

}
