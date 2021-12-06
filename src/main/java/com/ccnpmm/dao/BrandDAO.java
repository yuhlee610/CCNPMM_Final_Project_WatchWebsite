package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Brand;

@Repository
public class BrandDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(Brand entity) {
		String sql = "INSERT INTO Brand (BrandName) VALUES (?)";
		jdbc.update(sql, entity.getBrandName());
	}

	public void update(Brand entity) {
		String sql = "UPDATE Brand SET BrandName=? WHERE BrandId=?";
		jdbc.update(sql, entity.getBrandName(), entity.getBrandId());
	}

	public void delete(Serializable id) {
		String sql = "DELETE FROM Brand WHERE BrandId=?";
		jdbc.update(sql, id);
	}

	public Brand getById(Serializable id) {
		String sql = "SELECT * FROM Brand WHERE BrandId=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}

	public List<Brand> getAll() {
		String sql = "SELECT * FROM Brand";
		return getBySql(sql);
	}

	protected List<Brand> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}
	public Brand getByName(String name)
	{
		String sql="SELECT * FROM Brand WHERE BrandName=?";
		return jdbc.queryForObject(sql, getRowMapper(),name);
		
	}
	private RowMapper<Brand> getRowMapper() {
		return new BeanPropertyRowMapper<Brand>(Brand.class);
	}
}
