package org.sdrc.cpis.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCIHumanResource;
import org.sdrc.cpis.domains.CCIHumanResourceOS;
import org.sdrc.cpis.domains.CCIHumanResourceSAA;
import org.sdrc.cpis.domains.CCITransactionDetails;
import org.sdrc.cpis.domains.CCITypeDetails;
import org.sdrc.cpis.domains.CCTSTypeDetails;
import org.sdrc.cpis.models.CCIHumanResourceModel;
import org.sdrc.cpis.models.CCIHumanResourceOSModel;
import org.sdrc.cpis.models.CCIHumanResourceSAAModel;
import org.sdrc.cpis.models.CCIInfoMapModel;
import org.sdrc.cpis.models.CasteModel;
import org.sdrc.cpis.models.GenderModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CCIHumanResourceOSRepository;
import org.sdrc.cpis.repository.CCIHumanResourceReposotory;
import org.sdrc.cpis.repository.CCIHumanResourceSAARepository;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.sdrc.cpis.repository.CCIInfoTransactionalRepository;
import org.sdrc.cpis.repository.CCTSTypeDetailsRepository;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.sdrc.cpis.repository.ChildInFitInstitutionRepository;
import org.sdrc.cpis.util.GeoLocationObject;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Pratyush Kumar Rath
 * pratyush@sdrc.co.in
 *
 */
@Service
public class CCIInfoServiceImpl implements CCIInfoService {
	@Autowired
	private CCIInfoRepository cciInfoRepository;
	
	@Autowired
	private ChildInFitInstitutionRepository childInFitInstitutionRepository;
	
	@Autowired
	private CCIInfoTransactionalRepository cciInfoTransactionalRepository;
	
	@Autowired
	private ChildDetailsRepository childDetailsRepository;
	
	@Autowired
	private CCTSTypeDetailsRepository cctsTypeDetailsRepository;
	
	@Autowired
	private CCIHumanResourceReposotory cciHumanResourceReposotory;
	
	@Autowired
	private CCIHumanResourceOSRepository cciHumanResourceOSRepository;
	
	@Autowired
	private CCIHumanResourceSAARepository cciHumanResourceSAARepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	Map<Integer, ValueObject> typeMap;
	
	public Map<Integer, ValueObject> getTypeMap(){
	    List<CCTSTypeDetails> typeDetails= cctsTypeDetailsRepository.getAllTypeDetails();
		Map<Integer, ValueObject> map=new HashMap<>();
		for (CCTSTypeDetails cctsTypeDetails : typeDetails) {
			ValueObject obj=new ValueObject();
			obj.setId(cctsTypeDetails.getTypeDetailsId());
			obj.setName(cctsTypeDetails.getTypeDetailsName());
			map.put(cctsTypeDetails.getTypeDetailsId(), obj);
		}
		return map;
	}
	
