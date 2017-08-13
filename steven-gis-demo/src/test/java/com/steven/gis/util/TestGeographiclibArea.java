package com.steven.gis.util;

import org.junit.Test;

import com.steven.gis.util.GeographicLibUtil;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import net.sf.geographiclib.PolygonArea;
import net.sf.geographiclib.PolygonResult;

/**
 * 测试使用封装了GeographicLib库的工具类
 * 
 * @author liuzhuanghong
 *
 */
public class TestGeographiclibArea {

	/**
	 * 测试的数据
	 */
	private static double[][] coords = { { 22.656304210485004, 114.06466523520893 },
			{ 22.651868529042016, 114.06466523520893 }, { 22.651868529042016, 114.06605998390202 },
			{ 22.656304210485004, 114.06605998390202 } };

	/**
	 * 没有使用封装的工具类实现
	 */
	@Test
	public void computeWithGeographicLib() {
		System.out.println("==========================");
		System.out.println("[没有使用封装的工具类实现]");
		GeodesicData g = null;
		double distance = 0.0f;
		for (int i = 0; i < coords.length - 1; i++) {
			// System.out.println(coords[i][0] + "," + coords[i][1]);
			g = Geodesic.WGS84.Inverse(coords[i][0], coords[i][1], coords[i + 1][0], coords[i + 1][1]);
			distance = g.s12;
			System.out.println("第" + (i + 1) + "和第" + (i + 2) + "个点的距离是：" + distance + "米");
		}
		g = Geodesic.WGS84.Inverse(coords[0][0], coords[0][1], coords[3][0], coords[3][1]);
		distance = g.s12;

		PolygonArea p = new PolygonArea(Geodesic.WGS84, false);
		for (int i = 0; i < coords.length; i++) {
			p.AddPoint(coords[i][0], coords[i][1]);
		}
		PolygonResult result = p.Compute();
		System.out.println("节点数：" + result.num + "，总长度：" + result.perimeter + "米,面积：" + result.area + "平方米");
	}

	/**
	 * 使用封装的工具类计算面积
	 */
	@Test
	public void testGeographicLibUtilArea() {
		System.out.println("==========================");
		System.out.println("[使用封装的工具类计算面积]");
		double areaResult = GeographicLibUtil.computeAreaWithGeographicLib(coords);
		System.out.println("使用工具类计算的面积是：" + areaResult + "平方米");
	}

	/**
	 * 使用封装的工具类计算两个点之间的距离
	 */
	@Test
	public void testGeographicLibUtilLength() {
		System.out.println("==========================");
		System.out.println("[使用封装的工具类计算长度]");
		double distance = 0.0f;
		for (int i = 0; i < coords.length - 1; i++) {
			// System.out.println(coords[i][0] + "," + coords[i][1]);
			distance = GeographicLibUtil.computeLengthWithGeographicLib(coords[i][0], coords[i][1], coords[i + 1][0],
					coords[i + 1][1]);
			System.out.println("第" + (i + 1) + "和第" + (i + 2) + "个点的距离是：" + distance + "米");
		}
	}
}
