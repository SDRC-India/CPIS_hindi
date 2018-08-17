package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.models.ConstitutionOfSJPUModel;
import org.sdrc.cpis.services.ConstitutionOfSJPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionOfSJPUController {

	
	@Autowired
	ConstitutionOfSJPUService constitutionOfSJPUService;
	// inserting 
	
	@RequestMapping(value="/saveConstitutionOfSJPU",method=RequestMethod.POST)
	@ResponseBody
	public String initConstitutionOfSJPU(@RequestBody ConstitutionOfSJPUModel constitutionOfSJPUModel)
	{
		constitutionOfSJPUService.saveConstitutionOfSJPUData(constitutionOfSJPUModel);
		
		return "redirect:ccts_login";
	}
	
	// viewing
	@RequestMapping(value="/constitutionOfSJPU")
	public String getConstitutionOfSJPU(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "constitutionOfJJB";
	}
	@ResponseBody
	@RequestMapping(value="/getConstitutionOfSJPUModel",method=RequestMethod.GET)
	public ConstitutionOfSJPUModel getDetails(@RequestParam("childId") String childId)
	{
		return constitutionOfSJPUService.getConstitutionOfSJPUModel(childId);
	}
	
}
