package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CCIHumanResourceOS;

public interface CCIHumanResourceOSRepository {
	
	@Transactional
	void save(CCIHumanResourceOS cciHumanResourceOS);

	List<CCIHumanResourceOS> findAll();

	CCIHumanResourceOS findByNameofOpenShelter(Integer osId);

}
