package com.ge.digital.quality.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "udtecm_heattreamenttimedata", schema = "dbo")
public class WipHeatTreamentdatetimeData extends WipModelBase {

	String line;
	String loadnumber;
	String eventcode;
	String year;
	String month;
	String partnumber;
	String recipenumber;
	String heatingentrydate;
	String heatingroutestartdate;
	String heatingrouteenddate;
	String heatingexitdate;
	String heatingnumber;
	String icbpentrydate;
	String icbpexitdate;
	String icbpnumber;
	String lineentrydate;
	String lineexitdate;
	String preheatingentrydate;
	String preheatingexitdate;
	String preheatingroutestartdate;
	String preheatingrouteenddate;
	String preheatingnumber;
	String quenchingentrydate;
	String quenchingexitdate;
	String quenchingnumber;
	String temperingnumber;
	String temperingentrydate;
	String temperingexitdate;
	String washingentrydate;
	String washingexitdate;
	String washingnumber;
	String coollingentrydate;
	String coollingexitdate;
	String coollingnumber;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLoadnumber() {
		return loadnumber;
	}

	public void setLoadnumber(String loadnumber) {
		this.loadnumber = loadnumber;
	}

	public String getEventcode() {
		return eventcode;
	}

	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPartnumber() {
		return partnumber;
	}

	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}

	public String getRecipenumber() {
		return recipenumber;
	}

	public void setRecipenumber(String recipenumber) {
		this.recipenumber = recipenumber;
	}

	public String getHeatingentrydate() {
		return heatingentrydate;
	}

	public void setHeatingentrydate(String heatingentrydate) {
		this.heatingentrydate = heatingentrydate;
	}

	public String getHeatingroutestartdate() {
		return heatingroutestartdate;
	}

	public void setHeatingroutestartdate(String heatingroutestartdate) {
		this.heatingroutestartdate = heatingroutestartdate;
	}

	public String getHeatingrouteenddate() {
		return heatingrouteenddate;
	}

	public void setHeatingrouteenddate(String heatingrouteenddate) {
		this.heatingrouteenddate = heatingrouteenddate;
	}

	public String getHeatingexitdate() {
		return heatingexitdate;
	}

	public void setHeatingexitdate(String heatingexitdate) {
		this.heatingexitdate = heatingexitdate;
	}

	public String getHeatingnumber() {
		return heatingnumber;
	}

	public void setHeatingnumber(String heatingnumber) {
		this.heatingnumber = heatingnumber;
	}

	public String getIcbpentrydate() {
		return icbpentrydate;
	}

	public void setIcbpentrydate(String icbpentrydate) {
		this.icbpentrydate = icbpentrydate;
	}

	public String getIcbpexitdate() {
		return icbpexitdate;
	}

	public void setIcbpexitdate(String icbpexitdate) {
		this.icbpexitdate = icbpexitdate;
	}

	public String getLineentrydate() {
		return lineentrydate;
	}

	public void setLineentrydate(String lineentrydate) {
		this.lineentrydate = lineentrydate;
	}

	public String getLineexitdate() {
		return lineexitdate;
	}

	public void setLineexitdate(String lineexitdate) {
		this.lineexitdate = lineexitdate;
	}

	public String getPreheatingentrydate() {
		return preheatingentrydate;
	}

	public void setPreheatingentrydate(String preheatingentrydate) {
		this.preheatingentrydate = preheatingentrydate;
	}

	public String getPreheatingexitdate() {
		return preheatingexitdate;
	}

	public void setPreheatingexitdate(String preheatingexitdate) {
		this.preheatingexitdate = preheatingexitdate;
	}

	public String getPreheatingroutestartdate() {
		return preheatingroutestartdate;
	}

	public void setPreheatingroutestartdate(String preheatingroutestartdate) {
		this.preheatingroutestartdate = preheatingroutestartdate;
	}

	public String getPreheatingrouteenddate() {
		return preheatingrouteenddate;
	}

	public void setPreheatingrouteenddate(String preheatingrouteenddate) {
		this.preheatingrouteenddate = preheatingrouteenddate;
	}

	public String getPreheatingnumber() {
		return preheatingnumber;
	}

	public void setPreheatingnumber(String preheatingnumber) {
		this.preheatingnumber = preheatingnumber;
	}

	public String getQuenchingentrydate() {
		return quenchingentrydate;
	}

	public void setQuenchingentrydate(String quenchingentrydate) {
		this.quenchingentrydate = quenchingentrydate;
	}

	public String getQuenchingexitdate() {
		return quenchingexitdate;
	}

	public void setQuenchingexitdate(String quenchingexitdate) {
		this.quenchingexitdate = quenchingexitdate;
	}

	public String getQuenchingnumber() {
		return quenchingnumber;
	}

	public void setQuenchingnumber(String quenchingnumber) {
		this.quenchingnumber = quenchingnumber;
	}

	public String getTemperingnumber() {
		return temperingnumber;
	}

	public void setTemperingnumber(String temperingnumber) {
		this.temperingnumber = temperingnumber;
	}

	public String getTemperingentrydate() {
		return temperingentrydate;
	}

	public void setTemperingentrydate(String temperingentrydate) {
		this.temperingentrydate = temperingentrydate;
	}

	public String getTemperingexitdate() {
		return temperingexitdate;
	}

	public void setTemperingexitdate(String temperingexitdate) {
		this.temperingexitdate = temperingexitdate;
	}

	public String getWashingentrydate() {
		return washingentrydate;
	}

	public void setWashingentrydate(String washingentrydate) {
		this.washingentrydate = washingentrydate;
	}

	public String getWashingexitdate() {
		return washingexitdate;
	}

	public void setWashingexitdate(String washingexitdate) {
		this.washingexitdate = washingexitdate;
	}

	public String getWashingnumber() {
		return washingnumber;
	}

	public void setWashingnumber(String washingnumber) {
		this.washingnumber = washingnumber;
	}

	public String getCoollingentrydate() {
		return coollingentrydate;
	}

	public void setCoollingentrydate(String coollingentrydate) {
		this.coollingentrydate = coollingentrydate;
	}

	public String getCoollingexitdate() {
		return coollingexitdate;
	}

	public void setCoollingexitdate(String coollingexitdate) {
		this.coollingexitdate = coollingexitdate;
	}

	public String getCoollingnumber() {
		return coollingnumber;
	}

	public void setCoollingnumber(String coollingnumber) {
		this.coollingnumber = coollingnumber;
	}

}
