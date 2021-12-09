package com.ccnpmm.entity;

import javax.persistence.Entity;

@Entity
public class OrderDetail {
	
	private Integer count;
	private Double price;
	private String productName;
	private Integer orderId;
	private String productId;

	public OrderDetail(Integer count, Double price, String productName, Integer orderId, String productId) {
		super();
		this.count = count;
		this.price = price;
		this.productName = productName;
		this.orderId = orderId;
		this.productId = productId;
	}

	public OrderDetail() {
		super();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
