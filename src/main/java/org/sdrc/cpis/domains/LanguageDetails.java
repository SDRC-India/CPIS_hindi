package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="language_details")
@Entity
public class LanguageDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8589568701049018991L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	Integer id;

	@Column(name="Type")
	String type;

	@Column(name="Language")
	String language;
	
	@OneToMany(mappedBy="languageDetails")
	private List<Header> headers;
	
//	@OneToMany(mappedBy="languageDetails")
//	private List<PageContentDetails> pageContentDetails;

	
	
	public LanguageDetails(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}

//	public List<PageContentDetails> getPageContentDetails() {
//		return pageContentDetails;
//	}
//
//	public void setPageContentDetails(List<PageContentDetails> pageContentDetails) {
//		this.pageContentDetails = pageContentDetails;
//	}

		
	
}
