package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.User;

@Repository
public class UserDAO {

	@Autowired
	protected JdbcTemplate jdbc;
	
	public void register(User entity) {
		String sql = "INSERT INTO User (Username, Password, Email) VALUES (?,?,?)";
		jdbc.update(sql, entity.getUsername(), entity.getPassword(), entity.getEmail());
	}
	
	public void update(User entity) {
		String sql = "UPDATE User SET Name=?, Address=?, Phone=?, Birthday=?, Avartar=? WHERE Id=?";
		jdbc.update(sql, entity.getName(), entity.getAddress(), entity.getPhone(),
				entity.getBirthday(), entity.getAvatar(), entity.getId());
	}
	
	public void changePassword(User entity) {
		String sql = "UPDATE User SET Username=?, Password=? WHERE Id=?";
		jdbc.update(sql, entity.getUsername(), entity.getPassword(), entity.getId());
	}
	
	public void delete(Serializable id) {
		String sql = "DELETE FROM User WHERE Id=?";
		jdbc.update(sql, id);
	}

	public User getById(Serializable id) {
		String sql = "SELECT * FROM User WHERE Id=?";
		return jdbc.queryForObject(sql, getRowMapper(), id);
	}
	
	public User getByUsername(String username) {
		String sql = "SELECT * FROM [User] WHERE Username=?";
		return jdbc.queryForObject(sql, getRowMapper(), username);
	}

	public List<User> getAll() {
		String sql = "SELECT * FROM User";
		return getBySql(sql);
	}

	protected List<User> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<User> getRowMapper() {
		return new BeanPropertyRowMapper<User>(User.class);
	}
	
}
