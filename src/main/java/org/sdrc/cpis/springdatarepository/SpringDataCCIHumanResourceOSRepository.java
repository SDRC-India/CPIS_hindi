package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCIHumanResourceOS;
import org.sdrc.cpis.repository.CCIHumanResourceOSRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCCIHumanResourceOSRepository extends CCIHumanResourceOSRepository,Repository<CCIHumanResourceOS, Integer> {
	
	@Override
	@Query("select hros from CCIHumanResourceOS hros")
	List<CCIHumanResourceOS> findAll();


	@Override
	@Query("select hros from CCIHumanResourceOS hros where hros.nameOfOpenShelter=:osId")
	CCIHumanResourceOS findByNameofOpenShelter(@Param("osId")Integer osId);
}
