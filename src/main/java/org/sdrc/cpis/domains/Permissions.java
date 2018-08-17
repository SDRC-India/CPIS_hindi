package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Permissions")
public class Permissions implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Permissions_Id")
	private Integer permissionsId;
	
	@Column(name="Permissions_Code")
	private Integer permissionsCode;
	
	@Column(name="Permissions_Name")
	private String permissionsName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Last_Updated_Date")
	private Timestamp lastUpdatedDate;
	
	@Column(name="Last_Updated_By")
	private String lastUpdatedBy;
	
	@OneToMany(mappedBy="permissions")
	private List<FeaturePermissionMapping> featurePermissionMappings;

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	}

	public Integer getPermissionsCode() {
		return permissionsCode;
	}

	public void setPermissionsCode(Integer permissionsCode) {
		this.permissionsCode = permissionsCode;
	}

	public String getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public List<FeaturePermissionMapping> getFeaturePermissionMappings() {
		return featurePermissionMappings;
	}

	public void setFeaturePermissionMappings(
			List<FeaturePermissionMapping> featurePermissionMappings) {
		this.featurePermissionMappings = featurePermissionMappings;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
