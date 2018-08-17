package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.models.ChildInFosterCareModel;
import org.sdrc.cpis.services.ChildInFosterCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChildInFosterCareController {
	
	@Autowired
	private ChildInFosterCareService childInFosterCareService;
	
	@RequestMapping(value = {"/saveFosterCareRecord"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveFosterCareRecord(@RequestBody ChildInFosterCareModel childInFosterCareModel) throws Exception{
		childInFosterCareService.saveChildInFosterCare(childInFosterCareModel);
		return null;
	}
	
	@RequestMapping(value = {"/getFosterCareRecord"}, method = {RequestMethod.GET})
	@ResponseBody
	public ChildInFosterCareModel getFosterCareRecord(@RequestParam("childId") String childId) throws Exception{
		return childInFosterCareService.getChildInFosterCare(childId);
	}
	
//	@Authorize(feature="childInFosterCare",permission="view")
	@RequestMapping(value="/childfostercare")
	public String getCaseHistory(@RequestParam("selectedChildId") String selectedChildId, Model model)
			throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "childfostercare";
	}

}
