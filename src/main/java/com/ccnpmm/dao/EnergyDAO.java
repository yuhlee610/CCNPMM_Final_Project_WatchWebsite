package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Energy;

@Repository
public class EnergyDAO {
	@Autowired
	protected JdbcTemplate jdbc;

	public void insert(Energy entity) {
		String sql = "INSERT INTO Energy (EnergyName) VALUES (?)";
		jdbc.update(sql, entity.getEnergyName());
	}

	public void update(Energy entity) {
		String sql = "UPDATE Energy SET EnergyName=? WHERE EnergyId=?";
		jdbc.update(sql, entity.getEnergyName(), entity.getEnergyId());
	}

	public void delete(Serializable id) {
		String sql = "DELETE FROM Energy WHERE EnergyId=?";
		jdbc.update(sql, id);
	}

	public Energy getById(Serializable id) {
		String sql = "SELECT * FROM Energy WHERE EnergyId=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}

	public List<Energy> getAll() {
		String sql = "SELECT * FROM Energy";
		return getBySql(sql);
	}

	protected List<Energy> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Energy> getRowMapper() {
		return new BeanPropertyRowMapper<Energy>(Energy.class);
	}
}
