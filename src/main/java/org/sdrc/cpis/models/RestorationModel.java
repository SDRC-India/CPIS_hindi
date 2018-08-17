package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class RestorationModel {

	private Integer id;
	private String childId;
	private ValueObject childOrderPlacedIn;
	private String section;
	private Integer timePeriod;
	private Date placedDate;
	private String presentInstitution;
	private AreaDetailsModel institutionDistrict;
	private String authorityIncharge;
	private String dischargeReason;
	private Date orderDate;
	private String placeOfOrder;
	private String childName;
	private String sirFatherName;
	private String sirMotherName;
	private String sirAddress;
	private String caseNum;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;

	private Integer childOrderPlacedInId;
	private String childOrderPlacedInName;
	private String institutionDistrictName;
	private String cwcName;
	private Integer programType;

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
	public ValueObject getChildOrderPlacedIn() {
		return childOrderPlacedIn;
	}
	public void setChildOrderPlacedIn(ValueObject childOrderPlacedIn) {
		this.childOrderPlacedIn = childOrderPlacedIn;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Integer getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(Integer timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	public String getChildOrderPlacedInName() {
		return childOrderPlacedInName;
	}
	public void setChildOrderPlacedInName(String childOrderPlacedInName) {
		this.childOrderPlacedInName = childOrderPlacedInName;
	}
	public Date getPlacedDate() {
		return placedDate;
	}
	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}
	public String getPresentInstitution() {
		return presentInstitution;
	}
	public void setPresentInstitution(String presentInstitution) {
		this.presentInstitution = presentInstitution;
	}
	public AreaDetailsModel getInstitutionDistrict() {
		return institutionDistrict;
	}
	public void setInstitutionDistrict(AreaDetailsModel institutionDistrict) {
		this.institutionDistrict = institutionDistrict;
	}
	public String getAuthorityIncharge() {
		return authorityIncharge;
	}
	public void setAuthorityIncharge(String authorityIncharge) {
		this.authorityIncharge = authorityIncharge;
	}
	public String getDischargeReason() {
		return dischargeReason;
	}
	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPlaceOfOrder() {
		return placeOfOrder;
	}
	public void setPlaceOfOrder(String placeOfOrder) {
		this.placeOfOrder = placeOfOrder;
	}
	
	public Integer getChildOrderPlacedInId() {
		return childOrderPlacedInId;
	}
	public void setChildOrderPlacedInId(Integer childOrderPlacedInId) {
		this.childOrderPlacedInId = childOrderPlacedInId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
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
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
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
	public String getInstitutionDistrictName() {
		return institutionDistrictName;
	}
	public void setInstitutionDistrictName(String institutionDistrictName) {
		this.institutionDistrictName = institutionDistrictName;
	}
	public String getCwcName() {
		return cwcName;
	}
	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
}
