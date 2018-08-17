package org.sdrc.cpis.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*FORM 10*/

@Entity
@Table(name="CICL_PeriodicReport_On_ChildProbation")
public class CICLPeriodicReportOnChildProbation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
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
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="found_Date")
	private Date foundDate;
	
	@Column(name="guardian_Name")
	private String guardianName;
	
	@Column(name="probationOfficer_Name")
	private String probationOfficerName;
	
	@Column(name="reg_no")
	private String regNo;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="father_Name")
	private String fatherName;
	
	@Column(name="region")
	private String religion;
	
	@Column(name="education")
	private String education;
	
	@Column(name="vocational_Traning")
	private String vocationalTraning;
	
	@Column(name="languages")
	private String languages;
	
	@Column(name="next_CourtDate")
	private Date nextCourtDate;
	
	@Column(name="employment")
	private String employment;
	
	@Column(name="dateOf_Admission")
	private Date dateOfAdmission;
	
	@Column(name="case_Details")
	private String caseDetails;
	
	@Column(name="visit_Date")
	private Date visitDate;
	
	@Column(name="other_FamilyMembers")
	private String otherFamilyMembers;
	
	@Column(name="child_Behaviour")
	private String childBehaviour;
	
	@Column(name="physical_Mental_HealthStatus")
	private String physicalMentalHealthStatus;
	
	@Column(name="childRelationship_WithFamily")
	private String childRelationshipWithFamily;
	
	@Column(name="childRelationship_WithFriends")
	private String childRelationshipWithFriends;
	
	@Column(name="difficulties_FacedByChild")
	private String difficultiesFacedByChild;
	
	@Column(name="difficulties_FacedByFamily")
	private String difficultiesFacedByFamily;
	
	@Column(name="changes_In_Hosehold")
	private String changesInHosehold;
	
	@Column(name="harmful_Activities")
	private String harmfulActivities;
	
	@Column(name="lastEngagement_in_HarmfulActivities")
	private String lastEngagementinHarmfulActivities;
	
	@Column(name="school_Name")
	private String schoolName;
	
	@Column(name="principal_Name")
	private String principalName;
	
	@Column(name="unusual_Behaviour")
	private String unusualBehaviour;
	
	@Column(name="feedback")
	private String feedback;
	
	@Column(name="attitude_Of_PeersToChild")
	private String attitudeOfPeersToChild;
	
	@Column(name="attitude_Of_ChildToPeers")
	private String attitudeOfChildToPeers;
	
	@Column(name="nature_Of_Work")
	private String natureOfWork;
	
	@Column(name="workingHours")
	private Integer workingHours;
	
	@Column(name="attitude_Of_ChildToWork")
	private String attitudeOfChildToWork;
	
	@Column(name="violation_Of_Law")
	private String violationOfLaw;
	
	@Column(name="timeSpentWithChild")
	private boolean timeSpentWithChild;
	
	@Column(name="progress_Status")
	private String progressStatus;
	
	@Column(name="preparedBy")
	private String preparedBy;
	
	@Column(name="next_VisitDate")
	private Date nextVisitDate;
	
	@Column(name="actiion_Point")
	private String actiionPoint;

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

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getProbationOfficerName() {
		return probationOfficerName;
	}

	public void setProbationOfficerName(String probationOfficerName) {
		this.probationOfficerName = probationOfficerName;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getVocationalTraning() {
		return vocationalTraning;
	}

	public void setVocationalTraning(String vocationalTraning) {
		this.vocationalTraning = vocationalTraning;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public Date getNextCourtDate() {
		return nextCourtDate;
	}

	public void setNextCourtDate(Date nextCourtDate) {
		this.nextCourtDate = nextCourtDate;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public String getCaseDetails() {
		return caseDetails;
	}

	public void setCaseDetails(String caseDetails) {
		this.caseDetails = caseDetails;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getOtherFamilyMembers() {
		return otherFamilyMembers;
	}

	public void setOtherFamilyMembers(String otherFamilyMembers) {
		this.otherFamilyMembers = otherFamilyMembers;
	}

	public String getChildBehaviour() {
		return childBehaviour;
	}

	public void setChildBehaviour(String childBehaviour) {
		this.childBehaviour = childBehaviour;
	}

	public String getPhysicalMentalHealthStatus() {
		return physicalMentalHealthStatus;
	}

	public void setPhysicalMentalHealthStatus(String physicalMentalHealthStatus) {
		this.physicalMentalHealthStatus = physicalMentalHealthStatus;
	}

	public String getChildRelationshipWithFamily() {
		return childRelationshipWithFamily;
	}

	public void setChildRelationshipWithFamily(String childRelationshipWithFamily) {
		this.childRelationshipWithFamily = childRelationshipWithFamily;
	}

	public String getChildRelationshipWithFriends() {
		return childRelationshipWithFriends;
	}

	public void setChildRelationshipWithFriends(String childRelationshipWithFriends) {
		this.childRelationshipWithFriends = childRelationshipWithFriends;
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

	public String getChangesInHosehold() {
		return changesInHosehold;
	}

	public void setChangesInHosehold(String changesInHosehold) {
		this.changesInHosehold = changesInHosehold;
	}

	public String getHarmfulActivities() {
		return harmfulActivities;
	}

	public void setHarmfulActivities(String harmfulActivities) {
		this.harmfulActivities = harmfulActivities;
	}

	public String getLastEngagementinHarmfulActivities() {
		return lastEngagementinHarmfulActivities;
	}

	public void setLastEngagementinHarmfulActivities(String lastEngagementinHarmfulActivities) {
		this.lastEngagementinHarmfulActivities = lastEngagementinHarmfulActivities;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getUnusualBehaviour() {
		return unusualBehaviour;
	}

	public void setUnusualBehaviour(String unusualBehaviour) {
		this.unusualBehaviour = unusualBehaviour;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getAttitudeOfPeersToChild() {
		return attitudeOfPeersToChild;
	}

	public void setAttitudeOfPeersToChild(String attitudeOfPeersToChild) {
		this.attitudeOfPeersToChild = attitudeOfPeersToChild;
	}

	public String getAttitudeOfChildToPeers() {
		return attitudeOfChildToPeers;
	}

	public void setAttitudeOfChildToPeers(String attitudeOfChildToPeers) {
		this.attitudeOfChildToPeers = attitudeOfChildToPeers;
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

	public String getViolationOfLaw() {
		return violationOfLaw;
	}

	public void setViolationOfLaw(String violationOfLaw) {
		this.violationOfLaw = violationOfLaw;
	}

	public boolean isTimeSpentWithChild() {
		return timeSpentWithChild;
	}

	public void setTimeSpentWithChild(boolean timeSpentWithChild) {
		this.timeSpentWithChild = timeSpentWithChild;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public Date getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(Date nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

	public String getActiionPoint() {
		return actiionPoint;
	}

	public void setActiionPoint(String actiionPoint) {
		this.actiionPoint = actiionPoint;
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
