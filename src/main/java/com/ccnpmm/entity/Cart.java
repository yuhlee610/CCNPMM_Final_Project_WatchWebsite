package com.ccnpmm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart {
	private Integer userId;
	private String productId;
	private Integer count;

	public Cart() {
		super();
	}

	public Cart(Integer userId, String productId, Integer count) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.count = count;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
