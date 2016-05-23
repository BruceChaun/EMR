package com.emr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.ruta.engine.Ruta;

import com.emr.mapred.EMRChain;
import com.emr.nlp.NLP;

public class EMRLauncher {
	private String inputEncoding = "UTF-8";
	
	private String view = CAS.NAME_DEFAULT_SOFA;
	
	private boolean inputRecursive = false;
	
	private String folder = "doc";
	
	private String desc = "desc/RoomNumberAnnotatorDescriptor.xml";
	
	public final String URL_ENCODING = "UTF-8";
	
	private static void configure(AnalysisEngine ae) 
			throws ResourceConfigurationException {
	    ae.reconfigure();
	}
	
	/*
	 * get all files in dir
	 */
	private static List<File> getFiles(File dir, boolean recusive) {
	    List<File> result = new ArrayList<File>();
	    File[] listFiles = dir.listFiles();
	    if (listFiles != null) {
	    	for (File each : listFiles) {
	    		// TODO: find a solution for this hotfix
	    		if (each.isHidden()) {
	    			continue;
	    		}
	    		if (each.isFile()) {
	    			result.add(each);
	    		} else if (each.isDirectory() && recusive) {
	    			result.addAll(getFiles(each, recusive));
	    		}
	    	}
	    }
	    return result;
	}
	
	/*
	 * process each file
	 * return value:
	 * 	0	success
	 * 	-1	IOException (FileInput)
	 * 	-2	IOException (BufferedReader)
	 * 	-3	IOException (TokenStream in NLP)
	 */
	private int processFile(File file, AnalysisEngine ae, JCas aJCas) {
		FileInputStream fstream;
		String text = "";
		BufferedReader br;
		try {
			fstream = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fstream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
			return -1;
		}
		
		try {
			while ((text = br.readLine()) != null) {
				NLP nlp = new NLP();
				String analyzedText = nlp.analyze(text);
				
				if (analyzedText.equals("")) {
					return -3;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
			return -2;
		}
		
		return 0;
	}

	public void process() throws Exception {
		File descriptor = new File(desc);
		AnalysisEngine ae = Ruta.wrapAnalysisEngine(descriptor.toURI().toURL(), 
				view, true, inputEncoding);
		configure(ae);
		JCas jcas = ae.newJCas();
		
		File inputFolder = new File(folder);
		List<File> inputFiles = getFiles(inputFolder, inputRecursive);
		for (File file : inputFiles) {
			processFile(file, ae, jcas);
		}
	}
	
	public static void main(String[] args) throws Exception {
		EMRChain chain = new EMRChain();
		chain.run(args);
	}
}
