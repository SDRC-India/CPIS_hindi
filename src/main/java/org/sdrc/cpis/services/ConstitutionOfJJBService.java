package org.sdrc.cpis.services;

import org.sdrc.cpis.models.ConstitutionOfJJBModel;

public interface ConstitutionOfJJBService {

	public String saveConstitutionOfJJBData(ConstitutionOfJJBModel constitutionOfJJBModel);
	
	public ConstitutionOfJJBModel getConstitutionOfJJBModel(String childId);
}
