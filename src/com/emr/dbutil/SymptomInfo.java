package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SymptomInfo extends DBTable {
	
	/*
	 * drop table if exists symptom_info;
	 * create table symptom_info (
	 * patient_id int,
	 * visit_id int,
	 * recording_date datetime,
	 * time_point datetime,
	 * vital_signs varchar(16),
	 * vital_signs_values decimal(8, 3),
	 * units varchar(8)
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	
	public int patient_id;
	public int visit_id;
	public String recording_date = "";
	public String time_point = "";
	public String vital_signs = "";
	public double vital_signs_values;
	public String units = "";

	public void insert() {
		String sql = "insert into symptom_info values "
				+ "(?,  ?,  ?,  ?,  ?,  ?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setInt(++i, patient_id);
			pst.setInt(++i, visit_id);
			pst.setString(++i, recording_date);
			pst.setString(++i, time_point);
			pst.setString(++i, vital_signs);
			pst.setDouble(++i, vital_signs_values);
			pst.setString(++i, units);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}

}
