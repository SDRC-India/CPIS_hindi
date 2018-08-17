package org.sdrc.cpis.services;

import org.sdrc.cpis.models.ConstitutionOfDCPCModel;

public interface ConstitutionOfDCPCService {

	public String saveConstitutionOfDCPCData(ConstitutionOfDCPCModel constitutionOfDCPCModel);
	
	public ConstitutionOfDCPCModel getConstitutionOfDCPCModel(String childId);
}
