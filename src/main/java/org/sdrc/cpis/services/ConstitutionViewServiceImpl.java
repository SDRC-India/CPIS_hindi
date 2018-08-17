package org.sdrc.cpis.services;


import java.util.ArrayList;
import java.util.List;

import org.sdrc.cpis.domains.ConstitutionOfBCPC;
import org.sdrc.cpis.domains.ConstitutionOfCWC;
import org.sdrc.cpis.domains.ConstitutionOfDCPC;
import org.sdrc.cpis.domains.ConstitutionOfJJB;
import org.sdrc.cpis.domains.ConstitutionOfSJPU;
import org.sdrc.cpis.models.ConstitutionViewListModel;
import org.sdrc.cpis.models.ConstitutionViewModel;
import org.sdrc.cpis.repository.ConstitutionOfDCPCRepository;
import org.sdrc.cpis.repository.ConstitutionOfJJBRepository;
import org.sdrc.cpis.repository.ConstitutionOfSJPURepository;
import org.sdrc.cpis.repository.ConstitutionofBCPCRepository;
import org.sdrc.cpis.repository.ConstitutionofCWCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstitutionViewServiceImpl implements ConstitutionViewService{

	@Autowired
	ConstitutionofCWCRepository constitutionofCWCRepository;
	
	@Autowired
	ConstitutionOfJJBRepository constitutionOfJJBRepository;
	
	@Autowired
	ConstitutionOfSJPURepository constitutionOfSJPURepository;
	
	@Autowired
	ConstitutionOfDCPCRepository constitutionOfDCPCRepository;
	
	@Autowired
	ConstitutionofBCPCRepository constitutionOfBCPCRepository;
	
	@Override
	public List<ConstitutionViewListModel> getConstitutionView(Integer district,Integer constitutionType, Integer blockId) {
		List<ConstitutionOfCWC> constList = new ArrayList<ConstitutionOfCWC>();
		List<ConstitutionOfJJB> constitutionOfJJBs = new ArrayList<ConstitutionOfJJB>();
		List<ConstitutionOfSJPU> constitutionOfSJPU = new ArrayList<ConstitutionOfSJPU>();
		List<ConstitutionOfDCPC> constitutionOfDCPC = new ArrayList<ConstitutionOfDCPC>();
		List<ConstitutionOfBCPC> constitutionOfBCPC = new ArrayList<ConstitutionOfBCPC>();
		
		
		List<ConstitutionViewModel> constitutionViewModelList = new ArrayList<ConstitutionViewModel>();
		List<ConstitutionViewListModel> constitutionViewListModellist=new ArrayList<ConstitutionViewListModel>();
		
		switch (constitutionType) {
		case 1:
			constList = constitutionofCWCRepository.findCWCConstitution(district);
			if(constList != null && constList.size() > 0){
				for(ConstitutionOfCWC constitutionOfCWC : constList){
					ConstitutionViewListModel constitutionViewListModel=new ConstitutionViewListModel();
					constitutionViewModelList = new ArrayList<ConstitutionViewModel>();
					
					ConstitutionViewModel chairpersonConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel dEOConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel member4ConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel member3ConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel member2ConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel member1ConstitutionViewModel = new ConstitutionViewModel();
					
					chairpersonConstitutionViewModel.setContactNo(constitutionOfCWC.getChairpersonContact()==null?null:constitutionOfCWC.getChairpersonContact());
					chairpersonConstitutionViewModel.setEmailId(constitutionOfCWC.getChairpersonEmail()==null?null:constitutionOfCWC.getChairpersonEmail());
					chairpersonConstitutionViewModel.setJoiningDate(constitutionOfCWC.getChairpersonJoiningDate()==null?null:constitutionOfCWC.getChairpersonJoiningDate());
					chairpersonConstitutionViewModel.setName(constitutionOfCWC.getChairpersonName()==null?null:constitutionOfCWC.getChairpersonName());
					chairpersonConstitutionViewModel.setDesignation("Chairperson");
					constitutionViewModelList.add(chairpersonConstitutionViewModel);
					
					dEOConstitutionViewModel.setContactNo(constitutionOfCWC.getDEOContact()==null?null:constitutionOfCWC.getDEOContact());
					dEOConstitutionViewModel.setEmailId(constitutionOfCWC.getDEOEmail()==null?null:constitutionOfCWC.getDEOEmail());
					dEOConstitutionViewModel.setJoiningDate(constitutionOfCWC.getDEOJoiningDate()==null?null:constitutionOfCWC.getDEOJoiningDate());
					dEOConstitutionViewModel.setName(constitutionOfCWC.getDEOName()==null?null:constitutionOfCWC.getDEOName());
					dEOConstitutionViewModel.setDesignation("Data Entry Operator");
					constitutionViewModelList.add(dEOConstitutionViewModel);
					
					member1ConstitutionViewModel.setContactNo(constitutionOfCWC.getMemberOneContact()==null?null:constitutionOfCWC.getMemberOneContact());
					member1ConstitutionViewModel.setEmailId(constitutionOfCWC.getMemberOneEmail()==null?null:constitutionOfCWC.getMemberOneEmail());
					member1ConstitutionViewModel.setJoiningDate(constitutionOfCWC.getMemberOneJoiningDate()==null?null:constitutionOfCWC.getMemberOneJoiningDate());
					member1ConstitutionViewModel.setName(constitutionOfCWC.getMemberOneName()==null?null:constitutionOfCWC.getMemberOneName());
					member1ConstitutionViewModel.setDesignation("Member 1");
					constitutionViewModelList.add(member1ConstitutionViewModel);
					
					member2ConstitutionViewModel.setContactNo(constitutionOfCWC.getMemberTwoContact()==null?null:constitutionOfCWC.getMemberTwoContact());
					member2ConstitutionViewModel.setEmailId(constitutionOfCWC.getMemberTwoEmail()==null?null:constitutionOfCWC.getMemberTwoEmail());
					member2ConstitutionViewModel.setJoiningDate(constitutionOfCWC.getMemberTwoJoiningDate()==null?null:constitutionOfCWC.getMemberTwoJoiningDate());
					member2ConstitutionViewModel.setName(constitutionOfCWC.getMemberTwoName()==null?null:constitutionOfCWC.getMemberTwoName());
					member2ConstitutionViewModel.setDesignation("Member 2");
					constitutionViewModelList.add(member2ConstitutionViewModel);
					
					member3ConstitutionViewModel.setContactNo(constitutionOfCWC.getMemberThreeContact()==null?null:constitutionOfCWC.getMemberThreeContact());
					member3ConstitutionViewModel.setEmailId(constitutionOfCWC.getMemberThreeEmail()==null?null:constitutionOfCWC.getMemberThreeEmail());
					member3ConstitutionViewModel.setJoiningDate(constitutionOfCWC.getMemberThreeJoiningDate()==null?null:constitutionOfCWC.getMemberThreeJoiningDate());
					member3ConstitutionViewModel.setName(constitutionOfCWC.getMemberThreeName()==null?null:constitutionOfCWC.getMemberThreeName());
					member3ConstitutionViewModel.setDesignation("Member 3");
					constitutionViewModelList.add(member3ConstitutionViewModel);
					
					member4ConstitutionViewModel.setContactNo(constitutionOfCWC.getMemberFourContact()==null?null:constitutionOfCWC.getMemberFourContact());
					member4ConstitutionViewModel.setEmailId(constitutionOfCWC.getMemberFourEmail()==null?null:constitutionOfCWC.getMemberFourEmail());
					member4ConstitutionViewModel.setJoiningDate(constitutionOfCWC.getMemberFourJoiningDate()==null?null:constitutionOfCWC.getMemberFourJoiningDate());
					member4ConstitutionViewModel.setName(constitutionOfCWC.getMemberFourName()==null?null:constitutionOfCWC.getMemberFourName());
					member4ConstitutionViewModel.setDesignation("Member 4");
					constitutionViewModelList.add(member4ConstitutionViewModel);

					constitutionViewListModel.setConstitutionFormationDate(constitutionOfCWC.getConstitutionDate().toString());
					constitutionViewListModel.setConstitutionViewModel(constitutionViewModelList);
					constitutionViewListModellist.add(constitutionViewListModel);
				}
				
			}	
			break;
		
		case 2:
			constitutionOfJJBs = constitutionOfJJBRepository.findJJBConstitution(district);
			if(constitutionOfJJBs != null && constitutionOfJJBs.size() > 0){
				for(ConstitutionOfJJB constitutionOfJJB : constitutionOfJJBs){
					ConstitutionViewListModel constitutionViewListModel=new ConstitutionViewListModel();
					constitutionViewModelList = new ArrayList<ConstitutionViewModel>();
					
					ConstitutionViewModel majistrateConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel socialWorker1ConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel socialWorker2ConstitutionViewModel = new ConstitutionViewModel();
					
					majistrateConstitutionViewModel.setContactNo(constitutionOfJJB.getMagistrateContactNo()==null?null:constitutionOfJJB.getMagistrateContactNo());
					majistrateConstitutionViewModel.setEmailId(constitutionOfJJB.getMagistrateEmailId()==null?null:constitutionOfJJB.getMagistrateEmailId());
					majistrateConstitutionViewModel.setJoiningDate(constitutionOfJJB.getJoiningDate()==null?null:constitutionOfJJB.getJoiningDate());
					majistrateConstitutionViewModel.setName(constitutionOfJJB.getMagistrateName()==null?null:constitutionOfJJB.getMagistrateName());
					majistrateConstitutionViewModel.setDesignation("Principal Magistrate/Chief Judicial Magistrate");
					constitutionViewModelList.add(majistrateConstitutionViewModel);
					
					socialWorker1ConstitutionViewModel.setContactNo(constitutionOfJJB.getSocialWorkerOneContactNo()==null?null:constitutionOfJJB.getSocialWorkerOneContactNo());
					socialWorker1ConstitutionViewModel.setEmailId(constitutionOfJJB.getSocialWorkerOneEmailId()==null?null:constitutionOfJJB.getSocialWorkerOneEmailId());
					socialWorker1ConstitutionViewModel.setJoiningDate(constitutionOfJJB.getSocialWorkerOneJoiningDate()==null?null:constitutionOfJJB.getSocialWorkerOneJoiningDate());
					socialWorker1ConstitutionViewModel.setName(constitutionOfJJB.getSocialWorkerOneName()==null?null:constitutionOfJJB.getSocialWorkerOneName());
					socialWorker1ConstitutionViewModel.setDesignation("Social Worker 1");
					constitutionViewModelList.add(socialWorker1ConstitutionViewModel);
					
					socialWorker2ConstitutionViewModel.setContactNo(constitutionOfJJB.getSocialWorkerTwoContactNo()==null?null:constitutionOfJJB.getSocialWorkerTwoContactNo());
					socialWorker2ConstitutionViewModel.setEmailId(constitutionOfJJB.getSocialWorkerTwoEmailId()==null?null:constitutionOfJJB.getSocialWorkerTwoEmailId());
					socialWorker2ConstitutionViewModel.setJoiningDate(constitutionOfJJB.getSocialWorkerTwoJoiningDate()==null?null:constitutionOfJJB.getSocialWorkerTwoJoiningDate());
					socialWorker2ConstitutionViewModel.setName(constitutionOfJJB.getSocialWorkerTwoName()==null?null:constitutionOfJJB.getSocialWorkerTwoName());
					socialWorker2ConstitutionViewModel.setDesignation("Social Worker 2");
					constitutionViewModelList.add(socialWorker2ConstitutionViewModel);
					
					constitutionViewListModel.setConstitutionFormationDate(constitutionOfJJB.getJjbDate().toString());
					constitutionViewListModel.setConstitutionViewModel(constitutionViewModelList);
					constitutionViewListModellist.add(constitutionViewListModel);
				}
			}	
			break;
			
		case 3:
			constitutionOfSJPU = constitutionOfSJPURepository.findSJPUConstitution(district);
			if(constitutionOfSJPU != null && constitutionOfSJPU.size() > 0){
				for(ConstitutionOfSJPU constitutionOfSJPUs : constitutionOfSJPU){
					ConstitutionViewListModel constitutionViewListModel=new ConstitutionViewListModel();
					constitutionViewModelList = new ArrayList<ConstitutionViewModel>();
					
					ConstitutionViewModel sPConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel sjpuSocialWorker1ConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel sjpusocialWorker2ConstitutionViewModel = new ConstitutionViewModel();
					
					sPConstitutionViewModel.setContactNo(constitutionOfSJPUs.getSpContactNo()==null?null:constitutionOfSJPUs.getSpContactNo());
					sPConstitutionViewModel.setEmailId(constitutionOfSJPUs.getSpEmailId()==null?null:constitutionOfSJPUs.getSpEmailId());
					sPConstitutionViewModel.setJoiningDate(constitutionOfSJPUs.getJoiningDate()==null?null:constitutionOfSJPUs.getJoiningDate());
					sPConstitutionViewModel.setName(constitutionOfSJPUs.getSpName()==null?null:constitutionOfSJPUs.getSpName());
					sPConstitutionViewModel.setDesignation("Dy. SP (HQ)");
					constitutionViewModelList.add(sPConstitutionViewModel);
					
					sjpuSocialWorker1ConstitutionViewModel.setContactNo(constitutionOfSJPUs.getSocialWorkerOneContactNo()==null?null:constitutionOfSJPUs.getSocialWorkerOneContactNo());
					sjpuSocialWorker1ConstitutionViewModel.setEmailId(constitutionOfSJPUs.getSocialWorkerOneEmailId()==null?null:constitutionOfSJPUs.getSocialWorkerOneEmailId());
					sjpuSocialWorker1ConstitutionViewModel.setJoiningDate(constitutionOfSJPUs.getSocialWorkerOneJoiningDate()==null?null:constitutionOfSJPUs.getSocialWorkerOneJoiningDate());
					sjpuSocialWorker1ConstitutionViewModel.setName(constitutionOfSJPUs.getSocialWorkerOneName()==null?null:constitutionOfSJPUs.getSocialWorkerOneName());
					sjpuSocialWorker1ConstitutionViewModel.setDesignation("Social Worker 1");
					constitutionViewModelList.add(sjpuSocialWorker1ConstitutionViewModel);
					
					sjpusocialWorker2ConstitutionViewModel.setContactNo(constitutionOfSJPUs.getSocialWorkerTwoContactNo()==null?null:constitutionOfSJPUs.getSocialWorkerTwoContactNo());
					sjpusocialWorker2ConstitutionViewModel.setEmailId(constitutionOfSJPUs.getSocialWorkerTwoEmailId()==null?null:constitutionOfSJPUs.getSocialWorkerTwoEmailId());
					sjpusocialWorker2ConstitutionViewModel.setJoiningDate(constitutionOfSJPUs.getSocialWorkerTwoJoiningDate()==null?null:constitutionOfSJPUs.getSocialWorkerTwoJoiningDate());
					sjpusocialWorker2ConstitutionViewModel.setName(constitutionOfSJPUs.getSocialWorkerTwoName()==null?null:constitutionOfSJPUs.getSocialWorkerTwoName());
					sjpusocialWorker2ConstitutionViewModel.setDesignation("Social Worker 2");
					constitutionViewModelList.add(sjpusocialWorker2ConstitutionViewModel);
					
					constitutionViewListModel.setConstitutionFormationDate(constitutionOfSJPUs.getSjpuDate().toString());
					constitutionViewListModel.setConstitutionViewModel(constitutionViewModelList);
					constitutionViewListModellist.add(constitutionViewListModel);
				}
			}
			break;
			
		case 4:
			constitutionOfDCPC = constitutionOfDCPCRepository.findDCPCConstitution(district);
			if(constitutionOfDCPC != null && constitutionOfDCPC.size() > 0){
				for(ConstitutionOfDCPC constitutionOfDCPCs : constitutionOfDCPC){
					ConstitutionViewListModel constitutionViewListModel=new ConstitutionViewListModel();
					constitutionViewModelList = new ArrayList<ConstitutionViewModel>();
					
					ConstitutionViewModel zillaParisadConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel magistrateConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel deoConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel cdmoConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel dloConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel pddradaConstitutionViewModel = new ConstitutionViewModel();
					ConstitutionViewModel sopConstitutionViewModel = new ConstitutionViewModel();
					
					zillaParisadConstitutionViewModel.setContactNo(constitutionOfDCPCs.getZillaParishadContactNo()==null?null:constitutionOfDCPCs.getZillaParishadContactNo());
					zillaParisadConstitutionViewModel.setEmailId(constitutionOfDCPCs.getZillaParishadEmailId()==null?null:constitutionOfDCPCs.getZillaParishadEmailId());
					zillaParisadConstitutionViewModel.setName(constitutionOfDCPCs.getZillaParishadName()==null?null:constitutionOfDCPCs.getZillaParishadName());
					zillaParisadConstitutionViewModel.setDesignation("Zilla Parishad (Chairperson, DCPC)");
					constitutionViewModelList.add(zillaParisadConstitutionViewModel);
					
					magistrateConstitutionViewModel.setContactNo(constitutionOfDCPCs.getMagistrateContactNo()==null?null:constitutionOfDCPCs.getMagistrateContactNo());
					magistrateConstitutionViewModel.setEmailId(constitutionOfDCPCs.getMagistrateEmailId()==null?null:constitutionOfDCPCs.getMagistrateEmailId());
					magistrateConstitutionViewModel.setName(constitutionOfDCPCs.getMagistrateName()==null?null:constitutionOfDCPCs.getMagistrateName());
					magistrateConstitutionViewModel.setDesignation("District Magistrate (Co Chairperson, DCPC)");
					constitutionViewModelList.add(magistrateConstitutionViewModel);
					
					deoConstitutionViewModel.setContactNo(constitutionOfDCPCs.getDeoContactNo()==null?null:constitutionOfDCPCs.getDeoContactNo());
					deoConstitutionViewModel.setEmailId(constitutionOfDCPCs.getDeoEmailId()==null?null:constitutionOfDCPCs.getDeoEmailId());
					deoConstitutionViewModel.setName(constitutionOfDCPCs.getDeoName()==null?null:constitutionOfDCPCs.getDeoName());
					deoConstitutionViewModel.setDesignation("DEO (member, DCPC)");
					constitutionViewModelList.add(deoConstitutionViewModel);
					
					cdmoConstitutionViewModel.setContactNo(constitutionOfDCPCs.getCdmoContactNo()==null?null:constitutionOfDCPCs.getCdmoContactNo());
					cdmoConstitutionViewModel.setEmailId(constitutionOfDCPCs.getCdmoEmailId()==null?null:constitutionOfDCPCs.getCdmoEmailId());
					cdmoConstitutionViewModel.setName(constitutionOfDCPCs.getCdmoName()==null?null:constitutionOfDCPCs.getCdmoName());
					cdmoConstitutionViewModel.setDesignation("CDMO (Member, DCPC)");
					constitutionViewModelList.add(cdmoConstitutionViewModel);
					
					dloConstitutionViewModel.setContactNo(constitutionOfDCPCs.getDloContactNo()==null?null:constitutionOfDCPCs.getDloContactNo());
					dloConstitutionViewModel.setEmailId(constitutionOfDCPCs.getDloEmailId()==null?null:constitutionOfDCPCs.getDloEmailId());
					dloConstitutionViewModel.setName(constitutionOfDCPCs.getDloName()==null?null:constitutionOfDCPCs.getDloName());
					dloConstitutionViewModel.setDesignation("DLO (Member, DCPC)");
					constitutionViewModelList.add(dloConstitutionViewModel);
					
					pddradaConstitutionViewModel.setContactNo(constitutionOfDCPCs.getPddrdaContactNo()==null?null:constitutionOfDCPCs.getPddrdaContactNo());
					pddradaConstitutionViewModel.setEmailId(constitutionOfDCPCs.getPddrdaEmailId()==null?null:constitutionOfDCPCs.getPddrdaEmailId());
					pddradaConstitutionViewModel.setName(constitutionOfDCPCs.getPddrdaName()==null?null:constitutionOfDCPCs.getPddrdaName());
					pddradaConstitutionViewModel.setDesignation("PDDRDA (Member, DCPC)");
					constitutionViewModelList.add(pddradaConstitutionViewModel);
					
					sopConstitutionViewModel.setName(constitutionOfDCPCs.getPoliceSuperintendentName()==null?null:constitutionOfDCPCs.getPoliceSuperintendentName());
					sopConstitutionViewModel.setDesignation("Superintendent of Police (Member, DCPC)");
					constitutionViewModelList.add(sopConstitutionViewModel);
					
					constitutionViewListModel.setConstitutionFormationDate(constitutionOfDCPCs.getDcpcDate().toString());
					constitutionViewListModel.setConstitutionViewModel(constitutionViewModelList);
					constitutionViewListModellist.add(constitutionViewListModel);
				}
			}
			break;
		case 5:
			constitutionOfBCPC = constitutionOfBCPCRepository.findByBlockId(blockId);
			
			for (ConstitutionOfBCPC constitutionOfBCPCs : constitutionOfBCPC) {
				ConstitutionViewListModel constitutionViewListModel=new ConstitutionViewListModel();
				constitutionViewModelList = new ArrayList<ConstitutionViewModel>();
				
				ConstitutionViewModel blockChairmanViewModel  = new ConstitutionViewModel();
				ConstitutionViewModel bDOMemberSecretaryBCPCViewModel = new ConstitutionViewModel();
				ConstitutionViewModel memberFromDCPUViewModel = new ConstitutionViewModel();
				ConstitutionViewModel iCDSfunctionaryViewModel = new ConstitutionViewModel();
				ConstitutionViewModel blockEducationOfficerViewModel = new ConstitutionViewModel();
				ConstitutionViewModel officerInchargeCHCViewModel = new ConstitutionViewModel();
				ConstitutionViewModel chairmanVillageLevelChildProtectionCommitteeViewModel = new ConstitutionViewModel();
				ConstitutionViewModel civilSocietyMemberViewModel = new ConstitutionViewModel();
				ConstitutionViewModel communityMemberViewModel = new ConstitutionViewModel();
				
				blockChairmanViewModel.setName(constitutionOfBCPCs.getBlockChairmanName()==null?null:constitutionOfBCPCs.getBlockChairmanName());
				blockChairmanViewModel.setContactNo(constitutionOfBCPCs.getBlockChairmanContact()==null?null:constitutionOfBCPCs.getBlockChairmanContact());
				blockChairmanViewModel.setEmailId(constitutionOfBCPCs.getBlockChairmanEmail()==null?null:constitutionOfBCPCs.getBlockChairmanEmail());
				blockChairmanViewModel.setDesignation("Block Chairman (Chairperson BCPC)");
				constitutionViewModelList.add(blockChairmanViewModel);
				
				bDOMemberSecretaryBCPCViewModel.setName(constitutionOfBCPCs.getBdoName());
				bDOMemberSecretaryBCPCViewModel.setContactNo(constitutionOfBCPCs.getBdoContact());
				bDOMemberSecretaryBCPCViewModel.setEmailId(constitutionOfBCPCs.getBdoEmail());
				bDOMemberSecretaryBCPCViewModel.setDesignation("Block Development Officer (Member Secretary BCPC)");
				constitutionViewModelList.add(bDOMemberSecretaryBCPCViewModel);
				
				memberFromDCPUViewModel.setName(constitutionOfBCPCs.getDcpuMemberName());
				memberFromDCPUViewModel.setContactNo(constitutionOfBCPCs.getDcpuMemberContact());
				memberFromDCPUViewModel.setEmailId(constitutionOfBCPCs.getDcpuMemberEmail());
				memberFromDCPUViewModel.setDesignation("Member from DCPU");
				constitutionViewModelList.add(memberFromDCPUViewModel);
				
				iCDSfunctionaryViewModel.setName(constitutionOfBCPCs.getIcdsFunctionaryName());
				iCDSfunctionaryViewModel.setContactNo(constitutionOfBCPCs.getIcdsFunctionaryContact());
				iCDSfunctionaryViewModel.setEmailId(constitutionOfBCPCs.getIcdsFunctionaryEmail());
				iCDSfunctionaryViewModel.setDesignation("ICDS functionary");
				constitutionViewModelList.add(iCDSfunctionaryViewModel);
				
				blockEducationOfficerViewModel.setName(constitutionOfBCPCs.getBeoName());
				blockEducationOfficerViewModel.setContactNo(constitutionOfBCPCs.getBeoContact());
				blockEducationOfficerViewModel.setEmailId(constitutionOfBCPCs.getBeoEmail());
				blockEducationOfficerViewModel.setDesignation("Block Education Officer");
				constitutionViewModelList.add(blockEducationOfficerViewModel);
				
				officerInchargeCHCViewModel.setName(constitutionOfBCPCs.getOichcName());
				officerInchargeCHCViewModel.setContactNo(constitutionOfBCPCs.getOichcContact());
				officerInchargeCHCViewModel.setEmailId(constitutionOfBCPCs.getOichcEmail());
				officerInchargeCHCViewModel.setDesignation("Officer Incharge CHC");
				constitutionViewModelList.add(officerInchargeCHCViewModel);
				
				chairmanVillageLevelChildProtectionCommitteeViewModel.setName(constitutionOfBCPCs.getVlgLvlCPCName());
				chairmanVillageLevelChildProtectionCommitteeViewModel.setContactNo(constitutionOfBCPCs.getVlgLvlCPCContact());
				chairmanVillageLevelChildProtectionCommitteeViewModel.setEmailId(constitutionOfBCPCs.getVlgLvlCPCEmail());
				chairmanVillageLevelChildProtectionCommitteeViewModel.setDesignation("Chairman, Village Level Child Protection Committee");
				constitutionViewModelList.add(chairmanVillageLevelChildProtectionCommitteeViewModel);
				
				civilSocietyMemberViewModel.setName(constitutionOfBCPCs.getCsmName());
				civilSocietyMemberViewModel.setContactNo(constitutionOfBCPCs.getCsmContact());
				civilSocietyMemberViewModel.setEmailId(constitutionOfBCPCs.getCsmEmail());
				civilSocietyMemberViewModel.setDesignation("Civil Society Member");
				constitutionViewModelList.add(civilSocietyMemberViewModel);
				
				communityMemberViewModel.setName(constitutionOfBCPCs.getCmName());
				communityMemberViewModel.setContactNo(constitutionOfBCPCs.getCmContact());
				communityMemberViewModel.setEmailId(constitutionOfBCPCs.getCmEmail());
				communityMemberViewModel.setDesignation("Community Member");
				constitutionViewModelList.add(communityMemberViewModel);
				
				constitutionViewListModel.setConstitutionFormationDate(constitutionOfBCPCs.getConstitutionDate().toString());
				constitutionViewListModel.setConstitutionViewModel(constitutionViewModelList);
				constitutionViewListModellist.add(constitutionViewListModel);
			}
		default:
			break;
		}		return constitutionViewListModellist;
	}
}
