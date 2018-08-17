package org.sdrc.cpis.controller;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.AfterCarePlacementOrderModel;
import org.sdrc.cpis.models.CaseSummaryCWCModel;
import org.sdrc.cpis.models.LegallyFreeForAdoptionModel;
import org.sdrc.cpis.models.OrderModel;
import org.sdrc.cpis.models.RestorationModel;
import org.sdrc.cpis.models.SponsorshipModel;
import org.sdrc.cpis.services.FinalOrderService;
import org.sdrc.cpis.services.LegallyFreeForAdoptionService;
import org.sdrc.cpis.services.RestorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FinalOrderController {
	
	@Autowired
	private FinalOrderService finalOrderService;
	
	@Autowired
	private RestorationService restorationService;
	
	@Autowired
	private LegallyFreeForAdoptionService legallyFreeForAdoptionService;
	
	@Authorize(feature="finalOrder",permission="edit")
	@RequestMapping(value={"/saveCaseSummaryCWCData"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveCaseSummaryCWCData(@RequestBody CaseSummaryCWCModel caseSummaryCWCModel) throws Exception{
		finalOrderService.saveCaseSummaryCWCData(caseSummaryCWCModel);
		return null;
	}
	
//	@Authorize(feature="case_summary",permission="view")
//	@RequestMapping(value="/case_summary")
//	public String getInterimOrder(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
//		model.addAttribute("selectedChild", selectedChildId);
//		return "case_summary";
//	}

	
	@RequestMapping(value = "/getCaseSummary", method = RequestMethod.GET)
	@ResponseBody
	public CaseSummaryCWCModel getAllCaseSummary(@RequestParam("childId") String childId)
			throws Exception {
		return finalOrderService.getAllCaseSummary(childId);
	}
	
	@Authorize(feature="finalOrder",permission="edit")
	@RequestMapping(value="/finalOrder")
	public String getFinalOrder(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "finalOrder";
	}
	
	@Authorize(feature="finalOrder",permission="edit")
	@RequestMapping(value={"/saveSponsorshipOrder"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveSponsorshipOrder(@RequestBody SponsorshipModel sponsorshipModel){
		finalOrderService.saveSponsorshipOrder(sponsorshipModel);
		return null;
	}

	@Authorize(feature="finalOrder",permission="edit")
	@RequestMapping(value={"/saveAfterCareData"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveAfterCareData(@RequestBody AfterCarePlacementOrderModel afterCarePlacementOrderModel){
		finalOrderService.saveAfterCareData(afterCarePlacementOrderModel);
		return null;
	}

	@Authorize(feature="finalOrder",permission="edit")
	@RequestMapping(value="/uploadEscortPDF", method=RequestMethod.POST)
	@ResponseBody
	public boolean uploadEscortPDF(@RequestBody OrderModel orderModel) throws Exception{
		return finalOrderService.savePdf(orderModel);
	}
	
	@RequestMapping(value="/getChildEscortAndRehab",method = RequestMethod.GET)
	@ResponseBody
	public OrderModel getChildEscortAndRehab(@RequestParam("childId") String childId) throws Exception{
//		String childId="235-Allahbad-2017";
		return finalOrderService.getChildEscortAndRehab(childId);
	}
	
	@RequestMapping(value = "/getSponsorshipData", method = RequestMethod.GET)
	@ResponseBody
	public SponsorshipModel getSponsorshipData(@RequestParam("childId") String childId)
			throws Exception {
		return finalOrderService.getSponsorshipData(childId);
	}
	
	@Authorize(feature="finalOrder",permission="edit")
	@ResponseBody
	@RequestMapping(value={"/saveRestorationData"}, method = {RequestMethod.POST})
	public String saveRestorationData(@RequestBody RestorationModel restorationModel){
		restorationService.saveRestorationData(restorationModel);
		return null;
	}
	@ResponseBody
	@RequestMapping(value={"/getRestorationData"}, method = {RequestMethod.GET})
	public RestorationModel getAllRestorationData(@RequestParam("childId") String childId){
		return restorationService.getAllRestorationData(childId);
		
	}
	
	@ResponseBody
	@RequestMapping(value={"/getAfterCareData"}, method = {RequestMethod.GET})
	public AfterCarePlacementOrderModel getAfterCareData(@RequestParam("childId") String childId){
		return finalOrderService.getAfterCareData(childId);
		
	}
	
	@Authorize(feature="finalOrder",permission="edit")
	@ResponseBody
	@RequestMapping(value={"/saveLegallyFreeData"}, method = {RequestMethod.POST})
	public String saveLegallyFreeData(@RequestBody LegallyFreeForAdoptionModel legallyFreeForAdoptionModel) throws Exception{
		legallyFreeForAdoptionService.saveLegallyFreeForAdoptionData(legallyFreeForAdoptionModel);
		return null;
	}
	@ResponseBody
	@RequestMapping(value={"/getLegallyFreeForAdoptionData"}, method = {RequestMethod.GET})
	public LegallyFreeForAdoptionModel getAllLegallyFreeForAdoptionData(@RequestParam("childId") String childId) throws Exception{
		return legallyFreeForAdoptionService.getAllLegallyFreeForAdoptionData(childId);
		
	}
	
}
