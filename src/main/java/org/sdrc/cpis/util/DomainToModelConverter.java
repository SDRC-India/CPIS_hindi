package org.sdrc.cpis.util;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.UserDetails;

public class DomainToModelConverter {
	public static ValueObject getDistrict(AreaDetails district){
		ValueObject districtObj=new ValueObject();
		
		districtObj.setId(district.getAreaId());
		districtObj.setName(district.getAreaName());
		return districtObj;
		
	}
	
	public static ValueObject getCWC(UserDetails userDetails){
		ValueObject userObj=new ValueObject();
		userObj.setId(userDetails.getUserId());
		userObj.setName(userDetails.getCwcName());
		return userObj;
	}
	
	public static ValueObject getCCIObject(CCIDetails cciDetails){
		ValueObject cciObject=new ValueObject();
		cciObject.setId(cciDetails.getCciId());
		cciObject.setName(cciDetails.getCciName());
		return cciObject;
	}
	
}
