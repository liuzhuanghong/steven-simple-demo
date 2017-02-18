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
	<div id="navigate-container">
		<input type="button" onClick="moveToLeft();" value="左移" /> <input
			type="button" onClick="moveToRight();" value="右移" /> <input
			type="button" onClick="moveToUp();" value="上移" /> <input
			type="button" onClick="moveToDown();" value="下移" /> <input
			type="button" onClick="moveToGuangzhou();" value="移到广州" /> <input
			type="button" onClick="zoomIn();" value="放大" /> <input type="button"
			onClick="zoomOut();" value="缩小" />
	</div>
	<script>
		//全局设置
		var globalProjection = 'EPSG:3857';
		var moveOffset = 50000;
		var globalMapCenter = ol.proj.transform([ 113.4, 23.1 ], 'EPSG:4326',
				'EPSG:3857');
		//baidu 2
		// 自定义分辨率和瓦片坐标系
		var resolutions = [];
		var maxZoom = 18;
		// 计算百度使用的分辨率
		for (var i = 0; i <= maxZoom; i++) {
			resolutions[i] = Math.pow(2, maxZoom - i);
		}
		var tilegrid = new ol.tilegrid.TileGrid({
			origin : [ 0, 0 ], // 设置原点坐标
			resolutions : resolutions
		// 设置分辨率
		});
		// 创建百度地图的数据源
		var baiduSource = new ol.source.TileImage({
			projection : 'EPSG:3857',
			tileGrid : tilegrid,
			tileUrlFunction : function(tileCoord, pixelRatio, proj) {
				var z = tileCoord[0];
				var x = tileCoord[1];
				var y = tileCoord[2];
				// 百度瓦片服务url将负数使用M前缀来标识
				if (x < 0) {
					x = 'M' + (-x);
				}
				if (y < 0) {
					y = 'M' + (-y);
				}
				return "http://online0.map.bdimg.com/onlinelabel/?qt=tile&x="
						+ x + "&y=" + y + "&z=" + z
						+ "&styles=pl&udt=20160426&scaler=1&p=0";
			}
		});
		// 创建地图
		var map = new ol.Map(
				{
					controls : ol.control.defaults({
						attributionOptions : ({
							collapsible : false
						})
					}),
					logo : {
						src : "face_monkey.png",
						href : "#"
					},
					// 设置地图图层
					layers : [
					//OSM
					/*
					new ol.layer.Tile({source: new ol.source.XYZ({
					    url: 'http://{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png'
					})})
					 */
					//gaode
					/*
					new ol.layer.Tile({
					      source: new ol.source.XYZ({
					          url:'http://webst0{1-4}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}'
					      })
					  })
					 */
					//yahoo 
					/*
					new ol.layer.Tile({
					      source: new ol.source.XYZ({
					          tileSize: 512,
					          url:'https://{0-3}.base.maps.api.here.com/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/512/png8?lg=ENG&ppi=250&token=TrLJuXVK62IQk0vuXFzaig%3D%3D&requestid=yahoo.prod&app_id=eAdkWGYRoc4RfxVo0Z4B'
					      })
					  })
					 */
					//baidu 1
					/*
					new ol.layer.Tile({
					      source: new ol.source.XYZ({
					          tilePixelRatio: 2,
					          tileUrlFunction: function(tileCoord){  // 参数tileCoord为瓦片坐标
					              var z = tileCoord[0];
					              var x = tileCoord[1];
					              var y = tileCoord[2];
					              // 计算当前层级下瓦片总数的一半，用于定位整个地图的中心点
					              var halfTileNum = Math.pow(2, z-1);
					              // 原点移到中心点后，计算xy方向上新的坐标位置
					              var baiduX =  x - halfTileNum;
					              var baiduY =  y + halfTileNum;
					              // 百度瓦片服务url将负数使用M前缀来标识
					              if (baiduX < 0) {
					                  baiduX = 'M' + (-baiduX);
					              }
					              if (baiduY < 0) {
					                  baiduY = 'M' + (-baiduY);
					              }
					              // 返回经过转换后，对应于百度在线瓦片的url
					              return 'http://online2.map.bdimg.com/onlinelabel/?qt=tile&x=' + baiduX + '&y=' + baiduY + '&z=' + z + '&styles=pl&udt=20160321&scaler=2&p=0';
					          }
					      })
					  })
					 */
					//baidu 2
					//new ol.layer.Tile({source: baiduSource})
					//Bing中文地图
					/*
					new ol.layer.Tile({
					    source: new ol.source.XYZ({
					        tileUrlFunction: function(tileCoord){
					            var z = tileCoord[0];
					            var x = tileCoord[1];
					            var y = -tileCoord[2] - 1;
					            var result='', zIndex=0;
					            for(; zIndex<z; zIndex++) {
					                result = ((x&1)+2*(y&1)).toString() + result;
					                x >>= 1;
					                y >>= 1;
					            }
					            return 'http://dynamic.t0.tiles.ditu.live.com/comp/ch/' + result + '?it=G,VE,BX,L,LA&mkt=zh-cn,syr&n=z&og=111&ur=CN';
					        }
					    })
					})
					 */
					//google.cn/Maps
					new ol.layer.Tile(
							{
								source : new ol.source.XYZ(
										{
											url : 'http://www.google.cn/maps/vt/pb=!1m4!1m3!1i{z}!2i{x}!3i{y}!2m3!1e0!2sm!3i345013117!3m8!2szh-CN!3scn!5e1105!12m4!1e68!2m2!1sset!2sRoadmap!4e0'
										})
							}) ],
					// 设置显示地图的视图
					view : new ol.View({
						center : globalMapCenter,
						//extent: [112.5, 22.5, 114.1, 23.7],
						projection : globalProjection,
						//minZoom: 7,
						//maxZoom: 12,    
						zoom : 10
					}),
					// 让id为map的div作为地图的容器
					target : 'map'
				});
		function moveToLeft() {
			var view = map.getView();
			var mapCenter = view.getCenter();
			mapCenter[0] += moveOffset;
			view.setCenter(mapCenter);
			map.render();
		}
		function moveToRight() {
			var view = map.getView();
			var mapCenter = view.getCenter();
			mapCenter[0] -= moveOffset;
			view.setCenter(mapCenter);
			map.render();
		}
		function moveToUp() {
			var view = map.getView();
			var mapCenter = view.getCenter();
			mapCenter[1] -= moveOffset;
			view.setCenter(mapCenter);
			map.render();
		}
		function moveToDown() {
			var view = map.getView();
			var mapCenter = view.getCenter();
			mapCenter[1] += moveOffset;
			view.setCenter(mapCenter);
			map.render();
		}
		function moveToGuangzhou() {
			var view = map.getView();
			//view.setCenter([113.4, 23.1]);
			//map.render();
			view.fit([ 112.5, 22.5, 114.1, 23.7 ], map.getSize());
		}
		function zoomIn() {
			var view = map.getView();
			view.setZoom(view.getZoom() + 1);
			//map.render();
		}
		function zoomOut() {
			var view = map.getView();
			view.setZoom(view.getZoom() - 1);
			//map.render();
		}
	</script>
</body>

</html>