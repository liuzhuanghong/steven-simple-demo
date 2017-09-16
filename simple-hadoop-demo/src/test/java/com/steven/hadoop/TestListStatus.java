package com.steven.hadoop;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class TestListStatus {

	@Test
	public void test() throws Exception {
		String uri = "hdfs://192.168.192.21:9000/";
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), config);
		// 列出hdfs上/目录下的所有文件和目录
		FileStatus[] statuses = fs.listStatus(new Path("/"));
		for (FileStatus status : statuses) {
			System.out.println(status);
		}
	}

}
