package com.ccnpmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CartUser.class)
public class Cart {
	@Id
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;
	
	@Column(name = "Count")
	private Integer count;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
