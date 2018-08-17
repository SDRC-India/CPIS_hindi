package org.sdrc.cpis.models;

import java.sql.Date;
import java.sql.Time;

import org.sdrc.cpis.util.ValueObject;


public class CICLCaseMoniteringSheetModel {
	
	private Integer id;
	private String childId;
	private String district;
	private String caseNo;
	private String caseName;
	private Date date;
	private String underSection;
	private String probationOfficerName;
	private String lawyerName;
	private String ioName;
	private String childWelfareOfficerName;
	private ValueObject natureOfOffence; //Value Object
	private String parentContactnumber;
	private String presentAddress;
	private String permanentAddress;
	private Date aprehendedDate;
	private Time aprehendedTime;
	private Date firstProductionDate;
	private Time firstProductionTime;
	private Date medicalExaminationDate;
	private Integer childAgeOnDateOfOffence;
	private Date childAgeDeterminationDate;
	private Double ageDeterminationTime;
	private ValueObject determinationBy;
	private String evidenceReliedDocument;
	private String evidenceReliedMedicalName;
	private ValueObject cocByWhom; //coc stands for -custody of child
	private String cocByWhomName;
	private Date cocFromDate;
	private Date cocToDate;
	private Date cocBailDate;
	private String supervision_institution_name;
	private Date socialBackgroundScheduledDate;
	private Date socialBackgroundActualDate;
	private Date bailConsiderationScheduledDate;
	private Date bailConsiderationActualDate;
	private Date ageDeterminationScheduledDate;
	private Date ageDeterminationActualDate;
	private Date sirByProbationOfficerScheduledDate;
	private Date sirByProbationOfficerActualDate;
	private Date crpc173ReportScheduledDate;
	private Date crpc173ReportActualDate;
	private Date reportsOnProvisionScheduledDate;
	private Date reportsOnProvisionActualDate;
	private Date crpc251ReportScheduledDate;
	private Date crpc251ReportActualDate;
	private Date prosecutionEvidienceFromDate;
	private Date prosecutionEvidienceToDate;
	private Date prosecutionEvidienceScheduledDate;
	private Date prosecutionEvidienceActualDate;
	private Date prosecutionEvidienceScheduledDate1;
	private Date prosecutionEvidienceActualDate1;
	private Date prosecutionEvidienceScheduledDate2;
	private Date prosecutionEvidienceActualDate2;
	private Date crpc281ReportScheduledDate;
	private Date crpc281ReportActualDate;
	private Date defenceEvidienceScheduledDate;
	private Date defenceEvidienceActualDate;
	private Date carePlanScheduledDate;
	private Date carePlanActualDate;
	private Date finalArgumentsScheduledDate;
	private Date finalArgumentsActualDate;
	private Date dispositionalOrderScheduledDate;
	private Date dispositionalOrderActualDate;
	private Date postDispositionalReviewScheduledDate;
	private Date postDispositionalReviewActualDate;
	private Integer formNo;
	private Date createdDate;
	private String gdNumber;
	private String firNumber;
	private String ddNumber;
	private String childName;
	private String policeStation;
	
	private String createdBy;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private String parentName;
	private Integer programType;
	
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
	public ValueObject getNatureOfOffence() {
		return natureOfOffence;
	}
	public void setNatureOfOffence(ValueObject natureOfOffence) {
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
	public ValueObject getDeterminationBy() {
		return determinationBy;
	}
	public void setDeterminationBy(ValueObject determinationBy) {
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
	public ValueObject getCocByWhom() {
		return cocByWhom;
	}
	public void setCocByWhom(ValueObject cocByWhom) {
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
	public String getGdNumber() {
		return gdNumber;
	}
	public void setGdNumber(String gdNumber) {
		this.gdNumber = gdNumber;
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
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
		
}
