package org.sdrc.cpis.models;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.util.ValueObject;

public class SocialinvestigationReportModel {

	private Integer id;
	private String childId;
	private String slNo;
	private String caseNo;
	private String cwcName;
	private ValueObject reportPreparedBy;
	private String childName;
	private Integer childAge;
	private ValueObject childSex;
	private ValueObject childCast;
	private ValueObject childReligion;
	private String otherChildReligion;
	private String fatherName;
	private String motherName;
	private String guardianName;
	private String permanantAddress;
	private String landmark;
	private String lastResidenceAddress;
	private String familyMemberContactNo;
	private boolean childDifferentlyAbled;
	private String differentlyAbledType;
	private String mentalIllSeverity;
	private String mentalRetireSeverity;
	private String otherDifferentlyAbled;
	private ValueObject relnFatherMother;
	private ValueObject relnFatherChild;
	private ValueObject relnMotherChild;
	private ValueObject relnFatherSiblings;
	private ValueObject relnMotherSiblings;
	private ValueObject relnChildSiblings;
	private ValueObject relnChildRelative;
	private boolean childMarried;
	private String spouseName;
	private Integer spouseAge;
	private String spouseDetails;
	private String childrenName1;
	private Integer childrenAge1;
	private ValueObject childrenSex1;
	private String childrenName2;
	private Integer childrenAge2;
	private ValueObject childrenSex2;
	private String religionAttitude;
	private String livingConditions;
	private String otherFactorImportance;
	private String goodHabits;
	private String otherGoodHabits;
	private String badHabits;
	private String drugType;
	private String otherBadHabits;
	private String extracurricularInterests;
	private String personalityTraits;
	private ValueObject education;
	private ValueObject schoolType;
	private String classMatesAttitude;
	private String teachersAttitude;
	private String reasonLeavingSchool;
	private String otherReasonLeavingSchool;
	private String vocationalTraining;
	private String employmentDetails;
	private String incomeUtilizationDtls;
	private String workRecord;
	private String majorityFriendTypes;
	private String attitudeTowardsFriends;
	private String friendsAttitudeTowardsChild;
	private String observationAboutNeighbourhood;
	private String mentalCondition;
	private String physicalCondition;
	private ValueObject hsRespiratoryDisorders;
	private ValueObject hsHearingImpairment;
	private ValueObject hsEyeDiseases;
	private ValueObject hsDentalDisease;
	private ValueObject hsCardiacDiseases;
	private ValueObject hsSkinDisease;
	private ValueObject hsSTD;
	private ValueObject hsNeurologicalDisorders;
	private ValueObject hsMentalHandicap;
	private ValueObject hsPhysicalHandicap;
	private ValueObject hsUrinaryTractInfections;
	private String hsotherHealthStatusName;
	private String otherHealthStatus;
	private boolean childHasAddiction;
	private ValueObject withWhomChildWasStaying;
	private String otherWithWhomChildWasStaying;
	private String historyRunAwayFromHome;
	private String parentsAttitudeChildReaction;
	private String reasonsLeavingFamily;
	private String otherReasonsLeavingFamily;
	private boolean childVictim;
	private String verbalAbuse;
	private String otherVerbalAbuse;
	private String physicalAbuse;
	private String otherPhysicalAbuse;
	private String sexualAbuse;
	private String otherSexualAbuse;
	private String otherAbuse;
	private String otherInOtherAbuse;
	private String illTreatmentDenialOfFood;
	private String otherIllTreatmentDenialOfFood;
	private String illTreatmentBeatenMercilessly;
	private String otherIllTreatmentBeatenMercilessly;
	private String illTreatmentCausingInjury;
	private String otherIllTreatmentCausingInjury;
	private String illTreatmentDetention;
	private String otherIllTreatmentDetention;//
	private String otherIllTreatment;//
	private String otherInOtherIllTreatment;
	private String exploitationFaced;
	private String otherExploitationFaced;
	private boolean boughtSoldProcuredTrafficked;
	private boolean usedForBegging;
	private boolean usedByAnyGang;
	private ValueObject institutionDocType;
	private String perpetratorName;
	private Integer perpetratorAge;
	private String perpetratorContact;
	private String perpetratorAddress;
	private String perpetratorPhysicalCharacteristics;
	private String perpetratorRelnWithFamily;
	private boolean perpetratorMiddleMenInvolved;
	private boolean perpetratorOtherChildAbused;
	private String perpetratorHowChildCame;
	private String attitudeTowardsPerpetrator;
	private boolean policeInformed;
	private String actionAgainstPerpetrator;
	private String anyOtherRemark;
	private String ooiEmotionalFactors;
	private String ooiPhysicalCondition;
	private String ooiIntelligence;
	private String ooiSocialEconomicFactors;
	private String ooiSuggestiveCausesProblems;
	private String ooiAnalysisOfCase;
	private String ooiReasonsForCareProtection;
	private String ooiOpinionExperts;
	private String ooiPsychoSocialAssessment;
	private String ooiReligiousFactors;
	private String ooiRiskAnalysis;
	private String ooiRecommendation;
	private List<SocialInvestigationReportFamilyDetailsModel> socialInvestigationReportFamilyDetailsModel;
	
