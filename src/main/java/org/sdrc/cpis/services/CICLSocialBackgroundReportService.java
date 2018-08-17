package org.sdrc.cpis.services;

import org.sdrc.cpis.models.CICLSocialBackgroundReportModel;

public interface CICLSocialBackgroundReportService {

 public String saveCICLSocialBackgroundReportData(CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel)throws Exception;

 public CICLSocialBackgroundReportModel getCICLSocialBackgroundReportModel(String childId) throws Exception;;
	
// public String updateCICLSocialBackgroundReportData(CICLSocialBackgroundReportModel ciclSocialBackgroundReportModel);
 
}
