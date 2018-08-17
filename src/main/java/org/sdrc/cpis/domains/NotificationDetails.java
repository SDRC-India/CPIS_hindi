package org.sdrc.cpis.domains;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notification_details")
public class NotificationDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private int notificationId;
	
	@Column(name="child_id")
	private String childId;
	
	@Column(name="district_id")
	private Integer districtId;
	
	@Column(name="division_id")
	private Integer divisionId;
	
	@Column(name="recipent_id")
	private Integer recipentId;
	
	@Column(name="date_of_action")
	private Date dateOfAction;
	
	@Column(name="notification_type")
	private String notificationType;
	
	@Column(name="notification_msg")
	private String notificationMsg;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="case_type")
	private String caseType;
	
	@Column(name="is_read")
	private Boolean isRead;
	
	@Column(name="is_active")
	private Boolean isActive;

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public Integer getRecipentId() {
		return recipentId;
	}

	public void setRecipentId(Integer recipentId) {
		this.recipentId = recipentId;
	}

	public Date getDateOfAction() {
		return dateOfAction;
	}

	public void setDateOfAction(Date dateOfAction) {
		this.dateOfAction = dateOfAction;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getNotificationMsg() {
		return notificationMsg;
	}

	public void setNotificationMsg(String notificationMsg) {
		this.notificationMsg = notificationMsg;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
