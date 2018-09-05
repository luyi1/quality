package com.ge.digital.quality.vo;

import java.util.Date;

public class QualityResultVO {
	String inspectionNo;
	String line;
	String loadNumber;
	Date inspectionTime;
	String predictionResult;

	Date predictionTime;
	String inspectionResult;
	String batchNumber;
	String furnaceNumber;
	String materialCode;
	String partNumber;
	String partName;
	String size;
	Date lineExitDate;
	String recipeNumber;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Date getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}

	public String getPredictionResult() {
		String value = "";
		switch (predictionResult) {
		case "1":
			value = "建议免检";
			break;
		case "2":
			value = "建议检测";
			break;
		case "3":
			value = "无法预测";
			break;
		default:
			value = "无";
			break;
		}
		return value;
	}

	public void setPredictionResult(String predictionResult) {
		this.predictionResult = predictionResult;
	}

	public String getInspectionResult() {
		String value = "无";
		switch (inspectionResult) {
		case "1":
			value = "合格";
			break;
		case "2":
			value = "不合格";
			break;
		default:
			value = "未检测";
			break;
		}
		return value;
	}

	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}

	public String getFurnaceNumber() {
		return furnaceNumber;
	}

	public void setFurnaceNumber(String furnaceNumber) {
		this.furnaceNumber = furnaceNumber;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getLineExitDate() {
		return lineExitDate;
	}

	public void setLineExitDate(Date lineExitDate) {
		this.lineExitDate = lineExitDate;
	}

	public String getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

	public String getInspectionNo() {
		return inspectionNo;
	}

	public void setInspectionNo(String inspectionNo) {
		this.inspectionNo = inspectionNo;
	}

	public Date getPredictionTime() {
		return predictionTime;
	}

	public void setPredictionTime(Date predictionTime) {
		this.predictionTime = predictionTime;
	}

}
