package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.ChildWithFitPerson;
import org.sdrc.cpis.repository.ChildWithFitPersonRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataChildWithFitPersonRepository 
				 extends Repository<ChildWithFitPerson, Integer>, ChildWithFitPersonRepository {

	
}
