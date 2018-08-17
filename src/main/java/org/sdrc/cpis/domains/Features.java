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
@Table(name="Features")
public class Features implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Featured_Id")
	private Integer featureId;
	
	@Column(name="Feature_Code")
	private Integer featureCode;
	
	@Column(name="Feature_Name")
	private String featureName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="Last_Updated_Date")
	private Timestamp lastUpdatedDate;
	
	@Column(name="Last_Updated_By")
	private String LastUpdatedBy;
	
	@OneToMany(mappedBy="features")
	private List<FeaturePermissionMapping> featurePermissionMappings;

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public Integer getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(Integer featureCode) {
		this.featureCode = featureCode;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
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
		return LastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
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
