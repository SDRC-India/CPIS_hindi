package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CaseHistoryCCI;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.ChildEmploymentDetails;
import org.sdrc.cpis.domains.FamilyHistoryOfCrime;
import org.sdrc.cpis.domains.HealthStatusOfChild;
import org.sdrc.cpis.models.CaseHistoryCCIModel;
import org.sdrc.cpis.models.ChildEmploymentDetailsModel;
import org.sdrc.cpis.models.FamilyHistoryOfCrimeModel;
import org.sdrc.cpis.models.HealthStatusOfChildModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CaseHistoryRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.ChildEmploymentDetailsRepository;
import org.sdrc.cpis.repository.FamilyHistoryOfCrimeRepository;
import org.sdrc.cpis.repository.HealthStatusOfChildRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseHistoryServiceImplementation implements CaseHistoryService{
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private CaseHistoryRepository caseHistoryRepository;
	
	@Autowired
	private FamilyHistoryOfCrimeRepository familyHistoryOfCrimeRepository;
	
	@Autowired
	private ChildEmploymentDetailsRepository childEmploymentDetailsRepository;
	
	@Autowired
	private HealthStatusOfChildRepository healthStatusOfChildRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private CCTSChildRegistrationService cctsChildRegistrationService;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	Map<Integer, ValueObject> typeMap;
	
	public Map<Integer, ValueObject> getTypeMap(){
	    List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(null == cctsTypeDetails.getTypeDetailsNameHindi() ? null : cctsTypeDetails.getTypeDetailsNameHindi());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}
	
	public String getTypeName (String idString, String lang){
		String name = "";
		List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		String [] idArray = idString.split(",");
		
		for(String id : idArray){
			int id1 = Integer.parseInt(id.trim());
			
			for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
				if(id1 == cctsTypeDetails.getTypeDetailsId())
					name += lang.equals("en") ? cctsTypeDetails.getTypeDetailsName() + "," : cctsTypeDetails.getTypeDetailsNameHindi() + ",";
			}
		}
		name = name.substring(0, name.length()-1);
		
		return name;
	}

	@Override
	public void saveCaseHistoryData(CaseHistoryCCIModel caseHistoryCCIModel) throws Exception {
		
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		CaseHistoryCCI caseHistoryCCI = new CaseHistoryCCI();
		List<FamilyHistoryOfCrime> familyHistoryOfCrimes = new ArrayList<FamilyHistoryOfCrime>();
		List<ChildEmploymentDetails> childEmploymentDetails = new ArrayList<ChildEmploymentDetails>();
		List<HealthStatusOfChild> healthStatusOfChilds = new ArrayList<HealthStatusOfChild>();
		
		if(caseHistoryCCIModel.getFamilyHistoryOfCrimeModels() != null && caseHistoryCCIModel.getFamilyHistoryOfCrimeModels().size() > 0){
			for(FamilyHistoryOfCrimeModel familyHistoryOfCrimeModel : caseHistoryCCIModel.getFamilyHistoryOfCrimeModels()){
				FamilyHistoryOfCrime familyHistoryOfCrime = new FamilyHistoryOfCrime();
				
				familyHistoryOfCrime.setLegalStatusOfTheCase(null == familyHistoryOfCrimeModel.getLegalStatusOfTheCase() ? null : familyHistoryOfCrimeModel.getLegalStatusOfTheCase());
				familyHistoryOfCrime.setNatureOfCrime(null == familyHistoryOfCrimeModel.getNatureOfCrime() ? null : familyHistoryOfCrimeModel.getNatureOfCrime());
				familyHistoryOfCrime.setPeriodOfConfinement(null == familyHistoryOfCrimeModel.getPeriodOfConfinement() ? null : familyHistoryOfCrimeModel.getPeriodOfConfinement());
				familyHistoryOfCrime.setPunishmentAwarded(null == familyHistoryOfCrimeModel.getPunishmentAwarded() ? null : familyHistoryOfCrimeModel.getPunishmentAwarded());
				familyHistoryOfCrime.setRelationshipWithChild(null == familyHistoryOfCrimeModel.getRelationshipWithChild() ? null : familyHistoryOfCrimeModel.getRelationshipWithChild().getId());
				familyHistoryOfCrime.setArrestIfAny(familyHistoryOfCrimeModel.isArrestIfAny());
				familyHistoryOfCrime.setRelationshipWithChildOthers(null == familyHistoryOfCrimeModel.getRelationshipWithChildOthers() ? null : familyHistoryOfCrimeModel.getRelationshipWithChildOthers());
				familyHistoryOfCrime.setChildId(childDetailsRepository.findChildById(caseHistoryCCIModel.getChildId()));
				familyHistoryOfCrime.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
				familyHistoryOfCrime.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
				familyHistoryOfCrime.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
				
				familyHistoryOfCrimes.add(familyHistoryOfCrime);
				
			}
			
			familyHistoryOfCrimeRepository.save(familyHistoryOfCrimes);
		}
		
		if(caseHistoryCCIModel.getChildEmploymentDetailsModels() != null && caseHistoryCCIModel.getChildEmploymentDetailsModels().size() > 0){
			for(ChildEmploymentDetailsModel childEmploymentDetailsModel : caseHistoryCCIModel.getChildEmploymentDetailsModels()){
				ChildEmploymentDetails childEmploymentDetail = new ChildEmploymentDetails();
				
				childEmploymentDetail.setDuration(null == childEmploymentDetailsModel.getDuration() ? null : childEmploymentDetailsModel.getDuration());
				childEmploymentDetail.setTiming(null == childEmploymentDetailsModel.getTiming() ? null : childEmploymentDetailsModel.getTiming());
				childEmploymentDetail.setTypeOfEmployment(null == childEmploymentDetailsModel.getTypeOfEmployment() ? null : childEmploymentDetailsModel.getTypeOfEmployment().getId());
				childEmploymentDetail.setWagesEarned(null == childEmploymentDetailsModel.getWagesEarned() ? null : childEmploymentDetailsModel.getWagesEarned());
				childEmploymentDetail.setTypeOfEmploymentOther(null == childEmploymentDetailsModel.getTypesOfEmploymentOther() ? null : childEmploymentDetailsModel.getTypesOfEmploymentOther());
				childEmploymentDetail.setChildId(childDetailsRepository.findChildById(caseHistoryCCIModel.getChildId()));
				
				childEmploymentDetails.add(childEmploymentDetail);
				
			}
			
			childEmploymentDetailsRepository.save(childEmploymentDetails);
		}
		
		if(caseHistoryCCIModel.getHealthStatusOfChildModels() != null && caseHistoryCCIModel.getHealthStatusOfChildModels().size() > 0){
			for(HealthStatusOfChildModel healthStatusOfChildModel : caseHistoryCCIModel.getHealthStatusOfChildModels()){
				HealthStatusOfChild healthStatusOfChild = new HealthStatusOfChild();
				
				healthStatusOfChild.setDateOfReview(null == healthStatusOfChildModel.getDateOfReview() ? null : healthStatusOfChildModel.getDateOfReview());
				healthStatusOfChild.setDental(null == healthStatusOfChildModel.getDental() ? null : healthStatusOfChildModel.getDental());
				healthStatusOfChild.setEnt(null == healthStatusOfChildModel.getEnt() ? null : healthStatusOfChildModel.getEnt());
				healthStatusOfChild.setEye(null == healthStatusOfChildModel.getEye() ? null : healthStatusOfChildModel.getEye());
				healthStatusOfChild.setHeight(null == healthStatusOfChildModel.getHeight() ? null : healthStatusOfChildModel.getHeight());
				healthStatusOfChild.setNutritiousDietGiven(null == healthStatusOfChildModel.getNutritiousDietGiven() ? null : healthStatusOfChildModel.getNutritiousDietGiven());
				healthStatusOfChild.setStress(null == healthStatusOfChildModel.getStress() ? null : healthStatusOfChildModel.getStress());
				healthStatusOfChild.setWeight(null == healthStatusOfChildModel.getWeight() ? null : healthStatusOfChildModel.getWeight());
				healthStatusOfChild.setQuarterNo(null == healthStatusOfChildModel.getQuarterNo() ? null : healthStatusOfChildModel.getQuarterNo());
				healthStatusOfChild.setChildId(childDetailsRepository.findChildById(caseHistoryCCIModel.getChildId()));
				
				healthStatusOfChilds.add(healthStatusOfChild);
			}
			
			healthStatusOfChildRepository.save(healthStatusOfChilds);
		}
		
		caseHistoryCCI.setActualWeightForHeightWeightChart(null == caseHistoryCCIModel.getActualWeightForHeightWeightChart() ? null : caseHistoryCCIModel.getActualWeightForHeightWeightChart());
		caseHistoryCCI.setAdmissibleWeightForHeightWeightChart(null == caseHistoryCCIModel.getAdmissibleWeightForHeightWeightChart() ? null : caseHistoryCCIModel.getAdmissibleWeightForHeightWeightChart());
		caseHistoryCCI.setAgeAtTimeOfAdmission(null == caseHistoryCCIModel.getAgeAtTimeOfAdmission() ? null : caseHistoryCCIModel.getAgeAtTimeOfAdmission());
		caseHistoryCCI.setAttitudeOfGroup_League(null == caseHistoryCCIModel.getAttitudeOfGroup_LeagueObject() ? null : caseHistoryCCIModel.getAttitudeOfGroup_LeagueObject().getId());
		caseHistoryCCI.setBrothersMarraigeType(null == caseHistoryCCIModel.getBrothersMarraigeTypeObject() ? null : caseHistoryCCIModel.getBrothersMarraigeTypeObject().getId());
		caseHistoryCCI.setCaste(null == caseHistoryCCIModel.getCasteObject() ? null : caseHistoryCCIModel.getCasteObject().getId());
		caseHistoryCCI.setCategory(null == caseHistoryCCIModel.getCategory() ? null : caseHistoryCCIModel.getCategory());
		caseHistoryCCI.setCategoryDetails(null == caseHistoryCCIModel.getCategoryDetails() ? null : caseHistoryCCIModel.getCategoryDetails());
		caseHistoryCCI.setCategoryDetailsOthers(null == caseHistoryCCIModel.getCategoryDetailsOther() ? null : caseHistoryCCIModel.getCategoryDetailsOther());
		caseHistoryCCI.setChildAttainPubertyAge(null == caseHistoryCCIModel.getChildAttainPubertyAge() ? null : caseHistoryCCIModel.getChildAttainPubertyAge());
		caseHistoryCCI.setChildBroughtBeforeCWCByWhom(null == caseHistoryCCIModel.getChildBroughtBeforeCWCByWhomObject() ? null : caseHistoryCCIModel.getChildBroughtBeforeCWCByWhomObject().getId());
		caseHistoryCCI.setChildBroughtBeforeCWCByWhomRelationship(null == caseHistoryCCIModel.getChildBroughtBeforeCWCByWhomRelationship() ? null : caseHistoryCCIModel.getChildBroughtBeforeCWCByWhomRelationship());
		caseHistoryCCI.setChildId(childDetailsRepository.findChildById(caseHistoryCCIModel.getChildId()));
		caseHistoryCCI.setChildImagePath(exportPDFServiceImpl.getPhotoPath(caseHistoryCCIModel.getChildImgpath(), caseHistoryCCIModel.getChildId(),"caseHistory"));
		caseHistoryCCI.setCorrespondenceWithParentAfterInstitutionalization(null == caseHistoryCCIModel.getCorrespondenceWithParentAfterInstitutionalizationObject() ? null : caseHistoryCCIModel.getCorrespondenceWithParentAfterInstitutionalizationObject().getId());
		caseHistoryCCI.setCorrespondenceWithParentPriorToInstitutionalization(null == caseHistoryCCIModel.getCorrespondenceWithParentPriorToInstitutionalizationObject() ? null : caseHistoryCCIModel.getCorrespondenceWithParentPriorToInstitutionalizationObject().getId());
		caseHistoryCCI.setDate(null == caseHistoryCCIModel.getDate() ? null : caseHistoryCCIModel.getDate());
		caseHistoryCCI.setDateMonthYearForHeightWeightChart(null == caseHistoryCCIModel.getDateMonthYearForHeightWeightChart() ? null : caseHistoryCCIModel.getDateMonthYearForHeightWeightChart());
		caseHistoryCCI.setDescriptionOfHousing1(null == caseHistoryCCIModel.getDescriptionOfHousing1Object() ? null : caseHistoryCCIModel.getDescriptionOfHousing1Object().getId());
		caseHistoryCCI.setDescriptionOfHousing2(null == caseHistoryCCIModel.getDescriptionOfHousing2Object() ? null : caseHistoryCCIModel.getDescriptionOfHousing2Object().getId());
		caseHistoryCCI.setDescriptionOfHousing3(null == caseHistoryCCIModel.getDescriptionOfHousing3Object() ? null : caseHistoryCCIModel.getDescriptionOfHousing3Object().getId());
		caseHistoryCCI.setDetailOfMembershipInGroup(null == caseHistoryCCIModel.getDetailOfMembershipInGroup() ? null : caseHistoryCCIModel.getDetailOfMembershipInGroup());
		caseHistoryCCI.setDetailOfMembershipInGroupOthers(null == caseHistoryCCIModel.getDetailOfMembershipInGroupOthers() ? null : caseHistoryCCIModel.getDetailOfMembershipInGroupOthers());
		caseHistoryCCI.setDetailsOfCertificationPath(null == caseHistoryCCIModel.getDetailsOfCertificationPath() ? null : caseHistoryCCIModel.getDetailsOfCertificationPath());
		caseHistoryCCI.setDetailsOfDelinquentBehaviour(null == caseHistoryCCIModel.getDetailsOfDelinquentBehaviour() ? null : caseHistoryCCIModel.getDetailsOfDelinquentBehaviour());
		caseHistoryCCI.setDetailsOfDelinquentBehaviourOthers(null == caseHistoryCCIModel.getDetailsOfDelinquentBehaviourOthers() ? null : caseHistoryCCIModel.getDetailsOfDelinquentBehaviourOthers());
		caseHistoryCCI.setDetailsOfDisability(null == caseHistoryCCIModel.getDetailsOfDisability() ? null : caseHistoryCCIModel.getDetailsOfDisability());
		caseHistoryCCI.setDetailsOfIncomeUtilization(null == caseHistoryCCIModel.getDetailsOfIncomeUtilization() ? null : caseHistoryCCIModel.getDetailsOfIncomeUtilization());
		caseHistoryCCI.setDetailsOfSaving(null == caseHistoryCCIModel.getDetailsOfSaving() ? null : caseHistoryCCIModel.getDetailsOfSaving());
		caseHistoryCCI.setDetailsOfSavingOthers(null == caseHistoryCCIModel.getDetailsOfSavingOthers() ? null : caseHistoryCCIModel.getDetailsOfSavingOthers());
		caseHistoryCCI.setDetailsOfSchoolStudiedLast(null == caseHistoryCCIModel.getDetailsOfSchoolStudiedLastObject() ? null : caseHistoryCCIModel.getDetailsOfSchoolStudiedLastObject().getId());
		caseHistoryCCI.setDurationOfWorkingHours(null == caseHistoryCCIModel.getDurationOfWorkingHoursObject() ? null : caseHistoryCCIModel.getDurationOfWorkingHoursObject().getId());
		caseHistoryCCI.setEducationalAttainmentClassStudied(null == caseHistoryCCIModel.getEducationalAttainmentClassStudied() ? null : caseHistoryCCIModel.getEducationalAttainmentClassStudied());
		caseHistoryCCI.setEducationalAttainmentNoOfYears(null == caseHistoryCCIModel.getEducationalAttainmentNoOfYears() ? null : caseHistoryCCIModel.getEducationalAttainmentNoOfYears());
		caseHistoryCCI.setEducationalAttainmentPromote_Detained(null == caseHistoryCCIModel.getEducationalAttainmentPromote_Detained() ? null : caseHistoryCCIModel.getEducationalAttainmentPromote_Detained().getId());
		caseHistoryCCI.setExploitaionFacedByTheChild(null == caseHistoryCCIModel.getExploitaionFacedByTheChild() ? null : caseHistoryCCIModel.getExploitaionFacedByTheChild());
		caseHistoryCCI.setExploitaionFacedByTheChildOthers(null == caseHistoryCCIModel.getExploitaionFacedByTheChildOthers() ? null : caseHistoryCCIModel.getExploitaionFacedByTheChildOthers());
		caseHistoryCCI.setFamilyType(null == caseHistoryCCIModel.getFamilyTypeObject() ? null : caseHistoryCCIModel.getFamilyTypeObject().getId());
		caseHistoryCCI.setFriendshipPriorToAdmissionIntoChildrensHome(null == caseHistoryCCIModel.getFriendshipPriorToAdmissionIntoChildrensHome() ? null : caseHistoryCCIModel.getFriendshipPriorToAdmissionIntoChildrensHome());
		caseHistoryCCI.setFriendshipPriorToAdmissionIntoChildrensHomeOthers(null == caseHistoryCCIModel.getFriendshipPriorToAdmissionIntoChildrensHomeOthers() ? null : caseHistoryCCIModel.getFriendshipPriorToAdmissionIntoChildrensHomeOthers());
		caseHistoryCCI.setHeightAtTimeOfAdmission(null == caseHistoryCCIModel.getHeightAtTimeOfAdmission() ? null : caseHistoryCCIModel.getHeightAtTimeOfAdmission());
		caseHistoryCCI.setHeightForHeightWeightChart(null == caseHistoryCCIModel.getHeightForHeightWeightChart() ? null : caseHistoryCCIModel.getHeightForHeightWeightChart());
		caseHistoryCCI.setHistoryOfChildEducation(null == caseHistoryCCIModel.getHistoryOfChildEducation() ? null : caseHistoryCCIModel.getHistoryOfChildEducation());
		caseHistoryCCI.setHistoryOfChildExtraCuricularActivites(null == caseHistoryCCIModel.getHistoryOfChildExtraCuricularActivites() ? null : caseHistoryCCIModel.getHistoryOfChildExtraCuricularActivites());
		caseHistoryCCI.setHistoryOfChildHealth(null == caseHistoryCCIModel.getHistoryOfChildHealth() ? null : caseHistoryCCIModel.getHistoryOfChildHealth());
		caseHistoryCCI.setHistoryOfChildOthers(null == caseHistoryCCIModel.getHistoryOfChildOthers() ? null : caseHistoryCCIModel.getHistoryOfChildOthers());
		caseHistoryCCI.setHistoryOfChildVocationalTraining(null == caseHistoryCCIModel.getHistoryOfChildVocationalTraining() ? null : caseHistoryCCIModel.getHistoryOfChildVocationalTraining());
		caseHistoryCCI.setHouseholdArticlesOBF(null == caseHistoryCCIModel.getHouseholdArticlesOBF() ? null : caseHistoryCCIModel.getHouseholdArticlesOBF());
		caseHistoryCCI.setLandedPropertiesOBF(null == caseHistoryCCIModel.getLandedPropertiesOBF() ? null : caseHistoryCCIModel.getLandedPropertiesOBF());
		caseHistoryCCI.setLocationMeetingPointOfGroups(null == caseHistoryCCIModel.getLocationMeetingPointOfGroupsObject() ? null : caseHistoryCCIModel.getLocationMeetingPointOfGroupsObject().getId());
		caseHistoryCCI.setMajorityFriendsAre(null == caseHistoryCCIModel.getMajorityFriendsAre() ? null : caseHistoryCCIModel.getMajorityFriendsAre());
		caseHistoryCCI.setMedicalHistoryOfChild(null == caseHistoryCCIModel.getMedicalHistoryOfChild() ? null : caseHistoryCCIModel.getMedicalHistoryOfChild());
		caseHistoryCCI.setMedicalHistoryOfParents(null == caseHistoryCCIModel.getMedicalHistoryOfParents() ? null : caseHistoryCCIModel.getMedicalHistoryOfParents());
		caseHistoryCCI.setNativeDistrict(null == caseHistoryCCIModel.getNativeDistrict() ? null : caseHistoryCCIModel.getNativeDistrict());
		caseHistoryCCI.setNativeState(null == caseHistoryCCIModel.getNativeState() ? null : caseHistoryCCIModel.getNativeState());
		caseHistoryCCI.setOthersOBF(null == caseHistoryCCIModel.getOthersOBF() ? null : caseHistoryCCIModel.getOthersOBF());
		caseHistoryCCI.setParentalCareTowardsChildBeforeAdmission(null == caseHistoryCCIModel.getParentalCareTowardsChildBeforeAdmission() ? null : caseHistoryCCIModel.getParentalCareTowardsChildBeforeAdmission());
		caseHistoryCCI.setParentsMarraigeType(null == caseHistoryCCIModel.getParentsMarraigeTypeObject() ? null : caseHistoryCCIModel.getParentsMarraigeTypeObject().getId());
		caseHistoryCCI.setPhysicalCondition(null == caseHistoryCCIModel.getPhysicalCondition() ? null : caseHistoryCCIModel.getPhysicalCondition());
		caseHistoryCCI.setPositionOfChildInGroup_League(null == caseHistoryCCIModel.getPositionOfChildInGroup_LeagueObject() ? null : caseHistoryCCIModel.getPositionOfChildInGroup_LeagueObject().getId());
		caseHistoryCCI.setPresentAge(null == caseHistoryCCIModel.getPresentAge() ? null : caseHistoryCCIModel.getPresentAge());
		caseHistoryCCI.setPurposeOfTakingMembershipInGroup(null == caseHistoryCCIModel.getPurposeOfTakingMembershipInGroup() ? null : caseHistoryCCIModel.getPurposeOfTakingMembershipInGroup());
		caseHistoryCCI.setPurposeOfTakingMembershipInGroupOthers(null == caseHistoryCCIModel.getPurposeOfTakingMembershipInGroupOthers() ? null : caseHistoryCCIModel.getPurposeOfTakingMembershipInGroupOthers());
		caseHistoryCCI.setReactionOfPoliceTowardsChildren(null == caseHistoryCCIModel.getReactionOfPoliceTowardsChildrenObject() ? null : caseHistoryCCIModel.getReactionOfPoliceTowardsChildrenObject().getId());
		caseHistoryCCI.setReactionOfSocietyTowardsChild(null == caseHistoryCCIModel.getReactionOfSocietyTowardsChildObject() ? null : caseHistoryCCIModel.getReactionOfSocietyTowardsChildObject().getId());
		caseHistoryCCI.setReasonForDelinquentBehaviour(null == caseHistoryCCIModel.getReasonForDelinquentBehaviour() ? null : caseHistoryCCIModel.getReasonForDelinquentBehaviour());
		caseHistoryCCI.setReasonForDelinquentBehaviourOthers(null == caseHistoryCCIModel.getReasonForDelinquentBehaviourOthers() ? null : caseHistoryCCIModel.getReasonForDelinquentBehaviourOthers());
		caseHistoryCCI.setReasonsForLeavingFamily(null == caseHistoryCCIModel.getReasonsForLeavingFamily() ? null : caseHistoryCCIModel.getReasonsForLeavingFamily());
		caseHistoryCCI.setReasonsForLeavingFamilyOthers(null == caseHistoryCCIModel.getReasonsForLeavingFamilyOthers() ? null : caseHistoryCCIModel.getReasonsForLeavingFamilyOthers());
		caseHistoryCCI.setReligion(null == caseHistoryCCIModel.getReligionObject() ? null : caseHistoryCCIModel.getReligionObject().getId());
		caseHistoryCCI.setReligionOthers(null == caseHistoryCCIModel.getReligionOthers() ? null : caseHistoryCCIModel.getReligionOthers());
		caseHistoryCCI.setResponseOfGeneralPublicTowardsChild(null == caseHistoryCCIModel.getResponseOfGeneralPublicTowardsChild() ? null : caseHistoryCCIModel.getResponseOfGeneralPublicTowardsChild());
		caseHistoryCCI.setSchoolMediumInstruction(null == caseHistoryCCIModel.getSchoolMediumInstructionObject() ? null : caseHistoryCCIModel.getSchoolMediumInstructionObject().getId());
		caseHistoryCCI.setSchoolMediumInstructionOthers(null == caseHistoryCCIModel.getSchoolMediumInstructionOthers() ? null : caseHistoryCCIModel.getSchoolMediumInstructionOthers());
		caseHistoryCCI.setSistersMarraigeType(null == caseHistoryCCIModel.getSistersMarraigeTypeObject() ? null : caseHistoryCCIModel.getSistersMarraigeTypeObject().getId());
		caseHistoryCCI.setSocialActivitesOfFamilyMembers(null == caseHistoryCCIModel.getSocialActivitesOfFamilyMembers() ? null : caseHistoryCCIModel.getSocialActivitesOfFamilyMembers());
		caseHistoryCCI.setTime(null == caseHistoryCCIModel.getTime() ? null : caseHistoryCCIModel.getTime());
		caseHistoryCCI.setVerbalAbuseMetByChild(null == caseHistoryCCIModel.getVerbalAbuseMetByChild() ? null : caseHistoryCCIModel.getVerbalAbuseMetByChild());
		caseHistoryCCI.setVerbalAbuseMetByChildOthers(null == caseHistoryCCIModel.getVerbalAbuseMetByChildOthers() ? null : caseHistoryCCIModel.getVerbalAbuseMetByChildOthers());
		caseHistoryCCI.setPhysicalAbuseMetByChild(null == caseHistoryCCIModel.getPhysicalAbuseMetByChild() ? null : caseHistoryCCIModel.getPhysicalAbuseMetByChild());
		caseHistoryCCI.setPhysicalAbuseMetByChildOthers(null == caseHistoryCCIModel.getPhysicalAbuseMetByChildOthers() ? null : caseHistoryCCIModel.getPhysicalAbuseMetByChildOthers());
		caseHistoryCCI.setSexualAbuseMetByChild(null == caseHistoryCCIModel.getSexualAbuseMetByChild() ? null : caseHistoryCCIModel.getSexualAbuseMetByChild());
		caseHistoryCCI.setSexualAbuseMetByChildOthers(null == caseHistoryCCIModel.getSexualAbuseMetByChildOthers() ? null : caseHistoryCCIModel.getSexualAbuseMetByChildOthers());
		caseHistoryCCI.setOtherAbuseMetByChildName(null == caseHistoryCCIModel.getOtherAbuseMetByChildName() ? null : caseHistoryCCIModel.getOtherAbuseMetByChildName());
		caseHistoryCCI.setOtherAbuseMetByChild(null == caseHistoryCCIModel.getOtherAbuseMetByChild() ? null : caseHistoryCCIModel.getOtherAbuseMetByChild());
		caseHistoryCCI.setOtherAbuseMetByChildOthers(null == caseHistoryCCIModel.getOtherAbuseMetByChildOthers() ? null : caseHistoryCCIModel.getOtherAbuseMetByChildOthers());
		caseHistoryCCI.setIllTreatmentBM(null == caseHistoryCCIModel.getIllTreatmentBM() ? null : caseHistoryCCIModel.getIllTreatmentBM());
		caseHistoryCCI.setIllTreatmentBMOthers(null == caseHistoryCCIModel.getIllTreatmentBMOthers() ? null : caseHistoryCCIModel.getIllTreatmentBMOthers());
		caseHistoryCCI.setIllTreatmentCI(null == caseHistoryCCIModel.getIllTreatmentCI() ? null : caseHistoryCCIModel.getIllTreatmentCI());
		caseHistoryCCI.setIllTreatmentCIOthers(null == caseHistoryCCIModel.getIllTreatmentCIOthers() ? null : caseHistoryCCIModel.getIllTreatmentCIOthers());
		caseHistoryCCI.setIllTreatmentdOF(null == caseHistoryCCIModel.getIllTreatmentDOF() ? null : caseHistoryCCIModel.getIllTreatmentDOF());
		caseHistoryCCI.setIllTreatmentDOFOthers(null == caseHistoryCCIModel.getIllTreatmentDOFOthers() ? null : caseHistoryCCIModel.getIllTreatmentDOFOthers());
		caseHistoryCCI.setIllTreatmentDP(null == caseHistoryCCIModel.getIllTreatmentDP() ? null : caseHistoryCCIModel.getIllTreatmentDP());
		caseHistoryCCI.setIllTreatmentDPOthers(null == caseHistoryCCIModel.getIllTreatmentDPOthers() ? null : caseHistoryCCIModel.getIllTreatmentDPOthers());
		caseHistoryCCI.setIllTreatmentOther(null == caseHistoryCCIModel.getIllTreatmentOther() ? null : caseHistoryCCIModel.getIllTreatmentOther());
		caseHistoryCCI.setIllTreatmentOtherName(null == caseHistoryCCIModel.getIllTreatmentOtherName() ? null : caseHistoryCCIModel.getIllTreatmentOtherName());
		caseHistoryCCI.setIllTreatmentOtherOther(null == caseHistoryCCIModel.getIllTreatmentOtherOther() ? null : caseHistoryCCIModel.getIllTreatmentOtherOther());
		caseHistoryCCI.setVehiclesOBF(null == caseHistoryCCIModel.getVehiclesOBF() ? null : caseHistoryCCIModel.getVehiclesOBF());
		caseHistoryCCI.setVisitOfChildAfterInstitutionalization(null == caseHistoryCCIModel.getVisitOfChildAfterInstitutionalizationObject() ? null : caseHistoryCCIModel.getVisitOfChildAfterInstitutionalizationObject().getId());
		caseHistoryCCI.setVisitOfChildPriorToInstitutionalization(null == caseHistoryCCIModel.getVisitOfChildPriorToInstitutionalizationObject() ? null : caseHistoryCCIModel.getVisitOfChildPriorToInstitutionalizationObject().getId());
		caseHistoryCCI.setVisitOfParentsAfterInstitutionalization(null == caseHistoryCCIModel.getVisitOfParentsAfterInstitutionalizationObject() ? null : caseHistoryCCIModel.getVisitOfParentsAfterInstitutionalizationObject().getId());
		caseHistoryCCI.setVisitOfParentsPriorToInstitutionalization(null == caseHistoryCCIModel.getVisitOfParentsPriorToInstitutionalizationObject() ? null : caseHistoryCCIModel.getVisitOfParentsPriorToInstitutionalizationObject().getId());
		caseHistoryCCI.setVoactionalTrainingNoOfYears(null == caseHistoryCCIModel.getVoactionalTrainingNoOfYears() ? null : caseHistoryCCIModel.getVoactionalTrainingNoOfYears());
		caseHistoryCCI.setVocationalTrainingNameOfTrade(null == caseHistoryCCIModel.getVocationalTrainingNameOfTrade() ? null : caseHistoryCCIModel.getVocationalTrainingNameOfTrade());
		caseHistoryCCI.setVocationalTrainingProficiencyObtained(null == caseHistoryCCIModel.getVocationalTrainingProficiencyObtained() ? null : caseHistoryCCIModel.getVocationalTrainingProficiencyObtained());
		caseHistoryCCI.setWeightAtTimeOfAdmission(null == caseHistoryCCIModel.getWeightAtTimeOfAdmission() ? null : caseHistoryCCIModel.getWeightAtTimeOfAdmission());
		caseHistoryCCI.setRespiratoryDisorders(null == caseHistoryCCIModel.getRespiratoryDisorders() ? null : caseHistoryCCIModel.getRespiratoryDisorders().getId());
		caseHistoryCCI.setHearingImpairment(null == caseHistoryCCIModel.getHearingImpairment() ? null : caseHistoryCCIModel.getHearingImpairment().getId());
		caseHistoryCCI.setEyeDiseases(null == caseHistoryCCIModel.getEyeDiseases() ? null : caseHistoryCCIModel.getEyeDiseases().getId());
		caseHistoryCCI.setDentalDisease(null == caseHistoryCCIModel.getDentalDisease() ? null : caseHistoryCCIModel.getDentalDisease().getId());
		caseHistoryCCI.setCardiacDiseases(null == caseHistoryCCIModel.getCardiacDiseases() ? null : caseHistoryCCIModel.getCardiacDiseases().getId());
		caseHistoryCCI.setSkinDisease(null == caseHistoryCCIModel.getSkinDisease() ? null : caseHistoryCCIModel.getSkinDisease().getId());
		caseHistoryCCI.setSexuallyTransmittedDiseases(null == caseHistoryCCIModel.getSexuallyTransmittedDiseases() ? null : caseHistoryCCIModel.getSexuallyTransmittedDiseases().getId());
		caseHistoryCCI.setNeurologicalDisorders(null == caseHistoryCCIModel.getNeurologicalDisorders() ? null : caseHistoryCCIModel.getNeurologicalDisorders().getId());
		caseHistoryCCI.setMentalHandicap(null == caseHistoryCCIModel.getMentalHandicap() ? null : caseHistoryCCIModel.getMentalHandicap().getId());
		caseHistoryCCI.setPhysicalHandicap(null == caseHistoryCCIModel.getPhysicalHandicap() ? null : caseHistoryCCIModel.getPhysicalHandicap().getId());
		caseHistoryCCI.setUrinaryTractInfections(null == caseHistoryCCIModel.getUrinaryTractInfections() ? null : caseHistoryCCIModel.getUrinaryTractInfections().getId());
		caseHistoryCCI.setOtherHealthIssues(null == caseHistoryCCIModel.getOtherHealthIssues() ? null : caseHistoryCCIModel.getOtherHealthIssues().getId());
		caseHistoryCCI.setOtherHealthIssuesName(null == caseHistoryCCIModel.getOtherHealthIssuesName() ? null : caseHistoryCCIModel.getOtherHealthIssuesName());
		caseHistoryCCI.setChildWasStayingWithWhomPriorToAdmission(null == caseHistoryCCIModel.getChildWasStayingWithWhomPriorToAdmission() ? null : caseHistoryCCIModel.getChildWasStayingWithWhomPriorToAdmission().getId());
		caseHistoryCCI.setChildWasStayingWithWhomPriorToAdmissionOther(null == caseHistoryCCIModel.getChildWasStayingWithWhomPriorToAdmissionOther() ? null : caseHistoryCCIModel.getChildWasStayingWithWhomPriorToAdmissionOther());
		caseHistoryCCI.setRelationChildRelative(null == caseHistoryCCIModel.getRelationChildRelative() ? null : caseHistoryCCIModel.getRelationChildRelative().getId());
		caseHistoryCCI.setRelationChildSiblings(null == caseHistoryCCIModel.getRelationChildSiblings() ? null : caseHistoryCCIModel.getRelationChildSiblings().getId());
		caseHistoryCCI.setRelationFatherChild(null == caseHistoryCCIModel.getRelationFatherChild() ? null : caseHistoryCCIModel.getRelationFatherChild().getId());
		caseHistoryCCI.setRelationFatherMother(null == caseHistoryCCIModel.getRelationFatherMother() ? null : caseHistoryCCIModel.getRelationFatherMother().getId());
		caseHistoryCCI.setRelationFatherSiblings(null == caseHistoryCCIModel.getRelationFatherSiblings() ? null : caseHistoryCCIModel.getRelationFatherSiblings().getId());
		caseHistoryCCI.setRelationMotherChild(null == caseHistoryCCIModel.getRelationMotherChild() ? null : caseHistoryCCIModel.getRelationMotherChild().getId());
		caseHistoryCCI.setRelationMotherSiblings(null == caseHistoryCCIModel.getRelationMotherSiblings() ? null : caseHistoryCCIModel.getRelationMotherSiblings().getId());
		caseHistoryCCI.setChildGoodHabits(null == caseHistoryCCIModel.getChildGoodHabits() ? null : caseHistoryCCIModel.getChildGoodHabits());
		caseHistoryCCI.setChildGoodHabitsOthers(null == caseHistoryCCIModel.getChildGoodHabitsOthers() ? null : caseHistoryCCIModel.getChildGoodHabitsOthers());
		caseHistoryCCI.setChildBadHabits(null == caseHistoryCCIModel.getChildBadHabits() ? null : caseHistoryCCIModel.getChildBadHabits());
		caseHistoryCCI.setChildBadHabitsOthers(null == caseHistoryCCIModel.getChildBadHabitsOthers() ? null : caseHistoryCCIModel.getChildBadHabitsOthers());
		caseHistoryCCI.setDetailsOfPastEducation(null == caseHistoryCCIModel.getDetailsOfPastEducationObject() ? null : caseHistoryCCIModel.getDetailsOfPastEducationObject().getId());
		caseHistoryCCI.setReasonBehindLeavingSchool(null == caseHistoryCCIModel.getReasonBehindLeavingSchool() ? null : caseHistoryCCIModel.getReasonBehindLeavingSchool());
		caseHistoryCCI.setReasonBehindLeavingSchoolOthers(null == caseHistoryCCIModel.getReasonBehindLeavingSchoolOthers() ? null : caseHistoryCCIModel.getReasonBehindLeavingSchoolOthers());
		caseHistoryCCI.setExtraCurricularActivities(null == caseHistoryCCIModel.getExtraCurricularActivities() ? null : caseHistoryCCIModel.getExtraCurricularActivities());
		caseHistoryCCI.setSuggestionByWhom(null == caseHistoryCCIModel.getSuggestionByWhom() ? null : caseHistoryCCIModel.getSuggestionByWhom().getId());
		caseHistoryCCI.setSuggestion(null == caseHistoryCCIModel.getSuggestion() ? null : caseHistoryCCIModel.getSuggestion());
		caseHistoryCCI.setFollowUpWhom(null == caseHistoryCCIModel.getFollowUpWhom() ? null : caseHistoryCCIModel.getFollowUpWhom().getId());
		caseHistoryCCI.setQuarterlyReviewOfCase(null == caseHistoryCCIModel.getQuarterlyReviewOfCase() ? null : caseHistoryCCIModel.getQuarterlyReviewOfCase());
		
		caseHistoryCCI.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		caseHistoryCCI.setCreatedDate(new java.sql.Date(new Date().getTime()));
		caseHistoryCCI.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		
		caseHistoryRepository.save(caseHistoryCCI);
		
		ChildDetails childDetails = childDetailsRepository.findChildById(caseHistoryCCIModel.getChildId());
		childDetails.setId(childDetails.getId());
		childDetails.setCaseHistoryFilled(1);
		childDetailsRepository.save(childDetails);
				
	}

	@Override
	public CaseHistoryCCIModel getCHDataByChildId(String childId, String lang) throws Exception {
		CaseHistoryCCI caseHistoryCCI = new CaseHistoryCCI();
		CaseHistoryCCIModel caseHistoryCCIModel = new CaseHistoryCCIModel();
		typeMap = getTypeMap();
		
		caseHistoryCCI = caseHistoryRepository.getByChildId(childId);
		
		if(caseHistoryCCI != null){
			caseHistoryCCIModel.setActualWeightForHeightWeightChart(caseHistoryCCI.getActualWeightForHeightWeightChart());
			caseHistoryCCIModel.setAdmissibleWeightForHeightWeightChart(caseHistoryCCI.getAdmissibleWeightForHeightWeightChart());
			caseHistoryCCIModel.setAgeAtTimeOfAdmission(caseHistoryCCI.getAgeAtTimeOfAdmission());
			caseHistoryCCIModel.setAttitudeOfGroup_LeagueObject(null == caseHistoryCCI.getAttitudeOfGroup_League() ? null : typeMap.get(caseHistoryCCI.getAttitudeOfGroup_League()));
			caseHistoryCCIModel.setBrothersMarraigeTypeObject(null == caseHistoryCCI.getBrothersMarraigeType()? null : typeMap.get(caseHistoryCCI.getBrothersMarraigeType()));
			caseHistoryCCIModel.setCardiacDiseases(null == caseHistoryCCI.getCardiacDiseases() ? null : typeMap.get(caseHistoryCCI.getCardiacDiseases()));
			caseHistoryCCIModel.setCasteObject(null == caseHistoryCCI.getCaste() ? null : typeMap.get(caseHistoryCCI.getCaste()));
			caseHistoryCCIModel.setCategory(null == caseHistoryCCI.getCategory() || caseHistoryCCI.getCategory().equals("") ? null : caseHistoryCCI.getCategory());
			caseHistoryCCIModel.setCategoryName(null == caseHistoryCCI.getCategory() || caseHistoryCCI.getCategory().equals("") ? null : getTypeName(caseHistoryCCI.getCategory(), lang));
			caseHistoryCCIModel.setCategoryDetails(caseHistoryCCI.getCategoryDetails());
			caseHistoryCCIModel.setCategoryDetailsOther(caseHistoryCCI.getCategoryDetailsOthers());
			caseHistoryCCIModel.setChildAttainPubertyAge(caseHistoryCCI.getChildAttainPubertyAge());
			caseHistoryCCIModel.setChildBadHabits(null == caseHistoryCCI.getChildBadHabits() || caseHistoryCCI.getChildBadHabits().equals("") ? null : caseHistoryCCI.getCategory());
			caseHistoryCCIModel.setChildBadHabitsName(null == caseHistoryCCI.getChildBadHabits() || caseHistoryCCI.getChildBadHabits().equals("") ? null : getTypeName(caseHistoryCCI.getChildBadHabits(), lang));
			caseHistoryCCIModel.setChildBadHabitsOthers(caseHistoryCCI.getChildBadHabitsOthers());
			caseHistoryCCIModel.setChildBroughtBeforeCWCByWhomObject(null == caseHistoryCCI.getChildBroughtBeforeCWCByWhom() ? null : typeMap.get(caseHistoryCCI.getChildBroughtBeforeCWCByWhom()));
			caseHistoryCCIModel.setChildBroughtBeforeCWCByWhomRelationship(caseHistoryCCI.getChildBroughtBeforeCWCByWhomRelationship());
	//		caseHistoryCCIModel.setChildEmploymentDetailsModels();
			caseHistoryCCIModel.setChildGoodHabits(null == caseHistoryCCI.getChildGoodHabits() || caseHistoryCCI.getChildGoodHabits().equals("") ? null : caseHistoryCCI.getChildGoodHabits());
			caseHistoryCCIModel.setChildGoodHabitsName(null == caseHistoryCCI.getChildGoodHabits() || caseHistoryCCI.getChildGoodHabits().equals("") ? null : getTypeName(caseHistoryCCI.getChildGoodHabits(), lang));
			caseHistoryCCIModel.setChildGoodHabitsOthers(caseHistoryCCI.getChildGoodHabitsOthers());
			caseHistoryCCIModel.setChildId(childId);
//			caseHistoryCCIModel.setChildImagePath(caseHistoryCCI.getChildImagePath());
			caseHistoryCCIModel.setChildImgpath(exportPDFServiceImpl.getChildPhoto(caseHistoryCCI.getChildImagePath()));
			caseHistoryCCIModel.setChildWasStayingWithWhomPriorToAdmission(null == caseHistoryCCI.getChildWasStayingWithWhomPriorToAdmission() ? null : typeMap.get(caseHistoryCCI.getChildWasStayingWithWhomPriorToAdmission()));
			caseHistoryCCIModel.setChildWasStayingWithWhomPriorToAdmissionOther(caseHistoryCCI.getChildWasStayingWithWhomPriorToAdmissionOther());
			caseHistoryCCIModel.setCorrespondenceWithParentAfterInstitutionalizationObject(null == caseHistoryCCI.getCorrespondenceWithParentAfterInstitutionalization() ? null : typeMap.get(caseHistoryCCI.getCorrespondenceWithParentAfterInstitutionalization()));
			caseHistoryCCIModel.setCorrespondenceWithParentPriorToInstitutionalizationObject(null == caseHistoryCCI.getCorrespondenceWithParentPriorToInstitutionalization() ? null : typeMap.get(caseHistoryCCI.getCorrespondenceWithParentPriorToInstitutionalization()));
			caseHistoryCCIModel.setDate(caseHistoryCCI.getDate());
			caseHistoryCCIModel.setDateMonthYearForHeightWeightChart(caseHistoryCCI.getDateMonthYearForHeightWeightChart());
			caseHistoryCCIModel.setDentalDisease(null == caseHistoryCCI.getDentalDisease() ? null : typeMap.get(caseHistoryCCI.getDentalDisease()));
			caseHistoryCCIModel.setDescriptionOfHousing1Object(null == caseHistoryCCI.getDescriptionOfHousing1() ? null : typeMap.get(caseHistoryCCI.getDescriptionOfHousing1()));
			caseHistoryCCIModel.setDescriptionOfHousing2Object(null == caseHistoryCCI.getDescriptionOfHousing2() ? null : typeMap.get(caseHistoryCCI.getDescriptionOfHousing2()));
			caseHistoryCCIModel.setDescriptionOfHousing3Object(null == caseHistoryCCI.getDescriptionOfHousing3() ? null : typeMap.get(caseHistoryCCI.getDescriptionOfHousing3()));
			caseHistoryCCIModel.setDetailOfMembershipInGroup(null == caseHistoryCCI.getDetailOfMembershipInGroup() || caseHistoryCCI.getDetailOfMembershipInGroup().equals("") ? null : caseHistoryCCI.getDetailOfMembershipInGroup());
			caseHistoryCCIModel.setDetailOfMembershipInGroupName(null == caseHistoryCCI.getDetailOfMembershipInGroup() || caseHistoryCCI.getDetailOfMembershipInGroup().equals("") ? null : getTypeName(caseHistoryCCI.getDetailOfMembershipInGroup(), lang));
			caseHistoryCCIModel.setDetailOfMembershipInGroupOthers(caseHistoryCCI.getDetailOfMembershipInGroupOthers());
			caseHistoryCCIModel.setDetailsOfCertificationPath(caseHistoryCCI.getDetailsOfCertificationPath());
			caseHistoryCCIModel.setDetailsOfDelinquentBehaviour(null == caseHistoryCCI.getDetailsOfDelinquentBehaviour() || caseHistoryCCI.getDetailsOfDelinquentBehaviour().equals("") ? null : caseHistoryCCI.getDetailsOfDelinquentBehaviour());
			caseHistoryCCIModel.setDetailsOfDelinquentBehaviourName(null == caseHistoryCCI.getDetailsOfDelinquentBehaviour() || caseHistoryCCI.getDetailsOfDelinquentBehaviour().equals("") ? null : getTypeName(caseHistoryCCI.getDetailsOfDelinquentBehaviour(), lang));
			caseHistoryCCIModel.setDetailsOfDelinquentBehaviourOthers(caseHistoryCCI.getDetailsOfDelinquentBehaviourOthers());
			caseHistoryCCIModel.setDetailsOfDisability(caseHistoryCCI.getDetailsOfDisability());
			caseHistoryCCIModel.setDetailsOfIncomeUtilization(null == caseHistoryCCI.getDetailsOfIncomeUtilization() || caseHistoryCCI.getDetailsOfIncomeUtilization().equals("") ? null : caseHistoryCCI.getDetailsOfIncomeUtilization());
			caseHistoryCCIModel.setDetailsOfIncomeUtilizationName(null == caseHistoryCCI.getDetailsOfIncomeUtilization() || caseHistoryCCI.getDetailsOfIncomeUtilization().equals("") ? null : getTypeName(caseHistoryCCI.getDetailsOfIncomeUtilization(), lang));
			caseHistoryCCIModel.setDetailsOfPastEducationObject(null == caseHistoryCCI.getDetailsOfPastEducation() ? null : typeMap.get(caseHistoryCCI.getDetailsOfPastEducation()));
			caseHistoryCCIModel.setDetailsOfSaving(null == caseHistoryCCI.getDetailsOfSaving() || caseHistoryCCI.getDetailsOfSaving().equals("") ? null : caseHistoryCCI.getDetailsOfSaving());
			caseHistoryCCIModel.setDetailsOfSavingName(null == caseHistoryCCI.getDetailsOfSaving() || caseHistoryCCI.getDetailsOfSaving().equals("") ? null : getTypeName(caseHistoryCCI.getDetailsOfSaving(), lang));
			caseHistoryCCIModel.setDetailsOfSavingOthers(caseHistoryCCI.getDetailsOfSavingOthers());
			caseHistoryCCIModel.setDetailsOfSchoolStudiedLastObject(null == caseHistoryCCI.getDetailsOfSchoolStudiedLast() ? null : typeMap.get(caseHistoryCCI.getDetailsOfSchoolStudiedLast()));
			caseHistoryCCIModel.setDurationOfWorkingHoursObject(null == caseHistoryCCI.getDurationOfWorkingHours() ? null : typeMap.get(caseHistoryCCI.getDurationOfWorkingHours()));
			caseHistoryCCIModel.setEducationalAttainmentClassStudied(caseHistoryCCI.getEducationalAttainmentClassStudied());
			caseHistoryCCIModel.setEducationalAttainmentNoOfYears(caseHistoryCCI.getEducationalAttainmentNoOfYears());
			caseHistoryCCIModel.setEducationalAttainmentPromote_Detained(null == caseHistoryCCI.getEducationalAttainmentPromote_Detained() ? null : typeMap.get(caseHistoryCCI.getEducationalAttainmentPromote_Detained()));
			caseHistoryCCIModel.setExploitaionFacedByTheChild(null == caseHistoryCCI.getExploitaionFacedByTheChild() || caseHistoryCCI.getExploitaionFacedByTheChild().equals("") ? null : caseHistoryCCI.getExploitaionFacedByTheChild());
			caseHistoryCCIModel.setExploitaionFacedByTheChildName(null == caseHistoryCCI.getExploitaionFacedByTheChild() || caseHistoryCCI.getExploitaionFacedByTheChild().equals("") ? null : getTypeName(caseHistoryCCI.getExploitaionFacedByTheChild(), lang));
			caseHistoryCCIModel.setExploitaionFacedByTheChildOthers(caseHistoryCCI.getExploitaionFacedByTheChildOthers());
			caseHistoryCCIModel.setExtraCurricularActivities(null == caseHistoryCCI.getExtraCurricularActivities() || caseHistoryCCI.getExtraCurricularActivities().equals("") ? null : caseHistoryCCI.getExtraCurricularActivities());
			caseHistoryCCIModel.setExtraCurricularActivitiesName(null == caseHistoryCCI.getExtraCurricularActivities() || caseHistoryCCI.getExtraCurricularActivities().equals("") ? null : getTypeName(caseHistoryCCI.getExtraCurricularActivities(), lang));
			caseHistoryCCIModel.setExtraCurricularActivitiesOthers(caseHistoryCCI.getExtraCurricularActivitiesOthers());
			caseHistoryCCIModel.setEyeDiseases(null == caseHistoryCCI.getEyeDiseases() ? null : typeMap.get(caseHistoryCCI.getEyeDiseases()));
	//		caseHistoryCCIModel.setFamilyHistoryOfCrimeModels(caseHistoryCCI.getFamil);
			caseHistoryCCIModel.setFamilyTypeObject(null == caseHistoryCCI.getFamilyType() ? null : typeMap.get(caseHistoryCCI.getFamilyType()));
			caseHistoryCCIModel.setFollowUpWhom(null == caseHistoryCCI.getFollowUpWhom() ? null : typeMap.get(caseHistoryCCI.getFollowUpWhom()));
			caseHistoryCCIModel.setFriendshipPriorToAdmissionIntoChildrensHome(null == caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHome() || caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHome().equals("") ? null : caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHome());
			caseHistoryCCIModel.setFriendshipPriorToAdmissionIntoChildrensHomeName(null == caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHome() || caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHome().equals("") ? null : getTypeName(caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHome(), lang));
			caseHistoryCCIModel.setFriendshipPriorToAdmissionIntoChildrensHomeOthers(caseHistoryCCI.getFriendshipPriorToAdmissionIntoChildrensHomeOthers());
	//		caseHistoryCCIModel.setHealthStatusOfChildModels(caseH);
			caseHistoryCCIModel.setHearingImpairment(null == caseHistoryCCI.getHearingImpairment() ? null : typeMap.get(caseHistoryCCI.getHearingImpairment()));
			caseHistoryCCIModel.setHeightAtTimeOfAdmission(caseHistoryCCI.getHeightAtTimeOfAdmission());
			caseHistoryCCIModel.setHeightForHeightWeightChart(caseHistoryCCI.getHeightForHeightWeightChart());
			caseHistoryCCIModel.setHistoryOfChildEducation(caseHistoryCCI.getHistoryOfChildEducation());
			caseHistoryCCIModel.setHistoryOfChildExtraCuricularActivites(caseHistoryCCI.getHistoryOfChildExtraCuricularActivites());
			caseHistoryCCIModel.setHistoryOfChildHealth(caseHistoryCCI.getHistoryOfChildHealth());
			caseHistoryCCIModel.setHistoryOfChildOthers(caseHistoryCCI.getHistoryOfChildOthers());
			caseHistoryCCIModel.setHistoryOfChildVocationalTraining(caseHistoryCCI.getHistoryOfChildVocationalTraining());
			caseHistoryCCIModel.setHouseholdArticlesOBF(null == caseHistoryCCI.getHouseholdArticlesOBF() || caseHistoryCCI.getHouseholdArticlesOBF().equals("") ? null : caseHistoryCCI.getHouseholdArticlesOBF());
			caseHistoryCCIModel.setHouseholdArticlesOBFName(null == caseHistoryCCI.getHouseholdArticlesOBF() || caseHistoryCCI.getHouseholdArticlesOBF().equals("") ? null : getTypeName(caseHistoryCCI.getHouseholdArticlesOBF(), lang));
			caseHistoryCCIModel.setId(caseHistoryCCI.getId());
			caseHistoryCCIModel.setIllTreatmentBM(null == caseHistoryCCI.getIllTreatmentBM() || caseHistoryCCI.getIllTreatmentBM().equals("") ? null : caseHistoryCCI.getIllTreatmentBM());
			caseHistoryCCIModel.setIllTreatmentBMName(null == caseHistoryCCI.getIllTreatmentBM() || caseHistoryCCI.getIllTreatmentBM().equals("") ? null : getTypeName(caseHistoryCCI.getIllTreatmentBM(), lang));
			caseHistoryCCIModel.setIllTreatmentBMOthers(caseHistoryCCI.getIllTreatmentBMOthers());
			caseHistoryCCIModel.setIllTreatmentCI(null == caseHistoryCCI.getIllTreatmentCI() || caseHistoryCCI.getIllTreatmentCI().equals("") ? null : caseHistoryCCI.getIllTreatmentCI());
			caseHistoryCCIModel.setIllTreatmentCIName(null == caseHistoryCCI.getIllTreatmentCI() || caseHistoryCCI.getIllTreatmentCI().equals("") ? null : getTypeName(caseHistoryCCI.getIllTreatmentCI(), lang));
			caseHistoryCCIModel.setIllTreatmentCIOthers(caseHistoryCCI.getIllTreatmentCIOthers());
			caseHistoryCCIModel.setIllTreatmentDOF(null == caseHistoryCCI.getIllTreatmentdOF() || caseHistoryCCI.getIllTreatmentdOF().equals("") ? null : caseHistoryCCI.getIllTreatmentdOF());
			caseHistoryCCIModel.setIllTreatmentDOFName(null == caseHistoryCCI.getIllTreatmentdOF() || caseHistoryCCI.getIllTreatmentdOF().equals("") ? null : getTypeName(caseHistoryCCI.getIllTreatmentdOF(), lang));
			caseHistoryCCIModel.setIllTreatmentDOFOthers(caseHistoryCCI.getIllTreatmentDOFOthers());
			caseHistoryCCIModel.setIllTreatmentDP(null == caseHistoryCCI.getIllTreatmentDP() || caseHistoryCCI.getIllTreatmentDP().equals("") ? null : caseHistoryCCI.getIllTreatmentDP());
			caseHistoryCCIModel.setIllTreatmentDPName(null == caseHistoryCCI.getIllTreatmentDP() || caseHistoryCCI.getIllTreatmentDP().equals("") ? null : getTypeName(caseHistoryCCI.getIllTreatmentDP(), lang));
			caseHistoryCCIModel.setIllTreatmentDPOthers(caseHistoryCCI.getIllTreatmentDPOthers());
			caseHistoryCCIModel.setIllTreatmentOther(null == caseHistoryCCI.getIllTreatmentOther() || caseHistoryCCI.getIllTreatmentOther().equals("") ? null : caseHistoryCCI.getIllTreatmentOther());
			caseHistoryCCIModel.setIllTreatmentOtherNames(null == caseHistoryCCI.getIllTreatmentOther() || caseHistoryCCI.getIllTreatmentOther().equals("") ? null : getTypeName(caseHistoryCCI.getIllTreatmentOther(), lang));
			caseHistoryCCIModel.setIllTreatmentOtherName(caseHistoryCCI.getIllTreatmentOtherName());
			caseHistoryCCIModel.setIllTreatmentOtherOther(caseHistoryCCI.getIllTreatmentOtherOther());
			caseHistoryCCIModel.setLandedPropertiesOBF(caseHistoryCCI.getLandedPropertiesOBF());
			caseHistoryCCIModel.setLocationMeetingPointOfGroupsObject(null == caseHistoryCCI.getLocationMeetingPointOfGroups() ? null : typeMap.get(caseHistoryCCI.getLocationMeetingPointOfGroups()));
			caseHistoryCCIModel.setMajorityFriendsAre(null == caseHistoryCCI.getMajorityFriendsAre() || caseHistoryCCI.getMajorityFriendsAre().equals("") ? null : caseHistoryCCI.getMajorityFriendsAre());
			caseHistoryCCIModel.setMajorityFriendsAreName(null == caseHistoryCCI.getMajorityFriendsAre() || caseHistoryCCI.getMajorityFriendsAre().equals("") ? null : getTypeName(caseHistoryCCI.getMajorityFriendsAre(), lang));
			caseHistoryCCIModel.setMedicalHistoryOfChild(caseHistoryCCI.getMedicalHistoryOfChild());
			caseHistoryCCIModel.setMedicalHistoryOfParents(caseHistoryCCI.getMedicalHistoryOfParents());
			caseHistoryCCIModel.setMentalHandicap(null == caseHistoryCCI.getMentalHandicap() ? null : typeMap.get(caseHistoryCCI.getMentalHandicap()));
			caseHistoryCCIModel.setNativeDistrict(caseHistoryCCI.getNativeDistrict());
			caseHistoryCCIModel.setNativeState(caseHistoryCCI.getNativeState());
			caseHistoryCCIModel.setNeurologicalDisorders(null == caseHistoryCCI.getNeurologicalDisorders() ? null : typeMap.get(caseHistoryCCI.getNeurologicalDisorders()));
			caseHistoryCCIModel.setOtherAbuseMetByChild(null == caseHistoryCCI.getOtherAbuseMetByChild() || caseHistoryCCI.getOtherAbuseMetByChild().equals("") ? null : caseHistoryCCI.getOtherAbuseMetByChild());
			caseHistoryCCIModel.setOtherAbuseMetByChildNames(null == caseHistoryCCI.getOtherAbuseMetByChild() || caseHistoryCCI.getOtherAbuseMetByChild().equals("") ? null : getTypeName(caseHistoryCCI.getOtherAbuseMetByChild(), lang));
			caseHistoryCCIModel.setOtherAbuseMetByChildName(caseHistoryCCI.getOtherAbuseMetByChildName());
			caseHistoryCCIModel.setOtherAbuseMetByChildOthers(caseHistoryCCI.getOtherAbuseMetByChildOthers());
			caseHistoryCCIModel.setOtherHealthIssues(null == caseHistoryCCI.getOtherHealthIssues() ? null : typeMap.get(caseHistoryCCI.getOtherHealthIssues()));
			caseHistoryCCIModel.setOtherHealthIssuesName(caseHistoryCCI.getOtherHealthIssuesName());
			caseHistoryCCIModel.setOthersOBF(caseHistoryCCI.getOthersOBF());
			caseHistoryCCIModel.setParentalCareTowardsChildBeforeAdmission(null == caseHistoryCCI.getParentalCareTowardsChildBeforeAdmission() || caseHistoryCCI.getParentalCareTowardsChildBeforeAdmission().equals("") ? null : caseHistoryCCI.getParentalCareTowardsChildBeforeAdmission());
			caseHistoryCCIModel.setParentalCareTowardsChildBeforeAdmissionName(null == caseHistoryCCI.getParentalCareTowardsChildBeforeAdmission() || caseHistoryCCI.getParentalCareTowardsChildBeforeAdmission().equals("") ? null : getTypeName(caseHistoryCCI.getParentalCareTowardsChildBeforeAdmission(), lang));
			caseHistoryCCIModel.setParentsMarraigeTypeObject(null == caseHistoryCCI.getParentsMarraigeType() ? null : typeMap.get(caseHistoryCCI.getParentsMarraigeType()));
			caseHistoryCCIModel.setPhysicalAbuseMetByChild(null == caseHistoryCCI.getPhysicalAbuseMetByChild() || caseHistoryCCI.getPhysicalAbuseMetByChild().equals("") ? null : caseHistoryCCI.getPhysicalAbuseMetByChild());
			caseHistoryCCIModel.setPhysicalAbuseMetByChildName(null == caseHistoryCCI.getPhysicalAbuseMetByChild() || caseHistoryCCI.getPhysicalAbuseMetByChild().equals("") ? null : getTypeName(caseHistoryCCI.getPhysicalAbuseMetByChild(), lang));
			caseHistoryCCIModel.setPhysicalAbuseMetByChildOthers(caseHistoryCCI.getPhysicalAbuseMetByChildOthers());
			caseHistoryCCIModel.setPhysicalCondition(caseHistoryCCI.getPhysicalCondition());
			caseHistoryCCIModel.setPhysicalHandicap(null == caseHistoryCCI.getPhysicalHandicap() ? null : typeMap.get(caseHistoryCCI.getPhysicalHandicap()));
			caseHistoryCCIModel.setPositionOfChildInGroup_LeagueObject(null == caseHistoryCCI.getPositionOfChildInGroup_League() ? null : typeMap.get(caseHistoryCCI.getPositionOfChildInGroup_League()));
			caseHistoryCCIModel.setPresentAge(caseHistoryCCI.getPresentAge());
			caseHistoryCCIModel.setPurposeOfTakingMembershipInGroup(null == caseHistoryCCI.getPurposeOfTakingMembershipInGroup() || caseHistoryCCI.getPurposeOfTakingMembershipInGroup().equals("") ? null : caseHistoryCCI.getPurposeOfTakingMembershipInGroup());
			caseHistoryCCIModel.setPurposeOfTakingMembershipInGroupName(null == caseHistoryCCI.getPurposeOfTakingMembershipInGroup() || caseHistoryCCI.getPurposeOfTakingMembershipInGroup().equals("") ? null : getTypeName(caseHistoryCCI.getPurposeOfTakingMembershipInGroup(), lang));
			caseHistoryCCIModel.setPurposeOfTakingMembershipInGroupOthers(caseHistoryCCI.getPurposeOfTakingMembershipInGroupOthers());
			caseHistoryCCIModel.setQuarterlyReviewOfCase(caseHistoryCCI.getQuarterlyReviewOfCase());
			caseHistoryCCIModel.setReactionOfPoliceTowardsChildrenObject(null == caseHistoryCCI.getReactionOfPoliceTowardsChildren() ? null : typeMap.get(caseHistoryCCI.getReactionOfPoliceTowardsChildren()));
			caseHistoryCCIModel.setReactionOfSocietyTowardsChildObject(null == caseHistoryCCI.getReactionOfSocietyTowardsChild() ? null : typeMap.get(caseHistoryCCI.getReactionOfSocietyTowardsChild()));
			caseHistoryCCIModel.setReasonBehindLeavingSchool(null == caseHistoryCCI.getReasonBehindLeavingSchool() || caseHistoryCCI.getReasonBehindLeavingSchool().equals("") ? null : caseHistoryCCI.getReasonBehindLeavingSchool());
			caseHistoryCCIModel.setReasonBehindLeavingSchoolName(null == caseHistoryCCI.getReasonBehindLeavingSchool() || caseHistoryCCI.getReasonBehindLeavingSchool().equals("") ? null : getTypeName(caseHistoryCCI.getReasonBehindLeavingSchool(), lang));
			caseHistoryCCIModel.setReasonBehindLeavingSchoolOthers(caseHistoryCCI.getReasonBehindLeavingSchoolOthers());
			caseHistoryCCIModel.setReasonForDelinquentBehaviour(null == caseHistoryCCI.getReasonForDelinquentBehaviour() || caseHistoryCCI.getReasonForDelinquentBehaviour().equals("") ? null : caseHistoryCCI.getReasonForDelinquentBehaviour());
			caseHistoryCCIModel.setReasonForDelinquentBehaviourName(null == caseHistoryCCI.getReasonForDelinquentBehaviour() || caseHistoryCCI.getReasonForDelinquentBehaviour().equals("") ? null : getTypeName(caseHistoryCCI.getReasonForDelinquentBehaviour(), lang));
			caseHistoryCCIModel.setReasonForDelinquentBehaviourOthers(caseHistoryCCI.getReasonForDelinquentBehaviourOthers());
			caseHistoryCCIModel.setReasonsForLeavingFamily(null == caseHistoryCCI.getReasonsForLeavingFamily() || caseHistoryCCI.getReasonsForLeavingFamily().equals("") ? null : caseHistoryCCI.getReasonsForLeavingFamily());
			caseHistoryCCIModel.setReasonsForLeavingFamilyName(null == caseHistoryCCI.getReasonsForLeavingFamily() || caseHistoryCCI.getReasonsForLeavingFamily().equals("") ? null : getTypeName(caseHistoryCCI.getReasonsForLeavingFamily(), lang));
			caseHistoryCCIModel.setReasonsForLeavingFamilyOthers(caseHistoryCCI.getReasonsForLeavingFamilyOthers());
			caseHistoryCCIModel.setRelationChildRelative(null == caseHistoryCCI.getRelationChildRelative() ? null : typeMap.get(caseHistoryCCI.getRelationChildRelative()));
			caseHistoryCCIModel.setRelationChildSiblings(null == caseHistoryCCI.getRelationChildSiblings() ? null : typeMap.get(caseHistoryCCI.getRelationChildSiblings()));
			caseHistoryCCIModel.setRelationFatherChild(null == caseHistoryCCI.getRelationFatherChild() ? null : typeMap.get(caseHistoryCCI.getRelationFatherChild()));
			caseHistoryCCIModel.setRelationFatherMother(null == caseHistoryCCI.getRelationFatherMother() ? null : typeMap.get(caseHistoryCCI.getRelationFatherMother()));
			caseHistoryCCIModel.setRelationFatherSiblings(null == caseHistoryCCI.getRelationFatherSiblings() ? null : typeMap.get(caseHistoryCCI.getRelationFatherSiblings()));
			caseHistoryCCIModel.setRelationMotherChild(null == caseHistoryCCI.getRelationMotherChild() ? null : typeMap.get(caseHistoryCCI.getRelationMotherChild()));
			caseHistoryCCIModel.setRelationMotherSiblings(null == caseHistoryCCI.getRelationMotherSiblings() ? null : typeMap.get(caseHistoryCCI.getRelationMotherSiblings()));
			caseHistoryCCIModel.setReligionObject(null == caseHistoryCCI.getReligion() ? null : typeMap.get(caseHistoryCCI.getReligion()));
			caseHistoryCCIModel.setReligionOthers(caseHistoryCCI.getReligionOthers());
			caseHistoryCCIModel.setRespiratoryDisorders(null == caseHistoryCCI.getRespiratoryDisorders() ? null : typeMap.get(caseHistoryCCI.getRespiratoryDisorders()));
			caseHistoryCCIModel.setResponseOfGeneralPublicTowardsChild(caseHistoryCCI.getResponseOfGeneralPublicTowardsChild());
			caseHistoryCCIModel.setSchoolMediumInstructionObject(null == caseHistoryCCI.getSchoolMediumInstruction() ? null : typeMap.get(caseHistoryCCI.getSchoolMediumInstruction()));
			caseHistoryCCIModel.setSchoolMediumInstructionOthers(caseHistoryCCI.getSchoolMediumInstructionOthers());
			caseHistoryCCIModel.setSexualAbuseMetByChild(null == caseHistoryCCI.getSexualAbuseMetByChild() || caseHistoryCCI.getSexualAbuseMetByChild().equals("") ? null : caseHistoryCCI.getSexualAbuseMetByChild());
			caseHistoryCCIModel.setSexualAbuseMetByChildName(null == caseHistoryCCI.getSexualAbuseMetByChild() || caseHistoryCCI.getSexualAbuseMetByChild().equals("") ? null : getTypeName(caseHistoryCCI.getSexualAbuseMetByChild(), lang));
			caseHistoryCCIModel.setSexualAbuseMetByChildOthers(caseHistoryCCI.getSexualAbuseMetByChildOthers());
			caseHistoryCCIModel.setSexuallyTransmittedDiseases(null == caseHistoryCCI.getSexuallyTransmittedDiseases() ? null : typeMap.get(caseHistoryCCI.getSexuallyTransmittedDiseases()));
			caseHistoryCCIModel.setSistersMarraigeTypeObject(null == caseHistoryCCI.getSistersMarraigeType() ? null : typeMap.get(caseHistoryCCI.getSistersMarraigeType()));
			caseHistoryCCIModel.setSkinDisease(null == caseHistoryCCI.getSkinDisease() ? null : typeMap.get(caseHistoryCCI.getSkinDisease()));
			caseHistoryCCIModel.setSocialActivitesOfFamilyMembers(null == caseHistoryCCI.getSocialActivitesOfFamilyMembers() || caseHistoryCCI.getSocialActivitesOfFamilyMembers().equals("") ? null : caseHistoryCCI.getSocialActivitesOfFamilyMembers());
			caseHistoryCCIModel.setSocialActivitesOfFamilyMembersName(null == caseHistoryCCI.getSocialActivitesOfFamilyMembers() || caseHistoryCCI.getSocialActivitesOfFamilyMembers().equals("") ? null : getTypeName(caseHistoryCCI.getSocialActivitesOfFamilyMembers(), lang));
			caseHistoryCCIModel.setSuggestion(caseHistoryCCI.getSuggestion());
			caseHistoryCCIModel.setSuggestionByWhom(null == caseHistoryCCI.getSuggestionByWhom() ? null : typeMap.get(caseHistoryCCI.getSuggestionByWhom()));
			caseHistoryCCIModel.setTime(caseHistoryCCI.getTime());
			caseHistoryCCIModel.setUrinaryTractInfections(null == caseHistoryCCI.getUrinaryTractInfections() ? null : typeMap.get(caseHistoryCCI.getUrinaryTractInfections()));
			caseHistoryCCIModel.setVehiclesOBF(null == caseHistoryCCI.getVehiclesOBF() ? null : caseHistoryCCI.getVehiclesOBF().equals("") ? null : caseHistoryCCI.getVehiclesOBF());
			caseHistoryCCIModel.setVehiclesOBFName(null == caseHistoryCCI.getVehiclesOBF() ? null : caseHistoryCCI.getVehiclesOBF().equals("") ? null : getTypeName(caseHistoryCCI.getVehiclesOBF(), lang));
			caseHistoryCCIModel.setVerbalAbuseMetByChild(null == caseHistoryCCI.getVerbalAbuseMetByChild() ? null : caseHistoryCCI.getVerbalAbuseMetByChild().equals("") ? null : caseHistoryCCI.getVerbalAbuseMetByChild());
			caseHistoryCCIModel.setVerbalAbuseMetByChildName(null == caseHistoryCCI.getVerbalAbuseMetByChild() ? null : caseHistoryCCI.getVerbalAbuseMetByChild().equals("") ? null : getTypeName(caseHistoryCCI.getVerbalAbuseMetByChild(), lang));
			caseHistoryCCIModel.setVerbalAbuseMetByChildOthers(caseHistoryCCI.getVerbalAbuseMetByChildOthers());
			caseHistoryCCIModel.setVisitOfChildAfterInstitutionalizationObject(null == caseHistoryCCI.getVisitOfChildAfterInstitutionalization() ? null : typeMap.get(caseHistoryCCI.getVisitOfChildAfterInstitutionalization()));
			caseHistoryCCIModel.setVisitOfChildPriorToInstitutionalizationObject(null == caseHistoryCCI.getVisitOfChildPriorToInstitutionalization() ? null : typeMap.get(caseHistoryCCI.getVisitOfChildPriorToInstitutionalization()));
			caseHistoryCCIModel.setVisitOfParentsAfterInstitutionalizationObject(null == caseHistoryCCI.getVisitOfParentsAfterInstitutionalization() ? null : typeMap.get(caseHistoryCCI.getVisitOfParentsAfterInstitutionalization()));
			caseHistoryCCIModel.setVisitOfParentsPriorToInstitutionalizationObject(null == caseHistoryCCI.getVisitOfParentsPriorToInstitutionalization() ? null : typeMap.get(caseHistoryCCI.getVisitOfParentsPriorToInstitutionalization()));
			caseHistoryCCIModel.setVoactionalTrainingNoOfYears(caseHistoryCCI.getVoactionalTrainingNoOfYears());
			caseHistoryCCIModel.setVocationalTrainingNameOfTrade(caseHistoryCCI.getVocationalTrainingNameOfTrade());
			caseHistoryCCIModel.setVocationalTrainingProficiencyObtained(caseHistoryCCI.getVocationalTrainingProficiencyObtained());
			caseHistoryCCIModel.setWeightAtTimeOfAdmission(caseHistoryCCI.getWeightAtTimeOfAdmission());
			
			
			List<ChildEmploymentDetails> childEmploymentDetails = new ArrayList<ChildEmploymentDetails>();
			List<ChildEmploymentDetailsModel> childEmploymentDetailsModelList = new ArrayList<ChildEmploymentDetailsModel>();
			
			childEmploymentDetails = childEmploymentDetailsRepository.getEmpDetailsByChildId(childId);
			
			if(childEmploymentDetails != null && childEmploymentDetails.size() > 0){
				for(ChildEmploymentDetails childDetails : childEmploymentDetails){
					ChildEmploymentDetailsModel childEmploymentDetailsModel = new ChildEmploymentDetailsModel();
					
					childEmploymentDetailsModel.setDuration(childDetails.getDuration());
					childEmploymentDetailsModel.setId(childDetails.getId());
					childEmploymentDetailsModel.setTiming(childDetails.getTiming());
					childEmploymentDetailsModel.setTypeOfEmployment( null == childDetails.getTypeOfEmployment() ? null : typeMap.get(childDetails.getTypeOfEmployment()));
					childEmploymentDetailsModel.setTypesOfEmploymentOther(childDetails.getTypeOfEmploymentOther());
					childEmploymentDetailsModel.setWagesEarned(childDetails.getWagesEarned());
					
					childEmploymentDetailsModelList.add(childEmploymentDetailsModel);
				}
			}
			caseHistoryCCIModel.setChildEmploymentDetailsModels(childEmploymentDetailsModelList);
			
			List<FamilyHistoryOfCrime> familyHistoryOfCrimesList = new ArrayList<FamilyHistoryOfCrime>();
			List<FamilyHistoryOfCrimeModel> familyHistoryOfCrimeModelsList = new ArrayList<FamilyHistoryOfCrimeModel>();
			
			familyHistoryOfCrimesList = familyHistoryOfCrimeRepository.findHistoryById(childId);
			
			if(familyHistoryOfCrimesList != null && familyHistoryOfCrimesList.size() > 0){
				for(FamilyHistoryOfCrime familyHistoryOfCrime : familyHistoryOfCrimesList){
					FamilyHistoryOfCrimeModel familyHistoryOfCrimeModel = new FamilyHistoryOfCrimeModel();
				
					familyHistoryOfCrimeModel.setArrestIfAny(familyHistoryOfCrime.isArrestIfAny());
					familyHistoryOfCrimeModel.setId(familyHistoryOfCrime.getId());
					familyHistoryOfCrimeModel.setLegalStatusOfTheCase(familyHistoryOfCrime.getLegalStatusOfTheCase());
					familyHistoryOfCrimeModel.setNatureOfCrime(familyHistoryOfCrime.getNatureOfCrime());
					familyHistoryOfCrimeModel.setPeriodOfConfinement(familyHistoryOfCrime.getPeriodOfConfinement());
					familyHistoryOfCrimeModel.setPunishmentAwarded(familyHistoryOfCrime.getPunishmentAwarded());
					familyHistoryOfCrimeModel.setRelationshipWithChild(null == familyHistoryOfCrime.getRelationshipWithChild() ? null : typeMap.get(familyHistoryOfCrime.getRelationshipWithChild()));
					familyHistoryOfCrimeModel.setRelationshipWithChildOthers(familyHistoryOfCrime.getRelationshipWithChildOthers());
					
					familyHistoryOfCrimeModelsList.add(familyHistoryOfCrimeModel);
				}
			}
			
			caseHistoryCCIModel.setFamilyHistoryOfCrimeModels(familyHistoryOfCrimeModelsList);
			
			List<HealthStatusOfChild> healthStatusOfChildList = new ArrayList<HealthStatusOfChild>();
			List<HealthStatusOfChildModel> healthStatusOfChildModelList = new ArrayList<HealthStatusOfChildModel>();
			
			healthStatusOfChildList = healthStatusOfChildRepository.findHealthStatusById(childId);
			
			if(healthStatusOfChildList != null && healthStatusOfChildList.size() > 0){
				for(HealthStatusOfChild healthStatusOfChild : healthStatusOfChildList){
					HealthStatusOfChildModel healthStatusOfChildModel = new HealthStatusOfChildModel();
					
					healthStatusOfChildModel.setDateOfReview(healthStatusOfChild.getDateOfReview());
					healthStatusOfChildModel.setDental(healthStatusOfChild.getDental());
					healthStatusOfChildModel.setEnt(healthStatusOfChild.getEnt());
					healthStatusOfChildModel.setEye(healthStatusOfChild.getEye());
					healthStatusOfChildModel.setHeight(healthStatusOfChild.getHeight());
					healthStatusOfChildModel.setId(healthStatusOfChild.getId());
					healthStatusOfChildModel.setNutritiousDietGiven(healthStatusOfChild.getNutritiousDietGiven());
					healthStatusOfChildModel.setQuarterNo(healthStatusOfChild.getQuarterNo());
					healthStatusOfChildModel.setStress(healthStatusOfChild.getStress());
					healthStatusOfChildModel.setWeight(healthStatusOfChild.getWeight());
					
					healthStatusOfChildModelList.add(healthStatusOfChildModel);
				}
			}
			
			caseHistoryCCIModel.setHealthStatusOfChildModels(healthStatusOfChildModelList);
			
			
			return caseHistoryCCIModel;
		}
		else{
			return null;
		}
		
	}

}
