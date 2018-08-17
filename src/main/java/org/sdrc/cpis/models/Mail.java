package org.sdrc.cpis.models;

import java.util.List;

public class Mail {
	
	private String fromUserName;
	private String fromMailId;
	private String toUserName;
	private int UserId;
	private List<String> toEmailIds;
	private List<String> ccEmailIds;
	private String subject;
	private String msg;
	private String message;
	private String contactNo;
	private String serverUrl;
	private int themeId;
	private long IMEI;
	
	
	public long getIMEI() {
		return IMEI;
	}

	public void setIMEI(long iMEI) {
		IMEI = iMEI;
	}

	public int getUserId() {
		return UserId;
	}
	
	public void setUserId(int userId) {
		UserId = userId;
	}
	
	public String getFromMailId() {
		return fromMailId;
	}
	
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	
	public int getThemeId() {
		return themeId;
	}
	
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	public String getToUserName() {
		return toUserName;
	}
	
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	public List<String> getToEmailIds() {
		return toEmailIds;
	}
	
	public void setToEmailIds(List<String> toEmailIds) {
		this.toEmailIds = toEmailIds;
	}
	
	public List<String> getCcEmailIds() {
		return ccEmailIds;
	}
	
	public void setCcEmailIds(List<String> ccEmailIds) {
		this.ccEmailIds = ccEmailIds;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}	
	
	
}
