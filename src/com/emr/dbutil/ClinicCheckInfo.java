package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClinicCheckInfo extends DBTable {
	/*
	 * drop table if exists clinic_check_info;
	 * create table clinic_check_info (
	 * patient_id int not null,
	 * exam_no int not null,
	 * exam_date_time datetime not null,
	 * exam_para varchar(32) not null,
	 * description varchar(512) not null,
	 * impression varchar(512) not null,
	 * recommendation varchar(512) not null,
	 * memo varchar(512) not null,
	 * primary key (patient_id, exam_no)
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */

	public void insert(int patientId, int examNo, String examDateTime,
			String examPara, String desc, String impression,
			String recommend, String memo) {
		String sql = "insert into clinic_check_info values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, patientId);
			pst.setInt(2, examNo);
			pst.setString(3, examDateTime);
			pst.setString(4, examPara);
			pst.setString(5, desc);
			pst.setString(6, impression);
			pst.setString(7, recommend);
			pst.setString(8, memo);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}
}
