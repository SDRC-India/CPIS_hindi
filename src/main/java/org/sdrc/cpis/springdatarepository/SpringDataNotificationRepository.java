package org.sdrc.cpis.springdatarepository;

import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.repository.NotificationRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataNotificationRepository extends
		NotificationRepository, Repository<NotificationDetails, Integer> {
	@Modifying
	@Override
	@Query("update NotificationDetails nd set nd.isRead=true where nd.notificationId=:notificationId")
	void setMarkAsRead(@Param("notificationId")Integer notificationId);
}
