package com.steven.springboot.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SimpleWebApplication {

	@RequestMapping(value = "/")
	public String index() {
		System.out.println("Spring in Action");
		return "Index of Simple Web Demo";
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebApplication.class, args);
	}

}
