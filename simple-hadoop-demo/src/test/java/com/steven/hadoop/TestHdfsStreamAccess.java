package com.steven.hadoop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * 学习用流式的方式操作HDFS
 * 
 * @author pc
 *
 */
public class TestHdfsStreamAccess {
	FileSystem fs = null;
	Configuration conf = null;

	@Before
	public void init() throws Exception {
		String uri = "hdfs://192.168.192.21:9000/";
		String username = "root";
		conf = new Configuration();
		// 拿到一个文件系统操作的客户端实例对象
		// fs = FileSystem.get(conf);
		// 可以直接传入 uri和用户身份
		fs = FileSystem.get(new URI(uri), conf, username);
	}

	/**
	 * 通过流的方式上传文件到hdfs
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStreamAccessUpload() throws Exception {
		// 上传到HDFS后在HDFS保存的路径
		FSDataOutputStream outputStream = fs.create(new Path("/upload.txt"), true);
		FileInputStream inputStream = new FileInputStream("D:/downloads/tips.txt");
		IOUtils.copy(inputStream, outputStream);
	}

	/**
	 * 通过流的方式从HDFS下载文件
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStreamAccessDownload() throws Exception {
		FSDataInputStream inputStream = fs.open(new Path("/upload.txt"));
		FileOutputStream outputStream = new FileOutputStream("D:/downloads/download.txt");
		IOUtils.copy(inputStream, outputStream);
	}

	/**
	 * 显示HDFS服务器上的文件内容
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCat() throws IllegalArgumentException, IOException {
		FSDataInputStream in = fs.open(new Path("/upload.txt"));
		StringWriter writer = new StringWriter();
		// 解决了中文乱码的问题。。。
		IOUtils.copy(in, writer, "GBK");
		String str = writer.toString();
		System.out.println(str);
	}
}
