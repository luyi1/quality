package com.ge.digital.quality.excel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ge.digital.quality.entity.ProcessParamThreshold;
import com.ge.digital.schedule.excelutil.ExcelSign;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;

public class ProcessParameterThresholdExcelSupport extends ProcessParamThreshold implements ExcelUploadSupport{
	@ExcelSign(title = "partNumber", checkNull = true)
	String partNumber;
	@ExcelSign(title = "line", checkNull = true)
	String line;
	@ExcelSign(title = "item", checkNull = true)
	String item;
	@ExcelSign(title = "sequenceNo", checkNull = true)
	Integer sequenceNo;
	@ExcelSign(title = "paramRelativeTime", checkNull = true)
	Date paramRelativeTime;
	@ExcelSign(title = "paramValStandard", checkNull = true)
	Float paramValStandard;
	@ExcelSign(title = "paramValMax", checkNull = true)
	Float paramValMax;
	@ExcelSign(title = "paramValMin", checkNull = true)
	Float paramValMin;
	
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
		String amkey = getPartNumber()+"#"+getLine()+"#"+getItem()+"#"+getSequenceNo();
		return amkey;
	}


}
