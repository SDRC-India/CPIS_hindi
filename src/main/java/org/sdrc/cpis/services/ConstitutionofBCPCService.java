package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.ConstitutionOfBCPCModel;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.transaction.annotation.Transactional;

public interface ConstitutionofBCPCService {

	@Transactional
	void saveConstitutionofBCPC(ConstitutionOfBCPCModel constitutionOfBCPCModel);
	
	public List<ValueObject> getBlockList(); 
}
