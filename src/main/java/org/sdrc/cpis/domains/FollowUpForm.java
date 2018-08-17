package org.sdrc.cpis.domains;

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
@Table(name="follow_up_form")
public class FollowUpForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="date_of_visit")
	private Date dateOfVisit;
	
	@Column(name="child_photo")
	private String childPhoto;
	
	@Column(name="periodic_follow_up")
	private Integer periodicFollowUp;
	
	@Column(name="type_of_restoration")
	private Integer typeOfRestoration;
	
	@Column(name="availability_of_child")
	private Boolean availabilityOfChild;
	
	@Column(name="child_gone_where")
	private String childGoneWhere;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="date_of_restoration")
	private Date dateOfRestoration;
	
	@Column(name="adhaar_no")
	private String adhaarCardNo;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="mother_name")
	private String motherName;
	
	@Column(name="parent_temp_address")
	private String parentTemporaryAddress;
	
	@Column(name="parent_permanent_address")
	private String parentPermanentAddress;
	
	@Column(name="parent_landline_no")
	private String parentLandlineNo;
	
	@Column(name="parent_mob_no")
	private String parentMobileNo;
	
	@Column(name="father_adhaar_no")
	private String fatherAdhaarCardNo;
	
	@Column(name="mother_adhaar_no")
	private String motherAdhaarCardNo;
	
	@Column(name="key_points")
	private String keyPoints;
	
	@Column(name="child_height")
	private Double childHeight;
	
	@Column(name="child_weight")
	private Double childWeight;
	
	@Column(name="body_mass_index")
	private Double bodyMassIndex;
	
	@Column(name="child_look")
	private Integer childLook;
	
	@Column(name="school_attended")
	private Boolean schoolAttended;
	
	@Column(name="date_of_admission")
	private Date dateOfAdmission;
	
	@Column(name="class_of_study")
	private String classOfStudy;
	
	@Column(name="roll_no")
	private String rollNo;
	
	@Column(name="school_address")
	private String schoolAddress;
	
	@Column(name="school_type")
	private Integer schoolType;
	
	@Column(name="child_performance")
	private String childPerformance;
	
	@Column(name="teacher_name")
	private String teacherName;
	
	@Column(name="remark_of_teacher")
	private String remarksOfTeacher;
	
	@Column(name="available_items")
	private String availableItems;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="health_card_provided")
	private Boolean healthCardProvided;
	
	@Column(name="health_card_provided_reason")
	private String healthCardProvidedReason;
	
	@Column(name="routine_checkup")
	private Boolean routineCheckUp; 
	
	@Column(name="routine_checkup_reason")
	private String routineCheckupReason;
	
	@Column(name="illness")
	private Boolean illness; 
	
	@Column(name="illness_status_reason")
	private String illnessStatusReason;
	
	@Column(name="hospitalized")
	private Boolean hospitalized; 
	
	@Column(name="is_hospitalized_reason")
	private String isHospitalizedReason;
	
	@Column(name="health_remark")
	private String healthRemark;
	
	@Column(name="intellective_status")
	private Integer intellectiveStatus;
	
	@Column(name="skill_developed")
	private Boolean skillDeveloped;
	
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="institute_name")
	private String instituteName;
	
	@Column(name="course_status")
	private Integer courseStatus;
	
	@Column(name="vocational_status")
	private Integer vocationalProgress;
	
	@Column(name="vocational_progress_status")
	private String vocationalProgressStatus;
	
	@Column(name="parents_behaviour")
	private Integer parentsBehaviour;
	
	@Column(name="sexually_abused")
	private Boolean sexuallyAbused;
	
	@Column(name="beaten_by_parents")
	private Boolean beatenByParents;
	
	@Column(name="beaten_by_parents_frequency")
	private String beatenByParentsFrequency;
	
	@Column(name="child_do_household_chores")
	private Boolean childDoHouseholdChores;
	
	@Column(name="type_of_work")
	private String typeOfWork;
	
	@Column(name="childs_behaviour")
	private String childsBehaviour;
	
	@Column(name="compliance_by_govt")
	private String complianceByGovt;
	
	@Column(name="time_spent_with_parents")
	private String timeSpentWithParents;
	
	@Column(name="behaviour_of_neighbour")
	private String behaviourOfNeighbour;
	
	@Column(name="child_share_problems")
	private Boolean childShareProblems;
	
	@Column(name="problem_share_time")
	private String problemShareTime;
	
	@Column(name="heading_for_facilitation")
	private Boolean headingForFacilitation;
	
	@Column(name="scheme_name")
	private String schemeName;
	
	@Column(name="type_of_facilitation")
	private String typeOfFacilitation;
	
	@Column(name="key_remarks")
	private String keyRemarks;
	
	@Column(name="other_compliance_by_govt")
	private String otherComplianceByGovt;

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

	public Date getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(Date dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public Integer getPeriodicFollowUp() {
		return periodicFollowUp;
	}

	public void setPeriodicFollowUp(Integer periodicFollowUp) {
		this.periodicFollowUp = periodicFollowUp;
	}

	
	public Integer getTypeOfRestoration() {
		return typeOfRestoration;
	}

	public void setTypeOfRestoration(Integer typeOfRestoration) {
		this.typeOfRestoration = typeOfRestoration;
	}

	public Boolean getAvailabilityOfChild() {
		return availabilityOfChild;
	}

	public void setAvailabilityOfChild(Boolean availabilityOfChild) {
		this.availabilityOfChild = availabilityOfChild;
	}

	public String getChildGoneWhere() {
		return childGoneWhere;
	}

	public void setChildGoneWhere(String childGoneWhere) {
		this.childGoneWhere = childGoneWhere;
	}

	public Date getDateOfRestoration() {
		return dateOfRestoration;
	}

	public void setDateOfRestoration(Date dateOfRestoration) {
		this.dateOfRestoration = dateOfRestoration;
	}

	public String getAdhaarCardNo() {
		return adhaarCardNo;
	}

	public void setAdhaarCardNo(String adhaarCardNo) {
		this.adhaarCardNo = adhaarCardNo;
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

	public String getParentTemporaryAddress() {
		return parentTemporaryAddress;
	}

	public void setParentTemporaryAddress(String parentTemporaryAddress) {
		this.parentTemporaryAddress = parentTemporaryAddress;
	}

	public String getParentPermanentAddress() {
		return parentPermanentAddress;
	}

	public void setParentPermanentAddress(String parentPermanentAddress) {
		this.parentPermanentAddress = parentPermanentAddress;
	}

	public String getParentLandlineNo() {
		return parentLandlineNo;
	}

	public void setParentLandlineNo(String parentLandlineNo) {
		this.parentLandlineNo = parentLandlineNo;
	}

	public String getParentMobileNo() {
		return parentMobileNo;
	}

	public void setParentMobileNo(String parentMobileNo) {
		this.parentMobileNo = parentMobileNo;
	}

	public String getFatherAdhaarCardNo() {
		return fatherAdhaarCardNo;
	}

	public void setFatherAdhaarCardNo(String fatherAdhaarCardNo) {
		this.fatherAdhaarCardNo = fatherAdhaarCardNo;
	}

	public String getMotherAdhaarCardNo() {
		return motherAdhaarCardNo;
	}

	public void setMotherAdhaarCardNo(String motherAdhaarCardNo) {
		this.motherAdhaarCardNo = motherAdhaarCardNo;
	}

	public String getKeyPoints() {
		return keyPoints;
	}

	public void setKeyPoints(String keyPoints) {
		this.keyPoints = keyPoints;
	}

	public Double getChildHeight() {
		return childHeight;
	}

	public void setChildHeight(Double childHeight) {
		this.childHeight = childHeight;
	}

	public Double getChildWeight() {
		return childWeight;
	}

	public void setChildWeight(Double childWeight) {
		this.childWeight = childWeight;
	}

	public Double getBodyMassIndex() {
		return bodyMassIndex;
	}

	public void setBodyMassIndex(Double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}

	public Integer getChildLook() {
		return childLook;
	}

	public void setChildLook(Integer childLook) {
		this.childLook = childLook;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public String getClassOfStudy() {
		return classOfStudy;
	}

	public void setClassOfStudy(String classOfStudy) {
		this.classOfStudy = classOfStudy;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public Integer getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(Integer schoolType) {
		this.schoolType = schoolType;
	}

	public String getChildPerformance() {
		return childPerformance;
	}

	public void setChildPerformance(String childPerformance) {
		this.childPerformance = childPerformance;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getRemarksOfTeacher() {
		return remarksOfTeacher;
	}

	public void setRemarksOfTeacher(String remarksOfTeacher) {
		this.remarksOfTeacher = remarksOfTeacher;
	}

	public String getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(String availableItems) {
		this.availableItems = availableItems;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getHealthCardProvided() {
		return healthCardProvided;
	}

	public void setHealthCardProvided(Boolean healthCardProvided) {
		this.healthCardProvided = healthCardProvided;
	}

	public String getHealthCardProvidedReason() {
		return healthCardProvidedReason;
	}

	public void setHealthCardProvidedReason(String healthCardProvidedReason) {
		this.healthCardProvidedReason = healthCardProvidedReason;
	}

	public Boolean getRoutineCheckUp() {
		return routineCheckUp;
	}

	public void setRoutineCheckUp(Boolean routineCheckUp) {
		this.routineCheckUp = routineCheckUp;
	}

	public String getRoutineCheckupReason() {
		return routineCheckupReason;
	}

	public void setRoutineCheckupReason(String routineCheckupReason) {
		this.routineCheckupReason = routineCheckupReason;
	}

	public Boolean getIllness() {
		return illness;
	}

	public void setIllness(Boolean illness) {
		this.illness = illness;
	}

	public String getIllnessStatusReason() {
		return illnessStatusReason;
	}

	public void setIllnessStatusReason(String illnessStatusReason) {
		this.illnessStatusReason = illnessStatusReason;
	}

	public Boolean getHospitalized() {
		return hospitalized;
	}

	public void setHospitalized(Boolean hospitalized) {
		this.hospitalized = hospitalized;
	}

	public String getIsHospitalizedReason() {
		return isHospitalizedReason;
	}

	public void setIsHospitalizedReason(String isHospitalizedReason) {
		this.isHospitalizedReason = isHospitalizedReason;
	}

	public String getHealthRemark() {
		return healthRemark;
	}

	public void setHealthRemark(String healthRemark) {
		this.healthRemark = healthRemark;
	}

	public Integer getIntellectiveStatus() {
		return intellectiveStatus;
	}

	public void setIntellectiveStatus(Integer intellectiveStatus) {
		this.intellectiveStatus = intellectiveStatus;
	}

	public Boolean getSkillDeveloped() {
		return skillDeveloped;
	}

	public void setSkillDeveloped(Boolean skillDeveloped) {
		this.skillDeveloped = skillDeveloped;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public Integer getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Integer getVocationalProgress() {
		return vocationalProgress;
	}

	public void setVocationalProgress(Integer vocationalProgress) {
		this.vocationalProgress = vocationalProgress;
	}

	public String getVocationalProgressStatus() {
		return vocationalProgressStatus;
	}

	public void setVocationalProgressStatus(String vocationalProgressStatus) {
		this.vocationalProgressStatus = vocationalProgressStatus;
	}

	public Integer getParentsBehaviour() {
		return parentsBehaviour;
	}

	public void setParentsBehaviour(Integer parentsBehaviour) {
		this.parentsBehaviour = parentsBehaviour;
	}

	public Boolean getSexuallyAbused() {
		return sexuallyAbused;
	}

	public void setSexuallyAbused(Boolean sexuallyAbused) {
		this.sexuallyAbused = sexuallyAbused;
	}

	public Boolean getBeatenByParents() {
		return beatenByParents;
	}

	public void setBeatenByParents(Boolean beatenByParents) {
		this.beatenByParents = beatenByParents;
	}

	public String getBeatenByParentsFrequency() {
		return beatenByParentsFrequency;
	}

	public void setBeatenByParentsFrequency(String beatenByParentsFrequency) {
		this.beatenByParentsFrequency = beatenByParentsFrequency;
	}

	public Boolean getChildDoHouseholdChores() {
		return childDoHouseholdChores;
	}

	public void setChildDoHouseholdChores(Boolean childDoHouseholdChores) {
		this.childDoHouseholdChores = childDoHouseholdChores;
	}

	public String getTypeOfWork() {
		return typeOfWork;
	}

	public void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	public String getChildsBehaviour() {
		return childsBehaviour;
	}

	public void setChildsBehaviour(String childsBehaviour) {
		this.childsBehaviour = childsBehaviour;
	}

	public String getComplianceByGovt() {
		return complianceByGovt;
	}

	public void setComplianceByGovt(String complianceByGovt) {
		this.complianceByGovt = complianceByGovt;
	}

	public String getTimeSpentWithParents() {
		return timeSpentWithParents;
	}

	public void setTimeSpentWithParents(String timeSpentWithParents) {
		this.timeSpentWithParents = timeSpentWithParents;
	}

	public String getBehaviourOfNeighbour() {
		return behaviourOfNeighbour;
	}

	public void setBehaviourOfNeighbour(String behaviourOfNeighbour) {
		this.behaviourOfNeighbour = behaviourOfNeighbour;
	}

	public Boolean getChildShareProblems() {
		return childShareProblems;
	}

	public void setChildShareProblems(Boolean childShareProblems) {
		this.childShareProblems = childShareProblems;
	}

	public String getProblemShareTime() {
		return problemShareTime;
	}

	public void setProblemShareTime(String problemShareTime) {
		this.problemShareTime = problemShareTime;
	}

	public Boolean getHeadingForFacilitation() {
		return headingForFacilitation;
	}

	public void setHeadingForFacilitation(Boolean headingForFacilitation) {
		this.headingForFacilitation = headingForFacilitation;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getKeyRemarks() {
		return keyRemarks;
	}

	public void setKeyRemarks(String keyRemarks) {
		this.keyRemarks = keyRemarks;
	}

	public String getTypeOfFacilitation() {
		return typeOfFacilitation;
	}

	public void setTypeOfFacilitation(String typeOfFacilitation) {
		this.typeOfFacilitation = typeOfFacilitation;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getChildPhoto() {
		return childPhoto;
	}

	public void setChildPhoto(String childPhoto) {
		this.childPhoto = childPhoto;
	}

	public Boolean getSchoolAttended() {
		return schoolAttended;
	}

	public void setSchoolAttended(Boolean schoolAttended) {
		this.schoolAttended = schoolAttended;
	}

	public String getOtherComplianceByGovt() {
		return otherComplianceByGovt;
	}

	public void setOtherComplianceByGovt(String otherComplianceByGovt) {
		this.otherComplianceByGovt = otherComplianceByGovt;
	}
	
	
}
