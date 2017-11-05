package com.steven.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 天气预报的App，使用的网址是http://www.weather.com.cn/data/cityinfo/101010100.html
 * 
 * @author Steven
 *
 */
@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
