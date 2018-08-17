package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.CICLChildCareInstitutionPendingInquiry;
import org.sdrc.cpis.repository.CICLChildCareInstitutionPendingInquiryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataCICLChildCareInstitutionPendingInquiryRepository
		extends CICLChildCareInstitutionPendingInquiryRepository,Repository<CICLChildCareInstitutionPendingInquiry, Integer> {
	
	@Override
	@Query("select cciData from CICLChildCareInstitutionPendingInquiry cciData where cciData.childId.childId= :childId")
	CICLChildCareInstitutionPendingInquiry getByChildId(@Param("childId") String childId);
	
	@Override
	@Query("select cciData from CICLChildCareInstitutionPendingInquiry cciData where cciData.childId.childId= :childId")
	List<CICLChildCareInstitutionPendingInquiry> findAllByChildId(@Param("childId")String childId);

	@Override
	@Query("SELECT child FROM CICLChildCareInstitutionPendingInquiry child WHERE "
			+ " child.dateOfOrder BETWEEN :startDate AND :endDate ")
	public List<CICLChildCareInstitutionPendingInquiry> findCICLChildCareInstitutionPendingInquiryWithinTimePeriod(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
