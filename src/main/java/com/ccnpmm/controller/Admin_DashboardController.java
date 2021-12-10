package com.ccnpmm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccnpmm.dao.ReportDAO;
import com.ccnpmm.entity.Report;

@Controller
@RequestMapping("/admin")
public class Admin_DashboardController {
	@Autowired ReportDAO rdao;
	@Autowired Common common;
	private Integer userID = 0;
	@RequestMapping()
	public String index(ModelMap model, HttpServletRequest request)
	{
		userID = common.AdminLogin(request);
		if(userID == 0) {
			return "redirect:/login";
		}
		
		List<Report> reportmonths =new ArrayList<Report>();
		List<Report> reportyears =new ArrayList<Report>();
		LocalDate current_date = LocalDate.now();
		
		
		int totalsoldmonth=0;
		double totalmoneymonth=0;
		int totalsoldyear=0;
		double totalmoneyyear=0;
		for(int i=1;i<=12;i++)
		{
			Report reportmonth =new Report();
			reportmonth.setDate(i);
			reportmonth.setTotalSold(rdao.getSumSoldMonth(i));
			reportmonth.setTotalMoney(rdao.getSumMoneyMonth(i));
			reportmonths.add(reportmonth);
			totalsoldmonth+=reportmonth.getTotalSold();
			totalmoneymonth+=reportmonth.getTotalMoney();
		}
		
		for(int i=2021;i<=current_date.getYear();i++)
		{
			Report reportyear =new Report();
			reportyear.setDate(i);
			reportyear.setTotalSold(rdao.getSumSoldYear(i));
			reportyear.setTotalMoney(rdao.getSumMoneyYear(i));
			reportyears.add(reportyear);
			totalsoldyear+=reportyear.getTotalSold();
			totalmoneyyear+=reportyear.getTotalMoney();
		}
	
		model.addAttribute("earnings", rdao.getSumMoney());
		model.addAttribute("users", rdao.getCountUser());
		model.addAttribute("products", rdao.getCountProduct());
		model.addAttribute("sold", rdao.getSumSold());
		model.addAttribute("reportmonths", reportmonths);
		model.addAttribute("reportyears", reportyears);
		model.addAttribute("totalsoldmonth", totalsoldmonth);
		model.addAttribute("totalmoneymonth",totalmoneymonth);
		model.addAttribute("totalsoldyear", totalsoldyear);
		model.addAttribute("totalmoneyyear",totalmoneyyear);
		return "admin/index";
	}
}
