package org.sdrc.cpis.services;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.LegallyFreeForAdoption;
import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.LegallyFreeForAdoptionModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CciUserRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.LegallyFreeForAdoptionRepository;
import org.sdrc.cpis.repository.NotificationRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class LegallyFreeForAdoptionServiceImpl implements LegallyFreeForAdoptionService 
{

	@Autowired
	LegallyFreeForAdoptionRepository legallyFreeForAdoptionRepository;
	 
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private CciUserRepository cciUserRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	public Map<Integer, ValueObject> getTypeMap(){
		List<CCTSTypeDetails> typeDetails=  cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails)
		{
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			
			map.put(cctsTypeDetails.getTypeDetailsId(),obj);
		}
			return map;
		}
	
	
	// insert
	
	@Transactional
	@Override
	public String saveLegallyFreeForAdoptionData(LegallyFreeForAdoptionModel legallyFreeForAdoptionModel) throws Exception {
		String path = applicationMessageSource.getMessage("store.legallyFreeForAdaption", null, null,null);
		if(legallyFreeForAdoptionModel != null)
		{
			
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			LegallyFreeForAdoption legallyFreeForAdoption=new LegallyFreeForAdoption();
			ChildDetails childDetails = childDetailsRepository.findChildById(legallyFreeForAdoptionModel.getChildId());
			
			legallyFreeForAdoption.setDateOfBirth(legallyFreeForAdoptionModel.getDateOfBirth()== null ? null : new Date(legallyFreeForAdoptionModel.getDateOfBirth().getTime()));
			legallyFreeForAdoption.setOrderDate(legallyFreeForAdoptionModel.getOrderDate()== null ? null :new Date(legallyFreeForAdoptionModel.getOrderDate().getTime()));
			
			legallyFreeForAdoption.setSaaOrCciId(legallyFreeForAdoptionModel.getSaaOrCciId()== null ? null :legallyFreeForAdoptionModel.getSaaOrCciId());
			
			legallyFreeForAdoption.setOrderNo(legallyFreeForAdoptionModel.getOrderNo()== null ? null :legallyFreeForAdoptionModel.getOrderNo());
			
			legallyFreeForAdoption.setInquiryReport(legallyFreeForAdoptionModel.getInquiryReport()==null?
					null:exportPDFServiceImpl.getFileName(legallyFreeForAdoptionModel.getInquiryReport(),"legallyFreeForAdaption", path));
			legallyFreeForAdoption.setSurrenderDeed(legallyFreeForAdoptionModel.getSurrenderDeed()==null?
					null:exportPDFServiceImpl.getFileName(legallyFreeForAdoptionModel.getSurrenderDeed(),"legallyFreeForAdaption", path));
			legallyFreeForAdoption.setDeclaration(legallyFreeForAdoptionModel.getDeclaration()==null?
					null:exportPDFServiceImpl.getFileName(legallyFreeForAdoptionModel.getDeclaration(),"legallyFreeForAdaption", path));
			legallyFreeForAdoption.setChildId(childDetailsRepository.findChildById(legallyFreeForAdoptionModel.getChildId()));
			
			legallyFreeForAdoption.setLegallyFreeDate(legallyFreeForAdoptionModel.getLegallyFreeDate()== null ? null :new Date(legallyFreeForAdoptionModel.getLegallyFreeDate().getTime()));
			legallyFreeForAdoption.setLegallyFreePlace(legallyFreeForAdoptionModel.getLegallyFreePlace()== null ? null :legallyFreeForAdoptionModel.getLegallyFreePlace());
			childDetails.setFinalOrderFilled(1);
			
			childDetailsRepository.save(childDetails);
			legallyFreeForAdoptionRepository.save(legallyFreeForAdoption);
			
			/*Sending notifications to district level authorities*/
			List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(childDetails.getChildDistrict().getAreaId());
			List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
			CciUserMapping cciUserMapping=new CciUserMapping();
			if(childDetails.getCciDetails()!=null)
				cciUserMapping = cciUserRepository.findByCciId(childDetails.getCciDetails().getCciId());
			Integer divisionId=areaRepository.fetchAreaById(childDetails.getChildDistrict().getParentArea().getAreaId()).getAreaId();
			if(cciUserMapping!= null) {
				NotificationDetails notificationDetails = new NotificationDetails();
				notificationDetails.setChildId(childDetails.getChildId());
				notificationDetails.setDistrictId(childDetails.getChildDistrict().getAreaId());
				notificationDetails.setDivisionId(divisionId);
				notificationDetails.setRecipentId(cciUserMapping.getUserId());
				notificationDetails.setDateOfAction(new Date(legallyFreeForAdoptionModel.getLegallyFreeDate().getTime()));
				notificationDetails.setNotificationType("finalorder");
				notificationDetails.setNotificationMsg("A new child has been released from your CCI with Child ID "+childDetails.getChildId());
				notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
				if(childDetails.getProgramType()==0)
					notificationDetails.setCaseType("CNCP");
				else
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
				notificationDetails.setDateOfAction(new Date(legallyFreeForAdoptionModel.getLegallyFreeDate().getTime()));
				notificationDetails.setNotificationType("finalorder");
				notificationDetails.setNotificationMsg("A child has been released with Child ID "+childDetails.getChildId());
				notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
				if(childDetails.getProgramType()==0)
					notificationDetails.setCaseType("CNCP");
				else
					notificationDetails.setCaseType("CICL");
				notificationDetails.setIsActive(true);
				notificationDetails.setIsRead(false);
				
				notifications.add(notificationDetails);
				}
			}
			notificationRepository.save(notifications);
			
			/*Notification part over*/
			
		}
		
		return "saved";
	}


	@Override
	public LegallyFreeForAdoptionModel getAllLegallyFreeForAdoptionData(
			String childId) throws Exception {
		LegallyFreeForAdoptionModel legallyFreeForAdoptionModel = new LegallyFreeForAdoptionModel();
		LegallyFreeForAdoption legallyFreeForAdoption	= legallyFreeForAdoptionRepository.findByChildIdChildId(childId);
		
		if(legallyFreeForAdoption != null){
			legallyFreeForAdoptionModel.setId(legallyFreeForAdoption.getId());
			legallyFreeForAdoptionModel.setDateOfBirth(legallyFreeForAdoption.getDateOfBirth()== null ? null : new Date(legallyFreeForAdoption.getDateOfBirth().getTime()));
			legallyFreeForAdoptionModel.setOrderDate(legallyFreeForAdoption.getOrderDate()== null ? null :new Date(legallyFreeForAdoption.getOrderDate().getTime()));
			legallyFreeForAdoptionModel.setSaaOrCciId(legallyFreeForAdoption.getSaaOrCciId()== null ? null :legallyFreeForAdoption.getSaaOrCciId());
			
			legallyFreeForAdoptionModel.setOrderNo(legallyFreeForAdoption.getOrderNo()== null ? null :legallyFreeForAdoption.getOrderNo());
			
			legallyFreeForAdoptionModel.setInquiryReport(legallyFreeForAdoption.getInquiryReport()==null?null:exportPDFServiceImpl.getPdf(legallyFreeForAdoption.getInquiryReport()));
			legallyFreeForAdoptionModel.setSurrenderDeed(legallyFreeForAdoption.getSurrenderDeed()==null?null:exportPDFServiceImpl.getPdf(legallyFreeForAdoption.getSurrenderDeed()));
			legallyFreeForAdoptionModel.setDeclaration(legallyFreeForAdoption.getDeclaration()==null?null:exportPDFServiceImpl.getPdf(legallyFreeForAdoption.getDeclaration()));
			
			legallyFreeForAdoptionModel.setLegallyFreeDate(legallyFreeForAdoption.getLegallyFreeDate()== null ? null :new Date(legallyFreeForAdoption.getLegallyFreeDate().getTime()));
			legallyFreeForAdoptionModel.setLegallyFreePlace(legallyFreeForAdoption.getLegallyFreePlace()== null ? null :legallyFreeForAdoption.getLegallyFreePlace());
		}
		
		return legallyFreeForAdoptionModel;
	}


}
