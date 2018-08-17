package org.sdrc.cpis.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.FollowUpForm;
import org.sdrc.cpis.models.FollowUpFormModel;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.FollowUpRepository;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowUpFormServiceImpl implements FollowUpFormService {

	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private FollowUpRepository followUpRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	public Map<Integer, ValueObject> getTypeMap(
			List<CCTSTypeDetails> typeDetails) {
		Map<Integer, ValueObject> map = new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj = new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(cctsTypeDetails.getTypeDetailsNameHindi());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}

	
	@Transactional
	@Override
	public void save(FollowUpFormModel followUpFormModel) throws Exception {
		// TODO Auto-generated method stub
		FollowUpForm followUpForm = new FollowUpForm();
		
		followUpForm.setAdhaarCardNo(followUpFormModel.getAdhaarCardNo());
		followUpForm.setAvailabilityOfChild(followUpFormModel.getAvailabilityOfChild());
		followUpForm.setAvailableItems(followUpFormModel.getAvailableItems());
		followUpForm.setBeatenByParents(followUpFormModel.getBeatenByParents());
		followUpForm.setBeatenByParentsFrequency(followUpFormModel.getBeatenByParentsFrequency());
		followUpForm.setBehaviourOfNeighbour(followUpFormModel.getBehaviourOfNeighbour());
		followUpForm.setBodyMassIndex(followUpFormModel.getBodyMassIndex());
		followUpForm.setChildDoHouseholdChores(followUpFormModel.getChildDoHouseholdChores());
		followUpForm.setChildGoneWhere(followUpFormModel.getChildGoneWhere());
		followUpForm.setChildHeight(followUpFormModel.getChildHeight());
		followUpForm.setChildId(childDetailsRepository.findChildById(followUpFormModel.getChildId()));
		followUpForm.setChildLook(followUpFormModel.getChildLook()!=null?followUpFormModel.getChildLook().getId():null);
		followUpForm.setChildPerformance(followUpFormModel.getChildPerformance());
		followUpForm.setChildPhoto(exportPDFServiceImpl.getPhotoPath(followUpFormModel.getChildPhoto(),followUpFormModel.getChildId(),"followUpForm"));
		followUpForm.setChildsBehaviour(followUpFormModel.getChildsBehaviour());
		followUpForm.setChildShareProblems(followUpFormModel.getChildShareProblems());
		followUpForm.setChildWeight(followUpFormModel.getChildWeight());
		followUpForm.setClassOfStudy(followUpFormModel.getClassOfStudy());
		followUpForm.setComplianceByGovt(followUpFormModel.getComplianceByGovt());
		followUpForm.setCourseName(followUpFormModel.getCourseName());
		followUpForm.setCourseStatus(followUpFormModel.getCourseStatus()!=null?followUpFormModel.getCourseStatus().getId():null);
		followUpForm.setDateOfAdmission(followUpFormModel.getDateOfAdmission());
		followUpForm.setDateOfBirth(followUpFormModel.getDateOfBirth());
		followUpForm.setDateOfRestoration(followUpFormModel.getDateOfRestoration());
		followUpForm.setDateOfVisit(followUpFormModel.getDateOfVisit());
		followUpForm.setFatherAdhaarCardNo(followUpFormModel.getFatherAdhaarCardNo());
		followUpForm.setFatherName(followUpFormModel.getFatherName());
		followUpForm.setHeadingForFacilitation(followUpFormModel.getHeadingForFacilitation());
		followUpForm.setHealthCardProvided(followUpFormModel.getHealthCardProvided());
		followUpForm.setHealthCardProvidedReason(followUpFormModel.getHealthCardProvidedReason());
		followUpForm.setHealthRemark(followUpFormModel.getHealthRemark());
		followUpForm.setHospitalized(followUpFormModel.getHospitalized());
		followUpForm.setIllness(followUpFormModel.getIllness());
		followUpForm.setIllnessStatusReason(followUpFormModel.getIllnessStatusReason());
		followUpForm.setInstituteName(followUpFormModel.getInstituteName());
		followUpForm.setIntellectiveStatus(followUpFormModel.getIntellectiveStatus()!=null?followUpFormModel.getIntellectiveStatus().getId():null);
		followUpForm.setIsHospitalizedReason(followUpFormModel.getIsHospitalizedReason());
		followUpForm.setKeyPoints(followUpFormModel.getKeyPoints());
		followUpForm.setKeyRemarks(followUpFormModel.getKeyRemarks());
		followUpForm.setMotherAdhaarCardNo(followUpFormModel.getMotherAdhaarCardNo());
		followUpForm.setMotherName(followUpFormModel.getMotherName());
		followUpForm.setParentLandlineNo(followUpFormModel.getParentLandlineNo());
		followUpForm.setParentMobileNo(followUpFormModel.getParentMobileNo());
		followUpForm.setParentPermanentAddress(followUpFormModel.getParentPermanentAddress());
		followUpForm.setParentsBehaviour(followUpFormModel.getParentsBehaviour()!=null?followUpFormModel.getParentsBehaviour().getId():null);
		followUpForm.setParentTemporaryAddress(followUpFormModel.getParentTemporaryAddress());
		followUpForm.setPeriodicFollowUp(followUpFormModel.getPeriodicFollowUp()!=null?followUpFormModel.getPeriodicFollowUp().getId():null);
		followUpForm.setProblemShareTime(followUpFormModel.getProblemShareTime());
		followUpForm.setRemarks(followUpFormModel.getRemarks());
		followUpForm.setRemarksOfTeacher(followUpFormModel.getRemarksOfTeacher());
		followUpForm.setRollNo(followUpFormModel.getRollNo());
		followUpForm.setRoutineCheckUp(followUpFormModel.getRoutineCheckUp());
		followUpForm.setRoutineCheckupReason(followUpFormModel.getRoutineCheckupReason());
		followUpForm.setSchemeName(followUpFormModel.getSchemeName());
		followUpForm.setSchoolAddress(followUpFormModel.getSchoolAddress());
		followUpForm.setSchoolAttended(followUpFormModel.getChildEnrolled()!=null?followUpFormModel.getChildEnrolled():false);
		followUpForm.setSchoolType(followUpFormModel.getSchoolType()!=null?followUpFormModel.getSchoolType().getId():null);
		followUpForm.setSexuallyAbused(followUpFormModel.getSexuallyAbused());
		followUpForm.setSkillDeveloped(followUpFormModel.getSkillDeveloped());
		followUpForm.setTeacherName(followUpFormModel.getTeacherName());
		followUpForm.setTimeSpentWithParents(followUpFormModel.getTimeSpentWithParents());
		followUpForm.setTypeOfWork(followUpFormModel.getTypeOfWork());
		followUpForm.setTypeOfRestoration(followUpFormModel.getRestorationType()!=null?followUpFormModel.getRestorationType().getId():null);
		followUpForm.setVocationalProgress(followUpFormModel.getVocationalProgress()!=null?followUpFormModel.getVocationalProgress().getId():null);
		followUpForm.setVocationalProgressStatus(followUpFormModel.getVocationalProgressStatus());
		followUpForm.setTypeOfFacilitation(followUpFormModel.getTypeOfFacilitation());
		followUpForm.setOtherComplianceByGovt(followUpFormModel.getOtherComplianceByGovt());
		
		followUpRepository.save(followUpForm);
	}
	
	@Override
	public List<FollowUpFormModel> getFollowUpForm(String childId) throws Exception {
		// TODO Auto-generated method stub
		List<CCTSTypeDetails> typeDetails = cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> typeMap = getTypeMap(typeDetails);
		List<FollowUpForm> followUpForms = followUpRepository.findByChildIdChildId(childId);
		List<FollowUpFormModel> followUpFormModels = new ArrayList<FollowUpFormModel>();
		
		for (FollowUpForm followUpForm : followUpForms) {
		FollowUpFormModel followUpFormModel = new FollowUpFormModel();
		
		followUpFormModel.setAdhaarCardNo(followUpForm.getAdhaarCardNo());
		followUpFormModel.setAvailabilityOfChild(followUpForm.getAvailabilityOfChild());
		followUpFormModel.setAvailableItems(followUpForm.getAvailableItems());
		followUpFormModel.setBeatenByParents(followUpForm.getBeatenByParents());
		followUpFormModel.setBeatenByParentsFrequency(followUpForm.getBeatenByParentsFrequency());
		followUpFormModel.setBehaviourOfNeighbour(followUpForm.getBehaviourOfNeighbour());
		followUpFormModel.setChildDoHouseholdChores(followUpForm.getChildDoHouseholdChores());
		followUpFormModel.setChildGoneWhere(followUpForm.getChildGoneWhere());
		followUpFormModel.setChildHeight(followUpForm.getChildHeight());
		followUpFormModel.setChildId(followUpForm.getChildId().getChildId());
		followUpFormModel.setChildLook(followUpForm.getChildLook()!=null?typeMap.get(followUpForm.getChildLook()):null);
		followUpFormModel.setChildPerformance(followUpForm.getChildPerformance());
		followUpFormModel.setChildPhoto(exportPDFServiceImpl.getChildPhoto(followUpForm.getChildPhoto()));
//		followUpFormModel.setChildPhotoPath(followUpForm.getChildPhoto());
		followUpFormModel.setChildsBehaviour(followUpForm.getChildsBehaviour());
		followUpFormModel.setChildShareProblems(followUpForm.getChildShareProblems());
		followUpFormModel.setChildWeight(followUpForm.getChildWeight());
		followUpFormModel.setClassOfStudy(followUpForm.getClassOfStudy());
		followUpFormModel.setComplianceByGovt(followUpForm.getComplianceByGovt());
		followUpFormModel.setCourseName(followUpForm.getCourseName());
		followUpFormModel.setCourseStatus(followUpForm.getCourseStatus()!=null?typeMap.get(followUpForm.getCourseStatus()):null);
		followUpFormModel.setDateOfAdmission(followUpForm.getDateOfAdmission());
		followUpFormModel.setDateOfBirth(followUpForm.getDateOfBirth());
		followUpFormModel.setDateOfRestoration(followUpForm.getDateOfRestoration());
		followUpFormModel.setDateOfVisit(followUpForm.getDateOfVisit());
		followUpFormModel.setFatherAdhaarCardNo(followUpForm.getFatherAdhaarCardNo());
		followUpFormModel.setFatherName(followUpForm.getFatherName());
		followUpFormModel.setHeadingForFacilitation(followUpForm.getHeadingForFacilitation());
		followUpFormModel.setHealthCardProvided(followUpForm.getHealthCardProvided());
		followUpFormModel.setHealthCardProvidedReason(followUpForm.getHealthCardProvidedReason());
		followUpFormModel.setHealthRemark(followUpForm.getHealthRemark());
		followUpFormModel.setHospitalized(followUpForm.getHospitalized());
		followUpFormModel.setIllness(followUpForm.getIllness());
		followUpFormModel.setIllnessStatusReason(followUpForm.getIllnessStatusReason());
		followUpFormModel.setInstituteName(followUpForm.getInstituteName());
		followUpFormModel.setIntellectiveStatus(followUpForm.getIntellectiveStatus()!=null?typeMap.get(followUpForm.getIntellectiveStatus()):null);
		followUpFormModel.setIsHospitalizedReason(followUpForm.getIsHospitalizedReason());
		followUpFormModel.setKeyPoints(followUpForm.getKeyPoints());
		followUpFormModel.setKeyRemarks(followUpForm.getKeyRemarks());
		followUpFormModel.setMotherAdhaarCardNo(followUpForm.getMotherAdhaarCardNo());
		followUpFormModel.setMotherName(followUpForm.getMotherName());
		followUpFormModel.setParentLandlineNo(followUpForm.getParentLandlineNo());
		followUpFormModel.setParentMobileNo(followUpForm.getParentMobileNo());
		followUpFormModel.setParentPermanentAddress(followUpForm.getParentPermanentAddress());
		followUpFormModel.setParentsBehaviour(typeMap.get(followUpForm.getParentsBehaviour()));
		followUpFormModel.setParentTemporaryAddress(followUpForm.getParentTemporaryAddress());
		followUpFormModel.setPeriodicFollowUp(followUpForm.getPeriodicFollowUp()!=null?typeMap.get(followUpForm.getPeriodicFollowUp()):null);
		followUpFormModel.setProblemShareTime(followUpForm.getProblemShareTime());
		followUpFormModel.setRemarks(followUpForm.getRemarks());
		followUpFormModel.setRemarksOfTeacher(followUpForm.getRemarksOfTeacher());
		followUpFormModel.setRollNo(followUpForm.getRollNo());
		followUpFormModel.setRoutineCheckUp(followUpForm.getRoutineCheckUp());
		followUpFormModel.setRoutineCheckupReason(followUpForm.getRoutineCheckupReason());
		followUpFormModel.setSchemeName(followUpForm.getSchemeName());
		followUpFormModel.setSchoolAddress(followUpForm.getSchoolAddress());
		followUpFormModel.setChildEnrolled(followUpForm.getSchoolAttended()!=null?followUpForm.getSchoolAttended():false);
		followUpFormModel.setSchoolType(followUpForm.getSchoolType()!=null?typeMap.get(followUpForm.getSchoolType()):null);
		followUpFormModel.setSexuallyAbused(followUpForm.getSexuallyAbused());
		followUpFormModel.setSkillDeveloped(followUpForm.getSkillDeveloped());
		followUpFormModel.setTeacherName(followUpForm.getTeacherName());
		followUpFormModel.setTimeSpentWithParents(followUpForm.getTimeSpentWithParents());
		followUpFormModel.setTypeOfWork(followUpForm.getTypeOfWork());
		followUpFormModel.setRestorationType(followUpForm.getTypeOfRestoration()!=null?typeMap.get(followUpForm.getTypeOfRestoration()):null);
		followUpFormModel.setVocationalProgress(followUpForm.getVocationalProgress()!=null?typeMap.get(followUpForm.getVocationalProgress()):null);
		followUpFormModel.setVocationalProgressStatus(followUpForm.getVocationalProgressStatus());
		followUpFormModel.setTypeOfFacilitation(followUpForm.getTypeOfFacilitation());
		followUpFormModel.setOtherComplianceByGovt(followUpForm.getOtherComplianceByGovt());
		followUpFormModels.add(followUpFormModel);
		}
		return followUpFormModels;
		
	}

}
