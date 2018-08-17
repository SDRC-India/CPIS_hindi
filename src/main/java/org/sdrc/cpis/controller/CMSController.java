package org.sdrc.cpis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.sdrc.cpis.domains.Header;
import org.sdrc.cpis.models.ContentObject;
import org.sdrc.cpis.models.DashboardIndicatorComparasionModel;
import org.sdrc.cpis.services.CMSService;
import org.sdrc.cpis.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@SessionAttributes(value="languageSet")
public class CMSController {
	@Autowired
	private CMSService cmsService;
	
	@Autowired
	private DashboardService dashboardService;
	


	@RequestMapping("/getHeaders")
	@ResponseBody
	public Header getHeader(@RequestParam("id") Integer id) {
		return cmsService.getHeader(id);

	}

	@RequestMapping("/")
	public String home() {
		return "home";

	}
	
	
//	@ModelAttribute(value="languageSet")
//	private int setLanguage() {
//		return selectedLang;
//	}

	@RequestMapping("/getContent")
	@ResponseBody
	public List<ContentObject> getHeader(@RequestParam("viewName") String viewName,
							@RequestParam("language") Integer language,HttpSession session) {
		Object obj = session.getAttribute("languageSet");
		int languageSet =1;
		if(obj!=null) {
			Integer languageSetObj = (Integer) obj;
			languageSet = languageSetObj;
		}
		return cmsService.getPageContent(viewName,languageSet);

	}
	
	@RequestMapping("/setLang")
	@ResponseBody
	public void setLang(@RequestParam("language") Integer language,HttpSession session ) {
		session.setAttribute("languageSet", language);
	}
	
	@RequestMapping("/setLangString")
	@ResponseBody
	public void setLangString(@RequestParam("language") String language,HttpSession session ) {
		session.setAttribute("languageString", language);
	}
	
	@RequestMapping("/getLang")
	@ResponseBody
	public String getLang(HttpSession session ) {
		return session.getAttribute("languageString")==null?"en":(String) session.getAttribute("languageString");
	}
	
	@RequestMapping("getOverAllScoreOfIndicator")
	@ResponseBody
	public List<DashboardIndicatorComparasionModel> getOverAllScoreOfIndicator(HttpSession session)
	{
		Object obj = session.getAttribute("languageSet");
		int languageSet =1;
		if(obj!=null) {
			Integer languageSetObj = (Integer) obj;
			languageSet = languageSetObj;
		}
		return dashboardService.getIusDataForCpisHome(languageSet);
	}

}
