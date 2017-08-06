package com.steven.gis.util;

import org.junit.Test;

import com.steveb.gis.util.GeoUtil;

public class TestGeoUtil {

	// 华为小西门 22.656195300366605,114.0578699112 北纬N22°39′22.30″ 东经E114°03′28.33″
	private final static double HW_F_WEST_LAT = 22.656195300366605;
	private final static double HW_F_WEST_LON = 114.0578699112;
	// 稼先路与五和大道交叉点 22.652630922007788,114.05892133713384 北纬N22°39′9.47″
	// 东经E114°03′32.12″
	private final static double JIAXIAN_WUHE_LAT = 22.652630922007788;
	private final static double JIAXIAN_WUHE_LON = 114.05892133713384;

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

	@Test
	public void getDistance() {
		// 百度地图上测距约430米
		double distance = GeoUtil.getDistance(HW_F_WEST_LAT, HW_F_WEST_LON, JIAXIAN_WUHE_LAT, JIAXIAN_WUHE_LON);
		System.out.println("华为小西门 到 稼先路与五和大道交叉点 的距离是：" + distance);

		// 百度地图上测距约4.6公里
		distance = GeoUtil.getDistance(BANTIAN_STATION_LAT, BANTIAN_STATION_LON, SZ_NORTH_STATION_LAT,
				SZ_NORTH_STATION_LON);
		System.out.println("坂田地铁站 到 深圳北站 的距离是：" + distance);

		// 百度地图上测距约32.5公里
		distance = GeoUtil.getDistance(SZ_PINSHAN_STATION_LAT, SZ_PINSHAN_STATION_LON, SZ_NORTH_STATION_LAT,
				SZ_NORTH_STATION_LON);
		System.out.println("坪山站 到 深圳北站 的距离是：" + distance);
	}

}
