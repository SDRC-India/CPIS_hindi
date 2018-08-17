package org.sdrc.cpis.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.util.Matrix;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.sdrc.cpis.models.AfterCarePlacementOrderModel;
import org.sdrc.cpis.models.CCIHumanResourceModel;
import org.sdrc.cpis.models.CCIHumanResourceOSModel;
import org.sdrc.cpis.models.CCIHumanResourceSAAModel;
import org.sdrc.cpis.models.CCTSChildRegistrationModel;
import org.sdrc.cpis.models.CICLCaseMoniteringSheetModel;
import org.sdrc.cpis.models.CICLChildCareInstitutionPendingInquiryModel;
import org.sdrc.cpis.models.CICLPeriodicReportModel;
import org.sdrc.cpis.models.CICLSocialBackgroundReportFamilyDetailsModel;
import org.sdrc.cpis.models.CICLSocialBackgroundReportModel;
import org.sdrc.cpis.models.CICLSocialInvestigationReportFamilyDetailsModel;
import org.sdrc.cpis.models.CICLSocialInvestigationReportModel;
import org.sdrc.cpis.models.CICLSupervisionOrderModel;
import org.sdrc.cpis.models.CaseHistoryCCIModel;
import org.sdrc.cpis.models.CaseMonitoringModel;
import org.sdrc.cpis.models.CaseSummaryCWCModel;
import org.sdrc.cpis.models.ChildEmploymentDetailsModel;
import org.sdrc.cpis.models.ChildInFitInstitutionModel;
import org.sdrc.cpis.models.ChildInFosterCareModel;
import org.sdrc.cpis.models.ConstitutionOfBCPCModel;
import org.sdrc.cpis.models.ConstitutionOfCWCModel;
import org.sdrc.cpis.models.ConstitutionOfDCPCModel;
import org.sdrc.cpis.models.ConstitutionOfJJBModel;
import org.sdrc.cpis.models.ConstitutionOfSJPUModel;
import org.sdrc.cpis.models.DCPUHRDetailsModel;
import org.sdrc.cpis.models.FamilyHistoryOfCrimeModel;
import org.sdrc.cpis.models.FitPersonDetailModel;
import org.sdrc.cpis.models.FollowUpFormModel;
import org.sdrc.cpis.models.FosterCareDetailsModel;
import org.sdrc.cpis.models.HealthStatusOfChildModel;
import org.sdrc.cpis.models.IndividualCarePlanAModel;
import org.sdrc.cpis.models.IndividualCarePlanBModel;
import org.sdrc.cpis.models.IndividualCarePlanCModel;
import org.sdrc.cpis.models.IndividualCarePlanDModel;
import org.sdrc.cpis.models.InfrastructureCCIModel;
import org.sdrc.cpis.models.InfrastructureOSModel;
import org.sdrc.cpis.models.InfrastructureSAAModel;
import org.sdrc.cpis.models.LegallyFreeForAdoptionModel;
import org.sdrc.cpis.models.RestorationModel;
import org.sdrc.cpis.models.SocialInvestigationReportFamilyDetailsModel;
import org.sdrc.cpis.models.SocialinvestigationReportModel;
import org.sdrc.cpis.models.SponsorshipModel;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

/*
 * @Author Subrata
 */
@Service
public class ExportPDFServiceImpl implements ExportPDFService{

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private ResourceBundleMessageSource notificationMessageSource;
	
	@Override
	public String generatePDFReport(CCTSChildRegistrationModel cctsChildRegistrationModel, String type) throws Exception {
		String pdfPath = null;
		if(type.equals("en"))
			pdfPath = "/resources/pdftemplate/FORM17.pdf";
		else
			pdfPath = "/resources/pdftemplate/FORM17_Hi.pdf";
		PDDocument pdfDocument = PDDocument.load(new File(servletContext.getRealPath(pdfPath)));
		return getGeneratedPDFName(pdfDocument, cctsChildRegistrationModel,"Form17",cctsChildRegistrationModel.getProgramType(),cctsChildRegistrationModel.getChildId(),type);
	}

