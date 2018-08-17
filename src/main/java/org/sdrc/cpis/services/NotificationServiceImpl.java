package org.sdrc.cpis.services;

import java.util.ArrayList;
import java.util.List;

import org.sdrc.cpis.domains.NotificationDetails;
import org.sdrc.cpis.models.NotificationDetailModel;
import org.sdrc.cpis.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	@Override
	public List<NotificationDetailModel> getUnreadNotifications(Integer userId) {
		// TODO Auto-generated method stub
		List<NotificationDetails> unreadNotifications=
				notificationRepository.findByRecipentIdAndIsReadFalseAndIsActiveTrueOrderByNotificationIdDesc(userId);
		List<NotificationDetailModel> notificationModels = new ArrayList<NotificationDetailModel>();
		for (NotificationDetails notificationDetails : unreadNotifications) {
			NotificationDetailModel notificationDetailModel = new NotificationDetailModel();
			notificationDetailModel.setCaseType(notificationDetails.getCaseType());
			notificationDetailModel.setDateOfAction(notificationDetails.getDateOfAction());
			notificationDetailModel.setNotificationType(notificationDetails.getNotificationType());
			notificationDetailModel.setNotificationMsg(notificationDetails.getNotificationMsg());
			notificationDetailModel.setNotificationId(notificationDetails.getNotificationId());
			
			notificationModels.add(notificationDetailModel);
		}
		return notificationModels;
	}
	@Override
	public List<NotificationDetailModel> getActiveNotifications(Integer userId) {
		// TODO Auto-generated method stub
		List<NotificationDetails> allNotifications=
				notificationRepository.findByRecipentIdAndIsActiveTrueOrderByNotificationIdDesc(userId);
		List<NotificationDetailModel> notificationModels = new ArrayList<NotificationDetailModel>();
		for (NotificationDetails notificationDetails : allNotifications) {
			NotificationDetailModel notificationDetailModel = new NotificationDetailModel();
			notificationDetailModel.setCaseType(notificationDetails.getCaseType());
			notificationDetailModel.setDateOfAction(notificationDetails.getDateOfAction());
			notificationDetailModel.setNotificationType(notificationDetails.getNotificationType());
			notificationDetailModel.setNotificationMsg(notificationDetails.getNotificationMsg());
			notificationDetailModel.setNotificationId(notificationDetails.getNotificationId());
			notificationDetailModel.setIsRead(notificationDetails.getIsRead());
			notificationDetailModel.setIsActive(notificationDetails.getIsActive());
			
			notificationModels.add(notificationDetailModel);
		}
		return notificationModels;
	}
	
	@Transactional
	@Override
	public boolean setMarkAsRead(Integer notificationId) {
		// TODO Auto-generated method stub
		notificationRepository.setMarkAsRead(notificationId);
		return true;
	}

}
