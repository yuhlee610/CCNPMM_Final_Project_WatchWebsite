package com.ccnpmm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Material {
	@Id
	@GeneratedValue
	private Integer materialId;
	private String materialName;

	public Material() {
		super();
	}

	public Material(Integer materialId, String materialName) {
		super();
		this.materialId = materialId;
		this.materialName = materialName;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String toString() {
		   return materialName;
		}
}
