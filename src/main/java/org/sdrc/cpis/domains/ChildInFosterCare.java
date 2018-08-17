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
@Table(name="child_record_in_foster_care")
public class ChildInFosterCare implements Serializable{
	
	private static final long serialVersionUID = -3164340957269259956L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="child_id",referencedColumnName = "child_id")
	private ChildDetails childRecord;
	
	@Column(name="cci_id")
	private Integer cciId; //Value Object
	
	@Column(name="cci_address")
	private String cciAddress;
	
	@Column(name="other_source_of_referral")
	private String otherSourceOfReferral;
	
	@Column(name="child_details_in_fosterCare")
	private String childDetailsInFosterCare;
	
	@Column(name="child_image")
	private String childImage;
	
	@Column(name="fotser_care_parent_image")
	private String fosterCareParentImage;
	
	@Column(name="biological_parent_image")
	private String biologicalParentImage;
	
	@Column(name="hsr_biological_family") //hsr stands for Home Study Report
	private String hsrBiologicalFamily;
	
	@Column(name="hsr_foster_family") //hsr stands for Home Study Report
	private String hsrFosterFamily;
	
	@Column(name="child_study_report")
	private String childStudyReport;
	
	@Column(name="record_of_each_visit")
	private String recordOfEachVisit;
	
	@Column(name="record_of_each_visit_file")
	private String recordOfEachVisitFile;
	
	@Column(name="observation")
	private String observation;
	
	@Column(name="extent_quality_compliance")
	private String extentQualityCompliance;
	
	@Column(name="developmental_milestones")
	private String developmentalMilestones;
	
	@Column(name="academic_progress")
	private String academicProgress;
	
	@Column(name="changes_in_family_environment")
	private String changesInFamilyEnvironment;
	
	@Column(name="reason_for_termination")
	private String reasonForTermination;
	
	@Column(name="date_for_termination")
	private Date dateForTermination;
	
	@Column(name="reason_for_extension")
	private String reasonForExtension;
	
	@Column(name="date_for_extension")
	private Date dateForExtension;
	
	@Column(name="child_handed_over_date")
	private Date childHandedOverDate;
	
	@Column(name="financial_assistance")
	private String financialAssistance;
	
	@Column(name="case_Worker_Name")
	private String caseWorkerName;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="cwc_address")
	private String cwcAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChildDetails getChildRecord() {
		return childRecord;
	}

	public void setChildRecord(ChildDetails childRecord) {
		this.childRecord = childRecord;
	}

	public Integer getCciId() {
		return cciId;
	}

	public void setCciId(Integer cciId) {
		this.cciId = cciId;
	}

	public String getCciAddress() {
		return cciAddress;
	}

	public void setCciAddress(String cciAddress) {
		this.cciAddress = cciAddress;
	}

	public String getOtherSourceOfReferral() {
		return otherSourceOfReferral;
	}

	public void setOtherSourceOfReferral(String otherSourceOfReferral) {
		this.otherSourceOfReferral = otherSourceOfReferral;
	}

	public String getChildDetailsInFosterCare() {
		return childDetailsInFosterCare;
	}

	public void setChildDetailsInFosterCare(String childDetailsInFosterCare) {
		this.childDetailsInFosterCare = childDetailsInFosterCare;
	}

	public String getChildImage() {
		return childImage;
	}

	public void setChildImage(String childImage) {
		this.childImage = childImage;
	}

	public String getFosterCareParentImage() {
		return fosterCareParentImage;
	}

	public void setFosterCareParentImage(String fosterCareParentImage) {
		this.fosterCareParentImage = fosterCareParentImage;
	}

	public String getBiologicalParentImage() {
		return biologicalParentImage;
	}

	public void setBiologicalParentImage(String biologicalParentImage) {
		this.biologicalParentImage = biologicalParentImage;
	}

	public String getHsrBiologicalFamily() {
		return hsrBiologicalFamily;
	}

	public void setHsrBiologicalFamily(String hsrBiologicalFamily) {
		this.hsrBiologicalFamily = hsrBiologicalFamily;
	}

	public String getHsrFosterFamily() {
		return hsrFosterFamily;
	}

	public void setHsrFosterFamily(String hsrFosterFamily) {
		this.hsrFosterFamily = hsrFosterFamily;
	}

	public String getChildStudyReport() {
		return childStudyReport;
	}

	public void setChildStudyReport(String childStudyReport) {
		this.childStudyReport = childStudyReport;
	}

	public String getRecordOfEachVisit() {
		return recordOfEachVisit;
	}

	public void setRecordOfEachVisit(String recordOfEachVisit) {
		this.recordOfEachVisit = recordOfEachVisit;
	}

	public String getRecordOfEachVisitFile() {
		return recordOfEachVisitFile;
	}

	public void setRecordOfEachVisitFile(String recordOfEachVisitFile) {
		this.recordOfEachVisitFile = recordOfEachVisitFile;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getExtentQualityCompliance() {
		return extentQualityCompliance;
	}

	public void setExtentQualityCompliance(String extentQualityCompliance) {
		this.extentQualityCompliance = extentQualityCompliance;
	}

	public String getDevelopmentalMilestones() {
		return developmentalMilestones;
	}

	public void setDevelopmentalMilestones(String developmentalMilestones) {
		this.developmentalMilestones = developmentalMilestones;
	}

	public String getAcademicProgress() {
		return academicProgress;
	}

	public void setAcademicProgress(String academicProgress) {
		this.academicProgress = academicProgress;
	}

	public String getChangesInFamilyEnvironment() {
		return changesInFamilyEnvironment;
	}

	public void setChangesInFamilyEnvironment(String changesInFamilyEnvironment) {
		this.changesInFamilyEnvironment = changesInFamilyEnvironment;
	}

	public String getReasonForTermination() {
		return reasonForTermination;
	}

	public void setReasonForTermination(String reasonForTermination) {
		this.reasonForTermination = reasonForTermination;
	}

	public Date getDateForTermination() {
		return dateForTermination;
	}

	public void setDateForTermination(Date dateForTermination) {
		this.dateForTermination = dateForTermination;
	}

	public String getReasonForExtension() {
		return reasonForExtension;
	}

	public void setReasonForExtension(String reasonForExtension) {
		this.reasonForExtension = reasonForExtension;
	}

	public Date getDateForExtension() {
		return dateForExtension;
	}

	public void setDateForExtension(Date dateForExtension) {
		this.dateForExtension = dateForExtension;
	}

	public Date getChildHandedOverDate() {
		return childHandedOverDate;
	}

	public void setChildHandedOverDate(Date childHandedOverDate) {
		this.childHandedOverDate = childHandedOverDate;
	}

	public String getFinancialAssistance() {
		return financialAssistance;
	}

	public void setFinancialAssistance(String financialAssistance) {
		this.financialAssistance = financialAssistance;
	}

	public String getCaseWorkerName() {
		return caseWorkerName;
	}

	public void setCaseWorkerName(String caseWorkerName) {
		this.caseWorkerName = caseWorkerName;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCwcAddress() {
		return cwcAddress;
	}

	public void setCwcAddress(String cwcAddress) {
		this.cwcAddress = cwcAddress;
	}
	
}