<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>simple-switch-map</title>

<link href="../3rd/ol3.13.1/ol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../3rd/ol3.13.1/ol.js"
	charset="utf-8"></script>
<style>
.map {
	height: 400px;
	width: 100%;
}
</style>
</head>
<body>
	<h2>动态切换地图</h2>
	<div id="map" class="map"></div>
	<input type="radio" checked="checked" name="mapSource"
		onclick="switch2OSM();" />OpenStreetMap地图
	<input type="radio" name="mapSource" onclick="switch2BingMap();" />Bing英文地图
	<input type="radio" name="mapSource" onclick="switch2BingCNMap();" />Bing中文地图
	<input type="radio" name="mapSource" onclick="switch2StamenMap();" />Stamen地图
	<input type="radio" name="mapSource" onclick="switch2GoadeMap();" />高德地图
	<input type="radio" name="mapSource" onclick="switch2GoogleMap();" />谷歌地图
	<input type="radio" name="mapSource" onclick="switch2BaiduMap();" />百度地图
	<script>
		// Open Street Map 地图层
		var openStreetMapLayer = new ol.layer.Tile({
			source : new ol.source.OSM()
		});

		// Bing地图层
		var bingMapLayer = new ol.layer.Tile(
				{
					source : new ol.source.BingMaps(
							{
								key : 'AkjzA7OhS4MIBjutL21bkAop7dc41HSE0CNTR5c6HJy8JKc7U9U9RveWJrylD3XJ',
								imagerySet : 'Road'
							})
				});
		// Bing中文地图层
		var bingCNMapLayer = new ol.layer.Tile(
				{
					source : new ol.source.XYZ(
							{
								tileUrlFunction : function(tileCoord) {
									var z = tileCoord[0];
									var x = tileCoord[1];
									var y = -tileCoord[2] - 1;
									var result = '', zIndex = 0;

									for (; zIndex < z; zIndex++) {
										result = ((x & 1) + 2 * (y & 1))
												.toString()
												+ result;
										x >>= 1;
										y >>= 1;
									}
									return 'http://dynamic.t0.tiles.ditu.live.com/comp/ch/'
											+ result
											+ '?it=G,VE,BX,L,LA&mkt=zh-cn,syr&n=z&og=111&ur=CN';
								}
							})
				});
		// Stamen地图层
		var stamenLayer = new ol.layer.Tile({
			source : new ol.source.Stamen({
				layer : 'watercolor'
			})
		});

		// 高德地图层
		var gaodeMapLayer = new ol.layer.Tile(
				{
					source : new ol.source.XYZ(
							{
								url : 'http://webst0{1-4}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}'
							})
				});

		// google地图层
		var googleMapLayer = new ol.layer.Tile(
				{
					source : new ol.source.XYZ(
							{
								url : 'http://www.google.cn/maps/vt/pb=!1m4!1m3!1i{z}!2i{x}!3i{y}!2m3!1e0!2sm!3i345013117!3m8!2szh-CN!3scn!5e1105!12m4!1e68!2m2!1sset!2sRoadmap!4e0'
							})
				});

		var projection = ol.proj.get("EPSG:3857");
		var resolutions = [];
		for (var i = 0; i < 19; i++) {
			resolutions[i] = Math.pow(2, 18 - i);
		}
		var tilegrid = new ol.tilegrid.TileGrid({
			origin : [ 0, 0 ],
			resolutions : resolutions
		});

		var baidu_source = new ol.source.TileImage({
			projection : projection,
			tileGrid : tilegrid,
			tileUrlFunction : function(tileCoord, pixelRatio, proj) {
				if (!tileCoord) {
					return "";
				}
				var z = tileCoord[0];
				var x = tileCoord[1];
				var y = tileCoord[2];

				if (x < 0) {
					x = "M" + (-x);
				}
				if (y < 0) {
					y = "M" + (-y);
				}

				return "http://online3.map.bdimg.com/onlinelabel/?qt=tile&x="
						+ x + "&y=" + y + "&z=" + z
						+ "&styles=pl&udt=20151021&scaler=1&p=1";
			}
		});

		var baiduMapLayer = new ol.layer.Tile({
			source : baidu_source
		});

		// 创建地图
		var map = new ol.Map({
			layers : [ openStreetMapLayer ],
			view : new ol.View({
				// 设置成都为地图中心
				center : [ 104.06, 30.67 ],
				projection : 'EPSG:4326',
				zoom : 10
			}),
			target : 'map'
		});

		function switch2OSM() {
			// 先移除当前的地图，再添加Open Street Map 地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(openStreetMapLayer);
		}

		function switch2BingMap() {
			// 先移除当前的地图，再添加Bing地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(bingMapLayer);
		}
		function switch2BingCNMap() {
			// 先移除当前的地图，再添加Bing中文地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(bingCNMapLayer);
		}
		function switch2StamenMap() {
			// 先移除当前的地图，再添加stamen地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(stamenLayer);
		}

		function switch2GoadeMap() {
			// 先移除当前的地图，再添加高德地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(gaodeMapLayer);
		}

		function switch2GoogleMap() {
			// 先移除当前的地图，再添加谷歌地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(googleMapLayer);
		}

		function switch2BaiduMap() {
			// 先移除当前的地图，再添加百度地图
			map.removeLayer(map.getLayers().item(0));
			//map.removeLayer(map.getLayers().item(0));
			map.addLayer(baiduMapLayer);
		}
	</script>
</body>
</html>