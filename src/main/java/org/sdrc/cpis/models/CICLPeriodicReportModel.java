package org.sdrc.cpis.models;


import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class CICLPeriodicReportModel {

	private Integer id;	
	private String childId;
	private String firNumber;
	private String policeStation;	
	private String sections;
	private String matter;
	private String vs;
	private String childName;	
	private Integer age;	
	private Date entryDate;	
	private ValueObject childUnderCare;
	private String childUnderSupervision;
	private String regNo;
	private ValueObject sexObject;
	private String fatherName;	
	private ValueObject religionObject;	
	private ValueObject casteObject;	
	private String casteOtherType;		// other than sc, st etc.
	private ValueObject educationObject;
	private String vocationalTraining;
	private ValueObject languageObject;	// Medium Instruction column name from type_details table
	private String otherLanguage;
	private Date nextCourtDate;
	private String employmentDetails;
	private Date dateOfAdmission;
	private String caseDetailsAndSummary;
	private Date visitDate;
	private String nameOfParentGuardian;
	private String nameOfOtherAdults;
	private String behaviourOfChild;
	private String physicalAndMentalHealthStatus;
	private String interpersonalRelationshipChildwithFamily;
	private String interpersonalRelationshipChildwithFriends;
	private String safetyAndSupervisionInFamily;
	private String difficultiesFacedByChild;
	private String difficultiesFacedByFamily;
	private String changesInHousehold;
	private String vocationalTrainingByChild;
	private String engagementOfChildInAntiSocialActivities;
	private String timeElapsed;
	private String nameOfSchoolOrCenter;
	private String nameOFTeacherOrPrincipal;
	private String unusualBehaviour;
	private String feedbackRecived;
	private String attitudeOfPeersToChild;
	private String attitudeOFChildToPeers;
	private String natureOfWork;
	private Integer workingHours;
	private String attitudeOfChildToWork;
	private String violationOfLabourLawsAndActionTaken;
	private boolean spentTimeSpeakingPrivately;
	private String detailsSpentTimeSpeakingPrivately;
	private String recommendation;
	private String progressMadeAsRehabilition;
	private String preparedBy;
	private Date rehabilitionDate;
	private Date planDateOfNextVisit;
	private String actionPoint;
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
	public String getFirNumber() {
		return firNumber;
	}
	public void setFirNumber(String firNumber) {
		this.firNumber = firNumber;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getVs() {
		return vs;
	}
	public void setVs(String vs) {
		this.vs = vs;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public ValueObject getChildUnderCare() {
		return childUnderCare;
	}
	public void setChildUnderCare(ValueObject childUnderCare) {
		this.childUnderCare = childUnderCare;
	}
	public String getChildUnderSupervision() {
		return childUnderSupervision;
	}
	public void setChildUnderSupervision(String childUnderSupervision) {
		this.childUnderSupervision = childUnderSupervision;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public ValueObject getReligionObject() {
		return religionObject;
	}
	public void setReligionObject(ValueObject religionObject) {
		this.religionObject = religionObject;
	}
	public ValueObject getCasteObject() {
		return casteObject;
	}
	public void setCasteObject(ValueObject casteObject) {
		this.casteObject = casteObject;
	}
	public String getCasteOtherType() {
		return casteOtherType;
	}
	public void setCasteOtherType(String casteOtherType) {
		this.casteOtherType = casteOtherType;
	}
	public String getVocationalTraining() {
		return vocationalTraining;
	}
	public void setVocationalTraining(String vocationalTraining) {
		this.vocationalTraining = vocationalTraining;
	}
	public String getOtherLanguage() {
		return otherLanguage;
	}
	public void setOtherLanguage(String otherLanguage) {
		this.otherLanguage = otherLanguage;
	}
	public Date getNextCourtDate() {
		return nextCourtDate;
	}
	public ValueObject getSexObject() {
		return sexObject;
	}
	public void setSexObject(ValueObject sexObject) {
		this.sexObject = sexObject;
	}
	public ValueObject getEducationObject() {
		return educationObject;
	}
	public void setEducationObject(ValueObject educationObject) {
		this.educationObject = educationObject;
	}
	public ValueObject getLanguageObject() {
		return languageObject;
	}
	public void setLanguageObject(ValueObject languageObject) {
		this.languageObject = languageObject;
	}
	public void setNextCourtDate(Date nextCourtDate) {
		this.nextCourtDate = nextCourtDate;
	}
	public String getEmploymentDetails() {
		return employmentDetails;
	}
	public void setEmploymentDetails(String employmentDetails) {
		this.employmentDetails = employmentDetails;
	}
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	public String getCaseDetailsAndSummary() {
		return caseDetailsAndSummary;
	}
	public void setCaseDetailsAndSummary(String caseDetailsAndSummary) {
		this.caseDetailsAndSummary = caseDetailsAndSummary;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getNameOfParentGuardian() {
		return nameOfParentGuardian;
	}
	public void setNameOfParentGuardian(String nameOfParentGuardian) {
		this.nameOfParentGuardian = nameOfParentGuardian;
	}
	public String getNameOfOtherAdults() {
		return nameOfOtherAdults;
	}
	public void setNameOfOtherAdults(String nameOfOtherAdults) {
		this.nameOfOtherAdults = nameOfOtherAdults;
	}
	public String getBehaviourOfChild() {
		return behaviourOfChild;
	}
	public void setBehaviourOfChild(String behaviourOfChild) {
		this.behaviourOfChild = behaviourOfChild;
	}
	public String getPhysicalAndMentalHealthStatus() {
		return physicalAndMentalHealthStatus;
	}
	public void setPhysicalAndMentalHealthStatus(String physicalAndMentalHealthStatus) {
		this.physicalAndMentalHealthStatus = physicalAndMentalHealthStatus;
	}
	public String getInterpersonalRelationshipChildwithFamily() {
		return interpersonalRelationshipChildwithFamily;
	}
	public void setInterpersonalRelationshipChildwithFamily(String interpersonalRelationshipChildwithFamily) {
		this.interpersonalRelationshipChildwithFamily = interpersonalRelationshipChildwithFamily;
	}
	public String getInterpersonalRelationshipChildwithFriends() {
		return interpersonalRelationshipChildwithFriends;
	}
	public void setInterpersonalRelationshipChildwithFriends(String interpersonalRelationshipChildwithFriends) {
		this.interpersonalRelationshipChildwithFriends = interpersonalRelationshipChildwithFriends;
	}
	public String getSafetyAndSupervisionInFamily() {
		return safetyAndSupervisionInFamily;
	}
	public void setSafetyAndSupervisionInFamily(String safetyAndSupervisionInFamily) {
		this.safetyAndSupervisionInFamily = safetyAndSupervisionInFamily;
	}
	public String getDifficultiesFacedByChild() {
		return difficultiesFacedByChild;
	}
	public void setDifficultiesFacedByChild(String difficultiesFacedByChild) {
		this.difficultiesFacedByChild = difficultiesFacedByChild;
	}
	public String getDifficultiesFacedByFamily() {
		return difficultiesFacedByFamily;
	}
	public void setDifficultiesFacedByFamily(String difficultiesFacedByFamily) {
		this.difficultiesFacedByFamily = difficultiesFacedByFamily;
	}
	public String getChangesInHousehold() {
		return changesInHousehold;
	}
	public void setChangesInHousehold(String changesInHousehold) {
		this.changesInHousehold = changesInHousehold;
	}
	public String getVocationalTrainingByChild() {
		return vocationalTrainingByChild;
	}
	public void setVocationalTrainingByChild(String vocationalTrainingByChild) {
		this.vocationalTrainingByChild = vocationalTrainingByChild;
	}
	public String getEngagementOfChildInAntiSocialActivities() {
		return engagementOfChildInAntiSocialActivities;
	}
	public void setEngagementOfChildInAntiSocialActivities(String engagementOfChildInAntiSocialActivities) {
		this.engagementOfChildInAntiSocialActivities = engagementOfChildInAntiSocialActivities;
	}
	public String getTimeElapsed() {
		return timeElapsed;
	}
	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	public String getNameOfSchoolOrCenter() {
		return nameOfSchoolOrCenter;
	}
	public void setNameOfSchoolOrCenter(String nameOfSchoolOrCenter) {
		this.nameOfSchoolOrCenter = nameOfSchoolOrCenter;
	}
	public String getNameOFTeacherOrPrincipal() {
		return nameOFTeacherOrPrincipal;
	}
	public void setNameOFTeacherOrPrincipal(String nameOFTeacherOrPrincipal) {
		this.nameOFTeacherOrPrincipal = nameOFTeacherOrPrincipal;
	}
	public String getUnusualBehaviour() {
		return unusualBehaviour;
	}
	public void setUnusualBehaviour(String unusualBehaviour) {
		this.unusualBehaviour = unusualBehaviour;
	}
	public String getFeedbackRecived() {
		return feedbackRecived;
	}
	public void setFeedbackRecived(String feedbackRecived) {
		this.feedbackRecived = feedbackRecived;
	}
	public String getAttitudeOfPeersToChild() {
		return attitudeOfPeersToChild;
	}
	public void setAttitudeOfPeersToChild(String attitudeOfPeersToChild) {
		this.attitudeOfPeersToChild = attitudeOfPeersToChild;
	}
	public String getAttitudeOFChildToPeers() {
		return attitudeOFChildToPeers;
	}
	public void setAttitudeOFChildToPeers(String attitudeOFChildToPeers) {
		this.attitudeOFChildToPeers = attitudeOFChildToPeers;
	}
	public String getNatureOfWork() {
		return natureOfWork;
	}
	public void setNatureOfWork(String natureOfWork) {
		this.natureOfWork = natureOfWork;
	}
	public Integer getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Integer workingHours) {
		this.workingHours = workingHours;
	}
	public String getAttitudeOfChildToWork() {
		return attitudeOfChildToWork;
	}
	public void setAttitudeOfChildToWork(String attitudeOfChildToWork) {
		this.attitudeOfChildToWork = attitudeOfChildToWork;
	}
	public String getViolationOfLabourLawsAndActionTaken() {
		return violationOfLabourLawsAndActionTaken;
	}
	public void setViolationOfLabourLawsAndActionTaken(String violationOfLabourLawsAndActionTaken) {
		this.violationOfLabourLawsAndActionTaken = violationOfLabourLawsAndActionTaken;
	}
	public boolean isSpentTimeSpeakingPrivately() {
		return spentTimeSpeakingPrivately;
	}
	public void setSpentTimeSpeakingPrivately(boolean spentTimeSpeakingPrivately) {
		this.spentTimeSpeakingPrivately = spentTimeSpeakingPrivately;
	}
	public String getDetailsSpentTimeSpeakingPrivately() {
		return detailsSpentTimeSpeakingPrivately;
	}
	public void setDetailsSpentTimeSpeakingPrivately(String detailsSpentTimeSpeakingPrivately) {
		this.detailsSpentTimeSpeakingPrivately = detailsSpentTimeSpeakingPrivately;
	}
	public String getProgressMadeAsRehabilition() {
		return progressMadeAsRehabilition;
	}
	public void setProgressMadeAsRehabilition(String progressMadeAsRehabilition) {
		this.progressMadeAsRehabilition = progressMadeAsRehabilition;
	}
	public String getPreparedBy() {
		return preparedBy;
	}
	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	public Date getRehabilitionDate() {
		return rehabilitionDate;
	}
	public void setRehabilitionDate(Date rehabilitionDate) {
		this.rehabilitionDate = rehabilitionDate;
	}
	public Date getPlanDateOfNextVisit() {
		return planDateOfNextVisit;
	}
	public void setPlanDateOfNextVisit(Date planDateOfNextVisit) {
		this.planDateOfNextVisit = planDateOfNextVisit;
	}
	public String getActionPoint() {
		return actionPoint;
	}
	public void setActionPoint(String actionPoint) {
		this.actionPoint = actionPoint;
	}
	
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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
