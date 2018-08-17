package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @author Pratyush
 */
@Entity
@Table(name="social_investigation_report_family_details")
public class SocialInvestigationReportFamilyDetails implements Serializable {

	private static final long serialVersionUID = -2792375526357842580L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="ref_id")
	private SocialinvestigationReport socialinvestigationReport;
	
	@Column(name="name")
	private String name;
	
	@Column(name="relationship")
	private String relationship;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="sex")
	private Integer sex;
	
	@Column(name="education")
	private Integer education;
	
	@Column(name="occupation")
	private String occupation;
	
	@Column(name="income")
	private Integer income;
	
	@Column(name="health_status")
	private String healthStatus;
	
	@Column(name="History_of_mental_illness")
	private String historyOfMentalIllness;
	
	@Column(name="addictions")
	private String addictions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public SocialinvestigationReport getSocialinvestigationReport() {
//		return socialinvestigationReport;
//	}
//
//	public void setSocialinvestigationReport(
//			SocialinvestigationReport socialinvestigationReport) {
//		this.socialinvestigationReport = socialinvestigationReport;
//	}

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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
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

	public SocialinvestigationReport getSocialinvestigationReport() {
		return socialinvestigationReport;
	}

	public void setSocialinvestigationReport(
			SocialinvestigationReport socialinvestigationReport) {
		this.socialinvestigationReport = socialinvestigationReport;
	}

	public String getName() {
		return name;
	}
	
}
