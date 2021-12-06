package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Material;

@Repository
public class MaterialDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(Material entity) {
		String sql = "INSERT INTO Material (MaterialName) VALUES (?)";
		jdbc.update(sql, entity.getMaterialName());
	}

	public void update(Material entity) {
		String sql = "UPDATE Material SET MaterialName=? WHERE MaterialId=?";
		jdbc.update(sql, entity.getMaterialName(), entity.getMaterialId());
	}

	public void delete(Serializable id) {
		String sql = "DELETE FROM Material WHERE MaterialId=?";
		jdbc.update(sql, id);
	}

	public Material getById(Serializable id) {
		String sql = "SELECT * FROM Material WHERE MaterialId=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}

	public List<Material> getAll() {
		String sql = "SELECT * FROM Material";
		return getBySql(sql);
	}

	protected List<Material> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	public Material getByName(String name)
	{
		String sql="SELECT * FROM Material WHERE MaterialName=?";
		return jdbc.queryForObject(sql, getRowMapper(),name);
		
	}

	private RowMapper<Material> getRowMapper() {
		return new BeanPropertyRowMapper<Material>(Material.class);
	}
}
