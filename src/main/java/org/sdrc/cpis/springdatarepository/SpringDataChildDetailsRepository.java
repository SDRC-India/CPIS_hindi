package org.sdrc.cpis.springdatarepository;

import java.util.List;

import org.sdrc.cpis.domains.ChildDetails;
import org.sdrc.cpis.repository.ChildDetailsRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataChildDetailsRepository extends 
	ChildDetailsRepository,Repository<ChildDetails, Integer> {
	
	@Override
	@Query("select max(child.id) from ChildDetails child")
	Integer findLastRecord();
	
//	@Override
//	@Query("select child from ChildDetails child")
//	List<ChildDetails> findAll();
//	
	@Override
	@Query("select child from ChildDetails child where child.childId = :childId")
	ChildDetails findChildById(@Param("childId")String childId);
	

	
	
	@Override
	@Query("select child.id from ChildDetails child where child.childId=:childId")
	Integer findByChildId(@Param("childId") String childId); 
	
	
	/*@Override
	@Query("UPDATE ChildDetails child SET child.childName=:childName,child.age=:age, child.childSex=:childSex,child.childDistrict=:childDistrict WHERE child.childId=:childId")
     Boolean updateByChildId(@Param("childName")String childName,@Param("childSex") Integer childSex,@Param("age") Integer age,@Param("childId") String childId);*/
	
	
	@Override
	@Query("UPDATE ChildDetails child SET child.childName=:childName,child.age=:age, child.childSex=:childSex,child.childDistrict=:childDistrict WHERE child.childId=:childId")
     Boolean updateByChildId(@Param("childName")String childName, @Param("childSex")Integer childSex, @Param("age")Integer age,@Param("childId") String childId,
			@Param("childDistrict")Integer childDistrict);
	
	@Override
	@Query("select child from ChildDetails child where child.programType = 0 AND child.childDistrict.areaId = :areaId")
	List<ChildDetails> findCNCPchilds(@Param("areaId")Integer areaId);
	
	@Override
	@Query("select child from ChildDetails child where child.programType = 1 AND child.childDistrict.areaId = :areaId")
	List<ChildDetails> findCICLChilds(@Param("areaId")Integer areaId);
	
	@Override
	@Query("select child from ChildDetails child where child.childDistrict.areaId = :areaId")
	List<ChildDetails> findChildsByDistrict(@Param("areaId")Integer areaId);
	
	@Override
	@Query(value="SELECT * FROM child_details AS cd WHERE cd.child_district in (SELECT ad1.id FROM area_details AS ad1 WHERE ad1.parent_area_id= :areaId)",nativeQuery=true)
	List<ChildDetails> findChildsByDivision(@Param("areaId")Integer areaId);

	@Override
	@Query("select cd.cciDetails.cciId AS cci_id, count(cd.cciDetails.cciId) AS count,sum(case when cd.childSex=144 then 1 end) AS boys, "+
			"sum(case when cd.childSex=145 then 1 end) AS girls,sum(case when cd.childSex=146 then 1 end) AS third_gender "+
			"from ChildDetails cd,CCIDetails ccd where cd.cciDetails.cciId=ccd.cciId GROUP BY cd.cciDetails.cciId")
	List<Object[]> findCciData();
	
	@Override
	@Query("select cd.cciDetails.cciId AS cci_id,(case when cd.sirChildCast=21 then 'General' "+
			"when cd.sirChildCast=22 then 'OBC' "+
			"when cd.sirChildCast=23 then 'SC' "+
			"when cd.sirChildCast=24 then 'ST' "+
			"when cd.sirChildCast=25 then 'EBC' "+
			"when cd.sirChildCast=26 then 'NOT SPECIFIED' "+
			"when cd.sirChildCast IS NULL then 'NA' end) AS caste,sum(case when cd.childSex=144 then 1 end) AS boys, "+
			"sum(case when cd.childSex=145 then 1 end) AS girls,sum(case when cd.childSex=146 then 1 end) AS third_gender "+
			"from ChildDetails cd,CCIDetails ccd where cd.cciDetails.cciId=ccd.cciId GROUP BY cd.sirChildCast,cd.cciDetails.cciId")
//	@Query(value="select (case when cd.sir_child_cast=21 then 'General' when cd.sir_child_cast=22 then 'OBC' when cd.sir_child_cast=23 then 'SC' when cd.sir_child_cast=24 then 'ST' when cd.sir_child_cast=25 then 'EBC' when cd.sir_child_cast=26 then 'NOT SPECIFIED'when cd.sir_child_cast IS NULL then 'NA' end) as caste,sum(case when cd.child_sex=144 then 1 end) AS boys, sum(case when cd.child_sex=145 then 1 end) AS girls,sum(case when cd.child_sex=146 then 1 end) AS third_gender from child_details cd GROUP BY cd.sir_child_cast")
	List<Object[]> getCasteWiseData();
	
	@Override
	@Modifying
	@Query("UPDATE ChildDetails child SET child.currentAge=:currentAge WHERE child.childId=:childId")
    void updateCurrentAgeByChildId(@Param("currentAge")Integer currentAge,@Param("childId") String childId);
	
	@Override
	@Query("select cd.cciDetails.cciId AS cci_id,sum(case when cd.childSex=144 then 1 else 0 end) AS boys, "+
			"sum(case when cd.childSex=145 then 1 else 0 end) AS girls,sum(case when cd.childSex=146 then 1 else 0 end) AS third_gender "+
			"from ChildDetails cd,CCIDetails ccd where cd.cciDetails.cciId=ccd.cciId GROUP BY cd.cciDetails.cciId")
	List<Object[]> getGenderWiseData();
	
	
	@Override
/*	@Query("select cd.cciDetails.cciId AS cci_id,(case "+
			"when cd.currentAge<10 then 'Below 10' "+
			"when cd.currentAge>=10 AND cd.currentAge<=14 then '10 to 14' "+
			"when cd.currentAge>14 AND cd.currentAge<=18 then '15 to 18' "+
			"when cd.currentAge>18 then 'Above 18' "+
			"when cd.currentAge IS NULL then 'NA' end) AS age,sum(case when cd.childSex=144 then 1 end) AS boys, "+
			"sum(case when cd.childSex=145 then 1 end) AS girls,sum(case when cd.childSex=146 then 1 end) AS third_gender "+
			"from ChildDetails cd,CCIDetails ccd where cd.cciDetails.cciId=ccd.cciId  GROUP BY cd.cciDetails.cciId,age")*/
	@Query(value="select cd.cci_id AS cci_id,(case when cd.child_current_age<10 then 'below 10' "
			+ "when cd.child_current_age>=10 AND cd.child_current_age<=14 then '10-14' "
			+ "when cd.child_current_age>14 AND cd.child_current_age<=18 then '14-18' "
			+ "when cd.child_current_age>18 then 'above 18' end) AS age,"
			+ "sum(case when cd.child_sex=144 then 1 end) AS boys, sum(case when cd.child_sex=145 then 1 end) AS girls,"
			+ "sum(case when cd.child_sex=146 then 1 end) AS third_gender from child_details cd,cci_details ccd where cd.cci_id=ccd.id "
			+ "GROUP BY age,cd.cci_id order by cd.cci_id",nativeQuery=true)
	public List<Object[]> getAgeWiseData();
}
