package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCIHumanResourceSAA;

public interface CCIHumanResourceSAARepository {
	
	@Transactional
	void save(CCIHumanResourceSAA cciHumanResourceSAA);

	List<CCIHumanResourceSAA> findAll();
	
	CCIHumanResourceSAA findByNameofSAA(Integer saaId);

}
