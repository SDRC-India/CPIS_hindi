/**
 * 
 */
package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author 
 * Biswabhusan Pradhan
 *
 */
@Entity
@Table(name="cci_transactional_details")
public class CCITransactionDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5640264846377722228L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	Integer Id;

	@ManyToOne
	@JoinColumn(name="cci_id")
	CCIDetails cciDetails;
	
	@Column(name="total_children")
	Integer totalChildren;
	
	@Column(name="total_boys")
	Integer totalBoys;

	@Column(name="total_girls")
	Integer totalGirls;
	
	@Column(name="third_gender")
	Integer totalThirdGender;
	
	@Column(name="updated_time")
	Date updatedTime;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public CCIDetails getCciDetails() {
		return cciDetails;
	}

	public void setCciDetails(CCIDetails cciDetails) {
		this.cciDetails = cciDetails;
	}

	public Integer getTotalChildren() {
		return totalChildren;
	}

	public void setTotalChildren(Integer totalChildren) {
		this.totalChildren = totalChildren;
	}

	public Integer getTotalBoys() {
		return totalBoys;
	}

	public void setTotalBoys(Integer totalBoys) {
		this.totalBoys = totalBoys;
	}

	public Integer getTotalGirls() {
		return totalGirls;
	}

	public void setTotalGirls(Integer totalGirls) {
		this.totalGirls = totalGirls;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getTotalThirdGender() {
		return totalThirdGender;
	}

	public void setTotalThirdGender(Integer totalThirdGender) {
		this.totalThirdGender = totalThirdGender;
	}

	
	
}
