package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.FosterCareDetails;
import org.sdrc.cpis.repository.FosterCareDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataFosterCareDetailsRepository extends
		FosterCareDetailsRepository, Repository<FosterCareDetails, Integer> {
	
	@Override
	@Query("SELECT foster FROM FosterCareDetails foster WHERE "
			+ "foster.dateOfFormFilled BETWEEN :startDate AND :endDate ")
	public List<FosterCareDetails> findFosterCareDetailsBetweenTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
