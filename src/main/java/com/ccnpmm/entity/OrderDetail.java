package com.ccnpmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(OrderProduct.class)
public class OrderDetail {

	
	@Column(name = "Count")
	private Integer count;
	@Column(name = "Price")
	private Double price;
	@Column(name = "ProductName")
	private String productName;
	@Id
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order;
	@Id
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;
	
	public OrderDetail() {
		super();
	
	}
	public OrderDetail(Integer count, Double price, String productName, Order order, Product product) {
		super();
		this.count = count;
		this.price = price;
		this.productName = productName;
		this.order = order;
		this.product = product;
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
