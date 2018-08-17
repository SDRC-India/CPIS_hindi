package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.ChildPlacedInFitInstitution;
import org.sdrc.cpis.repository.ChildInFitInstitutionRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataChildInFitInstitutionRepository extends
		ChildInFitInstitutionRepository,
		Repository<ChildPlacedInFitInstitution, Integer> {
	
	
	@Override
	@Query("SELECT child FROM ChildPlacedInFitInstitution child WHERE "
			+ " child.dateChildPlacedInFitInstitution BETWEEN :startDate AND :endDate ")
	public List<ChildPlacedInFitInstitution> findChildPlacedInFitInstitutionWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
