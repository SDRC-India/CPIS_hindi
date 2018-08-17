package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.SponsorshipOrder;
import org.sdrc.cpis.repository.SponsorshipRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataSponsorshipRepository extends SponsorshipRepository, Repository<SponsorshipOrder, Integer> {

	@Override
	@Query("SELECT so from SponsorshipOrder so WHERE so.childId.childId= :childId")
	SponsorshipOrder getSponsorshipDataByChild(@Param("childId")String childId);
	
	@Override
	@Query("SELECT order FROM SponsorshipOrder order WHERE "
			+ " order.createdDate BETWEEN :startDate AND :endDate ")
	public List<SponsorshipOrder> findSponsorshipOrderBetweenTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
