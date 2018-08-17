package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CICLSocialInvestigationReport;
import org.sdrc.cpis.repository.CICLSocialInvestigationReportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCICLSocialInvestigationReportRepository extends
		Repository<CICLSocialInvestigationReport, Integer>,
		CICLSocialInvestigationReportRepository {

	@Override
	@Query("select s from CICLSocialInvestigationReport s where s.childId.childId = :childIdParam")
	public List<CICLSocialInvestigationReport> findByChildId(
			@Param("childIdParam") String childId);

	@Override
	@Query("SELECT report FROM CICLSocialInvestigationReport report WHERE "
			+ "report.createdDate BETWEEN :startDate AND :endDate ")
	public List<CICLSocialInvestigationReport> findCICLSocialInvestigationReportWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
