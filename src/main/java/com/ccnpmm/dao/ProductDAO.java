package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Product;

@Repository
public class ProductDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(Product entity) {
		String sql = "INSERT INTO Product (Id, Name, Amount, Price, Image, Decription, Sold, BrandId, EnergyId, MaterialId) VALUES (?,?,?,?,?,?,?,?,?,?)";
		jdbc.update(sql, entity.getId(), entity.getName(), entity.getAmount(), entity.getPrice(), entity.getPrice(),
				entity.getImage(), entity.getDecription(), entity.getSold(), entity.getBrandId(), entity.getEnergyId(),
				entity.getMaterialId());
	}

	public void update(Product entity) {
		String sql = "UPDATE Product SET Name=?, Amount=?, Price=?, Image=?, Description=?, Sold=?, BrandId=?, EnergyId=?, MaterialId=? WHERE Id=?";
		jdbc.update(sql, entity.getName(), entity.getAmount(), entity.getPrice(), entity.getPrice(), entity.getImage(),
				entity.getDecription(), entity.getSold(), entity.getBrandId(), entity.getEnergyId(),
				entity.getMaterialId(), entity.getId());
	}

	public void delete(Serializable id) {
		String sql = "DELETE FROM Product WHERE Id=?";
		jdbc.update(sql, id);
	}

	public Product getById(String id) {
		String sql = "SELECT * FROM Product WHERE Id=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}

	public List<Product> getAll() {
		String sql = "SELECT * FROM Product";
		return getBySql(sql);
	}

	public List<Product> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Product> getRowMapper() {
		return new BeanPropertyRowMapper<Product>(Product.class);
	}
}
