package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.CICLCaseMoniteringSheetModel;
import org.sdrc.cpis.models.CICLChildCareInstitutionPendingInquiryModel;
import org.sdrc.cpis.models.CICLSupervisionOrderModel;
import org.springframework.transaction.annotation.Transactional;


public interface CICLInterimService {
	
	public String saveCICLSupervisionOrder(CICLSupervisionOrderModel ciclSupervisionOrderModel);
	
	public String saveCICLChildCareInstitutionPendingInquiry(CICLChildCareInstitutionPendingInquiryModel cICLChildCareInstitutionPendingInquiryModel);
	
	@Transactional
	public String saveCICLCaseMoniteringSheet(CICLCaseMoniteringSheetModel ciclCaseMoniteringSheetModel) throws Exception;

	public List<CICLCaseMoniteringSheetModel> getCiclCaseMonitoringByChildId(String childId) throws Exception;

//	public CICLSupervisionOrderModel getSupervisionOrderByChildId(String childId);

//	public CICLChildCareInstitutionPendingInquiryModel getCCIinquiryByChildId(String childId);

	public List<Object> fetchAll(String childId);
    
}
