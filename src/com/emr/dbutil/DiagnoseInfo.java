package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiagnoseInfo extends DBTable {
	
	public int patient_id;
	public int visit_id;
	public String diagnosis_type = "";
	public int diagnosis_no;
	public String diagnosis_desc = "";
	public String diagnosis_date = "";
	public int treat_days;
	public String treat_result = "";
	public String oper_treat_indicator = "";
	public String code_version = "";

	public void insert() {
		String sql = "insert into diagnose_info values "
				+ "(?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setInt(++i, patient_id);
			pst.setInt(++i, visit_id);
			pst.setString(++i, diagnosis_type);
			pst.setInt(++i, diagnosis_no);
			pst.setString(++i, diagnosis_desc);
			pst.setString(++i, diagnosis_date);
			pst.setInt(++i, treat_days);
			pst.setString(++i, treat_result);
			pst.setString(++i, oper_treat_indicator);
			pst.setString(++i, code_version);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}
}
