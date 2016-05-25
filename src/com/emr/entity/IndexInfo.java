package com.emr.entity;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class IndexInfo implements Writable,  DBWritable {
	private String keyword;
	
	private String doc_name;
	
	private int freq;
	
	private final String delimit = " ";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	@Override
	public void readFields(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		this.keyword = rs.getString(1);
		this.doc_name = rs.getString(2);
		this.freq = rs.getInt(3);
	}

	@Override
	public void write(PreparedStatement ps) throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(1, this.keyword);
		ps.setString(2, this.doc_name);
		ps.setInt(3, this.freq);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.keyword = Text.readString(in);
		this.doc_name = Text.readString(in);
		this.freq = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		Text.writeString(out, this.keyword);
		Text.writeString(out, this.doc_name);
		out.writeInt(this.freq);
	}

	public String toString() {
		return new String(this.keyword + this.delimit
				+ this.doc_name + this.delimit + this.freq);
	}
}
