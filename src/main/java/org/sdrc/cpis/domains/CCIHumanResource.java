package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cci_human_resource")
public class CCIHumanResource implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;	
	
	@Column(name="Name_of_District")
	private Integer nameOfDistrict;
	
	@Column(name="Name_of_CCI")
	private Integer nameOfCCI;
	
	@Column(name="OfficerIncharge_Superintendent")
	private Integer officerIncharge_Superintendent;
	
	@Column(name="Counselor")
	private Integer counselor;
	
	@Column(name="ProbationOfficer_CaseWorker_ChildWelfareOfficer")
	private Integer po_so_cwo;
	
	@Column(name="HouseMother_Father")
	private Integer houseMother_Father;
	
	@Column(name="ParaMedicalStaff")
	private Integer paraMedicalStaff;
	
	@Column(name="StoreKeeper_Accountant")
	private Integer storeKeeperCumAccountan;
	
	@Column(name="Cook")
	private Integer cook;
	
	@Column(name="AsstCook")
	private Integer asstCook;
	
	@Column(name="HouseKeeper")
	private Integer houseKeeper;
	
	@Column(name="Educator_Volunter_PartTime")
	private Integer educator;
	
	@Column(name="MBBSDoctor_Volunter_PartTime")
	private Integer mbbsDoctor;
	
	@Column(name="Art_Craft_MusicTeacher_Volunter_PartTime")
	private Integer artCraftMusicTeacher;
	
	@Column(name="PT_Instructor_YogaTeacher_Volunter_PartTime")
	private Integer ptInstructorYogaTeacher;
	
	@Column(name="Special_Educator_Therapist")
	private Integer special_Educator_Therapist;
	
	@Column(name="Female_Nurse")
	private Integer female_Nurse;
	
	@Column(name="CareTaker_VocationalInstructor")
	private Integer careTaker_VocationalInstructor;
	
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

	public Integer getNameOfCCI() {
		return nameOfCCI;
	}

	public void setNameOfCCI(Integer nameOfCCI) {
		this.nameOfCCI = nameOfCCI;
	}

	public Integer getCounselor() {
		return counselor;
	}

	public void setCounselor(Integer counselor) {
		this.counselor = counselor;
	}

	public Integer getPo_so_cwo() {
		return po_so_cwo;
	}

	public void setPo_so_cwo(Integer po_so_cwo) {
		this.po_so_cwo = po_so_cwo;
	}

	public Integer getHouseMother_Father() {
		return houseMother_Father;
	}

	public void setHouseMother_Father(Integer houseMother_Father) {
		this.houseMother_Father = houseMother_Father;
	}

	public Integer getStoreKeeperCumAccountan() {
		return storeKeeperCumAccountan;
	}

	public void setStoreKeeperCumAccountan(Integer storeKeeperCumAccountan) {
		this.storeKeeperCumAccountan = storeKeeperCumAccountan;
	}

	public Integer getCook() {
		return cook;
	}

	public void setCook(Integer cook) {
		this.cook = cook;
	}

	public Integer getAsstCook() {
		return asstCook;
	}

	public void setAsstCook(Integer asstCook) {
		this.asstCook = asstCook;
	}

	public Integer getHouseKeeper() {
		return houseKeeper;
	}

	public void setHouseKeeper(Integer houseKeeper) {
		this.houseKeeper = houseKeeper;
	}

	public Integer getEducator() {
		return educator;
	}

	public void setEducator(Integer educator) {
		this.educator = educator;
	}

	public Integer getMbbsDoctor() {
		return mbbsDoctor;
	}

	public void setMbbsDoctor(Integer mbbsDoctor) {
		this.mbbsDoctor = mbbsDoctor;
	}

	public Integer getArtCraftMusicTeacher() {
		return artCraftMusicTeacher;
	}

	public void setArtCraftMusicTeacher(Integer artCraftMusicTeacher) {
		this.artCraftMusicTeacher = artCraftMusicTeacher;
	}

	public Integer getPtInstructorYogaTeacher() {
		return ptInstructorYogaTeacher;
	}

	public void setPtInstructorYogaTeacher(Integer ptInstructorYogaTeacher) {
		this.ptInstructorYogaTeacher = ptInstructorYogaTeacher;
	}

	public Integer getSpecial_Educator_Therapist() {
		return special_Educator_Therapist;
	}

	public void setSpecial_Educator_Therapist(Integer special_Educator_Therapist) {
		this.special_Educator_Therapist = special_Educator_Therapist;
	}

	public Integer getFemale_Nurse() {
		return female_Nurse;
	}

	public void setFemale_Nurse(Integer female_Nurse) {
		this.female_Nurse = female_Nurse;
	}

	public Integer getCareTaker_VocationalInstructor() {
		return careTaker_VocationalInstructor;
	}

	public void setCareTaker_VocationalInstructor(Integer careTaker_VocationalInstructor) {
		this.careTaker_VocationalInstructor = careTaker_VocationalInstructor;
	}

	public Integer getOfficerIncharge_Superintendent() {
		return officerIncharge_Superintendent;
	}

	public void setOfficerIncharge_Superintendent(Integer officerIncharge_Superintendent) {
		this.officerIncharge_Superintendent = officerIncharge_Superintendent;
	}

	public Integer getParaMedicalStaff() {
		return paraMedicalStaff;
	}

	public void setParaMedicalStaff(Integer paraMedicalStaff) {
		this.paraMedicalStaff = paraMedicalStaff;
	}

	public Integer getOthers() {
		return others;
	}

	public void setOthers(Integer others) {
		this.others = others;
	}

}
