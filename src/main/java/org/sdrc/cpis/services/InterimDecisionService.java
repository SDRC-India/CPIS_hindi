package org.sdrc.cpis.services;


import java.util.List;

import org.sdrc.cpis.models.CaseMonitoringModel;
import org.sdrc.cpis.models.ChildDetailsModel;
import org.sdrc.cpis.models.ChildInFitInstitutionModel;
import org.sdrc.cpis.models.FitPersonDetailModel;
import org.sdrc.cpis.models.FosterCareDetailsModel;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.transaction.annotation.Transactional;

public interface InterimDecisionService {

	@Transactional
	void saveChildInterimData(CaseMonitoringModel caseMonitoringModel) throws Exception;
	
	@Transactional
	void saveFosterCareDetails(FosterCareDetailsModel fosterCareDetailsModel);
	
	ChildDetailsModel fetchChildByChildId(String childId);
	
	@Transactional
	void saveChildInFitInstituion(ChildInFitInstitutionModel chidlInFitInstitutionModel);

	@Transactional
	void saveFitPersonDetails(FitPersonDetailModel fitPersonDetailModel);

	List<Object>  fetchAllInterimOrder(String childId);
	
	List<CaseMonitoringModel> fetchCaseMonitoring(String childId) throws Exception;
	
	List<ValueObject> getAreaWiseCciList(Integer areaId);

	List<FosterCareDetailsModel> findAllFosterOrders(String childId);

	List<ValueObject> getAllCciList();
	
	/*@Transactional
	void updateFosterCareDetails(FosterCareDetailsModel fosterCareDetailsModel);
	@Transactional
	void updateChildInFitInstituion(ChildInFitInstitutionModel chidlInFitInstitutionModel);

	@Transactional
	void updateFitPersonDetails(FitPersonDetailModel fitPersonDetailModel);*/
	
}
