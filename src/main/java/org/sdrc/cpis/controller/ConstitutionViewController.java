package org.sdrc.cpis.controller;

import java.util.List;

import org.sdrc.cpis.models.ConstitutionViewListModel;
import org.sdrc.cpis.services.ConstitutionViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConstitutionViewController {

	@Autowired
	ConstitutionViewService constitutionViewService;
	
	@RequestMapping(value = "/getConstitutionView", method = RequestMethod.GET)
	@ResponseBody
	public List<ConstitutionViewListModel> getConstitutionView(@RequestParam("district") Integer district,
															   @RequestParam("constitutionType") Integer constitutionType,
															   @RequestParam(value="blockId", required= false) Integer blockId)
			throws Exception {
		return constitutionViewService.getConstitutionView(district,constitutionType, blockId);
	}
}
