package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.CciUserMapping;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CciUserRepository {
	@Transactional
	void save(CciUserMapping cciUserMapping);
	
	CciUserMapping findByUserId(Integer userId);
	
	CciUserMapping findByCciId(Integer cciId);
	
	@Transactional
	@Modifying
	@Query(value="delete from CciUserMapping c where c.userId =:userId")
	void deleteByUserId(@Param("userId")Integer userId);
}
