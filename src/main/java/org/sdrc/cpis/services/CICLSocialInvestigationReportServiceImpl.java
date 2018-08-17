package org.sdrc.cpis.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.CICLSocialInvestigationReport;
import org.sdrc.cpis.domains.CICLsocialInvestigationReportFamilyDetails;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.models.CICLSocialInvestigationReportFamilyDetailsModel;
import org.sdrc.cpis.models.CICLSocialInvestigationReportModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.CICLSocialInvestigationReportFamilyDetailsRepository;
import org.sdrc.cpis.repository.CICLSocialInvestigationReportRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class CICLSocialInvestigationReportServiceImpl implements
		CICLSocialInvestigationReportService {

	@Autowired
	CICLSocialInvestigationReportRepository cICLSocialInvestigationReportRepository; 
	
	@Autowired
	CICLSocialInvestigationReportFamilyDetailsRepository cICLSocialInvestigationReportFamilyDetailsRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private ServletContext servletContext;
	
	
private Integer generateChildId(){
		
		Integer lastChildId = 0;
		lastChildId = childDetailsRepository.findLastRecord();
		if(lastChildId == null){lastChildId = 1;}
		else{lastChildId = lastChildId + 1; }
		
		return lastChildId;
	}
	
	@Override
	public void saveCICLSocialInvestigationReport(
			CICLSocialInvestigationReportModel ciclSocialinvestigationReportModel,
			List<CICLSocialInvestigationReportFamilyDetailsModel> ciclSocialInvestigationReportFamilyDetailsModels) throws Exception {
		
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		
		CICLSocialInvestigationReport ciclSocialInvestigationReport = new CICLSocialInvestigationReport();
		
		ciclSocialInvestigationReport.setChildId(childDetailsRepository.findChildById(ciclSocialinvestigationReportModel.getChildId()));
		ciclSocialInvestigationReport.setSlNo(null == ciclSocialinvestigationReportModel.getSlNo()?null:ciclSocialinvestigationReportModel.getSlNo());
		ciclSocialInvestigationReport.setJjbAddress(null == ciclSocialinvestigationReportModel.getJjbAddress()?null:ciclSocialinvestigationReportModel.getJjbAddress());
		ciclSocialInvestigationReport.setCiclOrgType(null == ciclSocialinvestigationReportModel.getCiclOrgType()?null:ciclSocialinvestigationReportModel.getCiclOrgType().getId());
		ciclSocialInvestigationReport.setNameOfPerson(null == ciclSocialinvestigationReportModel.getNameOfPerson()?null:ciclSocialinvestigationReportModel.getNameOfPerson());
		ciclSocialInvestigationReport.setFirNumber(null == ciclSocialinvestigationReportModel.getFirNumber()?null:ciclSocialinvestigationReportModel.getFirNumber());
		ciclSocialInvestigationReport.setGdNumber(null == ciclSocialinvestigationReportModel.getGdNumber()?null:ciclSocialinvestigationReportModel.getGdNumber());
		ciclSocialInvestigationReport.setUnderSections(null == ciclSocialinvestigationReportModel.getUnderSections()?null:ciclSocialinvestigationReportModel.getUnderSections());
		ciclSocialInvestigationReport.setPoliceStation(null == ciclSocialinvestigationReportModel.getPoliceStation()?null:ciclSocialinvestigationReportModel.getPoliceStation());
		ciclSocialInvestigationReport.setNatureOfOffence(null == ciclSocialinvestigationReportModel.getNatureOfOffenceAlleged()?null:ciclSocialinvestigationReportModel.getNatureOfOffenceAlleged().getId());
		ciclSocialInvestigationReport.setChildName(null==ciclSocialinvestigationReportModel.getChildName()?null:ciclSocialinvestigationReportModel.getChildName());
		ciclSocialInvestigationReport.setChildAge(null==ciclSocialinvestigationReportModel.getChildAge()?null:ciclSocialinvestigationReportModel.getChildAge());
		ciclSocialInvestigationReport.setChildSex(null==ciclSocialinvestigationReportModel.getChildSex()?null:ciclSocialinvestigationReportModel.getChildSex().getId());
		ciclSocialInvestigationReport.setChildCast(null==ciclSocialinvestigationReportModel.getChildCast()?null:ciclSocialinvestigationReportModel.getChildCast().getId());
		ciclSocialInvestigationReport.setChildReligion(null==ciclSocialinvestigationReportModel.getChildReligion()?null:ciclSocialinvestigationReportModel.getChildReligion().getId());
		ciclSocialInvestigationReport.setOtherChildReligion(null==ciclSocialinvestigationReportModel.getOtherChildReligion()?null:ciclSocialinvestigationReportModel.getOtherChildReligion());
		ciclSocialInvestigationReport.setFatherName(null==ciclSocialinvestigationReportModel.getFatherName()?null:ciclSocialinvestigationReportModel.getFatherName());
		ciclSocialInvestigationReport.setMotherName(null==ciclSocialinvestigationReportModel.getMotherName()?null:ciclSocialinvestigationReportModel.getMotherName());
		
		ciclSocialInvestigationReport.setGuardianName(null == ciclSocialinvestigationReportModel.getGuardianName()?null:ciclSocialinvestigationReportModel.getGuardianName());
		ciclSocialInvestigationReport.setPermanantAddress(null == ciclSocialinvestigationReportModel.getPermanantAddress()?null:ciclSocialinvestigationReportModel.getPermanantAddress());
		ciclSocialInvestigationReport.setLandmark(null == ciclSocialinvestigationReportModel.getLandmark()?null:ciclSocialinvestigationReportModel.getLandmark());
		ciclSocialInvestigationReport.setLastResidenceAddress(null == ciclSocialinvestigationReportModel.getLastResidenceAddress()?null:ciclSocialinvestigationReportModel.getLastResidenceAddress());
		ciclSocialInvestigationReport.setFamilyMemberContactNo(null==ciclSocialinvestigationReportModel.getFamilyMemberContactNo()?null:ciclSocialinvestigationReportModel.getFamilyMemberContactNo());
		ciclSocialInvestigationReport.setChildDifferentlyAbled(ciclSocialinvestigationReportModel.isChildDifferentlyAbled());
		ciclSocialInvestigationReport.setDifferentlyAbledType(null==ciclSocialinvestigationReportModel.getDifferentlyAbledType()?null:ciclSocialinvestigationReportModel.getDifferentlyAbledType());
		ciclSocialInvestigationReport.setOtherDifferentlyAbled(null==ciclSocialinvestigationReportModel.getDifferentlyAbledType()?null:ciclSocialinvestigationReportModel.getOtherDifferentlyAbled());
		ciclSocialInvestigationReport.setMentalIllSeverity(null==ciclSocialinvestigationReportModel.getMentalIllSeverity()?null:ciclSocialinvestigationReportModel.getMentalIllSeverity());
		ciclSocialInvestigationReport.setMentalRetireSeverity(null==ciclSocialinvestigationReportModel.getMentalRetireSeverity()?null:ciclSocialinvestigationReportModel.getMentalRetireSeverity());
		
		ciclSocialInvestigationReport.setRelnFatherMother(null == ciclSocialinvestigationReportModel.getRelnFatherMother()?null:ciclSocialinvestigationReportModel.getRelnFatherMother().getId());
		ciclSocialInvestigationReport.setRelnFatherChild(null == ciclSocialinvestigationReportModel.getRelnFatherChild()?null:ciclSocialinvestigationReportModel.getRelnFatherChild().getId());
		ciclSocialInvestigationReport.setRelnMotherChild(null == ciclSocialinvestigationReportModel.getRelnMotherChild()?null:ciclSocialinvestigationReportModel.getRelnMotherChild().getId());
		ciclSocialInvestigationReport.setRelnFatherSiblings(null == ciclSocialinvestigationReportModel.getRelnFatherSiblings()?null:ciclSocialinvestigationReportModel.getRelnFatherSiblings().getId());
		ciclSocialInvestigationReport.setRelnMotherSiblings(null == ciclSocialinvestigationReportModel.getRelnMotherSiblings()?null:ciclSocialinvestigationReportModel.getRelnMotherSiblings().getId());
		ciclSocialInvestigationReport.setRelnChildSiblings(null == ciclSocialinvestigationReportModel.getRelnChildSiblings()?null:ciclSocialinvestigationReportModel.getRelnChildSiblings().getId());
		ciclSocialInvestigationReport.setRelnChildGrandParent(null == ciclSocialinvestigationReportModel.getRelnChildGrandParent()?null:ciclSocialinvestigationReportModel.getRelnChildGrandParent().getId());
		
		ciclSocialInvestigationReport.setChildMarried(ciclSocialinvestigationReportModel.isChildMarried());
		ciclSocialInvestigationReport.setSpouseName(null==ciclSocialinvestigationReportModel.getSpouseName()?null:ciclSocialinvestigationReportModel.getSpouseName());
		ciclSocialInvestigationReport.setSpouseAge(null==ciclSocialinvestigationReportModel.getSpouseAge()?null:ciclSocialinvestigationReportModel.getSpouseAge());
		ciclSocialInvestigationReport.setSpouseDetails(null==ciclSocialinvestigationReportModel.getSpouseDetails()?null:ciclSocialinvestigationReportModel.getSpouseDetails());
		ciclSocialInvestigationReport.setChildrenName1(null==ciclSocialinvestigationReportModel.getChildrenName1()?null:ciclSocialinvestigationReportModel.getChildrenName1());
		ciclSocialInvestigationReport.setChildrenAge1(null==ciclSocialinvestigationReportModel.getChildrenAge1()?null:ciclSocialinvestigationReportModel.getChildrenAge1());
		ciclSocialInvestigationReport.setChildrenSex1(null==ciclSocialinvestigationReportModel.getChildrenSex1()?null:ciclSocialinvestigationReportModel.getChildrenSex1().getId());
		ciclSocialInvestigationReport.setChildrenName2(null==ciclSocialinvestigationReportModel.getChildrenName2()?null:ciclSocialinvestigationReportModel.getChildrenName2());
		ciclSocialInvestigationReport.setChildrenAge2(null==ciclSocialinvestigationReportModel.getChildrenAge2()?null:ciclSocialinvestigationReportModel.getChildrenAge2());
		ciclSocialInvestigationReport.setChildrenSex2(null==ciclSocialinvestigationReportModel.getChildrenSex2()?null:ciclSocialinvestigationReportModel.getChildrenSex2().getId());
		
		ciclSocialInvestigationReport.setHoiFathernoc(null==ciclSocialinvestigationReportModel.getHoiFathernoc()?null:ciclSocialinvestigationReportModel.getHoiFathernoc());
		ciclSocialInvestigationReport.setHoiFatherLs(null==ciclSocialinvestigationReportModel.getHoiFatherLs()?null:ciclSocialinvestigationReportModel.getHoiFatherLs());
		ciclSocialInvestigationReport.setHoiFatherAr(ciclSocialinvestigationReportModel.isHoiFatherAr());
		ciclSocialInvestigationReport.setHoiFatherPoc(null==ciclSocialinvestigationReportModel.getHoiFatherPoc()?null:ciclSocialinvestigationReportModel.getHoiFatherPoc());
		ciclSocialInvestigationReport.setHoiFatherPa(null==ciclSocialinvestigationReportModel.getHoiFatherPa()?null:ciclSocialinvestigationReportModel.getHoiFatherPa());
		
		ciclSocialInvestigationReport.setHoiStepFathernoc(null==ciclSocialinvestigationReportModel.getHoiStepFathernoc()?null:ciclSocialinvestigationReportModel.getHoiStepFathernoc());
		ciclSocialInvestigationReport.setHoiStepFatherLs(null==ciclSocialinvestigationReportModel.getHoiStepFatherLs()?null:ciclSocialinvestigationReportModel.getHoiStepFatherLs());
		ciclSocialInvestigationReport.setHoiStepFatherAr(ciclSocialinvestigationReportModel.isHoiStepFatherAr());
		ciclSocialInvestigationReport.setHoiStepFatherPoc(null==ciclSocialinvestigationReportModel.getHoiStepFatherPoc()?null:ciclSocialinvestigationReportModel.getHoiStepFatherPoc());
		ciclSocialInvestigationReport.setHoiStepFatherPa(null==ciclSocialinvestigationReportModel.getHoiStepFatherPa()?null:ciclSocialinvestigationReportModel.getHoiStepFatherPa());
		
		ciclSocialInvestigationReport.setHoiMothernoc(null==ciclSocialinvestigationReportModel.getHoiMothernoc()?null:ciclSocialinvestigationReportModel.getHoiMothernoc());
		ciclSocialInvestigationReport.setHoiMotherLs(null==ciclSocialinvestigationReportModel.getHoiMotherLs()?null:ciclSocialinvestigationReportModel.getHoiMotherLs());
		ciclSocialInvestigationReport.setHoiMotherAr(ciclSocialinvestigationReportModel.isHoiMotherAr());
		ciclSocialInvestigationReport.setHoiMotherPoc(null==ciclSocialinvestigationReportModel.getHoiMotherPoc()?null:ciclSocialinvestigationReportModel.getHoiMotherPoc());
		ciclSocialInvestigationReport.setHoiMotherPa(null==ciclSocialinvestigationReportModel.getHoiMotherPa()?null:ciclSocialinvestigationReportModel.getHoiMotherPa());
		
		ciclSocialInvestigationReport.setHoiStepMothernoc(null==ciclSocialinvestigationReportModel.getHoiStepMothernoc()?null:ciclSocialinvestigationReportModel.getHoiStepMothernoc());
		ciclSocialInvestigationReport.setHoiStepMotherLs(null==ciclSocialinvestigationReportModel.getHoiStepMotherLs()?null:ciclSocialinvestigationReportModel.getHoiStepMotherLs());
		ciclSocialInvestigationReport.setHoiStepMotherAr(ciclSocialinvestigationReportModel.isHoiStepMotherAr());
		ciclSocialInvestigationReport.setHoiStepMotherPoc(null==ciclSocialinvestigationReportModel.getHoiStepMotherPoc()?null:ciclSocialinvestigationReportModel.getHoiStepMotherPoc());
		ciclSocialInvestigationReport.setHoiStepMotherPa(null==ciclSocialinvestigationReportModel.getHoiStepMotherPa()?null:ciclSocialinvestigationReportModel.getHoiStepMotherPa());
		
		ciclSocialInvestigationReport.setHoiBrothernoc(null==ciclSocialinvestigationReportModel.getHoiBrothernoc()?null:ciclSocialinvestigationReportModel.getHoiBrothernoc());
		ciclSocialInvestigationReport.setHoiBrotherLs(null==ciclSocialinvestigationReportModel.getHoiBrotherLs()?null:ciclSocialinvestigationReportModel.getHoiBrotherLs());
		ciclSocialInvestigationReport.setHoiBrotherAr(ciclSocialinvestigationReportModel.isHoiBrotherAr());
		ciclSocialInvestigationReport.setHoiBrotherPoc(null==ciclSocialinvestigationReportModel.getHoiBrotherPoc()?null:ciclSocialinvestigationReportModel.getHoiBrotherPoc());
		ciclSocialInvestigationReport.setHoiBrotherPa(null==ciclSocialinvestigationReportModel.getHoiBrotherPa()?null:ciclSocialinvestigationReportModel.getHoiBrotherPa());
		
		ciclSocialInvestigationReport.setHoiSisternoc(null==ciclSocialinvestigationReportModel.getHoiSisternoc()?null:ciclSocialinvestigationReportModel.getHoiSisternoc());
		ciclSocialInvestigationReport.setHoiSisterLs(null==ciclSocialinvestigationReportModel.getHoiSisterLs()?null:ciclSocialinvestigationReportModel.getHoiSisterLs());
		ciclSocialInvestigationReport.setHoiSisterAr(ciclSocialinvestigationReportModel.isHoiSisterAr());
		ciclSocialInvestigationReport.setHoiSisterPoc(null==ciclSocialinvestigationReportModel.getHoiSisterPoc()?null:ciclSocialinvestigationReportModel.getHoiSisterPoc());
		ciclSocialInvestigationReport.setHoiSisterPa(null==ciclSocialinvestigationReportModel.getHoiSisterPa()?null:ciclSocialinvestigationReportModel.getHoiSisterPa());
		
		ciclSocialInvestigationReport.setHoiOtherFamilyMemberName(null==ciclSocialinvestigationReportModel.getHoiOtherFamilyMemberName()?null:ciclSocialinvestigationReportModel.getHoiOtherFamilyMemberName());
		ciclSocialInvestigationReport.setHoiOthersnoc(null==ciclSocialinvestigationReportModel.getHoiOthersnoc()?null:ciclSocialinvestigationReportModel.getHoiOthersnoc());
		ciclSocialInvestigationReport.setHoiOthersLs(null==ciclSocialinvestigationReportModel.getHoiOthersLs()?null:ciclSocialinvestigationReportModel.getHoiOthersLs());
		ciclSocialInvestigationReport.setHoiOthersAr(ciclSocialinvestigationReportModel.isHoiOthersAr());
		ciclSocialInvestigationReport.setHoiOthersPoc(null==ciclSocialinvestigationReportModel.getHoiOthersPoc()?null:ciclSocialinvestigationReportModel.getHoiOthersPoc());
		ciclSocialInvestigationReport.setHoiOthersPa(null==ciclSocialinvestigationReportModel.getHoiOthersPa()?null:ciclSocialinvestigationReportModel.getHoiOthersPa());
		
		ciclSocialInvestigationReport.setReligionAttitude(null==ciclSocialinvestigationReportModel.getReligionAttitude()?null:ciclSocialinvestigationReportModel.getReligionAttitude());
		ciclSocialInvestigationReport.setLivingConditions(null==ciclSocialinvestigationReportModel.getLivingConditions()?null:ciclSocialinvestigationReportModel.getLivingConditions());
		ciclSocialInvestigationReport.setOtherFactorImportance(null==ciclSocialinvestigationReportModel.getOtherFactorImportance()?null:ciclSocialinvestigationReportModel.getOtherFactorImportance());
		ciclSocialInvestigationReport.setGoodHabits(null==ciclSocialinvestigationReportModel.getGoodHabits()?null:ciclSocialinvestigationReportModel.getGoodHabits());
		ciclSocialInvestigationReport.setOtherGoodHabits(null==ciclSocialinvestigationReportModel.getOtherGoodHabits()?null:ciclSocialinvestigationReportModel.getOtherGoodHabits());
		ciclSocialInvestigationReport.setBadHabits(null==ciclSocialinvestigationReportModel.getBadHabits()?null:ciclSocialinvestigationReportModel.getBadHabits());
		ciclSocialInvestigationReport.setDrugType(null==ciclSocialinvestigationReportModel.getDrugType()?null:ciclSocialinvestigationReportModel.getDrugType());
		ciclSocialInvestigationReport.setOtherBadHabits(null==ciclSocialinvestigationReportModel.getOtherBadHabits()?null:ciclSocialinvestigationReportModel.getOtherBadHabits());
		ciclSocialInvestigationReport.setExtracurricularInterests(null==ciclSocialinvestigationReportModel.getExtracurricularInterests()?null:ciclSocialinvestigationReportModel.getExtracurricularInterests());
		ciclSocialInvestigationReport.setPersonalityTraits(null==ciclSocialinvestigationReportModel.getPersonalityTraits()?null:ciclSocialinvestigationReportModel.getPersonalityTraits());
		
		ciclSocialInvestigationReport.setChildOpinionTowardsDiscipline(null==ciclSocialinvestigationReportModel.getChildOpinionTowardsDiscipline()?null:ciclSocialinvestigationReportModel.getChildOpinionTowardsDiscipline());
		ciclSocialInvestigationReport.setEmploymentDetails(null==ciclSocialinvestigationReportModel.getEmploymentDetails()?null:ciclSocialinvestigationReportModel.getEmploymentDetails());
		ciclSocialInvestigationReport.setIncomeUtilization(null==ciclSocialinvestigationReportModel.getIncomeUtilization()?null:ciclSocialinvestigationReportModel.getIncomeUtilization());
		ciclSocialInvestigationReport.setWorkRecord(null==ciclSocialinvestigationReportModel.getWorkRecord()?null:ciclSocialinvestigationReportModel.getWorkRecord());
		ciclSocialInvestigationReport.setEducation(null==ciclSocialinvestigationReportModel.getEducation()?null:ciclSocialinvestigationReportModel.getEducation().getId());
		ciclSocialInvestigationReport.setClassMatesAttitude(null==ciclSocialinvestigationReportModel.getClassMatesAttitude()?null:ciclSocialinvestigationReportModel.getClassMatesAttitude());
		ciclSocialInvestigationReport.setTeachersAttitude(null==ciclSocialinvestigationReportModel.getTeachersAttitude()?null:ciclSocialinvestigationReportModel.getTeachersAttitude());
		ciclSocialInvestigationReport.setReasonLeavingSchool(null==ciclSocialinvestigationReportModel.getReasonLeavingSchool()?null:ciclSocialinvestigationReportModel.getReasonLeavingSchool());
		ciclSocialInvestigationReport.setOtherReasonLeavingSchool(null==ciclSocialinvestigationReportModel.getOtherReasonLeavingSchool()?null:ciclSocialinvestigationReportModel.getOtherReasonLeavingSchool());
		ciclSocialInvestigationReport.setSchoolTypeStudiedLast(null==ciclSocialinvestigationReportModel.getSchoolType()?null:ciclSocialinvestigationReportModel.getSchoolType().getId());
		ciclSocialInvestigationReport.setVocationalTraining(null==ciclSocialinvestigationReportModel.getVocationalTraining()?null:ciclSocialinvestigationReportModel.getVocationalTraining());
		ciclSocialInvestigationReport.setMajorityFriendTypes(null==ciclSocialinvestigationReportModel.getMajorityFriendTypes()?null:ciclSocialinvestigationReportModel.getMajorityFriendTypes());
		ciclSocialInvestigationReport.setAttitudeTowardsFriends(null==ciclSocialinvestigationReportModel.getAttitudeTowardsFriends()?null:ciclSocialinvestigationReportModel.getAttitudeTowardsFriends());
		ciclSocialInvestigationReport.setFriendsAttitudeTowardsChild(null==ciclSocialinvestigationReportModel.getFriendsAttitudeTowardsChild()?null:ciclSocialinvestigationReportModel.getFriendsAttitudeTowardsChild());
		ciclSocialInvestigationReport.setObservationAboutNeighbourhood(null==ciclSocialinvestigationReportModel.getObservationAboutNeighbourhood()?null:ciclSocialinvestigationReportModel.getObservationAboutNeighbourhood());
		ciclSocialInvestigationReport.setObservationAboutNeighbourhoodToAsses(null==ciclSocialinvestigationReportModel.getObservationAboutNeighbourhoodToAsses()?null:ciclSocialinvestigationReportModel.getObservationAboutNeighbourhoodToAsses());
		ciclSocialInvestigationReport.setChildSubjectedOfAbuse(ciclSocialinvestigationReportModel.isChildSubjectedOfAbuse());
		
		ciclSocialInvestigationReport.setVerbalAbuse(null==ciclSocialinvestigationReportModel.getVerbalAbuse()?null:ciclSocialinvestigationReportModel.getVerbalAbuse());
		ciclSocialInvestigationReport.setOtherVerbalAbuse(null==ciclSocialinvestigationReportModel.getOtherVerbalAbuse()?null:ciclSocialinvestigationReportModel.getOtherVerbalAbuse());
		ciclSocialInvestigationReport.setPhysicalAbuse(null==ciclSocialinvestigationReportModel.getPhysicalAbuse()?null:ciclSocialinvestigationReportModel.getPhysicalAbuse());
		ciclSocialInvestigationReport.setOtherPhysicalAbuse(null==ciclSocialinvestigationReportModel.getOtherPhysicalAbuse()?null:ciclSocialinvestigationReportModel.getOtherPhysicalAbuse());
		ciclSocialInvestigationReport.setSexualAbuse(null==ciclSocialinvestigationReportModel.getSexualAbuse()?null:ciclSocialinvestigationReportModel.getSexualAbuse());
		ciclSocialInvestigationReport.setOtherSexualAbuse(null==ciclSocialinvestigationReportModel.getOtherSexualAbuse()?null:ciclSocialinvestigationReportModel.getOtherSexualAbuse());
		ciclSocialInvestigationReport.setOtherInOtherAbuse(null==ciclSocialinvestigationReportModel.getOtherInOtherAbuse()?null:ciclSocialinvestigationReportModel.getOtherInOtherAbuse());
		
		ciclSocialInvestigationReport.setChildVictim(ciclSocialinvestigationReportModel.isChildVictim());
		ciclSocialInvestigationReport.setUsedByAnyGang(ciclSocialinvestigationReportModel.isUsedByAnyGang());
		ciclSocialInvestigationReport.setHistoryRunAwayFromHome(null==ciclSocialinvestigationReportModel.getHistoryRunAwayFromHome()?null:ciclSocialinvestigationReportModel.getHistoryRunAwayFromHome());
		ciclSocialInvestigationReport.setCircumstancesOfApprehension(null==ciclSocialinvestigationReportModel.getCircumstancesOfApprehension()?null:ciclSocialinvestigationReportModel.getCircumstancesOfApprehension());
		ciclSocialInvestigationReport.setAllegedRoleInOffence(null==ciclSocialinvestigationReportModel.getAllegedRoleInOffence()?null:ciclSocialinvestigationReportModel.getAllegedRoleInOffence());
		ciclSocialInvestigationReport.setReasonForAllegedOffence(null==ciclSocialinvestigationReportModel.getReasonForAllegedOffence()?null:ciclSocialinvestigationReportModel.getReasonForAllegedOffence());
		ciclSocialInvestigationReport.setOtherReasonForAllegedOffence(null==ciclSocialinvestigationReportModel.getOtherReasonForAllegedOffence()?null:ciclSocialinvestigationReportModel.getOtherReasonForAllegedOffence());
		ciclSocialInvestigationReport.setApprehendedForOffence(ciclSocialinvestigationReportModel.isApprehendedForOffence());
		ciclSocialInvestigationReport.setApprehendedForOffenceDtls(null==ciclSocialinvestigationReportModel.getApprehendedForOffenceDtls()?null:ciclSocialinvestigationReportModel.getApprehendedForOffenceDtls());
		ciclSocialInvestigationReport.setInstitutionDocType(null==ciclSocialinvestigationReportModel.getInstitutionDocType()?null:ciclSocialinvestigationReportModel.getInstitutionDocType().getId());
		ciclSocialInvestigationReport.setPhysicalAppearance(null==ciclSocialinvestigationReportModel.getPhysicalAppearance()?null:ciclSocialinvestigationReportModel.getPhysicalAppearance());
		ciclSocialInvestigationReport.setHealthCondition(null==ciclSocialinvestigationReportModel.getHealthCondition()?null:ciclSocialinvestigationReportModel.getHealthCondition());
		ciclSocialInvestigationReport.setMentalCondition(null==ciclSocialinvestigationReportModel.getMentalCondition()?null:ciclSocialinvestigationReportModel.getMentalCondition());
		ciclSocialInvestigationReport.setAnyOtherRemark(null==ciclSocialinvestigationReportModel.getAnyOtherRemark()?null:ciclSocialinvestigationReportModel.getAnyOtherRemark());
		
		ciclSocialInvestigationReport.setRoiEmotionalFactors(null==ciclSocialinvestigationReportModel.getRoiEmotionalFactors()?null:ciclSocialinvestigationReportModel.getRoiEmotionalFactors());
		ciclSocialInvestigationReport.setRoiPhysicalCondition(null==ciclSocialinvestigationReportModel.getRoiPhysicalCondition()?null:ciclSocialinvestigationReportModel.getRoiPhysicalCondition());
		ciclSocialInvestigationReport.setRoiIntelligence(null==ciclSocialinvestigationReportModel.getRoiIntelligence()?null:ciclSocialinvestigationReportModel.getRoiIntelligence());
		ciclSocialInvestigationReport.setRoiSocialEconomicFactors(null==ciclSocialinvestigationReportModel.getRoiSocialEconomicFactors()?null:ciclSocialinvestigationReportModel.getRoiSocialEconomicFactors());
		ciclSocialInvestigationReport.setRoiSuggestiveCausesProblems(null==ciclSocialinvestigationReportModel.getRoiSuggestiveCausesProblems()?null:ciclSocialinvestigationReportModel.getRoiSuggestiveCausesProblems());
		ciclSocialInvestigationReport.setRoiAnalysisOfCase(null==ciclSocialinvestigationReportModel.getRoiAnalysisOfCase()?null:ciclSocialinvestigationReportModel.getRoiAnalysisOfCase());
		ciclSocialInvestigationReport.setRoiReasonsForCareProtection(null==ciclSocialinvestigationReportModel.getRoiReasonsForCareProtection()?null:ciclSocialinvestigationReportModel.getRoiReasonsForCareProtection());
		ciclSocialInvestigationReport.setRoiOpinionExperts(null==ciclSocialinvestigationReportModel.getRoiOpinionExperts()?null:ciclSocialinvestigationReportModel.getRoiOpinionExperts());
		ciclSocialInvestigationReport.setRoiRecommendation(null==ciclSocialinvestigationReportModel.getRoiRecommendation()?null:ciclSocialinvestigationReportModel.getRoiRecommendation());
		
		ciclSocialInvestigationReport.setUpdatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		ciclSocialInvestigationReport.setUpdatedDate(new java.sql.Date(new Date().getTime()));
		ciclSocialInvestigationReport.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		ciclSocialInvestigationReport.setBankAccountNo(ciclSocialinvestigationReportModel.getBankAccountNo()==null ? null : ciclSocialinvestigationReportModel.getBankAccountNo());
		ciclSocialInvestigationReport.setAccountholdername(null==ciclSocialinvestigationReportModel.getAccountholdername()?null:ciclSocialinvestigationReportModel.getAccountholdername());
		ciclSocialInvestigationReport.setBankname(null==ciclSocialinvestigationReportModel.getBankname()?null:ciclSocialinvestigationReportModel.getBankname());
		ciclSocialInvestigationReport.setIfsccode(null==ciclSocialinvestigationReportModel.getIfsccode()?null:ciclSocialinvestigationReportModel.getIfsccode());
		ciclSocialInvestigationReport.setBranch(null==ciclSocialinvestigationReportModel.getBranch()?null:ciclSocialinvestigationReportModel.getBranch());
		ciclSocialInvestigationReport.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		ciclSocialInvestigationReport.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());

		AreaDetails areadetails = areaRepository.fetchAreaById(userDetailModel.getAreaId());
		String childId =String.valueOf(generateChildId()) + "-" + areadetails.getAreaName()+ "-" + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		ciclSocialInvestigationReport.setChildPhoto(getPhotoPath(ciclSocialinvestigationReportModel.getChildPhoto(),childId,"childRegistration"));
		
		ciclSocialInvestigationReport = cICLSocialInvestigationReportRepository.save(ciclSocialInvestigationReport);
		
		ChildDetails childDetails = childDetailsRepository.findChildById(ciclSocialinvestigationReportModel.getChildId());
		
		childDetails.setId(childDetails.getId());
		childDetails.setSirFatherName(null==ciclSocialinvestigationReportModel.getFatherName()?null:ciclSocialinvestigationReportModel.getFatherName());
		childDetails.setSirMotherName(null==ciclSocialinvestigationReportModel.getMotherName()?null:ciclSocialinvestigationReportModel.getMotherName());
		childDetails.setSirChildAddress(null == ciclSocialinvestigationReportModel.getPermanantAddress()?null:ciclSocialinvestigationReportModel.getPermanantAddress());
		childDetails.setSirChildCast(null==ciclSocialinvestigationReportModel.getChildCast()?null:ciclSocialinvestigationReportModel.getChildCast().getId());
		childDetails.setSirChildReligion(null==ciclSocialinvestigationReportModel.getChildReligion()?null:ciclSocialinvestigationReportModel.getChildReligion().getId());
		childDetails.setSirOtherChildReligion(null==ciclSocialinvestigationReportModel.getOtherChildReligion()?null:ciclSocialinvestigationReportModel.getOtherChildReligion());
		childDetails.setSirFilled(1);
		
		childDetailsRepository.save(childDetails);
		System.out.println(childDetails.getId());
		
		List<CICLsocialInvestigationReportFamilyDetails> cICLSocialInvestigationReportFamilyDetails = new ArrayList<CICLsocialInvestigationReportFamilyDetails>();
		
		for(CICLSocialInvestigationReportFamilyDetailsModel ciclSocialInvestigationReportFamilyDetailsModel : ciclSocialInvestigationReportFamilyDetailsModels){
			
			CICLsocialInvestigationReportFamilyDetails cicLsocialInvestigationReportFamilyDetail = new CICLsocialInvestigationReportFamilyDetails();
			
			cicLsocialInvestigationReportFamilyDetail.setName(null==ciclSocialInvestigationReportFamilyDetailsModel.getName()?null:ciclSocialInvestigationReportFamilyDetailsModel.getName());
			cicLsocialInvestigationReportFamilyDetail.setRelationship(null==ciclSocialInvestigationReportFamilyDetailsModel.getRelationship()?null:ciclSocialInvestigationReportFamilyDetailsModel.getRelationship());
			cicLsocialInvestigationReportFamilyDetail.setAge(null == ciclSocialInvestigationReportFamilyDetailsModel.getAge()?null:ciclSocialInvestigationReportFamilyDetailsModel.getAge());
			cicLsocialInvestigationReportFamilyDetail.setSex(null==ciclSocialInvestigationReportFamilyDetailsModel.getSex()?null:ciclSocialInvestigationReportFamilyDetailsModel.getSex().getId());
			cicLsocialInvestigationReportFamilyDetail.setEducation(null==ciclSocialInvestigationReportFamilyDetailsModel.getEducation()?null:ciclSocialInvestigationReportFamilyDetailsModel.getEducation().getId());
			cicLsocialInvestigationReportFamilyDetail.setOccupation(null==ciclSocialInvestigationReportFamilyDetailsModel.getOccupation()?null:ciclSocialInvestigationReportFamilyDetailsModel.getOccupation());
			cicLsocialInvestigationReportFamilyDetail.setIncome(null==ciclSocialInvestigationReportFamilyDetailsModel.getIncome()?null:ciclSocialInvestigationReportFamilyDetailsModel.getIncome());
			cicLsocialInvestigationReportFamilyDetail.setHealthStatus(null==ciclSocialInvestigationReportFamilyDetailsModel.getHealthStatus()?null:ciclSocialInvestigationReportFamilyDetailsModel.getHealthStatus());
			cicLsocialInvestigationReportFamilyDetail.setHistoryOfMentalIllness(null==ciclSocialInvestigationReportFamilyDetailsModel.getHistoryOfMentalIllness()?null:ciclSocialInvestigationReportFamilyDetailsModel.getHistoryOfMentalIllness());
			cicLsocialInvestigationReportFamilyDetail.setAddictions(null==ciclSocialInvestigationReportFamilyDetailsModel.getAddictions()?null:ciclSocialInvestigationReportFamilyDetailsModel.getAddictions());
			
			cicLsocialInvestigationReportFamilyDetail.setcICLSocialInvestigationReport(ciclSocialInvestigationReport);
			
			cICLSocialInvestigationReportFamilyDetails.add(cicLsocialInvestigationReportFamilyDetail);
		}
		
		cICLSocialInvestigationReportFamilyDetailsRepository.save(cICLSocialInvestigationReportFamilyDetails);
	}
	
	 public String getPhotoPath(String childImage, String childId, String type) throws Exception {

	    	String path = type.equals("childRegistration")?applicationMessageSource.getMessage("store.CICLSIR", null, null,null):applicationMessageSource.getMessage("store.CaseHistory", null, null,null);
			File file = new File(path);
			 String finalPath = null;
			if(!file.exists()){
				file.mkdirs();
			}
			String ext = "."+childImage.substring(childImage.indexOf("/")+1, childImage.indexOf(";"));
			byte[] decodedBytes = Base64.decodeBase64(childImage.split(",")[1]);
			finalPath = path+""+childId+"_"+new SimpleDateFormat("ddMMyyyyHHmmssS").format(new java.util.Date())+ext;
			OutputStream out = new FileOutputStream(finalPath);
			out.write(decodedBytes );
			out.close();
			return finalPath;
		}

	public Map<Integer, ValueObject> getTypeMap(){
		List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			obj.setTypeNameHindi(cctsTypeDetails.getTypeDetailsNameHindi() == null ? 
					null : cctsTypeDetails.getTypeDetailsNameHindi());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
			return map;
		}

	@Override
	public Integer getId(String childId) {
		if(childId != null && !(childId.trim().equals(""))){
			List<CICLSocialInvestigationReport> obj = cICLSocialInvestigationReportRepository.findByChildId(childId);
			if(obj != null && obj.size() > 0){
				return obj.get(0).getId();
			}
		}
		return null;
	}
	
	public String getPath(String childPhoto) throws Exception {
		File filePath = null;
		FileInputStream fileInputStreamReader = null;
		String type = null;
		String result = null;
		if(childPhoto.substring(childPhoto.length()-3).equals("pdf")){
			filePath = new File(childPhoto);
			try {
				fileInputStreamReader = new FileInputStream(filePath);
				type = "data:application/pdf;base64,";
			} catch (Exception e) {
				type = null;
			}
			
		} else{
			try {
				filePath = new File(childPhoto==null?servletContext.getRealPath("/resources/img/default.png"):childPhoto);
				fileInputStreamReader = new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				filePath = new File(servletContext.getRealPath("/resources/img/default.png"));
				fileInputStreamReader = new FileInputStream(filePath);
			}
			type = "data:image/jpeg;base64,";
		}
		
		if(type != null){
			byte[] bytes = new byte[(int)filePath.length()];
			fileInputStreamReader.read(bytes);
		    fileInputStreamReader.close();
		    result = type + DatatypeConverter.printBase64Binary(bytes);
		}
		return result;
	}

	@Override
	public CICLSocialInvestigationReportModel getCICLSocialIvestigationreport(String childId) throws Exception {
		
		List<CICLSocialInvestigationReport> cICLSocialinvestigationReport = cICLSocialInvestigationReportRepository.findByChildId(childId);
		
		Map<Integer, ValueObject> typeDetailsMap = getTypeMap();
		
		CICLSocialInvestigationReportModel ciclSocialInvestigationReportModel = new CICLSocialInvestigationReportModel();
		
		if(cICLSocialinvestigationReport != null && cICLSocialinvestigationReport.size()>0){
			ciclSocialInvestigationReportModel.setSlNo(cICLSocialinvestigationReport.get(0).getSlNo());
			ciclSocialInvestigationReportModel.setJjbAddress(null == cICLSocialinvestigationReport.get(0).getJjbAddress()?null:cICLSocialinvestigationReport.get(0).getJjbAddress());
			ciclSocialInvestigationReportModel.setCiclOrgType(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getCiclOrgType()));
			ciclSocialInvestigationReportModel.setNameOfPerson(null == cICLSocialinvestigationReport.get(0).getNameOfPerson()?null:cICLSocialinvestigationReport.get(0).getNameOfPerson());
			ciclSocialInvestigationReportModel.setFirNumber(null == cICLSocialinvestigationReport.get(0).getFirNumber()?null:cICLSocialinvestigationReport.get(0).getFirNumber());
			ciclSocialInvestigationReportModel.setGdNumber(null == cICLSocialinvestigationReport.get(0).getGdNumber()?null:cICLSocialinvestigationReport.get(0).getGdNumber());
			ciclSocialInvestigationReportModel.setUnderSections(null == cICLSocialinvestigationReport.get(0).getUnderSections()?null:cICLSocialinvestigationReport.get(0).getUnderSections());
			ciclSocialInvestigationReportModel.setPoliceStation(null == cICLSocialinvestigationReport.get(0).getPoliceStation()?null:cICLSocialinvestigationReport.get(0).getPoliceStation());
			ciclSocialInvestigationReportModel.setNatureOfOffenceAlleged(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getNatureOfOffence()));
			ciclSocialInvestigationReportModel.setChildName(null==cICLSocialinvestigationReport.get(0).getChildName()?null:cICLSocialinvestigationReport.get(0).getChildName());
			ciclSocialInvestigationReportModel.setChildAge(null==cICLSocialinvestigationReport.get(0).getChildAge()?null:cICLSocialinvestigationReport.get(0).getChildAge());
			ciclSocialInvestigationReportModel.setChildSex(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getChildSex()));
			ciclSocialInvestigationReportModel.setChildCast(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getChildCast()));
			ciclSocialInvestigationReportModel.setChildReligion(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getChildReligion()));
			ciclSocialInvestigationReportModel.setOtherChildReligion(null==cICLSocialinvestigationReport.get(0).getOtherChildReligion()?null:cICLSocialinvestigationReport.get(0).getOtherChildReligion());
			ciclSocialInvestigationReportModel.setFatherName(null==cICLSocialinvestigationReport.get(0).getFatherName()?null:cICLSocialinvestigationReport.get(0).getFatherName());
			ciclSocialInvestigationReportModel.setMotherName(null==cICLSocialinvestigationReport.get(0).getMotherName()?null:cICLSocialinvestigationReport.get(0).getMotherName());
			
			ciclSocialInvestigationReportModel.setGuardianName(null == cICLSocialinvestigationReport.get(0).getGuardianName()?null:cICLSocialinvestigationReport.get(0).getGuardianName());
			ciclSocialInvestigationReportModel.setPermanantAddress(null == cICLSocialinvestigationReport.get(0).getPermanantAddress()?null:cICLSocialinvestigationReport.get(0).getPermanantAddress());
			ciclSocialInvestigationReportModel.setLandmark(null == cICLSocialinvestigationReport.get(0).getLandmark()?null:cICLSocialinvestigationReport.get(0).getLandmark());
			ciclSocialInvestigationReportModel.setLastResidenceAddress(null == cICLSocialinvestigationReport.get(0).getLastResidenceAddress()?null:cICLSocialinvestigationReport.get(0).getLastResidenceAddress());
			ciclSocialInvestigationReportModel.setFamilyMemberContactNo(null==cICLSocialinvestigationReport.get(0).getFamilyMemberContactNo()?null:cICLSocialinvestigationReport.get(0).getFamilyMemberContactNo());
			ciclSocialInvestigationReportModel.setChildDifferentlyAbled(cICLSocialinvestigationReport.get(0).isChildDifferentlyAbled());
			ciclSocialInvestigationReportModel.setDifferentlyAbledType(null==cICLSocialinvestigationReport.get(0).getDifferentlyAbledType()?null:cICLSocialinvestigationReport.get(0).getDifferentlyAbledType());
			ciclSocialInvestigationReportModel.setOtherDifferentlyAbled(null==cICLSocialinvestigationReport.get(0).getDifferentlyAbledType()?null:cICLSocialinvestigationReport.get(0).getOtherDifferentlyAbled());
			ciclSocialInvestigationReportModel.setMentalIllSeverity(null==cICLSocialinvestigationReport.get(0).getMentalIllSeverity()?null:cICLSocialinvestigationReport.get(0).getMentalIllSeverity());
			ciclSocialInvestigationReportModel.setMentalRetireSeverity(null==cICLSocialinvestigationReport.get(0).getMentalRetireSeverity()?null:cICLSocialinvestigationReport.get(0).getMentalRetireSeverity());
			
			ciclSocialInvestigationReportModel.setRelnFatherMother(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnFatherMother()));
			ciclSocialInvestigationReportModel.setRelnFatherChild(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnFatherChild()));
			ciclSocialInvestigationReportModel.setRelnMotherChild(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnMotherChild()));
			ciclSocialInvestigationReportModel.setRelnFatherSiblings(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnFatherSiblings()));
			ciclSocialInvestigationReportModel.setRelnMotherSiblings(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnMotherSiblings()));
			ciclSocialInvestigationReportModel.setRelnChildSiblings(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnChildSiblings()));
			ciclSocialInvestigationReportModel.setRelnChildGrandParent(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getRelnChildGrandParent()));
			
			ciclSocialInvestigationReportModel.setChildMarried(cICLSocialinvestigationReport.get(0).isChildMarried());
			ciclSocialInvestigationReportModel.setSpouseName(null==cICLSocialinvestigationReport.get(0).getSpouseName()?null:cICLSocialinvestigationReport.get(0).getSpouseName());
			ciclSocialInvestigationReportModel.setSpouseAge(null==cICLSocialinvestigationReport.get(0).getSpouseAge()?null:cICLSocialinvestigationReport.get(0).getSpouseAge());
			ciclSocialInvestigationReportModel.setSpouseDetails(null==cICLSocialinvestigationReport.get(0).getSpouseDetails()?null:cICLSocialinvestigationReport.get(0).getSpouseDetails());
			ciclSocialInvestigationReportModel.setChildrenName1(null==cICLSocialinvestigationReport.get(0).getChildrenName1()?null:cICLSocialinvestigationReport.get(0).getChildrenName1());
			ciclSocialInvestigationReportModel.setChildrenAge1(null==cICLSocialinvestigationReport.get(0).getChildrenAge1()?null:cICLSocialinvestigationReport.get(0).getChildrenAge1());
			ciclSocialInvestigationReportModel.setChildrenSex1(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getChildrenSex1()));
			ciclSocialInvestigationReportModel.setChildrenName2(null==cICLSocialinvestigationReport.get(0).getChildrenName2()?null:cICLSocialinvestigationReport.get(0).getChildrenName2());
			ciclSocialInvestigationReportModel.setChildrenAge2(null==cICLSocialinvestigationReport.get(0).getChildrenAge2()?null:cICLSocialinvestigationReport.get(0).getChildrenAge2());
			ciclSocialInvestigationReportModel.setChildrenSex2(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getChildrenSex2()));
			
			ciclSocialInvestigationReportModel.setHoiFathernoc(null==cICLSocialinvestigationReport.get(0).getHoiFathernoc()?null:cICLSocialinvestigationReport.get(0).getHoiFathernoc());
			ciclSocialInvestigationReportModel.setHoiFatherLs(null==cICLSocialinvestigationReport.get(0).getHoiFatherLs()?null:cICLSocialinvestigationReport.get(0).getHoiFatherLs());
			ciclSocialInvestigationReportModel.setHoiFatherAr(cICLSocialinvestigationReport.get(0).isHoiFatherAr());
			ciclSocialInvestigationReportModel.setHoiFatherPoc(null==cICLSocialinvestigationReport.get(0).getHoiFatherPoc()?null:cICLSocialinvestigationReport.get(0).getHoiFatherPoc());
			ciclSocialInvestigationReportModel.setHoiFatherPa(null==cICLSocialinvestigationReport.get(0).getHoiFatherPa()?null:cICLSocialinvestigationReport.get(0).getHoiFatherPa());
			
			ciclSocialInvestigationReportModel.setHoiStepFathernoc(null==cICLSocialinvestigationReport.get(0).getHoiStepFathernoc()?null:cICLSocialinvestigationReport.get(0).getHoiStepFathernoc());
			ciclSocialInvestigationReportModel.setHoiStepFatherLs(null==cICLSocialinvestigationReport.get(0).getHoiStepFatherLs()?null:cICLSocialinvestigationReport.get(0).getHoiStepFatherLs());
			ciclSocialInvestigationReportModel.setHoiStepFatherAr(cICLSocialinvestigationReport.get(0).isHoiStepFatherAr());
			ciclSocialInvestigationReportModel.setHoiStepFatherPoc(null==cICLSocialinvestigationReport.get(0).getHoiStepFatherPoc()?null:cICLSocialinvestigationReport.get(0).getHoiStepFatherPoc());
			ciclSocialInvestigationReportModel.setHoiStepFatherPa(null==cICLSocialinvestigationReport.get(0).getHoiStepFatherPa()?null:cICLSocialinvestigationReport.get(0).getHoiStepFatherPa());
			
			ciclSocialInvestigationReportModel.setHoiMothernoc(null==cICLSocialinvestigationReport.get(0).getHoiMothernoc()?null:cICLSocialinvestigationReport.get(0).getHoiMothernoc());
			ciclSocialInvestigationReportModel.setHoiMotherLs(null==cICLSocialinvestigationReport.get(0).getHoiMotherLs()?null:cICLSocialinvestigationReport.get(0).getHoiMotherLs());
			ciclSocialInvestigationReportModel.setHoiMotherAr(cICLSocialinvestigationReport.get(0).isHoiMotherAr());
			ciclSocialInvestigationReportModel.setHoiMotherPoc(null==cICLSocialinvestigationReport.get(0).getHoiMotherPoc()?null:cICLSocialinvestigationReport.get(0).getHoiMotherPoc());
			ciclSocialInvestigationReportModel.setHoiMotherPa(null==cICLSocialinvestigationReport.get(0).getHoiMotherPa()?null:cICLSocialinvestigationReport.get(0).getHoiMotherPa());
			
			ciclSocialInvestigationReportModel.setHoiStepMothernoc(null==cICLSocialinvestigationReport.get(0).getHoiStepMothernoc()?null:cICLSocialinvestigationReport.get(0).getHoiStepMothernoc());
			ciclSocialInvestigationReportModel.setHoiStepMotherLs(null==cICLSocialinvestigationReport.get(0).getHoiStepMotherLs()?null:cICLSocialinvestigationReport.get(0).getHoiStepMotherLs());
			ciclSocialInvestigationReportModel.setHoiStepMotherAr(cICLSocialinvestigationReport.get(0).isHoiStepMotherAr());
			ciclSocialInvestigationReportModel.setHoiStepMotherPoc(null==cICLSocialinvestigationReport.get(0).getHoiStepMotherPoc()?null:cICLSocialinvestigationReport.get(0).getHoiStepMotherPoc());
			ciclSocialInvestigationReportModel.setHoiStepMotherPa(null==cICLSocialinvestigationReport.get(0).getHoiStepMotherPa()?null:cICLSocialinvestigationReport.get(0).getHoiStepMotherPa());
			
			ciclSocialInvestigationReportModel.setHoiBrothernoc(null==cICLSocialinvestigationReport.get(0).getHoiBrothernoc()?null:cICLSocialinvestigationReport.get(0).getHoiBrothernoc());
			ciclSocialInvestigationReportModel.setHoiBrotherLs(null==cICLSocialinvestigationReport.get(0).getHoiBrotherLs()?null:cICLSocialinvestigationReport.get(0).getHoiBrotherLs());
			ciclSocialInvestigationReportModel.setHoiBrotherAr(cICLSocialinvestigationReport.get(0).isHoiBrotherAr());
			ciclSocialInvestigationReportModel.setHoiBrotherPoc(null==cICLSocialinvestigationReport.get(0).getHoiBrotherPoc()?null:cICLSocialinvestigationReport.get(0).getHoiBrotherPoc());
			ciclSocialInvestigationReportModel.setHoiBrotherPa(null==cICLSocialinvestigationReport.get(0).getHoiBrotherPa()?null:cICLSocialinvestigationReport.get(0).getHoiBrotherPa());
			
			ciclSocialInvestigationReportModel.setHoiSisternoc(null==cICLSocialinvestigationReport.get(0).getHoiSisternoc()?null:cICLSocialinvestigationReport.get(0).getHoiSisternoc());
			ciclSocialInvestigationReportModel.setHoiSisterLs(null==cICLSocialinvestigationReport.get(0).getHoiSisterLs()?null:cICLSocialinvestigationReport.get(0).getHoiSisterLs());
			ciclSocialInvestigationReportModel.setHoiSisterAr(cICLSocialinvestigationReport.get(0).isHoiSisterAr());
			ciclSocialInvestigationReportModel.setHoiSisterPoc(null==cICLSocialinvestigationReport.get(0).getHoiSisterPoc()?null:cICLSocialinvestigationReport.get(0).getHoiSisterPoc());
			ciclSocialInvestigationReportModel.setHoiSisterPa(null==cICLSocialinvestigationReport.get(0).getHoiSisterPa()?null:cICLSocialinvestigationReport.get(0).getHoiSisterPa());
			
			ciclSocialInvestigationReportModel.setHoiOtherFamilyMemberName(null==cICLSocialinvestigationReport.get(0).getHoiOtherFamilyMemberName()?null:cICLSocialinvestigationReport.get(0).getHoiOtherFamilyMemberName());
			ciclSocialInvestigationReportModel.setHoiOthersnoc(null==cICLSocialinvestigationReport.get(0).getHoiOthersnoc()?null:cICLSocialinvestigationReport.get(0).getHoiOthersnoc());
			ciclSocialInvestigationReportModel.setHoiOthersLs(null==cICLSocialinvestigationReport.get(0).getHoiOthersLs()?null:cICLSocialinvestigationReport.get(0).getHoiOthersLs());
			ciclSocialInvestigationReportModel.setHoiOthersAr(cICLSocialinvestigationReport.get(0).isHoiOthersAr());
			ciclSocialInvestigationReportModel.setHoiOthersPoc(null==cICLSocialinvestigationReport.get(0).getHoiOthersPoc()?null:cICLSocialinvestigationReport.get(0).getHoiOthersPoc());
			ciclSocialInvestigationReportModel.setHoiOthersPa(null==cICLSocialinvestigationReport.get(0).getHoiOthersPa()?null:cICLSocialinvestigationReport.get(0).getHoiOthersPa());
			
			ciclSocialInvestigationReportModel.setReligionAttitude(null==cICLSocialinvestigationReport.get(0).getReligionAttitude()?null:cICLSocialinvestigationReport.get(0).getReligionAttitude());
			ciclSocialInvestigationReportModel.setLivingConditions(null==cICLSocialinvestigationReport.get(0).getLivingConditions()?null:cICLSocialinvestigationReport.get(0).getLivingConditions());
			ciclSocialInvestigationReportModel.setOtherFactorImportance(null==cICLSocialinvestigationReport.get(0).getOtherFactorImportance()?null:cICLSocialinvestigationReport.get(0).getOtherFactorImportance());
			ciclSocialInvestigationReportModel.setGoodHabits(null==cICLSocialinvestigationReport.get(0).getGoodHabits()?null:cICLSocialinvestigationReport.get(0).getGoodHabits());
			ciclSocialInvestigationReportModel.setOtherGoodHabits(null==cICLSocialinvestigationReport.get(0).getOtherGoodHabits()?null:cICLSocialinvestigationReport.get(0).getOtherGoodHabits());
			ciclSocialInvestigationReportModel.setBadHabits(null==cICLSocialinvestigationReport.get(0).getBadHabits()?null:cICLSocialinvestigationReport.get(0).getBadHabits());
			ciclSocialInvestigationReportModel.setDrugType(null==cICLSocialinvestigationReport.get(0).getDrugType()?null:cICLSocialinvestigationReport.get(0).getDrugType());
			ciclSocialInvestigationReportModel.setOtherBadHabits(null==cICLSocialinvestigationReport.get(0).getOtherBadHabits()?null:cICLSocialinvestigationReport.get(0).getOtherBadHabits());
			ciclSocialInvestigationReportModel.setExtracurricularInterests(null==cICLSocialinvestigationReport.get(0).getExtracurricularInterests()?null:cICLSocialinvestigationReport.get(0).getExtracurricularInterests());
			ciclSocialInvestigationReportModel.setPersonalityTraits(null==cICLSocialinvestigationReport.get(0).getPersonalityTraits()?null:cICLSocialinvestigationReport.get(0).getPersonalityTraits());
			
			ciclSocialInvestigationReportModel.setChildOpinionTowardsDiscipline(null==cICLSocialinvestigationReport.get(0).getChildOpinionTowardsDiscipline()?null:cICLSocialinvestigationReport.get(0).getChildOpinionTowardsDiscipline());
			ciclSocialInvestigationReportModel.setEmploymentDetails(null==cICLSocialinvestigationReport.get(0).getEmploymentDetails()?null:cICLSocialinvestigationReport.get(0).getEmploymentDetails());
			ciclSocialInvestigationReportModel.setIncomeUtilization(null==cICLSocialinvestigationReport.get(0).getIncomeUtilization()?null:cICLSocialinvestigationReport.get(0).getIncomeUtilization());
			ciclSocialInvestigationReportModel.setWorkRecord(null==cICLSocialinvestigationReport.get(0).getWorkRecord()?null:cICLSocialinvestigationReport.get(0).getWorkRecord());
			ciclSocialInvestigationReportModel.setEducation(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getEducation()));
			ciclSocialInvestigationReportModel.setClassMatesAttitude(null==cICLSocialinvestigationReport.get(0).getClassMatesAttitude()?null:cICLSocialinvestigationReport.get(0).getClassMatesAttitude());
			ciclSocialInvestigationReportModel.setTeachersAttitude(null==cICLSocialinvestigationReport.get(0).getTeachersAttitude()?null:cICLSocialinvestigationReport.get(0).getTeachersAttitude());
			ciclSocialInvestigationReportModel.setReasonLeavingSchool(null==cICLSocialinvestigationReport.get(0).getReasonLeavingSchool()?null:cICLSocialinvestigationReport.get(0).getReasonLeavingSchool());
			ciclSocialInvestigationReportModel.setOtherReasonLeavingSchool(null==cICLSocialinvestigationReport.get(0).getOtherReasonLeavingSchool()?null:cICLSocialinvestigationReport.get(0).getOtherReasonLeavingSchool());
			ciclSocialInvestigationReportModel.setSchoolType(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getSchoolTypeStudiedLast()));
			ciclSocialInvestigationReportModel.setVocationalTraining(null==cICLSocialinvestigationReport.get(0).getVocationalTraining()?null:cICLSocialinvestigationReport.get(0).getVocationalTraining());
			ciclSocialInvestigationReportModel.setMajorityFriendTypes(null==cICLSocialinvestigationReport.get(0).getMajorityFriendTypes()?null:cICLSocialinvestigationReport.get(0).getMajorityFriendTypes());
			ciclSocialInvestigationReportModel.setAttitudeTowardsFriends(null==cICLSocialinvestigationReport.get(0).getAttitudeTowardsFriends()?null:cICLSocialinvestigationReport.get(0).getAttitudeTowardsFriends());
			ciclSocialInvestigationReportModel.setFriendsAttitudeTowardsChild(null==cICLSocialinvestigationReport.get(0).getFriendsAttitudeTowardsChild()?null:cICLSocialinvestigationReport.get(0).getFriendsAttitudeTowardsChild());
			ciclSocialInvestigationReportModel.setObservationAboutNeighbourhood(null==cICLSocialinvestigationReport.get(0).getObservationAboutNeighbourhood()?null:cICLSocialinvestigationReport.get(0).getObservationAboutNeighbourhood());
			ciclSocialInvestigationReportModel.setObservationAboutNeighbourhoodToAsses(null==cICLSocialinvestigationReport.get(0).getObservationAboutNeighbourhoodToAsses()?null:cICLSocialinvestigationReport.get(0).getObservationAboutNeighbourhoodToAsses());
			ciclSocialInvestigationReportModel.setChildSubjectedOfAbuse(cICLSocialinvestigationReport.get(0).isChildSubjectedOfAbuse());
			
			ciclSocialInvestigationReportModel.setVerbalAbuse(null==cICLSocialinvestigationReport.get(0).getVerbalAbuse()?null:cICLSocialinvestigationReport.get(0).getVerbalAbuse());
			ciclSocialInvestigationReportModel.setOtherVerbalAbuse(null==cICLSocialinvestigationReport.get(0).getOtherVerbalAbuse()?null:cICLSocialinvestigationReport.get(0).getOtherVerbalAbuse());
			ciclSocialInvestigationReportModel.setPhysicalAbuse(null==cICLSocialinvestigationReport.get(0).getPhysicalAbuse()?null:cICLSocialinvestigationReport.get(0).getPhysicalAbuse());
			ciclSocialInvestigationReportModel.setOtherPhysicalAbuse(null==cICLSocialinvestigationReport.get(0).getOtherPhysicalAbuse()?null:cICLSocialinvestigationReport.get(0).getOtherPhysicalAbuse());
			ciclSocialInvestigationReportModel.setSexualAbuse(null==cICLSocialinvestigationReport.get(0).getSexualAbuse()?null:cICLSocialinvestigationReport.get(0).getSexualAbuse());
			ciclSocialInvestigationReportModel.setOtherSexualAbuse(null==cICLSocialinvestigationReport.get(0).getOtherSexualAbuse()?null:cICLSocialinvestigationReport.get(0).getOtherSexualAbuse());
			ciclSocialInvestigationReportModel.setOtherInOtherAbuse(null==cICLSocialinvestigationReport.get(0).getOtherInOtherAbuse()?null:cICLSocialinvestigationReport.get(0).getOtherInOtherAbuse());
			
			ciclSocialInvestigationReportModel.setChildVictim(cICLSocialinvestigationReport.get(0).isChildVictim());
			ciclSocialInvestigationReportModel.setUsedByAnyGang(cICLSocialinvestigationReport.get(0).isUsedByAnyGang());
			ciclSocialInvestigationReportModel.setHistoryRunAwayFromHome(null==cICLSocialinvestigationReport.get(0).getHistoryRunAwayFromHome()?null:cICLSocialinvestigationReport.get(0).getHistoryRunAwayFromHome());
			ciclSocialInvestigationReportModel.setCircumstancesOfApprehension(null==cICLSocialinvestigationReport.get(0).getCircumstancesOfApprehension()?null:cICLSocialinvestigationReport.get(0).getCircumstancesOfApprehension());
			ciclSocialInvestigationReportModel.setAllegedRoleInOffence(null==cICLSocialinvestigationReport.get(0).getAllegedRoleInOffence()?null:cICLSocialinvestigationReport.get(0).getAllegedRoleInOffence());
			ciclSocialInvestigationReportModel.setReasonForAllegedOffence(null==cICLSocialinvestigationReport.get(0).getReasonForAllegedOffence()?null:cICLSocialinvestigationReport.get(0).getReasonForAllegedOffence());
			ciclSocialInvestigationReportModel.setOtherReasonForAllegedOffence(null==cICLSocialinvestigationReport.get(0).getOtherReasonForAllegedOffence()?null:cICLSocialinvestigationReport.get(0).getOtherReasonForAllegedOffence());
			ciclSocialInvestigationReportModel.setApprehendedForOffence(cICLSocialinvestigationReport.get(0).isApprehendedForOffence());
			ciclSocialInvestigationReportModel.setApprehendedForOffenceDtls(null==cICLSocialinvestigationReport.get(0).getApprehendedForOffenceDtls()?null:cICLSocialinvestigationReport.get(0).getApprehendedForOffenceDtls());
			ciclSocialInvestigationReportModel.setInstitutionDocType(typeDetailsMap.get(cICLSocialinvestigationReport.get(0).getInstitutionDocType()));
			ciclSocialInvestigationReportModel.setPhysicalAppearance(null==cICLSocialinvestigationReport.get(0).getPhysicalAppearance()?null:cICLSocialinvestigationReport.get(0).getPhysicalAppearance());
			ciclSocialInvestigationReportModel.setHealthCondition(null==cICLSocialinvestigationReport.get(0).getHealthCondition()?null:cICLSocialinvestigationReport.get(0).getHealthCondition());
			ciclSocialInvestigationReportModel.setMentalCondition(null==cICLSocialinvestigationReport.get(0).getMentalCondition()?null:cICLSocialinvestigationReport.get(0).getMentalCondition());
			ciclSocialInvestigationReportModel.setAnyOtherRemark(null==cICLSocialinvestigationReport.get(0).getAnyOtherRemark()?null:cICLSocialinvestigationReport.get(0).getAnyOtherRemark());
			
			ciclSocialInvestigationReportModel.setRoiEmotionalFactors(null==cICLSocialinvestigationReport.get(0).getRoiEmotionalFactors()?null:cICLSocialinvestigationReport.get(0).getRoiEmotionalFactors());
			ciclSocialInvestigationReportModel.setRoiPhysicalCondition(null==cICLSocialinvestigationReport.get(0).getRoiPhysicalCondition()?null:cICLSocialinvestigationReport.get(0).getRoiPhysicalCondition());
			ciclSocialInvestigationReportModel.setRoiIntelligence(null==cICLSocialinvestigationReport.get(0).getRoiIntelligence()?null:cICLSocialinvestigationReport.get(0).getRoiIntelligence());
			ciclSocialInvestigationReportModel.setRoiSocialEconomicFactors(null==cICLSocialinvestigationReport.get(0).getRoiSocialEconomicFactors()?null:cICLSocialinvestigationReport.get(0).getRoiSocialEconomicFactors());
			ciclSocialInvestigationReportModel.setRoiSuggestiveCausesProblems(null==cICLSocialinvestigationReport.get(0).getRoiSuggestiveCausesProblems()?null:cICLSocialinvestigationReport.get(0).getRoiSuggestiveCausesProblems());
			ciclSocialInvestigationReportModel.setRoiAnalysisOfCase(null==cICLSocialinvestigationReport.get(0).getRoiAnalysisOfCase()?null:cICLSocialinvestigationReport.get(0).getRoiAnalysisOfCase());
			ciclSocialInvestigationReportModel.setRoiReasonsForCareProtection(null==cICLSocialinvestigationReport.get(0).getRoiReasonsForCareProtection()?null:cICLSocialinvestigationReport.get(0).getRoiReasonsForCareProtection());
			ciclSocialInvestigationReportModel.setRoiOpinionExperts(null==cICLSocialinvestigationReport.get(0).getRoiOpinionExperts()?null:cICLSocialinvestigationReport.get(0).getRoiOpinionExperts());
			ciclSocialInvestigationReportModel.setRoiRecommendation(null==cICLSocialinvestigationReport.get(0).getRoiRecommendation()?null:cICLSocialinvestigationReport.get(0).getRoiRecommendation());
			ciclSocialInvestigationReportModel.setChildPhoto(getPath(cICLSocialinvestigationReport.get(0).getChildPhoto()));
			ciclSocialInvestigationReportModel.setBankAccountNo(cICLSocialinvestigationReport.get(0).getBankAccountNo()== null? null:cICLSocialinvestigationReport.get(0).getBankAccountNo());
			ciclSocialInvestigationReportModel.setAccountholdername(null==cICLSocialinvestigationReport.get(0).getAccountholdername()?null:cICLSocialinvestigationReport.get(0).getAccountholdername());
			ciclSocialInvestigationReportModel.setBankname(null==cICLSocialinvestigationReport.get(0).getBankname()?null:cICLSocialinvestigationReport.get(0).getBankname());
			ciclSocialInvestigationReportModel.setBranch(null==cICLSocialinvestigationReport.get(0).getBranch()?null:cICLSocialinvestigationReport.get(0).getBranch());
			ciclSocialInvestigationReportModel.setIfsccode(null==cICLSocialinvestigationReport.get(0).getIfsccode()?null:cICLSocialinvestigationReport.get(0).getIfsccode());
			
			List<CICLsocialInvestigationReportFamilyDetails> ciclSocialInvestigationReportFamilyDetails = cICLSocialInvestigationReportFamilyDetailsRepository.findByRefId(cICLSocialinvestigationReport.get(0).getId());
			List<CICLSocialInvestigationReportFamilyDetailsModel> ciclSocialInvestigationReportFamilyDetailsModels = new ArrayList<CICLSocialInvestigationReportFamilyDetailsModel>();
			
			for(CICLsocialInvestigationReportFamilyDetails ciclSocialInvestigationReportFamilyDetail : ciclSocialInvestigationReportFamilyDetails){
				CICLSocialInvestigationReportFamilyDetailsModel ciclSocialInvestigationReportFamilyDetailsModel = new CICLSocialInvestigationReportFamilyDetailsModel();
				
				ciclSocialInvestigationReportFamilyDetailsModel.setName(ciclSocialInvestigationReportFamilyDetail.getName());
				ciclSocialInvestigationReportFamilyDetailsModel.setAge(ciclSocialInvestigationReportFamilyDetail.getAge());
				ciclSocialInvestigationReportFamilyDetailsModel.setRelationship(ciclSocialInvestigationReportFamilyDetail.getRelationship());
				ciclSocialInvestigationReportFamilyDetailsModel.setSex(typeDetailsMap.get(ciclSocialInvestigationReportFamilyDetail.getSex()));
				ciclSocialInvestigationReportFamilyDetailsModel.setEducation(typeDetailsMap.get(ciclSocialInvestigationReportFamilyDetail.getEducation()));
				ciclSocialInvestigationReportFamilyDetailsModel.setOccupation(ciclSocialInvestigationReportFamilyDetail.getOccupation());
				ciclSocialInvestigationReportFamilyDetailsModel.setIncome(ciclSocialInvestigationReportFamilyDetail.getIncome());
				ciclSocialInvestigationReportFamilyDetailsModel.setHealthStatus(ciclSocialInvestigationReportFamilyDetail.getHealthStatus());
				ciclSocialInvestigationReportFamilyDetailsModel.setHistoryOfMentalIllness(ciclSocialInvestigationReportFamilyDetail.getHistoryOfMentalIllness());
				ciclSocialInvestigationReportFamilyDetailsModel.setAddictions(ciclSocialInvestigationReportFamilyDetail.getAddictions());
				
				ciclSocialInvestigationReportFamilyDetailsModels.add(ciclSocialInvestigationReportFamilyDetailsModel);
			}
			ciclSocialInvestigationReportModel.setcICLSocialInvestigationReportFamilyDetailsModel(ciclSocialInvestigationReportFamilyDetailsModels);
			return ciclSocialInvestigationReportModel;
		}
		return null;
	}

}
