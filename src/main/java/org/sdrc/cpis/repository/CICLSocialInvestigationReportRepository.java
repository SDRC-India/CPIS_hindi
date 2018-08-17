package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CICLSocialInvestigationReport;

public interface CICLSocialInvestigationReportRepository {

	@Transactional
	CICLSocialInvestigationReport save(CICLSocialInvestigationReport ciclSocialInvestigationReport);
	
	List<CICLSocialInvestigationReport> findByChildId(String childId);
	
	List<CICLSocialInvestigationReport> findCICLSocialInvestigationReportWithinTimePeriod(Date startDate,Date endDate);
}
