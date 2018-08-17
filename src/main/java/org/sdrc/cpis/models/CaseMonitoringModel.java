package org.sdrc.cpis.models;

import java.sql.Date;
import java.sql.Time;

public class CaseMonitoringModel {
	
	private String childId;
	private String caseName;
	private String psName;
	private String sectionsChildBooked;
	private String firGdDdNo;
	private String probationOfficerName;
	private String ioName;
	private String pgName;
	private String pgContactNo;
	private String childPresentAddress;
	private String childPermanentAddress;
	private Date childProducedBeforeCommitteeDate;
	private Time childProducedBeforeCommitteeTime;
	private Date dateOfMedicalExamination;
	private Date dateOfAgeDetermination;
	private String timeTakenForAgeDetermination;
	private String nameOfDeterminator;
	private String ageDeterminationCommitteeName;
	private String evidenceDocuments;
	private String evidenceMedicalReports;
	private String childrenHomeName;
	private String supervisionInstitutionName;
	private Date date;
	private Date dateChildSentToSupervisionInstitution;
	private Date dateTillChildSentToSupervisionInstitution;
	private Date scheduledDateOfAgeDetermination;
	private Date actualDateOfAgeDetermination;
	private Date scheduledDateOfSocialInvestigationReport;
	private Date actualDateOfSocialInvestigationReport;
	private Date scheduledDateOfSubmissionReportOnProvisions;
	private Date actualDateOfSubmissionReportOnProvisions;
	private Date scheduledDateOfStatementOfChild;
	private Date actualDateOfStatementOfChild;
	private Date scheduledDateOfIndividualCarePlan;
	private Date actualDateOfIndividualCarePlan;
	private Date scheduledDateOfFinalOrder;
	private Date actualDateOfFinalOrder;
	private Date scheduledDateOfPostDispositionalReview;
	private Date actualDateOfPostDispositionalReview;
	private Double ageondate;
	private String doc;
	private String medical;
	private String childName;
	private String caseNum;
	private String cwcName;
	
