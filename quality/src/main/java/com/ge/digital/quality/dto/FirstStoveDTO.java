package com.ge.digital.quality.dto;

import java.util.Date;

import org.apache.http.client.utils.DateUtils;

public class FirstStoveDTO {

	String line;
	String classification;
	String partNumber;
	String partName;
	Date taskEndDate;
	String materialCode;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getTaskEndDateString() {
		return DateUtils.formatDate(getTaskEndDate(), "yyyy-MM-dd");
	}

	public String getBeginDate() {
		String prefix = DateUtils.formatDate(getTaskEndDate(), "yyyy-MM-dd");
		String[] format = new String[1];
		format[0] = "yyyy-MM-dd HH:mm:ss";
		String str = prefix + " 00:00:00";
		return str;
	}

	public String getEndDate() {
		String prefix = DateUtils.formatDate(getTaskEndDate(), "yyyy-MM-dd");
		String[] format = new String[1];
		format[0] = "yyyy-MM-dd HH:mm:ss";
		String str = prefix + " 23:59:59";
		return str;
	}

	public String getUniqueCode() {
		return line + classification + partNumber + partName + materialCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

}
