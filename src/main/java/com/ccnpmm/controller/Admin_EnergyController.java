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


import com.ccnpmm.dao.EnergyDAO;

import com.ccnpmm.entity.Energy;

@Controller
@RequestMapping("/admin/energy")
public class Admin_EnergyController {
	@Autowired EnergyDAO edao;
	@Autowired Common common;
	private Integer userID = 0;
	@RequestMapping()
	public String index(ModelMap model, HttpServletRequest request)
	{
		userID = common.AdminLogin(request);
		if(userID == 0) {
			return "redirect:/login";
		}
		
		model.addAttribute("message", "");
		model.addAttribute("energy", new Energy());
		model.addAttribute("energys", edao.getAll());
		return "admin/energy";
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(ModelMap model,@ModelAttribute("energy")Energy energy)
	{
		if(energy.getEnergyName()!="")
		{
			edao.insert(energy);
		}
		model.addAttribute("message", "");
		model.addAttribute("energy", new Energy());
		model.addAttribute("energys", edao.getAll());
		return "admin/energy";
	}
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(ModelMap model,@PathVariable("id") Integer id)
	{
		if(id!=null) {
			Energy energy=edao.getById((id));
			model.addAttribute("energyName",energy.getEnergyName());
			model.addAttribute("energyId", energy.getEnergyId());
		
		}
		return "editenergy";
	}
	@RequestMapping(value="edit/{energyId}",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("energy")Energy energy)
	{
		if(energy.getEnergyName()!="")
		{
			
			try {
				if(edao.getByName(energy.getEnergyName())!=null)
				{
					model.addAttribute("energy", new Energy());
					model.addAttribute("energys", edao.getAll());
						model.addAttribute("message", "Energy name is already");
				}
				
				}
				catch(Exception e) {
					
						edao.update(energy);
						model.addAttribute("energy", new Energy());
						model.addAttribute("energys", edao.getAll());
							model.addAttribute("message", "Update success!");
				}
		}
		else {
			model.addAttribute("energy", new Energy());
			model.addAttribute("energys", edao.getAll());
			model.addAttribute("message", "Update fail!");
		}
		
		
		return "admin/energy";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(ModelMap model,HttpServletRequest request)
	{
		String a=request.getParameter("id");
		
		if(a!=null) {
			int id =Integer.valueOf(a) ;
		edao.delete(id);
		model.addAttribute("message", "");
		model.addAttribute("energy", new Energy());
		model.addAttribute("energys", edao.getAll());
		}
		return "admin/energy";
	}
	@RequestMapping(value="/checkname")
	@ResponseBody
	public String checkname(@RequestParam String name)
	{
		
			try {
				if(edao.getByName(name)!=null)
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
