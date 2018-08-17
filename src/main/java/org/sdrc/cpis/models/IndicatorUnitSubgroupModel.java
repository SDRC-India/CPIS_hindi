/**
 * 
 */
package org.sdrc.cpis.models;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public class IndicatorUnitSubgroupModel 
{

	private int indicatorUnitSubgroupId;
	private String indicatorName;
	private String subgroupName,unit;
	private Integer sectorId;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	private boolean highIsGood;
	public int getIndicatorUnitSubgroupId() {
		return indicatorUnitSubgroupId;
	}
	public void setIndicatorUnitSubgroupId(int indicatorUnitSubgroupId) {
		this.indicatorUnitSubgroupId = indicatorUnitSubgroupId;
	}
	public String getIndicatorName() {
		return indicatorName;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getSubgroupName() {
		return subgroupName;
	}
	public void setSubgroupName(String subgroupName) {
		this.subgroupName = subgroupName;
	}
	public boolean isHighIsGood() {
		return highIsGood;
	}
	public void setHighIsGood(boolean highIsGood) {
		this.highIsGood = highIsGood;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
	public Integer getSectorId() {
		return sectorId;
	}
	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}
	private String module;
	
}
