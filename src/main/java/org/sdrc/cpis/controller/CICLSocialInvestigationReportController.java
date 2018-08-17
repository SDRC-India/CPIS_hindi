package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CICLSocialInvestigationReportModel;
import org.sdrc.cpis.services.CICLSocialInvestigationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CICLSocialInvestigationReportController {

	@Autowired
	CICLSocialInvestigationReportService ciclSocialInvestigationReportService;
	
	@Authorize(feature="ciclSocialInvestigationReport",permission="edit")
	@ResponseBody
	@RequestMapping(value="/saveCICLSocialIvestigationreport", method={RequestMethod.POST})
	public void saveCICLSocialIvestigationreport(@RequestBody CICLSocialInvestigationReportModel ciclSocialInvestigationReportModel) throws Exception{
		
		ciclSocialInvestigationReportService.saveCICLSocialInvestigationReport(ciclSocialInvestigationReportModel, ciclSocialInvestigationReportModel.getcICLSocialInvestigationReportFamilyDetailsModel());
		
	}
	
	@Authorize(feature="ciclSocialInvestigationReport",permission="edit")
	@RequestMapping(value="/ciclSocialInvestigationReport")
	public String getCICLSocialInvestigation(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		Integer selectedId =  ciclSocialInvestigationReportService.getId(selectedChildId);
		
		model.addAttribute("selectedChild", selectedChildId);
		model.addAttribute("selectedId", selectedId);
		return "ciclSocialInvestigationReport";
	}

	@Authorize(feature="ciclSocialInvestigationReport",permission="edit")
	@ResponseBody
	@RequestMapping(value="/getCICLSocialIvestigationreport")
	public CICLSocialInvestigationReportModel getCICLSocialInvestigationReport(@RequestParam("childId") String childId) throws Exception{
		return ciclSocialInvestigationReportService.getCICLSocialIvestigationreport(childId);
	}
}
