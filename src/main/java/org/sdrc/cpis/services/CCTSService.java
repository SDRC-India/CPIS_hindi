package org.sdrc.cpis.services;

import java.util.List;
import java.util.Map;

import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.CCTSTypeDetailsModel;
import org.sdrc.cpis.models.ChildDetailsModel;
import org.sdrc.cpis.models.GridMenuItemModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.util.ValueObject;

public interface CCTSService {
	Map<String, Object> fetchType();
	
	List<CCIInfoMapModel> fetchCCISAAList();
	
	List<UserDetailModel> fetchCWCs();
	
	List<ChildDetailsModel> fetchChildDetails() throws Exception;
	
	List<GridMenuItemModel> getGridMenuItems(Integer userId);
	
	List<ValueObject> getAllTypeDetails();
	
	List<AreaDetailsModel> getUserWiseArea(Integer areaId);
	
	List<AreaDetailsModel> getDistrictList(Integer areaLevel);

	List<CCTSTypeDetailsModel> fetchAllType();
	
}
