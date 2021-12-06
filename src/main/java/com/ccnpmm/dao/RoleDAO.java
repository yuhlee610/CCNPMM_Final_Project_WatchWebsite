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
	
	public Role getById(Serializable id) {
		String sql = "SELECT * FROM Role WHERE RoleId=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}
	
	public List<Role> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}
	
	private RowMapper<Role> getRowMapper() {
		return new BeanPropertyRowMapper<Role>(Role.class);
	}
}
