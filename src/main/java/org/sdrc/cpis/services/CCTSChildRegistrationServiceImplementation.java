package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.ChildRegistrationDetails;
import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.CCTSChildRegistrationModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSChildRegistrationRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
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
public class CCTSChildRegistrationServiceImplementation implements CCTSChildRegistrationService {
	
	@Autowired 
	private CCTSChildRegistrationRepository cctsChildRegistrationRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;

	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	Map<Integer, ValueObject> typeMap;
	Map<Integer, ValueObject> areaMap ;
	Map<Integer, ValueObject> userMap ;
	
	public Map<Integer, ValueObject> getTypeMap(){
	    List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(null != cctsTypeDetails.getTypeDetailsNameHindi() ? cctsTypeDetails.getTypeDetailsNameHindi() : null);
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
	return map;
	}
	public Map<Integer, ValueObject> getAreaMap(){
	    List<AreaDetails> typeAreaDetails= areaRepository.getAllAreaDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (AreaDetails areaDetails : typeAreaDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(areaDetails.getAreaId());
			obj.setName(areaDetails.getAreaName());
			map.put(areaDetails.getAreaId(), obj);
		}
	return map;
	}
	public Map<Integer, ValueObject> getUserMap(){
	    List<UserDetails> userDetails = userDetailsRepository.getAllUserDetails();
	Map<Integer, ValueObject> map=new HashMap<>();
		for (UserDetails cctsUserDetail : userDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsUserDetail.getUserId());
			obj.setName(cctsUserDetail.getCwcName());
			map.put(cctsUserDetail.getUserId(), obj);
		}
	return map;
	}
	
	@Transactional
	@Override
	public String saveChildRegistrationDetails(
			CCTSChildRegistrationModel cctsChildRegistrationModel) throws Exception {
		
		Integer lastChildId = 0;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ChildRegistrationDetails childRegistrationDetails = new ChildRegistrationDetails();
		ChildDetails childDetails = new ChildDetails();
		ChildDetails lastChildDetails = new ChildDetails();
		lastChildId = childDetailsRepository.findLastRecord();

		if(lastChildId == null){
			lastChildId = 1;
		}
		else{
			lastChildId = lastChildId + 1;
		}
//		For inserting in child details table
		AreaDetails areadetails = areaRepository.fetchAreaById(cctsChildRegistrationModel.getChildProducedPlace());
		
		String childId = String.valueOf(lastChildId) + "-" + areadetails.getAreaName()+ "-" + String.valueOf(year);
		childDetails.setAge(null == cctsChildRegistrationModel.getChildAge() ? null : cctsChildRegistrationModel.getChildAge());
		childDetails.setCurrentAge(null == cctsChildRegistrationModel.getChildAge() ? null : cctsChildRegistrationModel.getChildAge()+2);
		childDetails.setChildDistrict(areadetails);
		childDetails.setChildName(null == cctsChildRegistrationModel.getChildName() ? null : cctsChildRegistrationModel.getChildName());
		childDetails.setChildId(childId);
		childDetails.setChildSex(null == cctsChildRegistrationModel.getChildSex() ? null : cctsChildRegistrationModel.getChildSex());
		childDetails.setCaseNum(null == cctsChildRegistrationModel.getCaseNo() ? null : cctsChildRegistrationModel.getCaseNo());
		childDetails.setProgramType(0);
		childDetails.setRecordCreatedOn(new java.sql.Date(new Date().getTime()));
		childDetails.setCwcId(cctsChildRegistrationModel.getChildWelfareCommittee());
		childDetails.setChildPhoto(exportPDFServiceImpl.getPhotoPath(cctsChildRegistrationModel.getChildImage(),childId,"childRegistration"));
		childDetails.setFinalOrderFilled(0);
		childDetails.setAdhaarNo(cctsChildRegistrationModel.getAdhaarCardNo());
		ChildDetails child=childDetailsRepository.save(childDetails);
		
		
		childRegistrationDetails.setAllegationByChild(null == cctsChildRegistrationModel.getAllegationByChild() ? null : cctsChildRegistrationModel.getAllegationByChild());
		childRegistrationDetails.setBelongingsOfTheChildAtTheTimeOfProduction(null == cctsChildRegistrationModel.getChildBelongings() ? null : cctsChildRegistrationModel.getChildBelongings());
		childRegistrationDetails.setCaseNo(null == cctsChildRegistrationModel.getCaseNo() ? null : cctsChildRegistrationModel.getCaseNo());
		childRegistrationDetails.setChildAge(null == cctsChildRegistrationModel.getChildAge() ? null : cctsChildRegistrationModel.getChildAge());
		childRegistrationDetails.setChildIdentityMark(null == cctsChildRegistrationModel.getChildIdentityMarks() ? null : cctsChildRegistrationModel.getChildIdentityMarks());
		childRegistrationDetails.setChildLanguage(null == cctsChildRegistrationModel.getChildLanguageUsed() ? null : cctsChildRegistrationModel.getChildLanguageUsed());
		childRegistrationDetails.setChildName(null == cctsChildRegistrationModel.getChildName() ? null : cctsChildRegistrationModel.getChildName());
		childRegistrationDetails.setChildSex(null == cctsChildRegistrationModel.getChildSex() ? null : cctsChildRegistrationModel.getChildSex());
		childRegistrationDetails.setCircumstancesUnderWhichChildFound(null == cctsChildRegistrationModel.getChildCircumstancesWhenFound() ? null : cctsChildRegistrationModel.getChildCircumstancesWhenFound());
		childRegistrationDetails.setDateChildCameToCCI_SAA(null == cctsChildRegistrationModel.getChildCameToCCIDate()? null : cctsChildRegistrationModel.getChildCameToCCIDate());
		childRegistrationDetails.setTimeChildCameToCCI_SAA(null == cctsChildRegistrationModel.getChildCameToCCITime()? null : cctsChildRegistrationModel.getChildCameToCCITime());
		childRegistrationDetails.setDateOfProduction(null == cctsChildRegistrationModel.getChildProducedDate() ? null : cctsChildRegistrationModel.getChildProducedDate());
		childRegistrationDetails.setImmediateEffortsToTraceFamily(null == cctsChildRegistrationModel.getImmediateEffortsToTraceFamily() ? null : cctsChildRegistrationModel.getImmediateEffortsToTraceFamily());
		childRegistrationDetails.setMedicalTreatmentProvidedToChild(null == cctsChildRegistrationModel.getMedicalTreatment() ? null : cctsChildRegistrationModel.getMedicalTreatment());
		childRegistrationDetails.setNameOfTheOrganization_CCI_SAA(null == cctsChildRegistrationModel.getOrganizationCCISAAName() ? null : cctsChildRegistrationModel.getOrganizationCCISAAName());
		childRegistrationDetails.setParentOrGuardianAddress(null == cctsChildRegistrationModel.getParentAddress() ? null : cctsChildRegistrationModel.getParentAddress());
		childRegistrationDetails.setParentOrGuardianAge(null == cctsChildRegistrationModel.getParentAge() ? null : cctsChildRegistrationModel.getParentAge());
		childRegistrationDetails.setParentOrGuardianContact(null == cctsChildRegistrationModel.getParentContactNo() ? null : cctsChildRegistrationModel.getParentContactNo());
		childRegistrationDetails.setParentOrGuardianName(null == cctsChildRegistrationModel.getParentName() ? null : cctsChildRegistrationModel.getParentName());
		childRegistrationDetails.setParentOrGuardianOccupation(null == cctsChildRegistrationModel.getParentOccupation() ? null : cctsChildRegistrationModel.getParentOccupation());
		childRegistrationDetails.setPersonWhoProduced_occupation_or_designation(null == cctsChildRegistrationModel.getPersonProducingChildOccupation() ? null : cctsChildRegistrationModel.getPersonProducingChildOccupation());
		childRegistrationDetails.setPersonWhoProducedAddress(null == cctsChildRegistrationModel.getPersonProducingChildAddress() ? null : cctsChildRegistrationModel.getPersonProducingChildAddress());
		childRegistrationDetails.setPersonWhoProducedAge(null == cctsChildRegistrationModel.getPersonProducingChildAge() ? null : cctsChildRegistrationModel.getPersonProducingChildAge());
		childRegistrationDetails.setPersonWhoProducedContactNumber(null == cctsChildRegistrationModel.getPersonProducingChildContactNo() ? null : cctsChildRegistrationModel.getPersonProducingChildContactNo());
		childRegistrationDetails.setPersonWhoProducedName(null == cctsChildRegistrationModel.getPersonProducingChildName() ? null : cctsChildRegistrationModel.getPersonProducingChildName());
		childRegistrationDetails.setPersonWhoProducedSex(null == cctsChildRegistrationModel.getPersonProducingChildSex() ? null : cctsChildRegistrationModel.getPersonProducingChildSex());
		childRegistrationDetails.setPhysicalConditionOfCchild(null == cctsChildRegistrationModel.getPhysicalConditionOfChild() ? null : cctsChildRegistrationModel.getPhysicalConditionOfChild());
		childRegistrationDetails.setPlaceOfProduction(null == cctsChildRegistrationModel.getChildProducedPlace() ? null : cctsChildRegistrationModel.getChildProducedPlace());
		childRegistrationDetails.setPlaceWhereChildFound(null == cctsChildRegistrationModel.getChildFoundPlace() ? null : cctsChildRegistrationModel.getChildFoundPlace());
		childRegistrationDetails.setPoliceInformed(null == cctsChildRegistrationModel.getPoliceInformed() ? null : cctsChildRegistrationModel.getPoliceInformed());
		childRegistrationDetails.setProducedBeforeTheCWCName(null == cctsChildRegistrationModel.getChildWelfareCommittee() ? null : cctsChildRegistrationModel.getChildWelfareCommittee());
		childRegistrationDetails.setTimeOfProduction(null == cctsChildRegistrationModel.getChildProducedTime() ? null : cctsChildRegistrationModel.getChildProducedTime());
		childRegistrationDetails.setWithWhomChildFoundAddress(null == cctsChildRegistrationModel.getWithWhomChildFoundAddress() ? null : cctsChildRegistrationModel.getWithWhomChildFoundAddress());
		childRegistrationDetails.setWithWhomChildFoundAge(null == cctsChildRegistrationModel.getWithWhomChildFoundAge() ? null : cctsChildRegistrationModel.getWithWhomChildFoundAge());
		childRegistrationDetails.setWithWhomChildFoundContact(null == cctsChildRegistrationModel.getWithWhomChildFoundContactNo() ? null : cctsChildRegistrationModel.getWithWhomChildFoundContactNo());
		childRegistrationDetails.setWithWhomChildFoundName(null == cctsChildRegistrationModel.getWithWhomChildFoundName() ? null : cctsChildRegistrationModel.getWithWhomChildFoundName());
		childRegistrationDetails.setWithWhomChildFoundOccupation(null == cctsChildRegistrationModel.getWithWhomChildFoundOccupation() ? null : cctsChildRegistrationModel.getWithWhomChildFoundOccupation());
		childRegistrationDetails.setTypeOfOrganization(null == cctsChildRegistrationModel.getTypeOfOrganization() ? null : cctsChildRegistrationModel.getTypeOfOrganization());
		childRegistrationDetails.setChildId(childDetails);
		childRegistrationDetails.setAdhaarCardNo(null == cctsChildRegistrationModel.getAdhaarCardNo()?null:cctsChildRegistrationModel.getAdhaarCardNo());
		
		childRegistrationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		childRegistrationDetails.setCreatedDate(new java.sql.Date(new Date().getTime()));
		childRegistrationDetails.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		childRegistrationDetails.setFormLang(cctsChildRegistrationModel.getFormLang());
		
		
		cctsChildRegistrationRepository.save(childRegistrationDetails);
		
		/*Sending notifications to district level authorities*/
		List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(areadetails.getAreaId());
		List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
		for (UserDetails userDetails : userList) {
			if(userDetails.getDesignation().getDesignationId()==5 || userDetails.getDesignation().getDesignationId()==6 || userDetails.getDesignation().getDesignationId()==9 ) {

			NotificationDetails notificationDetails = new NotificationDetails();
			notificationDetails.setChildId(childDetails.getChildId());
			notificationDetails.setDistrictId(areadetails.getAreaId());
			notificationDetails.setDivisionId(areaRepository.fetchAreaById(areadetails.getParentArea().getAreaId()).getAreaId());
			notificationDetails.setRecipentId(userDetails.getUserId());
			notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
			notificationDetails.setNotificationType("registration");
			notificationDetails.setNotificationMsg("A new child has been registered with Child Id "+child.getChildId());
			notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			notificationDetails.setCaseType("CNCP");
			notificationDetails.setIsActive(true);
			notificationDetails.setIsRead(false);
			
			notifications.add(notificationDetails);
			}
		}
		notificationRepository.save(notifications);
		
		return child.getChildId();
	}

	//	*@Auther Debiprasad
	//  *Email:-debiprasad@sdrc.co.in
	@Override
	public CCTSChildRegistrationModel getChildRegistration(String childId) throws Exception {
		CCTSChildRegistrationModel cCTSChildRegistrationModel = new CCTSChildRegistrationModel();
		ChildRegistrationDetails datas = cctsChildRegistrationRepository.findByChildIdChildId(childId);
		ChildDetails childDetails = childDetailsRepository.findChildById(childId);
		typeMap=getTypeMap();
		areaMap = getAreaMap();
		userMap = getUserMap();
		if(datas!= null){
			cCTSChildRegistrationModel.setChildImage(exportPDFServiceImpl.getChildPhoto(childDetails.getChildPhoto()));
			cCTSChildRegistrationModel.setChildId(datas.getChildId().getChildId());
			cCTSChildRegistrationModel.setAllegationByChild(datas.getAllegationByChild()!= null ? datas.getAllegationByChild() : "" );
			cCTSChildRegistrationModel.setCaseNo(datas.getCaseNo()!= null ? datas.getCaseNo() : "" );
			cCTSChildRegistrationModel.setChildAge(datas.getChildAge()!= null ? datas.getChildAge() : 0 );
			cCTSChildRegistrationModel.setChildBelongings(datas.getBelongingsOfTheChildAtTheTimeOfProduction()!= null ? datas.getBelongingsOfTheChildAtTheTimeOfProduction() : "" );
			cCTSChildRegistrationModel.setChildCircumstancesWhenFound(datas.getCircumstancesUnderWhichChildFound()!= null ? datas.getCircumstancesUnderWhichChildFound() : "" );
			cCTSChildRegistrationModel.setChildFoundPlace(datas.getPlaceWhereChildFound()!= null ? datas.getPlaceWhereChildFound() : "" );	
			cCTSChildRegistrationModel.setChildIdentityMarks(datas.getChildIdentityMark()!= null ? datas.getChildIdentityMark() : "");
			cCTSChildRegistrationModel.setChildLanguageUsed(datas.getChildLanguage()!= null ? datas.getChildLanguage() : "");
			cCTSChildRegistrationModel.setChildName(datas.getChildName()!= null ? datas.getChildName() :"");
			cCTSChildRegistrationModel.setChildCameToCCIDate(datas.getDateChildCameToCCI_SAA()!=null ?datas.getDateChildCameToCCI_SAA() : null);
			cCTSChildRegistrationModel.setChildCameToCCITime(datas.getTimeChildCameToCCI_SAA()!=null ?  datas.getTimeChildCameToCCI_SAA() : null);
			cCTSChildRegistrationModel.setChildProducedDate(datas.getDateOfProduction() !=null ?  datas.getDateOfProduction() : null);
			cCTSChildRegistrationModel.setChildProducedTime(datas.getTimeOfProduction() !=null ?  datas.getTimeOfProduction() : null);
			cCTSChildRegistrationModel.setNameOfChildProducedPlace(areaMap.get(datas.getPlaceOfProduction()) == null ? null : areaMap.get(datas.getPlaceOfProduction()).getName() );
			cCTSChildRegistrationModel.setChildProducedPlace(datas.getPlaceOfProduction()!= null ? datas.getPlaceOfProduction() : null );
			cCTSChildRegistrationModel.setChildSex(datas.getChildSex());
			cCTSChildRegistrationModel.setChildSexType(typeMap.get(datas.getChildSex())== null ? null :typeMap.get(datas.getChildSex()).getName() );
			cCTSChildRegistrationModel.setChildSexObject(typeMap.get(datas.getChildSex()));
			cCTSChildRegistrationModel.setNameOfchildWelfareCommittee(datas.getProducedBeforeTheCWCName() == null ? null : userMap.get(datas.getProducedBeforeTheCWCName()).getName());
			cCTSChildRegistrationModel.setChildWelfareCommittee(datas.getProducedBeforeTheCWCName()!= null ? datas.getProducedBeforeTheCWCName() : null );
			cCTSChildRegistrationModel.setImmediateEffortsToTraceFamily(datas.getImmediateEffortsToTraceFamily()!= null ? datas.getImmediateEffortsToTraceFamily() : "" );
			cCTSChildRegistrationModel.setMedicalTreatment(datas.getMedicalTreatmentProvidedToChild()!= null ? datas.getMedicalTreatmentProvidedToChild() : "" );
			cCTSChildRegistrationModel.setOrganizationCCISAAName(datas.getNameOfTheOrganization_CCI_SAA()!= null ? datas.getNameOfTheOrganization_CCI_SAA() : "" );
			cCTSChildRegistrationModel.setParentAddress(datas.getParentOrGuardianAddress()!= null ? datas.getParentOrGuardianAddress() : "" );
			cCTSChildRegistrationModel.setParentAge(datas.getParentOrGuardianAge()!= null ? datas.getParentOrGuardianAge() :null);
			cCTSChildRegistrationModel.setParentContactNo(datas.getParentOrGuardianContact()!= null ? datas.getParentOrGuardianContact() : "" );
			cCTSChildRegistrationModel.setParentName(datas.getParentOrGuardianName()!= null ? datas.getParentOrGuardianName() : "" );
			cCTSChildRegistrationModel.setParentOccupation(datas.getParentOrGuardianOccupation()!= null ? datas.getParentOrGuardianOccupation() : "" );
			cCTSChildRegistrationModel.setPersonProducingChildAddress(datas.getPersonWhoProducedAddress()!= null ? datas.getPersonWhoProducedAddress() : "" );
			cCTSChildRegistrationModel.setPersonProducingChildAge(datas.getPersonWhoProducedAge()!= null ? datas.getPersonWhoProducedAge() : null);
			cCTSChildRegistrationModel.setPersonProducingChildContactNo(datas.getPersonWhoProducedContactNumber()!= null ? datas.getPersonWhoProducedContactNumber() : "" );
			cCTSChildRegistrationModel.setPersonProducingChildName(datas.getPersonWhoProducedName()!= null ? datas.getPersonWhoProducedName() :"");
			cCTSChildRegistrationModel.setPersonProducingChildOccupation(datas.getPersonWhoProduced_occupation_or_designation()!= null ? datas.getPersonWhoProduced_occupation_or_designation() : "" );
			cCTSChildRegistrationModel.setPersonProducingChildSexType(typeMap.get(datas.getPersonWhoProducedSex()) == null ? null : typeMap.get(datas.getPersonWhoProducedSex()).getName() );
			cCTSChildRegistrationModel.setPersonProducingChildSex(datas.getPersonWhoProducedSex()!= null ? datas.getPersonWhoProducedSex() : null );
			cCTSChildRegistrationModel.setPhysicalConditionOfChild(datas.getPhysicalConditionOfCchild()!= null ? datas.getPhysicalConditionOfCchild() : "" );
			cCTSChildRegistrationModel.setPoliceInformed(datas.getPoliceInformed()!= null ? datas.getPoliceInformed() :false);
			cCTSChildRegistrationModel.setOrganizationType(typeMap.get(datas.getTypeOfOrganization()) == null ? null : typeMap.get(datas.getTypeOfOrganization()).getName());
			cCTSChildRegistrationModel.setTypeOfOrganization(datas.getTypeOfOrganization()!= null ? datas.getTypeOfOrganization() : null);
			cCTSChildRegistrationModel.setWithWhomChildFoundAddress(datas.getWithWhomChildFoundAddress()!= null ? datas.getWithWhomChildFoundAddress() : "" );
			cCTSChildRegistrationModel.setWithWhomChildFoundAge(datas.getWithWhomChildFoundAge()!= null ? datas.getWithWhomChildFoundAge() :null);
			cCTSChildRegistrationModel.setWithWhomChildFoundContactNo(datas.getWithWhomChildFoundContact()!= null ? datas.getWithWhomChildFoundContact() : "" );
			cCTSChildRegistrationModel.setWithWhomChildFoundName(datas.getWithWhomChildFoundName()!= null ? datas.getWithWhomChildFoundName() :"");
			cCTSChildRegistrationModel.setWithWhomChildFoundOccupation(datas.getWithWhomChildFoundOccupation()!= null ? datas.getWithWhomChildFoundOccupation() : "" );
			cCTSChildRegistrationModel.setChildWelfareCommittee(datas.getProducedBeforeTheCWCName()!= null ? datas.getProducedBeforeTheCWCName() : null);
			cCTSChildRegistrationModel.setChildAgeValue(null==datas.getChildAge()?null:Integer.parseInt(typeMap.get(datas.getChildAge()).getName()));
			cCTSChildRegistrationModel.setAdhaarCardNo(null==datas.getAdhaarCardNo()?null:datas.getAdhaarCardNo());
			cCTSChildRegistrationModel.setFormLang(null==datas.getFormLang()?"en":datas.getFormLang());
			cCTSChildRegistrationModel.setProgramType(childDetails.getProgramType());
		}
		return cCTSChildRegistrationModel;
	}
	
    @Transactional
	@Override
	public String updateChildRegistration(CCTSChildRegistrationModel cctsChildRegistrationModel) throws Exception  {
    	
    	UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		
		ChildRegistrationDetails childRegistrationDetails = cctsChildRegistrationRepository.findByChildIdChildId(cctsChildRegistrationModel.getChildId());
		childRegistrationDetails.setCrdId(childRegistrationDetails.getCrdId());
		childRegistrationDetails.setAllegationByChild(null == cctsChildRegistrationModel.getAllegationByChild() ? null : cctsChildRegistrationModel.getAllegationByChild());
		childRegistrationDetails.setBelongingsOfTheChildAtTheTimeOfProduction(null == cctsChildRegistrationModel.getChildBelongings() ? null : cctsChildRegistrationModel.getChildBelongings());
		childRegistrationDetails.setChildAge(null == cctsChildRegistrationModel.getChildAge() ? null : cctsChildRegistrationModel.getChildAge());
		childRegistrationDetails.setChildIdentityMark(null == cctsChildRegistrationModel.getChildIdentityMarks() ? null : cctsChildRegistrationModel.getChildIdentityMarks());
		childRegistrationDetails.setChildLanguage(null == cctsChildRegistrationModel.getChildLanguageUsed() ? null : cctsChildRegistrationModel.getChildLanguageUsed());
		childRegistrationDetails.setChildName(null == cctsChildRegistrationModel.getChildName() ? null : cctsChildRegistrationModel.getChildName());
		childRegistrationDetails.setChildSex(null == cctsChildRegistrationModel.getChildSex() ? null : cctsChildRegistrationModel.getChildSex());
		childRegistrationDetails.setCircumstancesUnderWhichChildFound(null == cctsChildRegistrationModel.getChildCircumstancesWhenFound() ? null : cctsChildRegistrationModel.getChildCircumstancesWhenFound());
		childRegistrationDetails.setDateChildCameToCCI_SAA(null == cctsChildRegistrationModel.getChildCameToCCIDate() ? null : cctsChildRegistrationModel.getChildCameToCCIDate());
		childRegistrationDetails.setTimeChildCameToCCI_SAA(null == cctsChildRegistrationModel.getChildCameToCCITime() ? null : cctsChildRegistrationModel.getChildCameToCCITime());
		childRegistrationDetails.setImmediateEffortsToTraceFamily(null == cctsChildRegistrationModel.getImmediateEffortsToTraceFamily() ? null : cctsChildRegistrationModel.getImmediateEffortsToTraceFamily());
		childRegistrationDetails.setMedicalTreatmentProvidedToChild(null == cctsChildRegistrationModel.getMedicalTreatment() ? null : cctsChildRegistrationModel.getMedicalTreatment());
		childRegistrationDetails.setNameOfTheOrganization_CCI_SAA(null == cctsChildRegistrationModel.getOrganizationCCISAAName() ? null : cctsChildRegistrationModel.getOrganizationCCISAAName());
		childRegistrationDetails.setParentOrGuardianAddress(null == cctsChildRegistrationModel.getParentAddress() ? null : cctsChildRegistrationModel.getParentAddress());
		childRegistrationDetails.setParentOrGuardianAge(null == cctsChildRegistrationModel.getParentAge() ? null : cctsChildRegistrationModel.getParentAge());
		childRegistrationDetails.setParentOrGuardianContact(null == cctsChildRegistrationModel.getParentContactNo() ? null : cctsChildRegistrationModel.getParentContactNo());
		childRegistrationDetails.setParentOrGuardianName(null == cctsChildRegistrationModel.getParentName() ? null : cctsChildRegistrationModel.getParentName());
		childRegistrationDetails.setParentOrGuardianOccupation(null == cctsChildRegistrationModel.getParentOccupation() ? null : cctsChildRegistrationModel.getParentOccupation());
		childRegistrationDetails.setPersonWhoProduced_occupation_or_designation(null == cctsChildRegistrationModel.getPersonProducingChildOccupation() ? null : cctsChildRegistrationModel.getPersonProducingChildOccupation());
		childRegistrationDetails.setPersonWhoProducedAddress(null == cctsChildRegistrationModel.getPersonProducingChildAddress() ? null : cctsChildRegistrationModel.getPersonProducingChildAddress());
		childRegistrationDetails.setPersonWhoProducedAge(null == cctsChildRegistrationModel.getPersonProducingChildAge() ? null : cctsChildRegistrationModel.getPersonProducingChildAge());
		childRegistrationDetails.setPersonWhoProducedContactNumber(null == cctsChildRegistrationModel.getPersonProducingChildContactNo() ? null : cctsChildRegistrationModel.getPersonProducingChildContactNo());
		childRegistrationDetails.setPersonWhoProducedName(null == cctsChildRegistrationModel.getPersonProducingChildName() ? null : cctsChildRegistrationModel.getPersonProducingChildName());
		childRegistrationDetails.setPersonWhoProducedSex(null == cctsChildRegistrationModel.getPersonProducingChildSex() ? null : cctsChildRegistrationModel.getPersonProducingChildSex());
		childRegistrationDetails.setPhysicalConditionOfCchild(null == cctsChildRegistrationModel.getPhysicalConditionOfChild() ? null : cctsChildRegistrationModel.getPhysicalConditionOfChild());
		childRegistrationDetails.setPlaceOfProduction(null == cctsChildRegistrationModel.getChildProducedPlace() ? null : cctsChildRegistrationModel.getChildProducedPlace());
		childRegistrationDetails.setPlaceWhereChildFound(null == cctsChildRegistrationModel.getChildFoundPlace() ? null : cctsChildRegistrationModel.getChildFoundPlace());
		childRegistrationDetails.setPoliceInformed(null == cctsChildRegistrationModel.getPoliceInformed() ? null : cctsChildRegistrationModel.getPoliceInformed());
		childRegistrationDetails.setWithWhomChildFoundAddress(null == cctsChildRegistrationModel.getWithWhomChildFoundAddress() ? null : cctsChildRegistrationModel.getWithWhomChildFoundAddress());
		childRegistrationDetails.setWithWhomChildFoundAge(null == cctsChildRegistrationModel.getWithWhomChildFoundAge() ? null : cctsChildRegistrationModel.getWithWhomChildFoundAge());
		childRegistrationDetails.setWithWhomChildFoundContact(null == cctsChildRegistrationModel.getWithWhomChildFoundContactNo() ? null : cctsChildRegistrationModel.getWithWhomChildFoundContactNo());
		childRegistrationDetails.setWithWhomChildFoundName(null == cctsChildRegistrationModel.getWithWhomChildFoundName() ? null : cctsChildRegistrationModel.getWithWhomChildFoundName());
		childRegistrationDetails.setWithWhomChildFoundOccupation(null == cctsChildRegistrationModel.getWithWhomChildFoundOccupation() ? null : cctsChildRegistrationModel.getWithWhomChildFoundOccupation());
		childRegistrationDetails.setTypeOfOrganization(null == cctsChildRegistrationModel.getTypeOfOrganization() ? null : cctsChildRegistrationModel.getTypeOfOrganization());
		childRegistrationDetails.setChildId(childRegistrationDetails.getChildId());
		childRegistrationDetails.setAdhaarCardNo(null == cctsChildRegistrationModel.getAdhaarCardNo()?null:cctsChildRegistrationModel.getAdhaarCardNo());
		
		childRegistrationDetails.setUpdatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		childRegistrationDetails.setUpdatedDate(new java.sql.Date(new Date().getTime()));
		
		ChildDetails childDetails = childDetailsRepository.findChildById(childRegistrationDetails.getChildId().getChildId());
		childDetails.setAge(null == cctsChildRegistrationModel.getChildAge() ? 0 : cctsChildRegistrationModel.getChildAge());
		childDetails.setChildName(null == cctsChildRegistrationModel.getChildName() ? null : cctsChildRegistrationModel.getChildName());
		childDetails.setChildSex(null == cctsChildRegistrationModel.getChildSex() ? 1 : cctsChildRegistrationModel.getChildSex());
		childDetails.setProgramType(0);
		
		childDetails.setCwcId(null == cctsChildRegistrationModel.getChildWelfareCommittee() ? null : cctsChildRegistrationModel.getChildWelfareCommittee());
		childDetailsRepository.save(childDetails);
		cctsChildRegistrationRepository.save(childRegistrationDetails);
		return "success";
	}

}
