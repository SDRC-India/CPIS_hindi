package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;


public class CICLChildCareInstitutionPendingInquiryModel {

	private Integer id;	
	private String childId;
	private Date dateOfPlacement;
	private String parentName;
	private String addressOfChild;
	private Integer duration;
	private Date nextDateOfHearing;
	private Date dateOfOrder;
	private Integer formNo;
    private String childName;
    private String age;
	private String firNumber;
	private String ddNumber;
	private String policeStation;
	private ValueObject cciObject;
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
	public java.sql.Date getDateOfPlacement() {
		return dateOfPlacement;
	}
	public void setDateOfPlacement(java.sql.Date dateOfPlacement) {
		this.dateOfPlacement = dateOfPlacement;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getAddressOfChild() {
		return addressOfChild;
	}
	public void setAddressOfChild(String addressOfChild) {
		this.addressOfChild = addressOfChild;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Date getNextDateOfHearing() {
		return nextDateOfHearing;
	}
	public void setNextDateOfHearing(Date nextDateOfHearing) {
		this.nextDateOfHearing = nextDateOfHearing;
	}
	public java.sql.Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(java.sql.Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public Integer getFormNo() {
		return formNo;
	}
	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFirNumber() {
		return firNumber;
	}
	public void setFirNumber(String firNumber) {
		this.firNumber = firNumber;
	}
	public String getDdNumber() {
		return ddNumber;
	}
	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public ValueObject getCciObject() {
		return cciObject;
	}
	public void setCciObject(ValueObject cciObject) {
		this.cciObject = cciObject;
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
	
}
