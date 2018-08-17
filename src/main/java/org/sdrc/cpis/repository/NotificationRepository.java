package org.sdrc.cpis.repository;

import java.util.List;

import org.sdrc.cpis.domains.NotificationDetails;
import org.springframework.transaction.annotation.Transactional;

public interface NotificationRepository {
	@Transactional
	void save(Iterable<NotificationDetails> notificationDetails);
	
	List<NotificationDetails> findByRecipentIdAndIsReadFalseAndIsActiveTrueOrderByNotificationIdDesc(Integer userId);
	
	List<NotificationDetails> findByRecipentIdAndIsActiveTrueOrderByNotificationIdDesc(Integer userId);
	
	void setMarkAsRead(Integer notificationId);
}
