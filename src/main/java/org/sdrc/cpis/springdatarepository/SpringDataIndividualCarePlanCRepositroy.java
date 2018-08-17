package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.IndividualCarePlanC;
import org.sdrc.cpis.repository.IndividualCarePlanCRepositroy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataIndividualCarePlanCRepositroy extends IndividualCarePlanCRepositroy, Repository<IndividualCarePlanC, Integer> {

	@Override
	@Query("SELECT icpC from IndividualCarePlanC icpC WHERE icpC.childId.childId like :childId")
	IndividualCarePlanC getReportByChildId(@Param("childId")String childId);
}
