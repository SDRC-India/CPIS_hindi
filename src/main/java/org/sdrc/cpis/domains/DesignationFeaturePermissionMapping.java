package org.sdrc.cpis.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="designation_feature_permission_mapping")
public class DesignationFeaturePermissionMapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Designation_Feature_Permission_Id")
	private Integer designationFeaturePermissionId;
	
//	@ManyToOne
//	@JoinColumn(name="role_id")
//	private Role role;
	
	@ManyToOne
	@JoinColumn(name="designationId")
	private Designation designation;
	
	
	@ManyToOne
	@JoinColumn(name="feature_permission_id")
	private FeaturePermissionMapping featurePermissionMapping;


	public Integer getDesignationFeaturePermissionId() {
		return designationFeaturePermissionId;
	}


	public void setDesignationFeaturePermissionId(Integer designationFeaturePermissionId) {
		this.designationFeaturePermissionId = designationFeaturePermissionId;
	}


	public FeaturePermissionMapping getFeaturePermissionMapping() {
		return featurePermissionMapping;
	}


	public void setFeaturePermissionMapping(
			FeaturePermissionMapping featurePermissionMapping) {
		this.featurePermissionMapping = featurePermissionMapping;
	}


	public Designation getDesignation() {
		return designation;
	}


	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	
	
}
