package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfCWC;
import org.sdrc.cpis.repository.ConstitutionofCWCRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataConstitutionofCWCRepository extends ConstitutionofCWCRepository,Repository<ConstitutionOfCWC, Integer> {

	@Override
	@Query("select cwc from ConstitutionOfCWC cwc WHERE cwc.areaId= :district")
	List<ConstitutionOfCWC> findCWCConstitution(@Param("district")Integer district);
}
