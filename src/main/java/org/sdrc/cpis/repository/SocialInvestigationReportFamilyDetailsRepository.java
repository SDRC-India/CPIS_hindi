package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.SocialInvestigationReportFamilyDetails;

public interface SocialInvestigationReportFamilyDetailsRepository {

	@Transactional
	Iterable<SocialInvestigationReportFamilyDetails> save(Iterable<SocialInvestigationReportFamilyDetails> socialInvestigationReportFamilyDetails);
	
	List<SocialInvestigationReportFamilyDetails> findByRefId(int refId);
}
