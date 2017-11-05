package com.steven.weather.service;

import com.steven.weather.domain.Weatherinfo;

/**
 * 天气数据服务
 * 
 * @author Steven
 *
 */
public interface WeatherDataService {
	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	Weatherinfo getDataByCityId(String cityId);

}
