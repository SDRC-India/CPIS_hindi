package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pratyush
 */
@Entity
@Table(name="child_monitoring_report")
public class ChildMonitoringReport implements Serializable {

	private static final long serialVersionUID = 789921463637151563L;

	@Id
	@Column(name="id", columnDefinition = "serial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="individual_care_plan")
	private boolean IndividualCarePlan;
	
	@Column(name="other_source_of_referral")
	private String otherSourceOfReferral;
	
	@Column(name="photograph_of_child")
	private String photographOfChild;
	
	@Column(name="photograph_of_fcgiver_parent")
	private String photographOfFcGiverOrParent;
	
	@Column(name="photograph_of_biological_parents")
	private String photographOfBiologicalParents;
	
	@Column(name="child_details")
	private String childDetails;
	
	@Column(name="placement_type")
	private Integer placementType;
	
	@Column(name="date_of_placement")
	private Date dateOfPlacement;
	
	@Column(name="periodOfPlacement")
	private Integer periodOfPlacement;
	//hsr stands for home study report
	@Column(name="hsr_biological_family")
	private String hsrOfBiologicalFamily;
	
	@Column(name="hsr_foster_family")
	private String hsrOfFosterFamily;
	
	@Column(name="child_study_report")
	private String childStudyReport;
	
	@Column(name="record_each_visit")
	private String recordEachVisit;
	
	@Column(name="record_of_all_reviews_atchmnt")
	private String recordOfAllReviewsAtchmnt;
	
	@Column(name="record_of_all_reviews_dtls")
	private String recordOfAllReviewsDtls;
	
	@Column(name="date_termination")
	private Date dateTermination;
	
	@Column(name="reason_termination")
	private String reasonTermination;
	
	@Column(name="financial_assistance_provided")
	private boolean financialAssistanceProvided;
	
	@Column(name="financial_assistance_dtls")
	private String financialAssistanceDtls;
	
	@Column(name="caseworker_name")
	private String caseworkerName;

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

	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
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

	public void setFinancialAssistanceDtls(String financialAssistanceDtls) {
		this.financialAssistanceDtls = financialAssistanceDtls;
	}

	public String getCaseworkerName() {
		return caseworkerName;
	}

	public void setCaseworkerName(String caseworkerName) {
		this.caseworkerName = caseworkerName;
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
