package org.sdrc.cpis.services;

import org.sdrc.cpis.models.ConstitutionOfSJPUModel;

public interface ConstitutionOfSJPUService {

	public String saveConstitutionOfSJPUData(ConstitutionOfSJPUModel constitutionOfSJPUModel);
	
	public ConstitutionOfSJPUModel getConstitutionOfSJPUModel(String childId);
}
