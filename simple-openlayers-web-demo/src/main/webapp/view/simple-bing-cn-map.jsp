<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>simple-bing-cn-map</title>
<link href="../3rd/ol3.13.1/ol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../3rd/ol3.13.1/ol.js"
	charset="utf-8"></script>
</head>
<body>
	<h2>Bing中文地图</h2>
	<div id="bingMap" style="width: 100%"></div>
	<script>
		// Bing中文地图层
		var bingMapLayer = new ol.layer.Tile(
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

		// 创建地图
		var map = new ol.Map({
			layers : [ bingMapLayer ],
			view : new ol.View({
				// 设置成都为地图中心
				center : [ 104.06, 30.67 ],
				projection : 'EPSG:4326',
				zoom : 10
			}),
			target : 'bingMap'
		});
	</script>
</body>
</html>