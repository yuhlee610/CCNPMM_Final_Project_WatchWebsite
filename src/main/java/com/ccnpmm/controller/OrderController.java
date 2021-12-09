package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.OrderDAO;
import com.ccnpmm.entity.Order;

@Controller
@RequestMapping("/order/")
public class OrderController {
	
	
	@Autowired OrderDAO orderDao;
	@Autowired Common common;
	private Integer userId = 0;
	
	@RequestMapping("myOrder")
	public String getListOrder(HttpServletRequest request, ModelMap model) {
		
		try {
			userId = common.Login(request);
			if(userId != 0) {
				List<Order> order = orderDao.getByUserId(userId);
				model.addAttribute("orders",order);
				return "user/order";
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/login";
		
		
	}

}
