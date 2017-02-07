package com.steven.logback.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志大小配置测试，分别基于1.1.7和1.1.8版本进行验证
 * @author liuzhuanghong
 *
 */
public class SimpleLogbackDemo {
	// 定义一个全局的记录器，通过LoggerFactory获取
	private final static Logger logger = LoggerFactory.getLogger(SimpleLogbackDemo.class);

	public static void main(String[] args) {
		for (int i = 0; i < 100000; i++) {
			logger.info("logback 成功了:" + i);
		}
	}

}
