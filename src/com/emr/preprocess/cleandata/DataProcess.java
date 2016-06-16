package com.emr.preprocess.cleandata;

import java.sql.SQLException;
import java.util.ArrayList;

import com.emr.dbutil.BioChemInfo;
import com.emr.entity.Data;
import com.emr.preprocess.cleandata.alg.EM;

public class DataProcess {
	public static void fillMissingData() {
		BioChemInfo bci = new BioChemInfo();
		try {
			bci.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String[]> result = bci.getRawData();
		int n = result.size();
		Data[] x = new Data[n];
		for (int i = 0; i < n; i++) {
			Data data = new Data();
			data.init(result.get(i));
			x[i] = data;
		}
		
		EM.train(x);
		
		for (int i = 0; i < n; i++) {
			double[] d = x[i].data;
			for (int j = 0; j < d.length; j++) {
				System.out.print(d[j] + "\t");
			}
			System.out.println();
			int[] index = x[i].findMissingIndex();
			for (int k = 0; k < index.length; k++) {
				System.out.print(index[k] + "\t");
			}
			System.out.println();
		}
	}
}
