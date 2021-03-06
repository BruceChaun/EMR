package com.emr.mapred;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import com.emr.entity.IndexInfo;
import com.emr.nlp.NLP;

/*
 * map/reduce process of index table
 */
public class Index {
	private final static String separator = "/u0001/";
	
	public static class NLPMapper 
	extends Mapper<Object, Text, Text, IntWritable> {
		private final IntWritable one = new IntWritable(1);
		
		private Text word = new Text();
		
		/*
		 * output
		 * key is "file_name + word" 
		 * value is one
		 */
		public void map(Object key, Text value, Context context) 
				throws IOException, InterruptedException {
			FileSplit fs = (FileSplit) context.getInputSplit();
			String filename = fs.getPath().getName();
			
			NLP nlp = new NLP();
			String line = nlp.analyze(value.toString());
			StringTokenizer itr = new StringTokenizer(line);
		      
			while (itr.hasMoreTokens()) {
				String outputKey = filename + separator + itr.nextToken();
				word.set(outputKey);
		        context.write(word, one);
		      }
		}
	}
	
	public static class IndexReducer
	extends Reducer<Text, IntWritable, IndexInfo, NullWritable> {
		private IntWritable result = new IntWritable();
		private IndexInfo info = new IndexInfo();
		
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
				throws IOException, InterruptedException { 
			int sum = 0;
		    for (IntWritable val : values) {
		      sum += val.get();
		    }
		    result.set(sum);
		    
		    String[] splited = key.toString().split(separator);
			info.setDoc_name(splited[0]);
			info.setKeyword(splited[1]);
			info.setFreq(sum);
			context.write(info, NullWritable.get());
		    
		    /*
		     * write to index table
		     */
//		    try {
//				db.connect();
//				String[] splited = key.toString().split(separator);
//				String doc_name = splited[0];
//				String keyword = splited[1];
//				db.insert(keyword, doc_name, sum);
//			} catch (ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//				System.out.println(e.toString());
//			}
		}
	}
}
