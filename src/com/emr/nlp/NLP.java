package com.emr.nlp;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class NLP {
	private String delimit = " ";
	
	public String analyze(String text) {
		String result = "";
		
		Analyzer analyzer = new IKAnalyzer(true);
		StringReader reader = new StringReader(text);
		TokenStream ts = analyzer.tokenStream("", reader);  
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		
		try {
			while(ts.incrementToken()){  
			    result += term.toString() + delimit;  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
			result = "";
		}
        analyzer.close();
        reader.close();
        
		return result;
	}

}
