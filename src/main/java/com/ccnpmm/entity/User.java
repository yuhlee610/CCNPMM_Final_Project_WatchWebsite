package com.ccnpmm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer Id;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "State")
	private boolean state;
	
	@Column(name = "Phone")
	private String phone;
	
	@Column(name = "Birthday")
	private String birthday;
	
	@Column(name = "Avatar")
	private String avatar;

	@ManyToOne
	@JoinColumn(name = "RoleId")
	private Role role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Collection<Cart> carts;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Collection<Order> orders;
	
	public User() {
		super();
	}

	public User(Integer id, String username, String password, String email, String name, String address, boolean state,
			String phone, String birthday, String avatar, Role role, Collection<Cart> carts, Collection<Order> orders) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
		this.state = state;
		this.phone = phone;
		this.birthday = birthday;
		this.avatar = avatar;
		this.role = role;
		this.carts = carts;
		this.orders = orders;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Collection<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Cart> carts) {
		this.carts = carts;
	}

	
	
}
