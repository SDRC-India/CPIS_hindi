package org.sdrc.cpis.models;

import java.util.List;

public class DesignationModel {
	
	private Integer designationId;
	private String designationName;
	private Integer designationAreaLevel;
	private List<DesignationFeaturePermissionModel> designationFeaturePermissionMappings;
	
	public Integer getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public Integer getDesignationAreaLevel() {
		return designationAreaLevel;
	}
	public void setDesignationAreaLevel(Integer designationAreaLevel) {
		this.designationAreaLevel = designationAreaLevel;
	}
	public List<DesignationFeaturePermissionModel> getDesignationFeaturePermissionMappings() {
		return designationFeaturePermissionMappings;
	}
	public void setDesignationFeaturePermissionMappings(
			List<DesignationFeaturePermissionModel> designationFeaturePermissionMappings) {
		this.designationFeaturePermissionMappings = designationFeaturePermissionMappings;
	}
	
}
