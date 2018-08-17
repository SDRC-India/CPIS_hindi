package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.SocialInvestigationReportFamilyDetails;
import org.sdrc.cpis.repository.SocialInvestigationReportFamilyDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataSocialInvestigationReportFamilyDetailsRepository extends 
		Repository<SocialInvestigationReportFamilyDetails, Integer>, SocialInvestigationReportFamilyDetailsRepository{
	
	@Override
	@Query("select s from SocialInvestigationReportFamilyDetails s where s.socialinvestigationReport.id = :refIdParam")
	public List<SocialInvestigationReportFamilyDetails> findByRefId(@Param("refIdParam") int refId);

}
