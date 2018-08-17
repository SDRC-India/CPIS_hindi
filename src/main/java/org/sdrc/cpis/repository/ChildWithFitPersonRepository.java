package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.ChildWithFitPerson;
import org.springframework.transaction.annotation.Transactional;

public interface ChildWithFitPersonRepository {
	@Transactional
	void save(ChildWithFitPerson childWithFitPerson);
	
	List<ChildWithFitPerson> findByChildIdChildId(String childId);
}
