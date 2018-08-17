package org.sdrc.cpis.springdatarepository;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.domains.FollowUpForm;
import org.sdrc.cpis.repository.FollowUpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataFollowUpRepository extends FollowUpRepository,
		Repository<FollowUpForm, Integer> {
	
	@Query(value="select f.* from follow_up_form f inner join(select child_id, max(date_of_visit) as latest_date from follow_up_form ff1 group by child_id) as foo on f.child_id = foo.child_id and f.date_of_visit = foo.latest_date and f.date_of_visit between :startDate and :endDate", nativeQuery=true)
	public List<FollowUpForm> latestFollowUpForms(@Param("startDate")Date startDate,
			@Param("endDate")Date endDate);
	
	@Override
	@Query("Select COUNT(fuf) from FollowUpForm fuf  WHERE fuf.childId.childId like :childId")
	Long countByChildId(@Param("childId")String childId);

}
