package org.sdrc.cpis.services;

import org.sdrc.cpis.models.ConstitutionOfCWCModel;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionofCWCService {

	@Transactional
	void saveConstitutionofCWC(ConstitutionOfCWCModel constitutionOfCWCModel);
}
