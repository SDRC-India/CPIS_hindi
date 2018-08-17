package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.IndividualCarePlanB;
import org.springframework.transaction.annotation.Transactional;

public interface IndividualCarePlanBRepository {

	@Transactional
	void save(IndividualCarePlanB individualCarePlanB);
	
	List<IndividualCarePlanB> getProgressReports(String childId);
	
	Long countByChildId(String childId);
}
