package org.sdrc.cpis.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.sdrc.cpis.domains.CounterCount;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.TXN_User_LogIn_Meta_Model;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.LoginService;
import org.sdrc.cpis.services.UserService;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.ListObject;
import org.sdrc.cpis.util.StateManager;
import org.sdrc.cpis.util.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController implements AuthenticationProvider {
	
	@Autowired 
	private LoginService loginService;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageDigestPasswordEncoder messageDigest;
	
	@Autowired
	private ResourceBundleMessageSource applicationMessageSource;
	
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping(value="/getLoginData")
	@ResponseBody
	public ListObject loginData(){
		Map<String, Object> mstData = loginService.fetchLoginData();
		ListObject listObject = new ListObject();
		listObject.setValue(mstData);
		/*ArrayList<Object> mstData = new ArrayList<Object>();
		DesignationModel designationModel = new DesignationModel();
		UserDetailModel userDetailModel = new UserDetailModel();
		AreaDetailsModel areaDetailsModel = new AreaDetailsModel();
		
		mstData.add(designationModel);
		mstData.add(userDetailModel);
		mstData.add(areaDetailsModel);*/
		
		return listObject;
	}
	
	@RequestMapping(value= {"/checkCredentials"}, method= {RequestMethod.POST})
//	public String loginController(HttpServletRequest request,
//			RedirectAttributes redirectAttributes,
//			@RequestBody UserDetailModel userDetailModel) throws Exception{
	public String loginController(HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam("username")String userName,@RequestParam("password")String password) throws Exception{
		
		List<String> errMessgs = new ArrayList<>();
		String referer = request.getHeader("referer");
		referer = referer != null ? referer : "/";
		
		try{
//			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetailModel.getUserName(), userDetailModel.getPassword());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
			token.setDetails(new WebAuthenticationDetails(request));
			UserDetailModel userModel = userService.findActiveUserById(userName);
			TXN_User_LogIn_Meta_Model logIn_Meta_Model = loginService.getExistingLogInUser(userModel.getUserId());
			Authentication authentication = this.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
//			TXN_User_LogIn_Meta_Model logIn_Meta_Model = loginService.getExistingLogInUser(userModel.getUserId());
//			loginService.updateLoginMeta(logIn_Meta_Model.getTxnId(), new Timestamp(new Date().getTime()));
			if(authentication!=null && logIn_Meta_Model.getTxnId()!=null){
//				errMessgs.add("user_logged_in");
				errMessgs.add("User Logged In");
				redirectAttributes.addFlashAttribute("formError", errMessgs);
				redirectAttributes.addFlashAttribute("className", "alert alert-danger");
				redirectAttributes.addFlashAttribute("userId", logIn_Meta_Model.getUserDetails().getUserId());
				
				return "redirect:/ccts_login";
			}
			if(userModel.getDesignationId()==11){
				return "redirect:/userList";
			}
		}catch(Exception e){
			SecurityContextHolder.getContext().setAuthentication(null);
			errMessgs.add("Invalid Username or Password");
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("formError", errMessgs);
			redirectAttributes.addFlashAttribute("className", "alert alert-danger");
			LOGGER.error(e);
			return "redirect:/ccts_login";
		}
		
		return "redirect:/ccts";
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
//		String encodedPassword = passwordEncoder.encodePassword(password, userName);
		UserDetailModel userModel = userService.findActiveUserById(userName);
		String encodePassword = messageDigest.encodePassword(userModel.getEmail().split("@")[0], password);
		if(userModel == null || !userModel.getPassword().equals(encodePassword))
			throw new BadCredentialsException("Invalid User!");
		
		stateManager.setValue(Constants.USER_PRINCIPAL, userModel);
		TXN_User_LogIn_Meta_Model logIn_Meta_Model = loginService.getExistingLogInUser(userModel.getUserId());
//		loginService.updateLoginMeta(logIn_Meta_Model.getTxnId(), new Timestamp(new Date().getTime()));
		
		if(logIn_Meta_Model.getTxnId()==null){
			TXN_User_LogIn_Meta_Model txn_User_LogIn_Meta_Model=new TXN_User_LogIn_Meta_Model();
			ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request=attr.getRequest();
			
			String ipAddress=getIpAddr(request);
			txn_User_LogIn_Meta_Model.setUserIp(ipAddress);
			txn_User_LogIn_Meta_Model.setUserDetails(new UserDetails(userModel.getUserId()));
			
			String userAgent=request.getHeader("User-Agent");
			
			txn_User_LogIn_Meta_Model.setUserAgent(userAgent);
			long txnId=loginService.saveTxnLoginMeta(txn_User_LogIn_Meta_Model);
			
			stateManager.setValue("loginTxnId", txnId);
			userModel.setUserIp(ipAddress);
			userModel.setUserAgent(userAgent);
			
			((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).setLoginTxnId(txnId);
		}
	
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpServletRequest request = attr.getRequest();
//		String ipAddress = getIpAddr(request);
//		userModel.setUserIp(ipAddress);
//		String userAgent = request.getHeader("User-Agent");
//		userModel.setUserAgent(userAgent);
//		long txnId = userService.saveLoginMeta(userModel);
		// Save the user login TXN ID to the Session State Manager, for global access.
		
		
		return new UsernamePasswordAuthenticationToken(userName,password,null);
	}
	
	public String getIpAddr(HttpServletRequest request) {      
	   String ip = request.getHeader("x-forwarded-for");      
	   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))       
	       ip = request.getHeader("Proxy-Client-IP");      
	   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))       
	       ip = request.getHeader("WL-Proxy-Client-IP");      
	   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))       
	       ip = request.getRemoteAddr();      
	   return ip;      
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse resp)
			throws IOException, ServletException {
		/**
		 * Update the Log out Info i.e update 
		 */
		HttpSession session=request.getSession(false);
		if(session !=null){
		long txnId = ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getLoginTxnId();
		loginService.updateLoginMeta(txnId, new Timestamp(new Date().getTime()));
		stateManager.setValue(Constants.USER_PRINCIPAL, null);
		request.getSession().setAttribute(Constants.USER_PRINCIPAL, null);
		request.getSession().invalidate();
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attr.getRequest().getSession(true).removeAttribute(Constants.USER_PRINCIPAL);
		attr.getRequest().getSession(true).invalidate();
		request.logout();
		return "redirect:/ccts_login";
		}
		else{
			request.getSession().invalidate();
			return "redirect:/ccts_login";
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.POST)
	public String forgotPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes,HttpServletRequest request) throws IOException, ServletException{
		String url = request.getScheme() + "://" +InetAddress.getLocalHost().getHostAddress()+ ":" + request.getServerPort() + request.getContextPath();
		String check=loginService.forgotWebPassword(email, url);
		if(check=="true"){
			check="";
			redirectAttributes.addFlashAttribute("className","A confirmation mail has been sent.");
		}else
		{
			check="Invalid User";
			redirectAttributes.addFlashAttribute("className","Your email has not been sent.");
		}
		List<String> errMessgs = new ArrayList<String>();
		errMessgs.add(check);
		String referer = request.getHeader("referer");
		referer = referer != null ? referer : "/";
		redirectAttributes.addFlashAttribute("formError",errMessgs);
		return "redirect:" + referer;
	}
	
	@RequestMapping(value="/newPassword", method= {RequestMethod.POST})
	@ResponseBody
	public String resetPassword(@RequestParam(value="email", required=false) byte[] email, @RequestParam(value="password", required=false) String password)
	{
		
//		byte[] decodedEmailId=Base64.decode(email);
		byte[] decodedEmailId=Base64.getDecoder().decode(email);
		String eid=new String(decodedEmailId).split(",")[1];
		
		Integer mId=Integer.parseInt(new String(decodedEmailId).split(",")[0]);
		
		String check=null;
		ValueObject valueObject=new ValueObject();
		String status=null;
		if(password!=null)
		{
			check=userService.updateForgotPassword(eid, password);
			if(check=="true"){
				status="success";
			}else{
				status="fail";
			}
		}
		return status;		
	}
	
	@RequestMapping(value="/changeToNewPassword", method= {RequestMethod.POST})
	@ResponseBody
	public String changeToNewPassword(@RequestParam(value="password", required=false) String password)
	{
		
		UserDetailModel user = null;
		user = ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL));
		String check=null;
		String status=null;
		if(password!=null)
		{
			check=userService.updateForgotPassword(user.getEmail(), password);
			if(check=="true"){
				status="success";
			}else{
				status="fail";
			}
		}
		return status;		
	}
	
	@RequestMapping(value="/resetPassword{resetPassword}")
	public String userPasswordChange(@PathVariable(value="resetPassword") byte[] emailId, @RequestParam(value="password", required=false) String password)
	{
		
//		byte[] decodedEmailId=Base64.decode(emailId);
		byte[] decodedEmailId=Base64.getDecoder().decode(emailId);
		
		
		
		Integer mId=Integer.parseInt(new String(decodedEmailId).split(",")[0]);
		
		if(password!=null)
		{
		resetPassword(emailId, password);
		}
		
		Date date=new Date();
		long currentTime=date.getTime();
//		System.out.println("The current time is : ====================>>>>>>"+currentTime);
		
		
//		System.out.println("Message Id is : ====================================>>>> "+mId);
		
		if(userService.getActivatedEmailId(mId, currentTime).equals("true"))
			return "changePassword";
		else 
			return "link-expired";	
	}
	
	@RequestMapping(value="/logoutUser", method= RequestMethod.POST)
	public void logoutUser(@RequestParam("userId")Integer userId){
		TXN_User_LogIn_Meta_Model logIn_Meta_Model = loginService.getExistingLogInUser(userId);
		loginService.updateLoginMeta(logIn_Meta_Model.getTxnId(), new Timestamp(new Date().getTime()));
		System.out.println("************************************** done *****************************");
	}
	
	@RequestMapping(value = {"/countAdd"}, method = {RequestMethod.GET})
	@ResponseBody
	public CounterCount getCounter(){
		return loginService.getCounter();
	}
}
