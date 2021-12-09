package com.ccnpmm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.OrderDAO;
import com.ccnpmm.dao.OrderDetailDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.entity.Order;
import com.ccnpmm.entity.OrderDetail;



@Controller
@RequestMapping("admin/order")
public class Admin_OrderDetailController {
	@Autowired OrderDAO odao;
	@Autowired OrderDetailDAO oddao;
	@Autowired ProductDAO pdao;
	@RequestMapping("/detail/{orderId}")
	public String detail(ModelMap model,@PathVariable("orderId") Integer id)
	{
		Order order=odao.getById(id);
		List<OrderDetail> orderdetails=oddao.getByOrder(id);
		for(int i=0;i<orderdetails.size();i++)
		{
			orderdetails.get(i).setTotal(orderdetails.get(i).getPrice()*orderdetails.get(i).getCount());
			orderdetails.get(i).setImage(pdao.getById(orderdetails.get(i).getProductId()).getImage());
		}
		model.addAttribute("code", order.getCode());
		String a=order.getDeliveryStatus();
		if(Integer.valueOf(order.getDeliveryStatus())==1)
		{
			model.addAttribute("status", "Chưa xác nhận");
		}
		else if(Integer.valueOf(order.getDeliveryStatus())==2)
		{
			model.addAttribute("status", "Đã xác nhận");
		}
		else if(Integer.valueOf(order.getDeliveryStatus())==3)
		{
			model.addAttribute("status", "Đang giao");
		}
		else if(Integer.valueOf(order.getDeliveryStatus())==4)
		{
			model.addAttribute("status", "Đã giao");
		}
		else {
			model.addAttribute("status", "Đã hủy");
		}
		model.addAttribute("name", order.getName());
		model.addAttribute("address", order.getAddress());
		model.addAttribute("phone", order.getPhone());
		model.addAttribute("orderdate", order.getOrderDate());
		model.addAttribute("totalprice", order.getTotal());
		model.addAttribute("orderdetails", orderdetails);
		
		return "admin/orderdetail";
	}
}
