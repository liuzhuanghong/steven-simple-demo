package com.steven.gis.util;

import org.junit.Test;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import net.sf.geographiclib.PolygonArea;
import net.sf.geographiclib.PolygonResult;

/**
 * 这个库还有点靠谱
 * 
 * @author liuzhuanghong
 *
 */
public class TestGeographiclib {
	// 坂田地铁站 22.628633317001707,114.07139360905309 北纬N22°37′43.08″
	// 东经E114°04′17.02″
	private final static double BANTIAN_STATION_LAT = 22.628633317001707;
	private final static double BANTIAN_STATION_LON = 114.07139360905309;
	// 深圳北站 22.61071778681016,114.0315574407639 北纬N22°36′38.58″ 东经E114°01′53.61″
	private final static double SZ_NORTH_STATION_LAT = 22.61071778681016;
	private final static double SZ_NORTH_STATION_LON = 114.0315574407639;
	// 坪山站22.710911732458946,114.32460486889501 北纬N22°42′39.28″ 东经E114°19′28.58″
	private final static double SZ_PINSHAN_STATION_LAT = 22.710911732458946;
	private final static double SZ_PINSHAN_STATION_LON = 114.32460486889501;

	// 华为小西门 22.656304210485004,114.06466523520893 北纬N22°39′22.70″
	// 东经E114°03′52.79″
	private final static double HW_F_WEST_LAT = 22.656304210485004;
	private final static double HW_F_WEST_LON = 114.06466523520893;

	// 稼先路与五和大道交叉点 22.651868529042016,114.06485835430892 北纬N22°39′6.73″
	// 东经E114°03′53.49″
	private final static double JIAXIAN_WUHE_LAT = 22.651868529042016;
	private final static double JIAXIAN_WUHE_LON = 114.06485835430892;

	// 冲之大道与稼先路交叉点22.649333789649106,114.06605998390202 北纬N22°38′57.60″
	// 东经E114°03′57.82″
	private final static double JIAXIAN_CHONGZI_LAT = 22.649333789649106;
	private final static double JIAXIAN_CHONGZI_LON = 114.06605998390202;

	// 华为东门 22.656324012323562,114.06466523520893 北纬N22°39′22.77″
	// 东经E114°03′52.79″
	// 22.65630421048499,114.06408587806172 北纬N22°39′22.70″ 东经E114°03′50.71″
	private final static double HW_EAST_LAT = 22.65630421048499;
	private final static double HW_EAST_LON = 114.06408587806172;

	@Test
	public void testDistance() {
		// 百度地图上测距约4.6公里
		GeodesicData g = Geodesic.WGS84.Inverse(BANTIAN_STATION_LAT, BANTIAN_STATION_LON, SZ_NORTH_STATION_LAT,
				SZ_NORTH_STATION_LON);
		double distance = g.s12;
		System.out.println("坂田地铁站 到 深圳北站 的距离是：" + distance + "米，而在百度地图上测距约4.6公里");

		// 百度地图上测距约32.5公里
		g = Geodesic.WGS84.Inverse(SZ_PINSHAN_STATION_LAT, SZ_PINSHAN_STATION_LON, SZ_NORTH_STATION_LAT,
				SZ_NORTH_STATION_LON);
		distance = g.s12;
		System.out.println("坪山站 到 深圳北站 的距离是：" + distance + "米，而在百度地图上测距约32.5公里");

		// 百度地图上测距约430米
		g = Geodesic.WGS84.Inverse(HW_F_WEST_LAT, HW_F_WEST_LON, JIAXIAN_WUHE_LAT, JIAXIAN_WUHE_LON);
		distance = g.s12;
		System.out.println("华为小西门 到 稼先路与五和大道交叉点 的距离是：" + distance + "米，而在百度地图上测距约430米");

		// 百度地图上测距约320米
		g = Geodesic.WGS84.Inverse(JIAXIAN_CHONGZI_LAT, JIAXIAN_CHONGZI_LON, JIAXIAN_WUHE_LAT, JIAXIAN_WUHE_LON);
		distance = g.s12;
		System.out.println("稼先路与冲之大道一小段交叉点 的距离是：" + distance + "米，而在百度地图上测距约320米");

		// 百度地图上测距约330米
		g = Geodesic.WGS84.Inverse(HW_EAST_LAT, HW_EAST_LON, HW_F_WEST_LAT, HW_F_WEST_LON);
		distance = g.s12;
		System.out.println("华为东门与西门 的距离是：" + distance + "米，而在百度地图上测距约330米");

		// 百度地图上测距约500米
		g = Geodesic.WGS84.Inverse(HW_EAST_LAT, HW_EAST_LON, JIAXIAN_CHONGZI_LAT, JIAXIAN_CHONGZI_LON);
		distance = g.s12;
		System.out.println("华为东门与稼先路 的距离是：" + distance + "米，而在百度地图上测距约500米");

	}

	// @Test
	public void testArea() {
		PolygonArea p = new PolygonArea(Geodesic.WGS84, false);
		p.AddPoint(HW_F_WEST_LAT, HW_F_WEST_LON);
		p.AddPoint(JIAXIAN_WUHE_LAT, JIAXIAN_WUHE_LON);
		p.AddPoint(JIAXIAN_CHONGZI_LAT, JIAXIAN_CHONGZI_LON);
		p.AddPoint(HW_EAST_LAT, HW_EAST_LON);
		PolygonResult result = p.Compute();
		System.out.println(result.num + " " + result.perimeter + " " + result.area);

	}
}
