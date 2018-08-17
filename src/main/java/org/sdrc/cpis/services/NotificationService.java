package org.sdrc.cpis.services;

import java.util.List;

import org.sdrc.cpis.models.NotificationDetailModel;

public interface NotificationService {
	List<NotificationDetailModel> getUnreadNotifications(Integer userId);
	
	List<NotificationDetailModel> getActiveNotifications(Integer userId);
	
	boolean setMarkAsRead(Integer notificationId);
}
