package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.CICLPeriodicReport;
import org.springframework.transaction.annotation.Transactional;

public interface CICLPeriodicReportRepository {

	@Transactional
	CICLPeriodicReport save(CICLPeriodicReport ciclPeriodicReport);
	
	CICLPeriodicReport findByChildIdChildId(String childId);
}
