package org.sdrc.cpis.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cci_user_mapping")
public class CciUserMapping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cci_user_id", columnDefinition = "serial")
	private Integer id;
	
	@Column(name="cci_id")
	private Integer cciId;
	
	@Column(name="user_id")
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCciId() {
		return cciId;
	}

	public void setCciId(Integer cciId) {
		this.cciId = cciId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
