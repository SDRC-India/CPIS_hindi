package org.sdrc.cpis.domains;

import java.io.Serializable;
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
@Table(name="ccts_child_with_fit_person")
public class ChildWithFitPerson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4918479069199380652L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	Integer id;
	
//	@ManyToOne
//	@JoinColumn(name="child_id")
//	String childId;
	
//	@Column(name="case_no", nullable = false)
//	String caseNo;
	
	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}

	@Column(name="re")
	String re;
	
	@Column(name="name_of_the_fit_person")
	String nameOfTheFitPerson;
	
	@Column(name="address_of_the_fit_person")
	String addressOfTheFitPerson;
	
	@Column(name="reason_child_produced_in_cwc")
	String reasonChildProducedInCWC;
	
	@Column(name="period_of_sent_to_fit_person")
	Integer periodOfSentToFitPerson;
	
	@Column(name="jurisdiction_district")
	Integer jurisdictionDistrict;
	
	@Column(name="name_of_school")
	String nameOfSchool;
	
	@Column(name="address_of_school")
	String addressOfSchool;
	
	@Column(name="date_of_form_filled")
	Date dateOfFormFilled;
	
	@Column(name="date_when_cncp")
	Date dateWhenCNCP;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;

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

//	public String getCaseNo() {
//		return caseNo;
//	}
//
//	public void setCaseNo(String caseNo) {
//		this.caseNo = caseNo;
//	}

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getNameOfTheFitPerson() {
		return nameOfTheFitPerson;
	}

	public void setNameOfTheFitPerson(String nameOfTheFitPerson) {
		this.nameOfTheFitPerson = nameOfTheFitPerson;
	}

	public String getAddressOfTheFitPerson() {
		return addressOfTheFitPerson;
	}

	public void setAddressOfTheFitPerson(String addressOfTheFitPerson) {
		this.addressOfTheFitPerson = addressOfTheFitPerson;
	}

	public String getReasonChildProducedInCWC() {
		return reasonChildProducedInCWC;
	}

	public void setReasonChildProducedInCWC(String reasonChildProducedInCWC) {
		this.reasonChildProducedInCWC = reasonChildProducedInCWC;
	}

	public Integer getPeriodOfSentToFitPerson() {
		return periodOfSentToFitPerson;
	}

	public void setPeriodOfSentToFitPerson(Integer periodOfSentToFitPerson) {
		this.periodOfSentToFitPerson = periodOfSentToFitPerson;
	}

	public Integer getJurisdictionDistrict() {
		return jurisdictionDistrict;
	}

	public void setJurisdictionDistrict(Integer jurisdictionDistrict) {
		this.jurisdictionDistrict = jurisdictionDistrict;
	}

	public String getNameOfSchool() {
		return nameOfSchool;
	}

	public void setNameOfSchool(String nameOfSchool) {
		this.nameOfSchool = nameOfSchool;
	}

	public String getAddressOfSchool() {
		return addressOfSchool;
	}

	public void setAddressOfSchool(String addressOfSchool) {
		this.addressOfSchool = addressOfSchool;
	}

	public Date getDateOfFormFilled() {
		return dateOfFormFilled;
	}

	public void setDateOfFormFilled(Date dateOfFormFilled) {
		this.dateOfFormFilled = dateOfFormFilled;
	}

	public Date getDateWhenCNCP() {
		return dateWhenCNCP;
	}

	public void setDateWhenCNCP(Date dateWhenCNCP) {
		this.dateWhenCNCP = dateWhenCNCP;
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
