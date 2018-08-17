package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CICLsocialInvestigationReportFamilyDetails;

public interface CICLSocialInvestigationReportFamilyDetailsRepository {

	@Transactional
	Iterable<CICLsocialInvestigationReportFamilyDetails> save(
			Iterable<CICLsocialInvestigationReportFamilyDetails> cICLsocialInvestigationReportFamilyDetails);
	
	List<CICLsocialInvestigationReportFamilyDetails> findByRefId(int refId);
}
