package org.sdrc.cpis.controller;

import org.sdrc.cpis.models.ConstitutionOfCWCModel;
import org.sdrc.cpis.services.ConstitutionofCWCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionofCWCController {
	
	@Autowired
	ConstitutionofCWCService constitutionofCWCService; 

	@ResponseBody
	@RequestMapping(value="/saveCWCConstitution", method={RequestMethod.POST})
	public void saveCWCConstitution(@RequestBody ConstitutionOfCWCModel constitutionOfCWCModel){
		constitutionofCWCService.saveConstitutionofCWC(constitutionOfCWCModel);
	}
}
