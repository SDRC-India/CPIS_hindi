package org.sdrc.cpis.springdatarepository;

import java.sql.Timestamp;

import org.sdrc.cpis.domains.TXN_User_LogIn_Meta;
import org.sdrc.cpis.repository.TxnUserLoginMetaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SpringDataTxnUserLoginMetaRepository extends
		TxnUserLoginMetaRepository, JpaRepository<TXN_User_LogIn_Meta, Long> {
	
	@Override
	@Modifying 
	@Query(value="update txn_user_login_Meta set datetime_logged_out = :Datetime_Logged_Out , is_live='false' where txn_id = :TXN_ID ",nativeQuery=true)
	void updateStatus(@Param("Datetime_Logged_Out")Timestamp Datetime_Logged_Out,@Param("TXN_ID")long TXN_ID);

	@Override
	@Query("select user from TXN_User_LogIn_Meta user WHERE user.userDetails.userId= :userId AND user.isLive=TRUE")
	TXN_User_LogIn_Meta getExistingLogInUser(@Param("userId")Integer userId);
	
	@Override
	@Query("select user from TXN_User_LogIn_Meta user WHERE user.txnId= :txnId")
	TXN_User_LogIn_Meta getExistingUserByTxnId(@Param("txnId")Long txnId);
	
	@Override
	@Modifying 
	@Transactional
	@Query("UPDATE"
			+ " TXN_User_LogIn_Meta user SET "
			+ "user.datetime_Logged_Out = :Datetime_Logged_Out , "
			+ "user.isLive=false ")
	void updateStatusForAll(@Param("Datetime_Logged_Out")Timestamp Datetime_Logged_Out);
	
	@Override
	@Modifying 
	@Query(value="delete from TXN_User_LogIn_Meta tul where tul.userDetails.userId =:userId")
	void deleteLoginMetaData(@Param("userId")Integer userId);
}
