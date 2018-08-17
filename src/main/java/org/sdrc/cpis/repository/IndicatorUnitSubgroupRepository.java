/**
 * 
 */
package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.IndicatorUnitSubgroup;

/**
 * @author Harsh Pratyush(harsh@sdrc.co.in)
 *
 */
public interface IndicatorUnitSubgroupRepository {
	
	List<IndicatorUnitSubgroup> findAll();
	
	List<IndicatorUnitSubgroup> findBySubGroup(String subgroupName);

	List<IndicatorUnitSubgroup> findByIndicatorNameOrderByIndicatorIdAsc(String indicatorName);

	IndicatorUnitSubgroup findByIndicatorId(int indicatorId);

	List<IndicatorUnitSubgroup> findBySubGroupAndSectorsSectorId(String subgroupName,
			int sectorId);
	
//	List<IndicatorUnitSubgroup> find

}
