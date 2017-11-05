package com.steven.weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steven.weather.domain.WeatherResponse;
import com.steven.weather.domain.Weatherinfo;
import com.steven.weather.service.WeatherDataService;

/**
 * 天气数据服务
 * 
 * @author Steven
 *
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	@Autowired
	private RestTemplate restTemplate;

	private final String WEATHER_API = "http://www.weather.com.cn/data/cityinfo";
	private final int SUCCESS_STATUS_CODE = 200;

	@Override
	public Weatherinfo getDataByCityId(String cityId) {
		String uri = WEATHER_API + "/" + cityId + ".html";
		return this.doGetWeatherData(uri);
	}

	/**
	 * 获取指定城市的天气数据,但存在乱码
	 * 
	 * @param uri
	 *            具体服务URL
	 * @return 指定城市的天气数据
	 */
	private Weatherinfo doGetWeatherData(String uri) {
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		String strBody = null;
		WeatherResponse weatherResponse = null;
		Weatherinfo weather = null;
		if (response.getStatusCodeValue() == SUCCESS_STATUS_CODE) {
			strBody = response.getBody();
			ObjectMapper mapper = new ObjectMapper();

			try {
				weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
				weather = weatherResponse.getWeatherinfo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return weather;
	}
}
