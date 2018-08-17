package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.CICLFamilyDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface CICLSocialBackgroundFamilyDetailsRepository {

	@Transactional
	CICLFamilyDetails save(CICLFamilyDetails ciclFamilyDetails);
	
	List<CICLFamilyDetails> findByChildIdChildId(String childId);
	
	@Modifying
	@Transactional
	void deleteByChildIdChildId(String childId);
	
}
