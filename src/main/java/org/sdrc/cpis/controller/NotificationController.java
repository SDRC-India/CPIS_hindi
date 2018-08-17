package org.sdrc.cpis.controller;

import java.util.ArrayList;
import java.util.List;

import org.sdrc.cpis.models.NotificationDetailModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.NotificationService;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private StateManager stateManager;
	
	@RequestMapping("/getNotificationCount")
	@ResponseBody
	public Integer getNotificationCount(){
		UserDetailModel user = ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL));
		List<NotificationDetailModel> notificationModels=new ArrayList<NotificationDetailModel>();
		if(user!=null) {
		notificationModels= notificationService.getUnreadNotifications(user.getUserId());
		return notificationModels.size();
		}
		else {
			return -1;
		}
		
	}
	
	@RequestMapping("/getNotificationList")
	@ResponseBody
	public List<NotificationDetailModel> getNotificationList(){
		UserDetailModel user = ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL));
		List<NotificationDetailModel> notificationModels=new ArrayList<NotificationDetailModel>();
		if(user!=null)
			notificationModels= notificationService.getActiveNotifications(user.getUserId());
		return notificationModels;
	}
	
	@RequestMapping(value="/setMarkAsRead",method=RequestMethod.POST)
	@ResponseBody
	public boolean setMarkAsRead(@RequestParam("notificationId") Integer notificationId){
		return notificationService.setMarkAsRead(notificationId);
	}
}
