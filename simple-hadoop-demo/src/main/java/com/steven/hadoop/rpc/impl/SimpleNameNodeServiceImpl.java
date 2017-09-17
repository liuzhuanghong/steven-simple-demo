package com.steven.hadoop.rpc.impl;

import com.steven.hadoop.rpc.NameNodeService;

public class SimpleNameNodeServiceImpl implements NameNodeService {

	public String getMetaData(String path) {
		return "From Server: [" + path + "]";
	}

}
