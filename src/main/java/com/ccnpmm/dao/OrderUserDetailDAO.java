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
public class OrderUserDetailDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	
	public List<DetailCart> getByUserId(Serializable id) {
		String sql = "select * from Cart, Product where Cart.UserId = ? and ProductId = Product.Id";
		return jdbc.query(sql, getRowMapper(), id);
	}

	
	private RowMapper<DetailCart> getRowMapper() {
		return new BeanPropertyRowMapper<DetailCart>(DetailCart.class);
	}
}
