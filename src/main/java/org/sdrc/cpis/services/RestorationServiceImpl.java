package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.domains.RestorationDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.RestorationModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CciUserRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.NotificationRepository;
import org.sdrc.cpis.repository.RestorationRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestorationServiceImpl implements RestorationService {

	@Autowired
	private RestorationRepository restorationRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private CciUserRepository cciUserRepository;
	
	@Autowired
	private StateManager stateManager;
	
	UserDetailModel userDetailModel=null;
	
	@Override
	public void saveRestorationData(RestorationModel restorationModel) {
		userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		RestorationDetails restorationDetails=new RestorationDetails();
		ChildDetails childDetails = childDetailsRepository.findChildById(restorationModel.getChildId());
//		restorationDetails.setChildOrderPlacedIn(null==restorationModel.getChildOrderPlacedIn() ? null:restorationModel.getChildOrderPlacedIn().getId());
		restorationDetails.setAuthorityIncharge(null==restorationModel.getAuthorityIncharge() ? null : restorationModel.getAuthorityIncharge());
		restorationDetails.setDischargeReason(null==restorationModel.getDischargeReason()?null : restorationModel.getDischargeReason());
		restorationDetails.setInstitutionDistrict(null==restorationModel.getInstitutionDistrict() ? null : restorationModel.getInstitutionDistrict().getAreaId());
		restorationDetails.setOrderDate(null==restorationModel.getOrderDate() ? null : restorationModel.getOrderDate());
		restorationDetails.setChildOrderPlacedIn(null==restorationModel.getChildOrderPlacedIn() ? null : restorationModel.getChildOrderPlacedIn().getId());
		restorationDetails.setPlacedDate(null==restorationModel.getPlacedDate() ? null : restorationModel.getPlacedDate());
		restorationDetails.setPlaceOfOrder(null==restorationModel.getPlaceOfOrder() ? null : restorationModel.getPlaceOfOrder());
		restorationDetails.setPresentInstitution(null==restorationModel.getPresentInstitution() ? null : restorationModel.getPresentInstitution());
		restorationDetails.setSection(null==restorationModel.getSection() ? null : restorationModel.getSection());
		restorationDetails.setTimePeriod(null==restorationModel.getTimePeriod() ? null : restorationModel.getTimePeriod());
		restorationDetails.setChildId(childDetailsRepository.findChildById(restorationModel.getChildId()));
		childDetails.setFinalOrderFilled(1);
		childDetails.setDateOfRestoration(restorationModel.getPlacedDate());
		restorationDetails.setCreatedBy(userDetailModel.getUserName());
		restorationDetails.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		restorationDetails.setUserIp(userDetailModel.getUserIp());
		
		childDetailsRepository.save(childDetails);
		restorationRepository.save(restorationDetails);
		
		/*Sending notifications to district level authorities*/
		List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(childDetails.getChildDistrict().getAreaId());
		List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
		
		CciUserMapping cciUserMapping = null != childDetails.getCciDetails() ? cciUserRepository.findByCciId(childDetails.getCciDetails().getCciId()) : null;
		Integer divisionId=areaRepository.fetchAreaById(childDetails.getChildDistrict().getParentArea().getAreaId()).getAreaId();
		if(cciUserMapping!= null) {
			NotificationDetails notificationDetails = new NotificationDetails();
			notificationDetails.setChildId(childDetails.getChildId());
			notificationDetails.setDistrictId(childDetails.getChildDistrict().getAreaId());
			notificationDetails.setDivisionId(divisionId);
			notificationDetails.setRecipentId(cciUserMapping.getUserId());
			notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
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
			notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
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

	@Override
	public RestorationModel getAllRestorationData(String childId) {
		RestorationModel restorationModel = new RestorationModel();
		RestorationDetails restorationDatas	= restorationRepository.findByChildIdChildId(childId);
		AreaDetails areaDetails = new AreaDetails();
		
		if (restorationDatas != null) {

			if (restorationDatas.getInstitutionDistrict() != null) {
				areaDetails = areaRepository.fetchAreaById(restorationDatas
						.getInstitutionDistrict());

				AreaDetailsModel areaDetailsModel = new AreaDetailsModel();

				areaDetailsModel.setAreaCode(areaDetails.getAreaCode());
				areaDetailsModel.setAreaId(areaDetails.getAreaId());
				// areaDetailsModel.setAreaLevel(areaDetails.getAreaLevel());
				areaDetailsModel.setAreaName(areaDetails.getAreaName());

				restorationModel.setInstitutionDistrict(areaDetailsModel);
			}
			restorationModel.setId(restorationDatas.getId());
			restorationModel.setAuthorityIncharge(restorationDatas.getAuthorityIncharge());
			restorationModel.setDischargeReason(restorationDatas.getDischargeReason().toString());
			restorationModel.setOrderDate(restorationDatas.getOrderDate());
			restorationModel.setPlaceOfOrder(restorationDatas.getPlaceOfOrder());
			restorationModel.setPresentInstitution(restorationDatas.getPresentInstitution());
			restorationModel.setSection(restorationDatas.getSection());
			restorationModel.setTimePeriod(restorationDatas.getTimePeriod());
			restorationModel.setPlacedDate(restorationDatas.getPlacedDate());
			restorationModel.setChildOrderPlacedInId(restorationDatas.getChildOrderPlacedIn());
		}
		
		return restorationModel;
	}

}
