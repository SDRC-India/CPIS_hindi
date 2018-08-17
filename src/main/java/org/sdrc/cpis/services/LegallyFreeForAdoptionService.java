package org.sdrc.cpis.services;

import org.sdrc.cpis.models.LegallyFreeForAdoptionModel;

public interface LegallyFreeForAdoptionService {

	public String saveLegallyFreeForAdoptionData(LegallyFreeForAdoptionModel legallyFreeForAdoptionModel) throws Exception;
	
	LegallyFreeForAdoptionModel getAllLegallyFreeForAdoptionData(String childId) throws Exception;
}
