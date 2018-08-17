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
@Table(name="icp_personal_details")
public class IndividualCarePlanA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3785217636231374038L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="type_of_officer_worker")
	private Integer typeOfOfficerOrWorker;
	
	@Column(name="name_of_officer_worker")
	private String nameOfOfficerOrWorker;
	
	@Column(name="date_of_icp")
	private Date DateOfICP;
	
	@Column(name="police_station")
	private String policeStation;
	
	@Column(name="address_board_committee")
	private String addressBoardCommittee;
	
	@Column(name="admission_no")
	private String admissionNo;
	
	@Column(name="date_of_admission")
	private Date DateOfAdmission;
	
	@Column(name="stay_of_child")
	private Integer stayOfChild;
	
	@Column(name="child_age")
	private Integer childAge;
	
	@Column(name="child_dob")
	private Date childDob;
	
	@Column(name="nationality")
	private String nationality;
	
	@Column(name="religion")
	private Integer religion;
	
	@Column(name="religion_other")
	private String religionOther;
	
	@Column(name="caste")
	private Integer caste;
	
	@Column(name="language_spoken")
	private String languageSpoken;
	
	@Column(name="level_of_Education")
	private Integer LevelOfEducation;
	
	@Column(name="has_savings_account")
	private String hasSavingAccountDtls;
	
	@Column(name="savings_account_dtls")
	private String savingAccountDtls;
	
	@Column(name="child_earnings")
	private String childEarnings;
	
	@Column(name="awards_rewards_dtls")
	private String awardsRewardsDtls;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="mother_name")
	private String motherName;
	
	@Column(name="fir_no")
	private String firNo;
	
	@Column(name="u_sections")
	private String uSections;
	
	//here aoc stands for Areas Of Concern and pi stands for Proposed Interventions
	@Column(name="cat1_aoc")
	private String cat1Aoc;
	
	@Column(name="cat1_pi")
	private String cat1Pi;
	
	@Column(name="cat2_aoc")
	private String cat2Aoc;
	
	@Column(name="cat2_pi")
	private String cat2Pi;
	
	@Column(name="cat3_aoc")
	private String cat3Aoc;
	
	@Column(name="cat3_pi")
	private String cat3Pi;
	
	@Column(name="cat4_aoc")
	private String cat4Aoc;
	
	@Column(name="cat4_pi")
	private String cat4Pi;
	
	@Column(name="cat5_aoc")
	private String cat5Aoc;
	
	@Column(name="cat5_pi")
	private String cat5Pi;
	
	@Column(name="cat6_aoc")
	private String cat6Aoc;
	
	@Column(name="cat6_pi")
	private String cat6Pi;
	
	@Column(name="cat7_aoc")
	private String cat7Aoc;
	
	@Column(name="cat7_pi")
	private String cat7Pi;
	
	@Column(name="cat8_aoc")
	private String cat8Aoc;
	
	@Column(name="cat8_pi")
	private String cat8Pi;
	
	@Column(name="cat9_aoc")
	private String cat9Aoc;
	
	@Column(name="cat9_pi")
	private String cat9Pi;
	
	@Column(name="cat10_dtls")
	private String cat10Dtls;
	
	@Column(name="cat10_aoc")
	private String cat10Aoc;
	
	@Column(name="cat10_pi")
	private String cat10Pi;
	
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

	public Integer getTypeOfOfficerOrWorker() {
		return typeOfOfficerOrWorker;
	}

	public void setTypeOfOfficerOrWorker(Integer typeOfOfficerOrWorker) {
		this.typeOfOfficerOrWorker = typeOfOfficerOrWorker;
	}

	public String getNameOfOfficerOrWorker() {
		return nameOfOfficerOrWorker;
	}

	public void setNameOfOfficerOrWorker(String nameOfOfficerOrWorker) {
		this.nameOfOfficerOrWorker = nameOfOfficerOrWorker;
	}

	public Date getDateOfICP() {
		return DateOfICP;
	}

	public void setDateOfICP(Date dateOfICP) {
		DateOfICP = dateOfICP;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public String getAddressBoardCommittee() {
		return addressBoardCommittee;
	}

	public void setAddressBoardCommittee(String addressBoardCommittee) {
		this.addressBoardCommittee = addressBoardCommittee;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public Date getDateOfAdmission() {
		return DateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		DateOfAdmission = dateOfAdmission;
	}

	public Integer getStayOfChild() {
		return stayOfChild;
	}

	public void setStayOfChild(Integer stayOfChild) {
		this.stayOfChild = stayOfChild;
	}

	public Integer getChildAge() {
		return childAge;
	}

	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}

	public Date getChildDob() {
		return childDob;
	}

	public void setChildDob(Date childDob) {
		this.childDob = childDob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public String getReligionOther() {
		return religionOther;
	}

	public void setReligionOther(String religionOther) {
		this.religionOther = religionOther;
	}

	public Integer getCaste() {
		return caste;
	}

	public void setCaste(Integer caste) {
		this.caste = caste;
	}

	public String getLanguageSpoken() {
		return languageSpoken;
	}

	public void setLanguageSpoken(String languageSpoken) {
		this.languageSpoken = languageSpoken;
	}

	public Integer getLevelOfEducation() {
		return LevelOfEducation;
	}

	public void setLevelOfEducation(Integer levelOfEducation) {
		LevelOfEducation = levelOfEducation;
	}

	public String getHasSavingAccountDtls() {
		return hasSavingAccountDtls;
	}

	public void setHasSavingAccountDtls(String hasSavingAccountDtls) {
		this.hasSavingAccountDtls = hasSavingAccountDtls;
	}

	public String getSavingAccountDtls() {
		return savingAccountDtls;
	}

	public void setSavingAccountDtls(String savingAccountDtls) {
		this.savingAccountDtls = savingAccountDtls;
	}

	public String getChildEarnings() {
		return childEarnings;
	}

	public void setChildEarnings(String childEarnings) {
		this.childEarnings = childEarnings;
	}

	public String getAwardsRewardsDtls() {
		return awardsRewardsDtls;
	}

	public void setAwardsRewardsDtls(String awardsRewardsDtls) {
		this.awardsRewardsDtls = awardsRewardsDtls;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFirNo() {
		return firNo;
	}

	public void setFirNo(String firNo) {
		this.firNo = firNo;
	}

	public String getuSections() {
		return uSections;
	}

	public void setuSections(String uSections) {
		this.uSections = uSections;
	}

	public String getCat1Aoc() {
		return cat1Aoc;
	}

	public void setCat1Aoc(String cat1Aoc) {
		this.cat1Aoc = cat1Aoc;
	}

	public String getCat1Pi() {
		return cat1Pi;
	}

	public void setCat1Pi(String cat1Pi) {
		this.cat1Pi = cat1Pi;
	}

	public String getCat2Aoc() {
		return cat2Aoc;
	}

	public void setCat2Aoc(String cat2Aoc) {
		this.cat2Aoc = cat2Aoc;
	}

	public String getCat2Pi() {
		return cat2Pi;
	}

	public void setCat2Pi(String cat2Pi) {
		this.cat2Pi = cat2Pi;
	}

	public String getCat3Aoc() {
		return cat3Aoc;
	}

	public void setCat3Aoc(String cat3Aoc) {
		this.cat3Aoc = cat3Aoc;
	}

	public String getCat3Pi() {
		return cat3Pi;
	}

	public void setCat3Pi(String cat3Pi) {
		this.cat3Pi = cat3Pi;
	}

	public String getCat4Aoc() {
		return cat4Aoc;
	}

	public void setCat4Aoc(String cat4Aoc) {
		this.cat4Aoc = cat4Aoc;
	}

	public String getCat4Pi() {
		return cat4Pi;
	}

	public void setCat4Pi(String cat4Pi) {
		this.cat4Pi = cat4Pi;
	}

	public String getCat5Aoc() {
		return cat5Aoc;
	}

	public void setCat5Aoc(String cat5Aoc) {
		this.cat5Aoc = cat5Aoc;
	}

	public String getCat5Pi() {
		return cat5Pi;
	}

	public void setCat5Pi(String cat5Pi) {
		this.cat5Pi = cat5Pi;
	}

	public String getCat6Aoc() {
		return cat6Aoc;
	}

	public void setCat6Aoc(String cat6Aoc) {
		this.cat6Aoc = cat6Aoc;
	}

	public String getCat6Pi() {
		return cat6Pi;
	}

	public void setCat6Pi(String cat6Pi) {
		this.cat6Pi = cat6Pi;
	}

	public String getCat7Aoc() {
		return cat7Aoc;
	}

	public void setCat7Aoc(String cat7Aoc) {
		this.cat7Aoc = cat7Aoc;
	}

	public String getCat7Pi() {
		return cat7Pi;
	}

	public void setCat7Pi(String cat7Pi) {
		this.cat7Pi = cat7Pi;
	}

	public String getCat8Aoc() {
		return cat8Aoc;
	}

	public void setCat8Aoc(String cat8Aoc) {
		this.cat8Aoc = cat8Aoc;
	}

	public String getCat8Pi() {
		return cat8Pi;
	}

	public void setCat8Pi(String cat8Pi) {
		this.cat8Pi = cat8Pi;
	}

	public String getCat9Aoc() {
		return cat9Aoc;
	}

	public void setCat9Aoc(String cat9Aoc) {
		this.cat9Aoc = cat9Aoc;
	}

	public String getCat9Pi() {
		return cat9Pi;
	}

	public void setCat9Pi(String cat9Pi) {
		this.cat9Pi = cat9Pi;
	}

	public String getCat10Dtls() {
		return cat10Dtls;
	}

	public void setCat10Dtls(String cat10Dtls) {
		this.cat10Dtls = cat10Dtls;
	}

	public String getCat10Aoc() {
		return cat10Aoc;
	}

	public void setCat10Aoc(String cat10Aoc) {
		this.cat10Aoc = cat10Aoc;
	}

	public String getCat10Pi() {
		return cat10Pi;
	}

	public void setCat10Pi(String cat10Pi) {
		this.cat10Pi = cat10Pi;
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
