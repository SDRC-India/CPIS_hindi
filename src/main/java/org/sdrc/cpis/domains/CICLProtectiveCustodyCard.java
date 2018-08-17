package org.sdrc.cpis.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CICL_protective_custody")
public class CICLProtectiveCustodyCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName="child_id")
	private ChildDetails childId;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="mother_name")
	private String motherName;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="address_of_parent")
	private String addressOfParent;
	
	@Column(name="date_of_reciving")
	private Date dateOfreciving;
	
	@Column(name="details_of_presentingperson")
	private String detailsOfPresentingPerson;
	
	@Column(name="inquiry_date")
	private Date inquiryDate;
	
	@Column(name="producing_dateof_child")
	private Date producingDateOfChild;
	
	@Column(name="next_dateof_hearing")
	private Date nextDateOfHearing;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAddressOfParent() {
		return addressOfParent;
	}

	public void setAddressOfParent(String addressOfParent) {
		this.addressOfParent = addressOfParent;
	}

	public Date getDateOfreciving() {
		return dateOfreciving;
	}

	public void setDateOfreciving(Date dateOfreciving) {
		this.dateOfreciving = dateOfreciving;
	}

	public String getDetailsOfPresentingPerson() {
		return detailsOfPresentingPerson;
	}

	public void setDetailsOfPresentingPerson(String detailsOfPresentingPerson) {
		this.detailsOfPresentingPerson = detailsOfPresentingPerson;
	}

	public Date getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public Date getProducingDateOfChild() {
		return producingDateOfChild;
	}

	public void setProducingDateOfChild(Date producingDateOfChild) {
		this.producingDateOfChild = producingDateOfChild;
	}

	public Date getNextDateOfHearing() {
		return nextDateOfHearing;
	}

	public void setNextDateOfHearing(Date nextDateOfHearing) {
		this.nextDateOfHearing = nextDateOfHearing;
	}

	
}
