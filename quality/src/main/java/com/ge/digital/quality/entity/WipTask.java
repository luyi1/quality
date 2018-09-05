package com.ge.digital.quality.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "udtwip_task", schema = "dbo")
public class WipTask extends WipModelBase {
	String taskNo;
	String taskType;
	String taskStatus;
	String batchNumber;
	String sysCode;
	String line;
	Date planProdDate;
	Date planOfflineDate;
	Date taskStartDate;
	Date taskEndDate;
	String materialCode;
	String partNumber;
	String partName;
	Integer quantity;
	String agvCode;
	Integer processCardId;
	String processCardNumber;
	String loadNumber;
	String taskLock;
	String partStatus;
	String isCurExecTask;
	String execStatus;

	@Column(name = "taskno")
	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Column(name = "tasktype")
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Column(name = "taskstatus")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name = "batchnumber")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name = "syscode")
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "planproddate")
	public Date getPlanProdDate() {
		return planProdDate;
	}

	public void setPlanProdDate(Date planProdDate) {
		this.planProdDate = planProdDate;
	}

	@Column(name = "planofflinedate")
	public Date getPlanOfflineDate() {
		return planOfflineDate;
	}

	public void setPlanOfflineDate(Date planOfflineDate) {
		this.planOfflineDate = planOfflineDate;
	}

	@Column(name = "taskstartdate")
	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	@Column(name = "taskenddate")
	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	@Column(name = "materialcode")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "partname")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "agvcode")
	public String getAgvCode() {
		return agvCode;
	}

	public void setAgvCode(String agvCode) {
		this.agvCode = agvCode;
	}

	@Column(name = "processcardid")
	public Integer getProcessCardId() {
		return processCardId;
	}

	public void setProcessCardId(Integer processCardId) {
		this.processCardId = processCardId;
	}

	@Column(name = "processcardnumber")
	public String getProcessCardNumber() {
		return processCardNumber;
	}

	public void setProcessCardNumber(String processCardNumber) {
		this.processCardNumber = processCardNumber;
	}

	@Column(name = "loadnumber")
	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String loadNumber) {
		this.loadNumber = loadNumber;
	}

	@Column(name = "tasklock")
	public String getTaskLock() {
		return taskLock;
	}

	public void setTaskLock(String taskLock) {
		this.taskLock = taskLock;
	}

	@Column(name = "partstatus")
	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	@Column(name = "iscurexectask")
	public String getIsCurExecTask() {
		return isCurExecTask;
	}

	public void setIsCurExecTask(String isCurExecTask) {
		this.isCurExecTask = isCurExecTask;
	}

	@Column(name = "execstatus")
	public String getExecStatus() {
		return execStatus;
	}

	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus;
	}

}
