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
@Table(name="cicl_social_investigation_report")
public class CICLSocialInvestigationReport implements Serializable{
	
	private static final long serialVersionUID = -3694333377303106056L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="sl_no")
	private String slNo;
	
	@Column(name="jjb_address")
	private String jjbAddress;
	
	@Column(name="cicl_org_type")
	private Integer ciclOrgType;
	
	@Column(name="name_of_person")
	private String nameOfPerson;
	
	@Column(name="fir_number")
	private String firNumber;

	@Column(name="dd_number")
	private String gdNumber;
	
	@Column(name="under_sections")
	private String underSections;
	
	@Column(name="police_station")
	private String policeStation;
	
	@Column(name="nature_of_offence")
	private Integer natureOfOffence;
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="child_age")
	private Integer childAge;
	
	@Column(name="child_sex")
	private Integer childSex;
	
	@Column(name="child_cast")
	private Integer childCast;
	
	@Column(name="child_religion")
	private Integer childReligion;
	
	@Column(name="child_religion_other")
	private String otherChildReligion;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="mother_name")
	private String motherName;
	
	@Column(name="guardian_name")
	private String guardianName;
	
	@Column(name="permanent_address")
	private String permanantAddress;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="last_residence_address")
	private String lastResidenceAddress;
	
	@Column(name="family_contact_no")
	private String familyMemberContactNo;
	
	@Column(name="child_differently_abled")
	private boolean childDifferentlyAbled;
	
	@Column(name="differently_abled_type")
	private String differentlyAbledType;
	
	@Column(name="mental_ill_severity")
	private String mentalIllSeverity;
	
	@Column(name="mental_retire_severity")
	private String mentalRetireSeverity;
	
	@Column(name="other_differently_abled")
	private String otherDifferentlyAbled;
		
	@Column(name="reln_father_mother")
	private Integer relnFatherMother;
	
	@Column(name="reln_father_child")
	private Integer relnFatherChild;
	
	@Column(name="reln_mother_child")
	private Integer relnMotherChild;
	
	@Column(name="reln_father_siblings")
	private Integer relnFatherSiblings;
	
	@Column(name="reln_mother_siblings")
	private Integer relnMotherSiblings;
	
	@Column(name="reln_child_siblings")
	private Integer relnChildSiblings;
	
	@Column(name="reln_child_grand_parent")
	private Integer relnChildGrandParent;
	
	@Column(name="is_child_married")
	private boolean childMarried;
	
	@Column(name="spouse_name")
	private String spouseName;
	
	@Column(name="spouse_age")
	private Integer spouseAge;
	
	@Column(name="spouse_details")
	private String spouseDetails;
	
	@Column(name="children_name1")
	private String childrenName1;
	
	@Column(name="children_age1")
	private Integer childrenAge1;
	
	@Column(name="children_sex1")
	private Integer childrenSex1;
	
	@Column(name="children_name2")
	private String childrenName2;
	
	@Column(name="children_age2")
	private Integer childrenAge2;
	
	@Column(name="children_sex2")
	private Integer childrenSex2;
	
	@Column(name="hoi_father_noc")
	private String hoiFathernoc;

	@Column(name="hoi_stepfather_noc")
	private String hoiStepFathernoc;
	
	@Column(name="hoi_mother_noc")
	private String hoiMothernoc;
	
	@Column(name="hoi_stepmother_noc")
	private String hoiStepMothernoc;
	
	@Column(name="hoi_brother_noc")
	private String hoiBrothernoc;
	
	@Column(name="hoi_sister_noc")
	private String hoiSisternoc;
	
	@Column(name="hoi_others_noc")
	private String hoiOthersnoc;
	///////////////////////////////ls
	@Column(name="hoi_father_ls")
	private String hoiFatherLs;

	@Column(name="hoi_stepfather_ls")
	private String hoiStepFatherLs;
	
	@Column(name="hoi_mother_ls")
	private String hoiMotherLs;
	
	@Column(name="hoi_stepmother_ls")
	private String hoiStepMotherLs;
	
	@Column(name="hoi_brother_ls")
	private String hoiBrotherLs;
	
	@Column(name="hoi_sister_ls")
	private String hoiSisterLs;
	
	@Column(name="hoi_others_ls")
	private String hoiOthersLs;
	/////////////////////////////ar
	@Column(name="hoi_father_ar")
	private boolean hoiFatherAr;

	@Column(name="hoi_stepfather_ar")
	private boolean hoiStepFatherAr;
	
	@Column(name="hoi_mother_ar")
	private boolean hoiMotherAr;
	
	@Column(name="hoi_stepmother_ar")
	private boolean hoiStepMotherAr;
	
	@Column(name="hoi_brother_ar")
	private boolean hoiBrotherAr;
	
	@Column(name="hoi_sister_ar")
	private boolean hoiSisterAr;
	
	@Column(name="hoi_others_ar")
	private boolean hoiOthersAr;
	/////////////////////////////poc
	@Column(name="hoi_father_poc")
	private String hoiFatherPoc;

	@Column(name="hoi_stepfather_poc")
	private String hoiStepFatherPoc;
	
	@Column(name="hoi_mother_poc")
	private String hoiMotherPoc;
	
	@Column(name="hoi_stepmother_poc")
	private String hoiStepMotherPoc;
	
	@Column(name="hoi_brother_poc")
	private String hoiBrotherPoc;
	
	@Column(name="hoi_sister_poc")
	private String hoiSisterPoc;
	
	@Column(name="hoi_others_poc")
	private String hoiOthersPoc;
	//////////////////////////////pa
	@Column(name="hoi_father_pa")
	private String hoiFatherPa;

	@Column(name="hoi_stepfather_pa")
	private String hoiStepFatherPa;
	
	@Column(name="hoi_mother_pa")
	private String hoiMotherPa;
	
	@Column(name="hoi_stepmother_pa")
	private String hoiStepMotherPa;
	
	@Column(name="hoi_brother_pa")
	private String hoiBrotherPa;
	
	@Column(name="hoi_sister_pa")
	private String hoiSisterPa;
	
	@Column(name="hoi_others_pa")
	private String hoiOthersPa;
	
	@Column(name="hoi_other_family_member_name")
	private String hoiOtherFamilyMemberName;
	
	@Column(name="religion_attitude")
	private String religionAttitude;
	
	@Column(name="living_conditions")
	private String livingConditions;
	
	@Column(name="other_factors_importance")
	private String otherFactorImportance;
	
	@Column(name="good_habits")
	private String goodHabits;
	
	@Column(name="other_good_habits")
	private String otherGoodHabits;
	
	@Column(name="bad_habits")
	private String badHabits;
	
	@Column(name="drug_type")
	private String drugType;
	
	@Column(name="other_bad_habits")
	private String otherBadHabits;
	
	@Column(name="extra_curricular_interests")
	private String extracurricularInterests;
	
	@Column(name="personality_traits")
	private String personalityTraits;
	
	@Column(name="child_opinion_towards_discipline")
	private String childOpinionTowardsDiscipline;
	
	@Column(name="employment_details")
	private String employmentDetails;
	
	@Column(name="income_utilization")
	private String incomeUtilization;
	
	@Column(name="work_record")
	private String workRecord;
	
	@Column(name="education")
	private Integer education;
	
	@Column(name="class_mates_attitude")
	private String classMatesAttitude;
	
	@Column(name="teachers_attitude")
	private String teachersAttitude;
	
	@Column(name="reason_leaving_school")
	private String reasonLeavingSchool;
	
	@Column(name="other_reason_leaving_school")
	private String otherReasonLeavingSchool;
	
	@Column(name="school_type_studied_last")
	private Integer schoolTypeStudiedLast;
	
	@Column(name="vocational_training")
	private String vocationalTraining;
	
	@Column(name="majority_friend_types")
	private String majorityFriendTypes;
	
	@Column(name="attitude_towards_friends")
	private String attitudeTowardsFriends;
	
	@Column(name="friends_attitude_towards_child")
	private String friendsAttitudeTowardsChild;
	
	@Column(name="observation_neighbourhood")
	private String observationAboutNeighbourhood;
	
	@Column(name="observation_neighbourhood_to_asses")
	private String observationAboutNeighbourhoodToAsses;
	
	@Column(name="child_subjected_of_abuse")
	private boolean childSubjectedOfAbuse;
	
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
	
	@Column(name="other_in_other_abuse")
	private String otherInOtherAbuse;
	
	@Column(name="is_child_victim")
	private boolean childVictim;
	
	@Column(name="used_by_any_gang")
	private boolean usedByAnyGang;
	
	@Column(name="history_run_away_from_home")
	private String historyRunAwayFromHome;
	
	@Column(name="circumstances_of_apprehension")
	private String circumstancesOfApprehension;
	
	@Column(name="alleged_role_in_offence")
	private String allegedRoleInOffence;
	
	@Column(name="reason_for_alleged_offence")
	private String reasonForAllegedOffence;
	
	@Column(name="other_reason_for_alleged_offence")
	private String otherReasonForAllegedOffence;
	
	@Column(name="apprehended_for_offence")
	private boolean apprehendedForOffence;
	
	@Column(name="apprehended_for_offence_dtls")
	private String apprehendedForOffenceDtls;
	
	@Column(name="institution_doc_type")
	private Integer institutionDocType;
	
	@Column(name="physical_appearance")
	private String physicalAppearance;
	
	@Column(name="health_condition")
	private String healthCondition;
	
	@Column(name="mental_condition")
	private String mentalCondition;
	
	@Column(name="any_other_remark")
	private String anyOtherRemark;
	
	@Column(name="roi_emotional_factors")
	private String roiEmotionalFactors;
	
	@Column(name="roi_physical_condition")
	private String roiPhysicalCondition;
	
	@Column(name="roi_intelligence")
	private String roiIntelligence;
	
	@Column(name="roi_social_economic_factors")
	private String roiSocialEconomicFactors;
	
	@Column(name="roi_suggestive_causes_problems")
	private String roiSuggestiveCausesProblems;
	
	@Column(name="roi_analysis_of_case")
	private String roiAnalysisOfCase;
	
	@Column(name="roi_reasons_for_care_protection")
	private String roiReasonsForCareProtection;
	
	@Column(name="roi_opinion_experts")
	private String roiOpinionExperts;
	
	@Column(name="roi_recommendation")
	private String roiRecommendation;
	
	@Column(name="child_photo")
	private String childPhoto;

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
	
	@Column(name="account_holder_name")
	private String accountholdername;
	
	@Column(name="bank_name")
	private String bankname;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="ifsc_code")
	private String ifsccode;
	
	@Column(name="bank_account_no")
	private String bankAccountNo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getJjbAddress() {
		return jjbAddress;
	}

	public void setJjbAddress(String jjbAddress) {
		this.jjbAddress = jjbAddress;
	}

	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}

	public String getFirNumber() {
		return firNumber;
	}

	public void setFirNumber(String firNumber) {
		this.firNumber = firNumber;
	}

	public String getGdNumber() {
		return gdNumber;
	}

	public void setGdNumber(String gdNumber) {
		this.gdNumber = gdNumber;
	}

	public String getUnderSections() {
		return underSections;
	}

	public void setUnderSections(String underSections) {
		this.underSections = underSections;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public Integer getNatureOfOffence() {
		return natureOfOffence;
	}

	public void setNatureOfOffence(Integer natureOfOffence) {
		this.natureOfOffence = natureOfOffence;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Integer getChildAge() {
		return childAge;
	}

	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}

	public Integer getChildSex() {
		return childSex;
	}

	public void setChildSex(Integer childSex) {
		this.childSex = childSex;
	}

	public Integer getChildCast() {
		return childCast;
	}

	public void setChildCast(Integer childCast) {
		this.childCast = childCast;
	}

	public Integer getChildReligion() {
		return childReligion;
	}

	public void setChildReligion(Integer childReligion) {
		this.childReligion = childReligion;
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

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getPermanantAddress() {
		return permanantAddress;
	}

	public void setPermanantAddress(String permanantAddress) {
		this.permanantAddress = permanantAddress;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getLastResidenceAddress() {
		return lastResidenceAddress;
	}

	public void setLastResidenceAddress(String lastResidenceAddress) {
		this.lastResidenceAddress = lastResidenceAddress;
	}

	public String getFamilyMemberContactNo() {
		return familyMemberContactNo;
	}

	public void setFamilyMemberContactNo(String familyMemberContactNo) {
		this.familyMemberContactNo = familyMemberContactNo;
	}

	public boolean isChildDifferentlyAbled() {
		return childDifferentlyAbled;
	}

	public void setChildDifferentlyAbled(boolean childDifferentlyAbled) {
		this.childDifferentlyAbled = childDifferentlyAbled;
	}

	public String getDifferentlyAbledType() {
		return differentlyAbledType;
	}

	public void setDifferentlyAbledType(String differentlyAbledType) {
		this.differentlyAbledType = differentlyAbledType;
	}

	public String getOtherDifferentlyAbled() {
		return otherDifferentlyAbled;
	}

	public void setOtherDifferentlyAbled(String otherDifferentlyAbled) {
		this.otherDifferentlyAbled = otherDifferentlyAbled;
	}

	public Integer getRelnFatherMother() {
		return relnFatherMother;
	}

	public void setRelnFatherMother(Integer relnFatherMother) {
		this.relnFatherMother = relnFatherMother;
	}

	public Integer getRelnFatherChild() {
		return relnFatherChild;
	}

	public void setRelnFatherChild(Integer relnFatherChild) {
		this.relnFatherChild = relnFatherChild;
	}

	public Integer getRelnMotherChild() {
		return relnMotherChild;
	}

	public void setRelnMotherChild(Integer relnMotherChild) {
		this.relnMotherChild = relnMotherChild;
	}

	public Integer getRelnFatherSiblings() {
		return relnFatherSiblings;
	}

	public void setRelnFatherSiblings(Integer relnFatherSiblings) {
		this.relnFatherSiblings = relnFatherSiblings;
	}

	public Integer getRelnMotherSiblings() {
		return relnMotherSiblings;
	}

	public void setRelnMotherSiblings(Integer relnMotherSiblings) {
		this.relnMotherSiblings = relnMotherSiblings;
	}

	public Integer getRelnChildSiblings() {
		return relnChildSiblings;
	}

	public void setRelnChildSiblings(Integer relnChildSiblings) {
		this.relnChildSiblings = relnChildSiblings;
	}

	public Integer getRelnChildGrandParent() {
		return relnChildGrandParent;
	}

	public void setRelnChildGrandParent(Integer relnChildGrandParent) {
		this.relnChildGrandParent = relnChildGrandParent;
	}

	public boolean isChildMarried() {
		return childMarried;
	}

	public void setChildMarried(boolean childMarried) {
		this.childMarried = childMarried;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Integer getSpouseAge() {
		return spouseAge;
	}

	public void setSpouseAge(Integer spouseAge) {
		this.spouseAge = spouseAge;
	}

	public String getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(String spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	public String getChildrenName1() {
		return childrenName1;
	}

	public void setChildrenName1(String childrenName1) {
		this.childrenName1 = childrenName1;
	}

	public Integer getChildrenAge1() {
		return childrenAge1;
	}

	public void setChildrenAge1(Integer childrenAge1) {
		this.childrenAge1 = childrenAge1;
	}

	public Integer getChildrenSex1() {
		return childrenSex1;
	}

	public void setChildrenSex1(Integer childrenSex1) {
		this.childrenSex1 = childrenSex1;
	}

	public String getChildrenName2() {
		return childrenName2;
	}

	public void setChildrenName2(String childrenName2) {
		this.childrenName2 = childrenName2;
	}

	public Integer getChildrenAge2() {
		return childrenAge2;
	}

	public void setChildrenAge2(Integer childrenAge2) {
		this.childrenAge2 = childrenAge2;
	}

	public Integer getChildrenSex2() {
		return childrenSex2;
	}

	public void setChildrenSex2(Integer childrenSex2) {
		this.childrenSex2 = childrenSex2;
	}

	public String getReligionAttitude() {
		return religionAttitude;
	}

	public void setReligionAttitude(String religionAttitude) {
		this.religionAttitude = religionAttitude;
	}

	public String getLivingConditions() {
		return livingConditions;
	}

	public void setLivingConditions(String livingConditions) {
		this.livingConditions = livingConditions;
	}

	public String getOtherFactorImportance() {
		return otherFactorImportance;
	}

	public void setOtherFactorImportance(String otherFactorImportance) {
		this.otherFactorImportance = otherFactorImportance;
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

	public void setBadHabits(String badHabits) {
		this.badHabits = badHabits;
	}

	public String getOtherBadHabits() {
		return otherBadHabits;
	}

	public void setOtherBadHabits(String otherBadHabits) {
		this.otherBadHabits = otherBadHabits;
	}

	public String getExtracurricularInterests() {
		return extracurricularInterests;
	}

	public void setExtracurricularInterests(String extracurricularInterests) {
		this.extracurricularInterests = extracurricularInterests;
	}

	public String getPersonalityTraits() {
		return personalityTraits;
	}

	public void setPersonalityTraits(String personalityTraits) {
		this.personalityTraits = personalityTraits;
	}

	public String getChildOpinionTowardsDiscipline() {
		return childOpinionTowardsDiscipline;
	}

	public void setChildOpinionTowardsDiscipline(
			String childOpinionTowardsDiscipline) {
		this.childOpinionTowardsDiscipline = childOpinionTowardsDiscipline;
	}

	public String getEmploymentDetails() {
		return employmentDetails;
	}

	public void setEmploymentDetails(String employmentDetails) {
		this.employmentDetails = employmentDetails;
	}

	public String getIncomeUtilization() {
		return incomeUtilization;
	}

	public void setIncomeUtilization(String incomeUtilization) {
		this.incomeUtilization = incomeUtilization;
	}

	public String getWorkRecord() {
		return workRecord;
	}

	public void setWorkRecord(String workRecord) {
		this.workRecord = workRecord;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getClassMatesAttitude() {
		return classMatesAttitude;
	}

	public void setClassMatesAttitude(String classMatesAttitude) {
		this.classMatesAttitude = classMatesAttitude;
	}

	public String getTeachersAttitude() {
		return teachersAttitude;
	}

	public void setTeachersAttitude(String teachersAttitude) {
		this.teachersAttitude = teachersAttitude;
	}

	public String getReasonLeavingSchool() {
		return reasonLeavingSchool;
	}

	public void setReasonLeavingSchool(String reasonLeavingSchool) {
		this.reasonLeavingSchool = reasonLeavingSchool;
	}

	public String getOtherReasonLeavingSchool() {
		return otherReasonLeavingSchool;
	}

	public void setOtherReasonLeavingSchool(String otherReasonLeavingSchool) {
		this.otherReasonLeavingSchool = otherReasonLeavingSchool;
	}

	public Integer getSchoolTypeStudiedLast() {
		return schoolTypeStudiedLast;
	}

	public void setSchoolTypeStudiedLast(Integer schoolTypeStudiedLast) {
		this.schoolTypeStudiedLast = schoolTypeStudiedLast;
	}

	public String getVocationalTraining() {
		return vocationalTraining;
	}

	public void setVocationalTraining(String vocationalTraining) {
		this.vocationalTraining = vocationalTraining;
	}

	public String getMajorityFriendTypes() {
		return majorityFriendTypes;
	}

	public void setMajorityFriendTypes(String majorityFriendTypes) {
		this.majorityFriendTypes = majorityFriendTypes;
	}

	public String getAttitudeTowardsFriends() {
		return attitudeTowardsFriends;
	}

	public void setAttitudeTowardsFriends(String attitudeTowardsFriends) {
		this.attitudeTowardsFriends = attitudeTowardsFriends;
	}

	public String getFriendsAttitudeTowardsChild() {
		return friendsAttitudeTowardsChild;
	}

	public void setFriendsAttitudeTowardsChild(String friendsAttitudeTowardsChild) {
		this.friendsAttitudeTowardsChild = friendsAttitudeTowardsChild;
	}

	public String getObservationAboutNeighbourhood() {
		return observationAboutNeighbourhood;
	}

	public void setObservationAboutNeighbourhood(
			String observationAboutNeighbourhood) {
		this.observationAboutNeighbourhood = observationAboutNeighbourhood;
	}

	public String getObservationAboutNeighbourhoodToAsses() {
		return observationAboutNeighbourhoodToAsses;
	}

	public void setObservationAboutNeighbourhoodToAsses(
			String observationAboutNeighbourhoodToAsses) {
		this.observationAboutNeighbourhoodToAsses = observationAboutNeighbourhoodToAsses;
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

	public boolean isChildVictim() {
		return childVictim;
	}

	public void setChildVictim(boolean childVictim) {
		this.childVictim = childVictim;
	}

	public boolean isUsedByAnyGang() {
		return usedByAnyGang;
	}

	public void setUsedByAnyGang(boolean usedByAnyGang) {
		this.usedByAnyGang = usedByAnyGang;
	}

	public String getHistoryRunAwayFromHome() {
		return historyRunAwayFromHome;
	}

	public void setHistoryRunAwayFromHome(String historyRunAwayFromHome) {
		this.historyRunAwayFromHome = historyRunAwayFromHome;
	}

	public String getCircumstancesOfApprehension() {
		return circumstancesOfApprehension;
	}

	public void setCircumstancesOfApprehension(String circumstancesOfApprehension) {
		this.circumstancesOfApprehension = circumstancesOfApprehension;
	}

	public String getAllegedRoleInOffence() {
		return allegedRoleInOffence;
	}

	public void setAllegedRoleInOffence(String allegedRoleInOffence) {
		this.allegedRoleInOffence = allegedRoleInOffence;
	}

	public boolean isApprehendedForOffence() {
		return apprehendedForOffence;
	}

	public void setApprehendedForOffence(boolean apprehendedForOffence) {
		this.apprehendedForOffence = apprehendedForOffence;
	}

	public String getApprehendedForOffenceDtls() {
		return apprehendedForOffenceDtls;
	}

	public void setApprehendedForOffenceDtls(String apprehendedForOffenceDtls) {
		this.apprehendedForOffenceDtls = apprehendedForOffenceDtls;
	}

	public Integer getInstitutionDocType() {
		return institutionDocType;
	}

	public void setInstitutionDocType(Integer institutionDocType) {
		this.institutionDocType = institutionDocType;
	}

	public String getPhysicalAppearance() {
		return physicalAppearance;
	}

	public void setPhysicalAppearance(String physicalAppearance) {
		this.physicalAppearance = physicalAppearance;
	}

	public String getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}

	public String getMentalCondition() {
		return mentalCondition;
	}

	public void setMentalCondition(String mentalCondition) {
		this.mentalCondition = mentalCondition;
	}

	public String getAnyOtherRemark() {
		return anyOtherRemark;
	}

	public void setAnyOtherRemark(String anyOtherRemark) {
		this.anyOtherRemark = anyOtherRemark;
	}

	public String getRoiEmotionalFactors() {
		return roiEmotionalFactors;
	}

	public void setRoiEmotionalFactors(String roiEmotionalFactors) {
		this.roiEmotionalFactors = roiEmotionalFactors;
	}

	public String getRoiPhysicalCondition() {
		return roiPhysicalCondition;
	}

	public void setRoiPhysicalCondition(String roiPhysicalCondition) {
		this.roiPhysicalCondition = roiPhysicalCondition;
	}

	public String getRoiIntelligence() {
		return roiIntelligence;
	}

	public void setRoiIntelligence(String roiIntelligence) {
		this.roiIntelligence = roiIntelligence;
	}

	public String getRoiSocialEconomicFactors() {
		return roiSocialEconomicFactors;
	}

	public void setRoiSocialEconomicFactors(String roiSocialEconomicFactors) {
		this.roiSocialEconomicFactors = roiSocialEconomicFactors;
	}

	public String getRoiSuggestiveCausesProblems() {
		return roiSuggestiveCausesProblems;
	}

	public void setRoiSuggestiveCausesProblems(String roiSuggestiveCausesProblems) {
		this.roiSuggestiveCausesProblems = roiSuggestiveCausesProblems;
	}

	public String getRoiAnalysisOfCase() {
		return roiAnalysisOfCase;
	}

	public void setRoiAnalysisOfCase(String roiAnalysisOfCase) {
		this.roiAnalysisOfCase = roiAnalysisOfCase;
	}

	public String getRoiReasonsForCareProtection() {
		return roiReasonsForCareProtection;
	}

	public void setRoiReasonsForCareProtection(String roiReasonsForCareProtection) {
		this.roiReasonsForCareProtection = roiReasonsForCareProtection;
	}

	public String getRoiOpinionExperts() {
		return roiOpinionExperts;
	}

	public void setRoiOpinionExperts(String roiOpinionExperts) {
		this.roiOpinionExperts = roiOpinionExperts;
	}

	public String getRoiRecommendation() {
		return roiRecommendation;
	}

	public void setRoiRecommendation(String roiRecommendation) {
		this.roiRecommendation = roiRecommendation;
	}

	public Integer getCiclOrgType() {
		return ciclOrgType;
	}

	public void setCiclOrgType(Integer ciclOrgType) {
		this.ciclOrgType = ciclOrgType;
	}

	public String getHoiFathernoc() {
		return hoiFathernoc;
	}

	public void setHoiFathernoc(String hoiFathernoc) {
		this.hoiFathernoc = hoiFathernoc;
	}

	public String getHoiStepFathernoc() {
		return hoiStepFathernoc;
	}

	public void setHoiStepFathernoc(String hoiStepFathernoc) {
		this.hoiStepFathernoc = hoiStepFathernoc;
	}

	public String getHoiMothernoc() {
		return hoiMothernoc;
	}

	public void setHoiMothernoc(String hoiMothernoc) {
		this.hoiMothernoc = hoiMothernoc;
	}

	public String getHoiStepMothernoc() {
		return hoiStepMothernoc;
	}

	public void setHoiStepMothernoc(String hoiStepMothernoc) {
		this.hoiStepMothernoc = hoiStepMothernoc;
	}

	public String getHoiBrothernoc() {
		return hoiBrothernoc;
	}

	public void setHoiBrothernoc(String hoiBrothernoc) {
		this.hoiBrothernoc = hoiBrothernoc;
	}

	public String getHoiSisternoc() {
		return hoiSisternoc;
	}

	public void setHoiSisternoc(String hoiSisternoc) {
		this.hoiSisternoc = hoiSisternoc;
	}

	public String getHoiOthersnoc() {
		return hoiOthersnoc;
	}

	public void setHoiOthersnoc(String hoiOthersnoc) {
		this.hoiOthersnoc = hoiOthersnoc;
	}

	public String getHoiFatherLs() {
		return hoiFatherLs;
	}

	public void setHoiFatherLs(String hoiFatherLs) {
		this.hoiFatherLs = hoiFatherLs;
	}

	public String getHoiStepFatherLs() {
		return hoiStepFatherLs;
	}

	public void setHoiStepFatherLs(String hoiStepFatherLs) {
		this.hoiStepFatherLs = hoiStepFatherLs;
	}

	public String getHoiMotherLs() {
		return hoiMotherLs;
	}

	public void setHoiMotherLs(String hoiMotherLs) {
		this.hoiMotherLs = hoiMotherLs;
	}

	public String getHoiStepMotherLs() {
		return hoiStepMotherLs;
	}

	public void setHoiStepMotherLs(String hoiStepMotherLs) {
		this.hoiStepMotherLs = hoiStepMotherLs;
	}

	public String getHoiBrotherLs() {
		return hoiBrotherLs;
	}

	public void setHoiBrotherLs(String hoiBrotherLs) {
		this.hoiBrotherLs = hoiBrotherLs;
	}

	public String getHoiSisterLs() {
		return hoiSisterLs;
	}

	public void setHoiSisterLs(String hoiSisterLs) {
		this.hoiSisterLs = hoiSisterLs;
	}

	public String getHoiOthersLs() {
		return hoiOthersLs;
	}

	public void setHoiOthersLs(String hoiOthersLs) {
		this.hoiOthersLs = hoiOthersLs;
	}

	public boolean isHoiFatherAr() {
		return hoiFatherAr;
	}

	public void setHoiFatherAr(boolean hoiFatherAr) {
		this.hoiFatherAr = hoiFatherAr;
	}

	public boolean isHoiStepFatherAr() {
		return hoiStepFatherAr;
	}

	public void setHoiStepFatherAr(boolean hoiStepFatherAr) {
		this.hoiStepFatherAr = hoiStepFatherAr;
	}

	public boolean isHoiMotherAr() {
		return hoiMotherAr;
	}

	public void setHoiMotherAr(boolean hoiMotherAr) {
		this.hoiMotherAr = hoiMotherAr;
	}

	public boolean isHoiStepMotherAr() {
		return hoiStepMotherAr;
	}

	public void setHoiStepMotherAr(boolean hoiStepMotherAr) {
		this.hoiStepMotherAr = hoiStepMotherAr;
	}

	public boolean isHoiBrotherAr() {
		return hoiBrotherAr;
	}

	public void setHoiBrotherAr(boolean hoiBrotherAr) {
		this.hoiBrotherAr = hoiBrotherAr;
	}

	public boolean isHoiSisterAr() {
		return hoiSisterAr;
	}

	public void setHoiSisterAr(boolean hoiSisterAr) {
		this.hoiSisterAr = hoiSisterAr;
	}

	public boolean isHoiOthersAr() {
		return hoiOthersAr;
	}

	public void setHoiOthersAr(boolean hoiOthersAr) {
		this.hoiOthersAr = hoiOthersAr;
	}

	public String getHoiFatherPoc() {
		return hoiFatherPoc;
	}

	public void setHoiFatherPoc(String hoiFatherPoc) {
		this.hoiFatherPoc = hoiFatherPoc;
	}

	public String getHoiStepFatherPoc() {
		return hoiStepFatherPoc;
	}

	public void setHoiStepFatherPoc(String hoiStepFatherPoc) {
		this.hoiStepFatherPoc = hoiStepFatherPoc;
	}

	public String getHoiMotherPoc() {
		return hoiMotherPoc;
	}

	public void setHoiMotherPoc(String hoiMotherPoc) {
		this.hoiMotherPoc = hoiMotherPoc;
	}

	public String getHoiStepMotherPoc() {
		return hoiStepMotherPoc;
	}

	public void setHoiStepMotherPoc(String hoiStepMotherPoc) {
		this.hoiStepMotherPoc = hoiStepMotherPoc;
	}

	public String getHoiBrotherPoc() {
		return hoiBrotherPoc;
	}

	public void setHoiBrotherPoc(String hoiBrotherPoc) {
		this.hoiBrotherPoc = hoiBrotherPoc;
	}

	public String getHoiSisterPoc() {
		return hoiSisterPoc;
	}

	public void setHoiSisterPoc(String hoiSisterPoc) {
		this.hoiSisterPoc = hoiSisterPoc;
	}

	public String getHoiOthersPoc() {
		return hoiOthersPoc;
	}

	public void setHoiOthersPoc(String hoiOthersPoc) {
		this.hoiOthersPoc = hoiOthersPoc;
	}

	public String getHoiFatherPa() {
		return hoiFatherPa;
	}

	public void setHoiFatherPa(String hoiFatherPa) {
		this.hoiFatherPa = hoiFatherPa;
	}

	public String getHoiStepFatherPa() {
		return hoiStepFatherPa;
	}

	public void setHoiStepFatherPa(String hoiStepFatherPa) {
		this.hoiStepFatherPa = hoiStepFatherPa;
	}

	public String getHoiMotherPa() {
		return hoiMotherPa;
	}

	public void setHoiMotherPa(String hoiMotherPa) {
		this.hoiMotherPa = hoiMotherPa;
	}

	public String getHoiStepMotherPa() {
		return hoiStepMotherPa;
	}

	public void setHoiStepMotherPa(String hoiStepMotherPa) {
		this.hoiStepMotherPa = hoiStepMotherPa;
	}

	public String getHoiBrotherPa() {
		return hoiBrotherPa;
	}

	public void setHoiBrotherPa(String hoiBrotherPa) {
		this.hoiBrotherPa = hoiBrotherPa;
	}

	public String getHoiSisterPa() {
		return hoiSisterPa;
	}

	public void setHoiSisterPa(String hoiSisterPa) {
		this.hoiSisterPa = hoiSisterPa;
	}

	public String getHoiOthersPa() {
		return hoiOthersPa;
	}

	public void setHoiOthersPa(String hoiOthersPa) {
		this.hoiOthersPa = hoiOthersPa;
	}

	public String getHoiOtherFamilyMemberName() {
		return hoiOtherFamilyMemberName;
	}

	public void setHoiOtherFamilyMemberName(String hoiOtherFamilyMemberName) {
		this.hoiOtherFamilyMemberName = hoiOtherFamilyMemberName;
	}

	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}

	public String getReasonForAllegedOffence() {
		return reasonForAllegedOffence;
	}

	public void setReasonForAllegedOffence(String reasonForAllegedOffence) {
		this.reasonForAllegedOffence = reasonForAllegedOffence;
	}

	public String getOtherReasonForAllegedOffence() {
		return otherReasonForAllegedOffence;
	}

	public void setOtherReasonForAllegedOffence(String otherReasonForAllegedOffence) {
		this.otherReasonForAllegedOffence = otherReasonForAllegedOffence;
	}

	public boolean isChildSubjectedOfAbuse() {
		return childSubjectedOfAbuse;
	}

	public void setChildSubjectedOfAbuse(boolean childSubjectedOfAbuse) {
		this.childSubjectedOfAbuse = childSubjectedOfAbuse;
	}

	public String getChildPhoto() {
		return childPhoto;
	}

	public void setChildPhoto(String childPhoto) {
		this.childPhoto = childPhoto;
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

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getOtherChildReligion() {
		return otherChildReligion;
	}

	public void setOtherChildReligion(String otherChildReligion) {
		this.otherChildReligion = otherChildReligion;
	}

	public String getMentalIllSeverity() {
		return mentalIllSeverity;
	}

	public void setMentalIllSeverity(String mentalIllSeverity) {
		this.mentalIllSeverity = mentalIllSeverity;
	}

	public String getMentalRetireSeverity() {
		return mentalRetireSeverity;
	}

	public void setMentalRetireSeverity(String mentalRetireSeverity) {
		this.mentalRetireSeverity = mentalRetireSeverity;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getAccountholdername() {
		return accountholdername;
	}

	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	
}
