package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CICLSocialBackgroundReport;

public interface CICLSocialBackgroundReportRepository {

@Transactional
CICLSocialBackgroundReport save(CICLSocialBackgroundReport ciclSocialBackgroundReportl);

CICLSocialBackgroundReport findByChildIdChildId(String childId);

CICLSocialBackgroundReport findById(Integer id);

List<CICLSocialBackgroundReport> findWithinATimeperiod(Date startDate,Date endDate);
//List<CICLSocialBackgroundReport> getAllTypeDetails();

List<CICLSocialBackgroundReport> findAll();
	
}
