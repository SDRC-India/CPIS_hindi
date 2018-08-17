package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;

public class CCIDetailsModel {
	Integer cciId;

	String cciName;
	
	Integer areaDetails;
	
	String longitude;

	String latitude;
	
	String contact;
	
	Integer total;
	
	Integer boys;
	
	Integer girls;
	
	Integer totalRooms;
	
	Integer roomsForBoys;
	
	Integer roomsForGirls;

	Integer totalToilets;
	
	Integer toiletsForBoys;

	Integer toiletsForGirls;
	
	Integer childrenInSingleRoom;

	Boolean properElectricity;
	
	Boolean properDrinkingWater;
	
	Boolean boundaryWall;
	
	ValueObject building_Type;
	
	Double area_of_Building;
	
	ValueObject status_of_Building;
	
	Boolean power_Backupfacility_AllRooms;
	
	Boolean separate_Toiletsfor_Staff;
	
	Integer number_of_ContactPoints;

	public Integer getCciId() {
		return cciId;
	}

	public void setCciId(Integer cciId) {
		this.cciId = cciId;
	}

	public String getCciName() {
		return cciName;
	}

	public void setCciName(String cciName) {
		this.cciName = cciName;
	}

	public Integer getAreaDetails() {
		return areaDetails;
	}

	public void setAreaDetails(Integer areaDetails) {
		this.areaDetails = areaDetails;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getBoys() {
		return boys;
	}

	public void setBoys(Integer boys) {
		this.boys = boys;
	}

	public Integer getGirls() {
		return girls;
	}

	public void setGirls(Integer girls) {
		this.girls = girls;
	}

	public Integer getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}

	public Integer getRoomsForBoys() {
		return roomsForBoys;
	}

	public void setRoomsForBoys(Integer roomsForBoys) {
		this.roomsForBoys = roomsForBoys;
	}

	public Integer getRoomsForGirls() {
		return roomsForGirls;
	}

	public void setRoomsForGirls(Integer roomsForGirls) {
		this.roomsForGirls = roomsForGirls;
	}

	public Integer getTotalToilets() {
		return totalToilets;
	}

	public void setTotalToilets(Integer totalToilets) {
		this.totalToilets = totalToilets;
	}

	public Integer getToiletsForBoys() {
		return toiletsForBoys;
	}

	public void setToiletsForBoys(Integer toiletsForBoys) {
		this.toiletsForBoys = toiletsForBoys;
	}

	public Integer getToiletsForGirls() {
		return toiletsForGirls;
	}

	public void setToiletsForGirls(Integer toiletsForGirls) {
		this.toiletsForGirls = toiletsForGirls;
	}

	public Integer getChildrenInSingleRoom() {
		return childrenInSingleRoom;
	}

	public void setChildrenInSingleRoom(Integer childrenInSingleRoom) {
		this.childrenInSingleRoom = childrenInSingleRoom;
	}

	public Boolean getProperElectricity() {
		return properElectricity;
	}

	public void setProperElectricity(Boolean properElectricity) {
		this.properElectricity = properElectricity;
	}

	public Boolean getProperDrinkingWater() {
		return properDrinkingWater;
	}

	public void setProperDrinkingWater(Boolean properDrinkingWater) {
		this.properDrinkingWater = properDrinkingWater;
	}

	public Boolean getBoundaryWall() {
		return boundaryWall;
	}

	public void setBoundaryWall(Boolean boundaryWall) {
		this.boundaryWall = boundaryWall;
	}

	public ValueObject getBuilding_Type() {
		return building_Type;
	}

	public void setBuilding_Type(ValueObject building_Type) {
		this.building_Type = building_Type;
	}

	public Double getArea_of_Building() {
		return area_of_Building;
	}

	public void setArea_of_Building(Double area_of_Building) {
		this.area_of_Building = area_of_Building;
	}

	public ValueObject getStatus_of_Building() {
		return status_of_Building;
	}

	public void setStatus_of_Building(ValueObject status_of_Building) {
		this.status_of_Building = status_of_Building;
	}

	public Boolean getPower_Backupfacility_AllRooms() {
		return power_Backupfacility_AllRooms;
	}

	public void setPower_Backupfacility_AllRooms(
			Boolean power_Backupfacility_AllRooms) {
		this.power_Backupfacility_AllRooms = power_Backupfacility_AllRooms;
	}

	public Boolean getSeparate_Toiletsfor_Staff() {
		return separate_Toiletsfor_Staff;
	}

	public void setSeparate_Toiletsfor_Staff(Boolean separate_Toiletsfor_Staff) {
		this.separate_Toiletsfor_Staff = separate_Toiletsfor_Staff;
	}

	public Integer getNumber_of_ContactPoints() {
		return number_of_ContactPoints;
	}

	public void setNumber_of_ContactPoints(Integer number_of_ContactPoints) {
		this.number_of_ContactPoints = number_of_ContactPoints;
	}

}
