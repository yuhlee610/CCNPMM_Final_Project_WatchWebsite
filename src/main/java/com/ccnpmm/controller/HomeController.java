package com.ccnpmm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
	@RequestMapping("/")
	public String index() {
		return "account/login";
	}
	
	@RequestMapping("/admin")
	public String index1() {
		return "admin/user";
	}
}
