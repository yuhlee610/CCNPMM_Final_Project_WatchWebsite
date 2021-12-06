package com.ccnpmm.entity;

import java.util.Date;
import java.util.List;

public class CommentUser extends Comment {
	private String avatar;
	private String username;
	private List<CommentUser> replyList;

	public CommentUser() { }

	public CommentUser(Integer id, Integer userId, String productId, String content, Date date, Integer replyFrom,
			String avatar, String username, List<CommentUser> replyList) {
		super(id, userId, productId, content, date, replyFrom);
		this.avatar = avatar;
		this.username = username;
		this.replyList = replyList;
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

	public List<CommentUser> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<CommentUser> replyList) {
		this.replyList = replyList;
	}
}
