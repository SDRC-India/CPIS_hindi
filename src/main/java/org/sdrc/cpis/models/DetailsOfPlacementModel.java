package org.sdrc.cpis.models;

import java.sql.Date;

public class DetailsOfPlacementModel {
	
	private Integer id;
	private String childId;
	private String individualOrGroup;
	private Date date;
	private Double periodOfPlacement;
	private String createdBy;
	private Date createdDate;
	private String ip;
	
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
	public String getIndividualOrGroup() {
		return individualOrGroup;
	}
	public void setIndividualOrGroup(String individualOrGroup) {
		this.individualOrGroup = individualOrGroup;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPeriodOfPlacement() {
		return periodOfPlacement;
	}
	public void setPeriodOfPlacement(Double periodOfPlacement) {
		this.periodOfPlacement = periodOfPlacement;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
