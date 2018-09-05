package com.ge.digital.quality.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "qa_alarmrecord")
public class AlarmRecord extends ModelBase {

	String line;

	String station;

	String alarmCode;

	Date alarmTime;

	String loadNumber;

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "station")
	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Column(name = "alarmcode")
	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

	@Column(name = "alarmtime")
	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

}
