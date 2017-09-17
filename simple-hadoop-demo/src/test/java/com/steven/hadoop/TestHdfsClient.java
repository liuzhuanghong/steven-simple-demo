package com.steven.hadoop;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * 学习HDFS的API相关操作
 * @author pc
 *
 */
public class TestHdfsClient {

	FileSystem fs = null;

	@Before
	public void init() throws Exception {
		String uri = "hdfs://192.168.192.21:9000/";
		String username = "root";
		// 构造一个配置参数对象，设置一个参数：我们要访问的hdfs的URI
		// 从而FileSystem.get()方法就知道应该是去构造一个访问hdfs文件系统的客户端，以及hdfs的访问地址
		// new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
		// 然后再加载classpath下的hdfs-site.xml
		Configuration conf = new Configuration();
		// conf.set("fs.defaultFS", uri);
		/**
		 * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
		 */
		// conf.set("dfs.replication", "3");

		// 获取一个hdfs的访问客户端，根据参数，这个实例应该是DistributedFileSystem的实例
		// fs = FileSystem.get(conf);

		// 如果这样去获取，那conf里面就可以不要配"fs.defaultFS"参数，而且，这个客户端的身份标识已经是hadoop用户
		fs = FileSystem.get(new URI(uri), conf, username);

	}

	/**
	 * 学习往HDFS上传文件
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFileToHdfs() throws Exception {
		// 要上传的文件所在的本地路径
		Path src = new Path("D:/downloads/tips.txt");
		// 要上传到hdfs的目标路径
		Path dst = new Path("/");
		fs.copyFromLocalFile(src, dst);
		fs.close();
	}

	/**
	 * 从hdfs中复制文件到本地文件系统
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testDownloadFileToLocal() throws Exception {
		// 直接使用fs.copyToLocalFile(new Path("/tips.txt"), new Path("D:/downloads/tips.txt"))，会出现java.io.IOException: (null) entry in command string: null chmod 0644的异常 
		fs.copyToLocalFile(false, new Path("/tips.txt"), new Path("D:/downloads/tips.txt"), true);
		fs.close();

	}
}
