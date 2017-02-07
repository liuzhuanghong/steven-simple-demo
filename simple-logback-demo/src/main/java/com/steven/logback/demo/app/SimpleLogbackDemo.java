package com.steven.logback.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogbackDemo {
	// 定义一个全局的记录器，通过LoggerFactory获取
	private final static Logger logger = LoggerFactory.getLogger(SimpleLogbackDemo.class);

	public static void main(String[] args) {
		for (int i = 0; i < 100000; i++) {
			logger.info("logback 成功了:" + i);
		}
	}

}
