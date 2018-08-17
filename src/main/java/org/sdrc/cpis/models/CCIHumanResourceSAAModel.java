package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;

public class CCIHumanResourceSAAModel {
	
	private Integer id;	
	private ValueObject nameOfSAA;
	private ValueObject district;
	private Integer project_Coordinator;
	private Integer programme_Manager;
	private Integer social_worker_Cum_Early_ChildHood_Educator;
	private Integer nurse;
	private Integer partTime_Doctor_Child_Specialist;
	private Integer ayah;
	private Integer chowkidar;
	private ValueObject nameOfCCI;
	private Integer others;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public ValueObject getNameOfSAA() {
		return nameOfSAA;
	}
	public void setNameOfSAA(ValueObject nameOfSAA) {
		this.nameOfSAA = nameOfSAA;
	}
	public ValueObject getDistrict() {
		return district;
	}
	public void setDistrict(ValueObject district) {
		this.district = district;
	}
	public Integer getProject_Coordinator() {
		return project_Coordinator;
	}
	public void setProject_Coordinator(Integer project_Coordinator) {
		this.project_Coordinator = project_Coordinator;
	}
	public Integer getProgramme_Manager() {
		return programme_Manager;
	}
	public void setProgramme_Manager(Integer programme_Manager) {
		this.programme_Manager = programme_Manager;
	}
	public Integer getSocial_worker_Cum_Early_ChildHood_Educator() {
		return social_worker_Cum_Early_ChildHood_Educator;
	}
	public void setSocial_worker_Cum_Early_ChildHood_Educator(Integer social_worker_Cum_Early_ChildHood_Educator) {
		this.social_worker_Cum_Early_ChildHood_Educator = social_worker_Cum_Early_ChildHood_Educator;
	}
	public Integer getNurse() {
		return nurse;
	}
	public void setNurse(Integer nurse) {
		this.nurse = nurse;
	}
	public Integer getPartTime_Doctor_Child_Specialist() {
		return partTime_Doctor_Child_Specialist;
	}
	public void setPartTime_Doctor_Child_Specialist(Integer partTime_Doctor_Child_Specialist) {
		this.partTime_Doctor_Child_Specialist = partTime_Doctor_Child_Specialist;
	}
	public Integer getAyah() {
		return ayah;
	}
	public void setAyah(Integer ayah) {
		this.ayah = ayah;
	}
	public Integer getChowkidar() {
		return chowkidar;
	}
	public void setChowkidar(Integer chowkidar) {
		this.chowkidar = chowkidar;
	}
	public ValueObject getNameOfCCI() {
		return nameOfCCI;
	}
	public void setNameOfCCI(ValueObject nameOfCCI) {
		this.nameOfCCI = nameOfCCI;
	}
	public Integer getOthers() {
		return others;
	}
	public void setOthers(Integer others) {
		this.others = others;
	}
	
}
