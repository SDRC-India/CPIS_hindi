package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.Years;

public interface YearsRepository {

	List<Years> fetchAllYears();

	List<String> fetchYearPeriod();

	@Transactional
	void save(Years years);
}
