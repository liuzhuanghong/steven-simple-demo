package com.steven.mapreduce.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.steven.mapreduce.map.WordCounterMapper;
import com.steven.mapreduce.reduce.WordCounterReducer;

/**
 * 相当于一个yarn集群的客户端 需要在此封装我们的MapReduce程序的相关运行参数，指定jar包 最后提交给yarn
 * 
 * @author pc
 *
 */
public class WordCounterApp {

	/**
	 * 打包后执行的方法：<br>
	 * hadoop jar WordCounterApp.jar com.steven.mapreduce.app.WordCounterApp
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		if (args == null || args.length == 0) {
			args = new String[2];
			args[0] = "hdfs://192.168.192.21:9000/readme.txt";
			args[1] = "hdfs://192.168.192.21:9000/output";
		}
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		// 指定本程序的jar包所在的本地路径
		job.setJarByClass(WordCounterApp.class);
		// 指定本业务job要使用的mapper/Reducer业务类
		job.setMapperClass(WordCounterMapper.class);
		job.setReducerClass(WordCounterReducer.class);

		// 指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		// 指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// 指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		// 指定job的输出结果所在目录
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// 将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
		/* job.submit(); */
		boolean res = job.waitForCompletion(true);
		System.exit(res ? 0 : 1);
	}

}
