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
@Table(name="family_history_of_crime")

public class FamilyHistoryOfCrime implements Serializable {

	private static final long serialVersionUID = 2173691128183892660L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id", referencedColumnName="child_id")
	private ChildDetails childId;
	
	@Column(name="relationship_with_child")
	private Integer relationshipWithChild;
	
	@Column(name="relationship_with_child_others")
	private String relationshipWithChildOthers;
	
	@Column(name="nature_of_crime")
	private String natureOfCrime;
	
	@Column(name="legal_status_of_the_case")
	private String legalStatusOfTheCase;
	
	@Column(name="arrest_if_any")
	private boolean arrestIfAny;
	
	@Column(name="period_of_confinement")
	private String periodOfConfinement;
	
	@Column(name="punishment_awarded")
	private String punishmentAwarded;

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

	public Integer getRelationshipWithChild() {
		return relationshipWithChild;
	}

	public void setRelationshipWithChild(Integer relationshipWithChild) {
		this.relationshipWithChild = relationshipWithChild;
	}

	public String getRelationshipWithChildOthers() {
		return relationshipWithChildOthers;
	}

	public void setRelationshipWithChildOthers(String relationshipWithChildOthers) {
		this.relationshipWithChildOthers = relationshipWithChildOthers;
	}

	public String getNatureOfCrime() {
		return natureOfCrime;
	}

	public void setNatureOfCrime(String natureOfCrime) {
		this.natureOfCrime = natureOfCrime;
	}

	public String getLegalStatusOfTheCase() {
		return legalStatusOfTheCase;
	}

	public void setLegalStatusOfTheCase(String legalStatusOfTheCase) {
		this.legalStatusOfTheCase = legalStatusOfTheCase;
	}

	public boolean isArrestIfAny() {
		return arrestIfAny;
	}

	public void setArrestIfAny(boolean arrestIfAny) {
		this.arrestIfAny = arrestIfAny;
	}

	public String getPeriodOfConfinement() {
		return periodOfConfinement;
	}

	public void setPeriodOfConfinement(String periodOfConfinement) {
		this.periodOfConfinement = periodOfConfinement;
	}

	public String getPunishmentAwarded() {
		return punishmentAwarded;
	}

	public void setPunishmentAwarded(String punishmentAwarded) {
		this.punishmentAwarded = punishmentAwarded;
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
