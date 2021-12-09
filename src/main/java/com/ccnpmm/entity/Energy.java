package com.ccnpmm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Energy {
	@Id
	@GeneratedValue
	private Integer energyId;
	private String energyName;

	public Energy() {
		super();
	}

	public Energy(Integer energyId, String energyName) {
		super();
		this.energyId = energyId;
		this.energyName = energyName;
	}

	public Integer getEnergyId() {
		return energyId;
	}

	public void setEnergyId(Integer energyId) {
		this.energyId = energyId;
	}

	public String getEnergyName() {
		return energyName;
	}

	public void setEnergyName(String energyName) {
		this.energyName = energyName;
	}
	public String toString() {
		   return energyName;
		}
}
