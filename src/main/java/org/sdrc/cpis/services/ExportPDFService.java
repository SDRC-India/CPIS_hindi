package org.sdrc.cpis.services;

import org.sdrc.cpis.models.AfterCarePlacementOrderModel;
import org.sdrc.cpis.models.CCIHumanResourceModel;
import org.sdrc.cpis.models.CCIHumanResourceOSModel;
import org.sdrc.cpis.models.CCIHumanResourceSAAModel;
import org.sdrc.cpis.models.CCTSChildRegistrationModel;
import org.sdrc.cpis.models.CICLCaseMoniteringSheetModel;
import org.sdrc.cpis.models.CICLChildCareInstitutionPendingInquiryModel;
import org.sdrc.cpis.models.CICLPeriodicReportModel;
import org.sdrc.cpis.models.CICLSocialBackgroundReportModel;
import org.sdrc.cpis.models.CICLSocialInvestigationReportModel;
import org.sdrc.cpis.models.CICLSupervisionOrderModel;
import org.sdrc.cpis.models.CaseHistoryCCIModel;
import org.sdrc.cpis.models.CaseMonitoringModel;
import org.sdrc.cpis.models.CaseSummaryCWCModel;
import org.sdrc.cpis.models.ChildInFitInstitutionModel;
import org.sdrc.cpis.models.ChildInFosterCareModel;
import org.sdrc.cpis.models.ConstitutionOfBCPCModel;
import org.sdrc.cpis.models.ConstitutionOfCWCModel;
import org.sdrc.cpis.models.ConstitutionOfDCPCModel;
import org.sdrc.cpis.models.ConstitutionOfJJBModel;
import org.sdrc.cpis.models.ConstitutionOfSJPUModel;
import org.sdrc.cpis.models.DCPUHRDetailsModel;
import org.sdrc.cpis.models.FitPersonDetailModel;
import org.sdrc.cpis.models.FollowUpFormModel;
import org.sdrc.cpis.models.FosterCareDetailsModel;
import org.sdrc.cpis.models.IndividualCarePlanAModel;
import org.sdrc.cpis.models.IndividualCarePlanBModel;
import org.sdrc.cpis.models.IndividualCarePlanCModel;
import org.sdrc.cpis.models.IndividualCarePlanDModel;
import org.sdrc.cpis.models.InfrastructureCCIModel;
import org.sdrc.cpis.models.InfrastructureOSModel;
import org.sdrc.cpis.models.InfrastructureSAAModel;
import org.sdrc.cpis.models.LegallyFreeForAdoptionModel;
import org.sdrc.cpis.models.RestorationModel;
import org.sdrc.cpis.models.SocialinvestigationReportModel;
import org.sdrc.cpis.models.SponsorshipModel;

public interface ExportPDFService {
	
	String generatePDFDataReportForFitInstitution(ChildInFitInstitutionModel chidlInFitInstitutionModel, String type) throws Exception;

	String generatePDFDataReportForFitPerson(FitPersonDetailModel fitPersonDetailModel, String type) throws Exception;

	String generatePDFDataReportForFosterCare(FosterCareDetailsModel fosterCareDetailsModel, String type) throws Exception;

	String generatePDFDataReportForICPAPersonDetails(IndividualCarePlanAModel individualCarePlanAModel, String type) throws Exception;

	String generatePDFDataReportForICPBPersonDetails(IndividualCarePlanBModel individualCarePlanBModel, String type) throws Exception;

	String generatePDFDataReportForICPCPersonDetails(IndividualCarePlanCModel individualCarePlanCModel, String type) throws Exception;

	String generatePDFDataReportForICPDPersonDetails(IndividualCarePlanDModel individualCarePlanDModel, String type) throws Exception;

	String generatePDFReport(CCTSChildRegistrationModel cctsChildRegistrationModel, String type) throws Exception;

	String generatePDFDataReportForCaseMonitoringDetails(CaseMonitoringModel caseMonitoringModel, String type) throws Exception;

