package org.sdrc.cpis.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.AfterCarePlacementOrder;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CaseSummaryByCWC;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.RehabilationEscortOrder;
import org.sdrc.cpis.domains.SponsorshipOrder;
import org.sdrc.cpis.models.AfterCarePlacementOrderModel;
import org.sdrc.cpis.models.CaseSummaryCWCModel;
import org.sdrc.cpis.models.OrderModel;
import org.sdrc.cpis.models.SponsorshipModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AfterCarePlacementOrderRepository;
import org.sdrc.cpis.repository.CCTSChildRegistrationRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLSocialBackgroundReportRepository;
import org.sdrc.cpis.repository.CaseSummaryCWCRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.RehabilitationEscortDetailsRepository;
import org.sdrc.cpis.repository.SponsorshipRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalOrderServiceImplementation implements FinalOrderService {
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private CaseSummaryCWCRepository caseSummaryCWCRepository;
	
	@Autowired
	private CCTSChildRegistrationRepository childRegistrationRepository;
	
	@Autowired
	private SponsorshipRepository sponsorshipRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private AfterCarePlacementOrderRepository afterCarePlacementOrderRepository;
	
	@Autowired
	private RehabilitationEscortDetailsRepository rehabilitationEscortDetailsRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private CCTSChildRegistrationService cctsChildRegistrationService;
	
	@Autowired
	private CICLSocialBackgroundReportRepository ciclSocialBackgroundReportRepository;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
Map<Integer, ValueObject> typeMap;

UserDetailModel userDetailModel=null;
	
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
	
	@Transactional
	@Override
	public void saveCaseSummaryCWCData(CaseSummaryCWCModel caseSummaryCWCModel) throws Exception {
		
		userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		CaseSummaryByCWC caseSummaryByCWC = new CaseSummaryByCWC();
		
//		if(caseSummaryCWCModel.getChildId() != null){
			caseSummaryByCWC.setAllDetails(caseSummaryCWCModel.isAllDetails());
			caseSummaryByCWC.setCaseHistory(caseSummaryCWCModel.isCaseHistory());
//			caseSummaryByCWC.setCaseNo(null == caseSummaryCWCModel.getCaseNo() ? null : caseSummaryCWCModel.getCaseNo());
			caseSummaryByCWC.setCaseRecord(null == caseSummaryCWCModel.getCaseRecord() ? null : caseSummaryCWCModel.getCaseRecord());
//			caseSummaryByCWC.setChildId(childDetailsRepository.findChildById(caseSummaryCWCModel.getChildId()));
			caseSummaryByCWC.setDateOfFormFilled(null == caseSummaryCWCModel.getDateOfFormFilled() ? null : caseSummaryCWCModel.getDateOfFormFilled());
			caseSummaryByCWC.setIndividualCarePlan(caseSummaryCWCModel.isIndividualCarePlan());
//			caseSummaryByCWC.setMedicalRecords(null == caseSummaryCWCModel.getMedicalRecords() ? null : caseSummaryCWCModel.getMedicalRecords());
			caseSummaryByCWC.setMedicalRecords(null ==caseSummaryCWCModel.getMedicalReports()?null: exportPDFServiceImpl.getFileName(caseSummaryCWCModel.getMedicalReports(), "caseSummary", applicationMessageSource.getMessage("store.ChildInFosterCare", null, null,null)));
			caseSummaryByCWC.setOrdersPassedByCWC(null == caseSummaryCWCModel.getOrdersPassedByCWC() ? null : caseSummaryCWCModel.getOrdersPassedByCWC());
			caseSummaryByCWC.setOrdersPassedByCwcOthers(null == caseSummaryCWCModel.getOrdersPassedByCwcOthers() ? null : caseSummaryCWCModel.getOrdersPassedByCwcOthers());
			caseSummaryByCWC.setPlaceOfFormFilled(null == caseSummaryCWCModel.getPlaceOfFormFilled() ? null : caseSummaryCWCModel.getPlaceOfFormFilled());
			caseSummaryByCWC.setRe(null == caseSummaryCWCModel.getRe() ? null : caseSummaryCWCModel.getRe());
			caseSummaryByCWC.setRehabitationCard(caseSummaryCWCModel.isRehabitationCard());
			caseSummaryByCWC.setSocialInvestigationReport(caseSummaryCWCModel.isSocialInvestigationReport());
			caseSummaryByCWC.setParentOrGuardianName(caseSummaryCWCModel.getParentOrGuardianName() == null ? null : caseSummaryCWCModel.getParentOrGuardianName()  );
			caseSummaryByCWC.setChildId(childDetailsRepository.findChildById(caseSummaryCWCModel.getChildId()));
			
			caseSummaryByCWC.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			caseSummaryByCWC.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
			caseSummaryByCWC.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			
			caseSummaryCWCRepository.save(caseSummaryByCWC);
//		}
		
	}

	@Override
	public CaseSummaryCWCModel getAllCaseSummary(String childId) throws Exception {
		CaseSummaryCWCModel caseSummaryCWCModel = new CaseSummaryCWCModel();
		ChildDetails childDetails= childDetailsRepository.findChildById(childId);
		CaseSummaryByCWC caseSummaryByCWCdatas = caseSummaryCWCRepository.findByChildIdChildId(childId);
		Date dateOfProduction = childDetails.getProgramType() == 1 ? ciclSocialBackgroundReportRepository.findByChildIdChildId(childId).getEntryDate() : childRegistrationRepository.findByChildIdChildId(childId).getDateOfProduction();
		if(caseSummaryByCWCdatas != null){
			caseSummaryCWCModel.setId(caseSummaryByCWCdatas.getId());
			caseSummaryCWCModel.setAllDetails(caseSummaryByCWCdatas.isAllDetails());
			caseSummaryCWCModel.setCaseRecord(caseSummaryByCWCdatas.getCaseRecord());
			caseSummaryCWCModel.setCaseNo(caseSummaryByCWCdatas.getChildId().getCaseNum());
			caseSummaryCWCModel.setChildId(caseSummaryByCWCdatas.getChildId().getChildId());
			caseSummaryCWCModel.setChildName(caseSummaryByCWCdatas.getChildId().getChildName());
			caseSummaryCWCModel.setTodayDate(new Date(new java.util.Date().getTime()));
			caseSummaryCWCModel.setChildProducedDate(dateOfProduction);
			caseSummaryCWCModel.setParentOrGuardianName(caseSummaryByCWCdatas.getParentOrGuardianName());
			caseSummaryCWCModel.setDateOfFormFilled(caseSummaryByCWCdatas.getDateOfFormFilled());
			caseSummaryCWCModel.setIndividualCarePlan(caseSummaryByCWCdatas.isIndividualCarePlan());
			caseSummaryCWCModel.setOrdersPassedByCWC(caseSummaryByCWCdatas.getOrdersPassedByCWC());
			caseSummaryCWCModel.setOrdersPassedByCwcOthers(caseSummaryByCWCdatas.getOrdersPassedByCwcOthers());
			caseSummaryCWCModel.setPlaceOfFormFilled(caseSummaryByCWCdatas.getPlaceOfFormFilled());
			caseSummaryCWCModel.setRe(caseSummaryByCWCdatas.getRe());
			caseSummaryCWCModel.setRehabitationCard(caseSummaryByCWCdatas.isRehabitationCard());
			caseSummaryCWCModel.setSocialInvestigationReport(caseSummaryByCWCdatas.isSocialInvestigationReport());
			caseSummaryCWCModel.setMedicalReports(null==caseSummaryByCWCdatas.getMedicalRecords()?null:exportPDFServiceImpl.getPdf(caseSummaryByCWCdatas.getMedicalRecords()));
			
		}
		return caseSummaryCWCModel;
	}

	@Override
	public void saveSponsorshipOrder(SponsorshipModel sponsorshipModel) {
		userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		SponsorshipOrder sponsorshipOrder = new SponsorshipOrder();
		
		sponsorshipOrder.setAccountName(sponsorshipModel.getAccountName());
		sponsorshipOrder.setChildId(childDetailsRepository.findChildById(sponsorshipModel.getChildId()));
		sponsorshipOrder.setDaysOrMonths(sponsorshipModel.getDaysOrMonths());
		sponsorshipOrder.setOperatedBy(sponsorshipModel.getOperatedBy());
		sponsorshipOrder.setSponsorshipAmount(sponsorshipModel.getSponsorshipAmount());
		sponsorshipOrder.setSponsorshipFor(sponsorshipModel.getSponsorshipFor().getId());
		sponsorshipOrder.setSponsorshipTime(sponsorshipModel.getSponsorshipTime());
		sponsorshipOrder.setSponsorshipPeriod(sponsorshipModel.getSponsorshipPeriod());
		sponsorshipOrder.setSponsorshipType(sponsorshipModel.getTypeOfSponsorship());
		sponsorshipOrder.setOtherSponsorshipFor(sponsorshipModel.getOtherSponsorshipFor());
		sponsorshipOrder.setCreatedBy(userDetailModel.getUserName());
		sponsorshipOrder.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		sponsorshipOrder.setUserIp(userDetailModel.getUserIp());
		
		sponsorshipRepository.save(sponsorshipOrder);
	}

	@Override
	public void saveAfterCareData(AfterCarePlacementOrderModel afterCarePlacementOrderModel) {
		AfterCarePlacementOrder afterCarePlacementOrder = new AfterCarePlacementOrder();
		userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		afterCarePlacementOrder.setChildId(childDetailsRepository.findChildById(afterCarePlacementOrderModel.getChildId()));
		afterCarePlacementOrder.setCompleting18On(null == afterCarePlacementOrderModel.getCompleting18On() ? null : afterCarePlacementOrderModel.getCompleting18On());
		afterCarePlacementOrder.setPurposeOfRehabilitation(null == afterCarePlacementOrderModel.getPurposeOfRehabilitation() ? null : afterCarePlacementOrderModel.getPurposeOfRehabilitation());
		afterCarePlacementOrder.setOrganizationName(null == afterCarePlacementOrderModel.getOrganizationName() ? null : afterCarePlacementOrderModel.getOrganizationName());
		afterCarePlacementOrder.setAmountReleased(null == afterCarePlacementOrderModel.getAmountReleased() ? null : afterCarePlacementOrderModel.getAmountReleased());
		afterCarePlacementOrder.setPeriod(null == afterCarePlacementOrderModel.getPeriod() ? null : afterCarePlacementOrderModel.getPeriod());
		afterCarePlacementOrder.setNameOfAccountHolder(null == afterCarePlacementOrderModel.getNameOfAccountHolder() ? null : afterCarePlacementOrderModel.getNameOfAccountHolder());
		afterCarePlacementOrder.setUserIp(userDetailModel.getUserIp());
		afterCarePlacementOrder.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		afterCarePlacementOrder.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		afterCarePlacementOrderRepository.save(afterCarePlacementOrder);
	}
	
	@Override
	public SponsorshipModel getSponsorshipData(String childId) {
		SponsorshipOrder sponsorshipOrder = sponsorshipRepository.getSponsorshipDataByChild(childId);
		SponsorshipModel sponsorshipModel = new SponsorshipModel();
		
		if(sponsorshipOrder != null){
			sponsorshipModel.setAccountName(sponsorshipOrder.getAccountName());
			sponsorshipModel.setDaysOrMonths(sponsorshipOrder.getDaysOrMonths());
			sponsorshipModel.setOperatedBy(sponsorshipOrder.getOperatedBy());
			sponsorshipModel.setSponsorshipAmount(sponsorshipOrder.getSponsorshipAmount());
			sponsorshipModel.setSponsorshipFor(getTypeMap().get(sponsorshipOrder.getSponsorshipFor()));
			sponsorshipModel.setSponsorshipPeriod(sponsorshipOrder.getSponsorshipPeriod());
			sponsorshipModel.setTypeOfSponsorship(sponsorshipOrder.getSponsorshipType());
			sponsorshipModel.setOtherSponsorshipFor(sponsorshipOrder.getOtherSponsorshipFor());
			sponsorshipModel.setId(sponsorshipOrder.getId());
		}
		
		return sponsorshipModel;
	}
	
	
	@SuppressWarnings("unused")
	@Transactional
	@Override
	public boolean savePdf(OrderModel orderModel) throws Exception {
		userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ChildDetails childDetails = childDetailsRepository.findChildById(orderModel.getChildId());
		childDetails.setId(childDetails.getId());
		RehabilationEscortOrder childRehabilationEscortOrder = rehabilitationEscortDetailsRepository.findByChildId(orderModel.getChildId());
		if(childRehabilationEscortOrder==null){
			if(orderModel != null || orderModel.getData()==""){
				
				RehabilationEscortOrder rehabilationEscortOrder = new RehabilationEscortOrder();
				rehabilationEscortOrder.setChildId(childDetailsRepository.findChildById(orderModel.getChildId()));
				rehabilationEscortOrder.setEscortOrderPath(orderModel.getType().equals("escortOrder")?getSavedPath(orderModel, orderModel.getType()):null);
				rehabilationEscortOrder.setRehabilitationCardPath(orderModel.getType().equals("rehabilitationCard")?getSavedPath(orderModel, orderModel.getType()):null);
				rehabilationEscortOrder.setUserIp(userDetailModel.getUserIp());
				rehabilationEscortOrder.setCreatedBy(userDetailModel.getUserName());
				rehabilationEscortOrder.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
				
				rehabilitationEscortDetailsRepository.save(rehabilationEscortOrder);
				childDetails.setRehabilitationCardFilled(1);;
				childDetailsRepository.save(childDetails);
				return true;
			} 
		} else{
			if(orderModel != null || orderModel.getData()==""){
				RehabilationEscortOrder rehabilationEscortOrder = childRehabilationEscortOrder;
				rehabilationEscortOrder.setEscortOrderPath(orderModel.getType().equals("escortOrder")?getSavedPath(orderModel, orderModel.getType()):childRehabilationEscortOrder.getEscortOrderPath());
				rehabilationEscortOrder.setRehabilitationCardPath(orderModel.getType().equals("rehabilitationCard")?getSavedPath(orderModel, orderModel.getType()):childRehabilationEscortOrder.getRehabilitationCardPath());
				rehabilationEscortOrder.setUpdatedBy(userDetailModel.getUserName());
				rehabilationEscortOrder.setUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));
				rehabilationEscortOrder.setUserIp(userDetailModel.getUserIp());
				
				rehabilitationEscortDetailsRepository.save(rehabilationEscortOrder);
				childDetails.setRehabilitationCardFilled(1);;
				childDetailsRepository.save(childDetails);
				return true;
			} 
		}
		return false;
	}

	private String getSavedPath(OrderModel orderModel, String type) throws Exception {
		String path = applicationMessageSource.getMessage("store.pdfPath", null, null,null);
		String data  = orderModel.getData();
		return exportPDFServiceImpl.getFileName(data, type, path);
	}

	@Override
	public OrderModel getChildEscortAndRehab(String childId) {
		RehabilationEscortOrder rehabilationEscortOrder = rehabilitationEscortDetailsRepository.findByChildId(childId);
		OrderModel orderModel = null;
		if(rehabilationEscortOrder!=null){
			orderModel = new OrderModel();
			orderModel.setChildId(childId);
			orderModel.setData(rehabilationEscortOrder.getEscortOrderPath()==null?null:rehabilationEscortOrder.getEscortOrderPath().split("/")[3]);
			orderModel.setType(rehabilationEscortOrder.getRehabilitationCardPath()==null?null:rehabilationEscortOrder.getRehabilitationCardPath().split("/")[3]);
		}		
		return orderModel;
	}

	@Override
	public AfterCarePlacementOrderModel getAfterCareData(String childId) {
		AfterCarePlacementOrder afterCarePlacementOrder=afterCarePlacementOrderRepository.getChildWiseAfterCare(childId);
		AfterCarePlacementOrderModel afterCarePlacementOrderModel=new AfterCarePlacementOrderModel();
		
		if(afterCarePlacementOrder != null){
			afterCarePlacementOrderModel.setAmountReleased(afterCarePlacementOrder.getAmountReleased());
			afterCarePlacementOrderModel.setChildId(childId);
			afterCarePlacementOrderModel.setCompleting18On(afterCarePlacementOrder.getCompleting18On());
			afterCarePlacementOrderModel.setNameOfAccountHolder(afterCarePlacementOrder.getNameOfAccountHolder());
			afterCarePlacementOrderModel.setId(afterCarePlacementOrder.getId());
			afterCarePlacementOrderModel.setOrganizationName(afterCarePlacementOrder.getOrganizationName());
			afterCarePlacementOrderModel.setPeriod(afterCarePlacementOrder.getPeriod());
			afterCarePlacementOrderModel.setPurposeOfRehabilitation(afterCarePlacementOrder.getPurposeOfRehabilitation());
		}
		
		return afterCarePlacementOrderModel;
	}
}
