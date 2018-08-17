package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.DataValueCciWise;
import org.sdrc.cpis.repository.DataValueCCIWiseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Abikananda(abikananda@sdrc.co.in)
 *
 */

public interface SpringDataDataValueCCIWiseRepository extends DataValueCCIWiseRepository,
JpaRepository<DataValueCciWise, Integer> {
	@Override
	 @Query(value="SELECT SUM(dv.value) AS value, STRING_AGG(dv.childIds,',') AS childIds,dv.areaId AS areaId,dv.IUSid AS IUSid, dv.cciname as cciname "
			+ "FROM Data_Value_Cci_Wise dv LEFT OUTER JOIN Indicato_Unit_Subgroup ius "
			+ "ON dv.IUSid=ius.indicatorId LEFT OUTER JOIN TimePeriod tp "
			+ "ON dv.timePeriodId=tp.id LEFT OUTER JOIN area_details ad "
			+ "ON dv.areaId=ad.Id WHERE ius.indicatorId IN (:iusIds) "
			+ "AND tp.id IN (:timeperiodId) AND ius.unit ='Number' GROUP BY dv.areaId,dv.IUSid,dv.cciname "
			+ "order by dv.cciname,dv.areaId,dv.IUSid asc",nativeQuery=true)
	 public List<Object[]> findByIndicatorUnitSubgroupIndicatorIdIsInAndTimePeriodTimePeriodIdIsInOrderByCciDetailsCciNameAscAreaDetailsAreaNameAscIndicatorUnitSubgroupIndicatorIdAscAgg(
				@Param("iusIds") List<Integer> iusIds,@Param("timeperiodId") List<Integer> timeperiodId);


	 
	 	@Override
	 	@Query("SELECT SUM(dv.value)"
				+ " ,dv.timePeriod.shortName FROM DataValueCciWise  dv WHERE "
				+ "dv.indicatorUnitSubgroup.indicatorId=:iusnId "
				+ "AND dv.timePeriod.startDate BETWEEN :startDate AND :endDate "
				+ "AND dv.cciDetails.cciId = :areaId "
				+ " GROUP BY dv.timePeriod.timePeriodId, dv.timePeriod.shortName , dv.timePeriod.startDate "
				+ "ORDER BY dv.timePeriod.startDate")
		public List<Object[]> findCCIValueForIUSNId(
				@Param("iusnId") int iusnId, @Param("startDate") Date startDate,
				@Param("endDate") Date endDate, @Param("areaId") int areaId);
}
