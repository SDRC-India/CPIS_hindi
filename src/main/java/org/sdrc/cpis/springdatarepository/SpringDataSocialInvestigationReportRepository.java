package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.SocialinvestigationReport;
import org.sdrc.cpis.repository.SocialInvestigationReportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataSocialInvestigationReportRepository extends
		Repository<SocialinvestigationReport, Integer>,
		SocialInvestigationReportRepository {

	@Override
	@Query("select s from SocialinvestigationReport s where s.childId.childId = :childIdParam")
	public List<SocialinvestigationReport> findByChildId(
			@Param("childIdParam") String childId);
	
	@Override
	@Query("SELECT report FROM SocialinvestigationReport report WHERE "
			+ " report.createdDate BETWEEN :startDate AND :endDate")
	public List<SocialinvestigationReport> findSocialinvestigationReportWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
