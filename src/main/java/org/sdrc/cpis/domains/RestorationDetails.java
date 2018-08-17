package org.sdrc.cpis.domains;

import java.io.Serializable;
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
@Table(name="restoration_details")
public class RestorationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059153027284399998L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="child_order_placed_in")
	private Integer childOrderPlacedIn;
	
	@Column(name="section")
	private String section;
	
	@Column(name="time_period")
	private Integer timePeriod;
	
	@Column(name="placed_date")
	private Date placedDate;
	
	@Column(name="present_institution")
	private String presentInstitution;
	
	@Column(name="institution_district")
	private Integer institutionDistrict;
	
	@Column(name="authority_incharge")
	private String authorityIncharge;
	
	@Column(name="discharge_reason")
	private String dischargeReason;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="place_of_order")
	private String placeOfOrder;

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

	public Integer getChildOrderPlacedIn() {
		return childOrderPlacedIn;
	}

	public void setChildOrderPlacedIn(Integer childOrderPlacedIn) {
		this.childOrderPlacedIn = childOrderPlacedIn;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Integer getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Integer timePeriod) {
		this.timePeriod = timePeriod;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public String getPresentInstitution() {
		return presentInstitution;
	}

	public void setPresentInstitution(String presentInstitution) {
		this.presentInstitution = presentInstitution;
	}

	public Integer getInstitutionDistrict() {
		return institutionDistrict;
	}

	public void setInstitutionDistrict(Integer institutionDistrict) {
		this.institutionDistrict = institutionDistrict;
	}

	public String getAuthorityIncharge() {
		return authorityIncharge;
	}

	public void setAuthorityIncharge(String authorityIncharge) {
		this.authorityIncharge = authorityIncharge;
	}

	public String getDischargeReason() {
		return dischargeReason;
	}

	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPlaceOfOrder() {
		return placeOfOrder;
	}

	public void setPlaceOfOrder(String placeOfOrder) {
		this.placeOfOrder = placeOfOrder;
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
