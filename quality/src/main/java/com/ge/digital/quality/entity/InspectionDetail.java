package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "qa_inspectiondetail")
public class InspectionDetail extends ModelBase {

	String inspectionNo;

	String itemCode;

	Float itemPredictionVal;

	String itemPredictionRemarks;

	String itemPredictionResult;

	String itemUnPredictableReasonCode;

	Float itemInspectionVal;

	String itemInspectionResult;

	Float itemNdtVal;

	String itemNdtResult;

	@Column(name = "inspectionno")
	public String getInspectionNo() {
		return inspectionNo;
	}

	public void setInspectionNo(String inspectionNo) {
		this.inspectionNo = inspectionNo;
	}

	@Column(name = "itemcode")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "itempredictionval")
	public Float getItemPredictionVal() {
		return itemPredictionVal;
	}

	public void setItemPredictionVal(Float itemPredictionVal) {
		this.itemPredictionVal = itemPredictionVal;
	}

	@Column(name = "itempredictionremarks")
	public String getItemPredictionRemarks() {
		return itemPredictionRemarks;
	}

	public void setItemPredictionRemarks(String itemPredictionRemarks) {
		this.itemPredictionRemarks = itemPredictionRemarks;
	}

	@Column(name = "itemunpredictablereasoncode")
	public String getItemUnPredictableReasonCode() {
		return itemUnPredictableReasonCode;
	}

	public void setItemUnPredictableReasonCode(String itemUnPredictableReasonCode) {
		this.itemUnPredictableReasonCode = itemUnPredictableReasonCode;
	}

	@Column(name = "iteminspectionval")
	public Float getItemInspectionVal() {
		return itemInspectionVal;
	}

	public void setItemInspectionVal(Float itemInspectionVal) {
		this.itemInspectionVal = itemInspectionVal;
	}

	@Column(name = "iteminspectionresult")
	public String getItemInspectionResult() {
		return itemInspectionResult;
	}

	public void setItemInspectionResult(String itemInspectionResult) {
		this.itemInspectionResult = itemInspectionResult;
	}

	@Column(name = "itemndtval")
	public Float getItemNdtVal() {
		return itemNdtVal;
	}

	public void setItemNdtVal(Float itemNdtVal) {
		this.itemNdtVal = itemNdtVal;
	}

	@Column(name = "itemndtresult")
	public String getItemNdtResult() {
		return itemNdtResult;
	}

	public void setItemNdtResult(String itemNdtResult) {
		this.itemNdtResult = itemNdtResult;
	}

	@Column(name = "itempredictionresult")
	public String getItemPredictionResult() {
		return itemPredictionResult;
	}

	public void setItemPredictionResult(String itemPredictionResult) {
		this.itemPredictionResult = itemPredictionResult;
	}

}
