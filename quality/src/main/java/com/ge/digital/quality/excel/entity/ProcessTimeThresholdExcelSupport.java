package com.ge.digital.quality.excel.entity;

import java.util.ArrayList;
import java.util.List;

import com.ge.digital.quality.entity.ProcessTimeThreshold;
import com.ge.digital.schedule.excelutil.ExcelSign;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;

public class ProcessTimeThresholdExcelSupport extends ProcessTimeThreshold implements ExcelUploadSupport{
	@ExcelSign(title = "partNumber", checkNull = true)
	String partNumber;
	@ExcelSign(title = "op30", checkNull = true)
	Long OP30;
	@ExcelSign(title = "op40", checkNull = true)
	Long OP40;
	
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
		String amkey = getPartNumber();
		return amkey;
	}


}
