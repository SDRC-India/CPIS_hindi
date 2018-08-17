package org.sdrc.cpis.domains;

import java.io.Serializable;
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

@Table(name="Page_Content_Details")
@Entity
public class PageContentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6901349903711397519L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	Integer pageId;

	@Column(name="View_Name")
	String viewName;

	@Column(name="Content_Title")
	String contentTitle;

	@Column(name="Content")
	String content;
	
	@Column(name="image_path")
	String imagePath;
	
	@Column(name="href")
	String href;
	
	@ManyToOne
	@JoinColumn(name="Parent_Content_Id")
	PageContentDetails pageContentDetails;

	@OneToMany(mappedBy="pageContentDetails")
	private List<PageContentDetails> childContents;
	
	@Column(name="LanguageType")
	Integer languageDetails;

//	@ManyToOne
//	@JoinColumn(name="LanguageType")
//	LanguageDetails languageDetails;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PageContentDetails getPageContentDetails() {
		return pageContentDetails;
	}

	public void setPageContentDetails(PageContentDetails pageContentDetails) {
		this.pageContentDetails = pageContentDetails;
	}

	public List<PageContentDetails> getChildContents() {
		return childContents;
	}

	public void setChildContents(List<PageContentDetails> childContents) {
		this.childContents = childContents;
	}

	public Integer getLanguage() {
		return languageDetails;
	}

	public void setLanguage(Integer languageDetails) {
		this.languageDetails = languageDetails;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	

//	public LanguageDetails getLanguageDetails() {
//		return languageDetails;
//	}
//
//	public void setLanguageDetails(LanguageDetails languageDetails) {
//		this.languageDetails = languageDetails;
//	}
	
	

}
