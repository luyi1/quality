package com.ge.digital.quality.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

public class ProductionProc {
	@Id
	private String loadNumber;
	private String recipeNumber;
	private String partNumber;
	private Integer year;
	private Integer month;
	private Integer line;
	private Integer washingNumber;
	private Integer preHeatingNumber;
	private Integer heatingNumber;
	private Integer temperingNumber;
	private Date lineEntryDate;
	private Date washingEntryDate;
	private Date washingExitDate;
	private Date preHeatingEntryDate;
	private Date preHeatingExitDate;
	private Date iCBPEntryDate;
	private Date heatingEntryDate;
	private Date heatingExitDate;
	private Date quenchingEntryDate;
	private Date quenchingExitDate;
	private Date iCBPExitDate;
	private Date temperingEntryDate;
	private Date temperingExitDate;
	private Date lineExitDate;
	private Integer eventCode;

	public String getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(String localNumber) {
		this.loadNumber = localNumber;
	}

	public String getRecipeNumber() {
		return recipeNumber;
	}

	public void setRecipeNumber(String recipeNumber) {
		this.recipeNumber = recipeNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public Integer getWashingNumber() {
		return washingNumber;
	}

	public void setWashingNumber(Integer washingNumber) {
		this.washingNumber = washingNumber;
	}

	public Integer getPreHeatingNumber() {
		return preHeatingNumber;
	}

	public void setPreHeatingNumber(Integer preHeatingNumber) {
		this.preHeatingNumber = preHeatingNumber;
	}

	public Integer getHeatingNumber() {
		return heatingNumber;
	}

	public void setHeatingNumber(Integer heatingNumber) {
		this.heatingNumber = heatingNumber;
	}

	public Integer getTemperingNumber() {
		return temperingNumber;
	}

	public void setTemperingNumber(Integer temperingNumber) {
		this.temperingNumber = temperingNumber;
	}

	public Date getLineEntryDate() {
		return lineEntryDate;
	}

	public void setLineEntryDate(Date lineEntryDate) {
		this.lineEntryDate = lineEntryDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getWashingEntryDate() {
		return washingEntryDate;
	}

	public void setWashingEntryDate(Date washingEntryDate) {
		this.washingEntryDate = washingEntryDate;
	}

	public Date getWashingExitDate() {
		return washingExitDate;
	}

	public void setWashingExitDate(Date washingExitDate) {
		this.washingExitDate = washingExitDate;
	}

	public Date getPreHeatingEntryDate() {
		return preHeatingEntryDate;
	}

	public void setPreHeatingEntryDate(Date preHeatingEntryDate) {
		this.preHeatingEntryDate = preHeatingEntryDate;
	}

	public Date getPreHeatingExitDate() {
		return preHeatingExitDate;
	}

	public void setPreHeatingExitDate(Date preHeatingExitDate) {
		this.preHeatingExitDate = preHeatingExitDate;
	}

	public Date getiCBPEntryDate() {
		return iCBPEntryDate;
	}

	public void setiCBPEntryDate(Date iCBPEntryDate) {
		this.iCBPEntryDate = iCBPEntryDate;
	}

	public Date getHeatingEntryDate() {
		return heatingEntryDate;
	}

	public void setHeatingEntryDate(Date heatingEntryDate) {
		this.heatingEntryDate = heatingEntryDate;
	}

	public Date getHeatingExitDate() {
		return heatingExitDate;
	}

	public void setHeatingExitDate(Date heatingExitDate) {
		this.heatingExitDate = heatingExitDate;
	}

	public Date getQuenchingEntryDate() {
		return quenchingEntryDate;
	}

	public void setQuenchingEntryDate(Date quenchingEntryDate) {
		this.quenchingEntryDate = quenchingEntryDate;
	}

	public Date getQuenchingExitDate() {
		return quenchingExitDate;
	}

	public void setQuenchingExitDate(Date quenchingExitDate) {
		this.quenchingExitDate = quenchingExitDate;
	}

	public Date getiCBPExitDate() {
		return iCBPExitDate;
	}

	public void setiCBPExitDate(Date iCBPExitDate) {
		this.iCBPExitDate = iCBPExitDate;
	}

	public Date getTemperingEntryDate() {
		return temperingEntryDate;
	}

	public void setTemperingEntryDate(Date temperingEntryDate) {
		this.temperingEntryDate = temperingEntryDate;
	}

	public Date getTemperingExitDate() {
		return temperingExitDate;
	}

	public void setTemperingExitDate(Date temperingExitDate) {
		this.temperingExitDate = temperingExitDate;
	}

	public Date getLineExitDate() {
		return lineExitDate;
	}

	public void setLineExitDate(Date lineExitDate) {
		this.lineExitDate = lineExitDate;
	}

	public Integer getEventCode() {
		return eventCode;
	}

	public void setEventCode(Integer eventCode) {
		this.eventCode = eventCode;
	}

}