	public ValueObject getValueObject(Integer id, String type){
		ValueObject obj = new ValueObject();
		AreaDetails areaDetails = null;
		CCIDetails cciDetails = null;
		
		switch (type) {
		case "AreaDetails":
			 areaDetails = areaRepository.fetchAreaById(id);
			 obj.setId(areaDetails.getAreaId());
			 obj.setName(areaDetails.getAreaName());
			break;
			
		case "CCIName":
			cciDetails = cciInfoRepository.getCciById(id);
			obj.setId(cciDetails.getCciId());
			obj.setName(cciDetails.getCciName());
			obj.setTypeId(cciDetails.getCciTypeDetails().getTypeId());
			break;

		default:
			break;
		}
	   
			
		return obj;
	}
	
	
	@Override
	public List<CCIInfoMapModel> fetchCCIMstData(){
//		Map<Integer,List<CCIInfoMapModel>> mstMap=new HashMap<Integer, List<CCIInfoMapModel>>();
		List<Object[]> cciMstData = cciInfoRepository.fetchCCIMstData();
		List<CCITypeDetails> cciTypes=cciInfoRepository.getCCITypes();
		List<Object[]> genderWiseData= childDetailsRepository.getGenderWiseData();
		Map<Integer, GenderModel> genderMap = new HashMap<Integer, GenderModel>();
		for (Object[] objects : genderWiseData) {
			GenderModel genderModel=new GenderModel();
			genderModel.setBoysCount(null==objects[1]?0:Integer.parseInt(String.valueOf(objects[1])));
			genderModel.setGirlsCount(null==objects[2]?0:Integer.parseInt(String.valueOf(objects[2])));
			genderModel.setThirdGenderCount(null==objects[3]?0:Integer.parseInt(String.valueOf(objects[3])));
			genderModel.setTotal(genderModel.getBoysCount()+genderModel.getGirlsCount()+genderModel.getThirdGenderCount());
			genderMap.put(Integer.parseInt(String.valueOf(objects[0])), genderModel);
		}
//		for (CCITypeDetails cciTypeDetails : cciTypes) {
//			mstMap.put(cciTypeDetails., null);
//		}
		typeMap=getTypeMap();
		List<CCIInfoMapModel> cciModels=new ArrayList<CCIInfoMapModel>();
		int i=0;
		for (Object[] objects : cciMstData) {
			i++;
			GeoLocationObject geoLocationObject = new GeoLocationObject();
			geoLocationObject.setLatitude(Double.parseDouble(String.valueOf(objects[3])));
			geoLocationObject.setLongitude(Double.parseDouble(String.valueOf(objects[2])));
			geoLocationObject.setAreaID(String.valueOf(objects[18]));
			geoLocationObject.setShowWindow(false);
			geoLocationObject.setAltitude(0.0);
			geoLocationObject.setDataValue(0.0);
			geoLocationObject.setImages("");
			geoLocationObject.setIcon("");
			geoLocationObject.setTitle(String.valueOf(objects[1]));
			geoLocationObject.setAddress(String.valueOf(objects[20]));
			geoLocationObject.setId(i);
			geoLocationObject.setCciType(Integer.parseInt(String.valueOf(objects[19])));
			Map<String,Object> options = new HashMap<String, Object>(2);
			options.put("opacity", 1.0);
//			options.put("clickable", true);
			geoLocationObject.setOptions(options);
			
			
			CCIInfoMapModel cciInfoMapModel=new CCIInfoMapModel();
			cciInfoMapModel.setCciId(Integer.parseInt(String.valueOf(objects[0])));
			cciInfoMapModel.setName(String.valueOf(objects[1]));
			cciInfoMapModel.setLongitude(null==objects[2]?null:String.valueOf(objects[2]));
			cciInfoMapModel.setLatitude(null==objects[3]?null:String.valueOf(objects[3]));
			cciInfoMapModel.setAddress(null==objects[4]?null:String.valueOf(objects[4]));
			cciInfoMapModel.setTotalCapacity(null==objects[5]?null:Integer.parseInt(String.valueOf(objects[5])));
			cciInfoMapModel.setBoysCapacity(null==objects[6]?null:Integer.parseInt(String.valueOf(objects[6])));
			cciInfoMapModel.setGirlsCapacity(null==objects[7]?null:Integer.parseInt(String.valueOf(objects[7])));
			cciInfoMapModel.setTotal_rooms_value(null==objects[8]?null:Integer.parseInt(String.valueOf(objects[8])));
			cciInfoMapModel.setNo_of_room_boys_value(null==objects[9]?null:Integer.parseInt(String.valueOf(objects[9])));
			cciInfoMapModel.setNo_of_room_girls_value(null==objects[10]?null:Integer.parseInt(String.valueOf(objects[10])));
			cciInfoMapModel.setNo_of_toilets_value(null==objects[11]?null:Integer.parseInt(String.valueOf(objects[11])));
			cciInfoMapModel.setNo_of_toilet_boys_value(null==objects[12]?null:Integer.parseInt(String.valueOf(objects[12])));
			cciInfoMapModel.setNo_of_toilet_girls_value(null==objects[13]?null:Integer.parseInt(String.valueOf(objects[13])));
			cciInfoMapModel.setNo_of_children_value(null==objects[14]?null:Integer.parseInt(String.valueOf(objects[14])));
			cciInfoMapModel.setProper_elec_value(null==objects[15]?false:Boolean.parseBoolean(String.valueOf(objects[15])));
			cciInfoMapModel.setDrinking_water_facility_value(null==objects[16]?false:Boolean.parseBoolean(String.valueOf(objects[16])));
			cciInfoMapModel.setBoundary_wall_value(null==objects[17]?false:Boolean.parseBoolean(String.valueOf(objects[17])));
			cciInfoMapModel.setDistrict(null==objects[18]?null:String.valueOf(objects[18]));
			cciInfoMapModel.setCciType(null==objects[19]?null:Integer.parseInt(String.valueOf(objects[19])));
			cciInfoMapModel.setTotalChildrenLiving(genderMap.get(Integer.parseInt(String.valueOf(objects[0])))==null?0:genderMap.get(Integer.parseInt(String.valueOf(objects[0]))).getTotal());
			cciInfoMapModel.setTotalBoysLiving(genderMap.get(Integer.parseInt(String.valueOf(objects[0])))==null?0:genderMap.get(Integer.parseInt(String.valueOf(objects[0]))).getBoysCount());
			cciInfoMapModel.setTotalGirlsLiving(genderMap.get(Integer.parseInt(String.valueOf(objects[0])))==null?0:genderMap.get(Integer.parseInt(String.valueOf(objects[0]))).getGirlsCount());
			cciInfoMapModel.setGeoLocationObject(geoLocationObject);
			cciInfoMapModel.setContact(null==objects[4]?null:String.valueOf(objects[4]));
			
			cciInfoMapModel.setBuilding_Type(objects[24] == null  ? null : typeMap.get(Integer.parseInt(String.valueOf(objects[24]))));
			cciInfoMapModel.setArea_of_Building(objects[25] == null ? null : Double.parseDouble(String.valueOf(objects[25])));
			cciInfoMapModel.setStatus_of_Building(objects[26] == null  ? null : typeMap.get(Integer.parseInt(String.valueOf(objects[26]))));
			cciInfoMapModel.setPower_Backupfacility_AllRooms(null==objects[27]?false:Boolean.parseBoolean(String.valueOf(objects[27])));
			cciInfoMapModel.setSeparate_Toiletsfor_Staff(null==objects[28]?false:Boolean.parseBoolean(String.valueOf(objects[28])));
			cciInfoMapModel.setNumber_of_ContactPoints(objects[29] == null ? null : Integer.parseInt(String.valueOf(objects[29])));
			cciInfoMapModel.setTotalThirdGenderLiving(genderMap.get(Integer.parseInt(String.valueOf(objects[0])))==null?0:genderMap.get(Integer.parseInt(String.valueOf(objects[0]))).getThirdGenderCount());
			
			for (CCITypeDetails cciTypeDetails : cciTypes) {
				if(cciTypeDetails.getTypeId()==Integer.parseInt(String.valueOf(objects[19]))){
					cciInfoMapModel.setCciTypeDetails(cciTypeDetails);
				}
			}
			
			cciModels.add(cciInfoMapModel);
						
			/*if(mstMap.containsKey(cciInfoMapModel.getCciType())){
				mstMap.get(cciInfoMapModel.getCciType()).add(cciInfoMapModel);
			}
			else{
				cciModels=new ArrayList<CCIInfoMapModel>();
				cciModels.add(cciInfoMapModel);
				mstMap.put(cciInfoMapModel.getCciType(), cciModels);
			}*/
		}
//		System.out.println(mstMap);

				
		
		return cciModels;
	}
	
