package com.ccnpmm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("username");
		}
		catch(Exception ex) {
			Constructor(request);
		}
		return "user/index";
	}

	public void Constructor(HttpServletRequest request) {
		// Khởi tạo Session
		HttpSession session = request.getSession();
		session.setAttribute("username", "");
		session.setAttribute("avatar", "");
		session.setAttribute("userId", "");
	}
}
