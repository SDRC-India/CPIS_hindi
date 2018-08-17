package org.sdrc.cpis.models;


public class CCTSTypeDetailsModel {
	
	Integer typeDetailsId;
	String typeDetailsName;
	Integer typeId;
	Boolean checked;
	
	public Integer getTypeDetailsId() {
		return typeDetailsId;
	}
	public void setTypeDetailsId(Integer typeDetailsId) {
		this.typeDetailsId = typeDetailsId;
	}
	public String getTypeDetailsName() {
		return typeDetailsName;
	}
	public void setTypeDetailsName(String typeDetailsName) {
		this.typeDetailsName = typeDetailsName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
