package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.IndividualCarePlanA;
import org.sdrc.cpis.repository.IcpPersonalDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataIcpPersonalDetailsRepository extends Repository<IndividualCarePlanA, Integer>, IcpPersonalDetailsRepository {

	@Override
	@Query("SELECT icpA from IndividualCarePlanA icpA WHERE icpA.childId.childId like :childId")
	IndividualCarePlanA getPersonalDetailsByChildId(@Param("childId")String childId);


@Override
@Query("SELECT icpA FROM IndividualCarePlanA icpA WHERE "
		+ "icpA.DateOfICP BETWEEN :startDate AND :endDate")
public List<IndividualCarePlanA> findIndividualCarePlanAWithinTimePeriod(
		@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
