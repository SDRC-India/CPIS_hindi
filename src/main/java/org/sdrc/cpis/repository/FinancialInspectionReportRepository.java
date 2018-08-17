package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.FinancialInspectionReport;
import org.springframework.transaction.annotation.Transactional;

public interface FinancialInspectionReportRepository {

	@Transactional
	FinancialInspectionReport save(FinancialInspectionReport financialInspectionReport);

	List<FinancialInspectionReport> fetchAll();
	
}
