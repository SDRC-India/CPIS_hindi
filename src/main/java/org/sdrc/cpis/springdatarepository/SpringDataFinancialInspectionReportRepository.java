package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.FinancialInspectionReport;
import org.sdrc.cpis.repository.FinancialInspectionReportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataFinancialInspectionReportRepository extends	FinancialInspectionReportRepository, Repository<FinancialInspectionReport, Integer> {

	@Override
	@Query("select fir from FinancialInspectionReport fir")
	public List<FinancialInspectionReport> fetchAll();
}
