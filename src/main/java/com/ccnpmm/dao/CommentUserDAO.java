package com.ccnpmm.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ccnpmm.entity.CommentUser;

@Repository
public class CommentUserDAO {
	@Autowired
	protected JdbcTemplate jdbc;
	
	public List<CommentUser> getByProductId(Serializable id) {
		String sql = "select Comment.Id, Comment.Content, Comment.Date, Comment.ProductId, Comment.ReplyFrom, Comment.UserId, [User].Username, [User].Avatar from Comment, [User] where Comment.UserId = [User].Id and ProductId = ?";
		return jdbc.query(sql, getRowMapper(), id);
	}
	
	public List<CommentUser> getReply(Serializable id, Serializable productId) {
		String sql = "select Comment.Id, Comment.Content, Comment.Date, Comment.ProductId, Comment.ReplyFrom, Comment.UserId, [User].Username, [User].Avatar from Comment, [User] where ReplyFrom = ? and ProductId = ? and Comment.UserId = [User].Id";
		return jdbc.query(sql, getRowMapper(), id, productId);
	}
	
	private RowMapper<CommentUser> getRowMapper() {
		return new BeanPropertyRowMapper<CommentUser>(CommentUser.class);
	}
}
