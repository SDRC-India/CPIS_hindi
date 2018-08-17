package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.IndividualCarePlanD;
import org.sdrc.cpis.repository.IndividualCarePlanDRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataIndividualCarePlanDRepository extends IndividualCarePlanDRepository,
							Repository<IndividualCarePlanD, Integer> {

	@Override
	@Query("SELECT icpDdata FROM IndividualCarePlanD icpDdata WHERE icpDdata.childId.childId like :childId")
	IndividualCarePlanD getPostReleaseReportByChildId(@Param("childId")String childId);
	
	@Override
	@Query("SELECT order FROM IndividualCarePlanD order WHERE "
			+ " order.createdDate BETWEEN :startDate AND :endDate ")
	public List<IndividualCarePlanD> findAdmissionDateBetweenTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
