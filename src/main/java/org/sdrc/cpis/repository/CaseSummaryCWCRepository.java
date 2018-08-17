package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.CaseSummaryByCWC;
import org.springframework.transaction.annotation.Transactional;

public interface CaseSummaryCWCRepository {

	@Transactional
	void save(CaseSummaryByCWC caseSummaryByCWC);
	
	CaseSummaryByCWC findByChildIdChildId(String childId);

}
