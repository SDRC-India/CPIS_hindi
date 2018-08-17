package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCIHumanResource;
import org.sdrc.cpis.domains.CCIHumanResourceOS;
import org.sdrc.cpis.domains.CCIHumanResourceSAA;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.domains.DCPUHRDetails;
import org.sdrc.cpis.domains.FinancialInspectionReport;
import org.sdrc.cpis.domains.Years;
import org.sdrc.cpis.models.CCIHumanResourceModel;
import org.sdrc.cpis.models.CCIHumanResourceOSModel;
import org.sdrc.cpis.models.CCIHumanResourceSAAModel;
import org.sdrc.cpis.models.DCPUHRDetailsModel;
import org.sdrc.cpis.models.FinancialInspectionReportModel;
import org.sdrc.cpis.models.InfrastructureCCIModel;
import org.sdrc.cpis.models.InfrastructureOSModel;
import org.sdrc.cpis.models.InfrastructureSAAModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCIHumanResourceOSRepository;
import org.sdrc.cpis.repository.CCIHumanResourceReposotory;
import org.sdrc.cpis.repository.CCIHumanResourceSAARepository;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.DCPUHRDetailsRepository;
import org.sdrc.cpis.repository.FinancialInspectionReportRepository;
import org.sdrc.cpis.repository.YearsRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportSummaryServiceImpl implements ReportSummaryService{
    
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CCIHumanResourceReposotory cciHumanResourceReposotory;
	
	@Autowired
	private CCIHumanResourceSAARepository cciHumanResourceSAARepository;
	
	@Autowired
	private CCIHumanResourceOSRepository cciHumanResourceOSRepository;
	
	@Autowired
	private YearsRepository yearsRepository;
	
	@Autowired
	private ExportPDFServiceImpl exportPDFServiceImpl;
	
	@Autowired
	private FinancialInspectionReportRepository financialInspectionReportRepository;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	@Autowired
	private CCIInfoRepository cciInfoRepository;
	
	@Autowired
	private DCPUHRDetailsRepository dcpuHRDetailsRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private CCIInfoRepository CCIInfoRepository;
	
	public Map<Integer, ValueObject> getTypeMap() {
		List<CCTSTypeDetails> typeDetails = cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map = new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj = new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}

	@Override
	public void saveCCIHumanResource(CCIHumanResourceModel cciHumanResourceModel) {
		CCIHumanResource cciHumanResourceCCIupdate=cciHumanResourceReposotory.findByNameofCII(cciHumanResourceModel.getNameOfCCI().getId());
		if(cciHumanResourceCCIupdate != null){
			cciHumanResourceCCIupdate.setArtCraftMusicTeacher(cciHumanResourceModel.getArtCraftMusicTeacher() == null ? null :cciHumanResourceModel.getArtCraftMusicTeacher());
			cciHumanResourceCCIupdate.setAsstCook(cciHumanResourceModel.getAsstCook() == null ? null : cciHumanResourceModel.getAsstCook());
			cciHumanResourceCCIupdate.setCareTaker_VocationalInstructor(cciHumanResourceModel.getCareTaker_VocationalInstructor() == null ? null : cciHumanResourceModel.getCareTaker_VocationalInstructor());
			cciHumanResourceCCIupdate.setCook(cciHumanResourceModel.getCook() == null ? null : cciHumanResourceModel.getCook());
			cciHumanResourceCCIupdate.setCounselor(cciHumanResourceModel.getCounselor() == null ? null : cciHumanResourceModel.getCounselor());
			cciHumanResourceCCIupdate.setEducator(cciHumanResourceModel.getEducator() == null ? null : cciHumanResourceModel.getEducator());
			cciHumanResourceCCIupdate.setFemale_Nurse(cciHumanResourceModel.getFemale_Nurse() == null ? null : cciHumanResourceModel.getFemale_Nurse());
			cciHumanResourceCCIupdate.setHouseKeeper(cciHumanResourceModel.getHouseKeeper() == null ? null : cciHumanResourceModel.getHouseKeeper());
			cciHumanResourceCCIupdate.setHouseMother_Father(cciHumanResourceModel.getHouseMother_Father() == null ? null : cciHumanResourceModel.getHouseMother_Father());
			cciHumanResourceCCIupdate.setMbbsDoctor(cciHumanResourceModel.getMbbsDoctor() == null ? null : cciHumanResourceModel.getMbbsDoctor());
			cciHumanResourceCCIupdate.setOfficerIncharge_Superintendent(cciHumanResourceModel.getOfficerIncharge_Superintendent() == null ? null : cciHumanResourceModel.getOfficerIncharge_Superintendent());
			cciHumanResourceCCIupdate.setParaMedicalStaff(cciHumanResourceModel.getParaMedicalStaff() == null ? null : cciHumanResourceModel.getParaMedicalStaff());
			cciHumanResourceCCIupdate.setPo_so_cwo(cciHumanResourceModel.getPo_so_cwo() == null ? null : cciHumanResourceModel.getPo_so_cwo());
			cciHumanResourceCCIupdate.setPtInstructorYogaTeacher(cciHumanResourceModel.getPtInstructorYogaTeacher() == null ? null : cciHumanResourceModel.getPtInstructorYogaTeacher());
			cciHumanResourceCCIupdate.setSpecial_Educator_Therapist(cciHumanResourceModel.getSpecial_Educator_Therapist() == null ? null : cciHumanResourceModel.getSpecial_Educator_Therapist());
			cciHumanResourceCCIupdate.setStoreKeeperCumAccountan(cciHumanResourceModel.getStoreKeeperCumAccountan() == null ? null : cciHumanResourceModel.getStoreKeeperCumAccountan());
			cciHumanResourceCCIupdate.setOthers(cciHumanResourceModel.getOthers()==null ? null : cciHumanResourceModel.getOthers());
			cciHumanResourceReposotory.save(cciHumanResourceCCIupdate);
		}
		
		else{
		CCIHumanResource cciHumanResource = new CCIHumanResource();
		cciHumanResource.setArtCraftMusicTeacher(cciHumanResourceModel.getArtCraftMusicTeacher() == null ? null :cciHumanResourceModel.getArtCraftMusicTeacher());
		cciHumanResource.setAsstCook(cciHumanResourceModel.getAsstCook() == null ? null : cciHumanResourceModel.getAsstCook());
		cciHumanResource.setCareTaker_VocationalInstructor(cciHumanResourceModel.getCareTaker_VocationalInstructor() == null ? null : cciHumanResourceModel.getCareTaker_VocationalInstructor());
		cciHumanResource.setNameOfCCI(cciHumanResourceModel.getNameOfCCI() == null ? null : cciHumanResourceModel.getNameOfCCI().getId());
		cciHumanResource.setCook(cciHumanResourceModel.getCook() == null ? null : cciHumanResourceModel.getCook());
		cciHumanResource.setCounselor(cciHumanResourceModel.getCounselor() == null ? null : cciHumanResourceModel.getCounselor());
		cciHumanResource.setNameOfDistrict(cciHumanResourceModel.getDistrict() == null ? null : cciHumanResourceModel.getDistrict().getKey());
		cciHumanResource.setEducator(cciHumanResourceModel.getEducator() == null ? null : cciHumanResourceModel.getEducator());
		cciHumanResource.setFemale_Nurse(cciHumanResourceModel.getFemale_Nurse() == null ? null : cciHumanResourceModel.getFemale_Nurse());
		cciHumanResource.setHouseKeeper(cciHumanResourceModel.getHouseKeeper() == null ? null : cciHumanResourceModel.getHouseKeeper());
		cciHumanResource.setHouseMother_Father(cciHumanResourceModel.getHouseMother_Father() == null ? null : cciHumanResourceModel.getHouseMother_Father());
		cciHumanResource.setMbbsDoctor(cciHumanResourceModel.getMbbsDoctor() == null ? null : cciHumanResourceModel.getMbbsDoctor());
		cciHumanResource.setOfficerIncharge_Superintendent(cciHumanResourceModel.getOfficerIncharge_Superintendent() == null ? null : cciHumanResourceModel.getOfficerIncharge_Superintendent());
		cciHumanResource.setParaMedicalStaff(cciHumanResourceModel.getParaMedicalStaff() == null ? null : cciHumanResourceModel.getParaMedicalStaff());
		cciHumanResource.setPo_so_cwo(cciHumanResourceModel.getPo_so_cwo() == null ? null : cciHumanResourceModel.getPo_so_cwo());
		cciHumanResource.setPtInstructorYogaTeacher(cciHumanResourceModel.getPtInstructorYogaTeacher() == null ? null : cciHumanResourceModel.getPtInstructorYogaTeacher());
		cciHumanResource.setSpecial_Educator_Therapist(cciHumanResourceModel.getSpecial_Educator_Therapist() == null ? null : cciHumanResourceModel.getSpecial_Educator_Therapist());
		cciHumanResource.setStoreKeeperCumAccountan(cciHumanResourceModel.getStoreKeeperCumAccountan() == null ? null : cciHumanResourceModel.getStoreKeeperCumAccountan());
		cciHumanResource.setOthers(cciHumanResourceModel.getOthers()==null ? null : cciHumanResourceModel.getOthers());
		cciHumanResourceReposotory.save(cciHumanResource);
		}
	}


	@Override
	public void saveCCIHumanREsourceSAA(CCIHumanResourceSAAModel cciHumanResourceSAAModel) {
		
		CCIHumanResourceSAA cciHumanResourceSAAupdate=cciHumanResourceSAARepository.findByNameofSAA(cciHumanResourceSAAModel.getNameOfSAA().getId());
		if(cciHumanResourceSAAupdate != null){
			cciHumanResourceSAAupdate.setAyah(cciHumanResourceSAAModel.getAyah() == null ? null : cciHumanResourceSAAModel.getAyah());
			cciHumanResourceSAAupdate.setChowkidar(cciHumanResourceSAAModel.getChowkidar() == null ? null : cciHumanResourceSAAModel.getChowkidar());
			cciHumanResourceSAAupdate.setNurse(cciHumanResourceSAAModel.getNurse() == null ? null : cciHumanResourceSAAModel.getNurse());
			cciHumanResourceSAAupdate.setPartTime_Doctor_Child_Specialist(cciHumanResourceSAAModel.getPartTime_Doctor_Child_Specialist() == null ? null : cciHumanResourceSAAModel.getPartTime_Doctor_Child_Specialist());
			cciHumanResourceSAAupdate.setProgramme_Manager(cciHumanResourceSAAModel.getProgramme_Manager() == null ? null : cciHumanResourceSAAModel.getProgramme_Manager());
			cciHumanResourceSAAupdate.setProject_Coordinator(cciHumanResourceSAAModel.getProject_Coordinator() == null ? null : cciHumanResourceSAAModel.getProject_Coordinator());
			cciHumanResourceSAAupdate.setSocial_worker_Cum_Early_ChildHood_Educator(cciHumanResourceSAAModel.getSocial_worker_Cum_Early_ChildHood_Educator() == null ? null : cciHumanResourceSAAModel.getSocial_worker_Cum_Early_ChildHood_Educator());
			cciHumanResourceSAAupdate.setOthers(cciHumanResourceSAAModel.getOthers()==null?null:cciHumanResourceSAAModel.getOthers());
			cciHumanResourceSAARepository.save(cciHumanResourceSAAupdate);
		}
		else{
		     CCIHumanResourceSAA cciHumanResourceSAA = new CCIHumanResourceSAA();
			 cciHumanResourceSAA.setAyah(cciHumanResourceSAAModel.getAyah() == null ? null : cciHumanResourceSAAModel.getAyah());
			 cciHumanResourceSAA.setChowkidar(cciHumanResourceSAAModel.getChowkidar() == null ? null : cciHumanResourceSAAModel.getChowkidar());
			 cciHumanResourceSAA.setNameOfDistrict(cciHumanResourceSAAModel.getDistrict() == null ? null :cciHumanResourceSAAModel.getDistrict().getKey());
			 cciHumanResourceSAA.setNameOfSAA(cciHumanResourceSAAModel.getNameOfSAA() == null ? null :cciHumanResourceSAAModel.getNameOfSAA().getId());
			 cciHumanResourceSAA.setNurse(cciHumanResourceSAAModel.getNurse() == null ? null : cciHumanResourceSAAModel.getNurse());
			 cciHumanResourceSAA.setPartTime_Doctor_Child_Specialist(cciHumanResourceSAAModel.getPartTime_Doctor_Child_Specialist() == null ? null : cciHumanResourceSAAModel.getPartTime_Doctor_Child_Specialist());
			 cciHumanResourceSAA.setProgramme_Manager(cciHumanResourceSAAModel.getProgramme_Manager() == null ? null : cciHumanResourceSAAModel.getProgramme_Manager());
			 cciHumanResourceSAA.setProject_Coordinator(cciHumanResourceSAAModel.getProject_Coordinator() == null ? null : cciHumanResourceSAAModel.getProject_Coordinator());
			 cciHumanResourceSAA.setSocial_worker_Cum_Early_ChildHood_Educator(cciHumanResourceSAAModel.getSocial_worker_Cum_Early_ChildHood_Educator() == null ? null : cciHumanResourceSAAModel.getSocial_worker_Cum_Early_ChildHood_Educator());
			 cciHumanResourceSAA.setOthers(cciHumanResourceSAAModel.getOthers()==null?null:cciHumanResourceSAAModel.getOthers());
			 cciHumanResourceSAARepository.save(cciHumanResourceSAA);
		 }
	}

	@Override
	public void saveCCIHumanResourceOS(CCIHumanResourceOSModel cciHumanResourceOSModel) {

		CCIHumanResourceOS cciHumanResourceOSupdate=cciHumanResourceOSRepository.findByNameofOpenShelter(cciHumanResourceOSModel.getNameOfOpenShelter().getId());
		
		if(cciHumanResourceOSupdate != null){
			cciHumanResourceOSupdate.setCareGiver_Cum_BridgeCourse_Educator(cciHumanResourceOSModel.getCareGiver_Cum_BridgeCourse_Educator() == null ? null : cciHumanResourceOSModel.getCareGiver_Cum_BridgeCourse_Educator());
			cciHumanResourceOSupdate.setHelper_For_Cleaning_Cooking(cciHumanResourceOSModel.getHelper_For_Cleaning_Cooking() == null ? null : cciHumanResourceOSModel.getHelper_For_Cleaning_Cooking());
			cciHumanResourceOSupdate.setOutReach_Worker(cciHumanResourceOSModel.getOutReach_Worker() == null ? null : cciHumanResourceOSModel.getOutReach_Worker());
			cciHumanResourceOSupdate.setProjectCoordinator_cum_Counselor(cciHumanResourceOSModel.getProjectCoordinator_cum_Counselor() == null ? null : cciHumanResourceOSModel.getProjectCoordinator_cum_Counselor());
			cciHumanResourceOSupdate.setSocial_Worker(cciHumanResourceOSModel.getSocial_Worker() == null ? null : cciHumanResourceOSModel.getSocial_Worker());
			cciHumanResourceOSupdate.setOthers(cciHumanResourceOSModel.getOthers()==null?null:cciHumanResourceOSModel.getOthers());
			cciHumanResourceOSRepository.save(cciHumanResourceOSupdate);
		 }		
		else{
			CCIHumanResourceOS cciHumanResourceOS = new CCIHumanResourceOS();
			cciHumanResourceOS.setCareGiver_Cum_BridgeCourse_Educator(cciHumanResourceOSModel.getCareGiver_Cum_BridgeCourse_Educator() == null ? null : cciHumanResourceOSModel.getCareGiver_Cum_BridgeCourse_Educator());
			cciHumanResourceOS.setNameOfDistrict(cciHumanResourceOSModel.getDistrict() == null ? null :cciHumanResourceOSModel.getDistrict().getKey());
			cciHumanResourceOS.setHelper_For_Cleaning_Cooking(cciHumanResourceOSModel.getHelper_For_Cleaning_Cooking() == null ? null : cciHumanResourceOSModel.getHelper_For_Cleaning_Cooking());
			cciHumanResourceOS.setNameOfOpenShelter(cciHumanResourceOSModel.getNameOfOpenShelter() == null ? null : cciHumanResourceOSModel.getNameOfOpenShelter().getId());
			cciHumanResourceOS.setOutReach_Worker(cciHumanResourceOSModel.getOutReach_Worker() == null ? null : cciHumanResourceOSModel.getOutReach_Worker());
			cciHumanResourceOS.setProjectCoordinator_cum_Counselor(cciHumanResourceOSModel.getProjectCoordinator_cum_Counselor() == null ? null : cciHumanResourceOSModel.getProjectCoordinator_cum_Counselor());
			cciHumanResourceOS.setSocial_Worker(cciHumanResourceOSModel.getSocial_Worker() == null ? null : cciHumanResourceOSModel.getSocial_Worker());
			cciHumanResourceOS.setNameOfCCI(null==cciHumanResourceOSModel.getNameOfOpenShelter()?null:cciHumanResourceOSModel.getNameOfOpenShelter().getId());
			cciHumanResourceOS.setOthers(cciHumanResourceOSModel.getOthers()==null?null:cciHumanResourceOSModel.getOthers());
			cciHumanResourceOSRepository.save(cciHumanResourceOS);
		}
	}

	@Override
	public ValueObject getArea() {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
				AreaDetails areadetails = areaRepository.fetchAreaById(userDetailModel.getAreaId());
				ValueObject valueObject =  new ValueObject();
				valueObject.setKey(areadetails.getAreaId());
				valueObject.setValue(areadetails.getAreaName());
		return valueObject;
	}
     /*===================================================================================*/
	/*==============================   Infrastructure   =================================*/
   /*===================================================================================*/
	@Override
	public void saveInfrastructureCCI(InfrastructureCCIModel infrastructureCCIModel) {
		
		if(infrastructureCCIModel!=null){
		    CCIDetails cciDetails = cciInfoRepository.getCciById(infrastructureCCIModel.getNameOfCCI().getId());
		    cciDetails.setArea_of_Building(infrastructureCCIModel.getArea_of_Building()==null ? null :infrastructureCCIModel.getArea_of_Building() );
			cciDetails.setProperElectricity(infrastructureCCIModel.getAvailability_Electricity_Allrooms()==null ? null : infrastructureCCIModel.getAvailability_Electricity_Allrooms());
		    cciDetails.setProperDrinkingWater(infrastructureCCIModel.getAvailability_of_DrinkingWater()==null ? null : infrastructureCCIModel.getAvailability_of_DrinkingWater());
			cciDetails.setBoundaryWall(infrastructureCCIModel.getBuilding_Protected_by_Boundarywall()==null ? null : infrastructureCCIModel.getBuilding_Protected_by_Boundarywall());
			cciDetails.setBuilding_Type(infrastructureCCIModel.getBuilding_Type()==null ? null : infrastructureCCIModel.getBuilding_Type().getId());
			cciDetails.setTotalToilets(infrastructureCCIModel.getNumber_of_toiletsAvailable()==null ? null : infrastructureCCIModel.getNumber_of_toiletsAvailable());
			cciDetails.setPower_Backupfacility_AllRooms(infrastructureCCIModel.getPower_Backupfacility_AllRooms()==null ? null : infrastructureCCIModel.getPower_Backupfacility_AllRooms());
			cciDetails.setStatus_of_Building(infrastructureCCIModel.getStatus_of_Building()==null ? null : infrastructureCCIModel.getStatus_of_Building().getId());
			cciDetails.setTotalRooms(infrastructureCCIModel.getTotal_Numbers_Room()==null ? null : infrastructureCCIModel.getTotal_Numbers_Room());
			cciDetails.setTotal(infrastructureCCIModel.getTotal_capacity()==null ? null : infrastructureCCIModel.getTotal_capacity());
			
			cciInfoRepository.save(cciDetails);
		}
	}


	@Override
	public void saveInfrastructureSAA(InfrastructureSAAModel infrastructureSAAModel) {

		if(infrastructureSAAModel!=null){
			
			
			 CCIDetails cciDetails = cciInfoRepository.getCciById(infrastructureSAAModel.getNameOfSAA().getId());
			    cciDetails.setArea_of_Building(infrastructureSAAModel.getArea_of_Building()==null ? null : infrastructureSAAModel.getArea_of_Building());
				cciDetails.setProperElectricity(infrastructureSAAModel.getAvailability_Electricity_Allrooms()==null ? null : infrastructureSAAModel.getAvailability_Electricity_Allrooms());
			    cciDetails.setProperDrinkingWater(infrastructureSAAModel.getAvailability_of_DrinkingWater()==null ? null : infrastructureSAAModel.getAvailability_of_DrinkingWater());
				cciDetails.setBoundaryWall(infrastructureSAAModel.getBuilding_Protected_by_Boundarywall()==null ? null :infrastructureSAAModel.getBuilding_Protected_by_Boundarywall());
				cciDetails.setBuilding_Type(infrastructureSAAModel.getBuilding_Type()==null ? null :infrastructureSAAModel.getBuilding_Type().getId());
				cciDetails.setTotalToilets(infrastructureSAAModel.getNumber_of_toiletsAvailable()==null ? null : infrastructureSAAModel.getNumber_of_toiletsAvailable());
				cciDetails.setPower_Backupfacility_AllRooms(infrastructureSAAModel.getPower_Backupfacility_AllRooms()==null ? null : infrastructureSAAModel.getPower_Backupfacility_AllRooms());
				cciDetails.setStatus_of_Building(infrastructureSAAModel.getStatus_of_Building()==null ? null : infrastructureSAAModel.getStatus_of_Building().getId());
				cciDetails.setTotalRooms(infrastructureSAAModel.getTotal_Numbers_Room()==null ? null : infrastructureSAAModel.getTotal_Numbers_Room());
				cciDetails.setTotal(infrastructureSAAModel.getTotal_capacity()==null ? null : infrastructureSAAModel.getTotal_capacity());
				
				cciInfoRepository.save(cciDetails);
		}
	}


	@Override
	public void saveInfrastructureOS(InfrastructureOSModel infrastructureOSModel) {
		
		if(infrastructureOSModel != null){
			CCIDetails cciDetails = cciInfoRepository.getCciById(infrastructureOSModel.getNameOfOS().getId());
		    cciDetails.setArea_of_Building(infrastructureOSModel.getArea_of_Building()==null ? null : infrastructureOSModel.getArea_of_Building());
			cciDetails.setProperElectricity(infrastructureOSModel.getAvailability_Electricity_Allrooms()==null ? null : infrastructureOSModel.getAvailability_Electricity_Allrooms());
		    cciDetails.setProperDrinkingWater(infrastructureOSModel.getAvailability_of_DrinkingWater()==null ? null : infrastructureOSModel.getAvailability_of_DrinkingWater());
			cciDetails.setBoundaryWall(infrastructureOSModel.getBuilding_Protected_by_Boundarywall()==null ? null : infrastructureOSModel.getBuilding_Protected_by_Boundarywall() );
			cciDetails.setBuilding_Type(infrastructureOSModel.getBuilding_Type()==null ? null : infrastructureOSModel.getBuilding_Type().getId());
			cciDetails.setTotalToilets(infrastructureOSModel.getTotalNumber_of_toiletsAvailable()==null ? null : infrastructureOSModel.getTotalNumber_of_toiletsAvailable() );
			cciDetails.setPower_Backupfacility_AllRooms(infrastructureOSModel.getPower_Backupfacility_AllRooms()==null ? null : infrastructureOSModel.getPower_Backupfacility_AllRooms());
			cciDetails.setStatus_of_Building(infrastructureOSModel.getStatus_of_Building()==null ? null : infrastructureOSModel.getStatus_of_Building().getId() );
			cciDetails.setTotalRooms(infrastructureOSModel.getTotal_Numbers_Room()==null ? null : infrastructureOSModel.getTotal_Numbers_Room() );
			cciDetails.setNumber_of_ContactPoints(infrastructureOSModel.getNumber_of_ContactPoints()==null ? null : infrastructureOSModel.getNumber_of_ContactPoints());
			cciDetails.setToiletsForBoys(infrastructureOSModel.getNumber_of_toiletsAvailableFor_Boys()==null ? null : infrastructureOSModel.getNumber_of_toiletsAvailableFor_Boys() );
			cciDetails.setToiletsForGirls(infrastructureOSModel.getNumber_of_toiletsAvailableFor_Girls()==null ? null : infrastructureOSModel.getNumber_of_toiletsAvailableFor_Girls() );
			cciDetails.setRoomsForBoys(infrastructureOSModel.getNumbers_RoomForBoys()==null ? null : infrastructureOSModel.getNumbers_RoomForBoys() );
			cciDetails.setRoomsForGirls(infrastructureOSModel.getNumbers_RoomForGirls()==null ? null :infrastructureOSModel.getNumbers_RoomForGirls());
			cciDetails.setSeparate_Toiletsfor_Staff(infrastructureOSModel.getSeparate_Toiletsfor_Staff()==null ? null : infrastructureOSModel.getSeparate_Toiletsfor_Staff() );
			cciDetails.setTotal(infrastructureOSModel.getTotal_capacity()==null ? null : infrastructureOSModel.getTotal_capacity());
			
			cciInfoRepository.save(cciDetails);
			
		}
		
		
	}
	
	@Override
	public List<ValueObject> getYearDetails() {
		List<ValueObject> listOfYears = new ArrayList<ValueObject>();
		List<String> listOfYearPeriod = yearsRepository.fetchYearPeriod();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		if(!listOfYearPeriod.contains(yearInString)){
			Years years = new Years();
			years.setYear_period(yearInString);
			yearsRepository.save(years);
		}
		
		List<Years> yearsList = yearsRepository.fetchAllYears();
		for (Years years : yearsList) {
			ValueObject valueObject = new ValueObject();
			valueObject.setId(years.getYearId());
			valueObject.setName(years.getYear_period());
			listOfYears.add(valueObject);
		}
		return listOfYears;
	}
	
	@Override
	public String saveFinancialReport(FinancialInspectionReportModel financialInspectionReportModel) throws Exception {
		String path = applicationMessageSource.getMessage("store.reportSummary", null, null,null);
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		FinancialInspectionReport financialInspectionReport = new FinancialInspectionReport();
		financialInspectionReport.setCciId(financialInspectionReportModel.getCciId());
		financialInspectionReport.setQuarter_id(financialInspectionReportModel.getQuarterId());
		financialInspectionReport.setYear_id(financialInspectionReportModel.getYearId());
		financialInspectionReport.setType(financialInspectionReportModel.getType());
		financialInspectionReport.setFinancial_inspection_path(exportPDFServiceImpl.getFileName(financialInspectionReportModel.getFinancialInspectionPath(),"reportSummary", path));
		financialInspectionReport.setCreatedBy(userDetailModel.getUserName()== null ? null :userDetailModel.getUserName());
		financialInspectionReport.setCreatedDate(new java.sql.Date(new Date().getTime()));
		financialInspectionReport.setUserIp(userDetailModel.getUserIp()== null ? null :userDetailModel.getUserIp());
		financialInspectionReportRepository.save(financialInspectionReport);
		return "success";
	}
	
	@Override
	public List<ValueObject> getFinancialInspectionReportDetails() throws Exception {
		List<ValueObject> listOfFinancialInspectionReportDetails = new ArrayList<ValueObject>();
		List<FinancialInspectionReport> reportList = financialInspectionReportRepository.fetchAll();
		
		Map<Integer, String> yearsMap = new LinkedHashMap<Integer, String>();
		List<Years> yearsList = yearsRepository.fetchAllYears();
		for (Years years : yearsList) {
			yearsMap.put(years.getYearId(), years.getYear_period());
		}
		
		for (FinancialInspectionReport financialInspectionReport : reportList) {
			ValueObject valueObject = new ValueObject();
			valueObject.setName(yearsMap.get(financialInspectionReport.getYear_id()));
			valueObject.setValue(exportPDFServiceImpl.getPdf(financialInspectionReport.getFinancial_inspection_path()));
			valueObject.setDesc(financialInspectionReport.getType());
			valueObject.setTypeId(financialInspectionReport.getQuarter_id());
			valueObject.setKey(financialInspectionReport.getYear_id());
			valueObject.setId(financialInspectionReport.getId());
			valueObject.setValue2(null==financialInspectionReport.getCciId()?null:CCIInfoRepository.getCciById(financialInspectionReport.getCciId()).getCciName());
			valueObject.setOther(getTypeMap().get(financialInspectionReport.getQuarter_id()).getName());
			listOfFinancialInspectionReportDetails.add(valueObject);
		}
		return listOfFinancialInspectionReportDetails;
	}
	
	@Override
	@Transactional
	public void saveDCPUHRDetails(DCPUHRDetailsModel dcpuhrDetailsModel) {
		
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);

		DCPUHRDetails dcpuDetails = dcpuHRDetailsRepository.findByDcpuAreaId(userDetailModel.getAreaId());
		
		//if area not present then creating a new object of DCPUHRDetails else sending the same object of DCPUHRDetails i.e. dcpuDetails
		if(dcpuDetails == null){
			DCPUHRDetails dcpuhrDetails = new DCPUHRDetails();
			saveDCPUDetails(dcpuhrDetails, dcpuhrDetailsModel, userDetailModel);
		}else{
			saveDCPUDetails(dcpuDetails, dcpuhrDetailsModel, userDetailModel);
		}
	}
	
	//saving dcpuhrdetails 
	private void saveDCPUDetails(DCPUHRDetails dcpuhrDetails, DCPUHRDetailsModel dcpuhrDetailsModel, UserDetailModel userDetailModel) {
		dcpuhrDetails.setAccountantContactNumber(dcpuhrDetailsModel.getAccountantContactNumber());
		dcpuhrDetails.setAccountantEmail(dcpuhrDetailsModel.getAccountantEmail());
		dcpuhrDetails.setAccountantName(dcpuhrDetailsModel.getAccountantName());
		dcpuhrDetails.setAccountantSex(dcpuhrDetailsModel.getAccountantSex());
		
		dcpuhrDetails.setAcdeoContactNumber(dcpuhrDetailsModel.getAcdeoContactNumber());
		dcpuhrDetails.setAcdeoEmail(dcpuhrDetailsModel.getAcdeoEmail());
		dcpuhrDetails.setAcdeoName(dcpuhrDetailsModel.getAcdeoName());
		dcpuhrDetails.setAcdeoSex(dcpuhrDetailsModel.getAcdeoSex());
		
		dcpuhrDetails.setCounsellorContactNumber(dcpuhrDetailsModel.getCounsellorContactNumber());
		dcpuhrDetails.setCounsellorEmail(dcpuhrDetailsModel.getCounsellorEmail());
		dcpuhrDetails.setCounsellorName(dcpuhrDetailsModel.getCounsellorName());
		dcpuhrDetails.setCounsellorSex(dcpuhrDetailsModel.getCounsellorSex());
		
		dcpuhrDetails.setDaContactNumber(dcpuhrDetailsModel.getDaContactNumber());
		dcpuhrDetails.setDaEmail(dcpuhrDetailsModel.getDaEmail());
		dcpuhrDetails.setDaName(dcpuhrDetailsModel.getDaName());
		dcpuhrDetails.setDaSex(dcpuhrDetailsModel.getDaSex());
		
		dcpuhrDetails.setDpoContactNumber(dcpuhrDetailsModel.getDpoContactNumber());
		dcpuhrDetails.setDpoEmail(dcpuhrDetailsModel.getDpoEmail());
		dcpuhrDetails.setDpoName(dcpuhrDetailsModel.getDpoName());
		dcpuhrDetails.setDpoSex(dcpuhrDetailsModel.getDpoSex());
		
		dcpuhrDetails.setLcpoavilContactNumber(dcpuhrDetailsModel.getLcpoavilContactNumber());
		dcpuhrDetails.setLcpoavilEmail(dcpuhrDetailsModel.getLcpoavilEmail());
		dcpuhrDetails.setLcpoavilName(dcpuhrDetailsModel.getLcpoavilName());
		dcpuhrDetails.setLcpoavilSex(dcpuhrDetailsModel.getLcpoavilSex());
		
		dcpuhrDetails.setOrw1ContactNumber(dcpuhrDetailsModel.getOrw1ContactNumber());
		dcpuhrDetails.setOrw1Email(dcpuhrDetailsModel.getOrw1Email());
		dcpuhrDetails.setOrw1Name(dcpuhrDetailsModel.getOrw1Name());
		dcpuhrDetails.setOrw1Sex(dcpuhrDetailsModel.getOrw1Sex());
		
		dcpuhrDetails.setOrw2ContactNumber(dcpuhrDetailsModel.getOrw2ContactNumber());
		dcpuhrDetails.setOrw2Email(dcpuhrDetailsModel.getOrw2Email());
		dcpuhrDetails.setOrw2Name(dcpuhrDetailsModel.getOrw2Name());
		dcpuhrDetails.setOrw2Sex(dcpuhrDetailsModel.getOrw2Sex());
		
		dcpuhrDetails.setOrw3ContactNumber(dcpuhrDetailsModel.getOrw3ContactNumber());
		dcpuhrDetails.setOrw3Email(dcpuhrDetailsModel.getOrw3Email());
		dcpuhrDetails.setOrw3Name(dcpuhrDetailsModel.getOrw3Name());
		dcpuhrDetails.setOrw3Sex(dcpuhrDetailsModel.getOrw3Sex());
		
		dcpuhrDetails.setOrw4ContactNumber(dcpuhrDetailsModel.getOrw4ContactNumber());
		dcpuhrDetails.setOrw4Email(dcpuhrDetailsModel.getOrw4Email());
		dcpuhrDetails.setOrw4Name(dcpuhrDetailsModel.getOrw4Name());
		dcpuhrDetails.setOrw4Sex(dcpuhrDetailsModel.getOrw4Sex());
		
		dcpuhrDetails.setOrw5ContactNumber(dcpuhrDetailsModel.getOrw5ContactNumber());
		dcpuhrDetails.setOrw5Email(dcpuhrDetailsModel.getOrw5Email());
		dcpuhrDetails.setOrw5Name(dcpuhrDetailsModel.getOrw5Name());
		dcpuhrDetails.setOrw5Sex(dcpuhrDetailsModel.getOrw5Sex());
		
		dcpuhrDetails.setOtherStaffContactNumber(dcpuhrDetailsModel.getOtherStaffContactNumber());
		dcpuhrDetails.setOtherStaffEmail(dcpuhrDetailsModel.getOtherStaffEmail());
		dcpuhrDetails.setOtherStaffName(dcpuhrDetailsModel.getOtherStaffName());
		dcpuhrDetails.setOtherStaffSex(dcpuhrDetailsModel.getOtherStaffSex());
		
		dcpuhrDetails.setPoicContactNumber(dcpuhrDetailsModel.getPoicContactNumber());
		dcpuhrDetails.setPoicEmail(dcpuhrDetailsModel.getPoicEmail());
		dcpuhrDetails.setPoicName(dcpuhrDetailsModel.getPoicName());
		dcpuhrDetails.setPoicSex(dcpuhrDetailsModel.getPoicSex());
		
		dcpuhrDetails.setPonicContactNumber(dcpuhrDetailsModel.getPonicContactNumber());
		dcpuhrDetails.setPonicEmail(dcpuhrDetailsModel.getPonicEmail());
		dcpuhrDetails.setPonicName(dcpuhrDetailsModel.getPonicName());
		dcpuhrDetails.setPonicSex(dcpuhrDetailsModel.getPonicSex());
		
		dcpuhrDetails.setSw1ContactNumber(dcpuhrDetailsModel.getSw1ContactNumber());
		dcpuhrDetails.setSw1Email(dcpuhrDetailsModel.getSw1Email());
		dcpuhrDetails.setSw1Name(dcpuhrDetailsModel.getSw1Name());
		dcpuhrDetails.setSw1Sex(dcpuhrDetailsModel.getSw1Sex());
		
		dcpuhrDetails.setSw2ContactNumber(dcpuhrDetailsModel.getSw2ContactNumber());
		dcpuhrDetails.setSw2Email(dcpuhrDetailsModel.getSw2Email());
		dcpuhrDetails.setSw2Name(dcpuhrDetailsModel.getSw2Name());
		dcpuhrDetails.setSw2Sex(dcpuhrDetailsModel.getSw2Sex());
		
		dcpuhrDetails.setDcpuAreaId(userDetailModel.getAreaId());
		
		dcpuHRDetailsRepository.save(dcpuhrDetails);
	}

	@Override
	public DCPUHRDetailsModel getDCPUHRDetails() {
		
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		DCPUHRDetails dcpuhrDetails = dcpuHRDetailsRepository.findByDcpuAreaId(userDetailModel.getAreaId());
		DCPUHRDetailsModel dcpuhrDetailsModel = null;
		if(dcpuhrDetails != null){
			dcpuhrDetailsModel = new DCPUHRDetailsModel();
			
			dcpuhrDetailsModel.setAccountantContactNumber(dcpuhrDetails.getAccountantContactNumber());
			dcpuhrDetailsModel.setAccountantEmail(dcpuhrDetails.getAccountantEmail());
			dcpuhrDetailsModel.setAccountantName(dcpuhrDetails.getAccountantName());
			dcpuhrDetailsModel.setAccountantSex(dcpuhrDetails.getAccountantSex());
			
			dcpuhrDetailsModel.setAcdeoContactNumber(dcpuhrDetails.getAcdeoContactNumber());
			dcpuhrDetailsModel.setAcdeoEmail(dcpuhrDetails.getAcdeoEmail());
			dcpuhrDetailsModel.setAcdeoName(dcpuhrDetails.getAcdeoName());
			dcpuhrDetailsModel.setAcdeoSex(dcpuhrDetails.getAcdeoSex());
			
			dcpuhrDetailsModel.setCounsellorContactNumber(dcpuhrDetails.getCounsellorContactNumber());
			dcpuhrDetailsModel.setCounsellorEmail(dcpuhrDetails.getCounsellorEmail());
			dcpuhrDetailsModel.setCounsellorName(dcpuhrDetails.getCounsellorName());
			dcpuhrDetailsModel.setCounsellorSex(dcpuhrDetails.getCounsellorSex());
			
			dcpuhrDetailsModel.setDaContactNumber(dcpuhrDetails.getDaContactNumber());
			dcpuhrDetailsModel.setDaEmail(dcpuhrDetails.getDaEmail());
			dcpuhrDetailsModel.setDaName(dcpuhrDetails.getDaName());
			dcpuhrDetailsModel.setDaSex(dcpuhrDetails.getDaSex());
			
			dcpuhrDetailsModel.setDpoContactNumber(dcpuhrDetails.getDpoContactNumber());
			dcpuhrDetailsModel.setDpoEmail(dcpuhrDetails.getDpoEmail());
			dcpuhrDetailsModel.setDpoName(dcpuhrDetails.getDpoName());
			dcpuhrDetailsModel.setDpoSex(dcpuhrDetails.getDpoSex());
			
			dcpuhrDetailsModel.setLcpoavilContactNumber(dcpuhrDetails.getLcpoavilContactNumber());
			dcpuhrDetailsModel.setLcpoavilEmail(dcpuhrDetails.getLcpoavilEmail());
			dcpuhrDetailsModel.setLcpoavilName(dcpuhrDetails.getLcpoavilName());
			dcpuhrDetailsModel.setLcpoavilSex(dcpuhrDetails.getLcpoavilSex());
			
			dcpuhrDetailsModel.setOrw1ContactNumber(dcpuhrDetails.getOrw1ContactNumber());
			dcpuhrDetailsModel.setOrw1Email(dcpuhrDetails.getOrw1Email());
			dcpuhrDetailsModel.setOrw1Name(dcpuhrDetails.getOrw1Name());
			dcpuhrDetailsModel.setOrw1Sex(dcpuhrDetails.getOrw1Sex());
			
			dcpuhrDetailsModel.setOrw2ContactNumber(dcpuhrDetails.getOrw2ContactNumber());
			dcpuhrDetailsModel.setOrw2Email(dcpuhrDetails.getOrw2Email());
			dcpuhrDetailsModel.setOrw2Name(dcpuhrDetails.getOrw2Name());
			dcpuhrDetailsModel.setOrw2Sex(dcpuhrDetails.getOrw2Sex());
			
			dcpuhrDetailsModel.setOrw3ContactNumber(dcpuhrDetails.getOrw3ContactNumber());
			dcpuhrDetailsModel.setOrw3Email(dcpuhrDetails.getOrw3Email());
			dcpuhrDetailsModel.setOrw3Name(dcpuhrDetails.getOrw3Name());
			dcpuhrDetailsModel.setOrw3Sex(dcpuhrDetails.getOrw3Sex());
			
			dcpuhrDetailsModel.setOrw4ContactNumber(dcpuhrDetails.getOrw4ContactNumber());
			dcpuhrDetailsModel.setOrw4Email(dcpuhrDetails.getOrw4Email());
			dcpuhrDetailsModel.setOrw4Name(dcpuhrDetails.getOrw4Name());
			dcpuhrDetailsModel.setOrw4Sex(dcpuhrDetails.getOrw4Sex());
			
			dcpuhrDetailsModel.setOrw5ContactNumber(dcpuhrDetails.getOrw5ContactNumber());
			dcpuhrDetailsModel.setOrw5Email(dcpuhrDetails.getOrw5Email());
			dcpuhrDetailsModel.setOrw5Name(dcpuhrDetails.getOrw5Name());
			dcpuhrDetailsModel.setOrw5Sex(dcpuhrDetails.getOrw5Sex());
			
			dcpuhrDetailsModel.setOtherStaffContactNumber(dcpuhrDetails.getOtherStaffContactNumber());
			dcpuhrDetailsModel.setOtherStaffEmail(dcpuhrDetails.getOtherStaffEmail());
			dcpuhrDetailsModel.setOtherStaffName(dcpuhrDetails.getOtherStaffName());
			dcpuhrDetailsModel.setOtherStaffSex(dcpuhrDetails.getOtherStaffSex());
			
			dcpuhrDetailsModel.setPoicContactNumber(dcpuhrDetails.getPoicContactNumber());
			dcpuhrDetailsModel.setPoicEmail(dcpuhrDetails.getPoicEmail());
			dcpuhrDetailsModel.setPoicName(dcpuhrDetails.getPoicName());
			dcpuhrDetailsModel.setPoicSex(dcpuhrDetails.getPoicSex());
			
			dcpuhrDetailsModel.setPonicContactNumber(dcpuhrDetails.getPonicContactNumber());
			dcpuhrDetailsModel.setPonicEmail(dcpuhrDetails.getPonicEmail());
			dcpuhrDetailsModel.setPonicName(dcpuhrDetails.getPonicName());
			dcpuhrDetailsModel.setPonicSex(dcpuhrDetails.getPonicSex());
			
			dcpuhrDetailsModel.setSw1ContactNumber(dcpuhrDetails.getSw1ContactNumber());
			dcpuhrDetailsModel.setSw1Email(dcpuhrDetails.getSw1Email());
			dcpuhrDetailsModel.setSw1Name(dcpuhrDetails.getSw1Name());
			dcpuhrDetailsModel.setSw1Sex(dcpuhrDetails.getSw1Sex());
			
			dcpuhrDetailsModel.setSw2ContactNumber(dcpuhrDetails.getSw2ContactNumber());
			dcpuhrDetailsModel.setSw2Email(dcpuhrDetails.getSw2Email());
			dcpuhrDetailsModel.setSw2Name(dcpuhrDetails.getSw2Name());
			dcpuhrDetailsModel.setSw2Sex(dcpuhrDetails.getSw2Sex());
			
			dcpuhrDetailsModel.setId(dcpuhrDetails.getId());
		}
				
		return dcpuhrDetailsModel;
	}
	
}
