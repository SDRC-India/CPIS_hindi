package org.sdrc.cpis.repository;

import org.sdrc.cpis.domains.EmailActivation;
import org.springframework.transaction.annotation.Transactional;

public interface EmailActivationRepository {

	@Transactional
	EmailActivation save(EmailActivation email);
	
	void updateActivationInfo(Integer mId);
	
	org.sdrc.cpis.domains.EmailActivation getIsActiveEmailId(Integer mId);
	
	EmailActivation findByEmailId(String email);
	
	void deactivateLink(Integer id);
	
	EmailActivation findByEmailIdAndUserIdAndIsLiveTrue(String email, Integer userId);
}
