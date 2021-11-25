package com.ccnpmm.controller;

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
	public String index(ModelMap model) {
		List<Product> productList = productDao.getBySql("Select top 6 * from Product order by Sold DESC");
		model.addAttribute("popularProducts", productList);
		return "user/index";
	}
}
