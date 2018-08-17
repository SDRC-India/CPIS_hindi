package org.sdrc.cpis.models;

import java.util.List;

public class ValueModel {
	
	private String name;
	
	private int id;
	
	private List<ValueModel> childs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ValueModel> getChilds() {
		return childs;
	}

	public void setChilds(List<ValueModel> childs) {
		this.childs = childs;
	}
	
	

}
