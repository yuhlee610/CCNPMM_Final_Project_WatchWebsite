package com.ccnpmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Integer brandId;
	private Integer energyId;
	private Integer materialId;

	public Product() {
		super();
	}

	public Product(String id, String name, Integer amount, float price, String image, String description, Integer sold,
			Integer brandId, Integer energyId, Integer materialId) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.description = description;
		this.sold = sold;
		this.brandId = brandId;
		this.energyId = energyId;
		this.materialId = materialId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
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

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getEnergyId() {
		return energyId;
	}

	public void setEnergyId(Integer energyId) {
		this.energyId = energyId;
	}

}
