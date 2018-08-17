/**
 * 
 */
package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.TimePeriod;
import org.sdrc.cpis.repository.TimePeriodRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public interface SpringDataTimePeriodRepository extends JpaRepository<TimePeriod, Integer>,
		TimePeriodRepository {
	
	@Override
	@Query("Select MAX(td.timePeriodId) From TimePeriod td")
	public int findLastTimePeriodId();

}
