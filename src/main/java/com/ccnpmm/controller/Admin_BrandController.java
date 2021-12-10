package com.ccnpmm.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccnpmm.dao.BrandDAO;
import com.ccnpmm.entity.Brand;





@Controller
@RequestMapping("/admin/brand")
public class Admin_BrandController {
	@Autowired BrandDAO branddao;
	@Autowired Common common;
	private Integer userID = 0;
	
@RequestMapping()
public String index(ModelMap model, HttpServletRequest request)
{
	userID = common.AdminLogin(request);
	if(userID == 0) {
		return "redirect:/login";
	}
	
	model.addAttribute("brand", new Brand());
	model.addAttribute("brands", branddao.getAll());
	model.addAttribute("message", "");
	return "admin/brand";
}
@RequestMapping(value = "/create", method = RequestMethod.POST)
public String create(ModelMap model,@ModelAttribute("brand")Brand brand)
{
	if(brand.getBrandName()!="")
	{
		branddao.insert(brand);
	}
	model.addAttribute("message", "");
	model.addAttribute("brand", new Brand());
	model.addAttribute("brands", branddao.getAll());
	return "admin/brand";
}
@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
public String edit(ModelMap model,@PathVariable("id") Integer id)
{
	if(id!=null) {
		Brand brand=branddao.getById((id));
		model.addAttribute("brandName",brand.getBrandName());
		model.addAttribute("brandId", brand.getBrandId());
	
	}
	return "editbrand";
}
@RequestMapping(value="edit/{brandId}",method=RequestMethod.POST)
public String edit(ModelMap model,@ModelAttribute("brand")Brand brand)
{
	if(brand.getBrandName()!="")
	{
		
		try {
			if(branddao.getByName(brand.getBrandName())!=null)
			{
				model.addAttribute("brand", new Brand());
				model.addAttribute("brands", branddao.getAll());
					model.addAttribute("message", "Brand name is already");
			}
			
			}
			catch(Exception e) {
				
					branddao.update(brand);
					model.addAttribute("brand", new Brand());
					model.addAttribute("brands", branddao.getAll());
						model.addAttribute("message", "Update success!");
			}
	}
	else {
		model.addAttribute("brand", new Brand());
		model.addAttribute("brands", branddao.getAll());
		model.addAttribute("message", "Update fail!");
	}
	
	
	return "admin/brand";
}
@RequestMapping(value="/delete",method=RequestMethod.POST)
public String delete(ModelMap model,HttpServletRequest request)
{
	String a=request.getParameter("id");
	
	if(a!=null) {
		int id =Integer.valueOf(a) ;
	branddao.delete(id);
	model.addAttribute("message", "");
	model.addAttribute("brand", new Brand());
	model.addAttribute("brands", branddao.getAll());
	}
	return "admin/brand";
}
@RequestMapping(value="/checkname")
@ResponseBody
public String checkname(@RequestParam String name)
{
	
		try {
			if(branddao.getByName(name)!=null)
			{
				return "false";
			}
		}
		catch(Exception e)
		{
			return "true";
		}
	
	return "";

}
}
