package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.HealthStatusOfChild;
import org.springframework.transaction.annotation.Transactional;

public interface HealthStatusOfChildRepository {

	@Transactional
	Iterable<HealthStatusOfChild> save(Iterable<HealthStatusOfChild> healthStatusOfChilds);

	List<HealthStatusOfChild> findHealthStatusById(String childId);

}
