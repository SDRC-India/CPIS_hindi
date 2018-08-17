package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class ChildInFitInstitutionModel {
	
	private Integer id;
	private String caseNo;
	private Date dateChildPlacedInFitInstitution;
	private String parentName;
	private String addressOfChild;
	private String childrenHomeSAAFacility;
	private ValueObject cci;
	private Integer periodForWhichSentToFitInstitution;
	private Date dateOfFormFilled;
	private String childId;
	private String childName;
	private Integer childAge;
	private String decisionType;
	private String cwcName;
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
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public Date getDateChildPlacedInFitInstitution() {
		return dateChildPlacedInFitInstitution;
	}
	public void setDateChildPlacedInFitInstitution(
			Date dateChildPlacedInFitInstitution) {
		this.dateChildPlacedInFitInstitution = dateChildPlacedInFitInstitution;
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
	public String getChildrenHomeSAAFacility() {
		return childrenHomeSAAFacility;
	}
	public void setChildrenHomeSAAFacility(String childrenHomeSAAFacility) {
		this.childrenHomeSAAFacility = childrenHomeSAAFacility;
	}
	public Integer getPeriodForWhichSentToFitInstitution() {
		return periodForWhichSentToFitInstitution;
	}
	public void setPeriodForWhichSentToFitInstitution(
			Integer periodForWhichSentToFitInstitution) {
		this.periodForWhichSentToFitInstitution = periodForWhichSentToFitInstitution;
	}
	public Date getDateOfFormFilled() {
		return dateOfFormFilled;
	}
	public void setDateOfFormFilled(Date dateOfFormFilled) {
		this.dateOfFormFilled = dateOfFormFilled;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
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
	public String getDecisionType() {
		return decisionType;
	}
	public void setDecisionType(String decisionType) {
		this.decisionType = decisionType;
	}
	public String getCwcName() {
		return cwcName;
	}
	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}
	public ValueObject getCci() {
		return cci;
	}
	public void setCci(ValueObject cci) {
		this.cci = cci;
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
