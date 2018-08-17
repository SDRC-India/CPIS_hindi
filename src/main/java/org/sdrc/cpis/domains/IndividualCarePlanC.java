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
@Table(name="icp_pre_release_report")
public class IndividualCarePlanC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8672682930204027951L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="order_type")
	private Integer orderType;
	
	@Column(name="place_of_transfer")
	private String placeOfTransfer;
	
	@Column(name="authority_name")
	private String authorityName;
	
	@Column(name="institution_details")
	private String institutionDetails;
	
	@Column(name="skills_acquired")
	private String skillsAcquired;
	
	@Column(name="cat1_rrpc")
	private String cat1rrpc;
	
	@Column(name="cat2_rrpc")
	private String cat2rrpc;
	
	@Column(name="cat3_rrpc")
	private String cat3rrpc;
	
	@Column(name="cat4_rrpc")
	private String cat4rrpc;
	
	@Column(name="cat5_rrpc")
	private String cat5rrpc;
	
	@Column(name="cat6_rrpc")
	private String cat6rrpc;
	
	@Column(name="cat7_rrpc")
	private String cat7rrpc;
	
	@Column(name="cat8_rrpc")
	private String cat8rrpc;
	
	@Column(name="cat9_rrpc")
	private String cat9rrpc;
	
	@Column(name="cat10_rrpc")
	private String cat10rrpc;
	
	@Column(name="type_of_order")
	private Integer typeOfOrder;
	
	@Column(name="date_of_order")
	private Date dateOfOrder;
	
	@Column(name="requisition_for_escort")
	private String requisitionForEscort;
	
	@Column(name="identification_proof_type")
	private Integer identificationProofType;
	
	@Column(name="otherIdentificationProof")
	private String otherIdentificationProof;
	
	@Column(name="identification_proof_number")
	private String identificationProofNumber;
	
	@Column(name="rehabilitation_plan")
	private String rehabilitationPlan;
	
	@Column(name="post_release_followup_by")
	private Integer postReleaseFollowupBy;
	
	@Column(name="officer_ngo_name")
	private String officerOrNgoName;
	
	@Column(name="mou_with_ngo_path")
	private String mouWithNgoPath;
	
	@Column(name="detail_of_sponsors")
	private String detailOfSponsors;
	
	@Column(name="mou_of_sponsors_path")
	private String mouOfSponsorsPath;
	
	@Column(name="medical_report_path")
	private String medicalReportPath;
	
	@Column(name="other_info")
	private String otherInfo;

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

	public Integer getTypeOfOrder() {
		return typeOfOrder;
	}

	public void setTypeOfOrder(Integer typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	
	public String getRequisitionForEscort() {
		return requisitionForEscort;
	}

	public void setRequisitionForEscort(String requisitionForEscort) {
		this.requisitionForEscort = requisitionForEscort;
	}

	public Integer getIdentificationProofType() {
		return identificationProofType;
	}

	public void setIdentificationProofType(Integer identificationProofType) {
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

	public String getMouWithNgoPath() {
		return mouWithNgoPath;
	}

	public void setMouWithNgoPath(String mouWithNgoPath) {
		this.mouWithNgoPath = mouWithNgoPath;
	}

	public String getDetailOfSponsors() {
		return detailOfSponsors;
	}

	public void setDetailOfSponsors(String detailOfSponsors) {
		this.detailOfSponsors = detailOfSponsors;
	}

	public String getMouOfSponsorsPath() {
		return mouOfSponsorsPath;
	}

	public void setMouOfSponsorsPath(String mouOfSponsorsPath) {
		this.mouOfSponsorsPath = mouOfSponsorsPath;
	}

	public String getMedicalReportPath() {
		return medicalReportPath;
	}

	public void setMedicalReportPath(String medicalReportPath) {
		this.medicalReportPath = medicalReportPath;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOtherIdentificationProof() {
		return otherIdentificationProof;
	}

	public void setOtherIdentificationProof(String otherIdentificationProof) {
		this.otherIdentificationProof = otherIdentificationProof;
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
