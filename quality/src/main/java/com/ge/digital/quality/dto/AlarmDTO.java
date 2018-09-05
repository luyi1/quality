package com.ge.digital.quality.dto;

import java.util.Date;

public class AlarmDTO {
	String line;
	String station;
	String alarmCode;
	Date alarmTime;
	String alarmContentCn;
	Integer alarmLevel;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmContentCn() {
		return alarmContentCn;
	}

	public void setAlarmContentCn(String alarmContentCn) {
		this.alarmContentCn = alarmContentCn;
	}

	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

}
