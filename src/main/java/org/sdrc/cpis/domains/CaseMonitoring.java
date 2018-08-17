package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ccts_case_monitoring")
public class CaseMonitoring implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1028883278146246921L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	Integer id;
	
	@Column(name="case_name")
	String caseName;
	
	@Column(name="ps_name")
	String psName;
	
	@Column(name="sections_child_booked")
	String sectionsChildBooked;
	
	@Column(name="fir_gd_dd_no")
	String firGdDdNo;
	
	@Column(name="probation_officer_name")
	String probationOfficerName;
	
	@Column(name="io_name")
	String ioName;
	
	@Column (name="parent_guardian_name")
	String pgName;
	
	@Column (name="parent_guardian_contactNo")
	String pgContactNo;
	
	@Column(name="child_present_address")
	String childPresentAddress;
	
	@Column(name="date")
	Date date;
	
	@Column(name="child_permanent_address")
	String childPermanentAddress;
	
	@Column (name="child_produced_before_committee_date")
	Date childProducedBeforeCommitteeDate;
	
	@Column (name="child_produced_before_committee_time")
	Time childProducedBeforeCommitteeTime;
	
	@Column(name="date_of_medical_examination")
	Date dateOfMedicalExamination;
	
	@Column(name="date_of_age_determination")
	Date DateOfAgeDetermination;
	
	@Column(name="time_taken_for_age_determination")
	Double timeTakenForAgeDetermination;
	
	@Column(name="name_of_age_determinator")
	String NameOfDeterminator;
	
	@Column(name="age_determinator_committee")
	String ageDeterminatorCommitteeName;
	
	@Column(name="evidence_documents")
	String evidenceDocuments;
	
	@Column(name="evidence_medical_reports")
	String evidenceMedicalReports;
	
	@Column(name="children_home_name")
	String childrenHomeName;
	
	@Column(name="supervision_institution")
	String supervisionInstitutionName;
	
	@Column(name="date_child_sent_to_supervision_institution")
	Date dateChildSentToSupervisionInstitution;
	
	@Column(name="date_till_child_sent_to_supervision_institution")
	Date dateTillChildSentToSupervisionInstitution;
	
	@Column(name="scheduled_date_of_age_determination")
	Date scheduledDateOfAgeDetermination;
	
	@Column(name="actual_date_of_age_determination")
	Date actualDateOfAgeDetermination;
	
	@Column(name="scheduled_date_of_social_investigation_report")
	Date scheduledDateOfSocialInvestigationReport;
	
	@Column(name="actual_date_of_social_investigation_report")
	Date actualDateOfSocialInvestigationReport;
	
	@Column(name="scheduled_date_of_submission_report_on_provisions")
	Date scheduledDateOfSubmissionReportOnProvisions;
	
	@Column(name="actual_date_of_submission_report_on_provisions")
	Date actualDateOfSubmissionReportOnProvisions;
	
	@Column(name="scheduled_date_of_statement_of_child")
	Date scheduledDateOfStatementOfChild;
	
	@Column(name="actual_date_of_statement_of_child")
	Date actualDateOfStatementOfChild;
	
	@Column(name="scheduled_date_of_individual_care_plan")
	Date scheduledDateOfIndividualCarePlan;
	
	@Column(name="actual_date_of_individual_care_plan")
	Date actualDateOfIndividualCarePlan;
	
	@Column(name="scheduled_date_of_final_order")
	Date scheduledDateOfFinalOrder;
	
	@Column(name="actual_date_of_final_order")
	Date actualDateOfFinalOrder;
	
	@Column(name="scheduled_date_of_post_dispositional_review")
	Date scheduledDateOfPostDispositionalReview;
	
	@Column(name="actual_date_of_post_dispositional_review")
	Date actualDateOfPostDispositionalReview;
	
	@Column(name="age_on_date_of_offence")
	Double ageOnDateOfOffence;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;

	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="user_ip")
	private String userIp;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return DateOfAgeDetermination;
	}

	public void setDateOfAgeDetermination(Date dateOfAgeDetermination) {
		DateOfAgeDetermination = dateOfAgeDetermination;
	}

	public Double getTimeTakenForAgeDetermination() {
		return timeTakenForAgeDetermination;
	}

	public void setTimeTakenForAgeDetermination(Double timeTakenForAgeDetermination) {
		this.timeTakenForAgeDetermination = timeTakenForAgeDetermination;
	}

	public String getNameOfDeterminator() {
		return NameOfDeterminator;
	}

	public void setNameOfDeterminator(String nameOfDeterminator) {
		NameOfDeterminator = nameOfDeterminator;
	}

	public String getAgeDeterminatorCommitteeName() {
		return ageDeterminatorCommitteeName;
	}

	public void setAgeDeterminatorCommitteeName(String ageDeterminatorCommitteeName) {
		this.ageDeterminatorCommitteeName = ageDeterminatorCommitteeName;
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

	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAgeOnDateOfOffence() {
		return ageOnDateOfOffence;
	}

	public void setAgeOnDateOfOffence(Double ageOnDateOfOffence) {
		this.ageOnDateOfOffence = ageOnDateOfOffence;
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
