package com.emr.dbutil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BioChemInfo extends DBTable {
	
	/*
	 * drop table if exists bio_chem_info;
	 * create table bio_chem_info (
	 * patient_id int,
	 * ztc varchar(32),
	 * sqxh varchar(12),
	 * bb varchar(8),
	 * bbsm varchar(64),
	 * zd varchar(128),
	 * rq datetime,
	 * basajzym varchar(16),
	 * tdasajzym varchar(16),
	 * zdb varchar(16),
	 * xqbdb varchar(16),
	 * zdhs varchar(16),
	 * zjdhs varchar(16),
	 * jxlsm varchar(16),
	 * ns varchar(16),
	 * gaxjzym varchar(16),
	 * js varchar(16),
	 * ptt varchar(16),
	 * gysz varchar(16),
	 * xqns varchar(16),
	 * zdgc varchar(16),
	 * jsjm varchar(16),
	 * rstqm varchar(16),
	 * g varchar(16),
	 * n varchar(16),
	 * j varchar(16),
	 * lhw varchar(16),
	 * wjl varchar(16),
	 * m varchar(16),
	 * zfm varchar(16),
	 * gmdzdbdgc varchar(16),
	 * t varchar(16),
	 * bbhtjhl varchar(16),
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
	public String basajzym = "";
	public String tdasajzym = "";
	public String zdb = "";
	public String xqbdb = "";
	public String zdhs = "";
	public String zjdhs = "";
	public String jxlsm = "";
	public String ns = "";
	public String gaxjzym = "";
	public String js = "";
	public String ptt = "";
	public String gysz = "";
	public String xqns = "";
	public String zdgc = "";
	public String jsjm = "";
	public String rstqm = "";
	public String g = "";
	public String n = "";
	public String j = "";
	public String lhw = "";
	public String wjl = "";
	public String m = "";
	public String zfm = "";
	public String gmdzdbdgc = "";
	public String t = "";
	public String bbhtjhl = "";

	public void insert() {
		String sql = "insert into bio_chem_info(ztc, sqxh, bb, bbsm, "
				+ "zd, rq, basajzym, tdasajzym, zdb, xqbdb, zdhs, zjdhs, "
				+ "jxlsm, ns, gaxjzym, js, ptt, gysz, xqns, zdgc, jsjm, "
				+ "rstqm, g, n, j, lhw, wjl, m, zfm, gmdzdbdgc, t, bbhtjhl) values "
				+ "(?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?, "
				+ " ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)";
		
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
			pst.setString(++i, basajzym);
			pst.setString(++i, tdasajzym);
			pst.setString(++i, zdb);
			pst.setString(++i, xqbdb);
			pst.setString(++i, zdhs);
			pst.setString(++i, zjdhs);
			pst.setString(++i, jxlsm);
			pst.setString(++i, ns);
			pst.setString(++i, gaxjzym);
			pst.setString(++i, js);
			pst.setString(++i, ptt);
			pst.setString(++i, gysz);
			pst.setString(++i, xqns);
			pst.setString(++i, zdgc);
			pst.setString(++i, jsjm);
			pst.setString(++i, rstqm);
			pst.setString(++i, g);
			pst.setString(++i, n);
			pst.setString(++i, j);
			pst.setString(++i, lhw);
			pst.setString(++i, wjl);
			pst.setString(++i, m);
			pst.setString(++i, zfm);
			pst.setString(++i, gmdzdbdgc);
			pst.setString(++i, t);
			pst.setString(++i, bbhtjhl);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.toString());
		}
	}
	
	public ArrayList<String[]> getRawData() {
		String sql = "select * from bio_chem_info order by ztc limit 10";
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				String[] s = new String[5];
				s[0] = rs.getString("basajzym");
				s[1] = rs.getString("tdasajzym");
				s[2] = rs.getString("zdb");
				s[3] = rs.getString("xqbdb");
				s[4] = rs.getString("zdhs");
				result.add(s);
			}
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
