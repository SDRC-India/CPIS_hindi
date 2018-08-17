package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.RehabilationEscortOrder;
import org.sdrc.cpis.repository.RehabilitationEscortDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataRehabilitationEscortDetailsRepository extends
		RehabilitationEscortDetailsRepository, Repository<RehabilationEscortOrder, Integer>{
	
	@Override
	@Query("SELECT reo from RehabilationEscortOrder reo where reo.childId.childId= :childId")
	RehabilationEscortOrder findByChildId(@Param("childId")String childId);

}
