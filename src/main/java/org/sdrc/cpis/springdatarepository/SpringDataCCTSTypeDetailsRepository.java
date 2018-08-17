package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataCCTSTypeDetailsRepository extends Repository<CCTSTypeDetails, Integer>, CCTSTypeDetailsRepository {
	
	@Override
	@Query("select typeDetails from CCTSTypeDetails typeDetails")
	List<CCTSTypeDetails> getAllTypeDetails();
}
