package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Designation")
public class Designation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9174490689247535376L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer designationId;
	
	@Column(name="Name")
	private String designationName;
	
	@ManyToOne
	@JoinColumn(name="area_level")
	private AreaLevel areaLevel;
	
	@OneToMany(mappedBy="designation",fetch=FetchType.EAGER)
	private List<DesignationFeaturePermissionMapping> designationFeaturePermissionMappings;
	
	@OneToMany(mappedBy="designation")
	private List<GridMenuDesignationMapping> gridMenuRoleMappings;

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

	public AreaLevel getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(AreaLevel areaLevel) {
		this.areaLevel = areaLevel;
	}


	public List<DesignationFeaturePermissionMapping> getDesignationFeaturePermissionMappings() {
		return designationFeaturePermissionMappings;
	}

	public void setDesignationFeaturePermissionMappings(
			List<DesignationFeaturePermissionMapping> designationFeaturePermissionMappings) {
		this.designationFeaturePermissionMappings = designationFeaturePermissionMappings;
	}

	public List<GridMenuDesignationMapping> getGridMenuRoleMappings() {
		return gridMenuRoleMappings;
	}

	public void setGridMenuRoleMappings(List<GridMenuDesignationMapping> gridMenuRoleMappings) {
		this.gridMenuRoleMappings = gridMenuRoleMappings;
	}
	
}
