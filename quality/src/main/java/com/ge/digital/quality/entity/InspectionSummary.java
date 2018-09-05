package com.ge.digital.quality.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Entity
@Table(name = "qa_inspectionsummary")
public class InspectionSummary extends ModelBase {

	String inspectionNo;

	String batchNumber;

	String line;

	String partNumber;

	Date predictionTime;

	String predictionResult;

	String predictionRemarks;

	String unPredictableReasonCode;

	String predictionModelNo;

	Date inspectionTime;

	String inspectionResult;

	String inspectionRemarks;

	String loadNumber;

	String processCardNumber;

	String order;

	String inspectionStaff;

	String confirmStaff;

	Date confirmTime;

	String confirmRemarks;

	@Column(name = "inspectionno")
	public String getInspectionNo() {
		return inspectionNo;
	}

	public void setInspectionNo(String inspectionNo) {
		this.inspectionNo = inspectionNo;
	}

	@Column(name = "batchnumber")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "predictiontime")
	public Date getPredictionTime() {
		return predictionTime;
	}

	public void setPredictionTime(Date predictionTime) {
		this.predictionTime = predictionTime;
	}

	@Column(name = "predictionresult")
	public String getPredictionResult() {
		return predictionResult;
	}

	public void setPredictionResult(String predictionResult) {
		this.predictionResult = predictionResult;
	}

	@Column(name = "predictionremarks")
	public String getPredictionRemarks() {
		return predictionRemarks;
	}

	public void setPredictionRemarks(String predictionRemarks) {
		this.predictionRemarks = predictionRemarks;
	}

	@Column(name = "unpredictablereasoncode")
	public String getUnPredictableReasonCode() {
		return unPredictableReasonCode;
	}

	public void setUnPredictableReasonCode(String unPredictableReasonCode) {
		this.unPredictableReasonCode = unPredictableReasonCode;
	}

	@Column(name = "predictionmodelno")
	public String getPredictionModelNo() {
		return predictionModelNo;
	}

	public void setPredictionModelNo(String predictionModelNo) {
		this.predictionModelNo = predictionModelNo;
	}

	@Column(name = "inspectiontime")
	public Date getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}

	@Column(name = "inspectionresult")
	public String getInspectionResult() {
		return inspectionResult;
	}

	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}

	@Column(name = "inspectionremarks")
	public String getInspectionRemarks() {
		return inspectionRemarks;
	}

	public void setInspectionRemarks(String inspectionRemarks) {
		this.inspectionRemarks = inspectionRemarks;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

	@Column(name = "processcardnumber")
	public String getProcessCardNumber() {
		return processCardNumber;
	}

	public void setProcessCardNumber(String processCardNumber) {
		this.processCardNumber = processCardNumber;
	}

	@Column(name = "[order]")
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Column(name = "inspectionstaff")
	public String getInspectionStaff() {
		return inspectionStaff;
	}

	public void setInspectionStaff(String inspectionStaff) {
		this.inspectionStaff = inspectionStaff;
	}

	@Column(name = "confirmstaff")
	public String getConfirmStaff() {
		return confirmStaff;
	}

	public void setConfirmStaff(String confirmStaff) {
		this.confirmStaff = confirmStaff;
	}

	@Column(name = "confirmtime")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	@Column(name = "confirmremarks")
	public String getConfirmRemarks() {
		return confirmRemarks;
	}

	public void setConfirmRemarks(String confirmRemarks) {
		this.confirmRemarks = confirmRemarks;
	}

}
