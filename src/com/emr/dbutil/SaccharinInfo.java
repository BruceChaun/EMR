package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaccharinInfo extends DBTable {
	
	/*
	 * drop table if exists saccharin_info;
	 * create table saccharin_info (
	 * patient_id int,
	 * ztc varchar(32),
	 * sqxh varchar(12),
	 * bb varchar(8),
	 * bbsm varchar(64),
	 * zd varchar(128),
	 * rq datetime,
	 * qxthxhdbcd decimal(8, 4),
	 * primary key (sqxh)
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	
	public int patient_id;
	public String ztc = "";
	public String sqxh = "";
	public String bb = "";
	public String bbsm = "";
	public String zd = "";
	public String rq = "";
	public double qxthxhdbcd;
	
	public void insert() {
		String sql = "insert into saccharin_info(ztc, sqxh, "
				+ "bb, bbsm, zd, rq, qxthxhdbcd) values "
				+ "(?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			int i = 0;
			pst.setString(++i, ztc);
			pst.setString(++i, sqxh);
			pst.setString(++i, bb);
			pst.setString(++i, bbsm);
			pst.setString(++i, zd);
			pst.setString(++i, rq);
			pst.setDouble(++i, qxthxhdbcd);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}

}
