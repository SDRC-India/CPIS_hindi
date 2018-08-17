package org.sdrc.cpis.models;

import java.sql.Date;
import java.util.List;

public class ChildInFosterCareModel {
	
	private Integer id;
	private String childId;
	private CCIInfoMapModel cciObject; //Value Object
	private String cciAddress;
	private String otherSourceOfReferral;
	private String childDetailsInFosterCare;
	private String childImage;
	private String fosterCareParentImage;
	private String biologicalParentImage;
	private List<DetailsOfPlacementModel> detailsOfPlacementList;
	private String hsrBiologicalFamily;
	private String hsrFosterFamily;
	private String childStudyReport;
	private String recordOfEachVisit;
	private String recordOfEachVisitFile;
	private String observation;
	private String extentQualityCompliance;
	private String developmentalMilestones;
	private String academicProgress;
	private String changesInFamilyEnvironment;
	private String reasonForTermination;
	private Date dateForTermination;
	private String reasonForExtension;
	private Date dateForExtension;
	private Date childHandedOverDate;
	private String financialAssistance;
	private String caseWorkerName;
	private String createdBy;
	private Date createdDate;
	private String ip;
	
	private String caseNo;
	private String childName;
	private String childAge;
	private String childSex;
	private String cciName;
	private String cwcAddress;
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
	public CCIInfoMapModel getCciObject() {
		return cciObject;
	}
	public void setCciObject(CCIInfoMapModel cciObject) {
		this.cciObject = cciObject;
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
	public List<DetailsOfPlacementModel> getDetailsOfPlacementList() {
		return detailsOfPlacementList;
	}
	public void setDetailsOfPlacementList(
			List<DetailsOfPlacementModel> detailsOfPlacementList) {
		this.detailsOfPlacementList = detailsOfPlacementList;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildAge() {
		return childAge;
	}
	public void setChildAge(String childAge) {
		this.childAge = childAge;
	}
	public String getChildSex() {
		return childSex;
	}
	public void setChildSex(String childSex) {
		this.childSex = childSex;
	}
	public String getCciName() {
		return cciName;
	}
	public void setCciName(String cciName) {
		this.cciName = cciName;
	}
	public String getCwcAddress() {
		return cwcAddress;
	}
	public void setCwcAddress(String cwcAddress) {
		this.cwcAddress = cwcAddress;
	}
	public Integer getProgramType() {
		return programType;
	}
	public void setProgramType(Integer programType) {
		this.programType = programType;
	}
	
}