	//hoi stands for History of involvement of family members in offences
	//noc = Nature of Crime, ls = Legal status of the case, ar = Arrest if any Made, poc = Period of	Confinement, pa = Punishment awarded
	private String hoiFathernoc;
	private String hoiStepFathernoc;
	private String hoiMothernoc;
	private String hoiStepMothernoc;
	private String hoiBrothernoc;
	private String hoiSisternoc;
	private String hoiOthersnoc;
	
	private String hoiFatherLs;
	private String hoiStepFatherLs;
	private String hoiMotherLs;
	private String hoiStepMotherLs;
	private String hoiBrotherLs;
	private String hoiSisterLs;
	private String hoiOthersLs;
	
	private boolean hoiFatherAr;
	private boolean hoiStepFatherAr;
	private boolean hoiMotherAr;
	private boolean hoiStepMotherAr;
	private boolean hoiBrotherAr;
	private boolean hoiSisterAr;
	private boolean hoiOthersAr;
	
	private String hoiFatherPoc;
	private String hoiStepFatherPoc;
	private String hoiMotherPoc;
	private String hoiStepMotherPoc;
	private String hoiBrotherPoc;
	private String hoiSisterPoc;
	private String hoiOthersPoc;
	
	private String hoiFatherPa;
	private String hoiStepFatherPa;
	private String hoiMotherPa;
	private String hoiStepMotherPa;
	private String hoiBrotherPa;
	private String hoiSisterPa;
	private String hoiOthersPa;
	
	private String hoiOtherFamilyMemberName;
	
	private String badNameString;
	private String bmIllTreatedNameString;
	private String ciIllTreatedNameString;
	private String differentlyAbledTypeString;
	private String dofIllTreatedNameString;
	private String dpIllTreatedNameString;
	private String exploitationFacedNameString;
	private String goodNameString;
	private String maorityNameString;
	private String physicalAbusedNameString;
	private String reasonForLeavingFamilyNameString;
	private String reasonNameString;
	private String sexualAbusedNameString;
	private String verbalAbusedNameString;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private String bankAccountNo; 
	private String accountholdername;
	private String bankname;
	private String branch;
	private String ifsccode;
	
