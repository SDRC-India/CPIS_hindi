package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Area_Level")
public class AreaLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409315198849933849L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer levelId;
	
	@Column(name="Name")
	private String levelName;
	
	@OneToMany(mappedBy="areaLevel",fetch = FetchType.EAGER)
	List<AreaDetails> areas;
	
	@OneToMany(mappedBy="areaLevel")
	List<Designation> designations;

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public List<AreaDetails> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaDetails> areas) {
		this.areas = areas;
	}

	public List<Designation> getDesignations() {
		return designations;
	}

	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}
	
	

}
