package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="icp_post_release_report")
public class IndividualCarePlanD implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2530860908717212486L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="status_of_bank_account")
	private Integer statusOfBankAccount;
	
	@Column(name="belongings_handed_to_parents")
	private Integer belongingsHandedToParents;
	
	@Column(name="interaction_report_by")
	private Integer interactionReportBy;
	
	@Column(name="interaction_details")
	private String interactionDetails;
	
	@Column(name="progress_made")
	private String progressMade;
	
	@Column(name="family_behaviour")
	private String familyBehaviour;
	
	@Column(name="social_milieu")
	private String socialMilieu;
	
	@Column(name="how_using_skills")
	private String howUsingSkills;
	
	@Column(name="child_went_to_learning_institue_type")
	private Integer childWentToLearningInstitueType;
	
	@Column(name="admission_date")
	private Date admissionDate;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="report_of_follow_up")
	private String reportOfFollowUp;
	
	@Column(name="child_view")
	private String childView;
	
	@Column(name="id_birth_certificate_produced")
	private boolean idBirthCertificateProduced;
	
	@Column(name="id_birth_certificate_num")
	private String idBirthCertificateNum;
	
	@Column(name="id_school_certificate_produced")
	private boolean idSchoolCertificateProduced;
	
	@Column(name="id_school_certificate_num")
	private String idSchoolCertificateNum;
	
	@Column(name="id_caste_certificate_produced")
	private boolean idCasteCertificateProduced;
	
	@Column(name="id_caste_certificate_num")
	private String idCasteCertificateNum;
	
	@Column(name="id_bpl_card_produced")
	private boolean idBplCardProduced;
	
	@Column(name="id_bpl_card_num")
	private String idBplCardNum;
	
	@Column(name="id_disabilty_certificate_produced")
	private boolean idDisabiltyCertificateProduced;
	
	@Column(name="id_disabilty_certificate_num")
	private String idDisabiltyCertificateNum;
	
	@Column(name="id_immunization_card_produced")
	private boolean idImmunizationCardProduced;
	
	@Column(name="id_immunization_card_num")
	private String idImmunizationCardNum;
	
	@Column(name="id_ration_card_produced")
	private boolean idRationCardProduced;
	
	@Column(name="id_ration_card_num")
	private String idRationCardNum;
	
	@Column(name="id_adhaar_card_produced")
	private boolean idAdhaarCardProduced;
	
	@Column(name="id_adhaar_card_num")
	private String idAdhaarCardNum;
	
	@Column(name="recieved_compensation")
	private boolean recievedCompensation;
	
	@Column(name="recieved_compensation_action")
	private String recievedCompensationAction;

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

	public Integer getStatusOfBankAccount() {
		return statusOfBankAccount;
	}

	public void setStatusOfBankAccount(Integer statusOfBankAccount) {
		this.statusOfBankAccount = statusOfBankAccount;
	}

	public Integer getBelongingsHandedToParents() {
		return belongingsHandedToParents;
	}

	public void setBelongingsHandedToParents(Integer belongingsHandedToParents) {
		this.belongingsHandedToParents = belongingsHandedToParents;
	}

	public Integer getInteractionReportBy() {
		return interactionReportBy;
	}

	public void setInteractionReportBy(Integer interactionReportBy) {
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

	public void setIdDisabiltyCertificateProduced(boolean idDisabiltyCertificateProduced) {
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

	public Integer getChildWentToLearningInstitueType() {
		return childWentToLearningInstitueType;
	}

	public void setChildWentToLearningInstitueType(
			Integer childWentToLearningInstitueType) {
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
	
}
