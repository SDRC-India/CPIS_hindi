package org.sdrc.cpis.controller;

import org.sdrc.cpis.models.ConstitutionOfDCPCModel;
import org.sdrc.cpis.services.ConstitutionOfDCPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionOfDCPCController {

	
	@Autowired
	ConstitutionOfDCPCService constitutionOfDCPCService;
	// inserting 
	
	@RequestMapping(value="/saveConstitutionOfDCPC",method=RequestMethod.POST)
	@ResponseBody
	public String initConstitutionOfDCPC(@RequestBody ConstitutionOfDCPCModel constitutionOfDCPCModel)
	{
		constitutionOfDCPCService.saveConstitutionOfDCPCData(constitutionOfDCPCModel);
		
		return "redirect:ccts_login";
	}
	
	// viewing
//	@RequestMapping(value="/constitutionOfSJPU")
//	public String getConstitutionOfSJPU(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
//		model.addAttribute("selectedChild", selectedChildId);
//		return "constitutionOfJJB";
//	}
//	@ResponseBody
//	@RequestMapping(value="/getConstitutionOfSJPUModel",method=RequestMethod.GET)
//	public ConstitutionOfSJPUModel getDetails(@RequestParam("childId") String childId)
//	{
//		return constitutionOfSJPUService.getConstitutionOfSJPUModel(childId);
//	}
	
}
