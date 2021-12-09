package com.ccnpmm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	public int getCountProduct()
	{
		int count;
		String sql="SELECT COUNT(Id) FROM Product";
		try {
			 count= jdbc.queryForObject(sql, new Object[] { }, Integer.class);
		}
		catch (Exception e) {
			 count=0;
		}
		return count;
	}
	public int getCountUser()
	{
		int count;
		String sql="SELECT COUNT(Id) FROM [User] WHERE Id=1";
		try {
			count= jdbc.queryForObject(sql, new Object[] { }, Integer.class);
		}
		catch (Exception e) {
			count=0;
		}
		return count;
	}
	public int getSumSold()
	{
		int sum;
		String sql="SELECT SUM(Sold) FROM Product";
		try {
			sum= jdbc.queryForObject(sql, new Object[] { }, Integer.class);
		}
		catch (Exception e) {
			sum=0;
		}
		return sum;
	}
	public double getSumMoney()
	{
		double sum;
		String sql="SELECT SUM(Total) FROM [Order] WHERE DeliveryStatus='4'";
		try {
			sum= jdbc.queryForObject(sql, new Object[] { }, Integer.class);
		}
		catch (Exception e) {
			sum=0;
		}
		return sum;
	}
	public double getSumMoneyMonth(int month)
	{
		double sum;
		String sql="Select sum(Total) from [Order] where DeliveryStatus='4' and month(OrderDate)=?";
		try {
			sum= jdbc.queryForObject(sql, new Object[] {month }, Integer.class);
		}
		catch (Exception e) {
			sum=0;
		}
		return sum;
	}
	public int getSumSoldMonth(int month)
	{
		int sum;
		String sql="Select sum(Count) from [Order],OrderDetail where month(OrderDate)=? and DeliveryStatus='4' and [Order].OrderId=OrderDetail.OrderId";
		try {
			sum= jdbc.queryForObject(sql, new Object[] {month }, Integer.class);
		}
		catch (Exception e) {
			sum=0;
		}
		return sum;
	}
	public double getSumMoneyYear(int year)
	{
		double sum;
		String sql="Select sum(Total) from [Order] where DeliveryStatus='4' and year(OrderDate)=?";
		try {
			sum= jdbc.queryForObject(sql, new Object[] {year }, Integer.class);
		}
		catch (Exception e) {
			sum=0;
		}
		return sum;
	}
	public int getSumSoldYear(int year)
	{
		int sum;
		String sql="Select sum(Count) from [Order],OrderDetail where year(OrderDate)=? and DeliveryStatus='4' and [Order].OrderId=OrderDetail.OrderId";
		try {
			sum= jdbc.queryForObject(sql, new Object[] {year }, Integer.class);
		}
		catch (Exception e) {
			sum=0;
		}
		return sum;
	}
}
