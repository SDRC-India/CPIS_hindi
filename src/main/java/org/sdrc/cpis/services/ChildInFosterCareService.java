package org.sdrc.cpis.services;

import org.sdrc.cpis.models.ChildInFosterCareModel;
import org.springframework.transaction.annotation.Transactional;

public interface ChildInFosterCareService {

	@Transactional
	void saveChildInFosterCare(ChildInFosterCareModel childInFosterCareModel) throws Exception;

	ChildInFosterCareModel getChildInFosterCare(String childId) throws Exception;

}
