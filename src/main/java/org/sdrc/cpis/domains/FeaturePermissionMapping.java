package org.sdrc.cpis.domains;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Feature_Permission_Mapping")
public class FeaturePermissionMapping implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Feature_Permission_Id")
	private Integer featurePermissionId;
	
	@ManyToOne
	@JoinColumn(name="Feature_Id")
	private Features features;
	
	@ManyToOne
	@JoinColumn(name="Permissions_Id")
	private Permissions permissions;
	
	@OneToMany(mappedBy="featurePermissionMapping")
	private List<DesignationFeaturePermissionMapping> roleFeaturePermissionMappings;

	public Integer getFeaturePermissionId() {
		return featurePermissionId;
	}

	public void setFeaturePermissionId(Integer featurePermissionId) {
		this.featurePermissionId = featurePermissionId;
	}

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public List<DesignationFeaturePermissionMapping> getRoleFeaturePermissionMappings() {
		return roleFeaturePermissionMappings;
	}

	public void setRoleFeaturePermissionMappings(List<DesignationFeaturePermissionMapping> roleFeaturePermissionMappings) {
		this.roleFeaturePermissionMappings = roleFeaturePermissionMappings;
	}

	
	
	

}
