package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.repository.CciUserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCCIUserRepository extends CciUserRepository, Repository<CciUserMapping, Integer> {
////	@Query(value="delete *. from cci_user_mapping where userId=:userId", nativeQuery=true)
//	public void deleteByUserId(@Param("userId")Integer userId);
	
}
