package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.RestorationDetails;
import org.springframework.transaction.annotation.Transactional;

public interface RestorationRepository {

	@Transactional
	void save(RestorationDetails restorationDetails);
	
	RestorationDetails findByChildIdChildId(String childId);
	
	List<RestorationDetails> findWithinRestorationDetailsTimePeriod(Date startDate,Date endDate);

	List<RestorationDetails> findByChildIdProgramType(int programmType);
}
