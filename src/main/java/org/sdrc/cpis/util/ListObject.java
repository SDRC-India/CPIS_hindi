package org.sdrc.cpis.util;

import java.util.Map;

public class ListObject {
	String key;
	Map<String, Object> value;
	
	public Map<String, Object> getValue() {
		return value;
	}
	public void setValue(Map<String, Object> value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
