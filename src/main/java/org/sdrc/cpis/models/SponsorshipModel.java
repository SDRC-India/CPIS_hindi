package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class SponsorshipModel {

	private Integer id;
	
	private String childId;
	
	private ValueObject sponsorshipFor;
	
	private String otherSponsorshipFor;
	
	private Integer sponsorshipTime;
	
	private Integer sponsorshipAmount;
	
	private Integer sponsorshipAmountOneTime;
	
	private Integer sponsorshipPeriod;
	
	private String typeOfSponsorship;
	
	private String daysOrMonths;
	
	private String accountName;
	
	private String operatedBy;
	
	private String fatherName;
	
	private String motherName;
	
	private String childName;
	private String sirAddress;
	private Integer age;
	private String printDate;

	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private Integer programType;

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

	public ValueObject getSponsorshipFor() {
		return sponsorshipFor;
	}

	public void setSponsorshipFor(ValueObject sponsorshipFor) {
		this.sponsorshipFor = sponsorshipFor;
	}

	public Integer getSponsorshipTime() {
		return sponsorshipTime;
	}

	public void setSponsorshipTime(Integer sponsorshipTime) {
		this.sponsorshipTime = sponsorshipTime;
	}

	public Integer getSponsorshipAmount() {
		return sponsorshipAmount;
	}

	public void setSponsorshipAmount(Integer sponsorshipAmount) {
		this.sponsorshipAmount = sponsorshipAmount;
	}

	public Integer getSponsorshipPeriod() {
		return sponsorshipPeriod;
	}

	public void setSponsorshipPeriod(Integer sponsorshipPeriod) {
		this.sponsorshipPeriod = sponsorshipPeriod;
	}

	public String getDaysOrMonths() {
		return daysOrMonths;
	}

	public void setDaysOrMonths(String daysOrMonths) {
		this.daysOrMonths = daysOrMonths;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOperatedBy() {
		return operatedBy;
	}

	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getTypeOfSponsorship() {
		return typeOfSponsorship;
	}

	public void setTypeOfSponsorship(String typeOfSponsorship) {
		this.typeOfSponsorship = typeOfSponsorship;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getSirAddress() {
		return sirAddress;
	}

	public void setSirAddress(String sirAddress) {
		this.sirAddress = sirAddress;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCurrentDate() {
		return printDate;
	}

	public void setCurrentDate(String printDate) {
		this.printDate = printDate;
	}

	public String getOtherSponsorshipFor() {
		return otherSponsorshipFor;
	}

	public void setOtherSponsorshipFor(String otherSponsorshipFor) {
		this.otherSponsorshipFor = otherSponsorshipFor;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	public Integer getProgramType() {
		return programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	public Integer getSponsorshipAmountOneTime() {
		return sponsorshipAmountOneTime;
	}

	public void setSponsorshipAmountOneTime(Integer sponsorshipAmountOneTime) {
		this.sponsorshipAmountOneTime = sponsorshipAmountOneTime;
	}
}
