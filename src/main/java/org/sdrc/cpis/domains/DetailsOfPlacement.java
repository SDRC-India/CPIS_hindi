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

/**
 * 
 * @author Naseem (naseem@sdrc.co.in)
 *
 */

@Entity
@Table(name="details_of_placement")
public class DetailsOfPlacement implements Serializable{

	private static final long serialVersionUID = 8820623309622461314L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id", referencedColumnName="child_id")
	private ChildDetails childRecord;
	
	@Column(name="individual_group")
	private String individualOrGroup;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="period_of_placement")
	private Double periodOfPlacement;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="ip")
	private String ip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChildDetails getChildRecord() {
		return childRecord;
	}

	public void setChildRecord(ChildDetails childRecord) {
		this.childRecord = childRecord;
	}

	public String getIndividualOrGroup() {
		return individualOrGroup;
	}

	public void setIndividualOrGroup(String individualOrGroup) {
		this.individualOrGroup = individualOrGroup;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPeriodOfPlacement() {
		return periodOfPlacement;
	}

	public void setPeriodOfPlacement(Double periodOfPlacement) {
		this.periodOfPlacement = periodOfPlacement;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
