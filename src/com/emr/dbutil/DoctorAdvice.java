package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorAdvice extends DBTable {
	
	/*
	 * drop table if exists doctor_advice;
	 * create table doctor_advice (
	 * patient_id int,
	 * visit_id int,
	 * order_no int,
	 * order_sub_no int,
	 * repeat_indicator int,
	 * order_class varchar(8),
	 * order_text varchar(64),
	 * dosage varchar(16),
	 * dosage_units varchar(8),
	 * administration varchar(16),
	 * duration varchar(16),
	 * duration_units varchar(8),
	 * start_date_time datetime,
	 * stop_date_time datetime,
	 * frequency varchar(16),
	 * freq_counter varchar(8),
	 * freq_interval varchar(8),
	 * freq_interval_unit varchar(8),
	 * freq_detail varchar(32),
	 * perform_schedule varchar(32),
	 * ca_pregnancy_history varchar(32),
	 * enter_date_time datetime,
	 * order_status int,
	 * billing_attr int,
	 * last_accting_date_time datetime,
	 * last_perform_date_time datetime,
	 * stop_nurse varchar(32),
	 * stop_order_date_time varchar(32),
	 * drug_billing_attr varchar(8)
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	public int patient_id;
	public int visit_id;
	public int order_no;
	public int order_sub_no;
	public int repeat_indicator;
	public String order_class = "";
	public String order_text = "";
	public String dosage = "";
	public String dosage_units = "";
	public String administration = "";
	public String duration = "";
	public String duration_units = "";
	public String start_date_time = "";
	public String stop_date_time = "";
	public String frequency = "";
	public String freq_counter = "";
	public String freq_interval = "";
	public String freq_interval_unit = "";
	public String freq_detail = "";
	public String perform_schedule = "";
	public String ca_pregnancy_history = "";
	public String enter_date_time = "";
	public int order_status;
	public int billing_attr;
	public String last_accting_date_time = "";
	public String last_perform_date_time = "";
	public String stop_nurse = "";
	public String stop_order_date_time = "";
	public String drug_billing_attr = "";

	public void insert() {
		String sql = "insert into doctor_advice values (?,  ?,  ?,  "
				+ "?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  "
				+ "?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setInt(++i, patient_id);
			pst.setInt(++i, visit_id);
			pst.setInt(++i, order_no);
			pst.setInt(++i, order_sub_no);
			pst.setInt(++i, repeat_indicator);
			pst.setString(++i, order_class);
			pst.setString(++i, order_text);
			pst.setString(++i, dosage);
			pst.setString(++i, dosage_units);
			pst.setString(++i, administration);
			pst.setString(++i, duration);
			pst.setString(++i, duration_units);
			pst.setString(++i, start_date_time);
			pst.setString(++i, stop_date_time);
			pst.setString(++i, frequency);
			pst.setString(++i, freq_counter);
			pst.setString(++i, freq_interval);
			pst.setString(++i, freq_interval_unit);
			pst.setString(++i, freq_detail);
			pst.setString(++i, perform_schedule);
			pst.setString(++i, ca_pregnancy_history);
			pst.setString(++i, enter_date_time);
			pst.setInt(++i, order_status);
			pst.setInt(++i, billing_attr);
			pst.setString(++i, last_accting_date_time);
			pst.setString(++i, last_perform_date_time);
			pst.setString(++i, stop_nurse);
			pst.setString(++i, stop_order_date_time);
			pst.setString(++i, drug_billing_attr);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}
}
