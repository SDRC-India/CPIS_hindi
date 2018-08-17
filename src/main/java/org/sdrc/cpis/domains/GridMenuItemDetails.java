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

@Entity
@Table(name="grid_menu_items")
public class GridMenuItemDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3126497435356378957L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id", columnDefinition = "serial")
	private Integer itemId;
	
	@Column(name="name")
	private String itemName;
	
	@Column(name="image_path")
	private String imagePath;
	
	@Column(name="url")
	private String url;
	
	@OneToMany(mappedBy="gridMenu")
	private List<GridMenuDesignationMapping> gridMenuRoles;
	
	@Column(name="image2_path")
	private String image2Path;
	
	@Column(name="order")
	private Integer order;
	
	@Column(name="program_type")
	private Integer programType;
	

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<GridMenuDesignationMapping> getGridMenuRoles() {
		return gridMenuRoles;
	}

	public void setGridMenuRoles(List<GridMenuDesignationMapping> gridMenuRoles) {
		this.gridMenuRoles = gridMenuRoles;
	}

	public String getImage2Path() {
		return image2Path;
	}

	public void setImage2Path(String image2Path) {
		this.image2Path = image2Path;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getProgramType() {
		return programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	
	
}
