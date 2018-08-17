package org.sdrc.cpis.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CaseHistoryCCIModel;
import org.sdrc.cpis.services.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CaseHistoryController {
	
	@Autowired
	private CaseHistoryService caseHistoryService;
	
	@RequestMapping(value = {"/saveCaseHistory"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveCaseHistory(@RequestBody CaseHistoryCCIModel caseHistoryCCIModel) throws Exception{
		
		caseHistoryService.saveCaseHistoryData(caseHistoryCCIModel);
		
		return null;
	}
	
	@RequestMapping(value="/caseHistoryByChildId")
	@ResponseBody
	public CaseHistoryCCIModel getCaseHistoryByChildId(@RequestParam("childId") String childId, HttpSession session) throws Exception{
		String lang = (String) session.getAttribute("languageString");
		return caseHistoryService.getCHDataByChildId(childId, lang);
	}
	
	@Authorize(feature="caseHistory",permission="view")
	@RequestMapping(value="/caseHistory")
	public String getCaseHistory(@RequestParam("selectedChildId") String selectedChildId, Model model)
			throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "caseHistory";
	}

}
