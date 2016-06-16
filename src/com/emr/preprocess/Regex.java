package com.emr.preprocess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static double parseNumeric(String s) {
		Pattern p = Pattern.compile("(\\d+\\.?\\d*)");
    	Matcher m = p.matcher(s);
    	String decimal = "";
    	if (m.find()) {
    		decimal = m.group(1);
    	}
    	return Double.parseDouble(decimal);
	}

}
