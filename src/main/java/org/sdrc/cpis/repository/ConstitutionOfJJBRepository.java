package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfJJB;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionOfJJBRepository {

	@Transactional
	ConstitutionOfJJB save(ConstitutionOfJJB constitutionOfJJB);
	
	List<ConstitutionOfJJB> findJJBConstitution(Integer district);
}
