package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CICLCaseMoniteringSheet;
import org.sdrc.cpis.domains.CICLChildCareInstitutionPendingInquiry;
import org.sdrc.cpis.domains.CICLSupervisionOrder;
import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.CICLCaseMoniteringSheetModel;
import org.sdrc.cpis.models.CICLChildCareInstitutionPendingInquiryModel;
import org.sdrc.cpis.models.CICLSupervisionOrderModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLCaseMonitoringSheetRepository;
import org.sdrc.cpis.repository.CICLChildCareInstitutionPendingInquiryRepository;
import org.sdrc.cpis.repository.CICLSupervisionOrderRepository;
import org.sdrc.cpis.repository.CciUserRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.NotificationRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class CICLInterimServiceImpl implements CICLInterimService {
	@Autowired 
	private CICLSupervisionOrderRepository  ciclSupervisionOrderRepository;
	
	@Autowired
	private CICLChildCareInstitutionPendingInquiryRepository ciclChildCareInstitutionPendingInquiryRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private CICLCaseMonitoringSheetRepository ciclCaseMonitoringSheetRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private CCIInfoRepository cciInfoRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CciUserRepository cciUserRepository;
	
	@Autowired
	private StateManager stateManager;
	
	Map<Integer, ValueObject> typeMap;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	public Map<Integer, ValueObject> getTypeMap(){
	    List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(cctsTypeDetails.getTypeDetailsNameHindi()!=null?cctsTypeDetails.getTypeDetailsNameHindi():cctsTypeDetails.getTypeDetailsName());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}
	
	public ValueObject getCCIName(Integer id){
		CCIDetails cciDetails = new CCIDetails();
		ValueObject cciObject = new ValueObject();
		cciDetails = cciInfoRepository.getCciById(id);
		
		cciObject.setId(null == cciDetails.getCciId() ? null : cciDetails.getCciId());
		cciObject.setName(null == cciDetails.getCciName() ? null : cciDetails.getCciName());
		
		return cciObject;
	}
	
	
	private String getSavedPath(String data, String type) throws Exception {
		String path = applicationMessageSource.getMessage("store.CaseMonitoring", null, null,null);
		return exportPDFServiceImpl.getFileName(data, type, path);
	}

	@Override
	public String saveCICLSupervisionOrder(CICLSupervisionOrderModel ciclSupervisionOrderModel) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		
		if(ciclSupervisionOrderModel != null){
			CICLSupervisionOrder ciclSupervisionOrder = new CICLSupervisionOrder();
			{    
				ciclSupervisionOrder.setChildId(childDetailsRepository.findChildById(ciclSupervisionOrderModel.getChildId()));
				ciclSupervisionOrder.setChildUnderCareOfWhom(ciclSupervisionOrderModel.getChildUnderCareOfWhom().getId());
				ciclSupervisionOrder.setSupervisionAuthorityName(ciclSupervisionOrderModel.getSupervisionAuthorityName());
				ciclSupervisionOrder.setSupervisionAuthorityAddress(ciclSupervisionOrderModel.getSupervisionAuthorityAddress());
				ciclSupervisionOrder.setChildPlacedPeriod(ciclSupervisionOrderModel.getChildPlacedPeriod());
				ciclSupervisionOrder.setDateOfOrder(ciclSupervisionOrderModel.getDateOfOrder());
				ciclSupervisionOrder.setFormNo(3);
				if(userDetailModel != null){
					ciclSupervisionOrder.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
					ciclSupervisionOrder.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
				}
				ciclSupervisionOrder.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
				ciclSupervisionOrderRepository.save(ciclSupervisionOrder);
			}
		}
		return "Saved SuccessFully";
	  }
	
	@Override
	public String saveCICLChildCareInstitutionPendingInquiry(CICLChildCareInstitutionPendingInquiryModel cICLChildCareInstitutionPendingInquiryModel) {
		if(cICLChildCareInstitutionPendingInquiryModel!=null){
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			CICLChildCareInstitutionPendingInquiry ciclChildCareInstitutionPendingInquiry = new CICLChildCareInstitutionPendingInquiry();
			ChildDetails childDetails = new ChildDetails();
			CCIDetails cciDetails = new CCIDetails();
			
			ciclChildCareInstitutionPendingInquiry.setChildId(childDetailsRepository.findChildById(cICLChildCareInstitutionPendingInquiryModel.getChildId()));
			ciclChildCareInstitutionPendingInquiry.setDateOfOrder(cICLChildCareInstitutionPendingInquiryModel.getDateOfOrder() == null ? null : cICLChildCareInstitutionPendingInquiryModel.getDateOfOrder());
			ciclChildCareInstitutionPendingInquiry.setDateOfPlacement(null == cICLChildCareInstitutionPendingInquiryModel.getDateOfPlacement() ? null : cICLChildCareInstitutionPendingInquiryModel.getDateOfPlacement());
			ciclChildCareInstitutionPendingInquiry.setDuration(cICLChildCareInstitutionPendingInquiryModel.getDuration());
			ciclChildCareInstitutionPendingInquiry.setNextDateOfHearing(new java.sql.Date(cICLChildCareInstitutionPendingInquiryModel.getNextDateOfHearing().getTime()));
			ciclChildCareInstitutionPendingInquiry.setAddressOfChild(null == cICLChildCareInstitutionPendingInquiryModel.getAddressOfChild() ? null : cICLChildCareInstitutionPendingInquiryModel.getAddressOfChild());
			ciclChildCareInstitutionPendingInquiry.setParentName(null == cICLChildCareInstitutionPendingInquiryModel.getParentName() ? null : cICLChildCareInstitutionPendingInquiryModel.getParentName());
			ciclChildCareInstitutionPendingInquiry.setFormNo(4);
			ciclChildCareInstitutionPendingInquiry.setCciId(cICLChildCareInstitutionPendingInquiryModel.getCciObject().getId());
			if(userDetailModel != null){
				ciclChildCareInstitutionPendingInquiry.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
				ciclChildCareInstitutionPendingInquiry.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			}
			ciclChildCareInstitutionPendingInquiry.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
			cciDetails.setCciId(cICLChildCareInstitutionPendingInquiryModel.getCciObject().getId());
			cciDetails.setCciName(cICLChildCareInstitutionPendingInquiryModel.getCciObject().getName());
			
			childDetails = childDetailsRepository.findChildById(cICLChildCareInstitutionPendingInquiryModel.getChildId());
			childDetails.setCciDetails(cciDetails);
			childDetailsRepository.save(childDetails);
			
			ciclChildCareInstitutionPendingInquiryRepository.save(ciclChildCareInstitutionPendingInquiry);
			
			/*Sending notifications to district level authorities*/
			List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(childDetails.getChildDistrict().getAreaId());
			List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
			CciUserMapping cciUserMapping = cciUserRepository.findByCciId(cICLChildCareInstitutionPendingInquiryModel.getCciObject().getId());
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
				notificationDetails.setCaseType("CICL");
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
				notificationDetails.setDivisionId(areaRepository.fetchAreaById(childDetails.getChildDistrict().getParentArea().getAreaId()).getAreaId());
				notificationDetails.setRecipentId(userDetails.getUserId());
				notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
				notificationDetails.setNotificationType("cci");
				notificationDetails.setNotificationMsg("A new child has been moved to "+cICLChildCareInstitutionPendingInquiryModel.getCciObject().getName()+" with Child ID "+childDetails.getChildId());
				notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
				notificationDetails.setCaseType("CICL");
				notificationDetails.setIsActive(true);
				notificationDetails.setIsRead(false);
				
				notifications.add(notificationDetails);
				}
			}
			notificationRepository.save(notifications);
			
			/*Notification part over*/
		}
		
		
		return "Saved SuccessFully";
	}

	@Override
	public String saveCICLCaseMoniteringSheet(CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel) throws Exception {
		if(ciclCaseMoniteringSheetModel != null){
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			CICLCaseMoniteringSheet ciclCaseMoniteringSheet = new CICLCaseMoniteringSheet();
			ChildDetails childDetails = new ChildDetails();
			
			childDetails = childDetailsRepository.findChildById(ciclCaseMoniteringSheetModel.getChildId());
			childDetails.setCaseNum(ciclCaseMoniteringSheetModel.getCaseNo());
			childDetailsRepository.save(childDetails);
			
			ciclCaseMoniteringSheet.setAgeDeterminationActualDate(null == ciclCaseMoniteringSheetModel.getAgeDeterminationActualDate() ? null : ciclCaseMoniteringSheetModel.getAgeDeterminationActualDate());
			ciclCaseMoniteringSheet.setAgeDeterminationScheduledDate(null == ciclCaseMoniteringSheetModel.getAgeDeterminationScheduledDate() ? null : ciclCaseMoniteringSheetModel.getAgeDeterminationScheduledDate());
			ciclCaseMoniteringSheet.setAgeDeterminationTime(null == ciclCaseMoniteringSheetModel.getAgeDeterminationTime() ? null : ciclCaseMoniteringSheetModel.getAgeDeterminationTime());
			ciclCaseMoniteringSheet.setAprehendedDate(null == ciclCaseMoniteringSheetModel.getAprehendedDate() ? null : ciclCaseMoniteringSheetModel.getAprehendedDate());
			ciclCaseMoniteringSheet.setAprehendedTime(null == ciclCaseMoniteringSheetModel.getAprehendedTime() ? null : ciclCaseMoniteringSheetModel.getAprehendedTime());
			ciclCaseMoniteringSheet.setBailConsiderationActualDate(null == ciclCaseMoniteringSheetModel.getBailConsiderationActualDate() ? null : ciclCaseMoniteringSheetModel.getBailConsiderationActualDate());
			ciclCaseMoniteringSheet.setBailConsiderationScheduledDate(null == ciclCaseMoniteringSheetModel.getBailConsiderationScheduledDate() ? null : ciclCaseMoniteringSheetModel.getBailConsiderationScheduledDate());
			ciclCaseMoniteringSheet.setCarePlanActualDate(null == ciclCaseMoniteringSheetModel.getCarePlanActualDate() ? null : ciclCaseMoniteringSheetModel.getCarePlanActualDate());
			ciclCaseMoniteringSheet.setCarePlanScheduledDate(null == ciclCaseMoniteringSheetModel.getCarePlanScheduledDate() ? null : ciclCaseMoniteringSheetModel.getCarePlanScheduledDate());
			ciclCaseMoniteringSheet.setCaseName(null == ciclCaseMoniteringSheetModel.getCaseName() ? null : ciclCaseMoniteringSheetModel.getCaseName());
			ciclCaseMoniteringSheet.setCaseNo(null == ciclCaseMoniteringSheetModel.getCaseNo() ? null : ciclCaseMoniteringSheetModel.getCaseNo());
			ciclCaseMoniteringSheet.setChildAgeDeterminationDate(null == ciclCaseMoniteringSheetModel.getChildAgeDeterminationDate() ? null : ciclCaseMoniteringSheetModel.getChildAgeDeterminationDate());
			ciclCaseMoniteringSheet.setChildAgeOnDateOfOffence(null == ciclCaseMoniteringSheetModel.getChildAgeOnDateOfOffence() ? null : ciclCaseMoniteringSheetModel.getChildAgeOnDateOfOffence());
			ciclCaseMoniteringSheet.setChildId(childDetails);
			ciclCaseMoniteringSheet.setChildWelfareOfficerName(null == ciclCaseMoniteringSheetModel.getChildWelfareOfficerName() ? null : ciclCaseMoniteringSheetModel.getChildWelfareOfficerName());
			ciclCaseMoniteringSheet.setCocBailDate(null == ciclCaseMoniteringSheetModel.getCocBailDate() ? null : ciclCaseMoniteringSheetModel.getCocBailDate());
			ciclCaseMoniteringSheet.setCocByWhom(null == ciclCaseMoniteringSheetModel.getCocByWhom() ? null : ciclCaseMoniteringSheetModel.getCocByWhom().getId());
			ciclCaseMoniteringSheet.setCocByWhomName(null == ciclCaseMoniteringSheetModel.getCocByWhomName() ? null : ciclCaseMoniteringSheetModel.getCocByWhomName());
			ciclCaseMoniteringSheet.setCocFromDate(null == ciclCaseMoniteringSheetModel.getCocFromDate() ? null : ciclCaseMoniteringSheetModel.getCocFromDate());
			ciclCaseMoniteringSheet.setCocToDate(null == ciclCaseMoniteringSheetModel.getCocToDate() ? null : ciclCaseMoniteringSheetModel.getCocToDate());
			ciclCaseMoniteringSheet.setCrpc173ReportActualDate(null == ciclCaseMoniteringSheetModel.getCrpc173ReportActualDate() ? null : ciclCaseMoniteringSheetModel.getCrpc173ReportActualDate());
			ciclCaseMoniteringSheet.setCrpc173ReportScheduledDate(null == ciclCaseMoniteringSheetModel.getCrpc173ReportScheduledDate() ? null : ciclCaseMoniteringSheetModel.getCrpc173ReportScheduledDate());
			ciclCaseMoniteringSheet.setCrpc251ReportActualDate(null == ciclCaseMoniteringSheetModel.getCrpc251ReportActualDate() ? null : ciclCaseMoniteringSheetModel.getCrpc251ReportActualDate());
			ciclCaseMoniteringSheet.setCrpc251ReportScheduledDate(null == ciclCaseMoniteringSheetModel.getCrpc251ReportScheduledDate() ? null : ciclCaseMoniteringSheetModel.getCrpc251ReportScheduledDate());
			ciclCaseMoniteringSheet.setCrpc281ReportActualDate(null == ciclCaseMoniteringSheetModel.getCrpc281ReportActualDate() ? null : ciclCaseMoniteringSheetModel.getCrpc281ReportActualDate());
			ciclCaseMoniteringSheet.setCrpc281ReportScheduledDate(null == ciclCaseMoniteringSheetModel.getCrpc281ReportScheduledDate() ? null : ciclCaseMoniteringSheetModel.getCrpc281ReportScheduledDate());
			ciclCaseMoniteringSheet.setDate(null == ciclCaseMoniteringSheetModel.getDate() ? null : ciclCaseMoniteringSheetModel.getDate());
			ciclCaseMoniteringSheet.setDefenceEvidienceActualDate(null == ciclCaseMoniteringSheetModel.getDefenceEvidienceActualDate() ? null : ciclCaseMoniteringSheetModel.getDefenceEvidienceActualDate());
			ciclCaseMoniteringSheet.setDefenceEvidienceScheduledDate(null == ciclCaseMoniteringSheetModel.getDefenceEvidienceScheduledDate() ? null : ciclCaseMoniteringSheetModel.getDefenceEvidienceScheduledDate());
			ciclCaseMoniteringSheet.setDeterminationBy(null == ciclCaseMoniteringSheetModel.getDeterminationBy() ? null : ciclCaseMoniteringSheetModel.getDeterminationBy().getId());
			ciclCaseMoniteringSheet.setDispositionalOrderActualDate(null == ciclCaseMoniteringSheetModel.getDispositionalOrderActualDate() ? null : ciclCaseMoniteringSheetModel.getDispositionalOrderActualDate());
			ciclCaseMoniteringSheet.setDispositionalOrderScheduledDate(null == ciclCaseMoniteringSheetModel.getDispositionalOrderScheduledDate() ? null : ciclCaseMoniteringSheetModel.getDispositionalOrderScheduledDate());
			ciclCaseMoniteringSheet.setDistrict(null == ciclCaseMoniteringSheetModel.getDistrict() ? null : ciclCaseMoniteringSheetModel.getDistrict());
			ciclCaseMoniteringSheet.setEvidenceReliedDocument(null == ciclCaseMoniteringSheetModel.getEvidenceReliedDocument() ? null : getSavedPath(ciclCaseMoniteringSheetModel.getEvidenceReliedDocument(), "Case_Monitoring"));
			ciclCaseMoniteringSheet.setEvidenceReliedMedicalName(null == ciclCaseMoniteringSheetModel.getEvidenceReliedMedicalName() ? null : ciclCaseMoniteringSheetModel.getEvidenceReliedMedicalName());
			ciclCaseMoniteringSheet.setFinalArgumentsActualDate(null == ciclCaseMoniteringSheetModel.getFinalArgumentsActualDate() ? null : ciclCaseMoniteringSheetModel.getFinalArgumentsActualDate());
			ciclCaseMoniteringSheet.setFinalArgumentsScheduledDate(null == ciclCaseMoniteringSheetModel.getFinalArgumentsScheduledDate() ? null : ciclCaseMoniteringSheetModel.getFinalArgumentsScheduledDate());
			ciclCaseMoniteringSheet.setFirstProductionDate(null == ciclCaseMoniteringSheetModel.getFirstProductionDate() ? null : ciclCaseMoniteringSheetModel.getFirstProductionDate());
			ciclCaseMoniteringSheet.setFirstProductionTime(null == ciclCaseMoniteringSheetModel.getFirstProductionTime() ? null : ciclCaseMoniteringSheetModel.getFirstProductionTime());
			ciclCaseMoniteringSheet.setIoName(null == ciclCaseMoniteringSheetModel.getIoName() ? null : ciclCaseMoniteringSheetModel.getIoName());
			ciclCaseMoniteringSheet.setLawyerName(null == ciclCaseMoniteringSheetModel.getLawyerName() ? null : ciclCaseMoniteringSheetModel.getLawyerName());
			ciclCaseMoniteringSheet.setMedicalExaminationDate(null == ciclCaseMoniteringSheetModel.getMedicalExaminationDate() ? null : ciclCaseMoniteringSheetModel.getMedicalExaminationDate());
			ciclCaseMoniteringSheet.setNatureOfOffence(null == ciclCaseMoniteringSheetModel.getNatureOfOffence() ? null : ciclCaseMoniteringSheetModel.getNatureOfOffence().getId());
			ciclCaseMoniteringSheet.setParentContactnumber(null == ciclCaseMoniteringSheetModel.getParentContactnumber() ? null : ciclCaseMoniteringSheetModel.getParentContactnumber());
			ciclCaseMoniteringSheet.setPermanentAddress(null == ciclCaseMoniteringSheetModel.getPermanentAddress() ? null : ciclCaseMoniteringSheetModel.getPermanentAddress());
			ciclCaseMoniteringSheet.setPostDispositionalReviewActualDate(null == ciclCaseMoniteringSheetModel.getPostDispositionalReviewActualDate() ? null : ciclCaseMoniteringSheetModel.getPostDispositionalReviewActualDate());
			ciclCaseMoniteringSheet.setPostDispositionalReviewScheduledDate(null == ciclCaseMoniteringSheetModel.getPostDispositionalReviewScheduledDate() ? null : ciclCaseMoniteringSheetModel.getPostDispositionalReviewScheduledDate());
			ciclCaseMoniteringSheet.setPresentAddress(null == ciclCaseMoniteringSheetModel.getPresentAddress() ? null : ciclCaseMoniteringSheetModel.getPresentAddress());
			ciclCaseMoniteringSheet.setProbationOfficerName(null == ciclCaseMoniteringSheetModel.getProbationOfficerName() ? null : ciclCaseMoniteringSheetModel.getProbationOfficerName());
			ciclCaseMoniteringSheet.setProsecutionEvidienceActualDate(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceActualDate() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceActualDate());
			ciclCaseMoniteringSheet.setProsecutionEvidienceActualDate1(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceActualDate1() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceActualDate1());
			ciclCaseMoniteringSheet.setProsecutionEvidienceActualDate2(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceActualDate2() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceActualDate2());
			ciclCaseMoniteringSheet.setProsecutionEvidienceFromDate(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceFromDate() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceFromDate());
			ciclCaseMoniteringSheet.setProsecutionEvidienceScheduledDate(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceScheduledDate() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceScheduledDate());
			ciclCaseMoniteringSheet.setProsecutionEvidienceScheduledDate1(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceScheduledDate1() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceScheduledDate1());
			ciclCaseMoniteringSheet.setProsecutionEvidienceScheduledDate2(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceScheduledDate2() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceScheduledDate2());
			ciclCaseMoniteringSheet.setProsecutionEvidienceToDate(null == ciclCaseMoniteringSheetModel.getProsecutionEvidienceToDate() ? null : ciclCaseMoniteringSheetModel.getProsecutionEvidienceToDate());
			ciclCaseMoniteringSheet.setReportsOnProvisionActualDate(null == ciclCaseMoniteringSheetModel.getReportsOnProvisionActualDate() ? null : ciclCaseMoniteringSheetModel.getReportsOnProvisionActualDate());
			ciclCaseMoniteringSheet.setReportsOnProvisionScheduledDate(null == ciclCaseMoniteringSheetModel.getReportsOnProvisionScheduledDate() ? null : ciclCaseMoniteringSheetModel.getReportsOnProvisionScheduledDate());
			ciclCaseMoniteringSheet.setSirByProbationOfficerActualDate(null == ciclCaseMoniteringSheetModel.getSirByProbationOfficerActualDate() ? null : ciclCaseMoniteringSheetModel.getSirByProbationOfficerActualDate());
			ciclCaseMoniteringSheet.setSirByProbationOfficerScheduledDate(null == ciclCaseMoniteringSheetModel.getSirByProbationOfficerScheduledDate() ? null : ciclCaseMoniteringSheetModel.getSirByProbationOfficerScheduledDate());
			ciclCaseMoniteringSheet.setSocialBackgroundActualDate(null == ciclCaseMoniteringSheetModel.getSocialBackgroundActualDate() ? null : ciclCaseMoniteringSheetModel.getSocialBackgroundActualDate());
			ciclCaseMoniteringSheet.setSocialBackgroundScheduledDate(null == ciclCaseMoniteringSheetModel.getSocialBackgroundScheduledDate() ? null : ciclCaseMoniteringSheetModel.getSocialBackgroundScheduledDate());
			ciclCaseMoniteringSheet.setSupervision_institution_name(null == ciclCaseMoniteringSheetModel.getSupervision_institution_name() ? null : ciclCaseMoniteringSheetModel.getSupervision_institution_name());
			ciclCaseMoniteringSheet.setUnderSection(null == ciclCaseMoniteringSheetModel.getUnderSection() ? null : ciclCaseMoniteringSheetModel.getUnderSection());
			ciclCaseMoniteringSheet.setFormNo(11);
			ciclCaseMoniteringSheet.setCreatedDate(ciclCaseMoniteringSheetModel.getCreatedDate());
			if(userDetailModel != null){
				ciclCaseMoniteringSheet.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
				ciclCaseMoniteringSheet.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			}
			
			ciclCaseMonitoringSheetRepository.save(ciclCaseMoniteringSheet);
			
		}
		return "Saved SuccessFully";
	}

	@Override
	public List<CICLCaseMoniteringSheetModel> getCiclCaseMonitoringByChildId(String childId) throws Exception {
		
		List<CICLCaseMoniteringSheet> ciclCaseMoniteringSheetList = new ArrayList<CICLCaseMoniteringSheet>();
		List<CICLCaseMoniteringSheetModel> caseMoniteringSheetModelList = new ArrayList<CICLCaseMoniteringSheetModel>();
		typeMap = getTypeMap();
		
		ciclCaseMoniteringSheetList = ciclCaseMonitoringSheetRepository.getByChildId(childId);
		
		if(ciclCaseMoniteringSheetList != null && ciclCaseMoniteringSheetList.size() > 0){
			for(CICLCaseMoniteringSheet ciclCaseMoniteringSheet : ciclCaseMoniteringSheetList){
				CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel = new CICLCaseMoniteringSheetModel();
				
				ciclCaseMoniteringSheetModel.setAgeDeterminationActualDate(ciclCaseMoniteringSheet.getAgeDeterminationActualDate());
				ciclCaseMoniteringSheetModel.setAgeDeterminationScheduledDate(ciclCaseMoniteringSheet.getAgeDeterminationScheduledDate());
				ciclCaseMoniteringSheetModel.setAgeDeterminationTime(ciclCaseMoniteringSheet.getAgeDeterminationTime());
				ciclCaseMoniteringSheetModel.setAprehendedDate(ciclCaseMoniteringSheet.getAprehendedDate());
				ciclCaseMoniteringSheetModel.setAprehendedTime(ciclCaseMoniteringSheet.getAprehendedTime());
				ciclCaseMoniteringSheetModel.setBailConsiderationActualDate(ciclCaseMoniteringSheet.getBailConsiderationActualDate());
				ciclCaseMoniteringSheetModel.setBailConsiderationScheduledDate(ciclCaseMoniteringSheet.getBailConsiderationScheduledDate());
				ciclCaseMoniteringSheetModel.setCarePlanActualDate(ciclCaseMoniteringSheet.getCarePlanActualDate());
				ciclCaseMoniteringSheetModel.setCarePlanScheduledDate(ciclCaseMoniteringSheet.getCarePlanScheduledDate());
				ciclCaseMoniteringSheetModel.setCaseName(ciclCaseMoniteringSheet.getCaseName());
				ciclCaseMoniteringSheetModel.setCaseNo(ciclCaseMoniteringSheet.getCaseNo());
				ciclCaseMoniteringSheetModel.setChildAgeDeterminationDate(ciclCaseMoniteringSheet.getChildAgeDeterminationDate());
				ciclCaseMoniteringSheetModel.setChildAgeOnDateOfOffence(ciclCaseMoniteringSheet.getChildAgeOnDateOfOffence());
				ciclCaseMoniteringSheetModel.setChildId(ciclCaseMoniteringSheet.getChildId().getChildId());
				ciclCaseMoniteringSheetModel.setChildWelfareOfficerName(ciclCaseMoniteringSheet.getChildWelfareOfficerName());
				ciclCaseMoniteringSheetModel.setCocBailDate(ciclCaseMoniteringSheet.getCocBailDate());
				ciclCaseMoniteringSheetModel.setCocByWhom(typeMap.get(ciclCaseMoniteringSheet.getCocByWhom()));
				ciclCaseMoniteringSheetModel.setCocByWhomName(ciclCaseMoniteringSheet.getCocByWhomName());
				ciclCaseMoniteringSheetModel.setCocFromDate(ciclCaseMoniteringSheet.getCocFromDate());
				ciclCaseMoniteringSheetModel.setCocToDate(ciclCaseMoniteringSheet.getCocToDate());
				ciclCaseMoniteringSheetModel.setCrpc173ReportActualDate(ciclCaseMoniteringSheet.getCrpc173ReportActualDate());
				ciclCaseMoniteringSheetModel.setCrpc173ReportScheduledDate(ciclCaseMoniteringSheet.getCrpc173ReportScheduledDate());
				ciclCaseMoniteringSheetModel.setCrpc251ReportActualDate(ciclCaseMoniteringSheet.getCrpc251ReportActualDate());
				ciclCaseMoniteringSheetModel.setCrpc251ReportScheduledDate(ciclCaseMoniteringSheet.getCrpc251ReportScheduledDate());
				ciclCaseMoniteringSheetModel.setCrpc281ReportActualDate(ciclCaseMoniteringSheet.getCrpc281ReportActualDate());
				ciclCaseMoniteringSheetModel.setCrpc281ReportScheduledDate(ciclCaseMoniteringSheet.getCrpc281ReportScheduledDate());
				ciclCaseMoniteringSheetModel.setDate(ciclCaseMoniteringSheet.getDate());
				ciclCaseMoniteringSheetModel.setDefenceEvidienceActualDate(ciclCaseMoniteringSheet.getDefenceEvidienceActualDate());
				ciclCaseMoniteringSheetModel.setDefenceEvidienceScheduledDate(ciclCaseMoniteringSheet.getDefenceEvidienceScheduledDate());
				ciclCaseMoniteringSheetModel.setDeterminationBy(typeMap.get(ciclCaseMoniteringSheet.getDeterminationBy()));
				ciclCaseMoniteringSheetModel.setDispositionalOrderActualDate(ciclCaseMoniteringSheet.getDispositionalOrderActualDate());
				ciclCaseMoniteringSheetModel.setDispositionalOrderScheduledDate(ciclCaseMoniteringSheet.getDispositionalOrderScheduledDate());
				ciclCaseMoniteringSheetModel.setDistrict(ciclCaseMoniteringSheet.getDistrict());
				
				ciclCaseMoniteringSheetModel.setEvidenceReliedDocument(ciclCaseMoniteringSheet.getEvidenceReliedDocument() == null ? null :
					exportPDFServiceImpl.getPdf(ciclCaseMoniteringSheet.getEvidenceReliedDocument()));
				
				ciclCaseMoniteringSheetModel.setEvidenceReliedMedicalName(ciclCaseMoniteringSheet.getEvidenceReliedMedicalName());
				ciclCaseMoniteringSheetModel.setFinalArgumentsActualDate(ciclCaseMoniteringSheet.getFinalArgumentsActualDate());
				ciclCaseMoniteringSheetModel.setFinalArgumentsScheduledDate(ciclCaseMoniteringSheet.getFinalArgumentsScheduledDate());
				ciclCaseMoniteringSheetModel.setFirstProductionDate(ciclCaseMoniteringSheet.getFirstProductionDate());
				ciclCaseMoniteringSheetModel.setFirstProductionTime(ciclCaseMoniteringSheet.getFirstProductionTime());
				ciclCaseMoniteringSheetModel.setId(ciclCaseMoniteringSheet.getId());
				ciclCaseMoniteringSheetModel.setIoName(ciclCaseMoniteringSheet.getIoName());
				ciclCaseMoniteringSheetModel.setLawyerName(ciclCaseMoniteringSheet.getLawyerName());
				ciclCaseMoniteringSheetModel.setMedicalExaminationDate(ciclCaseMoniteringSheet.getMedicalExaminationDate());
				ciclCaseMoniteringSheetModel.setNatureOfOffence(typeMap.get(ciclCaseMoniteringSheet.getNatureOfOffence()));
				ciclCaseMoniteringSheetModel.setParentContactnumber(ciclCaseMoniteringSheet.getParentContactnumber());
				ciclCaseMoniteringSheetModel.setPermanentAddress(ciclCaseMoniteringSheet.getPermanentAddress());
				ciclCaseMoniteringSheetModel.setPostDispositionalReviewActualDate(ciclCaseMoniteringSheet.getPostDispositionalReviewActualDate());
				ciclCaseMoniteringSheetModel.setPostDispositionalReviewScheduledDate(ciclCaseMoniteringSheet.getPostDispositionalReviewScheduledDate());
				ciclCaseMoniteringSheetModel.setPresentAddress(ciclCaseMoniteringSheet.getPresentAddress());
				ciclCaseMoniteringSheetModel.setProbationOfficerName(ciclCaseMoniteringSheet.getProbationOfficerName());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceActualDate(ciclCaseMoniteringSheet.getProsecutionEvidienceActualDate());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceActualDate1(ciclCaseMoniteringSheet.getProsecutionEvidienceActualDate1());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceActualDate2(ciclCaseMoniteringSheet.getProsecutionEvidienceActualDate2());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceFromDate(ciclCaseMoniteringSheet.getProsecutionEvidienceFromDate());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceScheduledDate(ciclCaseMoniteringSheet.getProsecutionEvidienceScheduledDate());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceScheduledDate1(ciclCaseMoniteringSheet.getProsecutionEvidienceScheduledDate1());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceScheduledDate2(ciclCaseMoniteringSheet.getProsecutionEvidienceScheduledDate2());
				ciclCaseMoniteringSheetModel.setProsecutionEvidienceToDate(ciclCaseMoniteringSheet.getProsecutionEvidienceToDate());
				ciclCaseMoniteringSheetModel.setReportsOnProvisionActualDate(ciclCaseMoniteringSheet.getReportsOnProvisionActualDate());
				ciclCaseMoniteringSheetModel.setReportsOnProvisionScheduledDate(ciclCaseMoniteringSheet.getReportsOnProvisionScheduledDate());
				ciclCaseMoniteringSheetModel.setSirByProbationOfficerActualDate(ciclCaseMoniteringSheet.getSirByProbationOfficerActualDate());
				ciclCaseMoniteringSheetModel.setSirByProbationOfficerScheduledDate(ciclCaseMoniteringSheet.getSirByProbationOfficerScheduledDate());
				ciclCaseMoniteringSheetModel.setSocialBackgroundActualDate(ciclCaseMoniteringSheet.getSocialBackgroundActualDate());
				ciclCaseMoniteringSheetModel.setSocialBackgroundScheduledDate(ciclCaseMoniteringSheet.getSocialBackgroundScheduledDate());
				ciclCaseMoniteringSheetModel.setSupervision_institution_name(ciclCaseMoniteringSheet.getSupervision_institution_name());
				ciclCaseMoniteringSheetModel.setUnderSection(ciclCaseMoniteringSheet.getUnderSection());
				ciclCaseMoniteringSheetModel.setFormNo(ciclCaseMoniteringSheet.getFormNo());
				ciclCaseMoniteringSheetModel.setCreatedDate(ciclCaseMoniteringSheet.getCreatedDate());
				
				caseMoniteringSheetModelList.add(ciclCaseMoniteringSheetModel);
			}
		}
		
		return caseMoniteringSheetModelList;
	}


