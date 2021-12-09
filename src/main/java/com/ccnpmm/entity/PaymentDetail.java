package com.ccnpmm.entity;

public class PaymentDetail {
	String productName;
	float subTotal;
	float shipping;
	float tax;
	float total;
	
	public PaymentDetail(String productName, String subTotal, String shipping, String tax, String total) {
		super();
		this.productName = productName;
		this.subTotal = Float.parseFloat(subTotal);
		this.shipping = Float.parseFloat(shipping);
		this.tax = Float.parseFloat(tax);
		this.total = Float.parseFloat(total);
	}

	public String getProductName() {
		return productName;
	}

	public String getSubTotal() {
		return String.format("%.2f", subTotal);
	}

	public String getShipping() {
		return String.format("%.2f", shipping);
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}

	public String getTotal() {
		return String.format("%.2f", total);
	}
	
}
