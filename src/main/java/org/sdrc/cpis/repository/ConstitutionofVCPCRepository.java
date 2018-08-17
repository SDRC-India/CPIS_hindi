package org.sdrc.cpis.repository;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.ConstitutionOfVCPC;

public interface ConstitutionofVCPCRepository {

	@Transactional
	ConstitutionOfVCPC save(ConstitutionOfVCPC constitutionOfVCPC);
}
