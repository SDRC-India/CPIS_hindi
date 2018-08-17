package org.sdrc.cpis.models;

import java.sql.Date;

public class FosterCareDetailsModel {

	private Integer id;
	private String childId;
	private String parentName1;
	private String parentName2;
	private String fosterParentName1;
	private String fosterParentName2;
	private String fosterParentAddress;
	private String fosterParentContact;
	private Integer durationOfStayAtFosterCare;
	private String cciName;
	private String cciAddress;
	private String fosterCareWorkerName;
	private String fosterCareWorkerAddress;
	private String fosterType;
	private String childName;
	private Integer childAge;
	private String childAddress;
	private String decisionType;
	private Date dateOfFormFilled;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
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
	public String getParentName1() {
		return parentName1;
	}
	public void setParentName1(String parentName1) {
		this.parentName1 = parentName1;
	}
	public String getParentName2() {
		return parentName2;
	}
	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}
	public String getFosterParentName1() {
		return fosterParentName1;
	}
	public void setFosterParentName1(String fosterParentName1) {
		this.fosterParentName1 = fosterParentName1;
	}
	public String getFosterParentName2() {
		return fosterParentName2;
	}
	public void setFosterParentName2(String fosterParentName2) {
		this.fosterParentName2 = fosterParentName2;
	}
	public String getFosterParentAddress() {
		return fosterParentAddress;
	}
	public void setFosterParentAddress(String fosterParentAddress) {
		this.fosterParentAddress = fosterParentAddress;
	}
	public String getFosterParentContact() {
		return fosterParentContact;
	}
	public void setFosterParentContact(String fosterParentContact) {
		this.fosterParentContact = fosterParentContact;
	}
	public Integer getDurationOfStayAtFosterCare() {
		return durationOfStayAtFosterCare;
	}
	public void setDurationOfStayAtFosterCare(Integer durationOfStayAtFosterCare) {
		this.durationOfStayAtFosterCare = durationOfStayAtFosterCare;
	}
	public String getCciName() {
		return cciName;
	}
	public void setCciName(String cciName) {
		this.cciName = cciName;
	}
	public String getCciAddress() {
		return cciAddress;
	}
	public void setCciAddress(String cciAddress) {
		this.cciAddress = cciAddress;
	}
	public String getFosterCareWorkerName() {
		return fosterCareWorkerName;
	}
	public void setFosterCareWorkerName(String fosterCareWorkerName) {
		this.fosterCareWorkerName = fosterCareWorkerName;
	}
	public String getFosterCareWorkerAddress() {
		return fosterCareWorkerAddress;
	}
	public void setFosterCareWorkerAddress(String fosterCareWorkerAddress) {
		this.fosterCareWorkerAddress = fosterCareWorkerAddress;
	}
	public String getFosterType() {
		return fosterType;
	}
	public void setFosterType(String fosterType) {
		this.fosterType = fosterType;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Integer getChildAge() {
		return childAge;
	}
	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}
	public String getChildAddress() {
		return childAddress;
	}
	public void setChildAddress(String childAddress) {
		this.childAddress = childAddress;
	}
	public String getDecisionType() {
		return decisionType;
	}
	public void setDecisionType(String decisionType) {
		this.decisionType = decisionType;
	}
	public Date getDateOfFormFilled() {
		return dateOfFormFilled;
	}
	public void setDateOfFormFilled(Date dateOfFormFilled) {
		this.dateOfFormFilled = dateOfFormFilled;
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
	
}
