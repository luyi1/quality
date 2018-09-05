package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "udtwip_processcardinfo", schema = "dbo")
public class WipProcessCardInfo extends WipModelBase {
	String processCardNumber;
	String materialCode;
	String partNumber;
	String partName;
	String endProductNumber;
	Integer quantity;
	String furnaceNumber;
	String materialFurnaceNumber;
	Integer batchNumber;
	String shipDate;
	String loadNumber;
	String packageNumber;
	String pilotNo;
	String size;

	@Column(name = "processcardnumber")
	public String getProcessCardNumber() {
		return processCardNumber;
	}

	public void setProcessCardNumber(String processCardNumber) {
		this.processCardNumber = processCardNumber;
	}

	@Column(name = "materialcode")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "partname")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "endproductnumber")
	public String getEndProductNumber() {
		return endProductNumber;
	}

	public void setEndProductNumber(String endProductNumber) {
		this.endProductNumber = endProductNumber;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "furnacenumber")
	public String getFurnaceNumber() {
		return furnaceNumber;
	}

	public void setFurnaceNumber(String furnaceNumber) {
		this.furnaceNumber = furnaceNumber;
	}

	@Column(name = "materialfurnacenumber")
	public String getMaterialFurnaceNumber() {
		return materialFurnaceNumber;
	}

	public void setMaterialFurnaceNumber(String materialFurnaceNumber) {
		this.materialFurnaceNumber = materialFurnaceNumber;
	}

	@Column(name = "batchnumber")
	public Integer getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name = "shipdate")
	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

	@Column(name = "packagenumber")
	public String getPackageNumber() {
		return packageNumber;
	}

	public void setPackageNumber(String packageNumber) {
		this.packageNumber = packageNumber;
	}

	@Column(name = "pilotno")
	public String getPilotNo() {
		return pilotNo;
	}

	public void setPilotNo(String pilotNo) {
		this.pilotNo = pilotNo;
	}

	@Column(name = "size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
