package org.sdrc.cpis.services;

import org.sdrc.cpis.domains.ConstitutionOfCWC;
import org.sdrc.cpis.models.ConstitutionOfCWCModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.ConstitutionofCWCRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionofCWCServiceImpl implements ConstitutionofCWCService {

	@Autowired
	ConstitutionofCWCRepository constitutionofCWCRepository;
	
	@Autowired
	private StateManager stateManager;
	
	@Override
	public void saveConstitutionofCWC(ConstitutionOfCWCModel constitutionOfCWCModel) {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		ConstitutionOfCWC constitutionOfCWC = new ConstitutionOfCWC();
		
		constitutionOfCWC.setChairpersonContact(null==constitutionOfCWCModel.getChairpersonContact()?null:constitutionOfCWCModel.getChairpersonContact());
		constitutionOfCWC.setChairpersonEmail(null==constitutionOfCWCModel.getChairpersonEmail()?null:constitutionOfCWCModel.getChairpersonEmail());
		constitutionOfCWC.setChairpersonJoiningDate(null==constitutionOfCWCModel.getChairpersonJoiningDate()?null:constitutionOfCWCModel.getChairpersonJoiningDate());
		constitutionOfCWC.setChairpersonName(null==constitutionOfCWCModel.getChairpersonName()?null:constitutionOfCWCModel.getChairpersonName());
		constitutionOfCWC.setChairpersonSex(null==constitutionOfCWCModel.getChairpersonSex()?null:constitutionOfCWCModel.getChairpersonSex().getId());
		constitutionOfCWC.setConstitutionDate(null==constitutionOfCWCModel.getConstitutionDate()?null:constitutionOfCWCModel.getConstitutionDate());
		constitutionOfCWC.setDEOContact(null==constitutionOfCWCModel.getdEOContact()?null:constitutionOfCWCModel.getdEOContact());
		constitutionOfCWC.setDEOEmail(null==constitutionOfCWCModel.getdEOEmail()?null:constitutionOfCWCModel.getdEOEmail());
		constitutionOfCWC.setDEOJoiningDate(null==constitutionOfCWCModel.getdEOJoiningDate()?null:constitutionOfCWCModel.getdEOJoiningDate());
		constitutionOfCWC.setDEOName(null==constitutionOfCWCModel.getdEOName()?null:constitutionOfCWCModel.getdEOName());
		constitutionOfCWC.setDEOSex(null==constitutionOfCWCModel.getdEOSex()?null:constitutionOfCWCModel.getdEOSex().getId());
		constitutionOfCWC.setMemberFourContact(null==constitutionOfCWCModel.getMemberFourContact()?null:constitutionOfCWCModel.getMemberFourContact());
		constitutionOfCWC.setMemberFourEmail(null==constitutionOfCWCModel.getMemberFourEmail()?null:constitutionOfCWCModel.getMemberFourEmail());
		constitutionOfCWC.setMemberFourJoiningDate(null==constitutionOfCWCModel.getMemberFourJoiningDate()?null:constitutionOfCWCModel.getMemberFourJoiningDate());
		constitutionOfCWC.setMemberFourName(null==constitutionOfCWCModel.getMemberFourName()?null:constitutionOfCWCModel.getMemberFourName());
		constitutionOfCWC.setMemberFourSex(null==constitutionOfCWCModel.getMemberFourSex()?null:constitutionOfCWCModel.getMemberFourSex().getId());
		constitutionOfCWC.setMemberOneContact(null==constitutionOfCWCModel.getMemberOneContact()?null:constitutionOfCWCModel.getMemberOneContact());
		constitutionOfCWC.setMemberOneEmail(null==constitutionOfCWCModel.getMemberOneEmail()?null:constitutionOfCWCModel.getMemberOneEmail());
		constitutionOfCWC.setMemberOneJoiningDate(null==constitutionOfCWCModel.getMemberOneJoiningDate()?null:constitutionOfCWCModel.getMemberOneJoiningDate());
		constitutionOfCWC.setMemberOneName(null==constitutionOfCWCModel.getMemberOneName()?null:constitutionOfCWCModel.getMemberOneName());
		constitutionOfCWC.setMemberOneSex(null==constitutionOfCWCModel.getMemberOneSex()?null:constitutionOfCWCModel.getMemberOneSex().getId());
		constitutionOfCWC.setMemberThreeContact(null==constitutionOfCWCModel.getMemberThreeContact()?null:constitutionOfCWCModel.getMemberThreeContact());
		constitutionOfCWC.setMemberThreeEmail(null==constitutionOfCWCModel.getMemberThreeEmail()?null:constitutionOfCWCModel.getMemberThreeEmail());
		constitutionOfCWC.setMemberThreeJoiningDate(null==constitutionOfCWCModel.getMemberThreeJoiningDate()?null:constitutionOfCWCModel.getMemberThreeJoiningDate());
		constitutionOfCWC.setMemberThreeName(null==constitutionOfCWCModel.getMemberThreeName()?null:constitutionOfCWCModel.getMemberThreeName());
		constitutionOfCWC.setMemberThreeSex(null==constitutionOfCWCModel.getMemberThreeSex()?null:constitutionOfCWCModel.getMemberThreeSex().getId());
		constitutionOfCWC.setMemberTwoContact(null==constitutionOfCWCModel.getMemberTwoContact()?null:constitutionOfCWCModel.getMemberTwoContact());
		constitutionOfCWC.setMemberTwoEmail(null==constitutionOfCWCModel.getMemberTwoEmail()?null:constitutionOfCWCModel.getMemberTwoEmail());
		constitutionOfCWC.setMemberTwoName(null==constitutionOfCWCModel.getMemberTwoName()?null:constitutionOfCWCModel.getMemberTwoName());
		constitutionOfCWC.setMemberTwoSex(null==constitutionOfCWCModel.getMemberTwoSex()?null:constitutionOfCWCModel.getMemberTwoSex().getId());
		constitutionOfCWC.setMemberTwoJoiningDate(null==constitutionOfCWCModel.getMemberTwoJoiningDate()?null:constitutionOfCWCModel.getMemberTwoJoiningDate());
		
		constitutionOfCWC.setAreaId(userDetailModel.getAreaId()==null?null:userDetailModel.getAreaId());
		
		constitutionOfCWC.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		constitutionOfCWC.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		constitutionOfCWC.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		
		
		constitutionofCWCRepository.save(constitutionOfCWC);
	}

}
