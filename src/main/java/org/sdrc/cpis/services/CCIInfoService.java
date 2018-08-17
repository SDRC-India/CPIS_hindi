package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.domains.CCITransactionDetails;
import org.sdrc.cpis.domains.CCITypeDetails;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.CasteModel;

/**
 * 
 * @author Pratyush Kumar Rath
 * pratyush@sdrc.co.in
 *
 */
public interface CCIInfoService {
	CCIInfoMapModel getCCIInfo();
	
	List<CCIInfoMapModel> fetchCCIMstData();
	
	List<CCITypeDetails> getCCITypes();

	List<CCITransactionDetails> updateCCITransactional();

	List<Object> getAllHRDetails();

	List<CasteModel> findByCaste();
	
	List<CasteModel> findByAge();
	
	
}
