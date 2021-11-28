package com.ccnpmm.entity;

public class Filter {
	private String price;
	private int[] brands;
	private String sortBy;
	private Boolean viewMore;
	private Integer items;

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Filter(String price, int[] brands, String sortBy, Boolean viewMore, Integer items) {
		super();
		this.price = price;
		this.brands = brands;
		this.sortBy = sortBy;
		this.viewMore = viewMore;
		this.items = items;
	}

	public Filter() {
		super();
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int[] getBrands() {
		return brands;
	}

	public void setBrands(int[] brands) {
		this.brands = brands;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public Boolean getViewMore() {
		return viewMore;
	}

	public void setViewMore(Boolean viewMore) {
		this.viewMore = viewMore;
	}

}
