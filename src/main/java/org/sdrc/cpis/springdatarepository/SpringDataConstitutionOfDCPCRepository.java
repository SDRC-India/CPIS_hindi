package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfDCPC;
import org.sdrc.cpis.repository.ConstitutionOfDCPCRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataConstitutionOfDCPCRepository extends ConstitutionOfDCPCRepository , Repository<ConstitutionOfDCPC, Integer>{
	
	@Override
	@Query("select dcpc from ConstitutionOfDCPC dcpc WHERE dcpc.areaId= :district")
	List<ConstitutionOfDCPC> findDCPCConstitution(@Param("district")Integer district);

}
