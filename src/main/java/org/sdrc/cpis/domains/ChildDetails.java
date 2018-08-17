package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Child_Details")
public class ChildDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4693832064006690048L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@Column(name="child_id", unique=true, nullable = false)
	private String childId;
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="child_age")
	private Integer age;
	
	@Column(name="child_current_age")
	private Integer currentAge;
	
	@Column(name="child_sex", nullable = false)
	private Integer childSex;
	
	@ManyToOne
	@JoinColumn(name="child_district",referencedColumnName="Id")
	private AreaDetails childDistrict;
	
	@Column(name="record_created_on")
	private Date recordCreatedOn;
	
	@Column(name="child_photo")
	private String childPhoto;
	
	@Column(name="final_order_filled")
	private Integer finalOrderFilled;
	
	@Column(name="date_of_restoration")
	private Date dateOfRestoration;
	
	@Column(name="adhaar_no")
	private String adhaarNo;
	
	@ManyToOne
	@JoinColumn(name="CCI_Id")
	private CCIDetails cciDetails;

	@Column(name="program_type")
	private Integer programType;

	@OneToMany(mappedBy="childId")
	private List<FosterCareDetails> fosterCareDetails;
	
	@OneToMany(mappedBy="childId")
	private List<ChildPlacedInFitInstitution> childPlacedInFitInstitution;
	
	@OneToMany(mappedBy="childId")
	private List<ChildWithFitPerson> childWithFitPersons;
	
	@OneToMany(mappedBy="childId")
	private List<CaseMonitoring> caseMonitorings;
	
	@OneToMany(mappedBy="childId")
	private List<CaseSummaryByCWC> caseSummaryByCWCs;
	
	@OneToMany(mappedBy="childId")
	private List<SocialinvestigationReport> socialinvestigationReports;
	
	@OneToMany(mappedBy="childId")
	private List<ChildMonitoringReport> childMonitoringReports;
	
	@OneToMany(mappedBy="childId")
	private List<RehabilationEscortOrder> rehabilationEscortOrders;
	
//	@OneToOne(mappedBy="childId")
//	private IndividualCarePlanA individualCarePlansA;
	
	@OneToMany(mappedBy="childId")
	private List<IndividualCarePlanB> individualCarePlansB;
	
	@OneToMany(mappedBy="childId")
	private List<FamilyHistoryOfCrime> familyHistoryOfCrime;
	
	@OneToMany(mappedBy="childId")
	private List<HealthStatusOfChild> healthStatusOfChild;
	
	@OneToMany(mappedBy="childId")
	private List<ChildEmploymentDetails> childEmploymentDetail;
	
	@OneToMany(mappedBy="childId")
	private List<CICLSocialInvestigationReport> cICLSocialInvestigationReport;
	
//	@OneToOne(mappedBy="childId")
//	private IndividualCarePlanC individualCarePlansC;
//	
//	@OneToOne(mappedBy="childId")
//	private ChildRegistrationDetails childRegistrationDetails;
	
	@Column(name="case_num")
	private String caseNum;
	
	@Column(name="cwc_id")
	private Integer cwcId;
	
	@Column(name="sir_father_name")
	private String sirFatherName;
	
	@Column(name="sir_mother_name")
	private String sirMotherName;
	
	@Column(name="sir_child_address")
	private String sirChildAddress;
	
	@Column(name="sir_filled")
	private Integer sirFilled;
	
	@Column(name="icp_filled")
	private Integer icpFilled;
	
	@Column(name="rehabilitation_card_filled")
	private Integer rehabilitationCardFilled;
	
	@Column(name="case_history_filled")
	private Integer caseHistoryFilled;
	
	@Column(name="foster_care_filled")
	private Integer fosterCareFilled;
	
	@Column(name="sir_child_cast")
	private Integer sirChildCast;
	
	@Column(name="sir_child_religion")
	private Integer sirChildReligion;
	
	@Column(name="sir_other_child_religion")
	private String sirOtherChildReligion;
	
	
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

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getChildSex() {
		return childSex;
	}

	public void setChildSex(Integer childSex) {
		this.childSex = childSex;
	}

