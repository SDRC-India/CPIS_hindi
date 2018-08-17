package org.sdrc.cpis.services;

import org.sdrc.cpis.models.ConstitutionOfVCPCModel;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionofVCPCService {

	@Transactional
	void saveConstitutionofVCPC(ConstitutionOfVCPCModel constitutionOfVCPCModel);
}
