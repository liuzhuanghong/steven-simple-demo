package com.steven.gateway.demo.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steven.gateway.demo.app.event.RefreshRouteService;

@RestController
public class GatewayController {
	@Autowired
	RefreshRouteService refreshRouteService;

	@RequestMapping("/refreshRoute")
	public String refreshRoute() {
		refreshRouteService.refreshRoute();
		return "refreshRoute";
	}

	@Autowired
	ZuulHandlerMapping zuulHandlerMapping;

	@RequestMapping("/watchNowRoute")
	public String watchNowRoute() {
		// 可以用debug模式看里面具体是什么
		Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
		System.out.println(handlerMap);
		return "watchNowRoute";
	}
}
