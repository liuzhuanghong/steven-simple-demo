package com.steven.hadoop.rpc.app;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

import com.steven.hadoop.rpc.NameNodeService;
import com.steven.hadoop.rpc.impl.SimpleNameNodeServiceImpl;

public class SimpleNameNodeServicePulish {

	/**
	 * 发布一个简单的Hadoop RPC框架服务，可以用在一些后台的服务上，比较高效。如果是在Web，用Spring Boot那些会更好。
	 * 
	 * @param args
	 * @throws IOException
	 * @throws HadoopIllegalArgumentException
	 */
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {

		Builder builder = new RPC.Builder(new Configuration());
		builder.setBindAddress("localhost").setPort(10080).setProtocol(NameNodeService.class)
				.setInstance(new SimpleNameNodeServiceImpl());
		Server server = builder.build();
		System.out.println("server启动了.....");
		server.start();

	}

}
