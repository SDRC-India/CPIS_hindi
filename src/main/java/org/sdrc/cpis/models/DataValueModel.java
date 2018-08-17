/**
 * 
 */
package org.sdrc.cpis.models;

/**
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public class DataValueModel 
{

	private int dataValueId;
	
	private float value;
	
	private String areaName;
	
	private String areaCode;

	public int getDataValueId() {
		return dataValueId;
	}

	public void setDataValueId(int dataValueId) {
		this.dataValueId = dataValueId;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
