package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfJJB;
import org.sdrc.cpis.repository.ConstitutionOfJJBRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataConstitutionOfJJBRepository extends ConstitutionOfJJBRepository , Repository<ConstitutionOfJJB, Integer>{
	
	@Override
	@Query("select jjb from ConstitutionOfJJB jjb WHERE jjb.areaId= :district")
	List<ConstitutionOfJJB> findJJBConstitution(@Param("district")Integer district);

}
