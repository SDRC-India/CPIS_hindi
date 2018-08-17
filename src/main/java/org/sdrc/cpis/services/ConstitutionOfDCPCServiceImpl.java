package org.sdrc.cpis.services;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.ConstitutionOfDCPC;
import org.sdrc.cpis.models.ConstitutionOfDCPCModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ConstitutionOfDCPCRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionOfDCPCServiceImpl implements ConstitutionOfDCPCService 
{

	@Autowired
	ConstitutionOfDCPCRepository constitutionOfDCPCRepository;
	 
	
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
	public String saveConstitutionOfDCPCData(ConstitutionOfDCPCModel constitutionOfDCPCModel) {

		if(constitutionOfDCPCModel != null)
		{
			UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
			ConstitutionOfDCPC constitutionOfDCPC=new ConstitutionOfDCPC();
			
			constitutionOfDCPC.setDcpcDate(constitutionOfDCPCModel.getDcpcDate()== null ? null : new Date(constitutionOfDCPCModel.getDcpcDate().getTime()));
			constitutionOfDCPC.setZillaParishadName(constitutionOfDCPCModel.getZillaParishadName()== null ? null :constitutionOfDCPCModel.getZillaParishadName());
			constitutionOfDCPC.setZillaParishadSex(constitutionOfDCPCModel.getZillaParishadSex()== null ? null :constitutionOfDCPCModel.getZillaParishadSex().getId());
			constitutionOfDCPC.setZillaParishadContactNo(constitutionOfDCPCModel.getZillaParishadContactNo()== null ? null :constitutionOfDCPCModel.getZillaParishadContactNo());
			constitutionOfDCPC.setZillaParishadEmailId(constitutionOfDCPCModel.getZillaParishadEmailId()== null ? null :constitutionOfDCPCModel.getZillaParishadEmailId());
			constitutionOfDCPC.setMagistrateName(constitutionOfDCPCModel.getMagistrateName()== null ? null :constitutionOfDCPCModel.getMagistrateName());
			constitutionOfDCPC.setMagistrateSex(constitutionOfDCPCModel.getMagistrateSex()== null ? null :constitutionOfDCPCModel.getMagistrateSex().getId());
			constitutionOfDCPC.setMagistrateContactNo(constitutionOfDCPCModel.getMagistrateContactNo()== null ? null :constitutionOfDCPCModel.getMagistrateContactNo());
			constitutionOfDCPC.setMagistrateEmailId(constitutionOfDCPCModel.getMagistrateEmailId()== null ? null :constitutionOfDCPCModel.getMagistrateEmailId());
			constitutionOfDCPC.setDeoName(constitutionOfDCPCModel.getDeoName()== null ? null :constitutionOfDCPCModel.getDeoName());
			constitutionOfDCPC.setDeoSex(constitutionOfDCPCModel.getDeoSex()== null ? null :constitutionOfDCPCModel.getDeoSex().getId());
			constitutionOfDCPC.setDeoContactNo(constitutionOfDCPCModel.getDeoContactNo()== null ? null :constitutionOfDCPCModel.getDeoContactNo());
			constitutionOfDCPC.setDeoEmailId(constitutionOfDCPCModel.getDeoEmailId()== null ? null :constitutionOfDCPCModel.getDeoEmailId());
			
			constitutionOfDCPC.setCdmoName(constitutionOfDCPCModel.getCdmoName()== null ? null :constitutionOfDCPCModel.getCdmoName());
			constitutionOfDCPC.setCdmoSex(constitutionOfDCPCModel.getCdmoSex()== null ? null :constitutionOfDCPCModel.getCdmoSex().getId());
			constitutionOfDCPC.setCdmoContactNo(constitutionOfDCPCModel.getCdmoContactNo()== null ? null :constitutionOfDCPCModel.getCdmoContactNo());
			constitutionOfDCPC.setCdmoEmailId(constitutionOfDCPCModel.getCdmoEmailId()== null ? null :constitutionOfDCPCModel.getCdmoEmailId());
			
			constitutionOfDCPC.setDloName(constitutionOfDCPCModel.getDloName()== null ? null :constitutionOfDCPCModel.getDloName());
			constitutionOfDCPC.setDloSex(constitutionOfDCPCModel.getDloSex()== null ? null :constitutionOfDCPCModel.getDloSex().getId());
			constitutionOfDCPC.setDloContactNo(constitutionOfDCPCModel.getDloContactNo()== null ? null :constitutionOfDCPCModel.getDloContactNo());
			constitutionOfDCPC.setDloEmailId(constitutionOfDCPCModel.getDloEmailId()== null ? null :constitutionOfDCPCModel.getDloEmailId());
			constitutionOfDCPC.setPddrdaName(constitutionOfDCPCModel.getPddrdaName()== null ? null :constitutionOfDCPCModel.getPddrdaName());
			constitutionOfDCPC.setPddrdaSex(constitutionOfDCPCModel.getPddrdaSex()== null ? null :constitutionOfDCPCModel.getPddrdaSex().getId());
			constitutionOfDCPC.setPddrdaContactNo(constitutionOfDCPCModel.getPddrdaContactNo()== null ? null :constitutionOfDCPCModel.getPddrdaContactNo());
			constitutionOfDCPC.setPddrdaEmailId(constitutionOfDCPCModel.getPddrdaEmailId()== null ? null :constitutionOfDCPCModel.getPddrdaEmailId());
			constitutionOfDCPC.setPoliceSuperintendentName(constitutionOfDCPCModel.getPoliceSuperintendentName()== null ? null :constitutionOfDCPCModel.getPoliceSuperintendentName());
			constitutionOfDCPC.setPoliceSuperintendentSex(constitutionOfDCPCModel.getPoliceSuperintendentSex()== null ? null :constitutionOfDCPCModel.getPoliceSuperintendentSex().getId());
			
			constitutionOfDCPC.setAreaId(userDetailModel.getAreaId()==null?null:userDetailModel.getAreaId());
			constitutionOfDCPC.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
			constitutionOfDCPC.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
			constitutionOfDCPC.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
			
			constitutionOfDCPC.setMemberOneName(constitutionOfDCPCModel.getMemberOneName()==null?null:constitutionOfDCPCModel.getMemberOneName());
			constitutionOfDCPC.setMemberOneEmailId(constitutionOfDCPCModel.getMemberOneEmailId()==null?null:constitutionOfDCPCModel.getMemberOneEmailId());
			constitutionOfDCPC.setMemberOneContactNo(constitutionOfDCPCModel.getMemberOneContactNo()==null?null:constitutionOfDCPCModel.getMemberOneContactNo());
			constitutionOfDCPC.setMemberOneSex(constitutionOfDCPCModel.getMemberOneSex()==null?null:constitutionOfDCPCModel.getMemberOneSex().getId());
			
			constitutionOfDCPC.setMemberTwoName(constitutionOfDCPCModel.getMemberTwoName()==null?null:constitutionOfDCPCModel.getMemberTwoName());
			constitutionOfDCPC.setMemberTwoEmailId(constitutionOfDCPCModel.getMemberTwoEmailId()==null?null:constitutionOfDCPCModel.getMemberTwoEmailId());
			constitutionOfDCPC.setMemberTwoContactNo(constitutionOfDCPCModel.getMemberTwoContactNo()==null?null:constitutionOfDCPCModel.getMemberTwoContactNo());
			constitutionOfDCPC.setMemberTwoSex(constitutionOfDCPCModel.getMemberTwoSex()==null?null:constitutionOfDCPCModel.getMemberTwoSex().getId());
			
			constitutionOfDCPC.setMemberThreeName(constitutionOfDCPCModel.getMemberThreeName()==null?null:constitutionOfDCPCModel.getMemberThreeName());
			constitutionOfDCPC.setMemberThreeEmailId(constitutionOfDCPCModel.getMemberThreeEmailId()==null?null:constitutionOfDCPCModel.getMemberThreeEmailId());
			constitutionOfDCPC.setMemberThreeContactNo(constitutionOfDCPCModel.getMemberThreeContactNo()==null?null:constitutionOfDCPCModel.getMemberThreeContactNo());
			constitutionOfDCPC.setMemberThreeSex(constitutionOfDCPCModel.getMemberThreeSex()== null?null:constitutionOfDCPCModel.getMemberThreeSex().getId());
			
			constitutionOfDCPC.setMemberFourName(constitutionOfDCPCModel.getMemberFourName()==null?null:constitutionOfDCPCModel.getMemberFourName());
			constitutionOfDCPC.setMemberFourEmailId(constitutionOfDCPCModel.getMemberFourEmailId()==null?null:constitutionOfDCPCModel.getMemberFourEmailId());
			constitutionOfDCPC.setMemberFourContactNo(constitutionOfDCPCModel.getMemberFourContactNo()==null?null:constitutionOfDCPCModel.getMemberFourContactNo());
			constitutionOfDCPC.setMemberFourSex(constitutionOfDCPCModel.getMemberFourSex()==null?null:constitutionOfDCPCModel.getMemberFourSex().getId());
			
			constitutionOfDCPC.setMemberFiveName(constitutionOfDCPCModel.getMemberFiveName()==null?null:constitutionOfDCPCModel.getMemberFiveName());
			constitutionOfDCPC.setMemberFiveEmailId(constitutionOfDCPCModel.getMemberFiveEmailId()==null?null:constitutionOfDCPCModel.getMemberFiveEmailId());
			constitutionOfDCPC.setMemberFiveContactNo(constitutionOfDCPCModel.getMemberFiveContactNo()==null?null:constitutionOfDCPCModel.getMemberFiveContactNo());
			constitutionOfDCPC.setMemberFiveSex(constitutionOfDCPCModel.getMemberFiveSex()==null?null:constitutionOfDCPCModel.getMemberFiveSex().getId());
			
			constitutionOfDCPC.setMemberSixName(constitutionOfDCPCModel.getMemberSixName()==null?null:constitutionOfDCPCModel.getMemberSixName());
			constitutionOfDCPC.setMemberSixEmailId(constitutionOfDCPCModel.getMemberSixEmailId()==null?null:constitutionOfDCPCModel.getMemberSixEmailId());
			constitutionOfDCPC.setMemberSixContactNo(constitutionOfDCPCModel.getMemberSixContactNo()==null?null:constitutionOfDCPCModel.getMemberSixContactNo());
			constitutionOfDCPC.setMemberSixSex(constitutionOfDCPCModel.getMemberSixSex()==null?null:constitutionOfDCPCModel.getMemberSixSex().getId());
			
			constitutionOfDCPC.setMemberSevenName(constitutionOfDCPCModel.getMemberSevenName()==null?null:constitutionOfDCPCModel.getMemberSevenName());
			constitutionOfDCPC.setMemberSevenEmailId(constitutionOfDCPCModel.getMemberSevenEmailId()==null?null:constitutionOfDCPCModel.getMemberSevenEmailId());
			constitutionOfDCPC.setMemberSevenContactNo(constitutionOfDCPCModel.getMemberSevenContactNo()==null?null:constitutionOfDCPCModel.getMemberSevenContactNo());
			constitutionOfDCPC.setMemberSevenSex(constitutionOfDCPCModel.getMemberSevenSex()==null?null:constitutionOfDCPCModel.getMemberSevenSex().getId());
			
			constitutionOfDCPC.setMemberEightName(constitutionOfDCPCModel.getMemberEightName()==null?null:constitutionOfDCPCModel.getMemberEightName());
			constitutionOfDCPC.setMemberEightEmailId(constitutionOfDCPCModel.getMemberEightEmailId()==null?null:constitutionOfDCPCModel.getMemberEightEmailId());
			constitutionOfDCPC.setMemberEightContactNo(constitutionOfDCPCModel.getMemberEightContactNo()==null?null:constitutionOfDCPCModel.getMemberEightContactNo());
			constitutionOfDCPC.setMemberEightSex(constitutionOfDCPCModel.getMemberEightSex()==null?null:constitutionOfDCPCModel.getMemberEightSex().getId());
			
			constitutionOfDCPCRepository.save(constitutionOfDCPC);
			
		}
		
		return "saved";
	}
	@Override
	public ConstitutionOfDCPCModel getConstitutionOfDCPCModel(String childId) {
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
