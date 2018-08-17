package org.sdrc.cpis.controller;

import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.UserService;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StateManager stateManager;
	
//	@Authorize(feature="changePassword",permission="edit")
	@RequestMapping(value="/openChangePassword")
	public String userPasswordChange()
	{
			return "changePassword";
	}
	
	@RequestMapping(value="/changeUserPassword")
	public String changeUserPassword(@RequestParam(value="email", required=false) String email)
	{
			return "changeUserPassword";
	}
	
	@RequestMapping(value="/changeUserPassword", method= {RequestMethod.POST})
	@ResponseBody
	public String resetPassword(@RequestParam(value="email", required=false) String email, @RequestParam(value="password", required=false) String password, 
			@RequestParam(value="newEmail", required=false) String newEmail)
	{
		
		String check=null;
		String status=null;
		if(password!=null)
		{
			check=userService.changeUserPasswordByAdmin(email, password, newEmail);
		}
		return check;		
	}
	
	@Authorize(feature="adminChangePassword",permission="edit")
	@RequestMapping(value="adminChangePassword")
	public String adminChangePassword()
	{
		return "adminChangePassword";		
	}
	
	@RequestMapping(value="deleteUser", method= {RequestMethod.POST})
	@ResponseBody
	public String deleteUser(@RequestParam(value="userId") Integer userId){
		System.out.println("inside delete user");
//		userService.deleteUser(userId);
		return userService.deleteUser(userId);
	}
	
	@Authorize(feature="userList",permission="view")
	@RequestMapping(value="userList")
	public String getUserList(){
		return "userList";
	}
	
	@Authorize(feature="role_management",permission="edit")
	@RequestMapping(value="role_management")
	public String getUserregPage(){
		return "role_management";
	}
}
