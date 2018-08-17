package org.sdrc.cpis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import org.sdrc.cpis.services.ExportPDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExportPDFController {

	@Autowired 
	ExportPDFService exportPDFService;
	
	//=========================download PDF=====================
	@RequestMapping(value="/downloadPDFDataReport", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReport(@RequestBody CCTSChildRegistrationModel cctsChildRegistrationModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFReport(cctsChildRegistrationModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForFitInstitution", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForFitInstitution(@RequestBody ChildInFitInstitutionModel chidlInFitInstitutionModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForFitInstitution(chidlInFitInstitutionModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForFitPerson", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForFitPerson(@RequestBody FitPersonDetailModel fitPersonDetailModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForFitPerson(fitPersonDetailModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForFosterCare", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForFosterCare(@RequestBody FosterCareDetailsModel fosterCareDetailsModel,@RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForFosterCare(fosterCareDetailsModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForICPAPersonDetails", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForICPAPersonDetails(@RequestBody IndividualCarePlanAModel individualCarePlanAModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForICPAPersonDetails(individualCarePlanAModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForICPBPersonDetails", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForICPBPersonDetails(@RequestBody IndividualCarePlanBModel individualCarePlanBModel,@RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForICPBPersonDetails(individualCarePlanBModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForICPCPersonDetails", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForICPCPersonDetails(@RequestBody IndividualCarePlanCModel individualCarePlanCModel,@RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForICPCPersonDetails(individualCarePlanCModel,type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForICPDPersonDetails", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForICPDPersonDetails(@RequestBody IndividualCarePlanDModel individualCarePlanDModel,@RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForICPDPersonDetails(individualCarePlanDModel,type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForCaseMonitoringDetails", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForCaseMonitoringDetails(@RequestBody CaseMonitoringModel caseMonitoringModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForCaseMonitoringDetails(caseMonitoringModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForSocialInvestigation", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForSocialInvestigation(@RequestBody SocialinvestigationReportModel socialinvestigationReportModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForSocialInvestigation(socialinvestigationReportModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForCaseSummary", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForCaseSummary(@RequestBody CaseSummaryCWCModel caseSummaryCWCModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForCaseSummary(caseSummaryCWCModel, type);
	}
	
	@RequestMapping(value="/downloadPDFTemplate", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFTemplate(@RequestParam("fileName") String fileName, @RequestParam("typeOfPdf") String typeOfPdf, @RequestParam("typeOfLanguage") String typeOfLanguage) throws Exception{
		return exportPDFService.downloadPDFTemplate(fileName, typeOfPdf, typeOfLanguage);
	}
	
	@RequestMapping(value="/downloadPDFDataReportForCaseHistoryCCI", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataReportForCaseHistoryCCI(@RequestBody CaseHistoryCCIModel caseHistoryCCIModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForCaseHistoryCCI(caseHistoryCCIModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataSponsorship", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataSponsorship(@RequestBody SponsorshipModel sponsorshipModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForSponsorship(sponsorshipModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataAfterCare", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataAfterCare(@RequestBody AfterCarePlacementOrderModel afterCarePlacementOrderModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForAfterCare(afterCarePlacementOrderModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataRestoration", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataRestoration(@RequestBody RestorationModel restorationModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataReportForRestoration(restorationModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForCICLSupervisionOrder", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForCICLSupervisionOrder(@RequestBody CICLSupervisionOrderModel ciclSupervisionOrderModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForCICLSupervisionOrder(ciclSupervisionOrderModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForCICLChildCareIPI", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForCICLChildCareIPI(@RequestBody CICLChildCareInstitutionPendingInquiryModel ciclChildCareInstitutionPendingInquiryModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForCICLChildCareIPI(ciclChildCareInstitutionPendingInquiryModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForCICLCaseMonitering", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForCICLCaseMoniteringSheet(@RequestBody CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForCICLCaseMoniteringSheet(ciclCaseMoniteringSheetModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForCICLPeriodicReport", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForCICLPeriodicReport(@RequestBody CICLPeriodicReportModel ciclPeriodicReportModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForCICLPeriodicReport(ciclPeriodicReportModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForCICLSBR", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForCICLSBR(@RequestBody CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForCICLSBR(ciclSocialBackgroundReportModel,type);
	}
	
	@RequestMapping(value="/downloadPDFDataForCICLSIR", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForCICLSIR(@RequestBody CICLSocialInvestigationReportModel ciclSocialInvestigationReportModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForCICLSIR(ciclSocialInvestigationReportModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForChildInFosterCare", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForChildInFosterCare(@RequestBody ChildInFosterCareModel childInFosterCareModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForChildInFosterCare(childInFosterCareModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForLegallyFreeForAdoption", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForLegallyFreeForAdoption(@RequestBody LegallyFreeForAdoptionModel legallyFreeForAdoptionModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForLegallyFreeForAdoption(legallyFreeForAdoptionModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForConstitutionOfCWC", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForConstitutionOfCWC(@RequestBody ConstitutionOfCWCModel constitutionOfCWCModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForConstitutionOfCWC(constitutionOfCWCModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForConstitutionOfJJB", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForConstitutionOfJJB(@RequestBody ConstitutionOfJJBModel constitutionOfJJBModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataForconstitutionOfJJB(constitutionOfJJBModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForConstitutionOfSJPU", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForConstitutionOfSJPU(@RequestBody ConstitutionOfSJPUModel constitutionOfSJPUModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataConstitutionOfSJPU(constitutionOfSJPUModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForConstitutionOfDCPC", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForConstitutionOfDCPC(@RequestBody ConstitutionOfDCPCModel constitutionOfDCPCModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataConstitutionOfDCPC(constitutionOfDCPCModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForHumanResourceDetailsHRCCI", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForHumanResourceDetailsHRCCI(@RequestBody CCIHumanResourceModel cciHumanResourceModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataHumanResourceDetailsHRCCI(cciHumanResourceModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForHumanResourceDetailsHRSAA", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForHumanResourceDetailsHRSAA(@RequestBody CCIHumanResourceSAAModel cciHumanResourceSAAModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataHumanResourceDetailsHRSAA(cciHumanResourceSAAModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForHumanResourceDetailsHROS", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForHumanResourceDetailsHROS(@RequestBody CCIHumanResourceOSModel cciHumanResourceOSModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataHumanResourceDetailsHROS(cciHumanResourceOSModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForInfraCCI", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForInfrastructureCCI(@RequestBody InfrastructureCCIModel infrastructureCCIModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataInfrastructureCCI(infrastructureCCIModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForInfraSAA", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForInfrastructureSAA(@RequestBody InfrastructureSAAModel infrastructureSAAModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataInfrastructureSAA(infrastructureSAAModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForInfraOS", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForInfrastructureOS(@RequestBody InfrastructureOSModel infrastructureOSModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataInfrastructureOS(infrastructureOSModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataForDCPUHRDetails", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForDCPUHRDetails(@RequestBody DCPUHRDetailsModel dcpuhrDetailsModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataDCPUHRDetails(dcpuhrDetailsModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataConstitutionOfBCPC", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForConstitutionOfBCPC(@RequestBody ConstitutionOfBCPCModel constitutionOfBCPCModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataConstitutionOfBCPC(constitutionOfBCPCModel, type);
	}
	
	@RequestMapping(value="/downloadPDFDataFollowUpForm", method=RequestMethod.POST)
	@ResponseBody
	public String downloadPDFDataForFollowUpForm(@RequestBody FollowUpFormModel followUpFormModel, @RequestParam("type") String type) throws Exception{
		return exportPDFService.generatePDFDataFollowUpForm(followUpFormModel, type);
	}
	
	// ===============for download pdf file=================
	@RequestMapping(value = "/downloadFile", method=RequestMethod.POST)
	public void downLoad(@RequestParam("fileName") String name,HttpServletResponse response) throws IOException {
		InputStream inputStream;
		String fileName = "";
		try {
			fileName=name.replaceAll("%3A", ":").replaceAll("%2F", "/")
						 .replaceAll("%5C", "/").replaceAll("%2C",",")
						 .replaceAll("\\+", " ").replaceAll("%22", "")
						 .replaceAll("%3F", "?").replaceAll("%3D", "=");
			inputStream = new FileInputStream(fileName);
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					new java.io.File(fileName).getName());
			response.setHeader(headerKey, headerValue);
			response.setContentType("application/octet-stream"); //for all file type
			ServletOutputStream outputStream = response.getOutputStream();
			FileCopyUtils.copy(inputStream, outputStream);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			new File(fileName).delete();
		}
	}
	
}
