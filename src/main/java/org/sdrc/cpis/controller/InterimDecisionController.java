package org.sdrc.cpis.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.CaseMonitoringModel;
import org.sdrc.cpis.models.ChildDetailsModel;
import org.sdrc.cpis.models.ChildInFitInstitutionModel;
import org.sdrc.cpis.models.FitPersonDetailModel;
import org.sdrc.cpis.models.FosterCareDetailsModel;
import org.sdrc.cpis.services.InterimDecisionService;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InterimDecisionController {
	
	@Autowired
	private InterimDecisionService interimDecisionService;

	@Authorize(feature="interim_order",permission="edit")
	@RequestMapping(value = {"/getCaseMonitoringDetails"}, method = {RequestMethod.POST})
	@ResponseBody
	public String getCaseMonitoringDetails(@RequestBody CaseMonitoringModel caseMonitoringModel, HttpServletResponse response) throws Exception{
		interimDecisionService.saveChildInterimData(caseMonitoringModel);
		return null;
		
	}
	
	@Authorize(feature="interim_order",permission="edit")
	@RequestMapping(value = {"/fosterCareDetails"}, method = {RequestMethod.POST})
	@ResponseBody
	public String getFosterCareDetails(@RequestBody FosterCareDetailsModel fosterCareDetailsModel, HttpServletResponse response){
		interimDecisionService.saveFosterCareDetails(fosterCareDetailsModel);
		return null;
	}
	
	
	@Authorize(feature="interim_order",permission="edit")
	@RequestMapping(value="/interim_order")
	public String getInterimOrder(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		return "interim_order";
	}
	
//	@Authorize(feature="interim_order",permission="view")
	@ResponseBody
	@RequestMapping(value="/getChildById")
	public ChildDetailsModel getChildById(@RequestParam("selectedChildId") String selectedChildId){
		return interimDecisionService.fetchChildByChildId(selectedChildId);
	}
	
	@Authorize(feature="interim_order",permission="edit")
	@RequestMapping(value={"/fitInstitutionDetails"}, method={RequestMethod.POST})
	public String fitInstitutionDetails(@RequestBody ChildInFitInstitutionModel chidlInFitInstitutionModel, HttpServletResponse response){
		interimDecisionService.saveChildInFitInstituion(chidlInFitInstitutionModel);
		return null;
		
	}
	
	@Authorize(feature="interim_order",permission="edit")
	@ResponseBody
	@RequestMapping(value={"/saveFitPersonData"}, method = {RequestMethod.POST})
	public String saveFitPersonData(@RequestBody FitPersonDetailModel fitPersonDetailModel){
		interimDecisionService.saveFitPersonDetails(fitPersonDetailModel);
		return null;
	}
	
//	@Authorize(feature="interim_order",permission="view")
	@RequestMapping(value={"/findAllInterimOrders"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<Object> fetchAllInterimOrder(@RequestParam("childId")String childId) {
		return interimDecisionService.fetchAllInterimOrder(childId);
	}
	
//	@Authorize(feature="interim_order",permission="view")
	@RequestMapping(value={"/findAllFosterOrders"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<FosterCareDetailsModel> findAllFosterOrders(@RequestParam("childId")String childId) {
		return interimDecisionService.findAllFosterOrders(childId);
	}
	
	@Authorize(feature="interim_order",permission="view")
	@RequestMapping(value={"/findCaseMonitoring"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<CaseMonitoringModel> fetchCaseMonitoring(@RequestParam("childId")String childId) throws Exception {
		return interimDecisionService.fetchCaseMonitoring(childId);
	}
	
	@RequestMapping(value={"/getCciList"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<ValueObject> getAreaWiseCciList(@RequestParam("areaId")Integer areaId)
			 {
		return interimDecisionService.getAreaWiseCciList(areaId);
	}
	
	@RequestMapping(value={"/getAllCciList"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<ValueObject> getAllCciList()
			 {
		return interimDecisionService.getAllCciList();
	}
	
	
	/*@ResponseBody
	@RequestMapping(value={"/updateFosterCareDetailsData"}, method = {RequestMethod.POST})
	public String updateFosterCareDetails(@RequestBody FosterCareDetailsModel fosterCareDetailsModel){
		interimDecisionService.updateFosterCareDetails(fosterCareDetailsModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value={"/updateChildInFitInstituionData"}, method = {RequestMethod.POST})
	public String updateChildInFitInstituion(@RequestBody ChildInFitInstitutionModel childInFitInstitutionModel){
		interimDecisionService.updateChildInFitInstituion(childInFitInstitutionModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value={"/updateFitPersonDataData"}, method = {RequestMethod.POST})
	public String updateFitPersonData(@RequestBody FitPersonDetailModel fitPersonDetailModel){
		interimDecisionService.updateFitPersonDetails(fitPersonDetailModel);
		return null;
	}*/
}
