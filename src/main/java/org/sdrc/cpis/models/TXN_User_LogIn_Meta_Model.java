package org.sdrc.cpis.models;

import java.sql.Timestamp;

import org.sdrc.cpis.domains.UserDetails;

public class TXN_User_LogIn_Meta_Model {
	
	private Long txnId;
	private Timestamp datetime_Logged_IN;
	private Timestamp datetime_Logged_Out;
	private Boolean isLive;
	private String userIp;
	private String userAgent;
	private UserDetails userDetails;
	
	
	public Long getTxnId() {
		return txnId;
	}
	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}
	public Timestamp getDatetime_Logged_IN() {
		return datetime_Logged_IN;
	}
	public void setDatetime_Logged_IN(Timestamp datetime_Logged_IN) {
		this.datetime_Logged_IN = datetime_Logged_IN;
	}
	public Timestamp getDatetime_Logged_Out() {
		return datetime_Logged_Out;
	}
	public void setDatetime_Logged_Out(Timestamp datetime_Logged_Out) {
		this.datetime_Logged_Out = datetime_Logged_Out;
	}
	public Boolean isLive() {
		return isLive;
	}
	public void setLive(Boolean isLive) {
		this.isLive = isLive;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
}
