package com.ge.digital.quality.vo;

public class AlarmVO {

	String alarmCode;
	String alarmContentCn;
	String alarmLevel;

	public AlarmVO() {
		// TODO Auto-generated constructor stub
	}

	public AlarmVO(String alarmCode, String alarmContentCn, String alarmLevel) {
		// TODO Auto-generated constructor stub
		switch (alarmLevel) {
		case "1":
			this.alarmLevel = "一般";
			break;
		case "2":
			this.alarmLevel = "严重";
			break;
		case "3":
			this.alarmLevel = "致命";
			break;

		default:
			this.alarmLevel = "未知";
			break;
		}
		this.alarmCode = alarmCode;
		this.alarmContentCn = alarmContentCn;
	}

	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

	public String getAlarmContentCn() {
		return alarmContentCn;
	}

	public void setAlarmContentCn(String alarmContentCn) {
		this.alarmContentCn = alarmContentCn;
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

}
