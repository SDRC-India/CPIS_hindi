package org.sdrc.cpis.services;

import org.sdrc.cpis.models.CICLPeriodicReportModel;

public interface CICLPeriodicReportService {

	public String saveCICLPeriodicReportData(CICLPeriodicReportModel ciclPeriodicReportModel);
	
	public CICLPeriodicReportModel getCiclPeriodicReportModel(String childId);
}
