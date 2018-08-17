package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CICLSupervisionOrder;

public interface CICLSupervisionOrderRepository {

	@Transactional
	CICLSupervisionOrder save(CICLSupervisionOrder ciclSupervisionOrder);

	CICLSupervisionOrder getByChildId(String childId);

	List<CICLSupervisionOrder> findAllByChildId(String childId);
	
	List<CICLSupervisionOrder> findCICLSupervisionOrderWithinTimePeriod(Date startDate,Date endDate);

	List<CICLSupervisionOrder> findAll();

	List<CICLSupervisionOrder> findByChildPlacedPeriodGreaterThan(int period);
	
}
