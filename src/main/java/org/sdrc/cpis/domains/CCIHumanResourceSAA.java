package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cci_human_resource_SAA")
public class CCIHumanResourceSAA implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;	
	
	@Column(name="Name_of_District")
	private Integer nameOfDistrict;
	
	@Column(name="Name_of_SSA")
	private Integer nameOfSAA;
	
	@Column(name="Project_Coordinator")
	private Integer project_Coordinator;
	
	@Column(name="Programme_Manager")
	private Integer programme_Manager;
	
	@Column(name="Social_worker_Cum_Early_ChildHood_Educator")
	private Integer social_worker_Cum_Early_ChildHood_Educator;
	
	@Column(name="Nurse")
	private Integer nurse;
	
	@Column(name="PartTime_Doctor_Child_Specialist")
	private Integer partTime_Doctor_Child_Specialist;
	
	@Column(name="ayah")
	private Integer ayah;
	
	@Column(name="chowkidar")
	private Integer chowkidar;
	
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

	public Integer getNameOfSAA() {
		return nameOfSAA;
	}

	public void setNameOfSAA(Integer nameOfSAA) {
		this.nameOfSAA = nameOfSAA;
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

	public Integer getOthers() {
		return others;
	}

	public void setOthers(Integer others) {
		this.others = others;
	}

}
