package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CaseHistoryCCI;
import org.sdrc.cpis.repository.CaseHistoryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCaseHistoryRepository extends CaseHistoryRepository,
																	Repository<CaseHistoryCCI, Integer>{
	@Override
	@Query("select caseHistory from CaseHistoryCCI caseHistory where caseHistory.childId.childId= :childId")
	CaseHistoryCCI getByChildId(@Param("childId")String childId);
	
	@Override
	@Query("SELECT order FROM CaseHistoryCCI order WHERE "
			+ "order.date BETWEEN :startDate AND :endDate")
	public List<CaseHistoryCCI> findCaseHistoryCCIWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
