package org.sdrc.cpis.repository;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CounterCount;

public interface CounterCountRepository {
	
	@Transactional
	void save(CounterCount counterCount);

	CounterCount findTotalCount();

}