	@Transactional
	@Override
	public List<CCITransactionDetails> updateCCITransactional(){
		List<Object[]> cciDataList=childDetailsRepository.findCciData();
		List<CCITransactionDetails> cciTransactionDetailList = cciInfoTransactionalRepository.getAllTransactionalData();
		Map<Integer,CCITransactionDetails> cciTransactionDetailMap=new LinkedHashMap<Integer,CCITransactionDetails>();
		for (CCITransactionDetails transactionDetails : cciTransactionDetailList) {
			cciTransactionDetailMap.put(transactionDetails.getCciDetails().getCciId(), transactionDetails);
		}
		
		List<CCITransactionDetails> newTransactionList = new ArrayList<>();
		CCIDetails cciDetails = new CCIDetails();
		System.out.println(cciDataList);
		CCITransactionDetails cciTransactionDetails=null;
		for (Object[] cciData : cciDataList) {
			cciTransactionDetails = new CCITransactionDetails();
			cciDetails = new CCIDetails();

			if(cciTransactionDetailMap.containsKey(Integer.parseInt(cciData[0].toString())))
			{
					cciTransactionDetails.setId(cciTransactionDetailMap.get(cciData[0]).getId());
			}
				cciDetails.setCciId(null==cciData[0]?null:Integer.valueOf(cciData[0].toString()));
				cciTransactionDetails.setCciDetails(cciDetails);
				cciTransactionDetails.setTotalChildren(null==cciData[1]?null:Integer.valueOf(cciData[1].toString()));
				cciTransactionDetails.setTotalBoys(null==cciData[2]?null:Integer.valueOf(cciData[2].toString()));
				cciTransactionDetails.setTotalGirls(null==cciData[3]?null:Integer.valueOf(cciData[3].toString()));
				cciTransactionDetails.setTotalThirdGender(null==cciData[4]?null:Integer.valueOf(cciData[4].toString()));
				cciTransactionDetails.setUpdatedTime(new Date(new java.util.Date().getTime()));
				newTransactionList.add(cciTransactionDetails);
		
		}
		
		cciInfoTransactionalRepository.save(newTransactionList);
		return cciTransactionDetailList;
	}
	

