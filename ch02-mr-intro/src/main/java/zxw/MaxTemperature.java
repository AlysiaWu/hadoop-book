package zxw;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemperature {

	public static void main(String[] args) throws Exception{
		
		//new a job
		Job job = new Job();
		job.setJarByClass(MaxTemperature.class);
		job.setJobName("Xiuwei 1st test");
		
		//set input output
		FileInputFormat.addInputPath(job, new Path("/root/dev/workspace/hadoop_book/hadoop-book/input/ncdc/all"));
		FileOutputFormat.setOutputPath(job, new Path("/root/dev/workspace/hadoop_book/hadoop-book/input/ncdc/output_" + System.currentTimeMillis()));
		
		//set varies classes
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		job.setOutputKeyClass(Text.class);
		//job.setMapOutputKeyClass(Text.class);	//optional
		job.setOutputValueClass(IntWritable.class);
		
		boolean resp = job.waitForCompletion(true);
		System.out.println(resp);
	}

}
