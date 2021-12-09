package com.ccnpmm.entity;

import javax.persistence.Entity;

@Entity
public class User1 {
	private Integer Id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String address;
	private boolean state;
	private String phone;
	private String birthday;
	private String avatar;
	private Integer roleId;
	private Role role;

	public User1() {
			super();
		}

	public User1(Integer id, String username, String password, String email, String name, String address, boolean state,
			String phone, String birthday, String avatar, Integer roleId, Role role) {
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
		this.roleId = roleId;
		this.role = role;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
