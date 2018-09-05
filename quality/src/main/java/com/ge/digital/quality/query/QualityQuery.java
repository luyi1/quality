package com.ge.digital.quality.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class QualityQuery extends PageQuery {

	String Line;
	String LoadNumber;
	String batchNumber;
	String furnaceNumber;
	String materialCode;
	String partNumber;
	String partName;
	Integer recipeNumber;
	Date lineExitDateFrom;
	Date lineExitDateTo;
	Date inspectionTimeFrom;
	Date inspectionTimeTo;
	String predictionResult;
	String inspectionResult;

	public String getLine() {
		return Line;
	}

	public void setLine(String line) {
		Line = line;
	}

	public String getLoadNumber() {
		return LoadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		LoadNumber = loadNumber;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
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

	public Integer getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(Integer recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public String getPredictionResult() {
		return predictionResult;
	}

	public void setPredictionResult(String predictionResult) {
		this.predictionResult = predictionResult;
	}

	public String getInspectionResult() {
		return inspectionResult;
	}

	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}

	public Date getLineExitDateFrom() {
		return lineExitDateFrom;
	}

	public void setLineExitDateFrom(Date lineExitDateFrom) {
		this.lineExitDateFrom = lineExitDateFrom;
	}

	public Date getLineExitDateTo() {
		return lineExitDateTo;
	}

	public void setLineExitDateTo(Date lineExitDateTo) {
		this.lineExitDateTo = lineExitDateTo;
	}

	public Date getInspectionTimeFrom() {
		return inspectionTimeFrom;
	}

	public void setInspectionTimeFrom(Date inspectionTimeFrom) {
		this.inspectionTimeFrom = inspectionTimeFrom;
	}

	public Date getInspectionTimeTo() {
		return inspectionTimeTo;
	}

	public void setInspectionTimeTo(Date inspectionTimeTo) {
		this.inspectionTimeTo = inspectionTimeTo;
	}

	public boolean verifyDate() {
		return null == getLineExitDateFrom() || null == getLineExitDateTo() || null == getInspectionTimeFrom()
				|| null == getInspectionTimeTo();
	}

	public String toWhere() {
		String dateForamt = "yyyy-MM-dd HH:mm:ss";
		StringBuffer where = new StringBuffer();
		if (StringUtils.isNotBlank(getLine()))
			where.append(" and qqis.line='").append(getLine()).append("'");
		if (StringUtils.isNotBlank(getLoadNumber()))
			where.append(" and qqis.loadNumber='").append(getLoadNumber()).append("'");
		if (StringUtils.isNotBlank(getBatchNumber()))
			where.append(" and duwpc.batchNumber='").append(getBatchNumber()).append("'");
		if (StringUtils.isNotBlank(getFurnaceNumber()))
			where.append(" and duwpc.furnaceNumber='").append(getFurnaceNumber()).append("'");
		if (StringUtils.isNotBlank(getMaterialCode()))
			where.append(" and duwpc.materialCode='").append(getMaterialCode()).append("'");
		if (StringUtils.isNotBlank(getPartNumber()))
			where.append(" and duwpc.partNumber='").append(getPartNumber()).append("'");
		if (StringUtils.isNotBlank(getPartName()))
			where.append(" and duwpc.partName='").append(getPartName()).append("'");
		if (StringUtils.isNotBlank(getLoadNumber()))
			where.append(" and uwei.recipeNumber='").append(getRecipeNumber()).append("'");
		if (null != getLineExitDateFrom())
			where.append(" and lineExitDate>'").append(DateFormatUtils.format(getLineExitDateFrom(), dateForamt))
					.append("'");
		if (null != getLineExitDateTo())
			where.append(" and lineExitDate<='").append(DateFormatUtils.format(getLineExitDateTo(), dateForamt))
					.append("'");
		if (null != getInspectionTimeFrom())
			where.append(" and inspectionTime>'").append(DateFormatUtils.format(getInspectionTimeFrom(), dateForamt))
					.append("'");
		if (null != getInspectionTimeTo())
			where.append(" and inspectionTime<='").append(DateFormatUtils.format(getInspectionTimeTo(), dateForamt))
					.append("'");
		if (null != getPredictionResult())
			where.append(" and predictionResult in (").append(getPredictionResult()).append(")");
		if (StringUtils.isNotBlank(getInspectionResult())) {
			if (getInspectionResult().indexOf("3") > -1) {
				where.append(" and (inspectionResult in (").append(getInspectionResult()).append(")")
						.append(" or inspectionResult is null)");
			} else {
				where.append(" and inspectionResult in (").append(getInspectionResult()).append(")");
			}
		}

		return where.toString();
	}

}
