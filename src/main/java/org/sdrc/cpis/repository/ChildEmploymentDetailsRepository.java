package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.ChildEmploymentDetails;
import org.springframework.transaction.annotation.Transactional;

public interface ChildEmploymentDetailsRepository {
	
	@Transactional
	Iterable<ChildEmploymentDetails> save(Iterable<ChildEmploymentDetails> childEmploymentDetails);

	List<ChildEmploymentDetails> getEmpDetailsByChildId(String childId);
	
	List<ChildEmploymentDetails> findAll();

}
