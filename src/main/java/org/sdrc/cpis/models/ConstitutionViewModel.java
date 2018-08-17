package org.sdrc.cpis.models;

import java.sql.Date;

public class ConstitutionViewModel {

	private Integer district;
	private Integer constitutionType;
	private String designation;
	private Date joiningDate;
	private String name;
	private String contactNo;
	private String emailId;
	private Date constitutionDate;
	
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public Integer getConstitutionType() {
		return constitutionType;
	}
	public void setConstitutionType(Integer constitutionType) {
		this.constitutionType = constitutionType;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getConstitutionDate() {
		return constitutionDate;
	}
	public void setConstitutionDate(Date constitutionDate) {
		this.constitutionDate = constitutionDate;
	}
}