package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.LegallyFreeForAdoption;
import org.sdrc.cpis.repository.LegallyFreeForAdoptionRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataLegallyFreeForAdoptionRepository extends LegallyFreeForAdoptionRepository , Repository<LegallyFreeForAdoption, Integer>{
	@Override
	@Query("SELECT foster FROM LegallyFreeForAdoption foster WHERE "
			+ "foster.legallyFreeDate BETWEEN :startDate AND :endDate ")
	public List<LegallyFreeForAdoption> findByChildIdProgramType1(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
