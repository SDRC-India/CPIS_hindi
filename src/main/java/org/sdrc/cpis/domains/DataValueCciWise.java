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
 * @author abikananda(abikananda@sdrc.co.in)
 *
 *This will have the data value for a specific indicator at some timeperiod and for CCI level
 */
@Entity
@Table(name="Data_Value_CCI_Wise")
public class DataValueCciWise implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private int dataValueId;
	
	@Column(nullable=false)
	private float value;
	
	@Column(nullable = true, columnDefinition = "text", length = 65556)
	private String childIds;
	
	@ManyToOne
	@JoinColumn(name="timePeriodId")
	private TimePeriod timePeriod;
	
	@ManyToOne
	@JoinColumn(name="areaId")
	private AreaDetails areaDetails;
	
	@ManyToOne
	@JoinColumn(name="cciId")
	private CCIDetails cciDetails;
	
	@ManyToOne
	@JoinColumn(name="IUSid")
	private IndicatorUnitSubgroup indicatorUnitSubgroup;
	
	@Column(nullable=false)
	private String cciName;
	
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

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public AreaDetails getAreaDetails() {
		return areaDetails;
	}

	public void setAreaDetails(AreaDetails areaDetails) {
		this.areaDetails = areaDetails;
	}

	public IndicatorUnitSubgroup getIndicatorUnitSubgroup() {
		return indicatorUnitSubgroup;
	}

	public void setIndicatorUnitSubgroup(IndicatorUnitSubgroup indicatorUnitSubgroup) {
		this.indicatorUnitSubgroup = indicatorUnitSubgroup;
	}

	public CCIDetails getCciDetails() {
		return cciDetails;
	}

	public void setCciDetails(CCIDetails cciDetails) {
		this.cciDetails = cciDetails;
	}

	public String getCciName() {
		return cciName;
	}

	public void setCciName(String cciName) {
		this.cciName = cciName;
	}

	public String getChildIds() {
		return childIds;
	}

	public void setChildIds(String childIds) {
		this.childIds = childIds;
	}
	
}
