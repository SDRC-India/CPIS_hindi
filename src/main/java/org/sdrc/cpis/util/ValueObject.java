package org.sdrc.cpis.util;

public class ValueObject {
	private Integer key;
	private String value;
	private String desc;
	private Boolean checked;
	private Integer typeId;
	private String name;
	private String typeNameHindi;
	private Integer id;
	private String other;
	private String value2;
	private Integer childCount;
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public ValueObject(String desc,String value) {
		this.desc=desc;
		this.value=value;
		// TODO Auto-generated constructor stub
	}
	public ValueObject() {
		// TODO Auto-generated constructor stub
	}
	public String getTypeNameHindi() {
		return typeNameHindi;
	}
	public void setTypeNameHindi(String typeNameHindi) {
		this.typeNameHindi = typeNameHindi;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	
	
}
