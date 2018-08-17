package org.sdrc.cpis.models;

import java.sql.Date;

public class AfterCarePlacementOrderModel {

	private Integer id;
	private String childId;
	private Date completing18On;
	private String purposeOfRehabilitation;
	private String organizationName;
	private Integer amountReleased;
	private Integer period;
	private String nameOfAccountHolder;
	private String printDate;
	private String childName;
	private String sirFatherName;
	private String sirMotherName;
	private Integer programType;
	
	
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public Date getCompleting18On() {
		return completing18On;
	}
	public void setCompleting18On(Date completing18On) {
		this.completing18On = completing18On;
	}
	public String getPurposeOfRehabilitation() {
		return purposeOfRehabilitation;
	}
	public void setPurposeOfRehabilitation(String purposeOfRehabilitation) {
		this.purposeOfRehabilitation = purposeOfRehabilitation;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Integer getAmountReleased() {
		return amountReleased;
	}
	public void setAmountReleased(Integer amountReleased) {
		this.amountReleased = amountReleased;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getNameOfAccountHolder() {
		return nameOfAccountHolder;
	}
	public void setNameOfAccountHolder(String nameOfAccountHolder) {
		this.nameOfAccountHolder = nameOfAccountHolder;
	}
	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getSirFatherName() {
		return sirFatherName;
	}
	public void setSirFatherName(String sirFatherName) {
		this.sirFatherName = sirFatherName;
	}
	public String getSirMotherName() {
		return sirMotherName;
	}
	public void setSirMotherName(String sirMotherName) {
		this.sirMotherName = sirMotherName;
	}
	
}
