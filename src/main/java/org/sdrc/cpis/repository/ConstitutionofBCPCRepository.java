package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.ConstitutionOfBCPC;

public interface ConstitutionofBCPCRepository {

	@Transactional
	ConstitutionOfBCPC save(ConstitutionOfBCPC constitutionOfBCPC);

	List<ConstitutionOfBCPC> findByBlockId(Integer blockId);
}
