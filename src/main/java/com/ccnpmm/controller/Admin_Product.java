package com.ccnpmm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.BrandDAO;
import com.ccnpmm.dao.EnergyDAO;
import com.ccnpmm.dao.MaterialDAO;
import com.ccnpmm.dao.ProductDAO;
import com.ccnpmm.entity.Brand;
import com.ccnpmm.entity.Energy;
import com.ccnpmm.entity.Material;
import com.ccnpmm.entity.Product;



@Controller
@RequestMapping("/admin/product")
public class Admin_Product {
	@Autowired ProductDAO pdao;
	@Autowired BrandDAO bdao;
	@Autowired EnergyDAO edao;
	@Autowired MaterialDAO mdao;
	@RequestMapping()
	public String index(ModelMap model)
	{
		List<Product> product=pdao.getAll();
		
		for(int i=0;i<product.size();i++)
		{
			Brand brand=new Brand();
			brand.setBrandId(product.get(i).getBrandId());
			brand.setBrandName(bdao.getById(product.get(i).getBrandId()).getBrandName());
			product.get(i).setBrand(brand);
			Energy energy=new Energy();
			energy.setEnergyId(product.get(i).getEnergyId());
			energy.setEnergyName(edao.getById(product.get(i).getEnergyId()).getEnergyName());
			product.get(i).setEnergy(energy);
			Material material=new Material();
			material.setMaterialId(product.get(i).getMaterialId());
			material.setMaterialName(mdao.getById(product.get(i).getMaterialId()).getMaterialName());
			product.get(i).setMaterial(material);
		}
		model.addAttribute("products", product);
		return "admin/product";
	}
}
