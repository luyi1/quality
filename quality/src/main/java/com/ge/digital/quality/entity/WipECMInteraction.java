package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "udtwip_ecminteraction", schema = "dbo")
public class WipECMInteraction extends WipModelBase {

	String batchNumber;
	String line;
	String lodingTableNumber;
	String unloadingTableNumber;
	String partNumber;
	String recipeNumber;
	String heatingNumber;
	String loadNumber;
	String line_entrytime;
	String line_exittime;
	String loadstatus;
	String timestamp;

	@Column(name = "batchnumber")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "lodingtablenumber")
	public String getLodingTableNumber() {
		return lodingTableNumber;
	}

	public void setLodingTableNumber(String lodingTableNumber) {
		this.lodingTableNumber = lodingTableNumber;
	}

	@Column(name = "unloadingtablenumber")
	public String getUnloadingTableNumber() {
		return unloadingTableNumber;
	}

	public void setUnloadingTableNumber(String unloadingTableNumber) {
		this.unloadingTableNumber = unloadingTableNumber;
	}

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "recipenumber")
	public String getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	@Column(name = "heatingnumber")
	public String getHeatingNumber() {
		return heatingNumber;
	}

	public void setHeatingNumber(String heatingNumber) {
		this.heatingNumber = heatingNumber;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

	@Column(name = "line_entrytime")
	public String getLine_entrytime() {
		return line_entrytime;
	}

	public void setLine_entrytime(String line_entrytime) {
		this.line_entrytime = line_entrytime;
	}

	@Column(name = "line_exittime")
	public String getLine_exittime() {
		return line_exittime;
	}

	public void setLine_exittime(String line_exittime) {
		this.line_exittime = line_exittime;
	}

	@Column(name = "loadstatus")
	public String getLoadstatus() {
		return loadstatus;
	}

	public void setLoadstatus(String loadstatus) {
		this.loadstatus = loadstatus;
	}

	@Column(name = "timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
