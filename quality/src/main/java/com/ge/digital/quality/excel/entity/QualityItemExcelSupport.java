package com.ge.digital.quality.excel.entity;

import java.util.ArrayList;
import java.util.List;

import com.ge.digital.quality.entity.QualityItem;
import com.ge.digital.schedule.excelutil.ExcelSign;
import com.ge.digital.schedule.excelutil.ExcelUploadSupport;


public class QualityItemExcelSupport extends QualityItem implements ExcelUploadSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@ExcelSign(title = "itemCode", checkNull = true)
	String itemCode;
	@ExcelSign(title = "itemName", checkNull = true)
	String itemName;
	@ExcelSign(title = "limsItemName", checkNull = true)
	String limsItemName;

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
		String amkey = getItemCode();
		return amkey;
	}

}
