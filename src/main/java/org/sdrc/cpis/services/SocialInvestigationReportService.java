package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.SocialInvestigationReportFamilyDetailsModel;
import org.sdrc.cpis.models.SocialinvestigationReportModel;
import org.springframework.transaction.annotation.Transactional;

public interface SocialInvestigationReportService {

	@Transactional
	void saveSocialInvestigationReport(SocialinvestigationReportModel socialinvestigationReportModel, List<SocialInvestigationReportFamilyDetailsModel> socialInvestigationReportFamilyDetailsModel);
	
	Integer getId(String selectedChildId);
	Integer getRefId(Integer selectedRefId);
	
	SocialinvestigationReportModel getSocialIvestigationreport(String childId);
	
}
