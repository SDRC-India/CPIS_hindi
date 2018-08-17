package org.sdrc.cpis.services;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.CounterCount;
import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.TXN_User_LogIn_Meta_Model;
import org.springframework.dao.DataAccessException;


public interface LoginService {
	Map<String, Object> fetchLoginData();

	String forgotWebPassword(String email, String url) throws UnsupportedEncodingException;

	String getActivatedEmailId(Integer mId, long currentTime);

	String updateWebForgotPassword(String email, String password);

	long saveTxnLoginMeta(TXN_User_LogIn_Meta_Model txn_User_LogIn_Meta_Model) throws DataAccessException;

	void updateLoginMeta(long txnId, Timestamp datetimeLoggedOut) throws DataAccessException;
	
	TXN_User_LogIn_Meta_Model getExistingLogInUser(Integer userId);
	
	TXN_User_LogIn_Meta_Model getExistingUserByTxnId(Long txnId);
	
	CounterCount getCounter();

	List<AreaDetailsModel> getAllAreaForLoginUser();
}
