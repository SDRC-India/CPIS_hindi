package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.util.ArrayList;
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

@Table(name="Header_Details")
@Entity
public class Header implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -2881330393914869412L;

	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="Item_Id")
Integer itemId;

@Column(name="Item_Name")
String itemName;

@Column(name="Item_Content")
String itemContent;

@ManyToOne
@JoinColumn(name="LanguageType")
LanguageDetails languageDetails;

@ManyToOne
@JoinColumn(name="Parent_Item_Id")
Header header;

@OneToMany(mappedBy="header")
private List<Header> subHeaders = new ArrayList<Header>();


public Integer getItemId() {
	return itemId;
}

public void setItemId(Integer itemId) {
	this.itemId = itemId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}


public String getItemContent() {
	return itemContent;
}

public void setItemContent(String itemContent) {
	this.itemContent = itemContent;
}

public LanguageDetails getLanguageDetails() {
	return languageDetails;
}

public void setLanguageDetails(LanguageDetails languageDetails) {
	this.languageDetails = languageDetails;
}

public Header getHeader() {
	return header;
}

public void setHeader(Header header) {
	this.header = header;
}

public List<Header> getSubHeaders() {
	return subHeaders;
}

public void setSubHeaders(List<Header> subHeaders) {
	this.subHeaders = subHeaders;
}




}
