package org.sdrc.cpis.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.sdrc.cpis.domains.FollowUpForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface FollowUpRepository {
	
	@Transactional
	public void save(FollowUpForm followUpForm);
	
	
	public List<FollowUpForm> findByChildIdChildId(String childId);


	public List<FollowUpForm> findByDateOfVisitIsBetween(Date startDate,
			Date endDate);
	
	public List<FollowUpForm> latestFollowUpForms(Date startDate,
			Date endDate);
	
	public Long countByChildId(String childId);
	

}
