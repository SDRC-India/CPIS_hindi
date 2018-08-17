package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.LegallyFreeForAdoption;
import org.springframework.transaction.annotation.Transactional;

public interface LegallyFreeForAdoptionRepository {

	@Transactional
	LegallyFreeForAdoption save(LegallyFreeForAdoption legallyFreeForAdoption);
	
	LegallyFreeForAdoption findByChildIdChildId(String childId);
	
	List<LegallyFreeForAdoption> findAll();

	List<LegallyFreeForAdoption> findByChildIdProgramType(int programmType);

	List<LegallyFreeForAdoption> findByChildIdProgramType1(Date startDate, Date endDate);
}
