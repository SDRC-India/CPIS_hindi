package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class IndividualCarePlanCModel {
	
	private Integer id;
	private String childId;
	private ValueObject orderType;
	private String placeOfTransfer;
	private String authorityName;
	private String institutionDetails;
	private String skillsAcquired;
	private String cat1rrpc;
	private String cat2rrpc;
	private String cat3rrpc;
	private String cat4rrpc;
	private String cat5rrpc;
	private String cat6rrpc;
	private String cat7rrpc;
	private String cat8rrpc;
	private String cat9rrpc;
	private String cat10rrpc;
	private ValueObject typeOfOrder;
	private Date dateOfOrder;
	private String requisitionForEscort;
	private ValueObject identificationProofType;
	private String otherIdentificationProof;
	private String identificationProofNumber;
	private String rehabilitationPlan;
	private Integer postReleaseFollowupBy;
	private String officerOrNgoName;
//	private String mouWithNgoPath;
	private String detailOfSponsors;
//	private String mouOfSponsorsPath;
//	private String medicalReportPath;
	private String otherInfo;
	private String mouOfSponsors;
	private String medicalReport;
	private String mouWithNgo;
	
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String userIp;
	private Integer programType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaceOfTransfer() {
		return placeOfTransfer;
	}
	public void setPlaceOfTransfer(String placeOfTransfer) {
		this.placeOfTransfer = placeOfTransfer;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getInstitutionDetails() {
		return institutionDetails;
	}
	public void setInstitutionDetails(String institutionDetails) {
		this.institutionDetails = institutionDetails;
	}
	public String getSkillsAcquired() {
		return skillsAcquired;
	}
	public void setSkillsAcquired(String skillsAcquired) {
		this.skillsAcquired = skillsAcquired;
	}
	public String getCat1rrpc() {
		return cat1rrpc;
	}
	public void setCat1rrpc(String cat1rrpc) {
		this.cat1rrpc = cat1rrpc;
	}
	public String getCat2rrpc() {
		return cat2rrpc;
	}
	public void setCat2rrpc(String cat2rrpc) {
		this.cat2rrpc = cat2rrpc;
	}
	public String getCat3rrpc() {
		return cat3rrpc;
	}
	public void setCat3rrpc(String cat3rrpc) {
		this.cat3rrpc = cat3rrpc;
	}
	public String getCat4rrpc() {
		return cat4rrpc;
	}
	public void setCat4rrpc(String cat4rrpc) {
		this.cat4rrpc = cat4rrpc;
	}
	public String getCat5rrpc() {
		return cat5rrpc;
	}
	public void setCat5rrpc(String cat5rrpc) {
		this.cat5rrpc = cat5rrpc;
	}
	public String getCat6rrpc() {
		return cat6rrpc;
	}
	public void setCat6rrpc(String cat6rrpc) {
		this.cat6rrpc = cat6rrpc;
	}
	public String getCat7rrpc() {
		return cat7rrpc;
	}
	public void setCat7rrpc(String cat7rrpc) {
		this.cat7rrpc = cat7rrpc;
	}
	public String getCat8rrpc() {
		return cat8rrpc;
	}
	public void setCat8rrpc(String cat8rrpc) {
		this.cat8rrpc = cat8rrpc;
	}
	public String getCat9rrpc() {
		return cat9rrpc;
	}
	public void setCat9rrpc(String cat9rrpc) {
		this.cat9rrpc = cat9rrpc;
	}
	public String getCat10rrpc() {
		return cat10rrpc;
	}
	public void setCat10rrpc(String cat10rrpc) {
		this.cat10rrpc = cat10rrpc;
	}
	public ValueObject getTypeOfOrder() {
		return typeOfOrder;
	}
	public void setTypeOfOrder(ValueObject typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public ValueObject getIdentificationProofType() {
		return identificationProofType;
	}
	public void setIdentificationProofType(ValueObject identificationProofType) {
		this.identificationProofType = identificationProofType;
	}
	public String getIdentificationProofNumber() {
		return identificationProofNumber;
	}
	public void setIdentificationProofNumber(String identificationProofNumber) {
		this.identificationProofNumber = identificationProofNumber;
	}
	public String getRehabilitationPlan() {
		return rehabilitationPlan;
	}
	public void setRehabilitationPlan(String rehabilitationPlan) {
		this.rehabilitationPlan = rehabilitationPlan;
	}
	public Integer getPostReleaseFollowupBy() {
		return postReleaseFollowupBy;
	}
	public void setPostReleaseFollowupBy(Integer postReleaseFollowupBy) {
		this.postReleaseFollowupBy = postReleaseFollowupBy;
	}
	public String getOfficerOrNgoName() {
		return officerOrNgoName;
	}
	public void setOfficerOrNgoName(String officerOrNgoName) {
		this.officerOrNgoName = officerOrNgoName;
	}
//	public String getMouWithNgoPath() {
//		return mouWithNgoPath;
//	}
//	public void setMouWithNgoPath(String mouWithNgoPath) {
//		this.mouWithNgoPath = mouWithNgoPath;
//	}
	public String getDetailOfSponsors() {
		return detailOfSponsors;
	}
	public void setDetailOfSponsors(String detailOfSponsors) {
		this.detailOfSponsors = detailOfSponsors;
	}
//	public String getMouOfSponsorsPath() {
//		return mouOfSponsorsPath;
//	}
//	public void setMouOfSponsorsPath(String mouOfSponsorsPath) {
//		this.mouOfSponsorsPath = mouOfSponsorsPath;
//	}
//	public String getMedicalReportPath() {
//		return medicalReportPath;
//	}
//	public void setMedicalReportPath(String medicalReportPath) {
//		this.medicalReportPath = medicalReportPath;
//	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public ValueObject getOrderType() {
		return orderType;
	}
	public void setOrderType(ValueObject orderType) {
		this.orderType = orderType;
	}
	public String getOtherIdentificationProof() {
		return otherIdentificationProof;
	}
	public void setOtherIdentificationProof(String otherIdentificationProof) {
		this.otherIdentificationProof = otherIdentificationProof;
	}
	public String getRequisitionForEscort() {
		return requisitionForEscort;
	}
	public void setRequisitionForEscort(String requisitionForEscort) {
		this.requisitionForEscort = requisitionForEscort;
	}
	public String getMouOfSponsors() {
		return mouOfSponsors;
	}
	public void setMouOfSponsors(String mouOfSponsors) {
		this.mouOfSponsors = mouOfSponsors;
	}
	public String getMedicalReport() {
		return medicalReport;
	}
	public void setMedicalReport(String medicalReport) {
		this.medicalReport = medicalReport;
	}
	public String getMouWithNgo() {
		return mouWithNgo;
	}
	public void setMouWithNgo(String mouWithNgo) {
		this.mouWithNgo = mouWithNgo;
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
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
}
