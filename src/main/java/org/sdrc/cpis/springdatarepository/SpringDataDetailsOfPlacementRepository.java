package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.DetailsOfPlacement;
import org.sdrc.cpis.repository.DetailsOfPlacementRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataDetailsOfPlacementRepository extends DetailsOfPlacementRepository, 
													Repository<DetailsOfPlacement, Integer> {
	
	@Override
	@Query("select details from DetailsOfPlacement details where details.childRecord.childId= :childId")
	List<DetailsOfPlacement> findDetailsByChildId(@Param("childId") String childId);

}
