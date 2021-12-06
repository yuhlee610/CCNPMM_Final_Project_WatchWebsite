package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.UserCartDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.User;
import com.ccnpmm.entity.UserCart;

@Controller
@RequestMapping("/checkout/")
public class CheckoutController {

	@Autowired
	private Common common;
	@Autowired
	UserCartDAO userCartDao;
	@Autowired
	UserDAO userDAO;
	private Integer userId = 0;

	@RequestMapping("")
	public String index(HttpServletRequest request, ModelMap model) {
		try {
			userId = common.Login(request);
			if (userId != 0) {

				// Get info customer
				User user = userDAO.getById(userId);
				model.addAttribute("user", user);

				float total = 0;
				List<UserCart> listCart = userCartDao.getByUser(userId);
				model.addAttribute("listCart", listCart);

				for (UserCart item : listCart) {
					total += item.getCount() * item.getPrice();
				}

				model.addAttribute("total", total);
				return "user/checkout";
			}
		} catch (Exception e) {

		}

		return "redirect:/login";
	}

}
