package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "ma_alarmmessage")
public class AlarmMessage extends ModelBase {

	String alarmContentEng;

	String alarmContentCn;

	String alarmLevel;

	String alarmOrigin;

	String line;

	String alarmCode;

	@Column(name = "alarmcode")
	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

	@Column(name = "alarmlevel")
	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	@Column(name = "alarmorigin")
	public String getAlarmOrigin() {
		return alarmOrigin;
	}

	public void setAlarmOrigin(String alarmOrigin) {
		this.alarmOrigin = alarmOrigin;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "alarmcontenteng")
	public String getAlarmContentEng() {
		return alarmContentEng;
	}

	public void setAlarmContentEng(String alarmContentEng) {
		this.alarmContentEng = alarmContentEng;
	}

	@Column(name = "alarmcontentcn")
	public String getAlarmContentCn() {
		return alarmContentCn;
	}

	public void setAlarmContentCn(String alarmContentCn) {
		this.alarmContentCn = alarmContentCn;
	}

}
