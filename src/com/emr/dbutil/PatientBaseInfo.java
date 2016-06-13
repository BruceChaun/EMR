package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientBaseInfo extends DBTable {
	
	/*
	 * drop table if exists patient_base_info;
	 * create table patient_base_info (
	 * patient_id int not null primary key,
	 * birth_place int not null,
	 * date_of_birth date not null,
	 * sex varchar(2) not null,
	 * fb varchar(32) not null
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	public void insert(int patientId, int birthPlace,
			String dateOfBirth, String sex, String fb) {
		String sql = "insert into patient_base_info values (?, ?, ?, ?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, patientId);
			pst.setInt(2, birthPlace);
			pst.setString(3, dateOfBirth);
			pst.setString(4, sex);
			pst.setString(5, fb);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}

}
