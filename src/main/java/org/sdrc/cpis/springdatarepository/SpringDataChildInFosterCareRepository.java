package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.ChildInFosterCare;
import org.sdrc.cpis.repository.ChildInFosterCareRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataChildInFosterCareRepository extends ChildInFosterCareRepository, 
														Repository<ChildInFosterCare, Integer>{
//	@Override
//	@Query("select fsData in ChildInFosterCare fsData where fsData.childRecord.childId =:childId")
//	ChildInFosterCare getByChildId(@Param("childId") String childId);

}
