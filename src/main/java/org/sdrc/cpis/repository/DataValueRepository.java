package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.DataValue;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public interface DataValueRepository 
{

	public List<DataValue> findByIndicatorUnitSubgroupIndicatorIdAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc(int iusnId,int timePeriodId);
	
	public List<Object[]> findStateAvgForIUSNId(int iusnId,Date startDate,Date endDate);
	
	public List<Object []> findDistrictValueForIUSNId(int iusnId,Date startDate,Date endDate,int areaId);
	
	public List<Object[]> findMaxAndMinValue(int iusnId,int timePeriodId);
	
//	public List<Object []> findAllTheIndicatorValueForSectorAndTime(int sectorId,Date time);
	
	public List<Object []> findAllTheIndicatorValueForSectorAndTime(Date time, List<Integer> iusId);
	
	
	@Transactional
	public <S extends DataValue> List<S> save(Iterable<S> entities);
		
	List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
			List<Integer> iusIds, List<Integer> timeperiodId);

	public List<Object[]> findTotalDataValueOfIus(List<Integer> iusNids);
	
	public List<Object[]> findTotalStateValueOfIusForAtimePeriod(List<Integer> iusnIds,int timePeriodId);

	public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg(
			List<Integer> iusIds, List<Integer> timeperiodId, int userAreaId);

	public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
			List<Integer> iusIds, List<Integer> timeperiodId, int userParentAreaId);

	public List<Object[]> findTotalDivisionalValueOfIusForAtimePeriod(List<Integer> iusIds, int timeperiodId,
			int userAreaId);
	public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg1(
			List<Integer> iusIds, List<Integer> timeperiodId, int userAreaId);

	public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
			List<Integer> iusIds, List<Integer> timeperiodId, int userParentAreaId);
	List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
			List<Integer> iusIds, List<Integer> timeperiodId);

	public List<Object[]> findAllIndicatorUnitSubgroupIndicatorIdInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc(
			List<Integer> icpIusIds, int timeNid);

	public List<DataValue> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc(
			List<Integer> list, int timePeriodId);
	
}
