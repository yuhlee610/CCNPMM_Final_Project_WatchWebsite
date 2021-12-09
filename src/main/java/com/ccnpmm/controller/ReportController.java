package com.ccnpmm.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ccnpmm.dao.ReportDAO;
import com.ccnpmm.entity.Report;

import com.ccnpmm.view.ExcelReportViewMonth;
import com.ccnpmm.view.ExcelReportViewYear;

@Controller
@RequestMapping("/admin")
public class ReportController {
	@Autowired ReportDAO rdao;
       @RequestMapping(value="/reportmonth",method=RequestMethod.GET)
       public ModelAndView getExcelMonth(){
              List<Report> reportmonths = new ArrayList<Report>();
             
      		for(int i=1;i<=12;i++)
    		{
    			Report reportmonth =new Report();
    			reportmonth.setDate(i);
    			reportmonth.setTotalSold(rdao.getSumSoldMonth(i));
    			reportmonth.setTotalMoney(rdao.getSumMoneyMonth(i));
    			reportmonths.add(reportmonth);
    			
    		}
    		
              return new ModelAndView(new ExcelReportViewMonth(), "reportmonths", reportmonths);
       }
       @RequestMapping(value="/reportyear",method=RequestMethod.GET)
       public ModelAndView getExcelYear(){
    	   List<Report> reportyears =new ArrayList<Report>();
   		LocalDate current_date = LocalDate.now();
             
   		for(int i=2021;i<=current_date.getYear();i++)
		{
			Report reportyear =new Report();
			reportyear.setDate(i);
			reportyear.setTotalSold(rdao.getSumSoldYear(i));
			reportyear.setTotalMoney(rdao.getSumMoneyYear(i));
			reportyears.add(reportyear);
			
		}
    		
              return new ModelAndView(new ExcelReportViewYear(), "reportyears", reportyears);
       }
}