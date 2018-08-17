package org.sdrc.cpis.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CICL_undertaking_for_intrimcustody")

public class CICLUndertakingForInterimCustody {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="guardian_name")
	private String guardianName;
	
	@Column(name="guardian_houseno")
	private String guardianHouseNo;
	
	@Column(name="guardian_street")
	private String guardianStreet;
	
	@Column(name="guardian_village_town")
	private String guardianVillageTown;
	
	@Column(name="guardian_district")
	private String guardianDistrict;
	
	@Column(name="guardian_state")
	private String guardianState;
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="child_age")
	private Integer childAge;
	
	@Column(name="board")
	private String board;
	
	@Column(name="signed_date")
	private Date signedDate;
	
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
	
	public ChildDetails getChildId() {
		return childId;
	}
	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianHouseNo() {
		return guardianHouseNo;
	}
	public void setGuardianHouseNo(String guardianHouseNo) {
		this.guardianHouseNo = guardianHouseNo;
	}
	public String getGuardianStreet() {
		return guardianStreet;
	}
	public void setGuardianStreet(String guardianStreet) {
		this.guardianStreet = guardianStreet;
	}
	public String getGuardianVillageTown() {
		return guardianVillageTown;
	}
	public void setGuardianVillageTown(String guardianVillageTown) {
		this.guardianVillageTown = guardianVillageTown;
	}
	public String getGuardianDistrict() {
		return guardianDistrict;
	}
	public void setGuardianDistrict(String guardianDistrict) {
		this.guardianDistrict = guardianDistrict;
	}
	public String getGuardianState() {
		return guardianState;
	}
	public void setGuardianState(String guardianState) {
		this.guardianState = guardianState;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Integer getChildAge() {
		return childAge;
	}
	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public Date getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
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
