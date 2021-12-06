package com.ccnpmm.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer userId;
	private String productId;
	private String content;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;
	private Integer replyFrom;

	public Comment(Integer id, Integer userId, String productId, String content, Date date, Integer replyFrom) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.content = content;
		this.date = date;
		this.replyFrom = replyFrom;
	}

	public Comment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getReplyFrom() {
		return replyFrom;
	}

	public void setReplyFrom(Integer replyFrom) {
		this.replyFrom = replyFrom;
	}
}
