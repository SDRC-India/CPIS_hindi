package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CaseMonitoring;

public interface CaseMonitoringRepository {

	@Transactional
	void save(CaseMonitoring caseMonitoring);
	
	List<CaseMonitoring> findByChildIdChildId(String childId);

}
