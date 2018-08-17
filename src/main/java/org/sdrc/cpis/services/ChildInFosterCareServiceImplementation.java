package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.domains.ChildInFosterCare;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.ChildInFosterCareModel;
import org.sdrc.cpis.models.DetailsOfPlacementModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.ChildInFosterCareRepository;
import org.sdrc.cpis.repository.DetailsOfPlacementRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class ChildInFosterCareServiceImplementation implements ChildInFosterCareService {
	
	@Autowired
	private ChildInFosterCareRepository childInFosterCareRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private CCTSChildRegistrationService cctsChildRegistrationService;
	
	@Autowired
	private DetailsOfPlacementRepository detailsOfPlacementRepository;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	@Autowired
	private CCIInfoRepository cciInfoRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private StateManager stateManager;

	@Override
	public void saveChildInFosterCare(ChildInFosterCareModel childInFosterCareModel) throws Exception {
		
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ChildInFosterCare childInFosterCare = new ChildInFosterCare();
		ChildDetails childDetails = new ChildDetails();
		
		childDetails = childDetailsRepository.findChildById(childInFosterCareModel.getChildId());
		
//		if(childInFosterCareModel.getDetailsOfPlacementList() != null && childInFosterCareModel.getDetailsOfPlacementList().size() > 0){
//			List<DetailsOfPlacement> detailsList = new ArrayList<DetailsOfPlacement>();
//			
//			for(DetailsOfPlacementModel detailsOfPlacementModel : childInFosterCareModel.getDetailsOfPlacementList()){
//				DetailsOfPlacement detailsOfPlacement = new DetailsOfPlacement();
//				
//				detailsOfPlacement.setChildRecord(childDetails);
//				detailsOfPlacement.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
//				detailsOfPlacement.setCreatedDate(new java.sql.Date(new Date().getTime()));
//				detailsOfPlacement.setDate(detailsOfPlacementModel.getDate() == null ? null : detailsOfPlacementModel.getDate());
//				detailsOfPlacement.setIndividualOrGroup(detailsOfPlacementModel.getIndividualOrGroup() == null ? null : detailsOfPlacementModel.getIndividualOrGroup());
//				detailsOfPlacement.setIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
//				detailsOfPlacement.setPeriodOfPlacement(detailsOfPlacementModel.getPeriodOfPlacement() == null ? null : detailsOfPlacementModel.getPeriodOfPlacement());
//				
//				detailsList.add(detailsOfPlacement);
//			}
//			
//			detailsOfPlacementRepository.save(detailsList);
//		}
		
		childInFosterCare.setAcademicProgress(null == childInFosterCareModel.getAcademicProgress() ? null : childInFosterCareModel.getAcademicProgress());
		
		childInFosterCare.setBiologicalParentImage(null == childInFosterCareModel.getBiologicalParentImage() || childInFosterCareModel.getBiologicalParentImage() == "" ? 
			null : exportPDFServiceImpl.getPhotoPath(childInFosterCareModel.getBiologicalParentImage(), "","ChildInFosterCare"));
		
		childInFosterCare.setCaseWorkerName(null == childInFosterCareModel.getCaseWorkerName() ? null : childInFosterCareModel.getCaseWorkerName());
		childInFosterCare.setCciAddress(null == childInFosterCareModel.getCciAddress() ? null : childInFosterCareModel.getCciAddress());
		childInFosterCare.setCciId(null == childInFosterCareModel.getCciObject() ? null : childInFosterCareModel.getCciObject().getCciId());
		childInFosterCare.setChangesInFamilyEnvironment(null == childInFosterCareModel.getChangesInFamilyEnvironment() ? null : childInFosterCareModel.getChangesInFamilyEnvironment());
		childInFosterCare.setChildDetailsInFosterCare(null == childInFosterCareModel.getChildDetailsInFosterCare() ? null : childInFosterCareModel.getChildDetailsInFosterCare());
		childInFosterCare.setChildHandedOverDate(null == childInFosterCareModel.getChildHandedOverDate() ? null : childInFosterCareModel.getChildHandedOverDate());
		
		childInFosterCare.setChildImage(null == childInFosterCareModel.getChildImage() || childInFosterCareModel.getChildImage() == "" ? null :
			exportPDFServiceImpl.getPhotoPath(childInFosterCareModel.getChildImage(), childInFosterCareModel.getChildId(),"ChildInFosterCare"));
		
		childInFosterCare.setChildRecord(childDetails);
		
		childInFosterCare.setChildStudyReport(null == childInFosterCareModel.getChildStudyReport() || "" == childInFosterCareModel.getChildStudyReport() ? null :
			exportPDFServiceImpl.getFileName(childInFosterCareModel.getChildStudyReport(), "ChildInFosterCare", applicationMessageSource.getMessage("store.ChildInFosterCare", null, null,null)));
		
		childInFosterCare.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		childInFosterCare.setCreatedDate(new java.sql.Date(new Date().getTime()));
		childInFosterCare.setDateForExtension(null == childInFosterCareModel.getDateForExtension() ? null : childInFosterCareModel.getDateForExtension());
		childInFosterCare.setDateForTermination(null == childInFosterCareModel.getDateForTermination() ? null : childInFosterCareModel.getDateForTermination());
		childInFosterCare.setDevelopmentalMilestones(null == childInFosterCareModel.getDevelopmentalMilestones() ? null : childInFosterCareModel.getDevelopmentalMilestones());
		childInFosterCare.setExtentQualityCompliance(null == childInFosterCareModel.getExtentQualityCompliance() ? null : childInFosterCareModel.getExtentQualityCompliance());
		childInFosterCare.setFinancialAssistance(null == childInFosterCareModel.getFinancialAssistance() ? null : childInFosterCareModel.getFinancialAssistance());
		
		childInFosterCare.setFosterCareParentImage(null == childInFosterCareModel.getFosterCareParentImage() || childInFosterCareModel.getFosterCareParentImage() == "" ?
			null : exportPDFServiceImpl.getPhotoPath(childInFosterCareModel.getFosterCareParentImage(), "_","ChildInFosterCare"));
		
		childInFosterCare.setHsrBiologicalFamily(null == childInFosterCareModel.getHsrBiologicalFamily() || "" == childInFosterCareModel.getHsrBiologicalFamily() ? null
			: exportPDFServiceImpl.getFileName(childInFosterCareModel.getHsrBiologicalFamily(),"ChildInFosterCare", applicationMessageSource.getMessage("store.ChildInFosterCare", null, null,null)));
		
		childInFosterCare.setHsrFosterFamily(null == childInFosterCareModel.getHsrFosterFamily() || "" == childInFosterCareModel.getHsrFosterFamily() ? null :
			exportPDFServiceImpl.getFileName(childInFosterCareModel.getHsrFosterFamily(),"ChildInFosterCare", applicationMessageSource.getMessage("store.ChildInFosterCare", null, null,null)));
		
		childInFosterCare.setIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		childInFosterCare.setObservation(null == childInFosterCareModel.getObservation() ? null : childInFosterCareModel.getObservation());
		childInFosterCare.setOtherSourceOfReferral(null == childInFosterCareModel.getOtherSourceOfReferral() ? null : childInFosterCareModel.getOtherSourceOfReferral());
		childInFosterCare.setReasonForExtension(null == childInFosterCareModel.getReasonForExtension() ? null : childInFosterCareModel.getReasonForExtension());
		childInFosterCare.setReasonForTermination(null == childInFosterCareModel.getReasonForTermination() ? null : childInFosterCareModel.getReasonForTermination());
		childInFosterCare.setRecordOfEachVisit(null == childInFosterCareModel.getRecordOfEachVisit() ? null : childInFosterCareModel.getRecordOfEachVisit());
		
		childInFosterCare.setRecordOfEachVisitFile(null == childInFosterCareModel.getRecordOfEachVisitFile() || "" == childInFosterCareModel.getRecordOfEachVisitFile() ?
			null : exportPDFServiceImpl.getFileName(childInFosterCareModel.getRecordOfEachVisitFile(),"ChildInFosterCare", applicationMessageSource.getMessage("store.ChildInFosterCare", null, null,null)));
		
		childInFosterCare.setCwcAddress(childInFosterCareModel.getCwcAddress() == null ? null : childInFosterCareModel.getCwcAddress());
		
		childInFosterCareRepository.save(childInFosterCare);
		
	}

	@Override
	public ChildInFosterCareModel getChildInFosterCare(String childId) throws Exception {
		ChildInFosterCare childInFosterCare = new ChildInFosterCare();
		ChildInFosterCareModel childInFosterCareModel = new ChildInFosterCareModel();
		CCIDetails cciDetails = new CCIDetails();
		CCIInfoMapModel cciInfoMapModel = new CCIInfoMapModel();
//		List<DetailsOfPlacement> detailsOfPlacementList = new ArrayList<DetailsOfPlacement>();
		List<DetailsOfPlacementModel> detailsOfPlacementModelList = new ArrayList<DetailsOfPlacementModel>();
		
//		detailsOfPlacementList = detailsOfPlacementRepository.findDetailsByChildId(childId); 
		childInFosterCare = childInFosterCareRepository.findByChildRecordChildId(childId);
		
		
		if(childInFosterCare != null){
			if(childInFosterCare.getCciId() != null){
				cciDetails = cciInfoRepository.getCciById(childInFosterCare.getCciId());
				
				cciInfoMapModel.setName(cciDetails.getCciName());
				cciInfoMapModel.setCciId(cciDetails.getCciId());
				cciInfoMapModel.setAddress(cciDetails.getAddress());
			}
			
//			if(detailsOfPlacementList != null && detailsOfPlacementList.size() > 0){
//				for(DetailsOfPlacement detailsOfPlacement : detailsOfPlacementList){
//					DetailsOfPlacementModel detailsOfPlacementModel = new DetailsOfPlacementModel();
//					
//					detailsOfPlacementModel.setIndividualOrGroup(detailsOfPlacement.getIndividualOrGroup());
//					detailsOfPlacementModel.setDate(detailsOfPlacement.getDate());
//					detailsOfPlacementModel.setPeriodOfPlacement(detailsOfPlacement.getPeriodOfPlacement());
//					
//					detailsOfPlacementModelList.add(detailsOfPlacementModel);
//				}
//				
//			}
			
			childInFosterCareModel.setAcademicProgress(childInFosterCare.getAcademicProgress());
			
			childInFosterCareModel.setBiologicalParentImage(childInFosterCare.getBiologicalParentImage() == null ? null : 
				exportPDFServiceImpl.getChildPhoto(childInFosterCare.getBiologicalParentImage()));
			
			childInFosterCareModel.setCaseWorkerName(childInFosterCare.getCaseWorkerName());
			childInFosterCareModel.setCciAddress(childInFosterCare.getCciAddress());
			childInFosterCareModel.setCciObject(cciInfoMapModel);
			childInFosterCareModel.setChangesInFamilyEnvironment(childInFosterCare.getChangesInFamilyEnvironment());
			childInFosterCareModel.setChildDetailsInFosterCare(childInFosterCare.getChildDetailsInFosterCare());
			childInFosterCareModel.setChildHandedOverDate(childInFosterCare.getChildHandedOverDate());
			
			childInFosterCareModel.setChildImage(childInFosterCare.getChildImage() == null ? null : 
				exportPDFServiceImpl.getChildPhoto(childInFosterCare.getChildImage()));
			
			childInFosterCareModel.setChildId(childInFosterCare.getChildRecord().getChildId());
			
			childInFosterCareModel.setChildStudyReport(childInFosterCare.getChildStudyReport() == null ? null :
				exportPDFServiceImpl.getPdf(childInFosterCare.getChildStudyReport()));
			
			childInFosterCareModel.setCreatedBy(childInFosterCare.getCreatedBy());
			childInFosterCareModel.setCreatedDate(childInFosterCare.getCreatedDate());
			childInFosterCareModel.setDateForExtension(childInFosterCare.getDateForExtension());
			childInFosterCareModel.setDateForTermination(childInFosterCare.getDateForTermination());
			childInFosterCareModel.setDevelopmentalMilestones(childInFosterCare.getDevelopmentalMilestones());
			childInFosterCareModel.setDetailsOfPlacementList(detailsOfPlacementModelList);
			childInFosterCareModel.setExtentQualityCompliance(childInFosterCare.getExtentQualityCompliance());
			childInFosterCareModel.setFinancialAssistance(childInFosterCare.getFinancialAssistance());
			
			childInFosterCareModel.setFosterCareParentImage(childInFosterCare.getFosterCareParentImage() == null ? null :
				exportPDFServiceImpl.getChildPhoto(childInFosterCare.getFosterCareParentImage()));
			
			childInFosterCareModel.setHsrBiologicalFamily(childInFosterCare.getHsrBiologicalFamily() == null ? null :
				exportPDFServiceImpl.getPdf(childInFosterCare.getHsrBiologicalFamily()));
			
			childInFosterCareModel.setHsrFosterFamily(childInFosterCare.getHsrFosterFamily() == null ? null :
				exportPDFServiceImpl.getPdf(childInFosterCare.getHsrFosterFamily()));
			
			childInFosterCareModel.setId(childInFosterCare.getId());
			childInFosterCareModel.setIp(childInFosterCare.getIp());
			childInFosterCareModel.setObservation(childInFosterCare.getObservation());
			childInFosterCareModel.setOtherSourceOfReferral(childInFosterCare.getOtherSourceOfReferral());
			childInFosterCareModel.setReasonForExtension(childInFosterCare.getReasonForExtension());
			childInFosterCareModel.setReasonForTermination(childInFosterCare.getReasonForTermination());
			childInFosterCareModel.setRecordOfEachVisit(childInFosterCare.getRecordOfEachVisit());
			
			childInFosterCareModel.setRecordOfEachVisitFile(childInFosterCare.getRecordOfEachVisitFile() == null ? null :
				exportPDFServiceImpl.getPdf(childInFosterCare.getRecordOfEachVisitFile()));
			
			childInFosterCareModel.setCwcAddress(childInFosterCare.getCwcAddress());
			childInFosterCareModel.setCciName(cciInfoMapModel.getName());
			
			return childInFosterCareModel;
		}else
			return null;
		
	}

}