	String generatePDFDataReportForSocialInvestigation(SocialinvestigationReportModel socialinvestigationReportModel, String type) throws Exception;

	String generatePDFDataReportForCaseSummary(CaseSummaryCWCModel caseSummaryCWCModel, String type) throws Exception;

	String downloadPDFTemplate(String fileName, String typeOfPdf, String typeOfLanguage) throws Exception;

	String generatePDFDataReportForCaseHistoryCCI(CaseHistoryCCIModel caseHistoryCCIModel, String type) throws Exception;

	String generatePDFDataReportForSponsorship(SponsorshipModel sponsorshipModel, String type) throws Exception;

	String generatePDFDataReportForAfterCare(AfterCarePlacementOrderModel afterCarePlacementOrderModel, String type) throws Exception;

	String generatePDFDataReportForRestoration(RestorationModel restorationModel, String type) throws Exception;

	String generatePDFDataForCICLSBR(CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel, String type) throws Exception;

	String generatePDFDataForCICLSupervisionOrder(CICLSupervisionOrderModel ciclSupervisionOrderModel, String type) throws Exception;

	String generatePDFDataForCICLChildCareIPI(CICLChildCareInstitutionPendingInquiryModel ciclChildCareInstitutionPendingInquiryModel, String type) throws Exception;

	String generatePDFDataForCICLCaseMoniteringSheet(CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel, String type) throws Exception;

	String generatePDFDataForCICLPeriodicReport(CICLPeriodicReportModel ciclPeriodicReportModel, String type) throws Exception;

	String generatePDFDataForCICLSIR(CICLSocialInvestigationReportModel ciclSocialInvestigationReportModel, String type) throws Exception;
	
	String getChildPhoto(String childPhoto) throws Exception;
	
	String getPhotoPath(String childImage, String childId, String type) throws Exception;
	
	String getFileName(String data, String type, String path) throws Exception;

	String getPdf(String pdfString) throws Exception;

	String generatePDFDataForChildInFosterCare(ChildInFosterCareModel childInFosterCareModel, String type) throws Exception;

	String generatePDFDataForLegallyFreeForAdoption(LegallyFreeForAdoptionModel legallyFreeForAdoptionModel, String type) throws Exception;

	String generatePDFDataForConstitutionOfCWC(ConstitutionOfCWCModel constitutionOfCWCModel, String type) throws Exception;

	String generatePDFDataForconstitutionOfJJB(ConstitutionOfJJBModel constitutionOfJJBModel, String type) throws Exception;

	String generatePDFDataConstitutionOfSJPU(ConstitutionOfSJPUModel constitutionOfSJPUModel, String type) throws Exception;

	String generatePDFDataConstitutionOfDCPC(ConstitutionOfDCPCModel constitutionOfDCPCModel, String type) throws Exception;

	String generatePDFDataHumanResourceDetailsHRCCI(CCIHumanResourceModel cciHumanResourceModel, String type) throws Exception;
	
	String generatePDFDataHumanResourceDetailsHRSAA(CCIHumanResourceSAAModel cciHumanResourceSAAModel, String type) throws Exception;

	String generatePDFDataHumanResourceDetailsHROS(CCIHumanResourceOSModel cciHumanResourceOSModel, String type) throws Exception;

	String generatePDFDataInfrastructureCCI(InfrastructureCCIModel infrastructureCCIModel, String type) throws Exception;

	String generatePDFDataInfrastructureSAA(InfrastructureSAAModel infrastructureSAAModel, String type) throws Exception;

	String generatePDFDataInfrastructureOS(InfrastructureOSModel infrastructureOSModel, String type) throws Exception;

	String generatePDFDataDCPUHRDetails(DCPUHRDetailsModel dcpuhrDetailsModel, String type) throws Exception;

	String generatePDFDataConstitutionOfBCPC(ConstitutionOfBCPCModel constitutionOfBCPCModel, String type) throws Exception;

	String generatePDFDataFollowUpForm(FollowUpFormModel followUpFormModel, String type) throws Exception;

}
