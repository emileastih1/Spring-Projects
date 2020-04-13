package com.pluralsight.conferencedemo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Value("${app.version}")
	private String appVersion;

	@Value ("${spring.application.name}")
	String appName;
	
	
//	@GetMapping
//	@RequestMapping("/")
//	public Map getStatus() {
//		Map map = new HashMap<String, String>();
//		map.put("app-version", appVersion);
//		map.put("app-name", appName);
//		return map;
//	}
	
	
//	@GetMapping("/")
//	public String homePage(Model model) {
//		model.addAttribute("appName", appName);
////		model.addAttribute("appVersion", appVersion);
//		return "home";
//	}
	
}
