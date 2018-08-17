package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CICLSupervisionOrder;
import org.sdrc.cpis.repository.CICLSupervisionOrderRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCICLSupervisionOrderRepository extends CICLSupervisionOrderRepository,Repository<CICLSupervisionOrder, Integer> {
	
	@Override
	@Query("select order from CICLSupervisionOrder order where order.childId.childId= :childId")
	CICLSupervisionOrder getByChildId(@Param("childId")String childId);
	
	@Override
	@Query("select order from CICLSupervisionOrder order where order.childId.childId= :childId")
	List<CICLSupervisionOrder> findAllByChildId(@Param("childId")String childId);

	@Override
	@Query("SELECT order FROM CICLSupervisionOrder order WHERE "
			+ "order.dateOfOrder BETWEEN :startDate AND :endDate")
	public List<CICLSupervisionOrder> findCICLSupervisionOrderWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
