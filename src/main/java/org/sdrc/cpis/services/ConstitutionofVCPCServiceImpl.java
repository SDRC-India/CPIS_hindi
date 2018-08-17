package org.sdrc.cpis.services;

import org.sdrc.cpis.domains.ConstitutionOfVCPC;
import org.sdrc.cpis.models.ConstitutionOfVCPCModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.ConstitutionofVCPCRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionofVCPCServiceImpl implements ConstitutionofVCPCService {
	
	@Autowired
	ConstitutionofVCPCRepository constitutionofVCPCRepository;

	@Autowired
	private StateManager stateManager;
	
	@Override
	public void saveConstitutionofVCPC(ConstitutionOfVCPCModel constitutionOfVCPCModel) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ConstitutionOfVCPC constitutionOfVCPC = new ConstitutionOfVCPC();
		
		constitutionOfVCPC.setConstitutionDate(constitutionOfVCPCModel.getConstitutionDate());
		constitutionOfVCPC.setAnmContact(constitutionOfVCPCModel.getAnmContact());
		constitutionOfVCPC.setAnmEmail(constitutionOfVCPCModel.getAnmEmail());
		constitutionOfVCPC.setAnmName(constitutionOfVCPCModel.getAnmName());
		constitutionOfVCPC.setAwContact(constitutionOfVCPCModel.getAwContact());
		constitutionOfVCPC.setAwEmail(constitutionOfVCPCModel.getAwEmail());
		constitutionOfVCPC.setAwName(constitutionOfVCPCModel.getAwName());
		constitutionOfVCPC.setCrOneContact(constitutionOfVCPCModel.getCrOneContact());
		constitutionOfVCPC.setCrOneEmail(constitutionOfVCPCModel.getCrOneEmail());
		constitutionOfVCPC.setCrOneName(constitutionOfVCPCModel.getCrOneName());
		constitutionOfVCPC.setCrOneSex(null==constitutionOfVCPCModel.getCrOneSex()?null:constitutionOfVCPCModel.getCrOneSex().getId());
		constitutionOfVCPC.setCrTwoContact(constitutionOfVCPCModel.getCrTwoContact());
		constitutionOfVCPC.setCrTwoEmail(constitutionOfVCPCModel.getCrTwoEmail());
		constitutionOfVCPC.setCrTwoName(constitutionOfVCPCModel.getCrTwoName());
		constitutionOfVCPC.setCrTwoSex(null==constitutionOfVCPCModel.getCrTwoSex()?null:constitutionOfVCPCModel.getCrTwoSex().getId());
		constitutionOfVCPC.setDcpuMemberContact(constitutionOfVCPCModel.getDcpuMemberContact());
		constitutionOfVCPC.setDcpuMemberEmail(constitutionOfVCPCModel.getDcpuMemberEmail());
		constitutionOfVCPC.setDcpuMemberName(constitutionOfVCPCModel.getDcpuMemberName());
		constitutionOfVCPC.setDcpuMemberSex(null==constitutionOfVCPCModel.getDcpuMemberSex()?null:constitutionOfVCPCModel.getDcpuMemberSex().getId());
		constitutionOfVCPC.setSchoolTeacherContact(constitutionOfVCPCModel.getSchoolTeacherContact());
		constitutionOfVCPC.setSchoolTeacherEmail(constitutionOfVCPCModel.getSchoolTeacherEmail());
		constitutionOfVCPC.setSchoolTeacherName(constitutionOfVCPCModel.getSchoolTeacherName());
		constitutionOfVCPC.setSchoolTeacherSex(null==constitutionOfVCPCModel.getSchoolTeacherSex()?null:constitutionOfVCPCModel.getSchoolTeacherSex().getId());
		constitutionOfVCPC.setVlgHeadManContact(constitutionOfVCPCModel.getVlgHeadManContact());
		constitutionOfVCPC.setVlgHeadManEmail(constitutionOfVCPCModel.getVlgHeadManEmail());
		constitutionOfVCPC.setVlgHeadManName(constitutionOfVCPCModel.getVlgHeadManName());
		constitutionOfVCPC.setVlgHeadManSex(null==constitutionOfVCPCModel.getVlgHeadManSex()?null:constitutionOfVCPCModel.getVlgHeadManSex().getId());
		constitutionOfVCPC.setVlgMemberOneContact(constitutionOfVCPCModel.getVlgMemberOneContact());
		constitutionOfVCPC.setVlgMemberOneEmail(constitutionOfVCPCModel.getVlgMemberOneEmail());
		constitutionOfVCPC.setVlgMemberOneName(constitutionOfVCPCModel.getVlgMemberOneName());
		constitutionOfVCPC.setVlgMemberOneSex(null==constitutionOfVCPCModel.getVlgMemberOneSex()?null:constitutionOfVCPCModel.getVlgMemberOneSex().getId());
		constitutionOfVCPC.setVlgMemberThreeContact(constitutionOfVCPCModel.getVlgMemberThreeContact());
		constitutionOfVCPC.setVlgMemberThreeEmail(constitutionOfVCPCModel.getVlgMemberThreeEmail());
		constitutionOfVCPC.setVlgMemberThreeName(constitutionOfVCPCModel.getVlgMemberThreeName());
		constitutionOfVCPC.setVlgMemberThreeSex(null==constitutionOfVCPCModel.getVlgMemberThreeSex()?null:constitutionOfVCPCModel.getVlgMemberThreeSex().getId());
		constitutionOfVCPC.setVlgMemberTwoContact(constitutionOfVCPCModel.getVlgMemberTwoContact());
		constitutionOfVCPC.setVlgMemberTwoEmail(constitutionOfVCPCModel.getVlgMemberTwoEmail());
		constitutionOfVCPC.setVlgMemberTwoName(constitutionOfVCPCModel.getVlgMemberTwoName());
		constitutionOfVCPC.setVlgMemberTwoSex(null==constitutionOfVCPCModel.getVlgMemberTwoSex()?null:constitutionOfVCPCModel.getVlgMemberTwoSex().getId());
		
		constitutionOfVCPC.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		constitutionOfVCPC.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		constitutionOfVCPC.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		
		constitutionofVCPCRepository.save(constitutionOfVCPC);
	}

}