//	public Integer getChildDistrict() {
//		return childDistrict;
//	}
//
//	public void setChildDistrict(Integer childDistrict) {
//		this.childDistrict = childDistrict;
//	}

	
	public Date getRecordCreatedOn() {
		return recordCreatedOn;
	}

	public void setRecordCreatedOn(Date recordCreatedOn) {
		this.recordCreatedOn = recordCreatedOn;
	}

	public String getChildPhoto() {
		return childPhoto;
	}

	public void setChildPhoto(String childPhoto) {
		this.childPhoto = childPhoto;
	}

	public CCIDetails getCciDetails() {
		return cciDetails;
	}

	public void setCciDetails(CCIDetails cciDetails) {
		this.cciDetails = cciDetails;
	}

	public Integer getProgramType() {
		return programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	public ChildDetails(String childId) {
		super();
		this.childId = childId;
	}

	public ChildDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AreaDetails getChildDistrict() {
		return childDistrict;
	}

	public void setChildDistrict(AreaDetails childDistrict) {
		this.childDistrict = childDistrict;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public Integer getCwcId() {
		return cwcId;
	}

	public void setCwcId(Integer cwcId) {
		this.cwcId = cwcId;
	}

	public String getSirFatherName() {
		return sirFatherName;
	}

	public void setSirFatherName(String sirFatherName) {
		this.sirFatherName = sirFatherName;
	}

	public String getSirMotherName() {
		return sirMotherName;
	}

	public void setSirMotherName(String sirMotherName) {
		this.sirMotherName = sirMotherName;
	}

	public String getSirChildAddress() {
		return sirChildAddress;
	}

	public void setSirChildAddress(String sirChildAddress) {
		this.sirChildAddress = sirChildAddress;
	}

	public Integer getFinalOrderFilled() {
		return finalOrderFilled;
	}

	public void setFinalOrderFilled(Integer finalOrderFilled) {
		this.finalOrderFilled = finalOrderFilled;
	}

	public Integer getSirFilled() {
		return sirFilled;
	}

	public void setSirFilled(Integer sirFilled) {
		this.sirFilled = sirFilled;
	}

	public Integer getIcpFilled() {
		return icpFilled;
	}

	public void setIcpFilled(Integer icpFilled) {
		this.icpFilled = icpFilled;
	}

	public Integer getRehabilitationCardFilled() {
		return rehabilitationCardFilled;
	}

	public void setRehabilitationCardFilled(Integer rehabilitationCardFilled) {
		this.rehabilitationCardFilled = rehabilitationCardFilled;
	}

	public Integer getCaseHistoryFilled() {
		return caseHistoryFilled;
	}

	public void setCaseHistoryFilled(Integer caseHistoryFilled) {
		this.caseHistoryFilled = caseHistoryFilled;
	}

	public Integer getFosterCareFilled() {
		return fosterCareFilled;
	}

	public void setFosterCareFilled(Integer fosterCareFilled) {
		this.fosterCareFilled = fosterCareFilled;
	}

	public Integer getSirChildCast() {
		return sirChildCast;
	}

	public void setSirChildCast(Integer sirChildCast) {
		this.sirChildCast = sirChildCast;
	}

	public Integer getSirChildReligion() {
		return sirChildReligion;
	}

	public void setSirChildReligion(Integer sirChildReligion) {
		this.sirChildReligion = sirChildReligion;
	}

	public String getSirOtherChildReligion() {
		return sirOtherChildReligion;
	}

	public void setSirOtherChildReligion(String sirOtherChildReligion) {
		this.sirOtherChildReligion = sirOtherChildReligion;
	}

	public Integer getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(Integer currentAge) {
		this.currentAge = currentAge;
	}

	public Date getDateOfRestoration() {
		return dateOfRestoration;
	}

	public void setDateOfRestoration(Date dateOfRestoration) {
		this.dateOfRestoration = dateOfRestoration;
	}

	public List<FosterCareDetails> getFosterCareDetails() {
		return fosterCareDetails;
	}

	public void setFosterCareDetails(List<FosterCareDetails> fosterCareDetails) {
		this.fosterCareDetails = fosterCareDetails;
	}

	public List<ChildPlacedInFitInstitution> getChildPlacedInFitInstitution() {
		return childPlacedInFitInstitution;
	}

	public void setChildPlacedInFitInstitution(
			List<ChildPlacedInFitInstitution> childPlacedInFitInstitution) {
		this.childPlacedInFitInstitution = childPlacedInFitInstitution;
	}

	public List<ChildWithFitPerson> getChildWithFitPersons() {
		return childWithFitPersons;
	}

	public void setChildWithFitPersons(List<ChildWithFitPerson> childWithFitPersons) {
		this.childWithFitPersons = childWithFitPersons;
	}

	public List<CaseMonitoring> getCaseMonitorings() {
		return caseMonitorings;
	}

	public void setCaseMonitorings(List<CaseMonitoring> caseMonitorings) {
		this.caseMonitorings = caseMonitorings;
	}

	public List<CaseSummaryByCWC> getCaseSummaryByCWCs() {
		return caseSummaryByCWCs;
	}

	public void setCaseSummaryByCWCs(List<CaseSummaryByCWC> caseSummaryByCWCs) {
		this.caseSummaryByCWCs = caseSummaryByCWCs;
	}

	public List<SocialinvestigationReport> getSocialinvestigationReports() {
		return socialinvestigationReports;
	}

	public void setSocialinvestigationReports(
			List<SocialinvestigationReport> socialinvestigationReports) {
		this.socialinvestigationReports = socialinvestigationReports;
	}

	public List<ChildMonitoringReport> getChildMonitoringReports() {
		return childMonitoringReports;
	}

	public void setChildMonitoringReports(
			List<ChildMonitoringReport> childMonitoringReports) {
		this.childMonitoringReports = childMonitoringReports;
	}

	public List<RehabilationEscortOrder> getRehabilationEscortOrders() {
		return rehabilationEscortOrders;
	}

	public void setRehabilationEscortOrders(
			List<RehabilationEscortOrder> rehabilationEscortOrders) {
		this.rehabilationEscortOrders = rehabilationEscortOrders;
	}

	public List<IndividualCarePlanB> getIndividualCarePlansB() {
		return individualCarePlansB;
	}

	public void setIndividualCarePlansB(
			List<IndividualCarePlanB> individualCarePlansB) {
		this.individualCarePlansB = individualCarePlansB;
	}

	public List<FamilyHistoryOfCrime> getFamilyHistoryOfCrime() {
		return familyHistoryOfCrime;
	}

	public void setFamilyHistoryOfCrime(
			List<FamilyHistoryOfCrime> familyHistoryOfCrime) {
		this.familyHistoryOfCrime = familyHistoryOfCrime;
	}

	public List<HealthStatusOfChild> getHealthStatusOfChild() {
		return healthStatusOfChild;
	}

	public void setHealthStatusOfChild(List<HealthStatusOfChild> healthStatusOfChild) {
		this.healthStatusOfChild = healthStatusOfChild;
	}

	public List<ChildEmploymentDetails> getChildEmploymentDetail() {
		return childEmploymentDetail;
	}

	public void setChildEmploymentDetail(
			List<ChildEmploymentDetails> childEmploymentDetail) {
		this.childEmploymentDetail = childEmploymentDetail;
	}

	public List<CICLSocialInvestigationReport> getcICLSocialInvestigationReport() {
		return cICLSocialInvestigationReport;
	}

	public void setcICLSocialInvestigationReport(
			List<CICLSocialInvestigationReport> cICLSocialInvestigationReport) {
		this.cICLSocialInvestigationReport = cICLSocialInvestigationReport;
	}

	public String getAdhaarNo() {
		return adhaarNo;
	}

	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
	
		
}
