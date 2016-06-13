package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ATC extends DBTable {
	
	/*
	 * drop table if exists atc;
	 * create table atc (
	 * atc_code varchar(16) not null primary key,
	 * atc_name varchar(64) not null
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	
	public String atc_code;
	public String atc_name;
	
	public void insert() {
		String sql = "insert into atc values (?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setString(++i, atc_code);
			pst.setString(++i, atc_name);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
		}
	}

}
