package com.ccnpmm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	private String id;
	private String name;
	private Integer amount;
	private float price;
	private String image;
	@Column(name = "Decription")
	private String description;
	private Integer sold;

	@ManyToOne
	@JoinColumn(name = "BrandId")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "EnergyId")
	private Energy energy;

	@ManyToOne
	@JoinColumn(name = "MaterialId")
	private Material material;
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Product() {
		super();
	}

	public Product(String id, String name, Integer amount, float price, String image, String description, Integer sold,
			Brand brand, Energy energy, Material material) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.description = description;
		this.sold = sold;
		this.brand = brand;
		this.energy = energy;
		this.material = material;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Energy getEnergy() {
		return energy;
	}

	public void setEnergy(Energy energy) {
		this.energy = energy;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
