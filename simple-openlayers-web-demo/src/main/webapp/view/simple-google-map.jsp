<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>simple-google-map</title>
<link href="../3rd/ol3.13.1/ol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../3rd/ol3.13.1/ol.js"
	charset="utf-8"></script>
</head>
<body>
	<h2>Google地图</h2>
	<div id="googleMap" style="width: 100%"></div>
	<script>
		// google地图层
		var googleMapLayer = new ol.layer.Tile(
				{
					source : new ol.source.XYZ(
							{
								url : 'http://www.google.cn/maps/vt/pb=!1m4!1m3!1i{z}!2i{x}!3i{y}!2m3!1e0!2sm!3i345013117!3m8!2szh-CN!3scn!5e1105!12m4!1e68!2m2!1sset!2sRoadmap!4e0'
							})
				});

		// 创建地图
		var map = new ol.Map({
			layers : [ googleMapLayer ],
			view : new ol.View({
				// 设置成都为地图中心
				center : [ 104.06, 30.67 ],
				projection : 'EPSG:4326',
				zoom : 10
			}),
			target : 'googleMap'
		});
	</script>
</body>
</html>