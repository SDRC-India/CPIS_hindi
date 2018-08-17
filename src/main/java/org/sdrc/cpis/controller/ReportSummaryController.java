package org.sdrc.cpis.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CCIHumanResourceModel;
import org.sdrc.cpis.models.CCIHumanResourceOSModel;
import org.sdrc.cpis.models.CCIHumanResourceSAAModel;
import org.sdrc.cpis.models.DCPUHRDetailsModel;
import org.sdrc.cpis.models.FinancialInspectionReportModel;
import org.sdrc.cpis.models.InfrastructureCCIModel;
import org.sdrc.cpis.models.InfrastructureOSModel;
import org.sdrc.cpis.models.InfrastructureSAAModel;
import org.sdrc.cpis.services.ReportSummaryService;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportSummaryController {
	@Autowired
	private ReportSummaryService reportSummaryService;
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value = {"/saveCCIHumanResource"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveCaseHistory(@RequestBody CCIHumanResourceModel cciHumanResourceModel) throws Exception{
		
		reportSummaryService.saveCCIHumanResource(cciHumanResourceModel);
		
		return "success";
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value = {"/saveCCIHumanResourceSAA"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveCaseHistory(@RequestBody CCIHumanResourceSAAModel cciHumanResourceSAAModel) throws Exception{
		
		reportSummaryService.saveCCIHumanREsourceSAA(cciHumanResourceSAAModel);
		
		return "success";
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value = {"/saveCCIHumanResourceOS"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveCaseHistory(@RequestBody CCIHumanResourceOSModel cciHumanResourceOSModel) throws Exception{
		
		reportSummaryService.saveCCIHumanResourceOS(cciHumanResourceOSModel);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAreadetails",method=RequestMethod.GET)
	public ValueObject getArea() {
		return reportSummaryService.getArea();
	}
	
	@RequestMapping(value="/getYearDetails", method=RequestMethod.GET)
	@ResponseBody
	public List<ValueObject> yearDetails() throws Exception{
		return reportSummaryService.getYearDetails();
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value= {"/saveFinancialInspectionReport"}, method= {RequestMethod.POST})
	@ResponseBody
	public String financialReport(@RequestBody FinancialInspectionReportModel financialInspectionReportModel, HttpServletResponse response)throws Exception{
		return reportSummaryService.saveFinancialReport(financialInspectionReportModel);
	}
	
	@RequestMapping(value="/getFinancialInspectionReportDetails", method=RequestMethod.GET)
	@ResponseBody
	public List<ValueObject> financialReportDetails() throws Exception{
		return reportSummaryService.getFinancialInspectionReportDetails();
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value = {"/saveInfraCCI"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveInfrastructureCCI(@RequestBody InfrastructureCCIModel infrastructureCCIModel) throws Exception{
		
		reportSummaryService.saveInfrastructureCCI(infrastructureCCIModel);
		
		return "success";
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value = {"/saveInfraSAA"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveInfrastructureSSA(@RequestBody InfrastructureSAAModel infrastructureSAAModel) throws Exception{
		reportSummaryService.saveInfrastructureSAA(infrastructureSAAModel);
		return "success";
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value = {"/saveInfraOS"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveInfrastructureOS(@RequestBody InfrastructureOSModel infrastructureOSModel) throws Exception{
		
		reportSummaryService.saveInfrastructureOS(infrastructureOSModel);
		
		return "success";
	}
	
	@Authorize(feature="reportSummary",permission="edit")
	@RequestMapping(value="/reportSummary")
	public String getReportSummary()
			throws ParseException{
		return "reportSummary";
	}
	
	@RequestMapping(value = {"/saveDCPUHRDetails"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveDCPUHRDetails(@RequestBody DCPUHRDetailsModel dcpuhrDetailsModel) throws Exception{
		reportSummaryService.saveDCPUHRDetails(dcpuhrDetailsModel);
		return "success";
	}
	
	
	@RequestMapping(value = {"/getDCPUHRDetails"}, method = {RequestMethod.GET})
	@ResponseBody
	public DCPUHRDetailsModel getDCPUHRDetails(){
		return reportSummaryService.getDCPUHRDetails();
	}
}
