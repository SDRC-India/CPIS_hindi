package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfDCPC;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionOfDCPCRepository {

	@Transactional
	ConstitutionOfDCPC save(ConstitutionOfDCPC constitutionOfDCPC);
	
	List<ConstitutionOfDCPC> findDCPCConstitution(Integer district);
}
