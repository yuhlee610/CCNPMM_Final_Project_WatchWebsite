package com.ccnpmm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "OrderId")
	private Integer orderId;
	@Column(name = "Code")
	private String code;
	@Column(name = "OrderDate")
	private Date orderDate;
	@Column(name = "Total")
	private Double total;
	@Column(name = "Address")
	private String address;
	@Column(name = "Name")
	private String name;
	@Column(name = "Phone")
	private String phone;
	@Column(name = "DeliveryStatus")
	private String deliveryStatus;
	private Integer userId;

	public Order() {
		super();
	}

	public Order(Integer orderId, String code, Date orderDate, Double total, String address, String name, String phone,
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
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
