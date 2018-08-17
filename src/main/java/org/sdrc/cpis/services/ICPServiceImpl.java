package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.IndividualCarePlanA;
import org.sdrc.cpis.domains.IndividualCarePlanB;
import org.sdrc.cpis.domains.IndividualCarePlanC;
import org.sdrc.cpis.domains.IndividualCarePlanD;
import org.sdrc.cpis.models.IndividualCarePlanAModel;
import org.sdrc.cpis.models.IndividualCarePlanBModel;
import org.sdrc.cpis.models.IndividualCarePlanCModel;
import org.sdrc.cpis.models.IndividualCarePlanDModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.IcpPersonalDetailsRepository;
import org.sdrc.cpis.repository.IndividualCarePlanBRepository;
import org.sdrc.cpis.repository.IndividualCarePlanCRepositroy;
import org.sdrc.cpis.repository.IndividualCarePlanDRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class ICPServiceImpl implements ICPService {
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private IndividualCarePlanBRepository individualCarePlanBRepository;
	
	@Autowired
	private IndividualCarePlanCRepositroy individualCarePlanCRepositroy;
	
	@Autowired
	private IndividualCarePlanDRepository individualCarePlanDRepository;

	@Autowired
	private IcpPersonalDetailsRepository icpPersonalDetailsRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	Map<Integer, ValueObject> typeMap;
	
	@Autowired
	private StateManager stateManager;
	
	
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
	
	@Override
	public void saveICPC(IndividualCarePlanCModel individualCarePlanCModel) throws Exception {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		String mouOfSponsorsPath = individualCarePlanCModel.getMouOfSponsors()==null?null:exportPDFServiceImpl.getFileName(individualCarePlanCModel.getMouOfSponsors(), "Mou_Of_Sponsors", applicationMessageSource.getMessage("store.icpImagePath", null, null,null));
		String medicalReportPath = individualCarePlanCModel.getMedicalReport()==null?null:exportPDFServiceImpl.getFileName(individualCarePlanCModel.getMedicalReport(), "Medical_Report", applicationMessageSource.getMessage("store.icpImagePath", null, null,null));
		String mouWithNgoPath  = individualCarePlanCModel.getMouWithNgo()==null?null:exportPDFServiceImpl.getFileName(individualCarePlanCModel.getMouWithNgo(), "Mou_With_Ngo", applicationMessageSource.getMessage("store.icpImagePath", null, null,null));
		
		IndividualCarePlanC individualCarePlanC = new IndividualCarePlanC();
		
		individualCarePlanC.setAuthorityName(null == individualCarePlanCModel.getAuthorityName() ? null : individualCarePlanCModel.getAuthorityName());
		individualCarePlanC.setCat10rrpc(null == individualCarePlanCModel.getCat10rrpc() ? null : individualCarePlanCModel.getCat10rrpc());
//		individualCarePlanC.(null == individualCarePlanCModel. ? null : individualCarePlanCModel.);
		individualCarePlanC.setCat1rrpc(null == individualCarePlanCModel.getCat1rrpc() ? null : individualCarePlanCModel.getCat1rrpc());
		individualCarePlanC.setCat2rrpc(null == individualCarePlanCModel.getCat2rrpc() ? null : individualCarePlanCModel.getCat2rrpc());
		individualCarePlanC.setCat3rrpc(null == individualCarePlanCModel.getCat3rrpc() ? null : individualCarePlanCModel.getCat3rrpc());
		individualCarePlanC.setCat4rrpc(null == individualCarePlanCModel.getCat4rrpc() ? null : individualCarePlanCModel.getCat4rrpc());
		individualCarePlanC.setCat5rrpc(null == individualCarePlanCModel.getCat5rrpc() ? null : individualCarePlanCModel.getCat5rrpc());
		individualCarePlanC.setCat6rrpc(null == individualCarePlanCModel.getCat6rrpc() ? null : individualCarePlanCModel.getCat6rrpc());
		individualCarePlanC.setCat7rrpc(null == individualCarePlanCModel.getCat7rrpc() ? null : individualCarePlanCModel.getCat7rrpc());
		individualCarePlanC.setCat8rrpc(null == individualCarePlanCModel.getCat8rrpc() ? null : individualCarePlanCModel.getCat8rrpc());
		individualCarePlanC.setCat9rrpc(null == individualCarePlanCModel.getCat9rrpc() ? null : individualCarePlanCModel.getCat9rrpc());
		individualCarePlanC.setChildId(childDetailsRepository.findChildById(individualCarePlanCModel.getChildId()));
		individualCarePlanC.setDateOfOrder(null == individualCarePlanCModel.getDateOfOrder() ? null : individualCarePlanCModel.getDateOfOrder());
		individualCarePlanC.setDetailOfSponsors(null == individualCarePlanCModel.getDetailOfSponsors() ? null : individualCarePlanCModel.getDetailOfSponsors());
		individualCarePlanC.setIdentificationProofNumber(null == individualCarePlanCModel.getIdentificationProofNumber() ? null : individualCarePlanCModel.getIdentificationProofNumber());
		individualCarePlanC.setOtherIdentificationProof(null == individualCarePlanCModel.getOtherIdentificationProof() ? null : individualCarePlanCModel.getOtherIdentificationProof());
		individualCarePlanC.setIdentificationProofType(null == individualCarePlanCModel.getIdentificationProofType() ? null : individualCarePlanCModel.getIdentificationProofType().getId());
		individualCarePlanC.setInstitutionDetails(null == individualCarePlanCModel.getInstitutionDetails() ? null : individualCarePlanCModel.getInstitutionDetails());
		
		
		individualCarePlanC.setMedicalReportPath(null == medicalReportPath ? null : medicalReportPath);
		individualCarePlanC.setMouOfSponsorsPath(null == mouOfSponsorsPath ? null : mouOfSponsorsPath);
		individualCarePlanC.setMouWithNgoPath(null == mouWithNgoPath ? null : mouWithNgoPath);
		
		
		individualCarePlanC.setOfficerOrNgoName(null == individualCarePlanCModel.getOfficerOrNgoName() ? null : individualCarePlanCModel.getOfficerOrNgoName());
		individualCarePlanC.setOrderType(null==individualCarePlanCModel.getOrderType()?null:individualCarePlanCModel.getOrderType().getId());
		individualCarePlanC.setOtherInfo(null == individualCarePlanCModel.getOtherInfo() ? null : individualCarePlanCModel.getOtherInfo());
		individualCarePlanC.setPlaceOfTransfer(null == individualCarePlanCModel.getPlaceOfTransfer() ? null : individualCarePlanCModel.getPlaceOfTransfer());
		individualCarePlanC.setPostReleaseFollowupBy(null == individualCarePlanCModel.getPostReleaseFollowupBy() ? null : individualCarePlanCModel.getPostReleaseFollowupBy());
		individualCarePlanC.setRehabilitationPlan(null == individualCarePlanCModel.getRehabilitationPlan() ? null : individualCarePlanCModel.getRehabilitationPlan());
		individualCarePlanC.setRequisitionForEscort(null == individualCarePlanCModel.getRequisitionForEscort() ? null : individualCarePlanCModel.getRequisitionForEscort());
		individualCarePlanC.setSkillsAcquired(null == individualCarePlanCModel.getSkillsAcquired() ? null : individualCarePlanCModel.getSkillsAcquired());
		individualCarePlanC.setUserIp(userDetailModel.getUserIp());
		individualCarePlanC.setCreatedBy(userDetailModel.getUserName());
		individualCarePlanC.setCreatedDate(new java.sql.Date(new Date().getTime()));
//		individualCarePlanC.setTypeOfOrder(individualCarePlanCModel.getTypeOfOrder().getId());
		
		individualCarePlanCRepositroy.save(individualCarePlanC);
	}
	
	@Override
	public void saveICPA(IndividualCarePlanAModel individualCarePlanAModel) {
		// TODO Auto-generated method stub
		IndividualCarePlanA individualCarePlanA=new IndividualCarePlanA();
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		
		individualCarePlanA.setChildId(childDetailsRepository.findChildById(individualCarePlanAModel.getChildId()));
		individualCarePlanA.setNameOfOfficerOrWorker(null==individualCarePlanAModel.getNameOfOfficer()?null:individualCarePlanAModel.getNameOfOfficer());
		individualCarePlanA.setAddressBoardCommittee(null==individualCarePlanAModel.getBoardAddress()?null:individualCarePlanAModel.getBoardAddress());
		individualCarePlanA.setAdmissionNo(null==individualCarePlanAModel.getAdmissionNum()?null:individualCarePlanAModel.getAdmissionNum());
		individualCarePlanA.setAwardsRewardsDtls(null==individualCarePlanAModel.getAwardDetails()?null:individualCarePlanAModel.getAwardDetails());
		individualCarePlanA.setCaste(null==individualCarePlanAModel.getCaste()?null:individualCarePlanAModel.getCaste().getId());
		individualCarePlanA.setCat10Aoc(null==individualCarePlanAModel.getCat10aoc()?null:individualCarePlanAModel.getCat10aoc());
		individualCarePlanA.setCat10Dtls(null==individualCarePlanAModel.getCat1aoc()?null:individualCarePlanAModel.getCat1aoc());
		individualCarePlanA.setCat10Pi(null==individualCarePlanAModel.getCat10pi()?null:individualCarePlanAModel.getCat10pi());
		individualCarePlanA.setCat1Aoc(null==individualCarePlanAModel.getCat1aoc()?null:individualCarePlanAModel.getCat1aoc());
		individualCarePlanA.setCat1Pi(null == individualCarePlanAModel.getCat1pi() ? null : individualCarePlanAModel.getCat1pi());
		individualCarePlanA.setCat2Aoc(null==individualCarePlanAModel.getCat2aoc()?null:individualCarePlanAModel.getCat2aoc());
		individualCarePlanA.setCat2Pi(null==individualCarePlanAModel.getCat2pi()?null:individualCarePlanAModel.getCat2pi());
		individualCarePlanA.setCat3Aoc(null==individualCarePlanAModel.getCat3aoc()?null:individualCarePlanAModel.getCat3aoc());
		individualCarePlanA.setCat3Pi(null==individualCarePlanAModel.getCat3pi()?null:individualCarePlanAModel.getCat3pi());
		individualCarePlanA.setCat4Aoc(null==individualCarePlanAModel.getCat4aoc()?null:individualCarePlanAModel.getCat4aoc());
		individualCarePlanA.setCat4Pi(null==individualCarePlanAModel.getCat4pi()?null:individualCarePlanAModel.getCat4pi());
		individualCarePlanA.setCat5Aoc(null==individualCarePlanAModel.getCat5aoc()?null:individualCarePlanAModel.getCat5aoc());
		individualCarePlanA.setCat5Pi(null==individualCarePlanAModel.getCat5pi()?null:individualCarePlanAModel.getCat5pi());
		individualCarePlanA.setCat6Aoc(null==individualCarePlanAModel.getCat6aoc()?null:individualCarePlanAModel.getCat6aoc());
		individualCarePlanA.setCat6Pi(null==individualCarePlanAModel.getCat6pi()?null:individualCarePlanAModel.getCat6pi());
		individualCarePlanA.setCat7Aoc(null==individualCarePlanAModel.getCat7aoc()?null:individualCarePlanAModel.getCat7aoc());
		individualCarePlanA.setCat7Pi(null==individualCarePlanAModel.getCat7pi()?null:individualCarePlanAModel.getCat7pi());
		individualCarePlanA.setCat8Aoc(null==individualCarePlanAModel.getCat8aoc()?null:individualCarePlanAModel.getCat8aoc());
		individualCarePlanA.setCat8Pi(null==individualCarePlanAModel.getCat8pi()?null:individualCarePlanAModel.getCat8pi());
		individualCarePlanA.setCat9Aoc(null==individualCarePlanAModel.getCat9aoc()?null:individualCarePlanAModel.getCat9aoc());
		individualCarePlanA.setCat9Pi(null==individualCarePlanAModel.getCat9pi()?null:individualCarePlanAModel.getCat9pi());
		individualCarePlanA.setChildAge(null==individualCarePlanAModel.getAge()?null:individualCarePlanAModel.getAge());
		individualCarePlanA.setChildEarnings(null==individualCarePlanAModel.getEarnings()?null:individualCarePlanAModel.getEarnings());
//		individualCarePlanA.setChildId(individualCarePlanAModel.);
		individualCarePlanA.setDateOfAdmission(null==individualCarePlanAModel.getAdmissionDate()?null:individualCarePlanAModel.getAdmissionDate());
		individualCarePlanA.setDateOfICP(null==individualCarePlanAModel.getDateOfIcp()?null:individualCarePlanAModel.getDateOfIcp());
		individualCarePlanA.setLanguageSpoken(null==individualCarePlanAModel.getLanguage()?null:individualCarePlanAModel.getLanguage());
		individualCarePlanA.setLevelOfEducation(null==individualCarePlanAModel.getEducation()?null:individualCarePlanAModel.getEducation().getId());
		individualCarePlanA.setNationality(null==individualCarePlanAModel.getNationality()?null:individualCarePlanAModel.getNationality());
		individualCarePlanA.setPoliceStation(null==individualCarePlanAModel.getPoliceStation()?null:individualCarePlanAModel.getPoliceStation());
		individualCarePlanA.setReligion(null==individualCarePlanAModel.getReligion()?null:individualCarePlanAModel.getReligion().getId());
		individualCarePlanA.setReligionOther(null == individualCarePlanAModel.getReligionOther() ? null : individualCarePlanAModel.getReligionOther());
		individualCarePlanA.setSavingAccountDtls(null==individualCarePlanAModel.getAccountDetail()?null:individualCarePlanAModel.getAccountDetail());
		individualCarePlanA.setStayOfChild(null==individualCarePlanAModel.getStayOfChild()?null:individualCarePlanAModel.getStayOfChild().getId());
//		individualCarePlanA.setTypeOfOfficerOrWorker(null==individualCarePlanAModel.getDesignation()?null:individualCarePlanAModel.getDesignation().getId());
		individualCarePlanA.setChildDob(null==individualCarePlanAModel.getDob()?null:individualCarePlanAModel.getDob());
		individualCarePlanA.setFatherName(null == individualCarePlanAModel.getFatherName() ? null : individualCarePlanAModel.getFatherName());
		individualCarePlanA.setMotherName(null == individualCarePlanAModel.getMotherName() ? null : individualCarePlanAModel.getMotherName());
		individualCarePlanA.setTypeOfOfficerOrWorker(null == individualCarePlanAModel.getDesignation() ? null : individualCarePlanAModel.getDesignation().getId());
		individualCarePlanA.setFirNo(null == individualCarePlanAModel.getFirNo() ? null : individualCarePlanAModel.getFirNo());
		individualCarePlanA.setuSections(null == individualCarePlanAModel.getuSections() ? null : individualCarePlanAModel.getuSections());
		individualCarePlanA.setUserIp(userDetailModel.getUserIp());
		individualCarePlanA.setCreatedBy(userDetailModel.getUserName());
		individualCarePlanA.setCreatedDate(new java.sql.Date(new Date().getTime()));
		
		icpPersonalDetailsRepository.save(individualCarePlanA);
		ChildDetails childDetails = childDetailsRepository.findChildById(individualCarePlanAModel.getChildId());
		childDetails.setId(childDetails.getId());
		childDetails.setIcpFilled(1);
	}
	
	@Override
	public void saveICPB(IndividualCarePlanBModel individualCarePlanBModel){
		IndividualCarePlanB individualCarePlanB=new IndividualCarePlanB();
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		
		individualCarePlanB.setChildId(childDetailsRepository.findChildById(individualCarePlanBModel.getChildId()));
		individualCarePlanB.setAddressOfParent(null==individualCarePlanBModel.getAddressOfParent()?null:individualCarePlanBModel.getAddressOfParent());
		individualCarePlanB.setAdmissionNum(null==individualCarePlanBModel.getAdmissionNum()?null:individualCarePlanBModel.getAdmissionNum());
		individualCarePlanB.setCat10poc(null==individualCarePlanBModel.getCat10poc()?null:individualCarePlanBModel.getCat10poc());
		individualCarePlanB.setCat1Poc(null==individualCarePlanBModel.getCat1Poc()?null:individualCarePlanBModel.getCat1Poc());
		individualCarePlanB.setCat2poc(null==individualCarePlanBModel.getCat2poc()?null:individualCarePlanBModel.getCat2poc());
		individualCarePlanB.setCat3poc(null==individualCarePlanBModel.getCat3poc()?null:individualCarePlanBModel.getCat3poc());
		individualCarePlanB.setCat4poc(null==individualCarePlanBModel.getCat4poc()?null:individualCarePlanBModel.getCat4poc());
		individualCarePlanB.setCat5poc(null==individualCarePlanBModel.getCat5poc()?null:individualCarePlanBModel.getCat5poc());
		individualCarePlanB.setCat6poc(null==individualCarePlanBModel.getCat6poc()?null:individualCarePlanBModel.getCat6poc());
		individualCarePlanB.setCat7poc(null==individualCarePlanBModel.getCat7poc()?null:individualCarePlanBModel.getCat7poc());
		individualCarePlanB.setCat8poc(null==individualCarePlanBModel.getCat8poc()?null:individualCarePlanBModel.getCat8poc());
		individualCarePlanB.setCat9poc(null==individualCarePlanBModel.getCat9poc()?null:individualCarePlanBModel.getCat9poc());
		individualCarePlanB.setDateOfInterview(null==individualCarePlanBModel.getDateOfInterview()?null:individualCarePlanBModel.getDateOfInterview());
		individualCarePlanB.setDateOfReport(null==individualCarePlanBModel.getDateOfReport()?null:individualCarePlanBModel.getDateOfReport());
		individualCarePlanB.setGeneralConductAndProgress(null==individualCarePlanBModel.getGeneralConductAndProgress()?null:individualCarePlanBModel.getGeneralConductAndProgress());
		individualCarePlanB.setNameOfOfficerOrWorker(null==individualCarePlanBModel.getNameOfOfficerOrWorker()?null:individualCarePlanBModel.getNameOfOfficerOrWorker());
		individualCarePlanB.setNameOfParent(null==individualCarePlanBModel.getNameOfParent()?null:individualCarePlanBModel.getNameOfParent());
		individualCarePlanB.setOtherProceedings(null==individualCarePlanBModel.getOtherProceedings()?null:individualCarePlanBModel.getOtherProceedings());
		individualCarePlanB.setPeriodOfReport(null==individualCarePlanBModel.getPeriodOfReport()?null:individualCarePlanBModel.getPeriodOfReport());
		individualCarePlanB.setPlaceOfInterview(null==individualCarePlanBModel.getPlaceOfInterview()?null:individualCarePlanBModel.getPlaceOfInterview());
		individualCarePlanB.setProceedings(null==individualCarePlanBModel.getProceedings()?null:individualCarePlanBModel.getProceedings().getId());
		individualCarePlanB.setResultOfSupervision(null==individualCarePlanBModel.getResultOfSupervision()?null:individualCarePlanBModel.getResultOfSupervision());
		individualCarePlanB.setSupervisionCompletionDate(null==individualCarePlanBModel.getSupervisionCompletionDate()?null:individualCarePlanBModel.getSupervisionCompletionDate());
		individualCarePlanB.setTypeOfOfficerOrWorker(null==individualCarePlanBModel.getTypeOfOfficerOrWorker()?null:individualCarePlanBModel.getTypeOfOfficerOrWorker().getId());
		individualCarePlanB.setTypeOfParent(null==individualCarePlanBModel.getTypeOfParent()?null:individualCarePlanBModel.getTypeOfParent().getId());
		individualCarePlanB.setStayOfChild(null == individualCarePlanBModel.getStayOfChild() ? null : individualCarePlanBModel.getStayOfChild().getId());
		individualCarePlanB.setUserIp(userDetailModel.getUserIp());
		individualCarePlanB.setCreatedBy(userDetailModel.getUserName());
		individualCarePlanB.setCreatedDate(new java.sql.Date(new Date().getTime()));
		
		individualCarePlanBRepository.save(individualCarePlanB);
	}

	@Override
	public void saveICPD(IndividualCarePlanDModel individualCarePlanDModel) {
		
		IndividualCarePlanD individualCarePlanD = new IndividualCarePlanD();
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		
		individualCarePlanD.setAdmissionDate(null == individualCarePlanDModel.getAdmissionDate() ? null : individualCarePlanDModel.getAdmissionDate());
		individualCarePlanD.setBelongingsHandedToParents(null == individualCarePlanDModel.getBelongingsHandedToParents() ? null : individualCarePlanDModel.getBelongingsHandedToParents().getId());
		individualCarePlanD.setChildWentToLearningInstitueType(null == individualCarePlanDModel.getChildWentToLearningInstitueType() ? null : individualCarePlanDModel.getChildWentToLearningInstitueType().getId());
		individualCarePlanD.setChildId(childDetailsRepository.findChildById(individualCarePlanDModel.getChildId()));
		individualCarePlanD.setChildView(null == individualCarePlanDModel.getChildView() ? null : individualCarePlanDModel.getChildView());
		individualCarePlanD.setFamilyBehaviour(null == individualCarePlanDModel.getFamilyBehaviour() ? null : individualCarePlanDModel.getFamilyBehaviour());
		individualCarePlanD.setHowUsingSkills(null == individualCarePlanDModel.getHowUsingSkills() ? null : individualCarePlanDModel.getHowUsingSkills());
		individualCarePlanD.setIdAdhaarCardNum(null == individualCarePlanDModel.getIdAdhaarCardNum() ? null : individualCarePlanDModel.getIdAdhaarCardNum());
		individualCarePlanD.setIdAdhaarCardProduced(individualCarePlanDModel.isIdAdhaarCardProduced());
		individualCarePlanD.setIdBirthCertificateNum(null == individualCarePlanDModel.getIdBirthCertificateNum() ? null : individualCarePlanDModel.getIdBirthCertificateNum());
		individualCarePlanD.setIdBirthCertificateProduced(individualCarePlanDModel.isIdBirthCertificateProduced());
		individualCarePlanD.setIdBplCardNum(null == individualCarePlanDModel.getIdBplCardNum() ? null : individualCarePlanDModel.getIdBplCardNum());
		individualCarePlanD.setIdBplCardProduced(individualCarePlanDModel.isIdBplCardProduced());
		individualCarePlanD.setIdCasteCertificateNum(null == individualCarePlanDModel.getIdCasteCertificateNum() ? null : individualCarePlanDModel.getIdCasteCertificateNum());
		individualCarePlanD.setIdCasteCertificateProduced(individualCarePlanDModel.isIdCasteCertificateProduced());
		individualCarePlanD.setIdDisabiltyCertificateProduced(individualCarePlanDModel.isIdDisabiltyCertificateProduced());
		individualCarePlanD.setIdDisabiltyCertificateNum(null == individualCarePlanDModel.getIdDisabiltyCertificateNum() ? null : individualCarePlanDModel.getIdDisabiltyCertificateNum());
		individualCarePlanD.setIdImmunizationCardNum(null == individualCarePlanDModel.getIdImmunizationCardNum() ? null : individualCarePlanDModel.getIdImmunizationCardNum());
		individualCarePlanD.setIdImmunizationCardProduced(individualCarePlanDModel.isIdImmunizationCardProduced());
		individualCarePlanD.setIdRationCardNum(null == individualCarePlanDModel.getIdRationCardNum() ? null : individualCarePlanDModel.getIdRationCardNum());
		individualCarePlanD.setIdRationCardProduced(individualCarePlanDModel.isIdRationCardProduced());
		individualCarePlanD.setIdSchoolCertificateNum(null == individualCarePlanDModel.getIdSchoolCertificateNum() ? null : individualCarePlanDModel.getIdSchoolCertificateNum());
		individualCarePlanD.setIdSchoolCertificateProduced(individualCarePlanDModel.isIdSchoolCertificateProduced());
		individualCarePlanD.setInteractionDetails(null == individualCarePlanDModel.getInteractionDetails() ? null : individualCarePlanDModel.getInteractionDetails());
		individualCarePlanD.setInteractionReportBy(null == individualCarePlanDModel.getInteractionReportBy() ? null : individualCarePlanDModel.getInteractionReportBy().getId());
		individualCarePlanD.setProgressMade(null == individualCarePlanDModel.getProgressMade() ? null : individualCarePlanDModel.getProgressMade());
		individualCarePlanD.setRecievedCompensation(individualCarePlanDModel.isRecievedCompensation());
		individualCarePlanD.setRecievedCompensationAction(null == individualCarePlanDModel.getRecievedCompensationAction() ? null : individualCarePlanDModel.getRecievedCompensationAction());
		individualCarePlanD.setReportOfFollowUp(null == individualCarePlanDModel.getReportOfFollowUp() ? null : individualCarePlanDModel.getReportOfFollowUp());
		individualCarePlanD.setSchoolName(null == individualCarePlanDModel.getSchoolName() ? null : individualCarePlanDModel.getSchoolName());
		individualCarePlanD.setSocialMilieu(null == individualCarePlanDModel.getSocialMilieu() ? null : individualCarePlanDModel.getSocialMilieu());
		individualCarePlanD.setStatusOfBankAccount(null == individualCarePlanDModel.getStatusOfBankAccount() ? null : individualCarePlanDModel.getStatusOfBankAccount().getId());
		individualCarePlanD.setUserIp(userDetailModel.getUserIp());
		individualCarePlanD.setCreatedBy(userDetailModel.getUserName());
		individualCarePlanD.setCreatedDate(new java.sql.Date(new Date().getTime()));
		
		individualCarePlanDRepository.save(individualCarePlanD);
	}

	@Override
	public IndividualCarePlanAModel getPersonalDetailsByChildId(String childId) {
		IndividualCarePlanA individualCarePlanA= icpPersonalDetailsRepository.getPersonalDetailsByChildId(childId);
		IndividualCarePlanAModel individualCarePlanAModel=new IndividualCarePlanAModel();
		typeMap=getTypeMap();
		if(individualCarePlanA!=null){
			individualCarePlanAModel.setAccountDetail(null==individualCarePlanA.getSavingAccountDtls()?null:individualCarePlanA.getSavingAccountDtls());
			individualCarePlanAModel.setAdmissionDate(null==individualCarePlanA.getDateOfAdmission()?null:individualCarePlanA.getDateOfAdmission());
			individualCarePlanAModel.setAdmissionNum(null==individualCarePlanA.getAdmissionNo()?null:individualCarePlanA.getAdmissionNo());
			individualCarePlanAModel.setBoardAddress(null == individualCarePlanA.getAddressBoardCommittee() ? null : individualCarePlanA.getAddressBoardCommittee());
			individualCarePlanAModel.setAge(null==individualCarePlanA.getChildAge()?null:individualCarePlanA.getChildAge());
			individualCarePlanAModel.setAwardDetails(null==individualCarePlanA.getAwardsRewardsDtls()?null:individualCarePlanA.getAwardsRewardsDtls());
			individualCarePlanAModel.setCaste(typeMap.get(null==individualCarePlanA.getCaste()?null:individualCarePlanA.getCaste()));
			individualCarePlanAModel.setCat10aoc(null==individualCarePlanA.getCat10Aoc()?null:individualCarePlanA.getCat10Aoc());
			individualCarePlanAModel.setCat10pi(null==individualCarePlanA.getCat10Pi()?null:individualCarePlanA.getCat10Pi());
			individualCarePlanAModel.setCat1aoc(null==individualCarePlanA.getCat1Aoc()?null:individualCarePlanA.getCat1Aoc());
			individualCarePlanAModel.setCat1pi(null==individualCarePlanA.getCat1Pi()?null:individualCarePlanA.getCat1Pi());
			individualCarePlanAModel.setCat2aoc(null==individualCarePlanA.getCat2Aoc()?null:individualCarePlanA.getCat2Aoc());
			individualCarePlanAModel.setCat2pi(null==individualCarePlanA.getCat2Pi()?null:individualCarePlanA.getCat2Pi());
			individualCarePlanAModel.setCat3aoc(null==individualCarePlanA.getCat3Aoc()?null:individualCarePlanA.getCat3Aoc());
			individualCarePlanAModel.setCat3pi(null==individualCarePlanA.getCat3Pi()?null:individualCarePlanA.getCat3Pi());
			individualCarePlanAModel.setCat4aoc(null==individualCarePlanA.getCat4Aoc()?null:individualCarePlanA.getCat4Aoc());
			individualCarePlanAModel.setCat4pi(null==individualCarePlanA.getCat4Pi()?null:individualCarePlanA.getCat4Pi());
			individualCarePlanAModel.setCat5aoc(null==individualCarePlanA.getCat5Aoc()?null:individualCarePlanA.getCat5Aoc());
			individualCarePlanAModel.setCat5pi(null==individualCarePlanA.getCat5Pi()?null:individualCarePlanA.getCat5Pi());
			individualCarePlanAModel.setCat6aoc(null==individualCarePlanA.getCat6Aoc()?null:individualCarePlanA.getCat6Aoc());
			individualCarePlanAModel.setCat6pi(null==individualCarePlanA.getCat6Pi()?null:individualCarePlanA.getCat6Pi());
			individualCarePlanAModel.setCat7aoc(null==individualCarePlanA.getCat7Aoc()?null:individualCarePlanA.getCat7Aoc());
			individualCarePlanAModel.setCat7pi(null==individualCarePlanA.getCat7Pi()?null:individualCarePlanA.getCat7Pi());
			individualCarePlanAModel.setCat8aoc(null==individualCarePlanA.getCat8Aoc()?null:individualCarePlanA.getCat8Aoc());
			individualCarePlanAModel.setCat8pi(null==individualCarePlanA.getCat8Pi()?null:individualCarePlanA.getCat8Pi());
			individualCarePlanAModel.setCat9aoc(null==individualCarePlanA.getCat9Aoc()?null:individualCarePlanA.getCat9Aoc());
			individualCarePlanAModel.setCat9pi(null==individualCarePlanA.getCat9Pi()?null:individualCarePlanA.getCat9Pi());
			individualCarePlanAModel.setNameOfOfficer(null==individualCarePlanA.getNameOfOfficerOrWorker()?null:individualCarePlanA.getNameOfOfficerOrWorker());
			individualCarePlanAModel.setPoliceStation(null==individualCarePlanA.getPoliceStation()?null:individualCarePlanA.getPoliceStation());
			individualCarePlanAModel.setReligion(null==individualCarePlanA.getReligion()?null:typeMap.get(individualCarePlanA.getReligion()));
			individualCarePlanAModel.setReligionOther(null == individualCarePlanA.getReligionOther() ? null : individualCarePlanA.getReligionOther());
			individualCarePlanAModel.setNationality(null==individualCarePlanA.getNationality()?null:individualCarePlanA.getNationality());
			individualCarePlanAModel.setDesignation(null==individualCarePlanA.getTypeOfOfficerOrWorker()?null:typeMap.get(individualCarePlanA.getTypeOfOfficerOrWorker()));
			individualCarePlanAModel.setStayOfChild(null==individualCarePlanA.getStayOfChild()?null:typeMap.get(individualCarePlanA.getStayOfChild()));
			individualCarePlanAModel.setEducation(null==individualCarePlanA.getLevelOfEducation()?null:typeMap.get(individualCarePlanA.getLevelOfEducation()));
			individualCarePlanAModel.setChildId(childId);
			individualCarePlanAModel.setId(null == individualCarePlanA.getId() ? null : individualCarePlanA.getId());
			individualCarePlanAModel.setMotherName(null == individualCarePlanA.getMotherName() ? null : individualCarePlanA.getMotherName());
			individualCarePlanAModel.setFatherName(null == individualCarePlanA.getFatherName() ? null : individualCarePlanA.getFatherName());
			individualCarePlanAModel.setDateOfIcp(null == individualCarePlanA.getDateOfICP() ? null : individualCarePlanA.getDateOfICP());
			individualCarePlanAModel.setDob(null == individualCarePlanA.getChildDob() ? null : individualCarePlanA.getChildDob());
			individualCarePlanAModel.setFirNo(null == individualCarePlanA.getFirNo() ? null : individualCarePlanA.getFirNo());
			individualCarePlanAModel.setLanguage(null == individualCarePlanA.getLanguageSpoken() ? null : individualCarePlanA.getLanguageSpoken());
			individualCarePlanAModel.setuSections(null == individualCarePlanA.getuSections() ? null : individualCarePlanA.getuSections());
			individualCarePlanAModel.setEarnings(null == individualCarePlanA.getChildEarnings() ? null : individualCarePlanA.getChildEarnings());
		}
		return individualCarePlanAModel;
	}

	@Override
	public List<IndividualCarePlanBModel> getProgressReports(String childId) {
		typeMap=getTypeMap();
		List<IndividualCarePlanB> icpBdata=individualCarePlanBRepository.getProgressReports(childId);
		List<IndividualCarePlanBModel> icpBlist=new ArrayList<>();
		if(!icpBdata.isEmpty()){
		for (IndividualCarePlanB individualCarePlanB : icpBdata) {
			IndividualCarePlanBModel icpBmodel=new IndividualCarePlanBModel();
			
			icpBmodel.setAddressOfParent(individualCarePlanB.getAddressOfParent());
			icpBmodel.setAdmissionNum(individualCarePlanB.getAdmissionNum());
			icpBmodel.setCat10poc(individualCarePlanB.getCat10poc());
			icpBmodel.setCat1Poc(individualCarePlanB.getCat1Poc());
			icpBmodel.setCat2poc(individualCarePlanB.getCat2poc());
			icpBmodel.setCat3poc(individualCarePlanB.getCat3poc());
			icpBmodel.setCat4poc(individualCarePlanB.getCat4poc());
			icpBmodel.setCat5poc(individualCarePlanB.getCat5poc());
			icpBmodel.setCat6poc(individualCarePlanB.getCat6poc());
			icpBmodel.setCat7poc(individualCarePlanB.getCat7poc());
			icpBmodel.setCat8poc(individualCarePlanB.getCat8poc());
			icpBmodel.setCat9poc(individualCarePlanB.getCat9poc());
			icpBmodel.setChildName(individualCarePlanB.getChildId().getChildName());
			icpBmodel.setDateOfInterview(individualCarePlanB.getDateOfInterview());
			icpBmodel.setDateOfReport(individualCarePlanB.getDateOfReport());
			icpBmodel.setGeneralConductAndProgress(individualCarePlanB.getGeneralConductAndProgress());
			icpBmodel.setNameOfOfficerOrWorker(individualCarePlanB.getNameOfOfficerOrWorker());
			icpBmodel.setNameOfParent(individualCarePlanB.getNameOfParent());
			icpBmodel.setOtherProceedings(individualCarePlanB.getOtherProceedings());
			icpBmodel.setPeriodOfReport(individualCarePlanB.getPeriodOfReport());
			icpBmodel.setPlaceOfInterview(individualCarePlanB.getPlaceOfInterview());
			icpBmodel.setProceedings(typeMap.get(individualCarePlanB.getProceedings()));
			icpBmodel.setResultOfSupervision(individualCarePlanB.getResultOfSupervision());
			icpBmodel.setSupervisionCompletionDate(individualCarePlanB.getSupervisionCompletionDate());
			icpBmodel.setTypeOfParent(typeMap.get(individualCarePlanB.getTypeOfParent()));
			icpBmodel.setTypeOfOfficerOrWorker(typeMap.get(individualCarePlanB.getTypeOfOfficerOrWorker()));
			icpBmodel.setStayOfChild(typeMap.get(individualCarePlanB.getStayOfChild()));
			icpBmodel.setChildId(childId);
			icpBmodel.setId(individualCarePlanB.getId());
		
			icpBlist.add(icpBmodel);
		}
		}
		
		
		
		return icpBlist;
	}

	@Override
	public IndividualCarePlanCModel getPreReleaseReport(String childId) throws Exception {
		typeMap=getTypeMap();
		IndividualCarePlanC icpC=individualCarePlanCRepositroy.getReportByChildId(childId);
		IndividualCarePlanCModel icpCmodel=new IndividualCarePlanCModel();
		if(icpC!=null){
		icpCmodel.setAuthorityName(null==icpC.getAuthorityName()?null:icpC.getAuthorityName());
		icpCmodel.setCat10rrpc(null==icpC.getCat10rrpc()?null:icpC.getCat10rrpc());
		icpCmodel.setCat1rrpc(null==icpC.getCat1rrpc()?null:icpC.getCat1rrpc());
		icpCmodel.setCat2rrpc(null==icpC.getCat2rrpc()?null:icpC.getCat2rrpc());
		icpCmodel.setCat3rrpc(null==icpC.getCat3rrpc()?null:icpC.getCat3rrpc());
		icpCmodel.setCat4rrpc(null==icpC.getCat4rrpc()?null:icpC.getCat4rrpc());
		icpCmodel.setCat5rrpc(null==icpC.getCat5rrpc()?null:icpC.getCat5rrpc());
		icpCmodel.setCat6rrpc(null==icpC.getCat6rrpc()?null:icpC.getCat6rrpc());
		icpCmodel.setCat7rrpc(null==icpC.getCat7rrpc()?null:icpC.getCat7rrpc());
		icpCmodel.setCat8rrpc(null==icpC.getCat8rrpc()?null:icpC.getCat8rrpc());
		icpCmodel.setCat9rrpc(null==icpC.getCat9rrpc()?null:icpC.getCat9rrpc());
		icpCmodel.setChildId(childId);
		icpCmodel.setDateOfOrder(null==icpC.getDateOfOrder()?null:icpC.getDateOfOrder());
		icpCmodel.setDetailOfSponsors(null==icpC.getDetailOfSponsors()?null:icpC.getDetailOfSponsors());
		icpCmodel.setId(icpC.getId());
		icpCmodel.setIdentificationProofNumber(null==icpC.getIdentificationProofNumber()?null:icpC.getIdentificationProofNumber());
		icpCmodel.setIdentificationProofType(null==icpC.getIdentificationProofType()?null:typeMap.get(icpC.getIdentificationProofType()));
		icpCmodel.setInstitutionDetails(null==icpC.getInstitutionDetails()?null:icpC.getInstitutionDetails());
		icpCmodel.setMedicalReport(exportPDFServiceImpl.getPdf(icpC.getMedicalReportPath()));
		icpCmodel.setMouOfSponsors(exportPDFServiceImpl.getPdf(icpC.getMouOfSponsorsPath()));
		icpCmodel.setMouWithNgo(exportPDFServiceImpl.getPdf(icpC.getMouWithNgoPath()));
		icpCmodel.setOfficerOrNgoName(null==icpC.getOfficerOrNgoName()?null:icpC.getOfficerOrNgoName());
		icpCmodel.setOrderType(null==icpC.getOrderType()?null:typeMap.get(icpC.getOrderType()));
		icpCmodel.setOtherIdentificationProof(null==icpC.getOtherIdentificationProof()?null:icpC.getOtherIdentificationProof());
		icpCmodel.setOtherInfo(null==icpC.getOtherInfo()?null:icpC.getOtherInfo());
		icpCmodel.setPlaceOfTransfer(null==icpC.getPlaceOfTransfer()?null:icpC.getPlaceOfTransfer());
		icpCmodel.setPostReleaseFollowupBy(null==icpC.getPostReleaseFollowupBy()?null:icpC.getPostReleaseFollowupBy());
		icpCmodel.setRehabilitationPlan(null==icpC.getRehabilitationPlan()?null:icpC.getRehabilitationPlan());
		icpCmodel.setRequisitionForEscort(null==icpC.getRequisitionForEscort()?null:icpC.getRequisitionForEscort());
		icpCmodel.setSkillsAcquired(null==icpC.getSkillsAcquired()?null:icpC.getSkillsAcquired());
		icpCmodel.setTypeOfOrder(null==icpC.getTypeOfOrder()?null:typeMap.get(icpC.getTypeOfOrder()));
		}
		return icpCmodel;
	}
	

	@Override
	public IndividualCarePlanDModel getPostReleaseReport(String childId) {
		typeMap=getTypeMap();
		IndividualCarePlanD individualCarePlanD = individualCarePlanDRepository.getPostReleaseReportByChildId(childId);
		IndividualCarePlanDModel individualCarePlanDModel = new IndividualCarePlanDModel();
		if(individualCarePlanD!=null){
		individualCarePlanDModel.setAdmissionDate(null==individualCarePlanD.getAdmissionDate()?null:individualCarePlanD.getAdmissionDate());
		individualCarePlanDModel.setBelongingsHandedToParents(null==individualCarePlanD.getBelongingsHandedToParents()?null:typeMap.get(individualCarePlanD.getBelongingsHandedToParents()));
		individualCarePlanDModel.setChildId(null==individualCarePlanD.getChildId().getChildId()?null:individualCarePlanD.getChildId().getChildId());
		individualCarePlanDModel.setChildView(null==individualCarePlanD.getChildView()?null:individualCarePlanD.getChildView());
		individualCarePlanDModel.setChildWentToLearningInstitueType(null==individualCarePlanD.getChildWentToLearningInstitueType()?null:typeMap.get(individualCarePlanD.getChildWentToLearningInstitueType()));
		individualCarePlanDModel.setFamilyBehaviour(null==individualCarePlanD.getFamilyBehaviour()?null:individualCarePlanD.getFamilyBehaviour());
		individualCarePlanDModel.setHowUsingSkills(null==individualCarePlanD.getHowUsingSkills()?null:individualCarePlanD.getHowUsingSkills());
		individualCarePlanDModel.setIdAdhaarCardProduced(individualCarePlanD.isIdAdhaarCardProduced());
		individualCarePlanDModel.setIdAdhaarCardNum(null==individualCarePlanD.getIdAdhaarCardNum()?null:individualCarePlanD.getIdAdhaarCardNum());
		individualCarePlanDModel.setIdBirthCertificateProduced(individualCarePlanD.isIdBirthCertificateProduced());
		individualCarePlanDModel.setIdBirthCertificateNum(null==individualCarePlanD.getIdBirthCertificateNum()?null:individualCarePlanD.getIdBirthCertificateNum());
		individualCarePlanDModel.setIdBplCardProduced(individualCarePlanD.isIdBplCardProduced());
		individualCarePlanDModel.setIdBplCardNum(null==individualCarePlanD.getIdBplCardNum()?null:individualCarePlanD.getIdBplCardNum());
		individualCarePlanDModel.setIdCasteCertificateProduced(individualCarePlanD.isIdCasteCertificateProduced());
		individualCarePlanDModel.setIdCasteCertificateNum(null==individualCarePlanD.getIdCasteCertificateNum()?null:individualCarePlanD.getIdCasteCertificateNum());
		individualCarePlanDModel.setIdDisabiltyCertificateProduced(individualCarePlanD.isIdDisabiltyCertificateProduced());
		individualCarePlanDModel.setIdDisabiltyCertificateNum(null==individualCarePlanD.getIdDisabiltyCertificateNum()?null:individualCarePlanD.getIdDisabiltyCertificateNum());
		individualCarePlanDModel.setIdImmunizationCardProduced(individualCarePlanD.isIdImmunizationCardProduced());
		individualCarePlanDModel.setIdImmunizationCardNum(null==individualCarePlanD.getIdImmunizationCardNum()?null:individualCarePlanD.getIdImmunizationCardNum());
		individualCarePlanDModel.setIdRationCardProduced(individualCarePlanD.isIdRationCardProduced());
		individualCarePlanDModel.setIdRationCardNum(null==individualCarePlanD.getIdRationCardNum()?null:individualCarePlanD.getIdRationCardNum());
		individualCarePlanDModel.setIdSchoolCertificateProduced(individualCarePlanD.isIdSchoolCertificateProduced());
		individualCarePlanDModel.setIdSchoolCertificateNum(null==individualCarePlanD.getIdSchoolCertificateNum()?null:individualCarePlanD.getIdSchoolCertificateNum());
		individualCarePlanDModel.setInteractionDetails(null==individualCarePlanD.getInteractionDetails()?null:individualCarePlanD.getInteractionDetails());
		individualCarePlanDModel.setInteractionReportBy(null==individualCarePlanD.getInteractionReportBy()?null:typeMap.get(individualCarePlanD.getInteractionReportBy()));
		individualCarePlanDModel.setProgressMade(null==individualCarePlanD.getProgressMade()?null:individualCarePlanD.getProgressMade());
		individualCarePlanDModel.setRecievedCompensation(individualCarePlanD.isRecievedCompensation());
		individualCarePlanDModel.setRecievedCompensationAction(null==individualCarePlanD.getRecievedCompensationAction()?null:individualCarePlanD.getRecievedCompensationAction());
		individualCarePlanDModel.setReportOfFollowUp(null==individualCarePlanD.getReportOfFollowUp()?null:individualCarePlanD.getReportOfFollowUp());
		individualCarePlanDModel.setSchoolName(null==individualCarePlanD.getSchoolName()?null:individualCarePlanD.getSchoolName());
		individualCarePlanDModel.setSocialMilieu(null==individualCarePlanD.getSocialMilieu()?null:individualCarePlanD.getSocialMilieu());
		individualCarePlanDModel.setStatusOfBankAccount(null==individualCarePlanD.getStatusOfBankAccount()?null:typeMap.get(individualCarePlanD.getStatusOfBankAccount()));
		individualCarePlanDModel.setId(individualCarePlanD.getId());
		}
		
		return individualCarePlanDModel;
	}


}
