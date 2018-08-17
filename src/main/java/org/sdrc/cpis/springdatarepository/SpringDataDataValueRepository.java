/**
 * 
 */
package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.DataValue;
import org.sdrc.cpis.repository.DataValueRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public interface SpringDataDataValueRepository extends DataValueRepository,
		JpaRepository<DataValue, Integer> {

	@Override
	@Query("SELECT  AVG(dv.value)"
			+ " ,dv.timePeriod.shortName FROM DataValue dv WHERE "
			+ "dv.indicatorUnitSubgroup.indicatorId=:iusnId "
			+ "AND dv.timePeriod.startDate BETWEEN :startDate AND :endDate"
			+ " GROUP BY dv.timePeriod.timePeriodId,dv.timePeriod.shortName , dv.timePeriod.startDate"
			+ " ORDER BY dv.timePeriod.startDate")
	public List<Object[]> findStateAvgForIUSNId(@Param("iusnId") int iusnId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Override
	@Query("Select Max(dv.value),Min(dv.value) FROM DataValue dv WHERE "
			+ "dv.indicatorUnitSubgroup.indicatorId=:iusnId AND "
			+ "dv.timePeriod.timePeriodId = :timePeriodId ")
	public List<Object[]> findMaxAndMinValue(@Param("iusnId") int iusnId,
			@Param("timePeriodId") int timePeriodId);

	// @Override
	// @Query("SELECT SUM(dv.value),dv.indicatorUnitSubgroup.indicatorName,"
	// + " dv.indicatorUnitSubgroup.highIsGood "
	// + " FROM DataValue dv WHERE "
	// + "dv.indicatorUnitSubgroup.sectors.sectorId = :sectorId"
	// + " AND dv.timePeriod.startDate = :time "
	// + " AND dv.indicatorUnitSubgroup.subGroup = 'Total' "
	// + " AND dv.indicatorUnitSubgroup.unit ='Number' "
	// + "GROUP BY dv.indicatorUnitSubgroup.indicatorName,"
	// + " dv.indicatorUnitSubgroup.highIsGood ")
	// public List<Object []> findAllTheIndicatorValueForSectorAndTime(
	// @Param("sectorId")int sectorId, @Param("time")Date time);

	@Override
	@Query("SELECT SUM(dv.value),dv.indicatorUnitSubgroup.indicatorName,"
			+ " dv.indicatorUnitSubgroup.highIsGood "
			+ " FROM DataValue dv WHERE "
			+ " dv.timePeriod.startDate = :time "
			+ " AND dv.indicatorUnitSubgroup.indicatorId IN :iusId "
			+ " AND dv.indicatorUnitSubgroup.subGroup = 'Total' "
			+ " AND dv.indicatorUnitSubgroup.unit ='Number' "
			+ "GROUP BY dv.indicatorUnitSubgroup.indicatorName,"
			+ " dv.indicatorUnitSubgroup.highIsGood ,dv.indicatorUnitSubgroup.indicatorId"
			+ " ORDER BY dv.indicatorUnitSubgroup.indicatorId")
	public List<Object[]> findAllTheIndicatorValueForSectorAndTime(
			@Param("time") Date time, @Param("iusId") List<Integer> iusId);

	@Override
	@Query("SELECT  AVG(dv.value)"
			+ " ,dv.timePeriod.shortName FROM DataValue dv WHERE "
			+ "dv.indicatorUnitSubgroup.indicatorId=:iusnId "
			+ "AND dv.timePeriod.startDate BETWEEN :startDate AND :endDate "
			+ "AND dv.areaDetails.areaId = :areaId "
			+ " GROUP BY dv.timePeriod.timePeriodId, dv.timePeriod.shortName , dv.timePeriod.startDate "
			+ "ORDER BY dv.timePeriod.startDate")
	public List<Object[]> findDistrictValueForIUSNId(
			@Param("iusnId") int iusnId, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("areaId") int areaId);

	@Override
	@Query("SELECT SUM(dv.value) ,dv.indicatorUnitSubgroup.indicatorName, dv.indicatorUnitSubgroup.indicatorNameHindi "
			+ " FROM DataValue dv WHERE "
			+ " dv.indicatorUnitSubgroup.indicatorId IN :iusNids "
			+ " GROUP BY dv.indicatorUnitSubgroup.indicatorName,dv.indicatorUnitSubgroup.indicatorNameHindi,"
			+ " dv.indicatorUnitSubgroup.indicatorId"
			+ " ORDER BY dv.indicatorUnitSubgroup.indicatorId")
	public List<Object[]> findTotalDataValueOfIus(
			@Param("iusNids") List<Integer> iusNids);
	
	@Override
	@Query("SELECT SUM(dv.value) ,dv.indicatorUnitSubgroup.indicatorName , dv.indicatorUnitSubgroup.subGroup "
			+ " FROM DataValue dv WHERE "
			+ " dv.indicatorUnitSubgroup.indicatorId IN :iusNids "
			+ " AND dv.timePeriod.timePeriodId = :timePeriodId "
			+ " AND dv.indicatorUnitSubgroup.unit ='Number' "
			+ " GROUP BY dv.indicatorUnitSubgroup.indicatorName,"
			+ " dv.indicatorUnitSubgroup.indicatorId , dv.indicatorUnitSubgroup.subGroup "
			+ " ORDER BY dv.indicatorUnitSubgroup.indicatorId")
	public List<Object[]> findTotalStateValueOfIusForAtimePeriod(
			@Param("iusNids") List<Integer> iusnIds, @Param("timePeriodId") int timePeriodId);
	
	
	@Override
	@Query("SELECT SUM(dv.value) ,dv.indicatorUnitSubgroup.indicatorName , dv.indicatorUnitSubgroup.subGroup "
			+ " FROM DataValue dv WHERE "
			+ " dv.indicatorUnitSubgroup.indicatorId IN :iusNids "
			+ " AND dv.timePeriod.timePeriodId = :timePeriodId "
			+ " AND dv.indicatorUnitSubgroup.unit ='Number' "
			+ " AND dv.areaDetails.parentArea.areaId=:userAreaId"
			+ " GROUP BY dv.indicatorUnitSubgroup.indicatorName,"
			+ " dv.indicatorUnitSubgroup.indicatorId , dv.indicatorUnitSubgroup.subGroup "
			+ " ORDER BY dv.indicatorUnitSubgroup.indicatorId")
	 List<Object[]> findTotalDivisionalValueOfIusForAtimePeriod(@Param("iusNids") List<Integer> iusIds,@Param("timePeriodId") int timeperiodId,
			 @Param("userAreaId") int userAreaId) ;
	 
	 @Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.areaId AS areaId,dv.IUSid AS IUSid "
			+ "FROM Data_Value dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad "
			+ "ON dv.areaId=ad.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId)  AND ad.Id=:userAreaId AND ius.unit ='Number' GROUP BY dv.areaId,dv.IUSid  "
			+ "order by dv.areaId,dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId, @Param("userAreaId") int userAreaId);
	 
	 @Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.areaId AS areaId,dv.IUSid AS IUSid "
			+ "FROM Data_Value dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad3 "
			+ "ON dv.areaId=ad3.Id LEFT OUTER JOIN area_details ad4 ON ad3.parent_area_id=ad4.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId)  AND ad4.Id=:userAreaId AND ius.unit ='Number' GROUP BY dv.areaId,dv.IUSid  "
			+ "order by dv.areaId,dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId, @Param("userAreaId") int userAreaId);

	 @Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.areaId AS areaId,dv.IUSid AS IUSid "
			+ "FROM Data_Value dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad3 "
			+ "ON dv.areaId=ad3.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId) AND ius.unit ='Number' GROUP BY dv.areaId,dv.IUSid  "
			+ "order by dv.areaId,dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId);

	 
	 
	 
	 
	 
	 
	 @Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.IUSid AS IUSid "
			+ "FROM Data_Value dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad "
			+ "ON dv.areaId=ad.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId)  AND ad.Id=:userAreaId AND ius.unit ='Number' GROUP BY dv.IUSid  "
			+ "order by dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscTimePeriodTimePeriodIdAscAgg1(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId, @Param("userAreaId") int userAreaId);
	 
	 @Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.IUSid AS IUSid "
			+ "FROM Data_Value dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad3 "
			+ "ON dv.areaId=ad3.Id LEFT OUTER JOIN area_details ad4 ON ad3.parent_area_id=ad4.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId)  AND ad4.Id=:userAreaId AND ius.unit ='Number' GROUP BY dv.IUSid  "
			+ "order by dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInAndAreaDetailsParentAreaAreaIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId, @Param("userAreaId") int userAreaId);

	 @Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.IUSid AS IUSid "
			+ "FROM Data_Value dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad3 "
			+ "ON dv.areaId=ad3.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId) AND ius.unit ='Number' GROUP BY dv.IUSid  "
			+ "order by dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg1(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId);
	 
	 
	 @Override
	 @Query("SELECT SUM(dv.value) ,dv.areaDetails.areaId  FROM DataValue dv WHERE dv.timePeriod.timePeriodId = :timeNid "
	 		+ " AND dv.indicatorUnitSubgroup.indicatorId IN :icpIusIds"
	 		+ " GROUP BY dv.areaDetails.areaId")
	public List<Object[]> findAllIndicatorUnitSubgroupIndicatorIdInAndTimePeriodTimePeriodIdOrderByAreaDetailsAreaNameAsc(
			@Param("icpIusIds")List<Integer> icpIusIds,@Param("timeNid") int timeNid);
}
