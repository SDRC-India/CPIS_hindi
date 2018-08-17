package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.IndividualCarePlanC;
import org.springframework.transaction.annotation.Transactional;

public interface IndividualCarePlanCRepositroy {

	@Transactional
	void save(IndividualCarePlanC individualCarePlanC);

	IndividualCarePlanC getReportByChildId(String childId);
}