	@Override
	public String generatePDFDataReportForFitInstitution(ChildInFitInstitutionModel chidlInFitInstitutionModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Form18.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM18_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, chidlInFitInstitutionModel,"Form18", chidlInFitInstitutionModel.getProgramType(), chidlInFitInstitutionModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForFitPerson(FitPersonDetailModel fitPersonDetailModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Form19.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM19_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, fitPersonDetailModel,"Form19",fitPersonDetailModel.getProgramType(), fitPersonDetailModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForFosterCare(FosterCareDetailsModel fosterCareDetailsModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(fosterCareDetailsModel.getFosterType().equals("FP")){
			if(type.equals("en"))
				pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Form32_1.pdf")));
			else
				pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM32_1_Hi.pdf")));
		} else{
			if(type.equals("en"))
				pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Form32_2.pdf")));
			else
				pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM32_2_Hi.pdf")));
		}
		return getGeneratedPDFName(pdfDocument, fosterCareDetailsModel,"Form32", fosterCareDetailsModel.getProgramType(), fosterCareDetailsModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForICPAPersonDetails(IndividualCarePlanAModel individualCarePlanAModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7_A.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7A_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, individualCarePlanAModel,"Form7A", 0, individualCarePlanAModel.getChildId(), type);
	}
	
	@Override
	public String generatePDFDataReportForICPBPersonDetails(IndividualCarePlanBModel individualCarePlanBModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7_B.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7B_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, individualCarePlanBModel,"Form7B",0, individualCarePlanBModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForICPCPersonDetails(IndividualCarePlanCModel individualCarePlanCModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7_C.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7C_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, individualCarePlanCModel,"Form7C", 0, individualCarePlanCModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForICPDPersonDetails(IndividualCarePlanDModel individualCarePlanDModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7_D.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 7D_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, individualCarePlanDModel,"Form7D", 0, individualCarePlanDModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForCaseMonitoringDetails(CaseMonitoringModel caseMonitoringModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM26.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 26_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, caseMonitoringModel,"Form26", caseMonitoringModel.getProgramType(), caseMonitoringModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForSocialInvestigation(SocialinvestigationReportModel socialinvestigationReportModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 22.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM22_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, socialinvestigationReportModel,"Form22", 0, socialinvestigationReportModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForCaseSummary(CaseSummaryCWCModel caseSummaryCWCModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM15.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM15_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, caseSummaryCWCModel,"Form15", caseSummaryCWCModel.getProgramType(), caseSummaryCWCModel.getChildId(),type);
	}

	@Override
	public String generatePDFDataReportForCaseHistoryCCI(CaseHistoryCCIModel caseHistoryCCIModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument=PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM43.pdf")));
		else
			pdfDocument=PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM43_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, caseHistoryCCIModel,"Form43",caseHistoryCCIModel.getProgramType(), caseHistoryCCIModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForSponsorship(SponsorshipModel sponsorshipModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 36.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM36_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, sponsorshipModel,"Form36",sponsorshipModel.getProgramType(), sponsorshipModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForAfterCare(AfterCarePlacementOrderModel afterCarePlacementOrderModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 37.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM37_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, afterCarePlacementOrderModel,"Form37", afterCarePlacementOrderModel.getProgramType(), afterCarePlacementOrderModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataReportForRestoration(RestorationModel restorationModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 44.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM44_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, restorationModel,"Form44", restorationModel.getProgramType(), restorationModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForCICLSBR(CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 1.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM1_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, ciclSocialBackgroundReportModel,"Form1",ciclSocialBackgroundReportModel.getProgramType(), ciclSocialBackgroundReportModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForCICLSupervisionOrder(CICLSupervisionOrderModel ciclSupervisionOrderModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 3.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM3_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, ciclSupervisionOrderModel,"Form3",1, ciclSupervisionOrderModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForCICLChildCareIPI(CICLChildCareInstitutionPendingInquiryModel ciclChildCareIPIModel,String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 4.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 4_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, ciclChildCareIPIModel,"Form4",1,ciclChildCareIPIModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForCICLCaseMoniteringSheet(CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 11.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 11_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, ciclCaseMoniteringSheetModel,"Form11",1, ciclCaseMoniteringSheetModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForCICLPeriodicReport(CICLPeriodicReportModel ciclPeriodicReportModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 10.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 10_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, ciclPeriodicReportModel,"Form10",ciclPeriodicReportModel.getProgramType(),ciclPeriodicReportModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForCICLSIR(CICLSocialInvestigationReportModel ciclSocialInvestigationReportModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 6.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 6_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, ciclSocialInvestigationReportModel,"Form6",1, ciclSocialInvestigationReportModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForChildInFosterCare(ChildInFosterCareModel childInFosterCareModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 34.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM34_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, childInFosterCareModel,"Form34",childInFosterCareModel.getProgramType(),childInFosterCareModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForLegallyFreeForAdoption(LegallyFreeForAdoptionModel legallyFreeForAdoptionModel, String type)	throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 25.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/FORM 25_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, legallyFreeForAdoptionModel,"Form25", legallyFreeForAdoptionModel.getProgramType(),legallyFreeForAdoptionModel.getChildId(),type);
	}
	
	@Override
	public String generatePDFDataForConstitutionOfCWC(ConstitutionOfCWCModel constitutionOfCWCModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of CWC.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of CWC_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, constitutionOfCWCModel,"ConstitutionOfCWC",2, "",type);
	}
	
	@Override
	public String generatePDFDataForconstitutionOfJJB(ConstitutionOfJJBModel constitutionOfJJBModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of JJB.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of JJB_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, constitutionOfJJBModel,"ConstitutionOfJJB",2,"",type);
	}
	
	@Override
	public String generatePDFDataConstitutionOfSJPU(ConstitutionOfSJPUModel constitutionOfSJPUModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of SJPU.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of SJPU_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, constitutionOfSJPUModel,"ConstitutionOfSJPU",2,"",type);
	}
	
	@Override
	public String generatePDFDataConstitutionOfDCPC(ConstitutionOfDCPCModel constitutionOfDCPCModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of DCPC.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of DCPC_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, constitutionOfDCPCModel,"ConstitutionOfDCPC",2,"",type);
	}	
	
	@Override
	public String generatePDFDataHumanResourceDetailsHRCCI(CCIHumanResourceModel cciHumanResourceModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HUMAN RESOURCE DETAILS _HRCCI.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HUMAN RESOURCE DETAILS _HRCCI_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, cciHumanResourceModel,"HumanResourceDetailsHRCCI",2,"",type);
	}
	
	@Override
	public String generatePDFDataHumanResourceDetailsHRSAA(CCIHumanResourceSAAModel cciHumanResourceSAAModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HUMAN RESOURCE DETAILS_HRSAA.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HUMAN RESOURCE DETAILS_HRSAA_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, cciHumanResourceSAAModel,"HumanResourceDetailsHRSAA",2,"",type);
	}
	
	@Override
	public String generatePDFDataHumanResourceDetailsHROS(CCIHumanResourceOSModel cciHumanResourceOSModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HUMAN RESOURCE DETAILS_HROS.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HUMAN RESOURCE DETAILS_HROS_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, cciHumanResourceOSModel,"HumanResourceDetailsHRSAA",2,"",type);
	}
	
	@Override
	public String generatePDFDataInfrastructureCCI(InfrastructureCCIModel infrastructureCCIModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/INFRASTRUCTURE OF INSTITUTION_INFRACCI.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/INFRASTRUCTURE OF INSTITUTION_INFRACCI_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, infrastructureCCIModel,"InfrastructureOfCCI",2,"",type);
	}
	
	@Override
	public String generatePDFDataInfrastructureSAA(InfrastructureSAAModel infrastructureSAAModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/INFRASTRUCTURE OF INSTITUTION_INFRASAA.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/INFRASTRUCTURE OF INSTITUTION_INFRASAA_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, infrastructureSAAModel,"InfrastructureOfSAA",2,"",type);
	}
	
	@Override
	public String generatePDFDataInfrastructureOS(InfrastructureOSModel infrastructureOSModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/INFRASTRUCTURE OF INSTITUTION_INFRAOS.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/INFRASTRUCTURE OF INSTITUTION_INFRAOS_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, infrastructureOSModel,"InfrastructureOfOS",2,"",type);
	}
	
	@Override
	public String generatePDFDataDCPUHRDetails(DCPUHRDetailsModel dcpuhrDetailsModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HR DETAILS OF DCPU.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/HR DETAILS OF DCPU_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, dcpuhrDetailsModel,"HrDetailsOfDCPU",2,"",type);
	}
	@Override
	public String generatePDFDataConstitutionOfBCPC(ConstitutionOfBCPCModel constitutionOfBCPCModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of BCPC.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Constitution of BCPC_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, constitutionOfBCPCModel,"ConstitutionOfBCPC",2,"",type);
	}
	@Override
	public String generatePDFDataFollowUpForm(FollowUpFormModel followUpFormModel, String type) throws Exception {
		PDDocument pdfDocument = null;
		if(type.equals("en"))
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Follow up format for DCPU.pdf")));
		else
			pdfDocument = PDDocument.load(new File(servletContext.getRealPath("/resources/pdftemplate/Follow up format for DCPU_Hi.pdf")));
		return getGeneratedPDFName(pdfDocument, followUpFormModel,"FollowUpForm",2,followUpFormModel.getChildId(),type);
	}

	@SuppressWarnings("deprecation")
	private String getGeneratedPDFName(PDDocument pdfDocument, Object objModelInstance, String formNo, int type, String childID_Name, String languageType)throws Exception{

		 PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();
		 ValueObject valueObject = null;
		 Set<String> familyHistoryOfCrimeFields  = new LinkedHashSet<String>(); 
		 Set<String> childEmploymentDetailsFields  = new LinkedHashSet<String>(); 
		 Set<String> healthStatusOfChildFields  = new LinkedHashSet<String>(); 
		 Set<String> setOfFields  = new LinkedHashSet<String>();
		 List<SocialInvestigationReportFamilyDetailsModel> socialInvestigationReportFamilyDetailsModel =  null;
		 List<FamilyHistoryOfCrimeModel> familyHistoryOfCrimeModel = null;
		 List<ChildEmploymentDetailsModel> childEmploymentDetailsModel = null;
		 List<HealthStatusOfChildModel> healthStatusOfChildModel = null;
		 List<CICLSocialBackgroundReportFamilyDetailsModel> ciclSocialBackgroundReportFamilyDetailsModel = null;
		 List<CICLSocialInvestigationReportFamilyDetailsModel> ciclSocialInvestigationReportFamilyDetailsModels = null;
		 
		 if(objModelInstance instanceof SocialinvestigationReportModel){
			 socialInvestigationReportFamilyDetailsModel =  ((SocialinvestigationReportModel) objModelInstance).getSocialInvestigationReportFamilyDetailsModel();
		 } else if(objModelInstance instanceof CaseHistoryCCIModel){
			 familyHistoryOfCrimeModel = ((CaseHistoryCCIModel) objModelInstance).getFamilyHistoryOfCrimeModels();
			 childEmploymentDetailsModel = ((CaseHistoryCCIModel) objModelInstance).getChildEmploymentDetailsModels();
			 healthStatusOfChildModel = ((CaseHistoryCCIModel) objModelInstance).getHealthStatusOfChildModels();
		 } else if(objModelInstance instanceof CICLSocialBackgroundReportModel){
			 ciclSocialBackgroundReportFamilyDetailsModel = ((CICLSocialBackgroundReportModel) objModelInstance).getFamilyDetails();
		 } else if(objModelInstance instanceof CICLSocialInvestigationReportModel){
			 ciclSocialInvestigationReportFamilyDetailsModels = ((CICLSocialInvestigationReportModel) objModelInstance).getcICLSocialInvestigationReportFamilyDetailsModel();
		 }
		
		 if (acroForm != null)
	     {
	        	// Get field names
	            List<PDField> fieldList = acroForm.getFields();
	            for (PDField pdField : fieldList) {
	            	
	            	String fieldName = pdField.getFullyQualifiedName();
	            		if(objModelInstance instanceof CaseHistoryCCIModel){
	            			if(getfamilyHistoryOfCrimeField(fieldName)){
	            				familyHistoryOfCrimeFields.add(fieldName.split("_")[0]);
	            			} else if(getChildEmploymentDetailsField(fieldName)){
	            				childEmploymentDetailsFields.add(fieldName.split("_")[0]);
	            			} else if(getHealthStatusOfChildField(fieldName)){
	            				healthStatusOfChildFields.add(fieldName.split("_")[0]);
	            			} else{
		            			setAllDownloadValues(acroForm, null, valueObject, objModelInstance, fieldName,languageType);
		            		}	
	            		} else if(objModelInstance instanceof CICLSocialBackgroundReportModel || objModelInstance instanceof CICLSocialInvestigationReportModel || objModelInstance instanceof SocialinvestigationReportModel){
	            			if(getResult(fieldName)){
	            				setOfFields.add(fieldName.split("_")[0]);
	            			} else{
		            			setAllDownloadValues(acroForm, null, valueObject, objModelInstance, fieldName,languageType);
		            		}
	            		} else{
	            			setAllDownloadValues(acroForm, null, valueObject, objModelInstance, fieldName,languageType);
	            		}	
	            }
	            if(socialInvestigationReportFamilyDetailsModel != null){
	            	int i = 0;
	            	for (SocialInvestigationReportFamilyDetailsModel socialInvestigationReport : socialInvestigationReportFamilyDetailsModel) {
	            		i++;
	            		for (String socialInvestigationField : setOfFields) {
	            			String fieldName = (socialInvestigationField+"_"+i);
	            			setAllDownloadValues(acroForm, socialInvestigationField, valueObject, socialInvestigationReport, fieldName,languageType);
						}
					}
	            } 
	            if(ciclSocialBackgroundReportFamilyDetailsModel != null){
	            	int i = 0;
	            	for (CICLSocialBackgroundReportFamilyDetailsModel ciclSocialBackgroundReport : ciclSocialBackgroundReportFamilyDetailsModel) {
	            		i++;
	            		for (String ciclSocialBackgroundReportField : setOfFields) {
	            			String fieldName = (ciclSocialBackgroundReportField+"_"+i);
	            			setAllDownloadValues(acroForm, ciclSocialBackgroundReportField, valueObject, ciclSocialBackgroundReport, fieldName,languageType);
						}
					}
	            } 
	            if(ciclSocialInvestigationReportFamilyDetailsModels != null){
	            	int i = 0;
	            	for (CICLSocialInvestigationReportFamilyDetailsModel ciclSocialInvestigationReport : ciclSocialInvestigationReportFamilyDetailsModels) {
	            		i++;
	            		for (String ciclSocialBackgroundReportField : setOfFields) {
	            			String fieldName = (ciclSocialBackgroundReportField+"_"+i);
	            			setAllDownloadValues(acroForm, ciclSocialBackgroundReportField, valueObject, ciclSocialInvestigationReport, fieldName,languageType);
						}
					}
	            } 
	            if(familyHistoryOfCrimeModel != null){
	            	int i = 0;
	            	for (FamilyHistoryOfCrimeModel typeOfModel : familyHistoryOfCrimeModel) {
	            		i++;
						for(String field : familyHistoryOfCrimeFields){
							String fieldName = (field+"_"+i);
	            			setAllDownloadValues(acroForm, field, valueObject, typeOfModel, fieldName,languageType);
						}
					}
	            }
	            if(childEmploymentDetailsModel != null){
	            	int i = 0;
	            	for (ChildEmploymentDetailsModel typeOfModel : childEmploymentDetailsModel) {
	            		i++;
						for(String field : childEmploymentDetailsFields){
							String fieldName = (field+"_"+i);
	            			setAllDownloadValues(acroForm, field, valueObject, typeOfModel, fieldName,languageType);
						}
					}
	            }
	            if(healthStatusOfChildModel != null){
	            	int i = 0;
	            	for (HealthStatusOfChildModel typeOfModel : healthStatusOfChildModel) {
	            		i++;
						for(String field : healthStatusOfChildFields){
							String fieldName = (field+"_"+i);
	            			setAllDownloadValues(acroForm, field, valueObject, typeOfModel, fieldName,languageType);
						}
					}
	            }
	        }
		 
	        String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	        String fileName = childID_Name+"_"+(type==0?"CNCP":type==1?"CICL":"")+"_"+formNo+"_"+date+".pdf";
			pdfDocument = setFooter(pdfDocument, objModelInstance, languageType);
			PDStream stream= new PDStream(pdfDocument);
		    stream.addCompression();
			pdfDocument.save(fileName);
	        pdfDocument.close();
			return fileName;
	}

	private boolean getHealthStatusOfChildField(String fieldName) {
		List<String> checkFieldNameList = Arrays.asList("dateOfReview","height","weight","nutritiousDietGiven","stress","dental","ent","eye");
		return isFieldExists(fieldName, checkFieldNameList);
	}

	private boolean getChildEmploymentDetailsField(String fieldName) {
		List<String> checkFieldNameList = Arrays.asList("typeOfEmployment","typesOfEmploymentOther","timing","duration","wagesEarned");
		return isFieldExists(fieldName, checkFieldNameList);
	}

	private boolean getfamilyHistoryOfCrimeField(String fieldName) {
		List<String> checkFieldNameList = Arrays.asList("relationshipWithChild","relationshipWithChildOthers","natureOfCrime","legalStatusOfTheCase","arrestIfAny","periodOfConfinement","punishmentAwarded");
		return isFieldExists(fieldName, checkFieldNameList);
	}

	private boolean getResult(String fieldName) {
		List<String> checkFieldNameList = Arrays.asList("name","relationship","age","sex","education","occupation","income","healthStatus","historyOfMentalIllness","addictions");
		return isFieldExists(fieldName, checkFieldNameList);
	}
	
	private boolean isFieldExists(String fieldName, List<String> checkFieldNameList) {
		if(checkFieldNameList.contains(fieldName.split("_")[0])){
//			for (String fieldNam : checkFieldNameList) {
				for(int i = 1;i<=10;i++){
					String field = fieldName.split("_")[0]+"_"+i;
					if(field.equals(fieldName))
						return true;
				}
//			}
		}
		return false;
	}
	private void setAllDownloadValues(PDAcroForm acroForm,String socialInvestigationField, ValueObject valueObject,	Object obj,String fieldName, String languageType) throws Exception {
		String getFieldName = "";
		/*
		 *  In acroForm field we are getting the output(in English PDF). 
		 *  But in case of Hindi(PDF), we are not getting the output.So i am enabling "setNeedAppearances" to true.
		 */
		if(!languageType.equals("en"))
			acroForm.setNeedAppearances(true);
		
		if(obj instanceof FamilyHistoryOfCrimeModel || obj instanceof ChildEmploymentDetailsModel || obj instanceof HealthStatusOfChildModel){
			getFieldName = socialInvestigationField;
		} else{
			getFieldName = socialInvestigationField==null ? fieldName : socialInvestigationField;
		}
		 
		Class<?> clazz = obj.getClass(); 
    	Field field = clazz.getDeclaredField(getFieldName); 
        field.setAccessible(true);
        	     
        Object fieldType = field.getType();
        if(fieldType==ValueObject.class){
        	valueObject = (ValueObject) field.get(obj);
        	if(languageType.equals("en"))
        		try {
        			acroForm.getField(fieldName).setValue(field.get(obj)==null ? "" : valueObject.getName());
    			} catch (Exception e) {
    				acroForm.setNeedAppearances(true);
    				acroForm.getField(fieldName).setValue(field.get(obj)==null ? "" : valueObject.getName());
    			}
        	else
        		acroForm.getField(fieldName).setValue(field.get(obj)==null ? "" : (valueObject.getTypeNameHindi()==null?valueObject.getName():valueObject.getTypeNameHindi()));
        } else{
        	if(languageType.equals("en"))
        		try {
        			acroForm.getField(fieldName).setValue(field.get(obj)==null ? "" : ((field.get(obj).toString()).equalsIgnoreCase("True") ? "Yes" : (field.get(obj).toString()).equalsIgnoreCase("false") ? "No" : (field.get(obj).toString())));
    			} catch (Exception e) {
    				acroForm.setNeedAppearances(true);
    				acroForm.getField(fieldName).setValue(field.get(obj)==null ? "" : ((field.get(obj).toString()).equalsIgnoreCase("True") ? "Yes" : (field.get(obj).toString()).equalsIgnoreCase("false") ? "No" : (field.get(obj).toString())));
    			}
        	else
        		acroForm.getField(fieldName).setValue(field.get(obj)==null ? "" : ((field.get(obj).toString()).equalsIgnoreCase("True") ? "हाँ" : (field.get(obj).toString()).equalsIgnoreCase("false") ? "नहीं" : (field.get(obj).toString())));
        }		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String downloadPDFTemplate(String formNumber, String typeOfPdf, String typeOfLanguage) throws Exception {
		File filePath = null;
		
		if(typeOfPdf.equals("template")){
			String filename = "";
			switch (formNumber) {
				case "21":
					filename = typeOfLanguage.equals("en")?"21.pdf":"21_Hi.pdf";
					break;
				case "30":
					filename = typeOfLanguage.equals("en")?"30.pdf":"30_Hi.pdf";
					break;
				case "31":
					filename = typeOfLanguage.equals("en")?"31.pdf":"31_Hi.pdf";
					break;
				case "35":
					filename = typeOfLanguage.equals("en")?"35.pdf":"35_Hi.pdf";
					break;
				case "42":
					filename = typeOfLanguage.equals("en")?"42.pdf":"42_Hi.pdf";
					break;
				case "45":
					filename = typeOfLanguage.equals("en")?"45.pdf":"45_Hi.pdf";
					break;
				case "14":
					filename = typeOfLanguage.equals("en")?"14.pdf":"14_Hi.pdf";
					break;
			}
			filePath = new File(servletContext.getRealPath("/resources/pdftemplate/uploadpdftemplate/FORM "+filename));
		}else{
//			String path = "D:/CPIS_Image/";
			String path = applicationMessageSource.getMessage("store.pdfPath", null, null,null);
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			filePath = new File(path+formNumber);
		}
		
		PDDocument pdfDocument = PDDocument.load(filePath);
		String date = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
		PDStream stream= new PDStream(pdfDocument);
	    stream.addCompression();
		String fileName = "CPIS_"+date+".pdf";
		pdfDocument.save(fileName);
        pdfDocument.close();
        
		return fileName;
	}
	
	@SuppressWarnings("deprecation")
	private PDDocument setFooter(PDDocument pdfDocument, Object typeOfModel, String languageType) throws Exception {
		String childId = null;
		String childImageString = null;
		String fosterCareParentImage = null;
		String biologicalParentImage = null;
		PDImageXObject pdImage = null;
		List<PDImageXObject> list = null;
		String footer = null;
		boolean childRegFlag = false, childCaseFlag = false, ciclSBRFlag = true, cifcFlag = true, followUpFlag = false;
		String date = new SimpleDateFormat("dd-MM-yyyy HH:mm a").format(new Date());
		
		if(typeOfModel instanceof CCTSChildRegistrationModel){
			
			childId = ((CCTSChildRegistrationModel) typeOfModel).getChildId();
			childImageString = ((CCTSChildRegistrationModel) typeOfModel).getChildImage().split(",")[1];
			pdImage = convertToImage(childImageString, pdfDocument);
			childRegFlag = true;
			
		} else if(typeOfModel instanceof ChildInFitInstitutionModel){
			childId = ((ChildInFitInstitutionModel) typeOfModel).getChildId();
		}
		else if(typeOfModel instanceof FollowUpFormModel){
			
			childId = ((FollowUpFormModel) typeOfModel).getChildId();
			childImageString = ((FollowUpFormModel) typeOfModel).getChildPhoto().split(",")[1];
			pdImage = convertToImage(childImageString, pdfDocument);
			followUpFlag = true;
			
		} else if(typeOfModel instanceof FitPersonDetailModel){
			childId = ((FitPersonDetailModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof FosterCareDetailsModel){
			childId = ((FosterCareDetailsModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof IndividualCarePlanAModel){
			childId = ((IndividualCarePlanAModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof IndividualCarePlanBModel){
			childId = ((IndividualCarePlanBModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof IndividualCarePlanCModel){
			childId = ((IndividualCarePlanCModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof IndividualCarePlanDModel){
			childId = ((IndividualCarePlanDModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CaseMonitoringModel){
			childId = ((CaseMonitoringModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof SocialinvestigationReportModel){
			childId = ((SocialinvestigationReportModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CaseSummaryCWCModel){
			childId = ((CaseSummaryCWCModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CaseHistoryCCIModel){
			childId = ((CaseHistoryCCIModel) typeOfModel).getChildId();
			childImageString = ((CaseHistoryCCIModel) typeOfModel).getChildImgpath().split(",")[1];
			pdImage = convertToImage(childImageString, pdfDocument);
			childCaseFlag = true;
		} else if(typeOfModel instanceof SponsorshipModel){
			childId = ((SponsorshipModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof AfterCarePlacementOrderModel){
			childId = ((AfterCarePlacementOrderModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof RestorationModel){
			childId = ((RestorationModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CICLSocialBackgroundReportModel){
			childId = ((CICLSocialBackgroundReportModel) typeOfModel).getChildId();
			childImageString = ((CICLSocialBackgroundReportModel) typeOfModel).getChildImage().split(",")[1];
			pdImage = convertToImage(childImageString, pdfDocument);
			ciclSBRFlag = true;
		} else if(typeOfModel instanceof CICLSupervisionOrderModel){
			childId = ((CICLSupervisionOrderModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CICLChildCareInstitutionPendingInquiryModel){
			childId = ((CICLChildCareInstitutionPendingInquiryModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CICLCaseMoniteringSheetModel){
			childId = ((CICLCaseMoniteringSheetModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CICLPeriodicReportModel){
			childId = ((CICLPeriodicReportModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof CICLSocialInvestigationReportModel){
			childId = ((CICLSocialInvestigationReportModel) typeOfModel).getChildId();
		} else if(typeOfModel instanceof ChildInFosterCareModel){
			list = new ArrayList<PDImageXObject>();
			childId = ((ChildInFosterCareModel) typeOfModel).getChildId();
			childImageString = ((ChildInFosterCareModel) typeOfModel).getChildImage() != null ? ((ChildInFosterCareModel) typeOfModel).getChildImage().split(",")[1]:null;
			if(childImageString!=null){
				pdImage = convertToImage(childImageString, pdfDocument);
			} 
			list.add(pdImage);
			pdImage = null;
			fosterCareParentImage = ((ChildInFosterCareModel) typeOfModel).getFosterCareParentImage() != null ? ((ChildInFosterCareModel) typeOfModel).getFosterCareParentImage().split(",")[1]:null;
			if(fosterCareParentImage != null){
				pdImage = convertToImage(fosterCareParentImage, pdfDocument);
				list.add(pdImage);
			}
			pdImage = null;
			biologicalParentImage = ((ChildInFosterCareModel) typeOfModel).getBiologicalParentImage() != null ? ((ChildInFosterCareModel) typeOfModel).getBiologicalParentImage().split(",")[1]:null;
			if(biologicalParentImage != null){
				pdImage = convertToImage(biologicalParentImage, pdfDocument);
				list.add(pdImage);
			}
		} else if(typeOfModel instanceof LegallyFreeForAdoptionModel){
			childId = ((LegallyFreeForAdoptionModel) typeOfModel).getChildId();
		}
		String domainName = notificationMessageSource.getMessage("domain.ip.name", null, null);
		if(typeOfModel instanceof ConstitutionOfCWCModel || typeOfModel instanceof ConstitutionOfJJBModel 
				|| typeOfModel instanceof ConstitutionOfSJPUModel || typeOfModel instanceof ConstitutionOfDCPCModel
				|| typeOfModel instanceof CCIHumanResourceModel || typeOfModel instanceof CCIHumanResourceSAAModel
				|| typeOfModel instanceof CCIHumanResourceOSModel || typeOfModel instanceof InfrastructureCCIModel
				|| typeOfModel instanceof InfrastructureSAAModel || typeOfModel instanceof InfrastructureOSModel
				|| typeOfModel instanceof DCPUHRDetailsModel || typeOfModel instanceof ConstitutionOfBCPCModel){
			footer = domainName+"                                           Printed on : "+date;
		} else{
			footer = "System generated child ID : "+ childId +" from  "+domainName+"                         Printed on : "+date;
		}
		
		
		PDFont font = PDType1Font.TIMES_ROMAN;
		float fontSize = 8.0f;
		float stringWidth = font.getStringWidth(footer) * fontSize / 1000f;
		int i = 0;
		for (PDPage page : pdfDocument.getPages()) {
			i++;
			PDRectangle pageSize = page.getMediaBox();

			int rotation = page.getRotation();
			boolean rotate = rotation == 90 || rotation == 270;
			float pageWidth = rotate ? pageSize.getHeight() : pageSize.getWidth();
			float pageHeight = rotate ? pageSize.getWidth() : pageSize.getHeight();
			float centerX = rotate ? pageHeight / 3f: (pageWidth - stringWidth) / 3f;
			float centerY = rotate ? (pageWidth - stringWidth) / 22f: pageHeight / 22f;
			PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page, AppendMode.APPEND, true, true);
			if(!(typeOfModel instanceof CaseSummaryCWCModel || typeOfModel instanceof CCTSChildRegistrationModel
				|| typeOfModel instanceof ChildInFitInstitutionModel || typeOfModel instanceof FitPersonDetailModel 
				|| typeOfModel instanceof FosterCareDetailsModel || typeOfModel instanceof AfterCarePlacementOrderModel
				|| typeOfModel instanceof SponsorshipModel)){
					contentStream.drawLine(50, 789, 545, 789);
			        contentStream.drawLine(50, 787, 545, 787);
			}else if((typeOfModel instanceof CaseSummaryCWCModel || typeOfModel instanceof CCTSChildRegistrationModel
					|| typeOfModel instanceof ChildInFitInstitutionModel || typeOfModel instanceof FitPersonDetailModel 
					|| typeOfModel instanceof FosterCareDetailsModel || typeOfModel instanceof AfterCarePlacementOrderModel
					|| typeOfModel instanceof SponsorshipModel) && (languageType.equals("en"))){
				contentStream.drawLine(50, 789, 545, 789);
		        contentStream.drawLine(50, 786, 545, 786);
			}
	        contentStream.drawLine(50, 50, 545, 50);
//			contentStream.drawLine(0, 50, 600, 50);
			if(typeOfModel instanceof CCTSChildRegistrationModel && childRegFlag == true){
				contentStream.drawImage(pdImage, 450, 700, 60, 70);
				childRegFlag  = false;
			} else if(typeOfModel instanceof CaseHistoryCCIModel && childCaseFlag == true){
				contentStream.drawImage(pdImage, 400, 600, 117, 101);
				childCaseFlag  = false;
			}else if(typeOfModel instanceof FollowUpFormModel && followUpFlag == true){
				contentStream.drawImage(pdImage, 425, 670, 80, 70);
				followUpFlag  = false;
			} else if(typeOfModel instanceof CICLSocialBackgroundReportModel && ciclSBRFlag == true){
				contentStream.drawImage(pdImage, 400, 600, 117, 101);
				ciclSBRFlag  = false;
			} else if(typeOfModel instanceof ChildInFosterCareModel && cifcFlag == true){
				if(languageType.equals("en") && i==1){
					if(list.size()==1 || list.size()>1){
						contentStream.drawImage(list.get(0), 300, 217, 80, 70);
					} 
					if(list.size()==2 || list.size()>2){
						 contentStream.drawImage(list.get(1), 300, 145, 80, 70);
					} 
					if(list.size()==3){
						contentStream.drawImage(list.get(2), 300, 74, 80, 70);
					}
					cifcFlag  = false;
				} else if(i==2){
					if(list.size()==1 || list.size()>1){
						contentStream.drawImage(list.get(0), 360, 676, 80, 90);
					} 
					if(list.size()==2 || list.size()>2){
						 contentStream.drawImage(list.get(1), 360, 574, 80, 90);
					} 
					if(list.size()==3){
						contentStream.drawImage(list.get(2), 360, 463, 80, 90);
					}
					cifcFlag  = false;
				}
			}
			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			if (rotate) {
				contentStream.setTextMatrix(Matrix.getRotateInstance(Math.PI / 2, centerX, centerY));
			} else {
				contentStream.setTextMatrix(Matrix.getTranslateInstance(centerX, centerY));
			}
			contentStream.showText(footer);
//			contentStream.showText("                     Page "+i+" of "+pdfDocument.getNumberOfPages());
			contentStream.showText("                Page "+i+" of "+pdfDocument.getNumberOfPages());
			contentStream.endText();
			contentStream.close();
		}

		PDStream stream= new PDStream(pdfDocument);
	    stream.addCompression();
	    return pdfDocument;
	}

	private PDImageXObject convertToImage(String childImageString, PDDocument pdfDocument) throws Exception {
		byte[] imageByte=null;
		BufferedImage image = null;
		PDImageXObject pdImage = null;
		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(childImageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		pdImage = image==null ? null : LosslessFactory.createFromImage(pdfDocument, image);
		return pdImage;
	}
	
/*
 * @Author Subrata
 * Taking path and returning base64 for image (for UI)
 * @see org.sdrc.cpis.services.ExportPDFService#getChildPhoto(java.lang.String)
 */
	@Override
	public String getChildPhoto(String childPhoto) throws Exception {

		File filePath = null;
		FileInputStream fileInputStreamReader = null;

		try {
			filePath = new File(childPhoto==null?servletContext.getRealPath("/resources/img/photo.jpg"):childPhoto);
			fileInputStreamReader = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			filePath = new File(servletContext.getRealPath("/resources/img/photo.jpg"));
			fileInputStreamReader = new FileInputStream(filePath);
		}
		
		byte[] bytes = new byte[(int)filePath.length()];
		fileInputStreamReader.read(bytes);
	    fileInputStreamReader.close();
	    String result = "data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(bytes);
		return result;
	}
	/*
	 * @Author Subrata
	 * Taking base64 and returning image path and storing image path in Database and saving file 
	 * @see org.sdrc.cpis.services.ExportPDFService#getChildPhoto(java.lang.String)
	 */
	@Override
	 public String getPhotoPath(String childImage, String childId, String type) throws Exception {
    	String path = null;
    	switch(type){
    		case "childRegistration":
    			path = applicationMessageSource.getMessage("store.imagePath", null, null,null);
    			break;
    		case "caseHistory":
    			path = applicationMessageSource.getMessage("store.CaseHistory", null, null,null);
    			break;
    		case "caseSummary":
    			path = applicationMessageSource.getMessage("store.CaseSummary", null, null,null);
    			break;
    		case "ChildInFosterCare":
    			path = applicationMessageSource.getMessage("store.ChildInFosterCare", null, null,null);
    			break;
    		case "followUpForm":
    			path = applicationMessageSource.getMessage("store.FollowUpForm", null, null,null);
    			break;
    	}
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
	    byte[] decodedBytes = Base64.decodeBase64(childImage.split(",")[1]);
	    String finalPath = path+""+childId+"_"+new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new java.util.Date())+".png";
	    OutputStream out = new FileOutputStream(finalPath);
	    out.write(decodedBytes );
	    out.close();
	    makeThumbnail(finalPath, decodedBytes);
		return finalPath;
	}
	
	public void makeThumbnail(String finalPath, byte[] decodedBytes) throws IOException {
		File readFilePath = new File(finalPath);
		BufferedImage img = ImageIO.read(readFilePath); // load image
	
		BufferedImage thumbImg = Scalr.resize(img, Method.QUALITY,	Mode.AUTOMATIC, 80, 80, Scalr.OP_ANTIALIAS);
		// convert bufferedImage to outputstream
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(thumbImg, "jpg", os);

		File writeFilePath = new File(finalPath+"-thumbnail.jpg");
		ImageIO.write(thumbImg, "jpg", writeFilePath);
	}
	
	/*
	 * @Author Subrata
	 * Taking base64 and returning pdf path and storing pdf path in Database and saving file 
	 * @see org.sdrc.cpis.services.ExportPDFService#getChildPhoto(java.lang.String)
	 */
	@Override
	public String getFileName(String data, String type, String path) throws Exception {
		String fileName = "CPIS_"+type+"_";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//decode base64format to pdf foramt
	    byte[] decodedBytes = Base64.decodeBase64(data.split(",")[1]);
	    String finalFilePath = path+""+fileName+"_"+new SimpleDateFormat("ddMMyyyyHHmmssS").format(new java.util.Date())+".pdf";
	    OutputStream out = new FileOutputStream(finalFilePath);
	    out.write(decodedBytes );
	    out.close();
	    return finalFilePath;
	}
	
	/*
	 * @Author Subrata
	 * Taking path and returning base64 for pdf (for UI).
	 * @see org.sdrc.cpis.services.ExportPDFService#getChildPhoto(java.lang.String)
	 */
	@Override
	public String getPdf(String pdfString) throws Exception {
		byte[] bytes = null;
		String result = null;
		File filePath = null;
		if(pdfString != null){
			FileInputStream fileInputStreamReader = null;
			filePath = new File(pdfString);
			try {
				fileInputStreamReader = new FileInputStream(filePath);
				bytes = new byte[(int)filePath.length()];
				fileInputStreamReader.read(bytes);
			    fileInputStreamReader.close();
			    result =  "data:application/pdf;base64," + DatatypeConverter.printBase64Binary(bytes);
			} catch (Exception e) {
				System.out.println("File not found.");
			}
		}
		return result;
	}
}
