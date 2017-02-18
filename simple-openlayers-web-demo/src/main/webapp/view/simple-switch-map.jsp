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
	<input type="radio" name="mapSource" onclick="switch2BingMap();" />Bing地图
	<input type="radio" name="mapSource" onclick="switch2StamenMap();" />Stamen地图
	<input type="radio" name="mapSource" onclick="switch2Goade();" />高德地图

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

		function switch2StamenMap() {
			// 先移除当前的地图，再添加stamen地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(stamenLayer);
		}

		function switch2Goade() {
			// 先移除当前的地图，再添加高德地图
			map.removeLayer(map.getLayers().item(0));
			map.addLayer(gaodeMapLayer);
		}
	</script>
</body>
</html>