package com.ge.digital.quality.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.utils.DateUtils;

public class GasUsageVO implements Serializable {

	String line;
	String loadNumber;
	String acetyleneUsage;
	String nitrogenUsage;
	String batchNumber;
	String materialCode;
	String partNumber;
	String partName;
	String recipeNumber;
	Date lineExitDate;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

	public String getAcetyleneUsage() {
		return acetyleneUsage;
	}

	public void setAcetyleneUsage(String acetyleneUsage) {
		this.acetyleneUsage = acetyleneUsage;
	}

	public String getNitrogenUsage() {
		return nitrogenUsage;
	}

	public void setNitrogenUsage(String nitrogenUsage) {
		this.nitrogenUsage = nitrogenUsage;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public Date getLineExitDate() {
		return lineExitDate;
	}

	public void setLineExitDate(Date lineExitDate) {
		this.lineExitDate = lineExitDate;
	}

	public List<Object> values() {
		List<Object> values = new ArrayList<>();
		values.add(getLine());
		values.add(getLoadNumber());
		values.add(acetyleneUsage);
		values.add(getNitrogenUsage());
		values.add(batchNumber);
		values.add(materialCode);
		values.add(partNumber);
		values.add(partName);
		values.add(recipeNumber);
		values.add(DateUtils.formatDate(getLineExitDate(), "yyyy-MM-dd HH:mm:ss"));
		return values;
	}

}
