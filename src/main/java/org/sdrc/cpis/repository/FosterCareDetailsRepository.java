package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.FosterCareDetails;

public interface FosterCareDetailsRepository {

	@Transactional
	void save(FosterCareDetails fosterCareDetails);
	
	List<FosterCareDetails> findByChildIdChildId(String childId);
	
	List<FosterCareDetails> findFosterCareDetailsBetweenTimePeriod(Date startDate,Date endDate);
}
