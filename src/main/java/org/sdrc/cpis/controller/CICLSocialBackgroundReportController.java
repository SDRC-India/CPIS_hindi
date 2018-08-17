package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CICLSocialBackgroundReportModel;
import org.sdrc.cpis.services.CICLSocialBackgroundReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CICLSocialBackgroundReportController {

	@Autowired
	CICLSocialBackgroundReportService ciclSocialBackgroundReportService;
	
	@Authorize(feature="ciclSocialBackgroundReport",permission="edit")
	@RequestMapping(value="/save_social",method=RequestMethod.POST)
	@ResponseBody
	public String init(@RequestBody CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel) throws Exception
	{
		return ciclSocialBackgroundReportService.saveCICLSocialBackgroundReportData(ciclSocialBackgroundReportModel);
	}
	
	@Authorize(feature="ciclSocialBackgroundView",permission="edit")
	@RequestMapping(value="/ciclSocialBackgroundView")
	public String getCICLSocialBackgroundView(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "ciclSocialBackgroundView";
	}
	
//	@Authorize(feature="ciclSocialBackgroundReport",permission="view")
	@ResponseBody
	@RequestMapping(value="/getSocialBackgroundReport",method=RequestMethod.GET)
	public CICLSocialBackgroundReportModel getDetails(@RequestParam("childId") String childId) throws Exception{
		return ciclSocialBackgroundReportService.getCICLSocialBackgroundReportModel(childId);
	}
	
	@Authorize(feature="ciclSocialBackgroundReport",permission="edit")
	@RequestMapping(value="ciclSocialBackgroundReport")
	public String getCiclChildRegPage(){
		return "/ciclSocialBackgroundReport";
		
	}
}
