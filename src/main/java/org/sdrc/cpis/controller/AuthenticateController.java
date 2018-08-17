package org.sdrc.cpis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticateController implements AuthenticationProvider{

	private StateManager stateManager;
	
	@Autowired
	public AuthenticateController(StateManager stateManager) {
		this.stateManager=stateManager;	
	}
//
//	@Autowired
//	private ResourceBundleMessageSource messages;
	
	@RequestMapping(value="/validateLogin", method=RequestMethod.POST)
	public String authorize(HttpServletRequest request, RedirectAttributes redirectAttributes,@RequestParam("username") String userEmail,
			@RequestParam("password") String password, Model model) throws IOException{
		
		List<String> errMessage=new ArrayList<String>();
		try{
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userEmail, password);
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authentication=this.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch(Exception e){
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			errMessage.add("Invalid Credential");
			redirectAttributes.addFlashAttribute("formError", errMessage);
//			redirectAttributes.addFlashAttribute("classeName", alert alert-danger);
			return "redirect:/login";
		}
		return "redirect:/home";

//		if(userEmail.equals("biswa@sdrc.co.in") && password.equals("sdrc@123")){
//			return "redirect:/home";
//		}
//		else{
//			return "redirect:/login";
//		}
//		model.addAttribute("userDetails", ((UserDetailsModel) stateManager.getValue(Constants.USER_PRINCIPAL)));
//		return "redirect:/admin";		
	}
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
//UserDetailsModel userDetailsModel=loginService.findByEmail(authentication.getName());
		UserDetailModel userDetailsModel = new UserDetailModel();
//		if( (userDetailsModel.getRole().getUserRoleName().equalsIgnoreCase("user")) || !userDetailsModel.getPassword().equals(encodePassword))
		if(!authentication.getName().equals("cwc@gmail.com") || !authentication.getCredentials().equals("cwc@123"))	
		throw new BadCredentialsException("Invalid User!");
		userDetailsModel.setUserName(authentication.getName());
		userDetailsModel.setPassword(authentication.getCredentials().toString());
		//if not "encodePassword" then you can use - (String)authentication.getCredentials()
		
		
		// Save the user to the Session State Manager, for global access.
		stateManager.setValue(Constants.USER_PRINCIPAL, userDetailsModel);
		
		/*
		 * Retrieve current request object
		 */
		
		ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attr.getRequest();
		
		
//		String userAgent=request.getHeader("User-Agent");
		
//		txn_User_LogIn_Meta_Model.setUserAgent(userAgent);
//		long txnId=loginService.saveTxnLoginMeta(txn_User_LogIn_Meta_Model);
		
	
		return new UsernamePasswordAuthenticationToken(authentication.getName(), (String)authentication.getCredentials(), null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
