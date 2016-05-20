package com.emr.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IndexDB {
	/*
	 * structure
	 * 
	 * drop table if exists index_table;
	 * create table index_table (
	 * keyword varchar(32) not null,
	 * doc_name varchar(32) not null,
	 * begin int not null,
	 * end int not null,
	 * index word_doc (keyword, doc_name),
	 * unique (keyword, doc_name, begin, end)
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	
	private Connection conn = null;
	
	private final String url = "jdbc:mysql://localhost:3306/emr";
	
	private final String user = "root";
	
	private final String password = "123456";
	
	public void connect() 
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public int insert(String keyword, String doc_name, int begin, int end) {
		String sql = "insert into index_table values (?, ?, ?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, keyword);
			pst.setString(2, doc_name);
			pst.setInt(3, begin);
			pst.setInt(4, end);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
			return -1;
		}
		
		return 0;
	}

}
