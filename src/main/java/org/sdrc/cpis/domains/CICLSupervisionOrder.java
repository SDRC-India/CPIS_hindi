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

/*FORM 3* 
 * 
 * @author Abhisheka Mishra*/

@Entity
@Table(name="CICL_supervision_order")
public class CICLSupervisionOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2228635002614946412L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName="child_id")
	private ChildDetails childId;
	
	@Column(name="childUnderCareOfWhom")
	private Integer childUnderCareOfWhom;
	
	@Column(name="supervisionAuthorityName")
	private String supervisionAuthorityName;
	
	@Column(name="supervisionAuthorityAddress")
	private String supervisionAuthorityAddress;
	
	@Column(name="childPlacedPeriod")
	private Integer childPlacedPeriod;
	
	@Column(name="dateOfOrder")
	private Date dateOfOrder;
	
	@Column(name="form_no")
	private Integer formNo;
	
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

	public Integer getChildUnderCareOfWhom() {
		return childUnderCareOfWhom;
	}

	public void setChildUnderCareOfWhom(Integer childUnderCareOfWhom) {
		this.childUnderCareOfWhom = childUnderCareOfWhom;
	}

	public String getSupervisionAuthorityName() {
		return supervisionAuthorityName;
	}

	public void setSupervisionAuthorityName(String supervisionAuthorityName) {
		this.supervisionAuthorityName = supervisionAuthorityName;
	}

	public String getSupervisionAuthorityAddress() {
		return supervisionAuthorityAddress;
	}

	public void setSupervisionAuthorityAddress(String supervisionAuthorityAddress) {
		this.supervisionAuthorityAddress = supervisionAuthorityAddress;
	}

	public Integer getChildPlacedPeriod() {
		return childPlacedPeriod;
	}

	public void setChildPlacedPeriod(Integer childPlacedPeriod) {
		this.childPlacedPeriod = childPlacedPeriod;
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
