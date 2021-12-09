package com.ccnpmm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.entity.Product;

@Controller

public class HomeController {
	@Autowired
	private ProductDAO productDao;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();
		try {
			session.getAttribute("userId");
		}
		catch(Exception ex) {
			Constructor(request);
		}

		List<Product> productList = productDao.getBySql("Select top 6 * from Product order by Sold DESC");
		model.addAttribute("popularProducts", productList);

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
