package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Cart;

@Repository
public class CartDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	
	public void insert(Cart entity) {
		String sql = "INSERT INTO Cart (UserId, ProductId, Count) VALUES (?,?,?)";
		jdbc.update(sql, entity.getUser().getId(), entity.getProduct().getId(), entity.getCount());
	}
	
	public void update(Cart entity) {
		String sql = "UPDATE User SET Count=? WHERE UserId=? and ProductId=?";
		jdbc.update(sql, entity.getCount(), entity.getUser().getId(), entity.getProduct().getId());
	}
	
	public void delete(Serializable userId, Serializable productId ) {
		String sql = "DELETE FROM Cart WHERE UserId=? and ProductId=?";
		jdbc.update(sql, userId, productId);
	}

	public Cart getByUser(Serializable userId) {
		String sql = "SELECT * FROM Cart WHERE UserId=?";
		return jdbc.queryForObject(sql, getRowMapper(), userId);
	}
	
	public Cart getByUserAndProduct(Serializable userId, Serializable productId) {
		String sql = "SELECT * FROM Cart WHERE UserId=? and ProductId=?";
		return jdbc.queryForObject(sql, getRowMapper(), userId, productId);
	}

	public List<Cart> getAll() {
		String sql = "SELECT * FROM Cart";
		return getBySql(sql);
	}

	public List<Cart> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Cart> getRowMapper() {
		return new BeanPropertyRowMapper<Cart>(Cart.class);
	}
}
