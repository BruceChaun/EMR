package com.emr.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTable {
	protected Connection conn = null;
	
	private final String url = "jdbc:mysql://localhost:3306/emr";
	
	private final String user = "root";
	
	private final String password = "123456";
	
	public void connect() 
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = DriverManager.getConnection(url, user, password);
	}

}
