package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.AreaDetails;

public interface AreaRepository {
	
	AreaDetails findByAreaId(int areaId);
	
	List<AreaDetails> fetchAllArea();
	
	List<AreaDetails> fetchUserWiseArea(Integer areaId);

	AreaDetails fetchAreaById(Integer areaId);

	AreaDetails fetchAreaByCode(String areaCode);
	
	List<AreaDetails> fetchAreaByLevel(Integer areaLevel);
	
	List<AreaDetails> getAllAreaDetails();

	List<AreaDetails> findByParentAreaAreaId(Integer areaId);

	List<AreaDetails> findAllByAreaId(int userAreaId);

	List<AreaDetails> findAllByParentAreaAreaId(int userAreaId);

	List<AreaDetails> findAllByParentAreaParentAreaAreaId(int userAreaId);

	List<AreaDetails> findAllByParentAreaParentAreaAreaIdOrParentAreaAreaIdOrAreaId(int userAreaId, int userAreaId2,
			int userAreaId3);

	List<AreaDetails> findAllByParentAreaAreaIdOrAreaId(int userAreaId, int userAreaId2);
	
}
