package com.steven.hadoop.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.junit.Test;

public class TestRPCClient {

	@Test
	public void test() throws IOException {

		NameNodeService namenode = RPC.getProxy(NameNodeService.class, 1L, new InetSocketAddress("localhost", 10080),
				new Configuration());
		String metaData = namenode.getMetaData("/donload.txt");
		System.out.println(metaData);
	}

}
