package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.ChildPlacedInFitInstitution;

public interface ChildInFitInstitutionRepository {

	@Transactional
	void save(ChildPlacedInFitInstitution childPlacedInFitInstitution);
	
	List<ChildPlacedInFitInstitution> findByChildIdChildId(String childId);
	
	List<ChildPlacedInFitInstitution> findChildPlacedInFitInstitutionWithinTimePeriod(Date startDate,Date endDate);

	List<ChildPlacedInFitInstitution> findAll();

	List<ChildPlacedInFitInstitution> findByPeriodForWhichSentToFitInstitutionGreaterThan(
			int period);
}
