package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.models.CICLPeriodicReportModel;
import org.sdrc.cpis.services.CICLPeriodicReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CICLPeriodicReportController {

	@Autowired
	CICLPeriodicReportService ciclPeriodicReportService;
	
	// inserting 
	
	@RequestMapping(value="/savePeriodicReport",method=RequestMethod.POST)
	@ResponseBody
	public String initPeriodicReport(@RequestBody CICLPeriodicReportModel ciclPeriodicReportModel)
	{
		ciclPeriodicReportService.saveCICLPeriodicReportData(ciclPeriodicReportModel);
		
		return "redirect:ccts_login";
	}
	
	// viewing
	@RequestMapping(value="/ciclPeriodicReport")
	public String getCICLSocialBackgroundView(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "ciclPeriodicReport";
	}
	@ResponseBody
	@RequestMapping(value="/getPeriodicReport",method=RequestMethod.GET)
	public CICLPeriodicReportModel getDetails(@RequestParam("childId") String childId)
	{
		return ciclPeriodicReportService.getCiclPeriodicReportModel(childId);
	}
	
}
