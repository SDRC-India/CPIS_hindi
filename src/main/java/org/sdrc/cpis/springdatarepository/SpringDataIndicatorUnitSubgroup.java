package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.IndicatorUnitSubgroup;
import org.sdrc.cpis.repository.IndicatorUnitSubgroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author Harsh Pratyush(harsh@sdrc.co.in)
 *
 */
public interface SpringDataIndicatorUnitSubgroup extends
		IndicatorUnitSubgroupRepository,
		JpaRepository<IndicatorUnitSubgroup, Integer> {

}
