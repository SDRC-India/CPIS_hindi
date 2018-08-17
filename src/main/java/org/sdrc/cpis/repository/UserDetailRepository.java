package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.UserDetails;
import org.springframework.transaction.annotation.Transactional;

public interface UserDetailRepository {

	UserDetails findActiveUserById(String userName);

	List<UserDetails> fetchAllUsers();
	
	List<UserDetails> fetchUserByDesignation(Integer designationId);

	UserDetails getUserById(Integer userId);
	
	List<UserDetails> getAllUserDetails();
	
	@Transactional
	UserDetails save(UserDetails userDetails);

	UserDetails findByEmail(String email);

	void resetUserPasswordByEmail(String Email, String Password);

	void deleteUser(Integer userId);
	
	List<UserDetails> findByAreaAreaId(Integer areaId);
	
	UserDetails findByUniqueEmail(String email);

	void resetUserPasswordByAdmin(String Email, String Password, String newEmail);
}
