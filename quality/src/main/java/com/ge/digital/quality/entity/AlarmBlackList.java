package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "ma_alarmblacklist")
public class AlarmBlackList extends ModelBase {

	String line;

	String alarmCode;

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "alarmcode")
	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

}
