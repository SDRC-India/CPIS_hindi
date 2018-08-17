package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.FamilyHistoryOfCrime;
import org.sdrc.cpis.repository.FamilyHistoryOfCrimeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataFamilyHistoryOfCrimeRepository extends FamilyHistoryOfCrimeRepository, 
													Repository<FamilyHistoryOfCrime, Integer>{
	@Override
	@Query("select history from FamilyHistoryOfCrime history where history.childId.childId= :childId")
	List<FamilyHistoryOfCrime> findHistoryById(@Param("childId") String childId);

}
