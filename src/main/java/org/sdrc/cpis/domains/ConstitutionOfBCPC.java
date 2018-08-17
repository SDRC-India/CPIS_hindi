package org.sdrc.cpis.domains;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="constitution_of_bcpc")
public class ConstitutionOfBCPC implements Serializable{

	private static final long serialVersionUID = 9204837232948921194L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "seriel")
	private Integer id;
	
	@Column(name="constitution_date")
	private Date constitutionDate;
	
	@Column(name="block_chairman_name")
	private String blockChairmanName;
	
	@Column(name="block_chairman_sex")
	private Integer blockChairmanSex;
	
	@Column(name="block_chairman_contact")
	private String blockChairmanContact;
	
	@Column(name="block_chairman_email")
	private String blockChairmanEmail;
	
	@Column(name="bdo_name")
	private String bdoName;
	
	@Column(name="bdo_sex")
	private Integer bdoSex;
	
	@Column(name="bdo_contact")
	private String bdoContact;
	
	@Column(name="bdo_email")
	private String bdoEmail;
	
	@Column(name="dcpu_member_name")
	private String dcpuMemberName;
	
	@Column(name="dcpu_member_sex")
	private Integer dcpuMemberSex;
	
	@Column(name="dcpu_member_contact")
	private String dcpuMemberContact;
	
	@Column(name="dcpu_member_email")
	private String dcpuMemberEmail;
	
	@Column(name="icds_functionary_name")
	private String  icdsFunctionaryName;
	
	@Column(name="icds_functionary_sex")
	private Integer icdsFunctionarySex;
	
	@Column(name="icds_functionary_contact")
	private String icdsFunctionaryContact;
	
	@Column(name="icds_functionary_email")
	private String icdsFunctionaryEmail;
	
	@Column(name="beo_name")
	private String beoName;
	
	@Column(name="beo_sex")
	private Integer beoSex;
	
	@Column(name="beo_contact")
	private String beoContact;
	
	@Column(name="beo_email")
	private String beoEmail;
	
	@Column(name="oichc_name")
	private String oichcName;
	
	@Column(name="oichc_sex")
	private Integer oichcSex;
	
	@Column(name="oichc_contact")
	private String oichcContact;
	
	@Column(name="oichc_email")
	private String oichcEmail;
	
	@Column(name="vlg_lvl_cpc_name")
	private String vlgLvlCPCName;
	
	@Column(name="vlg_lvl_cpc_sex")
	private Integer vlgLvlCPCSex;
	
	@Column(name="vlg_lvl_cpc_contact")
	private String vlgLvlCPCContact;
	
	@Column(name="vlg_lvl_cpc_email")
	private String vlgLvlCPCEmail;
	
	@Column(name="csm_name")
	private String csmName;
	
	@Column(name="csm_sex")
	private Integer csmSex;
	
	@Column(name="csm_contact")
	private String csmContact;
	
	@Column(name="csm_email")
	private String csmEmail;
	
	@Column(name="cm_name")
	private String cmName;
	
	@Column(name="cm_sex")
	private Integer cmSex;
	
	@Column(name="cm_contact")
	private String cmContact;
	
	@Column(name="cm_email")
	private String cmEmail;
	
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
	
	@Column(name="block_id")
	private Integer blockId;
	
	@Column(name="gpcpc_formed")
	private Integer gpcpcFormed;
	
	@Column(name="vcpc_formed")
	private Integer vcpcFormed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getConstitutionDate() {
		return constitutionDate;
	}

	public void setConstitutionDate(Date constitutionDate) {
		this.constitutionDate = constitutionDate;
	}

	public String getBlockChairmanName() {
		return blockChairmanName;
	}

	public void setBlockChairmanName(String blockChairmanName) {
		this.blockChairmanName = blockChairmanName;
	}

	public Integer getBlockChairmanSex() {
		return blockChairmanSex;
	}

	public void setBlockChairmanSex(Integer blockChairmanSex) {
		this.blockChairmanSex = blockChairmanSex;
	}

	public String getBlockChairmanContact() {
		return blockChairmanContact;
	}

	public void setBlockChairmanContact(String blockChairmanContact) {
		this.blockChairmanContact = blockChairmanContact;
	}

	public String getBlockChairmanEmail() {
		return blockChairmanEmail;
	}

	public void setBlockChairmanEmail(String blockChairmanEmail) {
		this.blockChairmanEmail = blockChairmanEmail;
	}

	public String getBdoName() {
		return bdoName;
	}

	public void setBdoName(String bdoName) {
		this.bdoName = bdoName;
	}

	public Integer getBdoSex() {
		return bdoSex;
	}

	public void setBdoSex(Integer bdoSex) {
		this.bdoSex = bdoSex;
	}

	public String getBdoContact() {
		return bdoContact;
	}

	public void setBdoContact(String bdoContact) {
		this.bdoContact = bdoContact;
	}

	public String getBdoEmail() {
		return bdoEmail;
	}

	public void setBdoEmail(String bdoEmail) {
		this.bdoEmail = bdoEmail;
	}

	public String getDcpuMemberName() {
		return dcpuMemberName;
	}

	public void setDcpuMemberName(String dcpuMemberName) {
		this.dcpuMemberName = dcpuMemberName;
	}

	public Integer getDcpuMemberSex() {
		return dcpuMemberSex;
	}

	public void setDcpuMemberSex(Integer dcpuMemberSex) {
		this.dcpuMemberSex = dcpuMemberSex;
	}

	public String getDcpuMemberContact() {
		return dcpuMemberContact;
	}

	public void setDcpuMemberContact(String dcpuMemberContact) {
		this.dcpuMemberContact = dcpuMemberContact;
	}

	public String getDcpuMemberEmail() {
		return dcpuMemberEmail;
	}

	public void setDcpuMemberEmail(String dcpuMemberEmail) {
		this.dcpuMemberEmail = dcpuMemberEmail;
	}

	public String getIcdsFunctionaryName() {
		return icdsFunctionaryName;
	}

	public void setIcdsFunctionaryName(String icdsFunctionaryName) {
		this.icdsFunctionaryName = icdsFunctionaryName;
	}

	public Integer getIcdsFunctionarySex() {
		return icdsFunctionarySex;
	}

	public void setIcdsFunctionarySex(Integer icdsFunctionarySex) {
		this.icdsFunctionarySex = icdsFunctionarySex;
	}

	public String getIcdsFunctionaryContact() {
		return icdsFunctionaryContact;
	}

	public void setIcdsFunctionaryContact(String icdsFunctionaryContact) {
		this.icdsFunctionaryContact = icdsFunctionaryContact;
	}

	public String getIcdsFunctionaryEmail() {
		return icdsFunctionaryEmail;
	}

	public void setIcdsFunctionaryEmail(String icdsFunctionaryEmail) {
		this.icdsFunctionaryEmail = icdsFunctionaryEmail;
	}

	public String getBeoName() {
		return beoName;
	}

	public void setBeoName(String beoName) {
		this.beoName = beoName;
	}

	public Integer getBeoSex() {
		return beoSex;
	}

	public void setBeoSex(Integer beoSex) {
		this.beoSex = beoSex;
	}

	public String getBeoContact() {
		return beoContact;
	}

	public void setBeoContact(String beoContact) {
		this.beoContact = beoContact;
	}

	public String getBeoEmail() {
		return beoEmail;
	}

	public void setBeoEmail(String beoEmail) {
		this.beoEmail = beoEmail;
	}

	public String getOichcName() {
		return oichcName;
	}

	public void setOichcName(String oichcName) {
		this.oichcName = oichcName;
	}

	public Integer getOichcSex() {
		return oichcSex;
	}

	public void setOichcSex(Integer oichcSex) {
		this.oichcSex = oichcSex;
	}

	public String getOichcContact() {
		return oichcContact;
	}

	public void setOichcContact(String oichcContact) {
		this.oichcContact = oichcContact;
	}

	public String getOichcEmail() {
		return oichcEmail;
	}

	public void setOichcEmail(String oichcEmail) {
		this.oichcEmail = oichcEmail;
	}

	public String getVlgLvlCPCName() {
		return vlgLvlCPCName;
	}

	public void setVlgLvlCPCName(String vlgLvlCPCName) {
		this.vlgLvlCPCName = vlgLvlCPCName;
	}

	public Integer getVlgLvlCPCSex() {
		return vlgLvlCPCSex;
	}

	public void setVlgLvlCPCSex(Integer vlgLvlCPCSex) {
		this.vlgLvlCPCSex = vlgLvlCPCSex;
	}

	public String getVlgLvlCPCContact() {
		return vlgLvlCPCContact;
	}

	public void setVlgLvlCPCContact(String vlgLvlCPCContact) {
		this.vlgLvlCPCContact = vlgLvlCPCContact;
	}

	public String getVlgLvlCPCEmail() {
		return vlgLvlCPCEmail;
	}

	public void setVlgLvlCPCEmail(String vlgLvlCPCEmail) {
		this.vlgLvlCPCEmail = vlgLvlCPCEmail;
	}

	public String getCsmName() {
		return csmName;
	}

	public void setCsmName(String csmName) {
		this.csmName = csmName;
	}

	public Integer getCsmSex() {
		return csmSex;
	}

	public void setCsmSex(Integer csmSex) {
		this.csmSex = csmSex;
	}

	public String getCsmContact() {
		return csmContact;
	}

	public void setCsmContact(String csmContact) {
		this.csmContact = csmContact;
	}

	public String getCsmEmail() {
		return csmEmail;
	}

	public void setCsmEmail(String csmEmail) {
		this.csmEmail = csmEmail;
	}

	public String getCmName() {
		return cmName;
	}

	public void setCmName(String cmName) {
		this.cmName = cmName;
	}

	public Integer getCmSex() {
		return cmSex;
	}

	public void setCmSex(Integer cmSex) {
		this.cmSex = cmSex;
	}

	public String getCmContact() {
		return cmContact;
	}

	public void setCmContact(String cmContact) {
		this.cmContact = cmContact;
	}

	public String getCmEmail() {
		return cmEmail;
	}

	public void setCmEmail(String cmEmail) {
		this.cmEmail = cmEmail;
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

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Integer getGpcpcFormed() {
		return gpcpcFormed;
	}

	public void setGpcpcFormed(Integer gpcpcFormed) {
		this.gpcpcFormed = gpcpcFormed;
	}

	public Integer getVcpcFormed() {
		return vcpcFormed;
	}

	public void setVcpcFormed(Integer vcpcFormed) {
		this.vcpcFormed = vcpcFormed;
	}
	
}
