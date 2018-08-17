package org.sdrc.cpis.services;

import org.sdrc.cpis.models.AfterCarePlacementOrderModel;
import org.sdrc.cpis.models.CaseSummaryCWCModel;
import org.sdrc.cpis.models.OrderModel;
import org.sdrc.cpis.models.SponsorshipModel;
import org.springframework.transaction.annotation.Transactional;

public interface FinalOrderService {

	@Transactional
	void saveCaseSummaryCWCData(CaseSummaryCWCModel caseSummaryCWCModel) throws Exception;
	
	CaseSummaryCWCModel getAllCaseSummary(String childId) throws Exception;
	
	@Transactional
	boolean savePdf(OrderModel orderModel) throws Exception;
	
	void saveSponsorshipOrder(SponsorshipModel sponsorshipModel);

	void saveAfterCareData(AfterCarePlacementOrderModel afterCarePlacementOrderModel);
	
	OrderModel getChildEscortAndRehab(String childId);
	
	SponsorshipModel getSponsorshipData(String childId);
	
	AfterCarePlacementOrderModel getAfterCareData(String childId);
}
