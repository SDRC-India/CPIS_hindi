package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.HealthStatusOfChild;
import org.sdrc.cpis.repository.HealthStatusOfChildRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataHealthStatusOfChildRepository extends HealthStatusOfChildRepository,
															Repository<HealthStatusOfChild, Integer>{
	
	@Override
	@Query("select hStatus from HealthStatusOfChild hStatus where hStatus.childId.childId= :childId")
	List<HealthStatusOfChild> findHealthStatusById(@Param("childId")String childId);

}
