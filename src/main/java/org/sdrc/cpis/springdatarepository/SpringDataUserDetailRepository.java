package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SpringDataUserDetailRepository extends UserDetailRepository, Repository<UserDetails, Integer> {
	
	@Override
	@Query("Select user from UserDetails user where user.userName= :userName")
	UserDetails findActiveUserById(@Param("userName")String userName);
	
	@Override
	@Query("Select user from UserDetails user where user.live=true")
	List<UserDetails> fetchAllUsers();

	@Override
	@Query("select user from UserDetails user where user.designation.designationId= :designationId")
	List<UserDetails> fetchUserByDesignation(@Param("designationId")Integer designationId);
	
	@Override
	@Query("select user from UserDetails user where user.userId= :userId")
	UserDetails getUserById(@Param("userId")Integer userId);
	
	
	@Override
	@Query("select userDetail from UserDetails userDetail where userDetail.live=true")
    List<UserDetails> getAllUserDetails();
	
	@Override
	@Transactional
	@Query("Select ud from UserDetails ud where ud.email = :email and ud.live = true")
	UserDetails findByEmail(@Param("email") String email);
	
	@Override
	@Transactional
	@Modifying
	@Query(value="update user_details set password = :Password where email = :Email",nativeQuery=true)
	void resetUserPasswordByEmail(@Param("Email") String Email, @Param("Password") String Password);
	
	@Override
	@Transactional
	@Modifying
	@Query(value="update user_details set password = :Password, email=:newEmail where email = :Email",nativeQuery=true)
	void resetUserPasswordByAdmin(@Param("Email") String Email, @Param("Password") String Password, @Param("newEmail") String newEmail);
	
	@Override
	@Transactional
	@Modifying
//	@Query(value="update user_details set live = false where user_id = :userId",nativeQuery=true)
	@Query(value="delete from UserDetails where user_id = :userId")
	void deleteUser(@Param("userId")Integer userId);
	
	@Override
	@Transactional
	@Query("Select ud from UserDetails ud where ud.email = :email")
	UserDetails findByUniqueEmail(@Param("email") String email);
}
