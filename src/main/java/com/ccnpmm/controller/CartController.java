package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ccnpmm.dao.CartDAO;
import com.ccnpmm.dao.DetailCartDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Cart;
import com.ccnpmm.entity.DetailCart;
import com.ccnpmm.entity.Product;
import com.ccnpmm.entity.User;

@Controller
public class CartController {
	@Autowired
	private ProductDAO productDao;

	@Autowired
	private CartDAO cartDao;

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private DetailCartDAO detailCartDao;
	
	@RequestMapping("/updateCart")
	public String updateCart(
			@RequestParam(value = "productId", required = true) String productId,
			@RequestParam(value = "quantity", required = true) Integer quantity, 
			HttpServletRequest request,
			ModelMap model) {
//		User user = (User) request.getSession().getAttribute("user");
		List<DetailCart> listCart = detailCartDao.getByUserId(2);
		DetailCart item = new DetailCart();
		for(final DetailCart dc : listCart) {
			if(dc.getProductId().equals(productId)) {
				item = dc;
			}
		}
		
		if(item == null) {
			request.getSession().setAttribute("message", "ProductId khong hop le");
		}
		else {
			if(quantity > item.getAmount()) {
				request.getSession().setAttribute("message", "So luong vuot hang ton");
			}
			else {
				if(quantity == 0) {
					cartDao.delete(2, productId);
					request.getSession().setAttribute("message", "Da xoa khoi gio hang");
				}
				else {
					Cart cart = new Cart(2, productId, quantity);
					cartDao.update(cart);
					request.getSession().setAttribute("message", "Cap nhat thanh cong");
				}
			}
		}
		
		return "redirect:cartList";
	}
	
	@RequestMapping(value = "cartList", method = RequestMethod.GET)
	public String viewCart(ModelMap model, HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute("user");
		List<DetailCart> cartList = detailCartDao.getByUserId(2);
		
		Float sum = (float) 0;
		for(final DetailCart dc : cartList) {
			sum = sum + dc.getPrice() * dc.getCount();
		}
		
		model.addAttribute("total", sum);
		
		model.addAttribute("items", cartList);
		
		String message = (String) request.getSession().getAttribute("message");
		model.addAttribute("message", message);
		
		return "user/cart";
	}
	
	@RequestMapping("/addToCart")
	public String addToCart(
			@RequestParam(value = "productId", required = true) String productId,
			@RequestParam(value = "quantity", required = true) Integer quantity, 
			HttpServletRequest request,
			ModelMap model) {
//		User user = (User) request.getSession().getAttribute("user");
		User user = userDao.getById(2);

		String message = (String) request.getSession().getAttribute("message");

		// check product exist
		Product product = productDao.getById(productId);
		if (product == null) {
			message = "San pham khong ton tai";
			request.getSession().setAttribute("message", message);
			return "redirect:shop";
		}

		if (product.getAmount() < quantity) {
			message = "Khong du san pham";
			request.getSession().setAttribute("message", message);
			return "redirect:shop";
		}

		String sql = "SELECT * FROM Cart WHERE UserId=" + user.getId() + " and ProductId=" + "'" + product.getId()
				+ "'";
		List<Cart> listCart = cartDao.getBySql(sql);

		if (listCart.size() == 0) {
			Cart cart = new Cart(user.getId(), product.getId(), quantity);
			cartDao.insert(cart);
			message = "Them san pham vao gio hang thanh cong";
			request.getSession().setAttribute("message", message);
		} else {
			if (listCart.get(0).getCount() + quantity > product.getAmount()) {
				message = "Khong du san pham";
				request.getSession().setAttribute("message", message);
				return "redirect:shop";
			} else {
				Cart tc = cartDao.getByUserAndProduct(user.getId(), product.getId());
				if (product.getAmount() < quantity + tc.getCount()) {
					message = "Khong du san pham";
					request.getSession().setAttribute("message", message);
				} else {
					tc.setCount(quantity + tc.getCount());
					cartDao.update(tc);
					message = "Them san pham vao gio hang thanh cong";
					request.getSession().setAttribute("message", message);
				}
			}
		}

		return "redirect:shop";
	}

	@RequestMapping("/addItemToCart")
	public RedirectView addItemToCart(
			@RequestParam(value = "productId", required = true) String productId,
			@RequestParam(value = "quantity", required = true) Integer quantity, 
			HttpServletRequest request,
			ModelMap model) {
//		User user = (User) request.getSession().getAttribute("user");
		User user = userDao.getById(2);

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setUrl("/detail?productId=" + productId);

		String message = (String) request.getSession().getAttribute("message");

		// check product exist
		Product product = productDao.getById(productId);
		if (product == null) {
			message = "San pham khong ton tai";
			request.getSession().setAttribute("message", message);
			return rv;
		}

		if (product.getAmount() < quantity) {
			message = "Khong du san pham";
			request.getSession().setAttribute("message", message);
			return rv;
		}

		String sql = "SELECT * FROM Cart WHERE UserId=" + user.getId() + " and ProductId=" + "'" + product.getId()
				+ "'";
		List<Cart> listCart = cartDao.getBySql(sql);

		if (listCart.size() == 0) {
			Cart cart = new Cart(user.getId(), product.getId(), quantity);
			cartDao.insert(cart);
			message = "Them san pham vao gio hang thanh cong";
			request.getSession().setAttribute("message", message);
		} else {
			if (listCart.get(0).getCount() + quantity > product.getAmount()) {
				message = "Khong du san pham";
				request.getSession().setAttribute("message", message);
				return rv;
			} else {
				Cart tc = cartDao.getByUserAndProduct(user.getId(), product.getId());
				if (product.getAmount() < quantity + tc.getCount()) {
					message = "Khong du san pham";
					request.getSession().setAttribute("message", message);
				} else {
					tc.setCount(quantity + tc.getCount());
					cartDao.update(tc);
					message = "Them san pham vao gio hang thanh cong";
					request.getSession().setAttribute("message", message);
				}
			}
		}

		return rv;
	}
}
