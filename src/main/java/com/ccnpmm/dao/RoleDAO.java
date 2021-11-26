package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Role;

@Repository
public class RoleDAO {

	@Autowired
	protected JdbcTemplate jdbc;
	
	public void delete(Serializable id) {
		String sql = "DELETE FROM Role WHERE RoleId=?";
		jdbc.update(sql, id);
	}

	public Role getRole(Serializable roleId) {
		String sql = "SELECT * FROM Role WHERE RoleId=?";
		return jdbc.queryForObject(sql, getRowMapper(), roleId);
	}

	public List<Role> getAll() {
		String sql = "SELECT * FROM Role";
		return getBySql(sql);
	}

	protected List<Role> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Role> getRowMapper() {
		return new BeanPropertyRowMapper<Role>(Role.class);
	}
}
