package org.sdrc.cpis.services;

import org.sdrc.cpis.models.CaseHistoryCCIModel;
import org.springframework.transaction.annotation.Transactional;

public interface CaseHistoryService {

	@Transactional
	void saveCaseHistoryData(CaseHistoryCCIModel caseHistoryCCIModel) throws Exception;

	CaseHistoryCCIModel getCHDataByChildId(String childId, String lang) throws Exception;

}
