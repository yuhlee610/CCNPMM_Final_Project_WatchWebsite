package com.ccnpmm.entity;

public class OrderUserDetail {
	private String name;
	private String image;
	private Integer count;
	private float price;
	public OrderUserDetail(String name, String image, Integer count, float price) {
		super();
		this.name = name;
		this.image = image;
		this.count = count;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
