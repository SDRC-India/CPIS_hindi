package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.CCIDetails;
import org.sdrc.cpis.domains.CCITypeDetails;
import org.sdrc.cpis.repository.CCIInfoRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCCIInfoRepository extends Repository<CCIDetails, Integer>,
		CCIInfoRepository {

	@Override
	@Query(value="select cid.id,cid.name,cid.longitude,cid.latitude,cid.contact,cid.Total_capacity,"
			+ "cid.Boys_capacity,cid.Girls_capacity,cid.total_no_of_rooms,"
			+ "cid.rooms_for_boys,cid.rooms_for_girls,cid.total_no_of_toilets,cid.toilets_for_boys,"
			+ "cid.Toilets_For_Girls,cid.Children_Living_In_Single_Room,cid.Proper_Electricity,"
			+ "cid.Proper_Drinking_Water_Facility,"
			+ "cid.boundary_wall,cid.District,cid.CCIType,cid.address,"
			+ "ctd.total_children,ctd.total_boys,"
			+ "ctd.total_girls,cid.building_type,cid.area_of_building,cid.status_of_building,"
			+ "cid.power_backupfacility_allrooms,cid.separate_toiletsfor_staff,"
			+ "cid.number_of_contactpoints,ctd.third_gender from cci_details cid "
			+"INNER JOIN cci_transactional_details ctd ON cid.id=ctd.cci_id",nativeQuery=true)
	List<Object[]> fetchCCIMstData();
	
	@Override
	@Query("select ct from CCITypeDetails ct")
	List<CCITypeDetails> getCCITypes();
	
	@Override
	@Query("select cd from CCIDetails cd")
	List<CCIDetails> fetchAllCCIs();
	
	@Override
	@Query("select cd from CCIDetails cd where cd.areaDetails= :areaId")
	List<CCIDetails> getAreaWiseCciList(@Param("areaId")Integer areaId);
	
	@Override
	@Query("select cd from CCIDetails cd where cd.cciId= :cciId")
	CCIDetails getCciById(@Param("cciId")Integer cciId);
	
	@Override
	@Query("SELECT  child FROM CCIDetails child")
	public List<CCIDetails> getAllCCIDetails();
	
	@Override
	@Query(value="select cci.id,count(cd) from cci_details cci join child_details cd on cd.cci_id=cci.id group by cci.id",nativeQuery=true)
	public List<Object[]> getCciWiseChildCount();
}
