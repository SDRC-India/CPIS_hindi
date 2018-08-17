package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;

public class CICLSocialBackgroundReportFamilyDetailsModel {
    private Integer id;
	private String name;
	private String relationship;
	private Integer age;
	private ValueObject sex;
	private ValueObject education;
	private String occupation;
	private Double income;
	private String healthStatus;
	private String historyOfMentalIllness;
	private String addictions;
	private String childId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public ValueObject getSex() {
		return sex;
	}
	public void setSex(ValueObject sex) {
		this.sex = sex;
	}
	public ValueObject getEducation() {
		return education;
	}
	public void setEducation(ValueObject education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getHistoryOfMentalIllness() {
		return historyOfMentalIllness;
	}
	public void setHistoryOfMentalIllness(String historyOfMentalIllness) {
		this.historyOfMentalIllness = historyOfMentalIllness;
	}
	public String getAddictions() {
		return addictions;
	}
	public void setAddictions(String addictions) {
		this.addictions = addictions;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	
	
}