//	@Override
//	public CICLSupervisionOrderModel getSupervisionOrderByChildId(String childId) {
//		
//		CICLSupervisionOrder ciclSupervisionOrder = new CICLSupervisionOrder();
//		CICLSupervisionOrderModel ciclSupervisionOrderModel = new CICLSupervisionOrderModel();
//		ciclSupervisionOrder = ciclSupervisionOrderRepository.getByChildId(childId);
//		typeMap = getTypeMap();
//		
//		if(ciclSupervisionOrder != null){
//			ciclSupervisionOrderModel.setChildPlacedPeriod(ciclSupervisionOrder.getChildPlacedPeriod());
//			ciclSupervisionOrderModel.setChildUnderCareOfWhom(null ==  ciclSupervisionOrder.getChildUnderCareOfWhom() ? null :typeMap.get(ciclSupervisionOrder.getChildUnderCareOfWhom()));
//			ciclSupervisionOrderModel.setDateOfOrder(ciclSupervisionOrder.getDateOfOrder());
//			ciclSupervisionOrderModel.setId(ciclSupervisionOrder.getId());
//			ciclSupervisionOrderModel.setSupervisionAuthorityAddress(ciclSupervisionOrder.getSupervisionAuthorityAddress());
//			ciclSupervisionOrderModel.setSupervisionAuthorityName(ciclSupervisionOrder.getSupervisionAuthorityName());
//			ciclSupervisionOrderModel.setFormNo(ciclSupervisionOrder.getFormNo());
//		}
//		
//		return ciclSupervisionOrderModel;
//	}


