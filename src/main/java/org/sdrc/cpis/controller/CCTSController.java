package org.sdrc.cpis.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.parser.ParseException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.CCTSTypeDetailsModel;
import org.sdrc.cpis.models.ChildDetailsModel;
import org.sdrc.cpis.models.GridMenuItemModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.CCTSService;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.ListObject;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CCTSController {
	@Autowired 
	private CCTSService cctsService;
	
	private StateManager stateManager;
	
	@Autowired
	public CCTSController(StateManager stateManager) {
		this.stateManager=stateManager;	
	}
	
	@Authorize(feature="ccts",permission="view")
	@RequestMapping(value="/ccts")
	public String getCctsHome(){
		return "/ccts";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllTypeDetails")
	public List<CCTSTypeDetailsModel> getAllTypeDetails(){
		return cctsService.fetchAllType();
	}
	
	@ResponseBody
	@RequestMapping(value="/getTypeDetails")
	public Map<String, Object> getTypeDetails(){
		return cctsService.fetchType();
	}
	
	@ResponseBody
	@RequestMapping(value="/getCCIlist")
	public List<CCIInfoMapModel> getCCIList(){
		List<CCIInfoMapModel> cciList=cctsService.fetchCCISAAList();
		return cciList;
	}
	
	@ResponseBody
	@RequestMapping(value="/getCWCList")
	public List<UserDetailModel> getAllCWCs(){
		List<UserDetailModel> cwcList = cctsService.fetchCWCs();
		return cwcList;
	}
	
	@ResponseBody
	@RequestMapping("getChildList")
    public List<ChildDetailsModel> getChildDetails() throws Exception{
		List<ChildDetailsModel> childList = cctsService.fetchChildDetails();
		return childList;
    }
	
	@ResponseBody
	@RequestMapping("getGridMenuItems")
	public List<GridMenuItemModel> getGridMenuItems(){
		UserDetailModel user=(UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		List<GridMenuItemModel> menuItems=cctsService.getGridMenuItems(user.getDesignationId());
		return menuItems;
	}
	
//	@ResponseBody
//	@RequestMapping("getCctsMstData")
//	public ListObject getCctsMstData(){
//		List<ChildDetailsModel> childList=getChildDetails();
//		List<GridMenuItemModel> menuList = getGridMenuItems();
//		
//		Map<String, Object> cctsMstData=new HashedMap();
//		cctsMstData.put("childList", childList);
//		cctsMstData.put("menuList", menuList);
//		
//		ListObject cctsMstDataObject=new ListObject();
//		cctsMstDataObject.setValue(cctsMstData);
//		return cctsMstDataObject;
//	}
	
	@ResponseBody
	@RequestMapping(value="/getChildRegMstData")
	public ListObject getChildRegMstData(){
//		ListObject typedetailsList = getTypeDetails();
//		List<CCIInfoMapModel> cciDetailsList= getCCIList();
//		List<UserDetailModel> cwcDetailsList = getAllCWCs();
		
		Map<String, Object> childRegMstData = new HashedMap();
		Map<String, Object> typeDetailsList=getTypeDetails();
		childRegMstData.put("ageList", typeDetailsList.get("ageList"));
		childRegMstData.put("sexList", typeDetailsList.get("genderList"));
		childRegMstData.put("organizationType", typeDetailsList.get("organizationType"));
		childRegMstData.put("cciList", getCCIList());
		childRegMstData.put("cwcList", getAllCWCs());
		
		ListObject childRegMstDataObj=new ListObject();
		childRegMstDataObj.setKey("mstData");
		childRegMstDataObj.setValue(childRegMstData);
		return childRegMstDataObj;
	}
	
	@ResponseBody
	@RequestMapping("getFilterOptions")
	public List<ValueObject> getFilterOptions(){
		List<ValueObject> typeDetailsList=cctsService.getAllTypeDetails();
		
		return typeDetailsList;
	}
	
	@ResponseBody
	@RequestMapping("getUserWiseAreaList")
	public List<AreaDetailsModel> areaList(){
		UserDetailModel user=(UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		List<AreaDetailsModel> areaList=cctsService.getUserWiseArea(user.getAreaId());
		
		return areaList;
	}
	
	@ResponseBody
	@RequestMapping("getDistrictList")
	public List<AreaDetailsModel> getAreaByLevel(@RequestParam("areaLevel")Integer areaLevel){
		List<AreaDetailsModel> areaList=cctsService.getDistrictList(areaLevel);
		
		return areaList;
	}
	
//	@Authorize(feature="childRegistration",permission="view")
//	@RequestMapping("child_registration")
//	public String getChildRegistrationPage(){
//		
//		return "child_registration";
//	}
	@Authorize(feature="constitutionofSociety",permission="edit")
	@RequestMapping(value="/constitutionofSociety")
	public String getConstitutionofSociety()
			throws ParseException{
		return "constitutionofSociety";
	}
	
}
