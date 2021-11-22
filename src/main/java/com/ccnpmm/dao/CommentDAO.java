package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.Comment;

@Repository
public class CommentDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	
	public void insert(Comment entity) {
		String sql = "INSERT INTO Comment (UserId, ProducId, Content, Date, ReplyFrom) VALUES (?,?,?,?,?)";
		jdbc.update(sql, entity.getUser().getId(), entity.getProduct().getId(), 
				entity.getContent(), entity.getDate(), entity.getComment().getId());
	}
	
	public void update(Comment entity) {
		String sql = "UPDATE Comment SET Content=?, Date =? WHERE Id=?";
		jdbc.update(sql, entity.getContent(), entity.getDate(), entity.getId());
	}
	
	public void delete(Serializable id) {
		String sql = "DELETE FROM Comment WHERE Id=?";
		jdbc.update(sql, id);
	}

	public Comment getByProduct(Serializable productId) {
		String sql = "SELECT * FROM Comment WHERE ProductId=?";
		return jdbc.queryForObject(sql, getRowMapper(), productId);
	}

	public List<Comment> getAll() {
		String sql = "SELECT * FROM Comment";
		return getBySql(sql);
	}

	protected List<Comment> getBySql(String sql) {
		return jdbc.query(sql, getRowMapper());
	}

	private RowMapper<Comment> getRowMapper() {
		return new BeanPropertyRowMapper<Comment>(Comment.class);
	}

}
