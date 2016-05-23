package com.emr.mapred;

import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

public class EMRChain extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		  
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		System.out.println(input.toString());
		FileSystem hdfs = FileSystem.get(conf);
		  
		// delete existing directory
		if (hdfs.exists(output)) {
			hdfs.delete(output, true);
		}
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(EMRChain.class);
		
		Configuration indexConfig = new Configuration(false);
		ChainMapper.addMapper(job, 
				Index.NLPMapper.class, 
				Text.class, Text.class, 
				Text.class, Text.class, 
				indexConfig);
		
		job.setReducerClass(Index.IndexReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		return (job.waitForCompletion(true) ? 0 : 1);
	}

}
