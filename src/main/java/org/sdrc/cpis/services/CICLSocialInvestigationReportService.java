package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.CICLSocialInvestigationReportFamilyDetailsModel;
import org.sdrc.cpis.models.CICLSocialInvestigationReportModel;
import org.springframework.transaction.annotation.Transactional;

public interface CICLSocialInvestigationReportService {

	@Transactional
	void saveCICLSocialInvestigationReport(CICLSocialInvestigationReportModel ciclSocialinvestigationReportModel, List<CICLSocialInvestigationReportFamilyDetailsModel> ciclSocialInvestigationReportFamilyDetailsModel)  throws Exception;
	
	Integer getId(String selectedChildId);
//	Integer getRefId(Integer selectedRefId);
//	
	CICLSocialInvestigationReportModel getCICLSocialIvestigationreport(String childId)throws Exception;
}
