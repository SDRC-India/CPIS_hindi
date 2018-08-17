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
@Table(name="sponsorship_order")
public class SponsorshipOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="sponsorship_for")
	private Integer sponsorshipFor;
	
	@Column(name="other_sponsorship_for")
	private String otherSponsorshipFor;
	
	@Column(name="sponsorship_time")
	private Integer sponsorshipTime;
	
	@Column(name="sponsorship_amount")
	private Integer sponsorshipAmount;
	
	@Column(name="sponsorship_period")
	private Integer sponsorshipPeriod;
	
	@Column(name="sponsorship_type")
	private String sponsorshipType;
	
	@Column(name="days_or_months")
	private String daysOrMonths;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="operated_by")
	private String operatedBy;

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

	public Integer getSponsorshipFor() {
		return sponsorshipFor;
	}

	public void setSponsorshipFor(Integer sponsorshipFor) {
		this.sponsorshipFor = sponsorshipFor;
	}

	public Integer getSponsorshipTime() {
		return sponsorshipTime;
	}

	public void setSponsorshipTime(Integer sponsorshipTime) {
		this.sponsorshipTime = sponsorshipTime;
	}

	public Integer getSponsorshipAmount() {
		return sponsorshipAmount;
	}

	public void setSponsorshipAmount(Integer sponsorshipAmount) {
		this.sponsorshipAmount = sponsorshipAmount;
	}

	public Integer getSponsorshipPeriod() {
		return sponsorshipPeriod;
	}

	public void setSponsorshipPeriod(Integer sponsorshipPeriod) {
		this.sponsorshipPeriod = sponsorshipPeriod;
	}

	public String getDaysOrMonths() {
		return daysOrMonths;
	}

	public void setDaysOrMonths(String daysOrMonths) {
		this.daysOrMonths = daysOrMonths;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOperatedBy() {
		return operatedBy;
	}

	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}

	public String getSponsorshipType() {
		return sponsorshipType;
	}

	public void setSponsorshipType(String sponsorshipType) {
		this.sponsorshipType = sponsorshipType;
	}

	public String getOtherSponsorshipFor() {
		return otherSponsorshipFor;
	}

	public void setOtherSponsorshipFor(String otherSponsorshipFor) {
		this.otherSponsorshipFor = otherSponsorshipFor;
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
