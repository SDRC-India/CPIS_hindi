package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.CCIHumanResourceModel;
import org.sdrc.cpis.models.CCIHumanResourceOSModel;
import org.sdrc.cpis.models.CCIHumanResourceSAAModel;
import org.sdrc.cpis.models.DCPUHRDetailsModel;
import org.sdrc.cpis.models.FinancialInspectionReportModel;
import org.sdrc.cpis.models.InfrastructureCCIModel;
import org.sdrc.cpis.models.InfrastructureOSModel;
import org.sdrc.cpis.models.InfrastructureSAAModel;
import org.sdrc.cpis.util.ValueObject;

public interface ReportSummaryService {
	
	ValueObject getArea();
	void saveCCIHumanResource(CCIHumanResourceModel cciHumanResourceModel);
	void saveCCIHumanREsourceSAA(CCIHumanResourceSAAModel cciHumanResourceSAAModel);
	void saveCCIHumanResourceOS(CCIHumanResourceOSModel cciHumanResourceOSModel);
	
	List<ValueObject> getYearDetails();
	
	void saveInfrastructureCCI(InfrastructureCCIModel infrastructureCCIModel);
	void saveInfrastructureSAA(InfrastructureSAAModel infrastructureSAAModel);
	void saveInfrastructureOS(InfrastructureOSModel infrastructureOSModel);
	String saveFinancialReport(FinancialInspectionReportModel financialInspectionReportModel) throws Exception;

	List<ValueObject> getFinancialInspectionReportDetails() throws Exception;
	
	void saveDCPUHRDetails(DCPUHRDetailsModel dcpuhrDetailsModel);
	
	DCPUHRDetailsModel getDCPUHRDetails();
}
