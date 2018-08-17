/**
 * 
 */
package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *This table contains all the sectors with a parent child relationship within it
 */
@Entity
public class Sectors implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="serial")
	private int sectorId;
	
	@Column(nullable=false)
	private String sectorName;
	
	@Column(nullable=false)
	private String SectorType;
	
	@Column(nullable=false)
	private int parentId;
	
	@OneToMany(mappedBy="sectors")
	private List<IndicatorUnitSubgroup> indicatorUnitSubgroup;
	

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getSectorType() {
		return SectorType;
	}

	public void setSectorType(String sectorType) {
		SectorType = sectorType;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<IndicatorUnitSubgroup> getIndicatorUnitSubgroup() {
		return indicatorUnitSubgroup;
	}

	public void setIndicatorUnitSubgroup(
			List<IndicatorUnitSubgroup> indicatorUnitSubgroup) {
		this.indicatorUnitSubgroup = indicatorUnitSubgroup;
	}
	
}
