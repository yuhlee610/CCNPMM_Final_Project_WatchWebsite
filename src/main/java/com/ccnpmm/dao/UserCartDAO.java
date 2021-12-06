package com.ccnpmm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.UserCart;

@Repository
public class UserCartDAO {
	@Autowired protected JdbcTemplate jdbc;
	
	public List<UserCart> getByUser(int userid) {
		String sql = "SELECT c.UserId as UserId, c.ProductId as ProductId, c.Count as Count, p.Name as Name, p.Price as Price, p.Amount as Amount, p.Image as Image, b.BrandName as BrandName "
				+ "FROM Cart AS c ,Product AS p, Brand AS b "
				+ "WHERE c.ProductId = p.Id and b.BrandId = p.BrandId and c.UserId="+userid;
		return getBySql(sql);
	}
	
	public List<UserCart> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}
	
	private RowMapper<UserCart> getRowMapper() {
		return new BeanPropertyRowMapper<UserCart>(UserCart.class);
	}
	
}
