package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "qa_gasusage")
public class GasUsage extends ModelBase {

	String line;
	String LoadNumber;
	String ProcessCardNumber;
	Float AcetyleneUsage;
	Float NitrogenUsage;

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return LoadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		LoadNumber = loadNumber;
	}

	@Column(name = "processcardnumber")
	public String getProcessCardNumber() {
		return ProcessCardNumber;
	}

	public void setProcessCardNumber(String processCardNumber) {
		ProcessCardNumber = processCardNumber;
	}

	@Column(name = "acetyleneusage")
	public Float getAcetyleneUsage() {
		return AcetyleneUsage;
	}

	public void setAcetyleneUsage(Float acetyleneUsage) {
		AcetyleneUsage = acetyleneUsage;
	}

	@Column(name = "notrogenusage")
	public Float getNitrogenUsage() {
		return NitrogenUsage;
	}

	public void setNitrogenUsage(Float nitrogenUsage) {
		NitrogenUsage = nitrogenUsage;
	}

}
