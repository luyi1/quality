package com.ge.digital.quality.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ge.digital.schedule.entity.ModelBase;

@Table(name = "udvma_allmaterialproperywithcode_v", schema = "dbo")
@Entity
public class View {

	String materialCode;

	String classification;

	String direction;

	String directionHeating;

	String heatingInCode1;

	String heatingInCode2;

	String heatingOutCode1;

	String heatingOutCode2;

	String materailName;

	String materialDeac;

	String partNumber;

	String source;

	String clamping;

	String gearModulus;

	String line;

	Integer loadQuantity;

	String material;

	String size;

	String surfaceArea;

	String volume;

	String weight;

	String ECM_LINE1;

	String ECM_LINE2;

	String ECM_LINE3;

	String ECM_LINE5;

	String ECM_LINE4;

	@Id
	@Column(name = "materialcode")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Column(name = "classification")
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Column(name = "direction")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Column(name = "directionheating")
	public String getDirectionHeating() {
		return directionHeating;
	}

	public void setDirectionHeating(String directionHeating) {
		this.directionHeating = directionHeating;
	}

	@Column(name = "heatingincode1")
	public String getHeatingInCode1() {
		return heatingInCode1;
	}

	public void setHeatingInCode1(String heatingInCode1) {
		this.heatingInCode1 = heatingInCode1;
	}

	@Column(name = "heatingincode2")
	public String getHeatingInCode2() {
		return heatingInCode2;
	}

	public void setHeatingInCode2(String heatingInCode2) {
		this.heatingInCode2 = heatingInCode2;
	}

	@Column(name = "heatingoutcode1")
	public String getHeatingOutCode1() {
		return heatingOutCode1;
	}

	public void setHeatingOutCode1(String heatingOutCode1) {
		this.heatingOutCode1 = heatingOutCode1;
	}

	@Column(name = "heatingoutcode2")
	public String getHeatingOutCode2() {
		return heatingOutCode2;
	}

	public void setHeatingOutCode2(String heatingOutCode2) {
		this.heatingOutCode2 = heatingOutCode2;
	}

	@Column(name = "materailname")
	public String getMaterailName() {
		return materailName;
	}

	public void setMaterailName(String materailName) {
		this.materailName = materailName;
	}

	@Column(name = "materialdeac")
	public String getMaterialDeac() {
		return materialDeac;
	}

	public void setMaterialDeac(String materialDeac) {
		this.materialDeac = materialDeac;
	}

	@Column(name = "partnumber")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "clamping")
	public String getClamping() {
		return clamping;
	}

	public void setClamping(String clamping) {
		this.clamping = clamping;
	}

	@Column(name = "gearmodulus")
	public String getGearModulus() {
		return gearModulus;
	}

	public void setGearModulus(String gearModulus) {
		this.gearModulus = gearModulus;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "loadquantity")
	public Integer getLoadQuantity() {
		return loadQuantity;
	}

	public void setLoadQuantity(Integer loadQuantity) {
		this.loadQuantity = loadQuantity;
	}

	@Column(name = "material")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "surfacearea")
	public String getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(String surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	@Column(name = "volume")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "weight")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "ecm_line1")
	public String getECM_LINE1() {
		return ECM_LINE1;
	}

	public void setECM_LINE1(String eCM_LINE1) {
		ECM_LINE1 = eCM_LINE1;
	}

	@Column(name = "ecm_line2")
	public String getECM_LINE2() {
		return ECM_LINE2;
	}

	public void setECM_LINE2(String eCM_LINE2) {
		ECM_LINE2 = eCM_LINE2;
	}

	@Column(name = "ecm_line3")
	public String getECM_LINE3() {
		return ECM_LINE3;
	}

	public void setECM_LINE3(String eCM_LINE3) {
		ECM_LINE3 = eCM_LINE3;
	}

	@Column(name = "ecm_line5")
	public String getECM_LINE5() {
		return ECM_LINE5;
	}

	public void setECM_LINE5(String eCM_LINE5) {
		ECM_LINE5 = eCM_LINE5;
	}

	@Column(name = "ecm_line4")
	public String getECM_LINE4() {
		return ECM_LINE4;
	}

	public void setECM_LINE4(String eCM_LINE4) {
		ECM_LINE4 = eCM_LINE4;
	}

}
