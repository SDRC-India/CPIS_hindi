package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.CICLChildCareInstitutionPendingInquiry;

public interface CICLChildCareInstitutionPendingInquiryRepository {
	@Transactional
	CICLChildCareInstitutionPendingInquiry save(CICLChildCareInstitutionPendingInquiry careInstitutionPendingInquiry);
	
	CICLChildCareInstitutionPendingInquiry getByChildId(String childId);

	List<CICLChildCareInstitutionPendingInquiry> findAllByChildId(String childId);

	List<CICLChildCareInstitutionPendingInquiry> findCICLChildCareInstitutionPendingInquiryWithinTimePeriod(
			Date startDate, Date endDate);
}
