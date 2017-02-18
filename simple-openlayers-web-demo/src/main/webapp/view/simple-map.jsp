<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
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
<title>OpenLayers 3 simple example</title>
</head>
<body>
	<h2>My Map</h2>
	<div id="map" class="map"></div>
	<script type="text/javascript">
		var map = new ol.Map({
			target : 'map',
			layers : [ new ol.layer.Tile({
				source : new ol.source.OSM()
			}) ],
			view : new ol.View({
				center : ol.proj.fromLonLat([ 114.41, 22.28 ]),
				zoom : 4
			})
		});
	</script>
</body>
</html>