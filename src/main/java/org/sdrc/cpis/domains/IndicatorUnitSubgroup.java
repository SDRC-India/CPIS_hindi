package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Harsh(harsh@sdrc.co.in)
 *	@version 1.0
 *
 *  This domain will have the indicators and its unit and subgroup according to Sector
 */
@Entity
@Table(name="Indicato_Unit_Subgroup")
public class IndicatorUnitSubgroup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="serial")
	private int indicatorId;
	
	@Column(nullable=false)
	private String indicatorName;
	
	@Column(nullable=true)
	private String indicatorNameHindi;
	
	@Column(nullable=false)
	private String unit;
	
	@Column(nullable=false)
	private String subGroup;
	
	@Column(nullable=false)
	private boolean highIsGood;
	
	@Column(nullable=false)
	private String Module;
	
	
	@Column
	private String subGroupType;
	
	@ManyToOne
	@JoinColumn(name="sectorId")
	private Sectors sectors;

	public int getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(int indicatorId) {
		this.indicatorId = indicatorId;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	public boolean isHighIsGood() {
		return highIsGood;
	}

	public void setHighIsGood(boolean highIsGood) {
		this.highIsGood = highIsGood;
	}

	public String getModule() {
		return Module;
	}

	public void setModule(String module) {
		Module = module;
	}

	public Sectors getSectors() {
		return sectors;
	}

	public void setSectors(Sectors sectors) {
		this.sectors = sectors;
	}

	public String getIndicatorNameHindi() {
		return indicatorNameHindi;
	}

	public void setIndicatorNameHindi(String indicatorNameHindi) {
		this.indicatorNameHindi = indicatorNameHindi;
	}

	public String getSubGroupType() {
		return subGroupType;
	}

	public void setSubGroupType(String subGroupType) {
		this.subGroupType = subGroupType;
	}

	@Override
	public String toString() {
		return "IndicatorUnitSubgroup [indicatorId=" + indicatorId + ", indicatorName=" + indicatorName + ", unit="
				+ unit + ", subGroup=" + subGroup + ", highIsGood=" + highIsGood + ", Module=" + Module + ", sectors="
				+ sectors + "]";
	}

}
