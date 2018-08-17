package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="legallyfree_for_adoption")
public class LegallyFreeForAdoption implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = -3664297447827081472L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;
		
		
	
		@Column(name="date_of_birth")
		private Date dateOfBirth;
		
		@Column(name="saa_or_cci_Id")
		private Integer saaOrCciId;

		@Column(name="order_no")
		private String orderNo;
		
		@Column(name="order_date")
		private Date orderDate;
		
		@Column(name="inquiry_report")
		private String inquiryReport;
		
		@Column(name="surrender_deed")
		private String surrenderDeed;
		
		@Column(name="declaration")
		private String declaration;
		
		@Column(name="legally_free_date")
		private Date legallyFreeDate;
		
		@Column(name="legally_free_place")
		private String legallyFreePlace;

		@OneToOne
		@JoinColumn(name="child_id",referencedColumnName = "child_id")
		private ChildDetails childId;
		
		@Column(name="created_by")
		private String createdBy;
		
		@Column(name="created_date")
		private Date createdDate;
		
		@Column(name="updated_by")
		private String updatedBy;
		
		@Column(name="updated_date")
		private Date updatedDate;
		
		@Column(name="user_ip")
		private String userIp;

		public Integer getId() {
			return id;
		}

		public ChildDetails getChildId() {
			return childId;
		}

		public void setChildId(ChildDetails childId) {
			this.childId = childId;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setSaaOrCciId(Integer saaOrCciId) {
			this.saaOrCciId = saaOrCciId;
		}

		public Integer getSaaOrCciId() {
			return saaOrCciId;
		}

		public void setSaaOrCciName(Integer saaOrCciId) {
			this.saaOrCciId = saaOrCciId;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public String getInquiryReport() {
			return inquiryReport;
		}

		public void setInquiryReport(String inquiryReport) {
			this.inquiryReport = inquiryReport;
		}

		public String getSurrenderDeed() {
			return surrenderDeed;
		}

		public void setSurrenderDeed(String surrenderDeed) {
			this.surrenderDeed = surrenderDeed;
		}

		public String getDeclaration() {
			return declaration;
		}

		public void setDeclaration(String declaration) {
			this.declaration = declaration;
		}

		public Date getLegallyFreeDate() {
			return legallyFreeDate;
		}

		public void setLegallyFreeDate(Date legallyFreeDate) {
			this.legallyFreeDate = legallyFreeDate;
		}

		public String getLegallyFreePlace() {
			return legallyFreePlace;
		}

		public void setLegallyFreePlace(String legallyFreePlace) {
			this.legallyFreePlace = legallyFreePlace;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public String getUserIp() {
			return userIp;
		}

		public void setUserIp(String userIp) {
			this.userIp = userIp;
		}

}
