package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cci_human_resource_OS")

public class CCIHumanResourceOS implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;	
	
	@Column(name="Name_of_District")
	private Integer nameOfDistrict;
	
	@Column(name="Name_of_OpenShelter")
	private Integer nameOfOpenShelter;
	
	@Column(name="ProjectCoordinator_cum_Counselor")
	private Integer projectCoordinator_cum_Counselor;
	
	@Column(name="Social_Worker")
	private Integer social_Worker;
	
	@Column(name="CareGiver_Cum_BridgeCourse_Educator")
	private Integer careGiver_Cum_BridgeCourse_Educator;
	
	@Column(name="OutReach_Worker")
	private Integer outReach_Worker;
	
	@Column(name="Helper_For_Cleaning_Cooking")
	private Integer helper_For_Cleaning_Cooking;
	
	@Column(name="Name_of_CCI")
	private Integer nameOfCCI;
	
	@Column(name="others")
	private Integer others;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNameOfDistrict() {
		return nameOfDistrict;
	}

	public void setNameOfDistrict(Integer nameOfDistrict) {
		this.nameOfDistrict = nameOfDistrict;
	}

	public Integer getNameOfOpenShelter() {
		return nameOfOpenShelter;
	}

	public void setNameOfOpenShelter(Integer nameOfOpenShelter) {
		this.nameOfOpenShelter = nameOfOpenShelter;
	}

	public Integer getProjectCoordinator_cum_Counselor() {
		return projectCoordinator_cum_Counselor;
	}

	public void setProjectCoordinator_cum_Counselor(Integer projectCoordinator_cum_Counselor) {
		this.projectCoordinator_cum_Counselor = projectCoordinator_cum_Counselor;
	}

	public Integer getSocial_Worker() {
		return social_Worker;
	}

	public void setSocial_Worker(Integer social_Worker) {
		this.social_Worker = social_Worker;
	}

	public Integer getCareGiver_Cum_BridgeCourse_Educator() {
		return careGiver_Cum_BridgeCourse_Educator;
	}

	public void setCareGiver_Cum_BridgeCourse_Educator(Integer careGiver_Cum_BridgeCourse_Educator) {
		this.careGiver_Cum_BridgeCourse_Educator = careGiver_Cum_BridgeCourse_Educator;
	}

	public Integer getOutReach_Worker() {
		return outReach_Worker;
	}

	public void setOutReach_Worker(Integer outReach_Worker) {
		this.outReach_Worker = outReach_Worker;
	}

	public Integer getHelper_For_Cleaning_Cooking() {
		return helper_For_Cleaning_Cooking;
	}

	public void setHelper_For_Cleaning_Cooking(Integer helper_For_Cleaning_Cooking) {
		this.helper_For_Cleaning_Cooking = helper_For_Cleaning_Cooking;
	}

	public Integer getNameOfCCI() {
		return nameOfCCI;
	}

	public void setNameOfCCI(Integer nameOfCCI) {
		this.nameOfCCI = nameOfCCI;
	}

	public Integer getOthers() {
		return others;
	}

	public void setOthers(Integer others) {
		this.others = others;
	}

}
