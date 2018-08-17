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
@Table(name="cicl_periodic_report")
public class CICLPeriodicReport implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="fir_number")
	private String firNumber;
	
	@Column(name="police_station")
	private String policeStation;
	
	@Column(name="sections")
	private String sections;
	
	@Column(name="matter")
	private String matter;
	
	@Column(name="vs")
	private String vs;
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="entry_date")
	private Date entryDate;	
	
	@Column(name="child_under_care")
	private Integer childUnderCare;
	
	@Column(name="child_under_supervis")
	private String childUnderSupervision;
	
	@Column(name="reg_no")
	private String regNo;
	
	@Column(name="sex")
	private Integer sex;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="religion_object")
	private Integer religionObject;
		
	@Column(name="caste_object")
	private Integer casteObject;
	
	@Column(name="caste_other_type")
	private String casteOtherType;		
	
	@Column(name="education")
	private Integer education;
	
	@Column(name="vocational_training")
	private String vocationalTraining;
	
	@Column(name="languages")
	private Integer languages;	// Medium Instruction column name from type_details table
	
	@Column(name="other_language")
	private String otherLanguage;
	
	@Column(name="next_court_date")
	private Date nextCourtDate;
	
	@Column(name="employment_details")
	private String employmentDetails;
	
	@Column(name="date_of_admission")
	private Date dateOfAdmission;
	
	@Column(name="case_details_and_summary")
	private String caseDetailsAndSummary;
	
	@Column(name="visit_date")
	private Date visitDate;
	
	@Column(name="nameof_parent_guardian")
	private String nameOfParentGuardian;
	
	@Column(name="nameof_other_adults")
	private String nameOfOtherAdults;
	
	@Column(name="behaviour_of_child")
	private String behaviourOfChild;
	
	@Column(name="physical_and_mental_healthStatus")
	private String physicalAndMentalHealthStatus;
	
	@Column(name="interpersonal_relationship_childwithFamily")
	private String interpersonalRelationshipChildwithFamily;
	
	@Column(name="interpersonal_relationship_childwithFriends")
	private String interpersonalRelationshipChildwithFriends;
	
	@Column(name="safetyAndSupervision_in_family")
	private String safetyAndSupervisionInFamily;
	
	@Column(name="difficulties_facedByChild")
	private String difficultiesFacedByChild;
	
	@Column(name="difficulties_facedByFamily")
	private String difficultiesFacedByFamily;
	
	@Column(name="changes_In_household")
	private String changesInHousehold;
	
	@Column(name="vocationaltraining_ByChild")
	private String vocationalTrainingByChild;

	@Column(name="engagementofchild_in_antisocialactivities")
	private String engagementOfChildInAntiSocialActivities;
	
	@Column(name="time_elapsed")
	private String timeElapsed;
	
	@Column(name="nameof_schoolorcenter")
	private String nameOfSchoolOrCenter;
	
	@Column(name="nameof_teacherorprincipal")
	private String nameOFTeacherOrPrincipal;
	
	@Column(name="unusual_behaviour")
	private String unusualBehaviour;
	
	@Column(name="feedback_recived")
	private String feedbackRecived;
	
	@Column(name="attitudeof_peerstochild")
	private String attitudeOfPeersToChild;
	
	@Column(name="attitudeof_childtopeers")
	private String attitudeOFChildToPeers;
	
	@Column(name="nature_of_work")
	private String natureOfWork;
	
	@Column(name="working_hours")
	private Integer workingHours;
	
	@Column(name="attitudeof_childtowork")
	private String attitudeOfChildToWork;
	
	@Column(name="violationof_labourlaws_and_actiontaken")
	private String violationOfLabourLawsAndActionTaken;
	
	@Column(name="spenttime_speakingprivately")
	private boolean spentTimeSpeakingPrivately;
	
	@Column(name="details_spenttime_speakingprivately")
	private String detailsSpentTimeSpeakingPrivately;
	
	@Column(name="recommendation")
	private String recommendation;
	
	@Column(name="progressmade_as_rehabilition")
	private String progressMadeAsRehabilition;
	
	@Column(name="preparedby")
	private String preparedBy;
	
	@Column(name="rehabilition_date")
	private Date rehabilitionDate;
	
	@Column(name="plandate_of_nextvisit")
	private Date planDateOfNextVisit;
	
	@Column(name="actionpoint")
	private String actionPoint;

	
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


	public Integer getChildUnderCare() {
		return childUnderCare;
	}

	public void setChildUnderCare(Integer childUnderCare) {
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Integer getReligionObject() {
		return religionObject;
	}

	public void setReligionObject(Integer religionObject) {
		this.religionObject = religionObject;
	}
	
	public Integer getCasteObject() {
		return casteObject;
	}

	public void setCasteObject(Integer casteObject) {
		this.casteObject = casteObject;
	}

	public String getCasteOtherType() {
		return casteOtherType;
	}

	public void setCasteOtherType(String casteOtherType) {
		this.casteOtherType = casteOtherType;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getVocationalTraining() {
		return vocationalTraining;
	}

	public void setVocationalTraining(String vocationalTraining) {
		this.vocationalTraining = vocationalTraining;
	}

	public Integer getLanguages() {
		return languages;
	}

	public void setLanguages(Integer languages) {
		this.languages = languages;
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

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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
