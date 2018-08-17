package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;

public class CCIHumanResourceOSModel {

	private Integer id;	
	private ValueObject district;
	private ValueObject nameOfOpenShelter;
	private Integer projectCoordinator_cum_Counselor;
	private Integer social_Worker;
	private Integer careGiver_Cum_BridgeCourse_Educator;
	private Integer outReach_Worker;
	private Integer helper_For_Cleaning_Cooking;
	private ValueObject nameOfCCI;
	private Integer others;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ValueObject getDistrict() {
		return district;
	}
	public void setDistrict(ValueObject district) {
		this.district = district;
	}
	public ValueObject getNameOfOpenShelter() {
		return nameOfOpenShelter;
	}
	public void setNameOfOpenShelter(ValueObject nameOfOpenShelter) {
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
