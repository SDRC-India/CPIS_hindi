package org.sdrc.cpis.models;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.sdrc.cpis.util.ValueObject;

public class CICLSocialBackgroundReportModel {

	private Integer id;	
	private String childId;	
	private String firNumber;	
	private String ddNumber;
	private String gdNumber;	
	private String sections;
	private String policeStation;	
	private Date entryDate;
	private Time entryTime;
	private String nameOfIo;
	private String nameOfCwpo;	
	private String childName;	
	private String fatherName;	
	private String motherName;	
	private String guardianName;
	private Integer age;
	private ValueObject sexObject;
	private String address;	
	private ValueObject religionObject;	
	private ValueObject casteObject;	
	private String casteOtherType;
	private boolean differentlyAbled;	
	private String differentlyAbledType;
	private String otherDifferentlyAbledType;
	private List<CICLSocialBackgroundReportFamilyDetailsModel> familyDetails;
	private String homeLeavingReason;
	private boolean familyInvolvementInOffence;
	private String otherFamilyInvolvementInOffence;
	private String goodHabits;
	private String otherGoodHabits;
	private String badHabits;
	private String otherBadHabits;
	private String otherDrugBadHabits;
	private String employmentDetails;	
	private boolean usedByFamily;
	private boolean usedBySelf;	
	private boolean usedBySelfDress;	
	private boolean usedBySelfGambling;	
	private boolean usedBySelfAlcohol;	
	private boolean usedBySelfDrug;	
	private boolean usedBySelfSmoking;	
	private boolean usedBySelfSavings;
	private ValueObject educationDetails;
	private String schoolLeavingReason;
	private String otherSchoolLeavingReason;
	private ValueObject schoolDetails;	
	private String vocationalTraining;
	private String majorityOfFriends;
	private boolean abused;
	private String otherAbused;
	private String verbalAbuse;
	private String otherVerbalAbuse;
	private String physicalAbuse;
	private String otherPhysicalAbuse;
	private String sexualAbuse;
	private String otherSexualAbuse;
	private String otherAbuse;
	private String otherInOtherAbuse;
	private boolean victimOfOffence;
	private String otherVictimOfOffence;
	private boolean childDrugPeddling;
	private String otherChildDrugPeddling;
	private String allegedOffence;
	private String apprehendedCircumstances;
	private String articlesRecovered;
	private String allegedRole;
	private String suggestions;	
	private String roleOfChildInOffence;
	private String adhaarCardNo;
	
