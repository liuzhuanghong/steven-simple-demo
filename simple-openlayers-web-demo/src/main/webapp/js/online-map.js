/**
 * 在线地图js库
 */

/**
 * Open Street Map 地图层
 */
var openStreetMapLayer = new ol.layer.Tile({
	source : new ol.source.OSM()
});

/**
 * Bing地图层
 */
var bingMapLayer = new ol.layer.Tile(
		{
			source : new ol.source.BingMaps(
					{
						key : 'AkjzA7OhS4MIBjutL21bkAop7dc41HSE0CNTR5c6HJy8JKc7U9U9RveWJrylD3XJ',
						imagerySet : 'Road'
					})
		});

/**
 * Bing中文地图层
 */
var bingCNMapLayer = new ol.layer.Tile({
	source : new ol.source.XYZ({
		tileUrlFunction : function(tileCoord) {
			var z = tileCoord[0];
			var x = tileCoord[1];
			var y = -tileCoord[2] - 1;
			var result = '', zIndex = 0;

			for (; zIndex < z; zIndex++) {
				result = ((x & 1) + 2 * (y & 1)).toString() + result;
				x >>= 1;
				y >>= 1;
			}
			return 'http://dynamic.t0.tiles.ditu.live.com/comp/ch/' + result
					+ '?it=G,VE,BX,L,LA&mkt=zh-cn,syr&n=z&og=111&ur=CN';
		}
	})
});

/**
 * Stamen地图层
 */
var stamenLayer = new ol.layer.Tile({
	source : new ol.source.Stamen({
		layer : 'watercolor'
	})
});

/**
 * 高德地图层
 */
var gaodeMapLayer = new ol.layer.Tile(
		{
			source : new ol.source.XYZ(
					{
						url : 'http://webst0{1-4}.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x={x}&y={y}&z={z}'
					})
		});

/**
 * google地图层
 */
var googleMapLayer = new ol.layer.Tile(
		{
			source : new ol.source.XYZ(
					{
						url : 'http://www.google.cn/maps/vt/pb=!1m4!1m3!1i{z}!2i{x}!3i{y}!2m3!1e0!2sm!3i345013117!3m8!2szh-CN!3scn!5e1105!12m4!1e68!2m2!1sset!2sRoadmap!4e0'
					})
		});
