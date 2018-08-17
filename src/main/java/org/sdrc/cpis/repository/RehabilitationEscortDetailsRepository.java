package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.RehabilationEscortOrder;
import org.springframework.transaction.annotation.Transactional;

public interface RehabilitationEscortDetailsRepository {
	
	@Transactional
	void save(RehabilationEscortOrder rehabilationEscortOrder);

	RehabilationEscortOrder findByChildId(String childId);

}
