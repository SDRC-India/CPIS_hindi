package org.sdrc.cpis.services;

import javax.transaction.Transactional;

import org.sdrc.cpis.models.CCTSChildRegistrationModel;


public interface CCTSChildRegistrationService {

	@Transactional
	String saveChildRegistrationDetails(CCTSChildRegistrationModel cctsChildRegistrationModel) throws Exception;
	
	CCTSChildRegistrationModel getChildRegistration(String childId) throws Exception;
	
	String updateChildRegistration(CCTSChildRegistrationModel cctsChildRegistrationModel)throws Exception ;
	
}
