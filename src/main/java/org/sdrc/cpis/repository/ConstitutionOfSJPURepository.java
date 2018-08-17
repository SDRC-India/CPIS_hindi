package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfSJPU;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionOfSJPURepository {

	@Transactional
	ConstitutionOfSJPU save(ConstitutionOfSJPU constitutionOfSJPU);
	
	List<ConstitutionOfSJPU> findSJPUConstitution(Integer district);
}
