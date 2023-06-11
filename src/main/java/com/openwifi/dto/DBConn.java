package com.openwifi.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConn {
	private static Connection conn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		if( conn == null) {
			String url = "jdbc:sqlite:H:/identifier.sqlite";
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		}
		return conn;
	}
}
