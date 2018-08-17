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

/**
 * @author SDRC_DEV
 *
 */
@Entity
@Table(name="case_history_cci")
public class CaseHistoryCCI implements Serializable {

	private static final long serialVersionUID = -4597772064095492601L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="time")
	private Time time;
	
	@Column(name="child_image_path")
	private String childImagePath;
	
	@Column(name="age_at_time_of_admission")
	private Integer ageAtTimeOfAdmission;
	
	@Column(name="present_age")
	private Integer presentAge;
	
	@Column(name="category")
	private String category;
	
	@Column(name="category_details")
	private String categoryDetails;
	
	@Column(name="category_details_others")
	private String categoryDetailsOthers;
	
	@Column(name="religion")
	private Integer religion;
	
	@Column(name="religion_others")
	private String religionOthers;
	
	@Column(name="caste")
	private Integer caste;
	
	@Column(name="native_district")
	private String nativeDistrict;
	
	@Column(name="native_state")
	private String nativeState;
	
	@Column(name="description_of_housing1")
	private Integer descriptionOfHousing1;
	
	@Column(name="description_of_housing2")
	private Integer descriptionOfHousing2;
	
	@Column(name="description_of_housing3")
	private Integer descriptionOfHousing3;
	
	@Column(name="child_brought_before_cwc_by_whom")
	private Integer childBroughtBeforeCWCByWhom;
	
	@Column(name="child_brought_before_cwc_by_whom_relationship")
	private String childBroughtBeforeCWCByWhomRelationship;
	
	@Column(name="reasons_for_leaving_family")
	private String reasonsForLeavingFamily;
	
	@Column(name="reasons_for_leaving_family_others")
	private String reasonsForLeavingFamilyOthers;
	
	@Column(name="verbal_abuse_met_by_child")
	private String verbalAbuseMetByChild;
	
	@Column(name="verbal_abuse_met_by_child_others")
	private String verbalAbuseMetByChildOthers;
	
	@Column(name="physical_abuse_met_by_child")
	private String physicalAbuseMetByChild;
	
	@Column(name="physical_abuse_met_by_child_others")
	private String physicalAbuseMetByChildOthers;
	
	@Column(name="sexual_abuse_met_by_child")
	private String sexualAbuseMetByChild;
	
	@Column(name="sexual_abuse_met_by_child_others")
	private String sexualAbuseMetByChildOthers;
	
	@Column(name="other_abuse_met_by_child_name")
	private String otherAbuseMetByChildName;
	
	@Column(name="other_abuse_met_by_child")
	private String otherAbuseMetByChild;
	
	@Column(name="other_abuse_met_by_child_others")
	private String otherAbuseMetByChildOthers;
	
	@Column(name="ill_treatment_dof")
	private String illTreatmentdOF;
	
	@Column(name="ill_treatment_dof_others")
	private String illTreatmentDOFOthers;
	
	@Column(name="ill_treatment_bm")
	private String illTreatmentBM;
	
	@Column(name="ill_treatment_bm_others")
	private String illTreatmentBMOthers;
	
	@Column(name="ill_treatment_ci")
	private String illTreatmentCI;
	
	@Column(name="ill_treatment_ci_others")
	private String illTreatmentCIOthers;
	
	@Column(name="ill_treatment_dp")
	private String illTreatmentDP;
	
	@Column(name="ill_treatment_dp_others")
	private String illTreatmentDPOthers;
	
	@Column(name="ill_treatment_other_name")
	private String illTreatmentOtherName;
	
	@Column(name="ill_treatment_other")
	private String illTreatmentOther;
	
	@Column(name="tll_treatment_other_other")
	private String illTreatmentOtherOther;
	
	@Column(name="exploitation_faced_by_the_child")
	private String exploitaionFacedByTheChild;
	
	@Column(name="exploitation_faced_by_the_childOthers")
	private String exploitaionFacedByTheChildOthers;
	
	@Column(name="respiratory_disorders")
	private Integer respiratoryDisorders;
	
	@Column(name="hearing_impairment")
	private Integer hearingImpairment;
	
	@Column(name="eye_diseases")
	private Integer eyeDiseases;
	
	@Column(name="dental_disease")
	private Integer dentalDisease;
	
	@Column(name="cardiacDiseases")
	private Integer cardiacDiseases;
	
	@Column(name="skin_disease")
	private Integer skinDisease;
	
	@Column(name="sexually_transmitted_diseases")
	private Integer sexuallyTransmittedDiseases;
	
	@Column(name="neurological_disorders")
	private Integer neurologicalDisorders;
	
	@Column(name="mental_handicap")
	private Integer mentalHandicap;
	
	@Column(name="physical_handicap")
	private Integer physicalHandicap;
	
	@Column(name="urinary_tract_infections")
	private Integer urinaryTractInfections;
	
	@Column(name="other_health_issues")
	private Integer otherHealthIssues;
	
	@Column(name="other_health_issues_name")
	private String otherHealthIssuesName;
	
	@Column(name="child_was_staying_with_whom_prior_to_admission")
	private Integer childWasStayingWithWhomPriorToAdmission;
	
	@Column(name="child_was_staying_with_whom_prior_to_admission_other")
	private String childWasStayingWithWhomPriorToAdmissionOther;
	
	@Column(name="visit_of_parents_prior_to_institutionalization")
	private Integer visitOfParentsPriorToInstitutionalization;
	
	@Column(name="visit_of_parents_after_institutionalization")
	private Integer visitOfParentsAfterInstitutionalization;
	
	@Column(name="visit_of_child_prior_to_institutionalization")
	private Integer visitOfChildPriorToInstitutionalization;
	
	@Column(name="visit_of_child_after_institutionalization")
	private Integer visitOfChildAfterInstitutionalization;
	
	@Column(name="correspondence_with_parent_prior_to_institutionalization")
	private Integer correspondenceWithParentPriorToInstitutionalization;
	
	@Column(name="correspondence_with_parent_after_institutionalization")
	private Integer correspondenceWithParentAfterInstitutionalization;
	
	@Column(name="details_of_disability")
	private String detailsOfDisability;
	
	@Column(name="family_type")
	private Integer familyType;
	
	@Column(name="relation_father_mother")
	private Integer relationFatherMother;
	
	@Column(name="relation_father_child")
	private Integer relationFatherChild;
	
	@Column(name="relation_mother_child")
	private Integer relationMotherChild;
	
	@Column(name="relation_father_siblings")
	private Integer relationFatherSiblings;
	
	@Column(name="relation_mother_siblings")
	private Integer relationMotherSiblings;
	
	@Column(name="relation_child_siblings")
	private Integer relationChildSiblings;
	
	@Column(name="relation_child_relative")
	private Integer relationChildRelative;
	
	@Column(name="landed_properties_obf") // owned by family (obf)
	private String landedPropertiesOBF;
	
	@Column(name="household_articles_obf") // owned by family (obf)
	private String householdArticlesOBF;
	
	@Column(name="vehicles_obf") // owned by family (obf)
	private String vehiclesOBF;
	
	@Column(name="others_obf") // owned by family (obf)
	private String othersOBF;
	
	@Column(name="parents_marraige_type")
	private Integer parentsMarraigeType;
	
	@Column(name="brothers_marraige_type")
	private Integer brothersMarraigeType;
	
	@Column(name="sisters_marraige_type")
	private Integer sistersMarraigeType;
	
	@Column(name="social_activities_of_family_member")
	private String socialActivitesOfFamilyMembers;
	
	@Column(name="parental_care_towards_child_before_admission")
	private String parentalCareTowardsChildBeforeAdmission;
	
	@Column(name="child_attain_puberty_age")
	private Integer childAttainPubertyAge;
	
	@Column(name="details_of_delinquent_behaviour")
	private String detailsOfDelinquentBehaviour;
	
	@Column(name="details_of_delinquent_behaviour_others")
	private String detailsOfDelinquentBehaviourOthers;
	
	@Column(name="reason_for_delinquent_behaviour")
	private String reasonForDelinquentBehaviour;
	
	@Column(name="reason_for_delinquent_behaviour_others")
	private String reasonForDelinquentBehaviourOthers;
	
	@Column(name="child_good_habits")
	private String childGoodHabits;
	
	@Column(name="child_good_habits_others")
	private String childGoodHabitsOthers;
	
	@Column(name="child_bad_habits")
	private String childBadHabits;
	
	@Column(name="child_bad_habits_others")
	private String childBadHabitsOthers;
	
	/*@Column(name="cooly_time_duration")
	private String coolyTimeAndDuration;
	
	@Column(name="cooly_wages_earned")
	private Double coolyWagesEarned;
	
	@Column(name="rag_picking_time_duration")
	private String ragPickingTimeAndDuration;
	
	@Column(name="rag_picking_wages_earned")
	private Double ragPickingWagesEarned;
	
	@Column(name="meachanic_time_duration")
	private String mechanicTimeAndDuration;
	
	@Column(name="mechanic_wages_earned")
	private Double mechanicWagesEarned;
	
	@Column(name="hotel_work_time_duration")
	private String hotelWorkTimeAndDuration;
	
	@Column(name="hotel_work_wages_earned")
	private Double hotelWorkWagesEarned;
	
	@Column(name="tea_shop_work_time_duration")
	private String teaShopWorkTimeAndDuration;
	
	@Column(name="tea_shop_work_wages_earned")
	private Double teaShopWorkWagesEarned;
	
	@Column(name="shoe_polish_time_duration")
	private String shoePolishTimeAndDuration;
	
	@Column(name="shoe_polish_wages_earned")
	private Double shoePolishWagesEarned;
	
	@Column(name="household_work_time_duration")
	private String houseHoldWorkTimeAndDuration;
	
	@Column(name="household_work_wages_earned")
	private Double houseHoldWorkWagesEarned;
	
	@Column(name="other_employment_name")
	private String otherEmploymentName;
	
	@Column(name="other_employment_time_duration")
	private String otherEmploymentTimeAndDuration;
	
	@Column(name="other_employment_wages_earned")
	private Double otherEmploymentWagesEarned;*/
	
	@Column(name="details_of_income_utilization")
	private String detailsOfIncomeUtilization;
	
	@Column(name="details_of_saving")
	private String detailsOfSaving;
	
	@Column(name="details_of_saving_others")
	private String detailsOfSavingOthers;
	
	@Column(name="duration_of_working_hours")
	private Integer durationOfWorkingHours;
	
	@Column(name="details_of_past_education")
	private Integer detailsOfPastEducation;
	
	@Column(name="reason_behind_leaving_school")
	private String reasonBehindLeavingSchool;
	
	@Column(name="reason_behind_leaving_school_others")
	private String reasonBehindLeavingSchoolOthers;
	
	@Column(name="details_of_school_studied_last")
	private Integer detailsOfSchoolStudiedLast;
	
	@Column(name="school_medium_instruction")
	private Integer schoolMediumInstruction;
	
	@Column(name="school_medium_instruction_others")
	private String schoolMediumInstructionOthers;
	
	@Column(name="educational_attainment_no_of_years")
	private Double educationalAttainmentNoOfYears;
	
	@Column(name="educational_attainment_class_studied")
	private String educationalAttainmentClassStudied;
	
	@Column(name="educational_attainment_promote_detained")
	private Integer educationalAttainmentPromote_Detained;
	
	@Column(name="vocational_training_no_of_years")
	private Double voactionalTrainingNoOfYears;
	
	@Column(name="vocational_training_name_of_trade")
	private String vocationalTrainingNameOfTrade;
	
	@Column(name="vocational_training_proficiency_obtained")
	private String vocationalTrainingProficiencyObtained;
	
	@Column(name="details_of_certification_path")
	private String detailsOfCertificationPath;
	
	@Column(name="extra_curricular_activities")
	private String extraCurricularActivities;
	
	@Column(name="extra_curricular_activities_others")
	private String extraCurricularActivitiesOthers;
	
	@Column(name="height_at_time_of_admission")
	private String heightAtTimeOfAdmission;
	
	@Column(name="weight_at_time_of_admission")
	private Double weightAtTimeOfAdmission;
	
	@Column(name="physical_condition")
	private String physicalCondition;
	
	@Column(name="medical_history_of_child")
	private String medicalHistoryOfChild;
	
	@Column(name="medical_history_of_parents")
	private String medicalHistoryOfParents;
	
	/*@Column(name="health_status_DOR_Q1")
	private Date healthStatusDORQ1;
	
	@Column(name="health_status_height_Q1")
	private String healthStatusHeightQ1;
	
	@Column(name="health_status_weight_Q1")
	private Double healthStatusWeightQ1;
	
	@Column(name="health_status_nutritious_diet_given_Q1")
	private String healthStatusNutritiousDietGivenQ1;
	
	@Column(name="health_status_stress_Q1")
	private String healthStatusStressQ1;
	
	@Column(name="health_status_dental_Q1")
	private String healthStatusDentalQ1;
	
	@Column(name="health_status_ent_Q1")
	private String healthStatusENTQ1;
	
	@Column(name="health_status_eye_Q1")
	private String healthStatusEyeQ1;
	
	@Column(name="health_status_DOR_Q2")
	private Date healthStatusDORQ2;
	
	@Column(name="health_status_height_Q2")
	private String healthStatusHeightQ2;
	
	@Column(name="health_status_weight_Q2")
	private Double healthStatusWeightQ2;
	
	@Column(name="health_status_nutritious_diet_given_Q2")
	private String healthStatusNutritiousDietGivenQ2;
	
	@Column(name="health_status_stress_Q2")
	private String healthStatusStressQ2;
	
	@Column(name="health_status_dental_Q2")
	private String healthStatusDentalQ2;
	
	@Column(name="health_status_ent_Q2")
	private String healthStatusENTQ2;
	
	@Column(name="health_status_eye_Q2")
	private String healthStatusEyeQ2;
	
	@Column(name="health_status_DOR_Q3")
	private Date healthStatusDORQ3;
	
	@Column(name="health_status_height_Q3")
	private String healthStatusHeightQ3;
	
	@Column(name="health_status_weight_Q3")
	private Double healthStatusWeightQ3;
	
	@Column(name="health_status_nutritious_diet_given_Q3")
	private String healthStatusNutritiousDietGivenQ3;
	
	@Column(name="health_status_stress_Q3")
	private String healthStatusStressQ3;
	
	@Column(name="health_status_dental_Q3")
	private String healthStatusDentalQ3;
	
	@Column(name="health_status_ent_Q3")
	private String healthStatusENTQ3;
	
	@Column(name="health_status_eye_Q3")
	private String healthStatusEyeQ3;
	
	@Column(name="health_status_DOR_Q4")
	private Date healthStatusDORQ4;
	
	@Column(name="health_status_height_Q4")
	private String healthStatusHeightQ4;
	
	@Column(name="health_status_weight_Q4")
	private Double healthStatusWeightQ4;
	
	@Column(name="health_status_nutritious_diet_given_Q4")
	private String healthStatusNutritiousDietGivenQ4;
	
	@Column(name="health_status_stress_Q4")
	private String healthStatusStressQ4;
	
	@Column(name="health_status_dental_Q4")
	private String healthStatusDentalQ4;
	
	@Column(name="health_status_ent_Q4")
	private String healthStatusENTQ4;
	
	@Column(name="health_status_eye_Q4")
	private String healthStatusEyeQ4;*/
	
	@Column(name="date_month_year_for_height_weight_chart")
	private Date dateMonthYearForHeightWeightChart;
	
	@Column(name="height_for_height_weight_chart")
	private  String heightForHeightWeightChart;
	
	@Column(name="admissible_weight_for_height_weight_chart")
	private Double admissibleWeightForHeightWeightChart;
	
	@Column(name="actual_weight_for_height_weight_chart")
	private  Double actualWeightForHeightWeightChart;
	
	@Column(name="friendship_prior_to_admission_into_childrens_home")
	private  String friendshipPriorToAdmissionIntoChildrensHome;
	
	@Column(name="friendship_prior_to_admission_into_childrens_home_others")
	private  String friendshipPriorToAdmissionIntoChildrensHomeOthers;
	
	@Column(name="majority_friends_are")
	private  String majorityFriendsAre;
	
	@Column(name="detail_of_membership_in_group")
	private String detailOfMembershipInGroup;
	
	@Column(name="detail_of_membership_in_group_others")
	private String detailOfMembershipInGroupOthers;
	
	@Column(name="position_of_child_in_group_league")
	private Integer positionOfChildInGroup_League;
	
	@Column(name="purpose_of_taking_membership_in_group")
	private String purposeOfTakingMembershipInGroup;
	
	@Column(name="purpose_of_taking_membership_in_group_others")
	private String purposeOfTakingMembershipInGroupOthers;
	
	@Column(name="attitude_of_group_league")
	private Integer attitudeOfGroup_League;
	
	@Column(name="location_meeting_point_of_groups")
	private Integer locationMeetingPointOfGroups;
	
	@Column(name="reaction_of_society_towards_child")
	private Integer reactionOfSocietyTowardsChild;
	
	@Column(name="reaction_of_police_towards_children")
	private Integer reactionOfPoliceTowardsChildren;
	
	@Column(name="response_of_general_public_towards_child")
	private String responseOfGeneralPublicTowardsChild;
	
	@Column(name="history_of_child_education")
	private String historyOfChildEducation;
	
	@Column(name="history_of_child_health")
	private String historyOfChildHealth;
	
	@Column(name="history_of_child_vocational_training")
	private String historyOfChildVocationalTraining;
	
	@Column(name="history_of_child_extra_curicular_activities")
	private String historyOfChildExtraCuricularActivites;
	
	@Column(name="history_of_child_others")
	private String historyOfChildOthers;
	
	@Column(name="suggestion_by_whom")
	private Integer suggestionByWhom;
	
	@Column(name="suggestion")
	private String suggestion;
	
	@Column(name="follow_up_by_whom")
	private Integer followUpWhom;
	
	@Column(name="quarterly_review_of_case")
	private String quarterlyReviewOfCase;
	
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

	public String getCategoryDetailsOthers() {
		return categoryDetailsOthers;
	}

	public void setCategoryDetailsOthers(String categoryDetailsOthers) {
		this.categoryDetailsOthers = categoryDetailsOthers;
	}

	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public String getReligionOthers() {
		return religionOthers;
	}

	public void setReligionOthers(String religionOthers) {
		this.religionOthers = religionOthers;
	}

	public Integer getCaste() {
		return caste;
	}

	public void setCaste(Integer caste) {
		this.caste = caste;
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

	public Integer getDescriptionOfHousing1() {
		return descriptionOfHousing1;
	}

	public void setDescriptionOfHousing1(Integer descriptionOfHousing1) {
		this.descriptionOfHousing1 = descriptionOfHousing1;
	}

	public Integer getDescriptionOfHousing2() {
		return descriptionOfHousing2;
	}

	public void setDescriptionOfHousing2(Integer descriptionOfHousing2) {
		this.descriptionOfHousing2 = descriptionOfHousing2;
	}

	public Integer getDescriptionOfHousing3() {
		return descriptionOfHousing3;
	}

	public void setDescriptionOfHousing3(Integer descriptionOfHousing3) {
		this.descriptionOfHousing3 = descriptionOfHousing3;
	}

	public Integer getChildBroughtBeforeCWCByWhom() {
		return childBroughtBeforeCWCByWhom;
	}

	public void setChildBroughtBeforeCWCByWhom(Integer childBroughtBeforeCWCByWhom) {
		this.childBroughtBeforeCWCByWhom = childBroughtBeforeCWCByWhom;
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

	public String getIllTreatmentdOF() {
		return illTreatmentdOF;
	}

	public void setIllTreatmentdOF(String illTreatmentdOF) {
		this.illTreatmentdOF = illTreatmentdOF;
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

	public Integer getRespiratoryDisorders() {
		return respiratoryDisorders;
	}

	public void setRespiratoryDisorders(Integer respiratoryDisorders) {
		this.respiratoryDisorders = respiratoryDisorders;
	}

	public Integer getHearingImpairment() {
		return hearingImpairment;
	}

	public void setHearingImpairment(Integer hearingImpairment) {
		this.hearingImpairment = hearingImpairment;
	}

	public Integer getEyeDiseases() {
		return eyeDiseases;
	}

	public void setEyeDiseases(Integer eyeDiseases) {
		this.eyeDiseases = eyeDiseases;
	}

	public Integer getDentalDisease() {
		return dentalDisease;
	}

	public void setDentalDisease(Integer dentalDisease) {
		this.dentalDisease = dentalDisease;
	}

	public Integer getCardiacDiseases() {
		return cardiacDiseases;
	}

	public void setCardiacDiseases(Integer cardiacDiseases) {
		this.cardiacDiseases = cardiacDiseases;
	}

	public Integer getSkinDisease() {
		return skinDisease;
	}

	public void setSkinDisease(Integer skinDisease) {
		this.skinDisease = skinDisease;
	}

	public Integer getSexuallyTransmittedDiseases() {
		return sexuallyTransmittedDiseases;
	}

	public void setSexuallyTransmittedDiseases(Integer sexuallyTransmittedDiseases) {
		this.sexuallyTransmittedDiseases = sexuallyTransmittedDiseases;
	}

	public Integer getNeurologicalDisorders() {
		return neurologicalDisorders;
	}

	public void setNeurologicalDisorders(Integer neurologicalDisorders) {
		this.neurologicalDisorders = neurologicalDisorders;
	}

	public Integer getMentalHandicap() {
		return mentalHandicap;
	}

	public void setMentalHandicap(Integer mentalHandicap) {
		this.mentalHandicap = mentalHandicap;
	}

	public Integer getPhysicalHandicap() {
		return physicalHandicap;
	}

	public void setPhysicalHandicap(Integer physicalHandicap) {
		this.physicalHandicap = physicalHandicap;
	}

	public Integer getUrinaryTractInfections() {
		return urinaryTractInfections;
	}

	public void setUrinaryTractInfections(Integer urinaryTractInfections) {
		this.urinaryTractInfections = urinaryTractInfections;
	}

	public Integer getOtherHealthIssues() {
		return otherHealthIssues;
	}

	public void setOtherHealthIssues(Integer otherHealthIssues) {
		this.otherHealthIssues = otherHealthIssues;
	}

	public String getOtherHealthIssuesName() {
		return otherHealthIssuesName;
	}

	public void setOtherHealthIssuesName(String otherHealthIssuesName) {
		this.otherHealthIssuesName = otherHealthIssuesName;
	}

	public Integer getChildWasStayingWithWhomPriorToAdmission() {
		return childWasStayingWithWhomPriorToAdmission;
	}

	public void setChildWasStayingWithWhomPriorToAdmission(
			Integer childWasStayingWithWhomPriorToAdmission) {
		this.childWasStayingWithWhomPriorToAdmission = childWasStayingWithWhomPriorToAdmission;
	}

	public String getChildWasStayingWithWhomPriorToAdmissionOther() {
		return childWasStayingWithWhomPriorToAdmissionOther;
	}

	public void setChildWasStayingWithWhomPriorToAdmissionOther(
			String childWasStayingWithWhomPriorToAdmissionOther) {
		this.childWasStayingWithWhomPriorToAdmissionOther = childWasStayingWithWhomPriorToAdmissionOther;
	}

	public Integer getVisitOfParentsPriorToInstitutionalization() {
		return visitOfParentsPriorToInstitutionalization;
	}

	public void setVisitOfParentsPriorToInstitutionalization(
			Integer visitOfParentsPriorToInstitutionalization) {
		this.visitOfParentsPriorToInstitutionalization = visitOfParentsPriorToInstitutionalization;
	}

	public Integer getVisitOfParentsAfterInstitutionalization() {
		return visitOfParentsAfterInstitutionalization;
	}

	public void setVisitOfParentsAfterInstitutionalization(
			Integer visitOfParentsAfterInstitutionalization) {
		this.visitOfParentsAfterInstitutionalization = visitOfParentsAfterInstitutionalization;
	}

	public Integer getVisitOfChildPriorToInstitutionalization() {
		return visitOfChildPriorToInstitutionalization;
	}

	public void setVisitOfChildPriorToInstitutionalization(
			Integer visitOfChildPriorToInstitutionalization) {
		this.visitOfChildPriorToInstitutionalization = visitOfChildPriorToInstitutionalization;
	}

	public Integer getVisitOfChildAfterInstitutionalization() {
		return visitOfChildAfterInstitutionalization;
	}

	public void setVisitOfChildAfterInstitutionalization(
			Integer visitOfChildAfterInstitutionalization) {
		this.visitOfChildAfterInstitutionalization = visitOfChildAfterInstitutionalization;
	}

	public Integer getCorrespondenceWithParentPriorToInstitutionalization() {
		return correspondenceWithParentPriorToInstitutionalization;
	}

	public void setCorrespondenceWithParentPriorToInstitutionalization(
			Integer correspondenceWithParentPriorToInstitutionalization) {
		this.correspondenceWithParentPriorToInstitutionalization = correspondenceWithParentPriorToInstitutionalization;
	}

	public Integer getCorrespondenceWithParentAfterInstitutionalization() {
		return correspondenceWithParentAfterInstitutionalization;
	}

	public void setCorrespondenceWithParentAfterInstitutionalization(
			Integer correspondenceWithParentAfterInstitutionalization) {
		this.correspondenceWithParentAfterInstitutionalization = correspondenceWithParentAfterInstitutionalization;
	}

	public String getDetailsOfDisability() {
		return detailsOfDisability;
	}

	public void setDetailsOfDisability(String detailsOfDisability) {
		this.detailsOfDisability = detailsOfDisability;
	}

	public Integer getFamilyType() {
		return familyType;
	}

	public void setFamilyType(Integer familyType) {
		this.familyType = familyType;
	}

	public Integer getRelationFatherMother() {
		return relationFatherMother;
	}

	public void setRelationFatherMother(Integer relationFatherMother) {
		this.relationFatherMother = relationFatherMother;
	}

	public Integer getRelationFatherChild() {
		return relationFatherChild;
	}

	public void setRelationFatherChild(Integer relationFatherChild) {
		this.relationFatherChild = relationFatherChild;
	}

	public Integer getRelationMotherChild() {
		return relationMotherChild;
	}

	public void setRelationMotherChild(Integer relationMotherChild) {
		this.relationMotherChild = relationMotherChild;
	}

	public Integer getRelationFatherSiblings() {
		return relationFatherSiblings;
	}

	public void setRelationFatherSiblings(Integer relationFatherSiblings) {
		this.relationFatherSiblings = relationFatherSiblings;
	}

	public Integer getRelationMotherSiblings() {
		return relationMotherSiblings;
	}

	public void setRelationMotherSiblings(Integer relationMotherSiblings) {
		this.relationMotherSiblings = relationMotherSiblings;
	}

	public Integer getRelationChildSiblings() {
		return relationChildSiblings;
	}

	public void setRelationChildSiblings(Integer relationChildSiblings) {
		this.relationChildSiblings = relationChildSiblings;
	}

	public Integer getRelationChildRelative() {
		return relationChildRelative;
	}

	public void setRelationChildRelative(Integer relationChildRelative) {
		this.relationChildRelative = relationChildRelative;
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

	public Integer getParentsMarraigeType() {
		return parentsMarraigeType;
	}

	public void setParentsMarraigeType(Integer parentsMarraigeType) {
		this.parentsMarraigeType = parentsMarraigeType;
	}

	public Integer getBrothersMarraigeType() {
		return brothersMarraigeType;
	}

	public void setBrothersMarraigeType(Integer brothersMarraigeType) {
		this.brothersMarraigeType = brothersMarraigeType;
	}

	public Integer getSistersMarraigeType() {
		return sistersMarraigeType;
	}

	public void setSistersMarraigeType(Integer sistersMarraigeType) {
		this.sistersMarraigeType = sistersMarraigeType;
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

	public Integer getDurationOfWorkingHours() {
		return durationOfWorkingHours;
	}

	public void setDurationOfWorkingHours(Integer durationOfWorkingHours) {
		this.durationOfWorkingHours = durationOfWorkingHours;
	}

	public Integer getDetailsOfPastEducation() {
		return detailsOfPastEducation;
	}

	public void setDetailsOfPastEducation(Integer detailsOfPastEducation) {
		this.detailsOfPastEducation = detailsOfPastEducation;
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

	public Integer getDetailsOfSchoolStudiedLast() {
		return detailsOfSchoolStudiedLast;
	}

	public void setDetailsOfSchoolStudiedLast(Integer detailsOfSchoolStudiedLast) {
		this.detailsOfSchoolStudiedLast = detailsOfSchoolStudiedLast;
	}

	public Integer getSchoolMediumInstruction() {
		return schoolMediumInstruction;
	}

	public void setSchoolMediumInstruction(Integer schoolMediumInstruction) {
		this.schoolMediumInstruction = schoolMediumInstruction;
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

	public Integer getEducationalAttainmentPromote_Detained() {
		return educationalAttainmentPromote_Detained;
	}

	public void setEducationalAttainmentPromote_Detained(
			Integer educationalAttainmentPromote_Detained) {
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

	public Integer getPositionOfChildInGroup_League() {
		return positionOfChildInGroup_League;
	}

	public void setPositionOfChildInGroup_League(
			Integer positionOfChildInGroup_League) {
		this.positionOfChildInGroup_League = positionOfChildInGroup_League;
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

	public Integer getAttitudeOfGroup_League() {
		return attitudeOfGroup_League;
	}

	public void setAttitudeOfGroup_League(Integer attitudeOfGroup_League) {
		this.attitudeOfGroup_League = attitudeOfGroup_League;
	}

	public Integer getLocationMeetingPointOfGroups() {
		return locationMeetingPointOfGroups;
	}

	public void setLocationMeetingPointOfGroups(Integer locationMeetingPointOfGroups) {
		this.locationMeetingPointOfGroups = locationMeetingPointOfGroups;
	}

	public Integer getReactionOfSocietyTowardsChild() {
		return reactionOfSocietyTowardsChild;
	}

	public void setReactionOfSocietyTowardsChild(
			Integer reactionOfSocietyTowardsChild) {
		this.reactionOfSocietyTowardsChild = reactionOfSocietyTowardsChild;
	}

	public Integer getReactionOfPoliceTowardsChildren() {
		return reactionOfPoliceTowardsChildren;
	}

	public void setReactionOfPoliceTowardsChildren(
			Integer reactionOfPoliceTowardsChildren) {
		this.reactionOfPoliceTowardsChildren = reactionOfPoliceTowardsChildren;
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

	public Integer getSuggestionByWhom() {
		return suggestionByWhom;
	}

	public void setSuggestionByWhom(Integer suggestionByWhom) {
		this.suggestionByWhom = suggestionByWhom;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Integer getFollowUpWhom() {
		return followUpWhom;
	}

	public void setFollowUpWhom(Integer followUpWhom) {
		this.followUpWhom = followUpWhom;
	}

	public String getQuarterlyReviewOfCase() {
		return quarterlyReviewOfCase;
	}

	public void setQuarterlyReviewOfCase(String quarterlyReviewOfCase) {
		this.quarterlyReviewOfCase = quarterlyReviewOfCase;
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
