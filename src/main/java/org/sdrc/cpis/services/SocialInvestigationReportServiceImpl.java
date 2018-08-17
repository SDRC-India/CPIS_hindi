package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.SocialInvestigationReportFamilyDetails;
import org.sdrc.cpis.domains.SocialinvestigationReport;
import org.sdrc.cpis.models.SocialInvestigationReportFamilyDetailsModel;
import org.sdrc.cpis.models.SocialinvestigationReportModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.SocialInvestigationReportFamilyDetailsRepository;
import org.sdrc.cpis.repository.SocialInvestigationReportRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialInvestigationReportServiceImpl implements SocialInvestigationReportService {

	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private SocialInvestigationReportRepository socialInvestigationReportRepository;
	
	@Autowired
	private SocialInvestigationReportFamilyDetailsRepository socialInvestigationReportFamilyDetailsRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private StateManager stateManager;
	
	public Map<Integer, ValueObject> getTypeMap(){
		List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(cctsTypeDetails.getTypeDetailsNameHindi() != null ? cctsTypeDetails.getTypeDetailsNameHindi() : null);
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
			return map;
		}
	
	@Override
	public void saveSocialInvestigationReport(
			SocialinvestigationReportModel socialinvestigationReportModel, List<SocialInvestigationReportFamilyDetailsModel> socialInvestigationReportFamilyDetailsModels) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		SocialinvestigationReport socialinvestigationReport = new SocialinvestigationReport();
		
//		socialinvestigationReport.setCaseNo(null);
		socialinvestigationReport.setReportPreparedBy(null == socialinvestigationReportModel.getReportPreparedBy()?null:socialinvestigationReportModel.getReportPreparedBy().getId());
		socialinvestigationReport.setId(socialinvestigationReportModel.getId());
		socialinvestigationReport.setChildId(childDetailsRepository.findChildById(socialinvestigationReportModel.getChildId()));
		socialinvestigationReport.setSlNo(null == socialinvestigationReportModel.getSlNo()?null:socialinvestigationReportModel.getSlNo());
		
		socialinvestigationReport.setChildName(null==socialinvestigationReportModel.getChildName()?null:socialinvestigationReportModel.getChildName());
		socialinvestigationReport.setChildSex(null==socialinvestigationReportModel.getChildSex()?null:socialinvestigationReportModel.getChildSex().getId());
		socialinvestigationReport.setChildAge(null==socialinvestigationReportModel.getChildAge()?null:socialinvestigationReportModel.getChildAge());
		socialinvestigationReport.setChildCast(null==socialinvestigationReportModel.getChildCast()?null:socialinvestigationReportModel.getChildCast().getId());
		socialinvestigationReport.setChildReligion(null==socialinvestigationReportModel.getChildReligion()?null:socialinvestigationReportModel.getChildReligion().getId());
		socialinvestigationReport.setOtherChildReligion(null==socialinvestigationReportModel.getOtherChildReligion()?null:socialinvestigationReportModel.getOtherChildReligion());
		socialinvestigationReport.setFatherName(null==socialinvestigationReportModel.getFatherName()?null:socialinvestigationReportModel.getFatherName());
		socialinvestigationReport.setMotherName(null==socialinvestigationReportModel.getMotherName()?null:socialinvestigationReportModel.getMotherName());
		
		socialinvestigationReport.setGuardianName(null == socialinvestigationReportModel.getGuardianName()?null:socialinvestigationReportModel.getGuardianName());
		socialinvestigationReport.setPermanantAddress(null == socialinvestigationReportModel.getPermanantAddress()?null:socialinvestigationReportModel.getPermanantAddress());
		socialinvestigationReport.setLandmark(null == socialinvestigationReportModel.getLandmark()?null:socialinvestigationReportModel.getLandmark());
		socialinvestigationReport.setLastResidenceAddress(null == socialinvestigationReportModel.getLastResidenceAddress()?null:socialinvestigationReportModel.getLastResidenceAddress());
		socialinvestigationReport.setFamilyMemberContactNo(null==socialinvestigationReportModel.getFamilyMemberContactNo()?null:socialinvestigationReportModel.getFamilyMemberContactNo());
		socialinvestigationReport.setChildDifferentlyAbled(socialinvestigationReportModel.isChildDifferentlyAbled());
		socialinvestigationReport.setDifferentlyAbledType(null==socialinvestigationReportModel.getDifferentlyAbledType()?null:socialinvestigationReportModel.getDifferentlyAbledType());
		socialinvestigationReport.setOtherDifferentlyAbled(null==socialinvestigationReportModel.getDifferentlyAbledType()?null:socialinvestigationReportModel.getOtherDifferentlyAbled());
		socialinvestigationReport.setMentalIllSeverity(null==socialinvestigationReportModel.getMentalIllSeverity()?null:socialinvestigationReportModel.getMentalIllSeverity());
		socialinvestigationReport.setMentalRetireSeverity(null==socialinvestigationReportModel.getMentalRetireSeverity()?null:socialinvestigationReportModel.getMentalRetireSeverity());
		
		socialinvestigationReport.setRelnFatherMother(null == socialinvestigationReportModel.getRelnFatherMother()?null:socialinvestigationReportModel.getRelnFatherMother().getId());
		socialinvestigationReport.setRelnFatherChild(null == socialinvestigationReportModel.getRelnFatherChild()?null:socialinvestigationReportModel.getRelnFatherChild().getId());
		socialinvestigationReport.setRelnMotherChild(null == socialinvestigationReportModel.getRelnMotherChild()?null:socialinvestigationReportModel.getRelnMotherChild().getId());
		socialinvestigationReport.setRelnFatherSiblings(null == socialinvestigationReportModel.getRelnFatherSiblings()?null:socialinvestigationReportModel.getRelnFatherSiblings().getId());
		socialinvestigationReport.setRelnMotherSiblings(null == socialinvestigationReportModel.getRelnMotherSiblings()?null:socialinvestigationReportModel.getRelnMotherSiblings().getId());
		socialinvestigationReport.setRelnChildSiblings(null == socialinvestigationReportModel.getRelnChildSiblings()?null:socialinvestigationReportModel.getRelnChildSiblings().getId());
		socialinvestigationReport.setRelnChildRelative(null == socialinvestigationReportModel.getRelnChildRelative()?null:socialinvestigationReportModel.getRelnChildRelative().getId());
		
		socialinvestigationReport.setChildMarried(socialinvestigationReportModel.isChildMarried());
		socialinvestigationReport.setSpouseName(null==socialinvestigationReportModel.getSpouseName()?null:socialinvestigationReportModel.getSpouseName());
		socialinvestigationReport.setSpouseAge(null==socialinvestigationReportModel.getSpouseAge()?null:socialinvestigationReportModel.getSpouseAge());
		socialinvestigationReport.setSpouseDetails(null==socialinvestigationReportModel.getSpouseDetails()?null:socialinvestigationReportModel.getSpouseDetails());
		socialinvestigationReport.setChildrenName1(null==socialinvestigationReportModel.getChildrenName1()?null:socialinvestigationReportModel.getChildrenName1());
		socialinvestigationReport.setChildrenAge1(null==socialinvestigationReportModel.getChildrenAge1()?null:socialinvestigationReportModel.getChildrenAge1());
		socialinvestigationReport.setChildrenSex1(null==socialinvestigationReportModel.getChildrenSex1()?null:socialinvestigationReportModel.getChildrenSex1().getId());
		
		socialinvestigationReport.setChildrenName2(null==socialinvestigationReportModel.getChildrenName2()?null:socialinvestigationReportModel.getChildrenName2());
		socialinvestigationReport.setChildrenAge2(null==socialinvestigationReportModel.getChildrenAge2()?null:socialinvestigationReportModel.getChildrenAge2());
		socialinvestigationReport.setChildrenSex2(null==socialinvestigationReportModel.getChildrenSex2()?null:socialinvestigationReportModel.getChildrenSex2().getId());
		
		socialinvestigationReport.setReligionAttitude(null==socialinvestigationReportModel.getReligionAttitude()?null:socialinvestigationReportModel.getReligionAttitude());
		socialinvestigationReport.setLivingConditions(null==socialinvestigationReportModel.getLivingConditions()?null:socialinvestigationReportModel.getLivingConditions());
		socialinvestigationReport.setOtherFactorImportance(null==socialinvestigationReportModel.getOtherFactorImportance()?null:socialinvestigationReportModel.getOtherFactorImportance());
		socialinvestigationReport.setGoodHabits(null==socialinvestigationReportModel.getGoodHabits()?null:socialinvestigationReportModel.getGoodHabits());
		socialinvestigationReport.setOtherGoodHabits(null==socialinvestigationReportModel.getOtherGoodHabits()?null:socialinvestigationReportModel.getOtherGoodHabits());
		socialinvestigationReport.setBadHabits(null==socialinvestigationReportModel.getBadHabits()?null:socialinvestigationReportModel.getBadHabits());
		socialinvestigationReport.setDrugType(null==socialinvestigationReportModel.getDrugType()?null:socialinvestigationReportModel.getDrugType());
		socialinvestigationReport.setOtherBadHabits(null==socialinvestigationReportModel.getOtherBadHabits()?null:socialinvestigationReportModel.getOtherBadHabits());
		socialinvestigationReport.setExtracurricularInterests(null==socialinvestigationReportModel.getExtracurricularInterests()?null:socialinvestigationReportModel.getExtracurricularInterests());
		socialinvestigationReport.setPersonalityTraits(null==socialinvestigationReportModel.getPersonalityTraits()?null:socialinvestigationReportModel.getPersonalityTraits());
		socialinvestigationReport.setEducation(null==socialinvestigationReportModel.getEducation()?null:socialinvestigationReportModel.getEducation().getId());
		socialinvestigationReport.setSchoolType(null==socialinvestigationReportModel.getSchoolType()?null:socialinvestigationReportModel.getSchoolType().getId());
		socialinvestigationReport.setClassMatesAttitude(null==socialinvestigationReportModel.getClassMatesAttitude()?null:socialinvestigationReportModel.getClassMatesAttitude());
		socialinvestigationReport.setTeachersAttitude(null==socialinvestigationReportModel.getTeachersAttitude()?null:socialinvestigationReportModel.getTeachersAttitude());
		socialinvestigationReport.setReasonLeavingSchool(null==socialinvestigationReportModel.getReasonLeavingSchool()?null:socialinvestigationReportModel.getReasonLeavingSchool());
		socialinvestigationReport.setOtherReasonLeavingSchool(null==socialinvestigationReportModel.getOtherReasonLeavingSchool()?null:socialinvestigationReportModel.getOtherReasonLeavingSchool());
		socialinvestigationReport.setVocationalTraining(null==socialinvestigationReportModel.getVocationalTraining()?null:socialinvestigationReportModel.getVocationalTraining());
		socialinvestigationReport.setEmploymentDetails(null==socialinvestigationReportModel.getEmploymentDetails()?null:socialinvestigationReportModel.getEmploymentDetails());
		socialinvestigationReport.setIncomeUtilizationDtls(null==socialinvestigationReportModel.getIncomeUtilizationDtls()?null:socialinvestigationReportModel.getIncomeUtilizationDtls());
		socialinvestigationReport.setWorkRecord(null==socialinvestigationReportModel.getWorkRecord()?null:socialinvestigationReportModel.getWorkRecord());
		socialinvestigationReport.setMajorityFriendTypes(null==socialinvestigationReportModel.getMajorityFriendTypes()?null:socialinvestigationReportModel.getMajorityFriendTypes());
		socialinvestigationReport.setAttitudeTowardsFriends(null==socialinvestigationReportModel.getAttitudeTowardsFriends()?null:socialinvestigationReportModel.getAttitudeTowardsFriends());
		socialinvestigationReport.setFriendsAttitudeTowardsChild(null==socialinvestigationReportModel.getFriendsAttitudeTowardsChild()?null:socialinvestigationReportModel.getFriendsAttitudeTowardsChild());
		socialinvestigationReport.setObservationAboutNeighbourhood(null==socialinvestigationReportModel.getObservationAboutNeighbourhood()?null:socialinvestigationReportModel.getObservationAboutNeighbourhood());
		socialinvestigationReport.setMentalCondition(null==socialinvestigationReportModel.getMentalCondition()?null:socialinvestigationReportModel.getMentalCondition());
		socialinvestigationReport.setPhysicalCondition(null==socialinvestigationReportModel.getPhysicalCondition()?null:socialinvestigationReportModel.getPhysicalCondition());
		
		socialinvestigationReport.setHsRespiratoryDisorders(null==socialinvestigationReportModel.getHsRespiratoryDisorders()?null:socialinvestigationReportModel.getHsRespiratoryDisorders().getId());
		socialinvestigationReport.setHsHearingImpairment(null==socialinvestigationReportModel.getHsHearingImpairment()?null:socialinvestigationReportModel.getHsHearingImpairment().getId());
		socialinvestigationReport.setHsEyeDiseases(null==socialinvestigationReportModel.getHsEyeDiseases()?null:socialinvestigationReportModel.getHsEyeDiseases().getId());
		socialinvestigationReport.setHsDentalDisease(null==socialinvestigationReportModel.getHsDentalDisease()?null:socialinvestigationReportModel.getHsDentalDisease().getId());
		socialinvestigationReport.setHsCardiacDiseases(null==socialinvestigationReportModel.getHsCardiacDiseases()?null:socialinvestigationReportModel.getHsCardiacDiseases().getId());
		socialinvestigationReport.setHsSkinDisease(null==socialinvestigationReportModel.getHsSkinDisease()?null:socialinvestigationReportModel.getHsSkinDisease().getId());
		socialinvestigationReport.setHsSTD(null==socialinvestigationReportModel.getHsSTD()?null:socialinvestigationReportModel.getHsSTD().getId());
		socialinvestigationReport.setHsNeurologicalDisorders(null==socialinvestigationReportModel.getHsNeurologicalDisorders()?null:socialinvestigationReportModel.getHsNeurologicalDisorders().getId());
		socialinvestigationReport.setHsMentalHandicap(null==socialinvestigationReportModel.getHsMentalHandicap()?null:socialinvestigationReportModel.getHsMentalHandicap().getId());
		socialinvestigationReport.setHsPhysicalHandicap(null==socialinvestigationReportModel.getHsPhysicalHandicap()?null:socialinvestigationReportModel.getHsPhysicalHandicap().getId());
		socialinvestigationReport.setHsUrinaryTractInfections(null==socialinvestigationReportModel.getHsUrinaryTractInfections()?null:socialinvestigationReportModel.getHsUrinaryTractInfections().getId());
		socialinvestigationReport.setHsotherHealthStatusName(null==socialinvestigationReportModel.getHsotherHealthStatusName()?null:socialinvestigationReportModel.getHsotherHealthStatusName());
		
		socialinvestigationReport.setChildHasAddiction(socialinvestigationReportModel.isChildHasAddiction());
		socialinvestigationReport.setWithWhomChildWasStaying(null==socialinvestigationReportModel.getWithWhomChildWasStaying()?null:socialinvestigationReportModel.getWithWhomChildWasStaying().getId());
		socialinvestigationReport.setOtherWithWhomChildWasStaying(null==socialinvestigationReportModel.getOtherWithWhomChildWasStaying()?null:socialinvestigationReportModel.getOtherWithWhomChildWasStaying());
		socialinvestigationReport.setHistoryRunAwayFromHome(null==socialinvestigationReportModel.getHistoryRunAwayFromHome()?null:socialinvestigationReportModel.getHistoryRunAwayFromHome());
		socialinvestigationReport.setParentsAttitudeChildReaction(null==socialinvestigationReportModel.getParentsAttitudeChildReaction()?null:socialinvestigationReportModel.getParentsAttitudeChildReaction());
		socialinvestigationReport.setReasonsLeavingFamily(null==socialinvestigationReportModel.getReasonsLeavingFamily()?null:socialinvestigationReportModel.getReasonsLeavingFamily());
		socialinvestigationReport.setOtherReasonsLeavingFamily(null==socialinvestigationReportModel.getOtherReasonsLeavingFamily()?null:socialinvestigationReportModel.getOtherReasonsLeavingFamily());
		socialinvestigationReport.setChildVictim(socialinvestigationReportModel.isChildVictim());;
		//add Types of abuse met by the child and Types of ill-treatment met by the child here
		socialinvestigationReport.setVerbalAbuse(null==socialinvestigationReportModel.getVerbalAbuse()?null:socialinvestigationReportModel.getVerbalAbuse());
		socialinvestigationReport.setOtherVerbalAbuse(null==socialinvestigationReportModel.getOtherVerbalAbuse()?null:socialinvestigationReportModel.getOtherVerbalAbuse());
		socialinvestigationReport.setPhysicalAbuse(null==socialinvestigationReportModel.getPhysicalAbuse()?null:socialinvestigationReportModel.getPhysicalAbuse());
		socialinvestigationReport.setOtherPhysicalAbuse(null==socialinvestigationReportModel.getOtherPhysicalAbuse()?null:socialinvestigationReportModel.getOtherPhysicalAbuse());
		socialinvestigationReport.setSexualAbuse(null==socialinvestigationReportModel.getSexualAbuse()?null:socialinvestigationReportModel.getSexualAbuse());
		socialinvestigationReport.setOtherSexualAbuse(null==socialinvestigationReportModel.getOtherSexualAbuse()?null:socialinvestigationReportModel.getOtherSexualAbuse());
		socialinvestigationReport.setOtherInOtherAbuse(null==socialinvestigationReportModel.getOtherInOtherAbuse()?null:socialinvestigationReportModel.getOtherInOtherAbuse());
		
		socialinvestigationReport.setIllTreatmentDenialOfFood(null==socialinvestigationReportModel.getIllTreatmentDenialOfFood()?null:socialinvestigationReportModel.getIllTreatmentDenialOfFood());
		socialinvestigationReport.setOtherIllTreatmentDenialOfFood(null==socialinvestigationReportModel.getOtherIllTreatmentDenialOfFood()?null:socialinvestigationReportModel.getOtherIllTreatmentDenialOfFood());
		socialinvestigationReport.setIllTreatmentBeatenMercilessly(null==socialinvestigationReportModel.getIllTreatmentBeatenMercilessly()?null:socialinvestigationReportModel.getIllTreatmentBeatenMercilessly());
		socialinvestigationReport.setOtherIllTreatmentBeatenMercilessly(null==socialinvestigationReportModel.getOtherIllTreatmentBeatenMercilessly()?null:socialinvestigationReportModel.getOtherIllTreatmentBeatenMercilessly());
		socialinvestigationReport.setIllTreatmentCausingInjury(null==socialinvestigationReportModel.getIllTreatmentCausingInjury()?null:socialinvestigationReportModel.getIllTreatmentCausingInjury());
		socialinvestigationReport.setOtherIllTreatmentCausingInjury(null==socialinvestigationReportModel.getOtherIllTreatmentCausingInjury()?null:socialinvestigationReportModel.getOtherIllTreatmentCausingInjury());
		socialinvestigationReport.setIllTreatmentDetention(null==socialinvestigationReportModel.getIllTreatmentDetention()?null:socialinvestigationReportModel.getIllTreatmentDetention());
		socialinvestigationReport.setOtherIllTreatmentDetention(null==socialinvestigationReportModel.getOtherIllTreatmentDetention()?null:socialinvestigationReportModel.getOtherIllTreatmentDetention());
		socialinvestigationReport.setOtherInOtherIllTreatment(null==socialinvestigationReportModel.getOtherInOtherIllTreatment()?null:socialinvestigationReportModel.getOtherInOtherIllTreatment());
		
		socialinvestigationReport.setExploitationFaced(null==socialinvestigationReportModel.getExploitationFaced()?null:socialinvestigationReportModel.getExploitationFaced());
		socialinvestigationReport.setOtherExploitationFaced(null==socialinvestigationReportModel.getOtherExploitationFaced()?null:socialinvestigationReportModel.getOtherExploitationFaced());
		socialinvestigationReport.setBoughtSoldProcuredTrafficked(socialinvestigationReportModel.isBoughtSoldProcuredTrafficked());
		socialinvestigationReport.setUsedForBegging(socialinvestigationReportModel.isUsedForBegging());
		socialinvestigationReport.setUsedByAnyGang(socialinvestigationReportModel.isUsedByAnyGang());
		socialinvestigationReport.setInstitutionDocType(null==socialinvestigationReportModel.getInstitutionDocType()?null:socialinvestigationReportModel.getInstitutionDocType().getId());
		
		socialinvestigationReport.setPerpetratorName(null==socialinvestigationReportModel.getPerpetratorName()?null:socialinvestigationReportModel.getPerpetratorName());
		socialinvestigationReport.setPerpetratorAge(null==socialinvestigationReportModel.getPerpetratorAge()?null:socialinvestigationReportModel.getPerpetratorAge());
		socialinvestigationReport.setPerpetratorContact(null==socialinvestigationReportModel.getPerpetratorContact()?null:socialinvestigationReportModel.getPerpetratorContact());
		socialinvestigationReport.setPerpetratorAddress(null==socialinvestigationReportModel.getPerpetratorAddress()?null:socialinvestigationReportModel.getPerpetratorAddress());
		socialinvestigationReport.setPerpetratorPhysicalCharacteristics(null==socialinvestigationReportModel.getPerpetratorPhysicalCharacteristics()?null:socialinvestigationReportModel.getPerpetratorPhysicalCharacteristics());
		socialinvestigationReport.setPerpetratorRelnWithFamily(null==socialinvestigationReportModel.getPerpetratorRelnWithFamily()?null:socialinvestigationReportModel.getPerpetratorRelnWithFamily());
		socialinvestigationReport.setPerpetratorMiddleMenInvolved(socialinvestigationReportModel.isPerpetratorMiddleMenInvolved());
		socialinvestigationReport.setPerpetratorOtherChildAbused(socialinvestigationReportModel.isPerpetratorOtherChildAbused());
		socialinvestigationReport.setPerpetratorHowChildCame(null==socialinvestigationReportModel.getPerpetratorHowChildCame()?null:socialinvestigationReportModel.getPerpetratorHowChildCame());
		socialinvestigationReport.setAttitudeTowardsPerpetrator(null==socialinvestigationReportModel.getAttitudeTowardsPerpetrator()?null:socialinvestigationReportModel.getAttitudeTowardsPerpetrator());
		socialinvestigationReport.setPoliceInformed(socialinvestigationReportModel.isPoliceInformed());
		socialinvestigationReport.setActionAgainstPerpetrator(null==socialinvestigationReportModel.getActionAgainstPerpetrator()?null:socialinvestigationReportModel.getActionAgainstPerpetrator());
		socialinvestigationReport.setAnyOtherRemark(null==socialinvestigationReportModel.getAnyOtherRemark()?null:socialinvestigationReportModel.getAnyOtherRemark());
		
		socialinvestigationReport.setOoiEmotionalFactors(null==socialinvestigationReportModel.getOoiEmotionalFactors()?null:socialinvestigationReportModel.getOoiEmotionalFactors());
		socialinvestigationReport.setOoiPhysicalCondition(null==socialinvestigationReportModel.getOoiPhysicalCondition()?null:socialinvestigationReportModel.getOoiPhysicalCondition());
		socialinvestigationReport.setOoiIntelligence(null==socialinvestigationReportModel.getOoiIntelligence()?null:socialinvestigationReportModel.getOoiIntelligence());
		socialinvestigationReport.setOoiSocialEconomicFactors(null==socialinvestigationReportModel.getOoiSocialEconomicFactors()?null:socialinvestigationReportModel.getOoiSocialEconomicFactors());
		socialinvestigationReport.setOoiSuggestiveCausesProblems(null==socialinvestigationReportModel.getOoiSuggestiveCausesProblems()?null:socialinvestigationReportModel.getOoiSuggestiveCausesProblems());
		socialinvestigationReport.setOoiAnalysisOfCase(null==socialinvestigationReportModel.getOoiAnalysisOfCase()?null:socialinvestigationReportModel.getOoiAnalysisOfCase());
		socialinvestigationReport.setOoiReasonsForCareProtection(null==socialinvestigationReportModel.getOoiReasonsForCareProtection()?null:socialinvestigationReportModel.getOoiReasonsForCareProtection());
		socialinvestigationReport.setOoiOpinionExperts(null==socialinvestigationReportModel.getOoiOpinionExperts()?null:socialinvestigationReportModel.getOoiOpinionExperts());
		socialinvestigationReport.setOoiPsychoSocialAssessment(null==socialinvestigationReportModel.getOoiPsychoSocialAssessment()?null:socialinvestigationReportModel.getOoiPsychoSocialAssessment());
		socialinvestigationReport.setOoiReligiousFactors(null==socialinvestigationReportModel.getOoiReligiousFactors()?null:socialinvestigationReportModel.getOoiReligiousFactors());
		socialinvestigationReport.setOoiRiskAnalysis(null==socialinvestigationReportModel.getOoiRiskAnalysis()?null:socialinvestigationReportModel.getOoiRiskAnalysis());
		socialinvestigationReport.setOoiRecommendation(null==socialinvestigationReportModel.getOoiRecommendation()?null:socialinvestigationReportModel.getOoiRecommendation());
		
		socialinvestigationReport.setHoiFathernoc(null==socialinvestigationReportModel.getHoiFathernoc()?null:socialinvestigationReportModel.getHoiFathernoc());
		socialinvestigationReport.setHoiFatherLs(null==socialinvestigationReportModel.getHoiFatherLs()?null:socialinvestigationReportModel.getHoiFatherLs());
		socialinvestigationReport.setHoiFatherAr(socialinvestigationReportModel.isHoiFatherAr());
		socialinvestigationReport.setHoiFatherPoc(null==socialinvestigationReportModel.getHoiFatherPoc()?null:socialinvestigationReportModel.getHoiFatherPoc());
		socialinvestigationReport.setHoiFatherPa(null==socialinvestigationReportModel.getHoiFatherPa()?null:socialinvestigationReportModel.getHoiFatherPa());
		
		socialinvestigationReport.setHoiStepFathernoc(null==socialinvestigationReportModel.getHoiStepFathernoc()?null:socialinvestigationReportModel.getHoiStepFathernoc());
		socialinvestigationReport.setHoiStepFatherLs(null==socialinvestigationReportModel.getHoiStepFatherLs()?null:socialinvestigationReportModel.getHoiStepFatherLs());
		socialinvestigationReport.setHoiStepFatherAr(socialinvestigationReportModel.isHoiStepFatherAr());
		socialinvestigationReport.setHoiStepFatherPoc(null==socialinvestigationReportModel.getHoiStepFatherPoc()?null:socialinvestigationReportModel.getHoiStepFatherPoc());
		socialinvestigationReport.setHoiStepFatherPa(null==socialinvestigationReportModel.getHoiStepFatherPa()?null:socialinvestigationReportModel.getHoiStepFatherPa());
		
		socialinvestigationReport.setHoiMothernoc(null==socialinvestigationReportModel.getHoiMothernoc()?null:socialinvestigationReportModel.getHoiMothernoc());
		socialinvestigationReport.setHoiMotherLs(null==socialinvestigationReportModel.getHoiMotherLs()?null:socialinvestigationReportModel.getHoiMotherLs());
		socialinvestigationReport.setHoiMotherAr(socialinvestigationReportModel.isHoiMotherAr());
		socialinvestigationReport.setHoiMotherPoc(null==socialinvestigationReportModel.getHoiMotherPoc()?null:socialinvestigationReportModel.getHoiMotherPoc());
		socialinvestigationReport.setHoiMotherPa(null==socialinvestigationReportModel.getHoiMotherPa()?null:socialinvestigationReportModel.getHoiMotherPa());
		
		socialinvestigationReport.setHoiStepMothernoc(null==socialinvestigationReportModel.getHoiStepMothernoc()?null:socialinvestigationReportModel.getHoiStepMothernoc());
		socialinvestigationReport.setHoiStepMotherLs(null==socialinvestigationReportModel.getHoiStepMotherLs()?null:socialinvestigationReportModel.getHoiStepMotherLs());
		socialinvestigationReport.setHoiStepMotherAr(socialinvestigationReportModel.isHoiStepMotherAr());
		socialinvestigationReport.setHoiStepMotherPoc(null==socialinvestigationReportModel.getHoiStepMotherPoc()?null:socialinvestigationReportModel.getHoiStepMotherPoc());
		socialinvestigationReport.setHoiStepMotherPa(null==socialinvestigationReportModel.getHoiStepMotherPa()?null:socialinvestigationReportModel.getHoiStepMotherPa());
		
		socialinvestigationReport.setHoiBrothernoc(null==socialinvestigationReportModel.getHoiBrothernoc()?null:socialinvestigationReportModel.getHoiBrothernoc());
		socialinvestigationReport.setHoiBrotherLs(null==socialinvestigationReportModel.getHoiBrotherLs()?null:socialinvestigationReportModel.getHoiBrotherLs());
		socialinvestigationReport.setHoiBrotherAr(socialinvestigationReportModel.isHoiBrotherAr());
		socialinvestigationReport.setHoiBrotherPoc(null==socialinvestigationReportModel.getHoiBrotherPoc()?null:socialinvestigationReportModel.getHoiBrotherPoc());
		socialinvestigationReport.setHoiBrotherPa(null==socialinvestigationReportModel.getHoiBrotherPa()?null:socialinvestigationReportModel.getHoiBrotherPa());
		
		socialinvestigationReport.setHoiSisternoc(null==socialinvestigationReportModel.getHoiSisternoc()?null:socialinvestigationReportModel.getHoiSisternoc());
		socialinvestigationReport.setHoiSisterLs(null==socialinvestigationReportModel.getHoiSisterLs()?null:socialinvestigationReportModel.getHoiSisterLs());
		socialinvestigationReport.setHoiSisterAr(socialinvestigationReportModel.isHoiSisterAr());
		socialinvestigationReport.setHoiSisterPoc(null==socialinvestigationReportModel.getHoiSisterPoc()?null:socialinvestigationReportModel.getHoiSisterPoc());
		socialinvestigationReport.setHoiSisterPa(null==socialinvestigationReportModel.getHoiSisterPa()?null:socialinvestigationReportModel.getHoiSisterPa());
		
		socialinvestigationReport.setHoiOtherFamilyMemberName(null==socialinvestigationReportModel.getHoiOtherFamilyMemberName()?null:socialinvestigationReportModel.getHoiOtherFamilyMemberName());
		socialinvestigationReport.setHoiOthersnoc(null==socialinvestigationReportModel.getHoiOthersnoc()?null:socialinvestigationReportModel.getHoiOthersnoc());
		socialinvestigationReport.setHoiOthersLs(null==socialinvestigationReportModel.getHoiOthersLs()?null:socialinvestigationReportModel.getHoiOthersLs());
		socialinvestigationReport.setHoiOthersAr(socialinvestigationReportModel.isHoiOthersAr());
		socialinvestigationReport.setHoiOthersPoc(null==socialinvestigationReportModel.getHoiOthersPoc()?null:socialinvestigationReportModel.getHoiOthersPoc());
		socialinvestigationReport.setHoiOthersPa(null==socialinvestigationReportModel.getHoiOthersPa()?null:socialinvestigationReportModel.getHoiOthersPa());
		socialinvestigationReport.setBankAccountno(socialinvestigationReportModel.getBankAccountNo()==null ? null : socialinvestigationReportModel.getBankAccountNo());
		socialinvestigationReport.setAccountholdername(null==socialinvestigationReportModel.getAccountholdername()?null:socialinvestigationReportModel.getAccountholdername());
		socialinvestigationReport.setBankname(null==socialinvestigationReportModel.getBankname()?null:socialinvestigationReportModel.getBankname());
		socialinvestigationReport.setIfsccode(null==socialinvestigationReportModel.getIfsccode()?null:socialinvestigationReportModel.getIfsccode());
		socialinvestigationReport.setBranch(null==socialinvestigationReportModel.getBranch()?null:socialinvestigationReportModel.getBranch());
		
		
		socialinvestigationReport.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		socialinvestigationReport.setCreatedDate(new java.sql.Date(new Date().getTime()));
		socialinvestigationReport.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		
		
		
		socialinvestigationReport = socialInvestigationReportRepository.save(socialinvestigationReport);
		
		ChildDetails childDetails = childDetailsRepository.findChildById(socialinvestigationReportModel.getChildId());
		
		childDetails.setId(childDetails.getId());
		childDetails.setSirFatherName(null==socialinvestigationReportModel.getFatherName()?null:socialinvestigationReportModel.getFatherName());
		childDetails.setSirMotherName(null==socialinvestigationReportModel.getMotherName()?null:socialinvestigationReportModel.getMotherName());
		childDetails.setSirChildAddress(null == socialinvestigationReportModel.getPermanantAddress()?null:socialinvestigationReportModel.getPermanantAddress());
		childDetails.setSirChildCast(null==socialinvestigationReportModel.getChildCast()?null:socialinvestigationReportModel.getChildCast().getId());
		childDetails.setSirChildReligion(null==socialinvestigationReportModel.getChildReligion()?null:socialinvestigationReportModel.getChildReligion().getId());
		childDetails.setSirOtherChildReligion(null==socialinvestigationReportModel.getOtherChildReligion()?null:socialinvestigationReportModel.getOtherChildReligion());
		childDetails.setSirFilled(1);
		
		childDetailsRepository.save(childDetails);
		System.out.println(childDetails.getId());
		
		List<SocialInvestigationReportFamilyDetails> socialInvestigationReportFamilyDetails = new ArrayList<SocialInvestigationReportFamilyDetails>();
		for(SocialInvestigationReportFamilyDetailsModel socialInvestigationReportFamilyDetailsModel : socialInvestigationReportFamilyDetailsModels){
			
			SocialInvestigationReportFamilyDetails socialInvestigationReportFamilyDetail = new SocialInvestigationReportFamilyDetails();
			
			socialInvestigationReportFamilyDetail.setName(null==socialInvestigationReportFamilyDetailsModel.getName()?null:socialInvestigationReportFamilyDetailsModel.getName());
			socialInvestigationReportFamilyDetail.setRelationship(null==socialInvestigationReportFamilyDetailsModel.getRelationship()?null:socialInvestigationReportFamilyDetailsModel.getRelationship());
		    socialInvestigationReportFamilyDetail.setAge(null==socialInvestigationReportFamilyDetailsModel.getAge()?null:socialInvestigationReportFamilyDetailsModel.getAge());
		    socialInvestigationReportFamilyDetail.setSex(null==socialInvestigationReportFamilyDetailsModel.getSex()?null:socialInvestigationReportFamilyDetailsModel.getSex().getId());
		    socialInvestigationReportFamilyDetail.setEducation(null==socialInvestigationReportFamilyDetailsModel.getEducation()?null:socialInvestigationReportFamilyDetailsModel.getEducation().getId());
		    socialInvestigationReportFamilyDetail.setOccupation(null==socialInvestigationReportFamilyDetailsModel.getOccupation()?null:socialInvestigationReportFamilyDetailsModel.getOccupation());
		    socialInvestigationReportFamilyDetail.setIncome(null==socialInvestigationReportFamilyDetailsModel.getIncome()?null:socialInvestigationReportFamilyDetailsModel.getIncome());
		    socialInvestigationReportFamilyDetail.setHealthStatus(null==socialInvestigationReportFamilyDetailsModel.getHealthStatus()?null:socialInvestigationReportFamilyDetailsModel.getHealthStatus());
		    socialInvestigationReportFamilyDetail.setHistoryOfMentalIllness(null==socialInvestigationReportFamilyDetailsModel.getHistoryOfMentalIllness()?null:socialInvestigationReportFamilyDetailsModel.getHistoryOfMentalIllness());
		    socialInvestigationReportFamilyDetail.setAddictions(null==socialInvestigationReportFamilyDetailsModel.getAddictions()?null:socialInvestigationReportFamilyDetailsModel.getAddictions());
		    socialInvestigationReportFamilyDetail.setSocialinvestigationReport(socialinvestigationReport);
			
			socialInvestigationReportFamilyDetails.add(socialInvestigationReportFamilyDetail);
		}
		socialInvestigationReportFamilyDetailsRepository.save(socialInvestigationReportFamilyDetails);

	}

	@Override
	public Integer getId(String childId) {
		if(childId != null && !(childId.trim().equals(""))){
			List<SocialinvestigationReport> obj = socialInvestigationReportRepository.findByChildId(childId);
			if(obj != null && obj.size() > 0){
				return obj.get(0).getId();
			}
		}
		return null;
	}
	

	@Override
	public Integer getRefId(Integer refId) {
		if(refId != null){
			List<SocialInvestigationReportFamilyDetails> fdObj = socialInvestigationReportFamilyDetailsRepository.findByRefId(refId);
			if(fdObj != null && fdObj.size() > 0){
//				System.out.println("----------------------"+fdObj.get(0).getId()+"------------------------");
				return fdObj.get(0).getId();
				
			}
		}
		return null;
	}

	@Override
	public SocialinvestigationReportModel getSocialIvestigationreport(String childId) {
		
		List<SocialinvestigationReport> socialinvestigationReport = socialInvestigationReportRepository.findByChildId(childId);
		
		
		Map<Integer, ValueObject> typeDetailsMap = getTypeMap();
		SocialinvestigationReportModel socialinvestigationReportModel = new SocialinvestigationReportModel();
		if(socialinvestigationReport != null && socialinvestigationReport.size()>0){
			
			socialinvestigationReportModel.setSlNo(socialinvestigationReport.get(0).getSlNo());
			socialinvestigationReportModel.setId(socialinvestigationReport.get(0).getId());
			socialinvestigationReportModel.setReportPreparedBy(typeDetailsMap.get(socialinvestigationReport.get(0).getReportPreparedBy()));
			socialinvestigationReportModel.setChildName(socialinvestigationReport.get(0).getChildName());
			socialinvestigationReportModel.setChildAge(socialinvestigationReport.get(0).getChildAge());
//			sex
			socialinvestigationReportModel.setChildSex(typeDetailsMap.get(socialinvestigationReport.get(0).getChildSex()));
//			caste
			socialinvestigationReportModel.setChildCast(typeDetailsMap.get(socialinvestigationReport.get(0).getChildCast()));
			socialinvestigationReportModel.setChildCast(typeDetailsMap.get(socialinvestigationReport.get(0).getChildCast()));
			socialinvestigationReportModel.setChildReligion(typeDetailsMap.get(socialinvestigationReport.get(0).getChildReligion()));
			socialinvestigationReportModel.setOtherChildReligion(socialinvestigationReport.get(0).getOtherChildReligion());
			socialinvestigationReportModel.setFatherName(socialinvestigationReport.get(0).getFatherName());
			socialinvestigationReportModel.setMotherName(socialinvestigationReport.get(0).getMotherName());
			socialinvestigationReportModel.setGuardianName(socialinvestigationReport.get(0).getGuardianName());
			socialinvestigationReportModel.setPermanantAddress(socialinvestigationReport.get(0).getPermanantAddress());
			socialinvestigationReportModel.setLandmark(socialinvestigationReport.get(0).getLandmark());
			socialinvestigationReportModel.setLastResidenceAddress(socialinvestigationReport.get(0).getLastResidenceAddress());
			socialinvestigationReportModel.setFamilyMemberContactNo(socialinvestigationReport.get(0).getFamilyMemberContactNo());
			//setChildDifferentlyAbleModel
			socialinvestigationReportModel.setChildDifferentlyAbled(socialinvestigationReport.get(0).isChildDifferentlyAbled());
			socialinvestigationReportModel.setDifferentlyAbledType(socialinvestigationReport.get(0).getDifferentlyAbledType());
			socialinvestigationReportModel.setOtherDifferentlyAbled(socialinvestigationReport.get(0).getOtherDifferentlyAbled());
			socialinvestigationReportModel.setMentalIllSeverity(socialinvestigationReport.get(0).getMentalIllSeverity());
			socialinvestigationReportModel.setMentalRetireSeverity(socialinvestigationReport.get(0).getMentalRetireSeverity());
			//all relations between family members
			socialinvestigationReportModel.setRelnFatherMother(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnFatherMother()));
			socialinvestigationReportModel.setRelnFatherChild(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnFatherChild()));
			socialinvestigationReportModel.setRelnMotherChild(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnMotherChild()));
			socialinvestigationReportModel.setRelnFatherSiblings(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnFatherSiblings()));
			socialinvestigationReportModel.setRelnMotherSiblings(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnMotherSiblings()));
			socialinvestigationReportModel.setRelnChildSiblings(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnChildSiblings()));
			socialinvestigationReportModel.setRelnChildRelative(typeDetailsMap.get(socialinvestigationReport.get(0).getRelnChildRelative()));
			//setChildMarriedModel
			socialinvestigationReportModel.setChildMarried(socialinvestigationReport.get(0).isChildMarried());
			socialinvestigationReportModel.setSpouseName(socialinvestigationReport.get(0).getSpouseName());
			socialinvestigationReportModel.setSpouseAge(socialinvestigationReport.get(0).getSpouseAge());
			socialinvestigationReportModel.setSpouseDetails(socialinvestigationReport.get(0).getSpouseDetails());
			socialinvestigationReportModel.setChildrenName1(socialinvestigationReport.get(0).getChildrenName1());
			socialinvestigationReportModel.setChildrenAge1(socialinvestigationReport.get(0).getChildrenAge1());
			//setChildrenSex1
			socialinvestigationReportModel.setChildrenSex1(typeDetailsMap.get(socialinvestigationReport.get(0).getChildrenSex1()));
			socialinvestigationReportModel.setChildrenName2(socialinvestigationReport.get(0).getChildrenName2());
			socialinvestigationReportModel.setChildrenAge2(socialinvestigationReport.get(0).getChildrenAge2());
			//setChildrenSex2
			socialinvestigationReportModel.setChildrenSex2(typeDetailsMap.get(socialinvestigationReport.get(0).getChildrenSex2()));
			socialinvestigationReportModel.setHoiFathernoc(socialinvestigationReport.get(0).getHoiFathernoc());
			socialinvestigationReportModel.setHoiFatherLs(socialinvestigationReport.get(0).getHoiFatherLs());
			//ar
			socialinvestigationReportModel.setHoiFatherAr(socialinvestigationReport.get(0).isHoiFatherAr());
			socialinvestigationReportModel.setHoiFatherPoc(socialinvestigationReport.get(0).getHoiFatherPoc());
			socialinvestigationReportModel.setHoiFatherPa(socialinvestigationReport.get(0).getHoiFatherPa());
			
			socialinvestigationReportModel.setHoiStepFathernoc(socialinvestigationReport.get(0).getHoiStepFathernoc());
			socialinvestigationReportModel.setHoiStepFatherLs(socialinvestigationReport.get(0).getHoiStepFatherLs());
			//ar
			socialinvestigationReportModel.setHoiStepFatherAr(socialinvestigationReport.get(0).isHoiStepFatherAr());
			socialinvestigationReportModel.setHoiStepFatherPoc(socialinvestigationReport.get(0).getHoiStepFatherPoc());
			socialinvestigationReportModel.setHoiStepFatherPa(socialinvestigationReport.get(0).getHoiStepFatherPa());
			
			socialinvestigationReportModel.setHoiMothernoc(socialinvestigationReport.get(0).getHoiMothernoc());
			socialinvestigationReportModel.setHoiMotherLs(socialinvestigationReport.get(0).getHoiMotherLs());
			//ar
			socialinvestigationReportModel.setHoiMotherAr(socialinvestigationReport.get(0).isHoiMotherAr());
			socialinvestigationReportModel.setHoiMotherPoc(socialinvestigationReport.get(0).getHoiMotherPoc());
			socialinvestigationReportModel.setHoiMotherPa(socialinvestigationReport.get(0).getHoiMotherPa());
			
			socialinvestigationReportModel.setHoiStepMothernoc(socialinvestigationReport.get(0).getHoiStepMothernoc());
			socialinvestigationReportModel.setHoiStepMotherLs(socialinvestigationReport.get(0).getHoiStepMotherLs());
			//ar
			socialinvestigationReportModel.setHoiStepMotherAr(socialinvestigationReport.get(0).isHoiStepMotherAr());
			socialinvestigationReportModel.setHoiStepMotherPoc(socialinvestigationReport.get(0).getHoiStepMotherPoc());
			socialinvestigationReportModel.setHoiStepMotherPa(socialinvestigationReport.get(0).getHoiStepMotherPa());
			
			socialinvestigationReportModel.setHoiBrothernoc(socialinvestigationReport.get(0).getHoiBrothernoc());
			socialinvestigationReportModel.setHoiBrotherLs(socialinvestigationReport.get(0).getHoiBrotherLs());
			//ar
			socialinvestigationReportModel.setHoiBrotherAr(socialinvestigationReport.get(0).isHoiBrotherAr());
			socialinvestigationReportModel.setHoiBrotherPoc(socialinvestigationReport.get(0).getHoiBrotherPoc());
			socialinvestigationReportModel.setHoiBrotherPa(socialinvestigationReport.get(0).getHoiBrotherPa());
			
			socialinvestigationReportModel.setHoiSisternoc(socialinvestigationReport.get(0).getHoiSisternoc());
			socialinvestigationReportModel.setHoiSisterLs(socialinvestigationReport.get(0).getHoiSisterLs());
			//ar
			socialinvestigationReportModel.setHoiSisterAr(socialinvestigationReport.get(0).isHoiSisterAr());
			socialinvestigationReportModel.setHoiSisterPoc(socialinvestigationReport.get(0).getHoiSisterPoc());
			socialinvestigationReportModel.setHoiSisterPa(socialinvestigationReport.get(0).getHoiSisterPa());
			
			socialinvestigationReportModel.setHoiOtherFamilyMemberName(socialinvestigationReport.get(0).getHoiOtherFamilyMemberName());
			socialinvestigationReportModel.setHoiOthersnoc(socialinvestigationReport.get(0).getHoiOthersnoc());
			socialinvestigationReportModel.setHoiOthersLs(socialinvestigationReport.get(0).getHoiOthersLs());
			//ar
			socialinvestigationReportModel.setHoiOthersAr(socialinvestigationReport.get(0).isHoiOthersAr());
			socialinvestigationReportModel.setHoiOthersPoc(socialinvestigationReport.get(0).getHoiOthersPoc());
			socialinvestigationReportModel.setHoiOthersPa(socialinvestigationReport.get(0).getHoiOthersPa());
			
			socialinvestigationReportModel.setReligionAttitude(socialinvestigationReport.get(0).getReligionAttitude());
			socialinvestigationReportModel.setLivingConditions(socialinvestigationReport.get(0).getLivingConditions());
			socialinvestigationReportModel.setOtherFactorImportance(socialinvestigationReport.get(0).getOtherFactorImportance());
			socialinvestigationReportModel.setOtherFactorImportance(socialinvestigationReport.get(0).getOtherFactorImportance());
			//setGoodHabits
			socialinvestigationReportModel.setGoodHabits(socialinvestigationReport.get(0).getGoodHabits());
			socialinvestigationReportModel.setOtherGoodHabits(socialinvestigationReport.get(0).getOtherGoodHabits());
			//setBadHabits
			socialinvestigationReportModel.setBadHabits(socialinvestigationReport.get(0).getBadHabits());
			socialinvestigationReportModel.setDrugType(socialinvestigationReport.get(0).getDrugType());
			socialinvestigationReportModel.setOtherBadHabits(socialinvestigationReport.get(0).getOtherBadHabits());
			socialinvestigationReportModel.setExtracurricularInterests(socialinvestigationReport.get(0).getExtracurricularInterests());
			socialinvestigationReportModel.setPersonalityTraits(socialinvestigationReport.get(0).getPersonalityTraits());
			//setEducation
			socialinvestigationReportModel.setEducation(typeDetailsMap.get(socialinvestigationReport.get(0).getEducation()));
			//setSchoolType
			socialinvestigationReportModel.setSchoolType(typeDetailsMap.get(socialinvestigationReport.get(0).getSchoolType()));
			socialinvestigationReportModel.setClassMatesAttitude(socialinvestigationReport.get(0).getClassMatesAttitude());
			socialinvestigationReportModel.setTeachersAttitude(socialinvestigationReport.get(0).getTeachersAttitude());
			//setReasonLeavingSchool
			socialinvestigationReportModel.setReasonLeavingSchool(socialinvestigationReport.get(0).getReasonLeavingSchool());
			socialinvestigationReportModel.setOtherReasonLeavingSchool(socialinvestigationReport.get(0).getOtherReasonLeavingSchool());
			socialinvestigationReportModel.setVocationalTraining(socialinvestigationReport.get(0).getVocationalTraining());
			socialinvestigationReportModel.setEmploymentDetails(socialinvestigationReport.get(0).getEmploymentDetails());
			socialinvestigationReportModel.setIncomeUtilizationDtls(socialinvestigationReport.get(0).getIncomeUtilizationDtls());
			socialinvestigationReportModel.setWorkRecord(socialinvestigationReport.get(0).getWorkRecord());
			//setMajorityFriendTypes
			socialinvestigationReportModel.setMajorityFriendTypes(socialinvestigationReport.get(0).getMajorityFriendTypes());
			socialinvestigationReportModel.setAttitudeTowardsFriends(socialinvestigationReport.get(0).getAttitudeTowardsFriends());
			socialinvestigationReportModel.setFriendsAttitudeTowardsChild(socialinvestigationReport.get(0).getFriendsAttitudeTowardsChild());
			socialinvestigationReportModel.setObservationAboutNeighbourhood(socialinvestigationReport.get(0).getObservationAboutNeighbourhood());
			socialinvestigationReportModel.setMentalCondition(socialinvestigationReport.get(0).getMentalCondition());
			socialinvestigationReportModel.setPhysicalCondition(socialinvestigationReport.get(0).getPhysicalCondition());
			//set all health status
			socialinvestigationReportModel.setHsRespiratoryDisorders(typeDetailsMap.get(socialinvestigationReport.get(0).getHsRespiratoryDisorders()));
			socialinvestigationReportModel.setHsHearingImpairment(typeDetailsMap.get(socialinvestigationReport.get(0).getHsHearingImpairment()));
			socialinvestigationReportModel.setHsEyeDiseases(typeDetailsMap.get(socialinvestigationReport.get(0).getHsEyeDiseases()));
			socialinvestigationReportModel.setHsDentalDisease(typeDetailsMap.get(socialinvestigationReport.get(0).getHsDentalDisease()));
			socialinvestigationReportModel.setHsCardiacDiseases(typeDetailsMap.get(socialinvestigationReport.get(0).getHsCardiacDiseases()));
			socialinvestigationReportModel.setHsSkinDisease(typeDetailsMap.get(socialinvestigationReport.get(0).getHsSkinDisease()));
			socialinvestigationReportModel.setHsSTD(typeDetailsMap.get(socialinvestigationReport.get(0).getHsSTD()));
			socialinvestigationReportModel.setHsNeurologicalDisorders(typeDetailsMap.get(socialinvestigationReport.get(0).getHsNeurologicalDisorders()));
			socialinvestigationReportModel.setHsMentalHandicap(typeDetailsMap.get(socialinvestigationReport.get(0).getHsMentalHandicap()));
			socialinvestigationReportModel.setHsPhysicalHandicap(typeDetailsMap.get(socialinvestigationReport.get(0).getHsPhysicalHandicap()));
			socialinvestigationReportModel.setHsUrinaryTractInfections(typeDetailsMap.get(socialinvestigationReport.get(0).getHsUrinaryTractInfections()));
			socialinvestigationReportModel.setHsotherHealthStatusName(socialinvestigationReport.get(0).getHsotherHealthStatusName());
			
			
			//set setChildHasAddiction
			socialinvestigationReportModel.setChildHasAddiction(socialinvestigationReport.get(0).isChildHasAddiction());
			socialinvestigationReportModel.setWithWhomChildWasStaying(typeDetailsMap.get(socialinvestigationReport.get(0).getWithWhomChildWasStaying()));
			socialinvestigationReportModel.setOtherWithWhomChildWasStaying(socialinvestigationReport.get(0).getOtherWithWhomChildWasStaying());
			socialinvestigationReportModel.setHistoryRunAwayFromHome(socialinvestigationReport.get(0).getHistoryRunAwayFromHome());
			socialinvestigationReportModel.setParentsAttitudeChildReaction(socialinvestigationReport.get(0).getParentsAttitudeChildReaction());
			//setReasonsLeavingFamily
			socialinvestigationReportModel.setReasonsLeavingFamily(socialinvestigationReport.get(0).getReasonsLeavingFamily());
			socialinvestigationReportModel.setOtherReasonsLeavingFamily(socialinvestigationReport.get(0).getOtherReasonsLeavingFamily());
			//setChildVictim
			socialinvestigationReportModel.setChildVictim(socialinvestigationReport.get(0).isChildVictim());
			//setVerbalAbuse
			socialinvestigationReportModel.setVerbalAbuse(socialinvestigationReport.get(0).getVerbalAbuse());
			socialinvestigationReportModel.setOtherVerbalAbuse(socialinvestigationReport.get(0).getOtherVerbalAbuse());
			//setPhysicalAbuse
			socialinvestigationReportModel.setPhysicalAbuse(socialinvestigationReport.get(0).getPhysicalAbuse());
			socialinvestigationReportModel.setOtherPhysicalAbuse(socialinvestigationReport.get(0).getOtherPhysicalAbuse());
			//setSexualAbuse
			socialinvestigationReportModel.setSexualAbuse(socialinvestigationReport.get(0).getSexualAbuse());
			socialinvestigationReportModel.setOtherSexualAbuse(socialinvestigationReport.get(0).getOtherSexualAbuse());
			socialinvestigationReportModel.setOtherInOtherAbuse(socialinvestigationReport.get(0).getOtherInOtherAbuse());
			
			socialinvestigationReportModel.setIllTreatmentDenialOfFood(socialinvestigationReport.get(0).getIllTreatmentDenialOfFood());
			socialinvestigationReportModel.setOtherIllTreatmentDenialOfFood(socialinvestigationReport.get(0).getOtherIllTreatmentDenialOfFood());
			//setIllTreatmentBeatenMercilessly
			socialinvestigationReportModel.setIllTreatmentBeatenMercilessly(socialinvestigationReport.get(0).getIllTreatmentBeatenMercilessly());
			socialinvestigationReportModel.setOtherIllTreatmentBeatenMercilessly(socialinvestigationReport.get(0).getOtherIllTreatmentBeatenMercilessly());
			//setIllTreatmentCausingInjury
			socialinvestigationReportModel.setIllTreatmentCausingInjury(socialinvestigationReport.get(0).getIllTreatmentCausingInjury());
			socialinvestigationReportModel.setOtherIllTreatmentCausingInjury(socialinvestigationReport.get(0).getOtherIllTreatmentCausingInjury());
			//setIllTreatmentDetention
			socialinvestigationReportModel.setIllTreatmentDetention(socialinvestigationReport.get(0).getIllTreatmentDetention());
			socialinvestigationReportModel.setOtherIllTreatmentDetention(socialinvestigationReport.get(0).getOtherIllTreatmentDetention());
			socialinvestigationReportModel.setOtherInOtherIllTreatment(socialinvestigationReport.get(0).getOtherInOtherIllTreatment());
			//setExploitationFaced
			socialinvestigationReportModel.setExploitationFaced(socialinvestigationReport.get(0).getExploitationFaced());
			socialinvestigationReportModel.setOtherExploitationFaced(socialinvestigationReport.get(0).getOtherExploitationFaced());
			//setBoughtSoldProcuredTrafficked
			socialinvestigationReportModel.setBoughtSoldProcuredTrafficked(socialinvestigationReport.get(0).isBoughtSoldProcuredTrafficked());
			//setUsedForBegging
			socialinvestigationReportModel.setUsedForBegging(socialinvestigationReport.get(0).isUsedForBegging());
			//setUsedByAnyGang
			socialinvestigationReportModel.setUsedByAnyGang(socialinvestigationReport.get(0).isUsedByAnyGang());
			//setInstitutionDocType
			socialinvestigationReportModel.setInstitutionDocType(typeDetailsMap.get(socialinvestigationReport.get(0).getInstitutionDocType()));
			socialinvestigationReportModel.setPerpetratorName(socialinvestigationReport.get(0).getPerpetratorName());
			socialinvestigationReportModel.setPerpetratorAge(socialinvestigationReport.get(0).getPerpetratorAge());
			socialinvestigationReportModel.setPerpetratorContact(socialinvestigationReport.get(0).getPerpetratorContact());
			socialinvestigationReportModel.setPerpetratorAddress(socialinvestigationReport.get(0).getPerpetratorAddress());
			socialinvestigationReportModel.setPerpetratorPhysicalCharacteristics(socialinvestigationReport.get(0).getPerpetratorPhysicalCharacteristics());
			socialinvestigationReportModel.setPerpetratorRelnWithFamily(socialinvestigationReport.get(0).getPerpetratorRelnWithFamily());
			//setPerpetratorMiddleMenInvolved
			socialinvestigationReportModel.setPerpetratorMiddleMenInvolved(socialinvestigationReport.get(0).isPerpetratorMiddleMenInvolved());
			//setPerpetratorOtherChildAbused
			socialinvestigationReportModel.setPerpetratorOtherChildAbused(socialinvestigationReport.get(0).isPerpetratorOtherChildAbused());
			socialinvestigationReportModel.setPerpetratorHowChildCame(socialinvestigationReport.get(0).getPerpetratorHowChildCame());
			socialinvestigationReportModel.setAttitudeTowardsPerpetrator(socialinvestigationReport.get(0).getAttitudeTowardsPerpetrator());
			//setPoliceInformed
			socialinvestigationReportModel.setPoliceInformed(socialinvestigationReport.get(0).isPoliceInformed());
			socialinvestigationReportModel.setActionAgainstPerpetrator(socialinvestigationReport.get(0).getActionAgainstPerpetrator());
			socialinvestigationReportModel.setAnyOtherRemark(socialinvestigationReport.get(0).getAnyOtherRemark());
			
			socialinvestigationReportModel.setOoiEmotionalFactors(socialinvestigationReport.get(0).getOoiEmotionalFactors());
			socialinvestigationReportModel.setOoiPhysicalCondition(socialinvestigationReport.get(0).getOoiPhysicalCondition());
			socialinvestigationReportModel.setOoiIntelligence(socialinvestigationReport.get(0).getOoiIntelligence());
			socialinvestigationReportModel.setOoiSocialEconomicFactors(socialinvestigationReport.get(0).getOoiSocialEconomicFactors());
			socialinvestigationReportModel.setOoiSuggestiveCausesProblems(socialinvestigationReport.get(0).getOoiSuggestiveCausesProblems());
			socialinvestigationReportModel.setOoiAnalysisOfCase(socialinvestigationReport.get(0).getOoiAnalysisOfCase());
			socialinvestigationReportModel.setOoiReasonsForCareProtection(socialinvestigationReport.get(0).getOoiReasonsForCareProtection());
			socialinvestigationReportModel.setOoiOpinionExperts(socialinvestigationReport.get(0).getOoiOpinionExperts());
			socialinvestigationReportModel.setOoiPsychoSocialAssessment(socialinvestigationReport.get(0).getOoiPsychoSocialAssessment());
			socialinvestigationReportModel.setOoiReligiousFactors(socialinvestigationReport.get(0).getOoiReligiousFactors());
			socialinvestigationReportModel.setOoiRiskAnalysis(socialinvestigationReport.get(0).getOoiRiskAnalysis());
			socialinvestigationReportModel.setOoiRecommendation(socialinvestigationReport.get(0).getOoiRecommendation());
			socialinvestigationReportModel.setBankAccountNo(socialinvestigationReport.get(0).getBankAccountno()==null?null:socialinvestigationReport.get(0).getBankAccountno());
			socialinvestigationReportModel.setAccountholdername(socialinvestigationReport.get(0).getAccountholdername());
			socialinvestigationReportModel.setBranch(socialinvestigationReport.get(0).getBranch());
			socialinvestigationReportModel.setIfsccode(socialinvestigationReport.get(0).getIfsccode());
			socialinvestigationReportModel.setBankname(socialinvestigationReport.get(0).getBankname());
			
			
			List<SocialInvestigationReportFamilyDetails> socialInvestigationReportFamilyDetails = socialInvestigationReportFamilyDetailsRepository.findByRefId(socialinvestigationReport.get(0).getId());
			List<SocialInvestigationReportFamilyDetailsModel> socialInvestigationReportFamilyDetailsModels = new ArrayList<SocialInvestigationReportFamilyDetailsModel>();
			
			for(SocialInvestigationReportFamilyDetails socialInvestigationReportFamilyDetail : socialInvestigationReportFamilyDetails){
				SocialInvestigationReportFamilyDetailsModel socialInvestigationReportFamilyDetailsModel = new SocialInvestigationReportFamilyDetailsModel();
				socialInvestigationReportFamilyDetailsModel.setName(socialInvestigationReportFamilyDetail.getName());
				socialInvestigationReportFamilyDetailsModel.setAge(socialInvestigationReportFamilyDetail.getAge());
				socialInvestigationReportFamilyDetailsModel.setRelationship(socialInvestigationReportFamilyDetail.getRelationship());
				socialInvestigationReportFamilyDetailsModel.setSex(typeDetailsMap.get(socialInvestigationReportFamilyDetail.getSex()));
				socialInvestigationReportFamilyDetailsModel.setEducation(typeDetailsMap.get(socialInvestigationReportFamilyDetail.getEducation()));
				socialInvestigationReportFamilyDetailsModel.setOccupation(socialInvestigationReportFamilyDetail.getOccupation());
				socialInvestigationReportFamilyDetailsModel.setIncome(socialInvestigationReportFamilyDetail.getIncome());
				socialInvestigationReportFamilyDetailsModel.setHealthStatus(socialInvestigationReportFamilyDetail.getHealthStatus());
				socialInvestigationReportFamilyDetailsModel.setHistoryOfMentalIllness(socialInvestigationReportFamilyDetail.getHistoryOfMentalIllness());
				socialInvestigationReportFamilyDetailsModel.setAddictions(socialInvestigationReportFamilyDetail.getAddictions());
				
				socialInvestigationReportFamilyDetailsModels.add(socialInvestigationReportFamilyDetailsModel);
			}
			socialinvestigationReportModel.setSocialInvestigationReportFamilyDetailsModel(socialInvestigationReportFamilyDetailsModels);
			
			return socialinvestigationReportModel;
		}
		return null;
	}

}
