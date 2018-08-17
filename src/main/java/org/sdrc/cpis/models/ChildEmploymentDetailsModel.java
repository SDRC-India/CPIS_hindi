package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;

public class ChildEmploymentDetailsModel {
	
	private Integer id;
	private String childId;
	private ValueObject typeOfEmployment;
	private String typesOfEmploymentOther;
	private String timing;
	private String duration;
	private Double wagesEarned;
	
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
	public ValueObject getTypeOfEmployment() {
		return typeOfEmployment;
	}
	public void setTypeOfEmployment(ValueObject typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}
	public String getTypesOfEmploymentOther() {
		return typesOfEmploymentOther;
	}
	public void setTypesOfEmploymentOther(String typesOfEmploymentOther) {
		this.typesOfEmploymentOther = typesOfEmploymentOther;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Double getWagesEarned() {
		return wagesEarned;
	}
	public void setWagesEarned(Double wagesEarned) {
		this.wagesEarned = wagesEarned;
	}
	
}