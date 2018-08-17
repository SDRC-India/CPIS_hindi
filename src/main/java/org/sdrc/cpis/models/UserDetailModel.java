package org.sdrc.cpis.models;


public class UserDetailModel {

	private Integer userId;
	
	private String name;
	
	private String userName;
	
	private Long loginTxnId;

	private String userIp;

	private String email;
	
	private String contactNum;
	
	private String password;
	
	private String address;
	
	private String updatedBy;
	
	private String updatedDate;
	
	private Integer areaId;
	
	private String areaName;
	
	private Integer designationId;
	
	private DesignationModel designationModel;
	
	private boolean isLive;
	
	private long loginMetaId;
	
	private String createdBy;
	
	private String createdDate;
	
	private String userAgent;
	
	private Integer cciId;
	
	private int areaLevelId;
	
//	private Integer roleId;
	
//	private List<UserRoleFeaturePermissionMappingModel> userRoleFeaturePermissionMappings;

	public int getAreaLevelId() {
		return areaLevelId;
	}

	public void setAreaLevelId(int areaLevelId) {
		this.areaLevelId = areaLevelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	
	public Long getLoginTxnId() {
		return loginTxnId;
	}

	public void setLoginTxnId(Long loginTxnId) {
		this.loginTxnId = loginTxnId;
	}
	
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getEmail() {
		return email;
	}

	public String getContactNum() {
		return contactNum;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}
	

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public boolean isLive() {
		return isLive;
	}

	
/*	public List<UserRoleFeaturePermissionMappingModel> getUserRoleFeaturePermissionMappings() {
		return userRoleFeaturePermissionMappings;
	}
	
	public void setUserRoleFeaturePermissionMappings(
			List<UserRoleFeaturePermissionMappingModel> userRoleFeaturePermissionMappings) {
		this.userRoleFeaturePermissionMappings = userRoleFeaturePermissionMappings;
	}*/

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public long getLoginMetaId() {
		return loginMetaId;
	}

	public void setLoginMetaId(long loginMetaId) {
		this.loginMetaId = loginMetaId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public DesignationModel getDesignationModel() {
		return designationModel;
	}

	public void setDesignationModel(DesignationModel designationModel) {
		this.designationModel = designationModel;
	}
	

	public Integer getCciId() {
		return cciId;
	}

	public void setCciId(Integer cciId) {
		this.cciId = cciId;
	}

	@Override
	public String toString() {
		return "{userId:" + userId + ", name:" + name + ", userName:" + userName + ", loginTxnId:"
				+ loginTxnId + ", userIp:" + userIp + ", email:" + email + ", contactNum:" + contactNum + ", password:"
				+ password + ", address:" + address + ", updatedBy:" + updatedBy + ", updatedDate:" + updatedDate
				+ ", areaId:" + areaId + ", areaName:" + areaName + ", designationId:" + designationId
				+ ", designationModel:" + designationModel + ", isLive:" + isLive + ", loginMetaId:" + loginMetaId
				+ ", createdBy:" + createdBy + ", createdDate:" + createdDate + ", userAgent:" + userAgent + "}";
	}

	
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}
	
	

}
