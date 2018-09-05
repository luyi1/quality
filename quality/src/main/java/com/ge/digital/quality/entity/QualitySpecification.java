package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "ma_qualityspecification")
public class QualitySpecification extends ModelBase {

	String partNumber;

	String itemCode;

	Float specMin;

	Float specMax;

	String specType;

	String specUnit;

	boolean isPredictionItem;

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "itemcode")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "specmin")
	public Float getSpecMin() {
		return specMin;
	}

	public void setSpecMin(Float specMin) {
		this.specMin = specMin;
	}

	@Column(name = "specmax")
	public Float getSpecMax() {
		return specMax;
	}

	public void setSpecMax(Float specMax) {
		this.specMax = specMax;
	}

	@Column(name = "spectype")
	public String getSpecType() {
		return specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	@Column(name = "specunit")
	public String getSpecUnit() {
		return specUnit;
	}

	public void setSpecUnit(String specUnit) {
		this.specUnit = specUnit;
	}

	@Column(name = "ispredictionitem")
	public boolean isPredictionItem() {
		return isPredictionItem;
	}

	public void setPredictionItem(boolean isPredictionItem) {
		this.isPredictionItem = isPredictionItem;
	}

}
