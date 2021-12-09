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


import com.ccnpmm.dao.MaterialDAO;

import com.ccnpmm.entity.Material;

@Controller
@RequestMapping("/admin/material")
public class Admin_MaterialController {
	@Autowired MaterialDAO mtdao;
	@RequestMapping()
	public String index(ModelMap model)
	{
		model.addAttribute("material", new Material());
		model.addAttribute("materials", mtdao.getAll());
		model.addAttribute("message", "");
		return "admin/material";
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(ModelMap model,@ModelAttribute("material")Material material)
	{
		if(material.getMaterialName()!=null)
		{
			mtdao.insert(material);
		}
		
		model.addAttribute("material", new Material());
		model.addAttribute("materials", mtdao.getAll());
		model.addAttribute("message", "");
		return "admin/material";
	}
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(ModelMap model,@PathVariable("id") Integer id)
	{
		if(id!=null) {
			Material material=mtdao.getById((id));
			model.addAttribute("materialName",material.getMaterialName());
			model.addAttribute("materialId", material.getMaterialId());
		
		}
		return "editmaterial";
	}
	@RequestMapping(value="edit/{materialId}",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("material")Material material)
	{
		if(material.getMaterialName()!="" )
		{
			
			try {
				if(mtdao.getByName(material.getMaterialName())!=null)
				{
					model.addAttribute("material", new Material());
					model.addAttribute("materials", mtdao.getAll());
						model.addAttribute("message", "Material name is already");
				}
				
				}
				catch(Exception e) {
				
					
						mtdao.update(material);
						model.addAttribute("material", new Material());
						model.addAttribute("materials", mtdao.getAll());
							model.addAttribute("message", "Update success!");
				}
		}
		else {
			model.addAttribute("material", new Material());
			model.addAttribute("materials", mtdao.getAll());
			model.addAttribute("message", "Update fail!");
		}
		
		
		return "admin/material";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(ModelMap model,HttpServletRequest request)
	{
		String a=request.getParameter("id");
		
		if(a!=null) {
			int id =Integer.valueOf(a) ;
		mtdao.delete(id);
		model.addAttribute("message", "");
		model.addAttribute("material", new Material());
		model.addAttribute("materials", mtdao.getAll());
		}
		return "admin/material";
	}
	@RequestMapping(value="/checkname")
	@ResponseBody
	public String checkname(@RequestParam String name)
	{
		
			try {
				if(mtdao.getByName(name)!=null)
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
