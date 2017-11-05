package com.steven.simple.usermanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户测试
 * @author Steven
 *
 */
@RestController
public class VersionController {
	@RequestMapping("/version")
	public String getVersion() {
	    return "Current Version: 0.0.1";
	}
}
