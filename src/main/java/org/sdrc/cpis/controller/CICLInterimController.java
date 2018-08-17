package org.sdrc.cpis.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CICLCaseMoniteringSheetModel;
import org.sdrc.cpis.models.CICLChildCareInstitutionPendingInquiryModel;
import org.sdrc.cpis.models.CICLSupervisionOrderModel;
import org.sdrc.cpis.services.CICLInterimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CICLInterimController {
	
	@Autowired
	CICLInterimService ciclInterimService;
	
		 @Authorize(feature="ciclinterimOrder",permission="edit")
	     @RequestMapping(value = {"/saveSupervisionOrder"}, method = {RequestMethod.POST})
	     @ResponseBody
	     public String saveCICLSupervisionOrder(@RequestBody CICLSupervisionOrderModel ciclSupervisionOrderModel){
		
		 ciclInterimService.saveCICLSupervisionOrder(ciclSupervisionOrderModel);
		
		 return null;
	     }
		
		@Authorize(feature="ciclinterimOrder",permission="edit")
		@RequestMapping(value = {"/saveChildCareInstitutionPendingInquiry"}, method = {RequestMethod.POST})
		@ResponseBody
		public String saveCICLChildCareInstitutionPendingInquiry(@RequestBody CICLChildCareInstitutionPendingInquiryModel cICLChildCareInstitutionPendingInquiryModel){
			ciclInterimService.saveCICLChildCareInstitutionPendingInquiry(cICLChildCareInstitutionPendingInquiryModel);
			return null;
		}
		
		@Authorize(feature="ciclinterimOrder",permission="edit")
		@RequestMapping(value={"/saveCiclCaseMonitoring"}, method = {RequestMethod.POST})
		@ResponseBody
		public String saveCiclCaseMonitoring(@RequestBody CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel) throws Exception{
			ciclInterimService.saveCICLCaseMoniteringSheet(ciclCaseMoniteringSheetModel);
			return null;
		}
		
		@RequestMapping(value={"/getAllCiclCaseMonitoringData"}, method = {RequestMethod.GET})
		@ResponseBody
		public List<CICLCaseMoniteringSheetModel> getAllCiclCaseMonitoringData(@RequestParam("childId") String childId) throws Exception{
			return ciclInterimService.getCiclCaseMonitoringByChildId(childId);
		}
		
//		@RequestMapping(value={"/getSupervisionOrderData"}, method = {RequestMethod.GET})
//		@ResponseBody
//		public CICLSupervisionOrderModel getSupervisionOrderData(@RequestParam("childId") String childId){
//			return ciclInterimService.getSupervisionOrderByChildId(childId);
//		}
		
//		@RequestMapping(value = {"/getChildCareInstitutionPendingInquiry"}, method = {RequestMethod.GET})
//		@ResponseBody
//		public CICLChildCareInstitutionPendingInquiryModel getChildCareInstitutionPendingInquiry(@RequestParam("childId") String childId){
//			return ciclInterimService.getCCIinquiryByChildId(childId);
//		}
		
		@RequestMapping(value= {"/getAllCiclInterimRecords"}, method = {RequestMethod.GET})
		@ResponseBody
		public List<Object> getAllCiclInterimRecords(@RequestParam("childId") String childId){
			return ciclInterimService.fetchAll(childId);
		}
		
		@RequestMapping(value="/ciclinterimOrder")
		public String getCaseHistory(@RequestParam("selectedChildId") String selectedChildId, Model model)
				throws ParseException{
			model.addAttribute("selectedChild", selectedChildId);
			return "ciclinterimOrder";
		}


}
