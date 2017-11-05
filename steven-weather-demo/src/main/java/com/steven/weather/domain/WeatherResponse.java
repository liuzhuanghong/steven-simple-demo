package com.steven.weather.domain;

import java.io.Serializable;

/**
 * 返回消息对象 http://wthrcdn.etouch.cn/weather_mini?city=深圳
 * 
 * @author Steven
 *
 */
public class WeatherResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 消息数据
	 */
	private Weatherinfo weatherinfo;

	public WeatherResponse() {
		super();
	}

	public Weatherinfo getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(Weatherinfo weatherinfo) {
		this.weatherinfo = weatherinfo;
	}

	@Override
	public String toString() {
		return "WeatherResponse [weatherinfo=" + weatherinfo + "]";
	}

}
