package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.models.ConstitutionOfJJBModel;
import org.sdrc.cpis.services.ConstitutionOfJJBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionOfJJBController {

	
	@Autowired
	ConstitutionOfJJBService constitutionOfJJBService;
	// inserting 
	
	@RequestMapping(value="/saveConstitutionOfJJB",method=RequestMethod.POST)
	@ResponseBody
	public String initConstitutionOfJJB(@RequestBody ConstitutionOfJJBModel constitutionOfJJBModel)
	{
		constitutionOfJJBService.saveConstitutionOfJJBData(constitutionOfJJBModel);
		
		return "redirect:ccts_login";
	}
	
	// viewing
	@RequestMapping(value="/constitutionOfJJB")
	public String getConstitutionOfJJB(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "constitutionOfJJB";
	}
	@ResponseBody
	@RequestMapping(value="/getConstitutionOfJJBModel",method=RequestMethod.GET)
	public ConstitutionOfJJBModel getDetails(@RequestParam("childId") String childId)
	{
		return constitutionOfJJBService.getConstitutionOfJJBModel(childId);
	}
	
}
