/**
 * 
 */
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
 * @author Harsh(harsh@sdrc.co.in)
 *
 *This will have the data value for a specific indicator at some timeperiod and for some area
 */
@Entity
@Table(name="Data_Value")
public class DataValue implements Serializable {
	
	/**
	 * 
	 */
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
	@JoinColumn(name="IUSid")
	private IndicatorUnitSubgroup indicatorUnitSubgroup;

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

	public String getChildIds() {
		return childIds;
	}

	public void setChildIds(String childIds) {
		this.childIds = childIds;
	}

	@Override
	public String toString() {
		return "DataValue [dataValueId=" + dataValueId + ", value=" + value + ", childIds=" + childIds + ", timePeriod="
				+ timePeriod + ", areaDetails=" + areaDetails + ", indicatorUnitSubgroup=" + indicatorUnitSubgroup
				+ "]";
	}

}
