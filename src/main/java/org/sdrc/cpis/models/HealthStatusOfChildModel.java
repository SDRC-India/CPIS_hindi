package org.sdrc.cpis.models;

import java.sql.Date;

public class HealthStatusOfChildModel {
	
	private Integer id;
	private String childId;
	private Date dateOfReview;
	private String height;
	private Double weight;
	private String nutritiousDietGiven;
	private String stress;
	private String dental;
	private String ent;
	private String eye;
	private Integer quarterNo;
	
	
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
	public Date getDateOfReview() {
		return dateOfReview;
	}
	public void setDateOfReview(Date dateOfReview) {
		this.dateOfReview = dateOfReview;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getNutritiousDietGiven() {
		return nutritiousDietGiven;
	}
	public void setNutritiousDietGiven(String nutritiousDietGiven) {
		this.nutritiousDietGiven = nutritiousDietGiven;
	}
	public String getStress() {
		return stress;
	}
	public void setStress(String stress) {
		this.stress = stress;
	}
	public String getDental() {
		return dental;
	}
	public void setDental(String dental) {
		this.dental = dental;
	}
	public String getEnt() {
		return ent;
	}
	public void setEnt(String ent) {
		this.ent = ent;
	}
	public String getEye() {
		return eye;
	}
	public void setEye(String eye) {
		this.eye = eye;
	}
	public Integer getQuarterNo() {
		return quarterNo;
	}
	public void setQuarterNo(Integer quarterNo) {
		this.quarterNo = quarterNo;
	}
	
}
