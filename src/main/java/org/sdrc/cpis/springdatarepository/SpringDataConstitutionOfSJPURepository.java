package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfSJPU;
import org.sdrc.cpis.repository.ConstitutionOfSJPURepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataConstitutionOfSJPURepository extends ConstitutionOfSJPURepository , Repository<ConstitutionOfSJPU, Integer>{
	
	@Override
	@Query("select sjpu from ConstitutionOfSJPU sjpu WHERE sjpu.areaId= :district")
	List<ConstitutionOfSJPU> findSJPUConstitution(@Param("district")Integer district);

}
