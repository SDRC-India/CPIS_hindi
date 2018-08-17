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
@Table(name="CCI_TypeDetails")
public class CCITypeDetails implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7421814551913985877L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	Integer typeId;

	@Column(name="Name")
	String typeName;

	@Column(name="Description")
	String description;
	
	@OneToMany(mappedBy="cciTypeDetails")
	private List<CCIDetails> cciDetails;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	
}
