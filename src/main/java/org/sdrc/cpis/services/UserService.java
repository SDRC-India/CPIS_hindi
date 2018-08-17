package org.sdrc.cpis.services;

import org.sdrc.cpis.models.UserDetailModel;
import org.springframework.dao.DataAccessException;

public interface UserService {
	
	UserDetailModel findActiveUserById(String userName) throws DataAccessException;
	
	long saveLoginMeta(UserDetailModel userDetailModel) throws DataAccessException;
	
	void saveNewUser(UserDetailModel userDetailModel,String url, String password);
	
	String getActivatedEmailId(Integer mId, long currentTime);

	String updateForgotPassword(String eid, String password);

	String updateActivatedAccount(Integer mId);

	String changeUserPassword(String email, String password);

	String deleteUser(Integer userId);

	String changeUserPasswordByAdmin(String email, String password, String newEmail);
}