	@Override
	public CCIInfoMapModel getCCIInfo() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<CCITypeDetails> getCCITypes() {
		// TODO Auto-generated method stub
		return cciInfoRepository.getCCITypes();
	}


	@Override
	public List<Object> getAllHRDetails() {
		
		List<Object> humanResourceList = new ArrayList<Object>();
		List<CCIHumanResource> hrList = cciHumanResourceReposotory.findAll();
		List<CCIHumanResourceOS> hrOSList = cciHumanResourceOSRepository.findAll();
		List<CCIHumanResourceSAA> hrSaaList = cciHumanResourceSAARepository.findAll();
		
		if(hrList != null && hrList.size() > 0){
			for(CCIHumanResource cciHumanResource : hrList){
				CCIHumanResourceModel cciHumanResourceModel = new CCIHumanResourceModel();
				
				cciHumanResourceModel.setArtCraftMusicTeacher(cciHumanResource.getArtCraftMusicTeacher());
				cciHumanResourceModel.setAsstCook(cciHumanResource.getAsstCook());
				cciHumanResourceModel.setCareTaker_VocationalInstructor(cciHumanResource.getCareTaker_VocationalInstructor());
				cciHumanResourceModel.setCook(cciHumanResource.getCook());
				cciHumanResourceModel.setCounselor(cciHumanResource.getCounselor());
				cciHumanResourceModel.setDistrict(cciHumanResource.getNameOfDistrict() == null ? null : getValueObject(cciHumanResource.getNameOfDistrict(),"AreaDetails"));
				cciHumanResourceModel.setEducator(cciHumanResource.getEducator());
				cciHumanResourceModel.setFemale_Nurse(cciHumanResource.getFemale_Nurse());
				cciHumanResourceModel.setHouseKeeper(cciHumanResource.getHouseKeeper());
				cciHumanResourceModel.setHouseMother_Father(cciHumanResource.getHouseMother_Father());
	//			cciHumanResourceModel.setId(cciHumanResource.get);
				cciHumanResourceModel.setMbbsDoctor(cciHumanResource.getMbbsDoctor());
				cciHumanResourceModel.setNameOfCCI(cciHumanResource.getNameOfCCI() == null ? null : getValueObject(cciHumanResource.getNameOfCCI(),"CCIName"));
				cciHumanResourceModel.setOfficerIncharge_Superintendent(cciHumanResource.getOfficerIncharge_Superintendent());
				cciHumanResourceModel.setParaMedicalStaff(cciHumanResource.getParaMedicalStaff());
				cciHumanResourceModel.setPo_so_cwo(cciHumanResource.getPo_so_cwo());
				cciHumanResourceModel.setPtInstructorYogaTeacher(cciHumanResource.getPtInstructorYogaTeacher());
				cciHumanResourceModel.setSpecial_Educator_Therapist(cciHumanResource.getSpecial_Educator_Therapist());
				cciHumanResourceModel.setStoreKeeperCumAccountan(cciHumanResource.getStoreKeeperCumAccountan());
				cciHumanResourceModel.setOthers(cciHumanResource.getOthers());
				humanResourceList.add(cciHumanResourceModel);
			}
		}
		
		if(hrOSList != null && hrOSList.size() > 0){
			for(CCIHumanResourceOS cciHumanResourceOS : hrOSList){
				CCIHumanResourceOSModel cciHumanResourceOSModel = new CCIHumanResourceOSModel();
				
				cciHumanResourceOSModel.setCareGiver_Cum_BridgeCourse_Educator(cciHumanResourceOS.getCareGiver_Cum_BridgeCourse_Educator());
				
				cciHumanResourceOSModel.setDistrict(cciHumanResourceOS.getNameOfDistrict() == null ? null : 
					getValueObject(cciHumanResourceOS.getNameOfDistrict(),"AreaDetails"));
				
				cciHumanResourceOSModel.setHelper_For_Cleaning_Cooking(cciHumanResourceOS.getHelper_For_Cleaning_Cooking());
	//			cciHumanResourceOSModel.setId(cciHumanResourceOS.get);
				
				cciHumanResourceOSModel.setNameOfOpenShelter(cciHumanResourceOS.getNameOfOpenShelter() == null ? null :
					getValueObject(cciHumanResourceOS.getNameOfOpenShelter(),"CCIName"));
				
				cciHumanResourceOSModel.setOutReach_Worker(cciHumanResourceOS.getOutReach_Worker());
				cciHumanResourceOSModel.setProjectCoordinator_cum_Counselor(cciHumanResourceOS.getProjectCoordinator_cum_Counselor());
				cciHumanResourceOSModel.setSocial_Worker(cciHumanResourceOS.getSocial_Worker());
				cciHumanResourceOSModel.setOthers(cciHumanResourceOS.getOthers());
				humanResourceList.add(cciHumanResourceOSModel);
			}
		}
		
		if(hrSaaList != null && hrSaaList.size() > 0){
			for(CCIHumanResourceSAA cciHumanResourceSAA : hrSaaList){
				CCIHumanResourceSAAModel cciHumanResourceSAAModel = new CCIHumanResourceSAAModel();
				
				cciHumanResourceSAAModel.setAyah(cciHumanResourceSAA.getAyah());
				cciHumanResourceSAAModel.setChowkidar(cciHumanResourceSAA.getChowkidar());
				
				cciHumanResourceSAAModel.setDistrict(cciHumanResourceSAA.getNameOfDistrict() == null ? null : 
					getValueObject(cciHumanResourceSAA.getNameOfDistrict(),"AreaDetails"));
//				cciHumanResourceSAAModel.setId(cciHumanResourceSAA.get);
				
				cciHumanResourceSAAModel.setNameOfSAA(cciHumanResourceSAA.getNameOfSAA() == null ? null : 
					getValueObject(cciHumanResourceSAA.getNameOfSAA(),"CCIName"));
				
				cciHumanResourceSAAModel.setNurse(cciHumanResourceSAA.getNurse());
				cciHumanResourceSAAModel.setPartTime_Doctor_Child_Specialist(cciHumanResourceSAA.getPartTime_Doctor_Child_Specialist());
				cciHumanResourceSAAModel.setProgramme_Manager(cciHumanResourceSAA.getProgramme_Manager());
				cciHumanResourceSAAModel.setProject_Coordinator(cciHumanResourceSAA.getProject_Coordinator());
				cciHumanResourceSAAModel.setSocial_worker_Cum_Early_ChildHood_Educator(cciHumanResourceSAA.getSocial_worker_Cum_Early_ChildHood_Educator());
				cciHumanResourceSAAModel.setOthers(cciHumanResourceSAA.getOthers());
				humanResourceList.add(cciHumanResourceSAAModel);
			}
		}
		return humanResourceList;
	}
	
