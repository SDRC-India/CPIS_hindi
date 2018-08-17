package org.sdrc.cpis.controller;

import org.sdrc.cpis.services.RestorationService;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RestorationController {
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private RestorationService restorationService;

//	@RequestMapping(value="/restoration_order")
//	public String getInterimOrder(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
//		model.addAttribute("selectedChild", selectedChildId);
//		model.addAttribute("cwcName", ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getUserName());
//		model.addAttribute("district", ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getAreaName());
//		return "restoration_order";
//	}
	
//	@ResponseBody
//	@RequestMapping(value={"/saveRestorationData"}, method = {RequestMethod.POST})
//	public String saveRestorationData(@RequestBody RestorationModel restorationModel){
//		restorationService.saveRestorationData(restorationModel);
//		return "/restoration_order";
//	}
//	@ResponseBody
//	@RequestMapping(value={"/getRestorationData"}, method = {RequestMethod.GET})
//	public RestorationModel getAllRestorationData(@RequestParam("childId") String childId){
//		return restorationService.getAllRestorationData(childId);
//		
//	}
}
