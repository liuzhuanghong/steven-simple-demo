package com.steven.weather.domain;

import java.io.Serializable;

/**
 * 天气实体类，类的对象对应网上一个免费天气预报网站的json结果<br>
 * http://www.weather.com.cn/data/cityinfo/101010100.html { "weatherinfo": {
 * "city": "北京", "cityid": "101010100", "temp1": "-2℃", "temp2": "16℃",
 * "weather": "晴", "img1": "n0.gif", "img2": "d0.gif", "ptime": "18:00" } }
 * 
 * @author Steven
 *
 */
public class Weatherinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Weatherinfo() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String getTemp2() {
		return temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	private String city;
	private String cityid;
	private String temp1;
	private String temp2;
	private String weather;
	private String img1;
	private String img2;
	private String ptime;

	@Override
	public String toString() {
		return "Weather [city=" + city + ", cityid=" + cityid + ", temp1=" + temp1 + ", temp2=" + temp2 + ", weather="
				+ weather + ", img1=" + img1 + ", img2=" + img2 + ", ptime=" + ptime + "]";
	}

}
