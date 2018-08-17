package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.ConstitutionOfCWC;

public interface ConstitutionofCWCRepository {

	@Transactional
	ConstitutionOfCWC save(ConstitutionOfCWC constitutionOfCWC);

	List<ConstitutionOfCWC> findCWCConstitution(Integer district);
}
