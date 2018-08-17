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
@Table(name="area_details")
public class AreaDetails implements Serializable {

	/**
	 * 
	 */
		private static final long serialVersionUID = -2295952636358742934L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="Id")
		Integer areaId;
		
		@Column(name="area_code")
		String areaCode;
		
		@Column(name="name")
		String areaName;
		
		@ManyToOne
		@JoinColumn(name="area_level")
		AreaLevel areaLevel;
		
		@ManyToOne
		@JoinColumn(name="parent_area_id")
		AreaDetails parentArea;
		
		@OneToMany(mappedBy="parentArea")
		List<AreaDetails> areas;
		
		@OneToMany(mappedBy="areaId")
		List<AreaDetails> areaList;
		
		@OneToMany(mappedBy="areaId")
		List<AreaDetails> districtList;
		
//		@Column(name="area_Short_Name")
//		String areaShortName;
		
//		@OneToMany(mappedBy="district")
//		List<CCIDetails> cciDetails;
		
		

		public Integer getAreaId() {
			return areaId;
		}

		public AreaDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public AreaDetails(String areaCode) {
			super();
			this.areaCode = areaCode;
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



		public AreaDetails getParentArea() {
			return parentArea;
		}

		public void setParentArea(AreaDetails parentArea) {
			this.parentArea = parentArea;
		}

		public List<AreaDetails> getAreas() {
			return areas;
		}

		public void setAreas(List<AreaDetails> areas) {
			this.areas = areas;
		}


		public AreaLevel getAreaLevel() {
			return areaLevel;
		}

		public void setAreaLevel(AreaLevel areaLevel) {
			this.areaLevel = areaLevel;
		}

		public List<AreaDetails> getAreaList() {
			return areaList;
		}

		public void setAreaList(List<AreaDetails> areaList) {
			this.areaList = areaList;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public List<AreaDetails> getDistrictList() {
			return districtList;
		}

		public void setDistrictList(List<AreaDetails> districtList) {
			this.districtList = districtList;
		}

//		public String getAreaShortName() {
//			return areaShortName;
//		}
//
//		public void setAreaShortName(String areaShortName) {
//			this.areaShortName = areaShortName;
//		}

		
		
//		public List<CCIDetails> getCciDetails() {
//			return cciDetails;
//		}
//
//		public void setCciDetails(List<CCIDetails> cciDetails) {
//			this.cciDetails = cciDetails;
//		}
		
		

	
}