//	@Override
//	public CICLChildCareInstitutionPendingInquiryModel getCCIinquiryByChildId(String childId) {
//		CICLChildCareInstitutionPendingInquiryModel ciclChildCareInstitutionPendingInquiryModel = new CICLChildCareInstitutionPendingInquiryModel();
//		CICLChildCareInstitutionPendingInquiry ciclChildCareInstitutionPendingInquiry = new CICLChildCareInstitutionPendingInquiry();
//		
//		ciclChildCareInstitutionPendingInquiry = ciclChildCareInstitutionPendingInquiryRepository.getByChildId(childId);
//		
//		if(ciclChildCareInstitutionPendingInquiry != null){
//			ciclChildCareInstitutionPendingInquiryModel.setAddressOfChild(ciclChildCareInstitutionPendingInquiry.getAddressOfChild());
//			ciclChildCareInstitutionPendingInquiryModel.setDuration(ciclChildCareInstitutionPendingInquiry.getDuration());
//			ciclChildCareInstitutionPendingInquiryModel.setDateOfOrder(ciclChildCareInstitutionPendingInquiry.getDateOfOrder());
//			ciclChildCareInstitutionPendingInquiryModel.setId(ciclChildCareInstitutionPendingInquiry.getId());
//			ciclChildCareInstitutionPendingInquiryModel.setNextDateOfHearing(ciclChildCareInstitutionPendingInquiry.getNextDateOfHearing());
//			ciclChildCareInstitutionPendingInquiryModel.setParentName(ciclChildCareInstitutionPendingInquiry.getParentName());
//			ciclChildCareInstitutionPendingInquiryModel.setFormNo(ciclChildCareInstitutionPendingInquiry.getFormNo());
//		}
//		
//		return ciclChildCareInstitutionPendingInquiryModel;
//	}


	@Override
	public List<Object> fetchAll(String childId) {
		
		List<Object> allRecords = new ArrayList<Object>();
		List<CICLSupervisionOrder> ciList = new ArrayList<CICLSupervisionOrder>();
		
		List<CICLChildCareInstitutionPendingInquiry> ciclChildCareInstitutionPendingInquiriesList  = new ArrayList<CICLChildCareInstitutionPendingInquiry>();
		
		ciList = ciclSupervisionOrderRepository.findAllByChildId(childId);
		ciclChildCareInstitutionPendingInquiriesList = ciclChildCareInstitutionPendingInquiryRepository.findAllByChildId(childId);
		
		if(ciList != null && ciList.size() > 0){
			for(CICLSupervisionOrder ciclSupervisionOrder : ciList){
				CICLSupervisionOrderModel ciclSupervisionOrderModel = new CICLSupervisionOrderModel();
				
				ciclSupervisionOrderModel.setChildPlacedPeriod(ciclSupervisionOrder.getChildPlacedPeriod());
				ciclSupervisionOrderModel.setChildUnderCareOfWhom(null == ciclSupervisionOrder.getChildUnderCareOfWhom() ? null : typeMap.get(ciclSupervisionOrder.getChildUnderCareOfWhom()));
				ciclSupervisionOrderModel.setDateOfOrder(ciclSupervisionOrder.getDateOfOrder());
				ciclSupervisionOrderModel.setId(ciclSupervisionOrder.getId());
				ciclSupervisionOrderModel.setSupervisionAuthorityAddress(ciclSupervisionOrder.getSupervisionAuthorityAddress());
				ciclSupervisionOrderModel.setSupervisionAuthorityName(ciclSupervisionOrder.getSupervisionAuthorityName());
				ciclSupervisionOrderModel.setFormNo(ciclSupervisionOrder.getFormNo());
				
				allRecords.add(ciclSupervisionOrderModel);
			}
		}
		
		if(ciclChildCareInstitutionPendingInquiriesList != null && ciclChildCareInstitutionPendingInquiriesList.size() > 0){
			for(CICLChildCareInstitutionPendingInquiry ciclChildCareInstitutionPendingInquiry : ciclChildCareInstitutionPendingInquiriesList){
				CICLChildCareInstitutionPendingInquiryModel ciclChildCareInstitutionPendingInquiryModel = new CICLChildCareInstitutionPendingInquiryModel();
				
				ciclChildCareInstitutionPendingInquiryModel.setAddressOfChild(ciclChildCareInstitutionPendingInquiry.getAddressOfChild());
				ciclChildCareInstitutionPendingInquiryModel.setDuration(ciclChildCareInstitutionPendingInquiry.getDuration());
				ciclChildCareInstitutionPendingInquiryModel.setDateOfOrder(ciclChildCareInstitutionPendingInquiry.getDateOfOrder());
				ciclChildCareInstitutionPendingInquiryModel.setId(ciclChildCareInstitutionPendingInquiry.getId());
				ciclChildCareInstitutionPendingInquiryModel.setNextDateOfHearing(ciclChildCareInstitutionPendingInquiry.getNextDateOfHearing());
				ciclChildCareInstitutionPendingInquiryModel.setParentName(ciclChildCareInstitutionPendingInquiry.getParentName());
				ciclChildCareInstitutionPendingInquiryModel.setFormNo(ciclChildCareInstitutionPendingInquiry.getFormNo());
				ciclChildCareInstitutionPendingInquiryModel.setDateOfPlacement(ciclChildCareInstitutionPendingInquiry.getDateOfPlacement());
				ciclChildCareInstitutionPendingInquiryModel.setCciObject(null == ciclChildCareInstitutionPendingInquiry.getCciId() ? null : getCCIName(ciclChildCareInstitutionPendingInquiry.getCciId()));
				
				allRecords.add(ciclChildCareInstitutionPendingInquiryModel);
			}
		}
				
		return allRecords;
	}


	

}