	private String badNameString;
	private String differentlyAbledTypeString;
	private String goodNameString;
	private String maorityNameString;
	private String reasonNameString;
	private String sexualAbusedNameString;
	private String physicalAbusedNameString;
	private String verbalAbusedNameString;
	private String entryDateStr;
	private String childImage;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private String entryTimeString;
	private String formLang;
	private Integer programType;
	
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	public String getChildImage() {
		return childImage;
	}
	public void setChildImage(String childImage) {
		this.childImage = childImage;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public ValueObject getEducationDetails() {
		return educationDetails;
	}
	public void setEducationDetails(ValueObject educationDetails) {
		this.educationDetails = educationDetails;
	}
	public void setSchoolDetails(ValueObject schoolDetails) {
		this.schoolDetails = schoolDetails;
	}
	public boolean isUsedByFamily() {
		return usedByFamily;
	}
	public void setUsedByFamily(boolean usedByFamily) {
		this.usedByFamily = usedByFamily;
	}
	public String getRoleOfChildInOffence() {
		return roleOfChildInOffence;
	}
	public void setRoleOfChildInOffence(String roleOfChildInOffence) {
		this.roleOfChildInOffence = roleOfChildInOffence;
	}
	public String getDifferentlyAbledType() {
		return differentlyAbledType;
	}
	public void setDifferentlyAbledType(String differentlyAbledType) {
		this.differentlyAbledType = differentlyAbledType;
	}
	public String getOtherAbused() {
		return otherAbused;
	}
	public void setOtherAbused(String otherAbused) {
		this.otherAbused = otherAbused;
	}
	public String getNameOfIo() {
		return nameOfIo;
	}
	public void setNameOfIo(String nameOfIo) {
		this.nameOfIo = nameOfIo;
	}
	public String getNameOfCwpo() {
		return nameOfCwpo;
	}
	public void setNameOfCwpo(String nameOfCwpo) {
		this.nameOfCwpo = nameOfCwpo;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean getAbused() {
		return abused;
	}
	public void setAbused(boolean abused) {
		this.abused = abused;
	}
	public String getHomeLeavingReason() {
		return homeLeavingReason;
	}
	public void setHomeLeavingReason(String homeLeavingReason) {
		this.homeLeavingReason = homeLeavingReason;
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
	public String getOtherDrugBadHabits() {
		return otherDrugBadHabits;
	}
	public void setOtherDrugBadHabits(String otherDrugBadHabits) {
		this.otherDrugBadHabits = otherDrugBadHabits;
	}
	public List<CICLSocialBackgroundReportFamilyDetailsModel> getFamilyDetails() {
		return familyDetails;
	}
	public void setFamilyDetails(List<CICLSocialBackgroundReportFamilyDetailsModel> familyDetails) {
		this.familyDetails = familyDetails;
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
	public String getDdNumber() {
		return ddNumber;
	}
	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}
	public String getMajorityOfFriends() {
		return majorityOfFriends;
	}
	public void setMajorityOfFriends(String majorityOfFriends) {
		this.majorityOfFriends = majorityOfFriends;
	}
	public String getApprehendedCircumstances() {
		return apprehendedCircumstances;
	}
	public void setApprehendedCircumstances(String apprehendedCircumstances) {
		this.apprehendedCircumstances = apprehendedCircumstances;
	}
	public String getArticlesRecovered() {
		return articlesRecovered;
	}
	public void setArticlesRecovered(String articlesRecovered) {
		this.articlesRecovered = articlesRecovered;
	}
	
	public String getOtherDifferentlyAbledType() {
		return otherDifferentlyAbledType;
	}
	public void setOtherDifferentlyAbledType(String otherDifferentlyAbledType) {
		this.otherDifferentlyAbledType = otherDifferentlyAbledType;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	public String getGdNumber() {
		return gdNumber;
	}
	public void setGdNumber(String gdNumber) {
		this.gdNumber = gdNumber;
	}
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
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
	
	public Time getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Time entryTime) {
		this.entryTime = entryTime;
	}
	public boolean getFamilyInvolvementInOffence() {
		return familyInvolvementInOffence;
	}
	public void setFamilyInvolvementInOffence(boolean familyInvolvementInOffence) {
		this.familyInvolvementInOffence = familyInvolvementInOffence;
	}
	public String getOtherFamilyInvolvementInOffence() {
		return otherFamilyInvolvementInOffence;
	}
	public void setOtherFamilyInvolvementInOffence(String otherFamilyInvolvementInOffence) {
		this.otherFamilyInvolvementInOffence = otherFamilyInvolvementInOffence;
	}
	
	public String getOtherVictimOfOffence() {
		return otherVictimOfOffence;
	}
	public void setOtherVictimOfOffence(String otherVictimOfOffence) {
		this.otherVictimOfOffence = otherVictimOfOffence;
	}
	public String getOtherChildDrugPeddling() {
		return otherChildDrugPeddling;
	}
	public void setOtherChildDrugPeddling(String otherChildDrugPeddling) {
		this.otherChildDrugPeddling = otherChildDrugPeddling;
	}
	public String getEmploymentDetails() {
		return employmentDetails;
	}
	public boolean getDifferentlyAbled() {
		return differentlyAbled;
	}
	public void setDifferentlyAbled(boolean differentlyAbled) {
		this.differentlyAbled = differentlyAbled;
	}
	public void setEmploymentDetails(String employmentDetails) {
		this.employmentDetails = employmentDetails;
	}
	
	public boolean getUsedBySelf() {
		return usedBySelf;
	}
	public void setUsedBySelf(boolean usedBySelf) {
		this.usedBySelf = usedBySelf;
	}
	public boolean getUsedBySelfDress() {
		return usedBySelfDress;
	}
	public void setUsedBySelfDress(boolean usedBySelfDress) {
		this.usedBySelfDress = usedBySelfDress;
	}
	public boolean getUsedBySelfGambling() {
		return usedBySelfGambling;
	}
	public void setUsedBySelfGambling(boolean usedBySelfGambling) {
		this.usedBySelfGambling = usedBySelfGambling;
	}
	public boolean getUsedBySelfAlcohol() {
		return usedBySelfAlcohol;
	}
	public void setUsedBySelfAlcohol(boolean usedBySelfAlcohol) {
		this.usedBySelfAlcohol = usedBySelfAlcohol;
	}
	public boolean getUsedBySelfDrug() {
		return usedBySelfDrug;
	}
	public void setUsedBySelfDrug(boolean usedBySelfDrug) {
		this.usedBySelfDrug = usedBySelfDrug;
	}
	public boolean getUsedBySelfSmoking() {
		return usedBySelfSmoking;
	}
	public void setUsedBySelfSmoking(boolean usedBySelfSmoking) {
		this.usedBySelfSmoking = usedBySelfSmoking;
	}
	public boolean getUsedBySelfSavings() {
		return usedBySelfSavings;
	}
	public void setUsedBySelfSavings(boolean usedBySelfSavings) {
		this.usedBySelfSavings = usedBySelfSavings;
	}
	public String getOtherSchoolLeavingReason() {
		return otherSchoolLeavingReason;
	}
	public void setOtherSchoolLeavingReason(String otherSchoolLeavingReason) {
		this.otherSchoolLeavingReason = otherSchoolLeavingReason;
	}
	public ValueObject getSchoolDetails() {
		return schoolDetails;
	}
	public String getSchoolLeavingReason() {
		return schoolLeavingReason;
	}
	public void setSchoolLeavingReason(String schoolLeavingReason) {
		this.schoolLeavingReason = schoolLeavingReason;
	}
	public String getVocationalTraining() {
		return vocationalTraining;
	}
	public void setVocationalTraining(String vocationalTraining) {
		this.vocationalTraining = vocationalTraining;
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
	public boolean getVictimOfOffence() {
		return victimOfOffence;
	}
	public void setVictimOfOffence(boolean victimOfOffence) {
		this.victimOfOffence = victimOfOffence;
	}
	/*public boolean getUsedByGangs() {
		return usedByGangs;
	}
	public void setUsedByGangs(boolean usedByGangs) {
		this.usedByGangs = usedByGangs;
	}*/
	
	public String getAllegedOffence() {
		return allegedOffence;
	}
	public boolean isChildDrugPeddling() {
		return childDrugPeddling;
	}
	public void setChildDrugPeddling(boolean childDrugPeddling) {
		this.childDrugPeddling = childDrugPeddling;
	}
	public String getAllegedRole() {
		return allegedRole;
	}
	public void setAllegedRole(String allegedRole) {
		this.allegedRole = allegedRole;
	}
	public void setAllegedOffence(String allegedOffence) {
		this.allegedOffence = allegedOffence;
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
	public String getPhysicalAbusedNameString() {
		return physicalAbusedNameString;
	}
	public void setPhysicalAbusedNameString(String physicalAbusedNameString) {
		this.physicalAbusedNameString = physicalAbusedNameString;
	}
	public String getVerbalAbusedNameString() {
		return verbalAbusedNameString;
	}
	public void setVerbalAbusedNameString(String verbalAbusedNameString) {
		this.verbalAbusedNameString = verbalAbusedNameString;
	}
	public String getEntryDateStr() {
		return entryDateStr;
	}
	public void setEntryDateStr(String entryDateStr) {
		this.entryDateStr = entryDateStr;
	}
	public ValueObject getSexObject() {
		return sexObject;
	}
	public void setSexObject(ValueObject sexObject) {
		this.sexObject = sexObject;
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
	public String getEntryTimeString() {
		return entryTimeString;
	}
	public void setEntryTimeString(String entryTimeString) {
		this.entryTimeString = entryTimeString;
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
