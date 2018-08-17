package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.DCPUHRDetails;
import org.sdrc.cpis.repository.DCPUHRDetailsRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataDCPUHRDetailsRepository extends Repository<DCPUHRDetails, Integer>, 
		DCPUHRDetailsRepository {

}
