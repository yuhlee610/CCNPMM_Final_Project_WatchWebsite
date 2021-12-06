package com.ccnpmm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccnpmm.dao.BrandDAO;
import com.ccnpmm.dao.CommentUserDAO;
import com.ccnpmm.dao.EnergyDAO;
import com.ccnpmm.dao.MaterialDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.entity.Brand;
import com.ccnpmm.entity.CommentUser;
import com.ccnpmm.entity.Filter;
import com.ccnpmm.entity.Product;

@Controller
public class ShopController {
	@Autowired
	private BrandDAO brandDao;
	
	@Autowired
	private EnergyDAO energyDao;
	
	@Autowired
	private MaterialDAO materialDao;

	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private CommentUserDAO commentUserDao;
	

	@RequestMapping("/detail")
	public String detail(
			@RequestParam(value = "productId", required = true) String productId, 
			ModelMap model,
			HttpServletRequest request) {
		Product pro = productDao.getById(productId);
		model.addAttribute("product", pro);
		
		String message = (String) request.getSession().getAttribute("message");
		model.addAttribute("message", message);
		
		model.addAttribute("brand", brandDao.getById(pro.getBrandId()).getBrandName());
		model.addAttribute("energy", energyDao.getById(pro.getEnergyId()).getEnergyName());
		model.addAttribute("material", materialDao.getById(pro.getMaterialId()).getMaterialName());
		
		List<CommentUser> commentUserList = commentUserDao.getCommentByProductId(productId);
		for(final CommentUser item: commentUserList) {
			List<CommentUser> replyList = commentUserDao.getReply(item.getId(), productId);
			if(replyList.size() != 0) {
				item.setReplyList(replyList);
			}
		}
		
		model.addAttribute("comments", commentUserList);
		
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
}
