package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.DetailCart;


@Repository
public class DetailCartDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	
	public List<DetailCart> getByUserId(Serializable id) {
		String sql = "select * from Cart, Product where Cart.UserId = ? and ProductId = Product.Id";
		return jdbc.query(sql, getRowMapper(), id);
	}
	
	public List<DetailCart> getDetailOrder(Serializable orderId) {
		String sql = "select ProductId, ProductName as Name, Count, OrderDetail.Price AS Price, Product.Image AS Image\r\n"
				+ "from OrderDetail, Product\r\n"
				+ "where OrderDetail.ProductId = Product.Id and OrderDetail.OrderId = ?";
		return jdbc.query(sql, getRowMapper(), orderId);
	}

	
	private RowMapper<DetailCart> getRowMapper() {
		return new BeanPropertyRowMapper<DetailCart>(DetailCart.class);
	}
}
