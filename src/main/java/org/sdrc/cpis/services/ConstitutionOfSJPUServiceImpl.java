package org.sdrc.cpis.services;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.ConstitutionOfSJPU;
import org.sdrc.cpis.models.ConstitutionOfSJPUModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ConstitutionOfSJPURepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionOfSJPUServiceImpl implements ConstitutionOfSJPUService 
{

	@Autowired
	ConstitutionOfSJPURepository constitutionOfSJPURepository;
	 
	
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
	public String saveConstitutionOfSJPUData(ConstitutionOfSJPUModel constitutionOfSJPUModel) {

		if(constitutionOfSJPUModel != null)
		{
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			ConstitutionOfSJPU constitutionOfSJPU=new ConstitutionOfSJPU();
			
			constitutionOfSJPU.setSjpuDate(constitutionOfSJPUModel.getSjpuDate()== null ? null : new Date(constitutionOfSJPUModel.getSjpuDate().getTime()));
			constitutionOfSJPU.setSpName(constitutionOfSJPUModel.getSpName()== null ? null :constitutionOfSJPUModel.getSpName());
			constitutionOfSJPU.setJoiningDate(constitutionOfSJPUModel.getJoiningDate()== null ? null :new Date(constitutionOfSJPUModel.getJoiningDate().getTime()));
			constitutionOfSJPU.setSpSex(constitutionOfSJPUModel.getSpSex()== null ? null :constitutionOfSJPUModel.getSpSex().getId());
			constitutionOfSJPU.setSpContactNo(constitutionOfSJPUModel.getSpContactNo()== null ? null :constitutionOfSJPUModel.getSpContactNo());
			constitutionOfSJPU.setSpEmailId(constitutionOfSJPUModel.getSpEmailId()== null ? null :constitutionOfSJPUModel.getSpEmailId());
			constitutionOfSJPU.setSocialWorkerOneName(constitutionOfSJPUModel.getSocialWorkerOneName()== null ? null :constitutionOfSJPUModel.getSocialWorkerOneName());
			constitutionOfSJPU.setSocialWorkerOneJoiningDate(constitutionOfSJPUModel.getSocialWorkerOneJoiningDate()== null ? null :new Date(constitutionOfSJPUModel.getSocialWorkerOneJoiningDate().getTime()));
			constitutionOfSJPU.setSocialWorkerOneSex(constitutionOfSJPUModel.getSocialWorkerOneSex()== null ? null :constitutionOfSJPUModel.getSocialWorkerOneSex().getId());
			constitutionOfSJPU.setSocialWorkerOneContactNo(constitutionOfSJPUModel.getSocialWorkerOneContactNo()== null ? null :constitutionOfSJPUModel.getSocialWorkerOneContactNo());
			constitutionOfSJPU.setSocialWorkerOneEmailId(constitutionOfSJPUModel.getSocialWorkerOneEmailId()== null ? null :constitutionOfSJPUModel.getSocialWorkerOneEmailId());
			constitutionOfSJPU.setSocialWorkerTwoName(constitutionOfSJPUModel.getSocialWorkerTwoName()== null ? null :constitutionOfSJPUModel.getSocialWorkerTwoName());
			constitutionOfSJPU.setSocialWorkerTwoJoiningDate(constitutionOfSJPUModel.getSocialWorkerTwoJoiningDate()== null ? null :new Date(constitutionOfSJPUModel.getSocialWorkerTwoJoiningDate().getTime()));
			constitutionOfSJPU.setSocialWorkerTwoSex(constitutionOfSJPUModel.getSocialWorkerTwoSex()== null ? null :constitutionOfSJPUModel.getSocialWorkerTwoSex().getId());
			constitutionOfSJPU.setSocialWorkerTwoContactNo(constitutionOfSJPUModel.getSocialWorkerTwoContactNo()== null ? null :constitutionOfSJPUModel.getSocialWorkerTwoContactNo());
			constitutionOfSJPU.setSocialWorkerTwoEmailId(constitutionOfSJPUModel.getSocialWorkerTwoEmailId()== null ? null :constitutionOfSJPUModel.getSocialWorkerTwoEmailId());
		
			constitutionOfSJPU.setAreaId(userDetailModel.getAreaId()==null?null:userDetailModel.getAreaId());
			
			constitutionOfSJPU.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			constitutionOfSJPU.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
			constitutionOfSJPU.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			
			constitutionOfSJPURepository.save(constitutionOfSJPU);
			
		}
		
		return "saved";
	}


	@Override
	public ConstitutionOfSJPUModel getConstitutionOfSJPUModel(String childId) {
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
