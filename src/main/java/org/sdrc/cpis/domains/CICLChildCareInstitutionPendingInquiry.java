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

/*FORM 4* 
 * 
 * @author Abhisheka Mishra*/

@Entity
@Table(name="CICL_childcare_institution_pendinginquiry")
public class CICLChildCareInstitutionPendingInquiry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3841154551267361545L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName="child_id")
	private ChildDetails childId;
	
	@Column(name="date_of_placement")
	private Date dateOfPlacement;
	
	@Column(name="parent_name")
	private String parentName;
	
	@Column(name="address_of_child")
	private String addressOfChild;
	
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="next_dateof_hearing")
	private Date nextDateOfHearing;
	
	@Column(name="date_of_order")
	private Date dateOfOrder;
	
	@Column(name="form_no")
	private Integer formNo;
	
	@Column(name="cci_id")
	private Integer cciId;
	
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

	public Date getDateOfPlacement() {
		return dateOfPlacement;
	}

	public void setDateOfPlacement(Date dateOfPlacement) {
		this.dateOfPlacement = dateOfPlacement;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getAddressOfChild() {
		return addressOfChild;
	}

	public void setAddressOfChild(String addressOfChild) {
		this.addressOfChild = addressOfChild;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getNextDateOfHearing() {
		return nextDateOfHearing;
	}

	public void setNextDateOfHearing(Date nextDateOfHearing) {
		this.nextDateOfHearing = nextDateOfHearing;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public Integer getCciId() {
		return cciId;
	}

	public void setCciId(Integer cciId) {
		this.cciId = cciId;
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
