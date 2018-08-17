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
@Table(name="health_status_of_child")
public class HealthStatusOfChild implements Serializable {

	private static final long serialVersionUID = 4173071861197280349L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="date_of_review")
	private Date dateOfReview;
	
	@Column(name="height")
	private String height;
	
	@Column(name="weight")
	private Double weight;
	
	@Column(name="nutritious_diet_given")
	private String nutritiousDietGiven;
	
	@Column(name="stress")
	private String stress;
	
	@Column(name="dental")
	private String dental;
	
	@Column(name="ent")
	private String ent;
	
	@Column(name="eye")
	private String eye;
	
	@Column(name="quarter_no")
	private Integer quarterNo;

	
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
