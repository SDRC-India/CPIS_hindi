package org.sdrc.cpis.springdatarepository;



import org.sdrc.cpis.domains.CICLFamilyDetails;
import org.sdrc.cpis.repository.CICLSocialBackgroundFamilyDetailsRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SpringDateCICLFamilyDetails extends CICLSocialBackgroundFamilyDetailsRepository, Repository<CICLFamilyDetails, Integer>{
	@Override
	@Modifying
	@Transactional
	@Query("delete  from CICLFamilyDetails child where child.id in(Select cfd.id from CICLFamilyDetails cfd where cfd.childId.childId=:childId)")
	 void deleteByChildIdChildId(@Param("childId")String childId) ;

}
