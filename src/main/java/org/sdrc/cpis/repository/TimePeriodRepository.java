package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.TimePeriod;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author Harsh Pratyush
 *
 */
public interface TimePeriodRepository 
{

	List<TimePeriod> findAll();
	
	TimePeriod findByTimePeriodId(int timePeriodId);
	
	@Transactional
	TimePeriod save(TimePeriod timeperiod);
	
	TimePeriod findByStartDateAndEndDate(Date startDate,Date endDate);
	
	int findLastTimePeriodId();

	List<TimePeriod> findByOrderByStartDateAsc();

	List<TimePeriod> findTop12ByOrderByTimePeriodIdDesc();
}
