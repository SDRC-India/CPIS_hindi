package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the TXN_User_LogIn_Meta database table.
 * 
 */
@Entity
@Table(name="txn_user_logIn_meta")
public class TXN_User_LogIn_Meta implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="txn_id")
	private long txnId;

	@Column(name="datetime_logged_in")
	private Timestamp datetime_Logged_IN;

	@Column(name="datetime_logged_out")
	private Timestamp datetime_Logged_Out;

	@Column(name="is_live")
	private boolean isLive;
	
	@Column(name="user_ip")
	private String userIp;
	
	@Column(name="user_agent")
	private String userAgent;

	//bi-directional many-to-one association to MST_User
	@ManyToOne 
	@JoinColumn(name="user_id")
	private UserDetails userDetails;

	public long getTxnId() {
		return txnId;
	}

	public void setTxnId(long txnId) {
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

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}