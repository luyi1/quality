package com.ge.digital.quality.excel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.quality.entity.AlarmMessage;
import com.ge.digital.schedule.excelutil.ExcelSign;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;


public class AlarmMessageExcelSupport extends AlarmMessage implements ExcelUploadSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExcelSign(title = "alarmContentEng", checkNull = true)
	String alarmContentEng;

	@ExcelSign(title = "alarmContentCn", checkNull = true)
	String alarmContentCn;

	@ExcelSign(title = "alarmLevel", checkNull = true)
	String alarmLevel;

	@ExcelSign(title = "alarmOrigin", checkNull = true)
	String alarmOrigin;

	@ExcelSign(title = "line", checkNull = true)
	String line;

	@ExcelSign(title = "alarmCode", checkNull = true)
	String alarmCode;

	private long batchUploadID;

	public long getBatchUploadID() {
		return batchUploadID;
	}

	public void setBatchUploadID(long batchUploadID) {
		this.batchUploadID = batchUploadID;
	}

	public int uploadStatus = 1;
	List<String> errors = new ArrayList<>();

	public int getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void addError(String error) {
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String getCombinedKey() {
		String amkey = getLine()+"#"+getAlarmCode();
		return amkey;
	}

}
