package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.OrderDetail;

@Repository
public class OrderDetailDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(OrderDetail entity) {
		String sql = "INSERT INTO OrderDetail (OrderId, ProductId, Count, Price, ProductName) VALUES (?,?,?,?,?)";
		jdbc.update(sql, entity.getOrder().getOrderId(), entity.getProduct().getId(), entity.getCount(), entity.getPrice(), 
				entity.getProductName());
	}

	public void update(OrderDetail entity) {
		String sql = "UPDATE OrderDetail SET Count=?, Price=?, ProductName=? WHERE OrderId=? and ProductId=?";
		jdbc.update(sql, entity.getCount(), entity.getPrice(), 
				entity.getProductName(),entity.getOrder().getOrderId(), entity.getProduct().getId());
	}
	public void delete(Serializable orderid,Serializable productid) {
		String sql = "DELETE FROM OrderDetail WHERE OrderId=? and ProductId=?";
		jdbc.update(sql, orderid,productid);
	}

	public OrderDetail getById(Serializable orderid,Serializable productid) {
		String sql = "SELECT * FROM OrderDetail WHERE OrderId=? and ProductId=?";
		return jdbc.queryForObject(sql, getRowMapper(), orderid,productid);
	}

	public List<OrderDetail> getAll() {
		String sql = "SELECT * FROM OrderDetail";
		return getBySql(sql);
	}

	protected List<OrderDetail> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<OrderDetail> getRowMapper() {
		return new BeanPropertyRowMapper<OrderDetail>(OrderDetail.class);
	}
}
