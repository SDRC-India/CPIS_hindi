package org.sdrc.cpis.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CICLSocialBackgroundReport;
import org.sdrc.cpis.domains.CaseMonitoring;
import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.ChildPlacedInFitInstitution;
import org.sdrc.cpis.domains.ChildRegistrationDetails;
import org.sdrc.cpis.domains.ChildWithFitPerson;
import org.sdrc.cpis.domains.FosterCareDetails;
import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.CaseMonitoringModel;
import org.sdrc.cpis.models.ChildDetailsModel;
import org.sdrc.cpis.models.ChildInFitInstitutionModel;
import org.sdrc.cpis.models.FitPersonDetailModel;
import org.sdrc.cpis.models.FosterCareDetailsModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.sdrc.cpis.repository.CCTSChildRegistrationRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLSocialBackgroundReportRepository;
import org.sdrc.cpis.repository.CaseMonitoringRepository;
import org.sdrc.cpis.repository.CciUserRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.ChildInFitInstitutionRepository;
import org.sdrc.cpis.repository.ChildWithFitPersonRepository;
import org.sdrc.cpis.repository.FosterCareDetailsRepository;
import org.sdrc.cpis.repository.NotificationRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.DomainToModelConverter;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InterimDecisionServiceImpl implements InterimDecisionService {

	@Autowired
	private CaseMonitoringRepository caseMonitoringRepository;

	@Autowired
	private FosterCareDetailsRepository fosterCareDetailsRepository;

	@Autowired
	private ChildDetailsRepository childDetailsRepository;

	@Autowired
	private ChildInFitInstitutionRepository childInFitInstitutionRepository;

	@Autowired
	private ChildWithFitPersonRepository childWithFitPersonRepository;

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Autowired
	private CCTSChildRegistrationRepository cctsChildRegistrationRepository;

	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;

	@Autowired
	private CCIInfoRepository cciInfoRepository;

	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;

	@Autowired
	private StateManager stateManager;

	@Autowired
	private CICLSocialBackgroundReportRepository ciclSocialBackgroundReportRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CciUserRepository cciUserRepository;

	@Autowired
	private CICLInterimService ciclInterimService;

	Map<Integer, ValueObject> typeMap;

	public Map<Integer, ValueObject> getTypeMap() {
		List<CCTSTypeDetails> typeDetails = cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map = new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj = new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(null != cctsTypeDetails.getTypeDetailsNameHindi() ? cctsTypeDetails.getTypeDetailsNameHindi() : null);
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}

	@Transactional
	@Override
	public void saveChildInterimData(CaseMonitoringModel caseMonitoringModel) throws Exception {

		String medicalReportsRelied = caseMonitoringModel.getMedicalReportsRelied() == null ? null
				: exportPDFServiceImpl.getFileName(caseMonitoringModel.getMedicalReportsRelied(),
						"MedicalReportsRelied",
						applicationMessageSource.getMessage("store.CaseMonitoring", null, null, null));
		String determineChildAge = caseMonitoringModel.getDetermineChildAge() == null ? null
				: exportPDFServiceImpl.getFileName(caseMonitoringModel.getDetermineChildAge(), "DetermineChildAge",
						applicationMessageSource.getMessage("store.CaseMonitoring", null, null, null));
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		CaseMonitoring caseMonitoring = new CaseMonitoring();

		if (caseMonitoringModel.getChildId() != null) {
			caseMonitoring.setActualDateOfAgeDetermination(null == caseMonitoringModel.getActualDateOfAgeDetermination()
					? null : caseMonitoringModel.getActualDateOfAgeDetermination());
			caseMonitoring.setActualDateOfFinalOrder(null == caseMonitoringModel.getActualDateOfFinalOrder() ? null
					: caseMonitoringModel.getActualDateOfFinalOrder());
			caseMonitoring
					.setActualDateOfIndividualCarePlan(null == caseMonitoringModel.getActualDateOfIndividualCarePlan()
							? null : caseMonitoringModel.getActualDateOfIndividualCarePlan());
			caseMonitoring.setActualDateOfPostDispositionalReview(
					null == caseMonitoringModel.getActualDateOfPostDispositionalReview() ? null
							: caseMonitoringModel.getActualDateOfPostDispositionalReview());
			caseMonitoring.setActualDateOfSocialInvestigationReport(
					null == caseMonitoringModel.getActualDateOfSocialInvestigationReport() ? null
							: caseMonitoringModel.getActualDateOfSocialInvestigationReport());
			caseMonitoring.setActualDateOfStatementOfChild(null == caseMonitoringModel.getActualDateOfStatementOfChild()
					? null : caseMonitoringModel.getActualDateOfStatementOfChild());
			caseMonitoring.setActualDateOfSubmissionReportOnProvisions(
					null == caseMonitoringModel.getActualDateOfSubmissionReportOnProvisions() ? null
							: caseMonitoringModel.getActualDateOfSubmissionReportOnProvisions());
			caseMonitoring
					.setAgeDeterminatorCommitteeName(null == caseMonitoringModel.getAgeDeterminationCommitteeName()
							? null : caseMonitoringModel.getAgeDeterminationCommitteeName());
			caseMonitoring
					.setCaseName(null == caseMonitoringModel.getCaseName() ? null : caseMonitoringModel.getCaseName());
			caseMonitoring.setChildId(childDetailsRepository.findChildById(caseMonitoringModel.getChildId()));
			caseMonitoring.setChildPermanentAddress(null == caseMonitoringModel.getChildPermanentAddress() ? null
					: caseMonitoringModel.getChildPermanentAddress());
			caseMonitoring.setChildPresentAddress(null == caseMonitoringModel.getChildPresentAddress() ? null
					: caseMonitoringModel.getChildPresentAddress());
			caseMonitoring.setChildProducedBeforeCommitteeDate(
					null == caseMonitoringModel.getChildProducedBeforeCommitteeDate() ? null
							: caseMonitoringModel.getChildProducedBeforeCommitteeDate());
			caseMonitoring.setChildProducedBeforeCommitteeTime(
					null == caseMonitoringModel.getChildProducedBeforeCommitteeTime() ? null
							: caseMonitoringModel.getChildProducedBeforeCommitteeTime());
			caseMonitoring.setChildrenHomeName(null == caseMonitoringModel.getChildrenHomeName() ? null
					: caseMonitoringModel.getChildrenHomeName());
			caseMonitoring.setDateChildSentToSupervisionInstitution(
					null == caseMonitoringModel.getDateChildSentToSupervisionInstitution() ? null
							: caseMonitoringModel.getDateChildSentToSupervisionInstitution());
			caseMonitoring.setDateOfAgeDetermination(null == caseMonitoringModel.getDateOfAgeDetermination() ? null
					: caseMonitoringModel.getDateOfAgeDetermination());
			caseMonitoring.setDateOfMedicalExamination(null == caseMonitoringModel.getDateOfMedicalExamination() ? null
					: caseMonitoringModel.getDateOfMedicalExamination());
			caseMonitoring.setDateTillChildSentToSupervisionInstitution(
					null == caseMonitoringModel.getDateTillChildSentToSupervisionInstitution() ? null
							: caseMonitoringModel.getDateTillChildSentToSupervisionInstitution());
			caseMonitoring.setEvidenceDocuments(determineChildAge);
			caseMonitoring.setEvidenceMedicalReports(medicalReportsRelied);
			caseMonitoring.setFirGdDdNo(
					null == caseMonitoringModel.getFirGdDdNo() ? null : caseMonitoringModel.getFirGdDdNo());
			// caseMonitoring.setId();
			caseMonitoring.setIoName(null == caseMonitoringModel.getIoName() ? null : caseMonitoringModel.getIoName());
			caseMonitoring.setNameOfDeterminator(null == caseMonitoringModel.getNameOfDeterminator() ? null
					: caseMonitoringModel.getNameOfDeterminator());
			caseMonitoring.setProbationOfficerName(null == caseMonitoringModel.getProbationOfficerName() ? null
					: caseMonitoringModel.getProbationOfficerName());
			caseMonitoring.setPsName(null == caseMonitoringModel.getPsName() ? null : caseMonitoringModel.getPsName());
			caseMonitoring
					.setScheduledDateOfAgeDetermination(null == caseMonitoringModel.getScheduledDateOfAgeDetermination()
							? null : caseMonitoringModel.getScheduledDateOfAgeDetermination());
			caseMonitoring.setScheduledDateOfFinalOrder(null == caseMonitoringModel.getScheduledDateOfFinalOrder()
					? null : caseMonitoringModel.getScheduledDateOfFinalOrder());
			caseMonitoring.setScheduledDateOfIndividualCarePlan(
					null == caseMonitoringModel.getScheduledDateOfIndividualCarePlan() ? null
							: caseMonitoringModel.getScheduledDateOfIndividualCarePlan());
			caseMonitoring.setScheduledDateOfPostDispositionalReview(
					null == caseMonitoringModel.getScheduledDateOfPostDispositionalReview() ? null
							: caseMonitoringModel.getScheduledDateOfPostDispositionalReview());
			caseMonitoring.setScheduledDateOfSocialInvestigationReport(
					null == caseMonitoringModel.getScheduledDateOfSocialInvestigationReport() ? null
							: caseMonitoringModel.getScheduledDateOfSocialInvestigationReport());
			caseMonitoring
					.setScheduledDateOfStatementOfChild(null == caseMonitoringModel.getScheduledDateOfStatementOfChild()
							? null : caseMonitoringModel.getScheduledDateOfStatementOfChild());
			caseMonitoring.setScheduledDateOfSubmissionReportOnProvisions(
					null == caseMonitoringModel.getScheduledDateOfSubmissionReportOnProvisions() ? null
							: caseMonitoringModel.getScheduledDateOfSubmissionReportOnProvisions());
			caseMonitoring.setSectionsChildBooked(null == caseMonitoringModel.getSectionsChildBooked() ? null
					: caseMonitoringModel.getSectionsChildBooked());
			caseMonitoring.setSupervisionInstitutionName(null == caseMonitoringModel.getSupervisionInstitutionName()
					? null : caseMonitoringModel.getSupervisionInstitutionName());
			caseMonitoring.setTimeTakenForAgeDetermination(null == caseMonitoringModel.getTimeTakenForAgeDetermination()
					? null : Double.valueOf(caseMonitoringModel.getTimeTakenForAgeDetermination()));
			caseMonitoring.setPgContactNo(caseMonitoringModel.getPgContactNo());
			caseMonitoring.setPgName(caseMonitoringModel.getPgName());
			caseMonitoring.setAgeOnDateOfOffence(caseMonitoringModel.getAgeondate());
			caseMonitoring.setDate(caseMonitoringModel.getDate());
			caseMonitoring.setCreatedBy(userDetailModel.getUserName() == null ? null : userDetailModel.getUserName());
			caseMonitoring.setCreatedDate(new java.sql.Date(new Date().getTime()));
			caseMonitoring.setUserIp(userDetailModel.getUserIp() == null ? null : userDetailModel.getUserIp());

			caseMonitoringRepository.save(caseMonitoring);
		}

	}

	@Override
	public void saveFosterCareDetails(FosterCareDetailsModel fosterCareDetailsModel) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		FosterCareDetails fosterCareDetails = new FosterCareDetails();
		ChildDetails childDetails = childDetailsRepository.findChildById(fosterCareDetailsModel.getChildId());
		fosterCareDetails.setChildId(childDetails);
		// fosterCareDetails.setDateOfFormFilled( new
		// java.sql.Date(Calendar.getInstance().YEAR,
		// Calendar.getInstance().MONTH, Calendar.getInstance().DATE));
		fosterCareDetails.setDateOfFormFilled(fosterCareDetailsModel.getDateOfFormFilled());
		// Foster Care For Family
		fosterCareDetails.setChildAddress(
				null == fosterCareDetailsModel.getChildAddress() ? null : fosterCareDetailsModel.getChildAddress());
		fosterCareDetails.setParentName1(
				null == fosterCareDetailsModel.getParentName1() ? null : fosterCareDetailsModel.getParentName1());
		fosterCareDetails.setParentName2(
				null == fosterCareDetailsModel.getParentName2() ? null : fosterCareDetailsModel.getParentName2());
		fosterCareDetails.setFosterParentName1(null == fosterCareDetailsModel.getFosterParentName1() ? null
				: fosterCareDetailsModel.getFosterParentName1());
		fosterCareDetails.setFosterParentName2(null == fosterCareDetailsModel.getFosterParentName2() ? null
				: fosterCareDetailsModel.getFosterParentName2());
		fosterCareDetails.setFosterParentAddress(null == fosterCareDetailsModel.getFosterParentAddress() ? null
				: fosterCareDetailsModel.getFosterParentAddress());
		fosterCareDetails.setFosterParentContact(null == fosterCareDetailsModel.getFosterParentContact() ? null
				: fosterCareDetailsModel.getFosterParentContact());

		// Group Foster Care
		fosterCareDetails
				.setCciName(null == fosterCareDetailsModel.getCciName() ? null : fosterCareDetailsModel.getCciName());
		fosterCareDetails.setCciAddress(
				null == fosterCareDetailsModel.getCciAddress() ? null : fosterCareDetailsModel.getCciAddress());
		fosterCareDetails.setDurationOfStayAtFosterCare(null == fosterCareDetailsModel.getDurationOfStayAtFosterCare()
				? null : fosterCareDetailsModel.getDurationOfStayAtFosterCare());
		fosterCareDetails.setFosterCareWorkerName(null == fosterCareDetailsModel.getFosterCareWorkerName() ? null
				: fosterCareDetailsModel.getFosterCareWorkerName());
		fosterCareDetails.setFosterCareWorkerAddress(null == fosterCareDetailsModel.getFosterCareWorkerAddress() ? null
				: fosterCareDetailsModel.getFosterCareWorkerAddress());

		fosterCareDetails.setChildId(childDetails);

		fosterCareDetails.setFosterType(
				null == fosterCareDetailsModel.getFosterType() ? null : fosterCareDetailsModel.getFosterType());
		fosterCareDetails.setUserIp(userDetailModel.getUserIp());
		fosterCareDetails.setCreatedBy(userDetailModel.getUserName());
		fosterCareDetails.setCreatedDate(new java.sql.Date(new Date().getTime()));
		fosterCareDetailsRepository.save(fosterCareDetails);
		
		/*Sending notifications to district level authorities*/
		
		List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(childDetails.getChildDistrict().getAreaId());
		List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
		for (UserDetails userDetails : userList) {
			if(userDetails.getDesignation().getDesignationId()==5 || userDetails.getDesignation().getDesignationId()==6) {

			NotificationDetails notificationDetails = new NotificationDetails();
			notificationDetails.setChildId(childDetails.getChildId());
			notificationDetails.setDistrictId(childDetails.getChildDistrict().getAreaId());
			notificationDetails.setDivisionId(areaRepository.fetchAreaById(childDetails.getChildDistrict().getParentArea().getAreaId()).getAreaId());
			notificationDetails.setRecipentId(userDetails.getUserId());
			notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
			notificationDetails.setNotificationType("fostercare");
			notificationDetails.setNotificationMsg("A new child has been moved to foster care.");
			notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			notificationDetails.setCaseType("CNCP");
			notificationDetails.setIsActive(true);
			notificationDetails.setIsRead(false);
			
			notifications.add(notificationDetails);
			}
		}
		notificationRepository.save(notifications);
		
		/*Notification part over*/

	}

	@Override
	public ChildDetailsModel fetchChildByChildId(String childId) {
		typeMap = getTypeMap();
		ChildDetails childDetails = childDetailsRepository.findChildById(childId);
		ChildRegistrationDetails child = new ChildRegistrationDetails();
		CICLSocialBackgroundReport ciclChild = new CICLSocialBackgroundReport();
		Date dateOfProduction = null;
		Time timeOfProduction = null;
		if (childDetails.getProgramType() == 0) {
			child = cctsChildRegistrationRepository.findByChildIdChildId(childId);
			if (child != null) {
				dateOfProduction = child.getDateOfProduction() == null ? null : child.getDateOfProduction();
				timeOfProduction = child.getTimeOfProduction() == null ? null : child.getTimeOfProduction();
			}
		} else {
			ciclChild = ciclSocialBackgroundReportRepository.findByChildIdChildId(childId);
			if (ciclChild != null) {
				dateOfProduction = ciclChild.getEntryDate() == null ? null : ciclChild.getEntryDate();
				timeOfProduction = ciclChild.getEntryTime() == null ? null : ciclChild.getEntryTime();
			}
		}
		ChildDetailsModel childDetailsModel = new ChildDetailsModel();
		childDetailsModel.setChildId(null == childDetails.getChildId() ? null : childDetails.getChildId());
		childDetailsModel.setChildName(null == childDetails.getChildName() ? null : childDetails.getChildName());
		childDetailsModel.setCaseNum(null == childDetails.getCaseNum() ? null : childDetails.getCaseNum());
		childDetailsModel.setChildSex(null == childDetails.getChildSex() ? null : childDetails.getChildSex());
		childDetailsModel.setChildDistrict(
				null == childDetails.getChildDistrict() ? null : childDetails.getChildDistrict().getAreaId());
		childDetailsModel.setProgramType(null == childDetails.getProgramType() ? null : childDetails.getProgramType());
		childDetailsModel.setAge(
				null == childDetails.getAge() ? null : Integer.parseInt(typeMap.get(childDetails.getAge()).getName()));
		childDetailsModel.setDistrict(null == childDetails.getChildDistrict() ? null
				: DomainToModelConverter.getDistrict(childDetails.getChildDistrict()));
		childDetailsModel.setCaseNum(null == childDetails.getCaseNum() ? null : childDetails.getCaseNum());
		childDetailsModel.setCwc(null == childDetails.getCwcId() ? null
				: DomainToModelConverter.getCWC(userDetailRepository.getUserById(childDetails.getCwcId())));
		childDetailsModel.setPersonWhoProduceChild(
				child.getPersonWhoProducedName() == null ? null : child.getPersonWhoProducedName());
		Calendar cal = Calendar.getInstance();
		cal.setTime(childDetails.getRecordCreatedOn() == null ? null : childDetails.getRecordCreatedOn());
		int year = cal.get(Calendar.YEAR);
		childDetailsModel.setYear(year);
		childDetailsModel.setRecordCreatedOn(
				null == childDetails.getRecordCreatedOn() ? null : childDetails.getRecordCreatedOn());
		childDetailsModel.setDateOfFirstProduction((java.sql.Date) dateOfProduction);
		childDetailsModel.setTimeOfFirstProduction(timeOfProduction.toString());
		childDetailsModel
				.setDateOfCaseRegistered(null == child.getDateOfProduction() ? null : child.getDateOfProduction());
		childDetailsModel.setSirAddress(childDetails.getSirChildAddress());
		childDetailsModel.setSirFatherName(childDetails.getSirFatherName());
		childDetailsModel.setSirMotherName(childDetails.getSirMotherName());
		childDetailsModel.setFirNumber(ciclChild.getFirNumber() == null ? null : ciclChild.getFirNumber());
		childDetailsModel.setPoliceStation(ciclChild.getPoliceStation() == null ? null : ciclChild.getPoliceStation());
		childDetailsModel.setGdNumber(ciclChild.getGdNumber() == null ? null : ciclChild.getGdNumber());
		childDetailsModel.setDdNumber(ciclChild.getDdNumber() == null ? null : ciclChild.getDdNumber());
		childDetailsModel.setSections(ciclChild.getSections() == null ? null : ciclChild.getSections());
		childDetailsModel.setFinalOrderFilled(childDetails.getFinalOrderFilled());
		childDetailsModel
				.setSexObject(childDetails.getChildSex() == null ? null : typeMap.get(childDetails.getChildSex()));
		childDetailsModel.setSirFilled(childDetails.getSirFilled() == null ? null : childDetails.getSirFilled());
		childDetailsModel.setIcpFilled(childDetails.getIcpFilled() == null ? null : childDetails.getIcpFilled());
		childDetailsModel.setRehabilitationCardFilled(
				childDetails.getRehabilitationCardFilled() == null ? null : childDetails.getRehabilitationCardFilled());
		childDetailsModel.setCaseHistoryFilled(
				childDetails.getCaseHistoryFilled() == null ? null : childDetails.getCaseHistoryFilled());
		childDetailsModel.setFosterCareFilled(
				childDetails.getFosterCareFilled() == null ? null : childDetails.getFosterCareFilled());
		childDetailsModel.setSirChildCast(
				childDetails.getSirChildCast() == null ? null : typeMap.get(childDetails.getSirChildCast()));
		childDetailsModel.setSirChildReligion(
				childDetails.getSirChildReligion() == null ? null : typeMap.get(childDetails.getSirChildReligion()));
		childDetailsModel.setSirOtherChildReligion(
				childDetails.getSirOtherChildReligion() == null ? null : childDetails.getSirOtherChildReligion());
		childDetailsModel
				.setCciId(null == childDetails.getCciDetails() ? null : childDetails.getCciDetails().getCciId());
		childDetailsModel.setAdhaarNo(childDetails.getAdhaarNo());
		childDetailsModel.setDateOfRestoration(childDetails.getDateOfRestoration());

		return childDetailsModel;
	}

	@Transactional
	@Override
	public void saveChildInFitInstituion(ChildInFitInstitutionModel childInFitInstitutionModel) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ChildPlacedInFitInstitution childPlacedInFitInstitution = new ChildPlacedInFitInstitution();
		CCIDetails cciDetails = new CCIDetails();
		childPlacedInFitInstitution.setAddressOfChild(null == childInFitInstitutionModel.getAddressOfChild() ? null
				: childInFitInstitutionModel.getAddressOfChild());
		// childPlacedInFitInstitution.setCaseNo(childInFitInstitutionModel.getCaseNo());
		childPlacedInFitInstitution
				.setChildId(childDetailsRepository.findChildById(childInFitInstitutionModel.getChildId()));
		// childPlacedInFitInstitution.setChildrenHomeSAAFacility(null==childInFitInstitutionModel.getChildrenHomeSAAFacility()
		// ? null : childInFitInstitutionModel.getChildrenHomeSAAFacility());
		childPlacedInFitInstitution.setDateChildPlacedInFitInstitution(
				null == childInFitInstitutionModel.getDateChildPlacedInFitInstitution() ? null
						: childInFitInstitutionModel.getDateChildPlacedInFitInstitution());
		childPlacedInFitInstitution.setDateOfFormFilled(null == childInFitInstitutionModel.getDateOfFormFilled() ? null
				: childInFitInstitutionModel.getDateOfFormFilled());
		childPlacedInFitInstitution.setParentName(
				null == childInFitInstitutionModel.getParentName() ? null : childInFitInstitutionModel.getParentName());
		childPlacedInFitInstitution.setPeriodForWhichSentToFitInstitution(
				null == childInFitInstitutionModel.getPeriodForWhichSentToFitInstitution() ? null
						: childInFitInstitutionModel.getPeriodForWhichSentToFitInstitution());
		childPlacedInFitInstitution.setCci(cciInfoRepository.getCciById(childInFitInstitutionModel.getCci().getId()));
		childPlacedInFitInstitution.setUserIp(userDetailModel.getUserIp());
		childPlacedInFitInstitution.setCreatedBy(userDetailModel.getUserName());
		childPlacedInFitInstitution.setCreatedDate(new java.sql.Date(new Date().getTime()));
		ChildDetails childDetails = childDetailsRepository.findChildById(childInFitInstitutionModel.getChildId());

		cciDetails.setCciId(childInFitInstitutionModel.getCci().getId());
		childDetails.setCciDetails(cciDetails);
		childDetailsRepository.save(childDetails);
		childInFitInstitutionRepository.save(childPlacedInFitInstitution);
		
		/*Sending notifications to district level authorities*/
		List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
		List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(childDetails.getChildDistrict().getAreaId());
		CciUserMapping cciUserMapping = cciUserRepository.findByCciId(childInFitInstitutionModel.getCci().getId());
		Integer divisionId=areaRepository.fetchAreaById(childDetails.getChildDistrict().getParentArea().getAreaId()).getAreaId();
		if(cciUserMapping!= null) {
			NotificationDetails notificationDetails = new NotificationDetails();
			notificationDetails.setChildId(childDetails.getChildId());
			notificationDetails.setDistrictId(childDetails.getChildDistrict().getAreaId());
			notificationDetails.setDivisionId(divisionId);
			notificationDetails.setRecipentId(cciUserMapping.getUserId());
			notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
			notificationDetails.setNotificationType("cci");
			notificationDetails.setNotificationMsg("A new child has been assigned to your CCI with Child ID "+childDetails.getChildId());
			notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			notificationDetails.setCaseType("CNCP");
			notificationDetails.setIsActive(true);
			notificationDetails.setIsRead(false);
			
			notifications.add(notificationDetails);
		}
		
		for (UserDetails userDetails : userList) {
			if(userDetails.getDesignation().getDesignationId()==5 || userDetails.getDesignation().getDesignationId()==6 || 
					userDetails.getDesignation().getDesignationId()==9) {
			
			NotificationDetails notificationDetails = new NotificationDetails();
			notificationDetails.setChildId(childDetails.getChildId());
			notificationDetails.setDistrictId(childDetails.getChildDistrict().getAreaId());
			notificationDetails.setDivisionId(divisionId);
			notificationDetails.setRecipentId(userDetails.getUserId());
			notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
			notificationDetails.setNotificationType("cci");
			notificationDetails.setNotificationMsg("A new child has been moved to "+childInFitInstitutionModel.getCci().getName()+" with Child ID "+childDetails.getChildId());
			notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			notificationDetails.setCaseType("CNCP");
			notificationDetails.setIsActive(true);
			notificationDetails.setIsRead(false);
			
			notifications.add(notificationDetails);
			}
		}
		notificationRepository.save(notifications);
		
		/*Notification part over*/
	}

	@Transactional
	@Override
	public void saveFitPersonDetails(FitPersonDetailModel fitPersonDetailModel) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ChildWithFitPerson childWithFitPerson = new ChildWithFitPerson();

		// childWithFitPerson.setCaseNo(fitPersonDetailModel.getCaseNum());
		childWithFitPerson.setChildId(childDetailsRepository.findChildById(fitPersonDetailModel.getChildId()));
		childWithFitPerson.setJurisdictionDistrict(
				null == fitPersonDetailModel.getDistrictId() ? null : fitPersonDetailModel.getDistrictId());
		childWithFitPerson.setPeriodOfSentToFitPerson(
				null == fitPersonDetailModel.getTimePeriod() ? null : fitPersonDetailModel.getTimePeriod());
		childWithFitPerson.setNameOfTheFitPerson(
				null == fitPersonDetailModel.getFitPersonName() ? null : fitPersonDetailModel.getFitPersonName());
		childWithFitPerson.setNameOfSchool(
				null == fitPersonDetailModel.getSchoolName() ? null : fitPersonDetailModel.getSchoolName());
		childWithFitPerson.setAddressOfSchool(
				null == fitPersonDetailModel.getSchoolAddress() ? null : fitPersonDetailModel.getSchoolAddress());
		childWithFitPerson.setRe(null == fitPersonDetailModel.getRe() ? null : fitPersonDetailModel.getRe());
		childWithFitPerson.setReasonChildProducedInCWC(null == fitPersonDetailModel.getReasonForProduced() ? null
				: fitPersonDetailModel.getReasonForProduced());
		childWithFitPerson.setDateOfFormFilled(
				null == fitPersonDetailModel.getDateOfFormFilled() ? null : fitPersonDetailModel.getDateOfFormFilled());
		childWithFitPerson.setDateWhenCNCP(
				null == fitPersonDetailModel.getDateWhenCNCP() ? null : fitPersonDetailModel.getDateWhenCNCP());
		childWithFitPerson.setAddressOfTheFitPerson(
				null == fitPersonDetailModel.getFitPersonAddress() ? null : fitPersonDetailModel.getFitPersonAddress());
		childWithFitPerson.setPeriodOfSentToFitPerson(
				null == fitPersonDetailModel.getTimePeriod() ? null : fitPersonDetailModel.getTimePeriod());
		childWithFitPerson.setUserIp(userDetailModel.getUserIp());
		childWithFitPerson.setCreatedBy(userDetailModel.getUserName());
		childWithFitPerson.setCreatedDate(new java.sql.Date(new Date().getTime()));

		ChildDetails childDetails = childDetailsRepository.findChildById(childWithFitPerson.getChildId().getChildId());
