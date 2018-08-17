package org.sdrc.cpis.models;

import org.sdrc.cpis.domains.CCITypeDetails;
import org.sdrc.cpis.util.GeoLocationObject;
import org.sdrc.cpis.util.ValueObject;

/**
 * 
 * @author Pratyush Kumar Rath
 * My first model
 * pratyush@sdrc.co.in
 *
 */
public class CCIInfoMapModel {
	
	private int cciId;
	private String name;
	private String longitude;
	private String latitude;
	private String district;
	private String address;
	private String contact;
	private Integer totalCapacity;
	private Integer boysCapacity;
	private Integer girlsCapacity;
	
	private Integer total_rooms_value;
	private Integer no_of_children_value;
	private Integer no_of_room_boys_value;
	private Integer no_of_room_girls_value;
	private boolean proper_elec_value;
	private Integer no_of_toilets_value;
	private boolean drinking_water_facility_value;
	private Integer no_of_toilet_boys_value;
	private Integer no_of_toilet_girls_value;
	private boolean boundary_wall_value;
	
	private Integer totalChildrenLiving;
	private Integer totalBoysLiving;
	private Integer totalGirlsLiving;
	private Integer totalThirdGenderLiving;
	private Integer cciType;
	private GeoLocationObject geoLocationObject;
	private CCITypeDetails cciTypeDetails;
	
	private ValueObject building_Type;
	private Double area_of_Building;
	private ValueObject status_of_Building;
	private Boolean power_Backupfacility_AllRooms;
	private Boolean separate_Toiletsfor_Staff;
	private Integer number_of_ContactPoints;
	
	
	public int getCciId() {
		return cciId;
	}
	public void setCciId(int cciId) {
		this.cciId = cciId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getTotalCapacity() {
		return totalCapacity;
	}
	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	public Integer getBoysCapacity() {
		return boysCapacity;
	}
	public void setBoysCapacity(Integer boysCapacity) {
		this.boysCapacity = boysCapacity;
	}
	public Integer getGirlsCapacity() {
		return girlsCapacity;
	}
	public void setGirlsCapacity(Integer girlsCapacity) {
		this.girlsCapacity = girlsCapacity;
	}
	public Integer getTotal_rooms_value() {
		return total_rooms_value;
	}
	public void setTotal_rooms_value(Integer total_rooms_value) {
		this.total_rooms_value = total_rooms_value;
	}
	public Integer getNo_of_children_value() {
		return no_of_children_value;
	}
	public void setNo_of_children_value(Integer no_of_children_value) {
		this.no_of_children_value = no_of_children_value;
	}
	public Integer getNo_of_room_boys_value() {
		return no_of_room_boys_value;
	}
	public void setNo_of_room_boys_value(Integer no_of_room_boys_value) {
		this.no_of_room_boys_value = no_of_room_boys_value;
	}
	public Integer getNo_of_room_girls_value() {
		return no_of_room_girls_value;
	}
	public void setNo_of_room_girls_value(Integer no_of_room_girls_value) {
		this.no_of_room_girls_value = no_of_room_girls_value;
	}
	public boolean isProper_elec_value() {
		return proper_elec_value;
	}
	public void setProper_elec_value(boolean proper_elec_value) {
		this.proper_elec_value = proper_elec_value;
	}
	public Integer getNo_of_toilets_value() {
		return no_of_toilets_value;
	}
	public void setNo_of_toilets_value(Integer no_of_toilets_value) {
		this.no_of_toilets_value = no_of_toilets_value;
	}
	public boolean isDrinking_water_facility_value() {
		return drinking_water_facility_value;
	}
	public void setDrinking_water_facility_value(boolean drinking_water_facility_value) {
		this.drinking_water_facility_value = drinking_water_facility_value;
	}
	public Integer getNo_of_toilet_boys_value() {
		return no_of_toilet_boys_value;
	}
	public void setNo_of_toilet_boys_value(Integer no_of_toilet_boys_value) {
		this.no_of_toilet_boys_value = no_of_toilet_boys_value;
	}
	public Integer getNo_of_toilet_girls_value() {
		return no_of_toilet_girls_value;
	}
	public void setNo_of_toilet_girls_value(Integer no_of_toilet_girls_value) {
		this.no_of_toilet_girls_value = no_of_toilet_girls_value;
	}
	public boolean isBoundary_wall_value() {
		return boundary_wall_value;
	}
	public void setBoundary_wall_value(boolean boundary_wall_value) {
		this.boundary_wall_value = boundary_wall_value;
	}
	public Integer getTotalChildrenLiving() {
		return totalChildrenLiving;
	}
	public void setTotalChildrenLiving(Integer totalChildrenLiving) {
		this.totalChildrenLiving = totalChildrenLiving;
	}
	public Integer getTotalBoysLiving() {
		return totalBoysLiving;
	}
	public void setTotalBoysLiving(Integer totalBoysLiving) {
		this.totalBoysLiving = totalBoysLiving;
	}
	public Integer getTotalGirlsLiving() {
		return totalGirlsLiving;
	}
	public void setTotalGirlsLiving(Integer totalGirlsLiving) {
		this.totalGirlsLiving = totalGirlsLiving;
	}
	public Integer getCciType() {
		return cciType;
	}
	public void setCciType(Integer cciType) {
		this.cciType = cciType;
	}
	public GeoLocationObject getGeoLocationObject() {
		return geoLocationObject;
	}
	public void setGeoLocationObject(GeoLocationObject geoLocationObject) {
		this.geoLocationObject = geoLocationObject;
	}
	public CCITypeDetails getCciTypeDetails() {
		return cciTypeDetails;
	}
	public void setCciTypeDetails(CCITypeDetails cciTypeDetails) {
		this.cciTypeDetails = cciTypeDetails;
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
	public Integer getTotalThirdGenderLiving() {
		return totalThirdGenderLiving;
	}
	public void setTotalThirdGenderLiving(Integer totalThirdGenderLiving) {
		this.totalThirdGenderLiving = totalThirdGenderLiving;
	}
	
}
