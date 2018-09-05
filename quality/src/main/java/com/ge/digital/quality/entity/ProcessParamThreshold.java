package com.ge.digital.quality.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "ma_processparamthreshold")
public class ProcessParamThreshold extends ModelBase {

	String partNumber;

	String line;

	String item;

	Integer sequenceNo;

	Date paramRelativeTime;

	Float paramValStandard;

	Float paramValMax;

	Float paramValMin;

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "item")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "sequenceno")
	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	@Column(name = "paramrelativetime")
	public Date getParamRelativeTime() {
		return paramRelativeTime;
	}

	public void setParamRelativeTime(Date paramRelativeTime) {
		this.paramRelativeTime = paramRelativeTime;
	}

	@Column(name = "paramvalstandard")
	public Float getParamValStandard() {
		return paramValStandard;
	}

	public void setParamValStandard(Float paramValStandard) {
		this.paramValStandard = paramValStandard;
	}

	@Column(name = "paramvalmax")
	public Float getParamValMax() {
		return paramValMax;
	}

	public void setParamValMax(Float paramValMax) {
		this.paramValMax = paramValMax;
	}

	@Column(name = "paramvalmin")
	public Float getParamValMin() {
		return paramValMin;
	}

	public void setParamValMin(Float paramValMin) {
		this.paramValMin = paramValMin;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

}
