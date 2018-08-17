package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CICLSocialBackgroundReport;
import org.sdrc.cpis.repository.CICLSocialBackgroundReportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCICLSocialBackgroundReportRepository extends CICLSocialBackgroundReportRepository, Repository<CICLSocialBackgroundReport, Integer>{

	@Override
	@Query("select socialBackground from CICLSocialBackgroundReport socialBackground where id=:id")
	 CICLSocialBackgroundReport findById(@Param("id")Integer id);
	
	@Override
	@Query("SELECT socialBackground FROM CICLSocialBackgroundReport socialBackground"
			+ " WHERE socialBackground.entryDate BETWEEN :startDate AND :endDate")
	public List<CICLSocialBackgroundReport> findWithinATimeperiod(
			@Param("startDate")Date startDate,@Param("endDate") Date endDate);
	
}
