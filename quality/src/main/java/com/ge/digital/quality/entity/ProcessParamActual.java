package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "qa_processparamactual")
public class ProcessParamActual extends ModelBase {

	String line;

	String loadNumber;

	String item;

	String specificationVal;

	String actualVal;

	@Column(name = "item")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "specificationval")
	public String getSpecificationVal() {
		return specificationVal;
	}

	public void setSpecificationVal(String specificationVal) {
		this.specificationVal = specificationVal;
	}

	@Column(name = "actualval")
	public String getActualVal() {
		return actualVal;
	}

	public void setActualVal(String actualVal) {
		this.actualVal = actualVal;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

}
