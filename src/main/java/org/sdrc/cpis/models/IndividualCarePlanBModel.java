package org.sdrc.cpis.models;

import java.sql.Date;

import org.sdrc.cpis.util.ValueObject;

public class IndividualCarePlanBModel {
	
	private Integer id;
	private String childId;
	private ValueObject typeOfOfficerOrWorker;
	private String nameOfOfficerOrWorker;
	private String periodOfReport;
	private String admissionNum;
	private String placeOfInterview;
	private Date dateOfInterview;
	private String generalConductAndProgress;
	private String cat1Poc;
	private String cat2poc;
	private String cat3poc;
	private String cat4poc;
	private String cat5poc;
	private String cat6poc;
	private String cat7poc;
	private String cat8poc;
	private String cat9poc;
	private String cat10poc;
	private ValueObject proceedings;
	private String otherProceedings;
	private Date supervisionCompletionDate;
	private String resultOfSupervision;
	private ValueObject typeOfParent;
	private String nameOfParent;
	private String addressOfParent;
	private Date dateOfReport;
	private String childName;
	private ValueObject stayOfChild;
	private String cwcName;
	private String caseNum;
	
	private String cat1pi;
	private String cat2pi;
	private String cat3pi;
	private String cat4pi;
	private String cat5pi;
	private String cat6pi;
	private String cat7pi;
	private String cat8pi;
	private String cat9pi;
	private String cat10pi;
	
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
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public ValueObject getTypeOfOfficerOrWorker() {
		return typeOfOfficerOrWorker;
	}
	public void setTypeOfOfficerOrWorker(ValueObject typeOfOfficerOrWorker) {
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
	public ValueObject getProceedings() {
		return proceedings;
	}
	public void setProceedings(ValueObject proceedings) {
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
	public ValueObject getTypeOfParent() {
		return typeOfParent;
	}
	public void setTypeOfParent(ValueObject typeOfParent) {
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
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public ValueObject getStayOfChild() {
		return stayOfChild;
	}
	public void setStayOfChild(ValueObject stayOfChild) {
		this.stayOfChild = stayOfChild;
	}
	public String getCat1pi() {
		return cat1pi;
	}
	public void setCat1pi(String cat1pi) {
		this.cat1pi = cat1pi;
	}
	public String getCat2pi() {
		return cat2pi;
	}
	public void setCat2pi(String cat2pi) {
		this.cat2pi = cat2pi;
	}
	public String getCat3pi() {
		return cat3pi;
	}
	public void setCat3pi(String cat3pi) {
		this.cat3pi = cat3pi;
	}
	public String getCat4pi() {
		return cat4pi;
	}
	public void setCat4pi(String cat4pi) {
		this.cat4pi = cat4pi;
	}
	public String getCat5pi() {
		return cat5pi;
	}
	public void setCat5pi(String cat5pi) {
		this.cat5pi = cat5pi;
	}
	public String getCat6pi() {
		return cat6pi;
	}
	public void setCat6pi(String cat6pi) {
		this.cat6pi = cat6pi;
	}
	public String getCat7pi() {
		return cat7pi;
	}
	public void setCat7pi(String cat7pi) {
		this.cat7pi = cat7pi;
	}
	public String getCat8pi() {
		return cat8pi;
	}
	public void setCat8pi(String cat8pi) {
		this.cat8pi = cat8pi;
	}
	public String getCat9pi() {
		return cat9pi;
	}
	public void setCat9pi(String cat9pi) {
		this.cat9pi = cat9pi;
	}
	public String getCat10pi() {
		return cat10pi;
	}
	public void setCat10pi(String cat10pi) {
		this.cat10pi = cat10pi;
	}
	public String getCwcName() {
		return cwcName;
	}
	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
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
