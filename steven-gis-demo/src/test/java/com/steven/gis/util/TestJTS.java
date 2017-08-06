package com.steven.gis.util;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;

public class TestJTS {
	// 深圳北站 22.61071778681016,114.0315574407639 北纬N22°36′38.58″ 东经E114°01′53.61″
	private final static double SZ_NORTH_STATION_LAT = 22.61071778681016;
	private final static double SZ_NORTH_STATION_LON = 114.0315574407639;

	// 坂田地铁站 22.628633317001707,114.07139360905309 北纬N22°37′43.08″
	// 东经E114°04′17.02″
	private final static double BANTIAN_STATION_LAT = 22.628633317001707;
	private final static double BANTIAN_STATION_LON = 114.07139360905309;

	/**
	 * 平面跟球体是不一样的。。。
	 */
	@Test
	public void test() {

		Coordinate coordinatesSZNorth = new Coordinate(SZ_NORTH_STATION_LAT, SZ_NORTH_STATION_LON);
		Coordinate coordinatesBanTian = new Coordinate(BANTIAN_STATION_LAT, BANTIAN_STATION_LON);
		double distance = coordinatesSZNorth.distance(coordinatesBanTian);
		distance = distance * 1000;
		System.out.println("坂田地铁站 到 深圳北站 的距离是：" + distance);
	}

}
