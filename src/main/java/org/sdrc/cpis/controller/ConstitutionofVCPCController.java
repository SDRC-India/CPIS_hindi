package org.sdrc.cpis.controller;

import org.sdrc.cpis.models.ConstitutionOfVCPCModel;
import org.sdrc.cpis.services.ConstitutionofVCPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionofVCPCController {

	@Autowired
	ConstitutionofVCPCService constitutionofVCPCService;
	
	@ResponseBody
	@RequestMapping(value="/saveVCPCConstitution", method={RequestMethod.POST})
	public void saveVCPCConstitution(@RequestBody ConstitutionOfVCPCModel constitutionOfVCPCModel){
		constitutionofVCPCService.saveConstitutionofVCPC(constitutionOfVCPCModel);
	}
}
