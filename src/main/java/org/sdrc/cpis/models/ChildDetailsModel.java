package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class ChildDetailsModel {

	private Integer id;
	private String childId;
	private String childName;
	private String childPhoto;
	private Date recordCreatedOn;
	private Integer age;
	private Integer currentAge;
	private Integer childDistrict;
	private Integer childSex;
	private Integer programType;
	private ValueObject district;
	private String caseNum;
	private ValueObject cwc;
	private Integer year;
	private Date dateOfFirstProduction;
	private String timeOfFirstProduction;
	private String personWhoProduceChild;
	private Date dateOfCaseRegistered;
	private String sirFatherName;
	private String sirMotherName;
	private String sirAddress;
	private String firNumber;
	private String ddNumber;
	private String gdNumber;
	private String policeStation;
	private String sections;
	private Integer finalOrderFilled;
	private Date dateOfRestoration;
	private ValueObject sexObject;
	private Integer sirFilled;
	private Integer icpFilled;
	private Integer rehabilitationCardFilled;
	private Integer caseHistoryFilled;
	private Integer fosterCareFilled;
	private ValueObject sirChildCast;
	private ValueObject sirChildReligion;
	private String sirOtherChildReligion;
	private Integer cciId;
	private String cciName;
	private String adhaarNo;
	
	
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildPhoto() {
		return childPhoto;
	}
	public void setChildPhoto(String childPhoto) {
		this.childPhoto = childPhoto;
	}
	public Date getRecordCreatedOn() {
		return recordCreatedOn;
	}
	public void setRecordCreatedOn(Date recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getChildDistrict() {
		return childDistrict;
	}
	public void setChildDistrict(Integer childDistrict) {
		this.childDistrict = childDistrict;
	}
	public Integer getChildSex() {
		return childSex;
	}
	public void setChildSex(Integer childSex) {
		this.childSex = childSex;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
	public ValueObject getDistrict() {
		return district;
	}
	public void setDistrict(ValueObject district) {
		this.district = district;
	}
	
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	public ValueObject getCwc() {
		return cwc;
	}
	public void setCwc(ValueObject cwc) {
		this.cwc = cwc;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Date getDateOfFirstProduction() {
		return dateOfFirstProduction;
	}
	public void setDateOfFirstProduction(Date dateOfFirstProduction) {
		this.dateOfFirstProduction = dateOfFirstProduction;
	}
	public String getTimeOfFirstProduction() {
		return timeOfFirstProduction;
	}
	public void setTimeOfFirstProduction(String timeOfFirstProduction) {
		this.timeOfFirstProduction = timeOfFirstProduction;
	}
	public String getPersonWhoProduceChild() {
		return personWhoProduceChild;
	}
	public void setPersonWhoProduceChild(String personWhoProduceChild) {
		this.personWhoProduceChild = personWhoProduceChild;
	}
	
	public Date getDateOfCaseRegistered() {
		return dateOfCaseRegistered;
	}
	public void setDateOfCaseRegistered(Date dateOfCaseRegistered) {
		this.dateOfCaseRegistered = dateOfCaseRegistered;
	}
	
	public String getSirFatherName() {
		return sirFatherName;
	}
	public void setSirFatherName(String sirFatherName) {
		this.sirFatherName = sirFatherName;
	}
	public String getSirMotherName() {
		return sirMotherName;
	}
	public void setSirMotherName(String sirMotherName) {
		this.sirMotherName = sirMotherName;
	}
	public String getSirAddress() {
		return sirAddress;
	}
	public void setSirAddress(String sirAddress) {
		this.sirAddress = sirAddress;
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
	public String getGdNumber() {
		return gdNumber;
	}
	public void setGdNumber(String gdNumber) {
		this.gdNumber = gdNumber;
	}
	
	public Integer getFinalOrderFilled() {
		return finalOrderFilled;
	}
	public void setFinalOrderFilled(Integer finalOrderFilled) {
		this.finalOrderFilled = finalOrderFilled;
	}
	public ValueObject getSexObject() {
		return sexObject;
	}
	public void setSexObject(ValueObject sexObject) {
		this.sexObject = sexObject;
	}
	@Override
	public String toString() {
		return "ChildDetailsModel [childId=" + childId + ", childName=" + childName + ", childPhoto="
				+ childPhoto + ", recordCreatedOn=" + recordCreatedOn + ", age=" + age
				+ ", childDistrict=" + childDistrict + ", childSex=" + childSex 
				+ ", programType=" + programType + ",policeStation=" + policeStation 
				+ ",sections=" + sections + ",firNumber=" + firNumber + "]";
	}
	public Integer getSirFilled() {
		return sirFilled;
	}
	public void setSirFilled(Integer sirFilled) {
		this.sirFilled = sirFilled;
	}
	public Integer getIcpFilled() {
		return icpFilled;
	}
	public void setIcpFilled(Integer icpFilled) {
		this.icpFilled = icpFilled;
	}
	public Integer getRehabilitationCardFilled() {
		return rehabilitationCardFilled;
	}
	public void setRehabilitationCardFilled(Integer rehabilitationCardFilled) {
		this.rehabilitationCardFilled = rehabilitationCardFilled;
	}
	public Integer getCaseHistoryFilled() {
		return caseHistoryFilled;
	}
	public void setCaseHistoryFilled(Integer caseHistoryFilled) {
		this.caseHistoryFilled = caseHistoryFilled;
	}
	public Integer getFosterCareFilled() {
		return fosterCareFilled;
	}
	public void setFosterCareFilled(Integer fosterCareFilled) {
		this.fosterCareFilled = fosterCareFilled;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ValueObject getSirChildCast() {
		return sirChildCast;
	}
	public void setSirChildCast(ValueObject sirChildCast) {
		this.sirChildCast = sirChildCast;
	}
	public ValueObject getSirChildReligion() {
		return sirChildReligion;
	}
	public void setSirChildReligion(ValueObject sirChildReligion) {
		this.sirChildReligion = sirChildReligion;
	}
	public String getSirOtherChildReligion() {
		return sirOtherChildReligion;
	}
	public void setSirOtherChildReligion(String sirOtherChildReligion) {
		this.sirOtherChildReligion = sirOtherChildReligion;
	}
	public Integer getCciId() {
		return cciId;
	}
	public void setCciId(Integer cciId) {
		this.cciId = cciId;
	}
	public String getCciName() {
		return cciName;
	}
	public void setCciName(String cciName) {
		this.cciName = cciName;
	}
	public Integer getCurrentAge() {
		return currentAge;
	}
	public void setCurrentAge(Integer currentAge) {
		this.currentAge = currentAge;
	}
	public Date getDateOfRestoration() {
		return dateOfRestoration;
	}
	public void setDateOfRestoration(Date dateOfRestoration) {
		this.dateOfRestoration = dateOfRestoration;
	}
	public String getAdhaarNo() {
		return adhaarNo;
	}
	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
	
	
}
