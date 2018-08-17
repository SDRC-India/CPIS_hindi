package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.IndividualCarePlanD;
import org.springframework.transaction.annotation.Transactional;

public interface IndividualCarePlanDRepository {

	@Transactional
	void save(IndividualCarePlanD individualCarePlanD);

	IndividualCarePlanD getPostReleaseReportByChildId(String childId);

	List<IndividualCarePlanD> findAdmissionDateBetweenTimePeriod(
			Date startDate, Date endDate);
}
