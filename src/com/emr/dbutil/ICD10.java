package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ICD10 extends DBTable {
	
	/*
	 * drop table if exists icd10;
	 * create table icd10 (
	 * icd varchar(24) not null primary key,
	 * disease varchar(128) not null,
	 * stat_code varchar(8) not null
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	
	public String icd;
	public String disease;
	public String stat_code;
	
	public void insert() {
		String sql = "insert into icd10 values (?, ?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setString(++i, icd);
			pst.setString(++i, disease);
			pst.setString(++i, stat_code);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

}