//		CCIDetails cciDetails = new CCIDetails();
//		cciDetails.setCciId(null);
		childDetails.setCciDetails(null);
		childDetailsRepository.save(childDetails);

		childWithFitPersonRepository.save(childWithFitPerson);
	}

	@Override
	public List fetchAllInterimOrder(String childId) {
		/* String childId="005AGRA2001"; */

		List<Object> models = new ArrayList<>();

		List<ChildPlacedInFitInstitution> fitInstitutionDatas = childInFitInstitutionRepository
				.findByChildIdChildId(childId);
		List<ChildWithFitPerson> fitPersons = childWithFitPersonRepository.findByChildIdChildId(childId);
		List<FosterCareDetails> fosterCareDetails = fosterCareDetailsRepository.findByChildIdChildId(childId);
		List<Object> ciclInterimOrders = ciclInterimService.fetchAll(childId);
		if (!ciclInterimOrders.isEmpty()) {
			return ciclInterimOrders;
		} else {

			if (fitInstitutionDatas != null) {
				for (ChildPlacedInFitInstitution childPlacedInFitInstitution : fitInstitutionDatas) {
					ChildInFitInstitutionModel fitInstitutionModel = new ChildInFitInstitutionModel();
					fitInstitutionModel.setId(childPlacedInFitInstitution.getId());
					fitInstitutionModel.setAddressOfChild(null == childPlacedInFitInstitution.getAddressOfChild() ? null
							: childPlacedInFitInstitution.getAddressOfChild());
					// fitInstitutionModel.setCaseNo(childPlacedInFitInstitution.getCaseNo());
					fitInstitutionModel.setChildAge(null == childPlacedInFitInstitution.getChildId() ? null
							: childPlacedInFitInstitution.getChildId().getAge());
					fitInstitutionModel.setChildId(null == childPlacedInFitInstitution.getChildId() ? null
							: childPlacedInFitInstitution.getChildId().getChildId());
					fitInstitutionModel.setChildName(null == childPlacedInFitInstitution.getChildId() ? null
							: childPlacedInFitInstitution.getChildId().getChildName());
					fitInstitutionModel.setAddressOfChild(null == childPlacedInFitInstitution.getAddressOfChild() ? null
							: childPlacedInFitInstitution.getAddressOfChild());
					// fitInstitutionModel.setChildrenHomeSAAFacility(null==childPlacedInFitInstitution.getChildrenHomeSAAFacility()?null:childPlacedInFitInstitution.getChildrenHomeSAAFacility());
					fitInstitutionModel.setDateChildPlacedInFitInstitution(
							null == childPlacedInFitInstitution.getDateChildPlacedInFitInstitution() ? null
									: childPlacedInFitInstitution.getDateChildPlacedInFitInstitution());
					fitInstitutionModel.setPeriodForWhichSentToFitInstitution(
							null == childPlacedInFitInstitution.getPeriodForWhichSentToFitInstitution() ? null
									: childPlacedInFitInstitution.getPeriodForWhichSentToFitInstitution());
					fitInstitutionModel.setDateOfFormFilled(null == childPlacedInFitInstitution.getDateOfFormFilled()
							? null : childPlacedInFitInstitution.getDateOfFormFilled());
					fitInstitutionModel
							.setCci(DomainToModelConverter.getCCIObject(childPlacedInFitInstitution.getCci()));
					fitInstitutionModel.setParentName(null == childPlacedInFitInstitution.getParentName() ? null
							: childPlacedInFitInstitution.getParentName());
					fitInstitutionModel.setDecisionType("fitInstitution");

					models.add(fitInstitutionModel);
				}
			}

			if (fosterCareDetails != null) {
				for (FosterCareDetails fosterCareDetail : fosterCareDetails) {
					FosterCareDetailsModel fosterCareDetailsModel = new FosterCareDetailsModel();
					fosterCareDetailsModel.setId(fosterCareDetail.getId());
					fosterCareDetailsModel.setCciAddress(fosterCareDetail.getCciAddress());
					fosterCareDetailsModel.setCciName(fosterCareDetail.getCciName());
					fosterCareDetailsModel
							.setChildAddress(fosterCareDetail.getChildId().getChildDistrict().getAreaName());
					fosterCareDetailsModel.setChildAge(fosterCareDetail.getChildId().getAge());
					fosterCareDetailsModel.setChildId(fosterCareDetail.getChildId().getChildId());
					fosterCareDetailsModel.setChildName(fosterCareDetail.getChildId().getChildName());
					fosterCareDetailsModel
							.setDurationOfStayAtFosterCare(fosterCareDetail.getDurationOfStayAtFosterCare());
					fosterCareDetailsModel.setFosterCareWorkerAddress(fosterCareDetail.getFosterCareWorkerAddress());
					fosterCareDetailsModel.setFosterCareWorkerName(fosterCareDetail.getFosterCareWorkerName());
					fosterCareDetailsModel.setFosterParentAddress(fosterCareDetail.getFosterParentAddress());
					fosterCareDetailsModel.setFosterParentContact(fosterCareDetail.getFosterParentContact());
					fosterCareDetailsModel.setFosterParentName1(fosterCareDetail.getFosterParentName1());
					fosterCareDetailsModel.setFosterParentName2(fosterCareDetail.getFosterParentName2());
					fosterCareDetailsModel.setFosterType(fosterCareDetail.getFosterType());
					fosterCareDetailsModel.setId(fosterCareDetail.getId());
					fosterCareDetailsModel.setParentName1(fosterCareDetail.getParentName1());
					fosterCareDetailsModel.setParentName2(fosterCareDetail.getParentName2());
					fosterCareDetailsModel.setDecisionType("fosterCare");
					fosterCareDetailsModel.setDateOfFormFilled(fosterCareDetail.getDateOfFormFilled());
					// fosterCareDetailsModel.setDateOfFormFilled(new Date());
					models.add(fosterCareDetailsModel);

				}
			}

			if (fitPersons != null) {
				for (ChildWithFitPerson fitPerson : fitPersons) {
					FitPersonDetailModel fitPersonDetailModel = new FitPersonDetailModel();

					fitPersonDetailModel.setId(fitPerson.getId());
					fitPersonDetailModel.setChildId(fitPerson.getChildId().getChildId());
					// fitPersonDetailModel.setCaseNum(fitPerson.getCaseNo());
					fitPersonDetailModel.setDateOfFormFilled(fitPerson.getDateOfFormFilled());
					// fitPersonDetailModel.setDateWhenCNCP(fitPerson.getDateWhenCNCP());
					fitPersonDetailModel.setFitPersonName(fitPerson.getNameOfTheFitPerson());
					fitPersonDetailModel.setFitPersonAddress(fitPerson.getAddressOfTheFitPerson());
					fitPersonDetailModel.setDistrictId(fitPerson.getChildId().getChildDistrict().getAreaId());
					fitPersonDetailModel.setReasonForProduced(fitPerson.getReasonChildProducedInCWC());
					fitPersonDetailModel.setSchoolName(fitPerson.getNameOfSchool());
					fitPersonDetailModel.setSchoolAddress(fitPerson.getAddressOfSchool());
					fitPersonDetailModel.setDecisionType("fitPerson");
					fitPersonDetailModel.setRe(fitPerson.getRe());
					fitPersonDetailModel.setTimePeriod(fitPerson.getPeriodOfSentToFitPerson());
					// fitPersonDetailModel.setDateOfReg(fitPerson.getDate);

					models.add(fitPersonDetailModel);

				}
			}
			return models;
		}
	}

	@Override
	public List<CaseMonitoringModel> fetchCaseMonitoring(String childId) throws Exception {
		CaseMonitoringModel caseMonitoringModel = null;
		List<CaseMonitoringModel> listOfCaseMonitoring = new ArrayList<>();
		List<CaseMonitoring> listOfCaseMonitoringDatas = caseMonitoringRepository.findByChildIdChildId(childId);
		if (listOfCaseMonitoringDatas != null && !listOfCaseMonitoringDatas.isEmpty()) {
			for (CaseMonitoring listOfCaseMonitoringData : listOfCaseMonitoringDatas) {
				caseMonitoringModel = new CaseMonitoringModel();
				caseMonitoringModel
						.setActualDateOfAgeDetermination(listOfCaseMonitoringData.getActualDateOfAgeDetermination());
				caseMonitoringModel.setActualDateOfFinalOrder(listOfCaseMonitoringData.getActualDateOfFinalOrder());
				caseMonitoringModel.setActualDateOfIndividualCarePlan(
						listOfCaseMonitoringData.getActualDateOfIndividualCarePlan());
				caseMonitoringModel.setActualDateOfPostDispositionalReview(
						listOfCaseMonitoringData.getActualDateOfPostDispositionalReview());
				caseMonitoringModel.setActualDateOfSocialInvestigationReport(
						listOfCaseMonitoringData.getActualDateOfSocialInvestigationReport());
				caseMonitoringModel
						.setActualDateOfStatementOfChild(listOfCaseMonitoringData.getActualDateOfStatementOfChild());
				caseMonitoringModel.setActualDateOfSubmissionReportOnProvisions(
						listOfCaseMonitoringData.getActualDateOfSubmissionReportOnProvisions());
				caseMonitoringModel
						.setAgeDeterminationCommitteeName(listOfCaseMonitoringData.getAgeDeterminatorCommitteeName());
				caseMonitoringModel.setCaseName(listOfCaseMonitoringData.getCaseName());
				caseMonitoringModel.setChildId(listOfCaseMonitoringData.getChildId().getChildName());
				caseMonitoringModel.setChildPermanentAddress(listOfCaseMonitoringData.getChildPermanentAddress());
				caseMonitoringModel.setChildPresentAddress(listOfCaseMonitoringData.getChildPresentAddress());
				caseMonitoringModel.setChildProducedBeforeCommitteeDate(
						listOfCaseMonitoringData.getChildProducedBeforeCommitteeDate());
				caseMonitoringModel.setChildProducedBeforeCommitteeTime(
						listOfCaseMonitoringData.getChildProducedBeforeCommitteeTime());
				caseMonitoringModel.setChildrenHomeName(listOfCaseMonitoringData.getChildrenHomeName());
				caseMonitoringModel.setDateChildSentToSupervisionInstitution(
						listOfCaseMonitoringData.getDateChildSentToSupervisionInstitution());
				caseMonitoringModel.setDateOfAgeDetermination(listOfCaseMonitoringData.getDateOfAgeDetermination());
				caseMonitoringModel.setDateOfMedicalExamination(listOfCaseMonitoringData.getDateOfMedicalExamination());
				caseMonitoringModel.setDateTillChildSentToSupervisionInstitution(
						listOfCaseMonitoringData.getDateTillChildSentToSupervisionInstitution());
				caseMonitoringModel.setEvidenceDocuments(listOfCaseMonitoringData.getEvidenceDocuments());
				caseMonitoringModel.setEvidenceMedicalReports(listOfCaseMonitoringData.getEvidenceMedicalReports());
				caseMonitoringModel.setFirGdDdNo(listOfCaseMonitoringData.getFirGdDdNo());
				caseMonitoringModel.setIoName(listOfCaseMonitoringData.getIoName());
				caseMonitoringModel.setNameOfDeterminator(listOfCaseMonitoringData.getNameOfDeterminator());
				caseMonitoringModel.setPgContactNo(listOfCaseMonitoringData.getPgContactNo() == null ? null
						: listOfCaseMonitoringData.getPgContactNo().toString());
				caseMonitoringModel.setPgName(listOfCaseMonitoringData.getPgName());
				caseMonitoringModel.setProbationOfficerName(listOfCaseMonitoringData.getProbationOfficerName());
				caseMonitoringModel.setPsName(listOfCaseMonitoringData.getPsName());
				caseMonitoringModel.setScheduledDateOfAgeDetermination(
						listOfCaseMonitoringData.getScheduledDateOfAgeDetermination());
				caseMonitoringModel.setScheduledDateOfFinalOrder(listOfCaseMonitoringData.getActualDateOfFinalOrder());
				caseMonitoringModel.setScheduledDateOfIndividualCarePlan(
						listOfCaseMonitoringData.getScheduledDateOfIndividualCarePlan());
				caseMonitoringModel.setScheduledDateOfPostDispositionalReview(
						listOfCaseMonitoringData.getScheduledDateOfPostDispositionalReview());
				caseMonitoringModel.setScheduledDateOfSocialInvestigationReport(
						listOfCaseMonitoringData.getActualDateOfSocialInvestigationReport());
				caseMonitoringModel.setScheduledDateOfStatementOfChild(
						listOfCaseMonitoringData.getScheduledDateOfStatementOfChild());
				caseMonitoringModel.setScheduledDateOfSubmissionReportOnProvisions(
						listOfCaseMonitoringData.getScheduledDateOfSubmissionReportOnProvisions());
				caseMonitoringModel.setSectionsChildBooked(listOfCaseMonitoringData.getSectionsChildBooked());
				caseMonitoringModel
						.setSupervisionInstitutionName(listOfCaseMonitoringData.getSupervisionInstitutionName());
				caseMonitoringModel.setTimeTakenForAgeDetermination(
						null == listOfCaseMonitoringData.getTimeTakenForAgeDetermination() ? null
								: String.valueOf(listOfCaseMonitoringData.getTimeTakenForAgeDetermination()));
				caseMonitoringModel.setDate(listOfCaseMonitoringData.getDate());
				caseMonitoringModel.setAgeondate(listOfCaseMonitoringData.getAgeOnDateOfOffence());
				caseMonitoringModel
						.setDoc(exportPDFServiceImpl.getPdf(listOfCaseMonitoringData.getEvidenceDocuments()));
				caseMonitoringModel
						.setMedical(exportPDFServiceImpl.getPdf(listOfCaseMonitoringData.getEvidenceMedicalReports()));

				listOfCaseMonitoring.add(caseMonitoringModel);
			}
		}

		return listOfCaseMonitoring;
	}

	@Override
	public List<ValueObject> getAreaWiseCciList(Integer areaId) {
		List<CCIDetails> cciDetails = cciInfoRepository.getAreaWiseCciList(areaId);
		List<ValueObject> cciDetailModels = new ArrayList<>();
		List<Object[]> childCounts = cciInfoRepository.getCciWiseChildCount();
		Map<Integer, Integer> childCountMap=new HashMap<>();
		for (Object[] objects : childCounts) {
			childCountMap.put(Integer.parseInt(String.valueOf(objects[0])), Integer.parseInt(String.valueOf(objects[1])));
		}
		for (CCIDetails cciDetail : cciDetails) {
			// CCIDetailsModel cciDetailsModel=new CCIDetailsModel();
			ValueObject cciDetailsModel = new ValueObject();
			cciDetailsModel.setId(cciDetail.getCciId());
			cciDetailsModel.setName(cciDetail.getCciName()+"(Strength-"+(childCountMap.get(cciDetail.getCciId())!=null?childCountMap.get(cciDetail.getCciId()):0)+")");
			cciDetailsModel.setTypeId(cciDetail.getCciTypeDetails().getTypeId());

			cciDetailModels.add(cciDetailsModel);
		}
		return cciDetailModels;
	}

	@Override
	public List<FosterCareDetailsModel> findAllFosterOrders(String childId) {

		List<FosterCareDetails> fosterCareDetails = fosterCareDetailsRepository.findByChildIdChildId(childId);
		List<FosterCareDetailsModel> fosterModelList = new ArrayList<FosterCareDetailsModel>();

		if (fosterCareDetails != null && fosterCareDetails.size() > 0) {
			for (FosterCareDetails fosterCareDetail : fosterCareDetails) {
				FosterCareDetailsModel fosterCareDetailsModel = new FosterCareDetailsModel();

				fosterCareDetailsModel.setId(fosterCareDetail.getId());
				fosterCareDetailsModel.setCciAddress(fosterCareDetail.getCciAddress());
				fosterCareDetailsModel.setCciName(fosterCareDetail.getCciName());
				fosterCareDetailsModel.setChildAddress(fosterCareDetail.getChildId().getChildDistrict().getAreaName());
				fosterCareDetailsModel.setChildAge(fosterCareDetail.getChildId().getAge());
				fosterCareDetailsModel.setChildId(fosterCareDetail.getChildId().getChildId());
				fosterCareDetailsModel.setChildName(fosterCareDetail.getChildId().getChildName());
				fosterCareDetailsModel.setDurationOfStayAtFosterCare(fosterCareDetail.getDurationOfStayAtFosterCare());
				fosterCareDetailsModel.setFosterCareWorkerAddress(fosterCareDetail.getFosterCareWorkerAddress());
				fosterCareDetailsModel.setFosterCareWorkerName(fosterCareDetail.getFosterCareWorkerName());
				fosterCareDetailsModel.setFosterParentAddress(fosterCareDetail.getFosterParentAddress());
				fosterCareDetailsModel.setFosterParentContact(fosterCareDetail.getFosterParentContact());
				fosterCareDetailsModel.setFosterParentName1(fosterCareDetail.getFosterParentName1());
				fosterCareDetailsModel.setFosterParentName2(fosterCareDetail.getFosterParentName2());
				fosterCareDetailsModel.setFosterType(fosterCareDetail.getFosterType());
				fosterCareDetailsModel.setId(fosterCareDetail.getId());
				fosterCareDetailsModel.setParentName1(fosterCareDetail.getParentName1());
				fosterCareDetailsModel.setParentName2(fosterCareDetail.getParentName2());
				fosterCareDetailsModel.setDecisionType("fosterCare");
				fosterCareDetailsModel.setDateOfFormFilled(fosterCareDetail.getDateOfFormFilled());
				// fosterCareDetailsModel.setDateOfFormFilled(new Date());

				fosterModelList.add(fosterCareDetailsModel);
			}
		}

		return fosterModelList;
	}

	@Override
	public List<ValueObject> getAllCciList() {
		List<CCIDetails> cciDetails = cciInfoRepository.fetchAllCCIs();
		List<Object[]> childCounts = cciInfoRepository.getCciWiseChildCount();
		Map<Integer, Integer> childCountMap=new HashMap<>();
		for (Object[] objects : childCounts) {
			childCountMap.put(Integer.parseInt(String.valueOf(objects[0])), Integer.parseInt(String.valueOf(objects[1])));
		}
		List<ValueObject> cciDetailModels = new ArrayList<>();
		for (CCIDetails cciDetail : cciDetails) {
			// CCIDetailsModel cciDetailsModel=new CCIDetailsModel();
			ValueObject cciDetailsModel = new ValueObject();
			cciDetailsModel.setId(cciDetail.getCciId());
			cciDetailsModel.setName(cciDetail.getCciName()+"(Strength-"+(childCountMap.get(cciDetail.getCciId())!=null?childCountMap.get(cciDetail.getCciId()):0)+")");
			cciDetailsModel.setTypeId(cciDetail.getCciTypeDetails().getTypeId());
			cciDetailsModel.setKey(cciDetail.getAreaDetails());
			cciDetailsModel.setChildCount(childCountMap.get(cciDetail.getCciId())!=null?childCountMap.get(cciDetail.getCciId()):0);

			cciDetailModels.add(cciDetailsModel);
		}
		return cciDetailModels;
	}

}
