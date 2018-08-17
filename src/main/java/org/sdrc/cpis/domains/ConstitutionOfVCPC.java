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
@Table(name="constitution_of_vcpc")
public class ConstitutionOfVCPC implements Serializable{

	private static final long serialVersionUID = 6332327719765878661L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "seriel")
	private Integer id;
	
	@Column(name="constitution_date")
	private Date constitutionDate;
	
	@Column(name="vlg_head_man_name")
	private String vlgHeadManName;
	
	@Column(name="vlg_head_man_sex")
	private Integer vlgHeadManSex;
	
	@Column(name="vlg_head_man_contact")
	private String vlgHeadManContact;
	
	@Column(name="vlg_head_man_email")
	private String vlgHeadManEmail;
	
	@Column(name="anm_name")
	private String anmName;
	
	@Column(name="anm_contact")
	private String anmContact;
	
	@Column(name="anm_email")
	private String anmEmail;
	
	@Column(name="aw_name")
	private String awName;
	
	@Column(name="aw_contact")
	private String awContact;
	
	@Column(name="aw_email")
	private String awEmail;
	
	@Column(name="school_teacher_name")
	private String schoolTeacherName;
	
	@Column(name="school_teacher_sex")
	private Integer schoolTeacherSex;
	
	@Column(name="school_teacher_contact")
	private String schoolTeacherContact;
	
	@Column(name="school_teacher_email")
	private String schoolTeacherEmail;
	
	@Column(name="dcpu_member_name")
	private String dcpuMemberName;
	
	@Column(name="dcpu_member_sex")
	private Integer dcpuMemberSex;
	
	@Column(name="dcpu_member_contact")
	private String dcpuMemberContact;
	
	@Column(name="dcpu_member_email")
	private String dcpuMemberEmail;
	
	@Column(name="cr_one_name")
	private String crOneName;
	
	@Column(name="cr_one_sex")
	private Integer crOneSex;
	
	@Column(name="cr_one_contact")
	private String crOneContact;
	
	@Column(name="cr_one_email")
	private String crOneEmail;
	
	@Column(name="cr_two_name")
	private String crTwoName;
	
	@Column(name="cr_two_sex")
	private Integer crTwoSex;
	
	@Column(name="cr_two_contact")
	private String crTwoContact;
	
	@Column(name="cr_two_email")
	private String crTwoEmail;
	
	@Column(name="vlg_member_one_name")
	private String vlgMemberOneName;
	
	@Column(name="vlg_member_one_sex")
	private Integer vlgMemberOneSex;
	
	@Column(name="vlg_member_one_contact")
	private String vlgMemberOneContact;
	
	@Column(name="vlg_member_one_email")
	private String vlgMemberOneEmail;
	
	@Column(name="vlg_member_two_name")
	private String vlgMemberTwoName;
	
	@Column(name="vlg_member_two_sex")
	private Integer vlgMemberTwoSex;
	
	@Column(name="vlg_member_two_contact")
	private String vlgMemberTwoContact;
	
	@Column(name="vlg_member_two_email")
	private String vlgMemberTwoEmail;
	
	@Column(name="vlg_member_three_name")
	private String vlgMemberThreeName;
	
	@Column(name="vlg_member_three_sex")
	private Integer vlgMemberThreeSex;
	
	@Column(name="vlg_member_three_contact")
	private String vlgMemberThreeContact;
	
	@Column(name="vlg_member_three_email")
	private String vlgMemberThreeEmail;
	
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

	public Date getConstitutionDate() {
		return constitutionDate;
	}

	public void setConstitutionDate(Date constitutionDate) {
		this.constitutionDate = constitutionDate;
	}

	public String getVlgHeadManName() {
		return vlgHeadManName;
	}

	public void setVlgHeadManName(String vlgHeadManName) {
		this.vlgHeadManName = vlgHeadManName;
	}

	public Integer getVlgHeadManSex() {
		return vlgHeadManSex;
	}

	public void setVlgHeadManSex(Integer vlgHeadManSex) {
		this.vlgHeadManSex = vlgHeadManSex;
	}

	public String getVlgHeadManContact() {
		return vlgHeadManContact;
	}

	public void setVlgHeadManContact(String vlgHeadManContact) {
		this.vlgHeadManContact = vlgHeadManContact;
	}

	public String getVlgHeadManEmail() {
		return vlgHeadManEmail;
	}

	public void setVlgHeadManEmail(String vlgHeadManEmail) {
		this.vlgHeadManEmail = vlgHeadManEmail;
	}

	public String getAnmName() {
		return anmName;
	}

	public void setAnmName(String anmName) {
		this.anmName = anmName;
	}

	public String getAnmContact() {
		return anmContact;
	}

	public void setAnmContact(String anmContact) {
		this.anmContact = anmContact;
	}

	public String getAnmEmail() {
		return anmEmail;
	}

	public void setAnmEmail(String anmEmail) {
		this.anmEmail = anmEmail;
	}

	public String getAwName() {
		return awName;
	}

	public void setAwName(String awName) {
		this.awName = awName;
	}

	public String getAwContact() {
		return awContact;
	}

	public void setAwContact(String awContact) {
		this.awContact = awContact;
	}

	public String getAwEmail() {
		return awEmail;
	}

	public void setAwEmail(String awEmail) {
		this.awEmail = awEmail;
	}

	public String getSchoolTeacherName() {
		return schoolTeacherName;
	}

	public void setSchoolTeacherName(String schoolTeacherName) {
		this.schoolTeacherName = schoolTeacherName;
	}

	public Integer getSchoolTeacherSex() {
		return schoolTeacherSex;
	}

	public void setSchoolTeacherSex(Integer schoolTeacherSex) {
		this.schoolTeacherSex = schoolTeacherSex;
	}

	public String getSchoolTeacherContact() {
		return schoolTeacherContact;
	}

	public void setSchoolTeacherContact(String schoolTeacherContact) {
		this.schoolTeacherContact = schoolTeacherContact;
	}

	public String getSchoolTeacherEmail() {
		return schoolTeacherEmail;
	}

	public void setSchoolTeacherEmail(String schoolTeacherEmail) {
		this.schoolTeacherEmail = schoolTeacherEmail;
	}

	public String getDcpuMemberName() {
		return dcpuMemberName;
	}

	public void setDcpuMemberName(String dcpuMemberName) {
		this.dcpuMemberName = dcpuMemberName;
	}

	public Integer getDcpuMemberSex() {
		return dcpuMemberSex;
	}

	public void setDcpuMemberSex(Integer dcpuMemberSex) {
		this.dcpuMemberSex = dcpuMemberSex;
	}

	public String getDcpuMemberContact() {
		return dcpuMemberContact;
	}

	public void setDcpuMemberContact(String dcpuMemberContact) {
		this.dcpuMemberContact = dcpuMemberContact;
	}

	public String getDcpuMemberEmail() {
		return dcpuMemberEmail;
	}

	public void setDcpuMemberEmail(String dcpuMemberEmail) {
		this.dcpuMemberEmail = dcpuMemberEmail;
	}

	public String getCrOneName() {
		return crOneName;
	}

	public void setCrOneName(String crOneName) {
		this.crOneName = crOneName;
	}

	public Integer getCrOneSex() {
		return crOneSex;
	}

	public void setCrOneSex(Integer crOneSex) {
		this.crOneSex = crOneSex;
	}

	public String getCrOneContact() {
		return crOneContact;
	}

	public void setCrOneContact(String crOneContact) {
		this.crOneContact = crOneContact;
	}

	public String getCrOneEmail() {
		return crOneEmail;
	}

	public void setCrOneEmail(String crOneEmail) {
		this.crOneEmail = crOneEmail;
	}

	public String getCrTwoName() {
		return crTwoName;
	}

	public void setCrTwoName(String crTwoName) {
		this.crTwoName = crTwoName;
	}

	public Integer getCrTwoSex() {
		return crTwoSex;
	}

	public void setCrTwoSex(Integer crTwoSex) {
		this.crTwoSex = crTwoSex;
	}

	public String getCrTwoContact() {
		return crTwoContact;
	}

	public void setCrTwoContact(String crTwoContact) {
		this.crTwoContact = crTwoContact;
	}

	public String getCrTwoEmail() {
		return crTwoEmail;
	}

	public void setCrTwoEmail(String crTwoEmail) {
		this.crTwoEmail = crTwoEmail;
	}

	public String getVlgMemberOneName() {
		return vlgMemberOneName;
	}

	public void setVlgMemberOneName(String vlgMemberOneName) {
		this.vlgMemberOneName = vlgMemberOneName;
	}

	public Integer getVlgMemberOneSex() {
		return vlgMemberOneSex;
	}

	public void setVlgMemberOneSex(Integer vlgMemberOneSex) {
		this.vlgMemberOneSex = vlgMemberOneSex;
	}

	public String getVlgMemberOneContact() {
		return vlgMemberOneContact;
	}

	public void setVlgMemberOneContact(String vlgMemberOneContact) {
		this.vlgMemberOneContact = vlgMemberOneContact;
	}

	public String getVlgMemberOneEmail() {
		return vlgMemberOneEmail;
	}

	public void setVlgMemberOneEmail(String vlgMemberOneEmail) {
		this.vlgMemberOneEmail = vlgMemberOneEmail;
	}

	public String getVlgMemberTwoName() {
		return vlgMemberTwoName;
	}

	public void setVlgMemberTwoName(String vlgMemberTwoName) {
		this.vlgMemberTwoName = vlgMemberTwoName;
	}

	public Integer getVlgMemberTwoSex() {
		return vlgMemberTwoSex;
	}

	public void setVlgMemberTwoSex(Integer vlgMemberTwoSex) {
		this.vlgMemberTwoSex = vlgMemberTwoSex;
	}

	public String getVlgMemberTwoContact() {
		return vlgMemberTwoContact;
	}

	public void setVlgMemberTwoContact(String vlgMemberTwoContact) {
		this.vlgMemberTwoContact = vlgMemberTwoContact;
	}

	public String getVlgMemberTwoEmail() {
		return vlgMemberTwoEmail;
	}

	public void setVlgMemberTwoEmail(String vlgMemberTwoEmail) {
		this.vlgMemberTwoEmail = vlgMemberTwoEmail;
	}

	public String getVlgMemberThreeName() {
		return vlgMemberThreeName;
	}

	public void setVlgMemberThreeName(String vlgMemberThreeName) {
		this.vlgMemberThreeName = vlgMemberThreeName;
	}

	public Integer getVlgMemberThreeSex() {
		return vlgMemberThreeSex;
	}

	public void setVlgMemberThreeSex(Integer vlgMemberThreeSex) {
		this.vlgMemberThreeSex = vlgMemberThreeSex;
	}

	public String getVlgMemberThreeContact() {
		return vlgMemberThreeContact;
	}

	public void setVlgMemberThreeContact(String vlgMemberThreeContact) {
		this.vlgMemberThreeContact = vlgMemberThreeContact;
	}

	public String getVlgMemberThreeEmail() {
		return vlgMemberThreeEmail;
	}

	public void setVlgMemberThreeEmail(String vlgMemberThreeEmail) {
		this.vlgMemberThreeEmail = vlgMemberThreeEmail;
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
