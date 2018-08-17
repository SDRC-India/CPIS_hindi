package org.sdrc.cpis.repository;

import java.sql.Timestamp;

import org.sdrc.cpis.domains.TXN_User_LogIn_Meta;
import org.springframework.transaction.annotation.Transactional;

public interface TxnUserLoginMetaRepository {
	
	@Transactional
	TXN_User_LogIn_Meta save(TXN_User_LogIn_Meta txnUser);

	void updateStatus(Timestamp datetimeLoggedOut, long txnId);
	
	TXN_User_LogIn_Meta getExistingLogInUser(Integer userId);


	TXN_User_LogIn_Meta getExistingUserByTxnId(Long txnId);

	void updateStatusForAll(Timestamp Datetime_Logged_Out);
	
	void deleteLoginMetaData(Integer userId);


}
