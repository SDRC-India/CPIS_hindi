package org.sdrc.cpis.domains;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="after_care_order")
public class AfterCarePlacementOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="completing_18_on")
	private Date completing18On;
	
	@Column(name="purpose_of_rehabilitation")
	private String purposeOfRehabilitation;
	
	@Column(name="organization_name")
	private String organizationName;
	
	@Column(name="amount_released")
	private Integer amountReleased;
	
	@Column(name="period")
	private Integer period;
	
	@Column(name="name_of_account_holder")
	private String nameOfAccountHolder;

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

	public Date getCompleting18On() {
		return completing18On;
	}

	public void setCompleting18On(Date completing18On) {
		this.completing18On = completing18On;
	}

	public String getPurposeOfRehabilitation() {
		return purposeOfRehabilitation;
	}

	public void setPurposeOfRehabilitation(String purposeOfRehabilitation) {
		this.purposeOfRehabilitation = purposeOfRehabilitation;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getAmountReleased() {
		return amountReleased;
	}

	public void setAmountReleased(Integer amountReleased) {
		this.amountReleased = amountReleased;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getNameOfAccountHolder() {
		return nameOfAccountHolder;
	}

	public void setNameOfAccountHolder(String nameOfAccountHolder) {
		this.nameOfAccountHolder = nameOfAccountHolder;
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
