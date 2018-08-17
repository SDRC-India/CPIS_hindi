package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.RestorationDetails;
import org.sdrc.cpis.repository.RestorationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataRestorationRepository extends RestorationRepository,
		Repository<RestorationDetails, Integer> {

	@Override
	@Query("SELECT rd FROM RestorationDetails rd WHERE "
			+ " rd.orderDate BETWEEN :startDate AND :endDate ")
	public List<RestorationDetails> findWithinRestorationDetailsTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
