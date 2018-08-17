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
@Table(name="constitution_of_dcpc")
public class ConstitutionOfDCPC implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6508211500035078230L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="dcpc_date")
	private Date dcpcDate;
		
	@Column(name="zilla_parishad_name")
	private String  zillaParishadName;
	
	@Column(name="zilla_parishad_sex")
	private Integer zillaParishadSex;
	
	@Column(name="joining_date")
	private Date joiningDate;
	
	@Column(name="zilla_parishad_contactno")
	private String zillaParishadContactNo;
	
	@Column(name="zilla_parishad_emailid")
	private String zillaParishadEmailId;
	
	@Column(name="magistrate_name")
	private String magistrateName;
	
	@Column(name="magistrate_sex")
	private Integer magistrateSex;
	
	@Column(name="magistrate_contactno")
	private String magistrateContactNo;
	
	@Column(name="magistrate_emailid")
	private String magistrateEmailId;
	
	@Column(name="deo_name")
	private String deoName;
	
	@Column(name="deo_sex")
	private Integer deoSex;
	
	@Column(name="deo_contactno")
	private String deoContactNo;
	
	@Column(name="deo_emailid")
	private String deoEmailId;
	
	@Column(name="cdmo_name")
	private String cdmoName;
	
	@Column(name="cdmo_sex")
	private Integer cdmoSex;
	
	@Column(name="cdmo_contactno")
	private String cdmoContactNo;
	
	@Column(name="cdmo_emailid")
	private String cdmoEmailId;
	
	@Column(name="dlo_name")
	private String dloName;
	
	@Column(name="dlo_sex")
	private Integer dloSex;
	
	@Column(name="dlo_contactno")
	private String dloContactNo;
	
	@Column(name="dlo_emailid")
	private String dloEmailId;
	
	@Column(name="pddrda_name")
	private String pddrdaName;
	
	@Column(name="pddrda_sex")
	private Integer pddrdaSex;
	
	@Column(name="pddrda_contactno")
	private String pddrdaContactNo;
	
	@Column(name="pddrda_emailid")
	private String pddrdaEmailId;
	
	@Column(name="police_superintendent_name")
	private String policeSuperintendentName;
	
	@Column(name="police_superintendent_sex")
	private Integer policeSuperintendentSex;
	
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
	
	@Column(name="member_one_name")
	private String memberOneName;
	
	@Column(name="member_one_sex")
	private Integer memberOneSex;
	
	@Column(name="member_one_contactno")
	private String memberOneContactNo;
	
	@Column(name="member_one_emailid")
	private String memberOneEmailId;
	
	@Column(name="member_two_name")
	private String memberTwoName;
	
	@Column(name="member_two_sex")
	private Integer memberTwoSex;
	
	@Column(name="member_two_contactno")
	private String memberTwoContactNo;
	
	@Column(name="member_two_emailid")
	private String memberTwoEmailId;
	
	@Column(name="member_three_name")
	private String memberThreeName;
	
	@Column(name="member_three_sex")
	private Integer memberThreeSex;
	
	@Column(name="member_three_contactno")
	private String memberThreeContactNo;
	
	@Column(name="member_three_emailid")
	private String memberThreeEmailId;
	
	@Column(name="member_four_name")
	private String memberFourName;
	
	@Column(name="member_four_sex")
	private Integer memberFourSex;
	
	@Column(name="member_four_contactno")
	private String memberFourContactNo;
	
	@Column(name="member_four_emailid")
	private String memberFourEmailId;
	
	@Column(name="member_five_name")
	private String memberFiveName;
	
	@Column(name="member_five_sex")
	private Integer memberFiveSex;
	
	@Column(name="member_five_contactno")
	private String memberFiveContactNo;
	
	@Column(name="member_five_emailid")
	private String memberFiveEmailId;
	
	@Column(name="member_six_name")
	private String memberSixName;
	
	@Column(name="member_six_sex")
	private Integer memberSixSex;
	
	@Column(name="member_six_contactno")
	private String memberSixContactNo;
	
	@Column(name="member_six_emailid")
	private String memberSixEmailId;
	
	@Column(name="member_seven_name")
	private String memberSevenName;
	
	@Column(name="member_seven_sex")
	private Integer memberSevenSex;
	
	@Column(name="member_seven_contactno")
	private String memberSevenContactNo;
	
	@Column(name="member_seven_emailid")
	private String memberSevenEmailId;
	
	@Column(name="member_eight_name")
	private String memberEightName;
	
	@Column(name="member_eight_sex")
	private Integer memberEightSex;
	
	@Column(name="member_eight_contactno")
	private String memberEightContactNo;
	
	@Column(name="member_eight_emailid")
	private String memberEightEmailId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDcpcDate() {
		return dcpcDate;
	}

	public void setDcpcDate(Date dcpcDate) {
		this.dcpcDate = dcpcDate;
	}

	public String getZillaParishadName() {
		return zillaParishadName;
	}

	public void setZillaParishadName(String zillaParishadName) {
		this.zillaParishadName = zillaParishadName;
	}

	public Integer getZillaParishadSex() {
		return zillaParishadSex;
	}

	public void setZillaParishadSex(Integer zillaParishadSex) {
		this.zillaParishadSex = zillaParishadSex;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getZillaParishadContactNo() {
		return zillaParishadContactNo;
	}

	public void setZillaParishadContactNo(String zillaParishadContactNo) {
		this.zillaParishadContactNo = zillaParishadContactNo;
	}

	public String getZillaParishadEmailId() {
		return zillaParishadEmailId;
	}

	public void setZillaParishadEmailId(String zillaParishadEmailId) {
		this.zillaParishadEmailId = zillaParishadEmailId;
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

	public String getDeoName() {
		return deoName;
	}

	public void setDeoName(String deoName) {
		this.deoName = deoName;
	}

	public Integer getDeoSex() {
		return deoSex;
	}

	public void setDeoSex(Integer deoSex) {
		this.deoSex = deoSex;
	}

	public String getDeoContactNo() {
		return deoContactNo;
	}

	public void setDeoContactNo(String deoContactNo) {
		this.deoContactNo = deoContactNo;
	}

	public String getDeoEmailId() {
		return deoEmailId;
	}

	public void setDeoEmailId(String deoEmailId) {
		this.deoEmailId = deoEmailId;
	}

	public String getCdmoName() {
		return cdmoName;
	}

	public void setCdmoName(String cdmoName) {
		this.cdmoName = cdmoName;
	}

	public Integer getCdmoSex() {
		return cdmoSex;
	}

	public void setCdmoSex(Integer cdmoSex) {
		this.cdmoSex = cdmoSex;
	}

	public String getCdmoContactNo() {
		return cdmoContactNo;
	}

	public void setCdmoContactNo(String cdmoContactNo) {
		this.cdmoContactNo = cdmoContactNo;
	}

	public String getCdmoEmailId() {
		return cdmoEmailId;
	}

	public void setCdmoEmailId(String cdmoEmailId) {
		this.cdmoEmailId = cdmoEmailId;
	}

	public String getDloName() {
		return dloName;
	}

	public void setDloName(String dloName) {
		this.dloName = dloName;
	}

	public Integer getDloSex() {
		return dloSex;
	}

	public void setDloSex(Integer dloSex) {
		this.dloSex = dloSex;
	}

	public String getDloContactNo() {
		return dloContactNo;
	}

	public void setDloContactNo(String dloContactNo) {
		this.dloContactNo = dloContactNo;
	}

	public String getDloEmailId() {
		return dloEmailId;
	}

	public void setDloEmailId(String dloEmailId) {
		this.dloEmailId = dloEmailId;
	}

	public String getPddrdaName() {
		return pddrdaName;
	}

	public void setPddrdaName(String pddrdaName) {
		this.pddrdaName = pddrdaName;
	}

	public Integer getPddrdaSex() {
		return pddrdaSex;
	}

	public void setPddrdaSex(Integer pddrdaSex) {
		this.pddrdaSex = pddrdaSex;
	}

	public String getPddrdaContactNo() {
		return pddrdaContactNo;
	}

	public void setPddrdaContactNo(String pddrdaContactNo) {
		this.pddrdaContactNo = pddrdaContactNo;
	}

	public String getPddrdaEmailId() {
		return pddrdaEmailId;
	}

	public void setPddrdaEmailId(String pddrdaEmailId) {
		this.pddrdaEmailId = pddrdaEmailId;
	}

	public String getPoliceSuperintendentName() {
		return policeSuperintendentName;
	}

	public void setPoliceSuperintendentName(String policeSuperintendentName) {
		this.policeSuperintendentName = policeSuperintendentName;
	}
	public Integer getPoliceSuperintendentSex() {
		return policeSuperintendentSex;
	}

	public void setPoliceSuperintendentSex(Integer policeSuperintendentSex) {
		this.policeSuperintendentSex = policeSuperintendentSex;
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

	public String getMemberOneContactNo() {
		return memberOneContactNo;
	}

	public void setMemberOneContactNo(String memberOneContactNo) {
		this.memberOneContactNo = memberOneContactNo;
	}

	public String getMemberOneEmailId() {
		return memberOneEmailId;
	}

	public void setMemberOneEmailId(String memberOneEmailId) {
		this.memberOneEmailId = memberOneEmailId;
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

	public String getMemberTwoContactNo() {
		return memberTwoContactNo;
	}

	public void setMemberTwoContactNo(String memberTwoContactNo) {
		this.memberTwoContactNo = memberTwoContactNo;
	}

	public String getMemberTwoEmailId() {
		return memberTwoEmailId;
	}

	public void setMemberTwoEmailId(String memberTwoEmailId) {
		this.memberTwoEmailId = memberTwoEmailId;
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

	public String getMemberThreeContactNo() {
		return memberThreeContactNo;
	}

	public void setMemberThreeContactNo(String memberThreeContactNo) {
		this.memberThreeContactNo = memberThreeContactNo;
	}

	public String getMemberThreeEmailId() {
		return memberThreeEmailId;
	}

	public void setMemberThreeEmailId(String memberThreeEmailId) {
		this.memberThreeEmailId = memberThreeEmailId;
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

	public String getMemberFourContactNo() {
		return memberFourContactNo;
	}

	public void setMemberFourContactNo(String memberFourContactNo) {
		this.memberFourContactNo = memberFourContactNo;
	}

	public String getMemberFourEmailId() {
		return memberFourEmailId;
	}

	public void setMemberFourEmailId(String memberFourEmailId) {
		this.memberFourEmailId = memberFourEmailId;
	}

	public String getMemberFiveName() {
		return memberFiveName;
	}

	public void setMemberFiveName(String memberFiveName) {
		this.memberFiveName = memberFiveName;
	}

	public Integer getMemberFiveSex() {
		return memberFiveSex;
	}

	public void setMemberFiveSex(Integer memberFiveSex) {
		this.memberFiveSex = memberFiveSex;
	}

	public String getMemberFiveContactNo() {
		return memberFiveContactNo;
	}

	public void setMemberFiveContactNo(String memberFiveContactNo) {
		this.memberFiveContactNo = memberFiveContactNo;
	}

	public String getMemberFiveEmailId() {
		return memberFiveEmailId;
	}

	public void setMemberFiveEmailId(String memberFiveEmailId) {
		this.memberFiveEmailId = memberFiveEmailId;
	}

	public String getMemberSixName() {
		return memberSixName;
	}

	public void setMemberSixName(String memberSixName) {
		this.memberSixName = memberSixName;
	}

	public Integer getMemberSixSex() {
		return memberSixSex;
	}

	public void setMemberSixSex(Integer memberSixSex) {
		this.memberSixSex = memberSixSex;
	}

	public String getMemberSixContactNo() {
		return memberSixContactNo;
	}

	public void setMemberSixContactNo(String memberSixContactNo) {
		this.memberSixContactNo = memberSixContactNo;
	}

	public String getMemberSixEmailId() {
		return memberSixEmailId;
	}

	public void setMemberSixEmailId(String memberSixEmailId) {
		this.memberSixEmailId = memberSixEmailId;
	}

	public String getMemberSevenName() {
		return memberSevenName;
	}

	public void setMemberSevenName(String memberSevenName) {
		this.memberSevenName = memberSevenName;
	}

	public Integer getMemberSevenSex() {
		return memberSevenSex;
	}

	public void setMemberSevenSex(Integer memberSevenSex) {
		this.memberSevenSex = memberSevenSex;
	}

	public String getMemberSevenContactNo() {
		return memberSevenContactNo;
	}

	public void setMemberSevenContactNo(String memberSevenContactNo) {
		this.memberSevenContactNo = memberSevenContactNo;
	}

	public String getMemberSevenEmailId() {
		return memberSevenEmailId;
	}

	public void setMemberSevenEmailId(String memberSevenEmailId) {
		this.memberSevenEmailId = memberSevenEmailId;
	}

	public String getMemberEightName() {
		return memberEightName;
	}

	public void setMemberEightName(String memberEightName) {
		this.memberEightName = memberEightName;
	}

	public Integer getMemberEightSex() {
		return memberEightSex;
	}

	public void setMemberEightSex(Integer memberEightSex) {
		this.memberEightSex = memberEightSex;
	}

	public String getMemberEightContactNo() {
		return memberEightContactNo;
	}

	public void setMemberEightContactNo(String memberEightContactNo) {
		this.memberEightContactNo = memberEightContactNo;
	}

	public String getMemberEightEmailId() {
		return memberEightEmailId;
	}

	public void setMemberEightEmailId(String memberEightEmailId) {
		this.memberEightEmailId = memberEightEmailId;
	}

}
