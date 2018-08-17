package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;


public class FamilyHistoryOfCrimeModel {
	
	private Integer id;
	private String childId;
	private ValueObject relationshipWithChild;
	private String relationshipWithChildOthers;
	private String natureOfCrime;
	private String legalStatusOfTheCase;
	private boolean arrestIfAny;
	private String periodOfConfinement;
	private String punishmentAwarded;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public ValueObject getRelationshipWithChild() {
		return relationshipWithChild;
	}
	public void setRelationshipWithChild(ValueObject relationshipWithChild) {
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
	
}
