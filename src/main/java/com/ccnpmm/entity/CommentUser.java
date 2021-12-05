package com.ccnpmm.entity;

import java.util.Date;

public class CommentUser extends Comment {
	private String avatar;
	private String username;
	private Comment reply;

	public CommentUser(Integer id, Integer userId, String productId, String content, Date date, Integer replyFrom,
			String avatar, String username, Comment reply) {
		super(id, userId, productId, content, date, replyFrom);
		this.avatar = avatar;
		this.username = username;
		this.reply = reply;
	}

	public CommentUser(Integer id, Integer userId, String productId, String content, Date date, Integer replyFrom) {
		super(id, userId, productId, content, date, replyFrom);
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Comment getReply() {
		return reply;
	}

	public void setReply(Comment reply) {
		this.reply = reply;
	}
}
