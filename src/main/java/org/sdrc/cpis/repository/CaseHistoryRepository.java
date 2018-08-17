package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CaseHistoryCCI;
import org.springframework.transaction.annotation.Transactional;

public interface CaseHistoryRepository {

	@Transactional
	void save(CaseHistoryCCI caseHistoryCCI);

	CaseHistoryCCI getByChildId(String childId);
	
	List<CaseHistoryCCI> findCaseHistoryCCIWithinTimePeriod(Date startDate,Date endDate);

}
