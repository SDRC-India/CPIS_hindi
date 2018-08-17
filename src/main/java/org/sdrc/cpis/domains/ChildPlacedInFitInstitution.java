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
@Table(name="ccts_child_placed_in_fit_institution")
public class ChildPlacedInFitInstitution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 882305731172612581L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	Integer id;
	
//	@ManyToOne
//	@JoinColumn(name="child_id")
//	String childId;
	
//	@Column(name="case_no", nullable = false)
//	String caseNo;
	
	@Column(name="date_child__placed_in_fit_institution")
	Date dateChildPlacedInFitInstitution;
	
	@Column(name="parent_name")
	String parentName;
	
	@Column(name="address_of_child")
	String addressOfChild;
	
//	@Column(name="childrenHome_SAA_Facility")
//	String childrenHomeSAAFacility;
	
	@ManyToOne
	@JoinColumn(name="cci")
	CCIDetails cci;
	
	@Column(name="period_for_which_sent_to_fit_institution")
	Integer periodForWhichSentToFitInstitution;
	
	@Column(name="Date_of_form_filled")
	Date dateOfFormFilled;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
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

	public ChildDetails getChildId() {
		return childId;
	}

	public void setChildId(ChildDetails childId) {
		this.childId = childId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public String getCaseNo() {
//		return caseNo;
//	}
//
//	public void setCaseNo(String caseNo) {
//		this.caseNo = caseNo;
//	}
		
	public String getAddressOfChild() {
		return addressOfChild;
	}

	public void setAddressOfChild(String addressOfChild) {
		this.addressOfChild = addressOfChild;
	}


	public Date getDateOfFormFilled() {
		return dateOfFormFilled;
	}

	public void setDateOfFormFilled(Date dateOfFormFilled) {
		this.dateOfFormFilled = dateOfFormFilled;
	}

	public Date getDateChildPlacedInFitInstitution() {
		return dateChildPlacedInFitInstitution;
	}

	public void setDateChildPlacedInFitInstitution(
			Date dateChildPlacedInFitInstitution) {
		this.dateChildPlacedInFitInstitution = dateChildPlacedInFitInstitution;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

//	public String getChildrenHomeSAAFacility() {
//		return childrenHomeSAAFacility;
//	}
//
//	public void setChildrenHomeSAAFacility(String childrenHomeSAAFacility) {
//		this.childrenHomeSAAFacility = childrenHomeSAAFacility;
//	}

	public Integer getPeriodForWhichSentToFitInstitution() {
		return periodForWhichSentToFitInstitution;
	}

	public void setPeriodForWhichSentToFitInstitution(
			Integer periodForWhichSentToFitInstitution) {
		this.periodForWhichSentToFitInstitution = periodForWhichSentToFitInstitution;
	}

	public CCIDetails getCci() {
		return cci;
	}

	public void setCci(CCIDetails cci) {
		this.cci = cci;
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
