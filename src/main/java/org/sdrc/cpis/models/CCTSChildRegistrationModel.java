package org.sdrc.cpis.models;

import java.sql.Date;
import java.sql.Time;

import org.sdrc.cpis.util.ValueObject;

public class CCTSChildRegistrationModel {
	
	private String caseNo;
	private Integer childWelfareCommittee;
	private String nameOfchildWelfareCommittee;
	private Date childProducedDate;
	private Time childProducedTime;
	private Integer childProducedPlace;
	private String nameOfChildProducedPlace;
	private String childProducedPlaceName;
	private String personProducingChildName;
	private Integer personProducingChildAge;
	private Integer personProducingChildSex;
	private String personProducingChildSexType;
	private String personProducingChildAddress;
	private String personProducingChildContactNo;
	private String personProducingChildOccupation;
	private String organizationCCISAAName;
	private Integer typeOfOrganization;
	private String organizationType;
	private String childName;
	private Integer childAge;
	private Integer childAgeValue;
	private Integer childSex;
	private String childSexType;
	private ValueObject childSexObject;
	private String childIdentityMarks;
	private String childLanguageUsed;
	private String parentName;
	private Integer parentAge;
	private String parentAddress;
	private String parentContactNo;
	private String parentOccupation;
	private String childFoundPlace;
	private String withWhomChildFoundName;
	private Integer withWhomChildFoundAge;
	private String withWhomChildFoundAddress;
	private String withWhomChildFoundContactNo;
	private String withWhomChildFoundOccupation;
	private String childCircumstancesWhenFound;
	private String allegationByChild;
	private String physicalConditionOfChild;
	private String childBelongings;
	private Date childCameToCCIDate;
	private Time childCameToCCITime;
	private String immediateEffortsToTraceFamily;
	private String medicalTreatment;
	private Boolean policeInformed;
	private String childImage;
	private String childProducedConvertedTime;
	private String childCameToCCIConvertedTime;
	private String childId;
	private String adhaarCardNo;
	
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private String formLang;
	private Integer programType;
	
