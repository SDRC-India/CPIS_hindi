package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.AfterCarePlacementOrder;
import org.sdrc.cpis.repository.AfterCarePlacementOrderRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataAfterCarePlacementOrderRepository extends
	   AfterCarePlacementOrderRepository, Repository<AfterCarePlacementOrder, Integer> {

	@Override
	@Query("SELECT acp FROM AfterCarePlacementOrder acp WHERE acp.childId.childId = :childId")
	AfterCarePlacementOrder getChildWiseAfterCare(@Param("childId")String childId);
}
