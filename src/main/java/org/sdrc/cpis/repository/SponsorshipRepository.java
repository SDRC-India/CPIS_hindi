package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.SponsorshipOrder;
import org.springframework.transaction.annotation.Transactional;

public interface SponsorshipRepository {

	@Transactional
	void save(SponsorshipOrder sponsorshipOrder);
	
	SponsorshipOrder getSponsorshipDataByChild(String childId);
	
	List<SponsorshipOrder> findSponsorshipOrderBetweenTimePeriod(Date startDate,Date endDate);
}
