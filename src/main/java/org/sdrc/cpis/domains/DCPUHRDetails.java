package org.sdrc.cpis.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dcpu_hr_details")
public class DCPUHRDetails implements Serializable{
	
	private static final long serialVersionUID = 6401536479855037760L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "serial")
	private Integer id;
	
	@Column(name="accountant_contact_number")
	private String accountantContactNumber;
	
	@Column(name="accountant_email")
	private String accountantEmail;
	
	@Column(name="accountant_name")
	private String accountantName;
	
	@Column(name="accountant_sex")
	private Integer accountantSex;
	
	@Column(name="acdeo_contact_number")
	private String acdeoContactNumber;
	
	@Column(name="acdeo_email")
	private String acdeoEmail;
	
	@Column(name="acdeo_name")
	private String acdeoName;
	
	@Column(name="acdeo_sex")
	private Integer acdeoSex;
	
	@Column(name="counsellor_contact_number")	
	private String counsellorContactNumber;
	
	@Column(name="counsellor_email")	
	private String counsellorEmail;
	
	@Column(name="counsellor_name")	
	private String counsellorName;
	
	@Column(name="counsellor_sex")	
	private Integer counsellorSex;
	
	@Column(name="da_contact_number")	
	private String daContactNumber;
	
	@Column(name="da_email")
	private String daEmail;
	
	@Column(name="da_name")
	private String daName;
	
	@Column(name="da_sex")
	private Integer daSex;
	
	@Column(name="dpo_contact_number")
	private String dpoContactNumber;
	
	@Column(name="dpo_email")
	private String dpoEmail;
	
	@Column(name="dpo_name")
	private String dpoName;
	
	@Column(name="dpo_sex")
	private Integer dpoSex;
	
	@Column(name="lcpoavil_contact_number")
	private String lcpoavilContactNumber;
	
	@Column(name="lcpoavil_email")
	private String lcpoavilEmail;
	
	@Column(name="lcpoavil_name")
	private String lcpoavilName;
	
	@Column(name="lcpoavil_sex")
	private Integer lcpoavilSex;
	
	@Column(name="orw1_contact_number")
	private String orw1ContactNumber;
	
	@Column(name="orw1_email")
	private String orw1Email;
	
	@Column(name="orw1_name")
	private String orw1Name;
	
	@Column(name="orw1_sex")
	private Integer orw1Sex;
	
	@Column(name="orw2_contact_number")
	private String orw2ContactNumber;
	
	@Column(name="orw2_email")
	private String orw2Email;
	
	@Column(name="orw2_name")
	private String orw2Name;
	
	@Column(name="orw2_sex")
	private Integer orw2Sex;
	
	@Column(name="orw3_contact_number")
	private String orw3ContactNumber;
	
	@Column(name="orw3_email")
	private String orw3Email;
	
	@Column(name="orw3_name")
	private String orw3Name;
	
	@Column(name="orw3_sex")
	private Integer orw3Sex;
	
	@Column(name="orw4_contact_number")
	private String orw4ContactNumber;
	
	@Column(name="orw4_email")
	private String orw4Email;
	
	@Column(name="orw4_name")
	private String orw4Name;
	
	@Column(name="orw4_sex")
	private Integer orw4Sex;
	
	@Column(name="orw5_contact_number")
	private String orw5ContactNumber;
	
	@Column(name="orw5_email")
	private String orw5Email;
	
	@Column(name="orw5_name")
	private String orw5Name;
	
	@Column(name="orw5_sex")
	private Integer orw5Sex;
	
	@Column(name="other_staff_contact_number")
	private String otherStaffContactNumber;
	
	@Column(name="other_staff_email")
	private String otherStaffEmail;
	
	@Column(name="other_staff_name")
	private String otherStaffName;
	
	@Column(name="other_staff_sex")
	private Integer otherStaffSex;
	
	@Column(name="poic_contact_number")
	private String poicContactNumber;
	
	@Column(name="poic_email")
	private String poicEmail;
	
	@Column(name="poic_name")
	private String poicName;
	
	@Column(name="poic_sex")
	private Integer poicSex;
	
	@Column(name="ponic_contact_number")
	private String ponicContactNumber;
	
	@Column(name="ponic_email")
	private String ponicEmail;
	
	@Column(name="ponic_name")
	private String ponicName;
	
	@Column(name="ponic_sex")
	private Integer ponicSex;
	
	@Column(name="sw1_contact_number")
	private String sw1ContactNumber;
	
	@Column(name="sw1_email")
	private String sw1Email;
	
	@Column(name="sw1_name")
	private String sw1Name;
	
	@Column(name="sw1_sex")
	private Integer sw1Sex;
	
	@Column(name="sw2_contact_number")
	private String sw2ContactNumber;
	
	@Column(name="sw2_email")
	private String sw2Email;
	
	@Column(name="sw2_name")
	private String sw2Name;
	
	@Column(name="sw2_sex")
	private Integer sw2Sex;
	
	@Column(name="dcpu_area_id")
	private Integer dcpuAreaId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountantContactNumber() {
		return accountantContactNumber;
	}

	public void setAccountantContactNumber(String accountantContactNumber) {
		this.accountantContactNumber = accountantContactNumber;
	}

	public String getAccountantEmail() {
		return accountantEmail;
	}

	public void setAccountantEmail(String accountantEmail) {
		this.accountantEmail = accountantEmail;
	}

	public String getAccountantName() {
		return accountantName;
	}

	public void setAccountantName(String accountantName) {
		this.accountantName = accountantName;
	}

	public Integer getAccountantSex() {
		return accountantSex;
	}

	public void setAccountantSex(Integer accountantSex) {
		this.accountantSex = accountantSex;
	}

	public String getAcdeoContactNumber() {
		return acdeoContactNumber;
	}

	public void setAcdeoContactNumber(String acdeoContactNumber) {
		this.acdeoContactNumber = acdeoContactNumber;
	}

	public String getAcdeoEmail() {
		return acdeoEmail;
	}

	public void setAcdeoEmail(String acdeoEmail) {
		this.acdeoEmail = acdeoEmail;
	}

	public String getAcdeoName() {
		return acdeoName;
	}

	public void setAcdeoName(String acdeoName) {
		this.acdeoName = acdeoName;
	}

	public Integer getAcdeoSex() {
		return acdeoSex;
	}

	public void setAcdeoSex(Integer acdeoSex) {
		this.acdeoSex = acdeoSex;
	}

	public String getCounsellorContactNumber() {
		return counsellorContactNumber;
	}

	public void setCounsellorContactNumber(String counsellorContactNumber) {
		this.counsellorContactNumber = counsellorContactNumber;
	}

	public String getCounsellorEmail() {
		return counsellorEmail;
	}

	public void setCounsellorEmail(String counsellorEmail) {
		this.counsellorEmail = counsellorEmail;
	}

	public String getCounsellorName() {
		return counsellorName;
	}

	public void setCounsellorName(String counsellorName) {
		this.counsellorName = counsellorName;
	}

	public Integer getCounsellorSex() {
		return counsellorSex;
	}

	public void setCounsellorSex(Integer counsellorSex) {
		this.counsellorSex = counsellorSex;
	}

	public String getDaContactNumber() {
		return daContactNumber;
	}

	public void setDaContactNumber(String daContactNumber) {
		this.daContactNumber = daContactNumber;
	}

	public String getDaEmail() {
		return daEmail;
	}

	public void setDaEmail(String daEmail) {
		this.daEmail = daEmail;
	}

	public String getDaName() {
		return daName;
	}

	public void setDaName(String daName) {
		this.daName = daName;
	}

	public Integer getDaSex() {
		return daSex;
	}

	public void setDaSex(Integer daSex) {
		this.daSex = daSex;
	}

	public String getDpoContactNumber() {
		return dpoContactNumber;
	}

	public void setDpoContactNumber(String dpoContactNumber) {
		this.dpoContactNumber = dpoContactNumber;
	}

	public String getDpoEmail() {
		return dpoEmail;
	}

	public void setDpoEmail(String dpoEmail) {
		this.dpoEmail = dpoEmail;
	}

	public String getDpoName() {
		return dpoName;
	}

	public void setDpoName(String dpoName) {
		this.dpoName = dpoName;
	}

	public Integer getDpoSex() {
		return dpoSex;
	}

	public void setDpoSex(Integer dpoSex) {
		this.dpoSex = dpoSex;
	}

	public String getLcpoavilContactNumber() {
		return lcpoavilContactNumber;
	}

	public void setLcpoavilContactNumber(String lcpoavilContactNumber) {
		this.lcpoavilContactNumber = lcpoavilContactNumber;
	}

	public String getLcpoavilEmail() {
		return lcpoavilEmail;
	}

	public void setLcpoavilEmail(String lcpoavilEmail) {
		this.lcpoavilEmail = lcpoavilEmail;
	}

	public String getLcpoavilName() {
		return lcpoavilName;
	}

	public void setLcpoavilName(String lcpoavilName) {
		this.lcpoavilName = lcpoavilName;
	}

	public Integer getLcpoavilSex() {
		return lcpoavilSex;
	}

	public void setLcpoavilSex(Integer lcpoavilSex) {
		this.lcpoavilSex = lcpoavilSex;
	}

	public String getOrw1ContactNumber() {
		return orw1ContactNumber;
	}

	public void setOrw1ContactNumber(String orw1ContactNumber) {
		this.orw1ContactNumber = orw1ContactNumber;
	}

	public String getOrw1Email() {
		return orw1Email;
	}

	public void setOrw1Email(String orw1Email) {
		this.orw1Email = orw1Email;
	}

	public String getOrw1Name() {
		return orw1Name;
	}

	public void setOrw1Name(String orw1Name) {
		this.orw1Name = orw1Name;
	}

	public Integer getOrw1Sex() {
		return orw1Sex;
	}

	public void setOrw1Sex(Integer orw1Sex) {
		this.orw1Sex = orw1Sex;
	}

	public String getOrw2ContactNumber() {
		return orw2ContactNumber;
	}

	public void setOrw2ContactNumber(String orw2ContactNumber) {
		this.orw2ContactNumber = orw2ContactNumber;
	}

	public String getOrw2Email() {
		return orw2Email;
	}

	public void setOrw2Email(String orw2Email) {
		this.orw2Email = orw2Email;
	}

	public String getOrw2Name() {
		return orw2Name;
	}

	public void setOrw2Name(String orw2Name) {
		this.orw2Name = orw2Name;
	}

	public Integer getOrw2Sex() {
		return orw2Sex;
	}

	public void setOrw2Sex(Integer orw2Sex) {
		this.orw2Sex = orw2Sex;
	}

	public String getOrw3ContactNumber() {
		return orw3ContactNumber;
	}

	public void setOrw3ContactNumber(String orw3ContactNumber) {
		this.orw3ContactNumber = orw3ContactNumber;
	}

	public String getOrw3Email() {
		return orw3Email;
	}

	public void setOrw3Email(String orw3Email) {
		this.orw3Email = orw3Email;
	}

	public String getOrw3Name() {
		return orw3Name;
	}

	public void setOrw3Name(String orw3Name) {
		this.orw3Name = orw3Name;
	}

	public Integer getOrw3Sex() {
		return orw3Sex;
	}

	public void setOrw3Sex(Integer orw3Sex) {
		this.orw3Sex = orw3Sex;
	}

	public String getOrw4ContactNumber() {
		return orw4ContactNumber;
	}

	public void setOrw4ContactNumber(String orw4ContactNumber) {
		this.orw4ContactNumber = orw4ContactNumber;
	}

	public String getOrw4Email() {
		return orw4Email;
	}

	public void setOrw4Email(String orw4Email) {
		this.orw4Email = orw4Email;
	}

	public String getOrw4Name() {
		return orw4Name;
	}

	public void setOrw4Name(String orw4Name) {
		this.orw4Name = orw4Name;
	}

	public Integer getOrw4Sex() {
		return orw4Sex;
	}

	public void setOrw4Sex(Integer orw4Sex) {
		this.orw4Sex = orw4Sex;
	}

	public String getOrw5ContactNumber() {
		return orw5ContactNumber;
	}

	public void setOrw5ContactNumber(String orw5ContactNumber) {
		this.orw5ContactNumber = orw5ContactNumber;
	}

	public String getOrw5Email() {
		return orw5Email;
	}

	public void setOrw5Email(String orw5Email) {
		this.orw5Email = orw5Email;
	}

	public String getOrw5Name() {
		return orw5Name;
	}

	public void setOrw5Name(String orw5Name) {
		this.orw5Name = orw5Name;
	}

	public Integer getOrw5Sex() {
		return orw5Sex;
	}

	public void setOrw5Sex(Integer orw5Sex) {
		this.orw5Sex = orw5Sex;
	}

	public String getOtherStaffContactNumber() {
		return otherStaffContactNumber;
	}

	public void setOtherStaffContactNumber(String otherStaffContactNumber) {
		this.otherStaffContactNumber = otherStaffContactNumber;
	}

	public String getOtherStaffEmail() {
		return otherStaffEmail;
	}

	public void setOtherStaffEmail(String otherStaffEmail) {
		this.otherStaffEmail = otherStaffEmail;
	}

	public String getOtherStaffName() {
		return otherStaffName;
	}

	public void setOtherStaffName(String otherStaffName) {
		this.otherStaffName = otherStaffName;
	}

	public Integer getOtherStaffSex() {
		return otherStaffSex;
	}

	public void setOtherStaffSex(Integer otherStaffSex) {
		this.otherStaffSex = otherStaffSex;
	}

	public String getPoicContactNumber() {
		return poicContactNumber;
	}

	public void setPoicContactNumber(String poicContactNumber) {
		this.poicContactNumber = poicContactNumber;
	}

	public String getPoicEmail() {
		return poicEmail;
	}

	public void setPoicEmail(String poicEmail) {
		this.poicEmail = poicEmail;
	}

	public String getPoicName() {
		return poicName;
	}

	public void setPoicName(String poicName) {
		this.poicName = poicName;
	}

	public Integer getPoicSex() {
		return poicSex;
	}

	public void setPoicSex(Integer poicSex) {
		this.poicSex = poicSex;
	}

	public String getPonicContactNumber() {
		return ponicContactNumber;
	}

	public void setPonicContactNumber(String ponicContactNumber) {
		this.ponicContactNumber = ponicContactNumber;
	}

	public String getPonicEmail() {
		return ponicEmail;
	}

	public void setPonicEmail(String ponicEmail) {
		this.ponicEmail = ponicEmail;
	}

	public String getPonicName() {
		return ponicName;
	}

	public void setPonicName(String ponicName) {
		this.ponicName = ponicName;
	}

	public Integer getPonicSex() {
		return ponicSex;
	}

	public void setPonicSex(Integer ponicSex) {
		this.ponicSex = ponicSex;
	}

	public String getSw1ContactNumber() {
		return sw1ContactNumber;
	}

	public void setSw1ContactNumber(String sw1ContactNumber) {
		this.sw1ContactNumber = sw1ContactNumber;
	}

	public String getSw1Email() {
		return sw1Email;
	}

	public void setSw1Email(String sw1Email) {
		this.sw1Email = sw1Email;
	}

	public String getSw1Name() {
		return sw1Name;
	}

	public void setSw1Name(String sw1Name) {
		this.sw1Name = sw1Name;
	}

	public Integer getSw1Sex() {
		return sw1Sex;
	}

	public void setSw1Sex(Integer sw1Sex) {
		this.sw1Sex = sw1Sex;
	}

	public String getSw2ContactNumber() {
		return sw2ContactNumber;
	}

	public void setSw2ContactNumber(String sw2ContactNumber) {
		this.sw2ContactNumber = sw2ContactNumber;
	}

	public String getSw2Email() {
		return sw2Email;
	}

	public void setSw2Email(String sw2Email) {
		this.sw2Email = sw2Email;
	}

	public String getSw2Name() {
		return sw2Name;
	}

	public void setSw2Name(String sw2Name) {
		this.sw2Name = sw2Name;
	}

	public Integer getSw2Sex() {
		return sw2Sex;
	}

	public void setSw2Sex(Integer sw2Sex) {
		this.sw2Sex = sw2Sex;
	}

	public Integer getDcpuAreaId() {
		return dcpuAreaId;
	}

	public void setDcpuAreaId(Integer dcpuAreaId) {
		this.dcpuAreaId = dcpuAreaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
