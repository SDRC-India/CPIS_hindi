package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CCI_Details")
public class CCIDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2199791978005198373L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	Integer cciId;

	@Column(name="Name")
	String cciName;
	
	@Column(name="user_id")
	Integer userId;
	
//	@ManyToOne
	@Column(name="District")
	Integer areaDetails;
	

	@Column(name="Longitude")
	String longitude;

	@Column(name="Latitude")
	String latitude;
	
	@Column(name="Contact")
	String contact;
	
	@Column(name="address")
	String address;

	@Column(name="Total_capacity")
	Integer total;
	
	@Column(name="Boys_capacity")
	Integer boys;
	
	@Column(name="Girls_capacity")
	Integer girls;
	
	@Column(name="Total_No_Of_Rooms")
	Integer totalRooms;
	
	@Column(name="Rooms_For_Boys")
	Integer roomsForBoys;
	
	@Column(name="Rooms_For_Girls")
	Integer roomsForGirls;

	@Column(name="Total_No_Of_Toilets")
	Integer totalToilets;
	
	@Column(name="Toilets_For_Boys")
	Integer toiletsForBoys;

	@Column(name="Toilets_For_Girls")
	Integer toiletsForGirls;
	
	@Column(name="Children_Living_In_Single_Room")
	Integer childrenInSingleRoom;

	@Column(name="Proper_Electricity")
	Boolean properElectricity;
	
	@Column(name="Proper_Drinking_Water_Facility")
	Boolean properDrinkingWater;
	
	@Column(name="Boundary_Wall")
	Boolean boundaryWall;
	
	@Column(name="Building_Type")
	private Integer building_Type;
	
	@Column(name="Area_of_Building")
	private Double area_of_Building;
	
	@Column(name="Status_of_Building")
	private Integer status_of_Building;
	
	@Column(name="Power_Backupfacility_AllRooms")
	private Boolean power_Backupfacility_AllRooms;
	
	@Column(name="Separate_Toiletsfor_Staff")
	private Boolean separate_Toiletsfor_Staff;
	
	@Column(name="Number_of_ContactPoints")
	private Integer number_of_ContactPoints;


	@ManyToOne
	@JoinColumn(name="CCIType")
	CCITypeDetails cciTypeDetails;
	
	@OneToMany(mappedBy="cciDetails")
	private List<ChildDetails> childDetails;
	
	@OneToMany(mappedBy="cciDetails")
	private List<CCITransactionDetails> cciTrans;
	
	@OneToMany(mappedBy="cci")
	private List<ChildPlacedInFitInstitution> childInFitInstitute;

	
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

	public CCITypeDetails getCciTypeDetails() {
		return cciTypeDetails;
	}

	public void setCciTypeDetails(CCITypeDetails cciTypeDetails) {
		this.cciTypeDetails = cciTypeDetails;
	}

	public List<ChildDetails> getChildDetails() {
		return childDetails;
	}

	public void setChildDetails(List<ChildDetails> childDetails) {
		this.childDetails = childDetails;
	}

	public List<CCITransactionDetails> getCciTrans() {
		return cciTrans;
	}

	public void setCciTrans(List<CCITransactionDetails> cciTrans) {
		this.cciTrans = cciTrans;
	}

	public Integer getAreaDetails() {
		return areaDetails;
	}

	public void setAreaDetails(Integer areaDetails) {
		this.areaDetails = areaDetails;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBuilding_Type() {
		return building_Type;
	}

	public void setBuilding_Type(Integer building_Type) {
		this.building_Type = building_Type;
	}

	public Double getArea_of_Building() {
		return area_of_Building;
	}

	public void setArea_of_Building(Double area_of_Building) {
		this.area_of_Building = area_of_Building;
	}

	public Integer getStatus_of_Building() {
		return status_of_Building;
	}

	public void setStatus_of_Building(Integer status_of_Building) {
		this.status_of_Building = status_of_Building;
	}



	public Boolean getPower_Backupfacility_AllRooms() {
		return power_Backupfacility_AllRooms;
	}

	public void setPower_Backupfacility_AllRooms(Boolean power_Backupfacility_AllRooms) {
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

	public List<ChildPlacedInFitInstitution> getChildInFitInstitute() {
		return childInFitInstitute;
	}

	public void setChildInFitInstitute(List<ChildPlacedInFitInstitution> childInFitInstitute) {
		this.childInFitInstitute = childInFitInstitute;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	


}
