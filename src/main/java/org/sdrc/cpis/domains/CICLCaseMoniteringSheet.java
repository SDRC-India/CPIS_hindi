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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*FORM 11*/

@Entity
@Table(name="CICL_case_moniteringsheet")
public class CICLCaseMoniteringSheet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -604019653378618197L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="district")
	private String district;
	
	@Column(name="case_no")
	private String caseNo;
	
	@Column(name="case_name")
	private String caseName;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="under_section")
	private String underSection;
	
	@Column(name="probation_officername")
	private String probationOfficerName;
	
	@Column(name="lawyer_name")
	private String lawyerName;
	
	@Column(name="io_name")
	private String ioName;
	
	@Column(name="childwelfare_officername")
	private String childWelfareOfficerName;
	
	@Column(name="nature_of_offence")
	private Integer natureOfOffence; //Value Object
	
	@Column(name="parent_contactnumber")
	private String parentContactnumber;
	
	@Column(name="present_address")
	private String presentAddress;
	
	@Column(name="permanent_address")
	private String permanentAddress;
	
	@Column(name="aprehended_date")
	private Date aprehendedDate;
	
	@Column(name="aprehended_time")
	private Time aprehendedTime;
	
	@Column(name="firstProduction_date")
	private Date firstProductionDate;
	
	@Column(name="firstProduction_time")
	private Time firstProductionTime;
	
	@Column(name="medicalexamination_date")
	private Date medicalExaminationDate;
	
	@Column(name="childage_on_dateofOffence")
	private Integer childAgeOnDateOfOffence;
	
	@Column(name="childage_determinationdate")
	private Date childAgeDeterminationDate;
	
	@Column(name="childage_determinationtime")
	private Double ageDeterminationTime;
	
	@Column(name="determinationby")
	private Integer determinationBy;
	
	@Column(name="evidence_relied_document")
	private String evidenceReliedDocument;
	
	@Column(name="evidence_relied_medical_name")
	private String evidenceReliedMedicalName;
	
	@Column(name="coc_by_whom") //coc stands for -custody of child
	private Integer cocByWhom;
	
	@Column(name="coc_by_whom_name") //coc stands for -custody of child
	private String cocByWhomName;
	
	@Column(name="coc_fromdate")
	private Date cocFromDate;
	
	@Column(name="coc_todate")
	private Date cocToDate;
	
	@Column(name="coc_BailDate")
	private Date cocBailDate;
	
	@Column(name="supervision_institution_name")
	private String supervision_institution_name;
	
	@Column(name="socialBackground_ScheduledDate")
	private Date socialBackgroundScheduledDate;
	
	@Column(name="socialBackground_ActualDate")
	private Date socialBackgroundActualDate;
	
	@Column(name="bailConsideration_ScheduledDate")
	private Date bailConsiderationScheduledDate;
	
	@Column(name="bailConsideration_ActualDate")
	private Date bailConsiderationActualDate;
	
	@Column(name="ageDetermination_ScheduledDate")
	private Date ageDeterminationScheduledDate;
	
	@Column(name="ageDetermination_ActualDate")
	private Date ageDeterminationActualDate;
	
	@Column(name="sirByProbationOfficer_ScheduledDate")
	private Date sirByProbationOfficerScheduledDate;
	
	@Column(name="sirByProbationOfficer_ActualDate")
	private Date sirByProbationOfficerActualDate;
	
	@Column(name="crpc173Report_ScheduledDate")
	private Date crpc173ReportScheduledDate;
	
	@Column(name="crpc173Report_ActualDate")
	private Date crpc173ReportActualDate;
	
	@Column(name="reportsOnProvision_ScheduledDate")
	private Date reportsOnProvisionScheduledDate;
	
	@Column(name="reportsOnProvision_actualDate")
	private Date reportsOnProvisionActualDate;
	
	@Column(name="crpc251Report_ScheduledDate")
	private Date crpc251ReportScheduledDate;
	
	@Column(name="crpc251Report_ActualDate")
	private Date crpc251ReportActualDate;
	
	@Column(name="prosecutionEvidience_FromDate")
	private Date prosecutionEvidienceFromDate;
	
	@Column(name="prosecutionEvidience_ToDate")
	private Date prosecutionEvidienceToDate;
	
	@Column(name="prosecutionEvidience_ScheduledDate")
	private Date prosecutionEvidienceScheduledDate;
	
	@Column(name="prosecutionEvidience_ActualDate")
	private Date prosecutionEvidienceActualDate;
	
	@Column(name="prosecutionEvidience_ScheduledDate1")
	private Date prosecutionEvidienceScheduledDate1;
	
	@Column(name="prosecutionEvidience_ActualDate1")
	private Date prosecutionEvidienceActualDate1;
	
	@Column(name="prosecutionEvidience_ScheduledDate2")
	private Date prosecutionEvidienceScheduledDate2;
	
	@Column(name="prosecutionEvidience_ActualDate2")
	private Date prosecutionEvidienceActualDate2;
	
	@Column(name="crpc281Report_ScheduledDate")
	private Date crpc281ReportScheduledDate;
	
	@Column(name="crpc281Report_ActualDate")
	private Date crpc281ReportActualDate;
	
	@Column(name="defenceEvidience_ScheduledDate")
	private Date defenceEvidienceScheduledDate;
	
	@Column(name="defenceEvidience_ActualDate")
	private Date defenceEvidienceActualDate;
	
	@Column(name="carePlan_ScheduledDate")
	private Date carePlanScheduledDate;
	
	@Column(name="carePlan_ActualDate")
	private Date carePlanActualDate;
	
	@Column(name="finalArguments_ScheduledDate")
	private Date finalArgumentsScheduledDate;
	
	@Column(name="finalArguments_ActualDate")
	private Date finalArgumentsActualDate;
	
	@Column(name="dispositionalOrder_ScheduledDate")
	private Date dispositionalOrderScheduledDate;
	
	@Column(name="dispositionalOrder_ActualDate")
	private Date dispositionalOrderActualDate;
	
	@Column(name="postDispositionalReview_ScheduledDate")
	private Date postDispositionalReviewScheduledDate;
	
	@Column(name="postDispositionalReview_ActualDate")
	private Date postDispositionalReviewActualDate;
	
	@Column(name="form_no")
	private Integer formNo;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	
	@Column(name="created_by")
	private String createdBy;
	
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUnderSection() {
		return underSection;
	}

	public void setUnderSection(String underSection) {
		this.underSection = underSection;
	}

	public String getProbationOfficerName() {
		return probationOfficerName;
	}

	public void setProbationOfficerName(String probationOfficerName) {
		this.probationOfficerName = probationOfficerName;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public String getIoName() {
		return ioName;
	}

	public void setIoName(String ioName) {
		this.ioName = ioName;
	}

	public String getChildWelfareOfficerName() {
		return childWelfareOfficerName;
	}

	public void setChildWelfareOfficerName(String childWelfareOfficerName) {
		this.childWelfareOfficerName = childWelfareOfficerName;
	}

	public Integer getNatureOfOffence() {
		return natureOfOffence;
	}

	public void setNatureOfOffence(Integer natureOfOffence) {
		this.natureOfOffence = natureOfOffence;
	}

	public String getParentContactnumber() {
		return parentContactnumber;
	}

	public void setParentContactnumber(String parentContactnumber) {
		this.parentContactnumber = parentContactnumber;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Date getAprehendedDate() {
		return aprehendedDate;
	}

	public void setAprehendedDate(Date aprehendedDate) {
		this.aprehendedDate = aprehendedDate;
	}

	public Time getAprehendedTime() {
		return aprehendedTime;
	}

	public void setAprehendedTime(Time aprehendedTime) {
		this.aprehendedTime = aprehendedTime;
	}

	public Date getFirstProductionDate() {
		return firstProductionDate;
	}

	public void setFirstProductionDate(Date firstProductionDate) {
		this.firstProductionDate = firstProductionDate;
	}

	public Time getFirstProductionTime() {
		return firstProductionTime;
	}

	public void setFirstProductionTime(Time firstProductionTime) {
		this.firstProductionTime = firstProductionTime;
	}

	public Date getMedicalExaminationDate() {
		return medicalExaminationDate;
	}

	public void setMedicalExaminationDate(Date medicalExaminationDate) {
		this.medicalExaminationDate = medicalExaminationDate;
	}

	public Integer getChildAgeOnDateOfOffence() {
		return childAgeOnDateOfOffence;
	}

	public void setChildAgeOnDateOfOffence(Integer childAgeOnDateOfOffence) {
		this.childAgeOnDateOfOffence = childAgeOnDateOfOffence;
	}

	public Date getChildAgeDeterminationDate() {
		return childAgeDeterminationDate;
	}

	public void setChildAgeDeterminationDate(Date childAgeDeterminationDate) {
		this.childAgeDeterminationDate = childAgeDeterminationDate;
	}

	public Double getAgeDeterminationTime() {
		return ageDeterminationTime;
	}

	public void setAgeDeterminationTime(Double ageDeterminationTime) {
		this.ageDeterminationTime = ageDeterminationTime;
	}

	public Integer getDeterminationBy() {
		return determinationBy;
	}

	public void setDeterminationBy(Integer determinationBy) {
		this.determinationBy = determinationBy;
	}

	public String getEvidenceReliedDocument() {
		return evidenceReliedDocument;
	}

	public void setEvidenceReliedDocument(String evidenceReliedDocument) {
		this.evidenceReliedDocument = evidenceReliedDocument;
	}

	public String getEvidenceReliedMedicalName() {
		return evidenceReliedMedicalName;
	}

	public void setEvidenceReliedMedicalName(String evidenceReliedMedicalName) {
		this.evidenceReliedMedicalName = evidenceReliedMedicalName;
	}

	public Integer getCocByWhom() {
		return cocByWhom;
	}

	public void setCocByWhom(Integer cocByWhom) {
		this.cocByWhom = cocByWhom;
	}

	public String getCocByWhomName() {
		return cocByWhomName;
	}

	public void setCocByWhomName(String cocByWhomName) {
		this.cocByWhomName = cocByWhomName;
	}

	public Date getCocFromDate() {
		return cocFromDate;
	}

	public void setCocFromDate(Date cocFromDate) {
		this.cocFromDate = cocFromDate;
	}

	public Date getCocToDate() {
		return cocToDate;
	}

	public void setCocToDate(Date cocToDate) {
		this.cocToDate = cocToDate;
	}

	public Date getCocBailDate() {
		return cocBailDate;
	}

	public void setCocBailDate(Date cocBailDate) {
		this.cocBailDate = cocBailDate;
	}

	public String getSupervision_institution_name() {
		return supervision_institution_name;
	}

	public void setSupervision_institution_name(String supervision_institution_name) {
		this.supervision_institution_name = supervision_institution_name;
	}

	public Date getSocialBackgroundScheduledDate() {
		return socialBackgroundScheduledDate;
	}

	public void setSocialBackgroundScheduledDate(Date socialBackgroundScheduledDate) {
		this.socialBackgroundScheduledDate = socialBackgroundScheduledDate;
	}

	public Date getSocialBackgroundActualDate() {
		return socialBackgroundActualDate;
	}

	public void setSocialBackgroundActualDate(Date socialBackgroundActualDate) {
		this.socialBackgroundActualDate = socialBackgroundActualDate;
	}

	public Date getBailConsiderationScheduledDate() {
		return bailConsiderationScheduledDate;
	}

	public void setBailConsiderationScheduledDate(
			Date bailConsiderationScheduledDate) {
		this.bailConsiderationScheduledDate = bailConsiderationScheduledDate;
	}

	public Date getBailConsiderationActualDate() {
		return bailConsiderationActualDate;
	}

	public void setBailConsiderationActualDate(Date bailConsiderationActualDate) {
		this.bailConsiderationActualDate = bailConsiderationActualDate;
	}

	public Date getAgeDeterminationScheduledDate() {
		return ageDeterminationScheduledDate;
	}

	public void setAgeDeterminationScheduledDate(Date ageDeterminationScheduledDate) {
		this.ageDeterminationScheduledDate = ageDeterminationScheduledDate;
	}

	public Date getAgeDeterminationActualDate() {
		return ageDeterminationActualDate;
	}

	public void setAgeDeterminationActualDate(Date ageDeterminationActualDate) {
		this.ageDeterminationActualDate = ageDeterminationActualDate;
	}

	public Date getSirByProbationOfficerScheduledDate() {
		return sirByProbationOfficerScheduledDate;
	}

	public void setSirByProbationOfficerScheduledDate(
			Date sirByProbationOfficerScheduledDate) {
		this.sirByProbationOfficerScheduledDate = sirByProbationOfficerScheduledDate;
	}

	public Date getSirByProbationOfficerActualDate() {
		return sirByProbationOfficerActualDate;
	}

	public void setSirByProbationOfficerActualDate(
			Date sirByProbationOfficerActualDate) {
		this.sirByProbationOfficerActualDate = sirByProbationOfficerActualDate;
	}

	public Date getCrpc173ReportScheduledDate() {
		return crpc173ReportScheduledDate;
	}

	public void setCrpc173ReportScheduledDate(Date crpc173ReportScheduledDate) {
		this.crpc173ReportScheduledDate = crpc173ReportScheduledDate;
	}

	public Date getCrpc173ReportActualDate() {
		return crpc173ReportActualDate;
	}

	public void setCrpc173ReportActualDate(Date crpc173ReportActualDate) {
		this.crpc173ReportActualDate = crpc173ReportActualDate;
	}

	public Date getReportsOnProvisionScheduledDate() {
		return reportsOnProvisionScheduledDate;
	}

	public void setReportsOnProvisionScheduledDate(
			Date reportsOnProvisionScheduledDate) {
		this.reportsOnProvisionScheduledDate = reportsOnProvisionScheduledDate;
	}

	public Date getReportsOnProvisionActualDate() {
		return reportsOnProvisionActualDate;
	}

	public void setReportsOnProvisionActualDate(Date reportsOnProvisionActualDate) {
		this.reportsOnProvisionActualDate = reportsOnProvisionActualDate;
	}

	public Date getCrpc251ReportScheduledDate() {
		return crpc251ReportScheduledDate;
	}

	public void setCrpc251ReportScheduledDate(Date crpc251ReportScheduledDate) {
		this.crpc251ReportScheduledDate = crpc251ReportScheduledDate;
	}

	public Date getCrpc251ReportActualDate() {
		return crpc251ReportActualDate;
	}

	public void setCrpc251ReportActualDate(Date crpc251ReportActualDate) {
		this.crpc251ReportActualDate = crpc251ReportActualDate;
	}

	public Date getProsecutionEvidienceFromDate() {
		return prosecutionEvidienceFromDate;
	}

	public void setProsecutionEvidienceFromDate(Date prosecutionEvidienceFromDate) {
		this.prosecutionEvidienceFromDate = prosecutionEvidienceFromDate;
	}

	public Date getProsecutionEvidienceToDate() {
		return prosecutionEvidienceToDate;
	}

	public void setProsecutionEvidienceToDate(Date prosecutionEvidienceToDate) {
		this.prosecutionEvidienceToDate = prosecutionEvidienceToDate;
	}

	public Date getProsecutionEvidienceScheduledDate() {
		return prosecutionEvidienceScheduledDate;
	}

	public void setProsecutionEvidienceScheduledDate(
			Date prosecutionEvidienceScheduledDate) {
		this.prosecutionEvidienceScheduledDate = prosecutionEvidienceScheduledDate;
	}

	public Date getProsecutionEvidienceActualDate() {
		return prosecutionEvidienceActualDate;
	}

	public void setProsecutionEvidienceActualDate(
			Date prosecutionEvidienceActualDate) {
		this.prosecutionEvidienceActualDate = prosecutionEvidienceActualDate;
	}

	public Date getProsecutionEvidienceScheduledDate1() {
		return prosecutionEvidienceScheduledDate1;
	}

	public void setProsecutionEvidienceScheduledDate1(
			Date prosecutionEvidienceScheduledDate1) {
		this.prosecutionEvidienceScheduledDate1 = prosecutionEvidienceScheduledDate1;
	}

	public Date getProsecutionEvidienceActualDate1() {
		return prosecutionEvidienceActualDate1;
	}

	public void setProsecutionEvidienceActualDate1(
			Date prosecutionEvidienceActualDate1) {
		this.prosecutionEvidienceActualDate1 = prosecutionEvidienceActualDate1;
	}

	public Date getProsecutionEvidienceScheduledDate2() {
		return prosecutionEvidienceScheduledDate2;
	}

	public void setProsecutionEvidienceScheduledDate2(
			Date prosecutionEvidienceScheduledDate2) {
		this.prosecutionEvidienceScheduledDate2 = prosecutionEvidienceScheduledDate2;
	}

	public Date getProsecutionEvidienceActualDate2() {
		return prosecutionEvidienceActualDate2;
	}

	public void setProsecutionEvidienceActualDate2(
			Date prosecutionEvidienceActualDate2) {
		this.prosecutionEvidienceActualDate2 = prosecutionEvidienceActualDate2;
	}

	public Date getCrpc281ReportScheduledDate() {
		return crpc281ReportScheduledDate;
	}

	public void setCrpc281ReportScheduledDate(Date crpc281ReportScheduledDate) {
		this.crpc281ReportScheduledDate = crpc281ReportScheduledDate;
	}

	public Date getCrpc281ReportActualDate() {
		return crpc281ReportActualDate;
	}

	public void setCrpc281ReportActualDate(Date crpc281ReportActualDate) {
		this.crpc281ReportActualDate = crpc281ReportActualDate;
	}

	public Date getDefenceEvidienceScheduledDate() {
		return defenceEvidienceScheduledDate;
	}

	public void setDefenceEvidienceScheduledDate(Date defenceEvidienceScheduledDate) {
		this.defenceEvidienceScheduledDate = defenceEvidienceScheduledDate;
	}

	public Date getDefenceEvidienceActualDate() {
		return defenceEvidienceActualDate;
	}

	public void setDefenceEvidienceActualDate(Date defenceEvidienceActualDate) {
		this.defenceEvidienceActualDate = defenceEvidienceActualDate;
	}

	public Date getCarePlanScheduledDate() {
		return carePlanScheduledDate;
	}

	public void setCarePlanScheduledDate(Date carePlanScheduledDate) {
		this.carePlanScheduledDate = carePlanScheduledDate;
	}

	public Date getCarePlanActualDate() {
		return carePlanActualDate;
	}

	public void setCarePlanActualDate(Date carePlanActualDate) {
		this.carePlanActualDate = carePlanActualDate;
	}

	public Date getFinalArgumentsScheduledDate() {
		return finalArgumentsScheduledDate;
	}

	public void setFinalArgumentsScheduledDate(Date finalArgumentsScheduledDate) {
		this.finalArgumentsScheduledDate = finalArgumentsScheduledDate;
	}

	public Date getFinalArgumentsActualDate() {
		return finalArgumentsActualDate;
	}

	public void setFinalArgumentsActualDate(Date finalArgumentsActualDate) {
		this.finalArgumentsActualDate = finalArgumentsActualDate;
	}

	public Date getDispositionalOrderScheduledDate() {
		return dispositionalOrderScheduledDate;
	}

	public void setDispositionalOrderScheduledDate(
			Date dispositionalOrderScheduledDate) {
		this.dispositionalOrderScheduledDate = dispositionalOrderScheduledDate;
	}

	public Date getDispositionalOrderActualDate() {
		return dispositionalOrderActualDate;
	}

	public void setDispositionalOrderActualDate(Date dispositionalOrderActualDate) {
		this.dispositionalOrderActualDate = dispositionalOrderActualDate;
	}

	public Date getPostDispositionalReviewScheduledDate() {
		return postDispositionalReviewScheduledDate;
	}

	public void setPostDispositionalReviewScheduledDate(
			Date postDispositionalReviewScheduledDate) {
		this.postDispositionalReviewScheduledDate = postDispositionalReviewScheduledDate;
	}

	public Date getPostDispositionalReviewActualDate() {
		return postDispositionalReviewActualDate;
	}

	public void setPostDispositionalReviewActualDate(
			Date postDispositionalReviewActualDate) {
		this.postDispositionalReviewActualDate = postDispositionalReviewActualDate;
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
