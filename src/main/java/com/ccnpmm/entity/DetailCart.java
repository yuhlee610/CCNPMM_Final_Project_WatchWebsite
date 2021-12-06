package com.ccnpmm.entity;

public class DetailCart {
	private String productId;
	private String name;
	private Integer count;
	private Integer amount;
	private float price;
	private String image;

	public DetailCart() {
		super();
	}

	public DetailCart(String productId, String name, Integer count, Integer amount, float price, String image) {
		super();
		this.productId = productId;
		this.name = name;
		this.count = count;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
