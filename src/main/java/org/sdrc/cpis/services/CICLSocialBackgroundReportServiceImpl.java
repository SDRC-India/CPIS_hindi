package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CICLFamilyDetails;
import org.sdrc.cpis.domains.CICLSocialBackgroundReport;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.CICLSocialBackgroundReportFamilyDetailsModel;
import org.sdrc.cpis.models.CICLSocialBackgroundReportModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLSocialBackgroundFamilyDetailsRepository;
import org.sdrc.cpis.repository.CICLSocialBackgroundReportRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.NotificationRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CICLSocialBackgroundReportServiceImpl implements CICLSocialBackgroundReportService {
	
	@Autowired
	private CICLSocialBackgroundFamilyDetailsRepository ciclSocialBackgroundFamilyDetails;
	
	@Autowired 
	CICLSocialBackgroundReportRepository cICLSocialBackgroundReportRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;

	ChildDetails child;
	
	public Map<Integer, ValueObject> getTypeMap(){
	   List<CCTSTypeDetails> typeDetails=  cctsTypeDetailsRepository.getAllTypeDetails();
	   Map<Integer, ValueObject> map=new HashMap<>();
	   for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
		ValueObject obj=new ValueObject();
		obj.setId(cctsTypeDetails.getTypeDetailsId());
		obj.setName(cctsTypeDetails.getTypeDetailsName());
		obj.setTypeNameHindi(null != cctsTypeDetails.getTypeDetailsNameHindi() ? cctsTypeDetails.getTypeDetailsNameHindi() : null);
  		map.put(cctsTypeDetails.getTypeDetailsId(),obj);
		}
	 return map;
	}
	
	private Integer generateChildId(){
		
		Integer lastChildId = 0;
		lastChildId = childDetailsRepository.findLastRecord();
		if(lastChildId == null){lastChildId = 1;}
		else{lastChildId = lastChildId + 1; }
		
		return lastChildId;
	}
	
	/*------------Insert Details----------------*/
	
	@Override
	@Transactional
	public String saveCICLSocialBackgroundReportData(CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel) throws Exception {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ChildDetails childDetails = new ChildDetails();
		
		/*-------------For inserting in child details table-------------*/
		AreaDetails areadetails = areaRepository.fetchAreaById(userDetailModel.getAreaId());
		ChildDetails childDetailsdata =childDetailsRepository.findChildById(ciclSocialBackgroundReportModel.getChildId());
		String childId =String.valueOf(generateChildId()) + "-" + areadetails.getAreaName()+ "-" + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		if(childDetailsdata == null){
			ChildDetails cd=new ChildDetails();
			cd.setChildId(childId);
			cd.setChildPhoto(null==ciclSocialBackgroundReportModel.getChildImage()?null:exportPDFServiceImpl.getPhotoPath(ciclSocialBackgroundReportModel.getChildImage(),childId,"childRegistration"));
			cd.setRecordCreatedOn(new java.sql.Date(new Date().getTime()));
			cd.setAge(null==ciclSocialBackgroundReportModel.getAge()?null:ciclSocialBackgroundReportModel.getAge());
			cd.setCurrentAge(null==ciclSocialBackgroundReportModel.getAge()?null:ciclSocialBackgroundReportModel.getAge()+2);
			cd.setChildSex(ciclSocialBackgroundReportModel.getSexObject()== null ? null :ciclSocialBackgroundReportModel.getSexObject().getId());childDetails.setAge(ciclSocialBackgroundReportModel.getAge());
			cd.setChildName(null==ciclSocialBackgroundReportModel.getChildName()?null:ciclSocialBackgroundReportModel.getChildName());
			cd.setChildDistrict(null==areadetails?null:areadetails);
			cd.setProgramType(1);
			cd.setFinalOrderFilled(0);
			cd.setCwcId(userDetailModel.getUserId());
			
			child=childDetailsRepository.save(cd);
		}
		else{
		childDetailsdata.setAge(null==ciclSocialBackgroundReportModel.getAge()?null:ciclSocialBackgroundReportModel.getAge());
		childDetailsdata.setCurrentAge(null==ciclSocialBackgroundReportModel.getAge()?null:ciclSocialBackgroundReportModel.getAge());
		childDetailsdata.setChildSex(ciclSocialBackgroundReportModel.getSexObject()== null ? null :ciclSocialBackgroundReportModel.getSexObject().getId());childDetails.setAge(ciclSocialBackgroundReportModel.getAge());
		childDetailsdata.setChildName(null==ciclSocialBackgroundReportModel.getChildName()?null:ciclSocialBackgroundReportModel.getChildName());
		childDetailsdata.setChildDistrict(null==areadetails?null:areadetails);
		childDetailsdata.setProgramType(1);
		childDetailsdata.setCwcId(userDetailModel.getUserId());
		
		child=childDetailsRepository.save(childDetailsdata);
		}
		
		
//		if(ciclSocialBackgroundReportModel.getChildId() != null){
//			childDetails.setChildId(ciclSocialBackgroundReportModel.getChildId());
//			childDetails.setRecordCreatedOn(childDetailsdata.getRecordCreatedOn());
//			childDetails.setChildPhoto(childDetailsdata.getChildPhoto());
//			}
		 
//		child=childDetailsRepository.save(childDetailsdata);
		
		if(ciclSocialBackgroundReportModel != null){
			CICLSocialBackgroundReport ciclSocialBackgroundReport = new CICLSocialBackgroundReport();
			ciclSocialBackgroundReport.setId(ciclSocialBackgroundReportModel.getId()== null ? null : ciclSocialBackgroundReportModel.getId());
		    ciclSocialBackgroundReport.setFirNumber(ciclSocialBackgroundReportModel.getFirNumber()== null ? null :ciclSocialBackgroundReportModel.getFirNumber());
			ciclSocialBackgroundReport.setDdNumber(ciclSocialBackgroundReportModel.getDdNumber()== null ? null :ciclSocialBackgroundReportModel.getDdNumber());
			ciclSocialBackgroundReport.setGdNumber(ciclSocialBackgroundReportModel.getGdNumber()== null ? null :ciclSocialBackgroundReportModel.getGdNumber());
			ciclSocialBackgroundReport.setSections(ciclSocialBackgroundReportModel.getSections()== null ? null :ciclSocialBackgroundReportModel.getSections());
			ciclSocialBackgroundReport.setPoliceStation(ciclSocialBackgroundReportModel.getPoliceStation()== null ? null :ciclSocialBackgroundReportModel.getPoliceStation());
			ciclSocialBackgroundReport.setEntryDate((new java.sql.Date(ciclSocialBackgroundReportModel.getEntryDate().getTime()))== null ? null :new java.sql.Date(ciclSocialBackgroundReportModel.getEntryDate().getTime()));
			ciclSocialBackgroundReport.setEntryTime((ciclSocialBackgroundReportModel.getEntryTime())== null ? null :ciclSocialBackgroundReportModel.getEntryTime());
			ciclSocialBackgroundReport.setNameOfIo(ciclSocialBackgroundReportModel.getNameOfIo()== null ? null :ciclSocialBackgroundReportModel.getNameOfIo());
			ciclSocialBackgroundReport.setNameOfCwpo(ciclSocialBackgroundReportModel.getNameOfCwpo()== null ? null :ciclSocialBackgroundReportModel.getNameOfCwpo());
			ciclSocialBackgroundReport.setChildName(ciclSocialBackgroundReportModel.getChildName()== null ? null :ciclSocialBackgroundReportModel.getChildName());
			ciclSocialBackgroundReport.setFatherName(ciclSocialBackgroundReportModel.getFatherName()== null ? null :ciclSocialBackgroundReportModel.getFatherName());
			ciclSocialBackgroundReport.setMotherName(ciclSocialBackgroundReportModel.getMotherName()== null ? null :ciclSocialBackgroundReportModel.getMotherName());
			ciclSocialBackgroundReport.setGuardianName(ciclSocialBackgroundReportModel.getGuardianName()== null ? null :ciclSocialBackgroundReportModel.getGuardianName());
			ciclSocialBackgroundReport.setAge(ciclSocialBackgroundReportModel.getAge()== null ? null :ciclSocialBackgroundReportModel.getAge());
			ciclSocialBackgroundReport.setSex(ciclSocialBackgroundReportModel.getSexObject()== null ? null :ciclSocialBackgroundReportModel.getSexObject().getId());
			ciclSocialBackgroundReport.setAddress(ciclSocialBackgroundReportModel.getAddress()== null ? null :ciclSocialBackgroundReportModel.getAddress());			 
			ciclSocialBackgroundReport.setReligion(ciclSocialBackgroundReportModel.getReligionObject()== null ? null :ciclSocialBackgroundReportModel.getReligionObject().getId());
			ciclSocialBackgroundReport.setCasteHinduType(ciclSocialBackgroundReportModel.getCasteObject()== null ? null :ciclSocialBackgroundReportModel.getCasteObject().getId());
			ciclSocialBackgroundReport.setCasteOtherType(ciclSocialBackgroundReportModel.getCasteOtherType()== null ? null :ciclSocialBackgroundReportModel.getCasteOtherType());			 
			ciclSocialBackgroundReport.setDifferentlyAbled(ciclSocialBackgroundReportModel.getDifferentlyAbled());
			ciclSocialBackgroundReport.setDifferentlyAbledType(ciclSocialBackgroundReportModel.getDifferentlyAbledType()== null ? null :ciclSocialBackgroundReportModel.getDifferentlyAbledType());
			ciclSocialBackgroundReport.setOtherDifferentlyAbledType(ciclSocialBackgroundReportModel.getOtherDifferentlyAbledType()== null ? null :ciclSocialBackgroundReportModel.getOtherDifferentlyAbledType());
			if(ciclSocialBackgroundReportModel.getChildId() != null){
			   ciclSocialBackgroundReport.setChildId(childDetailsRepository.findChildById(ciclSocialBackgroundReportModel.getChildId()));
			   }
			 else{
				 ciclSocialBackgroundReport.setChildId(child);
				 
				 List<UserDetails> userList=userDetailsRepository.findByAreaAreaId(areadetails.getAreaId());
					List<NotificationDetails> notifications = new ArrayList<NotificationDetails>();
					for (UserDetails userDetails : userList) {
						if(userDetails.getDesignation().getDesignationId()==5 || userDetails.getDesignation().getDesignationId()==6 || userDetails.getDesignation().getDesignationId()==9 ) {
						NotificationDetails notificationDetails = new NotificationDetails();
						notificationDetails.setChildId(childId);
						notificationDetails.setDistrictId(areadetails.getAreaId());
						notificationDetails.setDivisionId(areaRepository.fetchAreaById(areadetails.getParentArea().getAreaId()).getAreaId());
						notificationDetails.setRecipentId(userDetails.getUserId());
						notificationDetails.setDateOfAction(new java.sql.Date(new Date().getTime()));
						notificationDetails.setNotificationType("registration");
						notificationDetails.setNotificationMsg("A new child has been registered with Child Id "+childId);
						notificationDetails.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
						notificationDetails.setCaseType("CICL");
						notificationDetails.setIsActive(true);
						notificationDetails.setIsRead(false);
						
						notifications.add(notificationDetails);
						}
					}
					notificationRepository.save(notifications);
				 }
			 
			ciclSocialBackgroundReport.setHomeLeavingReason(ciclSocialBackgroundReportModel.getHomeLeavingReason()== null ? null :ciclSocialBackgroundReportModel.getHomeLeavingReason());
			ciclSocialBackgroundReport.setFamilyInvolvementInOffence(ciclSocialBackgroundReportModel.getFamilyInvolvementInOffence());
			ciclSocialBackgroundReport.setOtherFamilyInvolvementInOffence(ciclSocialBackgroundReportModel.getOtherFamilyInvolvementInOffence()== null ? null :ciclSocialBackgroundReportModel.getOtherFamilyInvolvementInOffence());
			
			ciclSocialBackgroundReport.setOtherDrugBadHabits(ciclSocialBackgroundReportModel.getOtherDrugBadHabits() == null ? null : ciclSocialBackgroundReportModel.getOtherDrugBadHabits());
			ciclSocialBackgroundReport.setEmploymentDetails(ciclSocialBackgroundReportModel.getEmploymentDetails()== null ? null :ciclSocialBackgroundReportModel.getEmploymentDetails());
			 //ciclSocialBackgroundReport.setIncomeUtilizationForFamily(ciclSocialBackgroundReportModel.getIncomeUtilizationForFamily());
			ciclSocialBackgroundReport.setUsedBySelf(ciclSocialBackgroundReportModel.getUsedBySelf());
			ciclSocialBackgroundReport.setUsedBySelfDress(ciclSocialBackgroundReportModel.getUsedBySelfDress());
			ciclSocialBackgroundReport.setUsedBySelfAlcohol(ciclSocialBackgroundReportModel.getUsedBySelfAlcohol());
			ciclSocialBackgroundReport.setUsedBySelfGambling(ciclSocialBackgroundReportModel.getUsedBySelfGambling());
			ciclSocialBackgroundReport.setUsedBySelfDrug(ciclSocialBackgroundReportModel.getUsedBySelfDrug());
			ciclSocialBackgroundReport.setUsedBySelfSmoking(ciclSocialBackgroundReportModel.getUsedBySelfSmoking());
			ciclSocialBackgroundReport.setUsedBySelfSavings(ciclSocialBackgroundReportModel.getUsedBySelfSavings());
			ciclSocialBackgroundReport.setEducationDetails(ciclSocialBackgroundReportModel.getEducationDetails()== null ? null :  ciclSocialBackgroundReportModel.getEducationDetails().getId());
			ciclSocialBackgroundReport.setSchoolLeavingReason(ciclSocialBackgroundReportModel.getSchoolLeavingReason()== null ? null :ciclSocialBackgroundReportModel.getSchoolLeavingReason());
			ciclSocialBackgroundReport.setSchoolDetails(ciclSocialBackgroundReportModel.getSchoolDetails()== null ? null :ciclSocialBackgroundReportModel.getSchoolDetails().getId());
			ciclSocialBackgroundReport.setVocationalTraining(ciclSocialBackgroundReportModel.getVocationalTraining()== null ? null :ciclSocialBackgroundReportModel.getVocationalTraining());
			ciclSocialBackgroundReport.setMajorityOfFriends(ciclSocialBackgroundReportModel.getMajorityOfFriends()== null ? null :ciclSocialBackgroundReportModel.getMajorityOfFriends());
			ciclSocialBackgroundReport.setPhysicalAbuse(ciclSocialBackgroundReportModel.getPhysicalAbuse()== null ? null :ciclSocialBackgroundReportModel.getPhysicalAbuse());
			ciclSocialBackgroundReport.setVerbalAbuse(ciclSocialBackgroundReportModel.getVerbalAbuse()== null ? null :ciclSocialBackgroundReportModel.getVerbalAbuse());
			ciclSocialBackgroundReport.setSexualAbuse(ciclSocialBackgroundReportModel.getSexualAbuse()== null ? null :ciclSocialBackgroundReportModel.getSexualAbuse());
			ciclSocialBackgroundReport.setOtherPhysicalAbuse(ciclSocialBackgroundReportModel.getOtherPhysicalAbuse()== null ? null :ciclSocialBackgroundReportModel.getOtherPhysicalAbuse());
			ciclSocialBackgroundReport.setOtherVerbalAbuse(ciclSocialBackgroundReportModel.getOtherVerbalAbuse()== null ? null :ciclSocialBackgroundReportModel.getOtherVerbalAbuse());
			ciclSocialBackgroundReport.setOtherSexualAbuse(ciclSocialBackgroundReportModel.getOtherSexualAbuse()== null ? null :ciclSocialBackgroundReportModel.getOtherSexualAbuse());
			ciclSocialBackgroundReport.setAbused(ciclSocialBackgroundReportModel.getAbused());
			ciclSocialBackgroundReport.setOtherAbused(ciclSocialBackgroundReportModel.getOtherAbused());
			ciclSocialBackgroundReport.setOtherAbuse(ciclSocialBackgroundReportModel.getOtherAbuse());
			ciclSocialBackgroundReport.setVictimOfOffence(ciclSocialBackgroundReportModel.getVictimOfOffence());
			ciclSocialBackgroundReport.setOtherVictimOfOffence(ciclSocialBackgroundReportModel.getOtherVictimOfOffence());
			ciclSocialBackgroundReport.setChildDrugPeddling(ciclSocialBackgroundReportModel.isChildDrugPeddling());
			ciclSocialBackgroundReport.setOtherChildDrugPeddling(ciclSocialBackgroundReportModel.getOtherChildDrugPeddling());
			ciclSocialBackgroundReport.setAllegedOffence(ciclSocialBackgroundReportModel.getAllegedOffence()== null ? null :ciclSocialBackgroundReportModel.getAllegedOffence());
			ciclSocialBackgroundReport.setApprehendedCircumstances(ciclSocialBackgroundReportModel.getApprehendedCircumstances()== null ? null :ciclSocialBackgroundReportModel.getApprehendedCircumstances());
			ciclSocialBackgroundReport.setArticlesRecovered(ciclSocialBackgroundReportModel.getArticlesRecovered()== null ? null :ciclSocialBackgroundReportModel.getArticlesRecovered());
			ciclSocialBackgroundReport.setAllegedRole(ciclSocialBackgroundReportModel.getAllegedRole()== null ? null :ciclSocialBackgroundReportModel.getAllegedRole());
			ciclSocialBackgroundReport.setSuggestions(ciclSocialBackgroundReportModel.getSuggestions()== null ? null :ciclSocialBackgroundReportModel.getSuggestions());
			ciclSocialBackgroundReport.setRoleOfChildInOffence(ciclSocialBackgroundReportModel.getRoleOfChildInOffence()== null ? "Null Value!" :ciclSocialBackgroundReportModel.getRoleOfChildInOffence());
			ciclSocialBackgroundReport.setGoodHabits(ciclSocialBackgroundReportModel.getGoodHabits()== null ? null :ciclSocialBackgroundReportModel.getGoodHabits());
			ciclSocialBackgroundReport.setOtherGoodHabits(ciclSocialBackgroundReportModel.getOtherGoodHabits()== null ? null :ciclSocialBackgroundReportModel.getOtherGoodHabits());
			ciclSocialBackgroundReport.setBadHabits(ciclSocialBackgroundReportModel.getBadHabits()== null ? null :ciclSocialBackgroundReportModel.getBadHabits());
			ciclSocialBackgroundReport.setOtherBadHabits(ciclSocialBackgroundReportModel.getOtherBadHabits()== null ? null :ciclSocialBackgroundReportModel.getOtherBadHabits());
			ciclSocialBackgroundReport.setOtherInOtherAbuse(ciclSocialBackgroundReportModel.getOtherInOtherAbuse());
			ciclSocialBackgroundReport.setOtherSchoolLeavingReason(ciclSocialBackgroundReportModel.getOtherSchoolLeavingReason());
			ciclSocialBackgroundReport.setAdhaarCardNo(ciclSocialBackgroundReportModel.getAdhaarCardNo());
			
			ciclSocialBackgroundReport.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			ciclSocialBackgroundReport.setCreatedDate(new java.sql.Date(new Date().getTime()));
			ciclSocialBackgroundReport.setUpdatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			ciclSocialBackgroundReport.setUpdatedDate(new java.sql.Date(new Date().getTime()));
			ciclSocialBackgroundReport.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			ciclSocialBackgroundReport.setFormLang(ciclSocialBackgroundReportModel.getFormLang());
			
			 // saveFamilyDetails
			List<CICLSocialBackgroundReportFamilyDetailsModel> ciclSocialBackgroundReportFamilyDetailsList = ciclSocialBackgroundReportModel.getFamilyDetails();
			CICLFamilyDetails ciclFamilyDetails=null;
			if(ciclSocialBackgroundReportFamilyDetailsList != null && !ciclSocialBackgroundReportFamilyDetailsList.isEmpty()){
			  if (ciclSocialBackgroundReportModel.getChildId() != null) {
				ciclSocialBackgroundFamilyDetails.deleteByChildIdChildId(ciclSocialBackgroundReportModel.getChildId());
				}
			 for (CICLSocialBackgroundReportFamilyDetailsModel ciclSocialBackgroundReportFamilyDetails : ciclSocialBackgroundReportFamilyDetailsList){
			    ciclFamilyDetails = new CICLFamilyDetails();			
			    ciclFamilyDetails.setAge(ciclSocialBackgroundReportFamilyDetails.getAge()==null ? null : ciclSocialBackgroundReportFamilyDetails.getAge());
				ciclFamilyDetails.setNameOfFamilyMember(ciclSocialBackgroundReportFamilyDetails.getName() == null ? null :ciclSocialBackgroundReportFamilyDetails.getName());
				ciclFamilyDetails.setRelationship(ciclSocialBackgroundReportFamilyDetails.getRelationship()== null ? null :ciclSocialBackgroundReportFamilyDetails.getRelationship());
				ciclFamilyDetails.setSex(ciclSocialBackgroundReportFamilyDetails.getSex()== null ? null :ciclSocialBackgroundReportFamilyDetails.getSex().getId());
				ciclFamilyDetails.setEducation(ciclSocialBackgroundReportFamilyDetails.getEducation()== null ? null :ciclSocialBackgroundReportFamilyDetails.getEducation().getId());
				ciclFamilyDetails.setOccupation(ciclSocialBackgroundReportFamilyDetails.getOccupation()== null ? null :ciclSocialBackgroundReportFamilyDetails.getOccupation());
				ciclFamilyDetails.setIncome(ciclSocialBackgroundReportFamilyDetails.getIncome()== null ? 0.0 :(ciclSocialBackgroundReportFamilyDetails.getIncome()));
				ciclFamilyDetails.setHealthStatus(ciclSocialBackgroundReportFamilyDetails.getHealthStatus()== null ? null :ciclSocialBackgroundReportFamilyDetails.getHealthStatus());
				ciclFamilyDetails.setMentalIllness(ciclSocialBackgroundReportFamilyDetails.getHistoryOfMentalIllness()== null ? null :ciclSocialBackgroundReportFamilyDetails.getHistoryOfMentalIllness());
				ciclFamilyDetails.setAddictions(ciclSocialBackgroundReportFamilyDetails.getAddictions()== null ? null :ciclSocialBackgroundReportFamilyDetails.getAddictions());
				if(ciclSocialBackgroundReportFamilyDetails.getChildId() != null){
				   ciclSocialBackgroundReport.setChildId(childDetailsRepository.findChildById(ciclSocialBackgroundReportFamilyDetails.getChildId()));}
				else{ciclFamilyDetails.setChildId(child);}
				 ciclSocialBackgroundFamilyDetails.save(ciclFamilyDetails);
				 }
			 }
			cICLSocialBackgroundReportRepository.save(ciclSocialBackgroundReport);
			}
		return childId;
		}
	
	/*-----------View Details-------------*/
	
	@Override
	public CICLSocialBackgroundReportModel getCICLSocialBackgroundReportModel(String childId) throws Exception {
		CICLSocialBackgroundReport ciclSocialBackgroundReport=cICLSocialBackgroundReportRepository.findByChildIdChildId(childId);
		List<CICLFamilyDetails> ciclFamilyDetails = ciclSocialBackgroundFamilyDetails.findByChildIdChildId(childId);
		CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel = new CICLSocialBackgroundReportModel();
		ChildDetails childDetails = childDetailsRepository.findChildById(childId);
		
		
		if(ciclSocialBackgroundReport != null){
			
			ciclSocialBackgroundReportModel.setId(ciclSocialBackgroundReport.getId());
			ciclSocialBackgroundReportModel.setFirNumber(ciclSocialBackgroundReport.getFirNumber());
			ciclSocialBackgroundReportModel.setDdNumber(ciclSocialBackgroundReport.getDdNumber());
			ciclSocialBackgroundReportModel.setGdNumber(ciclSocialBackgroundReport.getGdNumber());
			ciclSocialBackgroundReportModel.setSections(ciclSocialBackgroundReport.getSections());
			ciclSocialBackgroundReportModel.setPoliceStation(ciclSocialBackgroundReport.getPoliceStation());
			ciclSocialBackgroundReportModel.setEntryDate(ciclSocialBackgroundReport.getEntryDate());
			ciclSocialBackgroundReportModel.setEntryTime(ciclSocialBackgroundReport.getEntryTime());
			ciclSocialBackgroundReportModel.setNameOfIo(ciclSocialBackgroundReport.getNameOfIo());
			ciclSocialBackgroundReportModel.setNameOfCwpo(ciclSocialBackgroundReport.getNameOfCwpo());
			ciclSocialBackgroundReportModel.setChildName(ciclSocialBackgroundReport.getChildName());
			ciclSocialBackgroundReportModel.setFatherName(ciclSocialBackgroundReport.getFatherName());
			ciclSocialBackgroundReportModel.setMotherName(ciclSocialBackgroundReport.getMotherName());
			ciclSocialBackgroundReportModel.setGuardianName(ciclSocialBackgroundReport.getGuardianName());
			ciclSocialBackgroundReportModel.setAge(ciclSocialBackgroundReport.getAge());
			ciclSocialBackgroundReportModel.setSexObject(getTypeMap().get(ciclSocialBackgroundReport.getSex()));
			ciclSocialBackgroundReportModel.setAddress(ciclSocialBackgroundReport.getAddress());
			ciclSocialBackgroundReportModel.setReligionObject(getTypeMap().get(ciclSocialBackgroundReport.getReligion()));
			ciclSocialBackgroundReportModel.setCasteObject(getTypeMap().get(ciclSocialBackgroundReport.getCasteHinduType()));
			ciclSocialBackgroundReportModel.setCasteOtherType(ciclSocialBackgroundReport.getCasteOtherType());
			ciclSocialBackgroundReportModel.setDifferentlyAbled(ciclSocialBackgroundReport.isDifferentlyAbled());
			ciclSocialBackgroundReportModel.setDifferentlyAbledType(ciclSocialBackgroundReport.getDifferentlyAbledType());
			ciclSocialBackgroundReportModel.setOtherDifferentlyAbledType(ciclSocialBackgroundReport.getOtherDifferentlyAbledType());
			ciclSocialBackgroundReportModel.setChildId(ciclSocialBackgroundReport.getChildId().getChildId());
			ciclSocialBackgroundReportModel.setAbused(ciclSocialBackgroundReport.getAbused());
			ciclSocialBackgroundReportModel.setPhysicalAbuse(ciclSocialBackgroundReport.getPhysicalAbuse());
			ciclSocialBackgroundReportModel.setOtherPhysicalAbuse(ciclSocialBackgroundReport.getOtherPhysicalAbuse());
			ciclSocialBackgroundReportModel.setSexualAbuse(ciclSocialBackgroundReport.getSexualAbuse());
			ciclSocialBackgroundReportModel.setOtherSexualAbuse(ciclSocialBackgroundReport.getOtherSexualAbuse());
			ciclSocialBackgroundReportModel.setVerbalAbuse(ciclSocialBackgroundReport.getVerbalAbuse());
			ciclSocialBackgroundReportModel.setOtherVerbalAbuse(ciclSocialBackgroundReport.getOtherVerbalAbuse());
			ciclSocialBackgroundReportModel.setHomeLeavingReason(ciclSocialBackgroundReport.getHomeLeavingReason());
			ciclSocialBackgroundReportModel.setFamilyInvolvementInOffence(ciclSocialBackgroundReport.isFamilyInvolvementInOffence());
			ciclSocialBackgroundReportModel.setOtherFamilyInvolvementInOffence(ciclSocialBackgroundReport.getOtherFamilyInvolvementInOffence());
			ciclSocialBackgroundReportModel.setGoodHabits(ciclSocialBackgroundReport.getGoodHabits());
			ciclSocialBackgroundReportModel.setOtherGoodHabits(ciclSocialBackgroundReport.getOtherGoodHabits());
			ciclSocialBackgroundReportModel.setBadHabits(ciclSocialBackgroundReport.getBadHabits());
			ciclSocialBackgroundReportModel.setOtherBadHabits(ciclSocialBackgroundReport.getOtherBadHabits());
			ciclSocialBackgroundReportModel.setOtherDrugBadHabits(ciclSocialBackgroundReport.getOtherDrugBadHabits());
			ciclSocialBackgroundReportModel.setEmploymentDetails(ciclSocialBackgroundReport.getEmploymentDetails());
			ciclSocialBackgroundReportModel.setUsedByFamily(ciclSocialBackgroundReport.isUsedByFamily());
			ciclSocialBackgroundReportModel.setUsedBySelf(ciclSocialBackgroundReport.isUsedBySelf());
			ciclSocialBackgroundReportModel.setUsedBySelfAlcohol(ciclSocialBackgroundReport.isUsedBySelfAlcohol());
			ciclSocialBackgroundReportModel.setUsedBySelfDress(ciclSocialBackgroundReport.isUsedBySelfDress());
			ciclSocialBackgroundReportModel.setUsedBySelfDrug(ciclSocialBackgroundReport.isUsedBySelfDrug());
			ciclSocialBackgroundReportModel.setUsedBySelfGambling(ciclSocialBackgroundReport.isUsedBySelfGambling());
			ciclSocialBackgroundReportModel.setUsedBySelfSavings(ciclSocialBackgroundReport.isUsedBySelfSavings());
			ciclSocialBackgroundReportModel.setUsedBySelfSmoking(ciclSocialBackgroundReport.isUsedBySelfSmoking());
			ciclSocialBackgroundReportModel.setEducationDetails(getTypeMap().get(ciclSocialBackgroundReport.getEducationDetails()));
			ciclSocialBackgroundReportModel.setSchoolLeavingReason(ciclSocialBackgroundReport.getSchoolLeavingReason());
			ciclSocialBackgroundReportModel.setOtherSchoolLeavingReason(ciclSocialBackgroundReport.getOtherSchoolLeavingReason());
			ciclSocialBackgroundReportModel.setSchoolDetails(getTypeMap().get(ciclSocialBackgroundReport.getSchoolDetails()));
			ciclSocialBackgroundReportModel.setVocationalTraining(ciclSocialBackgroundReport.getVocationalTraining());
			ciclSocialBackgroundReportModel.setMajorityOfFriends(ciclSocialBackgroundReport.getMajorityOfFriends());
			ciclSocialBackgroundReportModel.setVictimOfOffence(ciclSocialBackgroundReport.isVictimOfOffence());
			ciclSocialBackgroundReportModel.setOtherVictimOfOffence(ciclSocialBackgroundReport.getOtherVictimOfOffence());
			ciclSocialBackgroundReportModel.setChildDrugPeddling(ciclSocialBackgroundReport.isChildDrugPeddling());
			ciclSocialBackgroundReportModel.setOtherChildDrugPeddling(ciclSocialBackgroundReport.getOtherChildDrugPeddling());
			ciclSocialBackgroundReportModel.setAllegedOffence(ciclSocialBackgroundReport.getAllegedOffence());
			ciclSocialBackgroundReportModel.setApprehendedCircumstances(ciclSocialBackgroundReport.getApprehendedCircumstances());
			ciclSocialBackgroundReportModel.setArticlesRecovered(ciclSocialBackgroundReport.getArticlesRecovered());
			ciclSocialBackgroundReportModel.setAllegedRole(ciclSocialBackgroundReport.getAllegedRole());
			ciclSocialBackgroundReportModel.setSuggestions(ciclSocialBackgroundReport.getSuggestions());
			ciclSocialBackgroundReportModel.setRoleOfChildInOffence(ciclSocialBackgroundReport.getRoleOfChildInOffence());
			ciclSocialBackgroundReportModel.setAbused(ciclSocialBackgroundReport.getAbused());
			ciclSocialBackgroundReportModel.setOtherAbused(ciclSocialBackgroundReport.getOtherAbused());
			ciclSocialBackgroundReportModel.setVerbalAbuse(ciclSocialBackgroundReport.getVerbalAbuse());
			ciclSocialBackgroundReportModel.setOtherVerbalAbuse(ciclSocialBackgroundReport.getOtherVerbalAbuse());
			ciclSocialBackgroundReportModel.setPhysicalAbuse(ciclSocialBackgroundReport.getPhysicalAbuse());
			ciclSocialBackgroundReportModel.setOtherPhysicalAbuse(ciclSocialBackgroundReport.getOtherPhysicalAbuse());
			ciclSocialBackgroundReportModel.setSexualAbuse(ciclSocialBackgroundReport.getSexualAbuse());
			ciclSocialBackgroundReportModel.setOtherSexualAbuse(ciclSocialBackgroundReport.getOtherSexualAbuse());
			ciclSocialBackgroundReportModel.setOtherAbuse(ciclSocialBackgroundReport.getOtherAbuse());
			ciclSocialBackgroundReportModel.setOtherInOtherAbuse(ciclSocialBackgroundReport.getOtherInOtherAbuse());
			ciclSocialBackgroundReportModel.setChildImage(exportPDFServiceImpl.getChildPhoto(childDetails.getChildPhoto()));
			ciclSocialBackgroundReportModel.setAdhaarCardNo(ciclSocialBackgroundReport.getAdhaarCardNo());
			ciclSocialBackgroundReportModel.setFormLang(null==ciclSocialBackgroundReport.getFormLang()?"en":ciclSocialBackgroundReport.getFormLang());
			ciclSocialBackgroundReportModel.setProgramType(childDetails.getProgramType());
			/*-----------------Family Details View---------------*/
			List<CICLSocialBackgroundReportFamilyDetailsModel> ciclSocialBackgroundReportFamilyDetailsList = new ArrayList<>();
			
			for (CICLFamilyDetails ciclFamilyDetail : ciclFamilyDetails) {
				CICLSocialBackgroundReportFamilyDetailsModel ciclSocialBackgroundReportFamilyDetailsModel = new CICLSocialBackgroundReportFamilyDetailsModel();
				ciclSocialBackgroundReportFamilyDetailsModel.setId(ciclFamilyDetail.getId());
				ciclSocialBackgroundReportFamilyDetailsModel.setName(ciclFamilyDetail.getNameOfFamilyMember());
				ciclSocialBackgroundReportFamilyDetailsModel.setAge(ciclFamilyDetail.getAge());
				ciclSocialBackgroundReportFamilyDetailsModel.setEducation(getTypeMap().get(ciclFamilyDetail.getEducation()));
				ciclSocialBackgroundReportFamilyDetailsModel.setOccupation(ciclFamilyDetail.getOccupation());
				ciclSocialBackgroundReportFamilyDetailsModel.setIncome(ciclFamilyDetail.getIncome());
				ciclSocialBackgroundReportFamilyDetailsModel.setHealthStatus(ciclFamilyDetail.getHealthStatus());
				ciclSocialBackgroundReportFamilyDetailsModel.setAddictions(ciclFamilyDetail.getAddictions());
				ciclSocialBackgroundReportFamilyDetailsModel.setHistoryOfMentalIllness(ciclFamilyDetail.getMentalIllness());
				ciclSocialBackgroundReportFamilyDetailsModel.setRelationship(ciclFamilyDetail.getRelationship());
				ciclSocialBackgroundReportFamilyDetailsModel.setSex(getTypeMap().get(ciclFamilyDetail.getSex()));
				ciclSocialBackgroundReportFamilyDetailsList.add(ciclSocialBackgroundReportFamilyDetailsModel);
				ciclSocialBackgroundReportModel.setFamilyDetails(ciclSocialBackgroundReportFamilyDetailsList);
			}
			
		}
		return ciclSocialBackgroundReportModel;
	}

	

	

}
