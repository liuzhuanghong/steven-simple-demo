/**
 * 高德地图
 */

OpenLayers.Layer.Gaode = OpenLayers
		.Class(OpenLayers.Layer.TileCache,
				{
					initialize : function(name, url, options) {
						var tempoptions = OpenLayers.Util.extend({
							'format' : 'image/png',
							isBaseLayer : true
						}, options);
						OpenLayers.Layer.TileCache.prototype.initialize.apply(
								this, [ name, url, {}, tempoptions ]);
						this.extension = this.format.split('/')[1]
								.toLowerCase();
						this.extension = (this.extension == 'jpg') ? 'jpeg'
								: this.extension;
						this.transitionEffect = "resize";
						this.buffer = 0;
					},
					getURL : function(bounds) {
						var retUrl = this.url
								+ "lang=zh_cn&size=1&scale=1&style=8&";
						var res = this.map.getResolution();
						var bbox = this.map.getMaxExtent();
						var size = this.tileSize;
						// 计算列号
						var tileX = Math.round((bounds.left - bbox.left)
								/ (res * size.w));
						// 计算行号
						var tileY = Math.round((bbox.top - bounds.top)
								/ (res * size.h));
						// 当前的等级
						debugger;
						var tileZ = this.map.zoom - 1;
						if (tileX < 0)
							tileX = tileX
									+ Math.round(bbox.getWidth()
											/ bounds.getWidth());
						if (tileY < 0)
							tileY = tileY
									+ Math.round(bbox.getHeight()
											/ bounds.getHeight());
						// tileY = (Math.pow(2, tileZ) - 1 - tileY);
						// tileY = Math.pow(2,
						// tileZ)-tileY+Math.pow(2,this.map.zoom-3);
						tileY = 1 + tileY + Math.pow(2, this.map.zoom - 3);
						console.log(tileY + " =tileY");
						return retUrl + "x=" + tileX + "&y=" + tileY + "&z="
								+ this.map.zoom;
					},

					clone : function(obj) {
						if (obj == null) {
							obj = new OpenLayers.Layer.Gaode(this.name,
									this.url, this.options);
						}
						obj = OpenLayers.Layer.TileCache.prototype.clone.apply(
								this, [ obj ]);
						return obj;
					},
					CLASS_NAME : "OpenLayers.Layer.Gaode"
				});

// 高德地图
var vecLayer = new OpenLayers.Layer.XYZ(
		"高德地图",
		[
				"http://webrd01.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}",
				"http://webrd02.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}",
				"http://webrd03.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}",
				"http://webrd04.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=7&x=${x}&y=${y}&z=${z}"
		// "data/roadmap/${z}/${x}/${y}.png"
		], {
			isBaseLayer : true,
			visibility : true,
			displayInLayerSwitcher : true
		});
// map.addLayer(vecLayer);
