package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.ChildInFosterCare;
import org.springframework.transaction.annotation.Transactional;

public interface ChildInFosterCareRepository {

	@Transactional
	void save(ChildInFosterCare childInFosterCare);

//	ChildInFosterCare getByChildId(String childId);
	
	ChildInFosterCare findByChildRecordChildId(String childId);

}
