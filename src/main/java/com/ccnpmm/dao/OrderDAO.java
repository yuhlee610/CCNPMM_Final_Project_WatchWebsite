package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Order;
import com.ccnpmm.entity.Order1;

@Repository
public class OrderDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(Order entity) {
		String sql = "INSERT INTO [Order] (OrderId, Code, OrderDate, Total, Address, Name, Phone,UserId, DeliveryStatus) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbc.update(sql, entity.getOrderId(), entity.getCode(), entity.getOrderDate(), entity.getTotal(),
				entity.getAddress(), entity.getName(), entity.getPhone(), entity.getUserId(),
				entity.getDeliveryStatus());
	}
	
	public void insert1(Order1 entity) {
		String sql = "INSERT INTO [dbo].[Order] (Code, OrderDate, Total, Address, Name, Phone, UserId, DeliveryStatus) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbc.update(sql, entity.getCode(), entity.getOrderDate(), entity.getTotal(),
				entity.getAddress(), entity.getName(), entity.getPhone(), entity.getUserId(),
				entity.getDeliveryStatus());
	}

	public void update(Order entity) {
		String sql = "UPDATE [Order] SET Code=?, OrderDate=?, Total=?, Address=?, Name=?, Phone=?, UserId=?, DeliveryStatus=? WHERE OrderId=?";
		jdbc.update(sql, entity.getCode(), entity.getOrderDate(), entity.getTotal(), entity.getAddress(),
				entity.getName(), entity.getPhone(), entity.getUserId(), entity.getDeliveryStatus(),
				entity.getOrderId());
	}

	public void delete(Serializable id) {
		String sql = "DELETE FROM [Order] WHERE OrderId=?";
		jdbc.update(sql, id);
	}

	public Order getById(Serializable id) {
		String sql = "SELECT * FROM [Order] WHERE OrderId=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}
	
	public List<Order> getByUserId(Serializable userid) {
		String sql = "SELECT * FROM [Order] WHERE UserId="+userid;
		return getBySql(sql);
	}

	public Order getByCode(String code) {
		String sql = "SELECT * FROM [Order] WHERE Code=?";
		return jdbc.queryForObject(sql, getRowMapper(), code);
	}
	
	public List<Order> getAll() {
		String sql = "SELECT * FROM [Order]";
		return getBySql(sql);
	}
	public List<Order> getOrderWconfirm() {
		String sql = "SELECT * FROM [Order] WHERE DeliveryStatus='1'";
		return getBySql(sql);
	}
	public List<Order> getOrderConfirm() {
		String sql = "SELECT * FROM [Order] WHERE DeliveryStatus='2'";
		return getBySql(sql);
	}
	public List<Order> getOrderDeliver() {
		String sql = "SELECT * FROM [Order] WHERE DeliveryStatus='3'";
		return getBySql(sql);
	}
	public List<Order> getOrderDelivered() {
		String sql = "SELECT * FROM [Order] WHERE DeliveryStatus='4'";
		return getBySql(sql);
	}
	public List<Order> getOrderCancel() {
		String sql = "SELECT * FROM [Order] WHERE DeliveryStatus='5'";
		return getBySql(sql);
	}
	protected List<Order> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Order> getRowMapper() {
		return new BeanPropertyRowMapper<Order>(Order.class);
	}
}
