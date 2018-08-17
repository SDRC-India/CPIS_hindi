package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="child_employment_details")

public class ChildEmploymentDetails implements Serializable {
	
	private static final long serialVersionUID = -5292584072520391827L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id", referencedColumnName="child_id")
	private ChildDetails childId;
	
	@Column(name="type_of_employment")
	private Integer typeOfEmployment;
	
	@Column(name="type_of_employment_other")
	private String typeOfEmploymentOther;
	
	@Column(name="timing")
	private String timing;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="wages_earned")
	private Double wagesEarned;

	
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

	public Integer getTypeOfEmployment() {
		return typeOfEmployment;
	}

	public void setTypeOfEmployment(Integer typeOfEmployment) {
		this.typeOfEmployment = typeOfEmployment;
	}

	public String getTypeOfEmploymentOther() {
		return typeOfEmploymentOther;
	}

	public void setTypeOfEmploymentOther(String typeOfEmploymentOther) {
		this.typeOfEmploymentOther = typeOfEmploymentOther;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Double getWagesEarned() {
		return wagesEarned;
	}

	public void setWagesEarned(Double wagesEarned) {
		this.wagesEarned = wagesEarned;
	}
	
}
