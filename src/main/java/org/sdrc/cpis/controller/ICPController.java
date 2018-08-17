package org.sdrc.cpis.controller;

import java.text.ParseException;
import java.util.List;

import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.IndividualCarePlanAModel;
import org.sdrc.cpis.models.IndividualCarePlanBModel;
import org.sdrc.cpis.models.IndividualCarePlanCModel;
import org.sdrc.cpis.models.IndividualCarePlanDModel;
import org.sdrc.cpis.services.ICPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ICPController {
	
	@Autowired
	private ICPService icpService;
	
	@Authorize(feature="individualCarePlan",permission="edit")
	@RequestMapping(value={"/saveICPA"}, method={RequestMethod.POST})
	@ResponseBody
	public String saveICPA(@RequestBody IndividualCarePlanAModel individualCarePlanAModel){
		icpService.saveICPA(individualCarePlanAModel);
		return null;
	}
	
	@Authorize(feature="individualCarePlan",permission="edit")
	@RequestMapping(value={"/saveICPB"}, method={RequestMethod.POST})
	@ResponseBody
	public String saveICPB(@RequestBody IndividualCarePlanBModel individualCarePlanBModel){
		icpService.saveICPB(individualCarePlanBModel);
		return null;
	}
	
	@Authorize(feature="individualCarePlan",permission="edit")
	@RequestMapping(value= {"/saveICPC"}, method= {RequestMethod.POST})
	@ResponseBody
	public String saveICPC(@RequestBody IndividualCarePlanCModel individualCarePlanCModel) throws Exception{
		icpService.saveICPC(individualCarePlanCModel);
		return null;
	};
	
	@Authorize(feature="individualCarePlan",permission="edit")
	@RequestMapping(value="/saveICPD", method=RequestMethod.POST)
	@ResponseBody
	public String saveICPA(@RequestBody IndividualCarePlanDModel individualCarePlanDModel){
		icpService.saveICPD(individualCarePlanDModel);
		return null;
		
	}
	
	@Authorize(feature="individualCarePlan",permission="edit")
	@RequestMapping(value="/individualCarePlan")
	public String getIndividualCarePlan(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
//		model.addAttribute("icpAdata",icpService.getPersonalDetailsByChildId(selectedChildId));
		return "individualCarePlan";
	}
	
	@ResponseBody
	@RequestMapping(value="/getPersonalDetailsData")
	public IndividualCarePlanAModel getPersonalDetailsData(@RequestParam("childId") String childId){
		
		return icpService.getPersonalDetailsByChildId(childId);
	}
	
	@ResponseBody
	@RequestMapping(value="/getProgressReports")
	public List<IndividualCarePlanBModel> getProgessReports(@RequestParam("childId") String childId){
		
		return icpService.getProgressReports(childId);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPreReleaseReport")
	public IndividualCarePlanCModel getPreReleaseReport(@RequestParam("childId") String childId) throws Exception{
		
		return icpService.getPreReleaseReport(childId);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPostReleaseReport")
	public IndividualCarePlanDModel getPostReleaseReport(@RequestParam("childId") String childId){
		
		return icpService.getPostReleaseReport(childId);
	}
	
}
