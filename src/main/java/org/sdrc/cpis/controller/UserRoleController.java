package org.sdrc.cpis.controller;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRoleController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageDigestPasswordEncoder messageDigest;
	
	@RequestMapping(value = {"/saveNewUser"}, method = {RequestMethod.POST})
	@ResponseBody
	public String saveNewUser(@RequestBody UserDetailModel userDetailModel,HttpServletRequest request) throws Exception{
		String url = request.getScheme() + "://" +InetAddress.getLocalHost().getHostAddress()+ ":" + request.getServerPort() + request.getContextPath();
		String password=userDetailModel.getPassword();
		String encodePassword = messageDigest.encodePassword((userDetailModel.getEmail()).split("@")[0], userDetailModel.getPassword());
		userDetailModel.setPassword(encodePassword);
		userService.saveNewUser(userDetailModel, url, password);
		return null;
	}
}
