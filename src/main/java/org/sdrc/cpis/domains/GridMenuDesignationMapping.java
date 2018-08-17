package org.sdrc.cpis.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="grid_menu_designation_mapping")
public class GridMenuDesignationMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id", columnDefinition = "serial")
	Integer menuRoleMappingId;
	
	@ManyToOne
	@JoinColumn(name="grid_menu")
	GridMenuItemDetails gridMenu;
	
	@ManyToOne
	@JoinColumn(name="designation")
	private Designation designation;
	
//	@ManyToOne
//	@JoinColumn(name="role")
//	Role role;

	public Integer getMenuRoleMappingId() {
		return menuRoleMappingId;
	}

	public void setMenuRoleMappingId(Integer menuRoleMappingId) {
		this.menuRoleMappingId = menuRoleMappingId;
	}

	public GridMenuItemDetails getGridMenu() {
		return gridMenu;
	}

	public void setGridMenu(GridMenuItemDetails gridMenu) {
		this.gridMenu = gridMenu;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}

	
}
