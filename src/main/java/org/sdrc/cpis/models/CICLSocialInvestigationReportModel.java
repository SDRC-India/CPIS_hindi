package org.sdrc.cpis.models;


import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.util.ValueObject;

public class CICLSocialInvestigationReportModel {

	private Integer id;
	private String childId;
	private String slNo;
	private String jjbAddress;
	private ValueObject ciclOrgType;
	private String nameOfPerson;
	private String firNumber;
	private String gdNumber;
	private String underSections;
	private String policeStation;
	private ValueObject natureOfOffenceAlleged;
//	private ValueObject natureOfOffence;changed...set this property with the above one
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
	private ValueObject relnChildGrandParent;
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
	private String childOpinionTowardsDiscipline;
	private String employmentDetails;
	private String incomeUtilization;
	private String workRecord;
	private ValueObject education;
	private String classMatesAttitude;
	private String teachersAttitude;
	private String reasonLeavingSchool;
	private String otherReasonLeavingSchool;
//	private ValueObject schoolTypeStudiedLast;changed...set this property with the above one
	private ValueObject schoolType;
	private String vocationalTraining;
	private String majorityFriendTypes;
	private String attitudeTowardsFriends;
	private String friendsAttitudeTowardsChild;
	private String observationAboutNeighbourhood;
	private String observationAboutNeighbourhoodToAsses;
	private boolean childSubjectedOfAbuse;
	private String verbalAbuse;
	private String otherVerbalAbuse;
	private String physicalAbuse;
	private String otherPhysicalAbuse;
	private String sexualAbuse;
	private String otherSexualAbuse;
	private String otherAbuse;
	private String otherInOtherAbuse;
	private boolean childVictim;
	private boolean usedByAnyGang;
	private String historyRunAwayFromHome;
	private String circumstancesOfApprehension;
	private String allegedRoleInOffence;
	private String reasonForAllegedOffence;
	private String otherReasonForAllegedOffence;
	private boolean apprehendedForOffence;
	private String apprehendedForOffenceDtls;
	private ValueObject institutionDocType;
	private String physicalAppearance;
	private String healthCondition;
	private String mentalCondition;
	private String anyOtherRemark;
	private String roiEmotionalFactors;
	private String roiPhysicalCondition;
	private String roiIntelligence;
	private String roiSocialEconomicFactors;
	private String roiSuggestiveCausesProblems;
	private String roiAnalysisOfCase;
	private String roiReasonsForCareProtection;
	private String roiOpinionExperts;
	private String roiRecommendation;
	
	private List<CICLSocialInvestigationReportFamilyDetailsModel> cICLSocialInvestigationReportFamilyDetailsModel;
	
	private String badNameString;
	private String differentlyAbledTypeString;
	private String goodNameString;
	private String maorityNameString;
	private String physicalAbusedNameString;
	private String reasonForAllegedOffenceNameString;
	private String reasonNameString;
	private String sexualAbusedNameString;
	private String verbalAbusedNameString;
	private String childPhoto;
	
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
	public ValueObject getCiclOrgType() {
		return ciclOrgType;
	}
	public void setCiclOrgType(ValueObject ciclOrgType) {
		this.ciclOrgType = ciclOrgType;
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
	public String getOtherDifferentlyAbled() {
		return otherDifferentlyAbled;
	}
	public void setOtherDifferentlyAbled(String otherDifferentlyAbled) {
		this.otherDifferentlyAbled = otherDifferentlyAbled;
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
	public ValueObject getRelnChildGrandParent() {
		return relnChildGrandParent;
	}
	public void setRelnChildGrandParent(ValueObject relnChildGrandParent) {
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
	public ValueObject getEducation() {
		return education;
	}
	public void setEducation(ValueObject education) {
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
	public ValueObject getInstitutionDocType() {
		return institutionDocType;
	}
	public void setInstitutionDocType(ValueObject institutionDocType) {
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
	public List<CICLSocialInvestigationReportFamilyDetailsModel> getcICLSocialInvestigationReportFamilyDetailsModel() {
		return cICLSocialInvestigationReportFamilyDetailsModel;
	}
	public void setcICLSocialInvestigationReportFamilyDetailsModel(
			List<CICLSocialInvestigationReportFamilyDetailsModel> cICLSocialInvestigationReportFamilyDetailsModel) {
		this.cICLSocialInvestigationReportFamilyDetailsModel = cICLSocialInvestigationReportFamilyDetailsModel;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public ValueObject getNatureOfOffenceAlleged() {
		return natureOfOffenceAlleged;
	}
	public void setNatureOfOffenceAlleged(ValueObject natureOfOffenceAlleged) {
		this.natureOfOffenceAlleged = natureOfOffenceAlleged;
	}
	public ValueObject getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(ValueObject schoolType) {
		this.schoolType = schoolType;
	}
	public boolean isChildSubjectedOfAbuse() {
		return childSubjectedOfAbuse;
	}
	public void setChildSubjectedOfAbuse(boolean childSubjectedOfAbuse) {
		this.childSubjectedOfAbuse = childSubjectedOfAbuse;
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
	public String getBadNameString() {
		return badNameString;
	}
	public void setBadNameString(String badNameString) {
		this.badNameString = badNameString;
	}
	public String getDifferentlyAbledTypeString() {
		return differentlyAbledTypeString;
	}
	public void setDifferentlyAbledTypeString(String differentlyAbledTypeString) {
		this.differentlyAbledTypeString = differentlyAbledTypeString;
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
	public String getReasonForAllegedOffenceNameString() {
		return reasonForAllegedOffenceNameString;
	}
	public void setReasonForAllegedOffenceNameString(
			String reasonForAllegedOffenceNameString) {
		this.reasonForAllegedOffenceNameString = reasonForAllegedOffenceNameString;
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
