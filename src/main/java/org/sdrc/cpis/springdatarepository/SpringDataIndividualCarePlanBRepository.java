package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.IndividualCarePlanB;
import org.sdrc.cpis.repository.IndividualCarePlanBRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataIndividualCarePlanBRepository extends Repository<IndividualCarePlanB, Integer>, IndividualCarePlanBRepository {

	@Override
	@Query("SELECT icpB from IndividualCarePlanB icpB WHERE icpB.childId.childId like :childId")
	List<IndividualCarePlanB> getProgressReports(@Param("childId")String childId);
	
	@Override
	@Query("Select COUNT(icpB) from IndividualCarePlanB icpB WHERE icpB.childId.childId like :childId")
	Long countByChildId(@Param("childId")String childId);
}
