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
<script type="text/javascript" src="../js/online-map.js"></script>
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