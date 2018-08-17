package org.sdrc.cpis.util;

import java.util.Map;

public class GeoLocationObject {
	private int id;
	private String areaID;
	private Double dataValue;
	private Double longitude;
	private Double latitude;
	private Double altitude;
	private String images;
	private String title;
	private String address;
	private boolean showWindow;
	private String icon;
	private Map<String,Object> options;
	private Integer cciType;
	public Map<String,Object> getOptions() {
		return options;
	}
	public void setOptions(Map<String,Object> options) {
		this.options = options;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}
	public Double getDataValue() {
		return dataValue;
	}
	public void setDataValue(Double dataValue) {
		this.dataValue = dataValue;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isShowWindow() {
		return showWindow;
	}
	public void setShowWindow(boolean showWindow) {
		this.showWindow = showWindow;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getCciType() {
		return cciType;
	}
	public void setCciType(Integer cciType) {
		this.cciType = cciType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	}
