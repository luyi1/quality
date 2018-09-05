package com.ge.digital.quality.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;

public class StoveStatisticsQuery extends PageQuery {

	Date fromDate;
	Date toDate;
	String line;
	String materialCode;
	String partNumber;
	String partName;
	String classification;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

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

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String toWhere() {
		StringBuffer where = new StringBuffer();
		if (StringUtils.isNotBlank(getLine()))
			where.append(" and uwt.line='").append(getLine()).append("'");
		if (StringUtils.isNotBlank(getMaterialCode()))
			where.append(" and umview.materialCode='").append(getMaterialCode()).append("'");
		if (StringUtils.isNotBlank(getPartNumber()))
			where.append(" and uwt.partNumber='").append(getPartNumber()).append("'");
		if (StringUtils.isNotBlank(getPartName()))
			where.append(" and uwt.partName='").append(getPartName()).append("'");
		if (StringUtils.isNotBlank(getClassification()))
			where.append(" and umview.classification='").append(getClassification()).append("'");
		if (null != fromDate)
			where.append(" and uwt.taskEndDate > '").append(DateUtils.formatDate(getFromDate(), "yyyy-MM-dd 00:00:00"))
					.append("'");
		if (null != toDate)
			where.append(" and uwt.taskEndDate <= '").append(DateUtils.formatDate(getToDate(), "yyyy-MM-dd 23:59:59"))
					.append("'");
		return where.toString();
	}

}
