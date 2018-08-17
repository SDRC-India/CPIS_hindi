package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCIHumanResourceSAA;
import org.sdrc.cpis.repository.CCIHumanResourceSAARepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCCIHumanResourceSAARepository extends CCIHumanResourceSAARepository,Repository<CCIHumanResourceSAA, Integer> {
	
	@Override
	@Query("select hrSaa from CCIHumanResourceSAA hrSaa")
	List<CCIHumanResourceSAA> findAll();
	
	@Override
	@Query("select hrSaa from CCIHumanResourceSAA hrSaa where hrSaa.nameOfSAA=:saaId")
	CCIHumanResourceSAA findByNameofSAA(@Param("saaId")Integer saaId);

}
