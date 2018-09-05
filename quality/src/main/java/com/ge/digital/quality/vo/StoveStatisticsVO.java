package com.ge.digital.quality.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StoveStatisticsVO implements Serializable {

	String line;
	String materialCode;
	String partNumber;
	String partName;
	String classification;
	int loadQuantity;
	int totalStove;
	int totalAmount;
	Map<String, Integer> daliy = new LinkedHashMap<>();

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

	public int getLoadQuantity() {
		return loadQuantity;
	}

	public void setLoadQuantity(int loadQuantity) {
		this.loadQuantity = loadQuantity;
	}

	public Map<String, Integer> getDaliy() {
		return daliy;
	}

	public void setDaliy(Map<String, Integer> daliy) {
		this.daliy = daliy;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalStove() {
		return totalStove;
	}

	public void setTotalStove(int totalStove) {
		this.totalStove = totalStove;
	}

	public List<String> getExcelTitles() {
		List<String> titles = new ArrayList<>();
		titles.add("line");
		titles.add("materialCode");
		titles.add("partNumber");
		titles.add("partName");
		titles.add("classification");
		titles.add("loadQuantity");
		titles.add("totalStove");
		titles.add("totalAmount");
		Set<String> keys = daliy.keySet();
		for (String key : keys) {
			titles.add(key);
		}
		return titles;
	}

	public List<Object> getValues() {
		List<Object> values = new ArrayList<>();
		values.add(getLine());
		values.add(getMaterialCode());
		values.add(getPartNumber());
		values.add(getPartName());
		values.add(getClassification());
		values.add(getLoadQuantity());
		values.add(getTotalStove());
		values.add(getTotalAmount());
		Set<String> keys = daliy.keySet();
		for (String key : keys) {
			values.add(daliy.get(key));
		}
		return values;
	}

}
