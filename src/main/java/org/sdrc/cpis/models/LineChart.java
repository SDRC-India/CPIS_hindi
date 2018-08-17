package org.sdrc.cpis.models;
/**
 * This Model is for the chart data of state average for time periods in dashboard
 * @author Harsh(harsh@sdrc.co.in)
 *
 */
public class LineChart {

	private  String date;
	private  String source;
	private Object value;
	private String group;
	private String shtname;
	private String percentageChange;
	private Boolean highIsGood;
	private  String formattedDate;
	private String unit;
	
	public LineChart() {
		super();
	}

	public LineChart(String source, String date, Object value) {
		super();
		this.source = source;
		this.date = date;
		this.value = value;
	}

	
	
	public String getPercentageChange() {
		return percentageChange;
	}
	public void setPercentageChange(String percentageChange) {
		this.percentageChange = percentageChange;
	}
	public Boolean getIsPositive() {
		return highIsGood;
	}
	public void setIsPositive(Boolean isPositive) {
		this.highIsGood = isPositive;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getShtname() {
		return shtname;
	}
	public void setShtname(String shtname) {
		this.shtname = shtname;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	



}
