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

@Entity
@Table(name="case_summary_by_cwc")
public class CaseSummaryByCWC implements Serializable {

	private static final long serialVersionUID = 2414186215359835758L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
//	@Column(name="case_no", unique=true, nullable = false)
//	private String caseNo;
	
	@Column(name="re")
	private String re;
	
	@Column(name="case_record")
	private String caseRecord;
	
	@Column(name="orders_passed_by_cwc")
	private String ordersPassedByCWC;
	
	@Column(name="orders_passed_by_cwc_others")
	private String ordersPassedByCwcOthers;
	
	@Column(name="medical_records")
	private String medicalRecords;
	
	@Column(name="social_investigation_report")
	private boolean socialInvestigationReport;
	
	@Column(name="individual_care_plan")
	private boolean individualCarePlan;
	
	@Column(name="rehabitation_card")
	private boolean rehabitationCard;
	
	@Column(name="case_history")
	private boolean caseHistory;
	
	@Column(name="all_details")
	private boolean allDetails;
	
	@Column(name="date_of_form_filled")
	private Date dateOfFormFilled;
	
	@Column(name="place_of_form_filled")
	private String placeOfFormFilled;
	
	@Column(name="parent_or_guardian_name")
	String parentOrGuardianName;
	
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
	
	
	public String getParentOrGuardianName() {
		return parentOrGuardianName;
	}

	public void setParentOrGuardianName(String parentOrGuardianName) {
		this.parentOrGuardianName = parentOrGuardianName;
	}

	
	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}

//	public String getCaseNo() {
//		return caseNo;
//	}
//
//	public void setCaseNo(String caseNo) {
//		this.caseNo = caseNo;
//	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
