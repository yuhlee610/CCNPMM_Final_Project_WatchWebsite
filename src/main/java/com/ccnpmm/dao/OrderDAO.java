package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Order;

@Repository
public class OrderDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(Order entity) {
		String sql = "INSERT INTO Order (OrderId, Code, OrderDate, Total, Address, Name, Phone,UserId, DeliveryStatus) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbc.update(sql, entity.getOrderId(), entity.getCode(), entity.getOrderDate(), entity.getTotal(),
				entity.getAddress(), entity.getName(), entity.getPhone(), entity.getUserId(),
				entity.getDeliveryStatus());
	}

	public void update(Order entity) {
		String sql = "UPDATE Order SET Code=?, OrderDate=?, Total=?, Address=?, Name=?, Phone=?, UserId=?, DeliveryStatus=? WHERE OrderId=?";
		jdbc.update(sql, entity.getCode(), entity.getOrderDate(), entity.getTotal(), entity.getAddress(),
				entity.getName(), entity.getPhone(), entity.getUserId(), entity.getDeliveryStatus(),
				entity.getOrderId());
	}

	public void delete(Serializable id) {
		String sql = "DELETE FROM Order WHERE OrderId=?";
		jdbc.update(sql, id);
	}

	public Order getById(Serializable id) {
		String sql = "SELECT * FROM Order WHERE OrderId=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}

	public List<Order> getAll() {
		String sql = "SELECT * FROM Order";
		return getBySql(sql);
	}

	protected List<Order> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Order> getRowMapper() {
		return new BeanPropertyRowMapper<Order>(Order.class);
	}
}
