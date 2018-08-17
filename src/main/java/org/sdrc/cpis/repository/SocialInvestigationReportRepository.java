package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.SocialinvestigationReport;

public interface SocialInvestigationReportRepository {

	@Transactional
	SocialinvestigationReport save(SocialinvestigationReport socialinvestigationReport);
	
	List<SocialinvestigationReport> findByChildId(String childId);
	
	List<SocialinvestigationReport> findSocialinvestigationReportWithinTimePeriod(Date startDate,Date endDate);

	List<SocialinvestigationReport> findAll();
}
