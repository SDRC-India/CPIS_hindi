package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.List;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.ConstitutionOfBCPC;
import org.sdrc.cpis.models.ConstitutionOfBCPCModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.ConstitutionofBCPCRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionofBCPCServiceImpl implements ConstitutionofBCPCService {

	@Autowired
	ConstitutionofBCPCRepository constitutionofBCPCRepository;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public void saveConstitutionofBCPC(ConstitutionOfBCPCModel constitutionOfBCPCModel) {
		
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ConstitutionOfBCPC constitutionofBCPC = new ConstitutionOfBCPC();
		
		constitutionofBCPC.setConstitutionDate(constitutionOfBCPCModel.getConstitutionDate());
		constitutionofBCPC.setBdoContact(constitutionOfBCPCModel.getBdoContact());
		constitutionofBCPC.setBdoEmail(constitutionOfBCPCModel.getBdoEmail());
		constitutionofBCPC.setBdoName(constitutionOfBCPCModel.getBdoName());
		constitutionofBCPC.setBdoSex(null==constitutionOfBCPCModel.getBdoSex()?null:constitutionOfBCPCModel.getBdoSex().getId());
		constitutionofBCPC.setBeoContact(constitutionOfBCPCModel.getBeoContact());
		constitutionofBCPC.setBeoEmail(constitutionOfBCPCModel.getBeoEmail());
		constitutionofBCPC.setBeoName(constitutionOfBCPCModel.getBeoName());
		constitutionofBCPC.setBeoSex(null==constitutionOfBCPCModel.getBeoSex()?null:constitutionOfBCPCModel.getBeoSex().getId());
		constitutionofBCPC.setBlockChairmanContact(constitutionOfBCPCModel.getBlockChairmanContact());
		constitutionofBCPC.setBlockChairmanEmail(constitutionOfBCPCModel.getBlockChairmanEmail());
		constitutionofBCPC.setBlockChairmanName(constitutionOfBCPCModel.getBlockChairmanName());
		constitutionofBCPC.setBlockChairmanSex(null==constitutionOfBCPCModel.getBlockChairmanSex()?null:constitutionOfBCPCModel.getBlockChairmanSex().getId());
		constitutionofBCPC.setCmContact(constitutionOfBCPCModel.getCmContact());
		constitutionofBCPC.setCmEmail(constitutionOfBCPCModel.getCmEmail());
		constitutionofBCPC.setCmName(constitutionOfBCPCModel.getCmName());
		constitutionofBCPC.setCmSex(null==constitutionOfBCPCModel.getCmSex()?null:constitutionOfBCPCModel.getCmSex().getId());
		constitutionofBCPC.setCsmContact(constitutionOfBCPCModel.getCsmContact());
		constitutionofBCPC.setCsmEmail(constitutionOfBCPCModel.getCsmEmail());
		constitutionofBCPC.setCsmName(constitutionOfBCPCModel.getCsmName());
		constitutionofBCPC.setCsmSex(null==constitutionOfBCPCModel.getCsmSex()?null:constitutionOfBCPCModel.getCsmSex().getId());
		constitutionofBCPC.setDcpuMemberContact(constitutionOfBCPCModel.getDcpuMemberContact());
		constitutionofBCPC.setDcpuMemberEmail(constitutionOfBCPCModel.getDcpuMemberEmail());
		constitutionofBCPC.setDcpuMemberName(constitutionOfBCPCModel.getDcpuMemberName());
		constitutionofBCPC.setDcpuMemberSex(null==constitutionOfBCPCModel.getDcpuMemberSex()?null:constitutionOfBCPCModel.getDcpuMemberSex().getId());
		constitutionofBCPC.setIcdsFunctionaryContact(constitutionOfBCPCModel.getIcdsFunctionaryContact());
		constitutionofBCPC.setIcdsFunctionaryEmail(constitutionOfBCPCModel.getIcdsFunctionaryEmail());
		constitutionofBCPC.setIcdsFunctionaryName(constitutionOfBCPCModel.getIcdsFunctionaryName());
		constitutionofBCPC.setIcdsFunctionarySex(null==constitutionOfBCPCModel.getIcdsFunctionarySex()?null:constitutionOfBCPCModel.getIcdsFunctionarySex().getId());
		constitutionofBCPC.setOichcContact(constitutionOfBCPCModel.getOichcContact());
		constitutionofBCPC.setOichcEmail(constitutionOfBCPCModel.getOichcEmail());
		constitutionofBCPC.setOichcName(constitutionOfBCPCModel.getOichcName());
		constitutionofBCPC.setOichcSex(null==constitutionOfBCPCModel.getOichcSex()?null:constitutionOfBCPCModel.getOichcSex().getId());
		constitutionofBCPC.setVlgLvlCPCContact(constitutionOfBCPCModel.getVlgLvlCPCContact());
		constitutionofBCPC.setVlgLvlCPCEmail(constitutionOfBCPCModel.getVlgLvlCPCEmail());
		constitutionofBCPC.setVlgLvlCPCName(constitutionOfBCPCModel.getVlgLvlCPCName());
		constitutionofBCPC.setVlgLvlCPCSex(null==constitutionOfBCPCModel.getVlgLvlCPCSex()?null:constitutionOfBCPCModel.getVlgLvlCPCSex().getId());
		
		constitutionofBCPC.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		constitutionofBCPC.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		constitutionofBCPC.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		constitutionofBCPC.setBlockId(constitutionOfBCPCModel.getBlockId() == null ? null : constitutionOfBCPCModel.getBlockId());
		constitutionofBCPC.setGpcpcFormed(constitutionOfBCPCModel.getGpcpcFormed() == null ? null : constitutionOfBCPCModel.getGpcpcFormed());
		constitutionofBCPC.setVcpcFormed(constitutionOfBCPCModel.getVcpcFormed() == null ? null : constitutionOfBCPCModel.getVcpcFormed());
		
		constitutionofBCPCRepository.save(constitutionofBCPC);
	}

	@Override
	public List<ValueObject> getBlockList() {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		List<ValueObject> listValueObjects = new ArrayList<ValueObject>();
		List<AreaDetails> listOfBlock = areaRepository.findByParentAreaAreaId(userDetailModel.getAreaId());
		for (AreaDetails areaDetails : listOfBlock) {
			ValueObject valueObject = new ValueObject();
			valueObject.setId(areaDetails.getAreaId());
			valueObject.setName(areaDetails.getAreaName());
			listValueObjects.add(valueObject);
		}
		return listValueObjects;
	}
}
