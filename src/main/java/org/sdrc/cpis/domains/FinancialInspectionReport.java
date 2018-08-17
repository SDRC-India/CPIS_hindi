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
@Table(name="financial_inspection_Report_details")
public class FinancialInspectionReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -333092024045962768L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@Column(name="year_id")
	private Integer year_id;
	
	@Column(name="quarter_id")
	private Integer quarter_id;
	
	@Column(name="financial_inspection_path")
	private String financial_inspection_path;
	
	@Column(name="type")
	private String type;

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
	
	@Column(name="cci_id")
	private Integer cciId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear_id() {
		return year_id;
	}

	public void setYear_id(Integer year_id) {
		this.year_id = year_id;
	}

	public Integer getQuarter_id() {
		return quarter_id;
	}

	public void setQuarter_id(Integer quarter_id) {
		this.quarter_id = quarter_id;
	}

	public String getFinancial_inspection_path() {
		return financial_inspection_path;
	}

	public void setFinancial_inspection_path(String financial_inspection_path) {
		this.financial_inspection_path = financial_inspection_path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	
	public Integer getCciId() {
		return cciId;
	}

	public void setCciId(Integer cciId) {
		this.cciId = cciId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
