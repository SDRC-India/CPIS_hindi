package org.sdrc.cpis.models;

public class FeaturePermissionMappingModel {
private Integer featurePermissionId;
private FeatureModel feature;
private PermissionModel permission;
public Integer getFeaturePermissionId() {
	return featurePermissionId;
}
public void setFeaturePermissionId(Integer featurePermissionId) {
	this.featurePermissionId = featurePermissionId;
}
public FeatureModel getFeature() {
	return feature;
}
public void setFeature(FeatureModel feature) {
	this.feature = feature;
}
public PermissionModel getPermission() {
	return permission;
}
public void setPermission(PermissionModel permission) {
	this.permission = permission;
}



}
