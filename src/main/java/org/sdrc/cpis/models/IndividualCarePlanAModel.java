package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class IndividualCarePlanAModel {

	private Integer id;
	private String childId;
	private ValueObject designation;
	private String nameOfOfficer;
	private Date dateOfIcp;
	private String policeStation;
	private String boardAddress;
	private String admissionNum;
	private Date admissionDate;
	private ValueObject stayOfChild;
	private Integer age;
	private Date dob;
	private String nationality;
	private ValueObject religion;
	private String religionOther;
	private ValueObject caste;
	private String language;
	private ValueObject education;
	private String accountDetail;
	private String earnings;
	private String awardDetails;
	private String childName;
	private Integer childAge;
	private Integer year;
	private String fatherName;
	private String motherName;
	private String firNo;
	private String uSections;
	
	private String cat1aoc;
	private String cat1pi;
	private String cat2aoc;
	private String cat2pi;
	private String cat3aoc;
	private String cat3pi;
	private String cat4aoc;
	private String cat4pi;
	private String cat5aoc;
	private String cat5pi;
	private String cat6aoc;
	private String cat6pi;
	private String cat7aoc;
	private String cat7pi;
	private String cat8aoc;
	private String cat8pi;
	private String cat9aoc;
	private String cat9pi;
	private String cat10aoc;
	private String cat10pi;
	
	private String caseNum;
	private String childSexName;
	
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private Integer programType;
	
	public String getChildSexName() {
		return childSexName;
	}
	public void setChildSexName(String childSexName) {
		this.childSexName = childSexName;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
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
	public ValueObject getDesignation() {
		return designation;
	}
	public void setDesignation(ValueObject designation) {
		this.designation = designation;
	}
	public String getNameOfOfficer() {
		return nameOfOfficer;
	}
	public void setNameOfOfficer(String nameOfOfficer) {
		this.nameOfOfficer = nameOfOfficer;
	}
	public Date getDateOfIcp() {
		return dateOfIcp;
	}
	public void setDateOfIcp(Date dateOfIcp) {
		this.dateOfIcp = dateOfIcp;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getBoardAddress() {
		return boardAddress;
	}
	public void setBoardAddress(String boardAddress) {
		this.boardAddress = boardAddress;
	}
	public String getAdmissionNum() {
		return admissionNum;
	}
	public void setAdmissionNum(String admissionNum) {
		this.admissionNum = admissionNum;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public ValueObject getStayOfChild() {
		return stayOfChild;
	}
	public void setStayOfChild(ValueObject stayOfChild) {
		this.stayOfChild = stayOfChild;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public ValueObject getReligion() {
		return religion;
	}
	public void setReligion(ValueObject religion) {
		this.religion = religion;
	}
	public String getReligionOther() {
		return religionOther;
	}
	public void setReligionOther(String religionOther) {
		this.religionOther = religionOther;
	}
	public ValueObject getCaste() {
		return caste;
	}
	public void setCaste(ValueObject caste) {
		this.caste = caste;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public ValueObject getEducation() {
		return education;
	}
	public void setEducation(ValueObject education) {
		this.education = education;
	}
	public String getAccountDetail() {
		return accountDetail;
	}
	public void setAccountDetail(String accountDetail) {
		this.accountDetail = accountDetail;
	}
	public String getEarnings() {
		return earnings;
	}
	public void setEarnings(String earnings) {
		this.earnings = earnings;
	}
	public String getAwardDetails() {
		return awardDetails;
	}
	public void setAwardDetails(String awardDetails) {
		this.awardDetails = awardDetails;
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
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
	public String getFirNo() {
		return firNo;
	}
	public void setFirNo(String firNo) {
		this.firNo = firNo;
	}
	public String getuSections() {
		return uSections;
	}
	public void setuSections(String uSections) {
		this.uSections = uSections;
	}
	public String getCat1aoc() {
		return cat1aoc;
	}
	public void setCat1aoc(String cat1aoc) {
		this.cat1aoc = cat1aoc;
	}
	public String getCat1pi() {
		return cat1pi;
	}
	public void setCat1pi(String cat1pi) {
		this.cat1pi = cat1pi;
	}
	public String getCat2aoc() {
		return cat2aoc;
	}
	public void setCat2aoc(String cat2aoc) {
		this.cat2aoc = cat2aoc;
	}
	public String getCat2pi() {
		return cat2pi;
	}
	public void setCat2pi(String cat2pi) {
		this.cat2pi = cat2pi;
	}
	public String getCat3aoc() {
		return cat3aoc;
	}
	public void setCat3aoc(String cat3aoc) {
		this.cat3aoc = cat3aoc;
	}
	public String getCat3pi() {
		return cat3pi;
	}
	public void setCat3pi(String cat3pi) {
		this.cat3pi = cat3pi;
	}
	public String getCat4aoc() {
		return cat4aoc;
	}
	public void setCat4aoc(String cat4aoc) {
		this.cat4aoc = cat4aoc;
	}
	public String getCat4pi() {
		return cat4pi;
	}
	public void setCat4pi(String cat4pi) {
		this.cat4pi = cat4pi;
	}
	public String getCat5aoc() {
		return cat5aoc;
	}
	public void setCat5aoc(String cat5aoc) {
		this.cat5aoc = cat5aoc;
	}
	public String getCat5pi() {
		return cat5pi;
	}
	public void setCat5pi(String cat5pi) {
		this.cat5pi = cat5pi;
	}
	public String getCat6aoc() {
		return cat6aoc;
	}
	public void setCat6aoc(String cat6aoc) {
		this.cat6aoc = cat6aoc;
	}
	public String getCat6pi() {
		return cat6pi;
	}
	public void setCat6pi(String cat6pi) {
		this.cat6pi = cat6pi;
	}
	public String getCat7aoc() {
		return cat7aoc;
	}
	public void setCat7aoc(String cat7aoc) {
		this.cat7aoc = cat7aoc;
	}
	public String getCat7pi() {
		return cat7pi;
	}
	public void setCat7pi(String cat7pi) {
		this.cat7pi = cat7pi;
	}
	public String getCat8aoc() {
		return cat8aoc;
	}
	public void setCat8aoc(String cat8aoc) {
		this.cat8aoc = cat8aoc;
	}
	public String getCat8pi() {
		return cat8pi;
	}
	public void setCat8pi(String cat8pi) {
		this.cat8pi = cat8pi;
	}
	public String getCat9aoc() {
		return cat9aoc;
	}
	public void setCat9aoc(String cat9aoc) {
		this.cat9aoc = cat9aoc;
	}
	public String getCat9pi() {
		return cat9pi;
	}
	public void setCat9pi(String cat9pi) {
		this.cat9pi = cat9pi;
	}
	public String getCat10aoc() {
		return cat10aoc;
	}
	public void setCat10aoc(String cat10aoc) {
		this.cat10aoc = cat10aoc;
	}
	public String getCat10pi() {
		return cat10pi;
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
	public void setCat10pi(String cat10pi) {
		this.cat10pi = cat10pi;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
		
}
