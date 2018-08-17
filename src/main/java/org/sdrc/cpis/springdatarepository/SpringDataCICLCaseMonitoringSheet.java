package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CICLCaseMoniteringSheet;
import org.sdrc.cpis.repository.CICLCaseMonitoringSheetRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCICLCaseMonitoringSheet extends
		CICLCaseMonitoringSheetRepository,
		Repository<CICLCaseMoniteringSheet, Integer> {

	@Override
	@Query("select caseHistory from CICLCaseMoniteringSheet caseHistory where caseHistory.childId.childId= :childId")
	List<CICLCaseMoniteringSheet> getByChildId(@Param("childId") String childId);

	@Override
	@Query("SELECT caseHistory FROM CICLCaseMoniteringSheet caseHistory WHERE "
			+ " caseHistory.date BETWEEN :startDate AND :endDate ")
	public List<CICLCaseMoniteringSheet> findCICLCaseMoniteringSheetWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
