package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.SocialinvestigationReportModel;
import org.sdrc.cpis.services.SocialInvestigationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SocialInvestigationReportController {

	@Autowired
	SocialInvestigationReportService socialInvestigationReportService;
	
	@Authorize(feature="socialInvestigation",permission="edit")
	@ResponseBody
	@RequestMapping(value="/saveSocialIvestigationreport", method={RequestMethod.POST})
	public void saveSocialIvestigationreport(@RequestBody SocialinvestigationReportModel socialinvestigationReportModel){
		
		socialInvestigationReportService.saveSocialInvestigationReport(socialinvestigationReportModel, socialinvestigationReportModel.getSocialInvestigationReportFamilyDetailsModel());
		
//		socialInvestigationReportService.saveSocialInvestigationReportFamilyDetails(socialinvestigationReportModel.getSocialInvestigationReportFamilyDetailsModel());
	}
	
	@Authorize(feature="socialInvestigation",permission="edit")
	@RequestMapping(value="/socialInvestigation")
	public String getSocialInvestigation(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		Integer selectedId =  socialInvestigationReportService.getId(selectedChildId);
		
		model.addAttribute("selectedChild", selectedChildId);
		model.addAttribute("selectedId", selectedId);
		return "socialInvestigation";
	}
	
	@Authorize(feature="socialInvestigation",permission="edit")
	@ResponseBody
	@RequestMapping(value="/getSocialIvestigationreport")
	public SocialinvestigationReportModel getSocialInvestigationReport(@RequestParam("childId") String childId){
		return socialInvestigationReportService.getSocialIvestigationreport(childId);
	}
	
}
