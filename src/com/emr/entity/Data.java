package com.emr.entity;

import java.util.ArrayList;

import com.emr.preprocess.Regex;

public class Data {
	private ArrayList<Integer> missingIndex = new ArrayList<Integer>();
	
	public double[] data;
	
	public void init(String[] data) {
		int n = data.length;
		this.data = new double[n];
		for (int i = 0; i < n; i++) {
			if (!data[i].isEmpty())
				this.data[i] = Regex.parseNumeric(data[i]);
			
			if (data[i].isEmpty()) {
				missingIndex.add(i);
			}
		}
	}
	
	public boolean isComplete() {
		return missingIndex.size() == 0;
	}
	
	public int[] findMissingIndex() {
		int n = missingIndex.size();
		int[] index = new int[n];
		for (int i = 0; i < n; i++) {
			index[i] = missingIndex.get(i);
		}
		return index;
	}
}
