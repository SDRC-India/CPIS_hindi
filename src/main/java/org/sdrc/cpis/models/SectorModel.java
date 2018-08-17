/**
 * 
 */
package org.sdrc.cpis.models;

import java.util.List;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public class SectorModel 
{

	private int sectorId;
	private String sectorName;
	private int parentId;
	private String sectorType;
	private List<IndicatorUnitSubgroupModel> iusModel;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getSectorType() {
		return sectorType;
	}
	public void setSectorType(String sectorType) {
		this.sectorType = sectorType;
	}
	public List<IndicatorUnitSubgroupModel> getIusModel() {
		return iusModel;
	}
	public void setIusModel(List<IndicatorUnitSubgroupModel> iusModel) {
		this.iusModel = iusModel;
	}
	
	
}
