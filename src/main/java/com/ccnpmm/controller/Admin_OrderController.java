package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccnpmm.dao.OrderDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Order;
import com.ccnpmm.entity.User;

@Controller
@RequestMapping("admin/order")
public class Admin_OrderController {
	@Autowired OrderDAO odao;
	@Autowired UserDAO udao;
	@RequestMapping()
	public String index(ModelMap model)
	{
		List<Order> orderwconfirms= odao.getOrderWconfirm();
		List<Order> orderconfirms= odao.getOrderConfirm();
		List<Order> orderdelivers= odao.getOrderDeliver();
		List<Order> orderdelivereds= odao.getOrderDelivered();
		List<Order> ordercancels= odao.getOrderCancel();
		for(int i=0;i<orderwconfirms.size();i++)
		{
			User user=new User();
			user.setId(orderwconfirms.get(i).getUserId());
			user.setUsername(udao.getById(orderwconfirms.get(i).getUserId()).getUsername());
			orderwconfirms.get(i).setUser(user);
		}
		for(int i=0;i<orderconfirms.size();i++)
		{
			User user=new User();
			user.setId(orderconfirms.get(i).getUserId());
			user.setUsername(udao.getById(orderconfirms.get(i).getUserId()).getUsername());
			orderconfirms.get(i).setUser(user);
		}
		for(int i=0;i<orderdelivers.size();i++)
		{
			User user=new User();
			user.setId(orderdelivers.get(i).getUserId());
			user.setUsername(udao.getById(orderdelivers.get(i).getUserId()).getUsername());
			orderdelivers.get(i).setUser(user);
		}
		for(int i=0;i<orderdelivereds.size();i++)
		{
			User user=new User();
			user.setId(orderdelivereds.get(i).getUserId());
			user.setUsername(udao.getById(orderdelivereds.get(i).getUserId()).getUsername());
			orderdelivereds.get(i).setUser(user);
		}
		for(int i=0;i<ordercancels.size();i++)
		{
			User user=new User();
			user.setId(ordercancels.get(i).getUserId());
			user.setUsername(udao.getById(ordercancels.get(i).getUserId()).getUsername());
			ordercancels.get(i).setUser(user);
		}
		model.addAttribute("orderwconfirms", orderwconfirms);
		model.addAttribute("orderconfirms",  orderconfirms);
		model.addAttribute("orderdelivers", orderdelivers);
		model.addAttribute("orderdelivereds", orderdelivereds);
		model.addAttribute("ordercancels", ordercancels);
		
		return "admin/order";
	}
	@RequestMapping(value="/changeOrderStatus",method = RequestMethod.POST)
	public @ResponseBody String changeStatus(HttpServletRequest request) {
		String id=request.getParameter("id");
		String b=request.getParameter("state");
		int state =Integer.valueOf(b) ;
		Order order=odao.getById(id);
		if(state==2)
		{
			order.setDeliveryStatus("2");
			try {
				odao.update(order);
			}
			catch (Exception e) {
				
			}
		}
		else if(state==3)
		{
			order.setDeliveryStatus("3");
			try {
				odao.update(order);
			}
			catch (Exception e) {
				
			}
		}
		else if(state==4)
		{
			order.setDeliveryStatus("4");
			try {
				odao.update(order);
			}
			catch (Exception e) {
				
			}
		}
		else
		{
			order.setDeliveryStatus("5");
			try {
				odao.update(order);
			}
			catch (Exception e) {
				
			}
		}
		return "admin/order";
	}
}