	@Override
	public List<CasteModel> findByCaste(){
		List<Object[]> casteWiseData= childDetailsRepository.getCasteWiseData();
		List<CasteModel> casteModels=new ArrayList<>();
		for (Object[] objects : casteWiseData) {
			CasteModel casteModel=new CasteModel();
			casteModel.setCciId(null==objects[0]?null:(Integer) objects[0]);
			casteModel.setCategory(null==objects[1]?null:(String) objects[1]);
			casteModel.setBoys(null==objects[2]?"0":(String.valueOf(objects[2])));
			casteModel.setGirls(null==objects[3]?"0":(String.valueOf(objects[3])));
			casteModel.setThirdGender(null==objects[4]?"0":(String.valueOf(objects[4])));
			casteModels.add(casteModel);
		}
		return casteModels;
	}

	@Override
	public List<CasteModel> findByAge() {
		List<Object[]> casteWiseData= childDetailsRepository.getAgeWiseData();
		List<CasteModel> casteModels=new ArrayList<>();
		for (Object[] objects : casteWiseData) {
			CasteModel casteModel=new CasteModel();
			casteModel.setCciId(null==objects[0]?null:(Integer) objects[0]);
			casteModel.setCategory(null==objects[1]?null:(String) objects[1]);
			casteModel.setBoys(null==objects[2]?"0":(String.valueOf(objects[2])));
			casteModel.setGirls(null==objects[3]?"0":(String.valueOf(objects[3])));
			casteModel.setThirdGender(null==objects[4]?"0":(String.valueOf(objects[4])));
			casteModels.add(casteModel);
		}
		return casteModels;
	}
}
