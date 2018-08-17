package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.repository.AreaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataAreaRepository extends AreaRepository, Repository<AreaDetails, Integer> {
	@Override
	@Query("select area from AreaDetails area")
	List<AreaDetails> fetchAllArea();
	
	@Override
	@Query("SELECT area from AreaDetails area where area.parentArea.areaId= :areaId")
	List<AreaDetails> fetchUserWiseArea(@Param("areaId")Integer areaId);
	
	@Override
	@Query("SELECT area from AreaDetails area where area.areaId= :areaId")
	AreaDetails fetchAreaById(@Param("areaId")Integer areaId);
	
	@Override
	@Query("SELECT area from AreaDetails area where area.areaCode= :areaCode")
	AreaDetails fetchAreaByCode(@Param("areaCode")String areaCode);
	
	@Override
	@Query("SELECT area from AreaDetails area where area.areaLevel.levelId= :areaLevel")
	List<AreaDetails> fetchAreaByLevel(@Param("areaLevel")Integer areaLevel);
	
	@Override
	@Query("select areaDetail from AreaDetails areaDetail")
	 List<AreaDetails> getAllAreaDetails(); 
	
}
