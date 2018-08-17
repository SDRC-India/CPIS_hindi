package org.sdrc.cpis.models;

public class ReportModel {

	private String childId;
	private String childName;
	private String sirFatherName;
	private String sirMotherName;
	private String address;
	private Long count;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
