package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.GridMenuItemDetails;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.CCTSTypeDetailsModel;
import org.sdrc.cpis.models.ChildDetailsModel;
import org.sdrc.cpis.models.GridMenuItemModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CCTSTypeRepository;
import org.sdrc.cpis.repository.CciUserRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.GridMenuRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.DomainToModelConverter;
import org.sdrc.cpis.util.ListObject;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CCTSServiceImplementation implements CCTSService {
	@Autowired
	private CCTSTypeRepository cctsTypeRepository;
	
	@Autowired
	private CCIInfoRepository cciInfoRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private UserDetailRepository userDetailsRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private GridMenuRepository gridMenuRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	@Autowired
	private CciUserRepository cciUserRepository;
	
	Map<Integer, ValueObject> typeMap;

	public Map<Integer, ValueObject> getTypeMap(List<CCTSTypeDetails> typeDetails) {
		
		Map<Integer, ValueObject> map = new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj = new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}
	
	@Override
	public List<ValueObject> getAllTypeDetails(){
		List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		List<ValueObject> typeDetailsList=new ArrayList<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
				ValueObject valueObject= new ValueObject();
				valueObject.setId(cctsTypeDetails.getTypeDetailsId());
				valueObject.setName(cctsTypeDetails.getTypeDetailsName());
				valueObject.setChecked(false);
				valueObject.setTypeId(cctsTypeDetails.getTypeId().getTypeId());
				
				typeDetailsList.add(valueObject);
			}
		
		
		return typeDetailsList;
	}
	
	@Override
	public List<CCTSTypeDetailsModel> fetchAllType(){
		
		List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		List<CCTSTypeDetailsModel> cctsTypeDetailsModelList = new ArrayList<CCTSTypeDetailsModel>();
		
		for(CCTSTypeDetails  cctsTypeDetails : typeDetails){
			CCTSTypeDetailsModel cctsTypeDetailsModel = new CCTSTypeDetailsModel();
			cctsTypeDetailsModel.setChecked(false);
			cctsTypeDetailsModel.setTypeDetailsId(cctsTypeDetails.getTypeDetailsId());
			cctsTypeDetailsModel.setTypeDetailsName(cctsTypeDetails.getTypeDetailsName());
			cctsTypeDetailsModel.setTypeId(cctsTypeDetails.getTypeId().getTypeId());
			
			cctsTypeDetailsModelList.add(cctsTypeDetailsModel);
		}
		
		return cctsTypeDetailsModelList;
	}
	
	@Override
	public Map<String, Object> fetchType(){
//		List<CCTSType> cctsTypes=cctsTypeRepository.getAllTypes();
		List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		
		List<ValueObject> genderList=new ArrayList<>();
		List<ValueObject> ageList = new ArrayList<>();
		List<ValueObject> ageRanges=new ArrayList<>();
		List<ValueObject> ordersPassedByCWC = new ArrayList<>();
		List<ValueObject> childSex = new ArrayList<>();
		List<ValueObject> organizationType = new ArrayList<>();
		List<ValueObject> familyMemberRelationship = new ArrayList<>();
		List<ValueObject> childEducationDtls = new ArrayList<>();
		List<ValueObject> childSchoolDtls = new ArrayList<>();
		List<ValueObject> childHealthStatus = new ArrayList<>();
		List<ValueObject> childWasStayingWith = new ArrayList<>();
		List<ValueObject> institutionDocType = new ArrayList<>();
		List<ValueObject> differentlyAbledType = new ArrayList<>();
		List<ValueObject> goodHabits = new ArrayList<>();
		List<ValueObject> badHabits = new ArrayList<>();
		List<ValueObject> reasonLeavingSchool = new ArrayList<>();
		List<ValueObject> majorityFriendTypes = new ArrayList<>();
		List<ValueObject> reasonsLeavingFamily = new ArrayList<>();
		List<ValueObject> abusedBy = new ArrayList<>();
		List<ValueObject> exploitationFaced = new ArrayList<>();
		List<ValueObject> orderType = new ArrayList<>();
		List<ValueObject> idProofList = new ArrayList<>();
		List<ValueObject> bankAccountStatus = new ArrayList<>();
		List<ValueObject> childBelongingsHandedOverToWhom = new ArrayList<>();
		List<ValueObject> firstInteractionReportByWhom = new ArrayList<>();
		List<ValueObject> learningInstitute = new ArrayList<>();
		List<ValueObject> proceedingsBeforeCommittee = new ArrayList<>();
		List<ValueObject> icpDesignation = new ArrayList<>();
		List<ValueObject> stayOfTheChild = new ArrayList<>();
		List<ValueObject> casteList = new ArrayList<>();
		List<ValueObject> educationLevels = new ArrayList<>();
		List<ValueObject> icpParentType = new ArrayList<>();
		List<ValueObject> placedOrder = new ArrayList<>();
		List<ValueObject> childCategoryList = new ArrayList<>();
		List<ValueObject> religionList = new ArrayList<>();
		List<ValueObject> typeOfConstruction = new ArrayList<>();
		List<ValueObject> noOfRooms = new ArrayList<>();
		List<ValueObject> typeOfOccupancy = new ArrayList<>();
		List<ValueObject> childBroughtByWhomList = new ArrayList<>();
		List<ValueObject> frequencyOfVisit = new ArrayList<>();
		List<ValueObject> familyType = new ArrayList<>();
		List<ValueObject> householdArticles = new ArrayList<>();
		List<ValueObject> vehiclesOwned = new ArrayList<>();
		List<ValueObject> marraigeType = new ArrayList<>();
		List<ValueObject> socialActivity = new ArrayList<>();
		List<ValueObject> parentalCareTowardsChild = new ArrayList<>();
		List<ValueObject> delinquentBehaviour = new ArrayList<>();
		List<ValueObject> delinquentbehaviourReason = new ArrayList<>();
		List<ValueObject> incomeUtilization = new ArrayList<>();
		List<ValueObject> detailsOfSaving = new ArrayList<>();
		List<ValueObject> workDuration = new ArrayList<>();
		List<ValueObject> mediumOfInstruction = new ArrayList<>();
		List<ValueObject> promotionStatus = new ArrayList<>();
		List<ValueObject> extraCurricularActivity = new ArrayList<>();
		List<ValueObject> friendshipPriorToChildrensHome = new ArrayList<>();
		List<ValueObject> membershipInGroupDetails = new ArrayList<>();
		List<ValueObject> positionInGroup = new ArrayList<>();
		List<ValueObject> purposeOfTakingMembership = new ArrayList<>();
		List<ValueObject> attitudeOfTheGroup = new ArrayList<>();
		List<ValueObject> meetingPointOfGroup = new ArrayList<>();
		List<ValueObject> reactionOfSocietyTowardsChild = new ArrayList<>();
		List<ValueObject> reactionOfPoliceTowardsChild = new ArrayList<>();
		List<ValueObject> suggestionBy = new ArrayList<>();
		List<ValueObject> followUpBy = new ArrayList<>();
		List<ValueObject> realtionShipWithChild = new ArrayList<>();
		List<ValueObject> childEmploymentDetails = new ArrayList<>();
		List<ValueObject> reportPreparedBy = new ArrayList<>();
		List<ValueObject> sponsorshipSupportFor = new ArrayList<>();
		List<ValueObject> supervisionCareUnderWhom = new ArrayList<>();
		List<ValueObject> ciclOrgType = new ArrayList<>();
		List<ValueObject> natureOfOffenceAlleged = new ArrayList<>();
		List<ValueObject> reasonForAllegedOffence = new ArrayList<>();
		List<ValueObject> determinationBy = new ArrayList<>();
		List<ValueObject> childInCustodyOf = new ArrayList<>();
		List<ValueObject> natureOfCrime = new ArrayList<>();
		List<ValueObject> buildingType = new ArrayList<>();
		List<ValueObject> constructionOfBuilding = new ArrayList<>();
		List<ValueObject> quarters = new ArrayList<>();
		List<ValueObject> severity = new ArrayList<>();
		List<ValueObject> followType = new ArrayList<>();
		List<ValueObject> restorationType = new ArrayList<>();
		List<ValueObject> childLook = new ArrayList<>();
		List<ValueObject> schoolType = new ArrayList<>();
		List<ValueObject> itemsAvailable = new ArrayList<>();
		List<ValueObject> intellectiveStatus = new ArrayList<>();
		List<ValueObject> courseStatus = new ArrayList<>();
		List<ValueObject> progessOfCourse = new ArrayList<>();
		List<ValueObject> parentsBehaviour = new ArrayList<>();
		List<ValueObject> childsBehaviour = new ArrayList<>();
		List<ValueObject> complianceByGovt = new ArrayList<>();
		List<ValueObject> problemShareTime = new ArrayList<>();
		
		
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject valueObject=new ValueObject();
			valueObject.setId(cctsTypeDetails.getTypeDetailsId());
			valueObject.setName(cctsTypeDetails.getTypeDetailsName());
			valueObject.setTypeNameHindi(null==cctsTypeDetails.getTypeDetailsNameHindi()?null:cctsTypeDetails.getTypeDetailsNameHindi());
			valueObject.setChecked(false);
			
			switch(cctsTypeDetails.getTypeId().getTypeId()){
			case 1:
				genderList.add(valueObject);
				break;
			case 2:
				ageList.add(valueObject);
				break;
			case 4:
				casteList.add(valueObject);
				break;
			case 5:
				educationLevels.add(valueObject);
				break;
			case 6:
				ordersPassedByCWC.add(valueObject);
				break;
			case 7:
				differentlyAbledType.add(valueObject);
				break;
			case 8:
				familyMemberRelationship.add(valueObject);
				break;
			case 9:
				goodHabits.add(valueObject);
				break;
			case 10:
				badHabits.add(valueObject);
				break;
			case 11:
				childEducationDtls.add(valueObject);
				break;
			case 12:
				childSchoolDtls.add(valueObject);
				break;
			case 13:
				reasonLeavingSchool.add(valueObject);
				break;
			case 14:
				majorityFriendTypes.add(valueObject);
				break;
			case 15:
				childHealthStatus.add(valueObject);
				break;
			case 16:
				childWasStayingWith.add(valueObject);
				break;
			case 17:
				reasonsLeavingFamily.add(valueObject);
				break;
			case 18:
				abusedBy.add(valueObject);
				break;
			case 19:
				institutionDocType.add(valueObject);
				break;
			case 21:
				icpDesignation.add(valueObject);
				break;
			case 22:
				stayOfTheChild.add(valueObject);
				break;
			case 24:
				organizationType.add(valueObject);
				break;
			case 25:
				childSex.add(valueObject);
				break;
			case 26:
				exploitationFaced.add(valueObject);
				break;
			case 27:
				orderType.add(valueObject);
				break;
			case 28:
				idProofList.add(valueObject);
				break;
			case 29:
				bankAccountStatus.add(valueObject);
				break;
			case 30:
				childBelongingsHandedOverToWhom.add(valueObject);
				break;
			case 31:
				firstInteractionReportByWhom.add(valueObject);
				break;
			case 32:
				learningInstitute.add(valueObject);
				break;
			case 33:
				proceedingsBeforeCommittee.add(valueObject);
				break;
			case 34:
				icpParentType.add(valueObject);
				break;
			case 35:
				placedOrder.add(valueObject);
				break;
			case 36:
				childCategoryList.add(valueObject);
				break;
			case 37:
				religionList.add(valueObject);
				break;
			case 38:
				typeOfConstruction.add(valueObject);
				break;
			case 39:
				noOfRooms.add(valueObject);
				break;
			case 40:
				typeOfOccupancy.add(valueObject);
				break;
			case 41:
				childBroughtByWhomList.add(valueObject);
				break;
			case 42:
				frequencyOfVisit.add(valueObject);
				break;
			case 43:
				familyType.add(valueObject);
				break;
			case 44:
				householdArticles.add(valueObject);
				break;
			case 45:
				vehiclesOwned.add(valueObject);
				break;
			case 46:
				marraigeType.add(valueObject);
				break;
			case 47:
				socialActivity.add(valueObject);
				break;
			case 48:
				parentalCareTowardsChild.add(valueObject);
				break;
			case 49:
				delinquentBehaviour.add(valueObject);
				break;
			case 50:
				delinquentbehaviourReason.add(valueObject);
				break;
			case 51:
				incomeUtilization.add(valueObject);
				break;
			case 52:
				detailsOfSaving.add(valueObject);
				break;
			case 53:
				workDuration.add(valueObject);
				break;
			case 54:
				mediumOfInstruction.add(valueObject);
				break;
			case 55:
				promotionStatus.add(valueObject);
				break;
			case 56:
				extraCurricularActivity.add(valueObject);
				break;
			case 57:
				friendshipPriorToChildrensHome.add(valueObject);
				break;
			case 58:
				membershipInGroupDetails.add(valueObject);
				break;
			case 59:
				positionInGroup.add(valueObject);
				break;
			case 60:
				purposeOfTakingMembership.add(valueObject);
				break;
			case 61:
				attitudeOfTheGroup.add(valueObject);
				break;
			case 62:
				meetingPointOfGroup.add(valueObject);
				break;
			case 63:
				reactionOfSocietyTowardsChild.add(valueObject);
				break;
			case 64:
				reactionOfPoliceTowardsChild.add(valueObject);
				break;
			case 65:
				suggestionBy.add(valueObject);
				break;
			case 66:
				followUpBy.add(valueObject);
				break;
			case 67:
				realtionShipWithChild.add(valueObject);
				break;
			case 68:
				childEmploymentDetails.add(valueObject);
				break;
			case 69:
				reportPreparedBy.add(valueObject);
				break;
			case 70:
				sponsorshipSupportFor.add(valueObject);
				break;
			case 71:
				supervisionCareUnderWhom.add(valueObject);
				break;
			case 72:
				ciclOrgType.add(valueObject);
				break;
			case 73:
				natureOfOffenceAlleged.add(valueObject);
				natureOfCrime.add(valueObject);
				break;
			case 74:
				reasonForAllegedOffence.add(valueObject);
				break;
			case 75:
				determinationBy.add(valueObject);
				break;
			case 76:
				childInCustodyOf.add(valueObject);
				break;
			case 77:
				quarters.add(valueObject);
				break;
			case 78:
				buildingType.add(valueObject);
				break;
			case 79:
				constructionOfBuilding.add(valueObject);
				break;
			case 80:	
				severity.add(valueObject);
				break;
			case 81:	
				followType.add(valueObject);
				break;
			case 82:	
				restorationType.add(valueObject);
				break;
			case 83:	
				childLook.add(valueObject);
				break;
			case 84:	
				schoolType.add(valueObject);
				break;
			case 85:	
				itemsAvailable.add(valueObject);
				break;
			case 86:	
				intellectiveStatus.add(valueObject);
				break;
			case 87:	
				courseStatus.add(valueObject);
				break;
			case 88:	
				progessOfCourse.add(valueObject);
				break;
			case 89:	
				parentsBehaviour.add(valueObject);
				break;
			case 90:	
				childsBehaviour.add(valueObject);
				break;
			case 91:	
				complianceByGovt.add(valueObject);
				break;
			case 92:	
				problemShareTime.add(valueObject);
				break;
				
			}
			
				
			
		}
		
		ListObject listObject=new ListObject();
		Map<String, Object> typeDetailsMap=new HashMap<>();
		typeDetailsMap.put("ageList", ageList);
		typeDetailsMap.put("genderList", genderList);
		typeDetailsMap.put("AGE", ageRanges);
		typeDetailsMap.put("ordersByCWC", ordersPassedByCWC);
		typeDetailsMap.put("childSex", childSex);
		typeDetailsMap.put("organizationType", organizationType);
		typeDetailsMap.put("familyMemberRelationship", familyMemberRelationship);
		typeDetailsMap.put("childEducationDtls", childEducationDtls);
		typeDetailsMap.put("childSchoolDtls", childSchoolDtls);
		typeDetailsMap.put("childHealthStatus", childHealthStatus);
		typeDetailsMap.put("childWasStayingWith", childWasStayingWith);
		typeDetailsMap.put("institutionDocType", institutionDocType);
		typeDetailsMap.put("differentlyAbledType", differentlyAbledType);
		typeDetailsMap.put("goodHabits", goodHabits);
		typeDetailsMap.put("badHabits", badHabits);
		typeDetailsMap.put("reasonLeavingSchool", reasonLeavingSchool);
		typeDetailsMap.put("majorityFriendTypes", majorityFriendTypes);
		typeDetailsMap.put("reasonsLeavingFamily", reasonsLeavingFamily);
		typeDetailsMap.put("abusedBy", abusedBy);
		typeDetailsMap.put("exploitationFaced", exploitationFaced);
		typeDetailsMap.put("orderType", orderType);
		typeDetailsMap.put("idProofList", idProofList);
		typeDetailsMap.put("bankAccountStatus", bankAccountStatus);
		typeDetailsMap.put("childBelongingsHandedOverToWhom", childBelongingsHandedOverToWhom);
		typeDetailsMap.put("firstInteractionReportByWhom", firstInteractionReportByWhom);
		typeDetailsMap.put("learningInstitute", learningInstitute);
		typeDetailsMap.put("proceedingsBeforeCommittee", proceedingsBeforeCommittee);
		typeDetailsMap.put("icpDesignation", icpDesignation);
		typeDetailsMap.put("stayOfTheChild", stayOfTheChild);
		typeDetailsMap.put("casteList", casteList);
		typeDetailsMap.put("educationLevels", educationLevels);
		typeDetailsMap.put("icpParentType", icpParentType);
		typeDetailsMap.put("placedOrder", placedOrder);
		typeDetailsMap.put("childCategoryList", childCategoryList);
		typeDetailsMap.put("religionList", religionList);
		typeDetailsMap.put("typeOfConstruction", typeOfConstruction);
		typeDetailsMap.put("noOfRooms", noOfRooms);
		typeDetailsMap.put("typeOfOccupancy", typeOfOccupancy);
		typeDetailsMap.put("childBroughtByWhomList", childBroughtByWhomList);
		typeDetailsMap.put("frequencyOfVisit", frequencyOfVisit);
		typeDetailsMap.put("familyType", familyType);
		typeDetailsMap.put("householdArticles", householdArticles);
		typeDetailsMap.put("vehiclesOwned", vehiclesOwned);
		typeDetailsMap.put("marraigeType", marraigeType);
		typeDetailsMap.put("socialActivity", socialActivity);
		typeDetailsMap.put("parentalCareTowardsChild", parentalCareTowardsChild);
		typeDetailsMap.put("delinquentBehaviour", delinquentBehaviour);
		typeDetailsMap.put("delinquentbehaviourReason", delinquentbehaviourReason);
		typeDetailsMap.put("incomeUtilization", incomeUtilization);
		typeDetailsMap.put("detailsOfSaving", detailsOfSaving);
		typeDetailsMap.put("workDuration", workDuration);
		typeDetailsMap.put("mediumOfInstruction", mediumOfInstruction);
		typeDetailsMap.put("promotionStatus", promotionStatus);
		typeDetailsMap.put("extraCurricularActivity", extraCurricularActivity);
		typeDetailsMap.put("friendshipPriorToChildrensHome", friendshipPriorToChildrensHome);
		typeDetailsMap.put("membershipInGroupDetails", membershipInGroupDetails);
		typeDetailsMap.put("positionInGroup", positionInGroup);
		typeDetailsMap.put("purposeOfTakingMembership", purposeOfTakingMembership);
		typeDetailsMap.put("attitudeOfTheGroup", attitudeOfTheGroup);
		typeDetailsMap.put("meetingPointOfGroup", meetingPointOfGroup);
		typeDetailsMap.put("reactionOfSocietyTowardsChild", reactionOfSocietyTowardsChild);
		typeDetailsMap.put("reactionOfPoliceTowardsChild", reactionOfPoliceTowardsChild);
		typeDetailsMap.put("suggestionBy", suggestionBy);
		typeDetailsMap.put("followUpBy", followUpBy);
		typeDetailsMap.put("realtionShipWithChild", realtionShipWithChild);
		typeDetailsMap.put("childEmploymentDetails", childEmploymentDetails);
		typeDetailsMap.put("reportPreparedBy", reportPreparedBy);
		typeDetailsMap.put("sponsorshipSupportFor", sponsorshipSupportFor);
		typeDetailsMap.put("supervisionCareUnderWhom", supervisionCareUnderWhom);
		typeDetailsMap.put("ciclOrgType", ciclOrgType);
		typeDetailsMap.put("natureOfOffenceAlleged", natureOfOffenceAlleged);
		typeDetailsMap.put("reasonForAllegedOffence", reasonForAllegedOffence);
		typeDetailsMap.put("natureOfCrime", natureOfCrime);
		typeDetailsMap.put("determinationBy", determinationBy);
		typeDetailsMap.put("childInCustodyOf", childInCustodyOf);
		typeDetailsMap.put("quarters", quarters);
		typeDetailsMap.put("buildingType", buildingType);
		typeDetailsMap.put("constructionOfBuilding", constructionOfBuilding);
		typeDetailsMap.put("severity", severity);
		
		typeDetailsMap.put("followType", followType);
		typeDetailsMap.put("restorationType", restorationType);
		typeDetailsMap.put("childLook", childLook);
		typeDetailsMap.put("schoolType", schoolType);
		typeDetailsMap.put("itemsAvailable", itemsAvailable);
		typeDetailsMap.put("intellectiveStatus", intellectiveStatus);
		typeDetailsMap.put("courseStatus", courseStatus);
		typeDetailsMap.put("progessOfCourse", progessOfCourse);
		typeDetailsMap.put("parentsBehaviour", parentsBehaviour);
		typeDetailsMap.put("childsBehaviour", childsBehaviour);
		typeDetailsMap.put("complianceByGovt", complianceByGovt);
		typeDetailsMap.put("problemShareTime", problemShareTime);
		
		listObject.setKey("typeDetailsMap");
		listObject.setValue(typeDetailsMap);
		
		return typeDetailsMap;
	}
	
	
	
	@Override
	public List<CCIInfoMapModel> fetchCCISAAList() {
		List<CCIDetails> saaList=cciInfoRepository.fetchAllCCIs();
		List<CCIInfoMapModel> saaModels=new ArrayList<>();
		for (CCIDetails cciDetails : saaList) {
			CCIInfoMapModel cciInfoMapModel = new CCIInfoMapModel();
			cciInfoMapModel.setCciId(cciDetails.getCciId());
			cciInfoMapModel.setName(null != cciDetails.getCciName() ? cciDetails.getCciName() : null);
			cciInfoMapModel.setAddress(null == cciDetails.getAddress() ? null : cciDetails.getAddress());
			saaModels.add(cciInfoMapModel);
		}
		
		CCIInfoMapModel cciInfoMapModel = new CCIInfoMapModel();
		cciInfoMapModel.setCciId(0);
		cciInfoMapModel.setName("Others");
		saaModels.add(cciInfoMapModel);
		
		return saaModels;
	}
	
	
	
	@Override
	public List<UserDetailModel> fetchCWCs() {
		List<UserDetails> cwcList = userDetailsRepository.fetchUserByDesignation(7);
		List<UserDetailModel> cwcModels = new ArrayList<>();
		for (UserDetails userDetails : cwcList) {
			UserDetailModel userDetailModel = new UserDetailModel();
			userDetailModel.setUserName(userDetails.getUserName());
			userDetailModel.setUserId(userDetails.getUserId());
			userDetailModel.setName(userDetails.getCwcName());
			userDetailModel.setAreaId(userDetails.getArea().getAreaId());
			userDetailModel.setAreaName(userDetails.getArea().getAreaName());
			
			cwcModels.add(userDetailModel);
		}
		return cwcModels;
	}
	
	
	@Override
	public List<ChildDetailsModel> fetchChildDetails() throws Exception {
		List<CCTSTypeDetails> typeDetails = cctsTypeDetailsRepository.getAllTypeDetails();
		typeMap = getTypeMap(typeDetails);
		
		int designation=((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getDesignationId();
		Integer areaId=((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getAreaId();
		List<ChildDetailsModel> childModel = new ArrayList<ChildDetailsModel>();
		List<ChildDetails> childList = new ArrayList<>();
		if(designation==7){
			childList = childDetailsRepository.findCNCPchilds(areaId);
		}
		else if(designation==8 || designation==9){
			childList = childDetailsRepository.findCICLChilds(areaId);
		}
		else if(designation==4){
			childList = childDetailsRepository.findChildsByDivision(areaId);
		}
		else if(designation==5 || designation==6 || designation==9){
			childList = childDetailsRepository.findChildsByDistrict(areaId);
		}
		else if(designation==10){
			Integer userId = ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getUserId();
			CciUserMapping cciUserMapping = cciUserRepository.findByUserId(userId);
			Integer cciId=cciUserMapping.getCciId();
			childList = childDetailsRepository.findByCciDetailsCciId(cciId);
		}
		else{
			childList = childDetailsRepository.findAll();
		}
		for(ChildDetails childDetails : childList){
			ChildDetailsModel childDetailsModel = new ChildDetailsModel();
			childDetailsModel.setId(childDetails.getId());
			childDetailsModel.setChildId(null==childDetails.getChildId()?null:childDetails.getChildId());
			childDetailsModel.setChildName(null==childDetails.getChildName()?null:childDetails.getChildName());
//			childDetailsModel.setChildPhoto(null==childDetails.getChildPhoto()?null:childDetails.getChildPhoto());
			childDetailsModel.setRecordCreatedOn(null==childDetails.getRecordCreatedOn()?null:childDetails.getRecordCreatedOn());
			childDetailsModel.setAge(null==childDetails.getAge()?null:Integer.parseInt(typeMap.get(childDetails.getAge()).getName()));
			childDetailsModel.setCurrentAge(null==childDetails.getCurrentAge()?null:childDetails.getCurrentAge());
			childDetailsModel.setChildDistrict(null==childDetails.getChildDistrict()?null:childDetails.getChildDistrict().getAreaId());
			childDetailsModel.setDistrict(null==childDetails.getChildDistrict()?null:DomainToModelConverter.getDistrict(childDetails.getChildDistrict()));
			childDetailsModel.setChildSex(null==childDetails.getChildSex()?null:childDetails.getChildSex());
			childDetailsModel.setChildPhoto(exportPDFServiceImpl.getChildPhoto(childDetails.getChildPhoto()+"-thumbnail.jpg"));
			childDetailsModel.setFinalOrderFilled(null==childDetails.getFinalOrderFilled()?null:childDetails.getFinalOrderFilled());
			childDetailsModel.setProgramType(childDetails.getProgramType());
			childDetailsModel.setCciId(null==childDetails.getCciDetails()?null:childDetails.getCciDetails().getCciId());
			
			childDetailsModel.setFinalOrderFilled(childDetails.getFinalOrderFilled());
			childDetailsModel.setSirFilled(childDetails.getSirFilled());
			childDetailsModel.setIcpFilled(childDetails.getIcpFilled());
			childDetailsModel.setCciName(null==childDetails.getCciDetails()?null:childDetails.getCciDetails().getCciName());
			childDetailsModel.setCaseHistoryFilled(childDetails.getCaseHistoryFilled());
			
			childModel.add(childDetailsModel);
		}
		Collections.sort(childModel, new Comparator<ChildDetailsModel>(){
			public int compare(ChildDetailsModel firstValue, ChildDetailsModel secondValue){
				return (secondValue.getId()).compareTo(firstValue.getId());
			}
		});
		return childModel;
	}
	
	@Override
	public List<GridMenuItemModel> getGridMenuItems(Integer designationId) {
		List<GridMenuItemDetails> menuItems=gridMenuRepository.getGridMenuItemsByUserId(designationId);
		List<GridMenuItemModel> menuItemModels=new ArrayList<>();
		
		for (GridMenuItemDetails gridMenuItemDetails : menuItems) {
			GridMenuItemModel gridMenuItemModel = new GridMenuItemModel();
			gridMenuItemModel.setItemId(null==gridMenuItemDetails.getItemId()?null:gridMenuItemDetails.getItemId());
			gridMenuItemModel.setItemName(null==gridMenuItemDetails.getItemName()?null:gridMenuItemDetails.getItemName());
			gridMenuItemModel.setImagePath(null==gridMenuItemDetails.getImagePath()?null:gridMenuItemDetails.getImagePath());
			gridMenuItemModel.setUrl(null==gridMenuItemDetails.getUrl()?null:gridMenuItemDetails.getUrl());
			gridMenuItemModel.setImagePath2(null==gridMenuItemDetails.getImage2Path()?null:gridMenuItemDetails.getImage2Path());
			menuItemModels.add(gridMenuItemModel);
		}
		return menuItemModels;
	}



	@Override
	public List<AreaDetailsModel> getUserWiseArea(Integer areaId) {
		List<AreaDetails> areaDetails = areaRepository.fetchUserWiseArea(areaId); 
		List<AreaDetailsModel> areaDetailsModels = new ArrayList<>();
		for (AreaDetails areaDetail : areaDetails) {
			AreaDetailsModel areaDetailsModel=new AreaDetailsModel();
			areaDetailsModel.setAreaCode(areaDetail.getAreaCode());
			areaDetailsModel.setAreaId(areaDetail.getAreaId());
			areaDetailsModel.setAreaName(areaDetail.getAreaName());
			areaDetailsModel.setChecked(false);
			
			areaDetailsModels.add(areaDetailsModel);
		}
		return areaDetailsModels;
	}



	@Override
	public List<AreaDetailsModel> getDistrictList(Integer areaLevel) {
		List<AreaDetails> areaList=areaRepository.fetchAreaByLevel(areaLevel);
		List<AreaDetailsModel> areaDetailsModels = new ArrayList<>();
		for (AreaDetails areaDetail : areaList) {
			AreaDetailsModel areaDetailsModel=new AreaDetailsModel();
			areaDetailsModel.setAreaCode(areaDetail.getAreaCode());
			areaDetailsModel.setAreaId(areaDetail.getAreaId());
			areaDetailsModel.setAreaName(areaDetail.getAreaName());
			areaDetailsModel.setChecked(false);
			
			areaDetailsModels.add(areaDetailsModel);
		}
		return areaDetailsModels;
	}
	
	

}
