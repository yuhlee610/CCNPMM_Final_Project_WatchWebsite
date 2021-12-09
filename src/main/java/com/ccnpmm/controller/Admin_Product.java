package com.ccnpmm.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired
	ProductDAO pdao;
	@Autowired
	BrandDAO bdao;
	@Autowired
	EnergyDAO edao;
	@Autowired
	MaterialDAO mdao;
	@Autowired
	ServletContext context;

	@RequestMapping()
	public String index(ModelMap model) {
		List<Product> products = pdao.getAll();

		for (int i = 0; i < products.size(); i++) {
			Brand brand = new Brand();
			brand.setBrandId(products.get(i).getBrandId());
			brand.setBrandName(bdao.getById(products.get(i).getBrandId()).getBrandName());
			products.get(i).setBrand(brand);
			Energy energy = new Energy();
			energy.setEnergyId(products.get(i).getEnergyId());
			energy.setEnergyName(edao.getById(products.get(i).getEnergyId()).getEnergyName());
			products.get(i).setEnergy(energy);
			Material material = new Material();
			material.setMaterialId(products.get(i).getMaterialId());
			material.setMaterialName(mdao.getById(products.get(i).getMaterialId()).getMaterialName());
			products.get(i).setMaterial(material);
		}
		model.addAttribute("message", "");
		model.addAttribute("products", products);
		return "admin/product";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {
		model.addAttribute("product", new Product());
		List<Brand>brand=bdao.getAll();
		List<Energy>energy=edao.getAll();
		List<Material>material=mdao.getAll();
		LinkedHashMap<Integer, String> brands = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> energys = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> materials = new LinkedHashMap<Integer, String>();
		for(int i=0;i<brand.size();i++)
		{
			brands.put(brand.get(i).getBrandId(),brand.get(i).getBrandName() );
		}
		for(int j=0;j<energy.size();j++)
		{
			energys.put(energy.get(j).getEnergyId(),energy.get(j).getEnergyName() );
		}
		for(int k=0;k<material.size();k++)
		{
			materials.put(material.get(k).getMaterialId(),material.get(k).getMaterialName() );
		}
		
		model.addAttribute("brands",brands);
		model.addAttribute("energys", energys);
		model.addAttribute("materials", materials);
		return "admin/createproduct";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String doCreate(ModelMap model, @ModelAttribute("product") Product product,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			String imageUrl = "resources/assets/img/gallery/" + file.getOriginalFilename();
			String absolutePath = context.getRealPath(imageUrl);
			File uploadFile = new File(absolutePath);
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		product.setImage(file.getOriginalFilename());
		product.setSold(0);
		try {
			pdao.insert(product);
			model.addAttribute("message", "Insert success");
		}
		catch (Exception e) {
			model.addAttribute("message", "Insert fail");
		}
		List<Product> products = pdao.getAll();

		for (int i = 0; i < products.size(); i++) {
			Brand brand = new Brand();
			brand.setBrandId(products.get(i).getBrandId());
			brand.setBrandName(bdao.getById(products.get(i).getBrandId()).getBrandName());
			products.get(i).setBrand(brand);
			Energy energy = new Energy();
			energy.setEnergyId(products.get(i).getEnergyId());
			energy.setEnergyName(edao.getById(products.get(i).getEnergyId()).getEnergyName());
			products.get(i).setEnergy(energy);
			Material material = new Material();
			material.setMaterialId(products.get(i).getMaterialId());
			material.setMaterialName(mdao.getById(products.get(i).getMaterialId()).getMaterialName());
			products.get(i).setMaterial(material);
		}
		model.addAttribute("products", products);
		return "admin/product";
	}
	@RequestMapping(value="/checkid")
	@ResponseBody
	public String checkid(@RequestParam String id)
	{
		
			try {
				if(pdao.getById(id)!=null)
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
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(ModelMap model,@PathVariable("id") String id)
	{
		if(id!=null) {
			Product product=pdao.getById((id));
			model.addAttribute("id",product.getId());
			model.addAttribute("name", product.getName());
			model.addAttribute("price",product.getPrice());
			model.addAttribute("amount",product.getAmount());
			model.addAttribute("decription",product.getDecription());
			model.addAttribute("image",product.getImage());
			List<Brand>brand=bdao.getAll();
			List<Energy>energy=edao.getAll();
			List<Material>material=mdao.getAll();
			LinkedHashMap<Integer, String> brands = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> energys = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> materials = new LinkedHashMap<Integer, String>();
			brands.put(product.getBrandId(), bdao.getById(product.getBrandId()).getBrandName());
			energys.put(product.getEnergyId(), edao.getById(product.getEnergyId()).getEnergyName());
			materials.put(product.getMaterialId(), mdao.getById(product.getMaterialId()).getMaterialName());
			for(int i=0;i<brand.size();i++)
			{
				brands.put(brand.get(i).getBrandId(),brand.get(i).getBrandName() );
			}
			for(int j=0;j<energy.size();j++)
			{
				energys.put(energy.get(j).getEnergyId(),energy.get(j).getEnergyName() );
			}
			for(int k=0;k<material.size();k++)
			{
				materials.put(material.get(k).getMaterialId(),material.get(k).getMaterialName() );
			}
			model.addAttribute("brands",brands);
			model.addAttribute("energys", energys);
			model.addAttribute("materials", materials);
			model.addAttribute("editproduct", new Product());
		}
		return "admin/editproduct";
	}
	@RequestMapping(value="edit/{id}",method=RequestMethod.POST)
	public String edit(ModelMap model,@ModelAttribute("editproduct")Product product,@RequestParam("file") MultipartFile file)
	{
		Product p=pdao.getById(product.getId());
		if (!file.isEmpty()) {
			String imageUrl = "resources/assets/img/gallery/" + file.getOriginalFilename();
			String absolutePath = context.getRealPath(imageUrl);
			File uploadFile = new File(absolutePath);
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			product.setImage(file.getOriginalFilename());
		}
		else
		{
			product.setImage(p.getImage());
			product.setSold(p.getSold());
		}
try {
	pdao.update(product);
	model.addAttribute("message","Update Success");
}
catch(Exception e){
	model.addAttribute("message","Update Fail");
}
		
		List<Product> products = pdao.getAll();

		for (int i = 0; i < products.size(); i++) {
			Brand brand = new Brand();
			brand.setBrandId(products.get(i).getBrandId());
			brand.setBrandName(bdao.getById(products.get(i).getBrandId()).getBrandName());
			products.get(i).setBrand(brand);
			Energy energy = new Energy();
			energy.setEnergyId(products.get(i).getEnergyId());
			energy.setEnergyName(edao.getById(products.get(i).getEnergyId()).getEnergyName());
			products.get(i).setEnergy(energy);
			Material material = new Material();
			material.setMaterialId(products.get(i).getMaterialId());
			material.setMaterialName(mdao.getById(products.get(i).getMaterialId()).getMaterialName());
			products.get(i).setMaterial(material);
		}
		model.addAttribute("products", products);
		return "admin/product";
	}
	@RequestMapping(value="/delete/{id}")
	public String delete(ModelMap model,@PathVariable("id") String id)
	{
		
		if(id!=null) {
		try {
			pdao.delete(id);
			model.addAttribute("message", "Deleted");
		}
	 catch (Exception e) {
		model.addAttribute("message", "Delete fail");
	}
		
		}
		return "admin/product";
	}
}
