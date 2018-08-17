package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCITransactionDetails;

public interface CCIInfoTransactionalRepository {
	@Transactional
	Iterable<CCITransactionDetails> save(Iterable<CCITransactionDetails> cciTransactionDetails);
	
	List<CCITransactionDetails> getAllTransactionalData();
}
