package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class IndividualCarePlanDModel {

	private String childId;
	private Integer id;
	private ValueObject statusOfBankAccount;
	private ValueObject belongingsHandedToParents;
	private ValueObject interactionReportBy;
	private String interactionDetails;
	private String progressMade;
	private String familyBehaviour;
	private String socialMilieu;
	private String howUsingSkills;
	private ValueObject childWentToLearningInstitueType;
	private Date admissionDate;
	private String schoolName;
	private String reportOfFollowUp;
	private String childView;
	private boolean idBirthCertificateProduced;
	private String idBirthCertificateNum;
	private boolean idSchoolCertificateProduced;
	private String idSchoolCertificateNum;
	private boolean idCasteCertificateProduced;
	private String idCasteCertificateNum;
	private boolean idBplCardProduced;
	private String idBplCardNum;
	private boolean idDisabiltyCertificateProduced;
	private String idDisabiltyCertificateNum;
	private boolean idImmunizationCardProduced;
	private String idImmunizationCardNum;
	private boolean idRationCardProduced;
	private String idRationCardNum;
	private boolean idAdhaarCardProduced;
	private String idAdhaarCardNum;
	private boolean recievedCompensation;
	private String recievedCompensationAction;
	
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
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public ValueObject getStatusOfBankAccount() {
		return statusOfBankAccount;
	}
	public void setStatusOfBankAccount(ValueObject statusOfBankAccount) {
		this.statusOfBankAccount = statusOfBankAccount;
	}
	public ValueObject getBelongingsHandedToParents() {
		return belongingsHandedToParents;
	}
	public void setBelongingsHandedToParents(ValueObject belongingsHandedToParents) {
		this.belongingsHandedToParents = belongingsHandedToParents;
	}
	public ValueObject getInteractionReportBy() {
		return interactionReportBy;
	}
	public void setInteractionReportBy(ValueObject interactionReportBy) {
		this.interactionReportBy = interactionReportBy;
	}
	public String getInteractionDetails() {
		return interactionDetails;
	}
	public void setInteractionDetails(String interactionDetails) {
		this.interactionDetails = interactionDetails;
	}
	public String getProgressMade() {
		return progressMade;
	}
	public void setProgressMade(String progressMade) {
		this.progressMade = progressMade;
	}
	public String getFamilyBehaviour() {
		return familyBehaviour;
	}
	public void setFamilyBehaviour(String familyBehaviour) {
		this.familyBehaviour = familyBehaviour;
	}
	public String getSocialMilieu() {
		return socialMilieu;
	}
	public void setSocialMilieu(String socialMilieu) {
		this.socialMilieu = socialMilieu;
	}
	public String getHowUsingSkills() {
		return howUsingSkills;
	}
	public void setHowUsingSkills(String howUsingSkills) {
		this.howUsingSkills = howUsingSkills;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getReportOfFollowUp() {
		return reportOfFollowUp;
	}
	public void setReportOfFollowUp(String reportOfFollowUp) {
		this.reportOfFollowUp = reportOfFollowUp;
	}
	public String getChildView() {
		return childView;
	}
	public void setChildView(String childView) {
		this.childView = childView;
	}
	public boolean isIdBirthCertificateProduced() {
		return idBirthCertificateProduced;
	}
	public void setIdBirthCertificateProduced(boolean idBirthCertificateProduced) {
		this.idBirthCertificateProduced = idBirthCertificateProduced;
	}
	public String getIdBirthCertificateNum() {
		return idBirthCertificateNum;
	}
	public void setIdBirthCertificateNum(String idBirthCertificateNum) {
		this.idBirthCertificateNum = idBirthCertificateNum;
	}
	public boolean isIdSchoolCertificateProduced() {
		return idSchoolCertificateProduced;
	}
	public void setIdSchoolCertificateProduced(boolean idSchoolCertificateProduced) {
		this.idSchoolCertificateProduced = idSchoolCertificateProduced;
	}
	public String getIdSchoolCertificateNum() {
		return idSchoolCertificateNum;
	}
	public void setIdSchoolCertificateNum(String idSchoolCertificateNum) {
		this.idSchoolCertificateNum = idSchoolCertificateNum;
	}
	public boolean isIdCasteCertificateProduced() {
		return idCasteCertificateProduced;
	}
	public void setIdCasteCertificateProduced(boolean idCasteCertificateProduced) {
		this.idCasteCertificateProduced = idCasteCertificateProduced;
	}
	public String getIdCasteCertificateNum() {
		return idCasteCertificateNum;
	}
	public void setIdCasteCertificateNum(String idCasteCertificateNum) {
		this.idCasteCertificateNum = idCasteCertificateNum;
	}
	public boolean isIdBplCardProduced() {
		return idBplCardProduced;
	}
	public void setIdBplCardProduced(boolean idBplCardProduced) {
		this.idBplCardProduced = idBplCardProduced;
	}
	public String getIdBplCardNum() {
		return idBplCardNum;
	}
	public void setIdBplCardNum(String idBplCardNum) {
		this.idBplCardNum = idBplCardNum;
	}
	public boolean isIdDisabiltyCertificateProduced() {
		return idDisabiltyCertificateProduced;
	}
	public void setIdDisabiltyCertificateProduced(
			boolean idDisabiltyCertificateProduced) {
		this.idDisabiltyCertificateProduced = idDisabiltyCertificateProduced;
	}
	public String getIdDisabiltyCertificateNum() {
		return idDisabiltyCertificateNum;
	}
	public void setIdDisabiltyCertificateNum(String idDisabiltyCertificateNum) {
		this.idDisabiltyCertificateNum = idDisabiltyCertificateNum;
	}
	public boolean isIdImmunizationCardProduced() {
		return idImmunizationCardProduced;
	}
	public void setIdImmunizationCardProduced(boolean idImmunizationCardProduced) {
		this.idImmunizationCardProduced = idImmunizationCardProduced;
	}
	public String getIdImmunizationCardNum() {
		return idImmunizationCardNum;
	}
	public void setIdImmunizationCardNum(String idImmunizationCardNum) {
		this.idImmunizationCardNum = idImmunizationCardNum;
	}
	public boolean isIdRationCardProduced() {
		return idRationCardProduced;
	}
	public void setIdRationCardProduced(boolean idRationCardProduced) {
		this.idRationCardProduced = idRationCardProduced;
	}
	public String getIdRationCardNum() {
		return idRationCardNum;
	}
	public void setIdRationCardNum(String idRationCardNum) {
		this.idRationCardNum = idRationCardNum;
	}
	public boolean isIdAdhaarCardProduced() {
		return idAdhaarCardProduced;
	}
	public void setIdAdhaarCardProduced(boolean idAdhaarCardProduced) {
		this.idAdhaarCardProduced = idAdhaarCardProduced;
	}
	public String getIdAdhaarCardNum() {
		return idAdhaarCardNum;
	}
	public void setIdAdhaarCardNum(String idAdhaarCardNum) {
		this.idAdhaarCardNum = idAdhaarCardNum;
	}
	public boolean isRecievedCompensation() {
		return recievedCompensation;
	}
	public void setRecievedCompensation(boolean recievedCompensation) {
		this.recievedCompensation = recievedCompensation;
	}
	public String getRecievedCompensationAction() {
		return recievedCompensationAction;
	}
	public void setRecievedCompensationAction(String recievedCompensationAction) {
		this.recievedCompensationAction = recievedCompensationAction;
	}
	public ValueObject getChildWentToLearningInstitueType() {
		return childWentToLearningInstitueType;
	}
	public void setChildWentToLearningInstitueType(
			ValueObject childWentToLearningInstitueType) {
		this.childWentToLearningInstitueType = childWentToLearningInstitueType;
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
