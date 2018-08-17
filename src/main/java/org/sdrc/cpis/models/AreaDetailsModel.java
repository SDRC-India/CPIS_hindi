package org.sdrc.cpis.models;

public class AreaDetailsModel {
	
	private Integer areaId;
	private String areaName;
	private Integer areaLevel;
	private Integer parentAreaId;
	private String areaCode;
	private Boolean checked;
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	public Integer getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(Integer parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	

}
