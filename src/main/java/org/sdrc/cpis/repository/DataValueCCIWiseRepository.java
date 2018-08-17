package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.DataValueCciWise;
import org.springframework.transaction.annotation.Transactional;

public interface DataValueCCIWiseRepository {

	@Transactional
	public <S extends DataValueCciWise> List<S> save(Iterable<S> entities);
	
	public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInOrderByCciDetailsCciNameAscAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
			List<Integer> iusIds, List<Integer> timeperiodId);

	public List<DataValueCciWise> findByIndicatorUnitSubgroupIndicatorIdAndTimePeriodTimePeriodIdAndCciDetailsAreaDetailsOrderByCciDetailsCciNameAsc(
			int iusid, int timeperiodid, int areaId);

	public List<Object[]> findCCIValueForIUSNId(int iusid, Date startDate,
			Date endDate, int areaId);

}
