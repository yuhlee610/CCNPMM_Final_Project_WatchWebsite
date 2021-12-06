package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccnpmm.dao.CartDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.dao.UserCartDAO;
import com.ccnpmm.entity.Cart;
import com.ccnpmm.entity.Product;
import com.ccnpmm.entity.UserCart;


@Controller
@RequestMapping("/")
public class CartController {
	@Autowired
	private Common common;

	@Autowired
	CartDAO cartDao;
	
	@Autowired ProductDAO productDao;
	
	@Autowired
	UserCartDAO userCartDao;

	private Integer userId = 0;

	@RequestMapping("cart")
	public String index(HttpServletRequest request, ModelMap model) {
		userId = common.Login(request);
		if (userId != 0) {
			float total = 0;
			List<UserCart> listCart = userCartDao.getByUser(userId);
			model.addAttribute("listCart", listCart);
			
			for(UserCart item : listCart) {
				total += item.getCount()*item.getPrice();
			}
			
			model.addAttribute("total", total);
			return "user/cartview";
		}

		return "redirect:/login";

	}

	@RequestMapping("loadCart")
	public String loadCart(int userId) {
		return "";
	}
	
	@RequestMapping(value ="cart/changeCount", method = RequestMethod.POST)
	@ResponseBody
	public String changeQuantity(String productId, int quantity) {
		try {
			if(userId == 0 || productId.equals("")) {
				return "login";
			}
			Product tempProd = productDao.getById(productId);
			if(tempProd.getId().equals(null))
			{
				return "false";
			}
			if(quantity <= tempProd.getAmount() && quantity > 0 ) {
				Cart cart = new Cart(userId, productId, quantity);
				cartDao.update(cart);
				return "true";	
			}
			else {
				return tempProd.getAmount().toString();
			}
		}
		catch (Exception e) {
		}
		return "false";
	}
	
	@RequestMapping(value ="cart/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCart(String productId) {
		if(userId != 0) {
			try {
				cartDao.delete(userId, productId);
				return "true";
			}
			catch (Exception e) {
				
			}
		}
		return "redirect:/login";
	}
}
