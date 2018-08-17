package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="User_Details")
public class UserDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="User_Id", columnDefinition = "serial")
	private Integer userId;
	
	@Column(name="name")
	private String cwcName;
	
	@Column(name="User_Name", nullable = false)
	private String userName;
	
	@Column(name="Email", nullable = false)
	private String email;
	
	@Column(name="Password", nullable = false)
	private String password;
	
//	@ManyToOne
//	@JoinColumn(name="role")
//	private Role role;
	
//	@OneToMany(mappedBy="userDetails")
//	private List<UserFeaturePermissionMapping> userFeaturePermissionMappings;
	
//	@OneToMany(mappedBy="userId")
//	private List<GridMenuItemDetails> gridMenuItemDetails;

	
	@Column(name="Created_Date", nullable = false)
	private Timestamp createdDate;
	
	@Column(name="Live", nullable = false)
	private Boolean live;
	
	
	@Column(name="Updated_Date")
	private Timestamp updatedDate;
	
	@ManyToOne
	@JoinColumn(name="designation")
	private Designation designation;
	
	@ManyToOne
	@JoinColumn(name="areaId")
	private AreaDetails area;


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public AreaDetails getArea() {
		return area;
	}


	public void setArea(AreaDetails area) {
		this.area = area;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Timestamp getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	public Boolean getLive() {
		return live;
	}


	public void setLive(Boolean live) {
		this.live = live;
	}


	public Timestamp getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Designation getDesignation() {
		return designation;
	}


	public void setDesignation(Designation designation) {
		this.designation = designation;
	}


	public String getCwcName() {
		return cwcName;
	}


	public void setCwcName(String cwcName) {
		this.cwcName = cwcName;
	}


	public UserDetails(Integer userId) {
		super();
		this.userId = userId;
	}


	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


//	public Role getRole() {
//		return role;
//	}
//
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
	
	

}
