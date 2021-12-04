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
import org.springframework.web.servlet.view.RedirectView;

import com.ccnpmm.dao.BrandDAO;
import com.ccnpmm.dao.CartDAO;
import com.ccnpmm.dao.DetailCartDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.dao.UserDAO;
import com.ccnpmm.entity.Brand;
import com.ccnpmm.entity.Cart;
import com.ccnpmm.entity.DetailCart;
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

	@RequestMapping("/detail")
	public String detail(
			@RequestParam(value = "productId", required = true) String productId, 
			ModelMap model,
			HttpServletRequest request) {
		Product pro = productDao.getById(productId);
		model.addAttribute("product", pro);
		
		String message = (String) request.getSession().getAttribute("message");
		model.addAttribute("message", message);
		
		return "user/product-detail";
	}

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
		String query = "SELECT * FROM PRODUCT WHERE Name LIKE '%" + search + "%'";
		List<Product> productList = productDao.getBySql(query);
		List<Brand> brandList = brandDao.getAll();
		model.addAttribute("brandList", brandList);
		Filter filter = new Filter();
		model.addAttribute("filterModel", filter);
		model.addAttribute("products", productList);
		return "user/shop";
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
