package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.AfterCarePlacementOrder;
import org.springframework.transaction.annotation.Transactional;

public interface AfterCarePlacementOrderRepository {

	@Transactional
	void save(AfterCarePlacementOrder afterCarePlacementOrder);
	
	AfterCarePlacementOrder getChildWiseAfterCare(String childId);
}
