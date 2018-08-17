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

@Entity
@Table(name="child_registration_details")
public class ChildRegistrationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9078744723898305841L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	Integer crdId;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="case_no", nullable = false)
	String caseNo;
	
	@Column(name="produced_before_the_CWC_id")
	Integer ProducedBeforeTheCWCName;
	
	@Column(name="date_of_production")
	Date dateOfProduction;
	
	@Column(name="time_of_production")
	Time timeOfProduction;
	
	@Column(name="place_of_production")
	Integer placeOfProduction;
	
	@Column(name="person_who_produced_name")
	String personWhoProducedName;
	
	@Column(name="person_who_produced_age")
	Integer personWhoProducedAge;
	
	@Column(name="person_who_produced_sex")
	Integer personWhoProducedSex;
	
	@Column(name="person_who_produced_address")
	String personWhoProducedAddress;
	
	@Column(name="person_who_produced_contact_number")
	String personWhoProducedContactNumber;
	
	@Column(name="person_who_produced_occupation_or_designation")
	String personWhoProduced_occupation_or_designation;
	
	@Column(name="name_of_the_organization_CCI_SAA")
	String nameOfTheOrganization_CCI_SAA;
	
	@Column(name="type_of_organization")
	Integer typeOfOrganization;
	
	@Column(name="child_name")
	String childName;
	
	@Column(name="child_age")
	Integer childAge;
	
	@Column(name="child_sex")
	Integer childSex;
	
	@Column(name="adhaar_card_no")
	String adhaarCardNo;
	
	@Column(name="child_identity_mark")
	String childIdentityMark;
	
	@Column(name="child_language")
	String childLanguage;
	
	@Column(name="parent_or_guardian_name")
	String parentOrGuardianName;
	
	@Column(name="parent_or_guardian_age")
	Integer parentOrGuardianAge;
	
	@Column(name="parent_or_guardian_address")
	String parentOrGuardianAddress;
	
	@Column(name="parent_or_guardian_contact_number")
	String parentOrGuardianContact;
	
	@Column(name="parent_or_guardian_occupation")
	String parentOrGuardianOccupation;
	
	@Column(name="place_where_child_found")
	String placeWhereChildFound;
	
	@Column(name="with_whom_child_found_name")
	String withWhomChildFoundName;
	
	@Column(name="with_whom_child_found_age")
	Integer withWhomChildFoundAge;
	
	@Column(name="with_whom_child_found_address")
	String withWhomChildFoundAddress;
	
	@Column(name="with_whom_child_found_contact_number")
	String withWhomChildFoundContact;
	
	@Column(name="with_whom_child_found_occupation")
	String withWhomChildFoundOccupation;
	
	@Column(name="circumstances_under_which_child_found")
	String  circumstancesUnderWhichChildFound;
	
	@Column(name="allegation_by_child")
	String AllegationByChild;
	
	@Column(name="physical_condition_of_child")
	String PhysicalConditionOfCchild;
	
	@Column(name="belongings_of_child_at_the_time_of_production")
	String BelongingsOfTheChildAtTheTimeOfProduction;
	
	@Column(name="date_child_came_to_CCI_SAA")
	Date dateChildCameToCCI_SAA;
	
	@Column(name="time_child_came_to_CCI_SAA")
	Time timeChildCameToCCI_SAA;
	
	@Column(name="immediate_efforts_to_trace_family")
	String ImmediateEffortsToTraceFamily;
	
	@Column(name="medical_treatment_provided_to_child")
	String MedicalTreatmentProvidedToChild;
	
	@Column(name="police_informed")
	Boolean policeInformed;

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
	
	@Column(name="form_lang")
	private String formLang;
	
	public Integer getCrdId() {
		return crdId;
	}

	public void setCrdId(Integer crdId) {
		this.crdId = crdId;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public Integer getProducedBeforeTheCWCName() {
		return ProducedBeforeTheCWCName;
	}

	public void setProducedBeforeTheCWCName(Integer producedBeforeTheCWCName) {
		ProducedBeforeTheCWCName = producedBeforeTheCWCName;
	}

	public Date getDateOfProduction() {
		return dateOfProduction;
	}

	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}

	public Time getTimeOfProduction() {
		return timeOfProduction;
	}

	public void setTimeOfProduction(Time timeOfProduction) {
		this.timeOfProduction = timeOfProduction;
	}

	public Integer getPlaceOfProduction() {
		return placeOfProduction;
	}

	public void setPlaceOfProduction(Integer placeOfProduction) {
		this.placeOfProduction = placeOfProduction;
	}

	public String getPersonWhoProducedName() {
		return personWhoProducedName;
	}

	public void setPersonWhoProducedName(String personWhoProducedName) {
		this.personWhoProducedName = personWhoProducedName;
	}

	public Integer getPersonWhoProducedAge() {
		return personWhoProducedAge;
	}

	public void setPersonWhoProducedAge(Integer personWhoProducedAge) {
		this.personWhoProducedAge = personWhoProducedAge;
	}

	public Integer getPersonWhoProducedSex() {
		return personWhoProducedSex;
	}

	public void setPersonWhoProducedSex(Integer personWhoProducedSex) {
		this.personWhoProducedSex = personWhoProducedSex;
	}

	public String getPersonWhoProducedAddress() {
		return personWhoProducedAddress;
	}

	public void setPersonWhoProducedAddress(String personWhoProducedAddress) {
		this.personWhoProducedAddress = personWhoProducedAddress;
	}

	public String getPersonWhoProducedContactNumber() {
		return personWhoProducedContactNumber;
	}

	public void setPersonWhoProducedContactNumber(
			String personWhoProducedContactNumber) {
		this.personWhoProducedContactNumber = personWhoProducedContactNumber;
	}

	public String getPersonWhoProduced_occupation_or_designation() {
		return personWhoProduced_occupation_or_designation;
	}

	public void setPersonWhoProduced_occupation_or_designation(
			String personWhoProduced_occupation_or_designation) {
		this.personWhoProduced_occupation_or_designation = personWhoProduced_occupation_or_designation;
	}

	public String getNameOfTheOrganization_CCI_SAA() {
		return nameOfTheOrganization_CCI_SAA;
	}

	public void setNameOfTheOrganization_CCI_SAA(
			String nameOfTheOrganization_CCI_SAA) {
		this.nameOfTheOrganization_CCI_SAA = nameOfTheOrganization_CCI_SAA;
	}
	
	public Integer getTypeOfOrganization() {
		return typeOfOrganization;
	}

	public void setTypeOfOrganization(Integer typeOfOrganization) {
		this.typeOfOrganization = typeOfOrganization;
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

	public String getChildIdentityMark() {
		return childIdentityMark;
	}

	public void setChildIdentityMark(String childIdentityMark) {
		this.childIdentityMark = childIdentityMark;
	}

	public String getChildLanguage() {
		return childLanguage;
	}

	public void setChildLanguage(String childLanguage) {
		this.childLanguage = childLanguage;
	}

	public String getParentOrGuardianName() {
		return parentOrGuardianName;
	}

	public void setParentOrGuardianName(String parentOrGuardianName) {
		this.parentOrGuardianName = parentOrGuardianName;
	}

	public Integer getParentOrGuardianAge() {
		return parentOrGuardianAge;
	}

	public void setParentOrGuardianAge(Integer parentOrGuardianAge) {
		this.parentOrGuardianAge = parentOrGuardianAge;
	}

	public String getParentOrGuardianAddress() {
		return parentOrGuardianAddress;
	}

	public void setParentOrGuardianAddress(String parentOrGuardianAddress) {
		this.parentOrGuardianAddress = parentOrGuardianAddress;
	}

	public String getParentOrGuardianContact() {
		return parentOrGuardianContact;
	}

	public void setParentOrGuardianContact(String parentOrGuardianContact) {
		this.parentOrGuardianContact = parentOrGuardianContact;
	}

	public String getParentOrGuardianOccupation() {
		return parentOrGuardianOccupation;
	}

	public void setParentOrGuardianOccupation(String parentOrGuardianOccupation) {
		this.parentOrGuardianOccupation = parentOrGuardianOccupation;
	}

	public String getPlaceWhereChildFound() {
		return placeWhereChildFound;
	}

	public void setPlaceWhereChildFound(String placeWhereChildFound) {
		this.placeWhereChildFound = placeWhereChildFound;
	}

	public String getWithWhomChildFoundName() {
		return withWhomChildFoundName;
	}

	public void setWithWhomChildFoundName(String withWhomChildFoundName) {
		this.withWhomChildFoundName = withWhomChildFoundName;
	}

	public Integer getWithWhomChildFoundAge() {
		return withWhomChildFoundAge;
	}

	public void setWithWhomChildFoundAge(Integer withWhomChildFoundAge) {
		this.withWhomChildFoundAge = withWhomChildFoundAge;
	}

	public String getWithWhomChildFoundAddress() {
		return withWhomChildFoundAddress;
	}

	public void setWithWhomChildFoundAddress(String withWhomChildFoundAddress) {
		this.withWhomChildFoundAddress = withWhomChildFoundAddress;
	}

	public String getWithWhomChildFoundContact() {
		return withWhomChildFoundContact;
	}

	public void setWithWhomChildFoundContact(String withWhomChildFoundContact) {
		this.withWhomChildFoundContact = withWhomChildFoundContact;
	}

	public String getWithWhomChildFoundOccupation() {
		return withWhomChildFoundOccupation;
	}

	public void setWithWhomChildFoundOccupation(String withWhomChildFoundOccupation) {
		this.withWhomChildFoundOccupation = withWhomChildFoundOccupation;
	}

	public String getCircumstancesUnderWhichChildFound() {
		return circumstancesUnderWhichChildFound;
	}

	public void setCircumstancesUnderWhichChildFound(
			String circumstancesUnderWhichChildFound) {
		this.circumstancesUnderWhichChildFound = circumstancesUnderWhichChildFound;
	}

	public String getAllegationByChild() {
		return AllegationByChild;
	}

	public void setAllegationByChild(String allegationByChild) {
		AllegationByChild = allegationByChild;
	}

	public String getPhysicalConditionOfCchild() {
		return PhysicalConditionOfCchild;
	}

	public void setPhysicalConditionOfCchild(String physicalConditionOfCchild) {
		PhysicalConditionOfCchild = physicalConditionOfCchild;
	}

	public String getBelongingsOfTheChildAtTheTimeOfProduction() {
		return BelongingsOfTheChildAtTheTimeOfProduction;
	}

	public void setBelongingsOfTheChildAtTheTimeOfProduction(
			String belongingsOfTheChildAtTheTimeOfProduction) {
		BelongingsOfTheChildAtTheTimeOfProduction = belongingsOfTheChildAtTheTimeOfProduction;
	}

	public Date getDateChildCameToCCI_SAA() {
		return dateChildCameToCCI_SAA;
	}

	public void setDateChildCameToCCI_SAA(Date dateChildCameToCCI_SAA) {
		this.dateChildCameToCCI_SAA = dateChildCameToCCI_SAA;
	}

	public Time getTimeChildCameToCCI_SAA() {
		return timeChildCameToCCI_SAA;
	}

	public void setTimeChildCameToCCI_SAA(Time timeChildCameToCCI_SAA) {
		this.timeChildCameToCCI_SAA = timeChildCameToCCI_SAA;
	}

	public String getImmediateEffortsToTraceFamily() {
		return ImmediateEffortsToTraceFamily;
	}

	public void setImmediateEffortsToTraceFamily(
			String immediateEffortsToTraceFamily) {
		ImmediateEffortsToTraceFamily = immediateEffortsToTraceFamily;
	}

	public String getMedicalTreatmentProvidedToChild() {
		return MedicalTreatmentProvidedToChild;
	}

	public void setMedicalTreatmentProvidedToChild(
			String medicalTreatmentProvidedToChild) {
		MedicalTreatmentProvidedToChild = medicalTreatmentProvidedToChild;
	}

	public Boolean getPoliceInformed() {
		return policeInformed;
	}

	public void setPoliceInformed(Boolean policeInformed) {
		this.policeInformed = policeInformed;
	}

	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
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

	public String getAdhaarCardNo() {
		return adhaarCardNo;
	}

	public void setAdhaarCardNo(String adhaarCardNo) {
		this.adhaarCardNo = adhaarCardNo;
	}

	public String getFormLang() {
		return formLang;
	}

	public void setFormLang(String formLang) {
		this.formLang = formLang;
	}
	
}
