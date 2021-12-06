package com.ccnpmm.entity;

public class Filter {
	private String price;
	private int[] brands;
	private String sortBy;
	private Integer viewMore;

	public Filter(String price, int[] brands, String sortBy, Integer viewMore) {
		super();
		this.price = price;
		this.brands = brands;
		this.sortBy = sortBy;
		this.viewMore = viewMore;
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

	public Integer getViewMore() {
		return viewMore;
	}

	public void setViewMore(Integer viewMore) {
		this.viewMore = viewMore;
	}

}
