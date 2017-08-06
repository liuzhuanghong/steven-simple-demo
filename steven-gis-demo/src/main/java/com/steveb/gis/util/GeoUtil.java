package com.steveb.gis.util;

/**
 * 地理位置相关工具方法
 * 
 * @author liuzhuanghong
 *
 */
public class GeoUtil {

	/**
	 * 圆周率
	 */
	// private final static double PI = Math.PI;

	/**
	 * 地球的半径
	 */
	public final static double AXIS = 6378245.0;

	/**
	 * 坐标之间的距离
	 * 
	 * @param lat1 坐标点1纬度
	 * @param lng1 坐标点1经度
	 * @param lat2 坐标点2纬度
	 * @param lng2 坐标点2经度
	 * @return 坐标之间的距离（单位米）
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		lat1 = Math.toRadians(lat1);
		lng1 = Math.toRadians(lng1);
		lat2 = Math.toRadians(lat2);
		lng2 = Math.toRadians(lng2);
		double d1 = Math.abs(lat1 - lat2);
		double d2 = Math.abs(lng1 - lng2);
		double p = Math.pow(Math.sin(d1 / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(d2 / 2), 2);
		double dis = AXIS * 2 * Math.asin(Math.sqrt(p));
		return dis;
	}
}
