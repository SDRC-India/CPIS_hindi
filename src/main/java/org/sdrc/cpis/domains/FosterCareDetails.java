package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ccts_foster_care_details")
public class FosterCareDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264039596783317882L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="parent_name_1")
	private String parentName1;
	
	@Column(name="parent_name_2")
	private String parentName2;
	
	@Column(name="foster_parent_name_1")
	private String fosterParentName1;
	
	@Column(name="foster_parent_name_2")
	private String fosterParentName2;
	
	@Column(name="foster_parent_address")
	private String fosterParentAddress;
	
	@Column(name="foster_parent_contact")
	private String fosterParentContact;
	
	@Column(name="duration_of_stay_at_foster_care")
	private Integer durationOfStayAtFosterCare;
	
	@Column(name="cci_name")
	private String cciName;
	
	@Column(name="cci_address")
	private String cciAddress;
	
	@Column(name="foster_care_worker_name")
	private String fosterCareWorkerName;
	
	@Column(name="foster_care_worker_address")
	private String fosterCareWorkerAddress;

	@Column(name="foster_type")
	private String fosterType;
	
	@Column(name="date_of_form_filled")
	private Date dateOfFormFilled;
	
	@Column(name="child_address")
	private String childAddress;

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

	public String getParentName1() {
		return parentName1;
	}

	public void setParentName1(String parentName1) {
		this.parentName1 = parentName1;
	}

	public String getParentName2() {
		return parentName2;
	}

	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}

	public String getFosterParentName1() {
		return fosterParentName1;
	}

	public void setFosterParentName1(String fosterParentName1) {
		this.fosterParentName1 = fosterParentName1;
	}

	public String getFosterParentName2() {
		return fosterParentName2;
	}

	public void setFosterParentName2(String fosterParentName2) {
		this.fosterParentName2 = fosterParentName2;
	}

	public String getFosterParentAddress() {
		return fosterParentAddress;
	}

	public void setFosterParentAddress(String fosterParentAddress) {
		this.fosterParentAddress = fosterParentAddress;
	}

	public String getFosterParentContact() {
		return fosterParentContact;
	}

	public void setFosterParentContact(String fosterParentContact) {
		this.fosterParentContact = fosterParentContact;
	}

	public Integer getDurationOfStayAtFosterCare() {
		return durationOfStayAtFosterCare;
	}

	public void setDurationOfStayAtFosterCare(Integer durationOfStayAtFosterCare) {
		this.durationOfStayAtFosterCare = durationOfStayAtFosterCare;
	}

	public String getCciName() {
		return cciName;
	}

	public void setCciName(String cciName) {
		this.cciName = cciName;
	}

	public String getCciAddress() {
		return cciAddress;
	}

	public void setCciAddress(String cciAddress) {
		this.cciAddress = cciAddress;
	}

	public String getFosterCareWorkerName() {
		return fosterCareWorkerName;
	}

	public void setFosterCareWorkerName(String fosterCareWorkerName) {
		this.fosterCareWorkerName = fosterCareWorkerName;
	}

	public String getFosterCareWorkerAddress() {
		return fosterCareWorkerAddress;
	}

	public void setFosterCareWorkerAddress(String fosterCareWorkerAddress) {
		this.fosterCareWorkerAddress = fosterCareWorkerAddress;
	}

	public String getFosterType() {
		return fosterType;
	}

	public void setFosterType(String fosterType) {
		this.fosterType = fosterType;
	}

	public Date getDateOfFormFilled() {
		return dateOfFormFilled;
	}

	public void setDateOfFormFilled(Date dateOfFormFilled) {
		this.dateOfFormFilled = dateOfFormFilled;
	}

	public String getChildAddress() {
		return childAddress;
	}

	public void setChildAddress(String childAddress) {
		this.childAddress = childAddress;
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
