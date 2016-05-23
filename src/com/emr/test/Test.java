package com.emr.test;

import java.sql.SQLException;

import com.emr.dbutil.IndexDB;
import com.emr.nlp.NLP;

public class Test {
	public static void test_nlp() throws Exception {
		String text = "lxw的大数据田地 -- lxw1234.com 专注Hadoop、Spark、Hive等大数据技术博客。 北京优衣库";
		NLP nlp = new NLP();
		String result = nlp.analyze(text);
		System.out.println(result);
	}
	
	public static void test_db() throws ClassNotFoundException, SQLException {
		IndexDB db = new IndexDB();
		db.connect();
		int ret = db.insert("a", "b", 1);
		
		if (ret == 0)
			System.out.println("insert OK");
		else 
			System.out.println("dup error");
	}

	public static void main(String[] args) throws Exception {
		test_nlp();
		test_db();
	}
	
}
