package com.steven.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.steven.weather.domain.Weatherinfo;
import com.steven.weather.service.WeatherDataService;

/**
 * 天气控制器
 * 
 * @author Steven
 *
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherDataService weatherDataService;

	// @GetMapping("/cityId/{cityId}")
	@RequestMapping(value = "/cityId/{cityId}", method = RequestMethod.GET)
	public Weatherinfo getReportByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}

}
