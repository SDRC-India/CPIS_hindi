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
@Table(name="constitution_of_cwc")
public class ConstitutionOfCWC implements Serializable{

	private static final long serialVersionUID = -7662924102203210340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "seriel")
	private Integer id;
	
	@Column(name="constitution_date")
	private Date constitutionDate;
	
	@Column(name="chairperson_name")
	private String chairpersonName;
	
	@Column(name="chairperson_sex")
	private Integer chairpersonSex;
	
	@Column(name="chairperson_joining_date")
	private Date chairpersonJoiningDate;
	
	@Column(name="chairperson_contact")
	private String chairpersonContact;
	
	@Column(name="chairperson_email")
	private String chairpersonEmail;
	
	@Column(name="member_one_name")
	private String memberOneName;
	
	@Column(name="member_one_sex")
	private Integer memberOneSex;
	
	@Column(name="member_one_joining_date")
	private Date memberOneJoiningDate;
	
	@Column(name="member_one_contact")
	private String memberOneContact;
	
	@Column(name="member_one_email")
	private String memberOneEmail;
	
	@Column(name="member_two_name")
	private String memberTwoName;
	
	@Column(name="member_two_sex")
	private Integer memberTwoSex;
	
	@Column(name="member_two_joining_date")
	private Date memberTwoJoiningDate;
	
	@Column(name="member_two_contact")
	private String memberTwoContact;
	
	@Column(name="member_two_email")
	private String memberTwoEmail;
	
	@Column(name="member_three_name")
	private String memberThreeName;
	
	@Column(name="member_three_sex")
	private Integer memberThreeSex;
	
	@Column(name="member_three_joining_date")
	private Date memberThreeJoiningDate;
	
	@Column(name="member_three_contact")
	private String memberThreeContact;
	
	@Column(name="member_three_email")
	private String memberThreeEmail;
	
	@Column(name="member_four_name")
	private String memberFourName;
	
	@Column(name="member_four_sex")
	private Integer memberFourSex;
	
	@Column(name="member_four_joining_date")
	private Date memberFourJoiningDate;
	
	@Column(name="member_four_contact")
	private String memberFourContact;
	
	@Column(name="member_four_email")
	private String memberFourEmail;
	
	@Column(name="deo_name")
	private String DEOName;
	
	@Column(name="deo_sex")
	private Integer DEOSex;
	
	@Column(name="deo_joining_date")
	private Date DEOJoiningDate;
	
	@Column(name="deo_contact")
	private String DEOContact;
	
	@Column(name="deo_email")
	private String DEOEmail;
	
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

	public Date getConstitutionDate() {
		return constitutionDate;
	}

	public void setConstitutionDate(Date constitutionDate) {
		this.constitutionDate = constitutionDate;
	}

	public String getChairpersonName() {
		return chairpersonName;
	}

	public void setChairpersonName(String chairpersonName) {
		this.chairpersonName = chairpersonName;
	}

	public Integer getChairpersonSex() {
		return chairpersonSex;
	}

	public void setChairpersonSex(Integer chairpersonSex) {
		this.chairpersonSex = chairpersonSex;
	}

	public Date getChairpersonJoiningDate() {
		return chairpersonJoiningDate;
	}

	public void setChairpersonJoiningDate(Date chairpersonJoiningDate) {
		this.chairpersonJoiningDate = chairpersonJoiningDate;
	}

	public String getChairpersonContact() {
		return chairpersonContact;
	}

	public void setChairpersonContact(String chairpersonContact) {
		this.chairpersonContact = chairpersonContact;
	}

	public String getChairpersonEmail() {
		return chairpersonEmail;
	}

	public void setChairpersonEmail(String chairpersonEmail) {
		this.chairpersonEmail = chairpersonEmail;
	}

	public String getMemberOneName() {
		return memberOneName;
	}

	public void setMemberOneName(String memberOneName) {
		this.memberOneName = memberOneName;
	}

	public Integer getMemberOneSex() {
		return memberOneSex;
	}

	public void setMemberOneSex(Integer memberOneSex) {
		this.memberOneSex = memberOneSex;
	}

	public Date getMemberOneJoiningDate() {
		return memberOneJoiningDate;
	}

	public void setMemberOneJoiningDate(Date memberOneJoiningDate) {
		this.memberOneJoiningDate = memberOneJoiningDate;
	}

	public String getMemberOneContact() {
		return memberOneContact;
	}

	public void setMemberOneContact(String memberOneContact) {
		this.memberOneContact = memberOneContact;
	}

	public String getMemberOneEmail() {
		return memberOneEmail;
	}

	public void setMemberOneEmail(String memberOneEmail) {
		this.memberOneEmail = memberOneEmail;
	}

	public String getMemberTwoName() {
		return memberTwoName;
	}

	public void setMemberTwoName(String memberTwoName) {
		this.memberTwoName = memberTwoName;
	}

	public Integer getMemberTwoSex() {
		return memberTwoSex;
	}

	public void setMemberTwoSex(Integer memberTwoSex) {
		this.memberTwoSex = memberTwoSex;
	}

	public Date getMemberTwoJoiningDate() {
		return memberTwoJoiningDate;
	}

	public void setMemberTwoJoiningDate(Date memberTwoJoiningDate) {
		this.memberTwoJoiningDate = memberTwoJoiningDate;
	}

	public String getMemberTwoContact() {
		return memberTwoContact;
	}

	public void setMemberTwoContact(String memberTwoContact) {
		this.memberTwoContact = memberTwoContact;
	}

	public String getMemberTwoEmail() {
		return memberTwoEmail;
	}

	public void setMemberTwoEmail(String memberTwoEmail) {
		this.memberTwoEmail = memberTwoEmail;
	}

	public String getMemberThreeName() {
		return memberThreeName;
	}

	public void setMemberThreeName(String memberThreeName) {
		this.memberThreeName = memberThreeName;
	}

	public Integer getMemberThreeSex() {
		return memberThreeSex;
	}

	public void setMemberThreeSex(Integer memberThreeSex) {
		this.memberThreeSex = memberThreeSex;
	}

	public Date getMemberThreeJoiningDate() {
		return memberThreeJoiningDate;
	}

	public void setMemberThreeJoiningDate(Date memberThreeJoiningDate) {
		this.memberThreeJoiningDate = memberThreeJoiningDate;
	}

	public String getMemberThreeContact() {
		return memberThreeContact;
	}

	public void setMemberThreeContact(String memberThreeContact) {
		this.memberThreeContact = memberThreeContact;
	}

	public String getMemberThreeEmail() {
		return memberThreeEmail;
	}

	public void setMemberThreeEmail(String memberThreeEmail) {
		this.memberThreeEmail = memberThreeEmail;
	}

	public String getMemberFourName() {
		return memberFourName;
	}

	public void setMemberFourName(String memberFourName) {
		this.memberFourName = memberFourName;
	}

	public Integer getMemberFourSex() {
		return memberFourSex;
	}

	public void setMemberFourSex(Integer memberFourSex) {
		this.memberFourSex = memberFourSex;
	}

	public Date getMemberFourJoiningDate() {
		return memberFourJoiningDate;
	}

	public void setMemberFourJoiningDate(Date memberFourJoiningDate) {
		this.memberFourJoiningDate = memberFourJoiningDate;
	}

	public String getMemberFourContact() {
		return memberFourContact;
	}

	public void setMemberFourContact(String memberFourContact) {
		this.memberFourContact = memberFourContact;
	}

	public String getMemberFourEmail() {
		return memberFourEmail;
	}

	public void setMemberFourEmail(String memberFourEmail) {
		this.memberFourEmail = memberFourEmail;
	}

	public String getDEOName() {
		return DEOName;
	}

	public void setDEOName(String dEOName) {
		DEOName = dEOName;
	}

	public Integer getDEOSex() {
		return DEOSex;
	}

	public void setDEOSex(Integer dEOSex) {
		DEOSex = dEOSex;
	}

	public Date getDEOJoiningDate() {
		return DEOJoiningDate;
	}

	public void setDEOJoiningDate(Date dEOJoiningDate) {
		DEOJoiningDate = dEOJoiningDate;
	}

	public String getDEOContact() {
		return DEOContact;
	}

	public void setDEOContact(String dEOContact) {
		DEOContact = dEOContact;
	}

	public String getDEOEmail() {
		return DEOEmail;
	}

	public void setDEOEmail(String dEOEmail) {
		DEOEmail = dEOEmail;
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
