package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.ChildEmploymentDetails;
import org.sdrc.cpis.repository.ChildEmploymentDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataChildEmploymentDetailsRepository extends ChildEmploymentDetailsRepository, 
													Repository<ChildEmploymentDetails, Integer> {
	
	@Override
	@Query("select details from ChildEmploymentDetails details where details.childId.childId= :childId")
	List<ChildEmploymentDetails> getEmpDetailsByChildId(@Param("childId") String childId);

}
