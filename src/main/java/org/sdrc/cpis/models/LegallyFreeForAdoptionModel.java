package org.sdrc.cpis.models;

import java.sql.Date;

public class LegallyFreeForAdoptionModel {

	private Integer id;
	private Date dateOfBirth;
	private Integer saaOrCciId;
	private String orderNo;
	private Date orderDate;
	private String inquiryReport;
	private String surrenderDeed;
	private String declaration;
	private Date legallyFreeDate;
	private String legallyFreePlace;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	
	private String childName;
	private String childId;
	private String cciAddress;
	private String cwcName;
	private String saaOrCciName;
	private Integer programType;
	
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	public Integer getSaaOrCciId() {
		return saaOrCciId;
	}
	public void setSaaOrCciId(Integer saaOrCciId) {
		this.saaOrCciId = saaOrCciId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getInquiryReport() {
		return inquiryReport;
	}
	public void setInquiryReport(String inquiryReport) {
		this.inquiryReport = inquiryReport;
	}
	public String getSurrenderDeed() {
		return surrenderDeed;
	}
	public void setSurrenderDeed(String surrenderDeed) {
		this.surrenderDeed = surrenderDeed;
	}
	public String getDeclaration() {
		return declaration;
	}
	
	public Date getLegallyFreeDate() {
		return legallyFreeDate;
	}
	public void setLegallyFreeDate(Date legallyFreeDate) {
		this.legallyFreeDate = legallyFreeDate;
	}
	public String getLegallyFreePlace() {
		return legallyFreePlace;
	}
	public void setLegallyFreePlace(String legallyFreePlace) {
		this.legallyFreePlace = legallyFreePlace;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
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
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getCciAddress() {
		return cciAddress;
	}
	public void setCciAddress(String cciAddress) {
		this.cciAddress = cciAddress;
	}
	public String getCwcName() {
		return cwcName;
	}
	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}
	public String getSaaOrCciName() {
		return saaOrCciName;
	}
	public void setSaaOrCciName(String saaOrCciName) {
		this.saaOrCciName = saaOrCciName;
	}
	
}
