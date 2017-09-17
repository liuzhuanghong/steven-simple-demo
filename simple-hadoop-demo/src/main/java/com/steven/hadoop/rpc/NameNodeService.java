package com.steven.hadoop.rpc;

/**
 * 通过Hadoop的库实现RPC框架
 * 
 * @author pc
 *
 */
public interface NameNodeService {

	public static final long versionID = 100L;

	public String getMetaData(String path);
}
