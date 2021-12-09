package com.ccnpmm.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.ccnpmm.entity.Report;

public class ExcelReportViewYear extends AbstractXlsView{
	 
	 @Override
	 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
	 HttpServletResponse response) throws Exception {
	  
	 response.setHeader("Content-Disposition", "attachment;filename=\"year.xls\"");
	 List<Report> ReportList = (List<Report>) model.get("reportyears");
	 Sheet sheet = workbook.createSheet("Report Data");
	 Row header = sheet.createRow(0);
	 header.createCell(0).setCellValue("Year");
	 header.createCell(1).setCellValue("Total Sold");
	 header.createCell(2).setCellValue("Total Earnings");
	  
	 int rowNum = 1;
	 for(Report report:ReportList){
	 Row row = sheet.createRow(rowNum++);
	 row.createCell(0).setCellValue(report.getDate());
	 row.createCell(1).setCellValue(report.getTotalSold());
	 row.createCell(2).setCellValue(report.getTotalMoney());
	 }
	 }
	}
