package org.sdrc.cpis.services;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.ConstitutionOfJJB;
import org.sdrc.cpis.models.ConstitutionOfJJBModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ConstitutionOfJJBRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionOfJJBServiceImpl implements ConstitutionOfJJBService 
{

	@Autowired
	ConstitutionOfJJBRepository constitutionOfJJBRepository;
	 
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private StateManager stateManager;
	
	
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
	public String saveConstitutionOfJJBData(ConstitutionOfJJBModel constitutionOfJJBModel) {

		if(constitutionOfJJBModel != null)
		{
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			ConstitutionOfJJB constitutionOfJJB=new ConstitutionOfJJB();
			
			constitutionOfJJB.setJjbDate(constitutionOfJJBModel.getJjbDate()== null ? null : new Date(constitutionOfJJBModel.getJjbDate().getTime()));
			constitutionOfJJB.setMagistrateName(constitutionOfJJBModel.getMagistrateName()== null ? null :constitutionOfJJBModel.getMagistrateName());
			constitutionOfJJB.setJoiningDate(constitutionOfJJBModel.getJoiningDate()== null ? null :new Date(constitutionOfJJBModel.getJoiningDate().getTime()));
			constitutionOfJJB.setMagistrateSex(constitutionOfJJBModel.getMagistrateSex()== null ? null :constitutionOfJJBModel.getMagistrateSex().getId());
			constitutionOfJJB.setMagistrateContactNo(constitutionOfJJBModel.getMagistrateContactNo()== null ? null :constitutionOfJJBModel.getMagistrateContactNo());
			constitutionOfJJB.setMagistrateEmailId(constitutionOfJJBModel.getMagistrateEmailId()== null ? null :constitutionOfJJBModel.getMagistrateEmailId());
			constitutionOfJJB.setSocialWorkerOneName(constitutionOfJJBModel.getSocialWorkerOneName()== null ? null :constitutionOfJJBModel.getSocialWorkerOneName());
			constitutionOfJJB.setSocialWorkerOneJoiningDate(constitutionOfJJBModel.getSocialWorkerOneJoiningDate()== null ? null :new Date(constitutionOfJJBModel.getSocialWorkerOneJoiningDate().getTime()));
			constitutionOfJJB.setSocialWorkerOneSex(constitutionOfJJBModel.getSocialWorkerOneSex()== null ? null :constitutionOfJJBModel.getSocialWorkerOneSex().getId());
			constitutionOfJJB.setSocialWorkerOneContactNo(constitutionOfJJBModel.getSocialWorkerOneContactNo()== null ? null :constitutionOfJJBModel.getSocialWorkerOneContactNo());
			constitutionOfJJB.setSocialWorkerOneEmailId(constitutionOfJJBModel.getSocialWorkerOneEmailId()== null ? null :constitutionOfJJBModel.getSocialWorkerOneEmailId());
			constitutionOfJJB.setSocialWorkerTwoName(constitutionOfJJBModel.getSocialWorkerTwoName()== null ? null :constitutionOfJJBModel.getSocialWorkerTwoName());
			constitutionOfJJB.setSocialWorkerTwoJoiningDate(constitutionOfJJBModel.getSocialWorkerTwoJoiningDate()== null ? null :new Date(constitutionOfJJBModel.getSocialWorkerTwoJoiningDate().getTime()));
			constitutionOfJJB.setSocialWorkerTwoSex(constitutionOfJJBModel.getSocialWorkerTwoSex()== null ? null :constitutionOfJJBModel.getSocialWorkerTwoSex().getId());
			constitutionOfJJB.setSocialWorkerTwoContactNo(constitutionOfJJBModel.getSocialWorkerTwoContactNo()== null ? null :constitutionOfJJBModel.getSocialWorkerTwoContactNo());
			constitutionOfJJB.setSocialWorkerTwoEmailId(constitutionOfJJBModel.getSocialWorkerTwoEmailId()== null ? null :constitutionOfJJBModel.getSocialWorkerTwoEmailId());
			constitutionOfJJB.setAreaId(userDetailModel.getAreaId()==null?null:userDetailModel.getAreaId());
			
			constitutionOfJJB.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			constitutionOfJJB.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
			constitutionOfJJB.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			
			constitutionOfJJBRepository.save(constitutionOfJJB);
			
		}
		
		return "saved";
	}


	@Override
	public ConstitutionOfJJBModel getConstitutionOfJJBModel(String childId) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	// view
//	@Override
//	public CICLPeriodicReportModel getCiclPeriodicReportModel(String childId) {
//		
//		CICLPeriodicReport ciclPeriodicReport = constitutionOfJJBRepository.findByChildIdChildId(childId);
//		
//		CICLPeriodicReportModel ciclPeriodicReportModel = new CICLPeriodicReportModel();
//		
//		
//		
//		if(ciclPeriodicReport != null)
//		{
//			ciclPeriodicReportModel.setId(ciclPeriodicReport.getId());
//			ciclPeriodicReportModel.setChildId(ciclPeriodicReport.getChildId().getChildId());
//			ciclPeriodicReportModel.setFirNumber(ciclPeriodicReport.getFirNumber());
//			ciclPeriodicReportModel.setPoliceStation(ciclPeriodicReport.getPoliceStation());
//			ciclPeriodicReportModel.setSections(ciclPeriodicReport.getSections());
//			ciclPeriodicReportModel.setMatter(ciclPeriodicReport.getMatter());
//			ciclPeriodicReportModel.setVs(ciclPeriodicReport.getVs());
//			ciclPeriodicReportModel.setChildName(ciclPeriodicReport.getChildName());
//			ciclPeriodicReportModel.setAge(ciclPeriodicReport.getAge());
//			ciclPeriodicReportModel.setEntryDate(ciclPeriodicReport.getEntryDate());
//			ciclPeriodicReportModel.setChildUnderCare(getTypeMap().get(ciclPeriodicReport.getChildUnderCare()));
//			ciclPeriodicReportModel.setChildUnderSupervision(ciclPeriodicReport.getChildUnderSupervision());
//			ciclPeriodicReportModel.setRegNo(ciclPeriodicReport.getRegNo());
//			ciclPeriodicReportModel.setSexObject(getTypeMap().get(ciclPeriodicReport.getSex()));
//			ciclPeriodicReportModel.setFatherName(ciclPeriodicReport.getFatherName());
//			ciclPeriodicReportModel.setReligionObject(getTypeMap().get(ciclPeriodicReport.getReligionObject()));
//			ciclPeriodicReportModel.setCasteObject(getTypeMap().get(ciclPeriodicReport.getCasteObject()));
//			ciclPeriodicReportModel.setCasteOtherType(ciclPeriodicReport.getCasteOtherType());
//			ciclPeriodicReportModel.setEducationObject(getTypeMap().get(ciclPeriodicReport.getEducation()));
//			ciclPeriodicReportModel.setVocationalTraining(ciclPeriodicReport.getVocationalTraining());
//			ciclPeriodicReportModel.setLanguageObject(getTypeMap().get(ciclPeriodicReport.getLanguages()));
//			ciclPeriodicReportModel.setOtherLanguage(ciclPeriodicReport.getOtherLanguage() );
//			ciclPeriodicReportModel.setNextCourtDate(ciclPeriodicReport.getNextCourtDate());
//
//		
//		}
//		
//		
//		return ciclPeriodicReport != null?ciclPeriodicReportModel:null;
//	}

}
