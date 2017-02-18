<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!Doctype html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
<meta content=always name=referrer>
<title>OpenLayers 3地图示例</title>
<link href="../3rd/ol3.13.1/ol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../3rd/ol3.13.1/ol.js"
	charset="utf-8"></script>
</head>

<body>
	<h2>百度地图</h2>
	<div id="map" style="width: 100%"></div>
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

		var baidu_layer = new ol.layer.Tile({
			source : baidu_source
		});

		var map = new ol.Map({
			target : 'map',
			layers : [ baidu_layer ],
			view : new ol.View({
				center : [ 12959773, 4853101 ],
				zoom : 12
			})
		});
	</script>
</body>

</html>