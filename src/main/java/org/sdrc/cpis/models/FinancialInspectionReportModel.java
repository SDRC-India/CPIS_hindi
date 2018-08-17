package org.sdrc.cpis.models;

import java.sql.Date;

public class FinancialInspectionReportModel {

	private Integer id;
	private Integer quarterId;
	private Integer yearId;
	private String financialInspectionPath;
	private String type;
	private Integer cciId;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuarterId() {
		return quarterId;
	}
	public void setQuarterId(Integer quarterId) {
		this.quarterId = quarterId;
	}
	public Integer getYearId() {
		return yearId;
	}
	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}
	public String getFinancialInspectionPath() {
		return financialInspectionPath;
	}
	public void setFinancialInspectionPath(String financialInspectionPath) {
		this.financialInspectionPath = financialInspectionPath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCciId() {
		return cciId;
	}
	public void setCciId(Integer cciId) {
		this.cciId = cciId;
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
