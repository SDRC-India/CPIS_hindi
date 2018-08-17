package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.IndividualCarePlanA;
import org.springframework.transaction.annotation.Transactional;

public interface IcpPersonalDetailsRepository {

	@Transactional
	void save(IndividualCarePlanA individualCarePlanA);
	
	IndividualCarePlanA getPersonalDetailsByChildId(String childId);
	
	List<IndividualCarePlanA> findIndividualCarePlanAWithinTimePeriod(Date startDate,Date endDate);
}
