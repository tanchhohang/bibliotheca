package com.bibliotheca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConfig {
	
	private static final String DB_NAME = "bibliotheca";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	private static final String EMAIL = "root";
	private static final String PASSWORD = "";
	
	
	public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    return DriverManager.getConnection(URL, EMAIL, PASSWORD);
	}


	
}