<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!Doctype html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
<meta content=always name=referrer>
<link href="../3rd/ol3.13.1/ol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../3rd/ol3.13.1/ol.js"
	charset="utf-8"></script>
<!-- <link rel="stylesheet"
	href="https://openlayers.org/en/v3.20.1/css/ol.css" type="text/css"> -->
<style>
.map {
	height: 400px;
	width: 100%;
}
</style>
<!-- <script src="https://openlayers.org/en/v3.20.1/build/ol.js"
	type="text/javascript"></script> -->
<title>Tian OnLine Map</title>
</head>
<body>
	<h2>My Map</h2>
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
		var tian_di_tu_road_layer = new ol.layer.Tile(
				{
					title : "天地图路网",
					source : new ol.source.XYZ(
							{
								url : "http://t4.tianditu.com/DataServer?T=vec_w&x={x}&y={y}&l={z}"
							})
				});

		var tian_di_tu_annotation = new ol.layer.Tile(
				{
					title : "天地图文字标注",
					source : new ol.source.XYZ(
							{
								url : 'http://t3.tianditu.com/DataServer?T=cva_w&x={x}&y={y}&l={z}'
							})
				});
		map.addLayer(tian_di_tu_road_layer);
		map.addLayer(tian_di_tu_annotation);
	</script>
</body>
</html>