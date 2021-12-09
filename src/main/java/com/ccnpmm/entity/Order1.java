package com.ccnpmm.entity;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Order1 {
	private Integer orderId;
	private String code;
	private String orderDate;
	private Double total;
	private String address;
	private String name;
	private String phone;
	private String deliveryStatus;
	private Integer userId;

	public Order1() {
		super();
	}

	public Order1(Integer orderId, String code, String orderDate, Double total, String address, String name, String phone,
			String deliveryStatus, Integer userId) {
		super();
		this.orderId = orderId;
		this.code = code;
		this.orderDate = orderDate;
		this.total = total;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.deliveryStatus = deliveryStatus;
		this.userId = userId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
