package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.FamilyHistoryOfCrime;
import org.springframework.transaction.annotation.Transactional;

public interface FamilyHistoryOfCrimeRepository {

	@Transactional
	Iterable<FamilyHistoryOfCrime> save(Iterable<FamilyHistoryOfCrime> familyHistoryOfCrimes);

	List<FamilyHistoryOfCrime> findHistoryById(String childId);
	
}
