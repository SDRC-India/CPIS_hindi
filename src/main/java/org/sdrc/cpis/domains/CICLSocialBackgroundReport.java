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

/*FORM 1
 * 
 * 
 * @author Abhisheka Mishra*/

@Entity
@Table(name="cicl_social_background_report")
public class CICLSocialBackgroundReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;	
	
	
	/*-----NOT-NULLABLE ATTRIBUTES------*/
	
	@Column(name="Police_Station")
	private String policeStation;
	
	
	@Column(name="Name_of_io")
	private String nameOfIo;
	
	@Column(name="Name_of_CWPO")
	private String nameOfCwpo;
	
	@Column(name="Child_Name")
	private String childName;
	
	@Column(name="father_Name")
	private String fatherName;
	
	@Column(name="Mother_Name")
	private String motherName;
	
	@Column(name="Guardian_Name")
	private String guardianName;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="religion")
	private Integer religion;
	
	@Column(name="home_leaving_reason")
	private String homeLeavingReason;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="Fir_Number")
	private String firNumber;
	
	@Column(name="DD_Number")
	private String ddNumber;
	
	@Column(name="adhaar_card_no")
	String adhaarCardNo;
	
	@Column(name="education_details")
	private Integer educationDetails;
	
	@Column(name="majority_of_friends")
	private String majorityOfFriends;
	
	@Column(name="apprehended_circumstances")
	private String apprehendedCircumstances;
	
	@Column(name="articles_resolved")
	private String articlesRecovered;
	
	@Column(name="roll_of_child_in_offence")
	private String roleOfChildInOffence;
	
	@Column(name="suggetions")
	private String suggestions;
	
	@Column(name="child_drug_peddling")
	private boolean childDrugPeddling;
	
	/*-----NULLABLE ATTRIBUTES------*/
	
	@Column(name="GD_Number")
	private String gdNumber;
	
	@Column(name="Sections")
	private String sections;	

	
	@Column(name="Age")
	private Integer age;	
	
	@Column(name="sex")
	private Integer sex;
	
	@Column(name="entry_time")
	private Time entryTime;
	
	@Column(name="entry_date")
	private Date entryDate;
	
	@Column(name="caste_hindu_type")
	private Integer casteHinduType;
	
	@Column(name="caste_other_type")
	private String casteOtherType;
	
	@Column(name="Differently_abled")
	private boolean differentlyAbled;
	
	@Column(name="differently_abled_type")
	private String differentlyAbledType;
	
	@Column(name="other_differently_abled_type")
	private String otherDifferentlyAbledType;
	
	@Column(name="employment_details")
	private String employmentDetails;
	
	@Column(name="income_utilization_for_family")
	private boolean incomeUtilizationForFamily;
	
	@Column(name="used_by_family")
	private boolean usedByFamily;
	
	@Column(name="used_by_self")
	private boolean usedBySelf;
	
	@Column(name="used_by_self_dress")
	private boolean usedBySelfDress;
	
	@Column(name="used_by_self_gambling")
	private boolean usedBySelfGambling;
	
	@Column(name="used_by_self_alcohol")
	private boolean usedBySelfAlcohol;
	
	@Column(name="used_by_self_drug")
	private boolean usedBySelfDrug;
	
	@Column(name="used_by_self_smoking")
	private boolean usedBySelfSmoking;
	
	@Column(name="used_by_self_savings")
	private boolean usedBySelfSavings;	
	
	@Column(name="school_leaving_reason")
	private String schoolLeavingReason;
	
	@Column(name="other_school_leaving_reason")
	private String otherSchoolLeavingReason;
	
	@Column(name="school_details")
	private Integer schoolDetails;
	
	@Column(name="vocational_training")
	private String vocationalTraining;	
	
	@Column(name="abused")
	private boolean abused;
	
	@Column(name="other_abused")
	private String otherAbused;
	
	@Column(name="verbal_abuse")
	private String verbalAbuse;
	
	@Column(name="other_verbal_abuse")
	private String otherVerbalAbuse;
	
	@Column(name="physical_abuse")
	private String physicalAbuse;
	
	@Column(name="other_physical_abuse")
	private String otherPhysicalAbuse;
	
	@Column(name="sexual_abuse")
	private String sexualAbuse;
	
	@Column(name="other_sexual_abuse")
	private String otherSexualAbuse;
	
	@Column(name="other_abuse")
	private String otherAbuse;
	
	@Column(name="other_inother_abuse")
	private String otherInOtherAbuse;
	
	@Column(name="good_habits")
	private String goodHabits;
	
	@Column(name="other_good_habits")
	private String otherGoodHabits;
	
	@Column(name="bad_habits")
	private String badHabits;
	
	@Column(name="other_bad_habits")
	private String otherBadHabits;
	
	@Column(name="other_drug_badHabits")
	private String otherDrugBadHabits;
	
	@Column(name="other_child_Drug_Peddling")
	private String otherChildDrugPeddling;
	
	@Column(name="victim_of_offence")
	private boolean victimOfOffence;
	
	@Column(name="alleged_offence")
	private String allegedOffence;
	
	@Column(name="alleged_role")
	private String allegedRole;
	
	@Column(name="other_victim_of_offence")
	private String otherVictimOfOffence;
	
	@Column(name="family_involvement_in_offence")
	private boolean familyInvolvementInOffence;
	
	@Column(name="other_family_involvement_in_offence")
	private String otherFamilyInvolvementInOffence;
	
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
	
	@Column(name="form_lang")
	private String formLang;
	
	/*---------GETTERS & SETTERS STARTS HERE----------*/
	
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getFirNumber() {
		return firNumber;
	}
	public void setFirNumber(String firNumber) {
		this.firNumber = firNumber;
	}
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
	}
	public ChildDetails getChildId() {
		return childId;
	}
	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}
	