	public String getChildImage() {
		return childImage;
	}
	public void setChildImage(String childImage) {
		this.childImage = childImage;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public Integer getChildWelfareCommittee() {
		return childWelfareCommittee;
	}
	public void setChildWelfareCommittee(Integer childWelfareCommittee) {
		this.childWelfareCommittee = childWelfareCommittee;
	}
	public Date getChildProducedDate() {
		return childProducedDate;
	}
	public void setChildProducedDate(Date childProducedDate) {
		this.childProducedDate = childProducedDate;
	}
	public Time getChildProducedTime() {
		return childProducedTime;
	}
	public void setChildProducedTime(Time childProducedTime) {
		this.childProducedTime = childProducedTime;
	}
	public Integer getChildProducedPlace() {
		return childProducedPlace;
	}
	public void setChildProducedPlace(Integer childProducedPlace) {
		this.childProducedPlace = childProducedPlace;
	}
	public String getPersonProducingChildName() {
		return personProducingChildName;
	}
	public void setPersonProducingChildName(String personProducingChildName) {
		this.personProducingChildName = personProducingChildName;
	}
	public Integer getPersonProducingChildAge() {
		return personProducingChildAge;
	}
	public void setPersonProducingChildAge(Integer personProducingChildAge) {
		this.personProducingChildAge = personProducingChildAge;
	}
	public Integer getPersonProducingChildSex() {
		return personProducingChildSex;
	}
	public void setPersonProducingChildSex(Integer personProducingChildSex) {
		this.personProducingChildSex = personProducingChildSex;
	}
	public String getPersonProducingChildAddress() {
		return personProducingChildAddress;
	}
	public void setPersonProducingChildAddress(String personProducingChildAddress) {
		this.personProducingChildAddress = personProducingChildAddress;
	}
	public String getPersonProducingChildContactNo() {
		return personProducingChildContactNo;
	}
	public void setPersonProducingChildContactNo(
			String personProducingChildContactNo) {
		this.personProducingChildContactNo = personProducingChildContactNo;
	}
	public String getPersonProducingChildOccupation() {
		return personProducingChildOccupation;
	}
	public void setPersonProducingChildOccupation(
			String personProducingChildOccupation) {
		this.personProducingChildOccupation = personProducingChildOccupation;
	}
	public String getOrganizationCCISAAName() {
		return organizationCCISAAName;
	}
	public void setOrganizationCCISAAName(String organizationCCISAAName) {
		this.organizationCCISAAName = organizationCCISAAName;
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
	public String getChildIdentityMarks() {
		return childIdentityMarks;
	}
	public void setChildIdentityMarks(String childIdentityMarks) {
		this.childIdentityMarks = childIdentityMarks;
	}
	public String getChildLanguageUsed() {
		return childLanguageUsed;
	}
	public void setChildLanguageUsed(String childLanguageUsed) {
		this.childLanguageUsed = childLanguageUsed;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getParentAge() {
		return parentAge;
	}
	public void setParentAge(Integer parentAge) {
		this.parentAge = parentAge;
	}
	public String getParentAddress() {
		return parentAddress;
	}
	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}
	public String getParentContactNo() {
		return parentContactNo;
	}
	public void setParentContactNo(String parentContactNo) {
		this.parentContactNo = parentContactNo;
	}
	public String getParentOccupation() {
		return parentOccupation;
	}
	public void setParentOccupation(String parentOccupation) {
		this.parentOccupation = parentOccupation;
	}
	public String getChildFoundPlace() {
		return childFoundPlace;
	}
	public void setChildFoundPlace(String childFoundPlace) {
		this.childFoundPlace = childFoundPlace;
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
	public String getWithWhomChildFoundContactNo() {
		return withWhomChildFoundContactNo;
	}
	public void setWithWhomChildFoundContactNo(String withWhomChildFoundContactNo) {
		this.withWhomChildFoundContactNo = withWhomChildFoundContactNo;
	}
	public String getWithWhomChildFoundOccupation() {
		return withWhomChildFoundOccupation;
	}
	public void setWithWhomChildFoundOccupation(String withWhomChildFoundOccupation) {
		this.withWhomChildFoundOccupation = withWhomChildFoundOccupation;
	}
	public String getChildCircumstancesWhenFound() {
		return childCircumstancesWhenFound;
	}
	public void setChildCircumstancesWhenFound(String childCircumstancesWhenFound) {
		this.childCircumstancesWhenFound = childCircumstancesWhenFound;
	}
	public String getAllegationByChild() {
		return allegationByChild;
	}
	public void setAllegationByChild(String allegationByChild) {
		this.allegationByChild = allegationByChild;
	}
	public String getPhysicalConditionOfChild() {
		return physicalConditionOfChild;
	}
	public void setPhysicalConditionOfChild(String physicalConditionOfChild) {
		this.physicalConditionOfChild = physicalConditionOfChild;
	}
	public String getChildBelongings() {
		return childBelongings;
	}
	public void setChildBelongings(String childBelongings) {
		this.childBelongings = childBelongings;
	}
	public Date getChildCameToCCIDate() {
		return childCameToCCIDate;
	}
	public void setChildCameToCCIDate(Date childCameToCCIDate) {
		this.childCameToCCIDate = childCameToCCIDate;
	}
	public Time getChildCameToCCITime() {
		return childCameToCCITime;
	}
	public void setChildCameToCCITime(Time childCameToCCITime) {
		this.childCameToCCITime = childCameToCCITime;
	}
	public String getImmediateEffortsToTraceFamily() {
		return immediateEffortsToTraceFamily;
	}
	public void setImmediateEffortsToTraceFamily(
			String immediateEffortsToTraceFamily) {
		this.immediateEffortsToTraceFamily = immediateEffortsToTraceFamily;
	}
	public String getMedicalTreatment() {
		return medicalTreatment;
	}
	public void setMedicalTreatment(String medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}
	public Boolean getPoliceInformed() {
		return policeInformed;
	}
	public void setPoliceInformed(Boolean policeInformed) {
		this.policeInformed = policeInformed;
	}
	public String getChildProducedPlaceName() {
		return childProducedPlaceName;
	}
	public void setChildProducedPlaceName(String childProducedPlaceName) {
		this.childProducedPlaceName = childProducedPlaceName;
	}
	public String getNameOfChildProducedPlace() {
		return nameOfChildProducedPlace;
	}
	public void setNameOfChildProducedPlace(String nameOfChildProducedPlace) {
		this.nameOfChildProducedPlace = nameOfChildProducedPlace;
	}
	public String getNameOfchildWelfareCommittee() {
		return nameOfchildWelfareCommittee;
	}
	public void setNameOfchildWelfareCommittee(String nameOfchildWelfareCommittee) {
		this.nameOfchildWelfareCommittee = nameOfchildWelfareCommittee;
	}
	public String getPersonProducingChildSexType() {
		return personProducingChildSexType;
	}
	public void setPersonProducingChildSexType(String personProducingChildSexType) {
		this.personProducingChildSexType = personProducingChildSexType;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	public String getChildSexType() {
		return childSexType;
	}
	public void setChildSexType(String childSexType) {
		this.childSexType = childSexType;
	}
	public ValueObject getChildSexObject() {
		return childSexObject;
	}
	public void setChildSexObject(ValueObject childSexObject) {
		this.childSexObject = childSexObject;
	}
	public Integer getChildAgeValue() {
		return childAgeValue;
	}
	public void setChildAgeValue(Integer childAgeValue) {
		this.childAgeValue = childAgeValue;
	}
	public String getChildProducedConvertedTime() {
		return childProducedConvertedTime;
	}
	public void setChildProducedConvertedTime(String childProducedConvertedTime) {
		this.childProducedConvertedTime = childProducedConvertedTime;
	}
	public String getChildCameToCCIConvertedTime() {
		return childCameToCCIConvertedTime;
	}
	public void setChildCameToCCIConvertedTime(String childCameToCCIConvertedTime) {
		this.childCameToCCIConvertedTime = childCameToCCIConvertedTime;
	}
	public String getChildId() {
		return childId;
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
	public void setChildId(String childId) {
		this.childId = childId;
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
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
}
