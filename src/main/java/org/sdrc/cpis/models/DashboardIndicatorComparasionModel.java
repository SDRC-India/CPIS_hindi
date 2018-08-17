package org.sdrc.cpis.models;
/***
 * This model will contain the indicator comparison data for dashboard
 * 
 * @author Harsh Pratyush(harsh@sdrc.co.in)
 *
 *
 */
public class DashboardIndicatorComparasionModel 
{

	private String indicatorName;
	private String indicatorNameHindi;
	private String newValue;
	private String oldValue;
	private String diffrencePercent;
	private String cssClass;
	private String timePeriod;
	
	public String getIndicatorName() {
		return indicatorName;
	}
	public String getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getDiffrencePercent() {
		return diffrencePercent;
	}
	public void setDiffrencePercent(String diffrencePercent) {
		this.diffrencePercent = diffrencePercent;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getIndicatorNameHindi() {
		return indicatorNameHindi;
	}
	public void setIndicatorNameHindi(String indicatorNameHindi) {
		this.indicatorNameHindi = indicatorNameHindi;
	}
	
}
