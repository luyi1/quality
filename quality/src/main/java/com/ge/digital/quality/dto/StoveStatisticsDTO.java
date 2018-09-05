package com.ge.digital.quality.dto;

import java.util.Map;

public class StoveStatisticsDTO {

	long id;
	String line;
	String materialCode;
	String partNumber;
	String partName;
	String taskEndDate;
	String series;
	int amount;
	Map<Long, Map<String, Integer>> dates;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
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

	public String getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<Long, Map<String, Integer>> getDates() {
		return dates;
	}

	public void setDates(Map<Long, Map<String, Integer>> dates) {
		this.dates = dates;
	}

}
