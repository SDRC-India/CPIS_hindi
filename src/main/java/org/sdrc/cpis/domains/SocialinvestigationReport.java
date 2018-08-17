package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Pratyush
 */
@Entity
@Table(name="social_investigation_report")
public class SocialinvestigationReport implements Serializable {

	private static final long serialVersionUID = 7223095102988653268L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="sl_no")
	private String slNo;
	
//	@Column(name="case_no", unique=true, nullable = false)
//	private String caseNo;
	
	@Column(name="report_prepared_by")
	private Integer reportPreparedBy;
	
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
	///////////////////////////////////
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
	
	@Column(name="reln_child_relative")
	private Integer relnChildRelative;
	///////////////////////////////////
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
	
	@Column(name="education")
	private Integer education;
	
	@Column(name="school_type")
	private Integer schoolType;
	
	@Column(name="class_mates_attitude")
	private String classMatesAttitude;
	
	@Column(name="teachers_attitude")
	private String teachersAttitude;
	
	@Column(name="reason_leaving_school")
	private String reasonLeavingSchool;
	
	@Column(name="other_reason_leaving_school")
	private String otherReasonLeavingSchool;
	
	@Column(name="vocational_training")
	private String vocationalTraining;
	
	@Column(name="employment_details")
	private String employmentDetails;
	
	@Column(name="income_utilization_dtls")
	private String incomeUtilizationDtls;
	
	@Column(name="work_record")
	private String workRecord;
	
	@Column(name="majority_friend_types")
	private String majorityFriendTypes;
	
	@Column(name="attitude_towards_friends")
	private String attitudeTowardsFriends;
	
	@Column(name="friends_attitude_towards_child")
	private String friendsAttitudeTowardsChild;
	
	@Column(name="observation_neighbourhood")
	private String observationAboutNeighbourhood;
	
	@Column(name="mental_condition")
	private String mentalCondition;
	
	@Column(name="physical_condition")
	private String physicalCondition;
	//hs stands for health status
	@Column(name="hs_respiratory_disorders")
	private Integer hsRespiratoryDisorders;
	
	@Column(name="hs_hearing_impairment")
	private Integer hsHearingImpairment;
	
	@Column(name="hs_eye_diseases")
	private Integer hsEyeDiseases;
	
	@Column(name="hs_dental_disease")
	private Integer hsDentalDisease;
	
	@Column(name="hs_cardiac_diseases")
	private Integer hsCardiacDiseases;
	
	@Column(name="hs_skin_disease")
	private Integer hsSkinDisease;
	
	@Column(name="hs_std")
	private Integer hsSTD;
	
	@Column(name="hs_neurological_disorders")
	private Integer hsNeurologicalDisorders;
	
	@Column(name="hs_mental_handicap")
	private Integer hsMentalHandicap;
	
	@Column(name="hs_physical_handicap")
	private Integer hsPhysicalHandicap;
	
	@Column(name="hs_urinary_tract_infections")
	private Integer hsUrinaryTractInfections;
	
	@Column(name="hs_other_health_status_name")
	private String hsotherHealthStatusName;

	@Column(name="child_has_addiction")
	private boolean childHasAddiction;
	
	@Column(name="with_whom_child_was_staying")
	private Integer withWhomChildWasStaying;
	
	@Column(name="other_with_whom_child_was_staying")
	private String otherWithWhomChildWasStaying;
	
	@Column(name="history_run_away_from_home")
	private String historyRunAwayFromHome;
	
	@Column(name="parents_attitude_child_reaction")
	private String parentsAttitudeChildReaction;
	
	@Column(name="reasons_leaving_family")
	private String reasonsLeavingFamily;
	
	@Column(name="other_reasons_leaving_family")
	private String otherReasonsLeavingFamily;
	
	@Column(name="is_child_victim")
	private boolean childVictim;
	
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
	
	@Column(name="ill_treatment_denial_of_food")
	private String illTreatmentDenialOfFood;
	
	@Column(name="ill_treatment_other_denial_of_food")
	private String otherIllTreatmentDenialOfFood;
	
	@Column(name="ill_treatment_beaten_mercilessly")
	private String illTreatmentBeatenMercilessly;
	
	@Column(name="ill_treatment_other_beaten_mercilessly")
	private String otherIllTreatmentBeatenMercilessly;
	
	@Column(name="ill_treatment_causing_injury")
	private String illTreatmentCausingInjury;
	
	@Column(name="ill_treatment_other_causing_injury")
	private String otherIllTreatmentCausingInjury;
	
	@Column(name="ill_treatment_detention")
	private String illTreatmentDetention;
	
	@Column(name="ill_treatment_other_detention")
	private String otherIllTreatmentDetention;
	
	@Column(name="ill_treatment_other")
	private String otherIllTreatment;
	
	@Column(name="ill_treatment_other_in_other")
	private String otherInOtherIllTreatment;
	
	@Column(name="exploitation_faced")
	private String exploitationFaced;
	
	@Column(name="other_exploitation_faced")
	private String otherExploitationFaced;
	
	@Column(name="bought_sold_procured_trafficked")
	private boolean boughtSoldProcuredTrafficked;
	
	@Column(name="used_for_begging")
	private boolean usedForBegging;
	
	@Column(name="used_by_any_gang")
	private boolean usedByAnyGang;
	
	@Column(name="institution_doc_type")
	private Integer institutionDocType;
	////////////////////////////////////////////
	@Column(name="perpetrator_name")
	private String perpetratorName;
	
	@Column(name="perpetrator_age")
	private Integer perpetratorAge;
	
	@Column(name="perpetrator_contact")
	private String perpetratorContact;
	
	@Column(name="perpetrator_address")
	private String perpetratorAddress;
	
	@Column(name="perpetrator_physical_characteristics")
	private String perpetratorPhysicalCharacteristics;
	
	@Column(name="perpetrator_reln_with_family")
	private String perpetratorRelnWithFamily;
	
	@Column(name="perpetrator_middle_men_involved")
	private boolean perpetratorMiddleMenInvolved;
	
	@Column(name="perpetrator_other_child_abused")
	private boolean perpetratorOtherChildAbused;
	
	@Column(name="perpetrator_how_child_came")
	private String perpetratorHowChildCame;
	
	////////////////////////////////////////////
	@Column(name="attitude_towards_perpetrator")
	private String attitudeTowardsPerpetrator;
	
	@Column(name="police_informed")
	private boolean policeInformed;
	
	@Column(name="action_against_perpetrator")
	private String actionAgainstPerpetrator;
	
	@Column(name="any_other_remark")
	private String anyOtherRemark;
	
	@Column(name="ooi_emotional_factors")
	private String ooiEmotionalFactors;
	
	@Column(name="ooi_physical_condition")
	private String ooiPhysicalCondition;
	
	@Column(name="ooi_intelligence")
	private String ooiIntelligence;
	
	@Column(name="ooi_social_economic_factors")
	private String ooiSocialEconomicFactors;
	
	@Column(name="ooi_suggestive_causes_problems")
	private String ooiSuggestiveCausesProblems;
	
	@Column(name="ooi_analysis_of_case")
	private String ooiAnalysisOfCase;
	
	@Column(name="ooi_reasons_for_care_protection")
	private String ooiReasonsForCareProtection;
	
	@Column(name="ooi_opinion_experts")
	private String ooiOpinionExperts;
	
	@Column(name="ooi_psycho_social_assessment")
	private String ooiPsychoSocialAssessment;
	
	@Column(name="ooi_religious_factors")
	private String ooiReligiousFactors;
	
	@Column(name="ooi_risk_analysis")
	private String ooiRiskAnalysis;

	@Column(name="ooi_recommendation")
	private String ooiRecommendation;
	//hoi stands for History of involvement of family members in offences
	//noc = Nature of Crime, ls = Legal status of the case, ar = Arrest if any Made, poc = Period of	Confinement, pa = Punishment awarded
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
	
	@Column(name="account_holder_name")
	private String accountholdername;
	
	@Column(name="bank_name")
	private String bankname;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="ifsc_code")
	private String ifsccode;
	
	@Column(name="bank_account_no")
	private String bankAccountno;
	
	@OneToMany(mappedBy="socialinvestigationReport")
	List<SocialInvestigationReportFamilyDetails> socialInvestigationReportFamilyDetails;

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

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
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

	public Integer getRelnChildRelative() {
		return relnChildRelative;
	}

	public void setRelnChildRelative(Integer relnChildRelative) {
		this.relnChildRelative = relnChildRelative;
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

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
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

	public String getVocationalTraining() {
		return vocationalTraining;
	}

	public void setVocationalTraining(String vocationalTraining) {
		this.vocationalTraining = vocationalTraining;
	}

	public String getEmploymentDetails() {
		return employmentDetails;
	}

	public void setEmploymentDetails(String employmentDetails) {
		this.employmentDetails = employmentDetails;
	}

	public String getIncomeUtilizationDtls() {
		return incomeUtilizationDtls;
	}

	public void setIncomeUtilizationDtls(String incomeUtilizationDtls) {
		this.incomeUtilizationDtls = incomeUtilizationDtls;
	}

	public String getWorkRecord() {
		return workRecord;
	}

	public void setWorkRecord(String workRecord) {
		this.workRecord = workRecord;
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

	public String getMentalCondition() {
		return mentalCondition;
	}

	public void setMentalCondition(String mentalCondition) {
		this.mentalCondition = mentalCondition;
	}

	public String getPhysicalCondition() {
		return physicalCondition;
	}

	public void setPhysicalCondition(String physicalCondition) {
		this.physicalCondition = physicalCondition;
	}

	public Integer getHsRespiratoryDisorders() {
		return hsRespiratoryDisorders;
	}

	public void setHsRespiratoryDisorders(Integer hsRespiratoryDisorders) {
		this.hsRespiratoryDisorders = hsRespiratoryDisorders;
	}

	public Integer getHsHearingImpairment() {
		return hsHearingImpairment;
	}

	public void setHsHearingImpairment(Integer hsHearingImpairment) {
		this.hsHearingImpairment = hsHearingImpairment;
	}

	public Integer getHsEyeDiseases() {
		return hsEyeDiseases;
	}

	public void setHsEyeDiseases(Integer hsEyeDiseases) {
		this.hsEyeDiseases = hsEyeDiseases;
	}

	public Integer getHsDentalDisease() {
		return hsDentalDisease;
	}

	public void setHsDentalDisease(Integer hsDentalDisease) {
		this.hsDentalDisease = hsDentalDisease;
	}

	public Integer getHsCardiacDiseases() {
		return hsCardiacDiseases;
	}

	public void setHsCardiacDiseases(Integer hsCardiacDiseases) {
		this.hsCardiacDiseases = hsCardiacDiseases;
	}

	public Integer getHsSkinDisease() {
		return hsSkinDisease;
	}

	public void setHsSkinDisease(Integer hsSkinDisease) {
		this.hsSkinDisease = hsSkinDisease;
	}

	public Integer getHsSTD() {
		return hsSTD;
	}

	public void setHsSTD(Integer hsSTD) {
		this.hsSTD = hsSTD;
	}

	public Integer getHsNeurologicalDisorders() {
		return hsNeurologicalDisorders;
	}

	public void setHsNeurologicalDisorders(Integer hsNeurologicalDisorders) {
		this.hsNeurologicalDisorders = hsNeurologicalDisorders;
	}

	public Integer getHsMentalHandicap() {
		return hsMentalHandicap;
	}

	public void setHsMentalHandicap(Integer hsMentalHandicap) {
		this.hsMentalHandicap = hsMentalHandicap;
	}

	public Integer getHsPhysicalHandicap() {
		return hsPhysicalHandicap;
	}

	public void setHsPhysicalHandicap(Integer hsPhysicalHandicap) {
		this.hsPhysicalHandicap = hsPhysicalHandicap;
	}

	public Integer getHsUrinaryTractInfections() {
		return hsUrinaryTractInfections;
	}

	public void setHsUrinaryTractInfections(Integer hsUrinaryTractInfections) {
		this.hsUrinaryTractInfections = hsUrinaryTractInfections;
	}

	public String getHsotherHealthStatusName() {
		return hsotherHealthStatusName;
	}

	public void setHsotherHealthStatusName(String hsotherHealthStatusName) {
		this.hsotherHealthStatusName = hsotherHealthStatusName;
	}

	public boolean isChildHasAddiction() {
		return childHasAddiction;
	}

	public void setChildHasAddiction(boolean childHasAddiction) {
		this.childHasAddiction = childHasAddiction;
	}

	public Integer getWithWhomChildWasStaying() {
		return withWhomChildWasStaying;
	}

	public void setWithWhomChildWasStaying(Integer withWhomChildWasStaying) {
		this.withWhomChildWasStaying = withWhomChildWasStaying;
	}

	public String getOtherWithWhomChildWasStaying() {
		return otherWithWhomChildWasStaying;
	}

	public void setOtherWithWhomChildWasStaying(String otherWithWhomChildWasStaying) {
		this.otherWithWhomChildWasStaying = otherWithWhomChildWasStaying;
	}

	public String getHistoryRunAwayFromHome() {
		return historyRunAwayFromHome;
	}

	public void setHistoryRunAwayFromHome(String historyRunAwayFromHome) {
		this.historyRunAwayFromHome = historyRunAwayFromHome;
	}

	public String getParentsAttitudeChildReaction() {
		return parentsAttitudeChildReaction;
	}

	public void setParentsAttitudeChildReaction(String parentsAttitudeChildReaction) {
		this.parentsAttitudeChildReaction = parentsAttitudeChildReaction;
	}

	public String getReasonsLeavingFamily() {
		return reasonsLeavingFamily;
	}

	public void setReasonsLeavingFamily(String reasonsLeavingFamily) {
		this.reasonsLeavingFamily = reasonsLeavingFamily;
	}

	public String getOtherReasonsLeavingFamily() {
		return otherReasonsLeavingFamily;
	}

	public void setOtherReasonsLeavingFamily(String otherReasonsLeavingFamily) {
		this.otherReasonsLeavingFamily = otherReasonsLeavingFamily;
	}

	public boolean isChildVictim() {
		return childVictim;
	}

	public void setChildVictim(boolean childVictim) {
		this.childVictim = childVictim;
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

	public String getIllTreatmentDenialOfFood() {
		return illTreatmentDenialOfFood;
	}

	public void setIllTreatmentDenialOfFood(String illTreatmentDenialOfFood) {
		this.illTreatmentDenialOfFood = illTreatmentDenialOfFood;
	}

	public String getOtherIllTreatmentDenialOfFood() {
		return otherIllTreatmentDenialOfFood;
	}

	public void setOtherIllTreatmentDenialOfFood(
			String otherIllTreatmentDenialOfFood) {
		this.otherIllTreatmentDenialOfFood = otherIllTreatmentDenialOfFood;
	}

	public String getIllTreatmentBeatenMercilessly() {
		return illTreatmentBeatenMercilessly;
	}

	public void setIllTreatmentBeatenMercilessly(
			String illTreatmentBeatenMercilessly) {
		this.illTreatmentBeatenMercilessly = illTreatmentBeatenMercilessly;
	}

	public String getOtherIllTreatmentBeatenMercilessly() {
		return otherIllTreatmentBeatenMercilessly;
	}

	public void setOtherIllTreatmentBeatenMercilessly(
			String otherIllTreatmentBeatenMercilessly) {
		this.otherIllTreatmentBeatenMercilessly = otherIllTreatmentBeatenMercilessly;
	}

	public String getIllTreatmentCausingInjury() {
		return illTreatmentCausingInjury;
	}

	public void setIllTreatmentCausingInjury(String illTreatmentCausingInjury) {
		this.illTreatmentCausingInjury = illTreatmentCausingInjury;
	}

	public String getOtherIllTreatmentCausingInjury() {
		return otherIllTreatmentCausingInjury;
	}

	public void setOtherIllTreatmentCausingInjury(
			String otherIllTreatmentCausingInjury) {
		this.otherIllTreatmentCausingInjury = otherIllTreatmentCausingInjury;
	}

	public String getIllTreatmentDetention() {
		return illTreatmentDetention;
	}

	public void setIllTreatmentDetention(String illTreatmentDetention) {
		this.illTreatmentDetention = illTreatmentDetention;
	}

	public String getOtherIllTreatmentDetention() {
		return otherIllTreatmentDetention;
	}

	public void setOtherIllTreatmentDetention(String otherIllTreatmentDetention) {
		this.otherIllTreatmentDetention = otherIllTreatmentDetention;
	}

	public String getOtherIllTreatment() {
		return otherIllTreatment;
	}

	public void setOtherIllTreatment(String otherIllTreatment) {
		this.otherIllTreatment = otherIllTreatment;
	}

	public String getOtherInOtherIllTreatment() {
		return otherInOtherIllTreatment;
	}

	public void setOtherInOtherIllTreatment(String otherInOtherIllTreatment) {
		this.otherInOtherIllTreatment = otherInOtherIllTreatment;
	}

	public String getExploitationFaced() {
		return exploitationFaced;
	}

	public void setExploitationFaced(String exploitationFaced) {
		this.exploitationFaced = exploitationFaced;
	}

	public String getOtherExploitationFaced() {
		return otherExploitationFaced;
	}

	public void setOtherExploitationFaced(String otherExploitationFaced) {
		this.otherExploitationFaced = otherExploitationFaced;
	}

	public boolean isBoughtSoldProcuredTrafficked() {
		return boughtSoldProcuredTrafficked;
	}

	public void setBoughtSoldProcuredTrafficked(boolean boughtSoldProcuredTrafficked) {
		this.boughtSoldProcuredTrafficked = boughtSoldProcuredTrafficked;
	}

	public boolean isUsedForBegging() {
		return usedForBegging;
	}

	public void setUsedForBegging(boolean usedForBegging) {
		this.usedForBegging = usedForBegging;
	}

	public boolean isUsedByAnyGang() {
		return usedByAnyGang;
	}

	public void setUsedByAnyGang(boolean usedByAnyGang) {
		this.usedByAnyGang = usedByAnyGang;
	}

	public Integer getInstitutionDocType() {
		return institutionDocType;
	}

	public void setInstitutionDocType(Integer institutionDocType) {
		this.institutionDocType = institutionDocType;
	}

	public String getPerpetratorName() {
		return perpetratorName;
	}

	public void setPerpetratorName(String perpetratorName) {
		this.perpetratorName = perpetratorName;
	}

	public Integer getPerpetratorAge() {
		return perpetratorAge;
	}

	public void setPerpetratorAge(Integer perpetratorAge) {
		this.perpetratorAge = perpetratorAge;
	}

	public String getPerpetratorContact() {
		return perpetratorContact;
	}

	public void setPerpetratorContact(String perpetratorContact) {
		this.perpetratorContact = perpetratorContact;
	}

	public String getPerpetratorAddress() {
		return perpetratorAddress;
	}

	public void setPerpetratorAddress(String perpetratorAddress) {
		this.perpetratorAddress = perpetratorAddress;
	}

	public String getPerpetratorPhysicalCharacteristics() {
		return perpetratorPhysicalCharacteristics;
	}

	public void setPerpetratorPhysicalCharacteristics(
			String perpetratorPhysicalCharacteristics) {
		this.perpetratorPhysicalCharacteristics = perpetratorPhysicalCharacteristics;
	}

	public String getPerpetratorRelnWithFamily() {
		return perpetratorRelnWithFamily;
	}

	public void setPerpetratorRelnWithFamily(String perpetratorRelnWithFamily) {
		this.perpetratorRelnWithFamily = perpetratorRelnWithFamily;
	}

	public boolean isPerpetratorMiddleMenInvolved() {
		return perpetratorMiddleMenInvolved;
	}

	public void setPerpetratorMiddleMenInvolved(boolean perpetratorMiddleMenInvolved) {
		this.perpetratorMiddleMenInvolved = perpetratorMiddleMenInvolved;
	}

	public boolean isPerpetratorOtherChildAbused() {
		return perpetratorOtherChildAbused;
	}

	public void setPerpetratorOtherChildAbused(boolean perpetratorOtherChildAbused) {
		this.perpetratorOtherChildAbused = perpetratorOtherChildAbused;
	}

	public String getPerpetratorHowChildCame() {
		return perpetratorHowChildCame;
	}

	public void setPerpetratorHowChildCame(String perpetratorHowChildCame) {
		this.perpetratorHowChildCame = perpetratorHowChildCame;
	}

	public String getAttitudeTowardsPerpetrator() {
		return attitudeTowardsPerpetrator;
	}

	public void setAttitudeTowardsPerpetrator(String attitudeTowardsPerpetrator) {
		this.attitudeTowardsPerpetrator = attitudeTowardsPerpetrator;
	}

	public boolean isPoliceInformed() {
		return policeInformed;
	}

	public void setPoliceInformed(boolean policeInformed) {
		this.policeInformed = policeInformed;
	}

	public String getActionAgainstPerpetrator() {
		return actionAgainstPerpetrator;
	}

	public void setActionAgainstPerpetrator(String actionAgainstPerpetrator) {
		this.actionAgainstPerpetrator = actionAgainstPerpetrator;
	}

	public String getAnyOtherRemark() {
		return anyOtherRemark;
	}

	public void setAnyOtherRemark(String anyOtherRemark) {
		this.anyOtherRemark = anyOtherRemark;
	}

	public String getOoiEmotionalFactors() {
		return ooiEmotionalFactors;
	}

	public void setOoiEmotionalFactors(String ooiEmotionalFactors) {
		this.ooiEmotionalFactors = ooiEmotionalFactors;
	}

	public String getOoiPhysicalCondition() {
		return ooiPhysicalCondition;
	}

	public void setOoiPhysicalCondition(String ooiPhysicalCondition) {
		this.ooiPhysicalCondition = ooiPhysicalCondition;
	}

	public String getOoiIntelligence() {
		return ooiIntelligence;
	}

	public void setOoiIntelligence(String ooiIntelligence) {
		this.ooiIntelligence = ooiIntelligence;
	}

	public String getOoiSocialEconomicFactors() {
		return ooiSocialEconomicFactors;
	}

	public void setOoiSocialEconomicFactors(String ooiSocialEconomicFactors) {
		this.ooiSocialEconomicFactors = ooiSocialEconomicFactors;
	}

	public String getOoiSuggestiveCausesProblems() {
		return ooiSuggestiveCausesProblems;
	}

	public void setOoiSuggestiveCausesProblems(String ooiSuggestiveCausesProblems) {
		this.ooiSuggestiveCausesProblems = ooiSuggestiveCausesProblems;
	}

	public String getOoiAnalysisOfCase() {
		return ooiAnalysisOfCase;
	}

	public void setOoiAnalysisOfCase(String ooiAnalysisOfCase) {
		this.ooiAnalysisOfCase = ooiAnalysisOfCase;
	}

	public String getOoiReasonsForCareProtection() {
		return ooiReasonsForCareProtection;
	}

	public void setOoiReasonsForCareProtection(String ooiReasonsForCareProtection) {
		this.ooiReasonsForCareProtection = ooiReasonsForCareProtection;
	}

	public String getOoiOpinionExperts() {
		return ooiOpinionExperts;
	}

	public void setOoiOpinionExperts(String ooiOpinionExperts) {
		this.ooiOpinionExperts = ooiOpinionExperts;
	}

	public String getOoiPsychoSocialAssessment() {
		return ooiPsychoSocialAssessment;
	}

	public void setOoiPsychoSocialAssessment(String ooiPsychoSocialAssessment) {
		this.ooiPsychoSocialAssessment = ooiPsychoSocialAssessment;
	}

	public String getOoiReligiousFactors() {
		return ooiReligiousFactors;
	}

	public void setOoiReligiousFactors(String ooiReligiousFactors) {
		this.ooiReligiousFactors = ooiReligiousFactors;
	}

	public String getOoiRiskAnalysis() {
		return ooiRiskAnalysis;
	}

	public void setOoiRiskAnalysis(String ooiRiskAnalysis) {
		this.ooiRiskAnalysis = ooiRiskAnalysis;
	}

	public String getOoiRecommendation() {
		return ooiRecommendation;
	}

	public void setOoiRecommendation(String ooiRecommendation) {
		this.ooiRecommendation = ooiRecommendation;
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

	public List<SocialInvestigationReportFamilyDetails> getSocialInvestigationReportFamilyDetails() {
		return socialInvestigationReportFamilyDetails;
	}

	public void setSocialInvestigationReportFamilyDetails(
			List<SocialInvestigationReportFamilyDetails> socialInvestigationReportFamilyDetails) {
		this.socialInvestigationReportFamilyDetails = socialInvestigationReportFamilyDetails;
	}

	public String getOtherDifferentlyAbled() {
		return otherDifferentlyAbled;
	}

	public void setOtherDifferentlyAbled(String otherDifferentlyAbled) {
		this.otherDifferentlyAbled = otherDifferentlyAbled;
	}

	public Integer getReportPreparedBy() {
		return reportPreparedBy;
	}

	public void setReportPreparedBy(Integer reportPreparedBy) {
		this.reportPreparedBy = reportPreparedBy;
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

	public String getBankAccountno() {
		return bankAccountno;
	}

	public void setBankAccountno(String bankAccountno) {
		this.bankAccountno = bankAccountno;
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