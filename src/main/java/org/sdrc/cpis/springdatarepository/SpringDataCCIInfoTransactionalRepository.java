package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCITransactionDetails;
import org.sdrc.cpis.repository.CCIInfoTransactionalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SpringDataCCIInfoTransactionalRepository extends CCIInfoTransactionalRepository, Repository<CCITransactionDetails, Integer> {
	
	@Override
	@Query("SELECT ctd FROM CCITransactionDetails ctd")
	List<CCITransactionDetails> getAllTransactionalData();
}
