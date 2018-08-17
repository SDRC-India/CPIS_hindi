package org.sdrc.cpis.models;

import java.sql.Date;

public class ChildMonitoringReportModel {

	private Integer id;
	private String childId;
	private boolean IndividualCarePlan;
	private String otherSourceOfReferral;
	private String photographOfChild;
	private String photographOfFcGiverOrParent;
	private String photographOfBiologicalParents;
	private String childDetails;
	private Integer placementType;
	private Date dateOfPlacement;
	private Integer periodOfPlacement;
	private String hsrOfBiologicalFamily;
	private String hsrOfFosterFamily;
	private String childStudyReport;
	private String recordEachVisit;
	private String recordOfAllReviewsAtchmnt;
	private String recordOfAllReviewsDtls;
	private Date dateTermination;
	private String reasonTermination;
	private boolean financialAssistanceProvided;
	private String financialAssistanceDtls;
	private String caseworkerName;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	
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
	public boolean isIndividualCarePlan() {
		return IndividualCarePlan;
	}
	public void setIndividualCarePlan(boolean individualCarePlan) {
		IndividualCarePlan = individualCarePlan;
	}
	public String getOtherSourceOfReferral() {
		return otherSourceOfReferral;
	}
	public void setOtherSourceOfReferral(String otherSourceOfReferral) {
		this.otherSourceOfReferral = otherSourceOfReferral;
	}
	public String getPhotographOfChild() {
		return photographOfChild;
	}
	public void setPhotographOfChild(String photographOfChild) {
		this.photographOfChild = photographOfChild;
	}
	public String getPhotographOfFcGiverOrParent() {
		return photographOfFcGiverOrParent;
	}
	public void setPhotographOfFcGiverOrParent(String photographOfFcGiverOrParent) {
		this.photographOfFcGiverOrParent = photographOfFcGiverOrParent;
	}
	public String getPhotographOfBiologicalParents() {
		return photographOfBiologicalParents;
	}
	public void setPhotographOfBiologicalParents(
			String photographOfBiologicalParents) {
		this.photographOfBiologicalParents = photographOfBiologicalParents;
	}
	public String getChildDetails() {
		return childDetails;
	}
	public void setChildDetails(String childDetails) {
		this.childDetails = childDetails;
	}
	public Integer getPlacementType() {
		return placementType;
	}
	public void setPlacementType(Integer placementType) {
		this.placementType = placementType;
	}
	public Date getDateOfPlacement() {
		return dateOfPlacement;
	}
	public void setDateOfPlacement(Date dateOfPlacement) {
		this.dateOfPlacement = dateOfPlacement;
	}
	public Integer getPeriodOfPlacement() {
		return periodOfPlacement;
	}
	public void setPeriodOfPlacement(Integer periodOfPlacement) {
		this.periodOfPlacement = periodOfPlacement;
	}
	public String getHsrOfBiologicalFamily() {
		return hsrOfBiologicalFamily;
	}
	public void setHsrOfBiologicalFamily(String hsrOfBiologicalFamily) {
		this.hsrOfBiologicalFamily = hsrOfBiologicalFamily;
	}
	public String getHsrOfFosterFamily() {
		return hsrOfFosterFamily;
	}
	public void setHsrOfFosterFamily(String hsrOfFosterFamily) {
		this.hsrOfFosterFamily = hsrOfFosterFamily;
	}
	public String getChildStudyReport() {
		return childStudyReport;
	}
	public void setChildStudyReport(String childStudyReport) {
		this.childStudyReport = childStudyReport;
	}
	public String getRecordEachVisit() {
		return recordEachVisit;
	}
	public void setRecordEachVisit(String recordEachVisit) {
		this.recordEachVisit = recordEachVisit;
	}
	public String getRecordOfAllReviewsAtchmnt() {
		return recordOfAllReviewsAtchmnt;
	}
	public void setRecordOfAllReviewsAtchmnt(String recordOfAllReviewsAtchmnt) {
		this.recordOfAllReviewsAtchmnt = recordOfAllReviewsAtchmnt;
	}
	public String getRecordOfAllReviewsDtls() {
		return recordOfAllReviewsDtls;
	}
	public void setRecordOfAllReviewsDtls(String recordOfAllReviewsDtls) {
		this.recordOfAllReviewsDtls = recordOfAllReviewsDtls;
	}
	public Date getDateTermination() {
		return dateTermination;
	}
	public void setDateTermination(Date dateTermination) {
		this.dateTermination = dateTermination;
	}
	public String getReasonTermination() {
		return reasonTermination;
	}
	public void setReasonTermination(String reasonTermination) {
		this.reasonTermination = reasonTermination;
	}
	public boolean isFinancialAssistanceProvided() {
		return financialAssistanceProvided;
	}
	public void setFinancialAssistanceProvided(boolean financialAssistanceProvided) {
		this.financialAssistanceProvided = financialAssistanceProvided;
	}
	public String getFinancialAssistanceDtls() {
		return financialAssistanceDtls;
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
	public void setFinancialAssistanceDtls(String financialAssistanceDtls) {
		this.financialAssistanceDtls = financialAssistanceDtls;
	}
	public String getCaseworkerName() {
		return caseworkerName;
	}
	public void setCaseworkerName(String caseworkerName) {
		this.caseworkerName = caseworkerName;
	}

}
