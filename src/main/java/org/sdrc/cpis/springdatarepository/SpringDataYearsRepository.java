package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.Years;
import org.sdrc.cpis.repository.YearsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataYearsRepository extends YearsRepository, Repository<Years, Integer>{
	
	@Override
	@Query("select yr from Years yr")
	public List<Years> fetchAllYears();
	
	@Override
	@Query("select years.year_period from Years years")
	public List<String> fetchYearPeriod();
	
}
