package org.sdrc.cpis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.ChildDetails;


public interface ChildDetailsRepository {
	
	Integer findLastRecord();

	@Transactional
	ChildDetails save(ChildDetails childDetails);
	
	List<ChildDetails> findAll();
	
	ChildDetails findChildById(String childId);
//	List<ChildDetails> findByChildName(String childName);
//	List<ChildDetails> findByChildNameAndChildId(String childName, String childId);
	
	Integer findByChildId(String childId);
	
	Boolean updateByChildId(String childName,Integer childSex,Integer age,String childId,Integer childDistrict);

	List<ChildDetails> findCNCPchilds(Integer areaId);
	
	List<ChildDetails> findCICLChilds(Integer areaId);

	List<Object[]> findCciData();

	List<ChildDetails> findChildsByDivision(Integer areaId);

	List<ChildDetails> findChildsByDistrict(Integer areaId);

	List<ChildDetails> findByCciDetailsCciId(Integer cciId);

	List<Object[]> getCasteWiseData();
	
	List<ChildDetails> findByChildIdIsIn(List<String> childIds);
	
	void updateCurrentAgeByChildId(Integer currentAge,String childId);

	List<Object[]> getGenderWiseData();

	List<Object[]> getAgeWiseData();

}
