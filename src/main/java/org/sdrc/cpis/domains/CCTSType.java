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
@Table(name="ccts_type")
public class CCTSType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5419569723510645474L;

	@Id
	@Column(name="id", columnDefinition = "serial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer typeId;
	
	@Column(name="type_name")
	String typeName;
	
	@Column(name="description")
	String description;
	
	@OneToMany(mappedBy="typeId")
	List<CCITypeDetails> cctsTypeDetails;

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
