package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.CCIHumanResource;
import org.springframework.transaction.annotation.Transactional;

public interface CCIHumanResourceReposotory {
	
	@Transactional
	void save(CCIHumanResource cciHumanResource);

	List<CCIHumanResource> findAll();
	
	CCIHumanResource findByNameofCII(Integer ciiId);

}
