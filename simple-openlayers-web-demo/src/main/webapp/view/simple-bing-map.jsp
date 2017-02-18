<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<link href="../3rd/ol3.13.1/ol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../3rd/ol3.13.1/ol.js"
	charset="utf-8"></script>
<style>
.map {
	height: 400px;
	width: 100%;
}
</style>
<title>simple bing map</title>
</head>
<body>
	<h2>加载bing地图</h2>
	<div id="map" class="map"></div>
	<script type="text/javascript">
		var map = new ol.Map({
			target : "map",
			layer : [],
			view : new ol.View({
				center : ol.proj.fromLonLat([ 114.41, 22.28 ]),
				zoom : 6
			})
		});
		var key = "AuUKioHoVzV-16Ep0yv6ay21ixWZ5OZ7jDs-k7g03fiUMbN6GSH97IpRcQ_s_s3-";
		var BingMapRoad = new ol.layer.Tile({
			source : new ol.source.BingMaps({
				key : key,
				imagerySet : 'Road'
			}),
			name : 'BingMap的道路图层'
		});
		var BingMapAerial = new ol.layer.Tile({
			source : new ol.source.BingMaps({
				key : key,
				imagerySet : 'Aerial'
			}),
			name : 'BingMap的影像图层'
		});
		map.addLayer(BingMapAerial);
		map.addLayer(BingMapRoad);
	</script>
</body>
</html>