	private String determineChildAge;
	private String medicalReportsRelied;
	private String timeOfFirstProduction;
	private String dateOfFirstProduction;
	private String childProducedBeforeCommitteeTimeString;
	
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
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getSectionsChildBooked() {
		return sectionsChildBooked;
	}
	public void setSectionsChildBooked(String sectionsChildBooked) {
		this.sectionsChildBooked = sectionsChildBooked;
	}
	public String getFirGdDdNo() {
		return firGdDdNo;
	}
	public void setFirGdDdNo(String firGdDdNo) {
		this.firGdDdNo = firGdDdNo;
	}
	public String getProbationOfficerName() {
		return probationOfficerName;
	}
	public void setProbationOfficerName(String probationOfficerName) {
		this.probationOfficerName = probationOfficerName;
	}
	public String getIoName() {
		return ioName;
	}
	public void setIoName(String ioName) {
		this.ioName = ioName;
	}
	public String getChildPresentAddress() {
		return childPresentAddress;
	}
	public void setChildPresentAddress(String childPresentAddress) {
		this.childPresentAddress = childPresentAddress;
	}
	public String getChildPermanentAddress() {
		return childPermanentAddress;
	}
	public void setChildPermanentAddress(String childPermanentAddress) {
		this.childPermanentAddress = childPermanentAddress;
	}
	public Date getDateOfMedicalExamination() {
		return dateOfMedicalExamination;
	}
	public void setDateOfMedicalExamination(Date dateOfMedicalExamination) {
		this.dateOfMedicalExamination = dateOfMedicalExamination;
	}
	public Date getDateOfAgeDetermination() {
		return dateOfAgeDetermination;
	}
	public void setDateOfAgeDetermination(Date dateOfAgeDetermination) {
		this.dateOfAgeDetermination = dateOfAgeDetermination;
	}
	public String getTimeTakenForAgeDetermination() {
		return timeTakenForAgeDetermination;
	}
	public void setTimeTakenForAgeDetermination(String timeTakenForAgeDetermination) {
		this.timeTakenForAgeDetermination = timeTakenForAgeDetermination;
	}
	public String getNameOfDeterminator() {
		return nameOfDeterminator;
	}
	public void setNameOfDeterminator(String nameOfDeterminator) {
		this.nameOfDeterminator = nameOfDeterminator;
	}
	public String getAgeDeterminationCommitteeName() {
		return ageDeterminationCommitteeName;
	}
	public void setAgeDeterminationCommitteeName(
			String ageDeterminationCommitteeName) {
		this.ageDeterminationCommitteeName = ageDeterminationCommitteeName;
	}
	public String getEvidenceDocuments() {
		return evidenceDocuments;
	}
	public void setEvidenceDocuments(String evidenceDocuments) {
		this.evidenceDocuments = evidenceDocuments;
	}
	public String getEvidenceMedicalReports() {
		return evidenceMedicalReports;
	}
	public void setEvidenceMedicalReports(String evidenceMedicalReports) {
		this.evidenceMedicalReports = evidenceMedicalReports;
	}
	public String getChildrenHomeName() {
		return childrenHomeName;
	}
	public void setChildrenHomeName(String childrenHomeName) {
		this.childrenHomeName = childrenHomeName;
	}
	public String getSupervisionInstitutionName() {
		return supervisionInstitutionName;
	}
	public void setSupervisionInstitutionName(String supervisionInstitutionName) {
		this.supervisionInstitutionName = supervisionInstitutionName;
	}
	public Date getDateChildSentToSupervisionInstitution() {
		return dateChildSentToSupervisionInstitution;
	}
	public void setDateChildSentToSupervisionInstitution(
			Date dateChildSentToSupervisionInstitution) {
		this.dateChildSentToSupervisionInstitution = dateChildSentToSupervisionInstitution;
	}
	public Date getDateTillChildSentToSupervisionInstitution() {
		return dateTillChildSentToSupervisionInstitution;
	}
	public void setDateTillChildSentToSupervisionInstitution(
			Date dateTillChildSentToSupervisionInstitution) {
		this.dateTillChildSentToSupervisionInstitution = dateTillChildSentToSupervisionInstitution;
	}
	public Date getScheduledDateOfAgeDetermination() {
		return scheduledDateOfAgeDetermination;
	}
	public void setScheduledDateOfAgeDetermination(
			Date scheduledDateOfAgeDetermination) {
		this.scheduledDateOfAgeDetermination = scheduledDateOfAgeDetermination;
	}
	public Date getActualDateOfAgeDetermination() {
		return actualDateOfAgeDetermination;
	}
	public void setActualDateOfAgeDetermination(Date actualDateOfAgeDetermination) {
		this.actualDateOfAgeDetermination = actualDateOfAgeDetermination;
	}
	public Date getScheduledDateOfSocialInvestigationReport() {
		return scheduledDateOfSocialInvestigationReport;
	}
	public void setScheduledDateOfSocialInvestigationReport(
			Date scheduledDateOfSocialInvestigationReport) {
		this.scheduledDateOfSocialInvestigationReport = scheduledDateOfSocialInvestigationReport;
	}
	public Date getActualDateOfSocialInvestigationReport() {
		return actualDateOfSocialInvestigationReport;
	}
	public void setActualDateOfSocialInvestigationReport(
			Date actualDateOfSocialInvestigationReport) {
		this.actualDateOfSocialInvestigationReport = actualDateOfSocialInvestigationReport;
	}
	public Date getScheduledDateOfSubmissionReportOnProvisions() {
		return scheduledDateOfSubmissionReportOnProvisions;
	}
	public void setScheduledDateOfSubmissionReportOnProvisions(
			Date scheduledDateOfSubmissionReportOnProvisions) {
		this.scheduledDateOfSubmissionReportOnProvisions = scheduledDateOfSubmissionReportOnProvisions;
	}
	public Date getActualDateOfSubmissionReportOnProvisions() {
		return actualDateOfSubmissionReportOnProvisions;
	}
	public void setActualDateOfSubmissionReportOnProvisions(
			Date actualDateOfSubmissionReportOnProvisions) {
		this.actualDateOfSubmissionReportOnProvisions = actualDateOfSubmissionReportOnProvisions;
	}
	public Date getScheduledDateOfStatementOfChild() {
		return scheduledDateOfStatementOfChild;
	}
	public void setScheduledDateOfStatementOfChild(
			Date scheduledDateOfStatementOfChild) {
		this.scheduledDateOfStatementOfChild = scheduledDateOfStatementOfChild;
	}
	public Date getActualDateOfStatementOfChild() {
		return actualDateOfStatementOfChild;
	}
	public void setActualDateOfStatementOfChild(Date actualDateOfStatementOfChild) {
		this.actualDateOfStatementOfChild = actualDateOfStatementOfChild;
	}
	public Date getScheduledDateOfIndividualCarePlan() {
		return scheduledDateOfIndividualCarePlan;
	}
	public void setScheduledDateOfIndividualCarePlan(
			Date scheduledDateOfIndividualCarePlan) {
		this.scheduledDateOfIndividualCarePlan = scheduledDateOfIndividualCarePlan;
	}
	public Date getActualDateOfIndividualCarePlan() {
		return actualDateOfIndividualCarePlan;
	}
	public void setActualDateOfIndividualCarePlan(
			Date actualDateOfIndividualCarePlan) {
		this.actualDateOfIndividualCarePlan = actualDateOfIndividualCarePlan;
	}
	public Date getScheduledDateOfFinalOrder() {
		return scheduledDateOfFinalOrder;
	}
	public void setScheduledDateOfFinalOrder(Date scheduledDateOfFinalOrder) {
		this.scheduledDateOfFinalOrder = scheduledDateOfFinalOrder;
	}
	public Date getActualDateOfFinalOrder() {
		return actualDateOfFinalOrder;
	}
	public void setActualDateOfFinalOrder(Date actualDateOfFinalOrder) {
		this.actualDateOfFinalOrder = actualDateOfFinalOrder;
	}
	public Date getScheduledDateOfPostDispositionalReview() {
		return scheduledDateOfPostDispositionalReview;
	}
	public void setScheduledDateOfPostDispositionalReview(
			Date scheduledDateOfPostDispositionalReview) {
		this.scheduledDateOfPostDispositionalReview = scheduledDateOfPostDispositionalReview;
	}
	public Date getActualDateOfPostDispositionalReview() {
		return actualDateOfPostDispositionalReview;
	}
	public void setActualDateOfPostDispositionalReview(
			Date actualDateOfPostDispositionalReview) {
		this.actualDateOfPostDispositionalReview = actualDateOfPostDispositionalReview;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public String getPgContactNo() {
		return pgContactNo;
	}
	public void setPgContactNo(String pgContactNo) {
		this.pgContactNo = pgContactNo;
	}
	public Double getAgeondate() {
		return ageondate;
	}
	public void setAgeondate(Double ageondate) {
		this.ageondate = ageondate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getMedical() {
		return medical;
	}
	public void setMedical(String medical) {
		this.medical = medical;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getCwcName() {
		return cwcName;
	}
	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}
	public Date getChildProducedBeforeCommitteeDate() {
		return childProducedBeforeCommitteeDate;
	}
	public void setChildProducedBeforeCommitteeDate(
			Date childProducedBeforeCommitteeDate) {
		this.childProducedBeforeCommitteeDate = childProducedBeforeCommitteeDate;
	}
	public Time getChildProducedBeforeCommitteeTime() {
		return childProducedBeforeCommitteeTime;
	}
	public void setChildProducedBeforeCommitteeTime(
			Time childProducedBeforeCommitteeTime) {
		this.childProducedBeforeCommitteeTime = childProducedBeforeCommitteeTime;
	}
	public String getDetermineChildAge() {
		return determineChildAge;
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
	public void setDetermineChildAge(String determineChildAge) {
		this.determineChildAge = determineChildAge;
	}
	public String getMedicalReportsRelied() {
		return medicalReportsRelied;
	}
	public void setMedicalReportsRelied(String medicalReportsRelied) {
		this.medicalReportsRelied = medicalReportsRelied;
	}
	public String getTimeOfFirstProduction() {
		return timeOfFirstProduction;
	}
	public void setTimeOfFirstProduction(String timeOfFirstProduction) {
		this.timeOfFirstProduction = timeOfFirstProduction;
	}
	public String getDateOfFirstProduction() {
		return dateOfFirstProduction;
	}
	public void setDateOfFirstProduction(String dateOfFirstProduction) {
		this.dateOfFirstProduction = dateOfFirstProduction;
	}
	public String getChildProducedBeforeCommitteeTimeString() {
		return childProducedBeforeCommitteeTimeString;
	}
	public void setChildProducedBeforeCommitteeTimeString(
			String childProducedBeforeCommitteeTimeString) {
		this.childProducedBeforeCommitteeTimeString = childProducedBeforeCommitteeTimeString;
	}		
	
}
