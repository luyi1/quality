package com.ge.digital.quality.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;

public class GasUsageQuery {

	String line;
	String loadNumber;
	String materialCode;
	String partNumber;
	String partName;
	String recipeNumber;
	Date lineExitDateFrom;
	Date lineExitDateTo;

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

	public String toWhere() {
		StringBuffer where = new StringBuffer();
		if (StringUtils.isNotBlank(getLine())) {
			where.append(" and qgu.line='").append(getLine()).append("'");
		}
		if (StringUtils.isNotBlank(getLoadNumber())) {
			where.append(" and qgu.loadNumber='").append(getLoadNumber()).append("'");
		}
		if (StringUtils.isNotBlank(getMaterialCode())) {
			where.append(" and uwpci.materialCode='").append(getMaterialCode()).append("'");
		}
		if (StringUtils.isNotBlank(getPartNumber())) {
			where.append(" and uwpci.partNumber='").append(getPartNumber()).append("'");
		}
		if (StringUtils.isNotBlank(getPartName())) {
			where.append(" and uwpci.partName='").append(getPartName()).append("'");
		}
		if (StringUtils.isNotBlank(getRecipeNumber())) {
			where.append(" and uwei.recipeNumber='").append(getRecipeNumber()).append("'");
		}
		if (null != getLineExitDateFrom()) {
			where.append(" and uehttd.lineExitDate>='")
					.append(DateUtils.formatDate(getLineExitDateFrom(), "yyyy-MM-dd hh:mm:ss")).append("'");
		}
		if (null != getLineExitDateTo()) {
			where.append(" and uehttd.lineExitDate<'")
					.append(DateUtils.formatDate(getLineExitDateTo(), "yyyy-MM-dd hh:mm:ss")).append("'");
		}
		return where.toString();
	}

}
