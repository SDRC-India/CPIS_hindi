package org.sdrc.cpis.controller;

import java.util.List;

import org.sdrc.cpis.models.ConstitutionOfBCPCModel;
import org.sdrc.cpis.services.ConstitutionofBCPCService;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionofBCPCController {

	@Autowired
	ConstitutionofBCPCService constitutionofBCPCService;
	
	@ResponseBody
	@RequestMapping(value="/saveBCPCConstitution", method={RequestMethod.POST})
	public void saveBCPCConstitution(@RequestBody ConstitutionOfBCPCModel constitutionOfBCPCModel){
		constitutionofBCPCService.saveConstitutionofBCPC(constitutionOfBCPCModel);
	}
	
	@ResponseBody
	@RequestMapping(value="/getBCPCConstitution", method={RequestMethod.GET})
	public List<ValueObject> getBlockList(){
		return constitutionofBCPCService.getBlockList();
	}
	
}
