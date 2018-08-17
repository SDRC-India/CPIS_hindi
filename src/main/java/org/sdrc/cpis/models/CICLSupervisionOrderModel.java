package org.sdrc.cpis.models;


import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class CICLSupervisionOrderModel {

	private Integer id;
	private String childId;
	private ValueObject childUnderCareOfWhom;
	private String supervisionAuthorityName;
	private String supervisionAuthorityAddress;
	private Integer childPlacedPeriod;
	private Date dateOfOrder;
	private Integer formNo;
	private String firNumber;
	private String ddNumber;
	private String entryDate;
    private String policeStation;
    private String childName;
    private Integer programType;
	
	
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
	public ValueObject getChildUnderCareOfWhom() {
		return childUnderCareOfWhom;
	}
	public void setChildUnderCareOfWhom(ValueObject childUnderCareOfWhom) {
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
	public String getFirNumber() {
		return firNumber;
	}
	public void setFirNumber(String firNumber) {
		this.firNumber = firNumber;
	}
	public String getDdNumber() {
		return ddNumber;
	}
	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
}
