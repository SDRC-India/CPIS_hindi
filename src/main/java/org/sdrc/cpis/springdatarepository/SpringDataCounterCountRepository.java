package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.CounterCount;
import org.sdrc.cpis.repository.CounterCountRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataCounterCountRepository extends CounterCountRepository,Repository<CounterCount, Integer> {

	@Override
	@Query("select counter from CounterCount counter")
	CounterCount findTotalCount();
}
