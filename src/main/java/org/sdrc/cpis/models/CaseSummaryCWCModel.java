package org.sdrc.cpis.models;

import java.sql.Date;
/**
 * @author Pratyush
 */
public class CaseSummaryCWCModel {
	
	private Integer id;
	private String childId;
	private String caseNo;
	private String re;
	private String caseRecord;
	private String ordersPassedByCWC;
	private String ordersPassedByCwcOthers;
	private String medicalRecords;
	private boolean socialInvestigationReport;
	private boolean individualCarePlan;
	private boolean rehabitationCard;
	private boolean caseHistory;
	private boolean allDetails;
	private Date dateOfFormFilled;
	private String placeOfFormFilled;
	private Date printDate;
	private String childName;
	private Date todayDate;
	private	String parentOrGuardianName;
	private	String personName;
	private Date dateOfProduction;
	private	String interimOrderDates;
	
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private String ordersPassedByCWCName;
	private String medicalReports;
	
	private Integer programType;
	
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getRe() {
		return re;
	}
	public void setRe(String re) {
		this.re = re;
	}
	public String getCaseRecord() {
		return caseRecord;
	}
	public void setCaseRecord(String caseRecord) {
		this.caseRecord = caseRecord;
	}
	public String getOrdersPassedByCWC() {
		return ordersPassedByCWC;
	}
	public void setOrdersPassedByCWC(String ordersPassedByCWC) {
		this.ordersPassedByCWC = ordersPassedByCWC;
	}
	public String getMedicalRecords() {
		return medicalRecords;
	}
	public void setMedicalRecords(String medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	public boolean isSocialInvestigationReport() {
		return socialInvestigationReport;
	}
	public void setSocialInvestigationReport(boolean socialInvestigationReport) {
		this.socialInvestigationReport = socialInvestigationReport;
	}
	public boolean isIndividualCarePlan() {
		return individualCarePlan;
	}
	public void setIndividualCarePlan(boolean individualCarePlan) {
		this.individualCarePlan = individualCarePlan;
	}
	public boolean isRehabitationCard() {
		return rehabitationCard;
	}
	public void setRehabitationCard(boolean rehabitationCard) {
		this.rehabitationCard = rehabitationCard;
	}
	public boolean isCaseHistory() {
		return caseHistory;
	}
	public void setCaseHistory(boolean caseHistory) {
		this.caseHistory = caseHistory;
	}
	public boolean isAllDetails() {
		return allDetails;
	}
	public void setAllDetails(boolean allDetails) {
		this.allDetails = allDetails;
	}
	public Date getDateOfFormFilled() {
		return dateOfFormFilled;
	}
	public void setDateOfFormFilled(Date dateOfFormFilled) {
		this.dateOfFormFilled = dateOfFormFilled;
	}
	public String getPlaceOfFormFilled() {
		return placeOfFormFilled;
	}
	public void setPlaceOfFormFilled(String placeOfFormFilled) {
		this.placeOfFormFilled = placeOfFormFilled;
	}
	public String getOrdersPassedByCwcOthers() {
		return ordersPassedByCwcOthers;
	}
	public void setOrdersPassedByCwcOthers(String ordersPassedByCwcOthers) {
		this.ordersPassedByCwcOthers = ordersPassedByCwcOthers;
	}
	public Date getChildProducedDate() {
		return printDate;
	}
	public void setChildProducedDate(Date childProducedDate) {
		this.printDate = childProducedDate;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Date getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}
	public String getParentOrGuardianName() {
		return parentOrGuardianName;
	}
	public void setParentOrGuardianName(String parentOrGuardianName) {
		this.parentOrGuardianName = parentOrGuardianName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Date getDateOfProduction() {
		return dateOfProduction;
	}
	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}
	public String getInterimOrderDates() {
		return interimOrderDates;
	}
	public void setInterimOrderDates(String interimOrderDates) {
		this.interimOrderDates = interimOrderDates;
	}
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
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
	public String getOrdersPassedByCWCName() {
		return ordersPassedByCWCName;
	}
	public void setOrdersPassedByCWCName(String ordersPassedByCWCName) {
		this.ordersPassedByCWCName = ordersPassedByCWCName;
	}
	public String getMedicalReports() {
		return medicalReports;
	}
	public void setMedicalReports(String medicalReports) {
		this.medicalReports = medicalReports;
	}
	
}
