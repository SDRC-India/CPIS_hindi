package org.sdrc.cpis.springdatarepository;


import org.sdrc.cpis.domains.EmailActivation;
import org.sdrc.cpis.repository.EmailActivationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SpringDataEmailActivationRepository extends
		EmailActivationRepository, JpaRepository<EmailActivation, Integer> {
	
	@Override
	@Transactional
	@Modifying
	@Query(value="update Email_Activation set is_active='true',is_live='false' where m_id = :M_Id",nativeQuery=true)
	void updateActivationInfo(@Param("M_Id") Integer M_Id);

	@Override
	@Query("Select em from EmailActivation em where em.mId = :mId")
	EmailActivation getIsActiveEmailId(@Param("mId") Integer mId);

	@Query("Select em from EmailActivation em where em.emailId = :emailId and em.isLive=true")
	EmailActivation findByEmailId(@Param("emailId")String emailId);
	
	
	@Override
	@Transactional
	@Modifying
	@Query(value="update Email_Activation AS ea set is_active='true',is_live='false' where user_id = :User_Id",nativeQuery=true)
	public void deactivateLink(@Param("User_Id") Integer User_Id);
}
