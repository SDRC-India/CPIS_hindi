package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CICLCaseMoniteringSheet;
import org.springframework.transaction.annotation.Transactional;

public interface CICLCaseMonitoringSheetRepository {

	@Transactional
	void save(CICLCaseMoniteringSheet ciclCaseMoniteringSheet);

	List<CICLCaseMoniteringSheet> getByChildId(String childId);
	
	List<CICLCaseMoniteringSheet> findCICLCaseMoniteringSheetWithinTimePeriod(Date startDate,Date endDate);

}
