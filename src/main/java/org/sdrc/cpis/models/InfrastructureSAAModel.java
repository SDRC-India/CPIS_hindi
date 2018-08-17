package org.sdrc.cpis.models;

import org.sdrc.cpis.util.ValueObject;

public class InfrastructureSAAModel {
	
	private Integer id;	
	private ValueObject nameOfDistrict;
	private ValueObject nameOfSAA;
	private ValueObject building_Type;
	private Double area_of_Building;
	private Boolean building_Protected_by_Boundarywall;
	private ValueObject status_of_Building;
	private Integer total_Numbers_Room;
	private Boolean availability_Electricity_Allrooms;
	private Boolean power_Backupfacility_AllRooms;
	private Integer number_of_toiletsAvailable;
	private Boolean availability_of_DrinkingWater;
	private Integer total_capacity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ValueObject getNameOfDistrict() {
		return nameOfDistrict;
	}
	public void setNameOfDistrict(ValueObject nameOfDistrict) {
		this.nameOfDistrict = nameOfDistrict;
	}

	public ValueObject getNameOfSAA() {
		return nameOfSAA;
	}
	public void setNameOfSAA(ValueObject nameOfSAA) {
		this.nameOfSAA = nameOfSAA;
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
	public Boolean getBuilding_Protected_by_Boundarywall() {
		return building_Protected_by_Boundarywall;
	}
	public void setBuilding_Protected_by_Boundarywall(Boolean building_Protected_by_Boundarywall) {
		this.building_Protected_by_Boundarywall = building_Protected_by_Boundarywall;
	}
	public ValueObject getStatus_of_Building() {
		return status_of_Building;
	}
	public void setStatus_of_Building(ValueObject status_of_Building) {
		this.status_of_Building = status_of_Building;
	}
	public Integer getTotal_Numbers_Room() {
		return total_Numbers_Room;
	}
	public void setTotal_Numbers_Room(Integer total_Numbers_Room) {
		this.total_Numbers_Room = total_Numbers_Room;
	}
	public Boolean getAvailability_Electricity_Allrooms() {
		return availability_Electricity_Allrooms;
	}
	public void setAvailability_Electricity_Allrooms(Boolean availability_Electricity_Allrooms) {
		this.availability_Electricity_Allrooms = availability_Electricity_Allrooms;
	}
	public Boolean getPower_Backupfacility_AllRooms() {
		return power_Backupfacility_AllRooms;
	}
	public void setPower_Backupfacility_AllRooms(Boolean power_Backupfacility_AllRooms) {
		this.power_Backupfacility_AllRooms = power_Backupfacility_AllRooms;
	}
	public Integer getNumber_of_toiletsAvailable() {
		return number_of_toiletsAvailable;
	}
	public void setNumber_of_toiletsAvailable(Integer number_of_toiletsAvailable) {
		this.number_of_toiletsAvailable = number_of_toiletsAvailable;
	}
	public Boolean getAvailability_of_DrinkingWater() {
		return availability_of_DrinkingWater;
	}
	public void setAvailability_of_DrinkingWater(Boolean availability_of_DrinkingWater) {
		this.availability_of_DrinkingWater = availability_of_DrinkingWater;
	}
	
	public Integer getTotal_capacity() {
		return total_capacity;
	}
	public void setTotal_capacity(Integer total_capacity) {
		this.total_capacity = total_capacity;
	}
	
	

}