//	public List<CICLFamilyDetails> getFamilyDetails() {
//		return familyDetails;
//	}
//	public void setFamilyDetails(List<CICLFamilyDetails> familyDetails) {
//		this.familyDetails = familyDetails;
//	}
	
	public String getPoliceStation() {
		return policeStation;
	}
	public Time getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Time entryTime) {
		this.entryTime = entryTime;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public boolean isUsedByFamily() {
		return usedByFamily;
	}
	public void setUsedByFamily(boolean usedByFamily) {
		this.usedByFamily = usedByFamily;
	}
	public boolean getAbused() {
		return abused;
	}
	public void setAbused(boolean abused) {
		this.abused = abused;
	}
	public String getVerbalAbuse() {
		return verbalAbuse;
	}
	public void setVerbalAbuse(String verbalAbuse) {
		this.verbalAbuse = verbalAbuse;
	}
	public String getOtherVerbalAbuse() {
		return otherVerbalAbuse;
	}
	public void setOtherVerbalAbuse(String otherVerbalAbuse) {
		this.otherVerbalAbuse = otherVerbalAbuse;
	}
	public String getPhysicalAbuse() {
		return physicalAbuse;
	}
	public void setPhysicalAbuse(String physicalAbuse) {
		this.physicalAbuse = physicalAbuse;
	}
	public String getOtherPhysicalAbuse() {
		return otherPhysicalAbuse;
	}
	public void setOtherPhysicalAbuse(String otherPhysicalAbuse) {
		this.otherPhysicalAbuse = otherPhysicalAbuse;
	}
	public String getSexualAbuse() {
		return sexualAbuse;
	}
	public void setSexualAbuse(String sexualAbuse) {
		this.sexualAbuse = sexualAbuse;
	}
	public String getOtherSexualAbuse() {
		return otherSexualAbuse;
	}
	public void setOtherSexualAbuse(String otherSexualAbuse) {
		this.otherSexualAbuse = otherSexualAbuse;
	}
	public String getOtherAbuse() {
		return otherAbuse;
	}
	public void setOtherAbuse(String otherAbuse) {
		this.otherAbuse = otherAbuse;
	}
	
	public String getOtherInOtherAbuse() {
		return otherInOtherAbuse;
	}
	public void setOtherInOtherAbuse(String otherInOtherAbuse) {
		this.otherInOtherAbuse = otherInOtherAbuse;
	}
	public String getOtherDifferentlyAbledType() {
		return otherDifferentlyAbledType;
	}
	public void setOtherDifferentlyAbledType(String otherDifferentlyAbledType) {
		this.otherDifferentlyAbledType = otherDifferentlyAbledType;
	}
	public String getGoodHabits() {
		return goodHabits;
	}
	public void setGoodHabits(String goodHabits) {
		this.goodHabits = goodHabits;
	}
	public String getOtherGoodHabits() {
		return otherGoodHabits;
	}
	public void setOtherGoodHabits(String otherGoodHabits) {
		this.otherGoodHabits = otherGoodHabits;
	}
	public String getBadHabits() {
		return badHabits;
	}
	
	
	public String getOtherDrugBadHabits() {
		return otherDrugBadHabits;
	}
	public void setOtherDrugBadHabits(String otherDrugBadHabits) {
		this.otherDrugBadHabits = otherDrugBadHabits;
	}
	public void setBadHabits(String badHabits) {
		this.badHabits = badHabits;
	}
	public String getOtherBadHabits() {
		return otherBadHabits;
	}
	public void setOtherBadHabits(String otherBadHabits) {
		this.otherBadHabits = otherBadHabits;
	}
	public String getAllegedRole() {
		return allegedRole;
	}
	public void setAllegedRole(String allegedRole) {
		this.allegedRole = allegedRole;
	}
	public Integer getReligion() {
		return religion;
	}
	public void setReligion(Integer religion) {
		this.religion = religion;
	}
	public Integer getCasteHinduType() {
		return casteHinduType;
	}
	public void setCasteHinduType(Integer casteHinduType) {
		this.casteHinduType = casteHinduType;
	}
	public String getCasteOtherType() {
		return casteOtherType;
	}
	public void setCasteOtherType(String casteOtherType) {
		this.casteOtherType = casteOtherType;
	}
	
	public boolean isFamilyInvolvementInOffence() {
		return familyInvolvementInOffence;
	}
	public void setFamilyInvolvementInOffence(boolean familyInvolvementInOffence) {
		this.familyInvolvementInOffence = familyInvolvementInOffence;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	
	public String getNameOfIo() {
		return nameOfIo;
	}
	
	public String getDifferentlyAbledType() {
		return differentlyAbledType;
	}
	public void setDifferentlyAbledType(String differentlyAbledType) {
		this.differentlyAbledType = differentlyAbledType;
	}
	public void setNameOfIo(String nameOfIo) {
		this.nameOfIo = nameOfIo;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	
	public Integer getAge() {
		return age;
	}
	public String getOtherSchoolLeavingReason() {
		return otherSchoolLeavingReason;
	}
	public void setOtherSchoolLeavingReason(String otherSchoolLeavingReason) {
		this.otherSchoolLeavingReason = otherSchoolLeavingReason;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDdNumber() {
		return ddNumber;
	}
	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}
	public String getGdNumber() {
		return gdNumber;
	}
	public void setGdNumber(String gdNumber) {
		this.gdNumber = gdNumber;
	}
	

	
	public String getHomeLeavingReason() {
		return homeLeavingReason;
	}
	
	public void setHomeLeavingReason(String homeLeavingReason) {
		this.homeLeavingReason = homeLeavingReason;
	}
	
	public String getEmploymentDetails() {
		return employmentDetails;
	}
	public void setEmploymentDetails(String employmentDetails) {
		this.employmentDetails = employmentDetails;
	}
	public boolean isIncomeUtilizationForFamily() {
		return incomeUtilizationForFamily;
	}
	public void setIncomeUtilizationForFamily(boolean incomeUtilizationForFamily) {
		this.incomeUtilizationForFamily = incomeUtilizationForFamily;
	}
	public boolean isUsedBySelf() {
		return usedBySelf;
	}
	
	public String getNameOfCwpo() {
		return nameOfCwpo;
	}
	public void setNameOfCwpo(String nameOfCwpo) {
		this.nameOfCwpo = nameOfCwpo;
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
	public void setUsedBySelf(boolean usedBySelf) {
		this.usedBySelf = usedBySelf;
	}
	public boolean isUsedBySelfDress() {
		return usedBySelfDress;
	}
	public void setUsedBySelfDress(boolean usedBySelfDress) {
		this.usedBySelfDress = usedBySelfDress;
	}
	public boolean isUsedBySelfGambling() {
		return usedBySelfGambling;
	}
	public void setUsedBySelfGambling(boolean usedBySelfGambling) {
		this.usedBySelfGambling = usedBySelfGambling;
	}
	public boolean isUsedBySelfAlcohol() {
		return usedBySelfAlcohol;
	}
	public void setUsedBySelfAlcohol(boolean usedBySelfAlcohol) {
		this.usedBySelfAlcohol = usedBySelfAlcohol;
	}
	public boolean isUsedBySelfDrug() {
		return usedBySelfDrug;
	}
	public void setUsedBySelfDrug(boolean usedBySelfDrug) {
		this.usedBySelfDrug = usedBySelfDrug;
	}
	public boolean isUsedBySelfSmoking() {
		return usedBySelfSmoking;
	}
	public void setUsedBySelfSmoking(boolean usedBySelfSmoking) {
		this.usedBySelfSmoking = usedBySelfSmoking;
	}
	public boolean isUsedBySelfSavings() {
		return usedBySelfSavings;
	}
	public void setUsedBySelfSavings(boolean usedBySelfSavings) {
		this.usedBySelfSavings = usedBySelfSavings;
	}
	
	public String getSchoolLeavingReason() {
		return schoolLeavingReason;
	}
	public void setSchoolLeavingReason(String schoolLeavingReason) {
		this.schoolLeavingReason = schoolLeavingReason;
	}
	
	public Integer getEducationDetails() {
		return educationDetails;
	}
	public void setEducationDetails(Integer educationDetails) {
		this.educationDetails = educationDetails;
	}
	
	
	public boolean isDifferentlyAbled() {
		return differentlyAbled;
	}
	public void setDifferentlyAbled(boolean differentlyAbled) {
		this.differentlyAbled = differentlyAbled;
	}
	public String getOtherAbused() {
		return otherAbused;
	}
	public void setOtherAbused(String otherAbused) {
		this.otherAbused = otherAbused;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getSchoolDetails() {
		return schoolDetails;
	}
	public void setSchoolDetails(Integer schoolDetails) {
		this.schoolDetails = schoolDetails;
	}
	public String getVocationalTraining() {
		return vocationalTraining;
	}
	public void setVocationalTraining(String vocationalTraining) {
		this.vocationalTraining = vocationalTraining;
	}
	public String getMajorityOfFriends() {
		return majorityOfFriends;
	}
	public void setMajorityOfFriends(String majorityOfFriends) {
		this.majorityOfFriends = majorityOfFriends;
	}
	
	public boolean isVictimOfOffence() {
		return victimOfOffence;
	}
	public void setVictimOfOffence(boolean victimOfOffence) {
		this.victimOfOffence = victimOfOffence;
	}
	
	
	public String getAllegedOffence() {
		return allegedOffence;
	}
	
	public boolean isChildDrugPeddling() {
		return childDrugPeddling;
	}
	public void setChildDrugPeddling(boolean childDrugPeddling) {
		this.childDrugPeddling = childDrugPeddling;
	}
	public void setAllegedOffence(String allegedOffence) {
		this.allegedOffence = allegedOffence;
	}
	public String getApprehendedCircumstances() {
		return apprehendedCircumstances;
	}
	public void setApprehendedCircumstances(String apprehendedCircumstances) {
		this.apprehendedCircumstances = apprehendedCircumstances;
	}
	public String getArticlesRecovered() {
		return articlesRecovered;
	}
	public void setArticlesRecovered(String articlesRecovered) {
		this.articlesRecovered = articlesRecovered;
	}
	public String getRoleOfChildInOffence() {
		return roleOfChildInOffence;
	}
	public void setRoleOfChildInOffence(String roleOfChildInOffence) {
		this.roleOfChildInOffence = roleOfChildInOffence;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	
	public String getOtherChildDrugPeddling() {
		return otherChildDrugPeddling;
	}
	public void setOtherChildDrugPeddling(String otherChildDrugPeddling) {
		this.otherChildDrugPeddling = otherChildDrugPeddling;
	}
	public String getOtherVictimOfOffence() {
		return otherVictimOfOffence;
	}
	public void setOtherVictimOfOffence(String otherVictimOfOffence) {
		this.otherVictimOfOffence = otherVictimOfOffence;
	}
	public String getOtherFamilyInvolvementInOffence() {
		return otherFamilyInvolvementInOffence;
	}
	public void setOtherFamilyInvolvementInOffence(String otherFamilyInvolvementInOffence) {
		this.otherFamilyInvolvementInOffence = otherFamilyInvolvementInOffence;
	}
	public String getAdhaarCardNo() {
		return adhaarCardNo;
	}
	public void setAdhaarCardNo(String adhaarCardNo) {
		this.adhaarCardNo = adhaarCardNo;
	}
	public String getFormLang() {
		return formLang;
	}
	public void setFormLang(String formLang) {
		this.formLang = formLang;
	}
	
}
