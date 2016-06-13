package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UrineInfo extends DBTable {
	
	/*
	 * drop table if exists urine_info;
	 * create table urine_info (
	 * patient_id int,
	 * ztc varchar(32),
	 * sqxh varchar(12),
	 * bb varchar(8),
	 * bbsm varchar(64),
	 * zd varchar(128),
	 * rq datetime,
	 * nbxb decimal(8, 3),
	 * nbxbt decimal(8, 5),
	 * nbzcd decimal(8, 6),
	 * ndydxsy varchar(16),
	 * ndhsdxsy varchar(16),
	 * nhxb decimal(8, 3),
	 * nxmxb varchar(16),
	 * ntdxsy varchar(16),
	 * nyjj decimal(8, 5),
	 * nysjd decimal(4, 2),
	 * nyys varchar(16),
	 * nyyxsysy varchar(16),
	 * nzd varchar(16),
	 * nttsy varchar(16),
	 * nlzspxb varchar(16),
	 * nflzspxb varchar(16),
	 * ntjmgx varchar(16),
	 * nwflxg varchar(16),
	 * ndbdxsy varchar(16),
	 * primary key (patient_id, sqxh)
	 * ) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
	 */
	public int patientId;
	public String ztc;
	public String sqxh;
	public String bb;
	public String bbsm;
	public String zd;
	public String rq;
	public double nbxb;
	public double nbxbt;
	public double nbzcd;
	public String ndydxsy;
	public String ndhsdxsy;
	public double nhxb;
	public String nxmxb;
	public String ntdxsy;
	public double nyjj;
	public double nysjd;
	public String nyys;
	public String nyyxsysy;
	public String nzd;
	public String nttsy;
	public String nlzspxb;
	public String nflzspxb;
	public String ntjmgx;
	public String nwflxg;
	public String ndbdxsy;

	public void insert() {
		String sql = "insert into urine_info(ztc, sqxh, bb, "
				+ "bbsm, zd, rq, nbxb, nbzcd, ndydxsy, ndhsdxsy, "
				+ "nhxb, nxmxb, ntdxsy, nyjj, nysjd, nyys, nyyxsysy, "
				+ "nzd, nttsy, ndbdxsy) values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?)";
		
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
			pst.setDouble(++i, nbxb);
			pst.setDouble(++i, nbzcd);
			pst.setString(++i, ndydxsy);
			pst.setString(++i, ndhsdxsy);
			pst.setDouble(++i, nhxb);
			pst.setString(++i, nxmxb);
			pst.setString(++i, ntdxsy);
			pst.setDouble(++i, nyjj);
			pst.setDouble(++i, nysjd);
			pst.setString(++i, nyys);
			pst.setString(++i, nyyxsysy);
			pst.setString(++i, nzd);
			pst.setString(++i, nttsy);
			pst.setString(++i, ndbdxsy);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}
}
