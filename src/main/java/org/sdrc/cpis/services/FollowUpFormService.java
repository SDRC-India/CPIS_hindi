package org.sdrc.cpis.services;

import java.sql.Date;
import java.util.List;

import org.sdrc.cpis.models.FollowUpFormModel;

public interface FollowUpFormService {
	public void save(FollowUpFormModel followUpFormModel) throws Exception;

	List<FollowUpFormModel> getFollowUpForm(String childId) throws Exception;
	
}
