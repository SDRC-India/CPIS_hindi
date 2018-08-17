package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.ChildRegistrationDetails;
import org.sdrc.cpis.repository.CCTSChildRegistrationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCCTSChildRegistrationRepository extends CCTSChildRegistrationRepository,Repository<ChildRegistrationDetails, Integer>{

	@Override
	@Query("SELECT  child FROM ChildRegistrationDetails child WHERE "
			+ "child.dateOfProduction BETWEEN :startDate AND :endDate")
	public List<ChildRegistrationDetails> findChildRegisteredWithinATimepriod(
			@Param("startDate")Date startDate,@Param("endDate") Date endDate);
}
