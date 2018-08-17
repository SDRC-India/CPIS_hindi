package org.sdrc.cpis.services;

import org.sdrc.cpis.models.RestorationModel;
import org.springframework.transaction.annotation.Transactional;

public interface RestorationService {

	@Transactional
	void saveRestorationData(RestorationModel restorationModel);
	
	RestorationModel getAllRestorationData(String childId);
}
