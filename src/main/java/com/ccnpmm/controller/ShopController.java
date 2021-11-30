package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccnpmm.dao.BrandDAO;
import com.ccnpmm.dao.CartDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Brand;
import com.ccnpmm.entity.Cart;
import com.ccnpmm.entity.Filter;
import com.ccnpmm.entity.Product;
import com.ccnpmm.entity.User;

@Controller
public class ShopController {
	@Autowired
	private BrandDAO brandDao;

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private CartDAO cartDao;

	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "shop", method = RequestMethod.GET)
	public String filter(Filter filterModel, ModelMap model, HttpServletRequest request) {
		String filterBrand = "";
		if (ArrayUtils.isNotEmpty(filterModel.getBrands())) {
			for (int i = 0; i < filterModel.getBrands().length; i++) {
				String conjoin = " OR ";
				if (i == filterModel.getBrands().length - 1) {
					conjoin = " AND ";
				}
				filterBrand = filterBrand + "BrandId=" + filterModel.getBrands()[i] + conjoin;
			}
		}

		String filterPrice = "";
		if (filterModel.getPrice() != null) {
			if (filterModel.getPrice().equals("<500")) {
				filterPrice = "Price < 500 ";
			} else if (filterModel.getPrice().equals("500-1000")) {
				filterPrice = "Price < 1000 AND Price >= 500 ";
			} else if (filterModel.getPrice().equals(">1000")) {
				filterPrice = "Price > 1000 ";
			}
		}

		String filterSort = "";
		if (filterModel.getSortBy() != null) {
			if (filterModel.getSortBy().equals("Price high to low")) {
				filterSort = "ORDER BY Price DESC";
			} else if (filterModel.getSortBy().equals("Price low to high")) {
				filterSort = "ORDER BY Price";
			} else if (filterModel.getSortBy().equals("Popular")) {
				filterSort = "ORDER BY Sold DESC";
			}
		}

		Integer itemsPerPage = 1;
		if (filterModel.getViewMore() != null) {
			itemsPerPage = filterModel.getViewMore();
		}

		String query = "SELECT TOP " + itemsPerPage + " * FROM PRODUCT WHERE " + filterBrand + filterPrice + filterSort;
		if (filterBrand.isEmpty() && filterPrice.isEmpty() && filterSort.isEmpty()) {
			query = "SELECT TOP " + itemsPerPage + " * FROM PRODUCT";
		}

		System.out.println(query);
		List<Product> productList = productDao.getBySql(query);

		List<Brand> brandList = brandDao.getAll();
		model.addAttribute("brandList", brandList);
		Filter filter = new Filter();
		model.addAttribute("filterModel", filter);
		model.addAttribute("products", productList);

		String message = (String) request.getSession().getAttribute("message");
		model.addAttribute("message", message);

		return "user/shop";
	}
	
	@RequestMapping("/search")
	public String searchProduct(ModelMap model, @RequestParam(value = "search", required = true) String search) {
		String query = "SELECT * FROM PRODUCT WHERE Name LIKE '%" + search + "%'" ;
		List<Product> productList = productDao.getBySql(query);
		List<Brand> brandList = brandDao.getAll();
		model.addAttribute("brandList", brandList);
		Filter filter = new Filter();
		model.addAttribute("filterModel", filter);
		model.addAttribute("products", productList);
		return "user/shop";
	}

	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam(value = "productId", required = true) String productId,
			@RequestParam(value = "quantity", required = true) Integer quantity, HttpServletRequest request,
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
		
		
		if(listCart.size() == 0) {
//			Cart cart = new Cart(user, product, quantity);
//			cartDao.insert(cart);
			message = "Them san pham vao gio hang thanh cong";
			request.getSession().setAttribute("message", message);
		}
		else {
			if (listCart.get(0).getCount() + quantity > product.getAmount()) {
				message = "Khong du san pham";
				request.getSession().setAttribute("message", message);
				return "redirect:shop";
			} else {
//				listCart.get(0).setCount(listCart.get(0).getCount() + quantity);
//				System.out.println(listCart.get(0).getCount());
//				System.out.println(listCart.get(0).getUser().getId());
//				System.out.println(listCart.get(0).getProduct().getId());
//				cartDao.update(listCart.get(0));
//				message = "Them san pham vao gio hang thanh cong";
//				request.getSession().setAttribute("message", message);
				 
				Cart tc = cartDao.getByUser(user.getId());
//				System.out.println(tc.getUser().getId());
			}
		}
		
		return "redirect:shop";
	}
}
