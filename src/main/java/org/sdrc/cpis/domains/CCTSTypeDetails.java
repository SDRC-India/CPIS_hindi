package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ccts_type_detail")
public class CCTSTypeDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126160196508641237L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Integer typeDetailsId;
	
	@Column(name="type_details_name")
	String typeDetailsName;
	
	@Column(name="type_details_name_hi")
	String typeDetailsNameHindi;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	CCTSType typeId;

	public Integer getTypeDetailsId() {
		return typeDetailsId;
	}

	public void setTypeDetailsId(Integer typeDetailsId) {
		this.typeDetailsId = typeDetailsId;
	}

	public String getTypeDetailsName() {
		return typeDetailsName;
	}

	public void setTypeDetailsName(String typeDetailsName) {
		this.typeDetailsName = typeDetailsName;
	}

	public CCTSType getTypeId() {
		return typeId;
	}

	public void setTypeId(CCTSType typeId) {
		this.typeId = typeId;
	}

	public String getTypeDetailsNameHindi() {
		return typeDetailsNameHindi;
	}

	public void setTypeDetailsNameHindi(String typeDetailsNameHindi) {
		this.typeDetailsNameHindi = typeDetailsNameHindi;
	}

	

	
}
