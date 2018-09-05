package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "ma_processtimethreshold")
public class ProcessTimeThreshold extends ModelBase {

	String partNumber;

	Long OP30;

	Long OP40;

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "op30")
	public Long getOP30() {
		return OP30;
	}

	public void setOP30(Long oP30) {
		OP30 = oP30;
	}

	@Column(name = "op40")
	public Long getOP40() {
		return OP40;
	}

	public void setOP40(Long oP40) {
		OP40 = oP40;
	}

}
