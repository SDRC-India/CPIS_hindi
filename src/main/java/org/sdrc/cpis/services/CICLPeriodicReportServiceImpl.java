package org.sdrc.cpis.services;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CICLPeriodicReport;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.models.CICLPeriodicReportModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLPeriodicReportRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CICLPeriodicReportServiceImpl implements CICLPeriodicReportService 
{

	@Autowired
	CICLPeriodicReportRepository ciclPeriodicReportRepository;
	 
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private StateManager stateManager;
	
	ChildDetails child;
	
	
	public Map<Integer, ValueObject> getTypeMap(){
		List<CCTSTypeDetails> typeDetails=  cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails)
		{
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(cctsTypeDetails.getTypeDetailsNameHindi()!=null?cctsTypeDetails.getTypeDetailsNameHindi():cctsTypeDetails.getTypeDetailsName());
			
			map.put(cctsTypeDetails.getTypeDetailsId(),obj);
		}
			return map;
		}
	
	
	// insert
	
	@Transactional
	@Override
	public String saveCICLPeriodicReportData(CICLPeriodicReportModel ciclPeriodicReportModel) {

		if(ciclPeriodicReportModel != null)
		{
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			CICLPeriodicReport ciclPeriodicReport = new CICLPeriodicReport();
			
            ChildDetails childDetails = childDetailsRepository.findChildById(ciclPeriodicReportModel.getChildId());
			
            ciclPeriodicReport.setChildId(childDetails== null ? null : childDetails);
			ciclPeriodicReport.setFirNumber(ciclPeriodicReportModel.getFirNumber()== null ? null :ciclPeriodicReportModel.getFirNumber());
			ciclPeriodicReport.setPoliceStation(ciclPeriodicReportModel.getPoliceStation()== null ? null :ciclPeriodicReportModel.getPoliceStation());
			ciclPeriodicReport.setSections(ciclPeriodicReportModel.getSections()== null ? null :ciclPeriodicReportModel.getSections());
			ciclPeriodicReport.setMatter(ciclPeriodicReportModel.getMatter()== null ? null :ciclPeriodicReportModel.getMatter());
			ciclPeriodicReport.setVs(ciclPeriodicReportModel.getVs()== null ? null :ciclPeriodicReportModel.getVs());
			ciclPeriodicReport.setChildName(ciclPeriodicReportModel.getChildName()== null ? null :ciclPeriodicReportModel.getChildName());
			ciclPeriodicReport.setAge(ciclPeriodicReportModel.getAge()== null ? null :ciclPeriodicReportModel.getAge());
			ciclPeriodicReport.setEntryDate(ciclPeriodicReportModel.getEntryDate()== null ? null :new java.sql.Date(ciclPeriodicReportModel.getEntryDate().getTime()));
			ciclPeriodicReport.setChildUnderCare(ciclPeriodicReportModel.getChildUnderCare()== null ? null :ciclPeriodicReportModel.getChildUnderCare().getId());
			ciclPeriodicReport.setChildUnderSupervision(ciclPeriodicReportModel.getChildUnderSupervision()== null ? null :ciclPeriodicReportModel.getChildUnderSupervision());
			ciclPeriodicReport.setRegNo(ciclPeriodicReportModel.getRegNo()== null ? null :ciclPeriodicReportModel.getRegNo());
			ciclPeriodicReport.setSex(ciclPeriodicReportModel.getSexObject()== null ? null :ciclPeriodicReportModel.getSexObject().getId());
			ciclPeriodicReport.setFatherName(ciclPeriodicReportModel.getFatherName()== null ? null :ciclPeriodicReportModel.getFatherName());
			ciclPeriodicReport.setReligionObject(ciclPeriodicReportModel.getReligionObject().getId()== null ? null :ciclPeriodicReportModel.getReligionObject().getId());
			ciclPeriodicReport.setCasteObject(ciclPeriodicReportModel.getCasteObject()== null ? null :ciclPeriodicReportModel.getCasteObject().getId());
			ciclPeriodicReport.setCasteOtherType(ciclPeriodicReportModel.getCasteOtherType()== null ? null :ciclPeriodicReportModel.getCasteOtherType());
			ciclPeriodicReport.setEducation(ciclPeriodicReportModel.getEducationObject().getId()== null ? null :ciclPeriodicReportModel.getEducationObject().getId());
			ciclPeriodicReport.setVocationalTraining(ciclPeriodicReportModel.getVocationalTraining()== null ? null :ciclPeriodicReportModel.getVocationalTraining());
			ciclPeriodicReport.setLanguages(ciclPeriodicReportModel.getLanguageObject().getId()== null ? null :ciclPeriodicReportModel.getLanguageObject().getId());
			ciclPeriodicReport.setOtherLanguage(ciclPeriodicReportModel.getOtherLanguage()== null ? null :ciclPeriodicReportModel.getOtherLanguage());
			ciclPeriodicReport.setNextCourtDate(ciclPeriodicReportModel.getNextCourtDate()== null ? null :(new Date(ciclPeriodicReportModel.getNextCourtDate().getTime())));
			ciclPeriodicReport.setEmploymentDetails(ciclPeriodicReportModel.getEmploymentDetails()== null ? null :ciclPeriodicReportModel.getEmploymentDetails());
			ciclPeriodicReport.setDateOfAdmission(ciclPeriodicReportModel.getDateOfAdmission()== null ? null :(new Date(ciclPeriodicReportModel.getDateOfAdmission().getTime())));
			ciclPeriodicReport.setCaseDetailsAndSummary(ciclPeriodicReportModel.getCaseDetailsAndSummary()== null ? null :ciclPeriodicReportModel.getCaseDetailsAndSummary());
			ciclPeriodicReport.setVisitDate(ciclPeriodicReportModel.getVisitDate()== null ? null :(new Date(ciclPeriodicReportModel.getVisitDate().getTime())));
			ciclPeriodicReport.setNameOfParentGuardian(ciclPeriodicReportModel.getNameOfParentGuardian()== null ? null :ciclPeriodicReportModel.getNameOfParentGuardian());
			ciclPeriodicReport.setNameOfOtherAdults(ciclPeriodicReportModel.getNameOfOtherAdults()== null ? null :ciclPeriodicReportModel.getNameOfOtherAdults());
			ciclPeriodicReport.setBehaviourOfChild(ciclPeriodicReportModel.getBehaviourOfChild()== null ? null :ciclPeriodicReportModel.getBehaviourOfChild());
			ciclPeriodicReport.setPhysicalAndMentalHealthStatus(ciclPeriodicReportModel.getPhysicalAndMentalHealthStatus()== null ? null :ciclPeriodicReportModel.getPhysicalAndMentalHealthStatus());
			ciclPeriodicReport.setInterpersonalRelationshipChildwithFamily(ciclPeriodicReportModel.getInterpersonalRelationshipChildwithFamily()== null ? null :ciclPeriodicReportModel.getInterpersonalRelationshipChildwithFamily());
			ciclPeriodicReport.setInterpersonalRelationshipChildwithFriends(ciclPeriodicReportModel.getInterpersonalRelationshipChildwithFriends()== null ? null :ciclPeriodicReportModel.getInterpersonalRelationshipChildwithFriends());
			ciclPeriodicReport.setSafetyAndSupervisionInFamily(ciclPeriodicReportModel.getSafetyAndSupervisionInFamily()== null ? null :ciclPeriodicReportModel.getSafetyAndSupervisionInFamily());
			ciclPeriodicReport.setDifficultiesFacedByChild(ciclPeriodicReportModel.getDifficultiesFacedByChild()== null ? null :ciclPeriodicReportModel.getDifficultiesFacedByChild());
			ciclPeriodicReport.setDifficultiesFacedByFamily(ciclPeriodicReportModel.getDifficultiesFacedByFamily()== null ? null :ciclPeriodicReportModel.getDifficultiesFacedByFamily());
			ciclPeriodicReport.setChangesInHousehold(ciclPeriodicReportModel.getChangesInHousehold()== null ? null :ciclPeriodicReportModel.getChangesInHousehold());
			ciclPeriodicReport.setVocationalTrainingByChild(ciclPeriodicReportModel.getVocationalTrainingByChild()== null ? null :ciclPeriodicReportModel.getVocationalTrainingByChild());
			ciclPeriodicReport.setEngagementOfChildInAntiSocialActivities(ciclPeriodicReportModel.getEngagementOfChildInAntiSocialActivities()== null ? null :ciclPeriodicReportModel.getEngagementOfChildInAntiSocialActivities());
			ciclPeriodicReport.setTimeElapsed(ciclPeriodicReportModel.getTimeElapsed()== null ? null :ciclPeriodicReportModel.getTimeElapsed());
			ciclPeriodicReport.setNameOfSchoolOrCenter(ciclPeriodicReportModel.getNameOfSchoolOrCenter()== null ? null :ciclPeriodicReportModel.getNameOfSchoolOrCenter());
			ciclPeriodicReport.setNameOFTeacherOrPrincipal(ciclPeriodicReportModel.getNameOFTeacherOrPrincipal()== null ? null :ciclPeriodicReportModel.getNameOFTeacherOrPrincipal());
			ciclPeriodicReport.setUnusualBehaviour(ciclPeriodicReportModel.getUnusualBehaviour()== null ? null :ciclPeriodicReportModel.getUnusualBehaviour());
			ciclPeriodicReport.setFeedbackRecived(ciclPeriodicReportModel.getFeedbackRecived()== null ? null :ciclPeriodicReportModel.getFeedbackRecived());
			ciclPeriodicReport.setAttitudeOfPeersToChild(ciclPeriodicReportModel.getAttitudeOfPeersToChild()== null ? null :ciclPeriodicReportModel.getAttitudeOfPeersToChild());
			ciclPeriodicReport.setAttitudeOFChildToPeers(ciclPeriodicReportModel.getAttitudeOFChildToPeers()== null ? null :ciclPeriodicReportModel.getAttitudeOFChildToPeers());
			ciclPeriodicReport.setNatureOfWork(ciclPeriodicReportModel.getNatureOfWork()== null ? null :ciclPeriodicReportModel.getNatureOfWork());
			ciclPeriodicReport.setWorkingHours(ciclPeriodicReportModel.getWorkingHours()== null ? null :ciclPeriodicReportModel.getWorkingHours());
			ciclPeriodicReport.setAttitudeOfChildToWork(ciclPeriodicReportModel.getAttitudeOfChildToWork()== null ? null :ciclPeriodicReportModel.getAttitudeOfChildToWork());
			ciclPeriodicReport.setViolationOfLabourLawsAndActionTaken(ciclPeriodicReportModel.getViolationOfLabourLawsAndActionTaken()== null ? null :ciclPeriodicReportModel.getViolationOfLabourLawsAndActionTaken());
			ciclPeriodicReport.setSpentTimeSpeakingPrivately(ciclPeriodicReportModel.isSpentTimeSpeakingPrivately());
			ciclPeriodicReport.setDetailsSpentTimeSpeakingPrivately(ciclPeriodicReportModel.getDetailsSpentTimeSpeakingPrivately()== null ? null :ciclPeriodicReportModel.getDetailsSpentTimeSpeakingPrivately());
			ciclPeriodicReport.setProgressMadeAsRehabilition(ciclPeriodicReportModel.getProgressMadeAsRehabilition()== null ? null :ciclPeriodicReportModel.getProgressMadeAsRehabilition());
			ciclPeriodicReport.setPreparedBy(ciclPeriodicReportModel.getPreparedBy()== null ? null :ciclPeriodicReportModel.getPreparedBy());
			ciclPeriodicReport.setRecommendation(ciclPeriodicReportModel.getRecommendation()== null ? null :ciclPeriodicReportModel.getRecommendation());
			ciclPeriodicReport.setRehabilitionDate(ciclPeriodicReportModel.getRehabilitionDate()== null ? null :(new Date(ciclPeriodicReportModel.getRehabilitionDate().getTime())));
			ciclPeriodicReport.setPlanDateOfNextVisit(ciclPeriodicReportModel.getPlanDateOfNextVisit()== null ? null :(new Date(ciclPeriodicReportModel.getPlanDateOfNextVisit().getTime())));
			ciclPeriodicReport.setActionPoint(ciclPeriodicReportModel.getActionPoint()== null ? null :ciclPeriodicReportModel.getActionPoint());
			
			ciclPeriodicReport.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			ciclPeriodicReport.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
			ciclPeriodicReport.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			
			ciclPeriodicReportRepository.save(ciclPeriodicReport);
			
		}
		
		return "saved";
	}

	
	// view
	@Override
	public CICLPeriodicReportModel getCiclPeriodicReportModel(String childId) {
		
		CICLPeriodicReport ciclPeriodicReport = ciclPeriodicReportRepository.findByChildIdChildId(childId);
		
		CICLPeriodicReportModel ciclPeriodicReportModel = new CICLPeriodicReportModel();
		
		
		
		if(ciclPeriodicReport != null)
		{
			ciclPeriodicReportModel.setId(ciclPeriodicReport.getId());
			ciclPeriodicReportModel.setChildId(ciclPeriodicReport.getChildId().getChildId());
			ciclPeriodicReportModel.setFirNumber(ciclPeriodicReport.getFirNumber());
			ciclPeriodicReportModel.setPoliceStation(ciclPeriodicReport.getPoliceStation());
			ciclPeriodicReportModel.setSections(ciclPeriodicReport.getSections());
			ciclPeriodicReportModel.setMatter(ciclPeriodicReport.getMatter());
			ciclPeriodicReportModel.setVs(ciclPeriodicReport.getVs());
			ciclPeriodicReportModel.setChildName(ciclPeriodicReport.getChildName());
			ciclPeriodicReportModel.setAge(ciclPeriodicReport.getAge());
			ciclPeriodicReportModel.setEntryDate(ciclPeriodicReport.getEntryDate());
			ciclPeriodicReportModel.setChildUnderCare(getTypeMap().get(ciclPeriodicReport.getChildUnderCare()));
			ciclPeriodicReportModel.setChildUnderSupervision(ciclPeriodicReport.getChildUnderSupervision());
			ciclPeriodicReportModel.setRegNo(ciclPeriodicReport.getRegNo());
			ciclPeriodicReportModel.setSexObject(getTypeMap().get(ciclPeriodicReport.getSex()));
			ciclPeriodicReportModel.setFatherName(ciclPeriodicReport.getFatherName());
			ciclPeriodicReportModel.setReligionObject(getTypeMap().get(ciclPeriodicReport.getReligionObject()));
			ciclPeriodicReportModel.setCasteObject(getTypeMap().get(ciclPeriodicReport.getCasteObject()));
			ciclPeriodicReportModel.setCasteOtherType(ciclPeriodicReport.getCasteOtherType());
			ciclPeriodicReportModel.setEducationObject(getTypeMap().get(ciclPeriodicReport.getEducation()));
			ciclPeriodicReportModel.setVocationalTraining(ciclPeriodicReport.getVocationalTraining());
			ciclPeriodicReportModel.setLanguageObject(getTypeMap().get(ciclPeriodicReport.getLanguages()));
			ciclPeriodicReportModel.setOtherLanguage(ciclPeriodicReport.getOtherLanguage() );
			ciclPeriodicReportModel.setNextCourtDate(ciclPeriodicReport.getNextCourtDate());
			ciclPeriodicReportModel.setEmploymentDetails(ciclPeriodicReport.getEmploymentDetails());
			ciclPeriodicReportModel.setDateOfAdmission(ciclPeriodicReport.getDateOfAdmission());
			ciclPeriodicReportModel.setCaseDetailsAndSummary(ciclPeriodicReport.getCaseDetailsAndSummary());
			ciclPeriodicReportModel.setVisitDate(ciclPeriodicReport.getVisitDate());
			ciclPeriodicReportModel.setNameOfParentGuardian(ciclPeriodicReport.getNameOfParentGuardian());
			ciclPeriodicReportModel.setNameOfOtherAdults(ciclPeriodicReport.getNameOfOtherAdults());
			ciclPeriodicReportModel.setBehaviourOfChild(ciclPeriodicReport.getBehaviourOfChild());
			ciclPeriodicReportModel.setPhysicalAndMentalHealthStatus(ciclPeriodicReport.getPhysicalAndMentalHealthStatus());
			ciclPeriodicReportModel.setInterpersonalRelationshipChildwithFamily(ciclPeriodicReport.getInterpersonalRelationshipChildwithFamily());
			ciclPeriodicReportModel.setInterpersonalRelationshipChildwithFriends(ciclPeriodicReport.getInterpersonalRelationshipChildwithFriends());
			ciclPeriodicReportModel.setSafetyAndSupervisionInFamily(ciclPeriodicReport.getSafetyAndSupervisionInFamily());
			ciclPeriodicReportModel.setDifficultiesFacedByChild(ciclPeriodicReport.getDifficultiesFacedByChild());
			ciclPeriodicReportModel.setDifficultiesFacedByFamily(ciclPeriodicReport.getDifficultiesFacedByFamily());
			ciclPeriodicReportModel.setChangesInHousehold(ciclPeriodicReport.getChangesInHousehold());
			ciclPeriodicReportModel.setVocationalTrainingByChild(ciclPeriodicReport.getVocationalTrainingByChild());
			ciclPeriodicReportModel.setEngagementOfChildInAntiSocialActivities(ciclPeriodicReport.getEngagementOfChildInAntiSocialActivities());
			ciclPeriodicReportModel.setTimeElapsed(ciclPeriodicReport.getTimeElapsed());
			ciclPeriodicReportModel.setNameOfSchoolOrCenter(ciclPeriodicReport.getNameOfSchoolOrCenter());
			ciclPeriodicReportModel.setNameOFTeacherOrPrincipal(ciclPeriodicReport.getNameOFTeacherOrPrincipal());
			ciclPeriodicReportModel.setUnusualBehaviour(ciclPeriodicReport.getUnusualBehaviour());
			ciclPeriodicReportModel.setFeedbackRecived(ciclPeriodicReport.getFeedbackRecived());
			ciclPeriodicReportModel.setAttitudeOfPeersToChild(ciclPeriodicReport.getAttitudeOfPeersToChild());
			ciclPeriodicReportModel.setAttitudeOFChildToPeers(ciclPeriodicReport.getAttitudeOFChildToPeers());
			ciclPeriodicReportModel.setNatureOfWork(ciclPeriodicReport.getNatureOfWork());
			ciclPeriodicReportModel.setWorkingHours(ciclPeriodicReport.getWorkingHours());
			ciclPeriodicReportModel.setAttitudeOfChildToWork(ciclPeriodicReport.getAttitudeOfChildToWork());
			ciclPeriodicReportModel.setViolationOfLabourLawsAndActionTaken(ciclPeriodicReport.getViolationOfLabourLawsAndActionTaken());
			ciclPeriodicReportModel.setSpentTimeSpeakingPrivately(ciclPeriodicReport.isSpentTimeSpeakingPrivately());
			ciclPeriodicReportModel.setDetailsSpentTimeSpeakingPrivately(ciclPeriodicReport.getDetailsSpentTimeSpeakingPrivately());
			ciclPeriodicReportModel.setRecommendation(ciclPeriodicReport.getRecommendation());
			ciclPeriodicReportModel.setProgressMadeAsRehabilition(ciclPeriodicReport.getProgressMadeAsRehabilition());
			ciclPeriodicReportModel.setPreparedBy(ciclPeriodicReport.getPreparedBy());
			ciclPeriodicReportModel.setRehabilitionDate(ciclPeriodicReport.getRehabilitionDate());
			ciclPeriodicReportModel.setPlanDateOfNextVisit(ciclPeriodicReport.getPlanDateOfNextVisit());
			ciclPeriodicReportModel.setActionPoint(ciclPeriodicReport.getActionPoint());
		
		
		}
		
		
		return ciclPeriodicReport != null?ciclPeriodicReportModel:null;
	}

}
