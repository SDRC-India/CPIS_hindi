package org.sdrc.cpis.models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.sdrc.cpis.util.ValueObject;

public class CaseHistoryCCIModel {
	
	private Integer id;
	private String childId;
	private Date date;
	private Time time;
	private String childImagePath;
	private Integer ageAtTimeOfAdmission;
	private Integer presentAge;
	private String category;
	private String categoryDetails;
	private String categoryDetailsOther;
	private ValueObject religionObject; //multiple choice single select
	private String religionOthers;
	private ValueObject casteObject; //mc ss
	private String nativeDistrict;
	private String nativeState;
	private ValueObject descriptionOfHousing1Object; //mc ss
	private ValueObject descriptionOfHousing2Object; //mc ss
	private ValueObject descriptionOfHousing3Object; //mc ss
	private ValueObject childBroughtBeforeCWCByWhomObject; //mc ss
	private String childBroughtBeforeCWCByWhomRelationship;
	private String reasonsForLeavingFamily;
	private String reasonsForLeavingFamilyOthers;
	private String verbalAbuseMetByChild;
	private String verbalAbuseMetByChildOthers;
	private String physicalAbuseMetByChild;
	private String physicalAbuseMetByChildOthers;
	private String sexualAbuseMetByChild;
	private String sexualAbuseMetByChildOthers;
	private String otherAbuseMetByChildName;
	private String otherAbuseMetByChild;
	private String otherAbuseMetByChildOthers;
	private String illTreatmentDOF;
	private String illTreatmentDOFOthers;
	private String illTreatmentBM;
	private String illTreatmentBMOthers;
	private String illTreatmentCI;
	private String illTreatmentCIOthers;
	private String illTreatmentDP;
	private String illTreatmentDPOthers;
	private String illTreatmentOtherName;
	private String illTreatmentOther;
	private String illTreatmentOtherOther;
	private String exploitaionFacedByTheChild;
	private String exploitaionFacedByTheChildOthers;
	private ValueObject respiratoryDisorders; //mc ss
	private ValueObject hearingImpairment; //mc ss
	private ValueObject eyeDiseases; //mc ss
	private ValueObject dentalDisease; //mc ss
	private ValueObject cardiacDiseases; //mc ss
	private ValueObject skinDisease; //mc ss
	private ValueObject sexuallyTransmittedDiseases; //mc ss
	private ValueObject neurologicalDisorders; //mc ss
	private ValueObject mentalHandicap; //mc ss
	private ValueObject physicalHandicap; //mc ss
	private ValueObject urinaryTractInfections; //mc ss
	private ValueObject otherHealthIssues; //mc ss
	private String otherHealthIssuesName;
	private ValueObject childWasStayingWithWhomPriorToAdmission;
	private String childWasStayingWithWhomPriorToAdmissionOther;
	private ValueObject visitOfParentsPriorToInstitutionalizationObject; //mc ss
	private ValueObject visitOfParentsAfterInstitutionalizationObject; //mc ss
	private ValueObject visitOfChildPriorToInstitutionalizationObject; //mc ss
	private ValueObject visitOfChildAfterInstitutionalizationObject; //mc ss
	private ValueObject correspondenceWithParentPriorToInstitutionalizationObject; //mc ss
	private ValueObject correspondenceWithParentAfterInstitutionalizationObject; //mc ss
	private String detailsOfDisability;
	private ValueObject familyTypeObject; //mc ss
	private ValueObject relationFatherMother;
	private ValueObject relationFatherChild;
	private ValueObject relationMotherChild;
	private ValueObject relationFatherSiblings;
	private ValueObject relationMotherSiblings;
	private ValueObject relationChildSiblings;
	private ValueObject relationChildRelative;
	private List<FamilyHistoryOfCrimeModel> familyHistoryOfCrimeModels;
	private String landedPropertiesOBF; //Owned By Family (obf)
	private String householdArticlesOBF;
	private String vehiclesOBF;
	private String othersOBF;
	private ValueObject parentsMarraigeTypeObject; //mc ss
	private ValueObject brothersMarraigeTypeObject; //mc ss
	private ValueObject sistersMarraigeTypeObject; //mc ss
	private String socialActivitesOfFamilyMembers;
	private String parentalCareTowardsChildBeforeAdmission;
	private Integer childAttainPubertyAge;
	private String detailsOfDelinquentBehaviour;
	private String detailsOfDelinquentBehaviourOthers;
	private String reasonForDelinquentBehaviour;
	private String reasonForDelinquentBehaviourOthers;
	private String childGoodHabits;
	private String childGoodHabitsOthers;
	private String childBadHabits;
	private String childBadHabitsOthers;
	private List<ChildEmploymentDetailsModel> childEmploymentDetailsModels;
	private String detailsOfIncomeUtilization;
	private String detailsOfSaving;
	private String detailsOfSavingOthers;
	private ValueObject durationOfWorkingHoursObject; //mc ss
	private ValueObject detailsOfPastEducationObject;
	private String reasonBehindLeavingSchool;
	private String reasonBehindLeavingSchoolOthers;
	private ValueObject detailsOfSchoolStudiedLastObject; //mc ss
	private ValueObject schoolMediumInstructionObject; //mc ss
	private String schoolMediumInstructionOthers;
	private Double educationalAttainmentNoOfYears;
	private String educationalAttainmentClassStudied;
	private ValueObject educationalAttainmentPromote_Detained;
	private Double voactionalTrainingNoOfYears;
	private String vocationalTrainingNameOfTrade;
	private String vocationalTrainingProficiencyObtained;
	private String detailsOfCertificationPath;
	private String extraCurricularActivities;
	private String extraCurricularActivitiesOthers;
	private String heightAtTimeOfAdmission;
	private Double weightAtTimeOfAdmission;
	private String physicalCondition;
	private String medicalHistoryOfChild;
	private String medicalHistoryOfParents;
	private List<HealthStatusOfChildModel> healthStatusOfChildModels;
	private Date dateMonthYearForHeightWeightChart;
	private String heightForHeightWeightChart;
	private Double admissibleWeightForHeightWeightChart;
	private Double actualWeightForHeightWeightChart;
	private String friendshipPriorToAdmissionIntoChildrensHome;
	private String friendshipPriorToAdmissionIntoChildrensHomeOthers;
	private String majorityFriendsAre;
	private String detailOfMembershipInGroup;
	private String detailOfMembershipInGroupOthers;
	private ValueObject positionOfChildInGroup_LeagueObject; //mc ss
	private String purposeOfTakingMembershipInGroup;
	private String purposeOfTakingMembershipInGroupOthers;
	private ValueObject attitudeOfGroup_LeagueObject; //mc ss
	private ValueObject locationMeetingPointOfGroupsObject; //mc ss
	private ValueObject reactionOfSocietyTowardsChildObject; //mc ss
	private ValueObject reactionOfPoliceTowardsChildrenObject; //mc ss
	private String responseOfGeneralPublicTowardsChild;
	private String historyOfChildEducation;
	private String historyOfChildHealth;
	private String historyOfChildVocationalTraining;
	private String historyOfChildExtraCuricularActivites;
	private String historyOfChildOthers;
	private ValueObject suggestionByWhom;
	private String suggestion;
	private ValueObject followUpWhom;
	private String quarterlyReviewOfCase;
	