	public String getCwcName() {
		return cwcName;
	}
	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
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
	public ValueObject getChildSex() {
		return childSex;
	}
	public void setChildSex(ValueObject childSex) {
		this.childSex = childSex;
	}
	public ValueObject getChildCast() {
		return childCast;
	}
	public void setChildCast(ValueObject childCast) {
		this.childCast = childCast;
	}
	public ValueObject getChildReligion() {
		return childReligion;
	}
	public void setChildReligion(ValueObject childReligion) {
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
	public ValueObject getRelnFatherMother() {
		return relnFatherMother;
	}
	public void setRelnFatherMother(ValueObject relnFatherMother) {
		this.relnFatherMother = relnFatherMother;
	}
	public ValueObject getRelnFatherChild() {
		return relnFatherChild;
	}
	public void setRelnFatherChild(ValueObject relnFatherChild) {
		this.relnFatherChild = relnFatherChild;
	}
	public ValueObject getRelnMotherChild() {
		return relnMotherChild;
	}
	public void setRelnMotherChild(ValueObject relnMotherChild) {
		this.relnMotherChild = relnMotherChild;
	}
	public ValueObject getRelnFatherSiblings() {
		return relnFatherSiblings;
	}
	public void setRelnFatherSiblings(ValueObject relnFatherSiblings) {
		this.relnFatherSiblings = relnFatherSiblings;
	}
	public ValueObject getRelnMotherSiblings() {
		return relnMotherSiblings;
	}
	public void setRelnMotherSiblings(ValueObject relnMotherSiblings) {
		this.relnMotherSiblings = relnMotherSiblings;
	}
	public ValueObject getRelnChildSiblings() {
		return relnChildSiblings;
	}
	public void setRelnChildSiblings(ValueObject relnChildSiblings) {
		this.relnChildSiblings = relnChildSiblings;
	}
	public ValueObject getRelnChildRelative() {
		return relnChildRelative;
	}
	public void setRelnChildRelative(ValueObject relnChildRelative) {
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
	public ValueObject getChildrenSex1() {
		return childrenSex1;
	}
	public void setChildrenSex1(ValueObject childrenSex1) {
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
	public ValueObject getChildrenSex2() {
		return childrenSex2;
	}
	public void setChildrenSex2(ValueObject childrenSex2) {
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
	public ValueObject getEducation() {
		return education;
	}
	public void setEducation(ValueObject education) {
		this.education = education;
	}
	public ValueObject getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(ValueObject schoolType) {
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
	public ValueObject getHsRespiratoryDisorders() {
		return hsRespiratoryDisorders;
	}
	public void setHsRespiratoryDisorders(ValueObject hsRespiratoryDisorders) {
		this.hsRespiratoryDisorders = hsRespiratoryDisorders;
	}
	public ValueObject getHsHearingImpairment() {
		return hsHearingImpairment;
	}
	public void setHsHearingImpairment(ValueObject hsHearingImpairment) {
		this.hsHearingImpairment = hsHearingImpairment;
	}
	public ValueObject getHsEyeDiseases() {
		return hsEyeDiseases;
	}
	public void setHsEyeDiseases(ValueObject hsEyeDiseases) {
		this.hsEyeDiseases = hsEyeDiseases;
	}
	public ValueObject getHsDentalDisease() {
		return hsDentalDisease;
	}
	public void setHsDentalDisease(ValueObject hsDentalDisease) {
		this.hsDentalDisease = hsDentalDisease;
	}
	public ValueObject getHsCardiacDiseases() {
		return hsCardiacDiseases;
	}
	public void setHsCardiacDiseases(ValueObject hsCardiacDiseases) {
		this.hsCardiacDiseases = hsCardiacDiseases;
	}
	public ValueObject getHsSkinDisease() {
		return hsSkinDisease;
	}
	public void setHsSkinDisease(ValueObject hsSkinDisease) {
		this.hsSkinDisease = hsSkinDisease;
	}
	public ValueObject getHsSTD() {
		return hsSTD;
	}
	public void setHsSTD(ValueObject hsSTD) {
		this.hsSTD = hsSTD;
	}
	public ValueObject getHsNeurologicalDisorders() {
		return hsNeurologicalDisorders;
	}
	public void setHsNeurologicalDisorders(ValueObject hsNeurologicalDisorders) {
		this.hsNeurologicalDisorders = hsNeurologicalDisorders;
	}
	public ValueObject getHsMentalHandicap() {
		return hsMentalHandicap;
	}
	public void setHsMentalHandicap(ValueObject hsMentalHandicap) {
		this.hsMentalHandicap = hsMentalHandicap;
	}
	public ValueObject getHsPhysicalHandicap() {
		return hsPhysicalHandicap;
	}
	public void setHsPhysicalHandicap(ValueObject hsPhysicalHandicap) {
		this.hsPhysicalHandicap = hsPhysicalHandicap;
	}
	public ValueObject getHsUrinaryTractInfections() {
		return hsUrinaryTractInfections;
	}
	public void setHsUrinaryTractInfections(ValueObject hsUrinaryTractInfections) {
		this.hsUrinaryTractInfections = hsUrinaryTractInfections;
	}
	public String getHsotherHealthStatusName() {
		return hsotherHealthStatusName;
	}
	public void setHsotherHealthStatusName(String hsotherHealthStatusName) {
		this.hsotherHealthStatusName = hsotherHealthStatusName;
	}
	public String getOtherHealthStatus() {
		return otherHealthStatus;
	}
	public void setOtherHealthStatus(String otherHealthStatus) {
		this.otherHealthStatus = otherHealthStatus;
	}
	public boolean isChildHasAddiction() {
		return childHasAddiction;
	}
	public void setChildHasAddiction(boolean childHasAddiction) {
		this.childHasAddiction = childHasAddiction;
	}
	public ValueObject getWithWhomChildWasStaying() {
		return withWhomChildWasStaying;
	}
	public void setWithWhomChildWasStaying(ValueObject withWhomChildWasStaying) {
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
	public ValueObject getInstitutionDocType() {
		return institutionDocType;
	}
	public void setInstitutionDocType(ValueObject institutionDocType) {
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
	public List<SocialInvestigationReportFamilyDetailsModel> getSocialInvestigationReportFamilyDetailsModel() {
		return socialInvestigationReportFamilyDetailsModel;
	}
	public void setSocialInvestigationReportFamilyDetailsModel(
			List<SocialInvestigationReportFamilyDetailsModel> socialInvestigationReportFamilyDetailsModel) {
		this.socialInvestigationReportFamilyDetailsModel = socialInvestigationReportFamilyDetailsModel;
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
	public String getBadNameString() {
		return badNameString;
	}
	public void setBadNameString(String badNameString) {
		this.badNameString = badNameString;
	}
	public String getBmIllTreatedNameString() {
		return bmIllTreatedNameString;
	}
	public void setBmIllTreatedNameString(String bmIllTreatedNameString) {
		this.bmIllTreatedNameString = bmIllTreatedNameString;
	}
	public String getCiIllTreatedNameString() {
		return ciIllTreatedNameString;
	}
	public void setCiIllTreatedNameString(String ciIllTreatedNameString) {
		this.ciIllTreatedNameString = ciIllTreatedNameString;
	}
	public String getDifferentlyAbledTypeString() {
		return differentlyAbledTypeString;
	}
	public void setDifferentlyAbledTypeString(String differentlyAbledTypeString) {
		this.differentlyAbledTypeString = differentlyAbledTypeString;
	}
	public String getDofIllTreatedNameString() {
		return dofIllTreatedNameString;
	}
	public void setDofIllTreatedNameString(String dofIllTreatedNameString) {
		this.dofIllTreatedNameString = dofIllTreatedNameString;
	}
	public String getDpIllTreatedNameString() {
		return dpIllTreatedNameString;
	}
	public void setDpIllTreatedNameString(String dpIllTreatedNameString) {
		this.dpIllTreatedNameString = dpIllTreatedNameString;
	}
	public String getExploitationFacedNameString() {
		return exploitationFacedNameString;
	}
	public void setExploitationFacedNameString(String exploitationFacedNameString) {
		this.exploitationFacedNameString = exploitationFacedNameString;
	}
	public String getGoodNameString() {
		return goodNameString;
	}
	public void setGoodNameString(String goodNameString) {
		this.goodNameString = goodNameString;
	}
	public String getMaorityNameString() {
		return maorityNameString;
	}
	public void setMaorityNameString(String maorityNameString) {
		this.maorityNameString = maorityNameString;
	}
	public String getPhysicalAbusedNameString() {
		return physicalAbusedNameString;
	}
	public void setPhysicalAbusedNameString(String physicalAbusedNameString) {
		this.physicalAbusedNameString = physicalAbusedNameString;
	}
	public String getReasonForLeavingFamilyNameString() {
		return reasonForLeavingFamilyNameString;
	}
	public void setReasonForLeavingFamilyNameString(
			String reasonForLeavingFamilyNameString) {
		this.reasonForLeavingFamilyNameString = reasonForLeavingFamilyNameString;
	}
	public String getReasonNameString() {
		return reasonNameString;
	}
	public void setReasonNameString(String reasonNameString) {
		this.reasonNameString = reasonNameString;
	}
	public String getSexualAbusedNameString() {
		return sexualAbusedNameString;
	}
	public void setSexualAbusedNameString(String sexualAbusedNameString) {
		this.sexualAbusedNameString = sexualAbusedNameString;
	}
	public String getVerbalAbusedNameString() {
		return verbalAbusedNameString;
	}
	public void setVerbalAbusedNameString(String verbalAbusedNameString) {
		this.verbalAbusedNameString = verbalAbusedNameString;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOtherDifferentlyAbled() {
		return otherDifferentlyAbled;
	}
	public void setOtherDifferentlyAbled(String otherDifferentlyAbled) {
		this.otherDifferentlyAbled = otherDifferentlyAbled;
	}
	public ValueObject getReportPreparedBy() {
		return reportPreparedBy;
	}
	public void setReportPreparedBy(ValueObject reportPreparedBy) {
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
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
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
