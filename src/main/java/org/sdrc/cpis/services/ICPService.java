package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.IndividualCarePlanAModel;
import org.sdrc.cpis.models.IndividualCarePlanBModel;
import org.sdrc.cpis.models.IndividualCarePlanCModel;
import org.sdrc.cpis.models.IndividualCarePlanDModel;
import org.springframework.transaction.annotation.Transactional;

public interface ICPService {

	@Transactional
	void saveICPA(IndividualCarePlanAModel individualCarePlanAModel);
	
	@Transactional
	void saveICPB(IndividualCarePlanBModel individualCarePlanBModel);
	
	@Transactional
	void saveICPC(IndividualCarePlanCModel individualCarePlanCModel) throws Exception;

	@Transactional
	void saveICPD(IndividualCarePlanDModel individualCarePlanDModel);
	
	IndividualCarePlanAModel getPersonalDetailsByChildId(String childId);
	
	List<IndividualCarePlanBModel> getProgressReports(String childId);
	
	IndividualCarePlanCModel getPreReleaseReport(String childId)throws Exception;
	
	IndividualCarePlanDModel getPostReleaseReport(String childId);
}
