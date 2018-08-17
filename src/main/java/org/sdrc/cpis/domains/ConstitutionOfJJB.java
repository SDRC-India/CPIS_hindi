package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="constitution_of_jjb")
public class ConstitutionOfJJB implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1325628795223641749L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="jjb_date")
	private Date jjbDate;
		
	@Column(name="magistrate_name")
	private String magistrateName;
	
	@Column(name="magistrate_sex")
	private Integer magistrateSex;
	
	@Column(name="joining_date")
	private Date joiningDate;
	
	@Column(name="magistrate_contactno")
	private String magistrateContactNo;
	
	@Column(name="magistrate_emailid")
	private String magistrateEmailId;
	
	@Column(name="social_worker_one_name")
	private String socialWorkerOneName;
	
	@Column(name="social_worker_one_sex")
	private Integer socialWorkerOneSex;
	
	@Column(name="social_worker_one_joiningdate")
	private Date socialWorkerOneJoiningDate;
	
	@Column(name="social_worker_one_contactno")
	private String socialWorkerOneContactNo;
	
	@Column(name="social_worker_one_emailid")
	private String socialWorkerOneEmailId;
	
	@Column(name="social_worker_two_name")
	private String socialWorkerTwoName;
	
	@Column(name="social_worker_two_sex")
	private Integer socialWorkerTwoSex;
	
	@Column(name="social_worker_two_joiningdate")
	private Date socialWorkerTwoJoiningDate;
	
	@Column(name="social_worker_two_contactno")
	private String socialWorkerTwoContactNo;
	
	@Column(name="social_worker_two_emailid")
	private String socialWorkerTwoEmailId;
	
	@Column(name="area_id")
	private Integer areaId;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="user_ip")
	private String userIp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getJjbDate() {
		return jjbDate;
	}

	public void setJjbDate(Date jjbDate) {
		this.jjbDate = jjbDate;
	}

	public String getMagistrateName() {
		return magistrateName;
	}

	public void setMagistrateName(String magistrateName) {
		this.magistrateName = magistrateName;
	}

	public Integer getMagistrateSex() {
		return magistrateSex;
	}

	public void setMagistrateSex(Integer magistrateSex) {
		this.magistrateSex = magistrateSex;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getMagistrateContactNo() {
		return magistrateContactNo;
	}

	public void setMagistrateContactNo(String magistrateContactNo) {
		this.magistrateContactNo = magistrateContactNo;
	}

	public String getMagistrateEmailId() {
		return magistrateEmailId;
	}

	public void setMagistrateEmailId(String magistrateEmailId) {
		this.magistrateEmailId = magistrateEmailId;
	}

	public String getSocialWorkerOneName() {
		return socialWorkerOneName;
	}

	public void setSocialWorkerOneName(String socialWorkerOneName) {
		this.socialWorkerOneName = socialWorkerOneName;
	}

	public Integer getSocialWorkerOneSex() {
		return socialWorkerOneSex;
	}

	public void setSocialWorkerOneSex(Integer socialWorkerOneSex) {
		this.socialWorkerOneSex = socialWorkerOneSex;
	}

	public Date getSocialWorkerOneJoiningDate() {
		return socialWorkerOneJoiningDate;
	}

	public void setSocialWorkerOneJoiningDate(Date socialWorkerOneJoiningDate) {
		this.socialWorkerOneJoiningDate = socialWorkerOneJoiningDate;
	}

	public String getSocialWorkerOneContactNo() {
		return socialWorkerOneContactNo;
	}

	public void setSocialWorkerOneContactNo(String socialWorkerOneContactNo) {
		this.socialWorkerOneContactNo = socialWorkerOneContactNo;
	}

	public String getSocialWorkerOneEmailId() {
		return socialWorkerOneEmailId;
	}

	public void setSocialWorkerOneEmailId(String socialWorkerOneEmailId) {
		this.socialWorkerOneEmailId = socialWorkerOneEmailId;
	}

	public String getSocialWorkerTwoName() {
		return socialWorkerTwoName;
	}

	public void setSocialWorkerTwoName(String socialWorkerTwoName) {
		this.socialWorkerTwoName = socialWorkerTwoName;
	}

	public Integer getSocialWorkerTwoSex() {
		return socialWorkerTwoSex;
	}

	public void setSocialWorkerTwoSex(Integer socialWorkerTwoSex) {
		this.socialWorkerTwoSex = socialWorkerTwoSex;
	}

	public Date getSocialWorkerTwoJoiningDate() {
		return socialWorkerTwoJoiningDate;
	}

	public void setSocialWorkerTwoJoiningDate(Date socialWorkerTwoJoiningDate) {
		this.socialWorkerTwoJoiningDate = socialWorkerTwoJoiningDate;
	}

	public String getSocialWorkerTwoContactNo() {
		return socialWorkerTwoContactNo;
	}

	public void setSocialWorkerTwoContactNo(String socialWorkerTwoContactNo) {
		this.socialWorkerTwoContactNo = socialWorkerTwoContactNo;
	}

	public String getSocialWorkerTwoEmailId() {
		return socialWorkerTwoEmailId;
	}

	public void setSocialWorkerTwoEmailId(String socialWorkerTwoEmailId) {
		this.socialWorkerTwoEmailId = socialWorkerTwoEmailId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
}
