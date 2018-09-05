package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "ma_qualityitem")
public class QualityItem extends ModelBase {

	String itemCode;

	String itemName;


	String limsItemName;





	@Column(name = "itemcode")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "itemname")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "limsitemname")
	public String getLimsItemName() {
		return limsItemName;
	}

	public void setLimsItemName(String limsItemName) {
		this.limsItemName = limsItemName;
	}

}
