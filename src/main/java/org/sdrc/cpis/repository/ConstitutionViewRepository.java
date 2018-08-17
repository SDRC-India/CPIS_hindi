package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.ConstitutionOfJJB;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionViewRepository {

	@Transactional
	ConstitutionOfJJB save(ConstitutionOfJJB constitutionOfJJB);
}
