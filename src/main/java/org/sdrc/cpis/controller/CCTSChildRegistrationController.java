package org.sdrc.cpis.controller;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CCTSChildRegistrationModel;
import org.sdrc.cpis.services.CCTSChildRegistrationService;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CCTSChildRegistrationController {
	
	@Autowired private CCTSChildRegistrationService cCTSChildRegistrationService;
	
	@Autowired private StateManager stateManager;

	@Authorize(feature="childRegistration",permission="edit")
	@RequestMapping(value= {"/childRegistration"}, method= {RequestMethod.POST})
	@ResponseBody
	public String childRegistration(@RequestBody CCTSChildRegistrationModel cctsChildRegistrationModel, HttpServletResponse response)throws Exception{
//		System.out.println("****************************"+cCTSChildRegistrationService.saveChildRegistrationDetails(cctsChildRegistrationModel)+"************************");
		return cCTSChildRegistrationService.saveChildRegistrationDetails(cctsChildRegistrationModel);
		
	}
	
	@Authorize(feature="childRegistration",permission="edit")
	@RequestMapping(value="child_registration")
	public String getChildRegPage(){
		return "/child_registration";
		
	}

	@Authorize(feature="childRegistration",permission="view")
	@RequestMapping(value = "/getChild", method = RequestMethod.GET)
	@ResponseBody
	public CCTSChildRegistrationModel getChildRegistration(@RequestParam("childId") String childId)
			throws Exception {
		return cCTSChildRegistrationService.getChildRegistration(childId);
	}
	
	
	@Authorize(feature="child_registration_view",permission="edit")
	@RequestMapping(value = "/updateChild", method = RequestMethod.POST)
	@ResponseBody
	public String updateChildRegistration(@RequestBody CCTSChildRegistrationModel cctsChildRegistrationModel, HttpServletResponse response)
			throws Exception {
		return cCTSChildRegistrationService.updateChildRegistration(cctsChildRegistrationModel);
	}

	@Authorize(feature="child_registration_view",permission="view")
	@RequestMapping(value="/child_registration_view")
	public ModelAndView getRegistrationView(@RequestParam("selectedChildId") String selectedChildId) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("selectedChild", selectedChildId);
		modelAndView.setViewName("child_registration_view");
//		model.addAttribute("selectedChild", selectedChildId);
		return modelAndView;
	}
	
//	@RequestMapping(value="/getDistrictCaseNumbers")
//	@ResponseBody
//	public List<String> getDistrictCaseNumbers(){
//		UserDetailModel userDetailModel= (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
//		return null;
//	}

}
