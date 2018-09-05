package com.ge.digital.quality.vo;

public class InspectionDetailVO {

	String name;
	String itemPredictionResult;
	String itemInspectionResult;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemPredictionResult() {
		return itemPredictionResult;
	}

	public void setItemPredictionResult(String itemPredictionResult) {
		if (null == itemPredictionResult) {
			this.itemPredictionResult = itemPredictionResult;
			return;
		}
		switch (itemPredictionResult) {
		case "1":
			this.itemPredictionResult = "合格";
			break;
		case "2":
			this.itemPredictionResult = "不合格";
			break;
		case "3":
			this.itemPredictionResult = "无法预测";
			break;

		default:
			this.itemPredictionResult = "未知";
			break;
		}
	}

	public String getItemInspectionResult() {
		return itemInspectionResult;
	}

	public void setItemInspectionResult(String itemInspectionResult) {
		if (null == itemInspectionResult) {
			this.itemInspectionResult = itemInspectionResult;
			return;
		}
		switch (itemInspectionResult) {
		case "1":
			this.itemInspectionResult = "合格";
			break;
		case "2":
			this.itemInspectionResult = "不合格";
			break;

		default:
			this.itemInspectionResult = "未知";
			break;
		}
	}

}