	private String categoryName;
	private String reasonsForLeavingFamilyName;
	private String verbalAbuseMetByChildName;
	private String physicalAbuseMetByChildName;
	private String sexualAbuseMetByChildName;
	private String otherAbuseMetByChildNames;
	private String illTreatmentDOFName;
	private String illTreatmentBMName;
	private String illTreatmentCIName;
	private String illTreatmentDPName;
	private String illTreatmentOtherNames;
	private String exploitaionFacedByTheChildName;
	private String householdArticlesOBFName;
	private String vehiclesOBFName;
	private String socialActivitesOfFamilyMembersName;
	private String parentalCareTowardsChildBeforeAdmissionName;
	private String detailsOfDelinquentBehaviourName;
	private String reasonForDelinquentBehaviourName;
	private String childBadHabitsName;
	private String childGoodHabitsName;
	private String detailsOfIncomeUtilizationName;
	private String detailsOfSavingName;
	private String reasonBehindLeavingSchoolName;
	private String extraCurricularActivitiesName;
	private String friendshipPriorToAdmissionIntoChildrensHomeName;
	private String majorityFriendsAreName;
	private String detailOfMembershipInGroupName;
	private String purposeOfTakingMembershipInGroupName;
	
	private String childSex;
	private String childName;
	private String caseNo;
	private String childImgpath;
	private String timeToString;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getChildImagePath() {
		return childImagePath;
	}
	public void setChildImagePath(String childImagePath) {
		this.childImagePath = childImagePath;
	}
	public Integer getAgeAtTimeOfAdmission() {
		return ageAtTimeOfAdmission;
	}
	public void setAgeAtTimeOfAdmission(Integer ageAtTimeOfAdmission) {
		this.ageAtTimeOfAdmission = ageAtTimeOfAdmission;
	}
	public Integer getPresentAge() {
		return presentAge;
	}
	public void setPresentAge(Integer presentAge) {
		this.presentAge = presentAge;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryDetails() {
		return categoryDetails;
	}
	public void setCategoryDetails(String categoryDetails) {
		this.categoryDetails = categoryDetails;
	}
	public String getCategoryDetailsOther() {
		return categoryDetailsOther;
	}
	public void setCategoryDetailsOther(String categoryDetailsOther) {
		this.categoryDetailsOther = categoryDetailsOther;
	}
	public ValueObject getReligionObject() {
		return religionObject;
	}
	public void setReligionObject(ValueObject religionObject) {
		this.religionObject = religionObject;
	}
	public String getReligionOthers() {
		return religionOthers;
	}
	public void setReligionOthers(String religionOthers) {
		this.religionOthers = religionOthers;
	}
	public ValueObject getCasteObject() {
		return casteObject;
	}
	public void setCasteObject(ValueObject casteObject) {
		this.casteObject = casteObject;
	}
	public String getNativeDistrict() {
		return nativeDistrict;
	}
	public void setNativeDistrict(String nativeDistrict) {
		this.nativeDistrict = nativeDistrict;
	}
	public String getNativeState() {
		return nativeState;
	}
	public void setNativeState(String nativeState) {
		this.nativeState = nativeState;
	}
	public ValueObject getDescriptionOfHousing1Object() {
		return descriptionOfHousing1Object;
	}
	public void setDescriptionOfHousing1Object(
			ValueObject descriptionOfHousing1Object) {
		this.descriptionOfHousing1Object = descriptionOfHousing1Object;
	}
	public ValueObject getDescriptionOfHousing2Object() {
		return descriptionOfHousing2Object;
	}
	public void setDescriptionOfHousing2Object(
			ValueObject descriptionOfHousing2Object) {
		this.descriptionOfHousing2Object = descriptionOfHousing2Object;
	}
	public ValueObject getDescriptionOfHousing3Object() {
		return descriptionOfHousing3Object;
	}
	public void setDescriptionOfHousing3Object(
			ValueObject descriptionOfHousing3Object) {
		this.descriptionOfHousing3Object = descriptionOfHousing3Object;
	}
	public ValueObject getChildBroughtBeforeCWCByWhomObject() {
		return childBroughtBeforeCWCByWhomObject;
	}
	public void setChildBroughtBeforeCWCByWhomObject(
			ValueObject childBroughtBeforeCWCByWhomObject) {
		this.childBroughtBeforeCWCByWhomObject = childBroughtBeforeCWCByWhomObject;
	}
	public String getChildBroughtBeforeCWCByWhomRelationship() {
		return childBroughtBeforeCWCByWhomRelationship;
	}
	public void setChildBroughtBeforeCWCByWhomRelationship(
			String childBroughtBeforeCWCByWhomRelationship) {
		this.childBroughtBeforeCWCByWhomRelationship = childBroughtBeforeCWCByWhomRelationship;
	}
	public String getReasonsForLeavingFamily() {
		return reasonsForLeavingFamily;
	}
	public void setReasonsForLeavingFamily(String reasonsForLeavingFamily) {
		this.reasonsForLeavingFamily = reasonsForLeavingFamily;
	}
	public String getReasonsForLeavingFamilyOthers() {
		return reasonsForLeavingFamilyOthers;
	}
	public void setReasonsForLeavingFamilyOthers(
			String reasonsForLeavingFamilyOthers) {
		this.reasonsForLeavingFamilyOthers = reasonsForLeavingFamilyOthers;
	}
	public String getVerbalAbuseMetByChild() {
		return verbalAbuseMetByChild;
	}
	public void setVerbalAbuseMetByChild(String verbalAbuseMetByChild) {
		this.verbalAbuseMetByChild = verbalAbuseMetByChild;
	}
	public String getVerbalAbuseMetByChildOthers() {
		return verbalAbuseMetByChildOthers;
	}
	public void setVerbalAbuseMetByChildOthers(String verbalAbuseMetByChildOthers) {
		this.verbalAbuseMetByChildOthers = verbalAbuseMetByChildOthers;
	}
	public String getPhysicalAbuseMetByChild() {
		return physicalAbuseMetByChild;
	}
	public void setPhysicalAbuseMetByChild(String physicalAbuseMetByChild) {
		this.physicalAbuseMetByChild = physicalAbuseMetByChild;
	}
	public String getPhysicalAbuseMetByChildOthers() {
		return physicalAbuseMetByChildOthers;
	}
	public void setPhysicalAbuseMetByChildOthers(
			String physicalAbuseMetByChildOthers) {
		this.physicalAbuseMetByChildOthers = physicalAbuseMetByChildOthers;
	}
	public String getSexualAbuseMetByChild() {
		return sexualAbuseMetByChild;
	}
	public void setSexualAbuseMetByChild(String sexualAbuseMetByChild) {
		this.sexualAbuseMetByChild = sexualAbuseMetByChild;
	}
	public String getSexualAbuseMetByChildOthers() {
		return sexualAbuseMetByChildOthers;
	}
	public void setSexualAbuseMetByChildOthers(String sexualAbuseMetByChildOthers) {
		this.sexualAbuseMetByChildOthers = sexualAbuseMetByChildOthers;
	}
	public String getOtherAbuseMetByChildName() {
		return otherAbuseMetByChildName;
	}
	public void setOtherAbuseMetByChildName(String otherAbuseMetByChildName) {
		this.otherAbuseMetByChildName = otherAbuseMetByChildName;
	}
	public String getOtherAbuseMetByChild() {
		return otherAbuseMetByChild;
	}
	public void setOtherAbuseMetByChild(String otherAbuseMetByChild) {
		this.otherAbuseMetByChild = otherAbuseMetByChild;
	}
	public String getOtherAbuseMetByChildOthers() {
		return otherAbuseMetByChildOthers;
	}
	public void setOtherAbuseMetByChildOthers(String otherAbuseMetByChildOthers) {
		this.otherAbuseMetByChildOthers = otherAbuseMetByChildOthers;
	}
	public String getIllTreatmentDOF() {
		return illTreatmentDOF;
	}
	public void setIllTreatmentDOF(String illTreatmentDOF) {
		this.illTreatmentDOF = illTreatmentDOF;
	}
	public String getIllTreatmentDOFOthers() {
		return illTreatmentDOFOthers;
	}
	public void setIllTreatmentDOFOthers(String illTreatmentDOFOthers) {
		this.illTreatmentDOFOthers = illTreatmentDOFOthers;
	}
	public String getIllTreatmentBM() {
		return illTreatmentBM;
	}
	public void setIllTreatmentBM(String illTreatmentBM) {
		this.illTreatmentBM = illTreatmentBM;
	}
	public String getIllTreatmentBMOthers() {
		return illTreatmentBMOthers;
	}
	public void setIllTreatmentBMOthers(String illTreatmentBMOthers) {
		this.illTreatmentBMOthers = illTreatmentBMOthers;
	}
	public String getIllTreatmentCI() {
		return illTreatmentCI;
	}
	public void setIllTreatmentCI(String illTreatmentCI) {
		this.illTreatmentCI = illTreatmentCI;
	}
	public String getIllTreatmentCIOthers() {
		return illTreatmentCIOthers;
	}
	public void setIllTreatmentCIOthers(String illTreatmentCIOthers) {
		this.illTreatmentCIOthers = illTreatmentCIOthers;
	}
	public String getIllTreatmentDP() {
		return illTreatmentDP;
	}
	public void setIllTreatmentDP(String illTreatmentDP) {
		this.illTreatmentDP = illTreatmentDP;
	}
	public String getIllTreatmentDPOthers() {
		return illTreatmentDPOthers;
	}
	public void setIllTreatmentDPOthers(String illTreatmentDPOthers) {
		this.illTreatmentDPOthers = illTreatmentDPOthers;
	}
	public String getIllTreatmentOtherName() {
		return illTreatmentOtherName;
	}
	public void setIllTreatmentOtherName(String illTreatmentOtherName) {
		this.illTreatmentOtherName = illTreatmentOtherName;
	}
	public String getIllTreatmentOther() {
		return illTreatmentOther;
	}
	public void setIllTreatmentOther(String illTreatmentOther) {
		this.illTreatmentOther = illTreatmentOther;
	}
	public String getIllTreatmentOtherOther() {
		return illTreatmentOtherOther;
	}
	public void setIllTreatmentOtherOther(String illTreatmentOtherOther) {
		this.illTreatmentOtherOther = illTreatmentOtherOther;
	}
	public String getExploitaionFacedByTheChild() {
		return exploitaionFacedByTheChild;
	}
	public void setExploitaionFacedByTheChild(String exploitaionFacedByTheChild) {
		this.exploitaionFacedByTheChild = exploitaionFacedByTheChild;
	}
	public String getExploitaionFacedByTheChildOthers() {
		return exploitaionFacedByTheChildOthers;
	}
	public void setExploitaionFacedByTheChildOthers(
			String exploitaionFacedByTheChildOthers) {
		this.exploitaionFacedByTheChildOthers = exploitaionFacedByTheChildOthers;
	}
	public ValueObject getRespiratoryDisorders() {
		return respiratoryDisorders;
	}
	public void setRespiratoryDisorders(ValueObject respiratoryDisorders) {
		this.respiratoryDisorders = respiratoryDisorders;
	}
	public ValueObject getHearingImpairment() {
		return hearingImpairment;
	}
	public void setHearingImpairment(ValueObject hearingImpairment) {
		this.hearingImpairment = hearingImpairment;
	}
	public ValueObject getEyeDiseases() {
		return eyeDiseases;
	}
	public void setEyeDiseases(ValueObject eyeDiseases) {
		this.eyeDiseases = eyeDiseases;
	}
	public ValueObject getDentalDisease() {
		return dentalDisease;
	}
	public void setDentalDisease(ValueObject dentalDisease) {
		this.dentalDisease = dentalDisease;
	}
	public ValueObject getCardiacDiseases() {
		return cardiacDiseases;
	}
	public void setCardiacDiseases(ValueObject cardiacDiseases) {
		this.cardiacDiseases = cardiacDiseases;
	}
	public ValueObject getSkinDisease() {
		return skinDisease;
	}
	public void setSkinDisease(ValueObject skinDisease) {
		this.skinDisease = skinDisease;
	}
	public ValueObject getSexuallyTransmittedDiseases() {
		return sexuallyTransmittedDiseases;
	}
	public void setSexuallyTransmittedDiseases(
			ValueObject sexuallyTransmittedDiseases) {
		this.sexuallyTransmittedDiseases = sexuallyTransmittedDiseases;
	}
	public ValueObject getNeurologicalDisorders() {
		return neurologicalDisorders;
	}
	public void setNeurologicalDisorders(ValueObject neurologicalDisorders) {
		this.neurologicalDisorders = neurologicalDisorders;
	}
	public ValueObject getMentalHandicap() {
		return mentalHandicap;
	}
	public void setMentalHandicap(ValueObject mentalHandicap) {
		this.mentalHandicap = mentalHandicap;
	}
	public ValueObject getPhysicalHandicap() {
		return physicalHandicap;
	}
	public void setPhysicalHandicap(ValueObject physicalHandicap) {
		this.physicalHandicap = physicalHandicap;
	}
	public ValueObject getUrinaryTractInfections() {
		return urinaryTractInfections;
	}
	public void setUrinaryTractInfections(ValueObject urinaryTractInfections) {
		this.urinaryTractInfections = urinaryTractInfections;
	}
	public ValueObject getOtherHealthIssues() {
		return otherHealthIssues;
	}
	public void setOtherHealthIssues(ValueObject otherHealthIssues) {
		this.otherHealthIssues = otherHealthIssues;
	}
	public String getOtherHealthIssuesName() {
		return otherHealthIssuesName;
	}
	public void setOtherHealthIssuesName(String otherHealthIssuesName) {
		this.otherHealthIssuesName = otherHealthIssuesName;
	}
	public ValueObject getChildWasStayingWithWhomPriorToAdmission() {
		return childWasStayingWithWhomPriorToAdmission;
	}
	public void setChildWasStayingWithWhomPriorToAdmission(
			ValueObject childWasStayingWithWhomPriorToAdmission) {
		this.childWasStayingWithWhomPriorToAdmission = childWasStayingWithWhomPriorToAdmission;
	}
	public String getChildWasStayingWithWhomPriorToAdmissionOther() {
		return childWasStayingWithWhomPriorToAdmissionOther;
	}
	public void setChildWasStayingWithWhomPriorToAdmissionOther(
			String childWasStayingWithWhomPriorToAdmissionOther) {
		this.childWasStayingWithWhomPriorToAdmissionOther = childWasStayingWithWhomPriorToAdmissionOther;
	}
	public ValueObject getVisitOfParentsPriorToInstitutionalizationObject() {
		return visitOfParentsPriorToInstitutionalizationObject;
	}
	public void setVisitOfParentsPriorToInstitutionalizationObject(
			ValueObject visitOfParentsPriorToInstitutionalizationObject) {
		this.visitOfParentsPriorToInstitutionalizationObject = visitOfParentsPriorToInstitutionalizationObject;
	}
	public ValueObject getVisitOfParentsAfterInstitutionalizationObject() {
		return visitOfParentsAfterInstitutionalizationObject;
	}
	public void setVisitOfParentsAfterInstitutionalizationObject(
			ValueObject visitOfParentsAfterInstitutionalizationObject) {
		this.visitOfParentsAfterInstitutionalizationObject = visitOfParentsAfterInstitutionalizationObject;
	}
	public ValueObject getVisitOfChildPriorToInstitutionalizationObject() {
		return visitOfChildPriorToInstitutionalizationObject;
	}
	public void setVisitOfChildPriorToInstitutionalizationObject(
			ValueObject visitOfChildPriorToInstitutionalizationObject) {
		this.visitOfChildPriorToInstitutionalizationObject = visitOfChildPriorToInstitutionalizationObject;
	}
	public ValueObject getVisitOfChildAfterInstitutionalizationObject() {
		return visitOfChildAfterInstitutionalizationObject;
	}
	public void setVisitOfChildAfterInstitutionalizationObject(
			ValueObject visitOfChildAfterInstitutionalizationObject) {
		this.visitOfChildAfterInstitutionalizationObject = visitOfChildAfterInstitutionalizationObject;
	}
	public ValueObject getCorrespondenceWithParentPriorToInstitutionalizationObject() {
		return correspondenceWithParentPriorToInstitutionalizationObject;
	}
	public void setCorrespondenceWithParentPriorToInstitutionalizationObject(
			ValueObject correspondenceWithParentPriorToInstitutionalizationObject) {
		this.correspondenceWithParentPriorToInstitutionalizationObject = correspondenceWithParentPriorToInstitutionalizationObject;
	}
	public ValueObject getCorrespondenceWithParentAfterInstitutionalizationObject() {
		return correspondenceWithParentAfterInstitutionalizationObject;
	}
	public void setCorrespondenceWithParentAfterInstitutionalizationObject(
			ValueObject correspondenceWithParentAfterInstitutionalizationObject) {
		this.correspondenceWithParentAfterInstitutionalizationObject = correspondenceWithParentAfterInstitutionalizationObject;
	}
	public String getDetailsOfDisability() {
		return detailsOfDisability;
	}
	public void setDetailsOfDisability(String detailsOfDisability) {
		this.detailsOfDisability = detailsOfDisability;
	}
	public ValueObject getFamilyTypeObject() {
		return familyTypeObject;
	}
	public void setFamilyTypeObject(ValueObject familyTypeObject) {
		this.familyTypeObject = familyTypeObject;
	}
	public ValueObject getRelationFatherMother() {
		return relationFatherMother;
	}
	public void setRelationFatherMother(ValueObject relationFatherMother) {
		this.relationFatherMother = relationFatherMother;
	}
	public ValueObject getRelationFatherChild() {
		return relationFatherChild;
	}
	public void setRelationFatherChild(ValueObject relationFatherChild) {
		this.relationFatherChild = relationFatherChild;
	}
	public ValueObject getRelationMotherChild() {
		return relationMotherChild;
	}
	public void setRelationMotherChild(ValueObject relationMotherChild) {
		this.relationMotherChild = relationMotherChild;
	}
	public ValueObject getRelationFatherSiblings() {
		return relationFatherSiblings;
	}
	public void setRelationFatherSiblings(ValueObject relationFatherSiblings) {
		this.relationFatherSiblings = relationFatherSiblings;
	}
	public ValueObject getRelationMotherSiblings() {
		return relationMotherSiblings;
	}
	public void setRelationMotherSiblings(ValueObject relationMotherSiblings) {
		this.relationMotherSiblings = relationMotherSiblings;
	}
	public ValueObject getRelationChildSiblings() {
		return relationChildSiblings;
	}
	public void setRelationChildSiblings(ValueObject relationChildSiblings) {
		this.relationChildSiblings = relationChildSiblings;
	}
	public ValueObject getRelationChildRelative() {
		return relationChildRelative;
	}
	public void setRelationChildRelative(ValueObject relationChildRelative) {
		this.relationChildRelative = relationChildRelative;
	}
	public List<FamilyHistoryOfCrimeModel> getFamilyHistoryOfCrimeModels() {
		return familyHistoryOfCrimeModels;
	}
	public void setFamilyHistoryOfCrimeModels(
			List<FamilyHistoryOfCrimeModel> familyHistoryOfCrimeModels) {
		this.familyHistoryOfCrimeModels = familyHistoryOfCrimeModels;
	}
	public String getLandedPropertiesOBF() {
		return landedPropertiesOBF;
	}
	public void setLandedPropertiesOBF(String landedPropertiesOBF) {
		this.landedPropertiesOBF = landedPropertiesOBF;
	}
	public String getHouseholdArticlesOBF() {
		return householdArticlesOBF;
	}
	public void setHouseholdArticlesOBF(String householdArticlesOBF) {
		this.householdArticlesOBF = householdArticlesOBF;
	}
	public String getVehiclesOBF() {
		return vehiclesOBF;
	}
	public void setVehiclesOBF(String vehiclesOBF) {
		this.vehiclesOBF = vehiclesOBF;
	}
	public String getOthersOBF() {
		return othersOBF;
	}
	public void setOthersOBF(String othersOBF) {
		this.othersOBF = othersOBF;
	}
	public ValueObject getParentsMarraigeTypeObject() {
		return parentsMarraigeTypeObject;
	}
	public void setParentsMarraigeTypeObject(ValueObject parentsMarraigeTypeObject) {
		this.parentsMarraigeTypeObject = parentsMarraigeTypeObject;
	}
	public ValueObject getBrothersMarraigeTypeObject() {
		return brothersMarraigeTypeObject;
	}
	public void setBrothersMarraigeTypeObject(ValueObject brothersMarraigeTypeObject) {
		this.brothersMarraigeTypeObject = brothersMarraigeTypeObject;
	}
	public ValueObject getSistersMarraigeTypeObject() {
		return sistersMarraigeTypeObject;
	}
	public void setSistersMarraigeTypeObject(ValueObject sistersMarraigeTypeObject) {
		this.sistersMarraigeTypeObject = sistersMarraigeTypeObject;
	}
	public String getSocialActivitesOfFamilyMembers() {
		return socialActivitesOfFamilyMembers;
	}
	public void setSocialActivitesOfFamilyMembers(
			String socialActivitesOfFamilyMembers) {
		this.socialActivitesOfFamilyMembers = socialActivitesOfFamilyMembers;
	}
	public String getParentalCareTowardsChildBeforeAdmission() {
		return parentalCareTowardsChildBeforeAdmission;
	}
	public void setParentalCareTowardsChildBeforeAdmission(
			String parentalCareTowardsChildBeforeAdmission) {
		this.parentalCareTowardsChildBeforeAdmission = parentalCareTowardsChildBeforeAdmission;
	}
	public Integer getChildAttainPubertyAge() {
		return childAttainPubertyAge;
	}
	public void setChildAttainPubertyAge(Integer childAttainPubertyAge) {
		this.childAttainPubertyAge = childAttainPubertyAge;
	}
	public String getDetailsOfDelinquentBehaviour() {
		return detailsOfDelinquentBehaviour;
	}
	public void setDetailsOfDelinquentBehaviour(String detailsOfDelinquentBehaviour) {
		this.detailsOfDelinquentBehaviour = detailsOfDelinquentBehaviour;
	}
	public String getDetailsOfDelinquentBehaviourOthers() {
		return detailsOfDelinquentBehaviourOthers;
	}
	public void setDetailsOfDelinquentBehaviourOthers(
			String detailsOfDelinquentBehaviourOthers) {
		this.detailsOfDelinquentBehaviourOthers = detailsOfDelinquentBehaviourOthers;
	}
	public String getReasonForDelinquentBehaviour() {
		return reasonForDelinquentBehaviour;
	}
	public void setReasonForDelinquentBehaviour(String reasonForDelinquentBehaviour) {
		this.reasonForDelinquentBehaviour = reasonForDelinquentBehaviour;
	}
	public String getReasonForDelinquentBehaviourOthers() {
		return reasonForDelinquentBehaviourOthers;
	}
	public void setReasonForDelinquentBehaviourOthers(
			String reasonForDelinquentBehaviourOthers) {
		this.reasonForDelinquentBehaviourOthers = reasonForDelinquentBehaviourOthers;
	}
	public String getChildGoodHabits() {
		return childGoodHabits;
	}
	public void setChildGoodHabits(String childGoodHabits) {
		this.childGoodHabits = childGoodHabits;
	}
	public String getChildGoodHabitsOthers() {
		return childGoodHabitsOthers;
	}
	public void setChildGoodHabitsOthers(String childGoodHabitsOthers) {
		this.childGoodHabitsOthers = childGoodHabitsOthers;
	}
	public String getChildBadHabits() {
		return childBadHabits;
	}
	public void setChildBadHabits(String childBadHabits) {
		this.childBadHabits = childBadHabits;
	}
	public String getChildBadHabitsOthers() {
		return childBadHabitsOthers;
	}
	public void setChildBadHabitsOthers(String childBadHabitsOthers) {
		this.childBadHabitsOthers = childBadHabitsOthers;
	}
	public List<ChildEmploymentDetailsModel> getChildEmploymentDetailsModels() {
		return childEmploymentDetailsModels;
	}
	public void setChildEmploymentDetailsModels(
			List<ChildEmploymentDetailsModel> childEmploymentDetailsModels) {
		this.childEmploymentDetailsModels = childEmploymentDetailsModels;
	}
	public String getDetailsOfIncomeUtilization() {
		return detailsOfIncomeUtilization;
	}
	public void setDetailsOfIncomeUtilization(String detailsOfIncomeUtilization) {
		this.detailsOfIncomeUtilization = detailsOfIncomeUtilization;
	}
	public String getDetailsOfSaving() {
		return detailsOfSaving;
	}
	public void setDetailsOfSaving(String detailsOfSaving) {
		this.detailsOfSaving = detailsOfSaving;
	}
	public String getDetailsOfSavingOthers() {
		return detailsOfSavingOthers;
	}
	public void setDetailsOfSavingOthers(String detailsOfSavingOthers) {
		this.detailsOfSavingOthers = detailsOfSavingOthers;
	}
	public ValueObject getDurationOfWorkingHoursObject() {
		return durationOfWorkingHoursObject;
	}
	public void setDurationOfWorkingHoursObject(
			ValueObject durationOfWorkingHoursObject) {
		this.durationOfWorkingHoursObject = durationOfWorkingHoursObject;
	}
	public ValueObject getDetailsOfPastEducationObject() {
		return detailsOfPastEducationObject;
	}
	public void setDetailsOfPastEducationObject(
			ValueObject detailsOfPastEducationObject) {
		this.detailsOfPastEducationObject = detailsOfPastEducationObject;
	}
	public String getReasonBehindLeavingSchool() {
		return reasonBehindLeavingSchool;
	}
	public void setReasonBehindLeavingSchool(String reasonBehindLeavingSchool) {
		this.reasonBehindLeavingSchool = reasonBehindLeavingSchool;
	}
	public String getReasonBehindLeavingSchoolOthers() {
		return reasonBehindLeavingSchoolOthers;
	}
	public void setReasonBehindLeavingSchoolOthers(
			String reasonBehindLeavingSchoolOthers) {
		this.reasonBehindLeavingSchoolOthers = reasonBehindLeavingSchoolOthers;
	}
	public ValueObject getDetailsOfSchoolStudiedLastObject() {
		return detailsOfSchoolStudiedLastObject;
	}
	public void setDetailsOfSchoolStudiedLastObject(
			ValueObject detailsOfSchoolStudiedLastObject) {
		this.detailsOfSchoolStudiedLastObject = detailsOfSchoolStudiedLastObject;
	}
	public ValueObject getSchoolMediumInstructionObject() {
		return schoolMediumInstructionObject;
	}
	public void setSchoolMediumInstructionObject(
			ValueObject schoolMediumInstructionObject) {
		this.schoolMediumInstructionObject = schoolMediumInstructionObject;
	}
	public String getSchoolMediumInstructionOthers() {
		return schoolMediumInstructionOthers;
	}
	public void setSchoolMediumInstructionOthers(
			String schoolMediumInstructionOthers) {
		this.schoolMediumInstructionOthers = schoolMediumInstructionOthers;
	}
	public Double getEducationalAttainmentNoOfYears() {
		return educationalAttainmentNoOfYears;
	}
	public void setEducationalAttainmentNoOfYears(
			Double educationalAttainmentNoOfYears) {
		this.educationalAttainmentNoOfYears = educationalAttainmentNoOfYears;
	}
	public String getEducationalAttainmentClassStudied() {
		return educationalAttainmentClassStudied;
	}
	public void setEducationalAttainmentClassStudied(
			String educationalAttainmentClassStudied) {
		this.educationalAttainmentClassStudied = educationalAttainmentClassStudied;
	}
	public ValueObject getEducationalAttainmentPromote_Detained() {
		return educationalAttainmentPromote_Detained;
	}
	public void setEducationalAttainmentPromote_Detained(
			ValueObject educationalAttainmentPromote_Detained) {
		this.educationalAttainmentPromote_Detained = educationalAttainmentPromote_Detained;
	}
	public Double getVoactionalTrainingNoOfYears() {
		return voactionalTrainingNoOfYears;
	}
	public void setVoactionalTrainingNoOfYears(Double voactionalTrainingNoOfYears) {
		this.voactionalTrainingNoOfYears = voactionalTrainingNoOfYears;
	}
	public String getVocationalTrainingNameOfTrade() {
		return vocationalTrainingNameOfTrade;
	}
	public void setVocationalTrainingNameOfTrade(
			String vocationalTrainingNameOfTrade) {
		this.vocationalTrainingNameOfTrade = vocationalTrainingNameOfTrade;
	}
	public String getVocationalTrainingProficiencyObtained() {
		return vocationalTrainingProficiencyObtained;
	}
	public void setVocationalTrainingProficiencyObtained(
			String vocationalTrainingProficiencyObtained) {
		this.vocationalTrainingProficiencyObtained = vocationalTrainingProficiencyObtained;
	}
	public String getDetailsOfCertificationPath() {
		return detailsOfCertificationPath;
	}
	public void setDetailsOfCertificationPath(String detailsOfCertificationPath) {
		this.detailsOfCertificationPath = detailsOfCertificationPath;
	}
	public String getExtraCurricularActivities() {
		return extraCurricularActivities;
	}
	public void setExtraCurricularActivities(String extraCurricularActivities) {
		this.extraCurricularActivities = extraCurricularActivities;
	}
	public String getExtraCurricularActivitiesOthers() {
		return extraCurricularActivitiesOthers;
	}
	public void setExtraCurricularActivitiesOthers(
			String extraCurricularActivitiesOthers) {
		this.extraCurricularActivitiesOthers = extraCurricularActivitiesOthers;
	}
	public String getHeightAtTimeOfAdmission() {
		return heightAtTimeOfAdmission;
	}
	public void setHeightAtTimeOfAdmission(String heightAtTimeOfAdmission) {
		this.heightAtTimeOfAdmission = heightAtTimeOfAdmission;
	}
	public Double getWeightAtTimeOfAdmission() {
		return weightAtTimeOfAdmission;
	}
	public void setWeightAtTimeOfAdmission(Double weightAtTimeOfAdmission) {
		this.weightAtTimeOfAdmission = weightAtTimeOfAdmission;
	}
	public String getPhysicalCondition() {
		return physicalCondition;
	}
	public void setPhysicalCondition(String physicalCondition) {
		this.physicalCondition = physicalCondition;
	}
	public String getMedicalHistoryOfChild() {
		return medicalHistoryOfChild;
	}
	public void setMedicalHistoryOfChild(String medicalHistoryOfChild) {
		this.medicalHistoryOfChild = medicalHistoryOfChild;
	}
	public String getMedicalHistoryOfParents() {
		return medicalHistoryOfParents;
	}
	public void setMedicalHistoryOfParents(String medicalHistoryOfParents) {
		this.medicalHistoryOfParents = medicalHistoryOfParents;
	}
	public List<HealthStatusOfChildModel> getHealthStatusOfChildModels() {
		return healthStatusOfChildModels;
	}
	public void setHealthStatusOfChildModels(
			List<HealthStatusOfChildModel> healthStatusOfChildModels) {
		this.healthStatusOfChildModels = healthStatusOfChildModels;
	}
	public Date getDateMonthYearForHeightWeightChart() {
		return dateMonthYearForHeightWeightChart;
	}
	public void setDateMonthYearForHeightWeightChart(
			Date dateMonthYearForHeightWeightChart) {
		this.dateMonthYearForHeightWeightChart = dateMonthYearForHeightWeightChart;
	}
	public String getHeightForHeightWeightChart() {
		return heightForHeightWeightChart;
	}
	public void setHeightForHeightWeightChart(String heightForHeightWeightChart) {
		this.heightForHeightWeightChart = heightForHeightWeightChart;
	}
	public Double getAdmissibleWeightForHeightWeightChart() {
		return admissibleWeightForHeightWeightChart;
	}
	public void setAdmissibleWeightForHeightWeightChart(
			Double admissibleWeightForHeightWeightChart) {
		this.admissibleWeightForHeightWeightChart = admissibleWeightForHeightWeightChart;
	}
	public Double getActualWeightForHeightWeightChart() {
		return actualWeightForHeightWeightChart;
	}
	public void setActualWeightForHeightWeightChart(
			Double actualWeightForHeightWeightChart) {
		this.actualWeightForHeightWeightChart = actualWeightForHeightWeightChart;
	}
	public String getFriendshipPriorToAdmissionIntoChildrensHome() {
		return friendshipPriorToAdmissionIntoChildrensHome;
	}
	public void setFriendshipPriorToAdmissionIntoChildrensHome(
			String friendshipPriorToAdmissionIntoChildrensHome) {
		this.friendshipPriorToAdmissionIntoChildrensHome = friendshipPriorToAdmissionIntoChildrensHome;
	}
	public String getFriendshipPriorToAdmissionIntoChildrensHomeOthers() {
		return friendshipPriorToAdmissionIntoChildrensHomeOthers;
	}
	public void setFriendshipPriorToAdmissionIntoChildrensHomeOthers(
			String friendshipPriorToAdmissionIntoChildrensHomeOthers) {
		this.friendshipPriorToAdmissionIntoChildrensHomeOthers = friendshipPriorToAdmissionIntoChildrensHomeOthers;
	}
	public String getMajorityFriendsAre() {
		return majorityFriendsAre;
	}
	public void setMajorityFriendsAre(String majorityFriendsAre) {
		this.majorityFriendsAre = majorityFriendsAre;
	}
	public String getDetailOfMembershipInGroup() {
		return detailOfMembershipInGroup;
	}
	public void setDetailOfMembershipInGroup(String detailOfMembershipInGroup) {
		this.detailOfMembershipInGroup = detailOfMembershipInGroup;
	}
	public String getDetailOfMembershipInGroupOthers() {
		return detailOfMembershipInGroupOthers;
	}
	public void setDetailOfMembershipInGroupOthers(
			String detailOfMembershipInGroupOthers) {
		this.detailOfMembershipInGroupOthers = detailOfMembershipInGroupOthers;
	}
	public ValueObject getPositionOfChildInGroup_LeagueObject() {
		return positionOfChildInGroup_LeagueObject;
	}
	public void setPositionOfChildInGroup_LeagueObject(
			ValueObject positionOfChildInGroup_LeagueObject) {
		this.positionOfChildInGroup_LeagueObject = positionOfChildInGroup_LeagueObject;
	}
	public String getPurposeOfTakingMembershipInGroup() {
		return purposeOfTakingMembershipInGroup;
	}
	public void setPurposeOfTakingMembershipInGroup(
			String purposeOfTakingMembershipInGroup) {
		this.purposeOfTakingMembershipInGroup = purposeOfTakingMembershipInGroup;
	}
	public String getPurposeOfTakingMembershipInGroupOthers() {
		return purposeOfTakingMembershipInGroupOthers;
	}
	public void setPurposeOfTakingMembershipInGroupOthers(
			String purposeOfTakingMembershipInGroupOthers) {
		this.purposeOfTakingMembershipInGroupOthers = purposeOfTakingMembershipInGroupOthers;
	}
	public ValueObject getAttitudeOfGroup_LeagueObject() {
		return attitudeOfGroup_LeagueObject;
	}
	public void setAttitudeOfGroup_LeagueObject(
			ValueObject attitudeOfGroup_LeagueObject) {
		this.attitudeOfGroup_LeagueObject = attitudeOfGroup_LeagueObject;
	}
	public ValueObject getLocationMeetingPointOfGroupsObject() {
		return locationMeetingPointOfGroupsObject;
	}
	public void setLocationMeetingPointOfGroupsObject(
			ValueObject locationMeetingPointOfGroupsObject) {
		this.locationMeetingPointOfGroupsObject = locationMeetingPointOfGroupsObject;
	}
	public ValueObject getReactionOfSocietyTowardsChildObject() {
		return reactionOfSocietyTowardsChildObject;
	}
	public void setReactionOfSocietyTowardsChildObject(
			ValueObject reactionOfSocietyTowardsChildObject) {
		this.reactionOfSocietyTowardsChildObject = reactionOfSocietyTowardsChildObject;
	}
	public ValueObject getReactionOfPoliceTowardsChildrenObject() {
		return reactionOfPoliceTowardsChildrenObject;
	}
	public void setReactionOfPoliceTowardsChildrenObject(
			ValueObject reactionOfPoliceTowardsChildrenObject) {
		this.reactionOfPoliceTowardsChildrenObject = reactionOfPoliceTowardsChildrenObject;
	}
	public String getResponseOfGeneralPublicTowardsChild() {
		return responseOfGeneralPublicTowardsChild;
	}
	public void setResponseOfGeneralPublicTowardsChild(
			String responseOfGeneralPublicTowardsChild) {
		this.responseOfGeneralPublicTowardsChild = responseOfGeneralPublicTowardsChild;
	}
	public String getHistoryOfChildEducation() {
		return historyOfChildEducation;
	}
	public void setHistoryOfChildEducation(String historyOfChildEducation) {
		this.historyOfChildEducation = historyOfChildEducation;
	}
	public String getHistoryOfChildHealth() {
		return historyOfChildHealth;
	}
	public void setHistoryOfChildHealth(String historyOfChildHealth) {
		this.historyOfChildHealth = historyOfChildHealth;
	}
	public String getHistoryOfChildVocationalTraining() {
		return historyOfChildVocationalTraining;
	}
	public void setHistoryOfChildVocationalTraining(
			String historyOfChildVocationalTraining) {
		this.historyOfChildVocationalTraining = historyOfChildVocationalTraining;
	}
	public String getHistoryOfChildExtraCuricularActivites() {
		return historyOfChildExtraCuricularActivites;
	}
	public void setHistoryOfChildExtraCuricularActivites(
			String historyOfChildExtraCuricularActivites) {
		this.historyOfChildExtraCuricularActivites = historyOfChildExtraCuricularActivites;
	}
	public String getHistoryOfChildOthers() {
		return historyOfChildOthers;
	}
	public void setHistoryOfChildOthers(String historyOfChildOthers) {
		this.historyOfChildOthers = historyOfChildOthers;
	}
	public ValueObject getSuggestionByWhom() {
		return suggestionByWhom;
	}
	public void setSuggestionByWhom(ValueObject suggestionByWhom) {
		this.suggestionByWhom = suggestionByWhom;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public ValueObject getFollowUpWhom() {
		return followUpWhom;
	}
	public void setFollowUpWhom(ValueObject followUpWhom) {
		this.followUpWhom = followUpWhom;
	}
	public String getQuarterlyReviewOfCase() {
		return quarterlyReviewOfCase;
	}
	public void setQuarterlyReviewOfCase(String quarterlyReviewOfCase) {
		this.quarterlyReviewOfCase = quarterlyReviewOfCase;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getReasonsForLeavingFamilyName() {
		return reasonsForLeavingFamilyName;
	}
	public void setReasonsForLeavingFamilyName(String reasonsForLeavingFamilyName) {
		this.reasonsForLeavingFamilyName = reasonsForLeavingFamilyName;
	}
	public String getVerbalAbuseMetByChildName() {
		return verbalAbuseMetByChildName;
	}
	public void setVerbalAbuseMetByChildName(String verbalAbuseMetByChildName) {
		this.verbalAbuseMetByChildName = verbalAbuseMetByChildName;
	}
	public String getPhysicalAbuseMetByChildName() {
		return physicalAbuseMetByChildName;
	}
	public void setPhysicalAbuseMetByChildName(String physicalAbuseMetByChildName) {
		this.physicalAbuseMetByChildName = physicalAbuseMetByChildName;
	}
	public String getSexualAbuseMetByChildName() {
		return sexualAbuseMetByChildName;
	}
	public void setSexualAbuseMetByChildName(String sexualAbuseMetByChildName) {
		this.sexualAbuseMetByChildName = sexualAbuseMetByChildName;
	}
	public String getOtherAbuseMetByChildNames() {
		return otherAbuseMetByChildNames;
	}
	public void setOtherAbuseMetByChildNames(String otherAbuseMetByChildNames) {
		this.otherAbuseMetByChildNames = otherAbuseMetByChildNames;
	}
	public String getIllTreatmentDOFName() {
		return illTreatmentDOFName;
	}
	public void setIllTreatmentDOFName(String illTreatmentDOFName) {
		this.illTreatmentDOFName = illTreatmentDOFName;
	}
	public String getIllTreatmentBMName() {
		return illTreatmentBMName;
	}
	public void setIllTreatmentBMName(String illTreatmentBMName) {
		this.illTreatmentBMName = illTreatmentBMName;
	}
	public String getIllTreatmentCIName() {
		return illTreatmentCIName;
	}
	public void setIllTreatmentCIName(String illTreatmentCIName) {
		this.illTreatmentCIName = illTreatmentCIName;
	}
	public String getIllTreatmentDPName() {
		return illTreatmentDPName;
	}
	public void setIllTreatmentDPName(String illTreatmentDPName) {
		this.illTreatmentDPName = illTreatmentDPName;
	}
	public String getIllTreatmentOtherNames() {
		return illTreatmentOtherNames;
	}
	public void setIllTreatmentOtherNames(String illTreatmentOtherNames) {
		this.illTreatmentOtherNames = illTreatmentOtherNames;
	}
	public String getExploitaionFacedByTheChildName() {
		return exploitaionFacedByTheChildName;
	}
	public void setExploitaionFacedByTheChildName(
			String exploitaionFacedByTheChildName) {
		this.exploitaionFacedByTheChildName = exploitaionFacedByTheChildName;
	}
	public String getHouseholdArticlesOBFName() {
		return householdArticlesOBFName;
	}
	public void setHouseholdArticlesOBFName(String householdArticlesOBFName) {
		this.householdArticlesOBFName = householdArticlesOBFName;
	}
	public String getVehiclesOBFName() {
		return vehiclesOBFName;
	}
	public void setVehiclesOBFName(String vehiclesOBFName) {
		this.vehiclesOBFName = vehiclesOBFName;
	}
	public String getSocialActivitesOfFamilyMembersName() {
		return socialActivitesOfFamilyMembersName;
	}
	public void setSocialActivitesOfFamilyMembersName(
			String socialActivitesOfFamilyMembersName) {
		this.socialActivitesOfFamilyMembersName = socialActivitesOfFamilyMembersName;
	}
	public String getParentalCareTowardsChildBeforeAdmissionName() {
		return parentalCareTowardsChildBeforeAdmissionName;
	}
	public void setParentalCareTowardsChildBeforeAdmissionName(
			String parentalCareTowardsChildBeforeAdmissionName) {
		this.parentalCareTowardsChildBeforeAdmissionName = parentalCareTowardsChildBeforeAdmissionName;
	}
	public String getDetailsOfDelinquentBehaviourName() {
		return detailsOfDelinquentBehaviourName;
	}
	public void setDetailsOfDelinquentBehaviourName(
			String detailsOfDelinquentBehaviourName) {
		this.detailsOfDelinquentBehaviourName = detailsOfDelinquentBehaviourName;
	}
	public String getReasonForDelinquentBehaviourName() {
		return reasonForDelinquentBehaviourName;
	}
	public void setReasonForDelinquentBehaviourName(
			String reasonForDelinquentBehaviourName) {
		this.reasonForDelinquentBehaviourName = reasonForDelinquentBehaviourName;
	}
	public String getChildBadHabitsName() {
		return childBadHabitsName;
	}
	public void setChildBadHabitsName(String childBadHabitsName) {
		this.childBadHabitsName = childBadHabitsName;
	}
	public String getChildGoodHabitsName() {
		return childGoodHabitsName;
	}
	public void setChildGoodHabitsName(String childGoodHabitsName) {
		this.childGoodHabitsName = childGoodHabitsName;
	}
	public String getDetailsOfIncomeUtilizationName() {
		return detailsOfIncomeUtilizationName;
	}
	public void setDetailsOfIncomeUtilizationName(
			String detailsOfIncomeUtilizationName) {
		this.detailsOfIncomeUtilizationName = detailsOfIncomeUtilizationName;
	}
	public String getDetailsOfSavingName() {
		return detailsOfSavingName;
	}
	public void setDetailsOfSavingName(String detailsOfSavingName) {
		this.detailsOfSavingName = detailsOfSavingName;
	}
	public String getReasonBehindLeavingSchoolName() {
		return reasonBehindLeavingSchoolName;
	}
	public void setReasonBehindLeavingSchoolName(
			String reasonBehindLeavingSchoolName) {
		this.reasonBehindLeavingSchoolName = reasonBehindLeavingSchoolName;
	}
	public String getExtraCurricularActivitiesName() {
		return extraCurricularActivitiesName;
	}
	public void setExtraCurricularActivitiesName(
			String extraCurricularActivitiesName) {
		this.extraCurricularActivitiesName = extraCurricularActivitiesName;
	}
	public String getFriendshipPriorToAdmissionIntoChildrensHomeName() {
		return friendshipPriorToAdmissionIntoChildrensHomeName;
	}
	public void setFriendshipPriorToAdmissionIntoChildrensHomeName(
			String friendshipPriorToAdmissionIntoChildrensHomeName) {
		this.friendshipPriorToAdmissionIntoChildrensHomeName = friendshipPriorToAdmissionIntoChildrensHomeName;
	}
	public String getMajorityFriendsAreName() {
		return majorityFriendsAreName;
	}
	public void setMajorityFriendsAreName(String majorityFriendsAreName) {
		this.majorityFriendsAreName = majorityFriendsAreName;
	}
	public String getDetailOfMembershipInGroupName() {
		return detailOfMembershipInGroupName;
	}
	public void setDetailOfMembershipInGroupName(
			String detailOfMembershipInGroupName) {
		this.detailOfMembershipInGroupName = detailOfMembershipInGroupName;
	}
	public String getPurposeOfTakingMembershipInGroupName() {
		return purposeOfTakingMembershipInGroupName;
	}
	public void setPurposeOfTakingMembershipInGroupName(
			String purposeOfTakingMembershipInGroupName) {
		this.purposeOfTakingMembershipInGroupName = purposeOfTakingMembershipInGroupName;
	}
	public String getChildSex() {
		return childSex;
	}
	public void setChildSex(String childSex) {
		this.childSex = childSex;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getChildImgpath() {
		return childImgpath;
	}
	public void setChildImgpath(String childImgpath) {
		this.childImgpath = childImgpath;
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
	public String getTimeToString() {
		return timeToString;
	}
	public void setTimeToString(String timeToString) {
		this.timeToString = timeToString;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
	
}