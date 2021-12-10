package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccnpmm.dao.DetailCartDAO;
import com.ccnpmm.dao.OrderDAO;
import com.ccnpmm.entity.DetailCart;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderDAO odao;
	@Autowired
	DetailCartDAO odetailDao;

	@Autowired
	Common common;
	private Integer userId = 0;

	@RequestMapping()
	public String order(ModelMap model, HttpServletRequest request, @RequestParam("type") String type) {
		
		
		String deliveryStatus = "";
		if (type.equals("Waitting"))
			deliveryStatus = "1";
		else if (type.equals("Confirmed"))
			deliveryStatus = "2";
		else if (type.equals("Delivering"))
			deliveryStatus = "3";
		else if (type.equals("Received"))
			deliveryStatus = "4";
		else if (type.equals("Canceled"))
			deliveryStatus = "5";
		else
			deliveryStatus = "";

		userId = common.Login(request);
		if (userId == 0) {
			return "redirect:/login	";
		}

		if(deliveryStatus.equals(""))
		{
			model.addAttribute("orders", odao.getByUserid(userId));
		}
		else
		{
			model.addAttribute("orders", odao.getByDelivery(userId, deliveryStatus));
			model.addAttribute("type", type +" orders");
		}
		
		return "user/order";
	}

	@RequestMapping("orderDetail")
	public String order(ModelMap model, HttpServletRequest request, @RequestParam("orderId") Integer orderId) {
		
		model.addAttribute("order", odao.getById(orderId));

		List<DetailCart> orderItems = odetailDao.getDetailOrder(orderId);
		float sum = 0;
		for (DetailCart item : orderItems) {
			sum = sum + (item.getPrice() * item.getCount());
		}
		model.addAttribute("total", sum);
		model.addAttribute("listOrderItem", orderItems);

		return "user/detailorder";
	}
}