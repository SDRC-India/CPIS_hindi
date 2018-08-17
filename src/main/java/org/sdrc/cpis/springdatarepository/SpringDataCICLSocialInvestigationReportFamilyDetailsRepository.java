package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CICLsocialInvestigationReportFamilyDetails;
import org.sdrc.cpis.repository.CICLSocialInvestigationReportFamilyDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCICLSocialInvestigationReportFamilyDetailsRepository
		extends
		Repository<CICLsocialInvestigationReportFamilyDetails, Integer>,
		CICLSocialInvestigationReportFamilyDetailsRepository {

	@Override
	@Query("select s from CICLsocialInvestigationReportFamilyDetails s where s.cICLSocialInvestigationReport.id = :refIdParam")
	public List<CICLsocialInvestigationReportFamilyDetails> findByRefId(@Param("refIdParam") int refId);
}
