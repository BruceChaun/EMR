package com.emr.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.emr.entity.IndexInfo;

public class EMRChain extends Configured implements Tool{
	private final String driverClass = "com.mysql.jdbc.Driver";
	
	private final String dbUrl = "jdbc:mysql://localhost:3306/emr";
	
	private final String userName = "root";
	
	private final String passwd = "123456";

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		//JobConf conf = new JobConf(EMRChain.class);
		  
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		FileSystem hdfs = FileSystem.get(conf);
		  
		// delete existing directory
		if (hdfs.exists(output)) {
			hdfs.delete(output, true);
		}
		
		Job job = new Job(conf);
		
		DBConfiguration.configureDB(conf, driverClass, dbUrl, userName, passwd);
		
		job.setJarByClass(EMRChain.class);

		FileInputFormat.addInputPath(job, input);
		//FileOutputFormat.setOutputPath(job, output);
		
		Configuration indexConfig = new Configuration(false);
		ChainMapper.addMapper(job, 
				Index.NLPMapper.class, 
				Object.class, Text.class, 
				Text.class, IntWritable.class, 
				indexConfig);
		
		job.setReducerClass(Index.IndexReducer.class);
		
		job.setOutputKeyClass(IndexInfo.class);
		job.setOutputValueClass(NullWritable.class);
		
		job.setOutputFormatClass(DBOutputFormat.class);
		String[] cols = {"keyword", "doc_name", "freq"};
		DBOutputFormat.setOutput(job, "index_table", cols);
		
		return (job.waitForCompletion(true) ? 0 : 1);
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new EMRChain(), args);
		System.exit(exitCode);
	}
}
