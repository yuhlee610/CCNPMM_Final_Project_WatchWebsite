package com.ccnpmm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.OrderDAO;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired OrderDAO odao;
@RequestMapping()
public String order(ModelMap model)
{
	model.addAttribute("orders",odao.getByUserid(1));
	
	return "user/order";
}
}
