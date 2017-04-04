package com.steven.gateway.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SimpleGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleGatewayApplication.class, args);
	}

}
