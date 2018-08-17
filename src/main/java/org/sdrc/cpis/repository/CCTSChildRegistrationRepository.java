package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.ChildRegistrationDetails;

public interface CCTSChildRegistrationRepository {

	@Transactional
	void save(ChildRegistrationDetails childRegistrationDetails);
	
	ChildRegistrationDetails findByChildIdChildId(String childId);
	
	List<ChildRegistrationDetails> findChildRegisteredWithinATimepriod(Date startDate,Date endDate);

	List<ChildRegistrationDetails> findAll();
	

}
