package org.sdrc.cpis.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CICL_Family_Details")
public class CICLFamilyDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;	
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="Name")
	private String nameOfFamilyMember;
	
	@Column(name="Age")
	private Integer age;
	
	@Column(name="Relationship")
	private String relationship;
	
	@Column(name="Sex")
	private Integer Sex;
	
	@Column(name="Education")
	private Integer education;
	
	@Column(name="Occupation")
	private String occupation;
	
	@Column(name="Income")
	private double income;
	
	@Column(name="Health_status")
	private String healthStatus;
	
	@Column(name="Mental_illness")
	private String mentalIllness;
	
	@Column(name="Addictions")
	private String addictions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}

	
	

	public String getNameOfFamilyMember() {
		return nameOfFamilyMember;
	}

	public void setNameOfFamilyMember(String nameOfFamilyMember) {
		this.nameOfFamilyMember = nameOfFamilyMember;
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

	

	public Integer getSex() {
		return Sex;
	}

	public void setSex(Integer sex) {
		Sex = sex;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getMentalIllness() {
		return mentalIllness;
	}

	public void setMentalIllness(String mentalIllness) {
		this.mentalIllness = mentalIllness;
	}

	public String getAddictions() {
		return addictions;
	}

	public void setAddictions(String addictions) {
		this.addictions = addictions;
	}
	
	
}
