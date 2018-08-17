package org.sdrc.cpis.models;

public class DesignationFeaturePermissionModel {
	
			private Integer designationFeaturePermissionId;
			private DesignationModel designation;
			private FeaturePermissionMappingModel featurePermissionMapping;
			
	public Integer getDesignationFeaturePermissionId() {
		return designationFeaturePermissionId;
	}
	public void setDesignationFeaturePermissionId(Integer designationFeaturePermissionId) {
		this.designationFeaturePermissionId = designationFeaturePermissionId;
	}
	public DesignationModel getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationModel designation) {
		this.designation = designation;
	}
	public FeaturePermissionMappingModel getFeaturePermissionMapping() {
		return featurePermissionMapping;
	}
	public void setFeaturePermissionMapping(FeaturePermissionMappingModel featurePermissionMapping) {
		this.featurePermissionMapping = featurePermissionMapping;
	}


}
