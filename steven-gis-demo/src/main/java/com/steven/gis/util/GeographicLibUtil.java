package com.steven.gis.util;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import net.sf.geographiclib.PolygonArea;
import net.sf.geographiclib.PolygonResult;

/**
 * 使用GeographicLib来封装通用的GIS能力
 * 
 * @author liuzhuanghong
 *
 */
public class GeographicLibUtil {

	/**
	 * 计算两个坐标点之间的距离，单位为米
	 * 
	 * @param srcLat
	 *            起始点的纬度
	 * @param srcLon
	 *            起始点的经度
	 * @param descLat
	 *            目标点的纬度
	 * @param descLon
	 *            目标点的经度
	 * @return 两个坐标点之间的距离，单位为米
	 */
	public static double computeLengthWithGeographicLib(double srcLat, double srcLon, double descLat, double descLon) {
		double result = 0.0f;
		try {
			GeodesicData g = Geodesic.WGS84.Inverse(srcLat, srcLon, descLat, descLon);
			result = g.s12;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param coords
	 *            [纬度，经度]格式的数组
	 * @return 平方米
	 */
	public static double computeAreaWithGeographicLib(double[][] coords) {
		double result = 0.0f;
		try {
			PolygonArea p = new PolygonArea(Geodesic.WGS84, false);
			int size = coords.length;
			for (int i = 0; i < size; i++) {
				p.AddPoint(coords[i][0], coords[i][1]);
			}
			PolygonResult r = p.Compute();
			result = r.area;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
