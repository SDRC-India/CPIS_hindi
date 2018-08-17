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
@Table(name="icp_progress_report")
public class IndividualCarePlanB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7826167598647955263L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childId;
	
	@Column(name="type_of_officer_worker")
	private Integer typeOfOfficerOrWorker;
	
	@Column(name="name_of_officer_worker")
	private String nameOfOfficerOrWorker;
	
	@Column(name="period_of_report")
	private String periodOfReport;
	
	@Column(name="admission_num")
	private String admissionNum;
	
	@Column(name="place_of_interview")
	private String placeOfInterview;
	
	@Column(name="date_of_interview")
	private Date dateOfInterview;
	
	@Column(name="general_conduct_and_progress")
	private String generalConductAndProgress;
	
	@Column(name="stay_of_child")
	private Integer stayOfChild;
	
	@Column(name="cat1_poc")
	private String cat1Poc;
	
	@Column(name="cat2_poc")
	private String cat2poc;
	
	@Column(name="cat3_poc")
	private String cat3poc;
	
	@Column(name="cat4_poc")
	private String cat4poc;
	
	@Column(name="cat5_poc")
	private String cat5poc;
	
	@Column(name="cat6_poc")
	private String cat6poc;
	
	@Column(name="cat7_poc")
	private String cat7poc;
	
	@Column(name="cat8_poc")
	private String cat8poc;
	
	@Column(name="cat9_poc")
	private String cat9poc;
	
	@Column(name="cat10_poc")
	private String cat10poc;
	
	@Column(name="proceedings")
	private Integer proceedings;
	
	@Column(name="other_proceedings")
	private String otherProceedings;
	
	@Column(name="supervision_completion_date")
	private Date supervisionCompletionDate;
	
	@Column(name="result_of_supervision")
	private String resultOfSupervision;
	
	@Column(name="type_of_parent")
	private Integer typeOfParent;
	
	@Column(name="name_of_parent")
	private String nameOfParent;
	
	@Column(name="address_of_parent")
	private String addressOfParent;
	
	@Column(name="date_of_report")
	private Date dateOfReport;

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

	public Integer getTypeOfOfficerOrWorker() {
		return typeOfOfficerOrWorker;
	}

	public void setTypeOfOfficerOrWorker(Integer typeOfOfficerOrWorker) {
		this.typeOfOfficerOrWorker = typeOfOfficerOrWorker;
	}

	public String getNameOfOfficerOrWorker() {
		return nameOfOfficerOrWorker;
	}

	public void setNameOfOfficerOrWorker(String nameOfOfficerOrWorker) {
		this.nameOfOfficerOrWorker = nameOfOfficerOrWorker;
	}

	public String getPeriodOfReport() {
		return periodOfReport;
	}

	public void setPeriodOfReport(String periodOfReport) {
		this.periodOfReport = periodOfReport;
	}

	public String getAdmissionNum() {
		return admissionNum;
	}

	public void setAdmissionNum(String admissionNum) {
		this.admissionNum = admissionNum;
	}

	public String getPlaceOfInterview() {
		return placeOfInterview;
	}

	public void setPlaceOfInterview(String placeOfInterview) {
		this.placeOfInterview = placeOfInterview;
	}

	public Date getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(Date dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	public String getGeneralConductAndProgress() {
		return generalConductAndProgress;
	}

	public void setGeneralConductAndProgress(String generalConductAndProgress) {
		this.generalConductAndProgress = generalConductAndProgress;
	}

	public String getCat1Poc() {
		return cat1Poc;
	}

	public void setCat1Poc(String cat1Poc) {
		this.cat1Poc = cat1Poc;
	}

	public String getCat2poc() {
		return cat2poc;
	}

	public void setCat2poc(String cat2poc) {
		this.cat2poc = cat2poc;
	}

	public String getCat3poc() {
		return cat3poc;
	}

	public void setCat3poc(String cat3poc) {
		this.cat3poc = cat3poc;
	}

	public String getCat4poc() {
		return cat4poc;
	}

	public void setCat4poc(String cat4poc) {
		this.cat4poc = cat4poc;
	}

	public String getCat5poc() {
		return cat5poc;
	}

	public void setCat5poc(String cat5poc) {
		this.cat5poc = cat5poc;
	}

	public String getCat6poc() {
		return cat6poc;
	}

	public void setCat6poc(String cat6poc) {
		this.cat6poc = cat6poc;
	}

	public String getCat7poc() {
		return cat7poc;
	}

	public void setCat7poc(String cat7poc) {
		this.cat7poc = cat7poc;
	}

	public String getCat8poc() {
		return cat8poc;
	}

	public void setCat8poc(String cat8poc) {
		this.cat8poc = cat8poc;
	}

	public String getCat9poc() {
		return cat9poc;
	}

	public void setCat9poc(String cat9poc) {
		this.cat9poc = cat9poc;
	}

	public String getCat10poc() {
		return cat10poc;
	}

	public void setCat10poc(String cat10poc) {
		this.cat10poc = cat10poc;
	}

	public Integer getProceedings() {
		return proceedings;
	}

	public void setProceedings(Integer proceedings) {
		this.proceedings = proceedings;
	}

	public String getOtherProceedings() {
		return otherProceedings;
	}

	public void setOtherProceedings(String otherProceedings) {
		this.otherProceedings = otherProceedings;
	}

	public Date getSupervisionCompletionDate() {
		return supervisionCompletionDate;
	}

	public void setSupervisionCompletionDate(Date supervisionCompletionDate) {
		this.supervisionCompletionDate = supervisionCompletionDate;
	}

	public String getResultOfSupervision() {
		return resultOfSupervision;
	}

	public void setResultOfSupervision(String resultOfSupervision) {
		this.resultOfSupervision = resultOfSupervision;
	}

	public Integer getTypeOfParent() {
		return typeOfParent;
	}

	public void setTypeOfParent(Integer typeOfParent) {
		this.typeOfParent = typeOfParent;
	}

	public String getNameOfParent() {
		return nameOfParent;
	}

	public void setNameOfParent(String nameOfParent) {
		this.nameOfParent = nameOfParent;
	}

	public String getAddressOfParent() {
		return addressOfParent;
	}

	public void setAddressOfParent(String addressOfParent) {
		this.addressOfParent = addressOfParent;
	}

	public Date getDateOfReport() {
		return dateOfReport;
	}

	public void setDateOfReport(Date dateOfReport) {
		this.dateOfReport = dateOfReport;
	}

	public Integer getStayOfChild() {
		return stayOfChild;
	}

	public void setStayOfChild(Integer stayOfChild) {
		this.stayOfChild = stayOfChild;
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
